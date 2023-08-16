package com.android.settings.biometrics.fingerprint;

import android.app.KeyguardManager;
import android.content.Intent;
import android.hardware.fingerprint.FingerprintManager;
import com.android.settings.SetupWizardUtils;
import com.android.settings.Utils;
import com.android.settings.biometrics.BiometricUtils;
/* loaded from: classes.dex */
public class SetupFingerprintEnrollIntroduction extends FingerprintEnrollIntroduction {
    @Override // com.android.settings.biometrics.fingerprint.FingerprintEnrollIntroduction, com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 249;
    }

    @Override // com.android.settings.biometrics.fingerprint.FingerprintEnrollIntroduction, com.android.settings.biometrics.BiometricEnrollIntroduction
    protected Intent getEnrollingIntent() {
        Intent intent = new Intent(this, SetupFingerprintEnrollFindSensor.class);
        BiometricUtils.copyMultiBiometricExtras(getIntent(), intent);
        if (BiometricUtils.containsGatekeeperPasswordHandle(getIntent())) {
            intent.putExtra("gk_pw_handle", BiometricUtils.getGatekeeperPasswordHandle(getIntent()));
        }
        SetupWizardUtils.copySetupExtras(getIntent(), intent);
        return intent;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settings.biometrics.fingerprint.FingerprintEnrollIntroduction, com.android.settings.biometrics.BiometricEnrollIntroduction, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        boolean z;
        if (intent != null) {
            boolean booleanExtra = intent.getBooleanExtra("finished_enrolling_face", false);
            z = intent.getBooleanExtra("finished_enrolling_fingerprint", false);
            if (booleanExtra) {
                removeEnrollNextBiometric();
            }
        } else {
            z = false;
        }
        if (i == 2 && isKeyguardSecure()) {
            if (i2 == 1) {
                intent = setFingerprintCount(intent);
            }
            if (i2 == 0 && z) {
                setResult(i2, intent);
                finish();
                return;
            }
        } else if (i == 6) {
            boolean z2 = checkMaxEnrolled() == 0;
            if (i2 == 2 || i2 == 1) {
                setResult(1, intent);
                finish();
                return;
            } else if (i2 != 0) {
                super.onActivityResult(i, i2, intent);
                return;
            } else if (z2) {
                return;
            } else {
                finish();
                return;
            }
        }
        super.onActivityResult(i, i2, intent);
    }

    private Intent setFingerprintCount(Intent intent) {
        if (intent == null) {
            intent = new Intent();
        }
        FingerprintManager fingerprintManagerOrNull = Utils.getFingerprintManagerOrNull(this);
        if (fingerprintManagerOrNull != null) {
            intent.putExtra("fingerprint_enrolled_count", fingerprintManagerOrNull.getEnrolledFingerprints(this.mUserId).size());
        }
        return intent;
    }

    private boolean isKeyguardSecure() {
        return ((KeyguardManager) getSystemService(KeyguardManager.class)).isKeyguardSecure();
    }
}
