package com.android.settingslib.deviceinfo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settingslib.R$string;
import com.android.settingslib.core.lifecycle.Lifecycle;
/* loaded from: classes2.dex */
public abstract class AbstractWifiMacAddressPreferenceController extends AbstractConnectivityPreferenceController {
    private static final String[] CONNECTIVITY_INTENTS = {"android.net.conn.CONNECTIVITY_CHANGE", "android.net.wifi.LINK_CONFIGURATION_CHANGED", "android.net.wifi.STATE_CHANGE"};
    static final String KEY_WIFI_MAC_ADDRESS = "wifi_mac_address";
    static final int OFF = 0;
    static final int ON = 1;
    private Preference mWifiMacAddress;
    private final WifiManager mWifiManager;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return KEY_WIFI_MAC_ADDRESS;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return true;
    }

    public AbstractWifiMacAddressPreferenceController(Context context, Lifecycle lifecycle) {
        super(context, lifecycle);
        this.mWifiManager = (WifiManager) context.getSystemService(WifiManager.class);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        if (isAvailable()) {
            this.mWifiMacAddress = preferenceScreen.findPreference(KEY_WIFI_MAC_ADDRESS);
            updateConnectivity();
        }
    }

    @Override // com.android.settingslib.deviceinfo.AbstractConnectivityPreferenceController
    protected String[] getConnectivityIntents() {
        return CONNECTIVITY_INTENTS;
    }

    @Override // com.android.settingslib.deviceinfo.AbstractConnectivityPreferenceController
    @SuppressLint({"HardwareIds"})
    protected void updateConnectivity() {
        String[] factoryMacAddresses = this.mWifiManager.getFactoryMacAddresses();
        String str = (factoryMacAddresses == null || factoryMacAddresses.length <= 0) ? null : factoryMacAddresses[0];
        if (this.mWifiMacAddress == null) {
            return;
        }
        if (TextUtils.isEmpty(str) || str.equals("02:00:00:00:00:00")) {
            this.mWifiMacAddress.setSummary(R$string.status_unavailable);
        } else {
            this.mWifiMacAddress.setSummary(str);
        }
    }
}
