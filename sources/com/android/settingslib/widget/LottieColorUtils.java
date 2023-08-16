package com.android.settingslib.widget;

import android.content.Context;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.value.LottieFrameInfo;
import com.airbnb.lottie.value.SimpleLottieValueCallback;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes2.dex */
public class LottieColorUtils {
    private static final Map<String, Integer> DARK_TO_LIGHT_THEME_COLOR_MAP;

    static {
        HashMap hashMap = new HashMap();
        hashMap.put(".grey600", Integer.valueOf(R$color.settingslib_color_grey400));
        hashMap.put(".grey700", Integer.valueOf(R$color.settingslib_color_grey500));
        hashMap.put(".grey800", Integer.valueOf(R$color.settingslib_color_grey300));
        hashMap.put(".grey900", Integer.valueOf(R$color.settingslib_color_grey50));
        hashMap.put(".red400", Integer.valueOf(R$color.settingslib_color_red600));
        hashMap.put(".black", 17170443);
        hashMap.put(".blue400", Integer.valueOf(R$color.settingslib_color_blue600));
        hashMap.put(".green400", Integer.valueOf(R$color.settingslib_color_green600));
        DARK_TO_LIGHT_THEME_COLOR_MAP = Collections.unmodifiableMap(hashMap);
    }

    private static boolean isDarkMode(Context context) {
        return (context.getResources().getConfiguration().uiMode & 48) == 32;
    }

    public static void applyDynamicColors(Context context, LottieAnimationView lottieAnimationView) {
        if (isDarkMode(context)) {
            return;
        }
        for (String str : DARK_TO_LIGHT_THEME_COLOR_MAP.keySet()) {
            final int color = context.getColor(DARK_TO_LIGHT_THEME_COLOR_MAP.get(str).intValue());
            lottieAnimationView.addValueCallback(new KeyPath("**", str, "**"), (KeyPath) LottieProperty.COLOR_FILTER, (SimpleLottieValueCallback<KeyPath>) new SimpleLottieValueCallback() { // from class: com.android.settingslib.widget.LottieColorUtils$$ExternalSyntheticLambda0
                @Override // com.airbnb.lottie.value.SimpleLottieValueCallback
                public final Object getValue(LottieFrameInfo lottieFrameInfo) {
                    ColorFilter lambda$applyDynamicColors$0;
                    lambda$applyDynamicColors$0 = LottieColorUtils.lambda$applyDynamicColors$0(color, lottieFrameInfo);
                    return lambda$applyDynamicColors$0;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ ColorFilter lambda$applyDynamicColors$0(int i, LottieFrameInfo lottieFrameInfo) {
        return new PorterDuffColorFilter(i, PorterDuff.Mode.SRC_ATOP);
    }
}
