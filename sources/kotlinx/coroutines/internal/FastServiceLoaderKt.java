package kotlinx.coroutines.internal;

import kotlin.Result;
import kotlin.ResultKt;
/* compiled from: FastServiceLoader.kt */
/* loaded from: classes.dex */
public final class FastServiceLoaderKt {
    private static final boolean ANDROID_DETECTED;

    static {
        Object m104constructorimpl;
        try {
            Result.Companion companion = Result.Companion;
            m104constructorimpl = Result.m104constructorimpl(Class.forName("android.os.Build"));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            m104constructorimpl = Result.m104constructorimpl(ResultKt.createFailure(th));
        }
        ANDROID_DETECTED = Result.m109isSuccessimpl(m104constructorimpl);
    }

    public static final boolean getANDROID_DETECTED() {
        return ANDROID_DETECTED;
    }
}
