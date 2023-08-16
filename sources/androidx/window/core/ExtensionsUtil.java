package androidx.window.core;

import android.util.Log;
import androidx.window.extensions.WindowExtensionsProvider;
import kotlin.jvm.internal.Reflection;
/* compiled from: ExtensionsUtil.kt */
/* loaded from: classes.dex */
public final class ExtensionsUtil {
    public static final ExtensionsUtil INSTANCE = new ExtensionsUtil();
    private static final String TAG = Reflection.getOrCreateKotlinClass(ExtensionsUtil.class).getSimpleName();

    private ExtensionsUtil() {
    }

    public final int getSafeVendorApiLevel() {
        try {
            return WindowExtensionsProvider.getWindowExtensions().getVendorApiLevel();
        } catch (NoClassDefFoundError unused) {
            if (BuildConfig.INSTANCE.getVerificationMode() == VerificationMode.LOG) {
                Log.d(TAG, "Embedding extension version not found");
                return 0;
            }
            return 0;
        } catch (UnsupportedOperationException unused2) {
            if (BuildConfig.INSTANCE.getVerificationMode() == VerificationMode.LOG) {
                Log.d(TAG, "Stub Extension");
                return 0;
            }
            return 0;
        }
    }
}
