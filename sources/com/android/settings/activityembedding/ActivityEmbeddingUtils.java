package com.android.settings.activityembedding;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.FeatureFlagUtils;
import android.util.Log;
import android.util.TypedValue;
import androidx.window.embedding.SplitController;
import androidx.window.embedding.SplitRule;
import com.android.settings.R;
/* loaded from: classes.dex */
public class ActivityEmbeddingUtils {
    public static int getMinCurrentScreenSplitWidthDp() {
        return 720;
    }

    public static int getMinSmallestScreenSplitWidthDp() {
        return SplitRule.SPLIT_MIN_DIMENSION_DP_DEFAULT;
    }

    public static float getSplitRatio(Context context) {
        return context.getResources().getFloat(R.dimen.config_activity_embed_split_ratio);
    }

    public static boolean isEmbeddingActivityEnabled(Context context) {
        boolean isEnabled = FeatureFlagUtils.isEnabled(context, "settings_support_large_screen");
        boolean isSplitSupported = SplitController.getInstance(context).isSplitSupported();
        Log.d("ActivityEmbeddingUtils", "isFlagEnabled = " + isEnabled);
        Log.d("ActivityEmbeddingUtils", "isSplitSupported = " + isSplitSupported);
        return isEnabled && isSplitSupported;
    }

    public static boolean isRegularHomepageLayout(Activity activity) {
        DisplayMetrics displayMetrics = activity.getResources().getDisplayMetrics();
        return displayMetrics.widthPixels >= ((int) TypedValue.applyDimension(1, 380.0f, displayMetrics));
    }
}
