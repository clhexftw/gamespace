package com.google.android.material.timepicker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.R$attr;
import com.google.android.material.R$color;
import com.google.android.material.R$dimen;
import com.google.android.material.R$id;
import com.google.android.material.R$layout;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.timepicker.ClockHandView;
import java.util.Arrays;
/* loaded from: classes2.dex */
class ClockFaceView extends RadialViewGroup implements ClockHandView.OnRotateListener {
    private final int clockHandPadding;
    private final ClockHandView clockHandView;
    private final int clockSize;
    private float currentHandRotation;
    private final int[] gradientColors;
    private final float[] gradientPositions;
    private final int minimumHeight;
    private final int minimumWidth;
    private final RectF scratch;
    private final ColorStateList textColor;
    private final SparseArray<TextView> textViewPool;
    private final Rect textViewRect;
    private final AccessibilityDelegateCompat valueAccessibilityDelegate;
    private String[] values;

    public ClockFaceView(Context context) {
        this(context, null);
    }

    public ClockFaceView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.materialClockStyle);
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public ClockFaceView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.textViewRect = new Rect();
        this.scratch = new RectF();
        this.textViewPool = new SparseArray<>();
        this.gradientPositions = new float[]{0.0f, 0.9f, 1.0f};
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ClockFaceView, i, R$style.Widget_MaterialComponents_TimePicker_Clock);
        Resources resources = getResources();
        ColorStateList colorStateList = MaterialResources.getColorStateList(context, obtainStyledAttributes, R$styleable.ClockFaceView_clockNumberTextColor);
        this.textColor = colorStateList;
        LayoutInflater.from(context).inflate(R$layout.material_clockface_view, (ViewGroup) this, true);
        ClockHandView clockHandView = (ClockHandView) findViewById(R$id.material_clock_hand);
        this.clockHandView = clockHandView;
        this.clockHandPadding = resources.getDimensionPixelSize(R$dimen.material_clock_hand_padding);
        int colorForState = colorStateList.getColorForState(new int[]{16842913}, colorStateList.getDefaultColor());
        this.gradientColors = new int[]{colorForState, colorForState, colorStateList.getDefaultColor()};
        clockHandView.addOnRotateListener(this);
        int defaultColor = AppCompatResources.getColorStateList(context, R$color.material_timepicker_clockface).getDefaultColor();
        ColorStateList colorStateList2 = MaterialResources.getColorStateList(context, obtainStyledAttributes, R$styleable.ClockFaceView_clockFaceBackgroundColor);
        setBackgroundColor(colorStateList2 != null ? colorStateList2.getDefaultColor() : defaultColor);
        getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: com.google.android.material.timepicker.ClockFaceView.1
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                if (ClockFaceView.this.isShown()) {
                    ClockFaceView.this.getViewTreeObserver().removeOnPreDrawListener(this);
                    ClockFaceView.this.setRadius(((ClockFaceView.this.getHeight() / 2) - ClockFaceView.this.clockHandView.getSelectorRadius()) - ClockFaceView.this.clockHandPadding);
                    return true;
                }
                return true;
            }
        });
        setFocusable(true);
        obtainStyledAttributes.recycle();
        this.valueAccessibilityDelegate = new AccessibilityDelegateCompat() { // from class: com.google.android.material.timepicker.ClockFaceView.2
            @Override // androidx.core.view.AccessibilityDelegateCompat
            public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
                int intValue = ((Integer) view.getTag(R$id.material_value_index)).intValue();
                if (intValue > 0) {
                    accessibilityNodeInfoCompat.setTraversalAfter((View) ClockFaceView.this.textViewPool.get(intValue - 1));
                }
                accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(0, 1, intValue, 1, false, view.isSelected()));
                accessibilityNodeInfoCompat.setClickable(true);
                accessibilityNodeInfoCompat.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK);
            }

            @Override // androidx.core.view.AccessibilityDelegateCompat
            public boolean performAccessibilityAction(View view, int i2, Bundle bundle) {
                if (i2 == 16) {
                    long uptimeMillis = SystemClock.uptimeMillis();
                    float x = view.getX() + (view.getWidth() / 2.0f);
                    float height = (view.getHeight() / 2.0f) + view.getY();
                    ClockFaceView.this.clockHandView.onTouchEvent(MotionEvent.obtain(uptimeMillis, uptimeMillis, 0, x, height, 0));
                    ClockFaceView.this.clockHandView.onTouchEvent(MotionEvent.obtain(uptimeMillis, uptimeMillis, 1, x, height, 0));
                    return true;
                }
                return super.performAccessibilityAction(view, i2, bundle);
            }
        };
        String[] strArr = new String[12];
        Arrays.fill(strArr, "");
        setValues(strArr, 0);
        this.minimumHeight = resources.getDimensionPixelSize(R$dimen.material_time_picker_minimum_screen_height);
        this.minimumWidth = resources.getDimensionPixelSize(R$dimen.material_time_picker_minimum_screen_width);
        this.clockSize = resources.getDimensionPixelSize(R$dimen.material_clock_size);
    }

    public void setValues(String[] strArr, int i) {
        this.values = strArr;
        updateTextViews(i);
    }

    private void updateTextViews(int i) {
        LayoutInflater from = LayoutInflater.from(getContext());
        int size = this.textViewPool.size();
        for (int i2 = 0; i2 < Math.max(this.values.length, size); i2++) {
            TextView textView = this.textViewPool.get(i2);
            if (i2 >= this.values.length) {
                removeView(textView);
                this.textViewPool.remove(i2);
            } else {
                if (textView == null) {
                    textView = (TextView) from.inflate(R$layout.material_clockface_textview, (ViewGroup) this, false);
                    this.textViewPool.put(i2, textView);
                    addView(textView);
                }
                textView.setVisibility(0);
                textView.setText(this.values[i2]);
                textView.setTag(R$id.material_value_index, Integer.valueOf(i2));
                ViewCompat.setAccessibilityDelegate(textView, this.valueAccessibilityDelegate);
                textView.setTextColor(this.textColor);
                if (i != 0) {
                    textView.setContentDescription(getResources().getString(i, this.values[i2]));
                }
            }
        }
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        AccessibilityNodeInfoCompat.wrap(accessibilityNodeInfo).setCollectionInfo(AccessibilityNodeInfoCompat.CollectionInfoCompat.obtain(1, this.values.length, false, 1));
    }

    @Override // com.google.android.material.timepicker.RadialViewGroup
    public void setRadius(int i) {
        if (i != getRadius()) {
            super.setRadius(i);
            this.clockHandView.setCircleRadius(getRadius());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        findIntersectingTextView();
    }

    private void findIntersectingTextView() {
        RectF currentSelectorBox = this.clockHandView.getCurrentSelectorBox();
        for (int i = 0; i < this.textViewPool.size(); i++) {
            TextView textView = this.textViewPool.get(i);
            if (textView != null) {
                textView.getDrawingRect(this.textViewRect);
                offsetDescendantRectToMyCoords(textView, this.textViewRect);
                textView.setSelected(currentSelectorBox.contains(this.textViewRect.centerX(), this.textViewRect.centerY()));
                textView.getPaint().setShader(getGradientForTextView(currentSelectorBox, this.textViewRect, textView));
                textView.invalidate();
            }
        }
    }

    private RadialGradient getGradientForTextView(RectF rectF, Rect rect, TextView textView) {
        this.scratch.set(rect);
        this.scratch.offset(textView.getPaddingLeft(), textView.getPaddingTop());
        if (RectF.intersects(rectF, this.scratch)) {
            return new RadialGradient(rectF.centerX() - this.scratch.left, rectF.centerY() - this.scratch.top, rectF.width() * 0.5f, this.gradientColors, this.gradientPositions, Shader.TileMode.CLAMP);
        }
        return null;
    }

    @Override // com.google.android.material.timepicker.ClockHandView.OnRotateListener
    public void onRotate(float f, boolean z) {
        if (Math.abs(this.currentHandRotation - f) > 0.001f) {
            this.currentHandRotation = f;
            findIntersectingTextView();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.View
    public void onMeasure(int i, int i2) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int max3 = (int) (this.clockSize / max3(this.minimumHeight / displayMetrics.heightPixels, this.minimumWidth / displayMetrics.widthPixels, 1.0f));
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(max3, 1073741824);
        setMeasuredDimension(max3, max3);
        super.onMeasure(makeMeasureSpec, makeMeasureSpec);
    }

    private static float max3(float f, float f2, float f3) {
        return Math.max(Math.max(f, f2), f3);
    }
}
