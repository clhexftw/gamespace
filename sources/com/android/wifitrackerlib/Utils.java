package com.android.wifitrackerlib;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiEnterpriseConfig;
import android.os.UserHandle;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.util.Pair;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringJoiner;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
/* loaded from: classes2.dex */
public class Utils {
    public static int convertSecurityTypeToDpmWifiSecurity(int i) {
        switch (i) {
            case 0:
            case 6:
                return 0;
            case 1:
            case 2:
            case 4:
            case 7:
                return 1;
            case 3:
            case 8:
            case 9:
            case 11:
            case 12:
                return 2;
            case 5:
                return 3;
            case 10:
            default:
                return -1;
        }
    }

    public static ScanResult getBestScanResultByLevel(List<ScanResult> list) {
        if (list.isEmpty()) {
            return null;
        }
        return (ScanResult) Collections.max(list, Comparator.comparingInt(new ToIntFunction() { // from class: com.android.wifitrackerlib.Utils$$ExternalSyntheticLambda0
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                int i;
                i = ((ScanResult) obj).level;
                return i;
            }
        }));
    }

    public static List<Integer> getSecurityTypesFromScanResult(ScanResult scanResult) {
        ArrayList arrayList = new ArrayList();
        for (int i : scanResult.getSecurityTypes()) {
            arrayList.add(Integer.valueOf(i));
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List<Integer> getSecurityTypesFromWifiConfiguration(WifiConfiguration wifiConfiguration) {
        if (wifiConfiguration.allowedKeyManagement.get(14)) {
            return Arrays.asList(8);
        }
        if (wifiConfiguration.allowedKeyManagement.get(13)) {
            return Arrays.asList(7);
        }
        if (wifiConfiguration.allowedKeyManagement.get(10)) {
            return Arrays.asList(5);
        }
        if (wifiConfiguration.allowedKeyManagement.get(9)) {
            return Arrays.asList(6);
        }
        if (wifiConfiguration.allowedKeyManagement.get(8)) {
            return Arrays.asList(4);
        }
        if (wifiConfiguration.allowedKeyManagement.get(4)) {
            return Arrays.asList(2);
        }
        if (wifiConfiguration.allowedKeyManagement.get(2)) {
            return (wifiConfiguration.requirePmf && !wifiConfiguration.allowedPairwiseCiphers.get(1) && wifiConfiguration.allowedProtocols.get(1)) ? Arrays.asList(9) : Arrays.asList(3, 9);
        } else if (wifiConfiguration.allowedKeyManagement.get(1)) {
            return Arrays.asList(2);
        } else {
            if (wifiConfiguration.allowedKeyManagement.get(0) && wifiConfiguration.wepKeys != null) {
                int i = 0;
                while (true) {
                    String[] strArr = wifiConfiguration.wepKeys;
                    if (i >= strArr.length) {
                        break;
                    } else if (strArr[i] != null) {
                        return Arrays.asList(1);
                    } else {
                        i++;
                    }
                }
            }
            return Arrays.asList(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getSingleSecurityTypeFromMultipleSecurityTypes(List<Integer> list) {
        if (list.size() == 1) {
            return list.get(0).intValue();
        }
        if (list.size() == 2) {
            if (list.contains(0)) {
                return 0;
            }
            if (list.contains(2)) {
                return 2;
            }
            return list.contains(3) ? 3 : -1;
        }
        return -1;
    }

    static String getAppLabel(Context context, String str) {
        try {
            return context.getPackageManager().getApplicationInfo(str, 0).loadLabel(context.getPackageManager()).toString();
        } catch (PackageManager.NameNotFoundException unused) {
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00ed  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String getConnectedDescription(android.content.Context r6, android.net.wifi.WifiConfiguration r7, android.net.NetworkCapabilities r8, boolean r9, boolean r10, android.net.ConnectivityDiagnosticsManager.ConnectivityReport r11) {
        /*
            java.util.StringJoiner r0 = new java.util.StringJoiner
            int r1 = com.android.wifitrackerlib.R$string.wifitrackerlib_summary_separator
            java.lang.String r2 = r6.getString(r1)
            r0.<init>(r2)
            r2 = 1
            r3 = 0
            if (r7 == 0) goto L3f
            boolean r4 = r7.fromWifiNetworkSuggestion
            if (r4 != 0) goto L17
            boolean r4 = r7.fromWifiNetworkSpecifier
            if (r4 == 0) goto L3f
        L17:
            java.lang.String r4 = getSuggestionOrSpecifierLabel(r6, r7)
            boolean r5 = android.text.TextUtils.isEmpty(r4)
            if (r5 != 0) goto L3f
            if (r9 != 0) goto L31
            int r9 = com.android.wifitrackerlib.R$string.wifitrackerlib_available_via_app
            java.lang.Object[] r5 = new java.lang.Object[r2]
            r5[r3] = r4
            java.lang.String r9 = r6.getString(r9, r5)
            r0.add(r9)
            goto L3e
        L31:
            int r9 = com.android.wifitrackerlib.R$string.wifitrackerlib_connected_via_app
            java.lang.Object[] r5 = new java.lang.Object[r2]
            r5[r3] = r4
            java.lang.String r9 = r6.getString(r9, r5)
            r0.add(r9)
        L3e:
            r9 = r3
        L3f:
            if (r10 == 0) goto L4b
            int r9 = com.android.wifitrackerlib.R$string.wifi_connected_low_quality
            java.lang.String r9 = r6.getString(r9)
            r0.add(r9)
            r9 = r3
        L4b:
            if (r8 == 0) goto Lc2
            r10 = 17
            boolean r10 = r8.hasCapability(r10)
            if (r10 == 0) goto L6b
            android.content.res.Resources r7 = r6.getResources()
            java.lang.String r8 = "network_available_sign_in"
            java.lang.String r9 = "string"
            java.lang.String r10 = "android"
            int r7 = r7.getIdentifier(r8, r9, r10)
            java.lang.String r7 = r6.getString(r7)
            r0.add(r7)
            goto Lc3
        L6b:
            r10 = 24
            boolean r10 = r8.hasCapability(r10)
            if (r10 == 0) goto L7d
            int r7 = com.android.wifitrackerlib.R$string.wifitrackerlib_wifi_limited_connection
            java.lang.String r7 = r6.getString(r7)
            r0.add(r7)
            goto Lc3
        L7d:
            r10 = 16
            boolean r10 = r8.hasCapability(r10)
            if (r10 != 0) goto Lc2
            if (r7 == 0) goto L8e
            boolean r7 = r7.isNoInternetAccessExpected()
            if (r7 == 0) goto L8e
            goto L8f
        L8e:
            r2 = r3
        L8f:
            if (r11 != 0) goto L9d
            if (r2 != 0) goto L9d
            int r7 = com.android.wifitrackerlib.R$string.wifitrackerlib_checking_for_internet_access
            java.lang.String r7 = r6.getString(r7)
            r0.add(r7)
            goto Lc3
        L9d:
            boolean r7 = r8.isPrivateDnsBroken()
            if (r7 == 0) goto Lad
            int r7 = com.android.wifitrackerlib.R$string.wifitrackerlib_private_dns_broken
            java.lang.String r7 = r6.getString(r7)
            r0.add(r7)
            goto Lc3
        Lad:
            if (r2 == 0) goto Lb9
            int r7 = com.android.wifitrackerlib.R$string.wifitrackerlib_wifi_connected_cannot_provide_internet
            java.lang.String r7 = r6.getString(r7)
            r0.add(r7)
            goto Lc3
        Lb9:
            int r7 = com.android.wifitrackerlib.R$string.wifitrackerlib_wifi_no_internet
            java.lang.String r7 = r6.getString(r7)
            r0.add(r7)
        Lc2:
            r3 = r9
        Lc3:
            if (r3 == 0) goto Led
            java.util.StringJoiner r7 = new java.util.StringJoiner
            java.lang.String r8 = r6.getString(r1)
            r7.<init>(r8)
            android.content.res.Resources r6 = r6.getResources()
            int r8 = com.android.wifitrackerlib.R$array.wifitrackerlib_wifi_status
            java.lang.String[] r6 = r6.getStringArray(r8)
            android.net.NetworkInfo$DetailedState r8 = android.net.NetworkInfo.DetailedState.CONNECTED
            int r8 = r8.ordinal()
            r6 = r6[r8]
            java.util.StringJoiner r6 = r7.add(r6)
            java.util.StringJoiner r6 = r6.merge(r0)
            java.lang.String r6 = r6.toString()
            return r6
        Led:
            java.lang.String r6 = r0.toString()
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.wifitrackerlib.Utils.getConnectedDescription(android.content.Context, android.net.wifi.WifiConfiguration, android.net.NetworkCapabilities, boolean, boolean, android.net.ConnectivityDiagnosticsManager$ConnectivityReport):java.lang.String");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getConnectingDescription(Context context, NetworkInfo networkInfo) {
        NetworkInfo.DetailedState detailedState;
        if (context == null || networkInfo == null || (detailedState = networkInfo.getDetailedState()) == null) {
            return "";
        }
        String[] stringArray = context.getResources().getStringArray(R$array.wifitrackerlib_wifi_status);
        int ordinal = detailedState.ordinal();
        return ordinal >= stringArray.length ? "" : stringArray[ordinal];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getDisconnectedDescription(WifiTrackerInjector wifiTrackerInjector, Context context, WifiConfiguration wifiConfiguration, boolean z, boolean z2) {
        if (context == null || wifiConfiguration == null) {
            return "";
        }
        StringJoiner stringJoiner = new StringJoiner(context.getString(R$string.wifitrackerlib_summary_separator));
        if (z2) {
            stringJoiner.add(context.getString(R$string.wifitrackerlib_wifi_disconnected));
        } else if (z && !wifiConfiguration.isPasspoint()) {
            if (!wifiTrackerInjector.getNoAttributionAnnotationPackages().contains(wifiConfiguration.creatorName)) {
                String appLabel = getAppLabel(context, wifiConfiguration.creatorName);
                if (!TextUtils.isEmpty(appLabel)) {
                    stringJoiner.add(context.getString(R$string.wifitrackerlib_saved_network, appLabel));
                }
            }
        } else if (wifiConfiguration.fromWifiNetworkSuggestion) {
            String suggestionOrSpecifierLabel = getSuggestionOrSpecifierLabel(context, wifiConfiguration);
            if (!TextUtils.isEmpty(suggestionOrSpecifierLabel)) {
                stringJoiner.add(context.getString(R$string.wifitrackerlib_available_via_app, suggestionOrSpecifierLabel));
            }
        } else {
            stringJoiner.add(context.getString(R$string.wifitrackerlib_wifi_remembered));
        }
        String wifiConfigurationFailureMessage = getWifiConfigurationFailureMessage(context, wifiConfiguration);
        if (!TextUtils.isEmpty(wifiConfigurationFailureMessage)) {
            stringJoiner.add(wifiConfigurationFailureMessage);
        }
        return stringJoiner.toString();
    }

    private static String getSuggestionOrSpecifierLabel(Context context, WifiConfiguration wifiConfiguration) {
        if (context == null || wifiConfiguration == null) {
            return "";
        }
        String carrierNameForSubId = getCarrierNameForSubId(context, getSubIdForConfig(context, wifiConfiguration));
        if (TextUtils.isEmpty(carrierNameForSubId)) {
            String appLabel = getAppLabel(context, wifiConfiguration.creatorName);
            return !TextUtils.isEmpty(appLabel) ? appLabel : wifiConfiguration.creatorName;
        }
        return carrierNameForSubId;
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x004d, code lost:
        if (r1 != 9) goto L42;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String getWifiConfigurationFailureMessage(android.content.Context r4, android.net.wifi.WifiConfiguration r5) {
        /*
            java.lang.String r0 = ""
            if (r4 == 0) goto Lb0
            if (r5 != 0) goto L8
            goto Lb0
        L8:
            boolean r1 = r5.hasNoInternetAccess()
            r2 = 2
            if (r1 == 0) goto L23
            android.net.wifi.WifiConfiguration$NetworkSelectionStatus r5 = r5.getNetworkSelectionStatus()
            int r5 = r5.getNetworkSelectionStatus()
            if (r5 != r2) goto L1c
            int r5 = com.android.wifitrackerlib.R$string.wifitrackerlib_wifi_no_internet_no_reconnect
            goto L1e
        L1c:
            int r5 = com.android.wifitrackerlib.R$string.wifitrackerlib_wifi_no_internet
        L1e:
            java.lang.String r4 = r4.getString(r5)
            return r4
        L23:
            android.net.wifi.WifiConfiguration$NetworkSelectionStatus r1 = r5.getNetworkSelectionStatus()
            int r1 = r1.getNetworkSelectionStatus()
            if (r1 == 0) goto L7a
            android.net.wifi.WifiConfiguration$NetworkSelectionStatus r1 = r5.getNetworkSelectionStatus()
            int r1 = r1.getNetworkSelectionDisableReason()
            r3 = 1
            if (r1 == r3) goto L73
            if (r1 == r2) goto L6c
            r2 = 3
            if (r1 == r2) goto L65
            r2 = 4
            if (r1 == r2) goto L5e
            r2 = 6
            if (r1 == r2) goto L5e
            r2 = 13
            if (r1 == r2) goto L57
            r2 = 8
            if (r1 == r2) goto L50
            r2 = 9
            if (r1 == r2) goto L6c
            goto L7a
        L50:
            int r5 = com.android.wifitrackerlib.R$string.wifitrackerlib_wifi_check_password_try_again
            java.lang.String r4 = r4.getString(r5)
            return r4
        L57:
            int r5 = com.android.wifitrackerlib.R$string.wifitrackerlib_wifi_disabled_transition_disable_indication
            java.lang.String r4 = r4.getString(r5)
            return r4
        L5e:
            int r5 = com.android.wifitrackerlib.R$string.wifitrackerlib_wifi_no_internet_no_reconnect
            java.lang.String r4 = r4.getString(r5)
            return r4
        L65:
            int r5 = com.android.wifitrackerlib.R$string.wifitrackerlib_wifi_disabled_network_failure
            java.lang.String r4 = r4.getString(r5)
            return r4
        L6c:
            int r5 = com.android.wifitrackerlib.R$string.wifitrackerlib_wifi_disabled_password_failure
            java.lang.String r4 = r4.getString(r5)
            return r4
        L73:
            int r5 = com.android.wifitrackerlib.R$string.wifitrackerlib_wifi_disabled_generic
            java.lang.String r4 = r4.getString(r5)
            return r4
        L7a:
            int r5 = r5.getRecentFailureReason()
            r1 = 17
            if (r5 == r1) goto La9
            switch(r5) {
                case 1002: goto La9;
                case 1003: goto La2;
                case 1004: goto La9;
                case 1005: goto L9b;
                case 1006: goto L94;
                case 1007: goto L9b;
                case 1008: goto L9b;
                case 1009: goto L8d;
                case 1010: goto L8d;
                case 1011: goto L86;
                default: goto L85;
            }
        L85:
            return r0
        L86:
            int r5 = com.android.wifitrackerlib.R$string.wifitrackerlib_wifi_network_not_found
            java.lang.String r4 = r4.getString(r5)
            return r4
        L8d:
            int r5 = com.android.wifitrackerlib.R$string.wifitrackerlib_wifi_mbo_oce_assoc_disallowed_insufficient_rssi
            java.lang.String r4 = r4.getString(r5)
            return r4
        L94:
            int r5 = com.android.wifitrackerlib.R$string.wifitrackerlib_wifi_mbo_assoc_disallowed_max_num_sta_associated
            java.lang.String r4 = r4.getString(r5)
            return r4
        L9b:
            int r5 = com.android.wifitrackerlib.R$string.wifitrackerlib_wifi_mbo_assoc_disallowed_cannot_connect
            java.lang.String r4 = r4.getString(r5)
            return r4
        La2:
            int r5 = com.android.wifitrackerlib.R$string.wifitrackerlib_wifi_poor_channel_conditions
            java.lang.String r4 = r4.getString(r5)
            return r4
        La9:
            int r5 = com.android.wifitrackerlib.R$string.wifitrackerlib_wifi_ap_unable_to_handle_new_sta
            java.lang.String r4 = r4.getString(r5)
            return r4
        Lb0:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.wifitrackerlib.Utils.getWifiConfigurationFailureMessage(android.content.Context, android.net.wifi.WifiConfiguration):java.lang.String");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getAutoConnectDescription(Context context, WifiEntry wifiEntry) {
        return (context == null || wifiEntry == null || !wifiEntry.canSetAutoJoinEnabled() || wifiEntry.isAutoJoinEnabled()) ? "" : context.getString(R$string.wifitrackerlib_auto_connect_disable);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getMeteredDescription(Context context, WifiEntry wifiEntry) {
        if (context == null || wifiEntry == null) {
            return "";
        }
        if (wifiEntry.canSetMeteredChoice() || wifiEntry.getMeteredChoice() == 1) {
            if (wifiEntry.getMeteredChoice() == 1) {
                return context.getString(R$string.wifitrackerlib_wifi_metered_label);
            }
            if (wifiEntry.getMeteredChoice() == 2) {
                return context.getString(R$string.wifitrackerlib_wifi_unmetered_label);
            }
            return wifiEntry.isMetered() ? context.getString(R$string.wifitrackerlib_wifi_metered_label) : "";
        }
        return "";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getVerboseLoggingDescription(WifiEntry wifiEntry) {
        if (!BaseWifiTracker.isVerboseLoggingEnabled() || wifiEntry == null) {
            return "";
        }
        StringJoiner stringJoiner = new StringJoiner(" ");
        String wifiInfoDescription = wifiEntry.getWifiInfoDescription();
        if (!TextUtils.isEmpty(wifiInfoDescription)) {
            stringJoiner.add(wifiInfoDescription);
        }
        String networkCapabilityDescription = wifiEntry.getNetworkCapabilityDescription();
        if (!TextUtils.isEmpty(networkCapabilityDescription)) {
            stringJoiner.add(networkCapabilityDescription);
        }
        String scanResultDescription = wifiEntry.getScanResultDescription();
        if (!TextUtils.isEmpty(scanResultDescription)) {
            stringJoiner.add(scanResultDescription);
        }
        String networkSelectionDescription = wifiEntry.getNetworkSelectionDescription();
        if (!TextUtils.isEmpty(networkSelectionDescription)) {
            stringJoiner.add(networkSelectionDescription);
        }
        return stringJoiner.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getNetworkSelectionDescription(WifiConfiguration wifiConfiguration) {
        if (wifiConfiguration == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        WifiConfiguration.NetworkSelectionStatus networkSelectionStatus = wifiConfiguration.getNetworkSelectionStatus();
        if (networkSelectionStatus.getNetworkSelectionStatus() != 0) {
            sb.append(" (" + networkSelectionStatus.getNetworkStatusString());
            if (networkSelectionStatus.getDisableTime() > 0) {
                sb.append(" " + DateUtils.formatElapsedTime((System.currentTimeMillis() - networkSelectionStatus.getDisableTime()) / 1000));
            }
            sb.append(")");
        }
        int maxNetworkSelectionDisableReason = WifiConfiguration.NetworkSelectionStatus.getMaxNetworkSelectionDisableReason();
        for (int i = 0; i <= maxNetworkSelectionDisableReason; i++) {
            int disableReasonCounter = networkSelectionStatus.getDisableReasonCounter(i);
            if (disableReasonCounter != 0) {
                sb.append(" ");
                sb.append(WifiConfiguration.NetworkSelectionStatus.getNetworkSelectionDisableReasonString(i));
                sb.append("=");
                sb.append(disableReasonCounter);
            }
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isSimPresent(Context context, final int i) {
        List<SubscriptionInfo> activeSubscriptionInfoList;
        SubscriptionManager subscriptionManager = (SubscriptionManager) context.getSystemService("telephony_subscription_service");
        if (subscriptionManager == null || (activeSubscriptionInfoList = subscriptionManager.getActiveSubscriptionInfoList()) == null || activeSubscriptionInfoList.isEmpty()) {
            return false;
        }
        if (i == -1) {
            return true;
        }
        return activeSubscriptionInfoList.stream().anyMatch(new Predicate() { // from class: com.android.wifitrackerlib.Utils$$ExternalSyntheticLambda1
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean lambda$isSimPresent$1;
                lambda$isSimPresent$1 = Utils.lambda$isSimPresent$1(i, (SubscriptionInfo) obj);
                return lambda$isSimPresent$1;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$isSimPresent$1(int i, SubscriptionInfo subscriptionInfo) {
        return subscriptionInfo.getCarrierId() == i;
    }

    static String getCarrierNameForSubId(Context context, int i) {
        TelephonyManager telephonyManager;
        TelephonyManager createForSubscriptionId;
        CharSequence simCarrierIdName;
        if (i == -1 || (telephonyManager = (TelephonyManager) context.getSystemService("phone")) == null || (createForSubscriptionId = telephonyManager.createForSubscriptionId(i)) == null || (simCarrierIdName = createForSubscriptionId.getSimCarrierIdName()) == null) {
            return null;
        }
        return simCarrierIdName.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isSimCredential(WifiConfiguration wifiConfiguration) {
        WifiEnterpriseConfig wifiEnterpriseConfig = wifiConfiguration.enterpriseConfig;
        return wifiEnterpriseConfig != null && wifiEnterpriseConfig.isAuthenticationSimBased();
    }

    static int getSubIdForConfig(Context context, WifiConfiguration wifiConfiguration) {
        SubscriptionManager subscriptionManager;
        int i = -1;
        if (wifiConfiguration.carrierId == -1 || (subscriptionManager = (SubscriptionManager) context.getSystemService("telephony_subscription_service")) == null) {
            return -1;
        }
        List<SubscriptionInfo> activeSubscriptionInfoList = subscriptionManager.getActiveSubscriptionInfoList();
        if (activeSubscriptionInfoList != null && !activeSubscriptionInfoList.isEmpty()) {
            int defaultDataSubscriptionId = SubscriptionManager.getDefaultDataSubscriptionId();
            for (SubscriptionInfo subscriptionInfo : activeSubscriptionInfoList) {
                if (subscriptionInfo.getCarrierId() == wifiConfiguration.carrierId && (i = subscriptionInfo.getSubscriptionId()) == defaultDataSubscriptionId) {
                    break;
                }
            }
        }
        return i;
    }

    public static InetAddress getNetworkPart(InetAddress inetAddress, int i) {
        byte[] address = inetAddress.getAddress();
        maskRawAddress(address, i);
        try {
            return InetAddress.getByAddress(address);
        } catch (UnknownHostException e) {
            throw new RuntimeException("getNetworkPart error - " + e.toString());
        }
    }

    public static void maskRawAddress(byte[] bArr, int i) {
        if (i < 0 || i > bArr.length * 8) {
            throw new RuntimeException("IP address with " + bArr.length + " bytes has invalid prefix length " + i);
        }
        int i2 = i / 8;
        byte b = (byte) (255 << (8 - (i % 8)));
        if (i2 < bArr.length) {
            bArr[i2] = (byte) (b & bArr[i2]);
        }
        while (true) {
            i2++;
            if (i2 >= bArr.length) {
                return;
            }
            bArr[i2] = 0;
        }
    }

    private static Context createPackageContextAsUser(int i, Context context) {
        try {
            return context.createPackageContextAsUser(context.getPackageName(), 0, UserHandle.getUserHandleForUid(i));
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    private static DevicePolicyManager retrieveDevicePolicyManagerFromUserContext(int i, Context context) {
        Context createPackageContextAsUser = createPackageContextAsUser(i, context);
        if (createPackageContextAsUser == null) {
            return null;
        }
        return (DevicePolicyManager) createPackageContextAsUser.getSystemService(DevicePolicyManager.class);
    }

    private static Pair<UserHandle, ComponentName> getDeviceOwner(Context context) {
        DevicePolicyManager devicePolicyManager = (DevicePolicyManager) context.getSystemService(DevicePolicyManager.class);
        if (devicePolicyManager == null) {
            return null;
        }
        try {
            UserHandle deviceOwnerUser = devicePolicyManager.getDeviceOwnerUser();
            ComponentName deviceOwnerComponentOnAnyUser = devicePolicyManager.getDeviceOwnerComponentOnAnyUser();
            if (deviceOwnerUser == null || deviceOwnerComponentOnAnyUser == null || deviceOwnerComponentOnAnyUser.getPackageName() == null) {
                return null;
            }
            return new Pair<>(deviceOwnerUser, deviceOwnerComponentOnAnyUser);
        } catch (Exception e) {
            throw new RuntimeException("getDeviceOwner error - " + e.toString());
        }
    }

    public static boolean isDeviceOwner(int i, String str, Context context) {
        Pair<UserHandle, ComponentName> deviceOwner;
        return str != null && (deviceOwner = getDeviceOwner(context)) != null && ((UserHandle) deviceOwner.first).equals(UserHandle.getUserHandleForUid(i)) && ((ComponentName) deviceOwner.second).getPackageName().equals(str);
    }

    public static boolean isProfileOwner(int i, String str, Context context) {
        DevicePolicyManager retrieveDevicePolicyManagerFromUserContext;
        if (str == null || (retrieveDevicePolicyManagerFromUserContext = retrieveDevicePolicyManagerFromUserContext(i, context)) == null) {
            return false;
        }
        return retrieveDevicePolicyManagerFromUserContext.isProfileOwnerApp(str);
    }

    public static boolean isDeviceOrProfileOwner(int i, String str, Context context) {
        return isDeviceOwner(i, str, context) || isProfileOwner(i, str, context);
    }

    public static String getStandardString(Context context, int i) {
        if (i == 1) {
            return context.getString(R$string.wifitrackerlib_wifi_standard_legacy);
        }
        switch (i) {
            case 4:
                return context.getString(R$string.wifitrackerlib_wifi_standard_11n);
            case 5:
                return context.getString(R$string.wifitrackerlib_wifi_standard_11ac);
            case 6:
                return context.getString(R$string.wifitrackerlib_wifi_standard_11ax);
            case 7:
                return context.getString(R$string.wifitrackerlib_wifi_standard_11ad);
            case 8:
                return context.getString(R$string.wifitrackerlib_wifi_standard_11be);
            default:
                return context.getString(R$string.wifitrackerlib_wifi_standard_unknown);
        }
    }
}
