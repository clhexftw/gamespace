package kotlinx.coroutines.android;

import android.os.Handler;
import android.os.Looper;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: HandlerDispatcher.kt */
/* loaded from: classes2.dex */
public final class HandlerDispatcherKt {
    public static final HandlerDispatcher Main;

    public static final Handler asHandler(Looper looper, boolean z) {
        Intrinsics.checkNotNullParameter(looper, "<this>");
        if (!z) {
            return new Handler(looper);
        }
        Object invoke = Handler.class.getDeclaredMethod("createAsync", Looper.class).invoke(null, looper);
        Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.os.Handler");
        return (Handler) invoke;
    }

    static {
        Object m2159constructorimpl;
        try {
            Result.Companion companion = Result.Companion;
            Looper mainLooper = Looper.getMainLooper();
            Intrinsics.checkNotNullExpressionValue(mainLooper, "getMainLooper()");
            m2159constructorimpl = Result.m2159constructorimpl(new HandlerContext(asHandler(mainLooper, true), null, 2, null));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            m2159constructorimpl = Result.m2159constructorimpl(ResultKt.createFailure(th));
        }
        Main = Result.m2163isFailureimpl(m2159constructorimpl) ? null : m2159constructorimpl;
    }
}
