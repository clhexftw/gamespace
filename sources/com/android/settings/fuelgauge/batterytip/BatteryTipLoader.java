package com.android.settings.fuelgauge.batterytip;

import android.content.Context;
import android.os.BatteryUsageStats;
import com.android.settings.fuelgauge.BatteryInfo;
import com.android.settings.fuelgauge.BatteryUtils;
import com.android.settings.fuelgauge.batterytip.detectors.BatteryDefenderDetector;
import com.android.settings.fuelgauge.batterytip.detectors.DockDefenderDetector;
import com.android.settings.fuelgauge.batterytip.detectors.EarlyWarningDetector;
import com.android.settings.fuelgauge.batterytip.detectors.HighUsageDetector;
import com.android.settings.fuelgauge.batterytip.detectors.LowBatteryDetector;
import com.android.settings.fuelgauge.batterytip.detectors.SmartBatteryDetector;
import com.android.settings.fuelgauge.batterytip.tips.BatteryTip;
import com.android.settingslib.utils.AsyncLoaderCompat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes.dex */
public class BatteryTipLoader extends AsyncLoaderCompat<List<BatteryTip>> {
    private BatteryUsageStats mBatteryUsageStats;
    BatteryUtils mBatteryUtils;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settingslib.utils.AsyncLoaderCompat
    public void onDiscardResult(List<BatteryTip> list) {
    }

    public BatteryTipLoader(Context context, BatteryUsageStats batteryUsageStats) {
        super(context);
        this.mBatteryUsageStats = batteryUsageStats;
        this.mBatteryUtils = BatteryUtils.getInstance(context);
    }

    @Override // androidx.loader.content.AsyncTaskLoader
    public List<BatteryTip> loadInBackground() {
        ArrayList arrayList = new ArrayList();
        BatteryTipPolicy batteryTipPolicy = new BatteryTipPolicy(getContext());
        BatteryInfo batteryInfo = this.mBatteryUtils.getBatteryInfo("BatteryTipLoader");
        Context context = getContext();
        arrayList.add(new LowBatteryDetector(context, batteryTipPolicy, batteryInfo).detect());
        arrayList.add(new HighUsageDetector(context, batteryTipPolicy, this.mBatteryUsageStats, batteryInfo).detect());
        arrayList.add(new SmartBatteryDetector(context, batteryTipPolicy, batteryInfo, context.getContentResolver()).detect());
        arrayList.add(new EarlyWarningDetector(batteryTipPolicy, context).detect());
        arrayList.add(new BatteryDefenderDetector(batteryInfo, context.getApplicationContext()).detect());
        arrayList.add(new DockDefenderDetector(batteryInfo, context.getApplicationContext()).detect());
        Collections.sort(arrayList);
        return arrayList;
    }
}
