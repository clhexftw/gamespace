package kotlinx.atomicfu;

import kotlin.jvm.internal.Intrinsics;
/* compiled from: Interceptor.kt */
/* loaded from: classes2.dex */
public class AtomicOperationInterceptor {
    public void afterRMW(AtomicBoolean ref, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(ref, "ref");
    }

    public void afterRMW(AtomicInt ref, int i, int i2) {
        Intrinsics.checkNotNullParameter(ref, "ref");
    }

    public void afterRMW(AtomicLong ref, long j, long j2) {
        Intrinsics.checkNotNullParameter(ref, "ref");
    }

    public <T> void afterRMW(AtomicRef<T> ref, T t, T t2) {
        Intrinsics.checkNotNullParameter(ref, "ref");
    }

    public void afterSet(AtomicBoolean ref, boolean z) {
        Intrinsics.checkNotNullParameter(ref, "ref");
    }

    public void afterSet(AtomicInt ref, int i) {
        Intrinsics.checkNotNullParameter(ref, "ref");
    }

    public void afterSet(AtomicLong ref, long j) {
        Intrinsics.checkNotNullParameter(ref, "ref");
    }

    public <T> void afterSet(AtomicRef<T> ref, T t) {
        Intrinsics.checkNotNullParameter(ref, "ref");
    }

    public void beforeUpdate(AtomicBoolean ref) {
        Intrinsics.checkNotNullParameter(ref, "ref");
    }

    public void beforeUpdate(AtomicInt ref) {
        Intrinsics.checkNotNullParameter(ref, "ref");
    }

    public void beforeUpdate(AtomicLong ref) {
        Intrinsics.checkNotNullParameter(ref, "ref");
    }

    public <T> void beforeUpdate(AtomicRef<T> ref) {
        Intrinsics.checkNotNullParameter(ref, "ref");
    }
}
