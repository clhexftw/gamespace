package com.android.settings.fuelgauge.batteryusage;

import android.content.Context;
import android.os.BatteryUsageStats;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import com.android.settings.R;
import com.android.settings.fuelgauge.BatteryInfo;
import com.android.settings.fuelgauge.BatteryUtils;
import com.android.settings.overlay.FeatureFactory;
import com.android.settings.widget.UsageView;
/* loaded from: classes.dex */
public class BatteryHistoryPreference extends Preference {
    BatteryInfo mBatteryInfo;
    private BatteryChartPreferenceController mChartPreferenceController;
    private BatteryChartView mDailyChartView;
    boolean mHideSummary;
    private BatteryChartView mHourlyChartView;
    private boolean mIsChartGraphEnabled;
    private CharSequence mSummaryContent;
    private TextView mSummaryView;

    public BatteryHistoryPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        int i;
        this.mIsChartGraphEnabled = FeatureFactory.getFactory(context).getPowerUsageFeatureProvider(context).isChartGraphEnabled(context);
        Log.i("BatteryHistoryPreference", "isChartGraphEnabled: " + this.mIsChartGraphEnabled);
        if (this.mIsChartGraphEnabled) {
            i = R.layout.battery_chart_graph;
        } else {
            i = R.layout.battery_usage_graph;
        }
        setLayoutResource(i);
        setSelectable(false);
    }

    public void setBottomSummary(CharSequence charSequence) {
        this.mSummaryContent = charSequence;
        TextView textView = this.mSummaryView;
        if (textView != null) {
            textView.setVisibility(0);
            this.mSummaryView.setText(this.mSummaryContent);
        }
        this.mHideSummary = false;
    }

    public void hideBottomSummary() {
        TextView textView = this.mSummaryView;
        if (textView != null) {
            textView.setVisibility(8);
        }
        this.mHideSummary = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setBatteryUsageStats(BatteryUsageStats batteryUsageStats) {
        BatteryInfo.getBatteryInfo(getContext(), new BatteryInfo.Callback() { // from class: com.android.settings.fuelgauge.batteryusage.BatteryHistoryPreference$$ExternalSyntheticLambda0
            @Override // com.android.settings.fuelgauge.BatteryInfo.Callback
            public final void onBatteryInfoLoaded(BatteryInfo batteryInfo) {
                BatteryHistoryPreference.this.lambda$setBatteryUsageStats$0(batteryInfo);
            }
        }, batteryUsageStats, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setBatteryUsageStats$0(BatteryInfo batteryInfo) {
        this.mBatteryInfo = batteryInfo;
        notifyChanged();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setChartPreferenceController(BatteryChartPreferenceController batteryChartPreferenceController) {
        BatteryChartView batteryChartView;
        this.mChartPreferenceController = batteryChartPreferenceController;
        BatteryChartView batteryChartView2 = this.mDailyChartView;
        if (batteryChartView2 == null || (batteryChartView = this.mHourlyChartView) == null) {
            return;
        }
        batteryChartPreferenceController.setBatteryChartView(batteryChartView2, batteryChartView);
    }

    @Override // androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        long currentTimeMillis = System.currentTimeMillis();
        if (this.mBatteryInfo == null) {
            return;
        }
        if (this.mIsChartGraphEnabled) {
            TextView textView = (TextView) preferenceViewHolder.findViewById(R.id.companion_text);
            BatteryChartView batteryChartView = (BatteryChartView) preferenceViewHolder.findViewById(R.id.daily_battery_chart);
            this.mDailyChartView = batteryChartView;
            batteryChartView.setCompanionTextView(textView);
            BatteryChartView batteryChartView2 = (BatteryChartView) preferenceViewHolder.findViewById(R.id.hourly_battery_chart);
            this.mHourlyChartView = batteryChartView2;
            batteryChartView2.setCompanionTextView(textView);
            BatteryChartPreferenceController batteryChartPreferenceController = this.mChartPreferenceController;
            if (batteryChartPreferenceController != null) {
                batteryChartPreferenceController.setBatteryChartView(this.mDailyChartView, this.mHourlyChartView);
            }
        } else {
            ((TextView) preferenceViewHolder.findViewById(R.id.charge)).setText(this.mBatteryInfo.batteryPercentString);
            TextView textView2 = (TextView) preferenceViewHolder.findViewById(R.id.bottom_summary);
            this.mSummaryView = textView2;
            CharSequence charSequence = this.mSummaryContent;
            if (charSequence != null) {
                textView2.setText(charSequence);
            }
            if (this.mHideSummary) {
                this.mSummaryView.setVisibility(8);
            }
            UsageView usageView = (UsageView) preferenceViewHolder.findViewById(R.id.battery_usage);
            usageView.findViewById(R.id.label_group).setAlpha(0.7f);
            this.mBatteryInfo.bindHistory(usageView, new BatteryInfo.BatteryDataParser[0]);
        }
        BatteryUtils.logRuntime("BatteryHistoryPreference", "onBindViewHolder", currentTimeMillis);
    }
}
