package kotlinx.coroutines;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.atomicfu.AtomicBoolean;
import kotlinx.atomicfu.AtomicFU;
/* compiled from: CompletionState.kt */
/* loaded from: classes.dex */
public class CompletedExceptionally {
    private final AtomicBoolean _handled;
    public final Throwable cause;

    public CompletedExceptionally(Throwable cause, boolean z) {
        Intrinsics.checkNotNullParameter(cause, "cause");
        this.cause = cause;
        this._handled = AtomicFU.atomic(z);
    }

    public /* synthetic */ CompletedExceptionally(Throwable th, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(th, (i & 2) != 0 ? false : z);
    }

    public final boolean getHandled() {
        return this._handled.getValue();
    }

    public final boolean makeHandled() {
        return this._handled.compareAndSet(false, true);
    }

    public String toString() {
        String classSimpleName = DebugStringsKt.getClassSimpleName(this);
        Throwable th = this.cause;
        return classSimpleName + "[" + th + "]";
    }
}
