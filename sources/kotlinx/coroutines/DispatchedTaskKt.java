package kotlinx.coroutines;

import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.DispatchedContinuation;
import kotlinx.coroutines.internal.ThreadContextKt;
/* compiled from: DispatchedTask.kt */
/* loaded from: classes.dex */
public final class DispatchedTaskKt {
    public static final boolean isCancellableMode(int i) {
        return i == 1 || i == 2;
    }

    public static final boolean isReusableMode(int i) {
        return i == 2;
    }

    public static final <T> void dispatch(DispatchedTask<? super T> dispatchedTask, int i) {
        Intrinsics.checkNotNullParameter(dispatchedTask, "<this>");
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(i != -1)) {
                throw new AssertionError();
            }
        }
        Continuation<? super T> delegate$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host = dispatchedTask.getDelegate$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host();
        boolean z = i == 4;
        if (!z && (delegate$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host instanceof DispatchedContinuation) && isCancellableMode(i) == isCancellableMode(dispatchedTask.resumeMode)) {
            CoroutineDispatcher coroutineDispatcher = ((DispatchedContinuation) delegate$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host).dispatcher;
            CoroutineContext context = delegate$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host.getContext();
            if (coroutineDispatcher.isDispatchNeeded(context)) {
                coroutineDispatcher.mo112dispatch(context, dispatchedTask);
                return;
            } else {
                resumeUnconfined(dispatchedTask);
                return;
            }
        }
        resume(dispatchedTask, delegate$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host, z);
    }

    public static final <T> void resume(DispatchedTask<? super T> dispatchedTask, Continuation<? super T> delegate, boolean z) {
        Object successfulResult$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host;
        boolean clearThreadContext;
        Intrinsics.checkNotNullParameter(dispatchedTask, "<this>");
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        Object takeState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host = dispatchedTask.takeState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host();
        Throwable exceptionalResult$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host = dispatchedTask.getExceptionalResult$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(takeState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host);
        if (exceptionalResult$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host != null) {
            Result.Companion companion = Result.Companion;
            successfulResult$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host = ResultKt.createFailure(exceptionalResult$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host);
        } else {
            Result.Companion companion2 = Result.Companion;
            successfulResult$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host = dispatchedTask.getSuccessfulResult$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(takeState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host);
        }
        Object m104constructorimpl = Result.m104constructorimpl(successfulResult$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host);
        if (z) {
            DispatchedContinuation dispatchedContinuation = (DispatchedContinuation) delegate;
            Continuation<T> continuation = dispatchedContinuation.continuation;
            Object obj = dispatchedContinuation.countOrElement;
            CoroutineContext context = continuation.getContext();
            Object updateThreadContext = ThreadContextKt.updateThreadContext(context, obj);
            UndispatchedCoroutine<?> updateUndispatchedCompletion = updateThreadContext != ThreadContextKt.NO_THREAD_ELEMENTS ? CoroutineContextKt.updateUndispatchedCompletion(continuation, context, updateThreadContext) : null;
            try {
                dispatchedContinuation.continuation.resumeWith(m104constructorimpl);
                Unit unit = Unit.INSTANCE;
                if (updateUndispatchedCompletion != null) {
                    if (!clearThreadContext) {
                        return;
                    }
                }
                return;
            } finally {
                if (updateUndispatchedCompletion == null || updateUndispatchedCompletion.clearThreadContext()) {
                    ThreadContextKt.restoreThreadContext(context, updateThreadContext);
                }
            }
        }
        delegate.resumeWith(m104constructorimpl);
    }

    private static final void resumeUnconfined(DispatchedTask<?> dispatchedTask) {
        EventLoop eventLoop$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host = ThreadLocalEventLoop.INSTANCE.getEventLoop$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host();
        if (eventLoop$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host.isUnconfinedLoopActive()) {
            eventLoop$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host.dispatchUnconfined(dispatchedTask);
            return;
        }
        eventLoop$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host.incrementUseCount(true);
        try {
            resume(dispatchedTask, dispatchedTask.getDelegate$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(), true);
            do {
            } while (eventLoop$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host.processUnconfinedEvent());
        } finally {
            try {
            } finally {
            }
        }
    }
}
