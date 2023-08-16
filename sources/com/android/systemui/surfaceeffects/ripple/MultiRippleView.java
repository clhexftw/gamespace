package com.android.systemui.surfaceeffects.ripple;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import java.util.ArrayList;
import kotlin.jvm.internal.DefaultConstructorMarker;
/* compiled from: MultiRippleView.kt */
/* loaded from: classes2.dex */
public final class MultiRippleView extends View {
    public static final Companion Companion = new Companion(null);
    private boolean isWarningLogged;
    private final Paint ripplePaint;
    private final ArrayList<RippleAnimation> ripples;

    public static /* synthetic */ void getRipples$annotations() {
    }

    public MultiRippleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.ripples = new ArrayList<>();
        this.ripplePaint = new Paint();
    }

    public final ArrayList<RippleAnimation> getRipples() {
        return this.ripples;
    }

    /* compiled from: MultiRippleView.kt */
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        boolean z;
        if (canvas == null || !canvas.isHardwareAccelerated()) {
            if (this.isWarningLogged) {
                return;
            }
            Log.w("MultiRippleView", "Can't draw ripple shader. " + canvas + " does not support hardware acceleration.");
            this.isWarningLogged = true;
            return;
        }
        loop0: while (true) {
            for (RippleAnimation rippleAnimation : this.ripples) {
                this.ripplePaint.setShader(rippleAnimation.getRippleShader());
                canvas.drawPaint(this.ripplePaint);
                z = z || rippleAnimation.isPlaying();
            }
        }
        if (z) {
            invalidate();
        }
    }
}
