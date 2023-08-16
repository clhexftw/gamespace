package ink.kscope.settings.wifi.tether;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.MacAddress;
import android.net.TetheredClient;
import android.net.TetheringManager;
import android.net.wifi.SoftApCapability;
import android.net.wifi.SoftApConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settingslib.widget.FooterPreference;
import ink.kscope.settings.wifi.tether.preference.WifiTetherClientLimitPreference;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes2.dex */
public class WifiTetherClientManager extends SettingsPreferenceFragment implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener, WifiManager.SoftApCallback, TetheringManager.TetheringEventCallback {
    private PreferenceCategory mBlockedClientsPref;
    private WifiTetherClientLimitPreference mClientLimitPref;
    private PreferenceCategory mConnectedClientsPref;
    private FooterPreference mFooterPref;
    private boolean mSupportForceDisconnect;
    private TetheringManager mTetheringManager;
    private WifiManager mWifiManager;

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1014;
    }

    @Override // com.android.settings.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mWifiManager = (WifiManager) getSystemService(WifiManager.class);
        this.mTetheringManager = (TetheringManager) getSystemService(TetheringManager.class);
        this.mWifiManager.registerSoftApCallback(getActivity().getMainExecutor(), this);
        addPreferencesFromResource(R.xml.hotspot_client_manager);
        getActivity().setTitle(R.string.wifi_hotspot_client_manager_title);
        this.mClientLimitPref = (WifiTetherClientLimitPreference) findPreference("client_limit");
        this.mConnectedClientsPref = (PreferenceCategory) findPreference("connected_client_list");
        this.mBlockedClientsPref = (PreferenceCategory) findPreference("blocked_client_list");
        this.mFooterPref = (FooterPreference) findPreference("footer");
        this.mClientLimitPref.setOnPreferenceChangeListener(this);
        updateBlockedClients();
        updatePreferenceVisible();
    }

    public void onCapabilityChanged(SoftApCapability softApCapability) {
        this.mSupportForceDisconnect = softApCapability.areFeaturesSupported(2L);
        this.mWifiManager.unregisterSoftApCallback(this);
        if (this.mSupportForceDisconnect) {
            this.mClientLimitPref.setMin(1);
            this.mClientLimitPref.setMax(softApCapability.getMaxSupportedClients());
            this.mClientLimitPref.setValue(this.mWifiManager.getSoftApConfiguration().getMaxNumberOfClients(), false);
        }
        updatePreferenceVisible();
    }

    private void updatePreferenceVisible() {
        PreferenceCategory preferenceCategory;
        if (this.mBlockedClientsPref == null || this.mClientLimitPref == null || (preferenceCategory = this.mConnectedClientsPref) == null || this.mFooterPref == null) {
            return;
        }
        boolean z = true;
        boolean z2 = preferenceCategory.getPreferenceCount() > 0;
        boolean z3 = this.mBlockedClientsPref.getPreferenceCount() > 0;
        this.mClientLimitPref.setVisible(this.mSupportForceDisconnect);
        this.mBlockedClientsPref.setVisible(this.mSupportForceDisconnect && z3);
        this.mConnectedClientsPref.setVisible(z2);
        FooterPreference footerPreference = this.mFooterPref;
        if (z3 || z2) {
            z = false;
        }
        footerPreference.setVisible(z);
    }

    private void updateBlockedClients() {
        List<MacAddress> blockedClientList = this.mWifiManager.getSoftApConfiguration().getBlockedClientList();
        this.mBlockedClientsPref.removeAll();
        for (MacAddress macAddress : blockedClientList) {
            BlockedClientPreference blockedClientPreference = new BlockedClientPreference(getActivity(), macAddress);
            blockedClientPreference.setOnPreferenceClickListener(this);
            this.mBlockedClientsPref.addPreference(blockedClientPreference);
        }
        updatePreferenceVisible();
    }

    public void onClientsChanged(Collection<TetheredClient> collection) {
        this.mConnectedClientsPref.removeAll();
        for (TetheredClient tetheredClient : collection) {
            if (tetheredClient.getTetheringType() == 0) {
                ConnectedClientPreference connectedClientPreference = new ConnectedClientPreference(getActivity(), tetheredClient);
                connectedClientPreference.setOnPreferenceClickListener(this);
                this.mConnectedClientsPref.addPreference(connectedClientPreference);
            }
        }
        updatePreferenceVisible();
    }

    @Override // androidx.preference.Preference.OnPreferenceClickListener
    public boolean onPreferenceClick(Preference preference) {
        if (this.mSupportForceDisconnect) {
            if (preference instanceof ConnectedClientPreference) {
                showBlockClientDialog(((ConnectedClientPreference) preference).getMacAddress(), preference.getTitle());
                return true;
            } else if (preference instanceof BlockedClientPreference) {
                showUnblockClientDialog(((BlockedClientPreference) preference).getMacAddress());
                return true;
            }
        }
        return super.onPreferenceTreeClick(preference);
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (preference == this.mClientLimitPref) {
            return this.mWifiManager.setSoftApConfiguration(new SoftApConfiguration.Builder(this.mWifiManager.getSoftApConfiguration()).setMaxNumberOfClients(((Integer) obj).intValue()).build());
        }
        return false;
    }

    private void blockClient(MacAddress macAddress, boolean z) {
        SoftApConfiguration softApConfiguration = this.mWifiManager.getSoftApConfiguration();
        List blockedClientList = softApConfiguration.getBlockedClientList();
        if (z) {
            if (blockedClientList.contains(macAddress)) {
                return;
            }
            blockedClientList.add(macAddress);
        } else if (!blockedClientList.contains(macAddress)) {
            return;
        } else {
            blockedClientList.remove(macAddress);
        }
        this.mWifiManager.setSoftApConfiguration(new SoftApConfiguration.Builder(softApConfiguration).setBlockedClientList(blockedClientList).build());
        updateBlockedClients();
    }

    private void showBlockClientDialog(final MacAddress macAddress, CharSequence charSequence) {
        FragmentActivity activity = getActivity();
        new AlertDialog.Builder(activity).setTitle(R.string.wifi_hotspot_block_client_dialog_title).setMessage(activity.getString(R.string.wifi_hotspot_block_client_dialog_text, new Object[]{charSequence})).setPositiveButton(17039370, new DialogInterface.OnClickListener() { // from class: ink.kscope.settings.wifi.tether.WifiTetherClientManager$$ExternalSyntheticLambda1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                WifiTetherClientManager.this.lambda$showBlockClientDialog$0(macAddress, dialogInterface, i);
            }
        }).setNegativeButton(17039360, (DialogInterface.OnClickListener) null).create().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showBlockClientDialog$0(MacAddress macAddress, DialogInterface dialogInterface, int i) {
        blockClient(macAddress, true);
    }

    private void showUnblockClientDialog(final MacAddress macAddress) {
        FragmentActivity activity = getActivity();
        new AlertDialog.Builder(activity).setTitle(R.string.wifi_hotspot_unblock_client_dialog_title).setMessage(activity.getString(R.string.wifi_hotspot_unblock_client_dialog_text, new Object[]{macAddress.toString()})).setPositiveButton(17039370, new DialogInterface.OnClickListener() { // from class: ink.kscope.settings.wifi.tether.WifiTetherClientManager$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                WifiTetherClientManager.this.lambda$showUnblockClientDialog$1(macAddress, dialogInterface, i);
            }
        }).setNegativeButton(17039360, (DialogInterface.OnClickListener) null).create().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showUnblockClientDialog$1(MacAddress macAddress, DialogInterface dialogInterface, int i) {
        blockClient(macAddress, false);
    }

    @Override // com.android.settings.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        this.mTetheringManager.registerTetheringEventCallback(getActivity().getMainExecutor(), this);
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStop() {
        this.mTetheringManager.unregisterTetheringEventCallback(this);
        super.onStop();
    }

    /* loaded from: classes2.dex */
    private class ConnectedClientPreference extends Preference {
        private MacAddress mMacAddress;

        public ConnectedClientPreference(Context context, TetheredClient tetheredClient) {
            super(context);
            CharSequence charSequence;
            this.mMacAddress = tetheredClient.getMacAddress();
            String macAddress = tetheredClient.getMacAddress().toString();
            Iterator it = tetheredClient.getAddresses().iterator();
            while (true) {
                if (!it.hasNext()) {
                    charSequence = null;
                    break;
                }
                TetheredClient.AddressInfo addressInfo = (TetheredClient.AddressInfo) it.next();
                if (!TextUtils.isEmpty(addressInfo.getHostname())) {
                    charSequence = addressInfo.getHostname();
                    break;
                }
            }
            setKey(macAddress);
            if (!TextUtils.isEmpty(charSequence)) {
                setTitle(charSequence);
                setSummary(macAddress);
                return;
            }
            setTitle(macAddress);
        }

        public MacAddress getMacAddress() {
            return this.mMacAddress;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class BlockedClientPreference extends Preference {
        private MacAddress mMacAddress;

        public BlockedClientPreference(Context context, MacAddress macAddress) {
            super(context);
            this.mMacAddress = macAddress;
            setKey(macAddress.toString());
            setTitle(macAddress.toString());
        }

        public MacAddress getMacAddress() {
            return this.mMacAddress;
        }
    }
}
