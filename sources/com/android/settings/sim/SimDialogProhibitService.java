package com.android.settings.sim;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import java.lang.ref.WeakReference;
import java.util.concurrent.RejectedExecutionException;
/* loaded from: classes.dex */
public class SimDialogProhibitService {
    private static WeakReference<SimDialogActivity> sSimDialogActivity;

    public static void supportDismiss(SimDialogActivity simDialogActivity) {
        sSimDialogActivity = new WeakReference<>(simDialogActivity);
    }

    public static void dismissDialog(Context context) {
        if (dismissDialogThroughRunnable()) {
            return;
        }
        dismissDialogThroughIntent(context);
    }

    protected static boolean dismissDialogThroughRunnable() {
        WeakReference<SimDialogActivity> weakReference = sSimDialogActivity;
        final SimDialogActivity simDialogActivity = weakReference == null ? null : weakReference.get();
        if (simDialogActivity == null) {
            Log.i("SimDialogProhibitService", "No SimDialogActivity for dismiss.");
            return true;
        }
        try {
            simDialogActivity.getMainExecutor().execute(new Runnable() { // from class: com.android.settings.sim.SimDialogProhibitService$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    SimDialogActivity.this.forceClose();
                }
            });
            return true;
        } catch (RejectedExecutionException e) {
            Log.w("SimDialogProhibitService", "Fail to close SimDialogActivity through executor", e);
            return false;
        }
    }

    protected static void dismissDialogThroughIntent(Context context) {
        Intent intent = new Intent(context, SimDialogActivity.class);
        intent.addFlags(268435456);
        intent.putExtra(SimDialogActivity.DIALOG_TYPE_KEY, 5);
        context.startActivity(intent);
    }
}
