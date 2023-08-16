package com.android.settings.display;

import android.content.ContentResolver;
import android.content.Context;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import androidx.preference.PreferenceScreen;
import com.android.settings.core.BasePreferenceController;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.core.lifecycle.events.OnStop;
import org.nameless.custom.preference.SystemSettingSwitchPreference;
/* loaded from: classes.dex */
public class GesturesDisableInLandscapePreferenceController extends BasePreferenceController implements LifecycleObserver, OnStart, OnStop {
    private SystemSettingSwitchPreference mPreference;
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

    public GesturesDisableInLandscapePreferenceController(Context context, String str) {
        super(context, str);
    }

    @Override // com.android.settings.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreference = (SystemSettingSwitchPreference) preferenceScreen.findPreference(getPreferenceKey());
        this.mSettingObserver = new SettingObserver();
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStart
    public void onStart() {
        SettingObserver settingObserver = this.mSettingObserver;
        if (settingObserver != null) {
            settingObserver.register(this.mContext.getContentResolver());
            this.mSettingObserver.onChange(false, null);
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
    public void updateEnabledState() {
        boolean z = false;
        boolean z2 = Settings.Secure.getIntForUser(this.mContext.getContentResolver(), "double_tap_to_wake", 0, -2) == 1;
        boolean z3 = Settings.System.getIntForUser(this.mContext.getContentResolver(), "status_bar_brightness_control", 0, -2) == 1;
        SystemSettingSwitchPreference systemSettingSwitchPreference = this.mPreference;
        if (z2 || z3) {
            z = true;
        }
        systemSettingSwitchPreference.setEnabled(z);
    }

    /* loaded from: classes.dex */
    private class SettingObserver extends ContentObserver {
        private final Uri mBrightnessControlUri;
        private final Uri mDoubleTapGestureUri;

        SettingObserver() {
            super(new Handler());
            this.mDoubleTapGestureUri = Settings.Secure.getUriFor("double_tap_to_wake");
            this.mBrightnessControlUri = Settings.System.getUriFor("status_bar_brightness_control");
        }

        public void register(ContentResolver contentResolver) {
            contentResolver.registerContentObserver(this.mDoubleTapGestureUri, false, this);
            contentResolver.registerContentObserver(this.mBrightnessControlUri, false, this);
        }

        public void unregister(ContentResolver contentResolver) {
            contentResolver.unregisterContentObserver(this);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z, Uri uri) {
            super.onChange(z, uri);
            GesturesDisableInLandscapePreferenceController.this.updateEnabledState();
        }
    }
}
