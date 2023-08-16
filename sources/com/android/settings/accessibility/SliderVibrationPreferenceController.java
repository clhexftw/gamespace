package com.android.settings.accessibility;

import android.content.Context;
import android.content.IntentFilter;
import org.nameless.vibrator.VibratorExtManager;
/* loaded from: classes.dex */
public class SliderVibrationPreferenceController extends CustomVibrationTogglePreferenceController {
    @Override // com.android.settings.accessibility.CustomVibrationTogglePreferenceController, com.android.settings.core.TogglePreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ Class getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.settings.accessibility.CustomVibrationTogglePreferenceController, com.android.settings.core.TogglePreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.settings.accessibility.CustomVibrationTogglePreferenceController, com.android.settings.core.TogglePreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.settings.accessibility.CustomVibrationTogglePreferenceController, com.android.settings.core.TogglePreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public SliderVibrationPreferenceController(Context context, String str) {
        super(context, str, new CustomVibrationPreferenceConfig(context, "haptic_feedback_enabled", "custom_haptic_on_slider"));
    }

    @Override // com.android.settings.accessibility.CustomVibrationTogglePreferenceController, com.android.settings.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return VibratorExtManager.getInstance().isSupported() ? 0 : 3;
    }
}
