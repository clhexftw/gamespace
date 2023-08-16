package com.android.settings.fuelgauge.batterytip.detectors;

import android.content.Context;
import com.android.settings.fuelgauge.BatteryInfo;
import com.android.settings.fuelgauge.batterytip.tips.BatteryDefenderTip;
import com.android.settings.fuelgauge.batterytip.tips.BatteryTip;
import com.android.settings.overlay.FeatureFactory;
/* loaded from: classes.dex */
public class BatteryDefenderDetector {
    private final BatteryInfo mBatteryInfo;
    private final Context mContext;

    public BatteryDefenderDetector(BatteryInfo batteryInfo, Context context) {
        this.mBatteryInfo = batteryInfo;
        this.mContext = context;
    }

    public BatteryTip detect() {
        if (this.mBatteryInfo.isOverheated && !FeatureFactory.getFactory(this.mContext).getPowerUsageFeatureProvider(this.mContext).isExtraDefend()) {
            return new BatteryDefenderTip(0);
        }
        return new BatteryDefenderTip(2);
    }
}
