package kotlinx.coroutines.flow;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.flow.internal.ChannelFlow;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Builders.kt */
/* loaded from: classes2.dex */
public class ChannelFlowBuilder<T> extends ChannelFlow<T> {
    private final Function2<ProducerScope<? super T>, Continuation<? super Unit>, Object> block;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.flow.internal.ChannelFlow
    public Object collectTo(ProducerScope<? super T> producerScope, Continuation<? super Unit> continuation) {
        return collectTo$suspendImpl(this, producerScope, continuation);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public ChannelFlowBuilder(Function2<? super ProducerScope<? super T>, ? super Continuation<? super Unit>, ? extends Object> block, CoroutineContext context, int i, BufferOverflow onBufferOverflow) {
        super(context, i, onBufferOverflow);
        Intrinsics.checkNotNullParameter(block, "block");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(onBufferOverflow, "onBufferOverflow");
        this.block = block;
    }

    static /* synthetic */ Object collectTo$suspendImpl(ChannelFlowBuilder<T> channelFlowBuilder, ProducerScope<? super T> producerScope, Continuation<? super Unit> continuation) {
        Object coroutine_suspended;
        Object invoke = ((ChannelFlowBuilder) channelFlowBuilder).block.invoke(producerScope, continuation);
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        return invoke == coroutine_suspended ? invoke : Unit.INSTANCE;
    }

    @Override // kotlinx.coroutines.flow.internal.ChannelFlow
    public String toString() {
        Function2<ProducerScope<? super T>, Continuation<? super Unit>, Object> function2 = this.block;
        String channelFlow = super.toString();
        return "block[" + function2 + "] -> " + channelFlow;
    }
}
