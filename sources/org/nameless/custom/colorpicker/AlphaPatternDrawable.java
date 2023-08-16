package org.nameless.custom.colorpicker;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
/* loaded from: classes2.dex */
public class AlphaPatternDrawable extends Drawable {
    private Bitmap mBitmap;
    private int mRectangleSize;
    private int numRectanglesHorizontal;
    private int numRectanglesVertical;
    private Paint mPaint = new Paint();
    private Paint mPaintWhite = new Paint();
    private Paint mPaintGray = new Paint();

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public AlphaPatternDrawable(int i) {
        this.mRectangleSize = 10;
        this.mRectangleSize = i;
        this.mPaintWhite.setColor(-1);
        this.mPaintGray.setColor(-3421237);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Bitmap bitmap = this.mBitmap;
        if (bitmap != null) {
            canvas.drawBitmap(bitmap, (Rect) null, getBounds(), this.mPaint);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        throw new UnsupportedOperationException("Alpha is not supported by this drawwable.");
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        throw new UnsupportedOperationException("ColorFilter is not supported by this drawwable.");
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        int height = rect.height();
        this.numRectanglesHorizontal = (int) Math.ceil(rect.width() / this.mRectangleSize);
        this.numRectanglesVertical = (int) Math.ceil(height / this.mRectangleSize);
        generatePatternBitmap();
    }

    private void generatePatternBitmap() {
        if (getBounds().width() <= 0 || getBounds().height() <= 0) {
            return;
        }
        this.mBitmap = Bitmap.createBitmap(getBounds().width(), getBounds().height(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(this.mBitmap);
        Rect rect = new Rect();
        boolean z = true;
        for (int i = 0; i <= this.numRectanglesVertical; i++) {
            boolean z2 = z;
            for (int i2 = 0; i2 <= this.numRectanglesHorizontal; i2++) {
                int i3 = this.mRectangleSize;
                int i4 = i * i3;
                rect.top = i4;
                int i5 = i2 * i3;
                rect.left = i5;
                rect.bottom = i4 + i3;
                rect.right = i5 + i3;
                canvas.drawRect(rect, z2 ? this.mPaintWhite : this.mPaintGray);
                z2 = !z2;
            }
            z = !z;
        }
    }
}
