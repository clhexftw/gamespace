package kotlinx.coroutines;

import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.DispatchedContinuation;
/* compiled from: DebugStrings.kt */
/* loaded from: classes.dex */
public final class DebugStringsKt {
    public static final String getHexAddress(Object obj) {
        Intrinsics.checkNotNullParameter(obj, "<this>");
        String hexString = Integer.toHexString(System.identityHashCode(obj));
        Intrinsics.checkNotNullExpressionValue(hexString, "toHexString(System.identityHashCode(this))");
        return hexString;
    }

    public static final String toDebugString(Continuation<?> continuation) {
        String m104constructorimpl;
        Intrinsics.checkNotNullParameter(continuation, "<this>");
        if (continuation instanceof DispatchedContinuation) {
            return continuation.toString();
        }
        try {
            Result.Companion companion = Result.Companion;
            m104constructorimpl = Result.m104constructorimpl(continuation + "@" + getHexAddress(continuation));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            m104constructorimpl = Result.m104constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m106exceptionOrNullimpl(m104constructorimpl) != null) {
            m104constructorimpl = continuation.getClass().getName() + "@" + getHexAddress(continuation);
        }
        return (String) m104constructorimpl;
    }

    public static final String getClassSimpleName(Object obj) {
        Intrinsics.checkNotNullParameter(obj, "<this>");
        String simpleName = obj.getClass().getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "this::class.java.simpleName");
        return simpleName;
    }
}
