package com.android.settings.fuelgauge.batteryusage;

import android.content.ContentValues;
import android.content.Context;
import android.os.BatteryUsageStats;
import android.os.LocaleList;
import android.os.UserHandle;
import android.os.UserManager;
import android.text.format.DateFormat;
import com.android.settings.Utils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
/* loaded from: classes.dex */
public final class ConvertUtils {
    private static final Map<String, BatteryHistEntry> EMPTY_BATTERY_MAP = new HashMap();
    private static final BatteryHistEntry EMPTY_BATTERY_HIST_ENTRY = new BatteryHistEntry(new ContentValues());
    static double PERCENTAGE_OF_TOTAL_THRESHOLD = 1.0d;

    public static ContentValues convertToContentValues(BatteryEntry batteryEntry, BatteryUsageStats batteryUsageStats, int i, int i2, int i3, long j, long j2) {
        ContentValues contentValues = new ContentValues();
        if (batteryEntry != null && batteryUsageStats != null) {
            contentValues.put("uid", Long.valueOf(batteryEntry.getUid()));
            contentValues.put("userId", Long.valueOf(UserHandle.getUserId(batteryEntry.getUid())));
            contentValues.put("appLabel", batteryEntry.getLabel());
            contentValues.put("packageName", batteryEntry.getDefaultPackageName());
            contentValues.put("isHidden", Boolean.valueOf(batteryEntry.isHidden()));
            contentValues.put("totalPower", Double.valueOf(batteryUsageStats.getConsumedPower()));
            contentValues.put("consumePower", Double.valueOf(batteryEntry.getConsumedPower()));
            contentValues.put("percentOfTotal", Double.valueOf(batteryEntry.mPercent));
            contentValues.put("foregroundUsageTimeInMs", Long.valueOf(batteryEntry.getTimeInForegroundMs()));
            contentValues.put("backgroundUsageTimeInMs", Long.valueOf(batteryEntry.getTimeInBackgroundMs()));
            contentValues.put("drainType", Integer.valueOf(batteryEntry.getPowerComponentId()));
            contentValues.put("consumerType", Integer.valueOf(batteryEntry.getConsumerType()));
        } else {
            contentValues.put("packageName", "fake_package");
        }
        contentValues.put("bootTimestamp", Long.valueOf(j));
        contentValues.put("timestamp", Long.valueOf(j2));
        contentValues.put("zoneId", TimeZone.getDefault().getID());
        contentValues.put("batteryLevel", Integer.valueOf(i));
        contentValues.put("batteryStatus", Integer.valueOf(i2));
        contentValues.put("batteryHealth", Integer.valueOf(i3));
        return contentValues;
    }

    public static BatteryHistEntry convertToBatteryHistEntry(BatteryEntry batteryEntry, BatteryUsageStats batteryUsageStats) {
        return new BatteryHistEntry(convertToContentValues(batteryEntry, batteryUsageStats, 0, 0, 0, 0L, 0L));
    }

    public static String utcToLocalTime(Context context, long j) {
        return DateFormat.format(DateFormat.getBestDateTimePattern(getLocale(context), "MMM dd,yyyy HH:mm:ss"), j).toString();
    }

    public static String utcToLocalTimeHour(Context context, long j, boolean z) {
        return DateFormat.format(DateFormat.getBestDateTimePattern(getLocale(context), z ? "HHm" : "ha"), j).toString();
    }

    public static String utcToLocalTimeDayOfWeek(Context context, long j, boolean z) {
        return DateFormat.format(DateFormat.getBestDateTimePattern(getLocale(context), z ? "E" : "EEEE"), j).toString();
    }

    static void resolveMultiUsersData(Context context, Map<Integer, List<BatteryDiffEntry>> map) {
        int userId = context.getUserId();
        UserHandle managedProfile = Utils.getManagedProfile((UserManager) context.getSystemService(UserManager.class));
        int identifier = managedProfile != null ? managedProfile.getIdentifier() : Integer.MIN_VALUE;
        for (List<BatteryDiffEntry> list : map.values()) {
            Iterator<BatteryDiffEntry> it = list.iterator();
            double d = 0.0d;
            double d2 = 0.0d;
            while (it.hasNext()) {
                BatteryDiffEntry next = it.next();
                BatteryHistEntry batteryHistEntry = next.mBatteryHistEntry;
                if (batteryHistEntry.mConsumerType == 1) {
                    long j = batteryHistEntry.mUserId;
                    if (j != userId && j != identifier) {
                        it.remove();
                        d2 += next.mConsumePower;
                        d += next.getPercentOfTotal();
                    }
                }
            }
            if (d != 0.0d) {
                list.add(createOtherUsersEntry(context, d2, d));
            }
        }
    }

    static Locale getLocale(Context context) {
        if (context == null) {
            return Locale.getDefault();
        }
        LocaleList locales = context.getResources().getConfiguration().getLocales();
        return (locales == null || locales.isEmpty()) ? Locale.getDefault() : locales.get(0);
    }

    private static BatteryDiffEntry createOtherUsersEntry(Context context, double d, double d2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("uid", (Long) Long.MIN_VALUE);
        contentValues.put("userId", (Long) Long.MIN_VALUE);
        contentValues.put("consumerType", (Integer) 1);
        BatteryDiffEntry batteryDiffEntry = new BatteryDiffEntry(context, 0L, 0L, d, new BatteryHistEntry(contentValues));
        batteryDiffEntry.setTotalConsumePower((100.0d * d) / d2);
        return batteryDiffEntry;
    }
}
