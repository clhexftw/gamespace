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
/* loaded from: classes.dex */
public final class CompletionStateKt {
    public static /* synthetic */ Object toState$default(Object obj, Function1 function1, int i, Object obj2) {
        if ((i & 1) != 0) {
            function1 = null;
        }
        return toState(obj, function1);
    }

    public static final <T> Object toState(Object obj, Function1<? super Throwable, Unit> function1) {
        Throwable m106exceptionOrNullimpl = Result.m106exceptionOrNullimpl(obj);
        if (m106exceptionOrNullimpl == null) {
            return function1 != null ? new CompletedWithCancellation(obj, function1) : obj;
        }
        return new CompletedExceptionally(m106exceptionOrNullimpl, false, 2, null);
    }

    public static final <T> Object toState(Object obj, CancellableContinuation<?> caller) {
        Intrinsics.checkNotNullParameter(caller, "caller");
        Throwable m106exceptionOrNullimpl = Result.m106exceptionOrNullimpl(obj);
        if (m106exceptionOrNullimpl != null) {
            if (DebugKt.getRECOVER_STACK_TRACES()) {
                CancellableContinuation<?> cancellableContinuation = caller;
                if (cancellableContinuation instanceof CoroutineStackFrame) {
                    m106exceptionOrNullimpl = StackTraceRecoveryKt.recoverFromStackFrame(m106exceptionOrNullimpl, (CoroutineStackFrame) cancellableContinuation);
                }
            }
            obj = new CompletedExceptionally(m106exceptionOrNullimpl, false, 2, null);
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
            return Result.m104constructorimpl(ResultKt.createFailure(th));
        }
        Result.Companion companion2 = Result.Companion;
        return Result.m104constructorimpl(obj);
    }
}
