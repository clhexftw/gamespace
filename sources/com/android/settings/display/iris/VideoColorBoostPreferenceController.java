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
/* loaded from: classes.dex */
public class VideoColorBoostPreferenceController extends TogglePreferenceController implements LifecycleObserver, OnStart, OnStop {
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

    public VideoColorBoostPreferenceController(Context context, String str) {
        super(context, str);
        this.mPowerSaveReceiver = new BroadcastReceiver() { // from class: com.android.settings.display.iris.VideoColorBoostPreferenceController.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                if (intent.getAction() == "android.os.action.POWER_SAVE_MODE_CHANGED") {
                    VideoColorBoostPreferenceController videoColorBoostPreferenceController = VideoColorBoostPreferenceController.this;
                    videoColorBoostPreferenceController.mPowerSaveOn = videoColorBoostPreferenceController.mPowerManager.isPowerSaveMode();
                    VideoColorBoostPreferenceController.this.updateEnabledAndSummary();
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
        return FeaturesHolder.SDR2HDR_SUPPORTED ? 0 : 3;
    }

    @Override // com.android.settings.core.TogglePreferenceController
    public boolean isChecked() {
        return Settings.System.getIntForUser(this.mContext.getContentResolver(), "iris_video_color_boost", 0, -2) == 1;
    }

    @Override // com.android.settings.core.TogglePreferenceController
    public boolean setChecked(boolean z) {
        return Settings.System.putIntForUser(this.mContext.getContentResolver(), "iris_video_color_boost", z ? 1 : 0, -2);
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
        this.mPreference.setEnabled(!this.mPowerSaveOn);
        SwitchPreference switchPreference = this.mPreference;
        if (!this.mPowerSaveOn) {
            i = R.string.video_color_boost_summary;
        } else {
            i = R.string.video_enhancement_battery_saver_on_summary;
        }
        switchPreference.setSummary(i);
    }

    /* loaded from: classes.dex */
    private class SettingObserver extends ContentObserver {
        private final Preference mPreference;
        private final Uri mUri;

        SettingObserver(Preference preference) {
            super(new Handler());
            this.mUri = Settings.System.getUriFor("iris_video_color_boost");
            this.mPreference = preference;
        }

        public void register(ContentResolver contentResolver) {
            contentResolver.registerContentObserver(this.mUri, false, this);
        }

        public void unregister(ContentResolver contentResolver) {
            contentResolver.unregisterContentObserver(this);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z, Uri uri) {
            super.onChange(z, uri);
            if (uri == null || uri == this.mUri) {
                VideoColorBoostPreferenceController.this.updateEnabledAndSummary();
                VideoColorBoostPreferenceController.this.updateState(this.mPreference);
            }
        }
    }
}
