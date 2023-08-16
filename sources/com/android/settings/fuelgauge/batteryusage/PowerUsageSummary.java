package com.android.settings.fuelgauge.batteryusage;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.preference.Preference;
import com.android.internal.app.IBatteryStats;
import com.android.settings.R;
import com.android.settings.SettingsActivity;
import com.android.settings.Utils;
import com.android.settings.core.SubSettingLauncher;
import com.android.settings.fuelgauge.BatteryHeaderPreferenceController;
import com.android.settings.fuelgauge.BatteryInfo;
import com.android.settings.fuelgauge.BatteryInfoLoader;
import com.android.settings.fuelgauge.BatteryUtils;
import com.android.settings.fuelgauge.PowerUsageFeatureProvider;
import com.android.settings.fuelgauge.batterytip.BatteryTipLoader;
import com.android.settings.fuelgauge.batterytip.BatteryTipPreferenceController;
import com.android.settings.fuelgauge.batterytip.tips.BatteryTip;
import com.android.settings.overlay.FeatureFactory;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settingslib.widget.LayoutPreference;
import java.util.List;
/* loaded from: classes.dex */
public class PowerUsageSummary extends PowerUsageBase implements BatteryTipPreferenceController.BatteryTipListener {
    static final String KEY_BATTERY_ERROR = "battery_help_message";
    static final String KEY_BATTERY_USAGE = "battery_usage_summary";
    public static final BaseSearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider(R.xml.power_usage_summary);
    BatteryHeaderPreferenceController mBatteryHeaderPreferenceController;
    BatteryInfo mBatteryInfo;
    LayoutPreference mBatteryLayoutPref;
    private IBatteryStats mBatteryStats;
    PowerGaugePreference mBatteryTempPref;
    BatteryTipPreferenceController mBatteryTipPreferenceController;
    Preference mBatteryUsagePreference;
    BatteryUtils mBatteryUtils;
    Preference mHelpPreference;
    boolean mNeedUpdateBatteryTip;
    PowerUsageFeatureProvider mPowerFeatureProvider;
    final ContentObserver mSettingsObserver = new ContentObserver(new Handler()) { // from class: com.android.settings.fuelgauge.batteryusage.PowerUsageSummary.1
        @Override // android.database.ContentObserver
        public void onChange(boolean z, Uri uri) {
            PowerUsageSummary.this.restartBatteryInfoLoader();
        }
    };
    LoaderManager.LoaderCallbacks<BatteryInfo> mBatteryInfoLoaderCallbacks = new LoaderManager.LoaderCallbacks<BatteryInfo>() { // from class: com.android.settings.fuelgauge.batteryusage.PowerUsageSummary.2
        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public void onLoaderReset(Loader<BatteryInfo> loader) {
        }

        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public Loader<BatteryInfo> onCreateLoader(int i, Bundle bundle) {
            return new BatteryInfoLoader(PowerUsageSummary.this.getContext());
        }

        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public void onLoadFinished(Loader<BatteryInfo> loader, BatteryInfo batteryInfo) {
            PowerUsageSummary.this.mBatteryHeaderPreferenceController.updateHeaderPreference(batteryInfo);
            PowerUsageSummary powerUsageSummary = PowerUsageSummary.this;
            powerUsageSummary.mBatteryHeaderPreferenceController.updateHeaderByBatteryTips(powerUsageSummary.mBatteryTipPreferenceController.getCurrentBatteryTip(), batteryInfo);
            PowerUsageSummary.this.mBatteryInfo = batteryInfo;
        }
    };
    private LoaderManager.LoaderCallbacks<List<BatteryTip>> mBatteryTipsCallbacks = new LoaderManager.LoaderCallbacks<List<BatteryTip>>() { // from class: com.android.settings.fuelgauge.batteryusage.PowerUsageSummary.3
        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public void onLoaderReset(Loader<List<BatteryTip>> loader) {
        }

        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public Loader<List<BatteryTip>> onCreateLoader(int i, Bundle bundle) {
            return new BatteryTipLoader(PowerUsageSummary.this.getContext(), PowerUsageSummary.this.mBatteryUsageStats);
        }

        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public void onLoadFinished(Loader<List<BatteryTip>> loader, List<BatteryTip> list) {
            PowerUsageSummary.this.mBatteryTipPreferenceController.updateBatteryTips(list);
            PowerUsageSummary powerUsageSummary = PowerUsageSummary.this;
            powerUsageSummary.mBatteryHeaderPreferenceController.updateHeaderByBatteryTips(powerUsageSummary.mBatteryTipPreferenceController.getCurrentBatteryTip(), PowerUsageSummary.this.mBatteryInfo);
        }
    };

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settings.dashboard.DashboardFragment
    public String getLogTag() {
        return "PowerUsageSummary";
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1263;
    }

    @Override // com.android.settings.fuelgauge.batteryusage.PowerUsageBase
    protected boolean isBatteryHistoryNeeded() {
        return false;
    }

    @Override // com.android.settings.dashboard.DashboardFragment, com.android.settings.SettingsPreferenceFragment, com.android.settings.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        SettingsActivity settingsActivity = (SettingsActivity) getActivity();
        BatteryHeaderPreferenceController batteryHeaderPreferenceController = (BatteryHeaderPreferenceController) use(BatteryHeaderPreferenceController.class);
        this.mBatteryHeaderPreferenceController = batteryHeaderPreferenceController;
        batteryHeaderPreferenceController.setActivity(settingsActivity);
        this.mBatteryHeaderPreferenceController.setFragment(this);
        this.mBatteryHeaderPreferenceController.setLifecycle(getSettingsLifecycle());
        BatteryTipPreferenceController batteryTipPreferenceController = (BatteryTipPreferenceController) use(BatteryTipPreferenceController.class);
        this.mBatteryTipPreferenceController = batteryTipPreferenceController;
        batteryTipPreferenceController.setActivity(settingsActivity);
        this.mBatteryTipPreferenceController.setFragment(this);
        this.mBatteryTipPreferenceController.setBatteryTipListener(new BatteryTipPreferenceController.BatteryTipListener() { // from class: com.android.settings.fuelgauge.batteryusage.PowerUsageSummary$$ExternalSyntheticLambda0
            @Override // com.android.settings.fuelgauge.batterytip.BatteryTipPreferenceController.BatteryTipListener
            public final void onBatteryTipHandled(BatteryTip batteryTip) {
                PowerUsageSummary.this.onBatteryTipHandled(batteryTip);
            }
        });
    }

    @Override // com.android.settings.fuelgauge.batteryusage.PowerUsageBase, com.android.settings.dashboard.DashboardFragment, com.android.settings.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setAnimationAllowed(true);
        initFeatureProvider();
        initPreference();
        this.mBatteryTempPref = (PowerGaugePreference) findPreference("battery_temperature");
        this.mBatteryUtils = BatteryUtils.getInstance(getContext());
        this.mBatteryStats = IBatteryStats.Stub.asInterface(ServiceManager.getService("batterystats"));
        if (Utils.isBatteryPresent(getContext())) {
            restartBatteryInfoLoader();
        } else {
            this.mHelpPreference.setVisible(true);
        }
        this.mBatteryTipPreferenceController.restoreInstanceState(bundle);
        updateBatteryTipFlag(bundle);
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menu.add(0, 2, 0, R.string.battery_stats_reset).setIcon(R.drawable.ic_reset).setAlphabeticShortcut('r').setShowAsAction(1);
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 2) {
            resetStats();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override // com.android.settings.dashboard.DashboardFragment, com.android.settings.core.InstrumentedPreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.preference.PreferenceManager.OnPreferenceTreeClickListener
    public boolean onPreferenceTreeClick(Preference preference) {
        if ("battery_header".equals(preference.getKey())) {
            new SubSettingLauncher(getContext()).setDestination(PowerUsageAdvanced.class.getName()).setSourceMetricsCategory(getMetricsCategory()).setTitleRes(R.string.advanced_battery_title).launch();
            return true;
        }
        return super.onPreferenceTreeClick(preference);
    }

    @Override // com.android.settings.dashboard.DashboardFragment, com.android.settings.SettingsPreferenceFragment, com.android.settings.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        getContentResolver().registerContentObserver(Settings.Global.getUriFor("battery_estimates_last_update_time"), false, this.mSettingsObserver);
    }

    @Override // com.android.settings.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onPause() {
        getContentResolver().unregisterContentObserver(this.mSettingsObserver);
        super.onPause();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settings.dashboard.DashboardFragment, com.android.settings.core.InstrumentedPreferenceFragment
    public int getPreferenceScreenResId() {
        return R.xml.power_usage_summary;
    }

    @Override // com.android.settings.support.actionbar.HelpResourceProvider
    public int getHelpResource() {
        return R.string.help_url_battery;
    }

    private void resetStats() {
        new AlertDialog.Builder(getActivity()).setTitle(R.string.battery_stats_reset).setMessage(R.string.battery_stats_message).setPositiveButton(17039370, new DialogInterface.OnClickListener() { // from class: com.android.settings.fuelgauge.batteryusage.PowerUsageSummary.4
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                try {
                    PowerUsageSummary.this.mBatteryStats.resetStatistics();
                } catch (RemoteException unused) {
                    Log.e("PowerUsageSummary", "Failed to reset battery statistics");
                }
                PowerUsageSummary.this.refreshUi(0);
            }
        }).setNegativeButton(R.string.cancel, (DialogInterface.OnClickListener) null).create().show();
    }

    @Override // com.android.settings.fuelgauge.batteryusage.PowerUsageBase
    protected void refreshUi(int i) {
        if (getContext() != null && this.mIsBatteryPresent) {
            if (this.mNeedUpdateBatteryTip && i != 1) {
                restartBatteryTipLoader();
            } else {
                this.mNeedUpdateBatteryTip = true;
            }
            restartBatteryInfoLoader();
            if (BatteryInfo.batteryTemp != 0.0f) {
                PowerGaugePreference powerGaugePreference = this.mBatteryTempPref;
                powerGaugePreference.setSummary((BatteryInfo.batteryTemp / 10.0f) + " °C");
                return;
            }
            this.mBatteryTempPref.setSummary(getResources().getString(R.string.status_unavailable));
        }
    }

    void restartBatteryTipLoader() {
        restartLoader(2, Bundle.EMPTY, this.mBatteryTipsCallbacks);
    }

    void setBatteryLayoutPreference(LayoutPreference layoutPreference) {
        this.mBatteryLayoutPref = layoutPreference;
    }

    void initFeatureProvider() {
        Context context = getContext();
        this.mPowerFeatureProvider = FeatureFactory.getFactory(context).getPowerUsageFeatureProvider(context);
    }

    void initPreference() {
        Preference findPreference = findPreference(KEY_BATTERY_USAGE);
        this.mBatteryUsagePreference = findPreference;
        findPreference.setSummary(getString(R.string.advanced_battery_preference_summary));
        Preference findPreference2 = findPreference(KEY_BATTERY_ERROR);
        this.mHelpPreference = findPreference2;
        findPreference2.setVisible(false);
    }

    void restartBatteryInfoLoader() {
        if (getContext() != null && this.mIsBatteryPresent) {
            restartLoader(1, Bundle.EMPTY, this.mBatteryInfoLoaderCallbacks);
        }
    }

    void updateBatteryTipFlag(Bundle bundle) {
        this.mNeedUpdateBatteryTip = bundle == null || this.mBatteryTipPreferenceController.needUpdate();
    }

    @Override // com.android.settings.fuelgauge.batteryusage.PowerUsageBase
    protected void restartBatteryStatsLoader(int i) {
        super.restartBatteryStatsLoader(i);
        if (this.mIsBatteryPresent) {
            this.mBatteryHeaderPreferenceController.quickUpdateHeaderPreference();
        }
    }

    @Override // com.android.settings.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.mBatteryTipPreferenceController.saveInstanceState(bundle);
    }

    @Override // com.android.settings.fuelgauge.batterytip.BatteryTipPreferenceController.BatteryTipListener
    public void onBatteryTipHandled(BatteryTip batteryTip) {
        restartBatteryTipLoader();
    }
}
