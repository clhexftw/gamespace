package kotlinx.coroutines.internal;

import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.DebugStringsKt;
/* compiled from: Atomic.kt */
/* loaded from: classes.dex */
public abstract class OpDescriptor {
    public abstract AtomicOp<?> getAtomicOp();

    public abstract Object perform(Object obj);

    public String toString() {
        String classSimpleName = DebugStringsKt.getClassSimpleName(this);
        String hexAddress = DebugStringsKt.getHexAddress(this);
        return classSimpleName + "@" + hexAddress;
    }

    public final boolean isEarlierThan(OpDescriptor that) {
        AtomicOp<?> atomicOp;
        Intrinsics.checkNotNullParameter(that, "that");
        AtomicOp<?> atomicOp2 = getAtomicOp();
        return (atomicOp2 == null || (atomicOp = that.getAtomicOp()) == null || atomicOp2.getOpSequence() >= atomicOp.getOpSequence()) ? false : true;
    }
}
