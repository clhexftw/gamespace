package com.android.settings.biometrics.face;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.res.Configuration;
import android.hardware.SensorPrivacyManager;
import android.hardware.face.FaceManager;
import android.os.Bundle;
import android.os.UserHandle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.settings.R;
import com.android.settings.Settings;
import com.android.settings.Utils;
import com.android.settings.biometrics.BiometricEnrollIntroduction;
import com.android.settings.biometrics.BiometricUtils;
import com.android.settings.custom.biometrics.FaceUtils;
import com.android.settings.utils.SensorPrivacyManagerHelper;
import com.android.settingslib.RestrictedLockUtilsInternal;
import com.android.systemui.unfold.compat.ScreenSizeFoldProvider;
import com.android.systemui.unfold.updates.FoldProvider$FoldCallback;
import com.google.android.setupcompat.template.FooterBarMixin;
import com.google.android.setupcompat.template.FooterButton;
import com.google.android.setupcompat.util.WizardManagerHelper;
import com.google.android.setupdesign.span.LinkSpan;
import java.util.Objects;
import java.util.function.Supplier;
/* loaded from: classes.dex */
public class FaceEnrollIntroduction extends BiometricEnrollIntroduction {
    private FaceManager mFaceManager;
    private boolean mForRedo;
    private FooterButton mPrimaryFooterButton;
    private FooterButton mSecondaryFooterButton;
    private SensorPrivacyManager mSensorPrivacyManager;

    protected boolean generateChallengeOnCreate() {
        return true;
    }

    @Override // com.android.settings.biometrics.BiometricEnrollIntroduction
    protected String getExtraKeyForBiometric() {
        return "for_face";
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1506;
    }

    @Override // com.android.settings.biometrics.BiometricEnrollIntroduction
    public int getModality() {
        return 8;
    }

    @Override // com.google.android.setupdesign.span.LinkSpan.OnClickListener
    public void onClick(LinkSpan linkSpan) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settings.biometrics.BiometricEnrollIntroduction
    public void onSkipButtonClick(View view) {
        if (BiometricUtils.tryStartingNextBiometricEnroll(this, 6, "skip")) {
            return;
        }
        super.onSkipButtonClick(view);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settings.biometrics.BiometricEnrollIntroduction
    public void onEnrollmentSkipped(Intent intent) {
        if (BiometricUtils.tryStartingNextBiometricEnroll(this, 6, "skipped")) {
            return;
        }
        super.onEnrollmentSkipped(intent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settings.biometrics.BiometricEnrollIntroduction
    public void onFinishedEnrolling(Intent intent) {
        if (BiometricUtils.tryStartingNextBiometricEnroll(this, 6, "finished")) {
            return;
        }
        super.onFinishedEnrolling(intent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settings.biometrics.BiometricEnrollIntroduction, com.android.settings.biometrics.BiometricEnrollBase
    public boolean shouldFinishWhenBackgrounded() {
        return super.shouldFinishWhenBackgrounded() && !BiometricUtils.isPostureGuidanceShowing(this.mDevicePostureState, this.mLaunchedPostureGuidance);
    }

    private void initDefaultLayout() {
        ((ImageView) findViewById(R.id.icon_glasses)).getBackground().setColorFilter(getIconColorFilter());
        ((ImageView) findViewById(R.id.icon_looking)).getBackground().setColorFilter(getIconColorFilter());
        TextView textView = (TextView) findViewById(R.id.message_in_control);
        ((TextView) findViewById(R.id.info_message_glasses)).setText(getInfoMessageGlasses());
        ((TextView) findViewById(R.id.info_message_looking)).setText(getInfoMessageLooking());
        ((TextView) findViewById(R.id.title_in_control)).setText(getInControlTitle());
        ((TextView) findViewById(R.id.how_message)).setText(getHowMessage());
        textView.setText(Html.fromHtml(getString(getInControlMessage()), 0));
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        ((TextView) findViewById(R.id.info_message_less_secure)).setText(getLessSecureMessage());
        if (getResources().getBoolean(R.bool.config_face_intro_show_less_secure)) {
            ((LinearLayout) findViewById(R.id.info_row_less_secure)).setVisibility(0);
            ((ImageView) findViewById(R.id.icon_less_secure)).getBackground().setColorFilter(getIconColorFilter());
        }
        if (getResources().getBoolean(R.bool.config_face_intro_show_require_eyes)) {
            ((LinearLayout) findViewById(R.id.info_row_require_eyes)).setVisibility(0);
            ((ImageView) findViewById(R.id.icon_require_eyes)).getBackground().setColorFilter(getIconColorFilter());
            ((TextView) findViewById(R.id.info_message_require_eyes)).setText(getInfoMessageRequireEyes());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settings.biometrics.BiometricEnrollIntroduction, com.android.settings.biometrics.BiometricEnrollBase, com.android.settings.core.InstrumentedActivity, com.android.settingslib.core.lifecycle.ObservableActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        this.mFaceManager = getFaceManager();
        if (bundle == null && !WizardManagerHelper.isAnySetupWizard(getIntent()) && !getIntent().getBooleanExtra("from_settings_summary", false) && maxFacesEnrolled()) {
            Log.d("FaceEnrollIntroduction", "launch face settings");
            launchFaceSettingsActivity();
            finish();
        }
        super.onCreate(bundle);
        if (isFinishing()) {
            return;
        }
        if (!FaceUtils.isFaceUnlockSupported()) {
            initDefaultLayout();
        }
        this.mForRedo = getIntent().getBooleanExtra("for_redo", false);
        if (this.mToken == null && BiometricUtils.containsGatekeeperPasswordHandle(getIntent()) && generateChallengeOnCreate()) {
            this.mFooterBarMixin.getPrimaryButton().setEnabled(false);
            this.mFaceManager.generateChallenge(this.mUserId, new FaceManager.GenerateChallengeCallback() { // from class: com.android.settings.biometrics.face.FaceEnrollIntroduction$$ExternalSyntheticLambda0
                public final void onGenerateChallengeResult(int i, int i2, long j) {
                    FaceEnrollIntroduction.this.lambda$onCreate$0(i, i2, j);
                }
            });
        }
        this.mSensorPrivacyManager = (SensorPrivacyManager) getApplicationContext().getSystemService(SensorPrivacyManager.class);
        boolean isSensorBlocked = SensorPrivacyManagerHelper.getInstance(getApplicationContext()).isSensorBlocked(2, this.mUserId);
        Log.v("FaceEnrollIntroduction", "cameraPrivacyEnabled : " + isSensorBlocked);
        if (FaceUtils.isFaceUnlockSupported() && this.mHasPassword && this.mToken != null) {
            openCustomFaceUnlockPackage();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$0(int i, int i2, long j) {
        if (isFinishing()) {
            Log.w("FaceEnrollIntroduction", "activity finished before challenge callback launched.");
            return;
        }
        try {
            this.mToken = requestGatekeeperHat(j);
            this.mSensorId = i;
            this.mChallenge = j;
            this.mFooterBarMixin.getPrimaryButton().setEnabled(true);
        } catch (BiometricUtils.GatekeeperCredentialNotMatchException unused) {
            getIntent().removeExtra("gk_pw_handle");
            recreate();
        }
    }

    private void launchFaceSettingsActivity() {
        Intent intent = new Intent(this, Settings.FaceSettingsInternalActivity.class);
        byte[] byteArrayExtra = getIntent().getByteArrayExtra("hw_auth_token");
        if (byteArrayExtra != null) {
            intent.putExtra("hw_auth_token", byteArrayExtra);
        }
        int intExtra = getIntent().getIntExtra("android.intent.extra.USER_ID", UserHandle.myUserId());
        if (intExtra != -10000) {
            intent.putExtra("android.intent.extra.USER_ID", intExtra);
        }
        BiometricUtils.copyMultiBiometricExtras(getIntent(), intent);
        intent.putExtra("from_settings_summary", true);
        intent.putExtra("challenge", getIntent().getLongExtra("challenge", -1L));
        intent.putExtra("sensor_id", getIntent().getIntExtra("sensor_id", -1));
        startActivity(intent);
    }

    protected FaceManager getFaceManager() {
        return Utils.getFaceManagerOrNull(this);
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

    protected byte[] requestGatekeeperHat(long j) {
        return BiometricUtils.requestGatekeeperHat(this, getIntent(), this.mUserId, j);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.mScreenSizeFoldProvider == null || getPostureCallback() == null) {
            return;
        }
        this.mScreenSizeFoldProvider.onConfigurationChange(configuration);
    }

    @Override // com.android.settingslib.core.lifecycle.ObservableActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        listenFoldEventForPostureGuidance();
    }

    private void listenFoldEventForPostureGuidance() {
        if (maxFacesEnrolled()) {
            Log.d("FaceEnrollIntroduction", "Device has enrolled face, do not show posture guidance");
        } else if (getPostureGuidanceIntent() == null) {
            Log.d("FaceEnrollIntroduction", "Device do not support posture guidance");
        } else {
            BiometricUtils.setDevicePosturesAllowEnroll(getResources().getInteger(R.integer.config_face_enroll_supported_posture));
            if (getPostureCallback() == null) {
                this.mFoldCallback = new FoldProvider$FoldCallback() { // from class: com.android.settings.biometrics.face.FaceEnrollIntroduction$$ExternalSyntheticLambda2
                    @Override // com.android.systemui.unfold.updates.FoldProvider$FoldCallback
                    public final void onFoldUpdated(boolean z) {
                        FaceEnrollIntroduction.this.lambda$listenFoldEventForPostureGuidance$1(z);
                    }
                };
            }
            if (this.mScreenSizeFoldProvider == null) {
                ScreenSizeFoldProvider screenSizeFoldProvider = new ScreenSizeFoldProvider(getApplicationContext());
                this.mScreenSizeFoldProvider = screenSizeFoldProvider;
                screenSizeFoldProvider.registerCallback(this.mFoldCallback, getMainExecutor());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$listenFoldEventForPostureGuidance$1(boolean z) {
        int i = z ? 1 : 3;
        this.mDevicePostureState = i;
        if (!BiometricUtils.shouldShowPostureGuidance(i, this.mLaunchedPostureGuidance) || this.mNextLaunched) {
            return;
        }
        launchPostureGuidance();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settings.biometrics.BiometricEnrollIntroduction, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        if (FaceUtils.isFaceUnlockSupported()) {
            onActivityResultCustom(i, i2, intent);
            return;
        }
        if (i == 7) {
            this.mLaunchedPostureGuidance = false;
            if (i2 == 0 || i2 == 2) {
                onSkipButtonClick(getCurrentFocus());
                return;
            }
            return;
        }
        boolean z = true;
        boolean z2 = i == 2 || i == 6;
        if (i2 != 2 && i2 != 11 && i2 != 1) {
            z = false;
        }
        boolean booleanExtra = intent != null ? intent.getBooleanExtra("finished_enrolling_face", false) : false;
        if (i2 == 0 && (booleanExtra || !BiometricUtils.isPostureAllowEnrollment(this.mDevicePostureState))) {
            setResult(i2, intent);
            finish();
            return;
        }
        if ((z2 && z) || booleanExtra) {
            intent = setSkipPendingEnroll(intent);
        }
        super.onActivityResult(i, i2, intent);
    }

    void onActivityResultCustom(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1) {
            if (i2 == 1) {
                checkTokenAndOpenCustomFaceUnlockPackage(intent);
            }
        } else if (i == 4) {
            if (i2 != -1 || intent == null) {
                return;
            }
            checkTokenAndOpenCustomFaceUnlockPackage(intent);
        } else if (i == 5) {
            if (i2 == 1 || i2 == -1) {
                setResult(1);
                finish();
                return;
            }
            setResult(0);
            finish();
        }
    }

    private void openCustomFaceUnlockPackage() {
        ComponentName componentName;
        Intent intent = new Intent();
        intent.putExtra("hw_auth_token", this.mToken);
        int i = this.mUserId;
        if (i != -10000) {
            intent.putExtra("android.intent.extra.USER_ID", i);
        }
        if (this.mForRedo) {
            componentName = new ComponentName("org.pixelexperience.faceunlock", "org.pixelexperience.faceunlock.FaceEnrollActivity");
        } else {
            componentName = new ComponentName("org.pixelexperience.faceunlock", "org.pixelexperience.faceunlock.SetupFaceIntroActivity");
        }
        intent.setComponent(componentName);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 5);
        }
    }

    private void checkTokenAndOpenCustomFaceUnlockPackage(final Intent intent) {
        if (this.mToken == null) {
            this.mFaceManager.generateChallenge(this.mUserId, new FaceManager.GenerateChallengeCallback() { // from class: com.android.settings.biometrics.face.FaceEnrollIntroduction$$ExternalSyntheticLambda3
                public final void onGenerateChallengeResult(int i, int i2, long j) {
                    FaceEnrollIntroduction.this.lambda$checkTokenAndOpenCustomFaceUnlockPackage$2(intent, i, i2, j);
                }
            });
        } else {
            openCustomFaceUnlockPackage();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$checkTokenAndOpenCustomFaceUnlockPackage$2(Intent intent, int i, int i2, long j) {
        if (this.mToken == null) {
            this.mToken = BiometricUtils.requestGatekeeperHat(this, intent, this.mUserId, j);
            this.mSensorId = i;
            this.mChallenge = j;
            BiometricUtils.removeGatekeeperPasswordHandle(this, intent);
            openCustomFaceUnlockPackage();
        }
    }

    protected int getInfoMessageGlasses() {
        return R.string.security_settings_face_enroll_introduction_info_glasses;
    }

    protected int getInfoMessageLooking() {
        return R.string.security_settings_face_enroll_introduction_info_looking;
    }

    protected int getInfoMessageRequireEyes() {
        return R.string.security_settings_face_enroll_introduction_info_gaze;
    }

    protected int getHowMessage() {
        return R.string.security_settings_face_enroll_introduction_how_message;
    }

    protected int getInControlTitle() {
        return R.string.security_settings_face_enroll_introduction_control_title;
    }

    protected int getInControlMessage() {
        return R.string.security_settings_face_enroll_introduction_control_message;
    }

    protected int getLessSecureMessage() {
        return R.string.security_settings_face_enroll_introduction_info_less_secure;
    }

    @Override // com.android.settings.biometrics.BiometricEnrollIntroduction
    protected boolean isDisabledByAdmin() {
        return RestrictedLockUtilsInternal.checkIfKeyguardFeaturesDisabled(this, 128, this.mUserId) != null;
    }

    @Override // com.android.settings.biometrics.BiometricEnrollIntroduction
    protected int getLayoutResource() {
        if (FaceUtils.isFaceUnlockSupported()) {
            return R.layout.face_enroll_introduction_invisible;
        }
        return R.layout.face_enroll_introduction;
    }

    @Override // com.android.settings.biometrics.BiometricEnrollIntroduction
    protected int getHeaderResDisabledByAdmin() {
        return R.string.security_settings_face_enroll_introduction_title_unlock_disabled;
    }

    @Override // com.android.settings.biometrics.BiometricEnrollIntroduction
    protected int getHeaderResDefault() {
        return R.string.security_settings_face_enroll_introduction_title;
    }

    @Override // com.android.settings.biometrics.BiometricEnrollIntroduction
    protected String getDescriptionDisabledByAdmin() {
        return ((DevicePolicyManager) getSystemService(DevicePolicyManager.class)).getResources().getString("Settings.FACE_UNLOCK_DISABLED", new Supplier() { // from class: com.android.settings.biometrics.face.FaceEnrollIntroduction$$ExternalSyntheticLambda6
            @Override // java.util.function.Supplier
            public final Object get() {
                String lambda$getDescriptionDisabledByAdmin$3;
                lambda$getDescriptionDisabledByAdmin$3 = FaceEnrollIntroduction.this.lambda$getDescriptionDisabledByAdmin$3();
                return lambda$getDescriptionDisabledByAdmin$3;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ String lambda$getDescriptionDisabledByAdmin$3() {
        return getString(R.string.security_settings_face_enroll_introduction_message_unlock_disabled);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settings.biometrics.BiometricEnrollIntroduction, com.android.settings.biometrics.BiometricEnrollBase
    public FooterButton getNextButton() {
        FooterBarMixin footerBarMixin = this.mFooterBarMixin;
        if (footerBarMixin != null) {
            return footerBarMixin.getPrimaryButton();
        }
        return null;
    }

    @Override // com.android.settings.biometrics.BiometricEnrollIntroduction
    protected TextView getErrorTextView() {
        return (TextView) findViewById(R.id.error_text);
    }

    private boolean maxFacesEnrolled() {
        FaceManager faceManager = this.mFaceManager;
        return faceManager != null && faceManager.getEnrolledFaces(this.mUserId).size() >= getApplicationContext().getResources().getInteger(R.integer.suw_max_faces_enrollable);
    }

    @Override // com.android.settings.biometrics.BiometricEnrollIntroduction
    protected int checkMaxEnrolled() {
        if (this.mFaceManager != null) {
            if (maxFacesEnrolled()) {
                return R.string.face_intro_error_max;
            }
            return 0;
        }
        return R.string.face_intro_error_unknown;
    }

    @Override // com.android.settings.biometrics.BiometricEnrollIntroduction
    protected void getChallenge(final BiometricEnrollIntroduction.GenerateChallengeCallback generateChallengeCallback) {
        FaceManager faceManagerOrNull = Utils.getFaceManagerOrNull(this);
        this.mFaceManager = faceManagerOrNull;
        if (faceManagerOrNull == null) {
            generateChallengeCallback.onChallengeGenerated(0, 0, 0L);
            return;
        }
        int i = this.mUserId;
        Objects.requireNonNull(generateChallengeCallback);
        faceManagerOrNull.generateChallenge(i, new FaceManager.GenerateChallengeCallback() { // from class: com.android.settings.biometrics.face.FaceEnrollIntroduction$$ExternalSyntheticLambda5
            public final void onGenerateChallengeResult(int i2, int i3, long j) {
                BiometricEnrollIntroduction.GenerateChallengeCallback.this.onChallengeGenerated(i2, i3, j);
            }
        });
    }

    @Override // com.android.settings.biometrics.BiometricEnrollIntroduction
    protected Intent getEnrollingIntent() {
        Intent intent = new Intent(this, FaceEnrollEducation.class);
        WizardManagerHelper.copyWizardManagerExtras(getIntent(), intent);
        return intent;
    }

    @Override // com.android.settings.biometrics.BiometricEnrollIntroduction
    protected int getConfirmLockTitleResId() {
        return R.string.security_settings_face_preference_title;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settings.biometrics.BiometricEnrollIntroduction
    public void onNextButtonClick(View view) {
        boolean z = false;
        boolean booleanExtra = getIntent().getBooleanExtra("require_consent", false);
        boolean isSensorBlocked = SensorPrivacyManagerHelper.getInstance(getApplicationContext()).isSensorBlocked(2, this.mUserId);
        if (WizardManagerHelper.isAnySetupWizard(getIntent()) || (booleanExtra && !WizardManagerHelper.isUserSetupComplete(this))) {
            z = true;
        }
        if (isSensorBlocked && !z) {
            if (this.mSensorPrivacyManager == null) {
                this.mSensorPrivacyManager = (SensorPrivacyManager) getApplicationContext().getSystemService(SensorPrivacyManager.class);
            }
            this.mSensorPrivacyManager.showSensorUseDialog(2);
            return;
        }
        super.onNextButtonClick(view);
    }

    @Override // com.android.settings.biometrics.BiometricEnrollIntroduction
    protected FooterButton getPrimaryFooterButton() {
        if (this.mPrimaryFooterButton == null) {
            this.mPrimaryFooterButton = new FooterButton.Builder(this).setText(R.string.security_settings_face_enroll_introduction_agree).setButtonType(6).setListener(new View.OnClickListener() { // from class: com.android.settings.biometrics.face.FaceEnrollIntroduction$$ExternalSyntheticLambda4
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FaceEnrollIntroduction.this.onNextButtonClick(view);
                }
            }).setTheme(R.style.SudGlifButton_Primary).build();
        }
        return this.mPrimaryFooterButton;
    }

    @Override // com.android.settings.biometrics.BiometricEnrollIntroduction
    protected FooterButton getSecondaryFooterButton() {
        if (this.mSecondaryFooterButton == null) {
            this.mSecondaryFooterButton = new FooterButton.Builder(this).setText(R.string.security_settings_face_enroll_introduction_no_thanks).setListener(new View.OnClickListener() { // from class: com.android.settings.biometrics.face.FaceEnrollIntroduction$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FaceEnrollIntroduction.this.onSkipButtonClick(view);
                }
            }).setButtonType(5).setTheme(R.style.SudGlifButton_Primary).build();
        }
        return this.mSecondaryFooterButton;
    }

    @Override // com.android.settings.biometrics.BiometricEnrollIntroduction
    protected int getAgreeButtonTextRes() {
        return R.string.security_settings_fingerprint_enroll_introduction_agree;
    }

    @Override // com.android.settings.biometrics.BiometricEnrollIntroduction
    protected int getMoreButtonTextRes() {
        return R.string.security_settings_face_enroll_introduction_more;
    }

    protected static Intent setSkipPendingEnroll(Intent intent) {
        if (intent == null) {
            intent = new Intent();
        }
        intent.putExtra("skip_pending_enroll", true);
        return intent;
    }
}
