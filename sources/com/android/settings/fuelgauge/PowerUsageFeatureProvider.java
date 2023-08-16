package com.android.settings.fuelgauge;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.SparseIntArray;
import com.android.settings.fuelgauge.batteryusage.BatteryHistEntry;
import com.android.settingslib.fuelgauge.Estimate;
import java.util.Map;
import java.util.Set;
/* loaded from: classes.dex */
public interface PowerUsageFeatureProvider {
    String getAdvancedUsageScreenInfoString();

    Map<Long, Map<String, BatteryHistEntry>> getBatteryHistorySinceLastFullCharge(Context context);

    Uri getBatteryHistoryUri();

    boolean getEarlyWarningSignal(Context context, String str);

    Estimate getEnhancedBatteryPrediction(Context context);

    SparseIntArray getEnhancedBatteryPredictionCurve(Context context, long j);

    CharSequence[] getHideApplicationEntries(Context context);

    CharSequence[] getHideApplicationSummary(Context context);

    Set<CharSequence> getHideBackgroundUsageTimeSet(Context context);

    Intent getResumeChargeIntent(boolean z);

    boolean isAdaptiveChargingSupported();

    boolean isChartGraphEnabled(Context context);

    boolean isEnhancedBatteryPredictionEnabled(Context context);

    boolean isExtraDefend();

    boolean isSmartBatterySupported();

    boolean isTypeSystem(int i, String[] strArr);
}
