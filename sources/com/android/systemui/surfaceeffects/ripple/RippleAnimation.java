package com.android.systemui.surfaceeffects.ripple;

import android.animation.ValueAnimator;
/* compiled from: RippleAnimation.kt */
/* loaded from: classes2.dex */
public final class RippleAnimation {
    private final ValueAnimator animator;
    private final RippleShader rippleShader;

    public static /* synthetic */ void getRippleShader$annotations() {
    }

    public final RippleShader getRippleShader() {
        return this.rippleShader;
    }

    public final boolean isPlaying() {
        return this.animator.isRunning();
    }
}
