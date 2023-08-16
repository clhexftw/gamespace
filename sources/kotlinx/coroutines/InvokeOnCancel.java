package kotlinx.coroutines;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: CancellableContinuationImpl.kt */
/* loaded from: classes2.dex */
final class InvokeOnCancel extends CancelHandler {
    private final Function1<Throwable, Unit> handler;

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
        invoke2(th);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public InvokeOnCancel(Function1<? super Throwable, Unit> handler) {
        Intrinsics.checkNotNullParameter(handler, "handler");
        this.handler = handler;
    }

    @Override // kotlinx.coroutines.CancelHandlerBase
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public void invoke2(Throwable th) {
        this.handler.invoke(th);
    }

    public String toString() {
        String classSimpleName = DebugStringsKt.getClassSimpleName(this.handler);
        String hexAddress = DebugStringsKt.getHexAddress(this);
        return "InvokeOnCancel[" + classSimpleName + "@" + hexAddress + "]";
    }
}
