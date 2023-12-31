package com.android.settings.applications.specialaccess.vrlistener;

import android.app.ActivityManager;
import android.content.Context;
import android.content.IntentFilter;
import com.android.settings.R;
import com.android.settings.core.BasePreferenceController;
/* loaded from: classes.dex */
public class VrListenerScreenPreferenceController extends BasePreferenceController {
    private final ActivityManager mActivityManager;

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

    public VrListenerScreenPreferenceController(Context context, String str) {
        super(context, str);
        this.mActivityManager = (ActivityManager) this.mContext.getSystemService(ActivityManager.class);
    }

    @Override // com.android.settings.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return (!this.mContext.getResources().getBoolean(R.bool.config_show_enabled_vr_listeners) || this.mActivityManager.isLowRamDevice()) ? 3 : 0;
    }
}
