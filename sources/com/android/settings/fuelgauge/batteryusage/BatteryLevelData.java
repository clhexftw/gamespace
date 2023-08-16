package com.android.settings.fuelgauge.batteryusage;

import androidx.core.util.Preconditions;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
/* loaded from: classes.dex */
public final class BatteryLevelData {
    private final PeriodBatteryLevelData mDailyBatteryLevels;
    private final List<PeriodBatteryLevelData> mHourlyBatteryLevelsPerDay;

    /* loaded from: classes.dex */
    public static final class PeriodBatteryLevelData {
        private final List<Integer> mLevels;
        private final List<Long> mTimestamps;

        public PeriodBatteryLevelData(List<Long> list, List<Integer> list2) {
            boolean z = list.size() == list2.size();
            Preconditions.checkArgument(z, "Timestamp: " + list.size() + ", Level: " + list2.size());
            this.mTimestamps = list;
            this.mLevels = list2;
        }

        public List<Long> getTimestamps() {
            return this.mTimestamps;
        }

        public List<Integer> getLevels() {
            return this.mLevels;
        }

        public String toString() {
            return String.format(Locale.ENGLISH, "timestamps: %s; levels: %s", Objects.toString(this.mTimestamps), Objects.toString(this.mLevels));
        }
    }

    public BatteryLevelData(PeriodBatteryLevelData periodBatteryLevelData, List<PeriodBatteryLevelData> list) {
        long size = periodBatteryLevelData.getTimestamps().size();
        long size2 = list.size();
        Preconditions.checkArgument(size2 == size - 1, "DailySize: " + size + ", HourlySize: " + size2);
        this.mDailyBatteryLevels = periodBatteryLevelData;
        this.mHourlyBatteryLevelsPerDay = list;
    }

    public PeriodBatteryLevelData getDailyBatteryLevels() {
        return this.mDailyBatteryLevels;
    }

    public List<PeriodBatteryLevelData> getHourlyBatteryLevelsPerDay() {
        return this.mHourlyBatteryLevelsPerDay;
    }

    public String toString() {
        return String.format(Locale.ENGLISH, "dailyBatteryLevels: %s; hourlyBatteryLevelsPerDay: %s", Objects.toString(this.mDailyBatteryLevels), Objects.toString(this.mHourlyBatteryLevelsPerDay));
    }
}
