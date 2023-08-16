package com.android.settings.display.refreshrate;

import android.content.ContentResolver;
import android.content.Context;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.core.BasePreferenceController;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.core.lifecycle.events.OnStop;
import java.util.ArrayList;
import org.nameless.display.DisplayRefreshRateHelper;
/* loaded from: classes.dex */
public class ScreenRefreshRateController extends BasePreferenceController implements LifecycleObserver, OnStart, OnStop {
    private final DisplayRefreshRateHelper mHelper;
    private Preference mPreference;
    private SettingObserver mSettingObserver;

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

    public ScreenRefreshRateController(Context context, String str) {
        super(context, str);
        this.mHelper = DisplayRefreshRateHelper.getInstance(context);
    }

    @Override // com.android.settings.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return this.mHelper.getSupportedRefreshRateList().size() > 1 ? 0 : 3;
    }

    @Override // com.android.settings.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreference = preferenceScreen.findPreference(getPreferenceKey());
        this.mSettingObserver = new SettingObserver(this.mPreference);
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStart
    public void onStart() {
        SettingObserver settingObserver = this.mSettingObserver;
        if (settingObserver != null) {
            settingObserver.register(this.mContext.getContentResolver());
            this.mSettingObserver.onChange(false);
        }
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStop
    public void onStop() {
        SettingObserver settingObserver = this.mSettingObserver;
        if (settingObserver != null) {
            settingObserver.unregister(this.mContext.getContentResolver());
        }
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        String str;
        int minimumRefreshRate = this.mHelper.getMinimumRefreshRate();
        int peakRefreshRate = this.mHelper.getPeakRefreshRate();
        boolean z = Settings.System.getIntForUser(this.mContext.getContentResolver(), "extreme_refresh_rate", 0, -2) != 0;
        ArrayList supportedRefreshRateList = this.mHelper.getSupportedRefreshRateList();
        if (z && supportedRefreshRateList.size() > 0) {
            int intValue = ((Integer) supportedRefreshRateList.get(supportedRefreshRateList.size() - 1)).intValue();
            str = String.valueOf(intValue) + " Hz";
        } else if (minimumRefreshRate == peakRefreshRate) {
            str = String.valueOf(peakRefreshRate) + " Hz";
        } else {
            str = String.valueOf(minimumRefreshRate) + " ~ " + String.valueOf(peakRefreshRate) + " Hz";
        }
        if (str != null) {
            preference.setSummary(str);
        }
        super.updateState(preference);
    }

    /* loaded from: classes.dex */
    private class SettingObserver extends ContentObserver {
        private final Uri mExtremeUri;
        private final Uri mMinUri;
        private final Uri mPeakUri;
        private final Preference mPreference;

        SettingObserver(Preference preference) {
            super(new Handler());
            this.mMinUri = Settings.System.getUriFor("min_refresh_rate");
            this.mPeakUri = Settings.System.getUriFor("peak_refresh_rate");
            this.mExtremeUri = Settings.System.getUriFor("extreme_refresh_rate");
            this.mPreference = preference;
        }

        public void register(ContentResolver contentResolver) {
            contentResolver.registerContentObserver(this.mMinUri, false, this);
            contentResolver.registerContentObserver(this.mPeakUri, false, this);
        }

        public void unregister(ContentResolver contentResolver) {
            contentResolver.unregisterContentObserver(this);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            ScreenRefreshRateController.this.updateState(this.mPreference);
        }
    }
}
