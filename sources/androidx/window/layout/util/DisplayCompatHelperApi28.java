package androidx.window.layout.util;

import android.view.DisplayCutout;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: DisplayCompatHelper.kt */
/* loaded from: classes.dex */
public final class DisplayCompatHelperApi28 {
    public static final DisplayCompatHelperApi28 INSTANCE = new DisplayCompatHelperApi28();

    private DisplayCompatHelperApi28() {
    }

    public final int safeInsetLeft(DisplayCutout displayCutout) {
        Intrinsics.checkNotNullParameter(displayCutout, "displayCutout");
        return displayCutout.getSafeInsetLeft();
    }

    public final int safeInsetTop(DisplayCutout displayCutout) {
        Intrinsics.checkNotNullParameter(displayCutout, "displayCutout");
        return displayCutout.getSafeInsetTop();
    }

    public final int safeInsetRight(DisplayCutout displayCutout) {
        Intrinsics.checkNotNullParameter(displayCutout, "displayCutout");
        return displayCutout.getSafeInsetRight();
    }

    public final int safeInsetBottom(DisplayCutout displayCutout) {
        Intrinsics.checkNotNullParameter(displayCutout, "displayCutout");
        return displayCutout.getSafeInsetBottom();
    }
}
