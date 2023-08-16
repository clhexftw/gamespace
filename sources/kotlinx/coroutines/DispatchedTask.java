package kotlinx.coroutines;

import kotlin.ExceptionsKt__ExceptionsKt;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.DispatchedContinuation;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
import kotlinx.coroutines.internal.ThreadContextKt;
import kotlinx.coroutines.scheduling.Task;
import kotlinx.coroutines.scheduling.TaskContext;
/* compiled from: DispatchedTask.kt */
/* loaded from: classes2.dex */
public abstract class DispatchedTask<T> extends Task {
    public int resumeMode;

    public void cancelCompletedResult$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(Object obj, Throwable cause) {
        Intrinsics.checkNotNullParameter(cause, "cause");
    }

    public abstract Continuation<T> getDelegate$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host();

    /* JADX WARN: Multi-variable type inference failed */
    public <T> T getSuccessfulResult$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(Object obj) {
        return obj;
    }

    public abstract Object takeState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host();

    public DispatchedTask(int i) {
        this.resumeMode = i;
    }

    public final void handleFatalException(Throwable th, Throwable th2) {
        if (th == null && th2 == null) {
            return;
        }
        if (th != null && th2 != null) {
            ExceptionsKt__ExceptionsKt.addSuppressed(th, th2);
        }
        if (th == null) {
            th = th2;
        }
        Intrinsics.checkNotNull(th);
        CoroutineExceptionHandlerKt.handleCoroutineException(getDelegate$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host().getContext(), new CoroutinesInternalError("Fatal exception in coroutines machinery for " + this + ". Please read KDoc to 'handleFatalException' method and report this incident to maintainers", th));
    }

    public Throwable getExceptionalResult$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(Object obj) {
        CompletedExceptionally completedExceptionally = obj instanceof CompletedExceptionally ? (CompletedExceptionally) obj : null;
        if (completedExceptionally != null) {
            return completedExceptionally.cause;
        }
        return null;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Object m2159constructorimpl;
        Object m2159constructorimpl2;
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(this.resumeMode != -1)) {
                throw new AssertionError();
            }
        }
        TaskContext taskContext = this.taskContext;
        try {
            Continuation<T> delegate$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host = getDelegate$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host();
            Intrinsics.checkNotNull(delegate$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host, "null cannot be cast to non-null type kotlinx.coroutines.internal.DispatchedContinuation<T of kotlinx.coroutines.DispatchedTask>");
            DispatchedContinuation dispatchedContinuation = (DispatchedContinuation) delegate$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host;
            Continuation<T> continuation = dispatchedContinuation.continuation;
            Object obj = dispatchedContinuation.countOrElement;
            CoroutineContext context = continuation.getContext();
            Object updateThreadContext = ThreadContextKt.updateThreadContext(context, obj);
            UndispatchedCoroutine<?> updateUndispatchedCompletion = updateThreadContext != ThreadContextKt.NO_THREAD_ELEMENTS ? CoroutineContextKt.updateUndispatchedCompletion(continuation, context, updateThreadContext) : null;
            CoroutineContext context2 = continuation.getContext();
            Object takeState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host = takeState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host();
            Throwable exceptionalResult$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host = getExceptionalResult$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(takeState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host);
            Job job = (exceptionalResult$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host == null && DispatchedTaskKt.isCancellableMode(this.resumeMode)) ? (Job) context2.get(Job.Key) : null;
            if (job != null && !job.isActive()) {
                Throwable cancellationException = job.getCancellationException();
                cancelCompletedResult$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(takeState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host, cancellationException);
                Result.Companion companion = Result.Companion;
                if (DebugKt.getRECOVER_STACK_TRACES() && (continuation instanceof CoroutineStackFrame)) {
                    cancellationException = StackTraceRecoveryKt.access$recoverFromStackFrame(cancellationException, (CoroutineStackFrame) continuation);
                }
                continuation.resumeWith(Result.m2159constructorimpl(ResultKt.createFailure(cancellationException)));
            } else if (exceptionalResult$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host != null) {
                Result.Companion companion2 = Result.Companion;
                continuation.resumeWith(Result.m2159constructorimpl(ResultKt.createFailure(exceptionalResult$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host)));
            } else {
                Result.Companion companion3 = Result.Companion;
                continuation.resumeWith(Result.m2159constructorimpl(getSuccessfulResult$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(takeState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host)));
            }
            Unit unit = Unit.INSTANCE;
            if (updateUndispatchedCompletion == null || updateUndispatchedCompletion.clearThreadContext()) {
                ThreadContextKt.restoreThreadContext(context, updateThreadContext);
            }
            try {
                Result.Companion companion4 = Result.Companion;
                taskContext.afterTask();
                m2159constructorimpl2 = Result.m2159constructorimpl(unit);
            } catch (Throwable th) {
                Result.Companion companion5 = Result.Companion;
                m2159constructorimpl2 = Result.m2159constructorimpl(ResultKt.createFailure(th));
            }
            handleFatalException(null, Result.m2161exceptionOrNullimpl(m2159constructorimpl2));
        } catch (Throwable th2) {
            try {
                Result.Companion companion6 = Result.Companion;
                taskContext.afterTask();
                m2159constructorimpl = Result.m2159constructorimpl(Unit.INSTANCE);
            } catch (Throwable th3) {
                Result.Companion companion7 = Result.Companion;
                m2159constructorimpl = Result.m2159constructorimpl(ResultKt.createFailure(th3));
            }
            handleFatalException(th2, Result.m2161exceptionOrNullimpl(m2159constructorimpl));
        }
    }
}
