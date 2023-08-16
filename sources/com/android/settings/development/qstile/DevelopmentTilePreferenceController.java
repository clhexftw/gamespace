package com.android.settings.development.qstile;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.os.ServiceManager;
import android.os.SystemProperties;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.internal.statusbar.IStatusBarService;
import com.android.settings.core.BasePreferenceController;
import org.nameless.custom.preference.SwitchPreference;
/* loaded from: classes.dex */
public class DevelopmentTilePreferenceController extends BasePreferenceController {
    private static final String TAG = "DevTilePrefController";
    private final OnChangeHandler mOnChangeHandler;
    private final PackageManager mPackageManager;

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

    public DevelopmentTilePreferenceController(Context context, String str) {
        super(context, str);
        this.mOnChangeHandler = new OnChangeHandler(context);
        this.mPackageManager = context.getPackageManager();
    }

    @Override // com.android.settings.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        String string;
        super.displayPreference(preferenceScreen);
        Context context = preferenceScreen.getContext();
        for (ResolveInfo resolveInfo : this.mPackageManager.queryIntentServices(new Intent("android.service.quicksettings.action.QS_TILE").setPackage(context.getPackageName()), 640)) {
            ServiceInfo serviceInfo = resolveInfo.serviceInfo;
            Bundle bundle = serviceInfo.metaData;
            boolean z = false;
            if (bundle == null || (string = bundle.getString("com.android.settings.development.qstile.REQUIRES_SYSTEM_PROPERTY")) == null || SystemProperties.getBoolean(string, false)) {
                int componentEnabledSetting = this.mPackageManager.getComponentEnabledSetting(new ComponentName(serviceInfo.packageName, serviceInfo.name));
                if (componentEnabledSetting == 1 || (componentEnabledSetting == 0 && serviceInfo.enabled)) {
                    z = true;
                }
                SwitchPreference switchPreference = new SwitchPreference(context);
                switchPreference.setTitle(serviceInfo.loadLabel(this.mPackageManager));
                switchPreference.setIcon(serviceInfo.icon);
                switchPreference.setKey(serviceInfo.name);
                switchPreference.setChecked(z);
                switchPreference.setOnPreferenceChangeListener(this.mOnChangeHandler);
                preferenceScreen.addPreference(switchPreference);
            }
        }
    }

    /* loaded from: classes.dex */
    static class OnChangeHandler implements Preference.OnPreferenceChangeListener {
        private final Context mContext;
        private final PackageManager mPackageManager;
        private IStatusBarService mStatusBarService = IStatusBarService.Stub.asInterface(ServiceManager.checkService("statusbar"));

        public OnChangeHandler(Context context) {
            this.mContext = context;
            this.mPackageManager = context.getPackageManager();
        }

        @Override // androidx.preference.Preference.OnPreferenceChangeListener
        public boolean onPreferenceChange(Preference preference, Object obj) {
            this.mPackageManager.setComponentEnabledSetting(new ComponentName(this.mContext.getPackageName(), preference.getKey()), ((Boolean) obj).booleanValue() ? 1 : 2, 1);
            return true;
        }
    }
}
