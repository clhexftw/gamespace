package com.android.settings.security;

import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.CrossProfileApps;
import com.android.settings.R;
import com.android.settings.core.BasePreferenceController;
import java.util.function.Supplier;
/* loaded from: classes.dex */
public class SecurityAdvancedSettingsController extends BasePreferenceController {
    private final CrossProfileApps mCrossProfileApps;
    private final DevicePolicyManager mDevicePolicyManager;

    @Override // com.android.settings.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return 0;
    }

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

    public SecurityAdvancedSettingsController(Context context, String str) {
        super(context, str);
        this.mCrossProfileApps = (CrossProfileApps) context.getSystemService(CrossProfileApps.class);
        this.mDevicePolicyManager = (DevicePolicyManager) context.getSystemService(DevicePolicyManager.class);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public CharSequence getSummary() {
        if (isWorkProfilePresent()) {
            return this.mDevicePolicyManager.getResources().getString("Settings.MORE_SECURITY_SETTINGS_WORK_PROFILE_SUMMARY", new Supplier() { // from class: com.android.settings.security.SecurityAdvancedSettingsController$$ExternalSyntheticLambda0
                @Override // java.util.function.Supplier
                public final Object get() {
                    String lambda$getSummary$0;
                    lambda$getSummary$0 = SecurityAdvancedSettingsController.this.lambda$getSummary$0();
                    return lambda$getSummary$0;
                }
            });
        }
        return this.mContext.getResources().getString(R.string.security_advanced_settings_no_work_profile_settings_summary);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ String lambda$getSummary$0() {
        return this.mContext.getResources().getString(R.string.security_advanced_settings_work_profile_settings_summary);
    }

    private boolean isWorkProfilePresent() {
        return !this.mCrossProfileApps.getTargetUserProfiles().isEmpty();
    }
}
