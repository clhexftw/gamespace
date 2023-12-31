package com.android.settings.applications.appinfo;

import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import com.android.settings.R;
import com.android.settings.applications.AppInfoBase;
import com.android.settings.applications.AppInfoWithHeader;
import com.android.settings.applications.AppStateAppOpsBridge;
import com.android.settings.applications.AppStateMediaManagementAppsBridge;
import com.android.settingslib.applications.ApplicationsState;
import com.android.settingslib.core.instrumentation.MetricsFeatureProvider;
import org.nameless.custom.preference.SwitchPreference;
/* loaded from: classes.dex */
public class MediaManagementAppsDetails extends AppInfoWithHeader implements Preference.OnPreferenceChangeListener {
    private AppStateMediaManagementAppsBridge mAppBridge;
    private AppOpsManager mAppOpsManager;
    private AppStateAppOpsBridge.PermissionState mPermissionState;
    private SwitchPreference mSwitchPref;

    @Override // com.android.settings.applications.AppInfoBase
    protected AlertDialog createDialog(int i, int i2) {
        return null;
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1874;
    }

    @Override // com.android.settings.applications.AppInfoBase, com.android.settings.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        FragmentActivity activity = getActivity();
        this.mAppBridge = new AppStateMediaManagementAppsBridge(activity, ((AppInfoBase) this).mState, null);
        this.mAppOpsManager = (AppOpsManager) activity.getSystemService(AppOpsManager.class);
        addPreferencesFromResource(R.xml.media_management_apps);
        SwitchPreference switchPreference = (SwitchPreference) findPreference("media_management_apps_toggle");
        this.mSwitchPref = switchPreference;
        switchPreference.setOnPreferenceChangeListener(this);
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        boolean booleanValue = ((Boolean) obj).booleanValue();
        if (preference == this.mSwitchPref) {
            AppStateAppOpsBridge.PermissionState permissionState = this.mPermissionState;
            if (permissionState == null || booleanValue == permissionState.isPermissible()) {
                return true;
            }
            setCanManageMedia(booleanValue);
            logPermissionChange(booleanValue, this.mPackageName);
            refreshUi();
            return true;
        }
        return false;
    }

    private void setCanManageMedia(boolean z) {
        this.mAppOpsManager.setUidMode(110, this.mPackageInfo.applicationInfo.uid, z ? 0 : 2);
    }

    private void logPermissionChange(boolean z, String str) {
        MetricsFeatureProvider metricsFeatureProvider = this.mMetricsFeatureProvider;
        metricsFeatureProvider.action(metricsFeatureProvider.getAttribution(getActivity()), 1762, getMetricsCategory(), str, z ? 1 : 0);
    }

    @Override // com.android.settings.applications.AppInfoBase
    protected boolean refreshUi() {
        ApplicationInfo applicationInfo;
        PackageInfo packageInfo = this.mPackageInfo;
        if (packageInfo == null || (applicationInfo = packageInfo.applicationInfo) == null) {
            return false;
        }
        AppStateAppOpsBridge.PermissionState createPermissionState = this.mAppBridge.createPermissionState(this.mPackageName, applicationInfo.uid);
        this.mPermissionState = createPermissionState;
        this.mSwitchPref.setEnabled(createPermissionState.permissionDeclared);
        this.mSwitchPref.setChecked(this.mPermissionState.isPermissible());
        return true;
    }

    public static int getSummary(Context context, ApplicationsState.AppEntry appEntry) {
        AppStateAppOpsBridge.PermissionState createPermissionState;
        Object obj = appEntry.extraInfo;
        if (obj instanceof AppStateAppOpsBridge.PermissionState) {
            createPermissionState = (AppStateAppOpsBridge.PermissionState) obj;
        } else {
            AppStateMediaManagementAppsBridge appStateMediaManagementAppsBridge = new AppStateMediaManagementAppsBridge(context, null, null);
            ApplicationInfo applicationInfo = appEntry.info;
            createPermissionState = appStateMediaManagementAppsBridge.createPermissionState(applicationInfo.packageName, applicationInfo.uid);
        }
        return createPermissionState.isPermissible() ? R.string.app_permission_summary_allowed : R.string.app_permission_summary_not_allowed;
    }
}
