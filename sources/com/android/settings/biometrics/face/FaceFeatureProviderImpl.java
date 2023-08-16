package com.android.settings.biometrics.face;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.android.settings.R;
/* loaded from: classes.dex */
public class FaceFeatureProviderImpl implements FaceFeatureProvider {
    @Override // com.android.settings.biometrics.face.FaceFeatureProvider
    public boolean isAttentionSupported(Context context) {
        return true;
    }

    @Override // com.android.settings.biometrics.face.FaceFeatureProvider
    public boolean isSetupWizardSupported(Context context) {
        return true;
    }

    @Override // com.android.settings.biometrics.face.FaceFeatureProvider
    public Intent getPostureGuidanceIntent(Context context) {
        ComponentName unflattenFromString;
        String string = context.getString(R.string.config_face_enroll_guidance_page);
        if (TextUtils.isEmpty(string) || (unflattenFromString = ComponentName.unflattenFromString(string)) == null) {
            return null;
        }
        Intent intent = new Intent();
        intent.setComponent(unflattenFromString);
        return intent;
    }
}
