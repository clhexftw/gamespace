package com.android.settings.fuelgauge.batteryusage;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
/* loaded from: classes.dex */
public class BatteryDiffData {
    private final List<BatteryDiffEntry> mAppEntries;
    private final List<BatteryDiffEntry> mSystemEntries;

    public BatteryDiffData(List<BatteryDiffEntry> list, List<BatteryDiffEntry> list2) {
        this.mAppEntries = list;
        this.mSystemEntries = list2;
        sortEntries();
    }

    public BatteryDiffData(List<BatteryDiffEntry> list, List<BatteryDiffEntry> list2, double d) {
        this.mAppEntries = list;
        this.mSystemEntries = list2;
        setTotalConsumePowerForAllEntries(d);
        sortEntries();
    }

    public List<BatteryDiffEntry> getAppDiffEntryList() {
        return this.mAppEntries;
    }

    public List<BatteryDiffEntry> getSystemDiffEntryList() {
        return this.mSystemEntries;
    }

    private void setTotalConsumePowerForAllEntries(final double d) {
        this.mAppEntries.forEach(new Consumer() { // from class: com.android.settings.fuelgauge.batteryusage.BatteryDiffData$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((BatteryDiffEntry) obj).setTotalConsumePower(d);
            }
        });
        this.mSystemEntries.forEach(new Consumer() { // from class: com.android.settings.fuelgauge.batteryusage.BatteryDiffData$$ExternalSyntheticLambda1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((BatteryDiffEntry) obj).setTotalConsumePower(d);
            }
        });
    }

    private void sortEntries() {
        List<BatteryDiffEntry> list = this.mAppEntries;
        Comparator<BatteryDiffEntry> comparator = BatteryDiffEntry.COMPARATOR;
        Collections.sort(list, comparator);
        Collections.sort(this.mSystemEntries, comparator);
    }
}
