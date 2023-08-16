package com.android.settings.fuelgauge.batterytip.detectors;

import android.content.Context;
import com.android.settings.fuelgauge.BatteryInfo;
import com.android.settings.fuelgauge.BatteryUtils;
import com.android.settings.fuelgauge.batterytip.tips.BatteryTip;
import com.android.settings.fuelgauge.batterytip.tips.DockDefenderTip;
/* loaded from: classes.dex */
public class DockDefenderDetector {
    private final BatteryInfo mBatteryInfo;
    private final Context mContext;

    public DockDefenderDetector(BatteryInfo batteryInfo, Context context) {
        this.mBatteryInfo = batteryInfo;
        this.mContext = context;
    }

    public BatteryTip detect() {
        int currentDockDefenderMode = BatteryUtils.getCurrentDockDefenderMode(this.mContext, this.mBatteryInfo);
        return new DockDefenderTip(currentDockDefenderMode != 3 ? 0 : 2, currentDockDefenderMode);
    }
}
