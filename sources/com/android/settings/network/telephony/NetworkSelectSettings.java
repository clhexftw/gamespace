package com.android.settings.network.telephony;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.telephony.CarrierConfigManager;
import android.telephony.CellIdentity;
import android.telephony.CellInfo;
import android.telephony.NetworkRegistrationInfo;
import android.telephony.ServiceState;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import androidx.annotation.Keep;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import com.android.internal.telephony.OperatorInfo;
import com.android.settings.R;
import com.android.settings.dashboard.DashboardFragment;
import com.android.settings.network.telephony.NetworkScanHelper;
import com.android.settings.overlay.FeatureFactory;
import com.android.settingslib.core.instrumentation.MetricsFeatureProvider;
import com.android.settingslib.utils.ThreadUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Predicate;
@Keep
/* loaded from: classes.dex */
public class NetworkSelectSettings extends DashboardFragment {
    private static final int EVENT_NETWORK_SCAN_COMPLETED = 4;
    private static final int EVENT_NETWORK_SCAN_ERROR = 3;
    private static final int EVENT_NETWORK_SCAN_RESULTS = 2;
    private static final int EVENT_SET_NETWORK_SELECTION_MANUALLY_DONE = 1;
    private static final int MIN_NUMBER_OF_SCAN_REQUIRED = 2;
    private static final String PREF_KEY_NETWORK_OPERATORS = "network_operators_preference";
    private static final String TAG = "NetworkSelectSettings";
    List<CellInfo> mCellInfoList;
    private List<String> mForbiddenPlmns;
    private MetricsFeatureProvider mMetricsFeatureProvider;
    private NetworkScanHelper mNetworkScanHelper;
    private PreferenceCategory mPreferenceCategory;
    private View mProgressHeader;
    private long mRequestIdManualNetworkScan;
    private long mRequestIdManualNetworkSelect;
    NetworkOperatorPreference mSelectedPreference;
    private Preference mStatusMessagePreference;
    private TelephonyManager mTelephonyManager;
    private boolean mUseNewApi;
    private long mWaitingForNumberOfScanResults;
    private int mSubId = -1;
    private boolean mShow4GForLTE = false;
    private final ExecutorService mNetworkScanExecutor = Executors.newFixedThreadPool(1);
    boolean mIsAggregationEnabled = false;
    private final Handler mHandler = new Handler() { // from class: com.android.settings.network.telephony.NetworkSelectSettings.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i;
            int i2 = message.what;
            if (i2 == 1) {
                boolean booleanValue = ((Boolean) message.obj).booleanValue();
                NetworkSelectSettings.this.stopNetworkQuery();
                NetworkSelectSettings.this.setProgressBarVisible(false);
                NetworkSelectSettings.this.enablePreferenceScreen(true);
                NetworkOperatorPreference networkOperatorPreference = NetworkSelectSettings.this.mSelectedPreference;
                if (networkOperatorPreference != null) {
                    if (booleanValue) {
                        i = R.string.network_connected;
                    } else {
                        i = R.string.network_could_not_connect;
                    }
                    networkOperatorPreference.setSummary(i);
                    return;
                }
                Log.e(NetworkSelectSettings.TAG, "No preference to update!");
            } else if (i2 == 2) {
                NetworkSelectSettings.this.scanResultHandler((List) message.obj);
            } else if (i2 == 3) {
                NetworkSelectSettings.this.stopNetworkQuery();
                Log.i(NetworkSelectSettings.TAG, "Network scan failure " + message.arg1 + ": scan request 0x" + Long.toHexString(NetworkSelectSettings.this.mRequestIdManualNetworkScan) + ", waiting for scan results = " + NetworkSelectSettings.this.mWaitingForNumberOfScanResults + ", select request 0x" + Long.toHexString(NetworkSelectSettings.this.mRequestIdManualNetworkSelect));
                if (NetworkSelectSettings.this.mRequestIdManualNetworkScan < NetworkSelectSettings.this.mRequestIdManualNetworkSelect) {
                    return;
                }
                if (!NetworkSelectSettings.this.isPreferenceScreenEnabled()) {
                    NetworkSelectSettings.this.clearPreferenceSummary();
                    NetworkSelectSettings.this.enablePreferenceScreen(true);
                    return;
                }
                NetworkSelectSettings.this.addMessagePreference(R.string.network_query_error);
            } else if (i2 != 4) {
            } else {
                NetworkSelectSettings.this.stopNetworkQuery();
                Log.d(NetworkSelectSettings.TAG, "Network scan complete: scan request 0x" + Long.toHexString(NetworkSelectSettings.this.mRequestIdManualNetworkScan) + ", waiting for scan results = " + NetworkSelectSettings.this.mWaitingForNumberOfScanResults + ", select request 0x" + Long.toHexString(NetworkSelectSettings.this.mRequestIdManualNetworkSelect));
                if (NetworkSelectSettings.this.mRequestIdManualNetworkScan < NetworkSelectSettings.this.mRequestIdManualNetworkSelect) {
                    return;
                }
                if (!NetworkSelectSettings.this.isPreferenceScreenEnabled()) {
                    NetworkSelectSettings.this.clearPreferenceSummary();
                    NetworkSelectSettings.this.enablePreferenceScreen(true);
                    return;
                }
                NetworkSelectSettings networkSelectSettings = NetworkSelectSettings.this;
                if (networkSelectSettings.mCellInfoList == null) {
                    networkSelectSettings.addMessagePreference(R.string.empty_networks_list);
                }
            }
        }
    };
    private final NetworkScanHelper.NetworkScanCallback mCallback = new NetworkScanHelper.NetworkScanCallback() { // from class: com.android.settings.network.telephony.NetworkSelectSettings.2
        @Override // com.android.settings.network.telephony.NetworkScanHelper.NetworkScanCallback
        public void onResults(List<CellInfo> list) {
            NetworkSelectSettings.this.mHandler.obtainMessage(2, list).sendToTarget();
        }

        @Override // com.android.settings.network.telephony.NetworkScanHelper.NetworkScanCallback
        public void onComplete() {
            NetworkSelectSettings.this.mHandler.obtainMessage(4).sendToTarget();
        }

        @Override // com.android.settings.network.telephony.NetworkScanHelper.NetworkScanCallback
        public void onError(int i) {
            NetworkSelectSettings.this.mHandler.obtainMessage(3, i, 0).sendToTarget();
        }
    };

    @Override // com.android.settings.support.actionbar.HelpResourceProvider
    public /* bridge */ /* synthetic */ int getHelpResource() {
        return super.getHelpResource();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settings.dashboard.DashboardFragment
    public String getLogTag() {
        return TAG;
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1581;
    }

    @Override // com.android.settings.dashboard.DashboardFragment, com.android.settings.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        onCreateInitialization();
    }

    @Keep
    protected void onCreateInitialization() {
        this.mUseNewApi = enableNewAutoSelectNetworkUI(getContext());
        this.mSubId = getSubId();
        this.mPreferenceCategory = getPreferenceCategory(PREF_KEY_NETWORK_OPERATORS);
        Preference preference = new Preference(getContext());
        this.mStatusMessagePreference = preference;
        preference.setSelectable(false);
        this.mSelectedPreference = null;
        this.mTelephonyManager = getTelephonyManager(getContext(), this.mSubId);
        this.mNetworkScanHelper = new NetworkScanHelper(getContext(), this.mTelephonyManager, this.mCallback, this.mNetworkScanExecutor);
        PersistableBundle configForSubId = getCarrierConfigManager(getContext()).getConfigForSubId(this.mSubId);
        if (configForSubId != null) {
            this.mShow4GForLTE = configForSubId.getBoolean("show_4g_for_lte_data_icon_bool");
        }
        this.mMetricsFeatureProvider = getMetricsFeatureProvider(getContext());
        this.mIsAggregationEnabled = enableAggregation(getContext());
        Log.d(TAG, "init: mUseNewApi:" + this.mUseNewApi + " ,mIsAggregationEnabled:" + this.mIsAggregationEnabled + " ,mSubId:" + this.mSubId);
    }

    @Keep
    protected boolean enableNewAutoSelectNetworkUI(Context context) {
        return context.getResources().getBoolean(17891647);
    }

    @Keep
    protected boolean enableAggregation(Context context) {
        return context.getResources().getBoolean(R.bool.config_network_selection_list_aggregation_enabled);
    }

    @Keep
    protected PreferenceCategory getPreferenceCategory(String str) {
        return (PreferenceCategory) findPreference(str);
    }

    @Keep
    protected TelephonyManager getTelephonyManager(Context context, int i) {
        return ((TelephonyManager) context.getSystemService(TelephonyManager.class)).createForSubscriptionId(i);
    }

    @Keep
    protected CarrierConfigManager getCarrierConfigManager(Context context) {
        return (CarrierConfigManager) context.getSystemService(CarrierConfigManager.class);
    }

    @Keep
    protected MetricsFeatureProvider getMetricsFeatureProvider(Context context) {
        return FeatureFactory.getFactory(context).getMetricsFeatureProvider();
    }

    @Keep
    protected boolean isPreferenceScreenEnabled() {
        return getPreferenceScreen().isEnabled();
    }

    @Keep
    protected void enablePreferenceScreen(boolean z) {
        getPreferenceScreen().setEnabled(z);
    }

    @Keep
    protected int getSubId() {
        Intent intent = getActivity().getIntent();
        if (intent != null) {
            return intent.getIntExtra("android.provider.extra.SUB_ID", -1);
        }
        return -1;
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (getActivity() != null) {
            this.mProgressHeader = setPinnedHeaderView(R.layout.progress_header).findViewById(R.id.progress_bar_animation);
            setProgressBarVisible(false);
        }
        forceUpdateConnectedPreferenceCategory();
    }

    @Override // com.android.settings.dashboard.DashboardFragment, com.android.settings.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        updateForbiddenPlmns();
        if (!isProgressBarVisible() && this.mWaitingForNumberOfScanResults <= 0) {
            startNetworkQuery();
        }
    }

    @Keep
    protected void updateForbiddenPlmns() {
        List<String> arrayList;
        String[] forbiddenPlmns = this.mTelephonyManager.getForbiddenPlmns();
        if (forbiddenPlmns != null) {
            arrayList = Arrays.asList(forbiddenPlmns);
        } else {
            arrayList = new ArrayList<>();
        }
        this.mForbiddenPlmns = arrayList;
    }

    @Override // com.android.settings.dashboard.DashboardFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        if (this.mWaitingForNumberOfScanResults <= 0) {
            stopNetworkQuery();
        }
    }

    @Override // com.android.settings.dashboard.DashboardFragment, com.android.settings.core.InstrumentedPreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.preference.PreferenceManager.OnPreferenceTreeClickListener
    public boolean onPreferenceTreeClick(Preference preference) {
        if (preference != this.mSelectedPreference) {
            stopNetworkQuery();
            clearPreferenceSummary();
            NetworkOperatorPreference networkOperatorPreference = this.mSelectedPreference;
            if (networkOperatorPreference != null) {
                networkOperatorPreference.setSummary(R.string.network_disconnected);
            }
            NetworkOperatorPreference networkOperatorPreference2 = (NetworkOperatorPreference) preference;
            this.mSelectedPreference = networkOperatorPreference2;
            networkOperatorPreference2.setSummary(R.string.network_connecting);
            this.mMetricsFeatureProvider.action(getContext(), 1210, new Pair[0]);
            setProgressBarVisible(true);
            enablePreferenceScreen(false);
            this.mRequestIdManualNetworkSelect = getNewRequestId();
            this.mWaitingForNumberOfScanResults = 2L;
            final OperatorInfo operatorInfo = this.mSelectedPreference.getOperatorInfo();
            ThreadUtils.postOnBackgroundThread(new Runnable() { // from class: com.android.settings.network.telephony.NetworkSelectSettings$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    NetworkSelectSettings.this.lambda$onPreferenceTreeClick$0(operatorInfo);
                }
            });
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onPreferenceTreeClick$0(OperatorInfo operatorInfo) {
        Message obtainMessage = this.mHandler.obtainMessage(1);
        obtainMessage.obj = Boolean.valueOf(this.mTelephonyManager.setNetworkSelectionModeManual(operatorInfo, true));
        obtainMessage.sendToTarget();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settings.dashboard.DashboardFragment, com.android.settings.core.InstrumentedPreferenceFragment
    public int getPreferenceScreenResId() {
        return R.xml.choose_network;
    }

    List<CellInfo> doAggregation(List<CellInfo> list) {
        if (!this.mIsAggregationEnabled) {
            Log.d(TAG, "no aggregation");
            return new ArrayList(list);
        }
        ArrayList arrayList = new ArrayList();
        for (CellInfo cellInfo : list) {
            final String networkTitle = CellInfoUtil.getNetworkTitle(cellInfo.getCellIdentity(), CellInfoUtil.getCellIdentityMccMnc(cellInfo.getCellIdentity()));
            final Class<?> cls = cellInfo.getClass();
            Optional findFirst = arrayList.stream().filter(new Predicate() { // from class: com.android.settings.network.telephony.NetworkSelectSettings$$ExternalSyntheticLambda0
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    boolean lambda$doAggregation$1;
                    lambda$doAggregation$1 = NetworkSelectSettings.lambda$doAggregation$1(networkTitle, cls, (CellInfo) obj);
                    return lambda$doAggregation$1;
                }
            }).findFirst();
            if (findFirst.isPresent()) {
                if (cellInfo.isRegistered() && !((CellInfo) findFirst.get()).isRegistered()) {
                    arrayList.set(arrayList.indexOf(findFirst.get()), cellInfo);
                }
            } else {
                arrayList.add(cellInfo);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$doAggregation$1(String str, Class cls, CellInfo cellInfo) {
        return CellInfoUtil.getNetworkTitle(cellInfo.getCellIdentity(), CellInfoUtil.getCellIdentityMccMnc(cellInfo.getCellIdentity())).equals(str) && cellInfo.getClass().equals(cls);
    }

    @Keep
    protected void scanResultHandler(List<CellInfo> list) {
        if (this.mRequestIdManualNetworkScan < this.mRequestIdManualNetworkSelect) {
            Log.d(TAG, "CellInfoList (drop): " + CellInfoUtil.cellInfoListToString(new ArrayList(list)));
            return;
        }
        long j = this.mWaitingForNumberOfScanResults - 1;
        this.mWaitingForNumberOfScanResults = j;
        if (j <= 0 && !isResumed()) {
            stopNetworkQuery();
        }
        this.mCellInfoList = doAggregation(list);
        Log.d(TAG, "CellInfoList: " + CellInfoUtil.cellInfoListToString(this.mCellInfoList));
        List<CellInfo> list2 = this.mCellInfoList;
        if (list2 != null && list2.size() != 0) {
            NetworkOperatorPreference updateAllPreferenceCategory = updateAllPreferenceCategory();
            if (updateAllPreferenceCategory != null) {
                if (this.mSelectedPreference != null) {
                    this.mSelectedPreference = updateAllPreferenceCategory;
                }
            } else if (!isPreferenceScreenEnabled() && updateAllPreferenceCategory == null) {
                this.mSelectedPreference.setSummary(R.string.network_connecting);
            }
            enablePreferenceScreen(true);
        } else if (isPreferenceScreenEnabled()) {
            addMessagePreference(R.string.empty_networks_list);
            setProgressBarVisible(true);
        }
    }

    @Keep
    protected NetworkOperatorPreference createNetworkOperatorPreference(CellInfo cellInfo) {
        return new NetworkOperatorPreference(getPrefContext(), cellInfo, this.mForbiddenPlmns, this.mShow4GForLTE);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0070  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private com.android.settings.network.telephony.NetworkOperatorPreference updateAllPreferenceCategory() {
        /*
            r8 = this;
            androidx.preference.PreferenceCategory r0 = r8.mPreferenceCategory
            int r0 = r0.getPreferenceCount()
        L6:
            java.util.List<android.telephony.CellInfo> r1 = r8.mCellInfoList
            int r1 = r1.size()
            if (r0 <= r1) goto L1a
            int r0 = r0 + (-1)
            androidx.preference.PreferenceCategory r1 = r8.mPreferenceCategory
            androidx.preference.Preference r2 = r1.getPreference(r0)
            r1.removePreference(r2)
            goto L6
        L1a:
            r1 = 0
            r2 = 0
            r3 = r1
            r4 = r2
        L1e:
            java.util.List<android.telephony.CellInfo> r5 = r8.mCellInfoList
            int r5 = r5.size()
            if (r3 >= r5) goto L76
            java.util.List<android.telephony.CellInfo> r5 = r8.mCellInfoList
            java.lang.Object r5 = r5.get(r3)
            android.telephony.CellInfo r5 = (android.telephony.CellInfo) r5
            if (r3 >= r0) goto L45
            androidx.preference.PreferenceCategory r6 = r8.mPreferenceCategory
            androidx.preference.Preference r6 = r6.getPreference(r3)
            boolean r7 = r6 instanceof com.android.settings.network.telephony.NetworkOperatorPreference
            if (r7 == 0) goto L40
            com.android.settings.network.telephony.NetworkOperatorPreference r6 = (com.android.settings.network.telephony.NetworkOperatorPreference) r6
            r6.updateCell(r5)
            goto L46
        L40:
            androidx.preference.PreferenceCategory r7 = r8.mPreferenceCategory
            r7.removePreference(r6)
        L45:
            r6 = r2
        L46:
            if (r6 != 0) goto L54
            com.android.settings.network.telephony.NetworkOperatorPreference r6 = r8.createNetworkOperatorPreference(r5)
            r6.setOrder(r3)
            androidx.preference.PreferenceCategory r5 = r8.mPreferenceCategory
            r5.addPreference(r6)
        L54:
            java.lang.String r5 = r6.getOperatorName()
            r6.setKey(r5)
            java.util.List<android.telephony.CellInfo> r5 = r8.mCellInfoList
            java.lang.Object r5 = r5.get(r3)
            android.telephony.CellInfo r5 = (android.telephony.CellInfo) r5
            boolean r5 = r5.isRegistered()
            if (r5 == 0) goto L70
            int r4 = com.android.settings.R.string.network_connected
            r6.setSummary(r4)
            r4 = r6
            goto L73
        L70:
            r6.setSummary(r2)
        L73:
            int r3 = r3 + 1
            goto L1e
        L76:
            java.util.List<android.telephony.CellInfo> r0 = r8.mCellInfoList
            int r0 = r0.size()
            if (r1 >= r0) goto L9d
            java.util.List<android.telephony.CellInfo> r0 = r8.mCellInfoList
            java.lang.Object r0 = r0.get(r1)
            android.telephony.CellInfo r0 = (android.telephony.CellInfo) r0
            com.android.settings.network.telephony.NetworkOperatorPreference r2 = r8.mSelectedPreference
            if (r2 == 0) goto L9a
            boolean r0 = r2.isSameCell(r0)
            if (r0 == 0) goto L9a
            androidx.preference.PreferenceCategory r0 = r8.mPreferenceCategory
            androidx.preference.Preference r0 = r0.getPreference(r1)
            com.android.settings.network.telephony.NetworkOperatorPreference r0 = (com.android.settings.network.telephony.NetworkOperatorPreference) r0
            r8.mSelectedPreference = r0
        L9a:
            int r1 = r1 + 1
            goto L76
        L9d:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settings.network.telephony.NetworkSelectSettings.updateAllPreferenceCategory():com.android.settings.network.telephony.NetworkOperatorPreference");
    }

    private void forceUpdateConnectedPreferenceCategory() {
        ServiceState serviceState;
        List<NetworkRegistrationInfo> networkRegistrationInfoListForTransportType;
        int dataState = this.mTelephonyManager.getDataState();
        TelephonyManager telephonyManager = this.mTelephonyManager;
        if (dataState != 2 || (serviceState = telephonyManager.getServiceState()) == null || (networkRegistrationInfoListForTransportType = serviceState.getNetworkRegistrationInfoListForTransportType(1)) == null || networkRegistrationInfoListForTransportType.size() == 0) {
            return;
        }
        if (this.mForbiddenPlmns == null) {
            updateForbiddenPlmns();
        }
        for (NetworkRegistrationInfo networkRegistrationInfo : networkRegistrationInfoListForTransportType) {
            CellIdentity cellIdentity = networkRegistrationInfo.getCellIdentity();
            if (cellIdentity != null) {
                NetworkOperatorPreference networkOperatorPreference = new NetworkOperatorPreference(getPrefContext(), cellIdentity, this.mForbiddenPlmns, this.mShow4GForLTE);
                if (!networkOperatorPreference.isForbiddenNetwork()) {
                    networkOperatorPreference.setSummary(R.string.network_connected);
                    networkOperatorPreference.setIcon(4);
                    this.mPreferenceCategory.addPreference(networkOperatorPreference);
                    return;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearPreferenceSummary() {
        int preferenceCount = this.mPreferenceCategory.getPreferenceCount();
        while (preferenceCount > 0) {
            preferenceCount--;
            ((NetworkOperatorPreference) this.mPreferenceCategory.getPreference(preferenceCount)).setSummary((CharSequence) null);
        }
    }

    private long getNewRequestId() {
        return Math.max(this.mRequestIdManualNetworkSelect, this.mRequestIdManualNetworkScan) + 1;
    }

    private boolean isProgressBarVisible() {
        View view = this.mProgressHeader;
        return view != null && view.getVisibility() == 0;
    }

    protected void setProgressBarVisible(boolean z) {
        View view = this.mProgressHeader;
        if (view != null) {
            view.setVisibility(z ? 0 : 8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addMessagePreference(int i) {
        setProgressBarVisible(false);
        this.mStatusMessagePreference.setTitle(i);
        this.mPreferenceCategory.removeAll();
        this.mPreferenceCategory.addPreference(this.mStatusMessagePreference);
    }

    private void startNetworkQuery() {
        setProgressBarVisible(true);
        if (this.mNetworkScanHelper != null) {
            this.mRequestIdManualNetworkScan = getNewRequestId();
            this.mWaitingForNumberOfScanResults = 2L;
            this.mNetworkScanHelper.startNetworkScan(this.mUseNewApi ? 2 : 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopNetworkQuery() {
        setProgressBarVisible(false);
        NetworkScanHelper networkScanHelper = this.mNetworkScanHelper;
        if (networkScanHelper != null) {
            this.mWaitingForNumberOfScanResults = 0L;
            networkScanHelper.stopNetworkQuery();
        }
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        stopNetworkQuery();
        this.mNetworkScanExecutor.shutdown();
        super.onDestroy();
    }
}
