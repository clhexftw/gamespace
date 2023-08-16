package com.android.settings.fuelgauge.batteryusage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.TextView;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceScreen;
import com.android.settings.R;
import com.android.settings.SettingsActivity;
import com.android.settings.core.InstrumentedPreferenceFragment;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settings.fuelgauge.AdvancedPowerUsageDetail;
import com.android.settings.fuelgauge.BatteryUtils;
import com.android.settings.fuelgauge.batteryusage.BatteryChartView;
import com.android.settings.fuelgauge.batteryusage.BatteryChartViewModel;
import com.android.settings.fuelgauge.batteryusage.BatteryLevelData;
import com.android.settings.fuelgauge.batteryusage.DataProcessor;
import com.android.settings.fuelgauge.batteryusage.ExpandDividerPreference;
import com.android.settings.overlay.FeatureFactory;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.core.instrumentation.MetricsFeatureProvider;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnCreate;
import com.android.settingslib.core.lifecycle.events.OnDestroy;
import com.android.settingslib.core.lifecycle.events.OnResume;
import com.android.settingslib.core.lifecycle.events.OnSaveInstanceState;
import com.android.settingslib.utils.StringUtil;
import com.android.settingslib.widget.FooterPreference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes.dex */
public class BatteryChartPreferenceController extends AbstractPreferenceController implements PreferenceControllerMixin, LifecycleObserver, OnCreate, OnDestroy, OnSaveInstanceState, OnResume, ExpandDividerPreference.OnExpandListener {
    private static int sUiMode;
    private final SettingsActivity mActivity;
    PreferenceGroup mAppListPrefGroup;
    private View mBatteryChartViewGroup;
    Map<Integer, Map<Integer, BatteryDiffData>> mBatteryUsageMap;
    BatteryUtils mBatteryUtils;
    private View mCategoryTitleView;
    private TextView mChartSummaryTextView;
    int mDailyChartIndex;
    final DailyChartLabelTextGenerator mDailyChartLabelTextGenerator;
    BatteryChartView mDailyChartView;
    private BatteryChartViewModel mDailyViewModel;
    ExpandDividerPreference mExpandDividerPreference;
    private FooterPreference mFooterPreference;
    private final InstrumentedPreferenceFragment mFragment;
    private final Handler mHandler;
    private final AnimatorListenerAdapter mHourlyChartFadeInAdapter;
    private final AnimatorListenerAdapter mHourlyChartFadeOutAdapter;
    int mHourlyChartIndex;
    final HourlyChartLabelTextGenerator mHourlyChartLabelTextGenerator;
    BatteryChartView mHourlyChartView;
    private boolean mHourlyChartVisible;
    private List<BatteryChartViewModel> mHourlyViewModels;
    private boolean mIs24HourFormat;
    boolean mIsExpanded;
    private boolean mIsFooterPrefAdded;
    private final MetricsFeatureProvider mMetricsFeatureProvider;
    private final CharSequence[] mNotAllowShowSummaryPackages;
    Context mPrefContext;
    final Map<String, Preference> mPreferenceCache;
    private final String mPreferenceKey;
    private PreferenceScreen mPreferenceScreen;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return true;
    }

    public BatteryChartPreferenceController(Context context, String str, Lifecycle lifecycle, SettingsActivity settingsActivity, InstrumentedPreferenceFragment instrumentedPreferenceFragment) {
        super(context);
        this.mIsExpanded = false;
        this.mDailyChartIndex = -1;
        this.mHourlyChartIndex = -1;
        this.mIsFooterPrefAdded = false;
        this.mHourlyChartVisible = true;
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mHourlyChartFadeInAdapter = createHourlyChartAnimatorListenerAdapter(true);
        this.mHourlyChartFadeOutAdapter = createHourlyChartAnimatorListenerAdapter(false);
        this.mDailyChartLabelTextGenerator = new DailyChartLabelTextGenerator();
        this.mHourlyChartLabelTextGenerator = new HourlyChartLabelTextGenerator();
        this.mPreferenceCache = new HashMap();
        this.mActivity = settingsActivity;
        this.mFragment = instrumentedPreferenceFragment;
        this.mPreferenceKey = str;
        this.mIs24HourFormat = DateFormat.is24HourFormat(context);
        this.mMetricsFeatureProvider = FeatureFactory.getFactory(this.mContext).getMetricsFeatureProvider();
        this.mNotAllowShowSummaryPackages = FeatureFactory.getFactory(context).getPowerUsageFeatureProvider(context).getHideApplicationSummary(context);
        if (lifecycle != null) {
            lifecycle.addObserver(this);
        }
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnCreate
    public void onCreate(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        this.mDailyChartIndex = bundle.getInt("daily_chart_index", this.mDailyChartIndex);
        this.mHourlyChartIndex = bundle.getInt("hourly_chart_index", this.mHourlyChartIndex);
        this.mIsExpanded = bundle.getBoolean("expand_system_info", this.mIsExpanded);
        Log.d("BatteryChartPreferenceController", String.format("onCreate() dailyIndex=%d hourlyIndex=%d isExpanded=%b", Integer.valueOf(this.mDailyChartIndex), Integer.valueOf(this.mHourlyChartIndex), Boolean.valueOf(this.mIsExpanded)));
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnResume
    public void onResume() {
        int i = this.mContext.getResources().getConfiguration().uiMode & 48;
        if (sUiMode != i) {
            sUiMode = i;
            BatteryDiffEntry.clearCache();
            Log.d("BatteryChartPreferenceController", "clear icon and label cache since uiMode is changed");
        }
        this.mIs24HourFormat = DateFormat.is24HourFormat(this.mContext);
        this.mMetricsFeatureProvider.action(this.mPrefContext, 1880, new Pair[0]);
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnSaveInstanceState
    public void onSaveInstanceState(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        bundle.putInt("daily_chart_index", this.mDailyChartIndex);
        bundle.putInt("hourly_chart_index", this.mHourlyChartIndex);
        bundle.putBoolean("expand_system_info", this.mIsExpanded);
        Log.d("BatteryChartPreferenceController", String.format("onSaveInstanceState() dailyIndex=%d hourlyIndex=%d isExpanded=%b", Integer.valueOf(this.mDailyChartIndex), Integer.valueOf(this.mHourlyChartIndex), Boolean.valueOf(this.mIsExpanded)));
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnDestroy
    public void onDestroy() {
        if (this.mActivity.isChangingConfigurations()) {
            BatteryDiffEntry.clearCache();
        }
        this.mHandler.removeCallbacksAndMessages(null);
        this.mPreferenceCache.clear();
        PreferenceGroup preferenceGroup = this.mAppListPrefGroup;
        if (preferenceGroup != null) {
            preferenceGroup.removeAll();
        }
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreferenceScreen = preferenceScreen;
        this.mPrefContext = preferenceScreen.getContext();
        PreferenceGroup preferenceGroup = (PreferenceGroup) preferenceScreen.findPreference(this.mPreferenceKey);
        this.mAppListPrefGroup = preferenceGroup;
        preferenceGroup.setOrderingAsAdded(false);
        this.mAppListPrefGroup.setTitle("");
        FooterPreference footerPreference = (FooterPreference) preferenceScreen.findPreference("battery_graph_footer");
        this.mFooterPreference = footerPreference;
        if (footerPreference != null) {
            preferenceScreen.removePreference(footerPreference);
        }
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return this.mPreferenceKey;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean handlePreferenceTreeClick(Preference preference) {
        if (preference instanceof PowerGaugePreference) {
            PowerGaugePreference powerGaugePreference = (PowerGaugePreference) preference;
            BatteryDiffEntry batteryDiffEntry = powerGaugePreference.getBatteryDiffEntry();
            BatteryHistEntry batteryHistEntry = batteryDiffEntry.mBatteryHistEntry;
            String str = batteryHistEntry.mPackageName;
            this.mMetricsFeatureProvider.action(1880, batteryHistEntry.isAppEntry() ? 1768 : 1769, 1880, TextUtils.isEmpty(str) ? "none" : str, (int) Math.round(batteryDiffEntry.getPercentOfTotal()));
            Log.d("BatteryChartPreferenceController", String.format("handleClick() label=%s key=%s package=%s", batteryDiffEntry.getAppLabel(), batteryHistEntry.getKey(), batteryHistEntry.mPackageName));
            AdvancedPowerUsageDetail.startBatteryDetailPage(this.mActivity, this.mFragment, batteryDiffEntry, powerGaugePreference.getPercent(), isValidToShowSummary(str), getSlotInformation());
            return true;
        }
        return false;
    }

    @Override // com.android.settings.fuelgauge.batteryusage.ExpandDividerPreference.OnExpandListener
    public void onExpand(boolean z) {
        this.mIsExpanded = z;
        this.mMetricsFeatureProvider.action(this.mPrefContext, 1770, z);
        refreshExpandUi();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setBatteryHistoryMap(Map<Long, Map<String, BatteryHistEntry>> map) {
        StringBuilder sb = new StringBuilder();
        sb.append("setBatteryHistoryMap() ");
        sb.append(map == null ? "null" : "size=" + map.size());
        Log.d("BatteryChartPreferenceController", sb.toString());
        animateBatteryChartViewGroup();
        BatteryLevelData batteryLevelData = DataProcessor.getBatteryLevelData(this.mContext, this.mHandler, map, new DataProcessor.UsageMapAsyncResponse() { // from class: com.android.settings.fuelgauge.batteryusage.BatteryChartPreferenceController$$ExternalSyntheticLambda5
            @Override // com.android.settings.fuelgauge.batteryusage.DataProcessor.UsageMapAsyncResponse
            public final void onBatteryUsageMapLoaded(Map map2) {
                BatteryChartPreferenceController.this.lambda$setBatteryHistoryMap$0(map2);
            }
        });
        Log.d("BatteryChartPreferenceController", "getBatteryLevelData: " + batteryLevelData);
        this.mMetricsFeatureProvider.action(this.mPrefContext, 1801, getTotalHours(batteryLevelData));
        if (batteryLevelData == null) {
            this.mDailyChartIndex = -1;
            this.mHourlyChartIndex = -1;
            this.mDailyViewModel = null;
            this.mHourlyViewModels = null;
            refreshUi();
            return;
        }
        this.mDailyViewModel = new BatteryChartViewModel(batteryLevelData.getDailyBatteryLevels().getLevels(), batteryLevelData.getDailyBatteryLevels().getTimestamps(), BatteryChartViewModel.AxisLabelPosition.CENTER_OF_TRAPEZOIDS, this.mDailyChartLabelTextGenerator);
        this.mHourlyViewModels = new ArrayList();
        for (BatteryLevelData.PeriodBatteryLevelData periodBatteryLevelData : batteryLevelData.getHourlyBatteryLevelsPerDay()) {
            this.mHourlyViewModels.add(new BatteryChartViewModel(periodBatteryLevelData.getLevels(), periodBatteryLevelData.getTimestamps(), BatteryChartViewModel.AxisLabelPosition.BETWEEN_TRAPEZOIDS, this.mHourlyChartLabelTextGenerator));
        }
        refreshUi();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setBatteryHistoryMap$0(Map map) {
        this.mBatteryUsageMap = map;
        refreshUi();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setBatteryChartView(final BatteryChartView batteryChartView, final BatteryChartView batteryChartView2) {
        View view = (View) batteryChartView.getParent();
        if (view != null && view.getId() == R.id.battery_chart_group) {
            this.mBatteryChartViewGroup = (View) batteryChartView.getParent();
        }
        if (this.mDailyChartView != batteryChartView || this.mHourlyChartView != batteryChartView2) {
            this.mHandler.post(new Runnable() { // from class: com.android.settings.fuelgauge.batteryusage.BatteryChartPreferenceController$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    BatteryChartPreferenceController.this.lambda$setBatteryChartView$1(batteryChartView, batteryChartView2);
                }
            });
            animateBatteryChartViewGroup();
        }
        View view2 = this.mBatteryChartViewGroup;
        if (view2 != null) {
            View view3 = (View) view2.getParent();
            this.mChartSummaryTextView = view3 != null ? (TextView) view3.findViewById(R.id.chart_summary) : null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setBatteryChartViewInner */
    public void lambda$setBatteryChartView$1(BatteryChartView batteryChartView, BatteryChartView batteryChartView2) {
        this.mDailyChartView = batteryChartView;
        batteryChartView.setOnSelectListener(new BatteryChartView.OnSelectListener() { // from class: com.android.settings.fuelgauge.batteryusage.BatteryChartPreferenceController$$ExternalSyntheticLambda2
            @Override // com.android.settings.fuelgauge.batteryusage.BatteryChartView.OnSelectListener
            public final void onSelect(int i) {
                BatteryChartPreferenceController.this.lambda$setBatteryChartViewInner$2(i);
            }
        });
        this.mHourlyChartView = batteryChartView2;
        batteryChartView2.setOnSelectListener(new BatteryChartView.OnSelectListener() { // from class: com.android.settings.fuelgauge.batteryusage.BatteryChartPreferenceController$$ExternalSyntheticLambda3
            @Override // com.android.settings.fuelgauge.batteryusage.BatteryChartView.OnSelectListener
            public final void onSelect(int i) {
                BatteryChartPreferenceController.this.lambda$setBatteryChartViewInner$3(i);
            }
        });
        refreshUi();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setBatteryChartViewInner$2(int i) {
        if (this.mDailyChartIndex == i) {
            return;
        }
        Log.d("BatteryChartPreferenceController", "onDailyChartSelect:" + i);
        this.mDailyChartIndex = i;
        this.mHourlyChartIndex = -1;
        refreshUi();
        requestAccessibilityFocusForCategoryTitle(this.mDailyChartView);
        this.mMetricsFeatureProvider.action(this.mPrefContext, i == -1 ? 1800 : 1799, this.mDailyChartIndex);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setBatteryChartViewInner$3(int i) {
        if (this.mHourlyChartIndex == i) {
            return;
        }
        Log.d("BatteryChartPreferenceController", "onHourlyChartSelect:" + i);
        this.mHourlyChartIndex = i;
        refreshUi();
        requestAccessibilityFocusForCategoryTitle(this.mHourlyChartView);
        this.mMetricsFeatureProvider.action(this.mPrefContext, i == -1 ? 1767 : 1766, this.mHourlyChartIndex);
    }

    boolean refreshUi() {
        boolean refreshUiWithNoLevelDataCase;
        if (this.mDailyChartView == null || this.mHourlyChartView == null) {
            return false;
        }
        if (this.mDailyViewModel == null || this.mHourlyViewModels == null) {
            refreshUiWithNoLevelDataCase = refreshUiWithNoLevelDataCase();
        } else {
            refreshUiWithNoLevelDataCase = refreshUiWithLevelDataCase();
        }
        if (refreshUiWithNoLevelDataCase) {
            this.mHandler.post(new Runnable() { // from class: com.android.settings.fuelgauge.batteryusage.BatteryChartPreferenceController$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    BatteryChartPreferenceController.this.lambda$refreshUi$4();
                }
            });
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$refreshUi$4() {
        long currentTimeMillis = System.currentTimeMillis();
        removeAndCacheAllPrefs();
        addAllPreferences();
        refreshCategoryTitle();
        Log.d("BatteryChartPreferenceController", String.format("refreshUi is finished in %d/ms", Long.valueOf(System.currentTimeMillis() - currentTimeMillis)));
    }

    private boolean refreshUiWithNoLevelDataCase() {
        setChartSummaryVisible(false);
        Map<Integer, Map<Integer, BatteryDiffData>> map = this.mBatteryUsageMap;
        if (map == null) {
            this.mDailyChartView.setVisibility(8);
            this.mHourlyChartView.setVisibility(8);
            this.mDailyChartView.setViewModel(null);
            this.mHourlyChartView.setViewModel(null);
            return false;
        } else if (map.get(-1).get(-1) == null) {
            this.mDailyChartView.setVisibility(8);
            this.mHourlyChartView.setVisibility(0);
            this.mHourlyChartView.setViewModel(null);
            removeAndCacheAllPrefs();
            addFooterPreferenceIfNeeded(false);
            return false;
        } else {
            return true;
        }
    }

    private boolean refreshUiWithLevelDataCase() {
        setChartSummaryVisible(true);
        if (isBatteryLevelDataInOneDay()) {
            this.mDailyChartView.setVisibility(8);
            this.mDailyChartIndex = 0;
        } else {
            this.mDailyChartView.setVisibility(0);
            this.mDailyViewModel.setSelectedIndex(this.mDailyChartIndex);
            this.mDailyChartView.setViewModel(this.mDailyViewModel);
        }
        if (this.mDailyChartIndex == -1) {
            animateBatteryHourlyChartView(false);
        } else {
            animateBatteryHourlyChartView(true);
            BatteryChartViewModel batteryChartViewModel = this.mHourlyViewModels.get(this.mDailyChartIndex);
            batteryChartViewModel.setSelectedIndex(this.mHourlyChartIndex);
            this.mHourlyChartView.setViewModel(batteryChartViewModel);
        }
        return this.mBatteryUsageMap != null;
    }

    private void addAllPreferences() {
        BatteryDiffData batteryDiffData = this.mBatteryUsageMap.get(Integer.valueOf(this.mDailyChartIndex)).get(Integer.valueOf(this.mHourlyChartIndex));
        addFooterPreferenceIfNeeded((batteryDiffData == null || (batteryDiffData.getAppDiffEntryList().isEmpty() && batteryDiffData.getSystemDiffEntryList().isEmpty())) ? false : true);
        if (batteryDiffData == null) {
            Log.w("BatteryChartPreferenceController", "cannot find BatteryDiffEntry for daily_index: " + this.mDailyChartIndex + " hourly_index: " + this.mHourlyChartIndex);
            return;
        }
        if (!batteryDiffData.getAppDiffEntryList().isEmpty()) {
            addPreferenceToScreen(batteryDiffData.getAppDiffEntryList());
        }
        if (!batteryDiffData.getSystemDiffEntryList().isEmpty()) {
            if (this.mExpandDividerPreference == null) {
                ExpandDividerPreference expandDividerPreference = new ExpandDividerPreference(this.mPrefContext);
                this.mExpandDividerPreference = expandDividerPreference;
                expandDividerPreference.setOnExpandListener(this);
                this.mExpandDividerPreference.setIsExpanded(this.mIsExpanded);
            }
            this.mExpandDividerPreference.setOrder(this.mAppListPrefGroup.getPreferenceCount());
            this.mAppListPrefGroup.addPreference(this.mExpandDividerPreference);
        }
        refreshExpandUi();
    }

    void addPreferenceToScreen(List<BatteryDiffEntry> list) {
        if (this.mAppListPrefGroup == null || list.isEmpty()) {
            return;
        }
        int preferenceCount = this.mAppListPrefGroup.getPreferenceCount();
        for (BatteryDiffEntry batteryDiffEntry : list) {
            boolean z = false;
            String appLabel = batteryDiffEntry.getAppLabel();
            Drawable appIcon = batteryDiffEntry.getAppIcon();
            if (TextUtils.isEmpty(appLabel) || appIcon == null) {
                Log.w("BatteryChartPreferenceController", "cannot find app resource for:" + batteryDiffEntry.getPackageName());
            } else {
                String key = batteryDiffEntry.mBatteryHistEntry.getKey();
                PowerGaugePreference powerGaugePreference = (PowerGaugePreference) this.mAppListPrefGroup.findPreference(key);
                if (powerGaugePreference != null) {
                    Log.w("BatteryChartPreferenceController", "preference should be removed for:" + batteryDiffEntry.getPackageName());
                    z = true;
                } else {
                    powerGaugePreference = (PowerGaugePreference) this.mPreferenceCache.get(key);
                }
                if (powerGaugePreference == null) {
                    powerGaugePreference = new PowerGaugePreference(this.mPrefContext);
                    powerGaugePreference.setKey(key);
                    this.mPreferenceCache.put(key, powerGaugePreference);
                }
                powerGaugePreference.setIcon(appIcon);
                powerGaugePreference.setTitle(appLabel);
                powerGaugePreference.setOrder(preferenceCount);
                powerGaugePreference.setPercent(batteryDiffEntry.getPercentOfTotal());
                powerGaugePreference.setSingleLineTitle(true);
                powerGaugePreference.setBatteryDiffEntry(batteryDiffEntry);
                powerGaugePreference.setEnabled(batteryDiffEntry.validForRestriction());
                setPreferenceSummary(powerGaugePreference, batteryDiffEntry);
                if (!z) {
                    this.mAppListPrefGroup.addPreference(powerGaugePreference);
                }
                appIcon.setAlpha(powerGaugePreference.isEnabled() ? 255 : 85);
                preferenceCount++;
            }
        }
    }

    private void removeAndCacheAllPrefs() {
        PreferenceGroup preferenceGroup = this.mAppListPrefGroup;
        if (preferenceGroup == null || preferenceGroup.getPreferenceCount() == 0) {
            return;
        }
        int preferenceCount = this.mAppListPrefGroup.getPreferenceCount();
        for (int i = 0; i < preferenceCount; i++) {
            Preference preference = this.mAppListPrefGroup.getPreference(i);
            if (!TextUtils.isEmpty(preference.getKey())) {
                this.mPreferenceCache.put(preference.getKey(), preference);
            }
        }
        this.mAppListPrefGroup.removeAll();
    }

    private void refreshExpandUi() {
        List<BatteryDiffEntry> systemDiffEntryList = this.mBatteryUsageMap.get(Integer.valueOf(this.mDailyChartIndex)).get(Integer.valueOf(this.mHourlyChartIndex)).getSystemDiffEntryList();
        if (this.mIsExpanded) {
            addPreferenceToScreen(systemDiffEntryList);
            return;
        }
        for (BatteryDiffEntry batteryDiffEntry : systemDiffEntryList) {
            Preference findPreference = this.mAppListPrefGroup.findPreference(batteryDiffEntry.mBatteryHistEntry.getKey());
            if (findPreference != null) {
                this.mAppListPrefGroup.removePreference(findPreference);
                this.mPreferenceCache.put(findPreference.getKey(), findPreference);
            }
        }
    }

    void refreshCategoryTitle() {
        String slotInformation = getSlotInformation();
        Log.d("BatteryChartPreferenceController", String.format("refreshCategoryTitle:%s", slotInformation));
        PreferenceGroup preferenceGroup = this.mAppListPrefGroup;
        if (preferenceGroup != null) {
            preferenceGroup.setTitle(getSlotInformation(true, slotInformation));
        }
        ExpandDividerPreference expandDividerPreference = this.mExpandDividerPreference;
        if (expandDividerPreference != null) {
            expandDividerPreference.setTitle(getSlotInformation(false, slotInformation));
        }
    }

    private void requestAccessibilityFocusForCategoryTitle(View view) {
        if (AccessibilityManager.getInstance(this.mContext).isEnabled()) {
            if (this.mCategoryTitleView == null) {
                this.mCategoryTitleView = view.getRootView().findViewById(16908310);
            }
            View view2 = this.mCategoryTitleView;
            if (view2 != null) {
                view2.requestAccessibilityFocus();
            }
        }
    }

    private String getSlotInformation(boolean z, String str) {
        if (str != null) {
            return z ? this.mPrefContext.getString(R.string.battery_app_usage_for, str) : this.mPrefContext.getString(R.string.battery_system_usage_for, str);
        } else if (z) {
            return this.mPrefContext.getString(R.string.battery_app_usage);
        } else {
            return this.mPrefContext.getString(R.string.battery_system_usage);
        }
    }

    String getSlotInformation() {
        if (this.mDailyViewModel == null || this.mHourlyViewModels == null || isAllSelected()) {
            return null;
        }
        String fullText = this.mDailyViewModel.getFullText(this.mDailyChartIndex);
        if (this.mHourlyChartIndex == -1) {
            return fullText;
        }
        String fullText2 = this.mHourlyViewModels.get(this.mDailyChartIndex).getFullText(this.mHourlyChartIndex);
        return isBatteryLevelDataInOneDay() ? fullText2 : String.format("%s %s", fullText, fullText2);
    }

    void setPreferenceSummary(PowerGaugePreference powerGaugePreference, BatteryDiffEntry batteryDiffEntry) {
        long j = batteryDiffEntry.mForegroundUsageTimeInMs;
        long j2 = batteryDiffEntry.mBackgroundUsageTimeInMs;
        long j3 = j + j2;
        String str = null;
        if (!isValidToShowSummary(batteryDiffEntry.getPackageName())) {
            powerGaugePreference.setSummary((CharSequence) null);
            return;
        }
        if (j3 == 0) {
            powerGaugePreference.setSummary((CharSequence) null);
        } else if (j == 0 && j2 != 0) {
            str = buildUsageTimeInfo(j2, true);
        } else if (j3 < 60000) {
            str = buildUsageTimeInfo(j3, false);
        } else {
            str = buildUsageTimeInfo(j3, false);
            if (j2 > 0) {
                str = str + "\n" + buildUsageTimeInfo(j2, true);
            }
        }
        powerGaugePreference.setSummary(str);
    }

    private String buildUsageTimeInfo(long j, boolean z) {
        int i;
        int i2;
        if (j < 60000) {
            Context context = this.mPrefContext;
            if (z) {
                i2 = R.string.battery_usage_background_less_than_one_minute;
            } else {
                i2 = R.string.battery_usage_total_less_than_one_minute;
            }
            return context.getString(i2);
        }
        CharSequence formatElapsedTime = StringUtil.formatElapsedTime(this.mPrefContext, j, false, false);
        if (z) {
            i = R.string.battery_usage_for_background_time;
        } else {
            i = R.string.battery_usage_for_total_time;
        }
        return this.mPrefContext.getString(i, formatElapsedTime);
    }

    boolean isValidToShowSummary(String str) {
        return !DataProcessor.contains(str, this.mNotAllowShowSummaryPackages);
    }

    private void animateBatteryChartViewGroup() {
        View view = this.mBatteryChartViewGroup;
        if (view == null || view.getAlpha() != 0.0f) {
            return;
        }
        this.mBatteryChartViewGroup.animate().alpha(1.0f).setDuration(400L).start();
    }

    private void animateBatteryHourlyChartView(boolean z) {
        BatteryChartView batteryChartView = this.mHourlyChartView;
        if (batteryChartView == null || this.mHourlyChartVisible == z) {
            return;
        }
        this.mHourlyChartVisible = z;
        if (z) {
            batteryChartView.setVisibility(0);
            this.mHourlyChartView.animate().alpha(1.0f).setDuration(400L).setListener(this.mHourlyChartFadeInAdapter).start();
            return;
        }
        batteryChartView.animate().alpha(0.0f).setDuration(200L).setListener(this.mHourlyChartFadeOutAdapter).start();
    }

    private void setChartSummaryVisible(boolean z) {
        TextView textView = this.mChartSummaryTextView;
        if (textView != null) {
            textView.setVisibility(z ? 0 : 8);
        }
    }

    private AnimatorListenerAdapter createHourlyChartAnimatorListenerAdapter(boolean z) {
        final int i = z ? 0 : 8;
        return new AnimatorListenerAdapter() { // from class: com.android.settings.fuelgauge.batteryusage.BatteryChartPreferenceController.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                BatteryChartView batteryChartView = BatteryChartPreferenceController.this.mHourlyChartView;
                if (batteryChartView != null) {
                    batteryChartView.setVisibility(i);
                }
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                super.onAnimationCancel(animator);
                BatteryChartView batteryChartView = BatteryChartPreferenceController.this.mHourlyChartView;
                if (batteryChartView != null) {
                    batteryChartView.setVisibility(i);
                }
            }
        };
    }

    private void addFooterPreferenceIfNeeded(boolean z) {
        FooterPreference footerPreference;
        int i;
        if (this.mIsFooterPrefAdded || (footerPreference = this.mFooterPreference) == null) {
            return;
        }
        this.mIsFooterPrefAdded = true;
        Context context = this.mPrefContext;
        if (z) {
            i = R.string.battery_usage_screen_footer;
        } else {
            i = R.string.battery_usage_screen_footer_empty;
        }
        footerPreference.setTitle(context.getString(i));
        this.mHandler.post(new Runnable() { // from class: com.android.settings.fuelgauge.batteryusage.BatteryChartPreferenceController$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                BatteryChartPreferenceController.this.lambda$addFooterPreferenceIfNeeded$5();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$addFooterPreferenceIfNeeded$5() {
        this.mPreferenceScreen.addPreference(this.mFooterPreference);
    }

    private boolean isBatteryLevelDataInOneDay() {
        List<BatteryChartViewModel> list = this.mHourlyViewModels;
        return list != null && list.size() == 1;
    }

    private boolean isAllSelected() {
        return (isBatteryLevelDataInOneDay() || this.mDailyChartIndex == -1) && this.mHourlyChartIndex == -1;
    }

    static int getTotalHours(BatteryLevelData batteryLevelData) {
        if (batteryLevelData == null) {
            return 0;
        }
        List<Long> timestamps = batteryLevelData.getDailyBatteryLevels().getTimestamps();
        return (int) ((timestamps.get(timestamps.size() - 1).longValue() - timestamps.get(0).longValue()) / 3600000);
    }

    public static List<BatteryDiffEntry> getAppBatteryUsageData(Context context) {
        BatteryDiffData batteryDiffData;
        long currentTimeMillis = System.currentTimeMillis();
        Map<Long, Map<String, BatteryHistEntry>> batteryHistorySinceLastFullCharge = FeatureFactory.getFactory(context).getPowerUsageFeatureProvider(context).getBatteryHistorySinceLastFullCharge(context);
        if (batteryHistorySinceLastFullCharge == null || batteryHistorySinceLastFullCharge.isEmpty()) {
            return null;
        }
        Log.d("BatteryChartPreferenceController", String.format("getBatterySinceLastFullChargeUsageData() size=%d time=%d/ms", Integer.valueOf(batteryHistorySinceLastFullCharge.size()), Long.valueOf(System.currentTimeMillis() - currentTimeMillis)));
        Map<Integer, Map<Integer, BatteryDiffData>> batteryUsageData = DataProcessor.getBatteryUsageData(context, batteryHistorySinceLastFullCharge);
        if (batteryUsageData == null || (batteryDiffData = batteryUsageData.get(-1).get(-1)) == null) {
            return null;
        }
        return batteryDiffData.getAppDiffEntryList();
    }

    public static BatteryDiffEntry getAppBatteryUsageData(Context context, String str, int i) {
        List<BatteryDiffEntry> appBatteryUsageData;
        if (str == null || (appBatteryUsageData = getAppBatteryUsageData(context)) == null) {
            return null;
        }
        for (BatteryDiffEntry batteryDiffEntry : appBatteryUsageData) {
            BatteryHistEntry batteryHistEntry = batteryDiffEntry.mBatteryHistEntry;
            if (batteryHistEntry != null && batteryHistEntry.mConsumerType == 1 && batteryHistEntry.mUserId == i && str.equals(batteryDiffEntry.getPackageName())) {
                return batteryDiffEntry;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class DailyChartLabelTextGenerator implements BatteryChartViewModel.LabelTextGenerator {
        private DailyChartLabelTextGenerator() {
        }

        @Override // com.android.settings.fuelgauge.batteryusage.BatteryChartViewModel.LabelTextGenerator
        public String generateText(List<Long> list, int i) {
            return ConvertUtils.utcToLocalTimeDayOfWeek(((AbstractPreferenceController) BatteryChartPreferenceController.this).mContext, list.get(i).longValue(), true);
        }

        @Override // com.android.settings.fuelgauge.batteryusage.BatteryChartViewModel.LabelTextGenerator
        public String generateFullText(List<Long> list, int i) {
            return ConvertUtils.utcToLocalTimeDayOfWeek(((AbstractPreferenceController) BatteryChartPreferenceController.this).mContext, list.get(i).longValue(), false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class HourlyChartLabelTextGenerator implements BatteryChartViewModel.LabelTextGenerator {
        private HourlyChartLabelTextGenerator() {
        }

        @Override // com.android.settings.fuelgauge.batteryusage.BatteryChartViewModel.LabelTextGenerator
        public String generateText(List<Long> list, int i) {
            return ConvertUtils.utcToLocalTimeHour(((AbstractPreferenceController) BatteryChartPreferenceController.this).mContext, list.get(i).longValue(), BatteryChartPreferenceController.this.mIs24HourFormat);
        }

        @Override // com.android.settings.fuelgauge.batteryusage.BatteryChartViewModel.LabelTextGenerator
        public String generateFullText(List<Long> list, int i) {
            if (i == list.size() - 1) {
                return generateText(list, i);
            }
            Object[] objArr = new Object[3];
            objArr[0] = generateText(list, i);
            objArr[1] = BatteryChartPreferenceController.this.mIs24HourFormat ? "-" : " - ";
            objArr[2] = generateText(list, i + 1);
            return String.format("%s%s%s", objArr);
        }
    }
}
