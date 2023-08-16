package com.android.settings.fuelgauge;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.ContentResolver;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.text.format.DateFormat;
import android.widget.TimePicker;
import androidx.preference.Preference;
import com.android.settings.R;
import com.android.settings.dashboard.DashboardFragment;
import org.nameless.battery.WirelessChargingSettingsHelper;
/* loaded from: classes.dex */
public class WirelessChargingQuietModeFragment extends DashboardFragment {
    private final SettingsObserver mObserver = new SettingsObserver();

    @Override // com.android.settings.SettingsPreferenceFragment, com.android.settings.DialogCreatable
    public int getDialogMetricsCategory(int i) {
        return 1999;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settings.dashboard.DashboardFragment
    public String getLogTag() {
        return "WirelessChargingQuietModeFragment";
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1999;
    }

    /* loaded from: classes.dex */
    private final class SettingsObserver extends ContentObserver {
        private final Uri mEnabledUri;
        private final Uri mStatusUri;
        private final Uri mTimeUri;

        SettingsObserver() {
            super(new Handler());
            this.mEnabledUri = Settings.System.getUriFor("wireless_charging_quiet_mode_enabled");
            this.mStatusUri = Settings.System.getUriFor("wireless_charging_quiet_mode_status");
            this.mTimeUri = Settings.System.getUriFor("wireless_charging_quiet_mode_time");
        }

        void register(ContentResolver contentResolver) {
            contentResolver.registerContentObserver(this.mEnabledUri, false, this);
            contentResolver.registerContentObserver(this.mStatusUri, false, this);
            contentResolver.registerContentObserver(this.mTimeUri, false, this);
        }

        void unregister(ContentResolver contentResolver) {
            contentResolver.unregisterContentObserver(this);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z, Uri uri) {
            WirelessChargingQuietModeFragment.this.updatePreferenceStates();
        }
    }

    @Override // com.android.settings.dashboard.DashboardFragment, com.android.settings.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mObserver.register(getContext().getContentResolver());
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        this.mObserver.unregister(getContext().getContentResolver());
        super.onDestroy();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreateDialog$0(boolean z, TimePicker timePicker, int i, int i2) {
        updateTimeSetting(z, i, i2);
    }

    @Override // com.android.settings.SettingsPreferenceFragment, com.android.settings.DialogCreatable
    public Dialog onCreateDialog(int i) {
        int parseInt;
        int parseInt2;
        if (i == 0 || i == 1) {
            String[] split = WirelessChargingSettingsHelper.getQuietModeTime(getContext()).split(",");
            final boolean z = i == 0;
            TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() { // from class: com.android.settings.fuelgauge.WirelessChargingQuietModeFragment$$ExternalSyntheticLambda0
                @Override // android.app.TimePickerDialog.OnTimeSetListener
                public final void onTimeSet(TimePicker timePicker, int i2, int i3) {
                    WirelessChargingQuietModeFragment.this.lambda$onCreateDialog$0(z, timePicker, i2, i3);
                }
            };
            if (z) {
                String[] split2 = split[0].split(":", 0);
                parseInt = Integer.parseInt(split2[0]);
                parseInt2 = Integer.parseInt(split2[1]);
            } else {
                String[] split3 = split[1].split(":", 0);
                parseInt = Integer.parseInt(split3[0]);
                parseInt2 = Integer.parseInt(split3[1]);
            }
            return new TimePickerDialog(getContext(), onTimeSetListener, parseInt, parseInt2, DateFormat.is24HourFormat(getContext()));
        }
        return super.onCreateDialog(i);
    }

    @Override // com.android.settings.dashboard.DashboardFragment, com.android.settings.core.InstrumentedPreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.preference.PreferenceManager.OnPreferenceTreeClickListener
    public boolean onPreferenceTreeClick(Preference preference) {
        if ("wireless_charging_quiet_mode_start_time".equals(preference.getKey())) {
            writePreferenceClickMetric(preference);
            showDialog(0);
            return true;
        } else if ("wireless_charging_quiet_mode_end_time".equals(preference.getKey())) {
            writePreferenceClickMetric(preference);
            showDialog(1);
            return true;
        } else {
            return super.onPreferenceTreeClick(preference);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settings.dashboard.DashboardFragment, com.android.settings.core.InstrumentedPreferenceFragment
    public int getPreferenceScreenResId() {
        return R.xml.wireless_charging_quiet_mode;
    }

    private void updateTimeSetting(boolean z, int i, int i2) {
        String str;
        String[] split = WirelessChargingSettingsHelper.getQuietModeTime(getContext()).split(",");
        String str2 = "";
        if (i < 10) {
            str = "0";
        } else {
            str = "";
        }
        if (i2 < 10) {
            str2 = "0";
        }
        int i3 = !z ? 1 : 0;
        split[i3] = (str + String.valueOf(i)) + ":" + (str2 + String.valueOf(i2));
        WirelessChargingSettingsHelper.setQuietModeTime(getContext(), split[0] + "," + split[1]);
    }
}
