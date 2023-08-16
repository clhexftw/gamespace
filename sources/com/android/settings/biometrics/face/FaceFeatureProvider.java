package com.android.settings.biometrics.face;

import android.content.Context;
import android.content.Intent;
/* loaded from: classes.dex */
public interface FaceFeatureProvider {
    Intent getPostureGuidanceIntent(Context context);

    boolean isAttentionSupported(Context context);

    boolean isSetupWizardSupported(Context context);
}
