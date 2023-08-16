package com.android.settings.development.transcode;

import android.content.Context;
import android.content.IntentFilter;
import android.os.SystemProperties;
import com.android.settings.R;
import com.android.settings.core.TogglePreferenceController;
import com.android.settingslib.development.DevelopmentSettingsEnabler;
/* loaded from: classes.dex */
public class TranscodeUserControlPreferenceController extends TogglePreferenceController {
    private static final String TRANSCODE_USER_CONTROL_SYS_PROP_KEY = "persist.sys.fuse.transcode_user_control";

    @Override // com.android.settings.core.TogglePreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ Class getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.settings.core.TogglePreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.settings.core.TogglePreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.settings.core.TogglePreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public TranscodeUserControlPreferenceController(Context context, String str) {
        super(context, str);
    }

    @Override // com.android.settings.core.TogglePreferenceController
    public boolean isChecked() {
        return SystemProperties.getBoolean(TRANSCODE_USER_CONTROL_SYS_PROP_KEY, false);
    }

    @Override // com.android.settings.core.TogglePreferenceController
    public boolean setChecked(boolean z) {
        SystemProperties.set(TRANSCODE_USER_CONTROL_SYS_PROP_KEY, String.valueOf(z));
        return true;
    }

    @Override // com.android.settings.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return DevelopmentSettingsEnabler.isDevelopmentSettingsEnabled(this.mContext) ? 0 : 2;
    }

    @Override // com.android.settings.core.TogglePreferenceController, com.android.settings.slices.Sliceable
    public int getSliceHighlightMenuRes() {
        return R.string.menu_key_system;
    }
}
