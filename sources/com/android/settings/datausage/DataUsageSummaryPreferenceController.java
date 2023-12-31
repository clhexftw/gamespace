package com.android.settings.datausage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.INetworkPolicyManager;
import android.net.NetworkPolicyManager;
import android.net.NetworkTemplate;
import android.os.ServiceManager;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionPlan;
import android.text.TextUtils;
import android.util.Log;
import android.util.RecurrenceRule;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import com.android.internal.util.CollectionUtils;
import com.android.settings.R;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settings.datausage.lib.DataUsageLib;
import com.android.settings.network.ProxySubscriptionManager;
import com.android.settings.network.telephony.TelephonyBasePreferenceController;
import com.android.settings.widget.EntityHeaderController;
import com.android.settingslib.NetworkPolicyEditor;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.net.DataUsageController;
import com.android.settingslib.utils.ThreadUtils;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
/* loaded from: classes.dex */
public class DataUsageSummaryPreferenceController extends TelephonyBasePreferenceController implements PreferenceControllerMixin, LifecycleObserver, OnStart {
    private static final String KEY = "status_header";
    private static final long PETA = 1000000000000000L;
    private static final float RELATIVE_SIZE_LARGE = 1.5625f;
    private static final float RELATIVE_SIZE_SMALL = 0.64f;
    private static final String TAG = "DataUsageController";
    private CharSequence mCarrierName;
    private long mCycleEnd;
    private long mCycleStart;
    private long mDataBarSize;
    protected DataUsageInfoController mDataInfoController;
    protected DataUsageController mDataUsageController;
    private int mDataUsageTemplate;
    private int mDataplanCount;
    private long mDataplanSize;
    private long mDataplanUse;
    private NetworkTemplate mDefaultTemplate;
    private EntityHeaderController mEntityHeaderController;
    private final PreferenceFragmentCompat mFragment;
    private boolean mHasMobileData;
    private Future<Long> mHistoricalUsageLevel;
    private final Lifecycle mLifecycle;
    private Intent mManageSubscriptionIntent;
    protected NetworkPolicyEditor mPolicyEditor;
    private long mSnapshotTime;

    public static boolean unlimited(long j) {
        return j == Long.MAX_VALUE;
    }

    private static boolean validSize(long j) {
        return j >= 0 && j < PETA;
    }

    @Override // com.android.settings.network.telephony.TelephonyBasePreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ Class getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.settings.network.telephony.TelephonyBasePreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.settings.network.telephony.TelephonyBasePreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ int getSliceHighlightMenuRes() {
        return super.getSliceHighlightMenuRes();
    }

    @Override // com.android.settings.network.telephony.TelephonyBasePreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.settings.network.telephony.TelephonyBasePreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isPublicSlice() {
        return super.isPublicSlice();
    }

    @Override // com.android.settings.network.telephony.TelephonyBasePreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isSliceable() {
        return super.isSliceable();
    }

    @Override // com.android.settings.network.telephony.TelephonyBasePreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public DataUsageSummaryPreferenceController(Activity activity, Lifecycle lifecycle, PreferenceFragmentCompat preferenceFragmentCompat, int i) {
        super(activity, KEY);
        this.mLifecycle = lifecycle;
        this.mFragment = preferenceFragmentCompat;
        init(i);
    }

    public void init(int i) {
        this.mSubId = i;
        this.mHasMobileData = DataUsageUtils.hasMobileData(this.mContext);
        this.mDataUsageController = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void updateConfiguration(Context context, int i, SubscriptionInfo subscriptionInfo) {
        this.mPolicyEditor = new NetworkPolicyEditor((NetworkPolicyManager) context.getSystemService(NetworkPolicyManager.class));
        DataUsageController createDataUsageController = createDataUsageController(context);
        this.mDataUsageController = createDataUsageController;
        createDataUsageController.setSubscriptionId(i);
        this.mDataInfoController = new DataUsageInfoController();
        if (subscriptionInfo != null) {
            this.mDataUsageTemplate = R.string.cell_data_template;
            this.mDefaultTemplate = DataUsageLib.getMobileTemplate(context, i);
        } else if (DataUsageUtils.hasWifiRadio(context)) {
            this.mDataUsageTemplate = R.string.wifi_data_template;
            this.mDefaultTemplate = new NetworkTemplate.Builder(4).build();
        } else {
            this.mDataUsageTemplate = R.string.ethernet_data_template;
            this.mDefaultTemplate = DataUsageUtils.getDefaultTemplate(context, i);
        }
    }

    DataUsageController createDataUsageController(Context context) {
        return new DataUsageController(context);
    }

    DataUsageSummaryPreferenceController(DataUsageController dataUsageController, DataUsageInfoController dataUsageInfoController, NetworkTemplate networkTemplate, NetworkPolicyEditor networkPolicyEditor, int i, Activity activity, Lifecycle lifecycle, EntityHeaderController entityHeaderController, PreferenceFragmentCompat preferenceFragmentCompat, int i2) {
        super(activity, KEY);
        this.mDataUsageController = dataUsageController;
        this.mDataInfoController = dataUsageInfoController;
        this.mDefaultTemplate = networkTemplate;
        this.mPolicyEditor = networkPolicyEditor;
        this.mDataUsageTemplate = i;
        this.mHasMobileData = true;
        this.mLifecycle = lifecycle;
        this.mEntityHeaderController = entityHeaderController;
        this.mFragment = preferenceFragmentCompat;
        this.mSubId = i2;
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStart
    public void onStart() {
        if (this.mEntityHeaderController == null) {
            this.mEntityHeaderController = EntityHeaderController.newInstance((Activity) this.mContext, this.mFragment, null);
        }
        this.mEntityHeaderController.setRecyclerView(this.mFragment.getListView(), this.mLifecycle);
    }

    List<SubscriptionPlan> getSubscriptionPlans(int i) {
        return ProxySubscriptionManager.getInstance(this.mContext).get().getSubscriptionPlans(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SubscriptionInfo getSubscriptionInfo(int i) {
        if (this.mHasMobileData) {
            return ProxySubscriptionManager.getInstance(this.mContext).getAccessibleSubscriptionInfo(i);
        }
        return null;
    }

    @Override // com.android.settings.network.telephony.TelephonyBasePreferenceController, com.android.settings.network.telephony.TelephonyAvailabilityCallback
    public int getAvailabilityStatus(int i) {
        return (getSubscriptionInfo(i) != null || DataUsageUtils.hasWifiRadio(this.mContext)) ? 0 : 2;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        DataUsageSummaryPreference dataUsageSummaryPreference = (DataUsageSummaryPreference) preference;
        SubscriptionInfo subscriptionInfo = getSubscriptionInfo(this.mSubId);
        if (this.mDataUsageController == null) {
            updateConfiguration(this.mContext, this.mSubId, subscriptionInfo);
        }
        this.mHistoricalUsageLevel = ThreadUtils.postOnBackgroundThread(new Callable() { // from class: com.android.settings.datausage.DataUsageSummaryPreferenceController$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                Object lambda$updateState$0;
                lambda$updateState$0 = DataUsageSummaryPreferenceController.this.lambda$updateState$0();
                return lambda$updateState$0;
            }
        });
        DataUsageController.DataUsageInfo dataUsageInfo = this.mDataUsageController.getDataUsageInfo(this.mDefaultTemplate);
        long j = dataUsageInfo.usageLevel;
        if (subscriptionInfo != null) {
            this.mDataInfoController.updateDataLimit(dataUsageInfo, this.mPolicyEditor.getPolicy(this.mDefaultTemplate));
            dataUsageSummaryPreference.setWifiMode(false, null, false);
            refreshDataplanInfo(dataUsageInfo, subscriptionInfo);
            long j2 = dataUsageInfo.warningLevel;
            if (j2 > 0 && dataUsageInfo.limitLevel > 0) {
                dataUsageSummaryPreference.setLimitInfo(TextUtils.expandTemplate(this.mContext.getText(R.string.cell_data_warning_and_limit), DataUsageUtils.formatDataUsage(this.mContext, dataUsageInfo.warningLevel), DataUsageUtils.formatDataUsage(this.mContext, dataUsageInfo.limitLevel)));
            } else if (j2 > 0) {
                dataUsageSummaryPreference.setLimitInfo(TextUtils.expandTemplate(this.mContext.getText(R.string.cell_data_warning), DataUsageUtils.formatDataUsage(this.mContext, dataUsageInfo.warningLevel)));
            } else if (dataUsageInfo.limitLevel > 0) {
                dataUsageSummaryPreference.setLimitInfo(TextUtils.expandTemplate(this.mContext.getText(R.string.cell_data_limit), DataUsageUtils.formatDataUsage(this.mContext, dataUsageInfo.limitLevel)));
            } else {
                dataUsageSummaryPreference.setLimitInfo(null);
            }
            if (this.mDataplanUse <= 0 && this.mSnapshotTime < 0) {
                Log.d(TAG, "Display data usage from history");
                this.mDataplanUse = displayUsageLevel(j);
                this.mSnapshotTime = -1L;
            }
            dataUsageSummaryPreference.setUsageNumbers(this.mDataplanUse, this.mDataplanSize, this.mHasMobileData);
            if (this.mDataBarSize <= 0) {
                dataUsageSummaryPreference.setChartEnabled(false);
            } else {
                dataUsageSummaryPreference.setChartEnabled(true);
                dataUsageSummaryPreference.setLabels(DataUsageUtils.formatDataUsage(this.mContext, 0L), DataUsageUtils.formatDataUsage(this.mContext, this.mDataBarSize));
                dataUsageSummaryPreference.setProgress(((float) this.mDataplanUse) / ((float) this.mDataBarSize));
            }
            dataUsageSummaryPreference.setUsageInfo(this.mCycleEnd, this.mSnapshotTime, this.mCarrierName, this.mDataplanCount, this.mManageSubscriptionIntent);
            return;
        }
        dataUsageSummaryPreference.setWifiMode(true, dataUsageInfo.period, false);
        dataUsageSummaryPreference.setLimitInfo(null);
        dataUsageSummaryPreference.setUsageNumbers(displayUsageLevel(j), -1L, true);
        dataUsageSummaryPreference.setChartEnabled(false);
        dataUsageSummaryPreference.setUsageInfo(dataUsageInfo.cycleEnd, -1L, null, 0, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object lambda$updateState$0() throws Exception {
        return Long.valueOf(this.mDataUsageController.getHistoricalUsageLevel(this.mDefaultTemplate));
    }

    private long displayUsageLevel(long j) {
        if (j > 0) {
            return j;
        }
        try {
            return this.mHistoricalUsageLevel.get().longValue();
        } catch (Exception unused) {
            return j;
        }
    }

    private void refreshDataplanInfo(DataUsageController.DataUsageInfo dataUsageInfo, SubscriptionInfo subscriptionInfo) {
        ZonedDateTime zonedDateTime;
        this.mCarrierName = null;
        this.mDataplanCount = 0;
        this.mDataplanSize = -1L;
        this.mDataBarSize = this.mDataInfoController.getSummaryLimit(dataUsageInfo);
        this.mDataplanUse = dataUsageInfo.usageLevel;
        this.mCycleStart = dataUsageInfo.cycleStart;
        this.mCycleEnd = dataUsageInfo.cycleEnd;
        this.mSnapshotTime = -1L;
        if (subscriptionInfo != null && this.mHasMobileData) {
            this.mCarrierName = subscriptionInfo.getCarrierName();
            List<SubscriptionPlan> subscriptionPlans = getSubscriptionPlans(this.mSubId);
            SubscriptionPlan primaryPlan = getPrimaryPlan(subscriptionPlans);
            if (primaryPlan != null) {
                this.mDataplanCount = subscriptionPlans.size();
                long dataLimitBytes = primaryPlan.getDataLimitBytes();
                this.mDataplanSize = dataLimitBytes;
                if (unlimited(dataLimitBytes)) {
                    this.mDataplanSize = -1L;
                }
                this.mDataBarSize = this.mDataplanSize;
                this.mDataplanUse = primaryPlan.getDataUsageBytes();
                RecurrenceRule cycleRule = primaryPlan.getCycleRule();
                if (cycleRule != null && (zonedDateTime = cycleRule.start) != null && cycleRule.end != null) {
                    this.mCycleStart = zonedDateTime.toEpochSecond() * 1000;
                    this.mCycleEnd = cycleRule.end.toEpochSecond() * 1000;
                }
                this.mSnapshotTime = primaryPlan.getDataUsageTime();
            }
        }
        this.mManageSubscriptionIntent = null;
        Log.i(TAG, "Have " + this.mDataplanCount + " plans, dflt sub-id " + this.mSubId + ", intent " + this.mManageSubscriptionIntent);
    }

    Intent createManageSubscriptionIntent(int i) {
        String str;
        try {
            str = INetworkPolicyManager.Stub.asInterface(ServiceManager.getService("netpolicy")).getSubscriptionPlansOwner(i);
        } catch (Exception e) {
            Log.w(TAG, "Fail to get subscription plan owner for subId " + i, e);
            str = "";
        }
        if (TextUtils.isEmpty(str) || getSubscriptionPlans(i).isEmpty()) {
            return null;
        }
        Intent intent = new Intent("android.telephony.action.MANAGE_SUBSCRIPTION_PLANS");
        intent.setPackage(str);
        intent.putExtra("android.telephony.extra.SUBSCRIPTION_INDEX", i);
        if (this.mContext.getPackageManager().queryIntentActivities(intent, 65536).isEmpty()) {
            return null;
        }
        return intent;
    }

    private static SubscriptionPlan getPrimaryPlan(List<SubscriptionPlan> list) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        SubscriptionPlan subscriptionPlan = list.get(0);
        if (subscriptionPlan.getDataLimitBytes() <= 0 || !validSize(subscriptionPlan.getDataUsageBytes()) || subscriptionPlan.getCycleRule() == null) {
            return null;
        }
        return subscriptionPlan;
    }
}
