package com.android.settings.accessibility;

import android.content.Context;
import android.content.IntentFilter;
/* loaded from: classes.dex */
public class SwitchVibrationPreferenceController extends CustomVibrationTogglePreferenceController {
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

    public SwitchVibrationPreferenceController(Context context, String str) {
        super(context, str, new CustomVibrationPreferenceConfig(context, "haptic_feedback_enabled", "custom_haptic_on_switch"));
    }
}
