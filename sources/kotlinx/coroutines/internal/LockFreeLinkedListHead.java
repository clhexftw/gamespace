package kotlinx.coroutines.internal;
/* compiled from: LockFreeLinkedList.kt */
/* loaded from: classes2.dex */
public class LockFreeLinkedListHead extends LockFreeLinkedListNode {
    @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
    public boolean isRemoved() {
        return false;
    }

    @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
    /* renamed from: remove  reason: collision with other method in class */
    public /* bridge */ /* synthetic */ boolean mo2185remove() {
        return ((Boolean) remove()).booleanValue();
    }

    public final boolean isEmpty() {
        return getNext() == this;
    }

    public final Void remove() {
        throw new IllegalStateException("head cannot be removed".toString());
    }
}
