package kotlinx.coroutines;

import kotlin.jvm.internal.Intrinsics;
/* compiled from: Job.kt */
/* loaded from: classes2.dex */
public final class NonDisposableHandle implements DisposableHandle, ChildHandle {
    public static final NonDisposableHandle INSTANCE = new NonDisposableHandle();

    @Override // kotlinx.coroutines.ChildHandle
    public boolean childCancelled(Throwable cause) {
        Intrinsics.checkNotNullParameter(cause, "cause");
        return false;
    }

    @Override // kotlinx.coroutines.DisposableHandle
    public void dispose() {
    }

    public String toString() {
        return "NonDisposableHandle";
    }

    private NonDisposableHandle() {
    }
}
