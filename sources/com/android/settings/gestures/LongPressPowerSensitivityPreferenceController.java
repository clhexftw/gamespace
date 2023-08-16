package com.android.settings.gestures;

import android.content.Context;
import android.content.IntentFilter;
import android.net.Uri;
import android.provider.Settings;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.core.SliderPreferenceController;
import com.android.settings.gestures.PowerMenuSettingsUtils;
import com.android.settings.widget.LabeledSeekBarPreference;
/* loaded from: classes.dex */
public class LongPressPowerSensitivityPreferenceController extends SliderPreferenceController implements PowerMenuSettingsUtils.SettingsStateCallback, LifecycleObserver {
    private LabeledSeekBarPreference mPreference;
    private final int[] mSensitivityValues;
    private final PowerMenuSettingsUtils mUtils;

    @Override // com.android.settings.core.SliderPreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ Class getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.settings.core.SliderPreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.settings.core.SliderPreferenceController
    public int getMin() {
        return 0;
    }

    @Override // com.android.settings.core.SliderPreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ int getSliceHighlightMenuRes() {
        return super.getSliceHighlightMenuRes();
    }

    @Override // com.android.settings.core.SliderPreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.settings.core.SliderPreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isPublicSlice() {
        return super.isPublicSlice();
    }

    @Override // com.android.settings.core.SliderPreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isSliceable() {
        return super.isSliceable();
    }

    @Override // com.android.settings.core.SliderPreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public LongPressPowerSensitivityPreferenceController(Context context, String str) {
        super(context, str);
        this.mSensitivityValues = context.getResources().getIntArray(17236093);
        this.mUtils = new PowerMenuSettingsUtils(context);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
        this.mUtils.registerObserver(this);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {
        this.mUtils.unregisterObserver();
    }

    @Override // com.android.settings.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        LabeledSeekBarPreference labeledSeekBarPreference = (LabeledSeekBarPreference) preferenceScreen.findPreference(getPreferenceKey());
        this.mPreference = labeledSeekBarPreference;
        if (labeledSeekBarPreference != null) {
            labeledSeekBarPreference.setContinuousUpdates(false);
            this.mPreference.setHapticFeedbackMode(1);
            this.mPreference.setMin(getMin());
            this.mPreference.setMax(getMax());
        }
    }

    @Override // com.android.settings.core.SliderPreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        super.updateState(preference);
        LabeledSeekBarPreference labeledSeekBarPreference = (LabeledSeekBarPreference) preference;
        labeledSeekBarPreference.setVisible(PowerMenuSettingsUtils.isLongPressPowerForAssistantEnabled(this.mContext) && getAvailabilityStatus() == 0);
        labeledSeekBarPreference.setProgress(getSliderPosition());
    }

    @Override // com.android.settings.core.BasePreferenceController
    public int getAvailabilityStatus() {
        int[] iArr = this.mSensitivityValues;
        return (iArr == null || iArr.length < 2 || !PowerMenuSettingsUtils.isLongPressPowerSettingAvailable(this.mContext)) ? 3 : 0;
    }

    @Override // com.android.settings.core.SliderPreferenceController
    public int getSliderPosition() {
        int[] iArr = this.mSensitivityValues;
        if (iArr == null) {
            return 0;
        }
        return closestValueIndex(iArr, getCurrentSensitivityValue());
    }

    @Override // com.android.settings.core.SliderPreferenceController
    public boolean setSliderPosition(int i) {
        int[] iArr = this.mSensitivityValues;
        if (iArr == null || i < 0 || i >= iArr.length) {
            return false;
        }
        return Settings.Global.putInt(this.mContext.getContentResolver(), "power_button_long_press_duration_ms", this.mSensitivityValues[i]);
    }

    @Override // com.android.settings.gestures.PowerMenuSettingsUtils.SettingsStateCallback
    public void onChange(Uri uri) {
        LabeledSeekBarPreference labeledSeekBarPreference = this.mPreference;
        if (labeledSeekBarPreference != null) {
            updateState(labeledSeekBarPreference);
        }
    }

    @Override // com.android.settings.core.SliderPreferenceController
    public int getMax() {
        int[] iArr = this.mSensitivityValues;
        if (iArr == null || iArr.length == 0) {
            return 0;
        }
        return iArr.length - 1;
    }

    private int getCurrentSensitivityValue() {
        return Settings.Global.getInt(this.mContext.getContentResolver(), "power_button_long_press_duration_ms", this.mContext.getResources().getInteger(17694870));
    }

    private static int closestValueIndex(int[] iArr, int i) {
        int i2 = Integer.MAX_VALUE;
        int i3 = 0;
        for (int i4 = 0; i4 < iArr.length; i4++) {
            int abs = Math.abs(iArr[i4] - i);
            if (abs < i2) {
                i3 = i4;
                i2 = abs;
            }
        }
        return i3;
    }
}
