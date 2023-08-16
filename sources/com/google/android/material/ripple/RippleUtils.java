package com.google.android.material.ripple;

import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.StateSet;
import androidx.core.graphics.ColorUtils;
/* loaded from: classes2.dex */
public class RippleUtils {
    static final String TRANSPARENT_DEFAULT_COLOR_WARNING = "Use a non-transparent color for the default color as it will be used to finish ripple animations.";
    public static final boolean USE_FRAMEWORK_RIPPLE = true;
    private static final int[] PRESSED_STATE_SET = {16842919};
    private static final int[] HOVERED_FOCUSED_STATE_SET = {16843623, 16842908};
    private static final int[] FOCUSED_STATE_SET = {16842908};
    private static final int[] HOVERED_STATE_SET = {16843623};
    private static final int[] SELECTED_PRESSED_STATE_SET = {16842913, 16842919};
    private static final int[] SELECTED_HOVERED_FOCUSED_STATE_SET = {16842913, 16843623, 16842908};
    private static final int[] SELECTED_FOCUSED_STATE_SET = {16842913, 16842908};
    private static final int[] SELECTED_HOVERED_STATE_SET = {16842913, 16843623};
    private static final int[] SELECTED_STATE_SET = {16842913};
    private static final int[] ENABLED_PRESSED_STATE_SET = {16842910, 16842919};
    static final String LOG_TAG = RippleUtils.class.getSimpleName();

    private RippleUtils() {
    }

    public static ColorStateList convertToRippleDrawableColor(ColorStateList colorStateList) {
        if (USE_FRAMEWORK_RIPPLE) {
            int[] iArr = FOCUSED_STATE_SET;
            return new ColorStateList(new int[][]{SELECTED_STATE_SET, iArr, StateSet.NOTHING}, new int[]{getColorForState(colorStateList, SELECTED_PRESSED_STATE_SET), getColorForState(colorStateList, iArr), getColorForState(colorStateList, PRESSED_STATE_SET)});
        }
        int[] iArr2 = SELECTED_PRESSED_STATE_SET;
        int[] iArr3 = SELECTED_HOVERED_FOCUSED_STATE_SET;
        int[] iArr4 = SELECTED_FOCUSED_STATE_SET;
        int[] iArr5 = SELECTED_HOVERED_STATE_SET;
        int[] iArr6 = PRESSED_STATE_SET;
        int[] iArr7 = HOVERED_FOCUSED_STATE_SET;
        int[] iArr8 = FOCUSED_STATE_SET;
        int[] iArr9 = HOVERED_STATE_SET;
        return new ColorStateList(new int[][]{iArr2, iArr3, iArr4, iArr5, SELECTED_STATE_SET, iArr6, iArr7, iArr8, iArr9, StateSet.NOTHING}, new int[]{getColorForState(colorStateList, iArr2), getColorForState(colorStateList, iArr3), getColorForState(colorStateList, iArr4), getColorForState(colorStateList, iArr5), 0, getColorForState(colorStateList, iArr6), getColorForState(colorStateList, iArr7), getColorForState(colorStateList, iArr8), getColorForState(colorStateList, iArr9), 0});
    }

    public static ColorStateList sanitizeRippleDrawableColor(ColorStateList colorStateList) {
        return colorStateList != null ? colorStateList : ColorStateList.valueOf(0);
    }

    public static boolean shouldDrawRippleCompat(int[] iArr) {
        boolean z = false;
        boolean z2 = false;
        for (int i : iArr) {
            if (i == 16842910) {
                z = true;
            } else if (i == 16842908 || i == 16842919 || i == 16843623) {
                z2 = true;
            }
        }
        return z && z2;
    }

    private static int getColorForState(ColorStateList colorStateList, int[] iArr) {
        int colorForState = colorStateList != null ? colorStateList.getColorForState(iArr, colorStateList.getDefaultColor()) : 0;
        return USE_FRAMEWORK_RIPPLE ? doubleAlpha(colorForState) : colorForState;
    }

    @TargetApi(21)
    private static int doubleAlpha(int i) {
        return ColorUtils.setAlphaComponent(i, Math.min(Color.alpha(i) * 2, 255));
    }
}
