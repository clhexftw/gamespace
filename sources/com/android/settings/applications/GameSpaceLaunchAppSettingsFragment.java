package com.android.settings.applications;

import android.content.ContentResolver;
import android.content.Context;
import android.provider.Settings;
import com.android.settings.R;
import com.android.settings.fragment.AppListFragment;
import java.util.List;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: GameSpaceLaunchAppSettingsFragment.kt */
/* loaded from: classes.dex */
public final class GameSpaceLaunchAppSettingsFragment extends AppListFragment {
    /* JADX WARN: Code restructure failed: missing block: B:13:0x002a, code lost:
        r0 = kotlin.text.StringsKt__StringsKt.split$default(r3, new java.lang.String[]{";"}, false, 0, 6, null);
     */
    @Override // com.android.settings.fragment.AppListFragment, androidx.fragment.app.Fragment
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onCreate(android.os.Bundle r10) {
        /*
            r9 = this;
            super.onCreate(r10)
            r10 = 2
            r9.setDisplayCategory(r10)
            android.content.Context r0 = r9.getContext()
            r1 = 0
            if (r0 == 0) goto L13
            android.content.ContentResolver r0 = r0.getContentResolver()
            goto L14
        L13:
            r0 = r1
        L14:
            r2 = -2
            java.lang.String r3 = "gamespace_game_list"
            java.lang.String r0 = android.provider.Settings.System.getStringForUser(r0, r3, r2)
            if (r0 == 0) goto L61
            boolean r2 = kotlin.text.StringsKt.isBlank(r0)
            r2 = r2 ^ 1
            if (r2 == 0) goto L27
            r3 = r0
            goto L28
        L27:
            r3 = r1
        L28:
            if (r3 == 0) goto L61
            java.lang.String r0 = ";"
            java.lang.String[] r4 = new java.lang.String[]{r0}
            r5 = 0
            r6 = 0
            r7 = 6
            r8 = 0
            java.util.List r0 = kotlin.text.StringsKt.split$default(r3, r4, r5, r6, r7, r8)
            if (r0 == 0) goto L61
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.ArrayList r2 = new java.util.ArrayList
            r3 = 10
            int r3 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r0, r3)
            r2.<init>(r3)
            java.util.Iterator r0 = r0.iterator()
        L4b:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L65
            java.lang.Object r3 = r0.next()
            java.lang.String r3 = (java.lang.String) r3
            java.lang.String r4 = "="
            java.lang.String r3 = kotlin.text.StringsKt.substringBefore$default(r3, r4, r1, r10, r1)
            r2.add(r3)
            goto L4b
        L61:
            java.util.List r2 = kotlin.collections.CollectionsKt.emptyList()
        L65:
            android.content.Context r10 = r9.requireContext()
            android.content.res.Resources r10 = r10.getResources()
            int r0 = com.android.settings.R.array.config_quickLaunchAllowedSystemApps
            java.lang.String[] r10 = r10.getStringArray(r0)
            com.android.settings.applications.GameSpaceLaunchAppSettingsFragment$onCreate$1 r0 = new com.android.settings.applications.GameSpaceLaunchAppSettingsFragment$onCreate$1
            r0.<init>()
            r9.setCustomFilter(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settings.applications.GameSpaceLaunchAppSettingsFragment.onCreate(android.os.Bundle):void");
    }

    @Override // com.android.settings.fragment.AppListFragment
    protected int getTitle() {
        return R.string.quick_launch_apps_title;
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0023, code lost:
        r8 = kotlin.text.StringsKt__StringsKt.split$default(r2, new java.lang.String[]{";"}, false, 0, 6, null);
     */
    @Override // com.android.settings.fragment.AppListFragment
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected java.util.List<java.lang.String> getInitialCheckedList() {
        /*
            r8 = this;
            android.content.Context r8 = r8.getContext()
            r0 = 0
            if (r8 == 0) goto Lc
            android.content.ContentResolver r8 = r8.getContentResolver()
            goto Ld
        Lc:
            r8 = r0
        Ld:
            r1 = -2
            java.lang.String r2 = "gamespace_quick_launch_app_list"
            java.lang.String r8 = android.provider.Settings.System.getStringForUser(r8, r2, r1)
            if (r8 == 0) goto L33
            boolean r1 = kotlin.text.StringsKt.isBlank(r8)
            r1 = r1 ^ 1
            if (r1 == 0) goto L20
            r2 = r8
            goto L21
        L20:
            r2 = r0
        L21:
            if (r2 == 0) goto L33
            java.lang.String r8 = ";"
            java.lang.String[] r3 = new java.lang.String[]{r8}
            r4 = 0
            r5 = 0
            r6 = 6
            r7 = 0
            java.util.List r8 = kotlin.text.StringsKt.split$default(r2, r3, r4, r5, r6, r7)
            if (r8 != 0) goto L37
        L33:
            java.util.List r8 = kotlin.collections.CollectionsKt.emptyList()
        L37:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settings.applications.GameSpaceLaunchAppSettingsFragment.getInitialCheckedList():java.util.List");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settings.fragment.AppListFragment
    public void onListUpdate(List<String> list) {
        String joinToString$default;
        Intrinsics.checkNotNullParameter(list, "list");
        Context context = getContext();
        ContentResolver contentResolver = context != null ? context.getContentResolver() : null;
        joinToString$default = CollectionsKt___CollectionsKt.joinToString$default(list, ";", null, null, 0, null, null, 62, null);
        Settings.System.putStringForUser(contentResolver, "gamespace_quick_launch_app_list", joinToString$default, -2);
    }
}
