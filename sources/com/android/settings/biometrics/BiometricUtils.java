package com.android.settings.biometrics;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.hardware.face.FaceManager;
import android.hardware.face.FaceSensorPropertiesInternal;
import android.os.storage.StorageManager;
import android.util.Log;
import androidx.fragment.app.FragmentActivity;
import com.android.internal.widget.LockPatternUtils;
import com.android.internal.widget.VerifyCredentialResponse;
import com.android.settings.SetupWizardUtils;
import com.android.settings.biometrics.BiometricEnrollActivity;
import com.android.settings.biometrics.face.FaceEnrollIntroduction;
import com.android.settings.biometrics.fingerprint.FingerprintEnrollFindSensor;
import com.android.settings.biometrics.fingerprint.FingerprintEnrollIntroduction;
import com.android.settings.biometrics.fingerprint.SetupFingerprintEnrollFindSensor;
import com.android.settings.biometrics.fingerprint.SetupFingerprintEnrollIntroduction;
import com.android.settings.password.ChooseLockGeneric;
import com.android.settings.password.SetupChooseLockGeneric;
import com.google.android.setupcompat.util.WizardManagerHelper;
/* loaded from: classes.dex */
public class BiometricUtils {
    public static int REQUEST_ADD_ANOTHER = 7;
    public static int sAllowEnrollPosture;

    /* loaded from: classes.dex */
    public static class GatekeeperCredentialNotMatchException extends IllegalStateException {
        public GatekeeperCredentialNotMatchException(String str) {
            super(str);
        }
    }

    public static byte[] requestGatekeeperHat(Context context, Intent intent, int i, long j) {
        if (!containsGatekeeperPasswordHandle(intent)) {
            throw new IllegalStateException("Gatekeeper Password is missing!!");
        }
        return requestGatekeeperHat(context, intent.getLongExtra("gk_pw_handle", 0L), i, j);
    }

    public static byte[] requestGatekeeperHat(Context context, long j, int i, long j2) {
        VerifyCredentialResponse verifyGatekeeperPasswordHandle = new LockPatternUtils(context).verifyGatekeeperPasswordHandle(j, j2, i);
        if (!verifyGatekeeperPasswordHandle.isMatched()) {
            throw new GatekeeperCredentialNotMatchException("Unable to request Gatekeeper HAT");
        }
        return verifyGatekeeperPasswordHandle.getGatekeeperHAT();
    }

    public static boolean containsGatekeeperPasswordHandle(Intent intent) {
        return intent != null && intent.hasExtra("gk_pw_handle");
    }

    public static long getGatekeeperPasswordHandle(Intent intent) {
        return intent.getLongExtra("gk_pw_handle", 0L);
    }

    public static void removeGatekeeperPasswordHandle(Context context, Intent intent) {
        if (intent != null && containsGatekeeperPasswordHandle(intent)) {
            removeGatekeeperPasswordHandle(context, getGatekeeperPasswordHandle(intent));
        }
    }

    public static void removeGatekeeperPasswordHandle(Context context, long j) {
        new LockPatternUtils(context).removeGatekeeperPasswordHandle(j);
        Log.d("BiometricUtils", "Removed handle");
    }

    public static Intent getChooseLockIntent(Context context, Intent intent) {
        if (WizardManagerHelper.isAnySetupWizard(intent)) {
            Intent intent2 = new Intent(context, SetupChooseLockGeneric.class);
            if (StorageManager.isFileEncryptedNativeOrEmulated()) {
                intent2.putExtra("lockscreen.password_type", 131072);
                intent2.putExtra("show_options_button", true);
            }
            WizardManagerHelper.copyWizardManagerExtras(intent, intent2);
            return intent2;
        }
        return new Intent(context, ChooseLockGeneric.class);
    }

    public static Intent getFingerprintFindSensorIntent(Context context, Intent intent) {
        if (WizardManagerHelper.isAnySetupWizard(intent)) {
            Intent intent2 = new Intent(context, SetupFingerprintEnrollFindSensor.class);
            SetupWizardUtils.copySetupExtras(intent, intent2);
            return intent2;
        }
        return new Intent(context, FingerprintEnrollFindSensor.class);
    }

    public static Intent getFingerprintIntroIntent(Context context, Intent intent) {
        if (WizardManagerHelper.isAnySetupWizard(intent)) {
            Intent intent2 = new Intent(context, SetupFingerprintEnrollIntroduction.class);
            WizardManagerHelper.copyWizardManagerExtras(intent, intent2);
            return intent2;
        }
        return new Intent(context, FingerprintEnrollIntroduction.class);
    }

    public static Intent getFaceIntroIntent(Context context, Intent intent) {
        Intent intent2 = new Intent(context, FaceEnrollIntroduction.class);
        WizardManagerHelper.copyWizardManagerExtras(intent, intent2);
        return intent2;
    }

    public static Intent getHandoffToParentIntent(Context context, Intent intent) {
        Intent intent2 = new Intent(context, BiometricHandoffActivity.class);
        WizardManagerHelper.copyWizardManagerExtras(intent, intent2);
        return intent2;
    }

    public static void launchEnrollForResult(FragmentActivity fragmentActivity, Intent intent, int i, byte[] bArr, Long l, int i2) {
        if (bArr != null) {
            intent.putExtra("hw_auth_token", bArr);
        }
        if (l != null) {
            intent.putExtra("gk_pw_handle", l.longValue());
        }
        if (fragmentActivity instanceof BiometricEnrollActivity.InternalActivity) {
            intent.putExtra("android.intent.extra.USER_ID", i2);
        }
        if (i != 0) {
            fragmentActivity.startActivityForResult(intent, i);
            return;
        }
        fragmentActivity.startActivity(intent);
        fragmentActivity.finish();
    }

    public static boolean isMultiBiometricFaceEnrollmentFlow(Activity activity) {
        return activity.getIntent().hasExtra("enroll_after_face");
    }

    public static boolean isMultiBiometricFingerprintEnrollmentFlow(Activity activity) {
        return activity.getIntent().hasExtra("enroll_after_finger");
    }

    public static boolean isAnyMultiBiometricFlow(Activity activity) {
        return isMultiBiometricFaceEnrollmentFlow(activity) || isMultiBiometricFingerprintEnrollmentFlow(activity);
    }

    public static boolean isPostureGuidanceShowing(int i, boolean z) {
        return !isPostureAllowEnrollment(i) && z;
    }

    public static boolean isPostureAllowEnrollment(int i) {
        int i2 = sAllowEnrollPosture;
        return i2 == 0 || i == i2;
    }

    public static boolean shouldShowPostureGuidance(int i, boolean z) {
        return (isPostureAllowEnrollment(i) || z) ? false : true;
    }

    public static void setDevicePosturesAllowEnroll(int i) {
        sAllowEnrollPosture = i;
    }

    public static void copyMultiBiometricExtras(Intent intent, Intent intent2) {
        PendingIntent pendingIntent = (PendingIntent) intent.getExtra("enroll_after_face", null);
        if (pendingIntent != null) {
            intent2.putExtra("enroll_after_face", pendingIntent);
        }
        PendingIntent pendingIntent2 = (PendingIntent) intent.getExtra("enroll_after_finger", null);
        if (pendingIntent2 != null) {
            intent2.putExtra("enroll_after_finger", pendingIntent2);
        }
    }

    public static boolean tryStartingNextBiometricEnroll(Activity activity, int i, String str) {
        PendingIntent pendingIntent = (PendingIntent) activity.getIntent().getExtra("enroll_after_face");
        if (pendingIntent == null) {
            pendingIntent = (PendingIntent) activity.getIntent().getExtra("enroll_after_finger");
        }
        if (pendingIntent != null) {
            try {
                activity.startIntentSenderForResult(pendingIntent.getIntentSender(), i, null, 0, 0, 0);
                return true;
            } catch (IntentSender.SendIntentException e) {
                Log.e("BiometricUtils", "Pending intent canceled: " + e);
                return false;
            }
        }
        return false;
    }

    public static boolean isReverseLandscape(Context context) {
        return context.getDisplay().getRotation() == 3;
    }

    public static boolean isConvenience(FaceManager faceManager) {
        for (FaceSensorPropertiesInternal faceSensorPropertiesInternal : faceManager.getSensorPropertiesInternal()) {
            if (faceSensorPropertiesInternal.sensorStrength == 0) {
                return true;
            }
        }
        return false;
    }

    public static boolean isLandscape(Context context) {
        return context.getDisplay().getRotation() == 1;
    }
}
