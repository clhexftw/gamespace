package com.android.settings.connecteddevice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.nfc.NfcAdapter;
import android.os.UserManager;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.R;
import com.android.settings.core.BasePreferenceController;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnResume;
import com.android.settingslib.core.lifecycle.events.OnStop;
/* loaded from: classes.dex */
public class NfcAndPaymentFragmentController extends BasePreferenceController implements LifecycleObserver, OnResume, OnStop {
    private final IntentFilter mIntentFilter;
    private final NfcAdapter mNfcAdapter;
    private final PackageManager mPackageManager;
    private Preference mPreference;
    private final BroadcastReceiver mReceiver;
    private final UserManager mUserManager;

    @Override // com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ Class getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ int getSliceHighlightMenuRes() {
        return super.getSliceHighlightMenuRes();
    }

    @Override // com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isPublicSlice() {
        return super.isPublicSlice();
    }

    @Override // com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isSliceable() {
        return super.isSliceable();
    }

    @Override // com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public NfcAndPaymentFragmentController(Context context, String str) {
        super(context, str);
        this.mReceiver = new BroadcastReceiver() { // from class: com.android.settings.connecteddevice.NfcAndPaymentFragmentController.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                if (NfcAndPaymentFragmentController.this.mPreference != null && "android.nfc.action.ADAPTER_STATE_CHANGED".equals(intent.getAction())) {
                    NfcAndPaymentFragmentController nfcAndPaymentFragmentController = NfcAndPaymentFragmentController.this;
                    nfcAndPaymentFragmentController.refreshSummary(nfcAndPaymentFragmentController.mPreference);
                }
            }
        };
        this.mPackageManager = context.getPackageManager();
        this.mUserManager = (UserManager) context.getSystemService(UserManager.class);
        this.mNfcAdapter = NfcAdapter.getDefaultAdapter(context);
        this.mIntentFilter = isNfcAvailable() ? new IntentFilter("android.nfc.action.ADAPTER_STATE_CHANGED") : null;
    }

    @Override // com.android.settings.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreference = preferenceScreen.findPreference(getPreferenceKey());
    }

    @Override // com.android.settings.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return (this.mPackageManager.hasSystemFeature("android.hardware.nfc") && this.mPackageManager.hasSystemFeature("android.hardware.nfc.hce")) ? 0 : 3;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public CharSequence getSummary() {
        NfcAdapter nfcAdapter = this.mNfcAdapter;
        if (nfcAdapter != null) {
            if (nfcAdapter.isEnabled()) {
                return this.mContext.getText(R.string.nfc_setting_on);
            }
            return this.mContext.getText(R.string.nfc_setting_off);
        }
        return null;
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStop
    public void onStop() {
        if (isNfcAvailable()) {
            this.mContext.unregisterReceiver(this.mReceiver);
        }
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnResume
    public void onResume() {
        if (isNfcAvailable()) {
            this.mContext.registerReceiver(this.mReceiver, this.mIntentFilter);
        }
    }

    private boolean isNfcAvailable() {
        return this.mNfcAdapter != null;
    }
}
