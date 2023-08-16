package kotlinx.coroutines.intrinsics;

import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.coroutines.CompletedExceptionally;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.JobSupportKt;
import kotlinx.coroutines.internal.ScopeCoroutine;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
import kotlinx.coroutines.internal.ThreadContextKt;
/* compiled from: Undispatched.kt */
/* loaded from: classes2.dex */
public final class UndispatchedKt {
    public static final <R, T> void startCoroutineUndispatched(Function2<? super R, ? super Continuation<? super T>, ? extends Object> function2, R r, Continuation<? super T> completion) {
        Object coroutine_suspended;
        Intrinsics.checkNotNullParameter(function2, "<this>");
        Intrinsics.checkNotNullParameter(completion, "completion");
        Continuation probeCoroutineCreated = DebugProbesKt.probeCoroutineCreated(completion);
        try {
            CoroutineContext context = completion.getContext();
            Object updateThreadContext = ThreadContextKt.updateThreadContext(context, null);
            Object invoke = ((Function2) TypeIntrinsics.beforeCheckcastToFunctionOfArity(function2, 2)).invoke(r, probeCoroutineCreated);
            ThreadContextKt.restoreThreadContext(context, updateThreadContext);
            coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (invoke != coroutine_suspended) {
                Result.Companion companion = Result.Companion;
                probeCoroutineCreated.resumeWith(Result.m2159constructorimpl(invoke));
            }
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            probeCoroutineCreated.resumeWith(Result.m2159constructorimpl(ResultKt.createFailure(th)));
        }
    }

    public static final <T, R> Object startUndispatchedOrReturn(ScopeCoroutine<? super T> scopeCoroutine, R r, Function2<? super R, ? super Continuation<? super T>, ? extends Object> block) {
        Object completedExceptionally;
        Object coroutine_suspended;
        Throwable recoverFromStackFrame;
        Object coroutine_suspended2;
        Object coroutine_suspended3;
        Intrinsics.checkNotNullParameter(scopeCoroutine, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        try {
            completedExceptionally = ((Function2) TypeIntrinsics.beforeCheckcastToFunctionOfArity(block, 2)).invoke(r, scopeCoroutine);
        } catch (Throwable th) {
            completedExceptionally = new CompletedExceptionally(th, false, 2, null);
        }
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (completedExceptionally == coroutine_suspended) {
            coroutine_suspended3 = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            return coroutine_suspended3;
        }
        Object makeCompletingOnce$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host = scopeCoroutine.makeCompletingOnce$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(completedExceptionally);
        if (makeCompletingOnce$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host == JobSupportKt.COMPLETING_WAITING_CHILDREN) {
            coroutine_suspended2 = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            return coroutine_suspended2;
        } else if (makeCompletingOnce$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host instanceof CompletedExceptionally) {
            Throwable th2 = ((CompletedExceptionally) makeCompletingOnce$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host).cause;
            Continuation<? super T> continuation = scopeCoroutine.uCont;
            if (DebugKt.getRECOVER_STACK_TRACES() && (continuation instanceof CoroutineStackFrame)) {
                recoverFromStackFrame = StackTraceRecoveryKt.recoverFromStackFrame(th2, (CoroutineStackFrame) continuation);
                throw recoverFromStackFrame;
            }
            throw th2;
        } else {
            return JobSupportKt.unboxState(makeCompletingOnce$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host);
        }
    }
}
