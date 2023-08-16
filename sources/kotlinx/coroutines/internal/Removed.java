package kotlinx.coroutines.internal;

import kotlin.jvm.internal.Intrinsics;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: LockFreeLinkedList.kt */
/* loaded from: classes2.dex */
public final class Removed {
    public final LockFreeLinkedListNode ref;

    public Removed(LockFreeLinkedListNode ref) {
        Intrinsics.checkNotNullParameter(ref, "ref");
        this.ref = ref;
    }

    public String toString() {
        LockFreeLinkedListNode lockFreeLinkedListNode = this.ref;
        return "Removed[" + lockFreeLinkedListNode + "]";
    }
}
