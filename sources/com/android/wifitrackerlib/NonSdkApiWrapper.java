package com.android.wifitrackerlib;

import android.app.admin.DevicePolicyManager;
import android.app.admin.WifiSsidPolicy;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.vcn.VcnTransportInfo;
import android.net.wifi.WifiInfo;
import android.os.Handler;
import android.os.UserManager;
import androidx.core.os.BuildCompat;
/* loaded from: classes2.dex */
class NonSdkApiWrapper {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static void startCaptivePortalApp(ConnectivityManager connectivityManager, Network network) {
        connectivityManager.startCaptivePortalApp(network);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isVcnOverWifi(NetworkCapabilities networkCapabilities) {
        VcnTransportInfo transportInfo = networkCapabilities.getTransportInfo();
        return (transportInfo == null || !(transportInfo instanceof VcnTransportInfo) || transportInfo.getWifiInfo() == null) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isDemoMode(Context context) {
        return UserManager.isDeviceInDemoMode(context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void registerSystemDefaultNetworkCallback(ConnectivityManager connectivityManager, ConnectivityManager.NetworkCallback networkCallback, Handler handler) {
        connectivityManager.registerSystemDefaultNetworkCallback(networkCallback, handler);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isPrimary(WifiInfo wifiInfo) {
        return wifiInfo.isPrimary();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static WifiSsidPolicy getWifiSsidPolicy(DevicePolicyManager devicePolicyManager) {
        if (BuildCompat.isAtLeastT()) {
            return devicePolicyManager.getWifiSsidPolicy();
        }
        return null;
    }
}
