package androidx.core.view;

import android.view.View;
import android.view.Window;
/* loaded from: classes.dex */
public final class WindowCompat {
    public static void setDecorFitsSystemWindows(Window window, boolean z) {
        Api30Impl.setDecorFitsSystemWindows(window, z);
    }

    public static WindowInsetsControllerCompat getInsetsController(Window window, View view) {
        return new WindowInsetsControllerCompat(window, view);
    }

    /* loaded from: classes.dex */
    static class Api30Impl {
        static void setDecorFitsSystemWindows(Window window, boolean z) {
            window.setDecorFitsSystemWindows(z);
        }
    }
}
