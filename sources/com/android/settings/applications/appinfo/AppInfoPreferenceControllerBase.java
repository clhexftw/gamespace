package com.android.settings.applications.appinfo;

import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.applications.appinfo.AppInfoDashboardFragment;
import com.android.settings.core.BasePreferenceController;
import com.android.settingslib.applications.ApplicationsState;
/* loaded from: classes.dex */
public abstract class AppInfoPreferenceControllerBase extends BasePreferenceController implements AppInfoDashboardFragment.Callback {
    protected ApplicationsState.AppEntry mAppEntry;
    private final Class<? extends SettingsPreferenceFragment> mDetailFragmentClass;
    protected AppInfoDashboardFragment mParent;
    protected Preference mPreference;

    protected Bundle getArguments() {
        return null;
    }

    @Override // com.android.settings.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return 0;
    }

    public /* bridge */ /* synthetic */ Class getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    protected Class<? extends SettingsPreferenceFragment> getDetailFragmentClass() {
        return null;
    }

    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    public /* bridge */ /* synthetic */ int getSliceHighlightMenuRes() {
        return super.getSliceHighlightMenuRes();
    }

    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    public /* bridge */ /* synthetic */ boolean isPublicSlice() {
        return super.isPublicSlice();
    }

    public /* bridge */ /* synthetic */ boolean isSliceable() {
        return super.isSliceable();
    }

    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public AppInfoPreferenceControllerBase(Context context, String str) {
        super(context, str);
        this.mDetailFragmentClass = getDetailFragmentClass();
    }

    @Override // com.android.settings.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreference = preferenceScreen.findPreference(getPreferenceKey());
    }

    @Override // com.android.settings.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public boolean handlePreferenceTreeClick(Preference preference) {
        Class<? extends SettingsPreferenceFragment> cls;
        if (!TextUtils.equals(preference.getKey(), this.mPreferenceKey) || (cls = this.mDetailFragmentClass) == null) {
            return false;
        }
        Bundle arguments = getArguments();
        AppInfoDashboardFragment appInfoDashboardFragment = this.mParent;
        AppInfoDashboardFragment.startAppInfoFragment(cls, -1, arguments, appInfoDashboardFragment, appInfoDashboardFragment.getAppEntry());
        return true;
    }

    @Override // com.android.settings.applications.appinfo.AppInfoDashboardFragment.Callback
    public void refreshUi() {
        updateState(this.mPreference);
    }

    public void setParentFragment(AppInfoDashboardFragment appInfoDashboardFragment) {
        this.mParent = appInfoDashboardFragment;
        appInfoDashboardFragment.addToCallbackList(this);
        this.mAppEntry = this.mParent.getAppEntry();
    }
}
