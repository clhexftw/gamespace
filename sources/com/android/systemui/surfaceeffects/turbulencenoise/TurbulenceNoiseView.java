package com.android.systemui.surfaceeffects.turbulencenoise;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: TurbulenceNoiseView.kt */
/* loaded from: classes2.dex */
public final class TurbulenceNoiseView extends View {
    public static final Companion Companion = new Companion(null);
    private ValueAnimator currentAnimator;
    private final Paint paint;
    private final TurbulenceNoiseShader turbulenceNoiseShader;

    public static /* synthetic */ void getCurrentAnimator$annotations() {
    }

    public static /* synthetic */ void getNoiseConfig$annotations() {
    }

    public final TurbulenceNoiseAnimationConfig getNoiseConfig() {
        return null;
    }

    public final void play(Runnable runnable) {
    }

    public final void playEaseIn(float f, float f2, Runnable runnable) {
    }

    public final void playEaseOut(Runnable runnable) {
    }

    public final void setNoiseConfig(TurbulenceNoiseAnimationConfig turbulenceNoiseAnimationConfig) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [com.android.systemui.surfaceeffects.turbulencenoise.TurbulenceNoiseShader, android.graphics.Shader] */
    public TurbulenceNoiseView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        ?? turbulenceNoiseShader = new TurbulenceNoiseShader();
        this.turbulenceNoiseShader = turbulenceNoiseShader;
        Paint paint = new Paint();
        paint.setShader(turbulenceNoiseShader);
        this.paint = paint;
    }

    /* compiled from: TurbulenceNoiseView.kt */
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public final ValueAnimator getCurrentAnimator() {
        return this.currentAnimator;
    }

    public final void setCurrentAnimator(ValueAnimator valueAnimator) {
        this.currentAnimator = valueAnimator;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (canvas == null || !canvas.isHardwareAccelerated()) {
            return;
        }
        canvas.drawPaint(this.paint);
    }

    public final void finish(Runnable runnable) {
        ValueAnimator valueAnimator = this.currentAnimator;
        if (valueAnimator != null) {
            valueAnimator.pause();
        }
        this.currentAnimator = null;
        if (runnable != null) {
            runnable.run();
        }
    }

    public final void applyConfig(TurbulenceNoiseAnimationConfig config) {
        Intrinsics.checkNotNullParameter(config, "config");
        throw null;
    }
}
