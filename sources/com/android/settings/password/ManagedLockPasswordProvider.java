package com.android.settings.password;

import android.content.Context;
import android.content.Intent;
import com.android.internal.widget.LockscreenCredential;
/* loaded from: classes.dex */
public class ManagedLockPasswordProvider {
    protected ManagedLockPasswordProvider() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Intent createIntent(boolean z, LockscreenCredential lockscreenCredential) {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CharSequence getPickerOptionTitle(boolean z) {
        return "";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isManagedPasswordChoosable() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isSettingManagedPasswordSupported() {
        return false;
    }

    public static ManagedLockPasswordProvider get(Context context, int i) {
        return new ManagedLockPasswordProvider();
    }
}
