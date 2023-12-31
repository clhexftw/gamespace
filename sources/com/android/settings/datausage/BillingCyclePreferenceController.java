package com.android.settings.datausage;

import android.content.Context;
import android.content.IntentFilter;
import android.net.NetworkPolicyManager;
import android.os.INetworkManagementService;
import android.os.ServiceManager;
import android.os.UserManager;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import androidx.preference.PreferenceScreen;
import com.android.settings.core.BasePreferenceController;
import com.android.settings.datausage.TemplatePreference;
import com.android.settings.datausage.lib.DataUsageLib;
import com.android.settingslib.NetworkPolicyEditor;
/* loaded from: classes.dex */
public class BillingCyclePreferenceController extends BasePreferenceController {
    private int mSubscriptionId;

    @Override // com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ Class getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ int getSliceHighlightMenuRes() {
        return super.getSliceHighlightMenuRes();
    }

    @Override // com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isPublicSlice() {
        return super.isPublicSlice();
    }

    @Override // com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isSliceable() {
        return super.isSliceable();
    }

    @Override // com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public BillingCyclePreferenceController(Context context, String str) {
        super(context, str);
    }

    public void init(int i) {
        this.mSubscriptionId = i;
    }

    @Override // com.android.settings.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        TemplatePreference.NetworkServices networkServices = new TemplatePreference.NetworkServices();
        networkServices.mNetworkService = INetworkManagementService.Stub.asInterface(ServiceManager.getService("network_management"));
        NetworkPolicyManager networkPolicyManager = (NetworkPolicyManager) this.mContext.getSystemService(NetworkPolicyManager.class);
        networkServices.mPolicyManager = networkPolicyManager;
        networkServices.mPolicyEditor = new NetworkPolicyEditor(networkPolicyManager);
        networkServices.mTelephonyManager = (TelephonyManager) this.mContext.getSystemService(TelephonyManager.class);
        networkServices.mSubscriptionManager = (SubscriptionManager) this.mContext.getSystemService(SubscriptionManager.class);
        networkServices.mUserManager = (UserManager) this.mContext.getSystemService(UserManager.class);
        ((BillingCyclePreference) preferenceScreen.findPreference(getPreferenceKey())).setTemplate(DataUsageLib.getMobileTemplate(this.mContext, this.mSubscriptionId), this.mSubscriptionId, networkServices);
    }

    @Override // com.android.settings.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return DataUsageUtils.hasMobileData(this.mContext) ? 0 : 2;
    }
}
