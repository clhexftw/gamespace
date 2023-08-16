package com.android.settings.accessibility;

import android.content.Context;
import android.content.IntentFilter;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.R;
import com.android.settings.accessibility.CustomVibrationPreferenceConfig;
import com.android.settings.core.TogglePreferenceController;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.core.lifecycle.events.OnStop;
/* loaded from: classes.dex */
public abstract class CustomVibrationTogglePreferenceController extends TogglePreferenceController implements LifecycleObserver, OnStart, OnStop {
    private final int mDefaultValue;
    protected final CustomVibrationPreferenceConfig mPreferenceConfig;
    private final CustomVibrationPreferenceConfig.SettingObserver mSettingsContentObserver;

    @Override // com.android.settings.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return 0;
    }

    @Override // com.android.settings.core.TogglePreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ Class getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.settings.core.TogglePreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.settings.core.TogglePreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.settings.core.TogglePreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public CustomVibrationTogglePreferenceController(Context context, String str, CustomVibrationPreferenceConfig customVibrationPreferenceConfig) {
        this(context, str, customVibrationPreferenceConfig, 1);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public CustomVibrationTogglePreferenceController(Context context, String str, CustomVibrationPreferenceConfig customVibrationPreferenceConfig, int i) {
        super(context, str);
        this.mPreferenceConfig = customVibrationPreferenceConfig;
        this.mSettingsContentObserver = new CustomVibrationPreferenceConfig.SettingObserver(customVibrationPreferenceConfig);
        this.mDefaultValue = i;
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStart
    public void onStart() {
        this.mSettingsContentObserver.register(this.mContext);
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStop
    public void onStop() {
        this.mSettingsContentObserver.unregister(this.mContext);
    }

    @Override // com.android.settings.core.TogglePreferenceController, com.android.settings.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        Preference findPreference = preferenceScreen.findPreference(getPreferenceKey());
        this.mSettingsContentObserver.onDisplayPreference(this, findPreference);
        findPreference.setEnabled(this.mPreferenceConfig.isPreferenceEnabled());
    }

    @Override // com.android.settings.core.TogglePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        super.updateState(preference);
        if (preference != null) {
            preference.setEnabled(this.mPreferenceConfig.isPreferenceEnabled());
        }
    }

    @Override // com.android.settings.core.TogglePreferenceController
    public boolean isChecked() {
        return this.mPreferenceConfig.isPreferenceEnabled() && this.mPreferenceConfig.readValue(this.mDefaultValue) == 1;
    }

    @Override // com.android.settings.core.TogglePreferenceController
    public boolean setChecked(boolean z) {
        if (this.mPreferenceConfig.isPreferenceEnabled()) {
            boolean value = this.mPreferenceConfig.setValue(z ? 1 : 0);
            if (value && z) {
                this.mPreferenceConfig.playVibrationPreview();
            }
            return value;
        }
        return false;
    }

    @Override // com.android.settings.core.TogglePreferenceController, com.android.settings.slices.Sliceable
    public int getSliceHighlightMenuRes() {
        return R.string.menu_key_accessibility;
    }
}
