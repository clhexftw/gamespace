package com.android.systemui.shared.shadow;

import android.graphics.Canvas;
import android.graphics.Color;
import android.widget.TextView;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: DoubleShadowTextHelper.kt */
/* loaded from: classes2.dex */
public final class DoubleShadowTextHelper {
    public static final DoubleShadowTextHelper INSTANCE = new DoubleShadowTextHelper();

    /* compiled from: DoubleShadowTextHelper.kt */
    /* loaded from: classes2.dex */
    public static final class ShadowInfo {
        private final float alpha;
        private final float blur;
        private final float offsetX;
        private final float offsetY;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof ShadowInfo) {
                ShadowInfo shadowInfo = (ShadowInfo) obj;
                return Float.compare(this.blur, shadowInfo.blur) == 0 && Float.compare(this.offsetX, shadowInfo.offsetX) == 0 && Float.compare(this.offsetY, shadowInfo.offsetY) == 0 && Float.compare(this.alpha, shadowInfo.alpha) == 0;
            }
            return false;
        }

        public int hashCode() {
            return (((((Float.hashCode(this.blur) * 31) + Float.hashCode(this.offsetX)) * 31) + Float.hashCode(this.offsetY)) * 31) + Float.hashCode(this.alpha);
        }

        public String toString() {
            float f = this.blur;
            float f2 = this.offsetX;
            float f3 = this.offsetY;
            float f4 = this.alpha;
            return "ShadowInfo(blur=" + f + ", offsetX=" + f2 + ", offsetY=" + f3 + ", alpha=" + f4 + ")";
        }

        public ShadowInfo(float f, float f2, float f3, float f4) {
            this.blur = f;
            this.offsetX = f2;
            this.offsetY = f3;
            this.alpha = f4;
        }

        public final float getBlur() {
            return this.blur;
        }

        public final float getOffsetX() {
            return this.offsetX;
        }

        public final float getOffsetY() {
            return this.offsetY;
        }

        public final float getAlpha() {
            return this.alpha;
        }
    }

    private DoubleShadowTextHelper() {
    }

    public final void applyShadows(ShadowInfo keyShadowInfo, ShadowInfo ambientShadowInfo, TextView view, Canvas canvas, Function0<Unit> onDrawCallback) {
        Intrinsics.checkNotNullParameter(keyShadowInfo, "keyShadowInfo");
        Intrinsics.checkNotNullParameter(ambientShadowInfo, "ambientShadowInfo");
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        Intrinsics.checkNotNullParameter(onDrawCallback, "onDrawCallback");
        view.getPaint().setShadowLayer(ambientShadowInfo.getBlur(), ambientShadowInfo.getOffsetX(), ambientShadowInfo.getOffsetY(), Color.argb(ambientShadowInfo.getAlpha(), 0.0f, 0.0f, 0.0f));
        onDrawCallback.invoke();
        canvas.save();
        canvas.clipRect(view.getScrollX(), view.getScrollY() + view.getExtendedPaddingTop(), view.getScrollX() + view.getWidth(), view.getScrollY() + view.getHeight());
        view.getPaint().setShadowLayer(keyShadowInfo.getBlur(), keyShadowInfo.getOffsetX(), keyShadowInfo.getOffsetY(), Color.argb(keyShadowInfo.getAlpha(), 0.0f, 0.0f, 0.0f));
        onDrawCallback.invoke();
        canvas.restore();
    }
}
