package kotlinx.coroutines;

import kotlinx.atomicfu.AtomicBoolean;
/* compiled from: CompletionState.kt */
/* loaded from: classes2.dex */
public final class CancelledContinuation extends CompletedExceptionally {
    private final AtomicBoolean _resumed;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public CancelledContinuation(kotlin.coroutines.Continuation<?> r3, java.lang.Throwable r4, boolean r5) {
        /*
            r2 = this;
            java.lang.String r0 = "continuation"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            if (r4 != 0) goto L22
            java.util.concurrent.CancellationException r4 = new java.util.concurrent.CancellationException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Continuation "
            r0.append(r1)
            r0.append(r3)
            java.lang.String r3 = " was cancelled normally"
            r0.append(r3)
            java.lang.String r3 = r0.toString()
            r4.<init>(r3)
        L22:
            r2.<init>(r4, r5)
            r3 = 0
            kotlinx.atomicfu.AtomicBoolean r3 = kotlinx.atomicfu.AtomicFU.atomic(r3)
            r2._resumed = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.CancelledContinuation.<init>(kotlin.coroutines.Continuation, java.lang.Throwable, boolean):void");
    }

    public final boolean makeResumed() {
        return this._resumed.compareAndSet(false, true);
    }
}
