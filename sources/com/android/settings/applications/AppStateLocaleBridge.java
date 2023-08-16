package com.android.settings.applications;

import android.content.Context;
import android.content.pm.ResolveInfo;
import android.os.UserHandle;
import android.os.UserManager;
import android.util.ArrayMap;
import android.util.Log;
import com.android.settings.Utils;
import com.android.settings.applications.AppStateBaseBridge;
import com.android.settingslib.applications.ApplicationsState;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/* loaded from: classes.dex */
public class AppStateLocaleBridge extends AppStateBaseBridge {
    public static final ApplicationsState.AppFilter FILTER_APPS_LOCALE = new ApplicationsState.AppFilter() { // from class: com.android.settings.applications.AppStateLocaleBridge.1
        @Override // com.android.settingslib.applications.ApplicationsState.AppFilter
        public void init() {
        }

        @Override // com.android.settingslib.applications.ApplicationsState.AppFilter
        public boolean filterApp(ApplicationsState.AppEntry appEntry) {
            Object obj = appEntry.extraInfo;
            if (obj == null) {
                String str = AppStateLocaleBridge.TAG;
                Log.d(str, "[" + appEntry.info.packageName + "] has No extra info.");
                return false;
            }
            return ((Boolean) obj).booleanValue();
        }
    };
    private static final String TAG = "AppStateLocaleBridge";
    private final Context mContext;
    private final Map<Integer, AppInfoByProfiles> mUserIdToAppInfoByProfiles;

    public AppStateLocaleBridge(Context context, ApplicationsState applicationsState, AppStateBaseBridge.Callback callback, UserManager userManager) {
        super(applicationsState, callback);
        this.mUserIdToAppInfoByProfiles = new ArrayMap();
        this.mContext = context;
        collectLocaleBridgeInfo(userManager);
    }

    @Override // com.android.settings.applications.AppStateBaseBridge
    protected void updateExtraInfo(ApplicationsState.AppEntry appEntry, String str, int i) {
        AppInfoByProfiles appInfo = getAppInfo(UserHandle.getUserId(i));
        appEntry.extraInfo = AppLocaleUtil.canDisplayLocaleUi(appInfo.mContextAsUser, appEntry.info.packageName, appInfo.mListInfos) ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override // com.android.settings.applications.AppStateBaseBridge
    protected void loadAllExtraInfo() {
        ArrayList<ApplicationsState.AppEntry> allApps = this.mAppSession.getAllApps();
        for (int i = 0; i < allApps.size(); i++) {
            ApplicationsState.AppEntry appEntry = allApps.get(i);
            AppInfoByProfiles appInfo = getAppInfo(UserHandle.getUserId(appEntry.info.uid));
            appEntry.extraInfo = AppLocaleUtil.canDisplayLocaleUi(appInfo.mContextAsUser, appEntry.info.packageName, appInfo.mListInfos) ? Boolean.TRUE : Boolean.FALSE;
        }
    }

    private void collectLocaleBridgeInfo(UserManager userManager) {
        ArrayList<Integer> arrayList = new ArrayList();
        arrayList.add(Integer.valueOf(this.mContext.getUserId()));
        int managedProfileId = Utils.getManagedProfileId(userManager, this.mContext.getUserId());
        if (managedProfileId != -10000) {
            arrayList.add(Integer.valueOf(managedProfileId));
        }
        for (Integer num : arrayList) {
            int intValue = num.intValue();
            if (!this.mUserIdToAppInfoByProfiles.containsKey(Integer.valueOf(intValue))) {
                this.mUserIdToAppInfoByProfiles.put(Integer.valueOf(intValue), new AppInfoByProfiles(this.mContext, intValue));
            }
        }
    }

    private AppInfoByProfiles getAppInfo(int i) {
        if (this.mUserIdToAppInfoByProfiles.containsKey(Integer.valueOf(i))) {
            return this.mUserIdToAppInfoByProfiles.get(Integer.valueOf(i));
        }
        AppInfoByProfiles appInfoByProfiles = new AppInfoByProfiles(this.mContext, i);
        this.mUserIdToAppInfoByProfiles.put(Integer.valueOf(i), appInfoByProfiles);
        return appInfoByProfiles;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class AppInfoByProfiles {
        public final Context mContextAsUser;
        public final List<ResolveInfo> mListInfos;

        private AppInfoByProfiles(Context context, int i) {
            Context createContextAsUser = context.createContextAsUser(UserHandle.of(i), 0);
            this.mContextAsUser = createContextAsUser;
            this.mListInfos = createContextAsUser.getPackageManager().queryIntentActivities(AppLocaleUtil.LAUNCHER_ENTRY_INTENT, 128);
        }
    }
}
