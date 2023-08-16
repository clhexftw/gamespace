package androidx.core.app;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.core.content.ContextCompat;
import java.util.Arrays;
import java.util.HashSet;
/* loaded from: classes.dex */
public class ActivityCompat extends ContextCompat {

    /* loaded from: classes.dex */
    public interface RequestPermissionsRequestCodeValidator {
        void validateRequestPermissionsRequestCode(int i);
    }

    public static void startActivityForResult(Activity activity, Intent intent, int i, Bundle bundle) {
        Api16Impl.startActivityForResult(activity, intent, i, bundle);
    }

    public static void startIntentSenderForResult(Activity activity, IntentSender intentSender, int i, Intent intent, int i2, int i3, int i4, Bundle bundle) throws IntentSender.SendIntentException {
        Api16Impl.startIntentSenderForResult(activity, intentSender, i, intent, i2, i3, i4, bundle);
    }

    public static void finishAffinity(Activity activity) {
        Api16Impl.finishAffinity(activity);
    }

    public static void requestPermissions(Activity activity, String[] strArr, int i) {
        HashSet hashSet = new HashSet();
        for (String str : strArr) {
            if (TextUtils.isEmpty(str)) {
                throw new IllegalArgumentException("Permission request for permissions " + Arrays.toString(strArr) + " must not contain null or empty values");
            }
        }
        int size = hashSet.size();
        String[] strArr2 = size > 0 ? new String[strArr.length - size] : strArr;
        if (size > 0) {
            if (size == strArr.length) {
                return;
            }
            int i2 = 0;
            for (int i3 = 0; i3 < strArr.length; i3++) {
                if (!hashSet.contains(Integer.valueOf(i3))) {
                    strArr2[i2] = strArr[i3];
                    i2++;
                }
            }
        }
        if (activity instanceof RequestPermissionsRequestCodeValidator) {
            ((RequestPermissionsRequestCodeValidator) activity).validateRequestPermissionsRequestCode(i);
        }
        Api23Impl.requestPermissions(activity, strArr, i);
    }

    public static boolean shouldShowRequestPermissionRationale(Activity activity, String str) {
        return Api23Impl.shouldShowRequestPermissionRationale(activity, str);
    }

    public static void recreate(Activity activity) {
        activity.recreate();
    }

    /* loaded from: classes.dex */
    static class Api16Impl {
        static void startActivityForResult(Activity activity, Intent intent, int i, Bundle bundle) {
            activity.startActivityForResult(intent, i, bundle);
        }

        static void startIntentSenderForResult(Activity activity, IntentSender intentSender, int i, Intent intent, int i2, int i3, int i4, Bundle bundle) throws IntentSender.SendIntentException {
            activity.startIntentSenderForResult(intentSender, i, intent, i2, i3, i4, bundle);
        }

        static void finishAffinity(Activity activity) {
            activity.finishAffinity();
        }
    }

    /* loaded from: classes.dex */
    static class Api23Impl {
        static void requestPermissions(Activity activity, String[] strArr, int i) {
            activity.requestPermissions(strArr, i);
        }

        static boolean shouldShowRequestPermissionRationale(Activity activity, String str) {
            return activity.shouldShowRequestPermissionRationale(str);
        }
    }
}
