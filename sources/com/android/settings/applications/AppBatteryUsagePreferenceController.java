package com.android.settings.applications;

import android.content.Context;
import android.content.IntentFilter;
import androidx.lifecycle.LifecycleObserver;
import com.android.settings.R;
import com.android.settings.core.BasePreferenceController;
/* loaded from: classes.dex */
public final class AppBatteryUsagePreferenceController extends BasePreferenceController implements LifecycleObserver {
    private static final String TAG = "AppBatteryUsagePreferenceController";
    private boolean mEnableAppBatteryUsagePage;

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

    public AppBatteryUsagePreferenceController(Context context, String str) {
        super(context, str);
        this.mEnableAppBatteryUsagePage = this.mContext.getResources().getBoolean(R.bool.config_app_battery_usage_list_enabled);
    }

    @Override // com.android.settings.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return this.mEnableAppBatteryUsagePage ? 0 : 2;
    }
}
