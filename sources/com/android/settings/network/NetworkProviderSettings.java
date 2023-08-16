package com.android.settings.network;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.net.NetworkTemplate;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.os.UserHandle;
import android.os.UserManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.EventLog;
import android.util.FeatureFlagUtils;
import android.util.Log;
import android.util.Pair;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceScreen;
import androidx.recyclerview.widget.RecyclerView;
import com.android.settings.AirplaneModeEnabler;
import com.android.settings.R;
import com.android.settings.RestrictedSettingsFragment;
import com.android.settings.core.SubSettingLauncher;
import com.android.settings.datausage.DataUsagePreference;
import com.android.settings.datausage.DataUsageUtils;
import com.android.settings.location.WifiScanningFragment;
import com.android.settings.network.InternetUpdater;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.wifi.AddNetworkFragment;
import com.android.settings.wifi.AddWifiNetworkPreference;
import com.android.settings.wifi.ConfigureWifiEntryFragment;
import com.android.settings.wifi.ConnectedWifiEntryPreference;
import com.android.settings.wifi.LongPressWifiEntryPreference;
import com.android.settings.wifi.WifiConnectListener;
import com.android.settings.wifi.WifiDialog2;
import com.android.settings.wifi.WifiEntryPreference;
import com.android.settings.wifi.WifiPickerTrackerHelper;
import com.android.settings.wifi.WifiUtils;
import com.android.settings.wifi.details.WifiNetworkDetailsFragment;
import com.android.settings.wifi.dpp.WifiDppUtils;
import com.android.settingslib.HelpUtils;
import com.android.settingslib.RestrictedLockUtils;
import com.android.settingslib.RestrictedLockUtilsInternal;
import com.android.settingslib.utils.ThreadUtils;
import com.android.settingslib.widget.FooterPreference;
import com.android.settingslib.widget.LayoutPreference;
import com.android.settingslib.wifi.WifiEnterpriseRestrictionUtils;
import com.android.settingslib.wifi.WifiSavedConfigUtils;
import com.android.wifitrackerlib.BaseWifiTracker;
import com.android.wifitrackerlib.WifiEntry;
import com.android.wifitrackerlib.WifiPickerTracker;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
/* loaded from: classes.dex */
public class NetworkProviderSettings extends RestrictedSettingsFragment implements WifiPickerTracker.WifiPickerTrackerCallback, WifiDialog2.WifiDialog2Listener, DialogInterface.OnDismissListener, AirplaneModeEnabler.OnAirplaneModeChangedListener, InternetUpdater.InternetChangeListener {
    static final int ADD_NETWORK_REQUEST = 2;
    static final int MENU_ID_DISCONNECT = 3;
    static final int MENU_ID_FORGET = 4;
    static final String PREF_KEY_CONNECTED_ACCESS_POINTS = "connected_access_point";
    static final String PREF_KEY_DATA_USAGE = "non_carrier_data_usage";
    static final String PREF_KEY_FIRST_ACCESS_POINTS = "first_access_points";
    static final String PREF_KEY_WIFI_TOGGLE = "main_toggle_wifi";
    public static final SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new SearchIndexProvider(R.xml.network_provider_settings);
    AddWifiNetworkPreference mAddWifiNetworkPreference;
    AirplaneModeEnabler mAirplaneModeEnabler;
    Preference mAirplaneModeMsgPreference;
    private boolean mClickedConnect;
    Preference mConfigureWifiSettingsPreference;
    private WifiManager.ActionListener mConnectListener;
    ConnectedEthernetNetworkController mConnectedEthernetNetworkController;
    PreferenceCategory mConnectedWifiEntryPreferenceCategory;
    DataUsagePreference mDataUsagePreference;
    private WifiDialog2 mDialog;
    private int mDialogMode;
    private WifiEntry mDialogWifiEntry;
    private String mDialogWifiEntryKey;
    private boolean mEnableNextOnConnection;
    PreferenceCategory mFirstWifiEntryPreferenceCategory;
    private WifiManager.ActionListener mForgetListener;
    final Runnable mHideProgressBarRunnable;
    protected InternetResetHelper mInternetResetHelper;
    InternetUpdater mInternetUpdater;
    boolean mIsAdmin;
    protected boolean mIsRestricted;
    private boolean mIsViewLoading;
    private boolean mIsWifiEntryListStale;
    private NetworkMobileProviderController mNetworkMobileProviderController;
    private String mOpenSsid;
    final Runnable mRemoveLoadingRunnable;
    LayoutPreference mResetInternetPreference;
    private WifiManager.ActionListener mSaveListener;
    Preference mSavedNetworksPreference;
    private WifiEntry mSelectedWifiEntry;
    final Runnable mUpdateWifiEntryPreferencesRunnable;
    PreferenceCategory mWifiEntryPreferenceCategory;
    protected WifiManager mWifiManager;
    WifiPickerTracker mWifiPickerTracker;
    private WifiPickerTrackerHelper mWifiPickerTrackerHelper;
    FooterPreference mWifiStatusMessagePreference;
    private WifiSwitchPreferenceController mWifiSwitchPreferenceController;

    @Override // com.android.settings.SettingsPreferenceFragment, com.android.settings.DialogCreatable
    public int getDialogMetricsCategory(int i) {
        return i != 1 ? 0 : 603;
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 103;
    }

    private static boolean isVerboseLoggingEnabled() {
        return BaseWifiTracker.isVerboseLoggingEnabled();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0() {
        if (this.mIsViewLoading) {
            setLoading(false, false);
            this.mIsViewLoading = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$1() {
        updateWifiEntryPreferences();
        getView().postDelayed(this.mRemoveLoadingRunnable, 10L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$2() {
        setProgressBarVisible(false);
    }

    public NetworkProviderSettings() {
        super("no_config_wifi");
        this.mRemoveLoadingRunnable = new Runnable() { // from class: com.android.settings.network.NetworkProviderSettings$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                NetworkProviderSettings.this.lambda$new$0();
            }
        };
        this.mIsWifiEntryListStale = true;
        this.mUpdateWifiEntryPreferencesRunnable = new Runnable() { // from class: com.android.settings.network.NetworkProviderSettings$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                NetworkProviderSettings.this.lambda$new$1();
            }
        };
        this.mHideProgressBarRunnable = new Runnable() { // from class: com.android.settings.network.NetworkProviderSettings$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                NetworkProviderSettings.this.lambda$new$2();
            }
        };
        this.mIsAdmin = true;
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (getActivity() == null) {
            return;
        }
        setPinnedHeaderView(R.layout.progress_header);
        setProgressBarVisible(false);
        if (hasWifiManager()) {
            setLoading(true, false);
            this.mIsViewLoading = true;
        }
    }

    private boolean hasWifiManager() {
        if (this.mWifiManager != null) {
            return true;
        }
        Context context = getContext();
        if (context == null) {
            return false;
        }
        WifiManager wifiManager = (WifiManager) context.getSystemService(WifiManager.class);
        this.mWifiManager = wifiManager;
        return wifiManager != null;
    }

    @Override // com.android.settings.RestrictedSettingsFragment, com.android.settings.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mAirplaneModeEnabler = new AirplaneModeEnabler(getContext(), this);
        setAnimationAllowed(false);
        addPreferences();
        this.mIsRestricted = isUiRestricted();
        this.mIsAdmin = isAdminUser();
    }

    private boolean isAdminUser() {
        UserManager userManager = (UserManager) getSystemService(UserManager.class);
        if (userManager == null) {
            return true;
        }
        return userManager.isAdminUser();
    }

    private void addPreferences() {
        addPreferencesFromResource(R.xml.network_provider_settings);
        this.mAirplaneModeMsgPreference = findPreference("airplane_mode_message");
        updateAirplaneModeMsgPreference(this.mAirplaneModeEnabler.isAirplaneModeOn());
        this.mConnectedWifiEntryPreferenceCategory = (PreferenceCategory) findPreference(PREF_KEY_CONNECTED_ACCESS_POINTS);
        this.mFirstWifiEntryPreferenceCategory = (PreferenceCategory) findPreference(PREF_KEY_FIRST_ACCESS_POINTS);
        this.mWifiEntryPreferenceCategory = (PreferenceCategory) findPreference("access_points");
        this.mConfigureWifiSettingsPreference = findPreference("configure_network_settings");
        this.mSavedNetworksPreference = findPreference("saved_networks");
        this.mAddWifiNetworkPreference = new AddWifiNetworkPreference(getPrefContext());
        DataUsagePreference dataUsagePreference = (DataUsagePreference) findPreference(PREF_KEY_DATA_USAGE);
        this.mDataUsagePreference = dataUsagePreference;
        dataUsagePreference.setVisible(DataUsageUtils.hasWifiRadio(getContext()));
        this.mDataUsagePreference.setTemplate(new NetworkTemplate.Builder(4).build(), 0, null);
        LayoutPreference layoutPreference = (LayoutPreference) findPreference("resetting_your_internet");
        this.mResetInternetPreference = layoutPreference;
        if (layoutPreference != null) {
            layoutPreference.setVisible(false);
        }
        addNetworkMobileProviderController();
        addConnectedEthernetNetworkController();
        addWifiSwitchPreferenceController();
        this.mWifiStatusMessagePreference = (FooterPreference) findPreference("wifi_status_message_footer");
        checkConnectivityRecovering();
    }

    private void updateAirplaneModeMsgPreference(boolean z) {
        Preference preference = this.mAirplaneModeMsgPreference;
        if (preference != null) {
            preference.setVisible(z);
        }
    }

    boolean showAnySubscriptionInfo(Context context) {
        return context != null && SubscriptionUtil.isSimHardwareVisible(context);
    }

    private void addNetworkMobileProviderController() {
        if (showAnySubscriptionInfo(getContext())) {
            if (this.mNetworkMobileProviderController == null) {
                this.mNetworkMobileProviderController = new NetworkMobileProviderController(getContext(), NetworkMobileProviderController.PREF_KEY_PROVIDER_MOBILE_NETWORK);
            }
            this.mNetworkMobileProviderController.init(getSettingsLifecycle());
            this.mNetworkMobileProviderController.displayPreference(getPreferenceScreen());
        }
    }

    private void addConnectedEthernetNetworkController() {
        if (this.mConnectedEthernetNetworkController == null) {
            this.mConnectedEthernetNetworkController = new ConnectedEthernetNetworkController(getContext(), getSettingsLifecycle());
        }
        this.mConnectedEthernetNetworkController.displayPreference(getPreferenceScreen());
    }

    private void addWifiSwitchPreferenceController() {
        if (hasWifiManager()) {
            if (this.mWifiSwitchPreferenceController == null) {
                this.mWifiSwitchPreferenceController = new WifiSwitchPreferenceController(getContext(), getSettingsLifecycle());
            }
            this.mWifiSwitchPreferenceController.displayPreference(getPreferenceScreen());
        }
    }

    private void checkConnectivityRecovering() {
        InternetResetHelper internetResetHelper = new InternetResetHelper(getContext(), getLifecycle(), this.mNetworkMobileProviderController, findPreference(PREF_KEY_WIFI_TOGGLE), this.mConnectedWifiEntryPreferenceCategory, this.mFirstWifiEntryPreferenceCategory, this.mWifiEntryPreferenceCategory, this.mResetInternetPreference);
        this.mInternetResetHelper = internetResetHelper;
        internetResetHelper.checkRecovering();
    }

    @Override // com.android.settings.RestrictedSettingsFragment, com.android.settings.SettingsPreferenceFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (hasWifiManager()) {
            WifiPickerTrackerHelper wifiPickerTrackerHelper = new WifiPickerTrackerHelper(getSettingsLifecycle(), getContext(), this);
            this.mWifiPickerTrackerHelper = wifiPickerTrackerHelper;
            this.mWifiPickerTracker = wifiPickerTrackerHelper.getWifiPickerTracker();
        }
        this.mInternetUpdater = new InternetUpdater(getContext(), getSettingsLifecycle(), this);
        this.mConnectListener = new WifiConnectListener(getActivity());
        this.mSaveListener = new WifiManager.ActionListener() { // from class: com.android.settings.network.NetworkProviderSettings.1
            public void onSuccess() {
            }

            public void onFailure(int i) {
                FragmentActivity activity = NetworkProviderSettings.this.getActivity();
                if (activity != null) {
                    Toast.makeText(activity, R.string.wifi_failed_save_message, 0).show();
                }
            }
        };
        this.mForgetListener = new WifiManager.ActionListener() { // from class: com.android.settings.network.NetworkProviderSettings.2
            public void onSuccess() {
            }

            public void onFailure(int i) {
                FragmentActivity activity = NetworkProviderSettings.this.getActivity();
                if (activity != null) {
                    Toast.makeText(activity, R.string.wifi_failed_forget_message, 0).show();
                }
            }
        };
        setHasOptionsMenu(true);
        if (bundle != null) {
            this.mDialogMode = bundle.getInt("dialog_mode");
            this.mDialogWifiEntryKey = bundle.getString("wifi_ap_key");
        }
        Intent intent = getActivity().getIntent();
        this.mEnableNextOnConnection = intent.getBooleanExtra("wifi_enable_next_on_connect", false);
        if (intent.hasExtra("wifi_start_connect_ssid")) {
            this.mOpenSsid = intent.getStringExtra("wifi_start_connect_ssid");
        }
        NetworkMobileProviderController networkMobileProviderController = this.mNetworkMobileProviderController;
        if (networkMobileProviderController != null) {
            networkMobileProviderController.setWifiPickerTrackerHelper(this.mWifiPickerTrackerHelper);
        }
    }

    @Override // com.android.settings.SettingsPreferenceFragment, com.android.settings.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override // com.android.settings.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        if (this.mIsViewLoading) {
            getView().postDelayed(this.mRemoveLoadingRunnable, (hasWifiManager() && this.mWifiManager.isWifiEnabled()) ? 1000L : 100L);
        }
        if (this.mIsRestricted) {
            restrictUi();
        } else {
            this.mAirplaneModeEnabler.start();
        }
    }

    private void restrictUi() {
        if (!isUiRestrictedByOnlyAdmin()) {
            getEmptyTextView().setText(R.string.wifi_empty_list_user_restricted);
        }
        getPreferenceScreen().removeAll();
    }

    @Override // com.android.settings.RestrictedSettingsFragment, com.android.settings.SettingsPreferenceFragment, com.android.settings.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        RecyclerView listView = getListView();
        if (listView != null) {
            listView.setItemAnimator(null);
        }
        boolean z = this.mIsRestricted;
        boolean isUiRestricted = isUiRestricted();
        this.mIsRestricted = isUiRestricted;
        if (!z && isUiRestricted) {
            restrictUi();
        }
        WifiPickerTracker wifiPickerTracker = this.mWifiPickerTracker;
        changeNextButtonState((wifiPickerTracker == null || wifiPickerTracker.getConnectedWifiEntry() == null) ? false : true);
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStop() {
        this.mIsWifiEntryListStale = true;
        getView().removeCallbacks(this.mRemoveLoadingRunnable);
        getView().removeCallbacks(this.mUpdateWifiEntryPreferencesRunnable);
        getView().removeCallbacks(this.mHideProgressBarRunnable);
        this.mAirplaneModeEnabler.stop();
        super.onStop();
    }

    @Override // com.android.settings.RestrictedSettingsFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        AirplaneModeEnabler airplaneModeEnabler = this.mAirplaneModeEnabler;
        if (airplaneModeEnabler != null) {
            airplaneModeEnabler.close();
        }
        super.onDestroy();
    }

    @Override // com.android.settings.RestrictedSettingsFragment, androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        WifiConfiguration wifiConfiguration;
        WifiDialog2 wifiDialog2;
        super.onActivityResult(i, i2, intent);
        if (hasWifiManager()) {
            if (i == 2) {
                handleAddNetworkRequest(i2, intent);
                return;
            } else if (i == 0) {
                if (i2 != -1 || (wifiDialog2 = this.mDialog) == null) {
                    return;
                }
                wifiDialog2.dismiss();
                return;
            } else if (i == 3) {
                if (i2 != -1 || (wifiConfiguration = (WifiConfiguration) intent.getParcelableExtra("network_config_key")) == null) {
                    return;
                }
                this.mWifiManager.connect(wifiConfiguration, new WifiConnectActionListener());
                return;
            } else if (i == 4) {
                return;
            }
        }
        boolean z = this.mIsRestricted;
        boolean isUiRestricted = isUiRestricted();
        this.mIsRestricted = isUiRestricted;
        if (z && !isUiRestricted && getPreferenceScreen().getPreferenceCount() == 0) {
            addPreferences();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settings.SettingsPreferenceFragment, androidx.preference.PreferenceFragmentCompat
    public RecyclerView.Adapter onCreateAdapter(PreferenceScreen preferenceScreen) {
        RecyclerView.Adapter onCreateAdapter = super.onCreateAdapter(preferenceScreen);
        onCreateAdapter.setHasStableIds(true);
        return onCreateAdapter;
    }

    @Override // com.android.settings.RestrictedSettingsFragment, com.android.settings.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.mDialog != null) {
            bundle.putInt("dialog_mode", this.mDialogMode);
            bundle.putString("wifi_ap_key", this.mDialogWifiEntryKey);
        }
    }

    @Override // androidx.fragment.app.Fragment, android.view.View.OnCreateContextMenuListener
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        Preference preference = (Preference) view.getTag();
        if (preference instanceof LongPressWifiEntryPreference) {
            WifiEntry wifiEntry = ((LongPressWifiEntryPreference) preference).getWifiEntry();
            this.mSelectedWifiEntry = wifiEntry;
            contextMenu.setHeaderTitle(wifiEntry.getTitle());
            if (this.mSelectedWifiEntry.canConnect()) {
                contextMenu.add(0, 2, 0, R.string.wifi_connect);
            }
            if (this.mSelectedWifiEntry.canDisconnect()) {
                if (this.mSelectedWifiEntry.canShare()) {
                    addShareMenuIfSuitable(contextMenu);
                }
                contextMenu.add(0, 3, 1, R.string.wifi_disconnect_button_text);
            }
            if (canForgetNetwork()) {
                addForgetMenuIfSuitable(contextMenu);
            }
            if (WifiUtils.isNetworkLockedDown(getActivity(), this.mSelectedWifiEntry.getWifiConfiguration())) {
                return;
            }
            addModifyMenuIfSuitable(contextMenu, this.mSelectedWifiEntry);
        }
    }

    void addShareMenuIfSuitable(ContextMenu contextMenu) {
        if (this.mIsAdmin) {
            contextMenu.add(0, 7, 0, R.string.share);
            return;
        }
        Log.w("NetworkProviderSettings", "Don't add the Wi-Fi share menu because the user is not an admin.");
        EventLog.writeEvent(1397638484, "206986392", -1, "User is not an admin");
    }

    void addForgetMenuIfSuitable(ContextMenu contextMenu) {
        if (this.mIsAdmin) {
            contextMenu.add(0, 4, 0, R.string.forget);
        }
    }

    void addModifyMenuIfSuitable(ContextMenu contextMenu, WifiEntry wifiEntry) {
        if (this.mIsAdmin && wifiEntry.isSaved() && wifiEntry.getConnectedState() != 2) {
            contextMenu.add(0, 5, 0, R.string.wifi_modify);
        }
    }

    private boolean canForgetNetwork() {
        return this.mSelectedWifiEntry.canForget() && !WifiUtils.isNetworkLockedDown(getActivity(), this.mSelectedWifiEntry.getWifiConfiguration());
    }

    @Override // androidx.fragment.app.Fragment
    public boolean onContextItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == 2) {
            connect(this.mSelectedWifiEntry, true, false);
            return true;
        } else if (itemId == 3) {
            this.mSelectedWifiEntry.disconnect(null);
            return true;
        } else if (itemId == 4) {
            forget(this.mSelectedWifiEntry);
            return true;
        } else if (itemId != 5) {
            if (itemId == 7) {
                WifiDppUtils.showLockScreen(getContext(), new Runnable() { // from class: com.android.settings.network.NetworkProviderSettings$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        NetworkProviderSettings.this.lambda$onContextItemSelected$3();
                    }
                });
                return true;
            }
            return super.onContextItemSelected(menuItem);
        } else if (!this.mIsAdmin) {
            Log.e("NetworkProviderSettings", "Can't modify Wi-Fi because the user isn't admin.");
            EventLog.writeEvent(1397638484, "237672190", Integer.valueOf(UserHandle.myUserId()), "User isn't admin");
            return true;
        } else {
            showDialog(this.mSelectedWifiEntry, 2);
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onContextItemSelected$3() {
        launchWifiDppConfiguratorActivity(this.mSelectedWifiEntry);
    }

    @Override // com.android.settings.core.InstrumentedPreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.preference.PreferenceManager.OnPreferenceTreeClickListener
    public boolean onPreferenceTreeClick(Preference preference) {
        if (preference.getFragment() != null) {
            preference.setOnPreferenceClickListener(null);
            return super.onPreferenceTreeClick(preference);
        } else if (preference instanceof LongPressWifiEntryPreference) {
            onSelectedWifiPreferenceClick((LongPressWifiEntryPreference) preference);
            return true;
        } else if (preference == this.mAddWifiNetworkPreference) {
            onAddNetworkPressed();
            return true;
        } else {
            return super.onPreferenceTreeClick(preference);
        }
    }

    void onSelectedWifiPreferenceClick(LongPressWifiEntryPreference longPressWifiEntryPreference) {
        WifiEntry wifiEntry = longPressWifiEntryPreference.getWifiEntry();
        if (wifiEntry.shouldEditBeforeConnect()) {
            launchConfigNewNetworkFragment(wifiEntry);
        } else if (wifiEntry.canConnect()) {
            connect(wifiEntry, true, true);
        } else if (wifiEntry.isSaved()) {
            launchNetworkDetailsFragment(longPressWifiEntryPreference);
        }
    }

    private void launchWifiDppConfiguratorActivity(WifiEntry wifiEntry) {
        Intent configuratorQrCodeGeneratorIntentOrNull = WifiDppUtils.getConfiguratorQrCodeGeneratorIntentOrNull(getContext(), this.mWifiManager, wifiEntry);
        if (configuratorQrCodeGeneratorIntentOrNull == null) {
            Log.e("NetworkProviderSettings", "Launch Wi-Fi DPP QR code generator with a wrong Wi-Fi network!");
            return;
        }
        this.mMetricsFeatureProvider.action(0, 1710, 1595, null, Integer.MIN_VALUE);
        startActivity(configuratorQrCodeGeneratorIntentOrNull);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showDialog(WifiEntry wifiEntry, int i) {
        if (WifiUtils.isNetworkLockedDown(getActivity(), wifiEntry.getWifiConfiguration()) && wifiEntry.getConnectedState() == 2) {
            RestrictedLockUtils.sendShowAdminSupportDetailsIntent(getActivity(), RestrictedLockUtilsInternal.getDeviceOwner(getActivity()));
            return;
        }
        if (this.mDialog != null) {
            removeDialog(1);
            this.mDialog = null;
        }
        this.mDialogWifiEntry = wifiEntry;
        this.mDialogWifiEntryKey = wifiEntry.getKey();
        this.mDialogMode = i;
        showDialog(1);
    }

    @Override // com.android.settings.SettingsPreferenceFragment, com.android.settings.DialogCreatable
    public Dialog onCreateDialog(int i) {
        if (i == 1) {
            WifiDialog2 createModal = WifiDialog2.createModal(getActivity(), this, this.mDialogWifiEntry, this.mDialogMode);
            this.mDialog = createModal;
            return createModal;
        }
        return super.onCreateDialog(i);
    }

    @Override // com.android.settings.SettingsPreferenceFragment
    public void onDialogShowing() {
        super.onDialogShowing();
        setOnDismissListener(this);
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        this.mDialog = null;
        this.mDialogWifiEntry = null;
        this.mDialogWifiEntryKey = null;
    }

    @Override // com.android.settings.network.InternetUpdater.InternetChangeListener
    public void onInternetTypeChanged(int i) {
        ThreadUtils.postOnMainThread(new Runnable() { // from class: com.android.settings.network.NetworkProviderSettings$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                NetworkProviderSettings.this.lambda$onInternetTypeChanged$4();
            }
        });
    }

    @Override // com.android.wifitrackerlib.BaseWifiTracker.BaseWifiTrackerCallback
    /* renamed from: onWifiStateChanged */
    public void lambda$onInternetTypeChanged$4() {
        if (this.mIsRestricted || !hasWifiManager()) {
            return;
        }
        int wifiState = this.mWifiPickerTracker.getWifiState();
        if (isVerboseLoggingEnabled()) {
            Log.i("NetworkProviderSettings", "onWifiStateChanged called with wifi state: " + wifiState);
        }
        if (isFinishingOrDestroyed()) {
            Log.w("NetworkProviderSettings", "onWifiStateChanged shouldn't run when fragment is finishing or destroyed");
        } else if (wifiState == 0) {
            removeConnectedWifiEntryPreference();
            removeWifiEntryPreference();
        } else if (wifiState == 1) {
            setWifiScanMessage(false);
            removeConnectedWifiEntryPreference();
            removeWifiEntryPreference();
            setAdditionalSettingsSummaries();
            setProgressBarVisible(false);
            this.mClickedConnect = false;
        } else if (wifiState == 2) {
            removeConnectedWifiEntryPreference();
            removeWifiEntryPreference();
            setProgressBarVisible(true);
        } else if (wifiState != 3) {
        } else {
            setWifiScanMessage(true);
            updateWifiEntryPreferences();
        }
    }

    void setWifiScanMessage(boolean z) {
        Context context = getContext();
        if (context == null) {
            return;
        }
        LocationManager locationManager = (LocationManager) context.getSystemService(LocationManager.class);
        if (!hasWifiManager() || z || !locationManager.isLocationEnabled() || !this.mWifiManager.isScanAlwaysAvailable()) {
            this.mWifiStatusMessagePreference.setVisible(false);
            return;
        }
        if (TextUtils.isEmpty(this.mWifiStatusMessagePreference.getTitle())) {
            this.mWifiStatusMessagePreference.setTitle(R.string.wifi_scan_notify_message);
            this.mWifiStatusMessagePreference.setLearnMoreText(context.getString(R.string.wifi_scan_change));
            this.mWifiStatusMessagePreference.setLearnMoreAction(new View.OnClickListener() { // from class: com.android.settings.network.NetworkProviderSettings$$ExternalSyntheticLambda4
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    NetworkProviderSettings.this.lambda$setWifiScanMessage$5(view);
                }
            });
        }
        this.mWifiStatusMessagePreference.setVisible(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setWifiScanMessage$5(View view) {
        launchWifiScanningFragment();
    }

    private void launchWifiScanningFragment() {
        new SubSettingLauncher(getContext()).setDestination(WifiScanningFragment.class.getName()).setSourceMetricsCategory(746).launch();
    }

    @Override // com.android.wifitrackerlib.WifiPickerTracker.WifiPickerTrackerCallback
    public void onWifiEntriesChanged() {
        WifiPickerTracker wifiPickerTracker;
        boolean z = false;
        if (this.mIsWifiEntryListStale) {
            this.mIsWifiEntryListStale = false;
            updateWifiEntryPreferences();
        } else {
            updateWifiEntryPreferencesDelayed();
        }
        WifiPickerTracker wifiPickerTracker2 = this.mWifiPickerTracker;
        if (wifiPickerTracker2 != null && wifiPickerTracker2.getConnectedWifiEntry() != null) {
            z = true;
        }
        changeNextButtonState(z);
        if (this.mOpenSsid == null || (wifiPickerTracker = this.mWifiPickerTracker) == null) {
            return;
        }
        Optional<WifiEntry> findFirst = wifiPickerTracker.getWifiEntries().stream().filter(new Predicate() { // from class: com.android.settings.network.NetworkProviderSettings$$ExternalSyntheticLambda9
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean lambda$onWifiEntriesChanged$6;
                lambda$onWifiEntriesChanged$6 = NetworkProviderSettings.this.lambda$onWifiEntriesChanged$6((WifiEntry) obj);
                return lambda$onWifiEntriesChanged$6;
            }
        }).filter(new Predicate() { // from class: com.android.settings.network.NetworkProviderSettings$$ExternalSyntheticLambda10
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean lambda$onWifiEntriesChanged$7;
                lambda$onWifiEntriesChanged$7 = NetworkProviderSettings.lambda$onWifiEntriesChanged$7((WifiEntry) obj);
                return lambda$onWifiEntriesChanged$7;
            }
        }).filter(new Predicate() { // from class: com.android.settings.network.NetworkProviderSettings$$ExternalSyntheticLambda11
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean lambda$onWifiEntriesChanged$8;
                lambda$onWifiEntriesChanged$8 = NetworkProviderSettings.lambda$onWifiEntriesChanged$8((WifiEntry) obj);
                return lambda$onWifiEntriesChanged$8;
            }
        }).findFirst();
        if (findFirst.isPresent()) {
            this.mOpenSsid = null;
            launchConfigNewNetworkFragment(findFirst.get());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$onWifiEntriesChanged$6(WifiEntry wifiEntry) {
        return TextUtils.equals(this.mOpenSsid, wifiEntry.getSsid());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$onWifiEntriesChanged$7(WifiEntry wifiEntry) {
        return (wifiEntry.getSecurity() == 0 || wifiEntry.getSecurity() == 4) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$onWifiEntriesChanged$8(WifiEntry wifiEntry) {
        return !wifiEntry.isSaved() || isDisabledByWrongPassword(wifiEntry);
    }

    @Override // com.android.wifitrackerlib.WifiPickerTracker.WifiPickerTrackerCallback
    public void onNumSavedNetworksChanged() {
        if (isFinishingOrDestroyed()) {
            return;
        }
        setAdditionalSettingsSummaries();
    }

    @Override // com.android.wifitrackerlib.WifiPickerTracker.WifiPickerTrackerCallback
    public void onNumSavedSubscriptionsChanged() {
        if (isFinishingOrDestroyed()) {
            return;
        }
        setAdditionalSettingsSummaries();
    }

    private void updateWifiEntryPreferencesDelayed() {
        WifiPickerTracker wifiPickerTracker;
        if (getActivity() == null || this.mIsRestricted || (wifiPickerTracker = this.mWifiPickerTracker) == null || wifiPickerTracker.getWifiState() != 3) {
            return;
        }
        View view = getView();
        Handler handler = view.getHandler();
        if (handler == null || !handler.hasCallbacks(this.mUpdateWifiEntryPreferencesRunnable)) {
            setProgressBarVisible(true);
            view.postDelayed(this.mUpdateWifiEntryPreferencesRunnable, 300L);
        }
    }

    protected void updateWifiEntryPreferences() {
        WifiPickerTracker wifiPickerTracker;
        if (getActivity() == null || getView() == null || this.mIsRestricted || (wifiPickerTracker = this.mWifiPickerTracker) == null || wifiPickerTracker.getWifiState() != 3) {
            return;
        }
        this.mWifiEntryPreferenceCategory.setVisible(true);
        final WifiEntry connectedWifiEntry = this.mWifiPickerTracker.getConnectedWifiEntry();
        PreferenceCategory connectedWifiPreferenceCategory = getConnectedWifiPreferenceCategory();
        connectedWifiPreferenceCategory.setVisible(connectedWifiEntry != null);
        if (connectedWifiEntry != null) {
            LongPressWifiEntryPreference longPressWifiEntryPreference = (LongPressWifiEntryPreference) connectedWifiPreferenceCategory.findPreference(connectedWifiEntry.getKey());
            if (longPressWifiEntryPreference == null || longPressWifiEntryPreference.getWifiEntry() != connectedWifiEntry) {
                connectedWifiPreferenceCategory.removeAll();
                final ConnectedWifiEntryPreference createConnectedWifiEntryPreference = createConnectedWifiEntryPreference(connectedWifiEntry);
                createConnectedWifiEntryPreference.setKey(connectedWifiEntry.getKey());
                createConnectedWifiEntryPreference.refresh();
                connectedWifiPreferenceCategory.addPreference(createConnectedWifiEntryPreference);
                createConnectedWifiEntryPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.settings.network.NetworkProviderSettings$$ExternalSyntheticLambda6
                    @Override // androidx.preference.Preference.OnPreferenceClickListener
                    public final boolean onPreferenceClick(Preference preference) {
                        boolean lambda$updateWifiEntryPreferences$9;
                        lambda$updateWifiEntryPreferences$9 = NetworkProviderSettings.this.lambda$updateWifiEntryPreferences$9(connectedWifiEntry, createConnectedWifiEntryPreference, preference);
                        return lambda$updateWifiEntryPreferences$9;
                    }
                });
                createConnectedWifiEntryPreference.setOnGearClickListener(new ConnectedWifiEntryPreference.OnGearClickListener() { // from class: com.android.settings.network.NetworkProviderSettings$$ExternalSyntheticLambda7
                    @Override // com.android.settings.wifi.ConnectedWifiEntryPreference.OnGearClickListener
                    public final void onGearClick(ConnectedWifiEntryPreference connectedWifiEntryPreference) {
                        NetworkProviderSettings.this.lambda$updateWifiEntryPreferences$10(createConnectedWifiEntryPreference, connectedWifiEntryPreference);
                    }
                });
                if (this.mClickedConnect) {
                    this.mClickedConnect = false;
                    scrollToPreference(connectedWifiPreferenceCategory);
                }
            }
        } else {
            connectedWifiPreferenceCategory.removeAll();
        }
        cacheRemoveAllPrefs(this.mWifiEntryPreferenceCategory);
        boolean z = false;
        int i = 0;
        for (final WifiEntry wifiEntry : this.mWifiPickerTracker.getWifiEntries()) {
            String key = wifiEntry.getKey();
            LongPressWifiEntryPreference longPressWifiEntryPreference2 = (LongPressWifiEntryPreference) getCachedPreference(key);
            if (longPressWifiEntryPreference2 != null) {
                if (longPressWifiEntryPreference2.getWifiEntry() == wifiEntry) {
                    longPressWifiEntryPreference2.setOrder(i);
                    i++;
                    z = true;
                } else {
                    removePreference(key);
                }
            }
            LongPressWifiEntryPreference createLongPressWifiEntryPreference = createLongPressWifiEntryPreference(wifiEntry);
            createLongPressWifiEntryPreference.setKey(wifiEntry.getKey());
            int i2 = i + 1;
            createLongPressWifiEntryPreference.setOrder(i);
            createLongPressWifiEntryPreference.refresh();
            if (wifiEntry.getHelpUriString() != null) {
                createLongPressWifiEntryPreference.setOnButtonClickListener(new WifiEntryPreference.OnButtonClickListener() { // from class: com.android.settings.network.NetworkProviderSettings$$ExternalSyntheticLambda8
                    @Override // com.android.settings.wifi.WifiEntryPreference.OnButtonClickListener
                    public final void onButtonClick(WifiEntryPreference wifiEntryPreference) {
                        NetworkProviderSettings.this.lambda$updateWifiEntryPreferences$11(wifiEntry, wifiEntryPreference);
                    }
                });
            }
            this.mWifiEntryPreferenceCategory.addPreference(createLongPressWifiEntryPreference);
            z = true;
            i = i2;
        }
        removeCachedPrefs(this.mWifiEntryPreferenceCategory);
        if (!z) {
            setProgressBarVisible(true);
            Preference preference = new Preference(getPrefContext());
            preference.setSelectable(false);
            preference.setSummary(R.string.wifi_empty_list_wifi_on);
            preference.setOrder(i);
            preference.setKey("wifi_empty_list");
            this.mWifiEntryPreferenceCategory.addPreference(preference);
            i++;
        } else {
            getView().postDelayed(this.mHideProgressBarRunnable, 1700L);
        }
        this.mAddWifiNetworkPreference.setOrder(i);
        this.mWifiEntryPreferenceCategory.addPreference(this.mAddWifiNetworkPreference);
        setAdditionalSettingsSummaries();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$updateWifiEntryPreferences$9(WifiEntry wifiEntry, ConnectedWifiEntryPreference connectedWifiEntryPreference, Preference preference) {
        if (wifiEntry.canSignIn()) {
            wifiEntry.signIn(null);
            return true;
        }
        launchNetworkDetailsFragment(connectedWifiEntryPreference);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$updateWifiEntryPreferences$10(ConnectedWifiEntryPreference connectedWifiEntryPreference, ConnectedWifiEntryPreference connectedWifiEntryPreference2) {
        launchNetworkDetailsFragment(connectedWifiEntryPreference);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$updateWifiEntryPreferences$11(WifiEntry wifiEntry, WifiEntryPreference wifiEntryPreference) {
        openSubscriptionHelpPage(wifiEntry);
    }

    PreferenceCategory getConnectedWifiPreferenceCategory() {
        if (this.mInternetUpdater.getInternetType() == 2) {
            this.mFirstWifiEntryPreferenceCategory.setVisible(false);
            this.mFirstWifiEntryPreferenceCategory.removeAll();
            return this.mConnectedWifiEntryPreferenceCategory;
        }
        this.mConnectedWifiEntryPreferenceCategory.setVisible(false);
        this.mConnectedWifiEntryPreferenceCategory.removeAll();
        return this.mFirstWifiEntryPreferenceCategory;
    }

    ConnectedWifiEntryPreference createConnectedWifiEntryPreference(WifiEntry wifiEntry) {
        if (this.mInternetUpdater.getInternetType() == 2) {
            return new ConnectedWifiEntryPreference(getPrefContext(), wifiEntry, this);
        }
        return new FirstWifiEntryPreference(getPrefContext(), wifiEntry, this);
    }

    void launchNetworkDetailsFragment(LongPressWifiEntryPreference longPressWifiEntryPreference) {
        CharSequence text;
        WifiEntry wifiEntry = longPressWifiEntryPreference.getWifiEntry();
        Context context = getContext();
        if (FeatureFlagUtils.isEnabled(context, "settings_wifi_details_datausage_header")) {
            text = wifiEntry.getTitle();
        } else {
            text = context.getText(R.string.pref_title_network_details);
        }
        Bundle bundle = new Bundle();
        bundle.putString("key_chosen_wifientry_key", wifiEntry.getKey());
        new SubSettingLauncher(context).setTitleText(text).setDestination(WifiNetworkDetailsFragment.class.getName()).setArguments(bundle).setSourceMetricsCategory(getMetricsCategory()).launch();
    }

    LongPressWifiEntryPreference createLongPressWifiEntryPreference(WifiEntry wifiEntry) {
        return new LongPressWifiEntryPreference(getPrefContext(), wifiEntry, this);
    }

    private void launchAddNetworkFragment() {
        new SubSettingLauncher(getContext()).setTitleRes(R.string.wifi_add_network).setDestination(AddNetworkFragment.class.getName()).setSourceMetricsCategory(getMetricsCategory()).setResultListener(this, 2).launch();
    }

    private void removeConnectedWifiEntryPreference() {
        this.mConnectedWifiEntryPreferenceCategory.removeAll();
        this.mConnectedWifiEntryPreferenceCategory.setVisible(false);
    }

    private void removeWifiEntryPreference() {
        this.mWifiEntryPreferenceCategory.removeAll();
        this.mWifiEntryPreferenceCategory.setVisible(false);
    }

    void setAdditionalSettingsSummaries() {
        int i;
        Preference preference = this.mConfigureWifiSettingsPreference;
        if (isWifiWakeupEnabled()) {
            i = R.string.wifi_configure_settings_preference_summary_wakeup_on;
        } else {
            i = R.string.wifi_configure_settings_preference_summary_wakeup_off;
        }
        preference.setSummary(getString(i));
        WifiPickerTracker wifiPickerTracker = this.mWifiPickerTracker;
        int numSavedNetworks = wifiPickerTracker == null ? 0 : wifiPickerTracker.getNumSavedNetworks();
        WifiPickerTracker wifiPickerTracker2 = this.mWifiPickerTracker;
        int numSavedSubscriptions = wifiPickerTracker2 == null ? 0 : wifiPickerTracker2.getNumSavedSubscriptions();
        if (numSavedNetworks + numSavedSubscriptions > 0) {
            this.mSavedNetworksPreference.setVisible(true);
            this.mSavedNetworksPreference.setSummary(getSavedNetworkSettingsSummaryText(numSavedNetworks, numSavedSubscriptions));
            return;
        }
        this.mSavedNetworksPreference.setVisible(false);
    }

    private String getSavedNetworkSettingsSummaryText(int i, int i2) {
        if (getResources() == null) {
            Log.w("NetworkProviderSettings", "getSavedNetworkSettingsSummaryText shouldn't run if resource is not ready");
            return null;
        } else if (i2 == 0) {
            return getResources().getQuantityString(R.plurals.wifi_saved_access_points_summary, i, Integer.valueOf(i));
        } else {
            if (i == 0) {
                return getResources().getQuantityString(R.plurals.wifi_saved_passpoint_access_points_summary, i2, Integer.valueOf(i2));
            }
            int i3 = i + i2;
            return getResources().getQuantityString(R.plurals.wifi_saved_all_access_points_summary, i3, Integer.valueOf(i3));
        }
    }

    private boolean isWifiWakeupEnabled() {
        Context context = getContext();
        return hasWifiManager() && this.mWifiManager.isAutoWakeupEnabled() && this.mWifiManager.isScanAlwaysAvailable() && Settings.Global.getInt(context.getContentResolver(), "airplane_mode_on", 0) == 0 && !((PowerManager) context.getSystemService(PowerManager.class)).isPowerSaveMode();
    }

    protected void setProgressBarVisible(boolean z) {
        showPinnedHeader(z);
    }

    void handleAddNetworkRequest(int i, Intent intent) {
        if (i == -1) {
            handleAddNetworkSubmitEvent(intent);
        }
    }

    private void handleAddNetworkSubmitEvent(Intent intent) {
        WifiConfiguration wifiConfiguration = (WifiConfiguration) intent.getParcelableExtra("wifi_config_key");
        if (wifiConfiguration == null || !hasWifiManager()) {
            return;
        }
        this.mWifiManager.save(wifiConfiguration, this.mSaveListener);
    }

    private void onAddNetworkPressed() {
        launchAddNetworkFragment();
    }

    @Override // com.android.settings.support.actionbar.HelpResourceProvider
    public int getHelpResource() {
        return R.string.help_url_wifi;
    }

    void changeNextButtonState(boolean z) {
        if (this.mEnableNextOnConnection && hasNextButton()) {
            getNextButton().setEnabled(z);
        }
    }

    @Override // com.android.settings.wifi.WifiDialog2.WifiDialog2Listener
    public void onForget(WifiDialog2 wifiDialog2) {
        forget(wifiDialog2.getWifiEntry());
    }

    @Override // com.android.settings.wifi.WifiDialog2.WifiDialog2Listener
    public void onSubmit(WifiDialog2 wifiDialog2) {
        if (hasWifiManager()) {
            int mode = wifiDialog2.getMode();
            WifiConfiguration config = wifiDialog2.getController().getConfig();
            WifiEntry wifiEntry = wifiDialog2.getWifiEntry();
            if (mode == 2) {
                if (config == null) {
                    Toast.makeText(getContext(), R.string.wifi_failed_save_message, 0).show();
                } else {
                    this.mWifiManager.save(config, this.mSaveListener);
                }
            } else if (mode == 1 || (mode == 0 && wifiEntry.canConnect())) {
                if (config == null) {
                    connect(wifiEntry, false, false);
                } else {
                    this.mWifiManager.connect(config, new WifiConnectActionListener());
                }
            }
        }
    }

    @Override // com.android.settings.wifi.WifiDialog2.WifiDialog2Listener
    public void onScan(WifiDialog2 wifiDialog2, String str) {
        startActivityForResult(WifiDppUtils.getEnrolleeQrCodeScannerIntent(wifiDialog2.getContext(), str), 0);
    }

    private void forget(WifiEntry wifiEntry) {
        this.mMetricsFeatureProvider.action(getActivity(), 137, new Pair[0]);
        wifiEntry.forget(null);
    }

    void connect(WifiEntry wifiEntry, boolean z, boolean z2) {
        this.mMetricsFeatureProvider.action(getActivity(), 135, wifiEntry.isSaved());
        wifiEntry.connect(new WifiEntryConnectCallback(wifiEntry, z, z2));
    }

    /* loaded from: classes.dex */
    private class WifiConnectActionListener implements WifiManager.ActionListener {
        private WifiConnectActionListener() {
        }

        public void onSuccess() {
            NetworkProviderSettings.this.mClickedConnect = true;
        }

        public void onFailure(int i) {
            if (NetworkProviderSettings.this.isFinishingOrDestroyed()) {
                return;
            }
            Toast.makeText(NetworkProviderSettings.this.getContext(), R.string.wifi_failed_connect_message, 0).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class SearchIndexProvider extends BaseSearchIndexProvider {
        private final WifiRestriction mWifiRestriction;

        SearchIndexProvider(int i) {
            super(i);
            this.mWifiRestriction = new WifiRestriction();
        }

        SearchIndexProvider(int i, WifiRestriction wifiRestriction) {
            super(i);
            this.mWifiRestriction = wifiRestriction;
        }

        @Override // com.android.settings.search.BaseSearchIndexProvider, com.android.settingslib.search.Indexable$SearchIndexProvider
        public List<String> getNonIndexableKeys(Context context) {
            List<String> nonIndexableKeys = super.getNonIndexableKeys(context);
            if (!this.mWifiRestriction.isChangeWifiStateAllowed(context)) {
                nonIndexableKeys.add(NetworkProviderSettings.PREF_KEY_WIFI_TOGGLE);
            }
            WifiManager wifiManager = (WifiManager) context.getSystemService(WifiManager.class);
            if (wifiManager == null) {
                return nonIndexableKeys;
            }
            if (WifiSavedConfigUtils.getAllConfigsCount(context, wifiManager) == 0) {
                nonIndexableKeys.add("saved_networks");
            }
            if (!DataUsageUtils.hasWifiRadio(context)) {
                nonIndexableKeys.add(NetworkProviderSettings.PREF_KEY_DATA_USAGE);
            }
            return nonIndexableKeys;
        }
    }

    /* loaded from: classes.dex */
    static class WifiRestriction {
        WifiRestriction() {
        }

        public boolean isChangeWifiStateAllowed(Context context) {
            if (context == null) {
                return true;
            }
            return WifiEnterpriseRestrictionUtils.isChangeWifiStateAllowed(context);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class WifiEntryConnectCallback implements WifiEntry.ConnectCallback {
        final WifiEntry mConnectWifiEntry;
        final boolean mEditIfNoConfig;
        final boolean mFullScreenEdit;

        WifiEntryConnectCallback(WifiEntry wifiEntry, boolean z, boolean z2) {
            this.mConnectWifiEntry = wifiEntry;
            this.mEditIfNoConfig = z;
            this.mFullScreenEdit = z2;
        }

        @Override // com.android.wifitrackerlib.WifiEntry.ConnectCallback
        public void onConnectResult(int i) {
            if (NetworkProviderSettings.this.isFinishingOrDestroyed()) {
                return;
            }
            if (i == 0) {
                NetworkProviderSettings.this.mClickedConnect = true;
            } else if (i != 1) {
                if (i == 2) {
                    Toast.makeText(NetworkProviderSettings.this.getContext(), R.string.wifi_failed_connect_message, 0).show();
                }
            } else if (this.mEditIfNoConfig) {
                if (this.mFullScreenEdit) {
                    NetworkProviderSettings.this.launchConfigNewNetworkFragment(this.mConnectWifiEntry);
                } else {
                    NetworkProviderSettings.this.showDialog(this.mConnectWifiEntry, 1);
                }
            }
        }
    }

    void launchConfigNewNetworkFragment(WifiEntry wifiEntry) {
        if (this.mIsRestricted) {
            Log.e("NetworkProviderSettings", "Can't configure Wi-Fi because NetworkProviderSettings is restricted.");
            EventLog.writeEvent(1397638484, "246301667", -1, "Fragment is restricted.");
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("key_chosen_wifientry_key", wifiEntry.getKey());
        new SubSettingLauncher(getContext()).setTitleText(wifiEntry.getTitle()).setDestination(ConfigureWifiEntryFragment.class.getName()).setArguments(bundle).setSourceMetricsCategory(getMetricsCategory()).setResultListener(this, 3).launch();
    }

    private static boolean isDisabledByWrongPassword(WifiEntry wifiEntry) {
        WifiConfiguration.NetworkSelectionStatus networkSelectionStatus;
        WifiConfiguration wifiConfiguration = wifiEntry.getWifiConfiguration();
        return (wifiConfiguration == null || (networkSelectionStatus = wifiConfiguration.getNetworkSelectionStatus()) == null || networkSelectionStatus.getNetworkSelectionStatus() == 0 || 8 != networkSelectionStatus.getNetworkSelectionDisableReason()) ? false : true;
    }

    void openSubscriptionHelpPage(WifiEntry wifiEntry) {
        Intent helpIntent = getHelpIntent(getContext(), wifiEntry.getHelpUriString());
        if (helpIntent != null) {
            try {
                startActivityForResult(helpIntent, 4);
            } catch (ActivityNotFoundException unused) {
                Log.e("NetworkProviderSettings", "Activity was not found for intent, " + helpIntent.toString());
            }
        }
    }

    Intent getHelpIntent(Context context, String str) {
        return HelpUtils.getHelpIntent(context, str, context.getClass().getName());
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        if (!this.mAirplaneModeEnabler.isAirplaneModeOn()) {
            MenuItem add = menu.add(0, 6, 0, R.string.fix_connectivity);
            add.setIcon(R.drawable.ic_repair_24dp);
            add.setShowAsAction(2);
        }
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 6) {
            if (isPhoneOnCall()) {
                showResetInternetDialog();
                return true;
            }
            fixConnectivity();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    void showResetInternetDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(R.string.reset_your_internet_title).setMessage(R.string.reset_internet_text).setPositiveButton(R.string.tts_reset, new DialogInterface.OnClickListener() { // from class: com.android.settings.network.NetworkProviderSettings.3
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                NetworkProviderSettings.this.fixConnectivity();
            }
        }).setNegativeButton(17039360, (DialogInterface.OnClickListener) null).create().show();
    }

    boolean isPhoneOnCall() {
        return ((TelephonyManager) getActivity().getSystemService(TelephonyManager.class)).getCallState() != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void fixConnectivity() {
        this.mInternetResetHelper.restart();
    }

    @Override // com.android.settings.AirplaneModeEnabler.OnAirplaneModeChangedListener
    public void onAirplaneModeChanged(boolean z) {
        updateAirplaneModeMsgPreference(z);
    }

    /* loaded from: classes.dex */
    public class FirstWifiEntryPreference extends ConnectedWifiEntryPreference {
        @Override // com.android.settings.wifi.WifiEntryPreference
        protected int getIconColorAttr() {
            return 16843817;
        }

        public FirstWifiEntryPreference(Context context, WifiEntry wifiEntry, Fragment fragment) {
            super(context, wifiEntry, fragment);
        }
    }
}
