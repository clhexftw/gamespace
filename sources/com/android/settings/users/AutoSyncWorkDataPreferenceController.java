package com.android.settings.users;

import android.content.Context;
import android.os.UserHandle;
import androidx.preference.PreferenceFragmentCompat;
import com.android.settings.Utils;
/* loaded from: classes.dex */
public class AutoSyncWorkDataPreferenceController extends AutoSyncDataPreferenceController {
    @Override // com.android.settings.users.AutoSyncDataPreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "auto_sync_work_account_data";
    }

    public AutoSyncWorkDataPreferenceController(Context context, PreferenceFragmentCompat preferenceFragmentCompat) {
        super(context, preferenceFragmentCompat);
        this.mUserHandle = Utils.getManagedProfileWithDisabled(this.mUserManager);
    }

    @Override // com.android.settings.users.AutoSyncDataPreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        UserHandle managedProfileWithDisabled = Utils.getManagedProfileWithDisabled(this.mUserManager);
        this.mUserHandle = managedProfileWithDisabled;
        return (managedProfileWithDisabled == null || this.mUserManager.isManagedProfile() || this.mUserManager.isLinkedUser() || this.mUserManager.getProfiles(UserHandle.myUserId()).size() <= 1) ? false : true;
    }
}
