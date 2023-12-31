package com.android.settings.notification;

import android.app.Dialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import com.android.settings.R;
import com.android.settings.core.instrumentation.InstrumentedDialogFragment;
/* loaded from: classes.dex */
public class NotificationAssistantDialogFragment extends InstrumentedDialogFragment implements DialogInterface.OnClickListener {
    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 790;
    }

    public static NotificationAssistantDialogFragment newInstance(Fragment fragment, ComponentName componentName) {
        NotificationAssistantDialogFragment notificationAssistantDialogFragment = new NotificationAssistantDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("c", componentName == null ? "" : componentName.flattenToString());
        notificationAssistantDialogFragment.setArguments(bundle);
        notificationAssistantDialogFragment.setTargetFragment(fragment, 0);
        return notificationAssistantDialogFragment;
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        return new AlertDialog.Builder(getContext()).setMessage(getResources().getString(R.string.notification_assistant_security_warning_summary)).setCancelable(true).setPositiveButton(R.string.okay, this).create();
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        ((ConfigureNotificationSettings) getTargetFragment()).enableNAS(ComponentName.unflattenFromString(getArguments().getString("c")));
    }
}
