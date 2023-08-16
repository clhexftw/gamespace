package androidx.tracing;
/* loaded from: classes.dex */
public final class Trace {
    public static boolean isEnabled() {
        return TraceApi29Impl.isEnabled();
    }

    public static void beginSection(String str) {
        TraceApi18Impl.beginSection(str);
    }

    public static void endSection() {
        TraceApi18Impl.endSection();
    }
}
