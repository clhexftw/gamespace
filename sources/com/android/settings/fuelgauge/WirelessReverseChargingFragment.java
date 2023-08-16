package com.android.settings.fuelgauge;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import com.android.settings.R;
import com.android.settings.dashboard.DashboardFragment;
/* loaded from: classes.dex */
public class WirelessReverseChargingFragment extends DashboardFragment {
    private final SettingsObserver mObserver = new SettingsObserver();

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settings.dashboard.DashboardFragment
    public String getLogTag() {
        return "WirelessReverseChargingFragment";
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1999;
    }

    /* loaded from: classes.dex */
    private final class SettingsObserver extends ContentObserver {
        private final Uri mEnabledUri;
        private final Uri mLevelUri;
        private final Uri mSuspendedUri;

        SettingsObserver() {
            super(new Handler());
            this.mEnabledUri = Settings.System.getUriFor("wireless_reverse_charging_enabled");
            this.mSuspendedUri = Settings.System.getUriFor("wireless_reverse_charging_suspended_status");
            this.mLevelUri = Settings.System.getUriFor("wireless_reverse_charging_min_level");
        }

        void register(ContentResolver contentResolver) {
            contentResolver.registerContentObserver(this.mEnabledUri, false, this);
            contentResolver.registerContentObserver(this.mSuspendedUri, false, this);
            contentResolver.registerContentObserver(this.mLevelUri, false, this);
        }

        void unregister(ContentResolver contentResolver) {
            contentResolver.unregisterContentObserver(this);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z, Uri uri) {
            WirelessReverseChargingFragment.this.updatePreferenceStates();
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
        return R.xml.wireless_reverse_charging;
    }
}
