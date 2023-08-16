package com.android.systemui.surfaceeffects.ripple;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import com.android.systemui.surfaceeffects.ripple.RippleShader;
import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: RippleView.kt */
/* loaded from: classes2.dex */
public class RippleView extends View {
    private final ValueAnimator animator;
    private float centerX;
    private float centerY;
    private long duration;
    private final Paint ripplePaint;
    protected RippleShader rippleShader;
    private RippleShader.RippleShape rippleShape;

    public final void setBaseRingFadeParams(float f) {
        setBaseRingFadeParams$default(this, f, 0.0f, 0.0f, 0.0f, 14, null);
    }

    public final void setCenterFillFadeParams(float f) {
        setCenterFillFadeParams$default(this, f, 0.0f, 0.0f, 0.0f, 14, null);
    }

    public final void setSparkleRingFadeParams(float f) {
        setSparkleRingFadeParams$default(this, f, 0.0f, 0.0f, 0.0f, 14, null);
    }

    public RippleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.ripplePaint = new Paint();
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        Intrinsics.checkNotNullExpressionValue(ofFloat, "ofFloat(0f, 1f)");
        this.animator = ofFloat;
        this.duration = 1750L;
    }

    protected final RippleShader getRippleShader() {
        RippleShader rippleShader = this.rippleShader;
        if (rippleShader != null) {
            return rippleShader;
        }
        Intrinsics.throwUninitializedPropertyAccessException("rippleShader");
        return null;
    }

    protected final void setRippleShader(RippleShader rippleShader) {
        Intrinsics.checkNotNullParameter(rippleShader, "<set-?>");
        this.rippleShader = rippleShader;
    }

    public final RippleShader.RippleShape getRippleShape() {
        RippleShader.RippleShape rippleShape = this.rippleShape;
        if (rippleShape != null) {
            return rippleShape;
        }
        Intrinsics.throwUninitializedPropertyAccessException("rippleShape");
        return null;
    }

    protected final ValueAnimator getAnimator() {
        return this.animator;
    }

    public final long getDuration() {
        return this.duration;
    }

    public final void setDuration(long j) {
        this.duration = j;
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        getRippleShader().setPixelDensity(getResources().getDisplayMetrics().density);
        super.onConfigurationChanged(configuration);
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        getRippleShader().setPixelDensity(getResources().getDisplayMetrics().density);
        super.onAttachedToWindow();
    }

    public final void setupShader(RippleShader.RippleShape rippleShape) {
        Intrinsics.checkNotNullParameter(rippleShape, "rippleShape");
        this.rippleShape = rippleShape;
        setRippleShader(new RippleShader(rippleShape));
        getRippleShader().setColor(-1);
        getRippleShader().setRawProgress(0.0f);
        getRippleShader().setSparkleStrength(0.3f);
        getRippleShader().setPixelDensity(getResources().getDisplayMetrics().density);
        this.ripplePaint.setShader(getRippleShader());
    }

    public static /* synthetic */ void setBaseRingFadeParams$default(RippleView rippleView, float f, float f2, float f3, float f4, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setBaseRingFadeParams");
        }
        if ((i & 1) != 0) {
            f = rippleView.getRippleShader().getBaseRingFadeParams().getFadeInStart();
        }
        if ((i & 2) != 0) {
            f2 = rippleView.getRippleShader().getBaseRingFadeParams().getFadeInEnd();
        }
        if ((i & 4) != 0) {
            f3 = rippleView.getRippleShader().getBaseRingFadeParams().getFadeOutStart();
        }
        if ((i & 8) != 0) {
            f4 = rippleView.getRippleShader().getBaseRingFadeParams().getFadeOutEnd();
        }
        rippleView.setBaseRingFadeParams(f, f2, f3, f4);
    }

    public final void setBaseRingFadeParams(float f, float f2, float f3, float f4) {
        setFadeParams(getRippleShader().getBaseRingFadeParams(), f, f2, f3, f4);
    }

    public static /* synthetic */ void setSparkleRingFadeParams$default(RippleView rippleView, float f, float f2, float f3, float f4, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setSparkleRingFadeParams");
        }
        if ((i & 1) != 0) {
            f = rippleView.getRippleShader().getSparkleRingFadeParams().getFadeInStart();
        }
        if ((i & 2) != 0) {
            f2 = rippleView.getRippleShader().getSparkleRingFadeParams().getFadeInEnd();
        }
        if ((i & 4) != 0) {
            f3 = rippleView.getRippleShader().getSparkleRingFadeParams().getFadeOutStart();
        }
        if ((i & 8) != 0) {
            f4 = rippleView.getRippleShader().getSparkleRingFadeParams().getFadeOutEnd();
        }
        rippleView.setSparkleRingFadeParams(f, f2, f3, f4);
    }

    public final void setSparkleRingFadeParams(float f, float f2, float f3, float f4) {
        setFadeParams(getRippleShader().getSparkleRingFadeParams(), f, f2, f3, f4);
    }

    public static /* synthetic */ void setCenterFillFadeParams$default(RippleView rippleView, float f, float f2, float f3, float f4, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setCenterFillFadeParams");
        }
        if ((i & 1) != 0) {
            f = rippleView.getRippleShader().getCenterFillFadeParams().getFadeInStart();
        }
        if ((i & 2) != 0) {
            f2 = rippleView.getRippleShader().getCenterFillFadeParams().getFadeInEnd();
        }
        if ((i & 4) != 0) {
            f3 = rippleView.getRippleShader().getCenterFillFadeParams().getFadeOutStart();
        }
        if ((i & 8) != 0) {
            f4 = rippleView.getRippleShader().getCenterFillFadeParams().getFadeOutEnd();
        }
        rippleView.setCenterFillFadeParams(f, f2, f3, f4);
    }

    public final void setCenterFillFadeParams(float f, float f2, float f3, float f4) {
        setFadeParams(getRippleShader().getCenterFillFadeParams(), f, f2, f3, f4);
    }

    private final void setFadeParams(RippleShader.FadeParams fadeParams, float f, float f2, float f3, float f4) {
        fadeParams.setFadeInStart(f);
        fadeParams.setFadeInEnd(f2);
        fadeParams.setFadeOutStart(f3);
        fadeParams.setFadeOutEnd(f4);
    }

    public final void setSizeAtProgresses(RippleShader.SizeAtProgress... targetSizes) {
        Intrinsics.checkNotNullParameter(targetSizes, "targetSizes");
        getRippleShader().getRippleSize().setSizeAtProgresses((RippleShader.SizeAtProgress[]) Arrays.copyOf(targetSizes, targetSizes.length));
    }

    public final void setSparkleStrength(float f) {
        getRippleShader().setSparkleStrength(f);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (canvas == null || !canvas.isHardwareAccelerated()) {
            return;
        }
        if (getRippleShape() == RippleShader.RippleShape.CIRCLE) {
            canvas.drawCircle(this.centerX, this.centerY, getRippleShader().getRippleSize().getCurrentWidth(), this.ripplePaint);
        } else if (getRippleShape() == RippleShader.RippleShape.ELLIPSE) {
            float f = 2;
            float currentWidth = getRippleShader().getRippleSize().getCurrentWidth() * f;
            float currentHeight = getRippleShader().getRippleSize().getCurrentHeight() * f;
            float f2 = this.centerX;
            float f3 = this.centerY;
            canvas.drawRect(f2 - currentWidth, f3 - currentHeight, f2 + currentWidth, f3 + currentHeight, this.ripplePaint);
        } else {
            canvas.drawPaint(this.ripplePaint);
        }
    }
}
