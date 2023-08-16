package com.android.settings.security.trustagent;

import android.content.Context;
import android.content.IntentFilter;
import android.os.UserHandle;
import androidx.preference.Preference;
import com.android.internal.widget.LockPatternUtils;
import com.android.settings.R;
import com.android.settings.core.BasePreferenceController;
import com.android.settings.overlay.FeatureFactory;
import com.android.settings.security.SecurityFeatureProvider;
/* loaded from: classes.dex */
public class ManageTrustAgentsPreferenceController extends BasePreferenceController {
    private static final int MY_USER_ID = UserHandle.myUserId();
    private final LockPatternUtils mLockPatternUtils;
    private TrustAgentManager mTrustAgentManager;

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

    public ManageTrustAgentsPreferenceController(Context context, String str) {
        super(context, str);
        SecurityFeatureProvider securityFeatureProvider = FeatureFactory.getFactory(context).getSecurityFeatureProvider();
        this.mLockPatternUtils = securityFeatureProvider.getLockPatternUtils(context);
        this.mTrustAgentManager = securityFeatureProvider.getTrustAgentManager();
    }

    @Override // com.android.settings.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return this.mContext.getResources().getBoolean(R.bool.config_show_manage_trust_agents) ? 0 : 3;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        int trustAgentCount = getTrustAgentCount();
        if (!this.mLockPatternUtils.isSecure(MY_USER_ID)) {
            preference.setEnabled(false);
            preference.setSummary(R.string.disabled_because_no_backup_security);
        } else if (trustAgentCount > 0) {
            preference.setEnabled(true);
            preference.setSummary(this.mContext.getResources().getQuantityString(R.plurals.manage_trust_agents_summary_on, trustAgentCount, Integer.valueOf(trustAgentCount)));
        } else {
            preference.setEnabled(true);
            preference.setSummary(R.string.manage_trust_agents_summary);
        }
    }

    private int getTrustAgentCount() {
        return this.mTrustAgentManager.getActiveTrustAgents(this.mContext, this.mLockPatternUtils, false).size();
    }
}
