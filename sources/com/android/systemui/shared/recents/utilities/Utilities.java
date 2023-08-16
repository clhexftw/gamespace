package com.android.systemui.shared.recents.utilities;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Rect;
import android.view.WindowManager;
/* loaded from: classes2.dex */
public class Utilities {
    public static float dpiFromPx(float f, int i) {
        return f / (i / 160.0f);
    }

    @TargetApi(30)
    public static boolean isLargeScreen(Context context) {
        Rect bounds = ((WindowManager) context.getSystemService(WindowManager.class)).getMaximumWindowMetrics().getBounds();
        return dpiFromPx((float) Math.min(bounds.width(), bounds.height()), context.getResources().getConfiguration().densityDpi) >= 600.0f;
    }
}
