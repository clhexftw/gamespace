package com.android.settings.display;

import android.os.Bundle;
import android.provider.Settings;
import androidx.preference.Preference;
import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;
import org.nameless.custom.preference.SystemSettingListPreference;
import org.nameless.custom.preference.SystemSettingSwitchPreference;
/* loaded from: classes.dex */
public class QsBrightnessSliderSettingsFragment extends SettingsPreferenceFragment implements Preference.OnPreferenceChangeListener {
    private SystemSettingSwitchPreference mAutoBrightnessIcon;
    private SystemSettingSwitchPreference mShowInBottom;
    private SystemSettingListPreference mWhereToShow;

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1999;
    }

    @Override // com.android.settings.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(R.xml.qs_brightness_slider);
        boolean z = Settings.System.getIntForUser(getContentResolver(), "qs_show_brightness", 1, -2) != 0;
        SystemSettingListPreference systemSettingListPreference = (SystemSettingListPreference) findPreference("qs_show_brightness");
        this.mWhereToShow = systemSettingListPreference;
        systemSettingListPreference.setOnPreferenceChangeListener(this);
        SystemSettingSwitchPreference systemSettingSwitchPreference = (SystemSettingSwitchPreference) findPreference("qs_show_auto_brightness_button");
        this.mAutoBrightnessIcon = systemSettingSwitchPreference;
        systemSettingSwitchPreference.setEnabled(z);
        SystemSettingSwitchPreference systemSettingSwitchPreference2 = (SystemSettingSwitchPreference) findPreference("qs_brightness_position_bottom");
        this.mShowInBottom = systemSettingSwitchPreference2;
        systemSettingSwitchPreference2.setEnabled(z);
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (preference == this.mWhereToShow) {
            boolean z = Integer.parseInt(obj.toString()) != 0;
            this.mAutoBrightnessIcon.setEnabled(z);
            this.mShowInBottom.setEnabled(z);
            return true;
        }
        return false;
    }
}
