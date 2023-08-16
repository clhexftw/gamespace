package com.android.settings.accessibility;

import android.content.Context;
import android.content.IntentFilter;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.accessibility.CustomVibrationPreferenceConfig;
import com.android.settings.core.BasePreferenceController;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.core.lifecycle.events.OnStop;
/* loaded from: classes.dex */
public class BackGestureVibrationPreferenceController extends BasePreferenceController implements LifecycleObserver, OnStart, OnStop, Preference.OnPreferenceChangeListener {
    private final CustomVibrationPreferenceConfig mPreferenceConfig;
    private final CustomVibrationPreferenceConfig.SettingObserver mSettingsContentObserver;

    @Override // com.android.settings.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return 0;
    }

    @Override // com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ Class getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ int getSliceHighlightMenuRes() {
        return super.getSliceHighlightMenuRes();
    }

    @Override // com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isPublicSlice() {
        return super.isPublicSlice();
    }

    @Override // com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isSliceable() {
        return super.isSliceable();
    }

    @Override // com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public BackGestureVibrationPreferenceController(Context context, String str) {
        super(context, str);
        CustomVibrationPreferenceConfig customVibrationPreferenceConfig = new CustomVibrationPreferenceConfig(context, "haptic_feedback_enabled", "custom_haptic_on_back_gesture");
        this.mPreferenceConfig = customVibrationPreferenceConfig;
        this.mSettingsContentObserver = new CustomVibrationPreferenceConfig.SettingObserver(customVibrationPreferenceConfig);
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public final boolean onPreferenceChange(Preference preference, Object obj) {
        if (preference instanceof ListPreference) {
            this.mPreferenceConfig.setValue(Integer.parseInt(String.valueOf(obj)));
            return true;
        }
        return true;
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStart
    public void onStart() {
        this.mSettingsContentObserver.register(this.mContext);
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStop
    public void onStop() {
        this.mSettingsContentObserver.unregister(this.mContext);
    }

    @Override // com.android.settings.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        Preference findPreference = preferenceScreen.findPreference(getPreferenceKey());
        this.mSettingsContentObserver.onDisplayPreference(this, findPreference);
        findPreference.setEnabled(this.mPreferenceConfig.isPreferenceEnabled());
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        super.updateState(preference);
        if (preference == null || !(preference instanceof ListPreference)) {
            return;
        }
        boolean isPreferenceEnabled = this.mPreferenceConfig.isPreferenceEnabled();
        ((ListPreference) preference).setValue(String.valueOf(isPreferenceEnabled ? this.mPreferenceConfig.readValue(2) : 0));
        preference.setEnabled(isPreferenceEnabled);
    }
}
