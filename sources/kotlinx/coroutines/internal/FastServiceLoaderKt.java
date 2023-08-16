package kotlinx.coroutines.internal;

import kotlin.Result;
import kotlin.ResultKt;
/* compiled from: FastServiceLoader.kt */
/* loaded from: classes2.dex */
public final class FastServiceLoaderKt {
    private static final boolean ANDROID_DETECTED;

    static {
        Object m2159constructorimpl;
        try {
            Result.Companion companion = Result.Companion;
            m2159constructorimpl = Result.m2159constructorimpl(Class.forName("android.os.Build"));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            m2159constructorimpl = Result.m2159constructorimpl(ResultKt.createFailure(th));
        }
        ANDROID_DETECTED = Result.m2164isSuccessimpl(m2159constructorimpl);
    }

    public static final boolean getANDROID_DETECTED() {
        return ANDROID_DETECTED;
    }
}
