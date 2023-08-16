package com.android.settings.biometrics.fingerprint;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.android.settings.R;
import com.android.settings.biometrics.BiometricEnrollBase;
import com.android.settings.core.instrumentation.InstrumentedDialogFragment;
/* loaded from: classes.dex */
public class FingerprintErrorDialog extends InstrumentedDialogFragment {
    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 569;
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        CharSequence charSequence = getArguments().getCharSequence("error_msg");
        CharSequence charSequence2 = getArguments().getCharSequence("error_title");
        int i = getArguments().getInt("error_id");
        final boolean z = getArguments().getBoolean("is_udfps", false);
        final boolean z2 = i == 3;
        AlertDialog.Builder cancelable = builder.setTitle(charSequence2).setMessage(charSequence).setCancelable(false);
        int i2 = R.string.security_settings_fingerprint_enroll_dialog_ok;
        cancelable.setPositiveButton(i2, new DialogInterface.OnClickListener() { // from class: com.android.settings.biometrics.fingerprint.FingerprintErrorDialog.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i3) {
                dialogInterface.dismiss();
                FragmentActivity activity = FingerprintErrorDialog.this.getActivity();
                if (z2 && !z) {
                    activity.setResult(3);
                } else {
                    activity.setResult(1);
                }
                activity.finish();
            }
        });
        if (z2 && z) {
            builder.setPositiveButton(R.string.security_settings_fingerprint_enroll_dialog_try_again, new DialogInterface.OnClickListener() { // from class: com.android.settings.biometrics.fingerprint.FingerprintErrorDialog.3
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i3) {
                    dialogInterface.dismiss();
                    FragmentActivity activity = FingerprintErrorDialog.this.getActivity();
                    Intent intent = activity.getIntent();
                    intent.addFlags(33554432);
                    intent.putExtra("is_canceled", false);
                    activity.startActivity(intent);
                    activity.finish();
                }
            }).setNegativeButton(i2, new DialogInterface.OnClickListener() { // from class: com.android.settings.biometrics.fingerprint.FingerprintErrorDialog.2
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i3) {
                    dialogInterface.dismiss();
                    FragmentActivity activity = FingerprintErrorDialog.this.getActivity();
                    activity.setResult(3);
                    activity.finish();
                }
            });
        }
        AlertDialog create = builder.create();
        create.setCanceledOnTouchOutside(false);
        return create;
    }

    public static void showErrorDialog(BiometricEnrollBase biometricEnrollBase, int i, boolean z) {
        if (biometricEnrollBase.isFinishing()) {
            return;
        }
        FragmentManager supportFragmentManager = biometricEnrollBase.getSupportFragmentManager();
        if (supportFragmentManager.isDestroyed() || supportFragmentManager.isStateSaved()) {
            return;
        }
        CharSequence text = biometricEnrollBase.getText(getErrorMessage(i));
        CharSequence text2 = biometricEnrollBase.getText(getErrorTitle(i));
        if (!z && i == 3) {
            text = biometricEnrollBase.getText(getErrorMessage(5));
        }
        newInstance(text, text2, i, z).show(supportFragmentManager, FingerprintErrorDialog.class.getName());
    }

    private static int getErrorMessage(int i) {
        if (i != 3) {
            if (i == 18) {
                return R.string.security_settings_fingerprint_bad_calibration;
            }
            return R.string.security_settings_fingerprint_enroll_error_generic_dialog_message;
        }
        return R.string.security_settings_fingerprint_enroll_error_timeout_dialog_message;
    }

    private static int getErrorTitle(int i) {
        if (i == 2) {
            return R.string.security_settings_fingerprint_enroll_error_unable_to_process_dialog_title;
        }
        return R.string.security_settings_fingerprint_enroll_error_dialog_title;
    }

    private static FingerprintErrorDialog newInstance(CharSequence charSequence, CharSequence charSequence2, int i, boolean z) {
        FingerprintErrorDialog fingerprintErrorDialog = new FingerprintErrorDialog();
        Bundle bundle = new Bundle();
        bundle.putCharSequence("error_msg", charSequence);
        bundle.putCharSequence("error_title", charSequence2);
        bundle.putInt("error_id", i);
        bundle.putBoolean("is_udfps", z);
        fingerprintErrorDialog.setArguments(bundle);
        return fingerprintErrorDialog;
    }
}
