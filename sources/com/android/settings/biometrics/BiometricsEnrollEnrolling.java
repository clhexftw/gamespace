package com.android.settings.biometrics;

import android.content.Intent;
import android.view.View;
import com.android.settings.R;
import com.android.settings.biometrics.BiometricEnrollSidecar;
/* loaded from: classes.dex */
public abstract class BiometricsEnrollEnrolling extends BiometricEnrollBase implements BiometricEnrollSidecar.Listener {
    protected BiometricEnrollSidecar mSidecar;

    protected abstract Intent getFinishIntent();

    protected abstract BiometricEnrollSidecar getSidecar();

    protected abstract boolean shouldStartAutomatically();

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settingslib.core.lifecycle.ObservableActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        if (shouldStartAutomatically()) {
            startEnrollment();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settings.biometrics.BiometricEnrollBase, com.android.settingslib.core.lifecycle.ObservableActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        BiometricEnrollSidecar biometricEnrollSidecar;
        BiometricEnrollSidecar biometricEnrollSidecar2 = this.mSidecar;
        if (biometricEnrollSidecar2 != null) {
            biometricEnrollSidecar2.setListener(null);
        }
        if (!isChangingConfigurations() && (biometricEnrollSidecar = this.mSidecar) != null) {
            biometricEnrollSidecar.cancelEnrollment();
            getSupportFragmentManager().beginTransaction().remove(this.mSidecar).commitAllowingStateLoss();
        }
        super.onStop();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        cancelEnrollment();
        super.onBackPressed();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onSkipButtonClick(View view) {
        cancelEnrollment();
        setResult(2);
        finish();
    }

    public void cancelEnrollment() {
        BiometricEnrollSidecar biometricEnrollSidecar = this.mSidecar;
        if (biometricEnrollSidecar != null) {
            biometricEnrollSidecar.setListener(null);
            this.mSidecar.cancelEnrollment();
            getSupportFragmentManager().beginTransaction().remove(this.mSidecar).commitAllowingStateLoss();
            this.mSidecar = null;
        }
    }

    public void startEnrollment() {
        BiometricEnrollSidecar biometricEnrollSidecar = (BiometricEnrollSidecar) getSupportFragmentManager().findFragmentByTag("sidecar");
        this.mSidecar = biometricEnrollSidecar;
        if (biometricEnrollSidecar == null) {
            this.mSidecar = getSidecar();
            getSupportFragmentManager().beginTransaction().add(this.mSidecar, "sidecar").commitAllowingStateLoss();
        }
        this.mSidecar.setListener(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void launchFinish(byte[] bArr) {
        Intent finishIntent = getFinishIntent();
        finishIntent.addFlags(637534208);
        finishIntent.putExtra("hw_auth_token", bArr);
        finishIntent.putExtra("sensor_id", this.mSensorId);
        finishIntent.putExtra("challenge", this.mChallenge);
        finishIntent.putExtra("from_settings_summary", this.mFromSettingsSummary);
        int i = this.mUserId;
        if (i != -10000) {
            finishIntent.putExtra("android.intent.extra.USER_ID", i);
        }
        startActivity(finishIntent);
        overridePendingTransition(R.anim.sud_slide_next_in, R.anim.sud_slide_next_out);
        finish();
    }
}
