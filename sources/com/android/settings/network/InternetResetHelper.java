package com.android.settings.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.util.Log;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import com.android.settingslib.connectivity.ConnectivitySubsystemsRecoveryManager;
import com.android.settingslib.utils.HandlerInjector;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class InternetResetHelper implements LifecycleObserver {
    protected final Context mContext;
    protected HandlerInjector mHandlerInjector;
    protected NetworkMobileProviderController mMobileNetworkController;
    protected RecoveryWorker mRecoveryWorker;
    protected Preference mResettingPreference;
    protected final WifiManager mWifiManager;
    protected final IntentFilter mWifiStateFilter;
    protected Preference mWifiTogglePreferences;
    protected List<PreferenceCategory> mWifiNetworkPreferences = new ArrayList();
    protected final BroadcastReceiver mWifiStateReceiver = new BroadcastReceiver() { // from class: com.android.settings.network.InternetResetHelper.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            InternetResetHelper.this.updateWifiStateChange();
        }
    };
    protected boolean mIsWifiReady = true;
    protected final Runnable mTimeoutRunnable = new Runnable() { // from class: com.android.settings.network.InternetResetHelper$$ExternalSyntheticLambda0
        @Override // java.lang.Runnable
        public final void run() {
            InternetResetHelper.this.lambda$new$0();
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0() {
        Log.w("InternetResetHelper", "Resume preferences due to connectivity subsystems recovery timed out.");
        this.mRecoveryWorker.clearRecovering();
        this.mIsWifiReady = true;
        resumePreferences();
    }

    public InternetResetHelper(Context context, Lifecycle lifecycle, NetworkMobileProviderController networkMobileProviderController, Preference preference, PreferenceCategory preferenceCategory, PreferenceCategory preferenceCategory2, PreferenceCategory preferenceCategory3, Preference preference2) {
        this.mContext = context;
        this.mMobileNetworkController = networkMobileProviderController;
        this.mWifiTogglePreferences = preference;
        this.mWifiNetworkPreferences.add(preferenceCategory);
        this.mWifiNetworkPreferences.add(preferenceCategory2);
        this.mWifiNetworkPreferences.add(preferenceCategory3);
        this.mResettingPreference = preference2;
        this.mHandlerInjector = new HandlerInjector(context.getMainThreadHandler());
        this.mWifiManager = (WifiManager) context.getSystemService(WifiManager.class);
        this.mWifiStateFilter = new IntentFilter("android.net.wifi.STATE_CHANGE");
        this.mRecoveryWorker = RecoveryWorker.getInstance(context, this);
        if (lifecycle != null) {
            lifecycle.addObserver(this);
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        this.mContext.registerReceiver(this.mWifiStateReceiver, this.mWifiStateFilter, 2);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        this.mContext.unregisterReceiver(this.mWifiStateReceiver);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        this.mHandlerInjector.removeCallbacks(this.mTimeoutRunnable);
    }

    protected void updateWifiStateChange() {
        if (this.mIsWifiReady || !this.mWifiManager.isWifiEnabled()) {
            return;
        }
        Log.d("InternetResetHelper", "The Wi-Fi subsystem is done for recovery.");
        this.mIsWifiReady = true;
        resumePreferences();
    }

    protected void suspendPreferences() {
        Log.d("InternetResetHelper", "Suspend the subsystem preferences");
        NetworkMobileProviderController networkMobileProviderController = this.mMobileNetworkController;
        if (networkMobileProviderController != null) {
            networkMobileProviderController.hidePreference(true, true);
        }
        Preference preference = this.mWifiTogglePreferences;
        if (preference != null) {
            preference.setVisible(false);
        }
        for (PreferenceCategory preferenceCategory : this.mWifiNetworkPreferences) {
            preferenceCategory.removeAll();
            preferenceCategory.setVisible(false);
        }
        Preference preference2 = this.mResettingPreference;
        if (preference2 != null) {
            preference2.setVisible(true);
        }
    }

    protected void resumePreferences() {
        boolean z = !this.mRecoveryWorker.isRecovering();
        if (z && this.mMobileNetworkController != null) {
            Log.d("InternetResetHelper", "Resume the Mobile Network controller");
            this.mMobileNetworkController.hidePreference(false, true);
        }
        if (this.mIsWifiReady && this.mWifiTogglePreferences != null) {
            Log.d("InternetResetHelper", "Resume the Wi-Fi preferences");
            this.mWifiTogglePreferences.setVisible(true);
            for (PreferenceCategory preferenceCategory : this.mWifiNetworkPreferences) {
                preferenceCategory.setVisible(true);
            }
        }
        if (z && this.mIsWifiReady) {
            this.mHandlerInjector.removeCallbacks(this.mTimeoutRunnable);
            if (this.mResettingPreference != null) {
                Log.d("InternetResetHelper", "Resume the Resetting preference");
                this.mResettingPreference.setVisible(false);
            }
        }
    }

    protected void showResettingAndSendTimeoutChecks() {
        suspendPreferences();
        this.mHandlerInjector.postDelayed(this.mTimeoutRunnable, 15000L);
    }

    public void restart() {
        if (!this.mRecoveryWorker.isRecoveryAvailable()) {
            Log.e("InternetResetHelper", "The connectivity subsystem is not available to restart.");
            return;
        }
        showResettingAndSendTimeoutChecks();
        this.mIsWifiReady = !this.mWifiManager.isWifiEnabled();
        this.mRecoveryWorker.triggerRestart();
    }

    public void checkRecovering() {
        if (this.mRecoveryWorker.isRecovering()) {
            this.mIsWifiReady = false;
            showResettingAndSendTimeoutChecks();
        }
    }

    /* loaded from: classes.dex */
    public static class RecoveryWorker implements ConnectivitySubsystemsRecoveryManager.RecoveryStatusCallback {
        private static WeakReference<InternetResetHelper> sCallback;
        private static RecoveryWorker sInstance;
        private static boolean sIsRecovering;
        private static ConnectivitySubsystemsRecoveryManager sRecoveryManager;

        public static RecoveryWorker getInstance(Context context, InternetResetHelper internetResetHelper) {
            sCallback = new WeakReference<>(internetResetHelper);
            RecoveryWorker recoveryWorker = sInstance;
            if (recoveryWorker != null) {
                return recoveryWorker;
            }
            sInstance = new RecoveryWorker();
            Context applicationContext = context.getApplicationContext();
            sRecoveryManager = new ConnectivitySubsystemsRecoveryManager(applicationContext, applicationContext.getMainThreadHandler());
            return sInstance;
        }

        public boolean isRecovering() {
            return sIsRecovering;
        }

        public void clearRecovering() {
            sIsRecovering = false;
        }

        public boolean isRecoveryAvailable() {
            return sRecoveryManager.isRecoveryAvailable();
        }

        public boolean triggerRestart() {
            if (!isRecoveryAvailable()) {
                Log.e("RecoveryWorker", "The connectivity subsystem is not available to restart.");
                return false;
            }
            sIsRecovering = true;
            sRecoveryManager.triggerSubsystemRestart(null, sInstance);
            Log.d("RecoveryWorker", "The connectivity subsystem is restarting for recovery.");
            return true;
        }

        @Override // com.android.settingslib.connectivity.ConnectivitySubsystemsRecoveryManager.RecoveryStatusCallback
        public void onSubsystemRestartOperationBegin() {
            Log.d("RecoveryWorker", "The connectivity subsystem is starting for recovery.");
            sIsRecovering = true;
        }

        @Override // com.android.settingslib.connectivity.ConnectivitySubsystemsRecoveryManager.RecoveryStatusCallback
        public void onSubsystemRestartOperationEnd() {
            Log.d("RecoveryWorker", "The connectivity subsystem is done for recovery.");
            sIsRecovering = false;
            InternetResetHelper internetResetHelper = sCallback.get();
            if (internetResetHelper == null) {
                return;
            }
            internetResetHelper.resumePreferences();
        }
    }
}
