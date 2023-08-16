package kotlinx.atomicfu;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.atomicfu.TraceBase;
/* compiled from: AtomicFU.kt */
/* loaded from: classes2.dex */
public final class AtomicRef<T> {
    private static final Companion Companion = new Companion(null);
    @Deprecated
    private static final AtomicReferenceFieldUpdater<AtomicRef<?>, Object> FU = AtomicReferenceFieldUpdater.newUpdater(AtomicRef.class, Object.class, "value");
    private final TraceBase trace;
    private volatile T value;

    public AtomicRef(T t, TraceBase trace) {
        Intrinsics.checkNotNullParameter(trace, "trace");
        this.trace = trace;
        this.value = t;
    }

    public final T getValue() {
        return this.value;
    }

    public final void setValue(T t) {
        InterceptorKt.getInterceptor().beforeUpdate(this);
        this.value = t;
        TraceBase traceBase = this.trace;
        if (traceBase != TraceBase.None.INSTANCE) {
            traceBase.append("set(" + t + ")");
        }
        InterceptorKt.getInterceptor().afterSet((AtomicRef<AtomicRef<T>>) this, (AtomicRef<T>) t);
    }

    public final void lazySet(T t) {
        InterceptorKt.getInterceptor().beforeUpdate(this);
        FU.lazySet(this, t);
        TraceBase traceBase = this.trace;
        if (traceBase != TraceBase.None.INSTANCE) {
            traceBase.append("lazySet(" + t + ")");
        }
        InterceptorKt.getInterceptor().afterSet((AtomicRef<AtomicRef<T>>) this, (AtomicRef<T>) t);
    }

    public final boolean compareAndSet(T t, T t2) {
        InterceptorKt.getInterceptor().beforeUpdate(this);
        boolean compareAndSet = FU.compareAndSet(this, t, t2);
        if (compareAndSet) {
            TraceBase traceBase = this.trace;
            if (traceBase != TraceBase.None.INSTANCE) {
                traceBase.append("CAS(" + t + ", " + t2 + ")");
            }
            InterceptorKt.getInterceptor().afterRMW(this, t, t2);
        }
        return compareAndSet;
    }

    public final T getAndSet(T t) {
        InterceptorKt.getInterceptor().beforeUpdate(this);
        T t2 = (T) FU.getAndSet(this, t);
        TraceBase traceBase = this.trace;
        if (traceBase != TraceBase.None.INSTANCE) {
            traceBase.append("getAndSet(" + t + "):" + t2);
        }
        InterceptorKt.getInterceptor().afterRMW(this, t2, t);
        return t2;
    }

    public String toString() {
        return String.valueOf(this.value);
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
