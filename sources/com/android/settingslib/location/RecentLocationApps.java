package com.android.settingslib.location;

import android.app.AppOpsManager;
import android.content.Context;
import android.content.PermissionChecker;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.UserHandle;
import android.os.UserManager;
import android.util.IconDrawableFactory;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
/* loaded from: classes2.dex */
public class RecentLocationApps {
    static final String ANDROID_SYSTEM_PACKAGE_NAME = "android";
    private static final String TAG = "RecentLocationApps";
    private final Context mContext;
    private final IconDrawableFactory mDrawableFactory;
    private final PackageManager mPackageManager;
    static final int[] LOCATION_REQUEST_OPS = {41, 42};
    static final int[] LOCATION_PERMISSION_OPS = {1, 0};

    public RecentLocationApps(Context context) {
        this.mContext = context;
        this.mPackageManager = context.getPackageManager();
        this.mDrawableFactory = IconDrawableFactory.newInstance(context);
    }

    public List<Request> getAppList(boolean z) {
        PackageManager packageManager;
        List list;
        int i;
        boolean z2;
        Request requestFromOps;
        PackageManager packageManager2 = this.mContext.getPackageManager();
        List packagesForOps = ((AppOpsManager) this.mContext.getSystemService("appops")).getPackagesForOps(LOCATION_REQUEST_OPS);
        int size = packagesForOps != null ? packagesForOps.size() : 0;
        ArrayList arrayList = new ArrayList(size);
        long currentTimeMillis = System.currentTimeMillis();
        List<UserHandle> userProfiles = ((UserManager) this.mContext.getSystemService("user")).getUserProfiles();
        int i2 = 0;
        while (i2 < size) {
            AppOpsManager.PackageOps packageOps = (AppOpsManager.PackageOps) packagesForOps.get(i2);
            String packageName = packageOps.getPackageName();
            int uid = packageOps.getUid();
            UserHandle userHandleForUid = UserHandle.getUserHandleForUid(uid);
            if ((uid == 1000 && "android".equals(packageName)) || !userProfiles.contains(userHandleForUid)) {
                packageManager = packageManager2;
                list = packagesForOps;
                i = size;
            } else {
                if (!z) {
                    int[] iArr = LOCATION_PERMISSION_OPS;
                    int length = iArr.length;
                    int i3 = 0;
                    while (i3 < length) {
                        list = packagesForOps;
                        String opToPermission = AppOpsManager.opToPermission(iArr[i3]);
                        int i4 = length;
                        int permissionFlags = packageManager2.getPermissionFlags(opToPermission, packageName, userHandleForUid);
                        packageManager = packageManager2;
                        i = size;
                        if (PermissionChecker.checkPermissionForPreflight(this.mContext, opToPermission, -1, uid, packageName) == 0) {
                            if ((permissionFlags & 256) == 0) {
                                z2 = false;
                                break;
                            }
                            i3++;
                            length = i4;
                            packagesForOps = list;
                            packageManager2 = packageManager;
                            size = i;
                        } else if ((permissionFlags & 512) == 0) {
                            z2 = false;
                            break;
                        } else {
                            i3++;
                            length = i4;
                            packagesForOps = list;
                            packageManager2 = packageManager;
                            size = i;
                        }
                    }
                }
                packageManager = packageManager2;
                list = packagesForOps;
                i = size;
                z2 = true;
                if (z2 && (requestFromOps = getRequestFromOps(currentTimeMillis, packageOps)) != null) {
                    arrayList.add(requestFromOps);
                }
            }
            i2++;
            packagesForOps = list;
            packageManager2 = packageManager;
            size = i;
        }
        return arrayList;
    }

    public List<Request> getAppListSorted(boolean z) {
        List<Request> appList = getAppList(z);
        Collections.sort(appList, Collections.reverseOrder(new Comparator<Request>() { // from class: com.android.settingslib.location.RecentLocationApps.1
            @Override // java.util.Comparator
            public int compare(Request request, Request request2) {
                return Long.compare(request.requestFinishTime, request2.requestFinishTime);
            }
        }));
        return appList;
    }

    private Request getRequestFromOps(long j, AppOpsManager.PackageOps packageOps) {
        String packageName = packageOps.getPackageName();
        long j2 = j - 86400000;
        boolean z = false;
        long j3 = 0;
        boolean z2 = false;
        for (AppOpsManager.OpEntry opEntry : packageOps.getOps()) {
            if (opEntry.isRunning() || opEntry.getTime() >= j2) {
                j3 = opEntry.getTime() + opEntry.getDuration();
                int op = opEntry.getOp();
                if (op == 41) {
                    z = true;
                } else if (op == 42) {
                    z2 = true;
                }
            }
        }
        if (!z2 && !z) {
            String str = TAG;
            if (Log.isLoggable(str, 2)) {
                Log.v(str, packageName + " hadn't used location within the time interval.");
            }
            return null;
        }
        int userId = UserHandle.getUserId(packageOps.getUid());
        try {
            ApplicationInfo applicationInfoAsUser = this.mPackageManager.getApplicationInfoAsUser(packageName, 128, userId);
            if (applicationInfoAsUser == null) {
                Log.w(TAG, "Null application info retrieved for package " + packageName + ", userId " + userId);
                return null;
            }
            UserHandle userHandle = new UserHandle(userId);
            Drawable badgedIcon = this.mDrawableFactory.getBadgedIcon(applicationInfoAsUser, userId);
            CharSequence applicationLabel = this.mPackageManager.getApplicationLabel(applicationInfoAsUser);
            CharSequence userBadgedLabel = this.mPackageManager.getUserBadgedLabel(applicationLabel, userHandle);
            return new Request(packageName, userHandle, badgedIcon, applicationLabel, z2, applicationLabel.toString().contentEquals(userBadgedLabel) ? null : userBadgedLabel, j3);
        } catch (PackageManager.NameNotFoundException unused) {
            Log.w(TAG, "package name not found for " + packageName + ", userId " + userId);
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static class Request {
        public final CharSequence contentDescription;
        public final Drawable icon;
        public final boolean isHighBattery;
        public final CharSequence label;
        public final String packageName;
        public final long requestFinishTime;
        public final UserHandle userHandle;

        public Request(String str, UserHandle userHandle, Drawable drawable, CharSequence charSequence, boolean z, CharSequence charSequence2, long j) {
            this.packageName = str;
            this.userHandle = userHandle;
            this.icon = drawable;
            this.label = charSequence;
            this.isHighBattery = z;
            this.contentDescription = charSequence2;
            this.requestFinishTime = j;
        }
    }
}
