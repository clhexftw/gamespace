package com.android.settings.dream;

import android.content.Context;
import androidx.preference.Preference;
import com.android.internal.annotations.VisibleForTesting;
import com.android.settings.R;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settings.display.AmbientDisplayAlwaysOnPreferenceController;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.dream.DreamBackend;
/* loaded from: classes.dex */
public class WhenToDreamPreferenceController extends AbstractPreferenceController implements PreferenceControllerMixin {
    private final DreamBackend mBackend;
    private final boolean mDreamsDisabledByAmbientModeSuppression;
    private final boolean mDreamsEnabledOnBattery;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "when_to_start";
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public WhenToDreamPreferenceController(Context context) {
        this(context, context.getResources().getBoolean(17891621), context.getResources().getBoolean(17891623));
    }

    @VisibleForTesting
    WhenToDreamPreferenceController(Context context, boolean z, boolean z2) {
        super(context);
        this.mBackend = DreamBackend.getInstance(context);
        this.mDreamsDisabledByAmbientModeSuppression = z;
        this.mDreamsEnabledOnBattery = z2;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        super.updateState(preference);
        if (this.mDreamsDisabledByAmbientModeSuppression && AmbientDisplayAlwaysOnPreferenceController.isAodSuppressedByBedtime(this.mContext)) {
            preference.setSummary(R.string.screensaver_settings_when_to_dream_bedtime);
        } else {
            preference.setSummary(DreamSettings.getDreamSettingDescriptionResId(this.mBackend.getWhenToDreamSetting(), this.mDreamsEnabledOnBattery));
        }
    }
}
