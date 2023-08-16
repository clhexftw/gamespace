package kotlinx.coroutines;

import kotlinx.atomicfu.AtomicBoolean;
/* compiled from: CompletionState.kt */
/* loaded from: classes.dex */
public final class CancelledContinuation extends CompletedExceptionally {
    private final AtomicBoolean _resumed;

    public final boolean makeResumed() {
        return this._resumed.compareAndSet(false, true);
    }
}
