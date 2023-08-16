package com.android.settings.notification;

import android.content.ContentResolver;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.provider.Settings;
import com.android.settings.R;
import com.android.settings.fragment.AppListFragment;
import java.util.List;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: HeadsUpBlacklistSettings.kt */
/* loaded from: classes.dex */
public final class HeadsUpBlacklistSettings extends AppListFragment {
    @Override // com.android.settings.fragment.AppListFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setDisplayCategory(2);
        final String[] stringArray = requireContext().getResources().getStringArray(R.array.config_headsUpConfAllowedSystemApps);
        setCustomFilter(new Function1<PackageInfo, Boolean>() { // from class: com.android.settings.notification.HeadsUpBlacklistSettings$onCreate$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(PackageInfo it) {
                boolean z;
                boolean contains;
                Intrinsics.checkNotNullParameter(it, "it");
                if (it.applicationInfo.isSystemApp()) {
                    String[] whiteListedPackages = stringArray;
                    Intrinsics.checkNotNullExpressionValue(whiteListedPackages, "whiteListedPackages");
                    contains = ArraysKt___ArraysKt.contains(whiteListedPackages, it.packageName);
                    if (!contains) {
                        z = false;
                        return Boolean.valueOf(z);
                    }
                }
                z = true;
                return Boolean.valueOf(z);
            }
        });
    }

    @Override // com.android.settings.fragment.AppListFragment
    protected int getTitle() {
        return R.string.heads_up_blacklist_title;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x001d, code lost:
        r6 = kotlin.text.StringsKt__StringsKt.split$default(r0, new java.lang.String[]{"|"}, false, 0, 6, null);
     */
    @Override // com.android.settings.fragment.AppListFragment
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected java.util.List<java.lang.String> getInitialCheckedList() {
        /*
            r6 = this;
            android.content.Context r6 = r6.requireContext()
            android.content.ContentResolver r6 = r6.getContentResolver()
            java.lang.String r0 = "heads_up_blacklist_values"
            java.lang.String r6 = android.provider.Settings.System.getString(r6, r0)
            if (r6 == 0) goto L2e
            boolean r0 = kotlin.text.StringsKt.isBlank(r6)
            r0 = r0 ^ 1
            if (r0 == 0) goto L19
            goto L1a
        L19:
            r6 = 0
        L1a:
            r0 = r6
            if (r0 == 0) goto L2e
            java.lang.String r6 = "|"
            java.lang.String[] r1 = new java.lang.String[]{r6}
            r2 = 0
            r3 = 0
            r4 = 6
            r5 = 0
            java.util.List r6 = kotlin.text.StringsKt.split$default(r0, r1, r2, r3, r4, r5)
            if (r6 != 0) goto L32
        L2e:
            java.util.List r6 = kotlin.collections.CollectionsKt.emptyList()
        L32:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settings.notification.HeadsUpBlacklistSettings.getInitialCheckedList():java.util.List");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settings.fragment.AppListFragment
    public void onListUpdate(List<String> list) {
        String joinToString$default;
        Intrinsics.checkNotNullParameter(list, "list");
        ContentResolver contentResolver = requireContext().getContentResolver();
        joinToString$default = CollectionsKt___CollectionsKt.joinToString$default(list, "|", null, null, 0, null, null, 62, null);
        Settings.System.putString(contentResolver, "heads_up_blacklist_values", joinToString$default);
    }
}
