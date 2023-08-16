package kotlinx.coroutines.internal;

import kotlin.jvm.internal.Intrinsics;
/* compiled from: SystemProps.kt */
/* loaded from: classes2.dex */
final /* synthetic */ class SystemPropsKt__SystemPropsKt {
    private static final int AVAILABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();

    public static final int getAVAILABLE_PROCESSORS() {
        return AVAILABLE_PROCESSORS;
    }

    public static final String systemProp(String propertyName) {
        Intrinsics.checkNotNullParameter(propertyName, "propertyName");
        try {
            return System.getProperty(propertyName);
        } catch (SecurityException unused) {
            return null;
        }
    }
}
