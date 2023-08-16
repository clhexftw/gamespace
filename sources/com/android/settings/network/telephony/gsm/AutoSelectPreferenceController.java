package com.android.settings.network.telephony.gsm;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.HandlerExecutor;
import android.os.Looper;
import android.os.PersistableBundle;
import android.os.SystemClock;
import android.telephony.ServiceState;
import android.telephony.TelephonyCallback;
import android.telephony.TelephonyManager;
import android.util.Log;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.R;
import com.android.settings.network.AllowedNetworkTypesListener;
import com.android.settings.network.CarrierConfigCache;
import com.android.settings.network.telephony.MobileNetworkUtils;
import com.android.settings.network.telephony.TelephonyTogglePreferenceController;
import com.android.settingslib.utils.ThreadUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import org.nameless.custom.preference.SwitchPreference;
/* loaded from: classes.dex */
public class AutoSelectPreferenceController extends TelephonyTogglePreferenceController implements LifecycleObserver {
    private static final String INTERNAL_LOG_TAG_AFTERSET = "AfterSet";
    private static final String INTERNAL_LOG_TAG_INIT = "Init";
    private static final String LOG_TAG = "AutoSelectPreferenceController";
    private static final long MINIMUM_DIALOG_TIME_MILLIS = TimeUnit.SECONDS.toMillis(1);
    private AllowedNetworkTypesListener mAllowedNetworkTypesListener;
    private int mCacheOfModeStatus;
    private List<OnNetworkSelectModeListener> mListeners;
    private boolean mOnlyAutoSelectInHome;
    private PreferenceScreen mPreferenceScreen;
    ProgressDialog mProgressDialog;
    private AtomicLong mRecursiveUpdate;
    SwitchPreference mSwitchPreference;
    TelephonyCallbackListener mTelephonyCallbackListener;
    private TelephonyManager mTelephonyManager;
    private final Handler mUiHandler;
    private AtomicBoolean mUpdatingConfig;

    /* loaded from: classes.dex */
    public interface OnNetworkSelectModeListener {
        void onNetworkSelectModeUpdated(int i);
    }

    @Override // com.android.settings.network.telephony.TelephonyTogglePreferenceController, com.android.settings.core.TogglePreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ Class getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.settings.network.telephony.TelephonyTogglePreferenceController, com.android.settings.core.TogglePreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.settings.network.telephony.TelephonyTogglePreferenceController, com.android.settings.core.TogglePreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.settings.network.telephony.TelephonyTogglePreferenceController, com.android.settings.core.TogglePreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public AutoSelectPreferenceController(Context context, String str) {
        super(context, str);
        this.mTelephonyManager = (TelephonyManager) context.getSystemService(TelephonyManager.class);
        this.mSubId = -1;
        this.mRecursiveUpdate = new AtomicLong();
        this.mUpdatingConfig = new AtomicBoolean();
        this.mCacheOfModeStatus = 0;
        this.mListeners = new ArrayList();
        Handler handler = new Handler(Looper.getMainLooper());
        this.mUiHandler = handler;
        AllowedNetworkTypesListener allowedNetworkTypesListener = new AllowedNetworkTypesListener(new HandlerExecutor(handler));
        this.mAllowedNetworkTypesListener = allowedNetworkTypesListener;
        allowedNetworkTypesListener.setAllowedNetworkTypesListener(new AllowedNetworkTypesListener.OnAllowedNetworkTypesListener() { // from class: com.android.settings.network.telephony.gsm.AutoSelectPreferenceController$$ExternalSyntheticLambda5
            @Override // com.android.settings.network.AllowedNetworkTypesListener.OnAllowedNetworkTypesListener
            public final void onAllowedNetworkTypesChanged() {
                AutoSelectPreferenceController.this.lambda$new$0();
            }
        });
        this.mTelephonyCallbackListener = new TelephonyCallbackListener();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: updatePreference */
    public void lambda$new$0() {
        PreferenceScreen preferenceScreen = this.mPreferenceScreen;
        if (preferenceScreen != null) {
            displayPreference(preferenceScreen);
        }
        if (this.mSwitchPreference != null) {
            this.mRecursiveUpdate.getAndIncrement();
            updateState(this.mSwitchPreference);
            this.mRecursiveUpdate.decrementAndGet();
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
        this.mAllowedNetworkTypesListener.register(this.mContext, this.mSubId);
        this.mTelephonyManager.registerTelephonyCallback(new HandlerExecutor(this.mUiHandler), this.mTelephonyCallbackListener);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {
        this.mAllowedNetworkTypesListener.unregister(this.mContext, this.mSubId);
        this.mTelephonyManager.unregisterTelephonyCallback(this.mTelephonyCallbackListener);
    }

    @Override // com.android.settings.network.telephony.TelephonyTogglePreferenceController, com.android.settings.network.telephony.TelephonyAvailabilityCallback
    public int getAvailabilityStatus(int i) {
        return MobileNetworkUtils.shouldDisplayNetworkSelectOptions(this.mContext, i) ? 0 : 2;
    }

    @Override // com.android.settings.core.TogglePreferenceController, com.android.settings.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreferenceScreen = preferenceScreen;
        this.mSwitchPreference = (SwitchPreference) preferenceScreen.findPreference(getPreferenceKey());
    }

    @Override // com.android.settings.core.TogglePreferenceController
    public boolean isChecked() {
        return this.mCacheOfModeStatus == 1;
    }

    @Override // com.android.settings.core.TogglePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        super.updateState(preference);
        preference.setSummary((CharSequence) null);
        ServiceState serviceState = this.mTelephonyManager.getServiceState();
        if (serviceState == null) {
            preference.setEnabled(false);
        } else if (serviceState.getRoaming()) {
            preference.setEnabled(true);
        } else {
            preference.setEnabled(!this.mOnlyAutoSelectInHome);
            if (this.mOnlyAutoSelectInHome) {
                preference.setSummary(this.mContext.getString(R.string.manual_mode_disallowed_summary, this.mTelephonyManager.getSimOperatorName()));
            }
        }
    }

    @Override // com.android.settings.core.TogglePreferenceController
    public boolean setChecked(boolean z) {
        if (this.mRecursiveUpdate.get() != 0) {
            return true;
        }
        if (z) {
            setAutomaticSelectionMode();
            return false;
        } else if (this.mSwitchPreference != null) {
            Intent intent = new Intent();
            intent.setClassName("com.android.settings", "com.android.settings.Settings$NetworkSelectActivity");
            intent.putExtra("android.provider.extra.SUB_ID", this.mSubId);
            this.mSwitchPreference.setIntent(intent);
            return false;
        } else {
            return false;
        }
    }

    Future setAutomaticSelectionMode() {
        final long elapsedRealtime = SystemClock.elapsedRealtime();
        showAutoSelectProgressBar();
        SwitchPreference switchPreference = this.mSwitchPreference;
        if (switchPreference != null) {
            switchPreference.setIntent(null);
            this.mSwitchPreference.setEnabled(false);
        }
        return ThreadUtils.postOnBackgroundThread(new Runnable() { // from class: com.android.settings.network.telephony.gsm.AutoSelectPreferenceController$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                AutoSelectPreferenceController.this.lambda$setAutomaticSelectionMode$4(elapsedRealtime);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setAutomaticSelectionMode$4(long j) {
        this.mUpdatingConfig.set(true);
        this.mTelephonyManager.setNetworkSelectionModeAutomatic();
        this.mUpdatingConfig.set(false);
        this.mUiHandler.postDelayed(new Runnable() { // from class: com.android.settings.network.telephony.gsm.AutoSelectPreferenceController$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                AutoSelectPreferenceController.this.lambda$setAutomaticSelectionMode$3();
            }
        }, Math.max(MINIMUM_DIALOG_TIME_MILLIS - (SystemClock.elapsedRealtime() - j), 0L));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setAutomaticSelectionMode$3() {
        ThreadUtils.postOnBackgroundThread(new Runnable() { // from class: com.android.settings.network.telephony.gsm.AutoSelectPreferenceController$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                AutoSelectPreferenceController.this.lambda$setAutomaticSelectionMode$2();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setAutomaticSelectionMode$2() {
        queryNetworkSelectionMode(INTERNAL_LOG_TAG_AFTERSET);
        this.mUiHandler.post(new Runnable() { // from class: com.android.settings.network.telephony.gsm.AutoSelectPreferenceController$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                AutoSelectPreferenceController.this.lambda$setAutomaticSelectionMode$1();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setAutomaticSelectionMode$1() {
        this.mRecursiveUpdate.getAndIncrement();
        SwitchPreference switchPreference = this.mSwitchPreference;
        if (switchPreference != null) {
            switchPreference.setEnabled(true);
            this.mSwitchPreference.setChecked(isChecked());
        }
        this.mRecursiveUpdate.decrementAndGet();
        updateListenerValue();
        dismissProgressBar();
    }

    public AutoSelectPreferenceController init(Lifecycle lifecycle, int i) {
        this.mSubId = i;
        this.mTelephonyManager = ((TelephonyManager) this.mContext.getSystemService(TelephonyManager.class)).createForSubscriptionId(this.mSubId);
        PersistableBundle configForSubId = CarrierConfigCache.getInstance(this.mContext).getConfigForSubId(this.mSubId);
        this.mOnlyAutoSelectInHome = configForSubId != null ? configForSubId.getBoolean("only_auto_select_in_home_network") : false;
        ThreadUtils.postOnBackgroundThread(new Runnable() { // from class: com.android.settings.network.telephony.gsm.AutoSelectPreferenceController$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                AutoSelectPreferenceController.this.lambda$init$6();
            }
        });
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$init$6() {
        queryNetworkSelectionMode(INTERNAL_LOG_TAG_INIT);
        this.mUiHandler.post(new Runnable() { // from class: com.android.settings.network.telephony.gsm.AutoSelectPreferenceController$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                AutoSelectPreferenceController.this.lambda$init$5();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$init$5() {
        if (this.mSwitchPreference != null) {
            this.mRecursiveUpdate.getAndIncrement();
            this.mSwitchPreference.setChecked(isChecked());
            this.mRecursiveUpdate.decrementAndGet();
            updateListenerValue();
        }
    }

    public AutoSelectPreferenceController addListener(OnNetworkSelectModeListener onNetworkSelectModeListener) {
        this.mListeners.add(onNetworkSelectModeListener);
        return this;
    }

    private void queryNetworkSelectionMode(String str) {
        this.mCacheOfModeStatus = this.mTelephonyManager.getNetworkSelectionMode();
        Log.d(LOG_TAG, str + ": query command done. mCacheOfModeStatus: " + this.mCacheOfModeStatus);
    }

    void updateUiAutoSelectValue(ServiceState serviceState) {
        if (serviceState == null || this.mUpdatingConfig.get()) {
            return;
        }
        int i = serviceState.getIsManualSelection() ? 2 : 1;
        if (this.mCacheOfModeStatus == i) {
            return;
        }
        this.mCacheOfModeStatus = i;
        Log.d(LOG_TAG, "updateUiAutoSelectValue: mCacheOfModeStatus: " + this.mCacheOfModeStatus);
        this.mRecursiveUpdate.getAndIncrement();
        updateState(this.mSwitchPreference);
        this.mRecursiveUpdate.decrementAndGet();
        updateListenerValue();
    }

    private void updateListenerValue() {
        for (OnNetworkSelectModeListener onNetworkSelectModeListener : this.mListeners) {
            onNetworkSelectModeListener.onNetworkSelectModeUpdated(this.mCacheOfModeStatus);
        }
    }

    private void showAutoSelectProgressBar() {
        if (this.mProgressDialog == null) {
            ProgressDialog progressDialog = new ProgressDialog(this.mContext);
            this.mProgressDialog = progressDialog;
            progressDialog.setMessage(this.mContext.getResources().getString(R.string.register_automatically));
            this.mProgressDialog.setCanceledOnTouchOutside(false);
            this.mProgressDialog.setCancelable(false);
            this.mProgressDialog.setIndeterminate(true);
        }
        this.mProgressDialog.show();
    }

    private void dismissProgressBar() {
        ProgressDialog progressDialog = this.mProgressDialog;
        if (progressDialog == null || !progressDialog.isShowing()) {
            return;
        }
        try {
            this.mProgressDialog.dismiss();
        } catch (IllegalArgumentException unused) {
        }
    }

    /* loaded from: classes.dex */
    private class TelephonyCallbackListener extends TelephonyCallback implements TelephonyCallback.ServiceStateListener {
        private TelephonyCallbackListener() {
        }

        @Override // android.telephony.TelephonyCallback.ServiceStateListener
        public void onServiceStateChanged(ServiceState serviceState) {
            AutoSelectPreferenceController.this.updateUiAutoSelectValue(serviceState);
        }
    }
}
