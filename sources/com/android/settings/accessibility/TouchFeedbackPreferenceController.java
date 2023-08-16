package com.android.settings.accessibility;

import android.content.ContentResolver;
import android.content.Context;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.R;
import com.android.settings.core.BasePreferenceController;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.core.lifecycle.events.OnStop;
/* loaded from: classes.dex */
public class TouchFeedbackPreferenceController extends BasePreferenceController implements LifecycleObserver, OnStart, OnStop {
    private Preference mPreference;
    private SettingObserver mSettingObserver;

    @Override // com.android.settings.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return 0;
    }

    @Override // com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ Class getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ int getSliceHighlightMenuRes() {
        return super.getSliceHighlightMenuRes();
    }

    @Override // com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isPublicSlice() {
        return super.isPublicSlice();
    }

    @Override // com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isSliceable() {
        return super.isSliceable();
    }

    @Override // com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public TouchFeedbackPreferenceController(Context context, String str) {
        super(context, str);
    }

    @Override // com.android.settings.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreference = preferenceScreen.findPreference(getPreferenceKey());
        this.mSettingObserver = new SettingObserver();
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStart
    public void onStart() {
        SettingObserver settingObserver = this.mSettingObserver;
        if (settingObserver != null) {
            settingObserver.register(this.mContext.getContentResolver());
            updatePreference();
        }
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStop
    public void onStop() {
        SettingObserver settingObserver = this.mSettingObserver;
        if (settingObserver != null) {
            settingObserver.unregister(this.mContext.getContentResolver());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePreference() {
        int i;
        boolean z = Settings.System.getInt(this.mContext.getContentResolver(), "vibrate_on", 1) == 1;
        boolean z2 = Settings.System.getInt(this.mContext.getContentResolver(), "haptic_feedback_enabled", 1) == 1;
        this.mPreference.setEnabled(z);
        Preference preference = this.mPreference;
        if (z && z2) {
            i = R.string.accessibility_vibration_settings_state_on;
        } else {
            i = R.string.accessibility_vibration_settings_state_off;
        }
        preference.setSummary(i);
    }

    /* loaded from: classes.dex */
    private final class SettingObserver extends ContentObserver {
        private final Uri mMainSettingUri;
        private final Uri mTouchFeedbackUri;

        SettingObserver() {
            super(new Handler());
            this.mMainSettingUri = Settings.System.getUriFor("vibrate_on");
            this.mTouchFeedbackUri = Settings.System.getUriFor("haptic_feedback_enabled");
        }

        public void register(ContentResolver contentResolver) {
            contentResolver.registerContentObserver(this.mMainSettingUri, false, this);
            contentResolver.registerContentObserver(this.mTouchFeedbackUri, false, this);
        }

        public void unregister(ContentResolver contentResolver) {
            contentResolver.unregisterContentObserver(this);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z, Uri uri) {
            TouchFeedbackPreferenceController.this.updatePreference();
        }
    }
}
