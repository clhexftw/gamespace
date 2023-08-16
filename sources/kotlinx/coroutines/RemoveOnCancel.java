package kotlinx.coroutines;

import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
/* compiled from: CancellableContinuation.kt */
/* loaded from: classes2.dex */
final class RemoveOnCancel extends BeforeResumeCancelHandler {
    private final LockFreeLinkedListNode node;

    public RemoveOnCancel(LockFreeLinkedListNode node) {
        Intrinsics.checkNotNullParameter(node, "node");
        this.node = node;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
        invoke2(th);
        return Unit.INSTANCE;
    }

    @Override // kotlinx.coroutines.CancelHandlerBase
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public void invoke2(Throwable th) {
        this.node.mo2185remove();
    }

    public String toString() {
        LockFreeLinkedListNode lockFreeLinkedListNode = this.node;
        return "RemoveOnCancel[" + lockFreeLinkedListNode + "]";
    }
}
