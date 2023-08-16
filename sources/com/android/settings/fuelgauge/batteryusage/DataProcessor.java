package com.android.settings.fuelgauge.batteryusage;

import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;
import android.os.BatteryStatsManager;
import android.os.BatteryUsageStats;
import android.os.BatteryUsageStatsQuery;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.os.UserManager;
import android.text.TextUtils;
import android.util.ArraySet;
import android.util.Log;
import com.android.internal.annotations.VisibleForTesting;
import com.android.settings.Utils;
import com.android.settings.fuelgauge.batteryusage.BatteryLevelData;
import com.android.settings.fuelgauge.batteryusage.DataProcessor;
import com.android.settings.overlay.FeatureFactory;
import com.android.settingslib.core.instrumentation.MetricsFeatureProvider;
import com.android.settingslib.fuelgauge.BatteryStatus;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
/* loaded from: classes.dex */
public final class DataProcessor {
    @VisibleForTesting
    static final double PERCENTAGE_OF_TOTAL_THRESHOLD = 1.0d;
    @VisibleForTesting
    static final int SELECTED_INDEX_ALL = -1;
    private static final Map<String, BatteryHistEntry> EMPTY_BATTERY_MAP = new HashMap();
    private static final BatteryHistEntry EMPTY_BATTERY_HIST_ENTRY = new BatteryHistEntry(new ContentValues());

    /* loaded from: classes.dex */
    public interface UsageMapAsyncResponse {
        void onBatteryUsageMapLoaded(Map<Integer, Map<Integer, BatteryDiffData>> map);
    }

    private static double getDiffValue(double d, double d2, double d3) {
        return (d2 > d ? d2 - d : 0.0d) + (d3 > d2 ? d3 - d2 : 0.0d);
    }

    private static long getDiffValue(long j, long j2, long j3) {
        return (j2 > j ? j2 - j : 0L) + (j3 > j2 ? j3 - j2 : 0L);
    }

    private static void log(Context context, String str, long j, BatteryHistEntry batteryHistEntry) {
    }

    public static BatteryLevelData getBatteryLevelData(Context context, Handler handler, Map<Long, Map<String, BatteryHistEntry>> map, UsageMapAsyncResponse usageMapAsyncResponse) {
        if (map == null || map.isEmpty()) {
            Log.d("DataProcessor", "batteryHistoryMap is null in getBatteryLevelData()");
            loadBatteryUsageDataFromBatteryStatsService(context, handler, usageMapAsyncResponse);
            return null;
        }
        if (handler == null) {
            handler = new Handler(Looper.getMainLooper());
        }
        Handler handler2 = handler;
        Map<Long, Map<String, BatteryHistEntry>> historyMapWithExpectedTimestamps = getHistoryMapWithExpectedTimestamps(context, map);
        BatteryLevelData levelDataThroughProcessedHistoryMap = getLevelDataThroughProcessedHistoryMap(context, historyMapWithExpectedTimestamps);
        if (levelDataThroughProcessedHistoryMap == null) {
            loadBatteryUsageDataFromBatteryStatsService(context, handler2, usageMapAsyncResponse);
            Log.d("DataProcessor", "getBatteryLevelData() returns null");
            return null;
        }
        new ComputeUsageMapAndLoadItemsTask(context, handler2, usageMapAsyncResponse, levelDataThroughProcessedHistoryMap.getHourlyBatteryLevelsPerDay(), historyMapWithExpectedTimestamps).execute(new Void[0]);
        return levelDataThroughProcessedHistoryMap;
    }

    public static Map<Integer, Map<Integer, BatteryDiffData>> getBatteryUsageData(Context context, Map<Long, Map<String, BatteryHistEntry>> map) {
        if (map == null || map.isEmpty()) {
            Log.d("DataProcessor", "getBatteryLevelData() returns null");
            return null;
        }
        Map<Long, Map<String, BatteryHistEntry>> historyMapWithExpectedTimestamps = getHistoryMapWithExpectedTimestamps(context, map);
        BatteryLevelData levelDataThroughProcessedHistoryMap = getLevelDataThroughProcessedHistoryMap(context, historyMapWithExpectedTimestamps);
        if (levelDataThroughProcessedHistoryMap == null) {
            return null;
        }
        return getBatteryUsageMap(context, levelDataThroughProcessedHistoryMap.getHourlyBatteryLevelsPerDay(), historyMapWithExpectedTimestamps);
    }

    public static boolean contains(String str, CharSequence[] charSequenceArr) {
        if (str != null && charSequenceArr != null) {
            for (CharSequence charSequence : charSequenceArr) {
                if (TextUtils.equals(str, charSequence)) {
                    return true;
                }
            }
        }
        return false;
    }

    @VisibleForTesting
    static Map<Long, Map<String, BatteryHistEntry>> getHistoryMapWithExpectedTimestamps(Context context, Map<Long, Map<String, BatteryHistEntry>> map) {
        long currentTimeMillis = System.currentTimeMillis();
        ArrayList arrayList = new ArrayList(map.keySet());
        HashMap hashMap = new HashMap();
        if (arrayList.isEmpty()) {
            Log.d("DataProcessor", "empty batteryHistoryMap in getHistoryMapWithExpectedTimestamps()");
            return hashMap;
        }
        Collections.sort(arrayList);
        interpolateHistory(context, arrayList, getTimestampSlots(arrayList), isFromFullCharge(map.get(arrayList.get(0))), map, hashMap);
        Log.d("DataProcessor", String.format("getHistoryMapWithExpectedTimestamps() size=%d in %d/ms", Integer.valueOf(hashMap.size()), Long.valueOf(System.currentTimeMillis() - currentTimeMillis)));
        return hashMap;
    }

    @VisibleForTesting
    static BatteryLevelData getLevelDataThroughProcessedHistoryMap(Context context, Map<Long, Map<String, BatteryHistEntry>> map) {
        ArrayList arrayList = new ArrayList(map.keySet());
        Collections.sort(arrayList);
        List<Long> dailyTimestamps = getDailyTimestamps(arrayList);
        if (dailyTimestamps.size() < 2) {
            return null;
        }
        return new BatteryLevelData(getPeriodBatteryLevelData(context, map, dailyTimestamps), getHourlyPeriodBatteryLevelData(context, map, getHourlyTimestamps(dailyTimestamps)));
    }

    @VisibleForTesting
    static List<Long> getTimestampSlots(List<Long> list) {
        ArrayList arrayList = new ArrayList();
        int size = list.size();
        if (size < 2) {
            return arrayList;
        }
        long longValue = list.get(0).longValue();
        long longValue2 = list.get(size + SELECTED_INDEX_ALL).longValue();
        long nearestEvenHourTimestamp = getNearestEvenHourTimestamp(longValue);
        long lastEvenHourBeforeTimestamp = getLastEvenHourBeforeTimestamp(longValue2);
        if (nearestEvenHourTimestamp >= lastEvenHourBeforeTimestamp) {
            return arrayList;
        }
        while (nearestEvenHourTimestamp <= lastEvenHourBeforeTimestamp) {
            arrayList.add(Long.valueOf(nearestEvenHourTimestamp));
            nearestEvenHourTimestamp += 3600000;
        }
        return arrayList;
    }

    @VisibleForTesting
    static List<Long> getDailyTimestamps(List<Long> list) {
        ArrayList arrayList = new ArrayList();
        if (list.size() < 2) {
            return arrayList;
        }
        long longValue = list.get(0).longValue();
        long longValue2 = list.get(list.size() + SELECTED_INDEX_ALL).longValue();
        if (longValue2 - longValue < 7200000) {
            return arrayList;
        }
        long timestampOfNextDay = getTimestampOfNextDay(longValue);
        if (timestampOfNextDay - longValue >= 7200000) {
            arrayList.add(Long.valueOf(longValue));
        }
        while (timestampOfNextDay < longValue2) {
            arrayList.add(Long.valueOf(timestampOfNextDay));
            timestampOfNextDay += 86400000;
        }
        if (longValue2 - ((Long) arrayList.get(arrayList.size() + SELECTED_INDEX_ALL)).longValue() >= 7200000) {
            arrayList.add(Long.valueOf(longValue2));
        }
        return arrayList.size() < 2 ? new ArrayList() : arrayList;
    }

    @VisibleForTesting
    static boolean isFromFullCharge(Map<String, BatteryHistEntry> map) {
        if (map == null) {
            Log.d("DataProcessor", "entryList is null in isFromFullCharge()");
            return false;
        }
        ArrayList arrayList = new ArrayList(map.keySet());
        if (arrayList.isEmpty()) {
            Log.d("DataProcessor", "empty entryList in isFromFullCharge()");
            return false;
        }
        BatteryHistEntry batteryHistEntry = map.get(arrayList.get(0));
        return BatteryStatus.isCharged(batteryHistEntry.mBatteryStatus, batteryHistEntry.mBatteryLevel);
    }

    @VisibleForTesting
    static long[] findNearestTimestamp(List<Long> list, final long j) {
        final long[] jArr = {Long.MIN_VALUE, Long.MAX_VALUE};
        list.forEach(new Consumer() { // from class: com.android.settings.fuelgauge.batteryusage.DataProcessor$$ExternalSyntheticLambda1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                DataProcessor.lambda$findNearestTimestamp$0(j, jArr, (Long) obj);
            }
        });
        long j2 = jArr[0];
        if (j2 == Long.MIN_VALUE) {
            j2 = 0;
        }
        jArr[0] = j2;
        long j3 = jArr[1];
        jArr[1] = j3 != Long.MAX_VALUE ? j3 : 0L;
        return jArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$findNearestTimestamp$0(long j, long[] jArr, Long l) {
        if (l.longValue() <= j && l.longValue() > jArr[0]) {
            jArr[0] = l.longValue();
        }
        if (l.longValue() < j || l.longValue() >= jArr[1]) {
            return;
        }
        jArr[1] = l.longValue();
    }

    @VisibleForTesting
    static long getTimestampOfNextDay(long j) {
        return getTimestampWithDayDiff(j, 1);
    }

    @VisibleForTesting
    static boolean isForDailyChart(boolean z, long j) {
        return z || j == getTimestampWithDayDiff(j, 0);
    }

    @VisibleForTesting
    static Map<Integer, Map<Integer, BatteryDiffData>> getBatteryUsageMap(Context context, List<BatteryLevelData.PeriodBatteryLevelData> list, Map<Long, Map<String, BatteryHistEntry>> map) {
        if (map.isEmpty()) {
            return null;
        }
        HashMap hashMap = new HashMap();
        insertHourlyUsageDiffData(context, list, map, hashMap);
        insertDailyUsageDiffData(list, hashMap);
        insertAllUsageDiffData(hashMap);
        int countOfApps = getCountOfApps(hashMap);
        purgeLowPercentageAndFakeData(context, hashMap);
        int countOfApps2 = getCountOfApps(hashMap);
        if (isUsageMapValid(hashMap, list)) {
            logAppCountMetrics(context, countOfApps, countOfApps2);
            return hashMap;
        }
        return null;
    }

    @VisibleForTesting
    static BatteryDiffData generateBatteryDiffData(Context context, List<BatteryEntry> list, BatteryUsageStats batteryUsageStats) {
        int i;
        List<BatteryHistEntry> convertToBatteryHistEntry = convertToBatteryHistEntry(list, batteryUsageStats);
        if (convertToBatteryHistEntry == null || convertToBatteryHistEntry.isEmpty()) {
            Log.w("DataProcessor", "batteryHistEntryList is null or empty in generateBatteryDiffData()");
            return null;
        }
        int userId = context.getUserId();
        UserHandle managedProfile = Utils.getManagedProfile((UserManager) context.getSystemService(UserManager.class));
        int identifier = managedProfile != null ? managedProfile.getIdentifier() : Integer.MIN_VALUE;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        double d = 0.0d;
        double d2 = 0.0d;
        for (BatteryHistEntry batteryHistEntry : convertToBatteryHistEntry) {
            boolean isConsumedFromOtherUsers = isConsumedFromOtherUsers(userId, identifier, batteryHistEntry);
            double d3 = batteryHistEntry.mConsumePower;
            double d4 = d + d3;
            if (isConsumedFromOtherUsers) {
                d2 += d3;
                i = userId;
            } else {
                i = userId;
                double d5 = d2;
                BatteryDiffEntry batteryDiffEntry = new BatteryDiffEntry(context, batteryHistEntry.mForegroundUsageTimeInMs, batteryHistEntry.mBackgroundUsageTimeInMs, batteryHistEntry.mConsumePower, batteryHistEntry);
                if (batteryDiffEntry.isSystemEntry()) {
                    arrayList2.add(batteryDiffEntry);
                } else {
                    arrayList.add(batteryDiffEntry);
                }
                d2 = d5;
            }
            userId = i;
            d = d4;
        }
        if (d2 != 0.0d) {
            arrayList2.add(createOtherUsersEntry(context, d2));
        }
        if (arrayList.isEmpty() && arrayList2.isEmpty()) {
            return null;
        }
        return new BatteryDiffData(arrayList, arrayList2, d);
    }

    private static void loadBatteryUsageDataFromBatteryStatsService(Context context, Handler handler, UsageMapAsyncResponse usageMapAsyncResponse) {
        new LoadUsageMapFromBatteryStatsServiceTask(context, handler, usageMapAsyncResponse).execute(new Void[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Map<Integer, Map<Integer, BatteryDiffData>> getBatteryUsageMapFromStatsService(Context context) {
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        Integer valueOf = Integer.valueOf((int) SELECTED_INDEX_ALL);
        hashMap2.put(valueOf, getBatteryDiffDataFromBatteryStatsService(context));
        hashMap.put(valueOf, hashMap2);
        int countOfApps = getCountOfApps(hashMap);
        purgeLowPercentageAndFakeData(context, hashMap);
        logAppCountMetrics(context, countOfApps, getCountOfApps(hashMap));
        return hashMap;
    }

    private static BatteryDiffData getBatteryDiffDataFromBatteryStatsService(Context context) {
        try {
            BatteryUsageStats batteryUsageStats = ((BatteryStatsManager) context.getSystemService(BatteryStatsManager.class)).getBatteryUsageStats(new BatteryUsageStatsQuery.Builder().includeBatteryHistory().build());
            if (batteryUsageStats == null) {
                Log.w("DataProcessor", "batteryUsageStats is null content");
                return null;
            }
            return generateBatteryDiffData(context, generateBatteryEntryListFromBatteryUsageStats(context, batteryUsageStats), batteryUsageStats);
        } catch (RuntimeException e) {
            Log.e("DataProcessor", "load batteryUsageStats:" + e);
            return null;
        }
    }

    private static List<BatteryEntry> generateBatteryEntryListFromBatteryUsageStats(Context context, BatteryUsageStats batteryUsageStats) {
        return new BatteryAppListPreferenceController(context, null, null, null, null).getBatteryEntryList(batteryUsageStats, true);
    }

    private static List<BatteryHistEntry> convertToBatteryHistEntry(List<BatteryEntry> list, final BatteryUsageStats batteryUsageStats) {
        if (list == null || list.isEmpty()) {
            Log.w("DataProcessor", "batteryEntryList is null or empty in convertToBatteryHistEntry()");
            return null;
        }
        return (List) list.stream().filter(new Predicate() { // from class: com.android.settings.fuelgauge.batteryusage.DataProcessor$$ExternalSyntheticLambda3
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean lambda$convertToBatteryHistEntry$1;
                lambda$convertToBatteryHistEntry$1 = DataProcessor.lambda$convertToBatteryHistEntry$1((BatteryEntry) obj);
                return lambda$convertToBatteryHistEntry$1;
            }
        }).map(new Function() { // from class: com.android.settings.fuelgauge.batteryusage.DataProcessor$$ExternalSyntheticLambda4
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                BatteryHistEntry lambda$convertToBatteryHistEntry$2;
                lambda$convertToBatteryHistEntry$2 = DataProcessor.lambda$convertToBatteryHistEntry$2(batteryUsageStats, (BatteryEntry) obj);
                return lambda$convertToBatteryHistEntry$2;
            }
        }).collect(Collectors.toList());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$convertToBatteryHistEntry$1(BatteryEntry batteryEntry) {
        return batteryEntry.getConsumedPower() > 0.0d || (batteryEntry.getConsumedPower() == 0.0d && !(batteryEntry.getTimeInForegroundMs() == 0 && batteryEntry.getTimeInBackgroundMs() == 0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ BatteryHistEntry lambda$convertToBatteryHistEntry$2(BatteryUsageStats batteryUsageStats, BatteryEntry batteryEntry) {
        return ConvertUtils.convertToBatteryHistEntry(batteryEntry, batteryUsageStats);
    }

    private static void interpolateHistory(Context context, List<Long> list, List<Long> list2, boolean z, Map<Long, Map<String, BatteryHistEntry>> map, Map<Long, Map<String, BatteryHistEntry>> map2) {
        int i;
        if (list.isEmpty() || list2.isEmpty()) {
            return;
        }
        long longValue = list2.get(0).longValue();
        long longValue2 = list.get(0).longValue();
        if (z || longValue < longValue2) {
            map2.put(Long.valueOf(longValue), map.get(Long.valueOf(longValue2)));
            i = 1;
        } else {
            i = 0;
        }
        int size = list2.size();
        while (i < size) {
            interpolateHistoryForSlot(context, list2.get(i).longValue(), list, map, map2, i == 0 || i == size + SELECTED_INDEX_ALL);
            i++;
        }
    }

    private static void interpolateHistoryForSlot(Context context, long j, List<Long> list, Map<Long, Map<String, BatteryHistEntry>> map, Map<Long, Map<String, BatteryHistEntry>> map2, boolean z) {
        long[] findNearestTimestamp = findNearestTimestamp(list, j);
        long j2 = findNearestTimestamp[0];
        long j3 = findNearestTimestamp[1];
        if (j3 == 0) {
            log(context, "job scheduler is delayed", j, null);
            map2.put(Long.valueOf(j), new HashMap());
        } else if (j3 - j < 5000) {
            log(context, "force align into the nearest slot", j, null);
            map2.put(Long.valueOf(j), map.get(Long.valueOf(j3)));
        } else if (j2 == 0) {
            log(context, "no lower timestamp slot data", j, null);
            map2.put(Long.valueOf(j), new HashMap());
        } else {
            interpolateHistoryForSlot(context, j, j2, j3, map, map2, z);
        }
    }

    private static void interpolateHistoryForSlot(Context context, long j, long j2, long j3, Map<Long, Map<String, BatteryHistEntry>> map, Map<Long, Map<String, BatteryHistEntry>> map2, boolean z) {
        Map<String, BatteryHistEntry> map3 = map.get(Long.valueOf(j2));
        Map<String, BatteryHistEntry> map4 = map.get(Long.valueOf(j3));
        BatteryHistEntry batteryHistEntry = map4.values().stream().findFirst().get();
        if (j2 < batteryHistEntry.mTimestamp - batteryHistEntry.mBootTimestamp && !isForDailyChart(z, j)) {
            if (j3 - j < 600000) {
                log(context, "force align into the nearest slot", j, null);
                map2.put(Long.valueOf(j), map4);
                return;
            }
            log(context, "in the different booting section", j, null);
            map2.put(Long.valueOf(j), new HashMap());
            return;
        }
        log(context, "apply interpolation arithmetic", j, null);
        HashMap hashMap = new HashMap();
        double d = j3 - j2;
        double d2 = j - j2;
        Iterator<String> it = map4.keySet().iterator();
        while (it.hasNext()) {
            String next = it.next();
            BatteryHistEntry batteryHistEntry2 = map3.get(next);
            BatteryHistEntry batteryHistEntry3 = map4.get(next);
            Map<String, BatteryHistEntry> map5 = map3;
            Map<String, BatteryHistEntry> map6 = map4;
            if (batteryHistEntry2 != null) {
                double d3 = d2;
                boolean z2 = batteryHistEntry2.mForegroundUsageTimeInMs > batteryHistEntry3.mForegroundUsageTimeInMs;
                boolean z3 = batteryHistEntry2.mBackgroundUsageTimeInMs > batteryHistEntry3.mBackgroundUsageTimeInMs;
                if (z2 || z3) {
                    hashMap.put(next, batteryHistEntry3);
                    log(context, "abnormal reset condition is found", j, batteryHistEntry3);
                    d2 = d3;
                    map4 = map6;
                    map3 = map5;
                } else {
                    d2 = d3;
                }
            }
            Iterator<String> it2 = it;
            double d4 = d2;
            double d5 = d;
            hashMap.put(next, BatteryHistEntry.interpolate(j, j3, d2 / d, batteryHistEntry2, batteryHistEntry3));
            if (batteryHistEntry2 == null) {
                log(context, "cannot find lower entry data", j, batteryHistEntry3);
            }
            it = it2;
            d = d5;
            map3 = map5;
            d2 = d4;
            map4 = map6;
        }
        map2.put(Long.valueOf(j), hashMap);
    }

    private static long getNearestEvenHourTimestamp(long j) {
        return getEvenHourTimestamp(j, 1);
    }

    private static long getLastEvenHourBeforeTimestamp(long j) {
        return getEvenHourTimestamp(j, SELECTED_INDEX_ALL);
    }

    private static long getEvenHourTimestamp(long j, int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        if (calendar.get(11) % 2 != 0) {
            calendar.add(11, i);
        }
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar.getTimeInMillis();
    }

    private static List<List<Long>> getHourlyTimestamps(List<Long> list) {
        ArrayList arrayList = new ArrayList();
        if (list.size() < 2) {
            return arrayList;
        }
        int i = 0;
        while (i < list.size() + SELECTED_INDEX_ALL) {
            i++;
            long longValue = list.get(i).longValue();
            ArrayList arrayList2 = new ArrayList();
            for (long longValue2 = list.get(i).longValue(); longValue2 <= longValue; longValue2 += 7200000) {
                arrayList2.add(Long.valueOf(longValue2));
            }
            arrayList.add(arrayList2);
        }
        return arrayList;
    }

    private static List<BatteryLevelData.PeriodBatteryLevelData> getHourlyPeriodBatteryLevelData(final Context context, final Map<Long, Map<String, BatteryHistEntry>> map, List<List<Long>> list) {
        final ArrayList arrayList = new ArrayList();
        list.forEach(new Consumer() { // from class: com.android.settings.fuelgauge.batteryusage.DataProcessor$$ExternalSyntheticLambda2
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                DataProcessor.lambda$getHourlyPeriodBatteryLevelData$3(arrayList, context, map, (List) obj);
            }
        });
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$getHourlyPeriodBatteryLevelData$3(List list, Context context, Map map, List list2) {
        list.add(getPeriodBatteryLevelData(context, map, list2));
    }

    private static BatteryLevelData.PeriodBatteryLevelData getPeriodBatteryLevelData(final Context context, final Map<Long, Map<String, BatteryHistEntry>> map, List<Long> list) {
        final ArrayList arrayList = new ArrayList();
        list.forEach(new Consumer() { // from class: com.android.settings.fuelgauge.batteryusage.DataProcessor$$ExternalSyntheticLambda6
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                DataProcessor.lambda$getPeriodBatteryLevelData$4(arrayList, context, map, (Long) obj);
            }
        });
        return new BatteryLevelData.PeriodBatteryLevelData(list, arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$getPeriodBatteryLevelData$4(List list, Context context, Map map, Long l) {
        list.add(getLevel(context, map, l.longValue()));
    }

    private static Integer getLevel(Context context, Map<Long, Map<String, BatteryHistEntry>> map, long j) {
        Map<String, BatteryHistEntry> map2 = map.get(Long.valueOf(j));
        if (map2 == null || map2.isEmpty()) {
            Log.e("DataProcessor", "abnormal entry list in the timestamp:" + ConvertUtils.utcToLocalTime(context, j));
            return null;
        }
        float f = 0.0f;
        for (BatteryHistEntry batteryHistEntry : map2.values()) {
            f += batteryHistEntry.mBatteryLevel;
        }
        return Integer.valueOf(Math.round(f / map2.size()));
    }

    private static void insertHourlyUsageDiffData(Context context, List<BatteryLevelData.PeriodBatteryLevelData> list, Map<Long, Map<String, BatteryHistEntry>> map, Map<Integer, Map<Integer, BatteryDiffData>> map2) {
        int userId = context.getUserId();
        UserHandle managedProfile = Utils.getManagedProfile((UserManager) context.getSystemService(UserManager.class));
        int identifier = managedProfile != null ? managedProfile.getIdentifier() : Integer.MIN_VALUE;
        for (int i = 0; i < list.size(); i++) {
            HashMap hashMap = new HashMap();
            map2.put(Integer.valueOf(i), hashMap);
            if (list.get(i) != null) {
                List<Long> timestamps = list.get(i).getTimestamps();
                for (int i2 = 0; i2 < timestamps.size() + SELECTED_INDEX_ALL; i2++) {
                    hashMap.put(Integer.valueOf(i2), insertHourlyUsageDiffDataPerSlot(context, userId, identifier, i2, timestamps, map));
                }
            }
        }
    }

    private static void insertDailyUsageDiffData(List<BatteryLevelData.PeriodBatteryLevelData> list, Map<Integer, Map<Integer, BatteryDiffData>> map) {
        for (int i = 0; i < list.size(); i++) {
            Map<Integer, BatteryDiffData> map2 = map.get(Integer.valueOf(i));
            if (map2 == null) {
                map2 = new HashMap<>();
                map.put(Integer.valueOf(i), map2);
            }
            map2.put(Integer.valueOf((int) SELECTED_INDEX_ALL), getAccumulatedUsageDiffData(map2.values()));
        }
    }

    private static void insertAllUsageDiffData(final Map<Integer, Map<Integer, BatteryDiffData>> map) {
        final ArrayList arrayList = new ArrayList();
        map.keySet().forEach(new Consumer() { // from class: com.android.settings.fuelgauge.batteryusage.DataProcessor$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                DataProcessor.lambda$insertAllUsageDiffData$5(arrayList, map, (Integer) obj);
            }
        });
        HashMap hashMap = new HashMap();
        Integer valueOf = Integer.valueOf((int) SELECTED_INDEX_ALL);
        hashMap.put(valueOf, getAccumulatedUsageDiffData(arrayList));
        map.put(valueOf, hashMap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$insertAllUsageDiffData$5(List list, Map map, Integer num) {
        list.add((BatteryDiffData) ((Map) map.get(num)).get(Integer.valueOf((int) SELECTED_INDEX_ALL)));
    }

    private static BatteryDiffData insertHourlyUsageDiffDataPerSlot(Context context, int i, int i2, int i3, List<Long> list, Map<Long, Map<String, BatteryHistEntry>> map) {
        BatteryHistEntry selectBatteryHistEntry;
        ArrayList arrayList;
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        Long l = list.get(i3);
        Long valueOf = Long.valueOf(l.longValue() + 3600000);
        Long valueOf2 = Long.valueOf(valueOf.longValue() + 3600000);
        Map<String, BatteryHistEntry> map2 = EMPTY_BATTERY_MAP;
        Map<String, BatteryHistEntry> orDefault = map.getOrDefault(l, map2);
        Map<String, BatteryHistEntry> orDefault2 = map.getOrDefault(valueOf, map2);
        Map<String, BatteryHistEntry> orDefault3 = map.getOrDefault(valueOf2, map2);
        if (orDefault.isEmpty() || orDefault2.isEmpty() || orDefault3.isEmpty()) {
            return null;
        }
        ArraySet<String> arraySet = new ArraySet();
        arraySet.addAll(orDefault.keySet());
        arraySet.addAll(orDefault2.keySet());
        arraySet.addAll(orDefault3.keySet());
        double d = 0.0d;
        double d2 = 0.0d;
        for (String str : arraySet) {
            BatteryHistEntry batteryHistEntry = EMPTY_BATTERY_HIST_ENTRY;
            BatteryHistEntry orDefault4 = orDefault.getOrDefault(str, batteryHistEntry);
            BatteryHistEntry orDefault5 = orDefault2.getOrDefault(str, batteryHistEntry);
            BatteryHistEntry orDefault6 = orDefault3.getOrDefault(str, batteryHistEntry);
            Map<String, BatteryHistEntry> map3 = orDefault;
            Map<String, BatteryHistEntry> map4 = orDefault2;
            Map<String, BatteryHistEntry> map5 = orDefault3;
            ArrayList arrayList4 = arrayList2;
            long diffValue = getDiffValue(orDefault4.mForegroundUsageTimeInMs, orDefault5.mForegroundUsageTimeInMs, orDefault6.mForegroundUsageTimeInMs);
            double d3 = d;
            long diffValue2 = getDiffValue(orDefault4.mBackgroundUsageTimeInMs, orDefault5.mBackgroundUsageTimeInMs, orDefault6.mBackgroundUsageTimeInMs);
            double d4 = d2;
            double diffValue3 = getDiffValue(orDefault4.mConsumePower, orDefault5.mConsumePower, orDefault6.mConsumePower);
            if ((diffValue == 0 && diffValue2 == 0 && diffValue3 == 0.0d) || (selectBatteryHistEntry = selectBatteryHistEntry(orDefault4, orDefault5, orDefault6)) == null) {
                orDefault = map3;
                orDefault2 = map4;
                arrayList2 = arrayList4;
                orDefault3 = map5;
                d = d3;
                d2 = d4;
            } else {
                float f = (float) (diffValue + diffValue2);
                if (f > 7200000.0f) {
                    float f2 = 7200000.0f / f;
                    diffValue = Math.round(((float) diffValue) * f2);
                    diffValue2 = Math.round(((float) diffValue2) * f2);
                    diffValue3 *= f2;
                }
                long j = diffValue;
                long j2 = diffValue2;
                double d5 = diffValue3;
                d2 = d4 + d5;
                if (isConsumedFromOtherUsers(i, i2, selectBatteryHistEntry)) {
                    d = d3 + d5;
                    arrayList = arrayList4;
                } else {
                    BatteryDiffEntry batteryDiffEntry = new BatteryDiffEntry(context, j, j2, d5, selectBatteryHistEntry);
                    if (batteryDiffEntry.isSystemEntry()) {
                        arrayList3.add(batteryDiffEntry);
                        arrayList = arrayList4;
                    } else {
                        arrayList = arrayList4;
                        arrayList.add(batteryDiffEntry);
                    }
                    d = d3;
                }
                arrayList2 = arrayList;
                orDefault = map3;
                orDefault2 = map4;
                orDefault3 = map5;
            }
        }
        ArrayList arrayList5 = arrayList2;
        double d6 = d;
        double d7 = d2;
        if (d6 != 0.0d) {
            arrayList3.add(createOtherUsersEntry(context, d6));
        }
        if (arrayList5.isEmpty() && arrayList3.isEmpty()) {
            return null;
        }
        return new BatteryDiffData(arrayList5, arrayList3, d7);
    }

    private static boolean isConsumedFromOtherUsers(int i, int i2, BatteryHistEntry batteryHistEntry) {
        if (batteryHistEntry.mConsumerType == 1) {
            long j = batteryHistEntry.mUserId;
            if (j != i && j != i2) {
                return true;
            }
        }
        return false;
    }

    private static BatteryDiffData getAccumulatedUsageDiffData(Collection<BatteryDiffData> collection) {
        HashMap hashMap = new HashMap();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        double d = 0.0d;
        for (BatteryDiffData batteryDiffData : collection) {
            if (batteryDiffData != null) {
                for (BatteryDiffEntry batteryDiffEntry : batteryDiffData.getAppDiffEntryList()) {
                    computeUsageDiffDataPerEntry(batteryDiffEntry, hashMap);
                    d += batteryDiffEntry.mConsumePower;
                }
                for (BatteryDiffEntry batteryDiffEntry2 : batteryDiffData.getSystemDiffEntryList()) {
                    computeUsageDiffDataPerEntry(batteryDiffEntry2, hashMap);
                    d += batteryDiffEntry2.mConsumePower;
                }
            }
        }
        Collection<BatteryDiffEntry> values = hashMap.values();
        for (BatteryDiffEntry batteryDiffEntry3 : values) {
            batteryDiffEntry3.setTotalConsumePower(d);
            if (batteryDiffEntry3.isSystemEntry()) {
                arrayList2.add(batteryDiffEntry3);
            } else {
                arrayList.add(batteryDiffEntry3);
            }
        }
        if (values.isEmpty()) {
            return null;
        }
        return new BatteryDiffData(arrayList, arrayList2);
    }

    private static void computeUsageDiffDataPerEntry(BatteryDiffEntry batteryDiffEntry, Map<String, BatteryDiffEntry> map) {
        String key = batteryDiffEntry.mBatteryHistEntry.getKey();
        BatteryDiffEntry batteryDiffEntry2 = map.get(key);
        if (batteryDiffEntry2 == null) {
            map.put(key, batteryDiffEntry.m883clone());
            return;
        }
        batteryDiffEntry2.mForegroundUsageTimeInMs += batteryDiffEntry.mForegroundUsageTimeInMs;
        batteryDiffEntry2.mBackgroundUsageTimeInMs += batteryDiffEntry.mBackgroundUsageTimeInMs;
        batteryDiffEntry2.mConsumePower += batteryDiffEntry.mConsumePower;
    }

    private static void purgeLowPercentageAndFakeData(Context context, final Map<Integer, Map<Integer, BatteryDiffData>> map) {
        final Set<CharSequence> hideBackgroundUsageTimeSet = FeatureFactory.getFactory(context).getPowerUsageFeatureProvider(context).getHideBackgroundUsageTimeSet(context);
        final CharSequence[] hideApplicationEntries = FeatureFactory.getFactory(context).getPowerUsageFeatureProvider(context).getHideApplicationEntries(context);
        map.keySet().forEach(new Consumer() { // from class: com.android.settings.fuelgauge.batteryusage.DataProcessor$$ExternalSyntheticLambda5
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                DataProcessor.lambda$purgeLowPercentageAndFakeData$7(map, hideBackgroundUsageTimeSet, hideApplicationEntries, (Integer) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$purgeLowPercentageAndFakeData$7(Map map, final Set set, final CharSequence[] charSequenceArr, Integer num) {
        ((Map) map.get(num)).values().forEach(new Consumer() { // from class: com.android.settings.fuelgauge.batteryusage.DataProcessor$$ExternalSyntheticLambda7
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                DataProcessor.lambda$purgeLowPercentageAndFakeData$6(set, charSequenceArr, (BatteryDiffData) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$purgeLowPercentageAndFakeData$6(Set set, CharSequence[] charSequenceArr, BatteryDiffData batteryDiffData) {
        if (batteryDiffData == null) {
            return;
        }
        purgeLowPercentageAndFakeData(batteryDiffData.getAppDiffEntryList(), set, charSequenceArr);
        purgeLowPercentageAndFakeData(batteryDiffData.getSystemDiffEntryList(), set, charSequenceArr);
    }

    private static void purgeLowPercentageAndFakeData(List<BatteryDiffEntry> list, Set<CharSequence> set, CharSequence[] charSequenceArr) {
        Iterator<BatteryDiffEntry> it = list.iterator();
        while (it.hasNext()) {
            BatteryDiffEntry next = it.next();
            String packageName = next.getPackageName();
            if (next.getPercentOfTotal() < PERCENTAGE_OF_TOTAL_THRESHOLD || "fake_package".equals(packageName) || contains(packageName, charSequenceArr)) {
                it.remove();
            }
            if (packageName != null && !set.isEmpty() && contains(packageName, set)) {
                next.mBackgroundUsageTimeInMs = 0L;
            }
        }
    }

    private static boolean isUsageMapValid(Map<Integer, Map<Integer, BatteryDiffData>> map, List<BatteryLevelData.PeriodBatteryLevelData> list) {
        Integer valueOf = Integer.valueOf((int) SELECTED_INDEX_ALL);
        if (map.get(valueOf) == null || !map.get(valueOf).containsKey(valueOf)) {
            Log.e("DataProcessor", "no [SELECTED_INDEX_ALL][SELECTED_INDEX_ALL] in batteryUsageMap");
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            if (map.get(Integer.valueOf(i)) == null || !map.get(Integer.valueOf(i)).containsKey(valueOf)) {
                Log.e("DataProcessor", "no [" + i + "][SELECTED_INDEX_ALL] in batteryUsageMap, daily size is: " + list.size());
                return false;
            }
            if (list.get(i) != null) {
                List<Long> timestamps = list.get(i).getTimestamps();
                for (int i2 = 0; i2 < timestamps.size() - 1; i2++) {
                    if (!map.get(Integer.valueOf(i)).containsKey(Integer.valueOf(i2))) {
                        Log.e("DataProcessor", "no [" + i + "][" + i2 + "] in batteryUsageMap, hourly size is: " + (timestamps.size() - 1));
                        return false;
                    }
                }
                continue;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void loadLabelAndIcon(Map<Integer, Map<Integer, BatteryDiffData>> map) {
        BatteryDiffData batteryDiffData;
        if (map == null || (batteryDiffData = map.get(Integer.valueOf((int) SELECTED_INDEX_ALL)).get(Integer.valueOf((int) SELECTED_INDEX_ALL))) == null) {
            return;
        }
        batteryDiffData.getAppDiffEntryList().forEach(new Consumer() { // from class: com.android.settings.fuelgauge.batteryusage.DataProcessor$$ExternalSyntheticLambda8
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((BatteryDiffEntry) obj).loadLabelAndIcon();
            }
        });
        batteryDiffData.getSystemDiffEntryList().forEach(new Consumer() { // from class: com.android.settings.fuelgauge.batteryusage.DataProcessor$$ExternalSyntheticLambda9
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((BatteryDiffEntry) obj).loadLabelAndIcon();
            }
        });
    }

    private static long getTimestampWithDayDiff(long j, int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        calendar.add(6, i);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        return calendar.getTimeInMillis();
    }

    private static int getCountOfApps(Map<Integer, Map<Integer, BatteryDiffData>> map) {
        Integer valueOf = Integer.valueOf((int) SELECTED_INDEX_ALL);
        BatteryDiffData batteryDiffData = map.get(valueOf).get(valueOf);
        if (batteryDiffData == null) {
            return 0;
        }
        return batteryDiffData.getSystemDiffEntryList().size() + batteryDiffData.getAppDiffEntryList().size();
    }

    private static boolean contains(String str, Set<CharSequence> set) {
        if (str == null || set == null) {
            return false;
        }
        for (CharSequence charSequence : set) {
            if (TextUtils.equals(str, charSequence)) {
                return true;
            }
        }
        return false;
    }

    private static BatteryHistEntry selectBatteryHistEntry(BatteryHistEntry... batteryHistEntryArr) {
        for (BatteryHistEntry batteryHistEntry : batteryHistEntryArr) {
            if (batteryHistEntry != null && batteryHistEntry != EMPTY_BATTERY_HIST_ENTRY) {
                return batteryHistEntry;
            }
        }
        return null;
    }

    private static BatteryDiffEntry createOtherUsersEntry(Context context, double d) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("uid", (Long) Long.MIN_VALUE);
        contentValues.put("userId", (Long) Long.MIN_VALUE);
        contentValues.put("consumerType", (Integer) 1);
        return new BatteryDiffEntry(context, 0L, 0L, d, new BatteryHistEntry(contentValues));
    }

    private static void logAppCountMetrics(Context context, int i, int i2) {
        Context applicationContext = context.getApplicationContext();
        MetricsFeatureProvider metricsFeatureProvider = FeatureFactory.getFactory(applicationContext).getMetricsFeatureProvider();
        metricsFeatureProvider.action(applicationContext, 1802, i2);
        metricsFeatureProvider.action(applicationContext, 1803, i - i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class ComputeUsageMapAndLoadItemsTask extends AsyncTask<Void, Void, Map<Integer, Map<Integer, BatteryDiffData>>> {
        Context mApplicationContext;
        final UsageMapAsyncResponse mAsyncResponseDelegate;
        private Map<Long, Map<String, BatteryHistEntry>> mBatteryHistoryMap;
        final Handler mHandler;
        private List<BatteryLevelData.PeriodBatteryLevelData> mHourlyBatteryLevelsPerDay;

        private ComputeUsageMapAndLoadItemsTask(Context context, Handler handler, UsageMapAsyncResponse usageMapAsyncResponse, List<BatteryLevelData.PeriodBatteryLevelData> list, Map<Long, Map<String, BatteryHistEntry>> map) {
            this.mApplicationContext = context.getApplicationContext();
            this.mHandler = handler;
            this.mAsyncResponseDelegate = usageMapAsyncResponse;
            this.mHourlyBatteryLevelsPerDay = list;
            this.mBatteryHistoryMap = map;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Map<Integer, Map<Integer, BatteryDiffData>> doInBackground(Void... voidArr) {
            if (this.mApplicationContext == null || this.mHandler == null || this.mAsyncResponseDelegate == null || this.mBatteryHistoryMap == null || this.mHourlyBatteryLevelsPerDay == null) {
                Log.e("DataProcessor", "invalid input for ComputeUsageMapAndLoadItemsTask()");
                return null;
            }
            long currentTimeMillis = System.currentTimeMillis();
            Map<Integer, Map<Integer, BatteryDiffData>> batteryUsageMap = DataProcessor.getBatteryUsageMap(this.mApplicationContext, this.mHourlyBatteryLevelsPerDay, this.mBatteryHistoryMap);
            DataProcessor.loadLabelAndIcon(batteryUsageMap);
            Log.d("DataProcessor", String.format("execute ComputeUsageMapAndLoadItemsTask in %d/ms", Long.valueOf(System.currentTimeMillis() - currentTimeMillis)));
            return batteryUsageMap;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(final Map<Integer, Map<Integer, BatteryDiffData>> map) {
            this.mApplicationContext = null;
            this.mHourlyBatteryLevelsPerDay = null;
            this.mBatteryHistoryMap = null;
            Handler handler = this.mHandler;
            if (handler == null || this.mAsyncResponseDelegate == null) {
                return;
            }
            handler.post(new Runnable() { // from class: com.android.settings.fuelgauge.batteryusage.DataProcessor$ComputeUsageMapAndLoadItemsTask$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    DataProcessor.ComputeUsageMapAndLoadItemsTask.this.lambda$onPostExecute$0(map);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onPostExecute$0(Map map) {
            this.mAsyncResponseDelegate.onBatteryUsageMapLoaded(map);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class LoadUsageMapFromBatteryStatsServiceTask extends ComputeUsageMapAndLoadItemsTask {
        private LoadUsageMapFromBatteryStatsServiceTask(Context context, Handler handler, UsageMapAsyncResponse usageMapAsyncResponse) {
            super(context, handler, usageMapAsyncResponse, null, null);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.android.settings.fuelgauge.batteryusage.DataProcessor.ComputeUsageMapAndLoadItemsTask, android.os.AsyncTask
        public Map<Integer, Map<Integer, BatteryDiffData>> doInBackground(Void... voidArr) {
            if (this.mApplicationContext == null || this.mHandler == null || this.mAsyncResponseDelegate == null) {
                Log.e("DataProcessor", "invalid input for ComputeUsageMapAndLoadItemsTask()");
                return null;
            }
            long currentTimeMillis = System.currentTimeMillis();
            Map<Integer, Map<Integer, BatteryDiffData>> batteryUsageMapFromStatsService = DataProcessor.getBatteryUsageMapFromStatsService(this.mApplicationContext);
            DataProcessor.loadLabelAndIcon(batteryUsageMapFromStatsService);
            Log.d("DataProcessor", String.format("execute LoadUsageMapFromBatteryStatsServiceTask in %d/ms", Long.valueOf(System.currentTimeMillis() - currentTimeMillis)));
            return batteryUsageMapFromStatsService;
        }
    }
}
