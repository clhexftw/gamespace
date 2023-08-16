package com.android.settings.display.iris;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.PowerManager;
import android.provider.Settings;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.R;
import com.android.settings.core.TogglePreferenceController;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.core.lifecycle.events.OnStop;
import org.nameless.custom.preference.SwitchPreference;
import org.nameless.wm.DisplayResolutionManager;
/* loaded from: classes.dex */
public class MemcPreferenceController extends TogglePreferenceController implements LifecycleObserver, OnStart, OnStop {
    private final PowerManager mPowerManager;
    private boolean mPowerSaveOn;
    private final BroadcastReceiver mPowerSaveReceiver;
    private SwitchPreference mPreference;
    private SettingObserver mSettingObserver;

    @Override // com.android.settings.core.TogglePreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ Class getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.settings.core.TogglePreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.settings.core.TogglePreferenceController, com.android.settings.slices.Sliceable
    public int getSliceHighlightMenuRes() {
        return 0;
    }

    @Override // com.android.settings.core.TogglePreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.settings.core.TogglePreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public MemcPreferenceController(Context context, String str) {
        super(context, str);
        this.mPowerSaveReceiver = new BroadcastReceiver() { // from class: com.android.settings.display.iris.MemcPreferenceController.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                if (intent.getAction() == "android.os.action.POWER_SAVE_MODE_CHANGED") {
                    MemcPreferenceController memcPreferenceController = MemcPreferenceController.this;
                    memcPreferenceController.mPowerSaveOn = memcPreferenceController.mPowerManager.isPowerSaveMode();
                    MemcPreferenceController.this.updateEnabledAndSummary();
                }
            }
        };
        PowerManager powerManager = (PowerManager) context.getSystemService(PowerManager.class);
        this.mPowerManager = powerManager;
        this.mPowerSaveOn = powerManager.isPowerSaveMode();
    }

    @Override // com.android.settings.core.TogglePreferenceController, com.android.settings.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreference = (SwitchPreference) preferenceScreen.findPreference(getPreferenceKey());
        this.mSettingObserver = new SettingObserver(this.mPreference);
    }

    @Override // com.android.settings.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return (FeaturesHolder.MEMC_FHD_SUPPORTED || FeaturesHolder.MEMC_QHD_SUPPORTED) ? 0 : 3;
    }

    @Override // com.android.settings.core.TogglePreferenceController
    public boolean isChecked() {
        return Settings.System.getIntForUser(this.mContext.getContentResolver(), "iris_memc_enabled", 0, -2) == 1;
    }

    @Override // com.android.settings.core.TogglePreferenceController
    public boolean setChecked(boolean z) {
        return Settings.System.putIntForUser(this.mContext.getContentResolver(), "iris_memc_enabled", z ? 1 : 0, -2);
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStart
    public void onStart() {
        SettingObserver settingObserver = this.mSettingObserver;
        if (settingObserver != null) {
            settingObserver.register(this.mContext.getContentResolver());
            this.mSettingObserver.onChange(false, null);
        }
        this.mContext.registerReceiver(this.mPowerSaveReceiver, new IntentFilter("android.os.action.POWER_SAVE_MODE_CHANGED"));
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStop
    public void onStop() {
        this.mContext.unregisterReceiver(this.mPowerSaveReceiver);
        SettingObserver settingObserver = this.mSettingObserver;
        if (settingObserver != null) {
            settingObserver.unregister(this.mContext.getContentResolver());
        }
    }

    public void updateEnabledAndSummary() {
        int i;
        int displayWidthSetting = DisplayResolutionManager.getDisplayWidthSetting(this.mContext);
        boolean z = ((displayWidthSetting == 1080 && FeaturesHolder.MEMC_FHD_SUPPORTED) || (displayWidthSetting == 1440 && FeaturesHolder.MEMC_QHD_SUPPORTED)) && !this.mPowerSaveOn;
        this.mPreference.setEnabled(z);
        SwitchPreference switchPreference = this.mPreference;
        if (z) {
            i = R.string.video_motion_enhancement_summary;
        } else {
            i = this.mPowerSaveOn ? R.string.video_enhancement_battery_saver_on_summary : R.string.video_motion_enhancement_summary_unavailable;
        }
        switchPreference.setSummary(i);
    }

    /* loaded from: classes.dex */
    private class SettingObserver extends ContentObserver {
        private final Uri mDisplayWidthUri;
        private final Uri mMemcEnabledUri;
        private final Preference mPreference;

        SettingObserver(Preference preference) {
            super(new Handler());
            this.mDisplayWidthUri = Settings.System.getUriFor("display_resolution_width");
            this.mMemcEnabledUri = Settings.System.getUriFor("iris_memc_enabled");
            this.mPreference = preference;
        }

        public void register(ContentResolver contentResolver) {
            contentResolver.registerContentObserver(this.mDisplayWidthUri, false, this);
            contentResolver.registerContentObserver(this.mMemcEnabledUri, false, this);
        }

        public void unregister(ContentResolver contentResolver) {
            contentResolver.unregisterContentObserver(this);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z, Uri uri) {
            super.onChange(z, uri);
            if (uri == null) {
                MemcPreferenceController.this.updateEnabledAndSummary();
                MemcPreferenceController.this.updateState(this.mPreference);
            } else if (uri == this.mDisplayWidthUri) {
                MemcPreferenceController.this.updateEnabledAndSummary();
            } else if (uri == this.mMemcEnabledUri) {
                MemcPreferenceController.this.updateState(this.mPreference);
            }
        }
    }
}
