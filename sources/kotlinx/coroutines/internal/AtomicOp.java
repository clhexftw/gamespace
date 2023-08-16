package kotlinx.coroutines.internal;

import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicRef;
import kotlinx.coroutines.DebugKt;
/* compiled from: Atomic.kt */
/* loaded from: classes2.dex */
public abstract class AtomicOp<T> extends OpDescriptor {
    private final AtomicRef<Object> _consensus = AtomicFU.atomic(AtomicKt.NO_DECISION);

    public abstract void complete(T t, Object obj);

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.coroutines.internal.OpDescriptor
    public AtomicOp<?> getAtomicOp() {
        return this;
    }

    public long getOpSequence() {
        return 0L;
    }

    public abstract Object prepare(T t);

    public final Object decide(Object obj) {
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(obj != AtomicKt.NO_DECISION)) {
                throw new AssertionError();
            }
        }
        Object value = this._consensus.getValue();
        Object obj2 = AtomicKt.NO_DECISION;
        return value != obj2 ? value : this._consensus.compareAndSet(obj2, obj) ? obj : this._consensus.getValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.coroutines.internal.OpDescriptor
    public final Object perform(Object obj) {
        Object value = this._consensus.getValue();
        if (value == AtomicKt.NO_DECISION) {
            value = decide(prepare(obj));
        }
        complete(obj, value);
        return value;
    }
}
