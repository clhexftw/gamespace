package com.android.settings.notification.zen;

import android.content.Context;
import androidx.preference.Preference;
import com.android.settings.R;
import com.android.settingslib.core.lifecycle.Lifecycle;
/* loaded from: classes.dex */
public class ZenModePeoplePreferenceController extends AbstractZenModePreferenceController {
    private final String KEY;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return true;
    }

    public ZenModePeoplePreferenceController(Context context, Lifecycle lifecycle, String str) {
        super(context, str, lifecycle);
        this.KEY = str;
    }

    @Override // com.android.settings.notification.zen.AbstractZenModePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return this.KEY;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        super.updateState(preference);
        int zenMode = getZenMode();
        if (zenMode == 2 || zenMode == 3) {
            preference.setEnabled(false);
            preference.setSummary(this.mBackend.getAlarmsTotalSilencePeopleSummary(4));
            return;
        }
        preference.setEnabled(true);
        preference.setSummary(getPeopleSummary());
    }

    private String getPeopleSummary() {
        int priorityCallSenders = this.mBackend.getPriorityCallSenders();
        int priorityMessageSenders = this.mBackend.getPriorityMessageSenders();
        int priorityConversationSenders = this.mBackend.getPriorityConversationSenders();
        boolean isPriorityCategoryEnabled = this.mBackend.isPriorityCategoryEnabled(16);
        if (priorityCallSenders == 0 && priorityMessageSenders == 0 && priorityConversationSenders == 1) {
            return this.mContext.getResources().getString(R.string.zen_mode_people_all);
        }
        if (priorityCallSenders == -1 && priorityMessageSenders == -1 && priorityConversationSenders == 3 && !isPriorityCategoryEnabled) {
            return this.mContext.getResources().getString(R.string.zen_mode_people_none);
        }
        return this.mContext.getResources().getString(R.string.zen_mode_people_some);
    }
}
