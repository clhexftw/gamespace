package com.android.settings;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.SeekBar;
/* loaded from: classes.dex */
public class IntervalSeekBar extends SeekBar {
    private float mDefault;
    private float mMax;
    private float mMin;
    private float mMultiplier;

    public IntervalSeekBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.IntervalSeekBar, 0, 0);
        this.mMax = obtainStyledAttributes.getFloat(R$styleable.IntervalSeekBar_maxI, 1.5f);
        this.mMin = obtainStyledAttributes.getFloat(R$styleable.IntervalSeekBar_minI, 0.5f);
        this.mDefault = obtainStyledAttributes.getFloat(R$styleable.IntervalSeekBar_defaultValuePure, 1.0f);
        this.mMultiplier = (float) Math.pow(10.0d, obtainStyledAttributes.getInt(R$styleable.IntervalSeekBar_digits, 0));
        float f = this.mMin;
        float f2 = this.mMax;
        if (f > f2) {
            this.mMax = f;
            this.mMin = f2;
        }
        setMax(convertFloatToProgress(this.mMax));
        setProgressFloat(this.mDefault);
        obtainStyledAttributes.recycle();
    }

    public float getProgressFloat() {
        return (getProgress() / this.mMultiplier) + this.mMin;
    }

    public void setProgressFloat(float f) {
        setProgress(convertFloatToProgress(f));
    }

    private int convertFloatToProgress(float f) {
        return Math.round((f - this.mMin) * this.mMultiplier);
    }

    public float getMinimum() {
        return this.mMin;
    }

    public float getMaximum() {
        return this.mMax;
    }

    public float getDefault() {
        return this.mDefault;
    }

    public void setMaximum(float f) {
        this.mMax = f;
        setMax(convertFloatToProgress(f));
    }

    public void setMinimum(float f) {
        this.mMin = f;
    }
}
