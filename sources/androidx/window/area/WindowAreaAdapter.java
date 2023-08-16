package androidx.window.area;

import androidx.window.core.ExperimentalWindowApi;
/* compiled from: WindowAreaAdapter.kt */
@ExperimentalWindowApi
/* loaded from: classes.dex */
public final class WindowAreaAdapter {
    public static final WindowAreaAdapter INSTANCE = new WindowAreaAdapter();

    private WindowAreaAdapter() {
    }

    public final WindowAreaStatus translate$window_release(int i) {
        if (i != 1) {
            if (i == 2) {
                return WindowAreaStatus.AVAILABLE;
            }
            return WindowAreaStatus.UNSUPPORTED;
        }
        return WindowAreaStatus.UNAVAILABLE;
    }
}
