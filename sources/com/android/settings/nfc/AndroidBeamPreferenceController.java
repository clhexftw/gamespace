package com.android.settings.nfc;

import android.content.Context;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import androidx.preference.PreferenceScreen;
import com.android.settings.core.BasePreferenceController;
import com.android.settingslib.RestrictedPreference;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnPause;
import com.android.settingslib.core.lifecycle.events.OnResume;
/* loaded from: classes.dex */
public class AndroidBeamPreferenceController extends BasePreferenceController implements LifecycleObserver, OnResume, OnPause {
    public static final String KEY_ANDROID_BEAM_SETTINGS = "android_beam_settings";
    private AndroidBeamEnabler mAndroidBeamEnabler;
    private final NfcAdapter mNfcAdapter;

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

    public AndroidBeamPreferenceController(Context context, String str) {
        super(context, str);
        this.mNfcAdapter = NfcAdapter.getDefaultAdapter(context);
    }

    @Override // com.android.settings.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        if (!isAvailable()) {
            this.mAndroidBeamEnabler = null;
            return;
        }
        this.mAndroidBeamEnabler = new AndroidBeamEnabler(this.mContext, (RestrictedPreference) preferenceScreen.findPreference(getPreferenceKey()));
    }

    @Override // com.android.settings.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return (this.mContext.getPackageManager().hasSystemFeature("android.sofware.nfc.beam") && this.mNfcAdapter != null) ? 0 : 3;
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnResume
    public void onResume() {
        AndroidBeamEnabler androidBeamEnabler = this.mAndroidBeamEnabler;
        if (androidBeamEnabler != null) {
            androidBeamEnabler.resume();
        }
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnPause
    public void onPause() {
        AndroidBeamEnabler androidBeamEnabler = this.mAndroidBeamEnabler;
        if (androidBeamEnabler != null) {
            androidBeamEnabler.pause();
        }
    }
}
