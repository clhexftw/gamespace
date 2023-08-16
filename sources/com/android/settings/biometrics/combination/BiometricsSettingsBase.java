package com.android.settings.biometrics.combination;

import android.content.Context;
import android.content.Intent;
import android.hardware.face.FaceManager;
import android.hardware.face.FaceSensorPropertiesInternal;
import android.hardware.fingerprint.FingerprintManager;
import android.hardware.fingerprint.FingerprintSensorPropertiesInternal;
import android.os.Bundle;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.Log;
import androidx.preference.Preference;
import com.android.settings.R;
import com.android.settings.Utils;
import com.android.settings.biometrics.BiometricUtils;
import com.android.settings.dashboard.DashboardFragment;
import com.android.settings.password.ChooseLockSettingsHelper;
/* loaded from: classes.dex */
public abstract class BiometricsSettingsBase extends DashboardFragment {
    static final int CONFIRM_REQUEST = 2001;
    static final String RETRY_PREFERENCE_BUNDLE = "retry_preference_bundle";
    static final String RETRY_PREFERENCE_KEY = "retry_preference_key";
    private boolean mConfirmCredential;
    private boolean mDoNotFinishActivity;
    private FaceManager mFaceManager;
    private FingerprintManager mFingerprintManager;
    protected long mGkPwHandle;
    protected int mUserId;
    private String mRetryPreferenceKey = null;
    private Bundle mRetryPreferenceExtra = null;

    public abstract String getFacePreferenceKey();

    public abstract String getFingerprintPreferenceKey();

    public abstract String getUnlockPhonePreferenceKey();

    public abstract String getUseInAppsPreferenceKey();

    @Override // com.android.settings.dashboard.DashboardFragment, com.android.settings.SettingsPreferenceFragment, com.android.settings.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mUserId = getActivity().getIntent().getIntExtra("android.intent.extra.USER_ID", UserHandle.myUserId());
    }

    @Override // com.android.settings.dashboard.DashboardFragment, com.android.settings.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mFaceManager = Utils.getFaceManagerOrNull(getActivity());
        this.mFingerprintManager = Utils.getFingerprintManagerOrNull(getActivity());
        if (BiometricUtils.containsGatekeeperPasswordHandle(getIntent())) {
            this.mGkPwHandle = BiometricUtils.getGatekeeperPasswordHandle(getIntent());
        }
        if (bundle != null) {
            this.mConfirmCredential = bundle.getBoolean("confirm_credential");
            this.mDoNotFinishActivity = bundle.getBoolean("do_not_finish_activity");
            this.mRetryPreferenceKey = bundle.getString(RETRY_PREFERENCE_KEY);
            this.mRetryPreferenceExtra = bundle.getBundle(RETRY_PREFERENCE_BUNDLE);
            if (bundle.containsKey("request_gk_pw_handle")) {
                this.mGkPwHandle = bundle.getLong("request_gk_pw_handle");
            }
        }
        if (this.mGkPwHandle == 0 && !this.mConfirmCredential) {
            this.mConfirmCredential = true;
            launchChooseOrConfirmLock();
        }
        Preference findPreference = findPreference(getUnlockPhonePreferenceKey());
        if (findPreference != null) {
            findPreference.setSummary(getUseAnyBiometricSummary());
        }
        Preference findPreference2 = findPreference(getUseInAppsPreferenceKey());
        if (findPreference2 != null) {
            findPreference2.setSummary(getUseClass2BiometricSummary());
        }
    }

    @Override // com.android.settings.dashboard.DashboardFragment, com.android.settings.SettingsPreferenceFragment, com.android.settings.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.mConfirmCredential) {
            return;
        }
        this.mDoNotFinishActivity = false;
    }

    @Override // com.android.settings.dashboard.DashboardFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        if (getActivity().isChangingConfigurations() || this.mDoNotFinishActivity) {
            return;
        }
        BiometricUtils.removeGatekeeperPasswordHandle(getActivity(), this.mGkPwHandle);
        getActivity().finish();
    }

    private boolean onRetryPreferenceTreeClick(final Preference preference, final boolean z) {
        String key = preference.getKey();
        final Context applicationContext = requireActivity().getApplicationContext();
        if (getFacePreferenceKey().equals(key)) {
            this.mDoNotFinishActivity = true;
            this.mFaceManager.generateChallenge(this.mUserId, new FaceManager.GenerateChallengeCallback() { // from class: com.android.settings.biometrics.combination.BiometricsSettingsBase$$ExternalSyntheticLambda0
                public final void onGenerateChallengeResult(int i, int i2, long j) {
                    BiometricsSettingsBase.this.lambda$onRetryPreferenceTreeClick$0(applicationContext, preference, z, i, i2, j);
                }
            });
            return true;
        } else if (getFingerprintPreferenceKey().equals(key)) {
            this.mDoNotFinishActivity = true;
            this.mFingerprintManager.generateChallenge(this.mUserId, new FingerprintManager.GenerateChallengeCallback() { // from class: com.android.settings.biometrics.combination.BiometricsSettingsBase$$ExternalSyntheticLambda1
                public final void onChallengeGenerated(int i, int i2, long j) {
                    BiometricsSettingsBase.this.lambda$onRetryPreferenceTreeClick$1(applicationContext, preference, z, i, i2, j);
                }
            });
            return true;
        } else {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onRetryPreferenceTreeClick$0(Context context, Preference preference, boolean z, int i, int i2, long j) {
        try {
            byte[] requestGatekeeperHat = requestGatekeeperHat(context, this.mGkPwHandle, this.mUserId, j);
            Bundle extras = preference.getExtras();
            extras.putByteArray("hw_auth_token", requestGatekeeperHat);
            extras.putInt("sensor_id", i);
            extras.putLong("challenge", j);
            super.onPreferenceTreeClick(preference);
        } catch (IllegalStateException e) {
            if (z) {
                this.mRetryPreferenceKey = preference.getKey();
                this.mRetryPreferenceExtra = preference.getExtras();
                this.mConfirmCredential = true;
                launchChooseOrConfirmLock();
                return;
            }
            Log.e(getLogTag(), "face generateChallenge fail", e);
            this.mDoNotFinishActivity = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onRetryPreferenceTreeClick$1(Context context, Preference preference, boolean z, int i, int i2, long j) {
        try {
            byte[] requestGatekeeperHat = requestGatekeeperHat(context, this.mGkPwHandle, this.mUserId, j);
            Bundle extras = preference.getExtras();
            extras.putByteArray("hw_auth_token", requestGatekeeperHat);
            extras.putLong("challenge", j);
            super.onPreferenceTreeClick(preference);
        } catch (IllegalStateException e) {
            if (z) {
                this.mRetryPreferenceKey = preference.getKey();
                this.mRetryPreferenceExtra = preference.getExtras();
                this.mConfirmCredential = true;
                launchChooseOrConfirmLock();
                return;
            }
            Log.e(getLogTag(), "fingerprint generateChallenge fail", e);
            this.mDoNotFinishActivity = false;
        }
    }

    protected byte[] requestGatekeeperHat(Context context, long j, int i, long j2) {
        return BiometricUtils.requestGatekeeperHat(context, j, i, j2);
    }

    @Override // com.android.settings.dashboard.DashboardFragment, com.android.settings.core.InstrumentedPreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.preference.PreferenceManager.OnPreferenceTreeClickListener
    public boolean onPreferenceTreeClick(Preference preference) {
        return onRetryPreferenceTreeClick(preference, true) || super.onPreferenceTreeClick(preference);
    }

    private void retryPreferenceKey(String str, Bundle bundle) {
        Preference findPreference = findPreference(str);
        if (findPreference == null) {
            String logTag = getLogTag();
            Log.w(logTag, ".retryPreferenceKey, fail to find " + str);
            return;
        }
        if (bundle != null) {
            findPreference.getExtras().putAll(bundle);
        }
        onRetryPreferenceTreeClick(findPreference, false);
    }

    @Override // com.android.settings.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("confirm_credential", this.mConfirmCredential);
        bundle.putBoolean("do_not_finish_activity", this.mDoNotFinishActivity);
        long j = this.mGkPwHandle;
        if (j != 0) {
            bundle.putLong("request_gk_pw_handle", j);
        }
        if (TextUtils.isEmpty(this.mRetryPreferenceKey)) {
            return;
        }
        bundle.putString(RETRY_PREFERENCE_KEY, this.mRetryPreferenceKey);
        bundle.putBundle(RETRY_PREFERENCE_BUNDLE, this.mRetryPreferenceExtra);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == CONFIRM_REQUEST || i == 2002) {
            this.mConfirmCredential = false;
            this.mDoNotFinishActivity = false;
            if (i2 == 1 || i2 == -1) {
                if (BiometricUtils.containsGatekeeperPasswordHandle(intent)) {
                    this.mGkPwHandle = BiometricUtils.getGatekeeperPasswordHandle(intent);
                    if (!TextUtils.isEmpty(this.mRetryPreferenceKey)) {
                        getActivity().overridePendingTransition(R.anim.sud_slide_next_in, R.anim.sud_slide_next_out);
                        retryPreferenceKey(this.mRetryPreferenceKey, this.mRetryPreferenceExtra);
                    }
                } else {
                    Log.d(getLogTag(), "Data null or GK PW missing.");
                    finish();
                }
            } else {
                Log.d(getLogTag(), "Password not confirmed.");
                finish();
            }
            this.mRetryPreferenceKey = null;
            this.mRetryPreferenceExtra = null;
        }
    }

    protected void launchChooseOrConfirmLock() {
        ChooseLockSettingsHelper.Builder returnCredentials = new ChooseLockSettingsHelper.Builder(getActivity(), this).setRequestCode(CONFIRM_REQUEST).setTitle(getString(R.string.security_settings_biometric_preference_title)).setRequestGatekeeperPasswordHandle(true).setForegroundOnly(true).setReturnCredentials(true);
        int i = this.mUserId;
        if (i != -10000) {
            returnCredentials.setUserId(i);
        }
        this.mDoNotFinishActivity = true;
        if (returnCredentials.show()) {
            return;
        }
        Intent chooseLockIntent = BiometricUtils.getChooseLockIntent(getActivity(), getIntent());
        chooseLockIntent.putExtra("hide_insecure_options", true);
        chooseLockIntent.putExtra("request_gk_pw_handle", true);
        chooseLockIntent.putExtra("for_biometrics", true);
        chooseLockIntent.putExtra("page_transition_type", 1);
        int i2 = this.mUserId;
        if (i2 != -10000) {
            chooseLockIntent.putExtra("android.intent.extra.USER_ID", i2);
        }
        startActivityForResult(chooseLockIntent, 2002);
    }

    private String getUseAnyBiometricSummary() {
        FaceManager faceManager = this.mFaceManager;
        boolean z = true;
        boolean z2 = faceManager != null && faceManager.isHardwareDetected();
        FingerprintManager fingerprintManager = this.mFingerprintManager;
        if (fingerprintManager == null || !fingerprintManager.isHardwareDetected()) {
            z = false;
        }
        int useBiometricSummaryRes = getUseBiometricSummaryRes(z2, z);
        return useBiometricSummaryRes == 0 ? "" : getString(useBiometricSummaryRes);
    }

    private String getUseClass2BiometricSummary() {
        boolean z;
        FaceManager faceManager = this.mFaceManager;
        boolean z2 = false;
        if (faceManager != null) {
            for (FaceSensorPropertiesInternal faceSensorPropertiesInternal : faceManager.getSensorPropertiesInternal()) {
                int i = faceSensorPropertiesInternal.sensorStrength;
                if (i != 1) {
                    if (i == 2) {
                    }
                }
                z = true;
            }
        }
        z = false;
        FingerprintManager fingerprintManager = this.mFingerprintManager;
        if (fingerprintManager != null) {
            for (FingerprintSensorPropertiesInternal fingerprintSensorPropertiesInternal : fingerprintManager.getSensorPropertiesInternal()) {
                int i2 = fingerprintSensorPropertiesInternal.sensorStrength;
                if (i2 != 1) {
                    if (i2 == 2) {
                    }
                }
                z2 = true;
            }
        }
        int useBiometricSummaryRes = getUseBiometricSummaryRes(z, z2);
        return useBiometricSummaryRes == 0 ? "" : getString(useBiometricSummaryRes);
    }

    private static int getUseBiometricSummaryRes(boolean z, boolean z2) {
        if (z && z2) {
            return R.string.biometric_settings_use_face_or_fingerprint_preference_summary;
        }
        if (z) {
            return R.string.biometric_settings_use_face_preference_summary;
        }
        if (z2) {
            return R.string.biometric_settings_use_fingerprint_preference_summary;
        }
        return 0;
    }
}
