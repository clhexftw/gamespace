package com.android.settings.datetime;

import android.app.time.TimeManager;
import android.app.time.TimeZoneCapabilitiesAndConfig;
import android.app.time.TimeZoneConfiguration;
import android.content.Context;
import androidx.preference.Preference;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settingslib.core.AbstractPreferenceController;
import org.nameless.custom.preference.SwitchPreference;
/* loaded from: classes.dex */
public class AutoTimeZonePreferenceController extends AbstractPreferenceController implements PreferenceControllerMixin, Preference.OnPreferenceChangeListener {
    private final UpdateTimeAndDateCallback mCallback;
    private final boolean mIsFromSUW;
    private final TimeManager mTimeManager;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "auto_zone";
    }

    public AutoTimeZonePreferenceController(Context context, UpdateTimeAndDateCallback updateTimeAndDateCallback, boolean z) {
        super(context);
        this.mTimeManager = (TimeManager) context.getSystemService(TimeManager.class);
        this.mCallback = updateTimeAndDateCallback;
        this.mIsFromSUW = z;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        int configureAutoDetectionEnabledCapability;
        if (this.mIsFromSUW || (configureAutoDetectionEnabledCapability = getTimeZoneCapabilitiesAndConfig().getCapabilities().getConfigureAutoDetectionEnabledCapability()) == 10 || configureAutoDetectionEnabledCapability == 20 || configureAutoDetectionEnabledCapability == 30) {
            return false;
        }
        if (configureAutoDetectionEnabledCapability == 40) {
            return true;
        }
        throw new IllegalStateException("Unknown capability=" + configureAutoDetectionEnabledCapability);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        if (preference instanceof SwitchPreference) {
            ((SwitchPreference) preference).setChecked(isEnabled());
        }
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        boolean updateTimeZoneConfiguration = this.mTimeManager.updateTimeZoneConfiguration(new TimeZoneConfiguration.Builder().setAutoDetectionEnabled(((Boolean) obj).booleanValue()).build());
        this.mCallback.updateTimeAndDateDisplay(this.mContext);
        return updateTimeZoneConfiguration;
    }

    boolean isEnabled() {
        return getTimeZoneCapabilitiesAndConfig().getConfiguration().isAutoDetectionEnabled();
    }

    private TimeZoneCapabilitiesAndConfig getTimeZoneCapabilitiesAndConfig() {
        return this.mTimeManager.getTimeZoneCapabilitiesAndConfig();
    }
}
