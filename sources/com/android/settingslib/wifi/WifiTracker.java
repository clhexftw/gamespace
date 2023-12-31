package com.android.settingslib.wifi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkKey;
import android.net.NetworkRequest;
import android.net.NetworkScoreManager;
import android.net.ScoredNetwork;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiNetworkScoreCache;
import android.net.wifi.hotspot2.OsuProvider;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.SystemClock;
import android.provider.Settings;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.Log;
import android.util.Pair;
import android.widget.Toast;
import com.android.settingslib.R$string;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnDestroy;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.core.lifecycle.events.OnStop;
import com.android.settingslib.utils.ThreadUtils;
import com.android.settingslib.wifi.WifiTracker;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Predicate;
import java.util.stream.Collectors;
@Deprecated
/* loaded from: classes2.dex */
public class WifiTracker implements LifecycleObserver, OnStart, OnStop, OnDestroy {
    static final long MAX_SCAN_RESULT_AGE_MILLIS = 15000;
    public static boolean sVerboseLogging;
    private final ConnectivityManager mConnectivityManager;
    private final Context mContext;
    private final IntentFilter mFilter;
    private WifiInfo mLastInfo;
    private NetworkInfo mLastNetworkInfo;
    private final WifiListenerExecutor mListener;
    private long mMaxSpeedLabelScoreCacheAge;
    private WifiTrackerNetworkCallback mNetworkCallback;
    private final NetworkRequest mNetworkRequest;
    private final NetworkScoreManager mNetworkScoreManager;
    private boolean mNetworkScoringUiEnabled;
    private boolean mRegistered;
    Scanner mScanner;
    private WifiNetworkScoreCache mScoreCache;
    private final WifiManager mWifiManager;
    Handler mWorkHandler;
    private HandlerThread mWorkThread;
    private final AtomicBoolean mConnected = new AtomicBoolean(false);
    private final Object mLock = new Object();
    private final List<AccessPoint> mInternalAccessPoints = new ArrayList();
    private final Set<NetworkKey> mRequestedScores = new ArraySet();
    private boolean mStaleScanResults = true;
    private boolean mLastScanSucceeded = true;
    private final HashMap<String, ScanResult> mScanResultCache = new HashMap<>();
    final BroadcastReceiver mReceiver = new BroadcastReceiver() { // from class: com.android.settingslib.wifi.WifiTracker.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("android.net.wifi.WIFI_STATE_CHANGED".equals(action)) {
                WifiTracker.this.updateWifiState(intent.getIntExtra("wifi_state", 4));
            } else if ("android.net.wifi.SCAN_RESULTS".equals(action)) {
                WifiTracker.this.mStaleScanResults = false;
                WifiTracker.this.mLastScanSucceeded = intent.getBooleanExtra("resultsUpdated", true);
                WifiTracker.this.fetchScansAndConfigsAndUpdateAccessPoints();
            } else if ("android.net.wifi.CONFIGURED_NETWORKS_CHANGE".equals(action) || "android.net.wifi.LINK_CONFIGURATION_CHANGED".equals(action)) {
                WifiTracker.this.fetchScansAndConfigsAndUpdateAccessPoints();
            } else if ("android.net.wifi.STATE_CHANGE".equals(action)) {
                WifiTracker.this.updateNetworkInfo((NetworkInfo) intent.getParcelableExtra("networkInfo"));
                WifiTracker.this.fetchScansAndConfigsAndUpdateAccessPoints();
            } else if ("android.net.wifi.RSSI_CHANGED".equals(action)) {
                WifiTracker.this.updateNetworkInfo(null);
            }
        }
    };

    /* loaded from: classes2.dex */
    public interface WifiListener {
        void onAccessPointsChanged();

        void onConnectedChanged();

        void onWifiStateChanged(int i);
    }

    /* renamed from: -$$Nest$smisVerboseLoggingEnabled  reason: not valid java name */
    static /* bridge */ /* synthetic */ boolean m2080$$Nest$smisVerboseLoggingEnabled() {
        return isVerboseLoggingEnabled();
    }

    private static final boolean DBG() {
        return Log.isLoggable("WifiTracker", 3);
    }

    private static boolean isVerboseLoggingEnabled() {
        return sVerboseLogging || Log.isLoggable("WifiTracker", 2);
    }

    WifiTracker(Context context, WifiListener wifiListener, WifiManager wifiManager, ConnectivityManager connectivityManager, NetworkScoreManager networkScoreManager, IntentFilter intentFilter) {
        boolean z = false;
        this.mContext = context;
        this.mWifiManager = wifiManager;
        this.mListener = new WifiListenerExecutor(wifiListener);
        this.mConnectivityManager = connectivityManager;
        if (wifiManager != null && wifiManager.isVerboseLoggingEnabled()) {
            z = true;
        }
        sVerboseLogging = z;
        this.mFilter = intentFilter;
        this.mNetworkRequest = new NetworkRequest.Builder().clearCapabilities().addCapability(15).addTransportType(1).build();
        this.mNetworkScoreManager = networkScoreManager;
        HandlerThread handlerThread = new HandlerThread("WifiTracker{" + Integer.toHexString(System.identityHashCode(this)) + "}", 10);
        handlerThread.start();
        setWorkThread(handlerThread);
    }

    void setWorkThread(HandlerThread handlerThread) {
        this.mWorkThread = handlerThread;
        this.mWorkHandler = new Handler(handlerThread.getLooper());
        this.mScoreCache = new WifiNetworkScoreCache(this.mContext, new WifiNetworkScoreCache.CacheListener(this.mWorkHandler) { // from class: com.android.settingslib.wifi.WifiTracker.1
            public void networkCacheUpdated(List<ScoredNetwork> list) {
                if (WifiTracker.this.mRegistered) {
                    if (Log.isLoggable("WifiTracker", 2)) {
                        Log.v("WifiTracker", "Score cache was updated with networks: " + list);
                    }
                    WifiTracker.this.updateNetworkScores();
                }
            }
        });
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnDestroy
    public void onDestroy() {
        this.mWorkThread.quit();
    }

    private void pauseScanning() {
        synchronized (this.mLock) {
            Scanner scanner = this.mScanner;
            if (scanner != null) {
                scanner.pause();
                this.mScanner = null;
            }
        }
        this.mStaleScanResults = true;
    }

    public void resumeScanning() {
        synchronized (this.mLock) {
            if (this.mScanner == null) {
                this.mScanner = new Scanner();
            }
            if (isWifiEnabled()) {
                this.mScanner.resume();
            }
        }
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStart
    public void onStart() {
        forceUpdate();
        registerScoreCache();
        this.mNetworkScoringUiEnabled = Settings.Global.getInt(this.mContext.getContentResolver(), "network_scoring_ui_enabled", 0) == 1;
        this.mMaxSpeedLabelScoreCacheAge = Settings.Global.getLong(this.mContext.getContentResolver(), "speed_label_cache_eviction_age_millis", 1200000L);
        resumeScanning();
        if (this.mRegistered) {
            return;
        }
        this.mContext.registerReceiver(this.mReceiver, this.mFilter, null, this.mWorkHandler, 2);
        WifiTrackerNetworkCallback wifiTrackerNetworkCallback = new WifiTrackerNetworkCallback();
        this.mNetworkCallback = wifiTrackerNetworkCallback;
        this.mConnectivityManager.registerNetworkCallback(this.mNetworkRequest, wifiTrackerNetworkCallback, this.mWorkHandler);
        this.mRegistered = true;
    }

    void forceUpdate() {
        this.mLastInfo = this.mWifiManager.getConnectionInfo();
        this.mLastNetworkInfo = this.mConnectivityManager.getNetworkInfo(this.mWifiManager.getCurrentNetwork());
        fetchScansAndConfigsAndUpdateAccessPoints();
    }

    private void registerScoreCache() {
        this.mNetworkScoreManager.registerNetworkScoreCache(1, this.mScoreCache, 2);
    }

    private void requestScoresForNetworkKeys(Collection<NetworkKey> collection) {
        if (collection.isEmpty()) {
            return;
        }
        if (DBG()) {
            Log.d("WifiTracker", "Requesting scores for Network Keys: " + collection);
        }
        this.mNetworkScoreManager.requestScores((NetworkKey[]) collection.toArray(new NetworkKey[collection.size()]));
        synchronized (this.mLock) {
            this.mRequestedScores.addAll(collection);
        }
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStop
    public void onStop() {
        if (this.mRegistered) {
            this.mContext.unregisterReceiver(this.mReceiver);
            this.mConnectivityManager.unregisterNetworkCallback(this.mNetworkCallback);
            this.mRegistered = false;
        }
        unregisterScoreCache();
        pauseScanning();
        this.mWorkHandler.removeCallbacksAndMessages(null);
    }

    private void unregisterScoreCache() {
        this.mNetworkScoreManager.unregisterNetworkScoreCache(1, this.mScoreCache);
        synchronized (this.mLock) {
            this.mRequestedScores.clear();
        }
    }

    public boolean isWifiEnabled() {
        WifiManager wifiManager = this.mWifiManager;
        return wifiManager != null && wifiManager.isWifiEnabled();
    }

    private ArrayMap<String, List<ScanResult>> updateScanResultCache(List<ScanResult> list) {
        List<ScanResult> list2;
        for (ScanResult scanResult : list) {
            String str = scanResult.SSID;
            if (str != null && !str.isEmpty()) {
                this.mScanResultCache.put(scanResult.BSSID, scanResult);
            }
        }
        evictOldScans();
        ArrayMap<String, List<ScanResult>> arrayMap = new ArrayMap<>();
        for (ScanResult scanResult2 : this.mScanResultCache.values()) {
            String str2 = scanResult2.SSID;
            if (str2 != null && str2.length() != 0 && !scanResult2.capabilities.contains("[IBSS]")) {
                String key = AccessPoint.getKey(this.mContext, scanResult2);
                if (arrayMap.containsKey(key)) {
                    list2 = arrayMap.get(key);
                } else {
                    ArrayList arrayList = new ArrayList();
                    arrayMap.put(key, arrayList);
                    list2 = arrayList;
                }
                list2.add(scanResult2);
            }
        }
        return arrayMap;
    }

    private void evictOldScans() {
        long j = this.mLastScanSucceeded ? MAX_SCAN_RESULT_AGE_MILLIS : 30000L;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        Iterator<ScanResult> it = this.mScanResultCache.values().iterator();
        while (it.hasNext()) {
            if (elapsedRealtime - (it.next().timestamp / 1000) > j) {
                it.remove();
            }
        }
    }

    private WifiConfiguration getWifiConfigurationForNetworkId(int i, List<WifiConfiguration> list) {
        if (list != null) {
            for (WifiConfiguration wifiConfiguration : list) {
                if (this.mLastInfo != null && i == wifiConfiguration.networkId) {
                    return wifiConfiguration;
                }
            }
            return null;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void fetchScansAndConfigsAndUpdateAccessPoints() {
        List<ScanResult> filterScanResultsByCapabilities = filterScanResultsByCapabilities(this.mWifiManager.getScanResults());
        if (isVerboseLoggingEnabled()) {
            Log.i("WifiTracker", "Fetched scan results: " + filterScanResultsByCapabilities);
        }
        updateAccessPoints(filterScanResultsByCapabilities, this.mWifiManager.getConfiguredNetworks());
    }

    private void updateAccessPoints(List<ScanResult> list, List<WifiConfiguration> list2) {
        boolean z;
        WifiInfo wifiInfo = this.mLastInfo;
        WifiConfiguration wifiConfigurationForNetworkId = wifiInfo != null ? getWifiConfigurationForNetworkId(wifiInfo.getNetworkId(), list2) : null;
        synchronized (this.mLock) {
            ArrayMap<String, List<ScanResult>> updateScanResultCache = updateScanResultCache(list);
            List<AccessPoint> arrayList = new ArrayList<>(this.mInternalAccessPoints);
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            for (Map.Entry<String, List<ScanResult>> entry : updateScanResultCache.entrySet()) {
                for (ScanResult scanResult : entry.getValue()) {
                    NetworkKey createFromScanResult = NetworkKey.createFromScanResult(scanResult);
                    if (createFromScanResult != null && !this.mRequestedScores.contains(createFromScanResult)) {
                        arrayList3.add(createFromScanResult);
                    }
                }
                final AccessPoint cachedOrCreate = getCachedOrCreate(entry.getValue(), arrayList);
                List list3 = (List) list2.stream().filter(new Predicate() { // from class: com.android.settingslib.wifi.WifiTracker$$ExternalSyntheticLambda0
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        boolean matches;
                        matches = AccessPoint.this.matches((WifiConfiguration) obj);
                        return matches;
                    }
                }).collect(Collectors.toList());
                int size = list3.size();
                if (size == 0) {
                    cachedOrCreate.update(null);
                } else if (size == 1) {
                    cachedOrCreate.update((WifiConfiguration) list3.get(0));
                } else {
                    Optional findFirst = list3.stream().filter(new Predicate() { // from class: com.android.settingslib.wifi.WifiTracker$$ExternalSyntheticLambda1
                        @Override // java.util.function.Predicate
                        public final boolean test(Object obj) {
                            boolean isSaeOrOwe;
                            isSaeOrOwe = WifiTracker.isSaeOrOwe((WifiConfiguration) obj);
                            return isSaeOrOwe;
                        }
                    }).findFirst();
                    if (findFirst.isPresent()) {
                        cachedOrCreate.update((WifiConfiguration) findFirst.get());
                    } else {
                        cachedOrCreate.update((WifiConfiguration) list3.get(0));
                    }
                }
                arrayList2.add(cachedOrCreate);
            }
            ArrayList arrayList4 = new ArrayList(this.mScanResultCache.values());
            arrayList2.addAll(updatePasspointAccessPoints(this.mWifiManager.getAllMatchingWifiConfigs(arrayList4), arrayList));
            arrayList2.addAll(updateOsuAccessPoints(this.mWifiManager.getMatchingOsuProviders(arrayList4), arrayList));
            if (this.mLastInfo != null && this.mLastNetworkInfo != null) {
                Iterator it = arrayList2.iterator();
                while (it.hasNext()) {
                    ((AccessPoint) it.next()).update(wifiConfigurationForNetworkId, this.mLastInfo, this.mLastNetworkInfo);
                }
            }
            if (arrayList2.isEmpty() && wifiConfigurationForNetworkId != null) {
                AccessPoint accessPoint = new AccessPoint(this.mContext, wifiConfigurationForNetworkId);
                accessPoint.update(wifiConfigurationForNetworkId, this.mLastInfo, this.mLastNetworkInfo);
                arrayList2.add(accessPoint);
                arrayList3.add(NetworkKey.createFromWifiInfo(this.mLastInfo));
            }
            requestScoresForNetworkKeys(arrayList3);
            Iterator it2 = arrayList2.iterator();
            while (it2.hasNext()) {
                ((AccessPoint) it2.next()).update(this.mScoreCache, this.mNetworkScoringUiEnabled, this.mMaxSpeedLabelScoreCacheAge);
            }
            Collections.sort(arrayList2);
            if (DBG()) {
                Log.d("WifiTracker", "------ Dumping AccessPoints that were not seen on this scan ------");
                for (AccessPoint accessPoint2 : this.mInternalAccessPoints) {
                    String title = accessPoint2.getTitle();
                    Iterator it3 = arrayList2.iterator();
                    while (true) {
                        if (!it3.hasNext()) {
                            z = false;
                            break;
                        }
                        AccessPoint accessPoint3 = (AccessPoint) it3.next();
                        if (accessPoint3.getTitle() != null && accessPoint3.getTitle().equals(title)) {
                            z = true;
                            break;
                        }
                    }
                    if (!z) {
                        Log.d("WifiTracker", "Did not find " + title + " in this scan");
                    }
                }
                Log.d("WifiTracker", "---- Done dumping AccessPoints that were not seen on this scan ----");
            }
            this.mInternalAccessPoints.clear();
            this.mInternalAccessPoints.addAll(arrayList2);
        }
        conditionallyNotifyListeners();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isSaeOrOwe(WifiConfiguration wifiConfiguration) {
        int security = AccessPoint.getSecurity(wifiConfiguration);
        return security == 5 || security == 4;
    }

    List<AccessPoint> updatePasspointAccessPoints(List<Pair<WifiConfiguration, Map<Integer, List<ScanResult>>>> list, List<AccessPoint> list2) {
        ArrayList arrayList = new ArrayList();
        ArraySet arraySet = new ArraySet();
        for (Pair<WifiConfiguration, Map<Integer, List<ScanResult>>> pair : list) {
            WifiConfiguration wifiConfiguration = (WifiConfiguration) pair.first;
            if (arraySet.add(wifiConfiguration.FQDN)) {
                arrayList.add(getCachedOrCreatePasspoint(wifiConfiguration, (List) ((Map) pair.second).get(0), (List) ((Map) pair.second).get(1), list2));
            }
        }
        return arrayList;
    }

    List<AccessPoint> updateOsuAccessPoints(Map<OsuProvider, List<ScanResult>> map, List<AccessPoint> list) {
        ArrayList arrayList = new ArrayList();
        Set keySet = this.mWifiManager.getMatchingPasspointConfigsForOsuProviders(map.keySet()).keySet();
        for (OsuProvider osuProvider : map.keySet()) {
            if (!keySet.contains(osuProvider)) {
                arrayList.add(getCachedOrCreateOsu(osuProvider, map.get(osuProvider), list));
            }
        }
        return arrayList;
    }

    private AccessPoint getCachedOrCreate(List<ScanResult> list, List<AccessPoint> list2) {
        AccessPoint cachedByKey = getCachedByKey(list2, AccessPoint.getKey(this.mContext, list.get(0)));
        if (cachedByKey == null) {
            return new AccessPoint(this.mContext, list);
        }
        cachedByKey.setScanResults(list);
        return cachedByKey;
    }

    private AccessPoint getCachedOrCreatePasspoint(WifiConfiguration wifiConfiguration, List<ScanResult> list, List<ScanResult> list2, List<AccessPoint> list3) {
        AccessPoint cachedByKey = getCachedByKey(list3, AccessPoint.getKey(wifiConfiguration));
        if (cachedByKey == null) {
            return new AccessPoint(this.mContext, wifiConfiguration, list, list2);
        }
        cachedByKey.update(wifiConfiguration);
        cachedByKey.setScanResultsPasspoint(list, list2);
        return cachedByKey;
    }

    private AccessPoint getCachedOrCreateOsu(OsuProvider osuProvider, List<ScanResult> list, List<AccessPoint> list2) {
        AccessPoint cachedByKey = getCachedByKey(list2, AccessPoint.getKey(osuProvider));
        if (cachedByKey == null) {
            return new AccessPoint(this.mContext, osuProvider, list);
        }
        cachedByKey.setScanResults(list);
        return cachedByKey;
    }

    private AccessPoint getCachedByKey(List<AccessPoint> list, String str) {
        ListIterator<AccessPoint> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            AccessPoint next = listIterator.next();
            if (next.getKey().equals(str)) {
                listIterator.remove();
                return next;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateNetworkInfo(NetworkInfo networkInfo) {
        if (!isWifiEnabled()) {
            clearAccessPointsAndConditionallyUpdate();
            return;
        }
        if (networkInfo != null) {
            this.mLastNetworkInfo = networkInfo;
            if (DBG()) {
                Log.d("WifiTracker", "mLastNetworkInfo set: " + this.mLastNetworkInfo);
            }
            if (networkInfo.isConnected() != this.mConnected.getAndSet(networkInfo.isConnected())) {
                this.mListener.onConnectedChanged();
            }
        }
        this.mLastInfo = this.mWifiManager.getConnectionInfo();
        if (DBG()) {
            Log.d("WifiTracker", "mLastInfo set as: " + this.mLastInfo);
        }
        WifiInfo wifiInfo = this.mLastInfo;
        WifiConfiguration wifiConfigurationForNetworkId = wifiInfo != null ? getWifiConfigurationForNetworkId(wifiInfo.getNetworkId(), this.mWifiManager.getConfiguredNetworks()) : null;
        synchronized (this.mLock) {
            boolean z = false;
            boolean z2 = false;
            for (int size = this.mInternalAccessPoints.size() - 1; size >= 0; size--) {
                AccessPoint accessPoint = this.mInternalAccessPoints.get(size);
                boolean isActive = accessPoint.isActive();
                if (accessPoint.update(wifiConfigurationForNetworkId, this.mLastInfo, this.mLastNetworkInfo)) {
                    if (isActive != accessPoint.isActive()) {
                        z = true;
                        z2 = true;
                    } else {
                        z2 = true;
                    }
                }
                if (accessPoint.update(this.mScoreCache, this.mNetworkScoringUiEnabled, this.mMaxSpeedLabelScoreCacheAge)) {
                    z = true;
                    z2 = true;
                }
            }
            if (z) {
                Collections.sort(this.mInternalAccessPoints);
            }
            if (z2) {
                conditionallyNotifyListeners();
            }
        }
    }

    private void clearAccessPointsAndConditionallyUpdate() {
        synchronized (this.mLock) {
            if (!this.mInternalAccessPoints.isEmpty()) {
                this.mInternalAccessPoints.clear();
                conditionallyNotifyListeners();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateNetworkScores() {
        synchronized (this.mLock) {
            boolean z = false;
            for (int i = 0; i < this.mInternalAccessPoints.size(); i++) {
                if (this.mInternalAccessPoints.get(i).update(this.mScoreCache, this.mNetworkScoringUiEnabled, this.mMaxSpeedLabelScoreCacheAge)) {
                    z = true;
                }
            }
            if (z) {
                Collections.sort(this.mInternalAccessPoints);
                conditionallyNotifyListeners();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateWifiState(int i) {
        if (isVerboseLoggingEnabled()) {
            Log.d("WifiTracker", "updateWifiState: " + i);
        }
        if (i == 3) {
            synchronized (this.mLock) {
                Scanner scanner = this.mScanner;
                if (scanner != null) {
                    scanner.resume();
                }
            }
        } else {
            clearAccessPointsAndConditionallyUpdate();
            this.mLastInfo = null;
            this.mLastNetworkInfo = null;
            synchronized (this.mLock) {
                Scanner scanner2 = this.mScanner;
                if (scanner2 != null) {
                    scanner2.pause();
                }
            }
            this.mStaleScanResults = true;
        }
        this.mListener.onWifiStateChanged(i);
    }

    /* loaded from: classes2.dex */
    private final class WifiTrackerNetworkCallback extends ConnectivityManager.NetworkCallback {
        private WifiTrackerNetworkCallback() {
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
            if (network.equals(WifiTracker.this.mWifiManager.getCurrentNetwork())) {
                WifiTracker.this.updateNetworkInfo(null);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public class Scanner extends Handler {
        private int mRetry = 0;

        Scanner() {
        }

        void resume() {
            if (WifiTracker.m2080$$Nest$smisVerboseLoggingEnabled()) {
                Log.d("WifiTracker", "Scanner resume");
            }
            if (hasMessages(0)) {
                return;
            }
            sendEmptyMessage(0);
        }

        void pause() {
            if (WifiTracker.m2080$$Nest$smisVerboseLoggingEnabled()) {
                Log.d("WifiTracker", "Scanner pause");
            }
            this.mRetry = 0;
            removeMessages(0);
        }

        boolean isScanning() {
            return hasMessages(0);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what != 0) {
                return;
            }
            if (WifiTracker.this.mWifiManager.startScan()) {
                this.mRetry = 0;
            } else {
                int i = this.mRetry + 1;
                this.mRetry = i;
                if (i >= 3) {
                    this.mRetry = 0;
                    if (WifiTracker.this.mContext != null) {
                        Toast.makeText(WifiTracker.this.mContext, R$string.wifi_fail_to_scan, 1).show();
                        return;
                    }
                    return;
                }
            }
            sendEmptyMessageDelayed(0, 10000L);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public class WifiListenerExecutor implements WifiListener {
        private final WifiListener mDelegatee;

        public WifiListenerExecutor(WifiListener wifiListener) {
            this.mDelegatee = wifiListener;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onWifiStateChanged$0(int i) {
            this.mDelegatee.onWifiStateChanged(i);
        }

        @Override // com.android.settingslib.wifi.WifiTracker.WifiListener
        public void onWifiStateChanged(final int i) {
            runAndLog(new Runnable() { // from class: com.android.settingslib.wifi.WifiTracker$WifiListenerExecutor$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    WifiTracker.WifiListenerExecutor.this.lambda$onWifiStateChanged$0(i);
                }
            }, String.format("Invoking onWifiStateChanged callback with state %d", Integer.valueOf(i)));
        }

        @Override // com.android.settingslib.wifi.WifiTracker.WifiListener
        public void onConnectedChanged() {
            final WifiListener wifiListener = this.mDelegatee;
            Objects.requireNonNull(wifiListener);
            runAndLog(new Runnable() { // from class: com.android.settingslib.wifi.WifiTracker$WifiListenerExecutor$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    WifiTracker.WifiListener.this.onConnectedChanged();
                }
            }, "Invoking onConnectedChanged callback");
        }

        @Override // com.android.settingslib.wifi.WifiTracker.WifiListener
        public void onAccessPointsChanged() {
            final WifiListener wifiListener = this.mDelegatee;
            Objects.requireNonNull(wifiListener);
            runAndLog(new Runnable() { // from class: com.android.settingslib.wifi.WifiTracker$WifiListenerExecutor$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    WifiTracker.WifiListener.this.onAccessPointsChanged();
                }
            }, "Invoking onAccessPointsChanged callback");
        }

        private void runAndLog(final Runnable runnable, final String str) {
            ThreadUtils.postOnMainThread(new Runnable() { // from class: com.android.settingslib.wifi.WifiTracker$WifiListenerExecutor$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    WifiTracker.WifiListenerExecutor.this.lambda$runAndLog$1(str, runnable);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$runAndLog$1(String str, Runnable runnable) {
            if (WifiTracker.this.mRegistered) {
                if (WifiTracker.m2080$$Nest$smisVerboseLoggingEnabled()) {
                    Log.i("WifiTracker", str);
                }
                runnable.run();
            }
        }
    }

    private void conditionallyNotifyListeners() {
        if (this.mStaleScanResults) {
            return;
        }
        this.mListener.onAccessPointsChanged();
    }

    private List<ScanResult> filterScanResultsByCapabilities(List<ScanResult> list) {
        if (list == null) {
            return null;
        }
        boolean isEnhancedOpenSupported = this.mWifiManager.isEnhancedOpenSupported();
        boolean isWpa3SaeSupported = this.mWifiManager.isWpa3SaeSupported();
        boolean isWpa3SuiteBSupported = this.mWifiManager.isWpa3SuiteBSupported();
        ArrayList arrayList = new ArrayList();
        for (ScanResult scanResult : list) {
            if (scanResult.capabilities.contains("PSK")) {
                arrayList.add(scanResult);
            } else if ((scanResult.capabilities.contains("SUITE_B_192") && !isWpa3SuiteBSupported) || ((scanResult.capabilities.contains("SAE") && !isWpa3SaeSupported) || (scanResult.capabilities.contains("OWE") && !isEnhancedOpenSupported))) {
                if (isVerboseLoggingEnabled()) {
                    Log.v("WifiTracker", "filterScanResultsByCapabilities: Filtering SSID " + scanResult.SSID + " with capabilities: " + scanResult.capabilities);
                }
            } else {
                arrayList.add(scanResult);
            }
        }
        return arrayList;
    }
}
