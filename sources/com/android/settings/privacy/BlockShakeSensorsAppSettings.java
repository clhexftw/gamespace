package com.android.settings.privacy;

import android.content.ContentResolver;
import android.os.Bundle;
import android.provider.Settings;
import com.android.settings.R;
import com.android.settings.fragment.AppListFragment;
import java.util.List;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: BlockShakeSensorsAppSettings.kt */
/* loaded from: classes.dex */
public final class BlockShakeSensorsAppSettings extends AppListFragment {
    @Override // com.android.settings.fragment.AppListFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setDisplayCategory(1);
    }

    @Override // com.android.settings.fragment.AppListFragment
    protected int getTitle() {
        return R.string.privacy_block_shake_sensors_title;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x001f, code lost:
        r6 = kotlin.text.StringsKt__StringsKt.split$default(r0, new java.lang.String[]{";"}, false, 0, 6, null);
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
            java.lang.String r0 = "shake_sensors_blacklist_app"
            r1 = -2
            java.lang.String r6 = android.provider.Settings.Secure.getStringForUser(r6, r0, r1)
            if (r6 == 0) goto L2f
            boolean r0 = kotlin.text.StringsKt.isBlank(r6)
            r0 = r0 ^ 1
            if (r0 == 0) goto L1b
            goto L1c
        L1b:
            r6 = 0
        L1c:
            r0 = r6
            if (r0 == 0) goto L2f
            java.lang.String r6 = ";"
            java.lang.String[] r1 = new java.lang.String[]{r6}
            r2 = 0
            r3 = 0
            r4 = 6
            r5 = 0
            java.util.List r6 = kotlin.text.StringsKt.split$default(r0, r1, r2, r3, r4, r5)
            if (r6 != 0) goto L33
        L2f:
            java.util.List r6 = kotlin.collections.CollectionsKt.emptyList()
        L33:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settings.privacy.BlockShakeSensorsAppSettings.getInitialCheckedList():java.util.List");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settings.fragment.AppListFragment
    public void onListUpdate(List<String> list) {
        String joinToString$default;
        Intrinsics.checkNotNullParameter(list, "list");
        ContentResolver contentResolver = requireContext().getContentResolver();
        joinToString$default = CollectionsKt___CollectionsKt.joinToString$default(list, ";", null, null, 0, null, null, 62, null);
        Settings.Secure.putStringForUser(contentResolver, "shake_sensors_blacklist_app", joinToString$default, -2);
    }
}
