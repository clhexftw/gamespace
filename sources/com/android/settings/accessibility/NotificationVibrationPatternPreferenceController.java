package com.android.settings.accessibility;

import android.content.Context;
import android.content.IntentFilter;
import android.os.VibrationAttributes;
import org.nameless.vibrator.CustomVibrationAttributes;
/* loaded from: classes.dex */
public class NotificationVibrationPatternPreferenceController extends VibrationPatternPreferenceController {
    @Override // com.android.settings.accessibility.VibrationPatternPreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ Class getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.settings.accessibility.VibrationPatternPreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.settings.accessibility.VibrationPatternPreferenceController
    public String getSettings() {
        return "vibration_pattern_notification";
    }

    @Override // com.android.settings.accessibility.VibrationPatternPreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ int getSliceHighlightMenuRes() {
        return super.getSliceHighlightMenuRes();
    }

    @Override // com.android.settings.accessibility.VibrationPatternPreferenceController
    public int getType() {
        return 0;
    }

    @Override // com.android.settings.accessibility.VibrationPatternPreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.settings.accessibility.VibrationPatternPreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isPublicSlice() {
        return super.isPublicSlice();
    }

    @Override // com.android.settings.accessibility.VibrationPatternPreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isSliceable() {
        return super.isSliceable();
    }

    @Override // com.android.settings.accessibility.VibrationPatternPreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public NotificationVibrationPatternPreferenceController(Context context, String str) {
        super(context, str);
    }

    @Override // com.android.settings.accessibility.VibrationPatternPreferenceController
    public VibrationAttributes getAttribute() {
        return CustomVibrationAttributes.VIBRATION_ATTRIBUTES_PREVIEW_NOTIFICATION;
    }
}
