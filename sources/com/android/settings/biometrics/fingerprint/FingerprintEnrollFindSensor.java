package com.android.settings.biometrics.fingerprint;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.hardware.fingerprint.FingerprintManager;
import android.hardware.fingerprint.FingerprintSensorPropertiesInternal;
import android.os.Bundle;
import android.util.Log;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import com.airbnb.lottie.LottieAnimationView;
import com.android.settings.R;
import com.android.settings.Utils;
import com.android.settings.biometrics.BiometricEnrollBase;
import com.android.settings.biometrics.BiometricEnrollSidecar;
import com.android.settings.biometrics.BiometricUtils;
import com.android.settingslib.widget.LottieColorUtils;
import com.android.systemui.unfold.compat.ScreenSizeFoldProvider;
import com.android.systemui.unfold.updates.FoldProvider$FoldCallback;
import com.google.android.setupcompat.template.FooterBarMixin;
import com.google.android.setupcompat.template.FooterButton;
import java.util.List;
/* loaded from: classes.dex */
public class FingerprintEnrollFindSensor extends BiometricEnrollBase implements BiometricEnrollSidecar.Listener, FoldProvider$FoldCallback {
    private FingerprintFindSensorAnimation mAnimation;
    private boolean mCanAssumeSfps;
    private boolean mCanAssumeUdfps;
    private LottieAnimationView mIllustrationLottie;
    private boolean mIsFolded;
    private boolean mIsReverseDefaultRotation;
    private boolean mNextClicked;
    private OrientationEventListener mOrientationEventListener;
    private int mPreviousRotation = 0;
    private ScreenSizeFoldProvider mScreenSizeFoldProvider;
    private FingerprintEnrollSidecar mSidecar;

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 241;
    }

    @Override // com.android.settings.biometrics.BiometricEnrollSidecar.Listener
    public void onEnrollmentHelp(int i, CharSequence charSequence) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settings.biometrics.BiometricEnrollBase, com.android.settings.core.InstrumentedActivity, com.android.settingslib.core.lifecycle.ObservableActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        FingerprintManager fingerprintManagerOrNull = Utils.getFingerprintManagerOrNull(this);
        List sensorPropertiesInternal = fingerprintManagerOrNull.getSensorPropertiesInternal();
        boolean z = true;
        this.mCanAssumeUdfps = sensorPropertiesInternal != null && sensorPropertiesInternal.size() == 1 && ((FingerprintSensorPropertiesInternal) sensorPropertiesInternal.get(0)).isAnyUdfpsType();
        if (sensorPropertiesInternal == null || sensorPropertiesInternal.size() != 1 || !((FingerprintSensorPropertiesInternal) sensorPropertiesInternal.get(0)).isAnySidefpsType()) {
            z = false;
        }
        this.mCanAssumeSfps = z;
        setContentView(getContentView());
        ScreenSizeFoldProvider screenSizeFoldProvider = new ScreenSizeFoldProvider(getApplicationContext());
        this.mScreenSizeFoldProvider = screenSizeFoldProvider;
        screenSizeFoldProvider.registerCallback(this, getApplicationContext().getMainExecutor());
        this.mScreenSizeFoldProvider.onConfigurationChange(getApplicationContext().getResources().getConfiguration());
        FooterBarMixin footerBarMixin = (FooterBarMixin) getLayout().getMixin(FooterBarMixin.class);
        this.mFooterBarMixin = footerBarMixin;
        footerBarMixin.setSecondaryButton(new FooterButton.Builder(this).setText(R.string.security_settings_fingerprint_enroll_enrolling_skip).setListener(new View.OnClickListener() { // from class: com.android.settings.biometrics.fingerprint.FingerprintEnrollFindSensor$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FingerprintEnrollFindSensor.this.onSkipButtonClick(view);
            }
        }).setButtonType(7).setTheme(R.style.SudGlifButton_Secondary).build());
        listenOrientationEvent();
        if (this.mCanAssumeUdfps) {
            setHeaderText(R.string.security_settings_udfps_enroll_find_sensor_title);
            setDescriptionText(R.string.security_settings_udfps_enroll_find_sensor_message);
            this.mFooterBarMixin.setPrimaryButton(new FooterButton.Builder(this).setText(R.string.security_settings_udfps_enroll_find_sensor_start_button).setListener(new View.OnClickListener() { // from class: com.android.settings.biometrics.fingerprint.FingerprintEnrollFindSensor$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FingerprintEnrollFindSensor.this.onStartButtonClick(view);
                }
            }).setButtonType(5).setTheme(R.style.SudGlifButton_Primary).build());
            this.mIllustrationLottie = (LottieAnimationView) findViewById(R.id.illustration_lottie);
            if (((AccessibilityManager) getSystemService(AccessibilityManager.class)).isEnabled()) {
                this.mIllustrationLottie.setAnimation(R.raw.udfps_edu_a11y_lottie);
            }
        } else if (this.mCanAssumeSfps) {
            setHeaderText(R.string.security_settings_sfps_enroll_find_sensor_title);
            setDescriptionText(R.string.security_settings_sfps_enroll_find_sensor_message);
            this.mIsReverseDefaultRotation = getApplicationContext().getResources().getBoolean(17891765);
        } else {
            setHeaderText(R.string.security_settings_fingerprint_enroll_find_sensor_title);
            setDescriptionText(R.string.security_settings_fingerprint_enroll_find_sensor_message);
        }
        if (bundle != null) {
            this.mNextClicked = bundle.getBoolean("is_next_clicked", this.mNextClicked);
        }
        if (this.mToken == null && BiometricUtils.containsGatekeeperPasswordHandle(getIntent())) {
            fingerprintManagerOrNull.generateChallenge(this.mUserId, new FingerprintManager.GenerateChallengeCallback() { // from class: com.android.settings.biometrics.fingerprint.FingerprintEnrollFindSensor$$ExternalSyntheticLambda2
                public final void onChallengeGenerated(int i, int i2, long j) {
                    FingerprintEnrollFindSensor.this.lambda$onCreate$0(i, i2, j);
                }
            });
        } else if (this.mToken != null) {
            if (!this.mNextClicked) {
                startLookingForFingerprint();
            }
        } else {
            throw new IllegalStateException("HAT and GkPwHandle both missing...");
        }
        this.mAnimation = null;
        if (this.mCanAssumeUdfps) {
            this.mIllustrationLottie.setOnClickListener(new View.OnClickListener() { // from class: com.android.settings.biometrics.fingerprint.FingerprintEnrollFindSensor.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    FingerprintEnrollFindSensor.this.onStartButtonClick(view);
                }
            });
        } else if (this.mCanAssumeSfps) {
        } else {
            View findViewById = findViewById(R.id.fingerprint_sensor_location_animation);
            if (findViewById instanceof FingerprintFindSensorAnimation) {
                this.mAnimation = (FingerprintFindSensorAnimation) findViewById;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$0(int i, int i2, long j) {
        this.mChallenge = j;
        this.mSensorId = i;
        this.mToken = BiometricUtils.requestGatekeeperHat(this, getIntent(), this.mUserId, j);
        getIntent().putExtra("hw_auth_token", this.mToken);
        if (this.mNextClicked) {
            return;
        }
        startLookingForFingerprint();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getRotationFromDefault(int i) {
        return this.mIsReverseDefaultRotation ? (i + 1) % 4 : i;
    }

    private void updateSfpsFindSensorAnimationAsset() {
        this.mScreenSizeFoldProvider.onConfigurationChange(getApplicationContext().getResources().getConfiguration());
        this.mIllustrationLottie = (LottieAnimationView) findViewById(R.id.illustration_lottie);
        int rotationFromDefault = getRotationFromDefault(getApplicationContext().getDisplay().getRotation());
        if (rotationFromDefault != 1) {
            if (rotationFromDefault != 2) {
                if (rotationFromDefault == 3) {
                    if (this.mIsFolded) {
                        this.mIllustrationLottie.setAnimation(R.raw.fingerprint_edu_lottie_folded_bottom_right);
                    } else {
                        this.mIllustrationLottie.setAnimation(R.raw.fingerprint_edu_lottie_portrait_bottom_right);
                    }
                } else if (this.mIsFolded) {
                    this.mIllustrationLottie.setAnimation(R.raw.fingerprint_edu_lottie_folded_top_right);
                } else {
                    this.mIllustrationLottie.setAnimation(R.raw.fingerprint_edu_lottie_landscape_top_right);
                }
            } else if (this.mIsFolded) {
                this.mIllustrationLottie.setAnimation(R.raw.fingerprint_edu_lottie_folded_bottom_left);
            } else {
                this.mIllustrationLottie.setAnimation(R.raw.fingerprint_edu_lottie_landscape_bottom_left);
            }
        } else if (this.mIsFolded) {
            this.mIllustrationLottie.setAnimation(R.raw.fingerprint_edu_lottie_folded_top_left);
        } else {
            this.mIllustrationLottie.setAnimation(R.raw.fingerprint_edu_lottie_portrait_top_left);
        }
        LottieColorUtils.applyDynamicColors(getApplicationContext(), this.mIllustrationLottie);
        this.mIllustrationLottie.setVisibility(0);
        this.mIllustrationLottie.playAnimation();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mScreenSizeFoldProvider.onConfigurationChange(configuration);
    }

    @Override // com.android.settingslib.core.lifecycle.ObservableActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        if (this.mCanAssumeSfps) {
            updateSfpsFindSensorAnimationAsset();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settings.biometrics.BiometricEnrollBase, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("is_next_clicked", this.mNextClicked);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        stopLookingForFingerprint();
        super.onBackPressed();
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper
    protected void onApplyThemeResource(Resources.Theme theme, int i, boolean z) {
        theme.applyStyle(R.style.SetupWizardPartnerResource, true);
        super.onApplyThemeResource(theme, i, z);
    }

    protected int getContentView() {
        if (this.mCanAssumeUdfps) {
            return R.layout.udfps_enroll_find_sensor_layout;
        }
        if (this.mCanAssumeSfps) {
            return R.layout.sfps_enroll_find_sensor_layout;
        }
        return R.layout.fingerprint_enroll_find_sensor;
    }

    @Override // com.android.settingslib.core.lifecycle.ObservableActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        FingerprintFindSensorAnimation fingerprintFindSensorAnimation = this.mAnimation;
        if (fingerprintFindSensorAnimation != null) {
            fingerprintFindSensorAnimation.startAnimation();
        }
    }

    private void stopLookingForFingerprint() {
        FingerprintEnrollSidecar fingerprintEnrollSidecar = this.mSidecar;
        if (fingerprintEnrollSidecar != null) {
            fingerprintEnrollSidecar.setListener(null);
            this.mSidecar.cancelEnrollment();
            getSupportFragmentManager().beginTransaction().remove(this.mSidecar).commitAllowingStateLoss();
            this.mSidecar = null;
        }
    }

    private void startLookingForFingerprint() {
        if (this.mCanAssumeUdfps) {
            return;
        }
        FingerprintEnrollSidecar fingerprintEnrollSidecar = (FingerprintEnrollSidecar) getSupportFragmentManager().findFragmentByTag("sidecar");
        this.mSidecar = fingerprintEnrollSidecar;
        if (fingerprintEnrollSidecar == null) {
            this.mSidecar = new FingerprintEnrollSidecar(this, 1);
            getSupportFragmentManager().beginTransaction().add(this.mSidecar, "sidecar").commitAllowingStateLoss();
        }
        this.mSidecar.setListener(this);
    }

    @Override // com.android.settings.biometrics.BiometricEnrollSidecar.Listener
    public void onEnrollmentProgressChange(int i, int i2) {
        this.mNextClicked = true;
        proceedToEnrolling(true);
    }

    @Override // com.android.settings.biometrics.BiometricEnrollSidecar.Listener
    public void onEnrollmentError(int i, CharSequence charSequence) {
        if (this.mNextClicked && i == 5) {
            proceedToEnrolling(false);
        } else {
            FingerprintErrorDialog.showErrorDialog(this, i, this.mCanAssumeUdfps);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settings.biometrics.BiometricEnrollBase, com.android.settingslib.core.lifecycle.ObservableActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        this.mScreenSizeFoldProvider.unregisterCallback(this);
        FingerprintFindSensorAnimation fingerprintFindSensorAnimation = this.mAnimation;
        if (fingerprintFindSensorAnimation != null) {
            fingerprintFindSensorAnimation.pauseAnimation();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settings.biometrics.BiometricEnrollBase
    public boolean shouldFinishWhenBackgrounded() {
        return super.shouldFinishWhenBackgrounded() && !this.mNextClicked;
    }

    @Override // com.android.settingslib.core.lifecycle.ObservableActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        stopListenOrientationEvent();
        super.onDestroy();
        FingerprintFindSensorAnimation fingerprintFindSensorAnimation = this.mAnimation;
        if (fingerprintFindSensorAnimation != null) {
            fingerprintFindSensorAnimation.stopAnimation();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onStartButtonClick(View view) {
        this.mNextClicked = true;
        startActivityForResult(getFingerprintEnrollingIntent(), 5);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onSkipButtonClick(View view) {
        stopLookingForFingerprint();
        setResult(2);
        finish();
    }

    private void proceedToEnrolling(boolean z) {
        FingerprintEnrollSidecar fingerprintEnrollSidecar = this.mSidecar;
        if (fingerprintEnrollSidecar != null) {
            if (z && fingerprintEnrollSidecar.cancelEnrollment()) {
                return;
            }
            this.mSidecar.setListener(null);
            getSupportFragmentManager().beginTransaction().remove(this.mSidecar).commitAllowingStateLoss();
            this.mSidecar = null;
            startActivityForResult(getFingerprintEnrollingIntent(), 5);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        Log.d("FingerprintEnrollFindSensor", "onActivityResult(requestCode=" + i + ", resultCode=" + i2 + ")");
        boolean booleanExtra = intent != null ? intent.getBooleanExtra("finished_enrolling_fingerprint", false) : false;
        if (i2 == 0 && booleanExtra) {
            setResult(i2, intent);
            finish();
        } else if (i == 4) {
            if (i2 == -1 && intent != null) {
                throw new IllegalStateException("Pretty sure this is dead code");
            }
            finish();
        } else if (i != 5) {
            super.onActivityResult(i, i2, intent);
        } else if (i2 == 1 || i2 == 2 || i2 == 3) {
            setResult(i2);
            finish();
        } else {
            FingerprintManager fingerprintManagerOrNull = Utils.getFingerprintManagerOrNull(this);
            if (fingerprintManagerOrNull.getEnrolledFingerprints().size() >= ((FingerprintSensorPropertiesInternal) fingerprintManagerOrNull.getSensorPropertiesInternal().get(0)).maxEnrollmentsPerUser) {
                finish();
                return;
            }
            this.mNextClicked = false;
            startLookingForFingerprint();
        }
    }

    private void listenOrientationEvent() {
        if (this.mCanAssumeSfps) {
            OrientationEventListener orientationEventListener = new OrientationEventListener(this) { // from class: com.android.settings.biometrics.fingerprint.FingerprintEnrollFindSensor.2
                @Override // android.view.OrientationEventListener
                public void onOrientationChanged(int i) {
                    FingerprintEnrollFindSensor fingerprintEnrollFindSensor = FingerprintEnrollFindSensor.this;
                    int rotationFromDefault = fingerprintEnrollFindSensor.getRotationFromDefault(fingerprintEnrollFindSensor.getDisplay().getRotation());
                    if ((rotationFromDefault + 2) % 4 == FingerprintEnrollFindSensor.this.mPreviousRotation) {
                        FingerprintEnrollFindSensor.this.mPreviousRotation = rotationFromDefault;
                        FingerprintEnrollFindSensor.this.recreate();
                    }
                }
            };
            this.mOrientationEventListener = orientationEventListener;
            orientationEventListener.enable();
            this.mPreviousRotation = getRotationFromDefault(getDisplay().getRotation());
        }
    }

    private void stopListenOrientationEvent() {
        if (this.mCanAssumeSfps) {
            OrientationEventListener orientationEventListener = this.mOrientationEventListener;
            if (orientationEventListener != null) {
                orientationEventListener.disable();
            }
            this.mOrientationEventListener = null;
        }
    }

    @Override // com.android.systemui.unfold.updates.FoldProvider$FoldCallback
    public void onFoldUpdated(boolean z) {
        Log.d("FingerprintEnrollFindSensor", "onFoldUpdated= " + z);
        this.mIsFolded = z;
    }
}
