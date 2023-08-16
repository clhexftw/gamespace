package ink.kscope.settings.wifi.tether;

import android.content.Context;
import android.net.wifi.SoftApCapability;
import android.net.wifi.SoftApConfiguration;
import android.net.wifi.WifiManager;
import android.util.FeatureFlagUtils;
import androidx.preference.Preference;
import com.android.settings.R;
import com.android.settings.wifi.tether.WifiTetherBasePreferenceController;
/* loaded from: classes2.dex */
public class WifiTetherClientManagerPreferenceController extends WifiTetherBasePreferenceController implements WifiManager.SoftApCallback {
    private boolean mSupportForceDisconnect;

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        return true;
    }

    public WifiTetherClientManagerPreferenceController(Context context, WifiTetherBasePreferenceController.OnTetherConfigUpdateListener onTetherConfigUpdateListener) {
        super(context, onTetherConfigUpdateListener);
        this.mWifiManager.registerSoftApCallback(context.getMainExecutor(), this);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return FeatureFlagUtils.isEnabled(this.mContext, "settings_tether_all_in_one") ? "wifi_tether_client_manager_2" : "wifi_tether_client_manager";
    }

    public void onCapabilityChanged(SoftApCapability softApCapability) {
        this.mSupportForceDisconnect = softApCapability.areFeaturesSupported(2L);
        this.mWifiManager.unregisterSoftApCallback(this);
        updateDisplay();
    }

    @Override // com.android.settings.wifi.tether.WifiTetherBasePreferenceController
    public void updateDisplay() {
        Preference preference = this.mPreference;
        if (preference != null) {
            if (this.mSupportForceDisconnect) {
                preference.setSummary(R.string.wifi_hotspot_client_manager_summary);
            } else {
                preference.setSummary(R.string.wifi_hotspot_client_manager_list_only_summary);
            }
        }
    }

    public void updateConfig(SoftApConfiguration.Builder builder) {
        if (builder == null || !this.mSupportForceDisconnect) {
            return;
        }
        SoftApConfiguration softApConfiguration = this.mWifiManager.getSoftApConfiguration();
        int maxNumberOfClients = softApConfiguration.getMaxNumberOfClients();
        builder.setMaxNumberOfClients(maxNumberOfClients).setBlockedClientList(softApConfiguration.getBlockedClientList());
    }
}
