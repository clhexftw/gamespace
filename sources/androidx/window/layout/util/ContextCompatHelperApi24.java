package androidx.window.layout.util;

import android.app.Activity;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: ContextCompatHelper.kt */
/* loaded from: classes.dex */
public final class ContextCompatHelperApi24 {
    public static final ContextCompatHelperApi24 INSTANCE = new ContextCompatHelperApi24();

    private ContextCompatHelperApi24() {
    }

    public final boolean isInMultiWindowMode(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        return activity.isInMultiWindowMode();
    }
}
