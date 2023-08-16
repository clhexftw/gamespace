package kotlinx.coroutines;

import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
/* compiled from: CompletionState.kt */
/* loaded from: classes2.dex */
public final class CompletionStateKt {
    public static /* synthetic */ Object toState$default(Object obj, Function1 function1, int i, Object obj2) {
        if ((i & 1) != 0) {
            function1 = null;
        }
        return toState(obj, function1);
    }

    public static final <T> Object toState(Object obj, Function1<? super Throwable, Unit> function1) {
        Throwable m2161exceptionOrNullimpl = Result.m2161exceptionOrNullimpl(obj);
        if (m2161exceptionOrNullimpl == null) {
            return function1 != null ? new CompletedWithCancellation(obj, function1) : obj;
        }
        return new CompletedExceptionally(m2161exceptionOrNullimpl, false, 2, null);
    }

    public static final <T> Object toState(Object obj, CancellableContinuation<?> caller) {
        Intrinsics.checkNotNullParameter(caller, "caller");
        Throwable m2161exceptionOrNullimpl = Result.m2161exceptionOrNullimpl(obj);
        if (m2161exceptionOrNullimpl != null) {
            if (DebugKt.getRECOVER_STACK_TRACES()) {
                CancellableContinuation<?> cancellableContinuation = caller;
                if (cancellableContinuation instanceof CoroutineStackFrame) {
                    m2161exceptionOrNullimpl = StackTraceRecoveryKt.recoverFromStackFrame(m2161exceptionOrNullimpl, (CoroutineStackFrame) cancellableContinuation);
                }
            }
            obj = new CompletedExceptionally(m2161exceptionOrNullimpl, false, 2, null);
        }
        return obj;
    }

    public static final <T> Object recoverResult(Object obj, Continuation<? super T> uCont) {
        Intrinsics.checkNotNullParameter(uCont, "uCont");
        if (obj instanceof CompletedExceptionally) {
            Result.Companion companion = Result.Companion;
            Throwable th = ((CompletedExceptionally) obj).cause;
            if (DebugKt.getRECOVER_STACK_TRACES() && (uCont instanceof CoroutineStackFrame)) {
                th = StackTraceRecoveryKt.recoverFromStackFrame(th, (CoroutineStackFrame) uCont);
            }
            return Result.m2159constructorimpl(ResultKt.createFailure(th));
        }
        Result.Companion companion2 = Result.Companion;
        return Result.m2159constructorimpl(obj);
    }
}
