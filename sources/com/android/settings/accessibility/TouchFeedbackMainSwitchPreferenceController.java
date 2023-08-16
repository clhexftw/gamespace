package com.android.settings.accessibility;

import android.content.Context;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.Vibrator;
import android.provider.Settings;
import com.android.settings.R;
import com.android.settings.widget.SettingsMainSwitchPreferenceController;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.core.lifecycle.events.OnStop;
/* loaded from: classes.dex */
public class TouchFeedbackMainSwitchPreferenceController extends SettingsMainSwitchPreferenceController implements LifecycleObserver, OnStart, OnStop {
    private final ContentObserver mSettingObserver;
    private final Vibrator mVibrator;

    @Override // com.android.settings.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return 0;
    }

    @Override // com.android.settings.widget.SettingsMainSwitchPreferenceController, com.android.settings.core.TogglePreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ Class getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.settings.widget.SettingsMainSwitchPreferenceController, com.android.settings.core.TogglePreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.settings.widget.SettingsMainSwitchPreferenceController, com.android.settings.core.TogglePreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.settings.widget.SettingsMainSwitchPreferenceController, com.android.settings.core.TogglePreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public TouchFeedbackMainSwitchPreferenceController(Context context, String str) {
        super(context, str);
        this.mVibrator = (Vibrator) context.getSystemService(Vibrator.class);
        this.mSettingObserver = new ContentObserver(new Handler(true)) { // from class: com.android.settings.accessibility.TouchFeedbackMainSwitchPreferenceController.1
            @Override // android.database.ContentObserver
            public void onChange(boolean z, Uri uri) {
                TouchFeedbackMainSwitchPreferenceController touchFeedbackMainSwitchPreferenceController = TouchFeedbackMainSwitchPreferenceController.this;
                touchFeedbackMainSwitchPreferenceController.updateState(((SettingsMainSwitchPreferenceController) touchFeedbackMainSwitchPreferenceController).mSwitchPreference);
            }
        };
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStart
    public void onStart() {
        this.mContext.getContentResolver().registerContentObserver(Settings.System.getUriFor("haptic_feedback_enabled"), false, this.mSettingObserver);
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStop
    public void onStop() {
        this.mContext.getContentResolver().unregisterContentObserver(this.mSettingObserver);
    }

    @Override // com.android.settings.core.TogglePreferenceController
    public boolean isChecked() {
        return Settings.System.getInt(this.mContext.getContentResolver(), "haptic_feedback_enabled", 1) == 1;
    }

    @Override // com.android.settings.core.TogglePreferenceController
    public boolean setChecked(boolean z) {
        boolean putInt = Settings.System.putInt(this.mContext.getContentResolver(), "haptic_feedback_enabled", z ? 1 : 0);
        if (putInt && z) {
            VibrationPreferenceConfig.playVibrationPreview(this.mVibrator, 18);
        }
        return putInt;
    }

    @Override // com.android.settings.core.TogglePreferenceController, com.android.settings.slices.Sliceable
    public int getSliceHighlightMenuRes() {
        return R.string.menu_key_accessibility;
    }
}
