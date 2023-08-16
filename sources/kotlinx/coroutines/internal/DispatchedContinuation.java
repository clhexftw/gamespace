package kotlinx.coroutines.internal;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicRef;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CompletedWithCancellation;
import kotlinx.coroutines.CompletionStateKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.DebugStringsKt;
import kotlinx.coroutines.DispatchedTask;
import kotlinx.coroutines.EventLoop;
import kotlinx.coroutines.ThreadLocalEventLoop;
/* compiled from: DispatchedContinuation.kt */
/* loaded from: classes2.dex */
public final class DispatchedContinuation<T> extends DispatchedTask<T> implements CoroutineStackFrame, Continuation<T> {
    private final AtomicRef<Object> _reusableCancellableContinuation;
    public Object _state;
    public final Continuation<T> continuation;
    public final Object countOrElement;
    public final CoroutineDispatcher dispatcher;

    @Override // kotlin.coroutines.Continuation
    public CoroutineContext getContext() {
        return this.continuation.getContext();
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public Continuation<T> getDelegate$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host() {
        return this;
    }

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public StackTraceElement getStackTraceElement() {
        return null;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public DispatchedContinuation(CoroutineDispatcher dispatcher, Continuation<? super T> continuation) {
        super(-1);
        Intrinsics.checkNotNullParameter(dispatcher, "dispatcher");
        Intrinsics.checkNotNullParameter(continuation, "continuation");
        this.dispatcher = dispatcher;
        this.continuation = continuation;
        this._state = DispatchedContinuationKt.access$getUNDEFINED$p();
        this.countOrElement = ThreadContextKt.threadContextElements(getContext());
        this._reusableCancellableContinuation = AtomicFU.atomic((Object) null);
    }

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public CoroutineStackFrame getCallerFrame() {
        Continuation<T> continuation = this.continuation;
        if (continuation instanceof CoroutineStackFrame) {
            return (CoroutineStackFrame) continuation;
        }
        return null;
    }

    private final CancellableContinuationImpl<?> getReusableCancellableContinuation() {
        Object value = this._reusableCancellableContinuation.getValue();
        if (value instanceof CancellableContinuationImpl) {
            return (CancellableContinuationImpl) value;
        }
        return null;
    }

    public final boolean isReusable() {
        return this._reusableCancellableContinuation.getValue() != null;
    }

    public final void awaitReusability() {
        do {
        } while (this._reusableCancellableContinuation.getValue() == DispatchedContinuationKt.REUSABLE_CLAIMED);
    }

    public final void release() {
        awaitReusability();
        CancellableContinuationImpl<?> reusableCancellableContinuation = getReusableCancellableContinuation();
        if (reusableCancellableContinuation != null) {
            reusableCancellableContinuation.detachChild$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host();
        }
    }

    public final CancellableContinuationImpl<T> claimReusableCancellableContinuation() {
        AtomicRef<Object> atomicRef = this._reusableCancellableContinuation;
        while (true) {
            Object value = atomicRef.getValue();
            if (value == null) {
                this._reusableCancellableContinuation.setValue(DispatchedContinuationKt.REUSABLE_CLAIMED);
                return null;
            } else if (value instanceof CancellableContinuationImpl) {
                if (this._reusableCancellableContinuation.compareAndSet(value, DispatchedContinuationKt.REUSABLE_CLAIMED)) {
                    return (CancellableContinuationImpl) value;
                }
            } else if (value != DispatchedContinuationKt.REUSABLE_CLAIMED && !(value instanceof Throwable)) {
                throw new IllegalStateException(("Inconsistent state " + value).toString());
            }
        }
    }

    public final Throwable tryReleaseClaimedContinuation(CancellableContinuation<?> continuation) {
        Symbol symbol;
        Intrinsics.checkNotNullParameter(continuation, "continuation");
        AtomicRef<Object> atomicRef = this._reusableCancellableContinuation;
        do {
            Object value = atomicRef.getValue();
            symbol = DispatchedContinuationKt.REUSABLE_CLAIMED;
            if (value != symbol) {
                if (value instanceof Throwable) {
                    if (!this._reusableCancellableContinuation.compareAndSet(value, null)) {
                        throw new IllegalArgumentException("Failed requirement.".toString());
                    }
                    return (Throwable) value;
                }
                throw new IllegalStateException(("Inconsistent state " + value).toString());
            }
        } while (!this._reusableCancellableContinuation.compareAndSet(symbol, continuation));
        return null;
    }

    public final boolean postponeCancellation(Throwable cause) {
        Intrinsics.checkNotNullParameter(cause, "cause");
        AtomicRef<Object> atomicRef = this._reusableCancellableContinuation;
        while (true) {
            Object value = atomicRef.getValue();
            Symbol symbol = DispatchedContinuationKt.REUSABLE_CLAIMED;
            if (Intrinsics.areEqual(value, symbol)) {
                if (this._reusableCancellableContinuation.compareAndSet(symbol, cause)) {
                    return true;
                }
            } else if (value instanceof Throwable) {
                return true;
            } else {
                if (this._reusableCancellableContinuation.compareAndSet(value, null)) {
                    return false;
                }
            }
        }
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public Object takeState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host() {
        Object obj = this._state;
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(obj != DispatchedContinuationKt.access$getUNDEFINED$p())) {
                throw new AssertionError();
            }
        }
        this._state = DispatchedContinuationKt.access$getUNDEFINED$p();
        return obj;
    }

    @Override // kotlin.coroutines.Continuation
    public void resumeWith(Object obj) {
        CoroutineContext context = this.continuation.getContext();
        Object state$default = CompletionStateKt.toState$default(obj, null, 1, null);
        if (this.dispatcher.isDispatchNeeded(context)) {
            this._state = state$default;
            this.resumeMode = 0;
            this.dispatcher.mo2186dispatch(context, this);
            return;
        }
        DebugKt.getASSERTIONS_ENABLED();
        EventLoop eventLoop$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host = ThreadLocalEventLoop.INSTANCE.getEventLoop$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host();
        if (!eventLoop$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host.isUnconfinedLoopActive()) {
            eventLoop$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host.incrementUseCount(true);
            try {
                CoroutineContext context2 = getContext();
                Object updateThreadContext = ThreadContextKt.updateThreadContext(context2, this.countOrElement);
                this.continuation.resumeWith(obj);
                Unit unit = Unit.INSTANCE;
                ThreadContextKt.restoreThreadContext(context2, updateThreadContext);
                do {
                } while (eventLoop$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host.processUnconfinedEvent());
            } finally {
                try {
                    return;
                } finally {
                }
            }
            return;
        }
        this._state = state$default;
        this.resumeMode = 0;
        eventLoop$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host.dispatchUnconfined(this);
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public void cancelCompletedResult$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(Object obj, Throwable cause) {
        Intrinsics.checkNotNullParameter(cause, "cause");
        if (obj instanceof CompletedWithCancellation) {
            ((CompletedWithCancellation) obj).onCancellation.invoke(cause);
        }
    }

    public String toString() {
        CoroutineDispatcher coroutineDispatcher = this.dispatcher;
        String debugString = DebugStringsKt.toDebugString(this.continuation);
        return "DispatchedContinuation[" + coroutineDispatcher + ", " + debugString + "]";
    }
}
