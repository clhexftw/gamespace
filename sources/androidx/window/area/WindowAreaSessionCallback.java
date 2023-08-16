package androidx.window.area;

import androidx.window.core.ExperimentalWindowApi;
/* compiled from: WindowAreaSessionCallback.kt */
@ExperimentalWindowApi
/* loaded from: classes.dex */
public interface WindowAreaSessionCallback {
    void onSessionEnded();

    void onSessionStarted(WindowAreaSession windowAreaSession);
}
