package com.android.settings.notification;

import android.content.Context;
import android.os.Vibrator;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settingslib.core.lifecycle.Lifecycle;
/* loaded from: classes.dex */
public class VibrateIconPreferenceController extends SettingPrefController {
    private final boolean mHasVibrator;

    @Override // com.android.settings.notification.SettingPrefController, com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return false;
    }

    public VibrateIconPreferenceController(Context context, SettingsPreferenceFragment settingsPreferenceFragment, Lifecycle lifecycle) {
        super(context, settingsPreferenceFragment, lifecycle);
        this.mHasVibrator = ((Vibrator) context.getSystemService(Vibrator.class)).hasVibrator();
        this.mPreference = new SettingPref(3, "vibrate_icon", "status_bar_show_vibrate_icon", 0, new int[0]);
    }
}
