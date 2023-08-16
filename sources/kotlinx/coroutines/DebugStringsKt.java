package kotlinx.coroutines;

import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.DispatchedContinuation;
/* compiled from: DebugStrings.kt */
/* loaded from: classes2.dex */
public final class DebugStringsKt {
    public static final String getHexAddress(Object obj) {
        Intrinsics.checkNotNullParameter(obj, "<this>");
        String hexString = Integer.toHexString(System.identityHashCode(obj));
        Intrinsics.checkNotNullExpressionValue(hexString, "toHexString(System.identityHashCode(this))");
        return hexString;
    }

    public static final String toDebugString(Continuation<?> continuation) {
        String m2159constructorimpl;
        Intrinsics.checkNotNullParameter(continuation, "<this>");
        if (continuation instanceof DispatchedContinuation) {
            return continuation.toString();
        }
        try {
            Result.Companion companion = Result.Companion;
            m2159constructorimpl = Result.m2159constructorimpl(continuation + "@" + getHexAddress(continuation));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            m2159constructorimpl = Result.m2159constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m2161exceptionOrNullimpl(m2159constructorimpl) != null) {
            m2159constructorimpl = continuation.getClass().getName() + "@" + getHexAddress(continuation);
        }
        return (String) m2159constructorimpl;
    }

    public static final String getClassSimpleName(Object obj) {
        Intrinsics.checkNotNullParameter(obj, "<this>");
        String simpleName = obj.getClass().getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "this::class.java.simpleName");
        return simpleName;
    }
}
