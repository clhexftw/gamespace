package com.android.settingslib.dream;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.provider.Settings;
import android.service.dreams.DreamService;
import android.service.dreams.IDreamManager;
import android.util.ArraySet;
import android.util.Log;
import com.android.internal.annotations.VisibleForTesting;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
/* loaded from: classes2.dex */
public class DreamBackend {
    private static DreamBackend sInstance;
    private final DreamInfoComparator mComparator;
    private final Context mContext;
    private final Set<ComponentName> mDisabledDreams;
    private final IDreamManager mDreamManager;
    private final boolean mDreamsActivatedOnDockByDefault;
    private final boolean mDreamsActivatedOnSleepByDefault;
    private final boolean mDreamsEnabledByDefault;
    private Set<Integer> mSupportedComplications;

    private static void logd(String str, Object... objArr) {
    }

    /* loaded from: classes2.dex */
    public static class DreamInfo {
        public CharSequence caption;
        public ComponentName componentName;
        public CharSequence description;
        public Drawable icon;
        public boolean isActive;
        public Drawable previewImage;
        public ComponentName settingsComponentName;
        public boolean supportsComplications = false;

        public String toString() {
            StringBuilder sb = new StringBuilder(DreamInfo.class.getSimpleName());
            sb.append('[');
            sb.append(this.caption);
            if (this.isActive) {
                sb.append(",active");
            }
            sb.append(',');
            sb.append(this.componentName);
            if (this.settingsComponentName != null) {
                sb.append("settings=");
                sb.append(this.settingsComponentName);
            }
            sb.append(']');
            return sb.toString();
        }
    }

    public static DreamBackend getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DreamBackend(context);
        }
        return sInstance;
    }

    public DreamBackend(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.mContext = applicationContext;
        Resources resources = applicationContext.getResources();
        this.mDreamManager = IDreamManager.Stub.asInterface(ServiceManager.getService("dreams"));
        this.mComparator = new DreamInfoComparator(getDefaultDream());
        this.mDreamsEnabledByDefault = resources.getBoolean(17891622);
        this.mDreamsActivatedOnSleepByDefault = resources.getBoolean(17891620);
        this.mDreamsActivatedOnDockByDefault = resources.getBoolean(17891619);
        this.mDisabledDreams = (Set) Arrays.stream(resources.getStringArray(17236037)).map(new Function() { // from class: com.android.settingslib.dream.DreamBackend$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ComponentName.unflattenFromString((String) obj);
            }
        }).collect(Collectors.toSet());
        this.mSupportedComplications = (Set) Arrays.stream(resources.getIntArray(17236147)).boxed().collect(Collectors.toSet());
    }

    public List<DreamInfo> getDreamInfos() {
        logd("getDreamInfos()", new Object[0]);
        ComponentName activeDream = getActiveDream();
        PackageManager packageManager = this.mContext.getPackageManager();
        List<ResolveInfo> queryIntentServices = packageManager.queryIntentServices(new Intent("android.service.dreams.DreamService"), 128);
        ArrayList arrayList = new ArrayList(queryIntentServices.size());
        for (ResolveInfo resolveInfo : queryIntentServices) {
            ComponentName dreamComponentName = getDreamComponentName(resolveInfo);
            if (dreamComponentName != null && !this.mDisabledDreams.contains(dreamComponentName)) {
                DreamInfo dreamInfo = new DreamInfo();
                dreamInfo.caption = resolveInfo.loadLabel(packageManager);
                dreamInfo.icon = resolveInfo.loadIcon(packageManager);
                dreamInfo.description = getDescription(resolveInfo, packageManager);
                dreamInfo.componentName = dreamComponentName;
                dreamInfo.isActive = dreamComponentName.equals(activeDream);
                DreamService.DreamMetadata dreamMetadata = DreamService.getDreamMetadata(this.mContext, resolveInfo.serviceInfo);
                if (dreamMetadata != null) {
                    dreamInfo.settingsComponentName = dreamMetadata.settingsActivity;
                    dreamInfo.previewImage = dreamMetadata.previewImage;
                    dreamInfo.supportsComplications = dreamMetadata.showComplications;
                }
                arrayList.add(dreamInfo);
            }
        }
        arrayList.sort(this.mComparator);
        return arrayList;
    }

    private static CharSequence getDescription(ResolveInfo resolveInfo, PackageManager packageManager) {
        ApplicationInfo applicationInfo;
        String str = resolveInfo.resolvePackageName;
        if (str == null) {
            ServiceInfo serviceInfo = resolveInfo.serviceInfo;
            String str2 = serviceInfo.packageName;
            applicationInfo = serviceInfo.applicationInfo;
            str = str2;
        } else {
            applicationInfo = null;
        }
        int i = resolveInfo.serviceInfo.descriptionRes;
        if (i != 0) {
            return packageManager.getText(str, i, applicationInfo);
        }
        return null;
    }

    public ComponentName getDefaultDream() {
        IDreamManager iDreamManager = this.mDreamManager;
        if (iDreamManager == null) {
            return null;
        }
        try {
            return iDreamManager.getDefaultDreamComponentForUser(this.mContext.getUserId());
        } catch (RemoteException e) {
            Log.w("DreamBackend", "Failed to get default dream", e);
            return null;
        }
    }

    public CharSequence getActiveDreamName() {
        ComponentName activeDream = getActiveDream();
        if (activeDream != null) {
            PackageManager packageManager = this.mContext.getPackageManager();
            try {
                ServiceInfo serviceInfo = packageManager.getServiceInfo(activeDream, 0);
                if (serviceInfo != null) {
                    return serviceInfo.loadLabel(packageManager);
                }
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        return null;
    }

    public int getWhenToDreamSetting() {
        if (isActivatedOnDock() && isActivatedOnSleep()) {
            return 2;
        }
        if (isActivatedOnDock()) {
            return 1;
        }
        return isActivatedOnSleep() ? 0 : 3;
    }

    public void setWhenToDream(int i) {
        setEnabled(i != 3);
        if (i == 0) {
            setActivatedOnDock(false);
            setActivatedOnSleep(true);
        } else if (i == 1) {
            setActivatedOnDock(true);
            setActivatedOnSleep(false);
        } else if (i != 2) {
        } else {
            setActivatedOnDock(true);
            setActivatedOnSleep(true);
        }
    }

    public Set<Integer> getEnabledComplications() {
        ArraySet arraySet = getComplicationsEnabled() ? new ArraySet(this.mSupportedComplications) : new ArraySet();
        if (!getHomeControlsEnabled()) {
            arraySet.remove(6);
        } else if (this.mSupportedComplications.contains(6)) {
            arraySet.add(6);
        }
        return arraySet;
    }

    public void setComplicationsEnabled(boolean z) {
        Settings.Secure.putInt(this.mContext.getContentResolver(), "screensaver_complications_enabled", z ? 1 : 0);
    }

    public void setHomeControlsEnabled(boolean z) {
        Settings.Secure.putInt(this.mContext.getContentResolver(), "screensaver_home_controls_enabled", z ? 1 : 0);
    }

    private boolean getHomeControlsEnabled() {
        return Settings.Secure.getInt(this.mContext.getContentResolver(), "screensaver_home_controls_enabled", 1) == 1;
    }

    public boolean getComplicationsEnabled() {
        return Settings.Secure.getInt(this.mContext.getContentResolver(), "screensaver_complications_enabled", 1) == 1;
    }

    public Set<Integer> getSupportedComplications() {
        return this.mSupportedComplications;
    }

    @VisibleForTesting
    public void setSupportedComplications(Set<Integer> set) {
        this.mSupportedComplications = set;
    }

    public boolean isEnabled() {
        return getBoolean("screensaver_enabled", this.mDreamsEnabledByDefault);
    }

    public void setEnabled(boolean z) {
        logd("setEnabled(%s)", Boolean.valueOf(z));
        setBoolean("screensaver_enabled", z);
    }

    public boolean isActivatedOnDock() {
        return getBoolean("screensaver_activate_on_dock", this.mDreamsActivatedOnDockByDefault);
    }

    public void setActivatedOnDock(boolean z) {
        logd("setActivatedOnDock(%s)", Boolean.valueOf(z));
        setBoolean("screensaver_activate_on_dock", z);
    }

    public boolean isActivatedOnSleep() {
        return getBoolean("screensaver_activate_on_sleep", this.mDreamsActivatedOnSleepByDefault);
    }

    public void setActivatedOnSleep(boolean z) {
        logd("setActivatedOnSleep(%s)", Boolean.valueOf(z));
        setBoolean("screensaver_activate_on_sleep", z);
    }

    private boolean getBoolean(String str, boolean z) {
        return Settings.Secure.getInt(this.mContext.getContentResolver(), str, z ? 1 : 0) == 1;
    }

    private void setBoolean(String str, boolean z) {
        Settings.Secure.putInt(this.mContext.getContentResolver(), str, z ? 1 : 0);
    }

    public void setActiveDream(ComponentName componentName) {
        logd("setActiveDream(%s)", componentName);
        IDreamManager iDreamManager = this.mDreamManager;
        if (iDreamManager == null) {
            return;
        }
        try {
            ComponentName[] componentNameArr = {componentName};
            if (componentName == null) {
                componentNameArr = null;
            }
            iDreamManager.setDreamComponents(componentNameArr);
        } catch (RemoteException e) {
            Log.w("DreamBackend", "Failed to set active dream to " + componentName, e);
        }
    }

    public ComponentName getActiveDream() {
        IDreamManager iDreamManager = this.mDreamManager;
        if (iDreamManager == null) {
            return null;
        }
        try {
            ComponentName[] dreamComponents = iDreamManager.getDreamComponents();
            if (dreamComponents == null || dreamComponents.length <= 0) {
                return null;
            }
            return dreamComponents[0];
        } catch (RemoteException e) {
            Log.w("DreamBackend", "Failed to get active dream", e);
            return null;
        }
    }

    public void launchSettings(Context context, DreamInfo dreamInfo) {
        logd("launchSettings(%s)", dreamInfo);
        if (dreamInfo == null || dreamInfo.settingsComponentName == null) {
            return;
        }
        context.startActivity(new Intent().setComponent(dreamInfo.settingsComponentName).addFlags(276824064));
    }

    public void preview(ComponentName componentName) {
        logd("preview(%s)", componentName);
        IDreamManager iDreamManager = this.mDreamManager;
        if (iDreamManager == null || componentName == null) {
            return;
        }
        try {
            iDreamManager.testDream(this.mContext.getUserId(), componentName);
        } catch (RemoteException e) {
            Log.w("DreamBackend", "Failed to preview " + componentName, e);
        }
    }

    private static ComponentName getDreamComponentName(ResolveInfo resolveInfo) {
        if (resolveInfo == null || resolveInfo.serviceInfo == null) {
            return null;
        }
        ServiceInfo serviceInfo = resolveInfo.serviceInfo;
        return new ComponentName(serviceInfo.packageName, serviceInfo.name);
    }

    /* loaded from: classes2.dex */
    private static class DreamInfoComparator implements Comparator<DreamInfo> {
        private final ComponentName mDefaultDream;

        public DreamInfoComparator(ComponentName componentName) {
            this.mDefaultDream = componentName;
        }

        @Override // java.util.Comparator
        public int compare(DreamInfo dreamInfo, DreamInfo dreamInfo2) {
            return sortKey(dreamInfo).compareTo(sortKey(dreamInfo2));
        }

        private String sortKey(DreamInfo dreamInfo) {
            StringBuilder sb = new StringBuilder();
            sb.append(dreamInfo.componentName.equals(this.mDefaultDream) ? '0' : '1');
            sb.append(dreamInfo.caption);
            return sb.toString();
        }
    }
}
