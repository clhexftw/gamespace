package com.android.settings.fuelgauge.batteryusage;

import android.app.Activity;
import android.os.BatteryUsageStats;
import android.os.Bundle;
import android.os.UserManager;
import android.util.Log;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import com.android.settings.dashboard.DashboardFragment;
import com.android.settings.fuelgauge.BatteryBroadcastReceiver;
import com.android.settings.fuelgauge.BatteryUtils;
/* loaded from: classes.dex */
public abstract class PowerUsageBase extends DashboardFragment {
    static final String KEY_INCLUDE_HISTORY = "include_history";
    static final String KEY_REFRESH_TYPE = "refresh_type";
    private BatteryBroadcastReceiver mBatteryBroadcastReceiver;
    BatteryUsageStats mBatteryUsageStats;
    protected UserManager mUm;
    protected boolean mIsBatteryPresent = true;
    final BatteryUsageStatsLoaderCallbacks mBatteryUsageStatsLoaderCallbacks = new BatteryUsageStatsLoaderCallbacks();

    protected abstract boolean isBatteryHistoryNeeded();

    protected abstract void refreshUi(int i);

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mUm = (UserManager) activity.getSystemService("user");
    }

    @Override // com.android.settings.dashboard.DashboardFragment, com.android.settings.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        BatteryBroadcastReceiver batteryBroadcastReceiver = new BatteryBroadcastReceiver(getContext());
        this.mBatteryBroadcastReceiver = batteryBroadcastReceiver;
        batteryBroadcastReceiver.setBatteryChangedListener(new BatteryBroadcastReceiver.OnBatteryChangedListener() { // from class: com.android.settings.fuelgauge.batteryusage.PowerUsageBase$$ExternalSyntheticLambda0
            @Override // com.android.settings.fuelgauge.BatteryBroadcastReceiver.OnBatteryChangedListener
            public final void onBatteryChanged(int i) {
                PowerUsageBase.this.lambda$onCreate$0(i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$0(int i) {
        if (i == 5) {
            this.mIsBatteryPresent = false;
        }
        restartBatteryStatsLoader(i);
    }

    @Override // com.android.settings.dashboard.DashboardFragment, com.android.settings.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        this.mBatteryBroadcastReceiver.register();
    }

    @Override // com.android.settings.dashboard.DashboardFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        this.mBatteryBroadcastReceiver.unRegister();
        closeBatteryUsageStatsIfNeeded();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void restartBatteryStatsLoader(int i) {
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_REFRESH_TYPE, i);
        bundle.putBoolean(KEY_INCLUDE_HISTORY, isBatteryHistoryNeeded());
        restartLoader(0, bundle, this.mBatteryUsageStatsLoaderCallbacks);
    }

    protected LoaderManager getLoaderManagerForCurrentFragment() {
        return LoaderManager.getInstance(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void restartLoader(int i, Bundle bundle, LoaderManager.LoaderCallbacks<?> loaderCallbacks) {
        LoaderManager loaderManagerForCurrentFragment = getLoaderManagerForCurrentFragment();
        Loader loader = loaderManagerForCurrentFragment.getLoader(i);
        if (loader != null && !loader.isReset()) {
            loaderManagerForCurrentFragment.restartLoader(i, bundle, loaderCallbacks);
        } else {
            loaderManagerForCurrentFragment.initLoader(i, bundle, loaderCallbacks);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onLoadFinished(int i) {
        refreshUi(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void updatePreference(BatteryHistoryPreference batteryHistoryPreference) {
        long currentTimeMillis = System.currentTimeMillis();
        batteryHistoryPreference.setBatteryUsageStats(this.mBatteryUsageStats);
        BatteryUtils.logRuntime("PowerUsageBase", "updatePreference", currentTimeMillis);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class BatteryUsageStatsLoaderCallbacks implements LoaderManager.LoaderCallbacks<BatteryUsageStats> {
        private int mRefreshType;

        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public void onLoaderReset(Loader<BatteryUsageStats> loader) {
        }

        private BatteryUsageStatsLoaderCallbacks() {
        }

        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public Loader<BatteryUsageStats> onCreateLoader(int i, Bundle bundle) {
            this.mRefreshType = bundle.getInt(PowerUsageBase.KEY_REFRESH_TYPE);
            return new BatteryUsageStatsLoader(PowerUsageBase.this.getContext(), bundle.getBoolean(PowerUsageBase.KEY_INCLUDE_HISTORY));
        }

        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public void onLoadFinished(Loader<BatteryUsageStats> loader, BatteryUsageStats batteryUsageStats) {
            PowerUsageBase.this.closeBatteryUsageStatsIfNeeded();
            PowerUsageBase powerUsageBase = PowerUsageBase.this;
            powerUsageBase.mBatteryUsageStats = batteryUsageStats;
            powerUsageBase.onLoadFinished(this.mRefreshType);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void closeBatteryUsageStatsIfNeeded() {
        BatteryUsageStats batteryUsageStats = this.mBatteryUsageStats;
        if (batteryUsageStats == null) {
            return;
        }
        try {
            try {
                batteryUsageStats.close();
            } catch (Exception e) {
                Log.e("PowerUsageBase", "BatteryUsageStats.close() failed", e);
            }
        } finally {
            this.mBatteryUsageStats = null;
        }
    }
}
