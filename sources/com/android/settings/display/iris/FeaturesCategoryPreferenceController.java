package com.android.settings.display.iris;

import android.app.ActivityManager;
import android.content.Context;
import android.content.IntentFilter;
import com.android.internal.util.nameless.CustomUtils;
import com.android.settings.core.BasePreferenceController;
/* loaded from: classes.dex */
public class FeaturesCategoryPreferenceController extends BasePreferenceController {
    private static final String IRIS_PACKAGE_NAME = "org.nameless.iris";
    private static final String IRIS_SERVICE_CLASS_NAME = "org.nameless.iris.service.IrisService";
    private final ActivityManager mActivityManager;
    private final Context mContext;

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

    public FeaturesCategoryPreferenceController(Context context, String str) {
        super(context, str);
        this.mContext = context;
        this.mActivityManager = (ActivityManager) context.getSystemService(ActivityManager.class);
    }

    @Override // com.android.settings.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return (CustomUtils.isPackageInstalled(this.mContext, IRIS_PACKAGE_NAME) && isServiceRunning() && supportAnyFeature()) ? 0 : 3;
    }

    private boolean isServiceRunning() {
        for (ActivityManager.RunningServiceInfo runningServiceInfo : this.mActivityManager.getRunningServices(Integer.MAX_VALUE)) {
            if (IRIS_SERVICE_CLASS_NAME.equals(runningServiceInfo.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    private boolean supportAnyFeature() {
        return FeaturesHolder.MEMC_FHD_SUPPORTED || FeaturesHolder.MEMC_QHD_SUPPORTED || FeaturesHolder.SDR2HDR_SUPPORTED;
    }
}
