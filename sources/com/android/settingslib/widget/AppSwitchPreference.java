package com.android.settingslib.widget;

import android.content.Context;
import android.os.VibrationAttributes;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.view.View;
import androidx.preference.PreferenceViewHolder;
import androidx.preference.SwitchPreference;
/* loaded from: classes.dex */
public class AppSwitchPreference extends SwitchPreference {
    private final Vibrator mVibrator;
    private static final VibrationAttributes VIBRATION_ATTRIBUTES_SWITCH = VibrationAttributes.createForUsage(212);
    private static final VibrationEffect EFFECT_CLICK = VibrationEffect.createPredefined(0);

    public AppSwitchPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setLayoutResource(R$layout.preference_app);
        this.mVibrator = (Vibrator) context.getSystemService(Vibrator.class);
    }

    @Override // androidx.preference.SwitchPreference, androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        View findViewById = preferenceViewHolder.findViewById(16908352);
        if (findViewById != null) {
            findViewById.getRootView().setFilterTouchesWhenObscured(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.preference.SwitchPreference, androidx.preference.Preference
    public void performClick(View view) {
        super.performClick(view);
        this.mVibrator.vibrate(EFFECT_CLICK, VIBRATION_ATTRIBUTES_SWITCH);
    }
}
