package kotlinx.coroutines.internal;

import kotlin.jvm.internal.Intrinsics;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicRef;
/* compiled from: LockFreeTaskQueue.kt */
/* loaded from: classes2.dex */
public class LockFreeTaskQueue<E> {
    private final AtomicRef<LockFreeTaskQueueCore<E>> _cur;

    public LockFreeTaskQueue(boolean z) {
        this._cur = AtomicFU.atomic(new LockFreeTaskQueueCore(8, z));
    }

    public final int getSize() {
        return this._cur.getValue().getSize();
    }

    public final void close() {
        AtomicRef<LockFreeTaskQueueCore<E>> atomicRef = this._cur;
        while (true) {
            LockFreeTaskQueueCore<E> value = atomicRef.getValue();
            if (value.close()) {
                return;
            }
            this._cur.compareAndSet(value, value.next());
        }
    }

    public final boolean addLast(E element) {
        Intrinsics.checkNotNullParameter(element, "element");
        AtomicRef<LockFreeTaskQueueCore<E>> atomicRef = this._cur;
        while (true) {
            LockFreeTaskQueueCore<E> value = atomicRef.getValue();
            int addLast = value.addLast(element);
            if (addLast == 0) {
                return true;
            }
            if (addLast == 1) {
                this._cur.compareAndSet(value, value.next());
            } else if (addLast == 2) {
                return false;
            }
        }
    }

    public final E removeFirstOrNull() {
        AtomicRef<LockFreeTaskQueueCore<E>> atomicRef = this._cur;
        while (true) {
            LockFreeTaskQueueCore<E> value = atomicRef.getValue();
            E e = (E) value.removeFirstOrNull();
            if (e != LockFreeTaskQueueCore.REMOVE_FROZEN) {
                return e;
            }
            this._cur.compareAndSet(value, value.next());
        }
    }
}
