package com.android.settings.notification;

import android.content.res.Resources;
import android.os.Bundle;
import android.provider.Settings;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;
/* loaded from: classes.dex */
public class HeadsUpSettings extends SettingsPreferenceFragment implements Preference.OnPreferenceChangeListener {
    private ListPreference mHeadsUpSnoozeTime;
    private ListPreference mHeadsUpTimeOut;

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1999;
    }

    @Override // com.android.settings.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(R.xml.heads_up_settings);
        try {
            Resources resourcesForApplication = getPackageManager().getResourcesForApplication("com.android.systemui");
            int integer = resourcesForApplication.getInteger(resourcesForApplication.getIdentifier("com.android.systemui:integer/heads_up_notification_decay", null, null));
            ListPreference listPreference = (ListPreference) findPreference("heads_up_time_out");
            this.mHeadsUpTimeOut = listPreference;
            listPreference.setOnPreferenceChangeListener(this);
            int i = Settings.System.getInt(getContentResolver(), "heads_up_timeout", integer);
            this.mHeadsUpTimeOut.setValue(String.valueOf(i));
            updateHeadsUpTimeOutSummary(i);
            int integer2 = resourcesForApplication.getInteger(resourcesForApplication.getIdentifier("com.android.systemui:integer/heads_up_default_snooze_length_ms", null, null));
            ListPreference listPreference2 = (ListPreference) findPreference("heads_up_snooze_time");
            this.mHeadsUpSnoozeTime = listPreference2;
            listPreference2.setOnPreferenceChangeListener(this);
            int i2 = Settings.System.getInt(getContentResolver(), "heads_up_notification_snooze", integer2);
            this.mHeadsUpSnoozeTime.setValue(String.valueOf(i2));
            updateHeadsUpSnoozeTimeSummary(i2);
        } catch (Exception unused) {
        }
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (preference == this.mHeadsUpTimeOut) {
            int intValue = Integer.valueOf((String) obj).intValue();
            Settings.System.putInt(getContentResolver(), "heads_up_timeout", intValue);
            updateHeadsUpTimeOutSummary(intValue);
            return true;
        } else if (preference == this.mHeadsUpSnoozeTime) {
            int intValue2 = Integer.valueOf((String) obj).intValue();
            Settings.System.putInt(getContentResolver(), "heads_up_notification_snooze", intValue2);
            updateHeadsUpSnoozeTimeSummary(intValue2);
            return true;
        } else {
            return true;
        }
    }

    private void updateHeadsUpTimeOutSummary(int i) {
        this.mHeadsUpTimeOut.setSummary(getResources().getString(R.string.heads_up_time_out_summary, Integer.valueOf(i / 1000)));
    }

    private void updateHeadsUpSnoozeTimeSummary(int i) {
        if (i == 0) {
            this.mHeadsUpSnoozeTime.setSummary(getResources().getString(R.string.heads_up_snooze_disabled_summary));
        } else if (i == 60000) {
            this.mHeadsUpSnoozeTime.setSummary(getResources().getString(R.string.heads_up_snooze_summary_one_minute));
        } else {
            this.mHeadsUpSnoozeTime.setSummary(getResources().getString(R.string.heads_up_snooze_summary, Integer.valueOf((i / 60) / 1000)));
        }
    }
}
