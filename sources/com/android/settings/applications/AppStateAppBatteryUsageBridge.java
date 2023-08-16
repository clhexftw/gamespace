package com.android.settings.applications;

import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.util.Log;
import com.android.internal.annotations.VisibleForTesting;
import com.android.settings.applications.AppStateBaseBridge;
import com.android.settingslib.applications.ApplicationsState;
import com.android.settingslib.fuelgauge.PowerAllowlistBackend;
import java.util.function.Consumer;
/* loaded from: classes.dex */
public class AppStateAppBatteryUsageBridge extends AppStateBaseBridge {
    @VisibleForTesting
    static final int MODE_OPTIMIZED = 2;
    @VisibleForTesting
    static final int MODE_RESTRICTED = 3;
    @VisibleForTesting
    static final int MODE_UNKNOWN = 0;
    @VisibleForTesting
    static final int MODE_UNRESTRICTED = 1;
    private static final String TAG = "AppStateAppBatteryUsageBridge";
    @VisibleForTesting
    AppOpsManager mAppOpsManager;
    @VisibleForTesting
    Context mContext;
    @VisibleForTesting
    PowerAllowlistBackend mPowerAllowlistBackend;
    static final boolean DEBUG = Build.IS_DEBUGGABLE;
    public static final ApplicationsState.AppFilter FILTER_BATTERY_UNRESTRICTED_APPS = new ApplicationsState.AppFilter() { // from class: com.android.settings.applications.AppStateAppBatteryUsageBridge.1
        @Override // com.android.settingslib.applications.ApplicationsState.AppFilter
        public void init() {
        }

        @Override // com.android.settingslib.applications.ApplicationsState.AppFilter
        public boolean filterApp(ApplicationsState.AppEntry appEntry) {
            return AppStateAppBatteryUsageBridge.getAppBatteryUsageDetailsMode(appEntry) == 1;
        }
    };
    public static final ApplicationsState.AppFilter FILTER_BATTERY_OPTIMIZED_APPS = new ApplicationsState.AppFilter() { // from class: com.android.settings.applications.AppStateAppBatteryUsageBridge.2
        @Override // com.android.settingslib.applications.ApplicationsState.AppFilter
        public void init() {
        }

        @Override // com.android.settingslib.applications.ApplicationsState.AppFilter
        public boolean filterApp(ApplicationsState.AppEntry appEntry) {
            return AppStateAppBatteryUsageBridge.getAppBatteryUsageDetailsMode(appEntry) == 2;
        }
    };
    public static final ApplicationsState.AppFilter FILTER_BATTERY_RESTRICTED_APPS = new ApplicationsState.AppFilter() { // from class: com.android.settings.applications.AppStateAppBatteryUsageBridge.3
        @Override // com.android.settingslib.applications.ApplicationsState.AppFilter
        public void init() {
        }

        @Override // com.android.settingslib.applications.ApplicationsState.AppFilter
        public boolean filterApp(ApplicationsState.AppEntry appEntry) {
            return AppStateAppBatteryUsageBridge.getAppBatteryUsageDetailsMode(appEntry) == 3;
        }
    };

    public AppStateAppBatteryUsageBridge(Context context, ApplicationsState applicationsState, AppStateBaseBridge.Callback callback) {
        super(applicationsState, callback);
        this.mContext = context;
        this.mAppOpsManager = (AppOpsManager) context.getSystemService(AppOpsManager.class);
        this.mPowerAllowlistBackend = PowerAllowlistBackend.getInstance(this.mContext);
    }

    @Override // com.android.settings.applications.AppStateBaseBridge
    protected void updateExtraInfo(ApplicationsState.AppEntry appEntry, String str, int i) {
        appEntry.extraInfo = getAppBatteryUsageState(str, i);
    }

    @Override // com.android.settings.applications.AppStateBaseBridge
    protected void loadAllExtraInfo() {
        boolean z = DEBUG;
        if (z) {
            Log.d(TAG, "Start loadAllExtraInfo()");
        }
        this.mAppSession.getAllApps().stream().forEach(new Consumer() { // from class: com.android.settings.applications.AppStateAppBatteryUsageBridge$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                AppStateAppBatteryUsageBridge.this.lambda$loadAllExtraInfo$0((ApplicationsState.AppEntry) obj);
            }
        });
        if (z) {
            Log.d(TAG, "End loadAllExtraInfo()");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$loadAllExtraInfo$0(ApplicationsState.AppEntry appEntry) {
        ApplicationInfo applicationInfo = appEntry.info;
        updateExtraInfo(appEntry, applicationInfo.packageName, applicationInfo.uid);
    }

    protected Object getAppBatteryUsageState(String str, int i) {
        int i2;
        boolean isAllowlisted = this.mPowerAllowlistBackend.isAllowlisted(str);
        int checkOpNoThrow = this.mAppOpsManager.checkOpNoThrow(70, i, str);
        String str2 = "";
        if (checkOpNoThrow == 1 && !isAllowlisted) {
            i2 = 3;
            if (DEBUG) {
                str2 = "RESTRICTED";
            }
        } else if (checkOpNoThrow == 0) {
            int i3 = isAllowlisted ? 1 : 2;
            if (DEBUG) {
                str2 = isAllowlisted ? "UNRESTRICTED" : "OPTIMIZED";
            }
            i2 = i3;
        } else {
            i2 = 0;
        }
        if (DEBUG) {
            String str3 = TAG;
            Log.d(str3, "Pkg: " + str + ", mode: " + str2);
        }
        return new AppBatteryUsageDetails(i2);
    }

    @VisibleForTesting
    static int getAppBatteryUsageDetailsMode(ApplicationsState.AppEntry appEntry) {
        Object obj;
        if (appEntry == null || (obj = appEntry.extraInfo) == null || !(obj instanceof AppBatteryUsageDetails)) {
            return 0;
        }
        return ((AppBatteryUsageDetails) obj).mMode;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class AppBatteryUsageDetails {
        int mMode;

        AppBatteryUsageDetails(int i) {
            this.mMode = i;
        }
    }
}
