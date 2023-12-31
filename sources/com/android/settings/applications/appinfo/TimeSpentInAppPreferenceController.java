package com.android.settings.applications.appinfo;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.applications.ApplicationFeatureProvider;
import com.android.settings.core.LiveDataController;
import com.android.settings.overlay.FeatureFactory;
import com.android.settingslib.applications.AppUtils;
import com.android.settingslib.applications.ApplicationsState;
import java.util.List;
/* loaded from: classes.dex */
public class TimeSpentInAppPreferenceController extends LiveDataController {
    static final Intent SEE_TIME_IN_APP_TEMPLATE = new Intent("android.settings.action.APP_USAGE_SETTINGS");
    protected ApplicationsState.AppEntry mAppEntry;
    private final ApplicationFeatureProvider mAppFeatureProvider;
    private Intent mIntent;
    private final PackageManager mPackageManager;
    private String mPackageName;
    protected AppInfoDashboardFragment mParent;

    @Override // com.android.settings.core.LiveDataController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ Class getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.settings.core.LiveDataController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.settings.core.LiveDataController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ int getSliceHighlightMenuRes() {
        return super.getSliceHighlightMenuRes();
    }

    @Override // com.android.settings.core.LiveDataController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.settings.core.LiveDataController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isPublicSlice() {
        return super.isPublicSlice();
    }

    @Override // com.android.settings.core.LiveDataController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isSliceable() {
        return super.isSliceable();
    }

    @Override // com.android.settings.core.LiveDataController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public TimeSpentInAppPreferenceController(Context context, String str) {
        super(context, str);
        this.mPackageManager = context.getPackageManager();
        this.mAppFeatureProvider = FeatureFactory.getFactory(context).getApplicationFeatureProvider(context);
    }

    public void setPackageName(String str) {
        this.mPackageName = str;
        this.mIntent = new Intent(SEE_TIME_IN_APP_TEMPLATE).putExtra("android.intent.extra.PACKAGE_NAME", this.mPackageName);
    }

    public void setParentFragment(AppInfoDashboardFragment appInfoDashboardFragment) {
        this.mParent = appInfoDashboardFragment;
        this.mAppEntry = appInfoDashboardFragment.getAppEntry();
    }

    @Override // com.android.settings.core.BasePreferenceController
    public int getAvailabilityStatus() {
        List<ResolveInfo> queryIntentActivities;
        if (!TextUtils.isEmpty(this.mPackageName) && (queryIntentActivities = this.mPackageManager.queryIntentActivities(this.mIntent, 0)) != null && !queryIntentActivities.isEmpty()) {
            for (ResolveInfo resolveInfo : queryIntentActivities) {
                if (isSystemApp(resolveInfo)) {
                    return 0;
                }
            }
        }
        return 3;
    }

    @Override // com.android.settings.core.LiveDataController, com.android.settings.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        Preference findPreference = preferenceScreen.findPreference(getPreferenceKey());
        if (findPreference != null) {
            findPreference.setIntent(this.mIntent);
        }
        findPreference.setEnabled(AppUtils.isAppInstalled(this.mAppEntry));
    }

    @Override // com.android.settings.core.LiveDataController
    protected CharSequence getSummaryTextInBackground() {
        return this.mAppFeatureProvider.getTimeSpentInApp(this.mPackageName);
    }

    private boolean isSystemApp(ResolveInfo resolveInfo) {
        ActivityInfo activityInfo;
        ApplicationInfo applicationInfo;
        return (resolveInfo == null || (activityInfo = resolveInfo.activityInfo) == null || (applicationInfo = activityInfo.applicationInfo) == null || (applicationInfo.flags & 1) == 0) ? false : true;
    }
}
