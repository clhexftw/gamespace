package kotlinx.atomicfu;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.atomicfu.TraceBase;
/* compiled from: AtomicFU.kt */
/* loaded from: classes2.dex */
public final class AtomicBoolean {
    private static final Companion Companion = new Companion(null);
    @Deprecated
    private static final AtomicIntegerFieldUpdater<AtomicBoolean> FU = AtomicIntegerFieldUpdater.newUpdater(AtomicBoolean.class, "_value");
    private volatile int _value;
    private final TraceBase trace;

    public AtomicBoolean(boolean z, TraceBase trace) {
        Intrinsics.checkNotNullParameter(trace, "trace");
        this.trace = trace;
        this._value = z ? 1 : 0;
    }

    public final boolean getValue() {
        return this._value != 0;
    }

    public final void setValue(boolean z) {
        InterceptorKt.getInterceptor().beforeUpdate(this);
        this._value = z ? 1 : 0;
        TraceBase traceBase = this.trace;
        if (traceBase != TraceBase.None.INSTANCE) {
            traceBase.append("set(" + z + ")");
        }
        InterceptorKt.getInterceptor().afterSet(this, z);
    }

    public final boolean compareAndSet(boolean z, boolean z2) {
        InterceptorKt.getInterceptor().beforeUpdate(this);
        boolean compareAndSet = FU.compareAndSet(this, z ? 1 : 0, z2 ? 1 : 0);
        if (compareAndSet) {
            TraceBase traceBase = this.trace;
            if (traceBase != TraceBase.None.INSTANCE) {
                traceBase.append("CAS(" + z + ", " + z2 + ")");
            }
            InterceptorKt.getInterceptor().afterRMW(this, z, z2);
        }
        return compareAndSet;
    }

    public String toString() {
        return String.valueOf(getValue());
    }

    /* compiled from: AtomicFU.kt */
    /* loaded from: classes2.dex */
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
