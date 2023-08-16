package com.android.settings.biometrics.fingerprint;

import android.content.Context;
import android.content.IntentFilter;
import android.hardware.fingerprint.FingerprintManager;
import android.os.UserHandle;
import android.provider.Settings;
import androidx.preference.Preference;
import com.android.internal.annotations.VisibleForTesting;
import com.android.settings.Utils;
/* loaded from: classes.dex */
public class FingerprintSettingsRequireScreenOnToAuthPreferenceController extends FingerprintSettingsPreferenceController {
    private static final String TAG = "FingerprintSettingsRequireScreenOnToAuthPreferenceController";
    @VisibleForTesting
    protected FingerprintManager mFingerprintManager;

    @Override // com.android.settings.biometrics.fingerprint.FingerprintSettingsPreferenceController, com.android.settings.core.TogglePreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ Class getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.settings.biometrics.fingerprint.FingerprintSettingsPreferenceController, com.android.settings.core.TogglePreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.settings.biometrics.fingerprint.FingerprintSettingsPreferenceController, com.android.settings.core.TogglePreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.settings.biometrics.fingerprint.FingerprintSettingsPreferenceController, com.android.settings.core.TogglePreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public FingerprintSettingsRequireScreenOnToAuthPreferenceController(Context context, String str) {
        super(context, str);
        this.mFingerprintManager = Utils.getFingerprintManagerOrNull(context);
    }

    /* JADX WARN: Type inference failed for: r0v9, types: [boolean, int] */
    @Override // com.android.settings.core.TogglePreferenceController
    public boolean isChecked() {
        if (FingerprintSettings.isFingerprintHardwareDetected(this.mContext) && getRestrictingAdmin() == null) {
            int intForUser = Settings.Secure.getIntForUser(this.mContext.getContentResolver(), "sfps_performant_auth_enabled_v2", -1, getUserHandle());
            int i = intForUser;
            if (intForUser == -1) {
                ?? r0 = this.mContext.getResources().getBoolean(17891744);
                Settings.Secure.putIntForUser(this.mContext.getContentResolver(), "sfps_performant_auth_enabled_v2", r0, getUserHandle());
                i = r0;
            }
            return i == 1;
        }
        return false;
    }

    @Override // com.android.settings.core.TogglePreferenceController
    public boolean setChecked(boolean z) {
        Settings.Secure.putIntForUser(this.mContext.getContentResolver(), "sfps_performant_auth_enabled_v2", z ? 1 : 0, getUserHandle());
        return true;
    }

    @Override // com.android.settings.core.TogglePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        super.updateState(preference);
        if (!FingerprintSettings.isFingerprintHardwareDetected(this.mContext)) {
            preference.setEnabled(false);
        } else if (!this.mFingerprintManager.hasEnrolledTemplates(getUserId())) {
            preference.setEnabled(false);
        } else {
            preference.setEnabled(true);
        }
    }

    @Override // com.android.settings.core.BasePreferenceController
    public int getAvailabilityStatus() {
        FingerprintManager fingerprintManager = this.mFingerprintManager;
        if (fingerprintManager != null && fingerprintManager.isHardwareDetected() && this.mFingerprintManager.isPowerbuttonFps()) {
            return this.mFingerprintManager.hasEnrolledTemplates(getUserId()) ? 0 : 5;
        }
        return 3;
    }

    private int getUserHandle() {
        return UserHandle.of(getUserId()).getIdentifier();
    }
}
