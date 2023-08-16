package com.android.settings.display;

import android.os.Bundle;
import android.provider.Settings;
import android.widget.Switch;
import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.preference.SystemSettingMainSwitchPreference;
import com.android.settingslib.widget.OnMainSwitchChangeListener;
import org.nameless.custom.preference.SystemSettingListPreference;
import org.nameless.custom.preference.SystemSettingSeekBarPreference;
/* loaded from: classes.dex */
public class NetworkTraffic extends SettingsPreferenceFragment implements OnMainSwitchChangeListener {
    private SystemSettingListPreference mIndicatorMode;
    private SystemSettingSeekBarPreference mInterval;
    private SystemSettingMainSwitchPreference mSwitchBar;
    private SystemSettingSeekBarPreference mThreshold;

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1999;
    }

    @Override // com.android.settings.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(R.xml.network_traffic);
        boolean z = Settings.System.getInt(getContentResolver(), "network_traffic_state", 0) == 1;
        SystemSettingMainSwitchPreference systemSettingMainSwitchPreference = (SystemSettingMainSwitchPreference) findPreference("network_traffic_state");
        this.mSwitchBar = systemSettingMainSwitchPreference;
        systemSettingMainSwitchPreference.addOnSwitchChangeListener(this);
        SystemSettingListPreference systemSettingListPreference = (SystemSettingListPreference) findPreference("network_traffic_mode");
        this.mIndicatorMode = systemSettingListPreference;
        systemSettingListPreference.setEnabled(z);
        SystemSettingSeekBarPreference systemSettingSeekBarPreference = (SystemSettingSeekBarPreference) findPreference("network_traffic_autohide_threshold");
        this.mThreshold = systemSettingSeekBarPreference;
        systemSettingSeekBarPreference.setEnabled(z);
        SystemSettingSeekBarPreference systemSettingSeekBarPreference2 = (SystemSettingSeekBarPreference) findPreference("network_traffic_refresh_interval");
        this.mInterval = systemSettingSeekBarPreference2;
        systemSettingSeekBarPreference2.setEnabled(z);
    }

    @Override // com.android.settingslib.widget.OnMainSwitchChangeListener
    public void onSwitchChanged(Switch r1, boolean z) {
        this.mIndicatorMode.setEnabled(z);
        this.mThreshold.setEnabled(z);
        this.mInterval.setEnabled(z);
    }
}
