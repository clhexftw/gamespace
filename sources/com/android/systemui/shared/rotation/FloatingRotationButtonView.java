package com.android.systemui.shared.rotation;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.android.systemui.navigationbar.buttons.KeyButtonRipple;
/* loaded from: classes2.dex */
public class FloatingRotationButtonView extends ImageView {
    private final Configuration mLastConfiguration;
    private final Paint mOvalBgPaint;
    private KeyButtonRipple mRipple;

    public FloatingRotationButtonView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FloatingRotationButtonView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mOvalBgPaint = new Paint(3);
        this.mLastConfiguration = getResources().getConfiguration();
        setClickable(true);
        setWillNotDraw(false);
        forceHasOverlappingRendering(false);
    }

    public void setRipple(int i) {
        KeyButtonRipple keyButtonRipple = new KeyButtonRipple(getContext(), this, i);
        this.mRipple = keyButtonRipple;
        setBackground(keyButtonRipple);
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        if (i != 0) {
            jumpDrawablesToCurrentState();
        }
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        KeyButtonRipple keyButtonRipple;
        int updateFrom = this.mLastConfiguration.updateFrom(configuration);
        if (((updateFrom & 1024) == 0 && (updateFrom & 4096) == 0) || (keyButtonRipple = this.mRipple) == null) {
            return;
        }
        keyButtonRipple.updateResources();
    }

    public void setDarkIntensity(float f) {
        this.mRipple.setDarkIntensity(f);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        float min = Math.min(getWidth(), getHeight());
        canvas.drawOval(0.0f, 0.0f, min, min, this.mOvalBgPaint);
        super.draw(canvas);
    }
}
