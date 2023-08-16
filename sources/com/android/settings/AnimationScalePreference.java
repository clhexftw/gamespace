package com.android.settings;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import com.android.settingslib.CustomDialogPreferenceCompat;
/* loaded from: classes.dex */
public class AnimationScalePreference extends CustomDialogPreferenceCompat implements SeekBar.OnSeekBarChangeListener {
    private float mScale;
    private TextView mScaleText;
    private IntervalSeekBar mSeekBar;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.preference.DialogPreference, androidx.preference.Preference
    public void onClick() {
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    public AnimationScalePreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mScale = 1.0f;
        setPositiveButtonText(17039370);
        setNegativeButtonText(17039360);
        setDialogLayoutResource(R.layout.preference_dialog_animation_scale);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settingslib.CustomDialogPreferenceCompat
    public void onBindDialogView(View view) {
        super.onBindDialogView(view);
        TextView textView = (TextView) view.findViewById(R.id.scale);
        this.mScaleText = textView;
        textView.setText(String.valueOf(this.mScale) + "x");
        IntervalSeekBar intervalSeekBar = (IntervalSeekBar) view.findViewById(R.id.scale_seekbar);
        this.mSeekBar = intervalSeekBar;
        intervalSeekBar.setProgressFloat(this.mScale);
        this.mSeekBar.setOnSeekBarChangeListener(this);
    }

    public void setScale(float f) {
        this.mScale = f;
        setSummary(String.valueOf(f) + "x");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settingslib.CustomDialogPreferenceCompat
    public void onDialogClosed(boolean z) {
        if (z) {
            callChangeListener(Float.valueOf(this.mSeekBar.getProgressFloat()));
        }
    }

    public void click() {
        super.onClick();
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        TextView textView = this.mScaleText;
        textView.setText(String.valueOf(this.mSeekBar.getProgressFloat()) + "x");
    }
}
