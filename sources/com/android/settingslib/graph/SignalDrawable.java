package com.android.settingslib.graph;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.DrawableWrapper;
import android.os.Handler;
import android.telephony.CellSignalStrength;
import android.util.PathParser;
import com.android.settingslib.R$color;
import com.android.settingslib.R$dimen;
import com.android.settingslib.Utils;
/* loaded from: classes2.dex */
public class SignalDrawable extends DrawableWrapper {
    private boolean mAnimating;
    private final Path mAttributionPath;
    private final Matrix mAttributionScaleMatrix;
    private final Runnable mChangeDot;
    private int mCurrentDot;
    private final float mCutoutHeightFraction;
    private final Path mCutoutPath;
    private final float mCutoutWidthFraction;
    private float mDarkIntensity;
    private final int mDarkModeFillColor;
    private final Paint mForegroundPaint;
    private final Path mForegroundPath;
    private final Handler mHandler;
    private final int mIntrinsicSize;
    private final int mLightModeFillColor;
    private final Path mScaledAttributionPath;
    private final Paint mTransparentPaint;

    public static int getCarrierChangeState(int i) {
        return (i << 8) | 196608;
    }

    public static int getState(int i) {
        return (i & 16711680) >> 16;
    }

    public static int getState(int i, int i2, boolean z) {
        return i | (i2 << 8) | ((z ? 2 : 0) << 16);
    }

    public SignalDrawable(Context context) {
        super(context.getDrawable(17302854));
        this.mForegroundPaint = new Paint(1);
        Paint paint = new Paint(1);
        this.mTransparentPaint = paint;
        this.mCutoutPath = new Path();
        this.mForegroundPath = new Path();
        Path path = new Path();
        this.mAttributionPath = path;
        this.mAttributionScaleMatrix = new Matrix();
        this.mScaledAttributionPath = new Path();
        this.mDarkIntensity = -1.0f;
        this.mChangeDot = new Runnable() { // from class: com.android.settingslib.graph.SignalDrawable.1
            @Override // java.lang.Runnable
            public void run() {
                SignalDrawable signalDrawable = SignalDrawable.this;
                int i = signalDrawable.mCurrentDot + 1;
                signalDrawable.mCurrentDot = i;
                if (i == 3) {
                    SignalDrawable.this.mCurrentDot = 0;
                }
                SignalDrawable.this.invalidateSelf();
                SignalDrawable.this.mHandler.postDelayed(SignalDrawable.this.mChangeDot, 1000L);
            }
        };
        path.set(PathParser.createPathFromPathData(context.getString(17040046)));
        updateScaledAttributionPath();
        this.mCutoutWidthFraction = context.getResources().getFloat(17105116);
        this.mCutoutHeightFraction = context.getResources().getFloat(17105115);
        this.mDarkModeFillColor = Utils.getColorStateListDefaultColor(context, R$color.dark_mode_icon_color_single_tone);
        this.mLightModeFillColor = Utils.getColorStateListDefaultColor(context, R$color.light_mode_icon_color_single_tone);
        this.mIntrinsicSize = context.getResources().getDimensionPixelSize(R$dimen.signal_icon_size);
        paint.setColor(context.getColor(17170445));
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        this.mHandler = new Handler();
        setDarkIntensity(0.0f);
    }

    private void updateScaledAttributionPath() {
        if (getBounds().isEmpty()) {
            this.mAttributionScaleMatrix.setScale(1.0f, 1.0f);
        } else {
            this.mAttributionScaleMatrix.setScale(getBounds().width() / 24.0f, getBounds().height() / 24.0f);
        }
        this.mAttributionPath.transform(this.mAttributionScaleMatrix, this.mScaledAttributionPath);
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.mIntrinsicSize;
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.mIntrinsicSize;
    }

    private void updateAnimation() {
        boolean z = isInState(3) && isVisible();
        if (z == this.mAnimating) {
            return;
        }
        this.mAnimating = z;
        if (z) {
            this.mChangeDot.run();
        } else {
            this.mHandler.removeCallbacks(this.mChangeDot);
        }
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    protected boolean onLevelChange(int i) {
        super.onLevelChange(unpackLevel(i));
        updateAnimation();
        setTintList(ColorStateList.valueOf(this.mForegroundPaint.getColor()));
        invalidateSelf();
        return true;
    }

    private int unpackLevel(int i) {
        return (i & 255) + (((65280 & i) >> 8) == CellSignalStrength.getNumSignalStrengthLevels() + 1 ? 10 : 0);
    }

    public void setDarkIntensity(float f) {
        if (f == this.mDarkIntensity) {
            return;
        }
        setTintList(ColorStateList.valueOf(getFillColor(f)));
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public void setTintList(ColorStateList colorStateList) {
        super.setTintList(colorStateList);
        int color = this.mForegroundPaint.getColor();
        this.mForegroundPaint.setColor(colorStateList.getDefaultColor());
        if (color != this.mForegroundPaint.getColor()) {
            invalidateSelf();
        }
    }

    private int getFillColor(float f) {
        return getColorForDarkIntensity(f, this.mLightModeFillColor, this.mDarkModeFillColor);
    }

    private int getColorForDarkIntensity(float f, int i, int i2) {
        return ((Integer) ArgbEvaluator.getInstance().evaluate(f, Integer.valueOf(i), Integer.valueOf(i2))).intValue();
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        updateScaledAttributionPath();
        invalidateSelf();
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        canvas.saveLayer(null, null);
        float width = getBounds().width();
        float height = getBounds().height();
        boolean z = getLayoutDirection() == 1;
        if (z) {
            canvas.save();
            canvas.translate(width, 0.0f);
            canvas.scale(-1.0f, 1.0f);
        }
        super.draw(canvas);
        this.mCutoutPath.reset();
        this.mCutoutPath.setFillType(Path.FillType.WINDING);
        float round = Math.round(0.083333336f * width);
        if (isInState(3)) {
            float f = 0.125f * height;
            float f2 = height * 0.0625f;
            float f3 = f2 + f;
            float f4 = (width - round) - f;
            float f5 = (height - round) - f;
            this.mForegroundPath.reset();
            drawDotAndPadding(f4, f5, f2, f, 2);
            drawDotAndPadding(f4 - f3, f5, f2, f, 1);
            drawDotAndPadding(f4 - (f3 * 2.0f), f5, f2, f, 0);
            canvas.drawPath(this.mCutoutPath, this.mTransparentPaint);
            canvas.drawPath(this.mForegroundPath, this.mForegroundPaint);
        } else if (isInState(2)) {
            float f6 = (this.mCutoutWidthFraction * width) / 24.0f;
            float f7 = (this.mCutoutHeightFraction * height) / 24.0f;
            this.mCutoutPath.moveTo(width, height);
            this.mCutoutPath.rLineTo(-f6, 0.0f);
            this.mCutoutPath.rLineTo(0.0f, -f7);
            this.mCutoutPath.rLineTo(f6, 0.0f);
            this.mCutoutPath.rLineTo(0.0f, f7);
            canvas.drawPath(this.mCutoutPath, this.mTransparentPaint);
            canvas.drawPath(this.mScaledAttributionPath, this.mForegroundPaint);
        }
        if (z) {
            canvas.restore();
        }
        canvas.restore();
    }

    private void drawDotAndPadding(float f, float f2, float f3, float f4, int i) {
        if (i == this.mCurrentDot) {
            float f5 = f + f4;
            float f6 = f2 + f4;
            this.mForegroundPath.addRect(f, f2, f5, f6, Path.Direction.CW);
            this.mCutoutPath.addRect(f - f3, f2 - f3, f5 + f3, f6 + f3, Path.Direction.CW);
        }
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        super.setAlpha(i);
        this.mForegroundPaint.setAlpha(i);
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        super.setColorFilter(colorFilter);
        this.mForegroundPaint.setColorFilter(colorFilter);
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        updateAnimation();
        return visible;
    }

    private boolean isInState(int i) {
        return getState(getLevel()) == i;
    }
}
