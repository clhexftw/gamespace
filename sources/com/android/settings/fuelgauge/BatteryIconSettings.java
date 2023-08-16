package com.android.settings.fuelgauge;

import android.os.Bundle;
import android.provider.Settings;
import androidx.preference.Preference;
import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;
import org.nameless.custom.preference.SystemSettingListPreference;
/* loaded from: classes.dex */
public class BatteryIconSettings extends SettingsPreferenceFragment implements Preference.OnPreferenceChangeListener {
    private SystemSettingListPreference mPercent;
    private SystemSettingListPreference mStyle;

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1999;
    }

    @Override // com.android.settings.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(R.xml.battery_icon);
        boolean z = false;
        int intForUser = Settings.System.getIntForUser(getContentResolver(), "status_bar_battery_style", 0, -2);
        boolean z2 = intForUser == 2;
        boolean z3 = intForUser == 3;
        SystemSettingListPreference systemSettingListPreference = (SystemSettingListPreference) findPreference("status_bar_battery_style");
        this.mStyle = systemSettingListPreference;
        systemSettingListPreference.setOnPreferenceChangeListener(this);
        SystemSettingListPreference systemSettingListPreference2 = (SystemSettingListPreference) findPreference("status_bar_show_battery_percent");
        this.mPercent = systemSettingListPreference2;
        if (!z2 && !z3) {
            z = true;
        }
        systemSettingListPreference2.setEnabled(z);
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (preference == this.mStyle) {
            int intValue = Integer.valueOf((String) obj).intValue();
            boolean z = false;
            boolean z2 = intValue == 2;
            boolean z3 = intValue == 3;
            SystemSettingListPreference systemSettingListPreference = this.mPercent;
            if (!z2 && !z3) {
                z = true;
            }
            systemSettingListPreference.setEnabled(z);
        }
        return true;
    }
}
