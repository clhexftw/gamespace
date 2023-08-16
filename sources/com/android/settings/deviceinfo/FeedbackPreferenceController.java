package com.android.settings.deviceinfo;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import androidx.fragment.app.Fragment;
import androidx.preference.Preference;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settingslib.DeviceInfoUtils;
import com.android.settingslib.core.AbstractPreferenceController;
/* loaded from: classes.dex */
public class FeedbackPreferenceController extends AbstractPreferenceController implements PreferenceControllerMixin {
    private final Intent intent;
    private final Fragment mHost;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "device_feedback";
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return false;
    }

    public FeedbackPreferenceController(Fragment fragment, Context context) {
        super(context);
        this.mHost = fragment;
        this.intent = new Intent("android.intent.action.BUG_REPORT");
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        super.updateState(preference);
        this.intent.setPackage(DeviceInfoUtils.getFeedbackReporterPackage(this.mContext));
        preference.setIntent(this.intent);
        if (isAvailable() && !preference.isVisible()) {
            preference.setVisible(true);
        } else if (isAvailable() || !preference.isVisible()) {
        } else {
            preference.setVisible(false);
        }
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean handlePreferenceTreeClick(Preference preference) {
        if (TextUtils.equals(preference.getKey(), "device_feedback") && isAvailable()) {
            this.mHost.startActivityForResult(this.intent, 0);
            return true;
        }
        return false;
    }
}
