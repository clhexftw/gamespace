package kotlinx.coroutines;

import java.util.concurrent.CancellationException;
import kotlin.KotlinNothingValueException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicInt;
import kotlinx.atomicfu.AtomicRef;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.internal.DispatchedContinuation;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
import kotlinx.coroutines.internal.Symbol;
/* compiled from: CancellableContinuationImpl.kt */
/* loaded from: classes2.dex */
public class CancellableContinuationImpl<T> extends DispatchedTask<T> implements CancellableContinuation<T>, CoroutineStackFrame {
    private final AtomicInt _decision;
    private final AtomicRef<Object> _state;
    private final CoroutineContext context;
    private final Continuation<T> delegate;
    private DisposableHandle parentHandle;

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public StackTraceElement getStackTraceElement() {
        return null;
    }

    protected String nameString() {
        return "CancellableContinuation";
    }

    private final void callCancelHandler(Function1<? super Throwable, Unit> function1, Throwable th) {
        try {
            function1.invoke(th);
        } catch (Throwable th2) {
            CoroutineContext context = getContext();
            CoroutineExceptionHandlerKt.handleCoroutineException(context, new CompletionHandlerException("Exception in invokeOnCancellation handler for " + this, th2));
        }
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public final Continuation<T> getDelegate$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host() {
        return this.delegate;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public CancellableContinuationImpl(Continuation<? super T> delegate, int i) {
        super(i);
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        this.delegate = delegate;
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(i != -1)) {
                throw new AssertionError();
            }
        }
        this.context = delegate.getContext();
        this._decision = AtomicFU.atomic(0);
        this._state = AtomicFU.atomic(Active.INSTANCE);
    }

    @Override // kotlin.coroutines.Continuation
    public CoroutineContext getContext() {
        return this.context;
    }

    public final Object getState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host() {
        return this._state.getValue();
    }

    public boolean isCompleted() {
        return !(getState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host() instanceof NotCompleted);
    }

    private final String getStateDebugRepresentation() {
        Object state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host = getState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host();
        return state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host instanceof NotCompleted ? "Active" : state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host instanceof CancelledContinuation ? "Cancelled" : "Completed";
    }

    public void initCancellability() {
        DisposableHandle installParentHandle = installParentHandle();
        if (installParentHandle != null && isCompleted()) {
            installParentHandle.dispose();
            this.parentHandle = NonDisposableHandle.INSTANCE;
        }
    }

    private final boolean isReusable() {
        if (DispatchedTaskKt.isReusableMode(this.resumeMode)) {
            Continuation<T> continuation = this.delegate;
            Intrinsics.checkNotNull(continuation, "null cannot be cast to non-null type kotlinx.coroutines.internal.DispatchedContinuation<*>");
            if (((DispatchedContinuation) continuation).isReusable()) {
                return true;
            }
        }
        return false;
    }

    public final boolean resetStateReusable() {
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(this.resumeMode == 2)) {
                throw new AssertionError();
            }
        }
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(this.parentHandle != NonDisposableHandle.INSTANCE)) {
                throw new AssertionError();
            }
        }
        Object value = this._state.getValue();
        if (!DebugKt.getASSERTIONS_ENABLED() || (!(value instanceof NotCompleted))) {
            if ((value instanceof CompletedContinuation) && ((CompletedContinuation) value).idempotentResume != null) {
                detachChild$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host();
                return false;
            }
            this._decision.setValue(0);
            this._state.setValue(Active.INSTANCE);
            return true;
        }
        throw new AssertionError();
    }

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public CoroutineStackFrame getCallerFrame() {
        Continuation<T> continuation = this.delegate;
        if (continuation instanceof CoroutineStackFrame) {
            return (CoroutineStackFrame) continuation;
        }
        return null;
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public Object takeState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host() {
        return getState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host();
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public void cancelCompletedResult$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(Object obj, Throwable cause) {
        Intrinsics.checkNotNullParameter(cause, "cause");
        AtomicRef<Object> atomicRef = this._state;
        while (true) {
            Object value = atomicRef.getValue();
            if (value instanceof NotCompleted) {
                throw new IllegalStateException("Not completed".toString());
            }
            if (value instanceof CompletedExceptionally) {
                return;
            }
            if (value instanceof CompletedContinuation) {
                CompletedContinuation completedContinuation = (CompletedContinuation) value;
                if (!(!completedContinuation.getCancelled())) {
                    throw new IllegalStateException("Must be called at most once".toString());
                }
                if (this._state.compareAndSet(value, CompletedContinuation.copy$default(completedContinuation, null, null, null, null, cause, 15, null))) {
                    completedContinuation.invokeHandlers(this, cause);
                    return;
                }
            } else if (this._state.compareAndSet(value, new CompletedContinuation(value, null, null, null, cause, 14, null))) {
                return;
            }
        }
    }

    private final boolean cancelLater(Throwable th) {
        if (isReusable()) {
            Continuation<T> continuation = this.delegate;
            Intrinsics.checkNotNull(continuation, "null cannot be cast to non-null type kotlinx.coroutines.internal.DispatchedContinuation<*>");
            return ((DispatchedContinuation) continuation).postponeCancellation(th);
        }
        return false;
    }

    public boolean cancel(Throwable th) {
        Object value;
        boolean z;
        AtomicRef<Object> atomicRef = this._state;
        do {
            value = atomicRef.getValue();
            if (!(value instanceof NotCompleted)) {
                return false;
            }
            z = value instanceof CancelHandler;
        } while (!this._state.compareAndSet(value, new CancelledContinuation(this, th, z)));
        CancelHandler cancelHandler = z ? (CancelHandler) value : null;
        if (cancelHandler != null) {
            callCancelHandler(cancelHandler, th);
        }
        detachChildIfNonResuable();
        dispatchResume(this.resumeMode);
        return true;
    }

    public final void parentCancelled$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(Throwable cause) {
        Intrinsics.checkNotNullParameter(cause, "cause");
        if (cancelLater(cause)) {
            return;
        }
        cancel(cause);
        detachChildIfNonResuable();
    }

    public final void callCancelHandler(CancelHandler handler, Throwable th) {
        Intrinsics.checkNotNullParameter(handler, "handler");
        try {
            handler.invoke(th);
        } catch (Throwable th2) {
            CoroutineContext context = getContext();
            CoroutineExceptionHandlerKt.handleCoroutineException(context, new CompletionHandlerException("Exception in invokeOnCancellation handler for " + this, th2));
        }
    }

    public final void callOnCancellation(Function1<? super Throwable, Unit> onCancellation, Throwable cause) {
        Intrinsics.checkNotNullParameter(onCancellation, "onCancellation");
        Intrinsics.checkNotNullParameter(cause, "cause");
        try {
            onCancellation.invoke(cause);
        } catch (Throwable th) {
            CoroutineContext context = getContext();
            CoroutineExceptionHandlerKt.handleCoroutineException(context, new CompletionHandlerException("Exception in resume onCancellation handler for " + this, th));
        }
    }

    public Throwable getContinuationCancellationCause(Job parent) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        return parent.getCancellationException();
    }

    private final boolean trySuspend() {
        AtomicInt atomicInt = this._decision;
        do {
            int value = atomicInt.getValue();
            if (value != 0) {
                if (value == 2) {
                    return false;
                }
                throw new IllegalStateException("Already suspended".toString());
            }
        } while (!this._decision.compareAndSet(0, 1));
        return true;
    }

    private final boolean tryResume() {
        AtomicInt atomicInt = this._decision;
        do {
            int value = atomicInt.getValue();
            if (value != 0) {
                if (value == 1) {
                    return false;
                }
                throw new IllegalStateException("Already resumed".toString());
            }
        } while (!this._decision.compareAndSet(0, 2));
        return true;
    }

    public final Object getResult() {
        Job job;
        Object coroutine_suspended;
        boolean isReusable = isReusable();
        if (trySuspend()) {
            if (this.parentHandle == null) {
                installParentHandle();
            }
            if (isReusable) {
                releaseClaimedReusableContinuation();
            }
            coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            return coroutine_suspended;
        }
        if (isReusable) {
            releaseClaimedReusableContinuation();
        }
        Object state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host = getState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host();
        if (state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host instanceof CompletedExceptionally) {
            Throwable th = ((CompletedExceptionally) state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host).cause;
            if (DebugKt.getRECOVER_STACK_TRACES()) {
                throw StackTraceRecoveryKt.access$recoverFromStackFrame(th, this);
            }
            throw th;
        } else if (DispatchedTaskKt.isCancellableMode(this.resumeMode) && (job = (Job) getContext().get(Job.Key)) != null && !job.isActive()) {
            CancellationException cancellationException = job.getCancellationException();
            cancelCompletedResult$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host, cancellationException);
            if (DebugKt.getRECOVER_STACK_TRACES()) {
                throw StackTraceRecoveryKt.access$recoverFromStackFrame(cancellationException, this);
            }
            throw cancellationException;
        } else {
            return getSuccessfulResult$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host);
        }
    }

    private final DisposableHandle installParentHandle() {
        Job job = (Job) getContext().get(Job.Key);
        if (job == null) {
            return null;
        }
        DisposableHandle invokeOnCompletion$default = Job.DefaultImpls.invokeOnCompletion$default(job, true, false, new ChildContinuation(this), 2, null);
        this.parentHandle = invokeOnCompletion$default;
        return invokeOnCompletion$default;
    }

    private final void releaseClaimedReusableContinuation() {
        Throwable tryReleaseClaimedContinuation;
        Continuation<T> continuation = this.delegate;
        DispatchedContinuation dispatchedContinuation = continuation instanceof DispatchedContinuation ? (DispatchedContinuation) continuation : null;
        if (dispatchedContinuation == null || (tryReleaseClaimedContinuation = dispatchedContinuation.tryReleaseClaimedContinuation(this)) == null) {
            return;
        }
        detachChild$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host();
        cancel(tryReleaseClaimedContinuation);
    }

    @Override // kotlin.coroutines.Continuation
    public void resumeWith(Object obj) {
        resumeImpl$default(this, CompletionStateKt.toState(obj, this), this.resumeMode, null, 4, null);
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public void resume(T t, Function1<? super Throwable, Unit> function1) {
        resumeImpl(t, this.resumeMode, function1);
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public void invokeOnCancellation(Function1<? super Throwable, Unit> handler) {
        Intrinsics.checkNotNullParameter(handler, "handler");
        CancelHandler makeCancelHandler = makeCancelHandler(handler);
        AtomicRef<Object> atomicRef = this._state;
        while (true) {
            Object value = atomicRef.getValue();
            if (value instanceof Active) {
                if (this._state.compareAndSet(value, makeCancelHandler)) {
                    return;
                }
            } else if (value instanceof CancelHandler) {
                multipleHandlersError(handler, value);
            } else {
                boolean z = value instanceof CompletedExceptionally;
                if (z) {
                    CompletedExceptionally completedExceptionally = (CompletedExceptionally) value;
                    if (!completedExceptionally.makeHandled()) {
                        multipleHandlersError(handler, value);
                    }
                    if (value instanceof CancelledContinuation) {
                        if (!z) {
                            completedExceptionally = null;
                        }
                        callCancelHandler(handler, completedExceptionally != null ? completedExceptionally.cause : null);
                        return;
                    }
                    return;
                } else if (value instanceof CompletedContinuation) {
                    CompletedContinuation completedContinuation = (CompletedContinuation) value;
                    if (completedContinuation.cancelHandler != null) {
                        multipleHandlersError(handler, value);
                    }
                    if (makeCancelHandler instanceof BeforeResumeCancelHandler) {
                        return;
                    }
                    if (completedContinuation.getCancelled()) {
                        callCancelHandler(handler, completedContinuation.cancelCause);
                        return;
                    } else {
                        if (this._state.compareAndSet(value, CompletedContinuation.copy$default(completedContinuation, null, makeCancelHandler, null, null, null, 29, null))) {
                            return;
                        }
                    }
                } else if (makeCancelHandler instanceof BeforeResumeCancelHandler) {
                    return;
                } else {
                    if (this._state.compareAndSet(value, new CompletedContinuation(value, makeCancelHandler, null, null, null, 28, null))) {
                        return;
                    }
                }
            }
        }
    }

    private final void multipleHandlersError(Function1<? super Throwable, Unit> function1, Object obj) {
        throw new IllegalStateException(("It's prohibited to register multiple handlers, tried to register " + function1 + ", already has " + obj).toString());
    }

    private final CancelHandler makeCancelHandler(Function1<? super Throwable, Unit> function1) {
        return function1 instanceof CancelHandler ? (CancelHandler) function1 : new InvokeOnCancel(function1);
    }

    private final void dispatchResume(int i) {
        if (tryResume()) {
            return;
        }
        DispatchedTaskKt.dispatch(this, i);
    }

    private final Object resumedState(NotCompleted notCompleted, Object obj, int i, Function1<? super Throwable, Unit> function1, Object obj2) {
        if (obj instanceof CompletedExceptionally) {
            if (DebugKt.getASSERTIONS_ENABLED()) {
                if (!(obj2 == null)) {
                    throw new AssertionError();
                }
            }
            if (DebugKt.getASSERTIONS_ENABLED()) {
                if (function1 == null) {
                    return obj;
                }
                throw new AssertionError();
            }
            return obj;
        } else if (DispatchedTaskKt.isCancellableMode(i) || obj2 != null) {
            if (function1 != null || (((notCompleted instanceof CancelHandler) && !(notCompleted instanceof BeforeResumeCancelHandler)) || obj2 != null)) {
                return new CompletedContinuation(obj, notCompleted instanceof CancelHandler ? (CancelHandler) notCompleted : null, function1, obj2, null, 16, null);
            }
            return obj;
        } else {
            return obj;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ void resumeImpl$default(CancellableContinuationImpl cancellableContinuationImpl, Object obj, int i, Function1 function1, int i2, Object obj2) {
        if (obj2 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: resumeImpl");
        }
        if ((i2 & 4) != 0) {
            function1 = null;
        }
        cancellableContinuationImpl.resumeImpl(obj, i, function1);
    }

    private final void resumeImpl(Object obj, int i, Function1<? super Throwable, Unit> function1) {
        Object value;
        AtomicRef<Object> atomicRef = this._state;
        do {
            value = atomicRef.getValue();
            if (value instanceof NotCompleted) {
            } else {
                if (value instanceof CancelledContinuation) {
                    CancelledContinuation cancelledContinuation = (CancelledContinuation) value;
                    if (cancelledContinuation.makeResumed()) {
                        if (function1 != null) {
                            callOnCancellation(function1, cancelledContinuation.cause);
                            return;
                        }
                        return;
                    }
                }
                alreadyResumedError(obj);
                throw new KotlinNothingValueException();
            }
        } while (!this._state.compareAndSet(value, resumedState((NotCompleted) value, obj, i, function1, null)));
        detachChildIfNonResuable();
        dispatchResume(i);
    }

    private final Symbol tryResumeImpl(Object obj, Object obj2, Function1<? super Throwable, Unit> function1) {
        Object value;
        AtomicRef<Object> atomicRef = this._state;
        do {
            value = atomicRef.getValue();
            if (value instanceof NotCompleted) {
            } else if (!(value instanceof CompletedContinuation) || obj2 == null) {
                return null;
            } else {
                CompletedContinuation completedContinuation = (CompletedContinuation) value;
                if (completedContinuation.idempotentResume == obj2) {
                    if (!DebugKt.getASSERTIONS_ENABLED() || Intrinsics.areEqual(completedContinuation.result, obj)) {
                        return CancellableContinuationImplKt.RESUME_TOKEN;
                    }
                    throw new AssertionError();
                }
                return null;
            }
        } while (!this._state.compareAndSet(value, resumedState((NotCompleted) value, obj, this.resumeMode, function1, obj2)));
        detachChildIfNonResuable();
        return CancellableContinuationImplKt.RESUME_TOKEN;
    }

    private final Void alreadyResumedError(Object obj) {
        throw new IllegalStateException(("Already resumed, but proposed with update " + obj).toString());
    }

    private final void detachChildIfNonResuable() {
        if (isReusable()) {
            return;
        }
        detachChild$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host();
    }

    public final void detachChild$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host() {
        DisposableHandle disposableHandle = this.parentHandle;
        if (disposableHandle == null) {
            return;
        }
        disposableHandle.dispose();
        this.parentHandle = NonDisposableHandle.INSTANCE;
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public Object tryResume(T t, Object obj, Function1<? super Throwable, Unit> function1) {
        return tryResumeImpl(t, obj, function1);
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public void completeResume(Object token) {
        Intrinsics.checkNotNullParameter(token, "token");
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(token == CancellableContinuationImplKt.RESUME_TOKEN)) {
                throw new AssertionError();
            }
        }
        dispatchResume(this.resumeMode);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.coroutines.DispatchedTask
    public <T> T getSuccessfulResult$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(Object obj) {
        return obj instanceof CompletedContinuation ? (T) ((CompletedContinuation) obj).result : obj;
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public Throwable getExceptionalResult$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(Object obj) {
        Throwable exceptionalResult$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host = super.getExceptionalResult$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(obj);
        if (exceptionalResult$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host != null) {
            Continuation<T> continuation = this.delegate;
            return (DebugKt.getRECOVER_STACK_TRACES() && (continuation instanceof CoroutineStackFrame)) ? StackTraceRecoveryKt.access$recoverFromStackFrame(exceptionalResult$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host, (CoroutineStackFrame) continuation) : exceptionalResult$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host;
        }
        return null;
    }

    public String toString() {
        String nameString = nameString();
        String debugString = DebugStringsKt.toDebugString(this.delegate);
        String stateDebugRepresentation = getStateDebugRepresentation();
        String hexAddress = DebugStringsKt.getHexAddress(this);
        return nameString + "(" + debugString + "){" + stateDebugRepresentation + "}@" + hexAddress;
    }
}
