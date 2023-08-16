package com.android.settings.custom.biometrics;

import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.util.Log;
import com.android.internal.util.custom.faceunlock.FaceUnlockUtils;
/* loaded from: classes.dex */
public final class FaceUtils {
    public static boolean isFaceUnlockSupported() {
        return FaceUnlockUtils.isFaceUnlockSupported();
    }

    public static boolean isFaceDisabledByAdmin(Context context) {
        DevicePolicyManager devicePolicyManager = (DevicePolicyManager) context.getSystemService("device_policy");
        try {
            if (devicePolicyManager.getPasswordQuality(null) > 32768) {
                return true;
            }
        } catch (SecurityException e) {
            Log.e("FaceUtils", "isFaceDisabledByAdmin error:", e);
        }
        return (devicePolicyManager.getKeyguardDisabledFeatures(null) & 128) != 0;
    }
}
