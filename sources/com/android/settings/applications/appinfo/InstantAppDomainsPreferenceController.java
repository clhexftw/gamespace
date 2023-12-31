package com.android.settings.applications.appinfo;

import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.util.ArraySet;
import androidx.preference.Preference;
import com.android.settings.Utils;
import com.android.settings.applications.AppDomainsPreference;
import com.android.settingslib.applications.AppUtils;
/* loaded from: classes.dex */
public class InstantAppDomainsPreferenceController extends AppInfoPreferenceControllerBase {
    private PackageManager mPackageManager;

    @Override // com.android.settings.applications.appinfo.AppInfoPreferenceControllerBase, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ Class getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.settings.applications.appinfo.AppInfoPreferenceControllerBase, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.settings.applications.appinfo.AppInfoPreferenceControllerBase, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ int getSliceHighlightMenuRes() {
        return super.getSliceHighlightMenuRes();
    }

    @Override // com.android.settings.applications.appinfo.AppInfoPreferenceControllerBase, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.settings.applications.appinfo.AppInfoPreferenceControllerBase, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isPublicSlice() {
        return super.isPublicSlice();
    }

    @Override // com.android.settings.applications.appinfo.AppInfoPreferenceControllerBase, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isSliceable() {
        return super.isSliceable();
    }

    @Override // com.android.settings.applications.appinfo.AppInfoPreferenceControllerBase, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public InstantAppDomainsPreferenceController(Context context, String str) {
        super(context, str);
        this.mPackageManager = this.mContext.getPackageManager();
    }

    @Override // com.android.settings.applications.appinfo.AppInfoPreferenceControllerBase, com.android.settings.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return (this.mParent.getPackageInfo() == null || !AppUtils.isInstant(this.mParent.getPackageInfo().applicationInfo)) ? 4 : 0;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        AppDomainsPreference appDomainsPreference = (AppDomainsPreference) preference;
        ArraySet<String> handledDomains = Utils.getHandledDomains(this.mPackageManager, this.mParent.getPackageInfo().packageName);
        String[] strArr = (String[]) handledDomains.toArray(new String[handledDomains.size()]);
        appDomainsPreference.setTitles(strArr);
        appDomainsPreference.setValues(new int[strArr.length]);
    }
}
