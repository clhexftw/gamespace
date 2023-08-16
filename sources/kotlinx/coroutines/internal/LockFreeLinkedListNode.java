package kotlinx.coroutines.internal;

import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference0Impl;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicRef;
import kotlinx.coroutines.DebugStringsKt;
/* compiled from: LockFreeLinkedList.kt */
/* loaded from: classes2.dex */
public class LockFreeLinkedListNode {
    private final AtomicRef<Object> _next = AtomicFU.atomic(this);
    private final AtomicRef<LockFreeLinkedListNode> _prev = AtomicFU.atomic(this);
    private final AtomicRef<Removed> _removedRef = AtomicFU.atomic((Object) null);

    /* compiled from: LockFreeLinkedList.kt */
    /* loaded from: classes2.dex */
    public static final class PrepareOp extends OpDescriptor {
    }

    private final Removed removed() {
        Removed value = this._removedRef.getValue();
        if (value == null) {
            Removed removed = new Removed(this);
            this._removedRef.lazySet(removed);
            return removed;
        }
        return value;
    }

    /* compiled from: LockFreeLinkedList.kt */
    /* loaded from: classes2.dex */
    public static abstract class CondAddOp extends AtomicOp<LockFreeLinkedListNode> {
        public final LockFreeLinkedListNode newNode;
        public LockFreeLinkedListNode oldNext;

        public CondAddOp(LockFreeLinkedListNode newNode) {
            Intrinsics.checkNotNullParameter(newNode, "newNode");
            this.newNode = newNode;
        }

        @Override // kotlinx.coroutines.internal.AtomicOp
        public void complete(LockFreeLinkedListNode affected, Object obj) {
            Intrinsics.checkNotNullParameter(affected, "affected");
            boolean z = obj == null;
            LockFreeLinkedListNode lockFreeLinkedListNode = z ? this.newNode : this.oldNext;
            if (lockFreeLinkedListNode != null && affected._next.compareAndSet(this, lockFreeLinkedListNode) && z) {
                LockFreeLinkedListNode lockFreeLinkedListNode2 = this.newNode;
                LockFreeLinkedListNode lockFreeLinkedListNode3 = this.oldNext;
                Intrinsics.checkNotNull(lockFreeLinkedListNode3);
                lockFreeLinkedListNode2.finishAdd(lockFreeLinkedListNode3);
            }
        }
    }

    public boolean isRemoved() {
        return getNext() instanceof Removed;
    }

    public final Object getNext() {
        AtomicRef<Object> atomicRef = this._next;
        while (true) {
            Object value = atomicRef.getValue();
            if (!(value instanceof OpDescriptor)) {
                return value;
            }
            ((OpDescriptor) value).perform(this);
        }
    }

    public final LockFreeLinkedListNode getNextNode() {
        return LockFreeLinkedListKt.unwrap(getNext());
    }

    public final LockFreeLinkedListNode getPrevNode() {
        LockFreeLinkedListNode correctPrev = correctPrev(null);
        return correctPrev == null ? findPrevNonRemoved(this._prev.getValue()) : correctPrev;
    }

    private final LockFreeLinkedListNode findPrevNonRemoved(LockFreeLinkedListNode lockFreeLinkedListNode) {
        while (lockFreeLinkedListNode.isRemoved()) {
            lockFreeLinkedListNode = lockFreeLinkedListNode._prev.getValue();
        }
        return lockFreeLinkedListNode;
    }

    public final boolean addOneIfEmpty(LockFreeLinkedListNode node) {
        Intrinsics.checkNotNullParameter(node, "node");
        node._prev.lazySet(this);
        node._next.lazySet(this);
        while (getNext() == this) {
            if (this._next.compareAndSet(this, node)) {
                node.finishAdd(this);
                return true;
            }
        }
        return false;
    }

    public final void addLast(LockFreeLinkedListNode node) {
        Intrinsics.checkNotNullParameter(node, "node");
        do {
        } while (!getPrevNode().addNext(node, this));
    }

    public final boolean addNext(LockFreeLinkedListNode node, LockFreeLinkedListNode next) {
        Intrinsics.checkNotNullParameter(node, "node");
        Intrinsics.checkNotNullParameter(next, "next");
        node._prev.lazySet(this);
        node._next.lazySet(next);
        if (this._next.compareAndSet(next, node)) {
            node.finishAdd(next);
            return true;
        }
        return false;
    }

    public final int tryCondAddNext(LockFreeLinkedListNode node, LockFreeLinkedListNode next, CondAddOp condAdd) {
        Intrinsics.checkNotNullParameter(node, "node");
        Intrinsics.checkNotNullParameter(next, "next");
        Intrinsics.checkNotNullParameter(condAdd, "condAdd");
        node._prev.lazySet(this);
        node._next.lazySet(next);
        condAdd.oldNext = next;
        if (this._next.compareAndSet(next, condAdd)) {
            return condAdd.perform(this) == null ? 1 : 2;
        }
        return 0;
    }

    /* renamed from: remove */
    public boolean mo2185remove() {
        return removeOrNext() == null;
    }

    public final LockFreeLinkedListNode removeOrNext() {
        Object next;
        LockFreeLinkedListNode lockFreeLinkedListNode;
        do {
            next = getNext();
            if (next instanceof Removed) {
                return ((Removed) next).ref;
            }
            if (next == this) {
                return (LockFreeLinkedListNode) next;
            }
            Intrinsics.checkNotNull(next, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }");
            lockFreeLinkedListNode = (LockFreeLinkedListNode) next;
        } while (!this._next.compareAndSet(next, lockFreeLinkedListNode.removed()));
        lockFreeLinkedListNode.correctPrev(null);
        return null;
    }

    public final void helpRemove() {
        Object next = getNext();
        Intrinsics.checkNotNull(next, "null cannot be cast to non-null type kotlinx.coroutines.internal.Removed");
        ((Removed) next).ref.helpRemovePrev();
    }

    public final void helpRemovePrev() {
        while (true) {
            Object next = this.getNext();
            if (next instanceof Removed) {
                this = ((Removed) next).ref;
            } else {
                this.correctPrev(null);
                return;
            }
        }
    }

    public final LockFreeLinkedListNode removeFirstOrNull() {
        while (true) {
            Object next = getNext();
            Intrinsics.checkNotNull(next, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }");
            LockFreeLinkedListNode lockFreeLinkedListNode = (LockFreeLinkedListNode) next;
            if (lockFreeLinkedListNode == this) {
                return null;
            }
            if (lockFreeLinkedListNode.mo2185remove()) {
                return lockFreeLinkedListNode;
            }
            lockFreeLinkedListNode.helpRemove();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void finishAdd(LockFreeLinkedListNode lockFreeLinkedListNode) {
        LockFreeLinkedListNode value;
        AtomicRef<LockFreeLinkedListNode> atomicRef = lockFreeLinkedListNode._prev;
        do {
            value = atomicRef.getValue();
            if (getNext() != lockFreeLinkedListNode) {
                return;
            }
        } while (!lockFreeLinkedListNode._prev.compareAndSet(value, this));
        if (isRemoved()) {
            lockFreeLinkedListNode.correctPrev(null);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x0050, code lost:
        if (r3._next.compareAndSet(r2, ((kotlinx.coroutines.internal.Removed) r4).ref) != false) goto L23;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final kotlinx.coroutines.internal.LockFreeLinkedListNode correctPrev(kotlinx.coroutines.internal.OpDescriptor r8) {
        /*
            r7 = this;
        L0:
            kotlinx.atomicfu.AtomicRef<kotlinx.coroutines.internal.LockFreeLinkedListNode> r0 = r7._prev
            java.lang.Object r0 = r0.getValue()
            kotlinx.coroutines.internal.LockFreeLinkedListNode r0 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r0
            r1 = 0
            r2 = r0
        La:
            r3 = r1
        Lb:
            kotlinx.atomicfu.AtomicRef<java.lang.Object> r4 = r2._next
            java.lang.Object r4 = r4.getValue()
            if (r4 != r7) goto L20
            if (r0 != r2) goto L16
            return r2
        L16:
            kotlinx.atomicfu.AtomicRef<kotlinx.coroutines.internal.LockFreeLinkedListNode> r1 = r7._prev
            boolean r0 = r1.compareAndSet(r0, r2)
            if (r0 != 0) goto L1f
            goto L0
        L1f:
            return r2
        L20:
            boolean r5 = r7.isRemoved()
            if (r5 == 0) goto L27
            return r1
        L27:
            if (r4 != r8) goto L2a
            return r2
        L2a:
            boolean r5 = r4 instanceof kotlinx.coroutines.internal.OpDescriptor
            if (r5 == 0) goto L40
            if (r8 == 0) goto L3a
            r0 = r4
            kotlinx.coroutines.internal.OpDescriptor r0 = (kotlinx.coroutines.internal.OpDescriptor) r0
            boolean r0 = r8.isEarlierThan(r0)
            if (r0 == 0) goto L3a
            return r1
        L3a:
            kotlinx.coroutines.internal.OpDescriptor r4 = (kotlinx.coroutines.internal.OpDescriptor) r4
            r4.perform(r2)
            goto L0
        L40:
            boolean r5 = r4 instanceof kotlinx.coroutines.internal.Removed
            if (r5 == 0) goto L5e
            if (r3 == 0) goto L55
            kotlinx.atomicfu.AtomicRef<java.lang.Object> r5 = r3._next
            kotlinx.coroutines.internal.Removed r4 = (kotlinx.coroutines.internal.Removed) r4
            kotlinx.coroutines.internal.LockFreeLinkedListNode r4 = r4.ref
            boolean r2 = r5.compareAndSet(r2, r4)
            if (r2 != 0) goto L53
            goto L0
        L53:
            r2 = r3
            goto La
        L55:
            kotlinx.atomicfu.AtomicRef<kotlinx.coroutines.internal.LockFreeLinkedListNode> r2 = r2._prev
            java.lang.Object r2 = r2.getValue()
            kotlinx.coroutines.internal.LockFreeLinkedListNode r2 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r2
            goto Lb
        L5e:
            java.lang.String r3 = "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4, r3)
            r3 = r4
            kotlinx.coroutines.internal.LockFreeLinkedListNode r3 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r3
            r6 = r3
            r3 = r2
            r2 = r6
            goto Lb
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.internal.LockFreeLinkedListNode.correctPrev(kotlinx.coroutines.internal.OpDescriptor):kotlinx.coroutines.internal.LockFreeLinkedListNode");
    }

    public String toString() {
        PropertyReference0Impl propertyReference0Impl = new PropertyReference0Impl(this) { // from class: kotlinx.coroutines.internal.LockFreeLinkedListNode$toString$1
            @Override // kotlin.jvm.internal.PropertyReference0Impl, kotlin.reflect.KProperty0
            public Object get() {
                return DebugStringsKt.getClassSimpleName(this.receiver);
            }
        };
        String hexAddress = DebugStringsKt.getHexAddress(this);
        return propertyReference0Impl + "@" + hexAddress;
    }
}
