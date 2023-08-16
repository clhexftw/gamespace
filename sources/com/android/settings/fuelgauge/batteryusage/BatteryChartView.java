package com.android.settings.fuelgauge.batteryusage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import com.android.settings.R;
import com.android.settings.fuelgauge.batteryusage.BatteryChartViewModel;
import com.android.settingslib.Utils;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
/* loaded from: classes.dex */
public class BatteryChartView extends AppCompatImageView implements View.OnClickListener {
    private static final int DIVIDER_COLOR = Color.parseColor("#CDCCC5");
    private AccessibilityNodeProvider mAccessibilityNodeProvider;
    private final List<Rect> mAxisLabelsBounds;
    private int mDividerHeight;
    private Paint mDividerPaint;
    private int mDividerWidth;
    private int mHoveredIndex;
    private final Rect mIndent;
    private OnSelectListener mOnSelectListener;
    private final Rect[] mPercentageBounds;
    private final String[] mPercentages;
    private int mTextPadding;
    private Paint mTextPaint;
    float mTouchUpEventX;
    private int mTrapezoidColor;
    private float mTrapezoidHOffset;
    private int mTrapezoidHoverColor;
    private Paint mTrapezoidPaint;
    TrapezoidSlot[] mTrapezoidSlots;
    private int mTrapezoidSolidColor;
    private float mTrapezoidVOffset;
    private BatteryChartViewModel mViewModel;

    /* loaded from: classes.dex */
    public interface OnSelectListener {
        void onSelect(int i);
    }

    public BatteryChartView(Context context) {
        super(context, null);
        this.mPercentages = getPercentages();
        this.mIndent = new Rect();
        this.mPercentageBounds = new Rect[]{new Rect(), new Rect(), new Rect()};
        this.mAxisLabelsBounds = new ArrayList();
        this.mHoveredIndex = -2;
        this.mTouchUpEventX = Float.MIN_VALUE;
    }

    public BatteryChartView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mPercentages = getPercentages();
        this.mIndent = new Rect();
        this.mPercentageBounds = new Rect[]{new Rect(), new Rect(), new Rect()};
        this.mAxisLabelsBounds = new ArrayList();
        this.mHoveredIndex = -2;
        this.mTouchUpEventX = Float.MIN_VALUE;
        initializeColors(context);
        setOnClickListener(this);
        setClickable(false);
        requestLayout();
    }

    public void setViewModel(BatteryChartViewModel batteryChartViewModel) {
        if (batteryChartViewModel == null) {
            this.mViewModel = null;
            invalidate();
            return;
        }
        Log.d("BatteryChartView", String.format("setViewModel(): size: %d, selectedIndex: %d.", Integer.valueOf(batteryChartViewModel.size()), Integer.valueOf(batteryChartViewModel.selectedIndex())));
        this.mViewModel = batteryChartViewModel;
        initializeAxisLabelsBounds();
        initializeTrapezoidSlots(batteryChartViewModel.size() - 1);
        setClickable(hasAnyValidTrapezoid(batteryChartViewModel));
        requestLayout();
    }

    public void setOnSelectListener(OnSelectListener onSelectListener) {
        this.mOnSelectListener = onSelectListener;
    }

    public void setCompanionTextView(TextView textView) {
        if (textView != null) {
            textView.draw(new Canvas());
            this.mTextPaint = textView.getPaint();
        } else {
            this.mTextPaint = null;
        }
        requestLayout();
    }

    @Override // android.widget.ImageView, android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        Paint paint = this.mTextPaint;
        if (paint != null) {
            paint.setTextAlign(Paint.Align.LEFT);
            int i3 = 0;
            while (true) {
                String[] strArr = this.mPercentages;
                if (i3 >= strArr.length) {
                    break;
                }
                Paint paint2 = this.mTextPaint;
                String str = strArr[i3];
                paint2.getTextBounds(str, 0, str.length(), this.mPercentageBounds[i3]);
                i3++;
            }
            this.mIndent.top = this.mPercentageBounds[0].height();
            this.mIndent.right = this.mPercentageBounds[0].width() + this.mTextPadding;
            if (this.mViewModel != null) {
                int i4 = 0;
                for (int i5 = 0; i5 < this.mViewModel.size(); i5++) {
                    String text = this.mViewModel.getText(i5);
                    this.mTextPaint.getTextBounds(text, 0, text.length(), this.mAxisLabelsBounds.get(i5));
                    i4 = Math.max(i4, -this.mAxisLabelsBounds.get(i5).top);
                }
                this.mIndent.bottom = i4 + Math.round(this.mTextPadding * 2.0f);
            }
            Log.d("BatteryChartView", "setIndent:" + this.mPercentageBounds[0]);
            return;
        }
        this.mIndent.set(0, 0, 0, 0);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        drawHorizontalDividers(canvas);
        if (this.mViewModel == null) {
            return;
        }
        drawVerticalDividers(canvas);
        drawTrapezoids(canvas);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 1) {
            this.mTouchUpEventX = motionEvent.getX();
        } else if (action == 3) {
            this.mTouchUpEventX = Float.MIN_VALUE;
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 7 || action == 9) {
            int trapezoidIndex = getTrapezoidIndex(motionEvent.getX());
            if (this.mHoveredIndex != trapezoidIndex) {
                this.mHoveredIndex = trapezoidIndex;
                invalidate();
                sendAccessibilityEventForHover(128);
            }
            return true;
        } else if (action == 10) {
            if (this.mHoveredIndex != -2) {
                sendAccessibilityEventForHover(256);
                this.mHoveredIndex = -2;
                invalidate();
            }
            return true;
        } else {
            return super.onTouchEvent(motionEvent);
        }
    }

    @Override // android.view.View
    public void onHoverChanged(boolean z) {
        super.onHoverChanged(z);
        if (z) {
            return;
        }
        this.mHoveredIndex = -2;
        invalidate();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        float f = this.mTouchUpEventX;
        if (f == Float.MIN_VALUE) {
            Log.w("BatteryChartView", "invalid motion event for onClick() callback");
        } else {
            onTrapezoidClicked(view, getTrapezoidIndex(f));
        }
    }

    @Override // android.view.View
    public AccessibilityNodeProvider getAccessibilityNodeProvider() {
        if (this.mViewModel == null) {
            return super.getAccessibilityNodeProvider();
        }
        if (this.mAccessibilityNodeProvider == null) {
            this.mAccessibilityNodeProvider = new BatteryChartAccessibilityNodeProvider();
        }
        return this.mAccessibilityNodeProvider;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onTrapezoidClicked(View view, int i) {
        if (isValidToDraw(this.mViewModel, i)) {
            OnSelectListener onSelectListener = this.mOnSelectListener;
            if (onSelectListener != null) {
                if (i == this.mViewModel.selectedIndex()) {
                    i = -1;
                }
                onSelectListener.onSelect(i);
            }
            view.performHapticFeedback(6);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean sendAccessibilityEvent(int i, int i2) {
        ViewParent parent = getParent();
        if (parent == null || !AccessibilityManager.getInstance(((ImageView) this).mContext).isEnabled()) {
            return false;
        }
        AccessibilityEvent accessibilityEvent = new AccessibilityEvent(i2);
        accessibilityEvent.setSource(this, i);
        accessibilityEvent.setEnabled(true);
        accessibilityEvent.setClassName(getAccessibilityClassName());
        accessibilityEvent.setPackageName(getContext().getPackageName());
        return parent.requestSendAccessibilityEvent(this, accessibilityEvent);
    }

    private void sendAccessibilityEventForHover(int i) {
        if (isTrapezoidIndexValid(this.mViewModel, this.mHoveredIndex)) {
            sendAccessibilityEvent(this.mHoveredIndex, i);
        }
    }

    private void initializeTrapezoidSlots(int i) {
        this.mTrapezoidSlots = new TrapezoidSlot[i];
        int i2 = 0;
        while (true) {
            TrapezoidSlot[] trapezoidSlotArr = this.mTrapezoidSlots;
            if (i2 >= trapezoidSlotArr.length) {
                return;
            }
            trapezoidSlotArr[i2] = new TrapezoidSlot();
            i2++;
        }
    }

    private void initializeColors(Context context) {
        setBackgroundColor(0);
        int colorAccentDefaultColor = Utils.getColorAccentDefaultColor(context);
        this.mTrapezoidSolidColor = colorAccentDefaultColor;
        this.mTrapezoidColor = Utils.getDisabled(context, colorAccentDefaultColor);
        this.mTrapezoidHoverColor = Utils.getColorAttrDefaultColor(context, 17956903);
        Resources resources = getContext().getResources();
        this.mDividerWidth = resources.getDimensionPixelSize(R.dimen.chartview_divider_width);
        this.mDividerHeight = resources.getDimensionPixelSize(R.dimen.chartview_divider_height);
        Paint paint = new Paint();
        this.mDividerPaint = paint;
        paint.setAntiAlias(true);
        this.mDividerPaint.setColor(DIVIDER_COLOR);
        this.mDividerPaint.setStyle(Paint.Style.STROKE);
        this.mDividerPaint.setStrokeWidth(this.mDividerWidth);
        Log.i("BatteryChartView", "mDividerWidth:" + this.mDividerWidth);
        Log.i("BatteryChartView", "mDividerHeight:" + this.mDividerHeight);
        this.mTrapezoidHOffset = resources.getDimension(R.dimen.chartview_trapezoid_margin_start);
        this.mTrapezoidVOffset = resources.getDimension(R.dimen.chartview_trapezoid_margin_bottom);
        Paint paint2 = new Paint();
        this.mTrapezoidPaint = paint2;
        paint2.setAntiAlias(true);
        this.mTrapezoidPaint.setColor(this.mTrapezoidSolidColor);
        this.mTrapezoidPaint.setStyle(Paint.Style.FILL);
        this.mTrapezoidPaint.setPathEffect(new CornerPathEffect(resources.getDimensionPixelSize(R.dimen.chartview_trapezoid_radius)));
        this.mTextPadding = resources.getDimensionPixelSize(R.dimen.chartview_text_padding);
    }

    private void drawHorizontalDividers(Canvas canvas) {
        int width = getWidth() - this.mIndent.right;
        int height = getHeight();
        Rect rect = this.mIndent;
        int i = rect.top;
        int i2 = (height - i) - rect.bottom;
        float f = i + (this.mDividerWidth * 0.5f);
        float f2 = width;
        canvas.drawLine(0.0f, f, f2, f, this.mDividerPaint);
        drawPercentage(canvas, 0, f);
        int i3 = this.mDividerWidth;
        float f3 = this.mIndent.top + i3 + ((((i2 - (i3 * 2)) - this.mTrapezoidVOffset) - this.mDividerHeight) * 0.5f);
        canvas.drawLine(0.0f, f3, f2, f3, this.mDividerPaint);
        drawPercentage(canvas, 1, f3);
        float f4 = this.mIndent.top + ((i2 - this.mDividerHeight) - (this.mDividerWidth * 0.5f));
        canvas.drawLine(0.0f, f4, f2, f4, this.mDividerPaint);
        drawPercentage(canvas, 2, f4);
    }

    private void drawPercentage(Canvas canvas, int i, float f) {
        Paint paint = this.mTextPaint;
        if (paint != null) {
            paint.setTextAlign(Paint.Align.RIGHT);
            canvas.drawText(this.mPercentages[i], getWidth(), f + (this.mPercentageBounds[i].height() * 0.5f), this.mTextPaint);
        }
    }

    private void drawVerticalDividers(Canvas canvas) {
        TrapezoidSlot[] trapezoidSlotArr;
        Rect[] axisLabelDisplayAreas;
        int width = getWidth() - this.mIndent.right;
        int length = this.mTrapezoidSlots.length + 1;
        float length2 = (width - (this.mDividerWidth * length)) / trapezoidSlotArr.length;
        float height = getHeight() - this.mIndent.bottom;
        float f = height - this.mDividerHeight;
        float f2 = this.mTrapezoidHOffset;
        int i = this.mDividerWidth;
        float f3 = f2 + (i * 0.5f);
        float f4 = i * 0.5f;
        int i2 = 0;
        while (i2 < length) {
            canvas.drawLine(f4, f, f4, height, this.mDividerPaint);
            float f5 = this.mDividerWidth + f4 + length2;
            TrapezoidSlot[] trapezoidSlotArr2 = this.mTrapezoidSlots;
            if (i2 < trapezoidSlotArr2.length) {
                trapezoidSlotArr2[i2].mLeft = Math.round(f4 + f3);
                this.mTrapezoidSlots[i2].mRight = Math.round(f5 - f3);
            }
            i2++;
            f4 = f5;
        }
        if (this.mViewModel != null) {
            float height2 = getHeight() - this.mTextPadding;
            if (AnonymousClass1.$SwitchMap$com$android$settings$fuelgauge$batteryusage$BatteryChartViewModel$AxisLabelPosition[this.mViewModel.axisLabelPosition().ordinal()] == 1) {
                int i3 = this.mDividerWidth;
                axisLabelDisplayAreas = getAxisLabelDisplayAreas(this.mViewModel.size() - 1, i3 + (0.5f * length2), i3 + length2, height2, false);
            } else {
                int size = this.mViewModel.size();
                int i4 = this.mDividerWidth;
                axisLabelDisplayAreas = getAxisLabelDisplayAreas(size, i4 * 0.5f, i4 + length2, height2, true);
            }
            drawAxisLabels(canvas, axisLabelDisplayAreas, height2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.android.settings.fuelgauge.batteryusage.BatteryChartView$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$android$settings$fuelgauge$batteryusage$BatteryChartViewModel$AxisLabelPosition;

        static {
            int[] iArr = new int[BatteryChartViewModel.AxisLabelPosition.values().length];
            $SwitchMap$com$android$settings$fuelgauge$batteryusage$BatteryChartViewModel$AxisLabelPosition = iArr;
            try {
                iArr[BatteryChartViewModel.AxisLabelPosition.CENTER_OF_TRAPEZOIDS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$android$settings$fuelgauge$batteryusage$BatteryChartViewModel$AxisLabelPosition[BatteryChartViewModel.AxisLabelPosition.BETWEEN_TRAPEZOIDS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private Rect[] getAxisLabelDisplayAreas(int i, float f, float f2, float f3, boolean z) {
        Rect[] rectArr = new Rect[i];
        for (int i2 = 0; i2 < i; i2++) {
            float width = this.mAxisLabelsBounds.get(i2).width();
            float f4 = (i2 * f2) + f;
            if (z) {
                if (i2 == 0) {
                    f4 += width * 0.5f;
                }
                if (i2 == i - 1) {
                    f4 -= width * 0.5f;
                }
            }
            float f5 = f4 - (0.5f * width);
            float f6 = this.mAxisLabelsBounds.get(i2).top + f3;
            rectArr[i2] = new Rect(Math.round(f5), Math.round(f6), Math.round(width + f5), Math.round(this.mAxisLabelsBounds.get(i2).height() + f6));
        }
        return rectArr;
    }

    private void drawAxisLabels(Canvas canvas, Rect[] rectArr, float f) {
        int length = rectArr.length - 1;
        drawAxisLabelText(canvas, 0, rectArr[0], f);
        drawAxisLabelText(canvas, length, rectArr[length], f);
        drawAxisLabelsBetweenStartIndexAndEndIndex(canvas, rectArr, 0, length, f);
    }

    private void drawAxisLabelsBetweenStartIndexAndEndIndex(Canvas canvas, Rect[] rectArr, int i, int i2, float f) {
        int i3 = i2 - i;
        if (i3 <= 1) {
            return;
        }
        if (i3 % 2 == 0) {
            int i4 = (i + i2) / 2;
            if (hasOverlap(rectArr, i, i4) || hasOverlap(rectArr, i4, i2)) {
                return;
            }
            drawAxisLabelText(canvas, i4, rectArr[i4], f);
            drawAxisLabelsBetweenStartIndexAndEndIndex(canvas, rectArr, i, i4, f);
            drawAxisLabelsBetweenStartIndexAndEndIndex(canvas, rectArr, i4, i2, f);
            return;
        }
        int round = Math.round(i3 / 3.0f) + i;
        int round2 = Math.round((i3 * 2) / 3.0f) + i;
        if (hasOverlap(rectArr, i, round) || hasOverlap(rectArr, round, round2) || hasOverlap(rectArr, round2, i2)) {
            return;
        }
        drawAxisLabelText(canvas, round, rectArr[round], f);
        drawAxisLabelText(canvas, round2, rectArr[round2], f);
        drawAxisLabelsBetweenStartIndexAndEndIndex(canvas, rectArr, i, round, f);
        drawAxisLabelsBetweenStartIndexAndEndIndex(canvas, rectArr, round, round2, f);
        drawAxisLabelsBetweenStartIndexAndEndIndex(canvas, rectArr, round2, i2, f);
    }

    private boolean hasOverlap(Rect[] rectArr, int i, int i2) {
        return ((float) rectArr[i].right) + (((float) this.mTextPadding) * 2.3f) > ((float) rectArr[i2].left);
    }

    private void drawAxisLabelText(Canvas canvas, int i, Rect rect, float f) {
        this.mTextPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(this.mViewModel.getText(i), rect.centerX(), f, this.mTextPaint);
    }

    private void drawTrapezoids(Canvas canvas) {
        Rect rect;
        Integer level;
        Integer level2;
        if (this.mViewModel == null) {
            return;
        }
        int height = (getHeight() - this.mIndent.bottom) - this.mDividerHeight;
        int i = this.mDividerWidth;
        float f = this.mTrapezoidVOffset;
        float f2 = (height - i) - f;
        float f3 = (((f2 - (i * 0.5f)) - rect.top) - f) / 100.0f;
        Path path = new Path();
        int i2 = 0;
        while (i2 < this.mTrapezoidSlots.length) {
            if (isValidToDraw(this.mViewModel, i2)) {
                int i3 = (this.mViewModel.selectedIndex() == i2 || this.mViewModel.selectedIndex() == -1) ? this.mTrapezoidSolidColor : this.mTrapezoidColor;
                int i4 = this.mHoveredIndex;
                boolean z = i4 == i2 && isValidToDraw(this.mViewModel, i4);
                Paint paint = this.mTrapezoidPaint;
                if (z) {
                    i3 = this.mTrapezoidHoverColor;
                }
                paint.setColor(i3);
                Objects.requireNonNull(this.mViewModel.getLevel(i2));
                float round = Math.round(f2 - (level.intValue() * f3));
                Objects.requireNonNull(this.mViewModel.getLevel(i2 + 1));
                path.reset();
                path.moveTo(this.mTrapezoidSlots[i2].mLeft, f2);
                path.lineTo(this.mTrapezoidSlots[i2].mLeft, round);
                path.lineTo(this.mTrapezoidSlots[i2].mRight, Math.round(f2 - (level2.intValue() * f3)));
                path.lineTo(this.mTrapezoidSlots[i2].mRight, f2);
                path.lineTo(this.mTrapezoidSlots[i2].mLeft, f2);
                path.lineTo(this.mTrapezoidSlots[i2].mLeft, round);
                canvas.drawPath(path, this.mTrapezoidPaint);
            }
            i2++;
        }
    }

    private int getTrapezoidIndex(float f) {
        if (this.mTrapezoidSlots == null) {
            return -2;
        }
        int i = 0;
        while (true) {
            TrapezoidSlot[] trapezoidSlotArr = this.mTrapezoidSlots;
            if (i >= trapezoidSlotArr.length) {
                return -2;
            }
            TrapezoidSlot trapezoidSlot = trapezoidSlotArr[i];
            float f2 = trapezoidSlot.mLeft;
            float f3 = this.mTrapezoidHOffset;
            if (f >= f2 - f3 && f <= trapezoidSlot.mRight + f3) {
                return i;
            }
            i++;
        }
    }

    private void initializeAxisLabelsBounds() {
        this.mAxisLabelsBounds.clear();
        for (int i = 0; i < this.mViewModel.size(); i++) {
            this.mAxisLabelsBounds.add(new Rect());
        }
    }

    private static boolean isTrapezoidValid(BatteryChartViewModel batteryChartViewModel, int i) {
        return (batteryChartViewModel.getLevel(i) == null || batteryChartViewModel.getLevel(i + 1) == null) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isTrapezoidIndexValid(BatteryChartViewModel batteryChartViewModel, int i) {
        return batteryChartViewModel != null && i >= 0 && i < batteryChartViewModel.size() - 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isValidToDraw(BatteryChartViewModel batteryChartViewModel, int i) {
        return isTrapezoidIndexValid(batteryChartViewModel, i) && isTrapezoidValid(batteryChartViewModel, i);
    }

    private static boolean hasAnyValidTrapezoid(BatteryChartViewModel batteryChartViewModel) {
        for (int i = 0; i < batteryChartViewModel.size() - 1; i++) {
            if (isTrapezoidValid(batteryChartViewModel, i)) {
                return true;
            }
        }
        return false;
    }

    private static String[] getPercentages() {
        return new String[]{Utils.formatPercentage(100.0d, true), Utils.formatPercentage(50.0d, true), Utils.formatPercentage(0.0d, true)};
    }

    /* loaded from: classes.dex */
    private class BatteryChartAccessibilityNodeProvider extends AccessibilityNodeProvider {
        private BatteryChartAccessibilityNodeProvider() {
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public AccessibilityNodeInfo createAccessibilityNodeInfo(int i) {
            if (i == -1) {
                AccessibilityNodeInfo accessibilityNodeInfo = new AccessibilityNodeInfo(BatteryChartView.this);
                for (int i2 = 0; i2 < BatteryChartView.this.mViewModel.size() - 1; i2++) {
                    accessibilityNodeInfo.addChild(BatteryChartView.this, i2);
                }
                return accessibilityNodeInfo;
            } else if (!BatteryChartView.isTrapezoidIndexValid(BatteryChartView.this.mViewModel, i)) {
                Log.w("BatteryChartView", "Invalid virtual view id:" + i);
                return null;
            } else {
                AccessibilityNodeInfo accessibilityNodeInfo2 = new AccessibilityNodeInfo(BatteryChartView.this, i);
                BatteryChartView.this.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo2);
                accessibilityNodeInfo2.setClickable(BatteryChartView.isValidToDraw(BatteryChartView.this.mViewModel, i));
                accessibilityNodeInfo2.setText(BatteryChartView.this.mViewModel.getFullText(i));
                accessibilityNodeInfo2.setContentDescription(BatteryChartView.this.mViewModel.getFullText(i));
                Rect rect = new Rect();
                BatteryChartView.this.getBoundsOnScreen(rect, true);
                float f = rect.left;
                rect.left = Math.round(BatteryChartView.this.mTrapezoidSlots[i].mLeft + f);
                rect.right = Math.round(f + BatteryChartView.this.mTrapezoidSlots[i].mRight);
                accessibilityNodeInfo2.setBoundsInScreen(rect);
                return accessibilityNodeInfo2;
            }
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public boolean performAction(int i, int i2, Bundle bundle) {
            if (i == -1) {
                return BatteryChartView.this.performAccessibilityAction(i2, bundle);
            }
            if (i2 == 16) {
                BatteryChartView batteryChartView = BatteryChartView.this;
                batteryChartView.onTrapezoidClicked(batteryChartView, i);
                return true;
            } else if (i2 != 64) {
                if (i2 == 128) {
                    return BatteryChartView.this.sendAccessibilityEvent(i, 65536);
                }
                return BatteryChartView.this.performAccessibilityAction(i2, bundle);
            } else {
                return BatteryChartView.this.sendAccessibilityEvent(i, 32768);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class TrapezoidSlot {
        public float mLeft;
        public float mRight;

        TrapezoidSlot() {
        }

        public String toString() {
            return String.format(Locale.US, "TrapezoidSlot[%f,%f]", Float.valueOf(this.mLeft), Float.valueOf(this.mRight));
        }
    }
}
