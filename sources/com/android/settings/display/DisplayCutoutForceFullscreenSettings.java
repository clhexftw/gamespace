package com.android.settings.display;

import android.app.ActivityManager;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import com.android.settings.R;
import com.android.settings.fragment.AppListFragment;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.nameless.cutout.CutoutFullscreenController;
/* compiled from: DisplayCutoutForceFullscreenSettings.kt */
/* loaded from: classes.dex */
public final class DisplayCutoutForceFullscreenSettings extends AppListFragment {
    public static final Companion Companion = new Companion(null);
    private ActivityManager activityManager;
    private CutoutFullscreenController cutoutForceFullscreenSettings;

    private final String getKey() {
        return "force_full_screen_cutout_apps";
    }

    @Override // com.android.settings.fragment.AppListFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Object systemService = requireContext().getSystemService(ActivityManager.class);
        Intrinsics.checkNotNullExpressionValue(systemService, "requireContext().getSyst…ivityManager::class.java)");
        this.activityManager = (ActivityManager) systemService;
        this.cutoutForceFullscreenSettings = new CutoutFullscreenController(requireContext());
        setDisplayCategory(2);
        setCustomFilter(new Function1<PackageInfo, Boolean>() { // from class: com.android.settings.display.DisplayCutoutForceFullscreenSettings$onCreate$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(PackageInfo it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return Boolean.valueOf(!it.applicationInfo.isSystemApp() || "com.google.android.dialer".equals(it.packageName));
            }
        });
    }

    @Override // com.android.settings.fragment.AppListFragment
    protected int getTitle() {
        return R.string.display_cutout_force_fullscreen_title;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x001f, code lost:
        r6 = kotlin.text.StringsKt__StringsKt.split$default(r0, new java.lang.String[]{","}, false, 0, 6, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x002f, code lost:
        r6 = kotlin.collections.CollectionsKt___CollectionsKt.toList(r6);
     */
    @Override // com.android.settings.fragment.AppListFragment
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected java.util.List<java.lang.String> getInitialCheckedList() {
        /*
            r6 = this;
            android.content.Context r0 = r6.requireContext()
            android.content.ContentResolver r0 = r0.getContentResolver()
            java.lang.String r6 = r6.getKey()
            java.lang.String r6 = android.provider.Settings.System.getString(r0, r6)
            if (r6 == 0) goto L38
            boolean r0 = kotlin.text.StringsKt.isBlank(r6)
            r0 = r0 ^ 1
            if (r0 == 0) goto L1b
            goto L1c
        L1b:
            r6 = 0
        L1c:
            r0 = r6
            if (r0 == 0) goto L38
            java.lang.String r6 = ","
            java.lang.String[] r1 = new java.lang.String[]{r6}
            r2 = 0
            r3 = 0
            r4 = 6
            r5 = 0
            java.util.List r6 = kotlin.text.StringsKt.split$default(r0, r1, r2, r3, r4, r5)
            if (r6 == 0) goto L38
            java.lang.Iterable r6 = (java.lang.Iterable) r6
            java.util.List r6 = kotlin.collections.CollectionsKt.toList(r6)
            if (r6 == 0) goto L38
            goto L3c
        L38:
            java.util.List r6 = kotlin.collections.CollectionsKt.emptyList()
        L3c:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settings.display.DisplayCutoutForceFullscreenSettings.getInitialCheckedList():java.util.List");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settings.fragment.AppListFragment
    public void onAppSelected(String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        CutoutFullscreenController cutoutFullscreenController = this.cutoutForceFullscreenSettings;
        ActivityManager activityManager = null;
        if (cutoutFullscreenController == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cutoutForceFullscreenSettings");
            cutoutFullscreenController = null;
        }
        cutoutFullscreenController.addApp(packageName);
        try {
            ActivityManager activityManager2 = this.activityManager;
            if (activityManager2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("activityManager");
            } else {
                activityManager = activityManager2;
            }
            activityManager.forceStopPackage(packageName);
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settings.fragment.AppListFragment
    public void onAppDeselected(String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        CutoutFullscreenController cutoutFullscreenController = this.cutoutForceFullscreenSettings;
        ActivityManager activityManager = null;
        if (cutoutFullscreenController == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cutoutForceFullscreenSettings");
            cutoutFullscreenController = null;
        }
        cutoutFullscreenController.removeApp(packageName);
        try {
            ActivityManager activityManager2 = this.activityManager;
            if (activityManager2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("activityManager");
            } else {
                activityManager = activityManager2;
            }
            activityManager.forceStopPackage(packageName);
        } catch (Exception unused) {
        }
    }

    /* compiled from: DisplayCutoutForceFullscreenSettings.kt */
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
