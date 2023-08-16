package kotlinx.coroutines.internal;

import java.util.concurrent.CancellationException;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CompletionStateKt;
import kotlinx.coroutines.CoroutineContextKt;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.EventLoop;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.ThreadLocalEventLoop;
import kotlinx.coroutines.UndispatchedCoroutine;
/* compiled from: DispatchedContinuation.kt */
/* loaded from: classes2.dex */
public final class DispatchedContinuationKt {
    private static final Symbol UNDEFINED = new Symbol("UNDEFINED");
    public static final Symbol REUSABLE_CLAIMED = new Symbol("REUSABLE_CLAIMED");

    public static final /* synthetic */ Symbol access$getUNDEFINED$p() {
        return UNDEFINED;
    }

    public static /* synthetic */ void resumeCancellableWith$default(Continuation continuation, Object obj, Function1 function1, int i, Object obj2) {
        if ((i & 2) != 0) {
            function1 = null;
        }
        resumeCancellableWith(continuation, obj, function1);
    }

    public static final <T> void resumeCancellableWith(Continuation<? super T> continuation, Object obj, Function1<? super Throwable, Unit> function1) {
        boolean z;
        Intrinsics.checkNotNullParameter(continuation, "<this>");
        if (continuation instanceof DispatchedContinuation) {
            DispatchedContinuation dispatchedContinuation = (DispatchedContinuation) continuation;
            Object state = CompletionStateKt.toState(obj, function1);
            if (dispatchedContinuation.dispatcher.isDispatchNeeded(dispatchedContinuation.getContext())) {
                dispatchedContinuation._state = state;
                dispatchedContinuation.resumeMode = 1;
                dispatchedContinuation.dispatcher.mo2186dispatch(dispatchedContinuation.getContext(), dispatchedContinuation);
                return;
            }
            DebugKt.getASSERTIONS_ENABLED();
            EventLoop eventLoop$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host = ThreadLocalEventLoop.INSTANCE.getEventLoop$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host();
            if (!eventLoop$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host.isUnconfinedLoopActive()) {
                eventLoop$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host.incrementUseCount(true);
                try {
                    Job job = (Job) dispatchedContinuation.getContext().get(Job.Key);
                    if (job == null || job.isActive()) {
                        z = false;
                    } else {
                        CancellationException cancellationException = job.getCancellationException();
                        dispatchedContinuation.cancelCompletedResult$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(state, cancellationException);
                        Result.Companion companion = Result.Companion;
                        dispatchedContinuation.resumeWith(Result.m2159constructorimpl(ResultKt.createFailure(cancellationException)));
                        z = true;
                    }
                    if (!z) {
                        Continuation<T> continuation2 = dispatchedContinuation.continuation;
                        Object obj2 = dispatchedContinuation.countOrElement;
                        CoroutineContext context = continuation2.getContext();
                        Object updateThreadContext = ThreadContextKt.updateThreadContext(context, obj2);
                        UndispatchedCoroutine<?> updateUndispatchedCompletion = updateThreadContext != ThreadContextKt.NO_THREAD_ELEMENTS ? CoroutineContextKt.updateUndispatchedCompletion(continuation2, context, updateThreadContext) : null;
                        dispatchedContinuation.continuation.resumeWith(obj);
                        Unit unit = Unit.INSTANCE;
                        if (updateUndispatchedCompletion == null || updateUndispatchedCompletion.clearThreadContext()) {
                            ThreadContextKt.restoreThreadContext(context, updateThreadContext);
                        }
                    }
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
            dispatchedContinuation._state = state;
            dispatchedContinuation.resumeMode = 1;
            eventLoop$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host.dispatchUnconfined(dispatchedContinuation);
            return;
        }
        continuation.resumeWith(obj);
    }
}
