package com.android.settings.biometrics;

import android.app.PendingIntent;
import android.content.Intent;
import android.hardware.face.FaceManager;
import android.hardware.fingerprint.FingerprintManager;
import androidx.fragment.app.FragmentActivity;
import com.android.internal.annotations.VisibleForTesting;
import java.util.function.Function;
/* loaded from: classes.dex */
public class MultiBiometricEnrollHelper {
    private final FragmentActivity mActivity;
    private final Intent mFaceEnrollIntroductionIntent;
    private final FaceManager mFaceManager;
    private final Intent mFingerprintEnrollIntroductionIntent;
    private final FingerprintManager mFingerprintManager;
    private Function<Long, byte[]> mGatekeeperHatSupplier;
    private final long mGkPwHandle;
    private final boolean mRequestEnrollFace;
    private final boolean mRequestEnrollFingerprint;
    private final int mUserId;

    @VisibleForTesting
    MultiBiometricEnrollHelper(FragmentActivity fragmentActivity, int i, boolean z, boolean z2, long j, FingerprintManager fingerprintManager, FaceManager faceManager, Intent intent, Intent intent2, Function<Long, byte[]> function) {
        this.mActivity = fragmentActivity;
        this.mUserId = i;
        this.mGkPwHandle = j;
        this.mRequestEnrollFace = z;
        this.mRequestEnrollFingerprint = z2;
        this.mFingerprintManager = fingerprintManager;
        this.mFaceManager = faceManager;
        this.mFingerprintEnrollIntroductionIntent = intent;
        this.mFaceEnrollIntroductionIntent = intent2;
        this.mGatekeeperHatSupplier = function;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MultiBiometricEnrollHelper(final FragmentActivity fragmentActivity, final int i, boolean z, boolean z2, final long j) {
        this(fragmentActivity, i, z, z2, j, (FingerprintManager) fragmentActivity.getSystemService(FingerprintManager.class), (FaceManager) fragmentActivity.getSystemService(FaceManager.class), BiometricUtils.getFingerprintIntroIntent(fragmentActivity, fragmentActivity.getIntent()), BiometricUtils.getFaceIntroIntent(fragmentActivity, fragmentActivity.getIntent()), new Function() { // from class: com.android.settings.biometrics.MultiBiometricEnrollHelper$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                byte[] lambda$new$0;
                lambda$new$0 = MultiBiometricEnrollHelper.lambda$new$0(FragmentActivity.this, j, i, (Long) obj);
                return lambda$new$0;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ byte[] lambda$new$0(FragmentActivity fragmentActivity, long j, int i, Long l) {
        return BiometricUtils.requestGatekeeperHat(fragmentActivity, j, i, l.longValue());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void startNextStep() {
        if (this.mRequestEnrollFingerprint) {
            launchFingerprintEnroll();
        } else if (this.mRequestEnrollFace) {
            launchFaceEnroll();
        } else {
            this.mActivity.setResult(2);
            this.mActivity.finish();
        }
    }

    private void launchFaceEnroll() {
        this.mFaceManager.generateChallenge(this.mUserId, new FaceManager.GenerateChallengeCallback() { // from class: com.android.settings.biometrics.MultiBiometricEnrollHelper$$ExternalSyntheticLambda2
            public final void onGenerateChallengeResult(int i, int i2, long j) {
                MultiBiometricEnrollHelper.this.lambda$launchFaceEnroll$1(i, i2, j);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$launchFaceEnroll$1(int i, int i2, long j) {
        byte[] apply = this.mGatekeeperHatSupplier.apply(Long.valueOf(j));
        this.mFaceEnrollIntroductionIntent.putExtra("sensor_id", i);
        this.mFaceEnrollIntroductionIntent.putExtra("challenge", j);
        BiometricUtils.launchEnrollForResult(this.mActivity, this.mFaceEnrollIntroductionIntent, 3000, apply, Long.valueOf(this.mGkPwHandle), this.mUserId);
    }

    private void launchFingerprintEnroll() {
        this.mFingerprintManager.generateChallenge(this.mUserId, new FingerprintManager.GenerateChallengeCallback() { // from class: com.android.settings.biometrics.MultiBiometricEnrollHelper$$ExternalSyntheticLambda1
            public final void onChallengeGenerated(int i, int i2, long j) {
                MultiBiometricEnrollHelper.this.lambda$launchFingerprintEnroll$2(i, i2, j);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$launchFingerprintEnroll$2(int i, int i2, long j) {
        byte[] apply = this.mGatekeeperHatSupplier.apply(Long.valueOf(j));
        this.mFingerprintEnrollIntroductionIntent.putExtra("sensor_id", i);
        this.mFingerprintEnrollIntroductionIntent.putExtra("challenge", j);
        if (this.mRequestEnrollFace) {
            this.mFaceEnrollIntroductionIntent.putExtra("android.intent.extra.USER_ID", this.mUserId);
            this.mFaceEnrollIntroductionIntent.putExtra("gk_pw_handle", this.mGkPwHandle);
            this.mFingerprintEnrollIntroductionIntent.putExtra("enroll_after_finger", PendingIntent.getActivity(this.mActivity, 0, this.mFaceEnrollIntroductionIntent, 201326592));
        }
        BiometricUtils.launchEnrollForResult(this.mActivity, this.mFingerprintEnrollIntroductionIntent, 3001, apply, Long.valueOf(this.mGkPwHandle), this.mUserId);
    }
}
