package com.android.settings.notification.app;

import android.content.Context;
import androidx.preference.Preference;
import com.android.settings.R;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settings.notification.NotificationBackend;
/* loaded from: classes.dex */
public class NotificationsOffPreferenceController extends NotificationPreferenceController implements PreferenceControllerMixin {
    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "block_desc";
    }

    public NotificationsOffPreferenceController(Context context) {
        super(context, null);
    }

    @Override // com.android.settings.notification.app.NotificationPreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        if (this.mAppRow == null) {
            return false;
        }
        if (this.mPreferenceFilter == null || isIncludedInFilter()) {
            return !super.isAvailable();
        }
        return false;
    }

    @Override // com.android.settings.notification.app.NotificationPreferenceController
    boolean isIncludedInFilter() {
        return this.mPreferenceFilter.contains("importance");
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        NotificationBackend.AppRow appRow = this.mAppRow;
        if (appRow != null) {
            if (this.mChannel != null) {
                preference.setTitle(R.string.channel_notifications_off_desc);
            } else if (this.mChannelGroup != null) {
                preference.setTitle(R.string.channel_group_notifications_off_desc);
            } else if (appRow.permissionStateLocked) {
                preference.setTitle(R.string.app_notifications_not_send_desc);
            } else {
                preference.setTitle(R.string.app_notifications_off_desc);
            }
        }
        preference.setSelectable(false);
    }
}
