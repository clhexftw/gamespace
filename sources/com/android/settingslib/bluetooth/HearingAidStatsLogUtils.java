package com.android.settingslib.bluetooth;

import android.util.Log;
import com.android.internal.annotations.VisibleForTesting;
import com.android.internal.util.FrameworkStatsLog;
import java.util.HashMap;
/* loaded from: classes2.dex */
public final class HearingAidStatsLogUtils {
    private static final HashMap<String, Integer> sDeviceAddressToBondEntryMap = new HashMap<>();

    public static void setBondEntryForDevice(int i, CachedBluetoothDevice cachedBluetoothDevice) {
        sDeviceAddressToBondEntryMap.put(cachedBluetoothDevice.getAddress(), Integer.valueOf(i));
    }

    public static void logHearingAidInfo(CachedBluetoothDevice cachedBluetoothDevice) {
        String address = cachedBluetoothDevice.getAddress();
        HashMap<String, Integer> hashMap = sDeviceAddressToBondEntryMap;
        if (hashMap.containsKey(address)) {
            FrameworkStatsLog.write(513, cachedBluetoothDevice.getDeviceMode(), cachedBluetoothDevice.getDeviceSide(), hashMap.getOrDefault(address, -1).intValue());
            hashMap.remove(address);
            return;
        }
        Log.w("HearingAidStatsLogUtils", "The device address was not found. Hearing aid device info is not logged.");
    }

    @VisibleForTesting
    static HashMap<String, Integer> getDeviceAddressToBondEntryMap() {
        return sDeviceAddressToBondEntryMap;
    }
}
