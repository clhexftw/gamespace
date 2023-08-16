package com.android.launcher3.icons;

import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
/* loaded from: classes.dex */
public class ShadowGenerator {
    private final BlurMaskFilter mDefaultBlurMaskFilter;
    private final int mIconSize;
    private final Paint mBlurPaint = new Paint(3);
    private final Paint mDrawPaint = new Paint(3);

    public ShadowGenerator(int i) {
        this.mIconSize = i;
        this.mDefaultBlurMaskFilter = new BlurMaskFilter(i * 0.035f, BlurMaskFilter.Blur.NORMAL);
    }

    public synchronized void drawShadow(Bitmap bitmap, Canvas canvas) {
        int[] iArr = new int[2];
        this.mBlurPaint.setMaskFilter(this.mDefaultBlurMaskFilter);
        Bitmap extractAlpha = bitmap.extractAlpha(this.mBlurPaint, iArr);
        this.mDrawPaint.setAlpha(25);
        canvas.drawBitmap(extractAlpha, iArr[0], iArr[1], this.mDrawPaint);
        this.mDrawPaint.setAlpha(7);
        canvas.drawBitmap(extractAlpha, iArr[0], iArr[1] + (this.mIconSize * 0.020833334f), this.mDrawPaint);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addPathShadow(Path path, Canvas canvas) {
        this.mDrawPaint.setMaskFilter(this.mDefaultBlurMaskFilter);
        this.mDrawPaint.setAlpha(25);
        canvas.drawPath(path, this.mDrawPaint);
        int save = canvas.save();
        this.mDrawPaint.setAlpha(7);
        canvas.translate(0.0f, this.mIconSize * 0.020833334f);
        canvas.drawPath(path, this.mDrawPaint);
        canvas.restoreToCount(save);
        this.mDrawPaint.setMaskFilter(null);
    }

    public static float getScaleForBounds(RectF rectF) {
        float min = Math.min(Math.min(rectF.left, rectF.right), rectF.top);
        float f = min < 0.035f ? 0.465f / (0.5f - min) : 1.0f;
        float f2 = rectF.bottom;
        return f2 < 0.055833332f ? Math.min(f, 0.44416666f / (0.5f - f2)) : f;
    }
}
