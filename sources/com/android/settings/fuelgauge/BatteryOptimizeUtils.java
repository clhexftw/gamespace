package com.android.settings.fuelgauge;

import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageManager;
import android.content.pm.ParceledListSlice;
import android.content.pm.UserInfo;
import android.os.UserHandle;
import android.os.UserManager;
import android.util.ArraySet;
import android.util.Log;
import com.android.settingslib.fuelgauge.PowerAllowlistBackend;
import java.util.Iterator;
import java.util.function.Predicate;
/* loaded from: classes.dex */
public class BatteryOptimizeUtils {
    boolean mAllowListed;
    AppOpsManager mAppOpsManager;
    BatteryUtils mBatteryUtils;
    int mMode;
    private final String mPackageName;
    PowerAllowlistBackend mPowerAllowListBackend;
    private final int mUid;

    public static int getAppOptimizationMode(int i, boolean z) {
        if (z || i != 1) {
            if (z && i == 0) {
                return 2;
            }
            return (z || i != 0) ? 0 : 3;
        }
        return 1;
    }

    public BatteryOptimizeUtils(Context context, int i, String str) {
        this.mUid = i;
        this.mPackageName = str;
        this.mAppOpsManager = (AppOpsManager) context.getSystemService(AppOpsManager.class);
        this.mBatteryUtils = BatteryUtils.getInstance(context);
        this.mPowerAllowListBackend = PowerAllowlistBackend.getInstance(context);
        this.mMode = this.mAppOpsManager.checkOpNoThrow(70, i, str);
        this.mAllowListed = this.mPowerAllowListBackend.isAllowlisted(str);
    }

    public int getAppOptimizationMode() {
        refreshState();
        return getAppOptimizationMode(this.mMode, this.mAllowListed);
    }

    public static void resetAppOptimizationMode(Context context, IPackageManager iPackageManager, AppOpsManager appOpsManager) {
        resetAppOptimizationMode(context, iPackageManager, appOpsManager, PowerAllowlistBackend.getInstance(context), BatteryUtils.getInstance(context));
    }

    public void setAppUsageState(int i) {
        if (getAppOptimizationMode(this.mMode, this.mAllowListed) == i) {
            Log.w("BatteryOptimizeUtils", "set the same optimization mode for: " + this.mPackageName);
            return;
        }
        setAppUsageStateInternal(i, this.mUid, this.mPackageName, this.mBatteryUtils, this.mPowerAllowListBackend);
    }

    public boolean isValidPackageName() {
        return this.mBatteryUtils.getPackageUid(this.mPackageName) != -1;
    }

    public boolean isSystemOrDefaultApp() {
        this.mPowerAllowListBackend.refreshList();
        return isSystemOrDefaultApp(this.mPowerAllowListBackend, this.mPackageName);
    }

    public static ArraySet<ApplicationInfo> getInstalledApplications(Context context, IPackageManager iPackageManager) {
        ArraySet<ApplicationInfo> arraySet = new ArraySet<>();
        for (UserInfo userInfo : ((UserManager) context.getSystemService(UserManager.class)).getProfiles(UserHandle.myUserId())) {
            try {
                ParceledListSlice installedApplications = iPackageManager.getInstalledApplications(userInfo.isAdmin() ? 4227584L : 33280L, userInfo.id);
                if (installedApplications != null) {
                    arraySet.addAll(installedApplications.getList());
                }
            } catch (Exception e) {
                Log.e("BatteryOptimizeUtils", "getInstalledApplications() is failed", e);
                return null;
            }
        }
        arraySet.removeIf(new Predicate() { // from class: com.android.settings.fuelgauge.BatteryOptimizeUtils$$ExternalSyntheticLambda0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean lambda$getInstalledApplications$0;
                lambda$getInstalledApplications$0 = BatteryOptimizeUtils.lambda$getInstalledApplications$0((ApplicationInfo) obj);
                return lambda$getInstalledApplications$0;
            }
        });
        return arraySet;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$getInstalledApplications$0(ApplicationInfo applicationInfo) {
        return (applicationInfo.enabledSetting == 3 || applicationInfo.enabled) ? false : true;
    }

    static void resetAppOptimizationMode(Context context, IPackageManager iPackageManager, AppOpsManager appOpsManager, PowerAllowlistBackend powerAllowlistBackend, BatteryUtils batteryUtils) {
        ArraySet<ApplicationInfo> installedApplications = getInstalledApplications(context, iPackageManager);
        if (installedApplications == null || installedApplications.isEmpty()) {
            Log.w("BatteryOptimizeUtils", "no data found in the getInstalledApplications()");
            return;
        }
        powerAllowlistBackend.refreshList();
        Iterator<ApplicationInfo> it = installedApplications.iterator();
        while (it.hasNext()) {
            ApplicationInfo next = it.next();
            int appOptimizationMode = getAppOptimizationMode(appOpsManager.checkOpNoThrow(70, next.uid, next.packageName), powerAllowlistBackend.isAllowlisted(next.packageName));
            if (appOptimizationMode != 3 && appOptimizationMode != 0 && !isSystemOrDefaultApp(powerAllowlistBackend, next.packageName)) {
                setAppUsageStateInternal(3, next.uid, next.packageName, batteryUtils, powerAllowlistBackend);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getPackageName() {
        String str = this.mPackageName;
        return str == null ? "unknown" : str;
    }

    private static boolean isSystemOrDefaultApp(PowerAllowlistBackend powerAllowlistBackend, String str) {
        return powerAllowlistBackend.isSysAllowlisted(str) || powerAllowlistBackend.isDefaultActiveApp(str);
    }

    private static void setAppUsageStateInternal(int i, int i2, String str, BatteryUtils batteryUtils, PowerAllowlistBackend powerAllowlistBackend) {
        if (i == 0) {
            Log.d("BatteryOptimizeUtils", "set unknown app optimization mode.");
        } else {
            setAppOptimizationModeInternal(i == 1 ? 1 : 0, i == 2, i2, str, batteryUtils, powerAllowlistBackend);
        }
    }

    private static void setAppOptimizationModeInternal(int i, boolean z, int i2, String str, BatteryUtils batteryUtils, PowerAllowlistBackend powerAllowlistBackend) {
        try {
            batteryUtils.setForceAppStandby(i2, str, i);
            if (z) {
                powerAllowlistBackend.addApp(str);
            } else {
                powerAllowlistBackend.removeApp(str);
            }
        } catch (Exception e) {
            Log.e("BatteryOptimizeUtils", "set OPTIMIZATION MODE failed for " + str, e);
        }
    }

    private void refreshState() {
        this.mPowerAllowListBackend.refreshList();
        this.mAllowListed = this.mPowerAllowListBackend.isAllowlisted(this.mPackageName);
        this.mMode = this.mAppOpsManager.checkOpNoThrow(70, this.mUid, this.mPackageName);
        Log.d("BatteryOptimizeUtils", String.format("refresh %s state, allowlisted = %s, mode = %d", this.mPackageName, Boolean.valueOf(this.mAllowListed), Integer.valueOf(this.mMode)));
    }
}
