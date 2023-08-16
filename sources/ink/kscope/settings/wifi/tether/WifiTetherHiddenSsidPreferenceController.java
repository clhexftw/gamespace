package ink.kscope.settings.wifi.tether;

import android.content.Context;
import android.net.wifi.SoftApConfiguration;
import android.net.wifi.WifiManager;
import android.util.FeatureFlagUtils;
import androidx.preference.Preference;
import androidx.preference.SwitchPreference;
import com.android.settings.wifi.tether.WifiTetherBasePreferenceController;
/* loaded from: classes2.dex */
public class WifiTetherHiddenSsidPreferenceController extends WifiTetherBasePreferenceController {
    private boolean mHiddenSsid;

    public WifiTetherHiddenSsidPreferenceController(Context context, WifiTetherBasePreferenceController.OnTetherConfigUpdateListener onTetherConfigUpdateListener) {
        super(context, onTetherConfigUpdateListener);
        SoftApConfiguration softApConfiguration;
        WifiManager wifiManager = this.mWifiManager;
        if (wifiManager == null || (softApConfiguration = wifiManager.getSoftApConfiguration()) == null) {
            return;
        }
        this.mHiddenSsid = softApConfiguration.isHiddenSsid();
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return FeatureFlagUtils.isEnabled(this.mContext, "settings_tether_all_in_one") ? "wifi_tether_hidden_ssid_2" : "wifi_tether_hidden_ssid";
    }

    @Override // com.android.settings.wifi.tether.WifiTetherBasePreferenceController
    public void updateDisplay() {
        Preference preference = this.mPreference;
        if (preference == null) {
            return;
        }
        ((SwitchPreference) preference).setChecked(this.mHiddenSsid);
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        this.mHiddenSsid = ((Boolean) obj).booleanValue();
        WifiTetherBasePreferenceController.OnTetherConfigUpdateListener onTetherConfigUpdateListener = this.mListener;
        if (onTetherConfigUpdateListener != null) {
            onTetherConfigUpdateListener.onTetherConfigUpdated(this);
            return true;
        }
        return true;
    }

    public boolean isHiddenSsidEnabled() {
        return this.mHiddenSsid;
    }
}
