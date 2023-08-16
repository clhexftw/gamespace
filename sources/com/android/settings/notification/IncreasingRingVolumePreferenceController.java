package com.android.settings.notification;

import android.content.Context;
import androidx.preference.PreferenceScreen;
import com.android.settings.Utils;
import com.android.settings.notification.IncreasingRingVolumePreference;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnResume;
import com.android.settingslib.core.lifecycle.events.OnStop;
/* loaded from: classes.dex */
public class IncreasingRingVolumePreferenceController extends AdjustVolumeRestrictedPreferenceController implements LifecycleObserver, OnResume, OnStop {
    private static final String KEY_INCREASING_RING_VOLUME = "increasing_ring_volume";
    private IncreasingRingVolumePreference.Callback mCallback;
    private AudioHelper mHelper;
    private IncreasingRingVolumePreference mPreference;

    @Override // com.android.settings.notification.AdjustVolumeRestrictedPreferenceController, com.android.settings.core.SliderPreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ Class getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.settings.core.SliderPreferenceController
    public int getMax() {
        return 0;
    }

    @Override // com.android.settings.core.SliderPreferenceController
    public int getMin() {
        return 0;
    }

    @Override // com.android.settings.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return KEY_INCREASING_RING_VOLUME;
    }

    @Override // com.android.settings.notification.AdjustVolumeRestrictedPreferenceController, com.android.settings.core.SliderPreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ int getSliceHighlightMenuRes() {
        return super.getSliceHighlightMenuRes();
    }

    @Override // com.android.settings.core.SliderPreferenceController, com.android.settings.core.BasePreferenceController
    public int getSliceType() {
        return 0;
    }

    @Override // com.android.settings.core.SliderPreferenceController
    public int getSliderPosition() {
        return 0;
    }

    @Override // com.android.settings.notification.AdjustVolumeRestrictedPreferenceController, com.android.settings.core.SliderPreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.settings.notification.AdjustVolumeRestrictedPreferenceController, com.android.settings.core.SliderPreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isPublicSlice() {
        return super.isPublicSlice();
    }

    @Override // com.android.settings.notification.AdjustVolumeRestrictedPreferenceController, com.android.settings.core.SliderPreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isSliceable() {
        return super.isSliceable();
    }

    @Override // com.android.settings.core.SliderPreferenceController
    public boolean setSliderPosition(int i) {
        return false;
    }

    @Override // com.android.settings.notification.AdjustVolumeRestrictedPreferenceController, com.android.settings.core.SliderPreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public IncreasingRingVolumePreferenceController(Context context) {
        super(context, KEY_INCREASING_RING_VOLUME);
        this.mHelper = new AudioHelper(context);
    }

    public void setCallback(IncreasingRingVolumePreference.Callback callback) {
        this.mCallback = callback;
    }

    @Override // com.android.settings.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        if (isAvailable()) {
            IncreasingRingVolumePreference increasingRingVolumePreference = (IncreasingRingVolumePreference) preferenceScreen.findPreference(getPreferenceKey());
            this.mPreference = increasingRingVolumePreference;
            increasingRingVolumePreference.setCallback(this.mCallback);
        }
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnResume
    public void onResume() {
        IncreasingRingVolumePreference increasingRingVolumePreference = this.mPreference;
        if (increasingRingVolumePreference != null) {
            increasingRingVolumePreference.onActivityResume();
        }
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStop
    public void onStop() {
        IncreasingRingVolumePreference increasingRingVolumePreference = this.mPreference;
        if (increasingRingVolumePreference != null) {
            increasingRingVolumePreference.onActivityStop();
        }
    }

    @Override // com.android.settings.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return Utils.isVoiceCapable(this.mContext) && !this.mHelper.isSingleVolume() ? 0 : 3;
    }
}
