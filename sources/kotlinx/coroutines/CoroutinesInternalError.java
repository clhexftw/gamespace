package kotlinx.coroutines;

import kotlin.jvm.internal.Intrinsics;
/* compiled from: Exceptions.common.kt */
/* loaded from: classes2.dex */
public final class CoroutinesInternalError extends Error {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CoroutinesInternalError(String message, Throwable cause) {
        super(message, cause);
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(cause, "cause");
    }
}
