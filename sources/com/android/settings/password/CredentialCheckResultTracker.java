package com.android.settings.password;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
/* loaded from: classes.dex */
public class CredentialCheckResultTracker extends Fragment {
    private boolean mHasResult = false;
    private Listener mListener;
    private Intent mResultData;
    private int mResultEffectiveUserId;
    private boolean mResultMatched;
    private int mResultTimeoutMs;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface Listener {
        void onCredentialChecked(boolean z, Intent intent, int i, int i2, boolean z2);
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setRetainInstance(true);
    }

    public void setListener(Listener listener) {
        if (this.mListener == listener) {
            return;
        }
        this.mListener = listener;
        if (listener == null || !this.mHasResult) {
            return;
        }
        listener.onCredentialChecked(this.mResultMatched, this.mResultData, this.mResultTimeoutMs, this.mResultEffectiveUserId, false);
    }

    public void setResult(boolean z, Intent intent, int i, int i2) {
        this.mResultMatched = z;
        this.mResultData = intent;
        this.mResultTimeoutMs = i;
        this.mResultEffectiveUserId = i2;
        this.mHasResult = true;
        Listener listener = this.mListener;
        if (listener != null) {
            listener.onCredentialChecked(z, intent, i, i2, true);
            this.mHasResult = false;
        }
    }

    public void clearResult() {
        this.mHasResult = false;
        this.mResultMatched = false;
        this.mResultData = null;
        this.mResultTimeoutMs = 0;
        this.mResultEffectiveUserId = 0;
    }
}
