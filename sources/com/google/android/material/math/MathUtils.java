package com.google.android.material.math;
/* loaded from: classes.dex */
public final class MathUtils {
    public static float lerp(float f, float f2, float f3) {
        return ((1.0f - f3) * f) + (f3 * f2);
    }

    private static float max(float f, float f2, float f3, float f4) {
        return (f <= f2 || f <= f3 || f <= f4) ? (f2 <= f3 || f2 <= f4) ? f3 > f4 ? f3 : f4 : f2 : f;
    }

    public static float dist(float f, float f2, float f3, float f4) {
        return (float) Math.hypot(f3 - f, f4 - f2);
    }

    public static float distanceToFurthestCorner(float f, float f2, float f3, float f4, float f5, float f6) {
        return max(dist(f, f2, f3, f4), dist(f, f2, f5, f4), dist(f, f2, f5, f6), dist(f, f2, f3, f6));
    }
}
