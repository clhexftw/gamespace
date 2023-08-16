package androidx.window.area;

import androidx.window.extensions.area.WindowAreaComponent;
import androidx.window.extensions.core.util.function.Consumer;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.channels.SendChannel;
/* compiled from: WindowAreaControllerImpl.kt */
@DebugMetadata(c = "androidx.window.area.WindowAreaControllerImpl$rearDisplayStatus$1", f = "WindowAreaControllerImpl.kt", l = {60}, m = "invokeSuspend")
/* loaded from: classes.dex */
final class WindowAreaControllerImpl$rearDisplayStatus$1 extends SuspendLambda implements Function2<ProducerScope<? super WindowAreaStatus>, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ WindowAreaControllerImpl this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WindowAreaControllerImpl$rearDisplayStatus$1(WindowAreaControllerImpl windowAreaControllerImpl, Continuation<? super WindowAreaControllerImpl$rearDisplayStatus$1> continuation) {
        super(2, continuation);
        this.this$0 = windowAreaControllerImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        WindowAreaControllerImpl$rearDisplayStatus$1 windowAreaControllerImpl$rearDisplayStatus$1 = new WindowAreaControllerImpl$rearDisplayStatus$1(this.this$0, continuation);
        windowAreaControllerImpl$rearDisplayStatus$1.L$0 = obj;
        return windowAreaControllerImpl$rearDisplayStatus$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(ProducerScope<? super WindowAreaStatus> producerScope, Continuation<? super Unit> continuation) {
        return ((WindowAreaControllerImpl$rearDisplayStatus$1) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended;
        WindowAreaComponent windowAreaComponent;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final ProducerScope producerScope = (ProducerScope) this.L$0;
            final WindowAreaControllerImpl windowAreaControllerImpl = this.this$0;
            final Consumer consumer = new Consumer() { // from class: androidx.window.area.WindowAreaControllerImpl$rearDisplayStatus$1$$ExternalSyntheticLambda0
                @Override // androidx.window.extensions.core.util.function.Consumer
                public final void accept(Object obj2) {
                    WindowAreaControllerImpl$rearDisplayStatus$1.invokeSuspend$lambda$0(WindowAreaControllerImpl.this, producerScope, (Integer) obj2);
                }
            };
            windowAreaComponent = this.this$0.windowAreaComponent;
            windowAreaComponent.addRearDisplayStatusListener(consumer);
            final WindowAreaControllerImpl windowAreaControllerImpl2 = this.this$0;
            Function0<Unit> function0 = new Function0<Unit>() { // from class: androidx.window.area.WindowAreaControllerImpl$rearDisplayStatus$1.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    WindowAreaComponent windowAreaComponent2;
                    windowAreaComponent2 = WindowAreaControllerImpl.this.windowAreaComponent;
                    windowAreaComponent2.removeRearDisplayStatusListener(consumer);
                }
            };
            this.label = 1;
            if (ProduceKt.awaitClose(producerScope, function0, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invokeSuspend$lambda$0(WindowAreaControllerImpl windowAreaControllerImpl, ProducerScope producerScope, Integer status) {
        WindowAreaStatus windowAreaStatus;
        WindowAreaAdapter windowAreaAdapter = WindowAreaAdapter.INSTANCE;
        Intrinsics.checkNotNullExpressionValue(status, "status");
        windowAreaControllerImpl.currentStatus = windowAreaAdapter.translate$window_release(status.intValue());
        SendChannel channel = producerScope.getChannel();
        windowAreaStatus = windowAreaControllerImpl.currentStatus;
        if (windowAreaStatus == null) {
            windowAreaStatus = WindowAreaStatus.UNSUPPORTED;
        }
        channel.mo2169trySendJP2dKIU(windowAreaStatus);
    }
}
