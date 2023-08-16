package com.android.settingslib;

import android.content.Context;
import android.os.VibrationAttributes;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Switch;
import androidx.annotation.Keep;
import androidx.preference.PreferenceViewHolder;
import com.android.settingslib.core.instrumentation.SettingsJankMonitor;
/* loaded from: classes.dex */
public class PrimarySwitchPreference extends RestrictedPreference {
    private boolean mChecked;
    private boolean mCheckedSet;
    private boolean mEnableSwitch;
    private Switch mSwitch;
    private final Vibrator mVibrator;
    private static final VibrationAttributes VIBRATION_ATTRIBUTES_SWITCH = VibrationAttributes.createForUsage(212);
    private static final VibrationEffect EFFECT_CLICK = VibrationEffect.createPredefined(0);

    public PrimarySwitchPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mEnableSwitch = true;
        this.mVibrator = (Vibrator) context.getSystemService(Vibrator.class);
    }

    public PrimarySwitchPreference(Context context) {
        super(context);
        this.mEnableSwitch = true;
        this.mVibrator = (Vibrator) context.getSystemService(Vibrator.class);
    }

    @Override // com.android.settingslib.widget.TwoTargetPreference
    protected int getSecondTargetResId() {
        return R$layout.preference_widget_primary_switch;
    }

    @Override // com.android.settingslib.RestrictedPreference, com.android.settingslib.widget.TwoTargetPreference, androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        Switch r2 = (Switch) preferenceViewHolder.findViewById(R$id.switchWidget);
        this.mSwitch = r2;
        if (r2 != null) {
            r2.setOnClickListener(new View.OnClickListener() { // from class: com.android.settingslib.PrimarySwitchPreference$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PrimarySwitchPreference.this.lambda$onBindViewHolder$0(view);
                }
            });
            this.mSwitch.setOnTouchListener(new View.OnTouchListener() { // from class: com.android.settingslib.PrimarySwitchPreference$$ExternalSyntheticLambda1
                @Override // android.view.View.OnTouchListener
                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    boolean lambda$onBindViewHolder$1;
                    lambda$onBindViewHolder$1 = PrimarySwitchPreference.lambda$onBindViewHolder$1(view, motionEvent);
                    return lambda$onBindViewHolder$1;
                }
            });
            this.mSwitch.setContentDescription(getTitle());
            this.mSwitch.setChecked(this.mChecked);
            this.mSwitch.setEnabled(this.mEnableSwitch);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onBindViewHolder$0(View view) {
        Switch r3 = this.mSwitch;
        if (r3 == null || r3.isEnabled()) {
            boolean z = !this.mChecked;
            if (callChangeListener(Boolean.valueOf(z))) {
                SettingsJankMonitor.detectToggleJank(getKey(), this.mSwitch);
                setChecked(z);
                persistBoolean(z);
            }
            this.mVibrator.vibrate(EFFECT_CLICK, VIBRATION_ATTRIBUTES_SWITCH);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$onBindViewHolder$1(View view, MotionEvent motionEvent) {
        return motionEvent.getActionMasked() == 2;
    }

    public boolean isChecked() {
        return this.mSwitch != null && this.mChecked;
    }

    @Keep
    public Boolean getCheckedState() {
        if (this.mCheckedSet) {
            return Boolean.valueOf(this.mChecked);
        }
        return null;
    }

    public void setChecked(boolean z) {
        if ((this.mChecked != z) || !this.mCheckedSet) {
            this.mChecked = z;
            this.mCheckedSet = true;
            Switch r2 = this.mSwitch;
            if (r2 != null) {
                r2.setChecked(z);
            }
        }
    }

    public void setSwitchEnabled(boolean z) {
        this.mEnableSwitch = z;
        Switch r0 = this.mSwitch;
        if (r0 != null) {
            r0.setEnabled(z);
        }
    }

    public Switch getSwitch() {
        return this.mSwitch;
    }

    @Override // com.android.settingslib.widget.TwoTargetPreference
    protected boolean shouldHideSecondTarget() {
        return getSecondTargetResId() == 0;
    }
}
