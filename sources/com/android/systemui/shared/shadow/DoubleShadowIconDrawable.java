package com.android.systemui.shared.shadow;

import android.graphics.BlendMode;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RenderEffect;
import android.graphics.RenderNode;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import com.android.systemui.shared.shadow.DoubleShadowTextHelper;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: DoubleShadowIconDrawable.kt */
/* loaded from: classes2.dex */
public final class DoubleShadowIconDrawable extends Drawable {
    private final int iconInsetSize;
    private final DoubleShadowTextHelper.ShadowInfo mAmbientShadowInfo;
    private final int mCanvasSize;
    private final RenderNode mDoubleShadowNode;
    private final InsetDrawable mIconDrawable;
    private final DoubleShadowTextHelper.ShadowInfo mKeyShadowInfo;

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -2;
    }

    public DoubleShadowIconDrawable(DoubleShadowTextHelper.ShadowInfo keyShadowInfo, DoubleShadowTextHelper.ShadowInfo ambientShadowInfo, Drawable iconDrawable, int i, int i2) {
        Intrinsics.checkNotNullParameter(keyShadowInfo, "keyShadowInfo");
        Intrinsics.checkNotNullParameter(ambientShadowInfo, "ambientShadowInfo");
        Intrinsics.checkNotNullParameter(iconDrawable, "iconDrawable");
        this.iconInsetSize = i2;
        int i3 = i + (i2 * 2);
        this.mCanvasSize = i3;
        this.mKeyShadowInfo = keyShadowInfo;
        this.mAmbientShadowInfo = ambientShadowInfo;
        setBounds(0, 0, i3, i3);
        InsetDrawable insetDrawable = new InsetDrawable(iconDrawable, i2);
        this.mIconDrawable = insetDrawable;
        insetDrawable.setBounds(0, 0, i3, i3);
        this.mDoubleShadowNode = createShadowRenderNode();
    }

    private final RenderNode createShadowRenderNode() {
        RenderNode renderNode = new RenderNode("DoubleShadowNode");
        int i = this.mCanvasSize;
        renderNode.setPosition(0, 0, i, i);
        renderNode.setRenderEffect(RenderEffect.createBlendModeEffect(createShadowRenderEffect(this.mAmbientShadowInfo.getBlur(), this.mAmbientShadowInfo.getOffsetX(), this.mAmbientShadowInfo.getOffsetY(), this.mAmbientShadowInfo.getAlpha()), createShadowRenderEffect(this.mKeyShadowInfo.getBlur(), this.mKeyShadowInfo.getOffsetX(), this.mKeyShadowInfo.getOffsetY(), this.mKeyShadowInfo.getAlpha()), BlendMode.DST_ATOP));
        return renderNode;
    }

    private final RenderEffect createShadowRenderEffect(float f, float f2, float f3, float f4) {
        RenderEffect createColorFilterEffect = RenderEffect.createColorFilterEffect(new PorterDuffColorFilter(Color.argb(f4, 0.0f, 0.0f, 0.0f), PorterDuff.Mode.MULTIPLY), RenderEffect.createOffsetEffect(f2, f3, RenderEffect.createBlurEffect(f, f, Shader.TileMode.CLAMP)));
        Intrinsics.checkNotNullExpressionValue(createColorFilterEffect, "createColorFilterEffect(…)\n            )\n        )");
        return createColorFilterEffect;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        if (canvas.isHardwareAccelerated()) {
            if (!this.mDoubleShadowNode.hasDisplayList()) {
                this.mIconDrawable.draw(this.mDoubleShadowNode.beginRecording());
                this.mDoubleShadowNode.endRecording();
            }
            canvas.drawRenderNode(this.mDoubleShadowNode);
        }
        this.mIconDrawable.draw(canvas);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.mIconDrawable.setAlpha(i);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.mIconDrawable.setColorFilter(colorFilter);
    }

    @Override // android.graphics.drawable.Drawable
    public void setTint(int i) {
        this.mIconDrawable.setTint(i);
    }
}
