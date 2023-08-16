package org.nameless.custom.colorpicker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
/* loaded from: classes2.dex */
public class ColorPickerView extends View {
    private float ALPHA_PANEL_HEIGHT;
    private float HUE_PANEL_WIDTH;
    private float PALETTE_CIRCLE_TRACKER_RADIUS;
    private float PANEL_SPACING;
    private float RECTANGLE_TRACKER_OFFSET;
    private int mAlpha;
    private Paint mAlphaPaint;
    private AlphaPatternDrawable mAlphaPattern;
    private RectF mAlphaRect;
    private Shader mAlphaShader;
    private String mAlphaSliderText;
    private Paint mAlphaTextPaint;
    private int mBorderColor;
    private Paint mBorderPaint;
    private float mDensity;
    private float mDrawingOffset;
    private RectF mDrawingRect;
    private float mHue;
    private Paint mHuePaint;
    private RectF mHueRect;
    private Shader mHueShader;
    private Paint mHueTrackerPaint;
    private int mLastTouchedPanel;
    private OnColorChangedListener mListener;
    private float mSat;
    private Shader mSatShader;
    private Paint mSatValPaint;
    private RectF mSatValRect;
    private Paint mSatValTrackerPaint;
    private boolean mShowAlphaPanel;
    private int mSliderTrackerColor;
    private Point mStartTouchPoint;
    private float mVal;
    private Shader mValShader;

    /* loaded from: classes2.dex */
    public interface OnColorChangedListener {
        void onColorChanged(int i);
    }

    public ColorPickerView(Context context) {
        this(context, null);
    }

    public ColorPickerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ColorPickerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.HUE_PANEL_WIDTH = 30.0f;
        this.ALPHA_PANEL_HEIGHT = 20.0f;
        this.PANEL_SPACING = 10.0f;
        this.PALETTE_CIRCLE_TRACKER_RADIUS = 5.0f;
        this.RECTANGLE_TRACKER_OFFSET = 2.0f;
        this.mDensity = 1.0f;
        this.mAlpha = 255;
        this.mHue = 360.0f;
        this.mSat = 0.0f;
        this.mVal = 0.0f;
        this.mAlphaSliderText = "";
        this.mSliderTrackerColor = -14935012;
        this.mBorderColor = -9539986;
        this.mShowAlphaPanel = false;
        this.mLastTouchedPanel = 0;
        this.mStartTouchPoint = null;
        init();
    }

    private void init() {
        setLayerType(1, null);
        float f = getContext().getResources().getDisplayMetrics().density;
        this.mDensity = f;
        this.PALETTE_CIRCLE_TRACKER_RADIUS *= f;
        this.RECTANGLE_TRACKER_OFFSET *= f;
        this.HUE_PANEL_WIDTH *= f;
        this.ALPHA_PANEL_HEIGHT *= f;
        this.PANEL_SPACING *= f;
        this.mDrawingOffset = calculateRequiredOffset();
        initPaintTools();
        setFocusable(true);
        setFocusableInTouchMode(true);
    }

    private void initPaintTools() {
        this.mSatValPaint = new Paint();
        this.mSatValTrackerPaint = new Paint();
        this.mHuePaint = new Paint();
        this.mHueTrackerPaint = new Paint();
        this.mAlphaPaint = new Paint();
        this.mAlphaTextPaint = new Paint();
        this.mBorderPaint = new Paint();
        this.mSatValTrackerPaint.setStyle(Paint.Style.STROKE);
        this.mSatValTrackerPaint.setStrokeWidth(this.mDensity * 2.0f);
        this.mSatValTrackerPaint.setAntiAlias(true);
        this.mHueTrackerPaint.setColor(this.mSliderTrackerColor);
        this.mHueTrackerPaint.setStyle(Paint.Style.STROKE);
        this.mHueTrackerPaint.setStrokeWidth(this.mDensity * 2.0f);
        this.mHueTrackerPaint.setAntiAlias(true);
        this.mAlphaTextPaint.setColor(-14935012);
        this.mAlphaTextPaint.setTextSize(this.mDensity * 14.0f);
        this.mAlphaTextPaint.setAntiAlias(true);
        this.mAlphaTextPaint.setTextAlign(Paint.Align.CENTER);
        this.mAlphaTextPaint.setFakeBoldText(true);
    }

    private float calculateRequiredOffset() {
        return Math.max(Math.max(this.PALETTE_CIRCLE_TRACKER_RADIUS, this.RECTANGLE_TRACKER_OFFSET), this.mDensity * 1.0f) * 1.5f;
    }

    private int[] buildHueColorArray() {
        int[] iArr = new int[361];
        int i = 360;
        int i2 = 0;
        while (i >= 0) {
            iArr[i2] = Color.HSVToColor(new float[]{i, 1.0f, 1.0f});
            i--;
            i2++;
        }
        return iArr;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.mDrawingRect.width() <= 0.0f || this.mDrawingRect.height() <= 0.0f) {
            return;
        }
        drawSatValPanel(canvas);
        drawHuePanel(canvas);
        drawAlphaPanel(canvas);
    }

    private void drawSatValPanel(Canvas canvas) {
        RectF rectF = this.mSatValRect;
        this.mBorderPaint.setColor(this.mBorderColor);
        RectF rectF2 = this.mDrawingRect;
        canvas.drawRect(rectF2.left, rectF2.top, rectF.right + 1.0f, rectF.bottom + 1.0f, this.mBorderPaint);
        if (this.mValShader == null) {
            float f = rectF.left;
            this.mValShader = new LinearGradient(f, rectF.top, f, rectF.bottom, -1, -16777216, Shader.TileMode.CLAMP);
        }
        int HSVToColor = Color.HSVToColor(new float[]{this.mHue, 1.0f, 1.0f});
        float f2 = rectF.left;
        float f3 = rectF.top;
        this.mSatShader = new LinearGradient(f2, f3, rectF.right, f3, -1, HSVToColor, Shader.TileMode.CLAMP);
        this.mSatValPaint.setShader(new ComposeShader(this.mValShader, this.mSatShader, PorterDuff.Mode.MULTIPLY));
        canvas.drawRect(rectF, this.mSatValPaint);
        Point satValToPoint = satValToPoint(this.mSat, this.mVal);
        this.mSatValTrackerPaint.setColor(-16777216);
        canvas.drawCircle(satValToPoint.x, satValToPoint.y, this.PALETTE_CIRCLE_TRACKER_RADIUS - (this.mDensity * 1.0f), this.mSatValTrackerPaint);
        this.mSatValTrackerPaint.setColor(-2236963);
        canvas.drawCircle(satValToPoint.x, satValToPoint.y, this.PALETTE_CIRCLE_TRACKER_RADIUS, this.mSatValTrackerPaint);
    }

    private void drawHuePanel(Canvas canvas) {
        RectF rectF = this.mHueRect;
        this.mBorderPaint.setColor(this.mBorderColor);
        canvas.drawRect(rectF.left - 1.0f, rectF.top - 1.0f, rectF.right + 1.0f, rectF.bottom + 1.0f, this.mBorderPaint);
        if (this.mHueShader == null) {
            float f = rectF.left;
            LinearGradient linearGradient = new LinearGradient(f, rectF.top, f, rectF.bottom, buildHueColorArray(), (float[]) null, Shader.TileMode.CLAMP);
            this.mHueShader = linearGradient;
            this.mHuePaint.setShader(linearGradient);
        }
        canvas.drawRect(rectF, this.mHuePaint);
        float f2 = (this.mDensity * 4.0f) / 2.0f;
        Point hueToPoint = hueToPoint(this.mHue);
        RectF rectF2 = new RectF();
        float f3 = rectF.left;
        float f4 = this.RECTANGLE_TRACKER_OFFSET;
        rectF2.left = f3 - f4;
        rectF2.right = rectF.right + f4;
        int i = hueToPoint.y;
        rectF2.top = i - f2;
        rectF2.bottom = i + f2;
        canvas.drawRoundRect(rectF2, 2.0f, 2.0f, this.mHueTrackerPaint);
    }

    private void drawAlphaPanel(Canvas canvas) {
        RectF rectF;
        if (!this.mShowAlphaPanel || (rectF = this.mAlphaRect) == null || this.mAlphaPattern == null) {
            return;
        }
        this.mBorderPaint.setColor(this.mBorderColor);
        canvas.drawRect(rectF.left - 1.0f, rectF.top - 1.0f, rectF.right + 1.0f, rectF.bottom + 1.0f, this.mBorderPaint);
        this.mAlphaPattern.draw(canvas);
        float[] fArr = {this.mHue, this.mSat, this.mVal};
        int HSVToColor = Color.HSVToColor(fArr);
        int HSVToColor2 = Color.HSVToColor(0, fArr);
        float f = rectF.left;
        float f2 = rectF.top;
        LinearGradient linearGradient = new LinearGradient(f, f2, rectF.right, f2, HSVToColor, HSVToColor2, Shader.TileMode.CLAMP);
        this.mAlphaShader = linearGradient;
        this.mAlphaPaint.setShader(linearGradient);
        canvas.drawRect(rectF, this.mAlphaPaint);
        String str = this.mAlphaSliderText;
        if (str != null && str != "") {
            canvas.drawText(str, rectF.centerX(), rectF.centerY() + (this.mDensity * 4.0f), this.mAlphaTextPaint);
        }
        float f3 = (this.mDensity * 4.0f) / 2.0f;
        Point alphaToPoint = alphaToPoint(this.mAlpha);
        RectF rectF2 = new RectF();
        int i = alphaToPoint.x;
        rectF2.left = i - f3;
        rectF2.right = i + f3;
        float f4 = rectF.top;
        float f5 = this.RECTANGLE_TRACKER_OFFSET;
        rectF2.top = f4 - f5;
        rectF2.bottom = rectF.bottom + f5;
        canvas.drawRoundRect(rectF2, 2.0f, 2.0f, this.mHueTrackerPaint);
    }

    private Point hueToPoint(float f) {
        RectF rectF = this.mHueRect;
        float height = rectF.height();
        Point point = new Point();
        point.y = (int) ((height - ((f * height) / 360.0f)) + rectF.top);
        point.x = (int) rectF.left;
        return point;
    }

    private Point satValToPoint(float f, float f2) {
        RectF rectF = this.mSatValRect;
        float height = rectF.height();
        float width = rectF.width();
        Point point = new Point();
        point.x = (int) ((f * width) + rectF.left);
        point.y = (int) (((1.0f - f2) * height) + rectF.top);
        return point;
    }

    private Point alphaToPoint(int i) {
        RectF rectF = this.mAlphaRect;
        float width = rectF.width();
        Point point = new Point();
        point.x = (int) ((width - ((i * width) / 255.0f)) + rectF.left);
        point.y = (int) rectF.top;
        return point;
    }

    private float[] pointToSatVal(float f, float f2) {
        float f3;
        RectF rectF = this.mSatValRect;
        float[] fArr = new float[2];
        float width = rectF.width();
        float height = rectF.height();
        float f4 = rectF.left;
        float f5 = 0.0f;
        if (f < f4) {
            f3 = 0.0f;
        } else {
            f3 = f > rectF.right ? width : f - f4;
        }
        float f6 = rectF.top;
        if (f2 >= f6) {
            f5 = f2 > rectF.bottom ? height : f2 - f6;
        }
        fArr[0] = (1.0f / width) * f3;
        fArr[1] = 1.0f - ((1.0f / height) * f5);
        return fArr;
    }

    private float pointToHue(float f) {
        float f2;
        RectF rectF = this.mHueRect;
        float height = rectF.height();
        float f3 = rectF.top;
        if (f < f3) {
            f2 = 0.0f;
        } else {
            f2 = f > rectF.bottom ? height : f - f3;
        }
        return 360.0f - ((f2 * 360.0f) / height);
    }

    private int pointToAlpha(int i) {
        int i2;
        RectF rectF = this.mAlphaRect;
        int width = (int) rectF.width();
        float f = i;
        float f2 = rectF.left;
        if (f < f2) {
            i2 = 0;
        } else {
            i2 = f > rectF.right ? width : i - ((int) f2);
        }
        return 255 - ((i2 * 255) / width);
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x009a  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTrackballEvent(android.view.MotionEvent r9) {
        /*
            r8 = this;
            float r0 = r9.getX()
            float r1 = r9.getY()
            int r2 = r9.getAction()
            r3 = 2
            r4 = 0
            r5 = 1
            if (r2 != r3) goto L77
            int r2 = r8.mLastTouchedPanel
            r6 = 0
            if (r2 == 0) goto L4f
            r7 = 1092616192(0x41200000, float:10.0)
            if (r2 == r5) goto L39
            if (r2 == r3) goto L1e
            goto L77
        L1e:
            boolean r1 = r8.mShowAlphaPanel
            if (r1 == 0) goto L77
            android.graphics.RectF r1 = r8.mAlphaRect
            if (r1 != 0) goto L27
            goto L77
        L27:
            int r1 = r8.mAlpha
            float r1 = (float) r1
            float r0 = r0 * r7
            float r1 = r1 - r0
            int r0 = (int) r1
            r1 = 255(0xff, float:3.57E-43)
            if (r0 >= 0) goto L33
            r0 = r4
            goto L36
        L33:
            if (r0 <= r1) goto L36
            r0 = r1
        L36:
            r8.mAlpha = r0
            goto L4d
        L39:
            float r0 = r8.mHue
            float r1 = r1 * r7
            float r0 = r0 - r1
            int r1 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
            r2 = 1135869952(0x43b40000, float:360.0)
            if (r1 >= 0) goto L44
            goto L4b
        L44:
            int r1 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r1 <= 0) goto L4a
            r6 = r2
            goto L4b
        L4a:
            r6 = r0
        L4b:
            r8.mHue = r6
        L4d:
            r0 = r5
            goto L78
        L4f:
            float r2 = r8.mSat
            r7 = 1112014848(0x42480000, float:50.0)
            float r0 = r0 / r7
            float r2 = r2 + r0
            float r0 = r8.mVal
            float r1 = r1 / r7
            float r0 = r0 - r1
            int r1 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            r7 = 1065353216(0x3f800000, float:1.0)
            if (r1 >= 0) goto L61
            r2 = r6
            goto L66
        L61:
            int r1 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
            if (r1 <= 0) goto L66
            r2 = r7
        L66:
            int r1 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
            if (r1 >= 0) goto L6b
            goto L72
        L6b:
            int r1 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1))
            if (r1 <= 0) goto L71
            r6 = r7
            goto L72
        L71:
            r6 = r0
        L72:
            r8.mSat = r2
            r8.mVal = r6
            goto L4d
        L77:
            r0 = r4
        L78:
            if (r0 == 0) goto L9a
            org.nameless.custom.colorpicker.ColorPickerView$OnColorChangedListener r9 = r8.mListener
            if (r9 == 0) goto L96
            int r0 = r8.mAlpha
            r1 = 3
            float[] r1 = new float[r1]
            float r2 = r8.mHue
            r1[r4] = r2
            float r2 = r8.mSat
            r1[r5] = r2
            float r2 = r8.mVal
            r1[r3] = r2
            int r0 = android.graphics.Color.HSVToColor(r0, r1)
            r9.onColorChanged(r0)
        L96:
            r8.invalidate()
            return r5
        L9a:
            boolean r8 = super.onTrackballEvent(r9)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: org.nameless.custom.colorpicker.ColorPickerView.onTrackballEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean moveTrackersIfNeeded;
        int action = motionEvent.getAction();
        if (action == 0) {
            this.mStartTouchPoint = new Point((int) motionEvent.getX(), (int) motionEvent.getY());
            moveTrackersIfNeeded = moveTrackersIfNeeded(motionEvent);
        } else if (action != 1) {
            moveTrackersIfNeeded = action != 2 ? false : moveTrackersIfNeeded(motionEvent);
        } else {
            this.mStartTouchPoint = null;
            moveTrackersIfNeeded = moveTrackersIfNeeded(motionEvent);
        }
        if (moveTrackersIfNeeded) {
            OnColorChangedListener onColorChangedListener = this.mListener;
            if (onColorChangedListener != null) {
                onColorChangedListener.onColorChanged(Color.HSVToColor(this.mAlpha, new float[]{this.mHue, this.mSat, this.mVal}));
            }
            invalidate();
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }

    private boolean moveTrackersIfNeeded(MotionEvent motionEvent) {
        Point point = this.mStartTouchPoint;
        if (point == null) {
            return false;
        }
        float f = point.x;
        float f2 = point.y;
        if (this.mHueRect.contains(f, f2)) {
            this.mLastTouchedPanel = 1;
            this.mHue = pointToHue(motionEvent.getY());
        } else if (this.mSatValRect.contains(f, f2)) {
            this.mLastTouchedPanel = 0;
            float[] pointToSatVal = pointToSatVal(motionEvent.getX(), motionEvent.getY());
            this.mSat = pointToSatVal[0];
            this.mVal = pointToSatVal[1];
        } else {
            RectF rectF = this.mAlphaRect;
            if (rectF == null || !rectF.contains(f, f2)) {
                return false;
            }
            this.mLastTouchedPanel = 2;
            this.mAlpha = pointToAlpha((int) motionEvent.getX());
        }
        return true;
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int chooseWidth = chooseWidth(mode, size);
        int chooseHeight = chooseHeight(mode2, size2);
        if (!this.mShowAlphaPanel) {
            float f = this.PANEL_SPACING;
            float f2 = this.HUE_PANEL_WIDTH;
            int i3 = (int) ((chooseWidth - f) - f2);
            if (i3 > chooseHeight) {
                chooseWidth = (int) (chooseHeight + f + f2);
            } else {
                chooseHeight = i3;
            }
        } else {
            float f3 = this.ALPHA_PANEL_HEIGHT;
            float f4 = this.HUE_PANEL_WIDTH;
            int i4 = (int) ((chooseHeight - f3) + f4);
            if (i4 > chooseWidth) {
                chooseHeight = (int) ((chooseWidth - f4) + f3);
            } else {
                chooseWidth = i4;
            }
        }
        setMeasuredDimension(chooseWidth, chooseHeight);
    }

    private int chooseWidth(int i, int i2) {
        return (i == Integer.MIN_VALUE || i == 1073741824) ? i2 : getPrefferedWidth();
    }

    private int chooseHeight(int i, int i2) {
        return (i == Integer.MIN_VALUE || i == 1073741824) ? i2 : getPrefferedHeight();
    }

    private int getPrefferedWidth() {
        int prefferedHeight = getPrefferedHeight();
        if (this.mShowAlphaPanel) {
            prefferedHeight = (int) (prefferedHeight - (this.PANEL_SPACING + this.ALPHA_PANEL_HEIGHT));
        }
        return (int) (prefferedHeight + this.HUE_PANEL_WIDTH + this.PANEL_SPACING);
    }

    private int getPrefferedHeight() {
        int i = (int) (this.mDensity * 200.0f);
        return this.mShowAlphaPanel ? (int) (i + this.PANEL_SPACING + this.ALPHA_PANEL_HEIGHT) : i;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        RectF rectF = new RectF();
        this.mDrawingRect = rectF;
        rectF.left = this.mDrawingOffset + getPaddingLeft();
        this.mDrawingRect.right = (i - this.mDrawingOffset) - getPaddingRight();
        this.mDrawingRect.top = this.mDrawingOffset + getPaddingTop();
        this.mDrawingRect.bottom = (i2 - this.mDrawingOffset) - getPaddingBottom();
        setUpSatValRect();
        setUpHueRect();
        setUpAlphaRect();
    }

    private void setUpSatValRect() {
        RectF rectF = this.mDrawingRect;
        float height = rectF.height() - 2.0f;
        if (this.mShowAlphaPanel) {
            height -= this.PANEL_SPACING + this.ALPHA_PANEL_HEIGHT;
        }
        float f = rectF.left + 1.0f;
        float f2 = rectF.top + 1.0f;
        this.mSatValRect = new RectF(f, f2, height + f, f2 + height);
    }

    private void setUpHueRect() {
        RectF rectF = this.mDrawingRect;
        float f = rectF.right;
        this.mHueRect = new RectF((f - this.HUE_PANEL_WIDTH) + 1.0f, rectF.top + 1.0f, f - 1.0f, (rectF.bottom - 1.0f) - (this.mShowAlphaPanel ? this.PANEL_SPACING + this.ALPHA_PANEL_HEIGHT : 0.0f));
    }

    private void setUpAlphaRect() {
        if (this.mShowAlphaPanel) {
            RectF rectF = this.mDrawingRect;
            float f = rectF.bottom;
            this.mAlphaRect = new RectF(rectF.left + 1.0f, (f - this.ALPHA_PANEL_HEIGHT) + 1.0f, rectF.right - 1.0f, f - 1.0f);
            AlphaPatternDrawable alphaPatternDrawable = new AlphaPatternDrawable((int) (this.mDensity * 5.0f));
            this.mAlphaPattern = alphaPatternDrawable;
            alphaPatternDrawable.setBounds(Math.round(this.mAlphaRect.left), Math.round(this.mAlphaRect.top), Math.round(this.mAlphaRect.right), Math.round(this.mAlphaRect.bottom));
        }
    }

    public void setOnColorChangedListener(OnColorChangedListener onColorChangedListener) {
        this.mListener = onColorChangedListener;
    }

    public void setBorderColor(int i) {
        this.mBorderColor = i;
        invalidate();
    }

    public int getBorderColor() {
        return this.mBorderColor;
    }

    public int getColor() {
        return Color.HSVToColor(this.mAlpha, new float[]{this.mHue, this.mSat, this.mVal});
    }

    public void setColor(int i) {
        setColor(i, false);
    }

    public void setColor(int i, boolean z) {
        OnColorChangedListener onColorChangedListener;
        int alpha = Color.alpha(i);
        float[] fArr = new float[3];
        Color.RGBToHSV(Color.red(i), Color.green(i), Color.blue(i), fArr);
        this.mAlpha = alpha;
        float f = fArr[0];
        this.mHue = f;
        float f2 = fArr[1];
        this.mSat = f2;
        float f3 = fArr[2];
        this.mVal = f3;
        if (z && (onColorChangedListener = this.mListener) != null) {
            onColorChangedListener.onColorChanged(Color.HSVToColor(alpha, new float[]{f, f2, f3}));
        }
        invalidate();
    }

    public float getDrawingOffset() {
        return this.mDrawingOffset;
    }

    public void setAlphaSliderVisible(boolean z) {
        if (this.mShowAlphaPanel != z) {
            this.mShowAlphaPanel = z;
            this.mValShader = null;
            this.mSatShader = null;
            this.mHueShader = null;
            this.mAlphaShader = null;
            requestLayout();
        }
    }

    public void setSliderTrackerColor(int i) {
        this.mSliderTrackerColor = i;
        this.mHueTrackerPaint.setColor(i);
        invalidate();
    }

    public int getSliderTrackerColor() {
        return this.mSliderTrackerColor;
    }

    public void setAlphaSliderText(int i) {
        setAlphaSliderText(getContext().getString(i));
    }

    public void setAlphaSliderText(String str) {
        this.mAlphaSliderText = str;
        invalidate();
    }

    public String getAlphaSliderText() {
        return this.mAlphaSliderText;
    }
}
