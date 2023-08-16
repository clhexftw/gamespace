package com.android.settings.display.refreshrate;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import com.android.settings.R;
import com.android.settings.dashboard.DashboardFragment;
/* loaded from: classes.dex */
public class ScreenRefreshRateFragment extends DashboardFragment {
    private final SettingsObserver mObserver = new SettingsObserver();

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settings.dashboard.DashboardFragment
    public String getLogTag() {
        return "ScreenRefreshRateFragment";
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1999;
    }

    /* loaded from: classes.dex */
    private final class SettingsObserver extends ContentObserver {
        private final Uri mExtremeUri;
        private final Uri mMinUri;
        private final Uri mPeakUri;

        SettingsObserver() {
            super(new Handler());
            this.mMinUri = Settings.System.getUriFor("min_refresh_rate");
            this.mPeakUri = Settings.System.getUriFor("peak_refresh_rate");
            this.mExtremeUri = Settings.System.getUriFor("extreme_refresh_rate");
        }

        void register(ContentResolver contentResolver) {
            contentResolver.registerContentObserver(this.mMinUri, false, this);
            contentResolver.registerContentObserver(this.mPeakUri, false, this);
            contentResolver.registerContentObserver(this.mExtremeUri, false, this);
        }

        void unregister(ContentResolver contentResolver) {
            contentResolver.unregisterContentObserver(this);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z, Uri uri) {
            ScreenRefreshRateFragment.this.updatePreferenceStates();
        }
    }

    @Override // com.android.settings.dashboard.DashboardFragment, com.android.settings.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mObserver.register(getContext().getContentResolver());
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        this.mObserver.unregister(getContext().getContentResolver());
        super.onDestroy();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settings.dashboard.DashboardFragment, com.android.settings.core.InstrumentedPreferenceFragment
    public int getPreferenceScreenResId() {
        return R.xml.screen_refresh_rate;
    }
}
