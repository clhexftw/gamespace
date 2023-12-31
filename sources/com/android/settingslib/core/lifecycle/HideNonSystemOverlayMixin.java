package com.android.settingslib.core.lifecycle;

import android.app.Activity;
import android.provider.Settings;
import android.util.EventLog;
import android.view.Window;
import android.view.WindowManager;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;
/* loaded from: classes2.dex */
public class HideNonSystemOverlayMixin implements androidx.lifecycle.LifecycleObserver {
    private final Activity mActivity;

    public HideNonSystemOverlayMixin(Activity activity) {
        this.mActivity = activity;
    }

    boolean isEnabled() {
        return Settings.Secure.getInt(this.mActivity.getContentResolver(), "secure_overlay_settings", 0) == 0;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
        if (this.mActivity == null || !isEnabled()) {
            return;
        }
        this.mActivity.getWindow().addSystemFlags(524288);
        EventLog.writeEvent(1397638484, "120484087", -1, "");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {
        if (this.mActivity == null || !isEnabled()) {
            return;
        }
        Window window = this.mActivity.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.privateFlags &= -524289;
        window.setAttributes(attributes);
    }
}
