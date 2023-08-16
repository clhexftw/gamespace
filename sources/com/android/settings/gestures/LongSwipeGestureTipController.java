package com.android.settings.gestures;

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
import com.android.settingslib.widget.FooterPreference;
/* loaded from: classes.dex */
public class LongSwipeGestureTipController extends BasePreferenceController implements LifecycleObserver, OnStart, OnStop {
    private static final String KEY_BACK_GESTURE_ARROW = "back_gesture_arrow";
    private FooterPreference mPreference;
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
    public int getSliceHighlightMenuRes() {
        return 0;
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

    public LongSwipeGestureTipController(Context context, String str) {
        super(context, str);
    }

    @Override // com.android.settings.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreference = (FooterPreference) preferenceScreen.findPreference(getPreferenceKey());
        this.mSettingObserver = new SettingObserver(this.mContext.getContentResolver(), this.mPreference);
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStart
    public void onStart() {
        SettingObserver settingObserver = this.mSettingObserver;
        if (settingObserver != null) {
            settingObserver.register();
            this.mSettingObserver.onChange(false, null);
        }
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStop
    public void onStop() {
        SettingObserver settingObserver = this.mSettingObserver;
        if (settingObserver != null) {
            settingObserver.unregister();
        }
    }

    /* loaded from: classes.dex */
    private class SettingObserver extends ContentObserver {
        private final Preference mPreference;
        private final ContentResolver mResolver;
        private final Uri mUri;

        SettingObserver(ContentResolver contentResolver, Preference preference) {
            super(new Handler());
            this.mUri = Settings.Secure.getUriFor(LongSwipeGestureTipController.KEY_BACK_GESTURE_ARROW);
            this.mResolver = contentResolver;
            this.mPreference = preference;
        }

        public void register() {
            this.mResolver.registerContentObserver(this.mUri, false, this);
        }

        public void unregister() {
            this.mResolver.unregisterContentObserver(this);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z, Uri uri) {
            int i;
            if (uri == null || this.mUri.equals(uri)) {
                boolean z2 = Settings.Secure.getInt(this.mResolver, LongSwipeGestureTipController.KEY_BACK_GESTURE_ARROW, 0) == 1;
                Preference preference = this.mPreference;
                if (z2) {
                    i = R.string.long_swipe_action_tip_has_arrow;
                } else {
                    i = R.string.long_swipe_action_tip_no_arrow;
                }
                preference.setTitle(i);
            }
        }
    }
}
