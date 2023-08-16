package com.android.wm.shell.startingsurface;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
@SuppressLint({"ViewConstructor"})
/* loaded from: classes2.dex */
public class SplashScreenExitAnimationUtils$RadialVanishAnimation extends View {
    private final Paint mVanishPaint;
    private final ViewGroup mView;

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(0.0f, 0.0f, this.mView.getWidth(), this.mView.getHeight(), this.mVanishPaint);
    }
}
