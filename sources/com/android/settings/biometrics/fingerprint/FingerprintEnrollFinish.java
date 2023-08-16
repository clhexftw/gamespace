package com.android.settings.biometrics.fingerprint;

import android.content.ComponentName;
import android.content.Intent;
import android.hardware.fingerprint.FingerprintManager;
import android.hardware.fingerprint.FingerprintSensorPropertiesInternal;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.android.settings.R;
import com.android.settings.Utils;
import com.android.settings.biometrics.BiometricEnrollBase;
import com.android.settings.biometrics.BiometricUtils;
import com.google.android.setupcompat.template.FooterBarMixin;
import com.google.android.setupcompat.template.FooterButton;
import com.google.android.setupcompat.util.WizardManagerHelper;
import java.util.List;
/* loaded from: classes.dex */
public class FingerprintEnrollFinish extends BiometricEnrollBase {
    static final String FINGERPRINT_SUGGESTION_ACTIVITY = "com.android.settings.SetupFingerprintSuggestionActivity";
    private boolean mCanAssumeSfps;
    private FingerprintManager mFingerprintManager;
    private boolean mIsAddAnotherOrFinish;

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 242;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settings.biometrics.BiometricEnrollBase, com.android.settings.core.InstrumentedActivity, com.android.settingslib.core.lifecycle.ObservableActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        FingerprintManager fingerprintManager = (FingerprintManager) getSystemService(FingerprintManager.class);
        this.mFingerprintManager = fingerprintManager;
        List sensorPropertiesInternal = fingerprintManager.getSensorPropertiesInternal();
        boolean z = true;
        if (sensorPropertiesInternal == null || sensorPropertiesInternal.size() != 1 || !((FingerprintSensorPropertiesInternal) sensorPropertiesInternal.get(0)).isAnySidefpsType()) {
            z = false;
        }
        this.mCanAssumeSfps = z;
        if (z) {
            setContentView(R.layout.sfps_enroll_finish);
        } else {
            setContentView(R.layout.fingerprint_enroll_finish);
        }
        setHeaderText(R.string.security_settings_fingerprint_enroll_finish_title);
        setDescriptionText(R.string.security_settings_fingerprint_enroll_finish_v2_message);
        if (this.mCanAssumeSfps) {
            setDescriptionForSfps();
        }
        FooterBarMixin footerBarMixin = (FooterBarMixin) getLayout().getMixin(FooterBarMixin.class);
        this.mFooterBarMixin = footerBarMixin;
        footerBarMixin.setSecondaryButton(new FooterButton.Builder(this).setText(R.string.fingerprint_enroll_button_add).setButtonType(7).setTheme(R.style.SudGlifButton_Secondary).build());
        this.mFooterBarMixin.setPrimaryButton(new FooterButton.Builder(this).setText(R.string.security_settings_fingerprint_enroll_done).setListener(new View.OnClickListener() { // from class: com.android.settings.biometrics.fingerprint.FingerprintEnrollFinish$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FingerprintEnrollFinish.this.onNextButtonClick(view);
            }
        }).setButtonType(5).setTheme(R.style.SudGlifButton_Primary).build());
    }

    private void setDescriptionForSfps() {
        FingerprintManager fingerprintManagerOrNull = Utils.getFingerprintManagerOrNull(this);
        if (fingerprintManagerOrNull != null) {
            if (fingerprintManagerOrNull.getEnrolledFingerprints(this.mUserId).size() < ((FingerprintSensorPropertiesInternal) fingerprintManagerOrNull.getSensorPropertiesInternal().get(0)).maxEnrollmentsPerUser) {
                setDescriptionText(R.string.security_settings_fingerprint_enroll_finish_v2_add_fingerprint_message);
            }
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        updateFingerprintSuggestionEnableState();
        setResult(0, getIntent().putExtra("finished_enrolling_fingerprint", true));
        finish();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settingslib.core.lifecycle.ObservableActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        FooterButton secondaryButton = this.mFooterBarMixin.getSecondaryButton();
        FingerprintManager fingerprintManagerOrNull = Utils.getFingerprintManagerOrNull(this);
        boolean z = false;
        if (fingerprintManagerOrNull != null) {
            if (fingerprintManagerOrNull.getEnrolledFingerprints(this.mUserId).size() >= ((FingerprintSensorPropertiesInternal) fingerprintManagerOrNull.getSensorPropertiesInternal().get(0)).maxEnrollmentsPerUser) {
                z = true;
            }
        }
        if (z) {
            secondaryButton.setVisibility(4);
        } else {
            secondaryButton.setOnClickListener(new View.OnClickListener() { // from class: com.android.settings.biometrics.fingerprint.FingerprintEnrollFinish$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FingerprintEnrollFinish.this.onAddAnotherButtonClick(view);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settingslib.core.lifecycle.ObservableActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        this.mIsAddAnotherOrFinish = false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onNextButtonClick(View view) {
        updateFingerprintSuggestionEnableState();
        finishAndToNext(1);
    }

    private void finishAndToNext(int i) {
        this.mIsAddAnotherOrFinish = true;
        setResult(i);
        if (WizardManagerHelper.isAnySetupWizard(getIntent())) {
            postEnroll();
        }
        finish();
    }

    private void updateFingerprintSuggestionEnableState() {
        FingerprintManager fingerprintManagerOrNull = Utils.getFingerprintManagerOrNull(this);
        if (fingerprintManagerOrNull != null) {
            int size = fingerprintManagerOrNull.getEnrolledFingerprints(this.mUserId).size();
            getPackageManager().setComponentEnabledSetting(new ComponentName(getApplicationContext(), FINGERPRINT_SUGGESTION_ACTIVITY), size == 1 ? 1 : 2, 1);
            StringBuilder sb = new StringBuilder();
            sb.append("com.android.settings.SetupFingerprintSuggestionActivity enabled state = ");
            sb.append(size == 1);
            Log.d("FingerprintEnrollFinish", sb.toString());
        }
    }

    private void postEnroll() {
        FingerprintManager fingerprintManagerOrNull = Utils.getFingerprintManagerOrNull(this);
        if (fingerprintManagerOrNull != null) {
            fingerprintManagerOrNull.revokeChallenge(this.mUserId, this.mChallenge);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onAddAnotherButtonClick(View view) {
        this.mIsAddAnotherOrFinish = true;
        startActivityForResult(getFingerprintEnrollingIntent(), BiometricUtils.REQUEST_ADD_ANOTHER);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settings.biometrics.BiometricEnrollBase
    public boolean shouldFinishWhenBackgrounded() {
        return !this.mIsAddAnotherOrFinish && super.shouldFinishWhenBackgrounded();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        updateFingerprintSuggestionEnableState();
        int i3 = BiometricUtils.REQUEST_ADD_ANOTHER;
        if (i == i3 && i2 == 3) {
            finishAndToNext(i2);
        } else if (i == i3 && i2 != 0) {
            finishAndToNext(1);
        } else {
            super.onActivityResult(i, i2, intent);
        }
    }
}
