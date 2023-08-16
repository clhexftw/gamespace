package com.android.settings.biometrics.face;

import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.hardware.face.FaceManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.CompoundButton;
import com.airbnb.lottie.LottieAnimationView;
import com.android.settings.R;
import com.android.settings.Utils;
import com.android.settings.biometrics.BiometricEnrollBase;
import com.android.settings.biometrics.BiometricUtils;
import com.android.systemui.unfold.compat.ScreenSizeFoldProvider;
import com.android.systemui.unfold.updates.FoldProvider$FoldCallback;
import com.google.android.setupcompat.template.FooterBarMixin;
import com.google.android.setupcompat.template.FooterButton;
import com.google.android.setupcompat.util.WizardManagerHelper;
import com.google.android.setupdesign.view.IllustrationVideoView;
/* loaded from: classes.dex */
public class FaceEnrollEducation extends BiometricEnrollBase {
    private boolean mAccessibilityEnabled;
    private FaceManager mFaceManager;
    private View mIllustrationAccessibility;
    private IllustrationVideoView mIllustrationDefault;
    private LottieAnimationView mIllustrationLottie;
    private boolean mIsUsingLottie;
    private Intent mResultIntent;
    private FaceEnrollAccessibilityToggle mSwitchDiversity;
    private final CompoundButton.OnCheckedChangeListener mSwitchDiversityListener = new CompoundButton.OnCheckedChangeListener() { // from class: com.android.settings.biometrics.face.FaceEnrollEducation.1
        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            int i;
            if (z) {
                i = R.string.security_settings_face_enroll_education_message_accessibility;
            } else {
                i = R.string.security_settings_face_enroll_education_message;
            }
            FaceEnrollEducation.this.setDescriptionText(i);
            if (z) {
                FaceEnrollEducation.this.hideDefaultIllustration();
                FaceEnrollEducation.this.mIllustrationAccessibility.setVisibility(0);
                return;
            }
            FaceEnrollEducation.this.showDefaultIllustration();
            FaceEnrollEducation.this.mIllustrationAccessibility.setVisibility(4);
        }
    };

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1506;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settings.biometrics.BiometricEnrollBase, com.android.settings.core.InstrumentedActivity, com.android.settingslib.core.lifecycle.ObservableActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.face_enroll_education);
        setTitle(R.string.security_settings_face_enroll_education_title);
        setDescriptionText(R.string.security_settings_face_enroll_education_message);
        this.mFaceManager = Utils.getFaceManagerOrNull(this);
        this.mIllustrationDefault = (IllustrationVideoView) findViewById(R.id.illustration_default);
        this.mIllustrationLottie = (LottieAnimationView) findViewById(R.id.illustration_lottie);
        this.mIllustrationAccessibility = findViewById(R.id.illustration_accessibility);
        boolean z = getResources().getBoolean(R.bool.config_face_education_use_lottie);
        this.mIsUsingLottie = z;
        boolean z2 = false;
        if (z) {
            this.mIllustrationDefault.stop();
            this.mIllustrationDefault.setVisibility(4);
            this.mIllustrationLottie.setAnimation(R.raw.face_education_lottie);
            this.mIllustrationLottie.setVisibility(0);
            this.mIllustrationLottie.playAnimation();
        }
        this.mFooterBarMixin = (FooterBarMixin) getLayout().getMixin(FooterBarMixin.class);
        if (WizardManagerHelper.isAnySetupWizard(getIntent())) {
            this.mFooterBarMixin.setSecondaryButton(new FooterButton.Builder(this).setText(R.string.skip_label).setListener(new View.OnClickListener() { // from class: com.android.settings.biometrics.face.FaceEnrollEducation$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FaceEnrollEducation.this.onSkipButtonClick(view);
                }
            }).setButtonType(7).setTheme(R.style.SudGlifButton_Secondary).build());
        } else {
            this.mFooterBarMixin.setSecondaryButton(new FooterButton.Builder(this).setText(R.string.security_settings_face_enroll_introduction_cancel).setListener(new View.OnClickListener() { // from class: com.android.settings.biometrics.face.FaceEnrollEducation$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FaceEnrollEducation.this.onSkipButtonClick(view);
                }
            }).setButtonType(2).setTheme(R.style.SudGlifButton_Secondary).build());
        }
        FooterButton build = new FooterButton.Builder(this).setText(R.string.security_settings_face_enroll_education_start).setListener(new View.OnClickListener() { // from class: com.android.settings.biometrics.face.FaceEnrollEducation$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FaceEnrollEducation.this.onNextButtonClick(view);
            }
        }).setButtonType(5).setTheme(R.style.SudGlifButton_Primary).build();
        AccessibilityManager accessibilityManager = (AccessibilityManager) getApplicationContext().getSystemService(AccessibilityManager.class);
        if (accessibilityManager != null) {
            if (accessibilityManager.isEnabled() && accessibilityManager.isTouchExplorationEnabled()) {
                z2 = true;
            }
            this.mAccessibilityEnabled = z2;
        }
        this.mFooterBarMixin.setPrimaryButton(build);
        final Button button = (Button) findViewById(R.id.accessibility_button);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.android.settings.biometrics.face.FaceEnrollEducation$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FaceEnrollEducation.this.lambda$onCreate$0(button, view);
            }
        });
        FaceEnrollAccessibilityToggle faceEnrollAccessibilityToggle = (FaceEnrollAccessibilityToggle) findViewById(R.id.toggle_diversity);
        this.mSwitchDiversity = faceEnrollAccessibilityToggle;
        faceEnrollAccessibilityToggle.setListener(this.mSwitchDiversityListener);
        this.mSwitchDiversity.setOnClickListener(new View.OnClickListener() { // from class: com.android.settings.biometrics.face.FaceEnrollEducation$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FaceEnrollEducation.this.lambda$onCreate$1(view);
            }
        });
        if (this.mAccessibilityEnabled) {
            button.callOnClick();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$0(Button button, View view) {
        this.mSwitchDiversity.setChecked(true);
        button.setVisibility(8);
        this.mSwitchDiversity.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$1(View view) {
        this.mSwitchDiversity.getSwitch().toggle();
    }

    @Override // com.android.settingslib.core.lifecycle.ObservableActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        if (getPostureGuidanceIntent() == null) {
            Log.d("FaceEducation", "Device do not support posture guidance");
            return;
        }
        BiometricUtils.setDevicePosturesAllowEnroll(getResources().getInteger(R.integer.config_face_enroll_supported_posture));
        if (getPostureCallback() == null) {
            this.mFoldCallback = new FoldProvider$FoldCallback() { // from class: com.android.settings.biometrics.face.FaceEnrollEducation$$ExternalSyntheticLambda4
                @Override // com.android.systemui.unfold.updates.FoldProvider$FoldCallback
                public final void onFoldUpdated(boolean z) {
                    FaceEnrollEducation.this.lambda$onStart$2(z);
                }
            };
        }
        if (this.mScreenSizeFoldProvider == null) {
            ScreenSizeFoldProvider screenSizeFoldProvider = new ScreenSizeFoldProvider(getApplicationContext());
            this.mScreenSizeFoldProvider = screenSizeFoldProvider;
            screenSizeFoldProvider.registerCallback(this.mFoldCallback, getMainExecutor());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onStart$2(boolean z) {
        int i = z ? 1 : 3;
        this.mDevicePostureState = i;
        if (!BiometricUtils.shouldShowPostureGuidance(i, this.mLaunchedPostureGuidance) || this.mNextLaunched) {
            return;
        }
        launchPostureGuidance();
    }

    @Override // com.android.settingslib.core.lifecycle.ObservableActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        this.mSwitchDiversityListener.onCheckedChanged(this.mSwitchDiversity.getSwitch(), this.mSwitchDiversity.isChecked());
        if (this.mFaceManager.getEnrolledFaces(this.mUserId).size() >= getResources().getInteger(17694842)) {
            finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settings.biometrics.BiometricEnrollBase
    public boolean shouldFinishWhenBackgrounded() {
        return (!super.shouldFinishWhenBackgrounded() || this.mNextLaunched || BiometricUtils.isPostureGuidanceShowing(this.mDevicePostureState, this.mLaunchedPostureGuidance)) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onNextButtonClick(View view) {
        final Intent intent = new Intent();
        byte[] bArr = this.mToken;
        if (bArr != null) {
            intent.putExtra("hw_auth_token", bArr);
        }
        int i = this.mUserId;
        if (i != -10000) {
            intent.putExtra("android.intent.extra.USER_ID", i);
        }
        intent.putExtra("challenge", this.mChallenge);
        intent.putExtra("sensor_id", this.mSensorId);
        intent.putExtra("from_settings_summary", this.mFromSettingsSummary);
        BiometricUtils.copyMultiBiometricExtras(getIntent(), intent);
        String string = getString(R.string.config_face_enroll);
        if (!TextUtils.isEmpty(string)) {
            intent.setComponent(ComponentName.unflattenFromString(string));
        } else {
            intent.setClass(this, FaceEnrollEnrolling.class);
        }
        WizardManagerHelper.copyWizardManagerExtras(getIntent(), intent);
        Intent intent2 = this.mResultIntent;
        if (intent2 != null) {
            intent.putExtras(intent2);
        }
        intent.putExtra("accessibility_diversity", !this.mSwitchDiversity.isChecked());
        if (!this.mSwitchDiversity.isChecked() && this.mAccessibilityEnabled) {
            FaceEnrollAccessibilityDialog newInstance = FaceEnrollAccessibilityDialog.newInstance();
            newInstance.setPositiveButtonListener(new DialogInterface.OnClickListener() { // from class: com.android.settings.biometrics.face.FaceEnrollEducation$$ExternalSyntheticLambda5
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i2) {
                    FaceEnrollEducation.this.lambda$onNextButtonClick$3(intent, dialogInterface, i2);
                }
            });
            newInstance.show(getSupportFragmentManager(), FaceEnrollAccessibilityDialog.class.getName());
            return;
        }
        startActivityForResult(intent, 2);
        this.mNextLaunched = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onNextButtonClick$3(Intent intent, DialogInterface dialogInterface, int i) {
        startActivityForResult(intent, 2);
        this.mNextLaunched = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onSkipButtonClick(View view) {
        if (BiometricUtils.tryStartingNextBiometricEnroll(this, 6, "edu_skip")) {
            return;
        }
        setResult(2);
        finish();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.mScreenSizeFoldProvider == null || getPostureCallback() == null) {
            return;
        }
        this.mScreenSizeFoldProvider.onConfigurationChange(configuration);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 7) {
            this.mLaunchedPostureGuidance = false;
            if (i2 == 0 || i2 == 2) {
                onSkipButtonClick(getCurrentFocus());
                return;
            }
            return;
        }
        this.mResultIntent = intent;
        boolean booleanExtra = intent != null ? intent.getBooleanExtra("finished_enrolling_face", false) : false;
        if (i2 == 3 || !BiometricUtils.isPostureAllowEnrollment(this.mDevicePostureState)) {
            setResult(i2, intent);
            finish();
        } else if ((i == 2 || i == 6) && (i2 == 2 || i2 == 1 || i2 == 11 || booleanExtra)) {
            setResult(i2, intent);
            finish();
        }
        this.mNextLaunched = false;
        super.onActivityResult(i, i2, intent);
    }

    protected Intent getPostureGuidanceIntent() {
        return this.mPostureGuidanceIntent;
    }

    protected FoldProvider$FoldCallback getPostureCallback() {
        return this.mFoldCallback;
    }

    protected int getDevicePostureState() {
        return this.mDevicePostureState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideDefaultIllustration() {
        if (this.mIsUsingLottie) {
            this.mIllustrationLottie.cancelAnimation();
            this.mIllustrationLottie.setVisibility(4);
            return;
        }
        this.mIllustrationDefault.stop();
        this.mIllustrationDefault.setVisibility(4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showDefaultIllustration() {
        if (this.mIsUsingLottie) {
            this.mIllustrationLottie.setAnimation(R.raw.face_education_lottie);
            this.mIllustrationLottie.setVisibility(0);
            this.mIllustrationLottie.playAnimation();
            this.mIllustrationLottie.setProgress(0.0f);
            return;
        }
        this.mIllustrationDefault.setVisibility(0);
        this.mIllustrationDefault.start();
    }
}
