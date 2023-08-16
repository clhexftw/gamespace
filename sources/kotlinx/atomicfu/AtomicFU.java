package kotlinx.atomicfu;

import kotlin.jvm.internal.Intrinsics;
import kotlinx.atomicfu.TraceBase;
/* compiled from: AtomicFU.kt */
/* loaded from: classes.dex */
public final class AtomicFU {
    public static final <T> AtomicRef<T> atomic(T t, TraceBase trace) {
        Intrinsics.checkNotNullParameter(trace, "trace");
        return new AtomicRef<>(t, trace);
    }

    public static final <T> AtomicRef<T> atomic(T t) {
        return atomic(t, TraceBase.None.INSTANCE);
    }

    public static final AtomicInt atomic(int i, TraceBase trace) {
        Intrinsics.checkNotNullParameter(trace, "trace");
        return new AtomicInt(i, trace);
    }

    public static final AtomicInt atomic(int i) {
        return atomic(i, (TraceBase) TraceBase.None.INSTANCE);
    }

    public static final AtomicLong atomic(long j, TraceBase trace) {
        Intrinsics.checkNotNullParameter(trace, "trace");
        return new AtomicLong(j, trace);
    }

    public static final AtomicLong atomic(long j) {
        return atomic(j, TraceBase.None.INSTANCE);
    }

    public static final AtomicBoolean atomic(boolean z, TraceBase trace) {
        Intrinsics.checkNotNullParameter(trace, "trace");
        return new AtomicBoolean(z, trace);
    }

    public static final AtomicBoolean atomic(boolean z) {
        return atomic(z, TraceBase.None.INSTANCE);
    }
}
