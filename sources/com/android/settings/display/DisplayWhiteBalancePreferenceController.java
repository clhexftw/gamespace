package com.android.settings.display;

import android.app.ActivityManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.hardware.display.ColorDisplayManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.R;
import com.android.settings.core.TogglePreferenceController;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.core.lifecycle.events.OnStop;
/* loaded from: classes.dex */
public class DisplayWhiteBalancePreferenceController extends TogglePreferenceController implements LifecycleObserver, OnStart, OnStop {
    private ColorDisplayManager mColorDisplayManager;
    ContentObserver mContentObserver;
    private Preference mPreference;

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

    public DisplayWhiteBalancePreferenceController(Context context, String str) {
        super(context, str);
    }

    @Override // com.android.settings.core.BasePreferenceController
    public int getAvailabilityStatus() {
        getColorDisplayManager();
        return ColorDisplayManager.isDisplayWhiteBalanceAvailable(this.mContext) ? 0 : 4;
    }

    @Override // com.android.settings.core.TogglePreferenceController
    public boolean isChecked() {
        return getColorDisplayManager().isDisplayWhiteBalanceEnabled();
    }

    @Override // com.android.settings.core.TogglePreferenceController
    public boolean setChecked(boolean z) {
        return getColorDisplayManager().setDisplayWhiteBalanceEnabled(z);
    }

    @Override // com.android.settings.core.TogglePreferenceController, com.android.settings.slices.Sliceable
    public int getSliceHighlightMenuRes() {
        return R.string.menu_key_display;
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStart
    public void onStart() {
        if (isAvailable()) {
            ContentResolver contentResolver = this.mContext.getContentResolver();
            this.mContentObserver = new ContentObserver(new Handler(Looper.getMainLooper())) { // from class: com.android.settings.display.DisplayWhiteBalancePreferenceController.1
                @Override // android.database.ContentObserver
                public void onChange(boolean z, Uri uri) {
                    super.onChange(z, uri);
                    DisplayWhiteBalancePreferenceController.this.updateVisibility();
                }
            };
            contentResolver.registerContentObserver(Settings.Secure.getUriFor("accessibility_display_inversion_enabled"), false, this.mContentObserver, ActivityManager.getCurrentUser());
            contentResolver.registerContentObserver(Settings.Secure.getUriFor("accessibility_display_daltonizer_enabled"), false, this.mContentObserver, ActivityManager.getCurrentUser());
            contentResolver.registerContentObserver(Settings.System.getUriFor("display_color_mode"), false, this.mContentObserver, ActivityManager.getCurrentUser());
            updateVisibility();
        }
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStop
    public void onStop() {
        if (this.mContentObserver != null) {
            this.mContext.getContentResolver().unregisterContentObserver(this.mContentObserver);
            this.mContentObserver = null;
        }
    }

    @Override // com.android.settings.core.TogglePreferenceController, com.android.settings.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreference = preferenceScreen.findPreference(getPreferenceKey());
    }

    ColorDisplayManager getColorDisplayManager() {
        if (this.mColorDisplayManager == null) {
            this.mColorDisplayManager = (ColorDisplayManager) this.mContext.getSystemService(ColorDisplayManager.class);
        }
        return this.mColorDisplayManager;
    }

    void updateVisibility() {
        if (this.mPreference != null) {
            this.mPreference.setVisible((getColorDisplayManager().getColorMode() == 2 || ColorDisplayManager.areAccessibilityTransformsEnabled(this.mContext)) ? false : true);
        }
    }
}
