package com.android.settings.biometrics.fingerprint;

import android.app.Activity;
import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.os.SystemClock;
import android.util.Log;
import com.android.internal.annotations.VisibleForTesting;
import com.android.settings.R;
import com.android.settings.biometrics.BiometricEnrollSidecar;
/* loaded from: classes.dex */
public class FingerprintEnrollSidecar extends BiometricEnrollSidecar {
    private int mEnrollReason;
    @VisibleForTesting
    FingerprintManager.EnrollmentCallback mEnrollmentCallback = new FingerprintManager.EnrollmentCallback() { // from class: com.android.settings.biometrics.fingerprint.FingerprintEnrollSidecar.1
        public void onEnrollmentProgress(int i) {
            FingerprintEnrollSidecar.super.onEnrollmentProgress(i);
        }

        public void onEnrollmentHelp(int i, CharSequence charSequence) {
            FingerprintEnrollSidecar.super.onEnrollmentHelp(i, charSequence);
        }

        public void onEnrollmentError(int i, CharSequence charSequence) {
            FingerprintEnrollSidecar.super.onEnrollmentError(i, charSequence);
        }
    };
    private FingerprintUpdater mFingerprintUpdater;
    private final MessageDisplayController mMessageDisplayController;
    private final boolean mMessageDisplayControllerFlag;

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 245;
    }

    public FingerprintEnrollSidecar(Context context, int i) {
        this.mEnrollReason = i;
        int integer = context.getResources().getInteger(R.integer.enrollment_help_minimum_time_display);
        int integer2 = context.getResources().getInteger(R.integer.enrollment_progress_minimum_time_display);
        boolean z = context.getResources().getBoolean(R.bool.enrollment_progress_priority_over_help);
        boolean z2 = context.getResources().getBoolean(R.bool.enrollment_prioritize_acquire_messages);
        int integer3 = context.getResources().getInteger(R.integer.enrollment_collect_time);
        this.mMessageDisplayControllerFlag = context.getResources().getBoolean(R.bool.enrollment_message_display_controller_flag);
        this.mMessageDisplayController = new MessageDisplayController(context.getMainThreadHandler(), this.mEnrollmentCallback, SystemClock.elapsedRealtimeClock(), integer, integer2, z, z2, integer3);
    }

    @Override // com.android.settings.biometrics.BiometricEnrollSidecar, androidx.fragment.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mFingerprintUpdater = new FingerprintUpdater(activity);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settings.biometrics.BiometricEnrollSidecar
    public void startEnrollment() {
        super.startEnrollment();
        byte[] bArr = this.mToken;
        if (bArr == null) {
            Log.e("FingerprintEnrollSidecar", "Null hardware auth token for enroll");
            onEnrollmentError(1, getString(R.string.fingerprint_intro_error_unknown));
            return;
        }
        int i = this.mEnrollReason;
        if (i == 2 && this.mMessageDisplayControllerFlag) {
            this.mFingerprintUpdater.enroll(bArr, this.mEnrollmentCancel, this.mUserId, this.mMessageDisplayController, i);
        } else {
            this.mFingerprintUpdater.enroll(bArr, this.mEnrollmentCancel, this.mUserId, this.mEnrollmentCallback, i);
        }
    }
}
