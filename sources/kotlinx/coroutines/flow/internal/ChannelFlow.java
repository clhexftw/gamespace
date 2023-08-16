package kotlinx.coroutines.flow.internal;

import java.util.ArrayList;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.DebugStringsKt;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
/* compiled from: ChannelFlow.kt */
/* loaded from: classes2.dex */
public abstract class ChannelFlow<T> implements Flow {
    public final int capacity;
    public final CoroutineContext context;
    public final BufferOverflow onBufferOverflow;

    protected String additionalToStringProps() {
        return null;
    }

    @Override // kotlinx.coroutines.flow.Flow
    public Object collect(FlowCollector<? super T> flowCollector, Continuation<? super Unit> continuation) {
        return collect$suspendImpl(this, flowCollector, continuation);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract Object collectTo(ProducerScope<? super T> producerScope, Continuation<? super Unit> continuation);

    public ChannelFlow(CoroutineContext context, int i, BufferOverflow onBufferOverflow) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(onBufferOverflow, "onBufferOverflow");
        this.context = context;
        this.capacity = i;
        this.onBufferOverflow = onBufferOverflow;
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(i != -1)) {
                throw new AssertionError();
            }
        }
    }

    public final Function2<ProducerScope<? super T>, Continuation<? super Unit>, Object> getCollectToFun$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host() {
        return new ChannelFlow$collectToFun$1(this, null);
    }

    public final int getProduceCapacity$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host() {
        int i = this.capacity;
        if (i == -3) {
            return -2;
        }
        return i;
    }

    public ReceiveChannel<T> produceImpl(CoroutineScope scope) {
        Intrinsics.checkNotNullParameter(scope, "scope");
        return ProduceKt.produce$default(scope, this.context, getProduceCapacity$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(), this.onBufferOverflow, CoroutineStart.ATOMIC, null, getCollectToFun$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(), 16, null);
    }

    static /* synthetic */ Object collect$suspendImpl(ChannelFlow<T> channelFlow, FlowCollector<? super T> flowCollector, Continuation<? super Unit> continuation) {
        Object coroutine_suspended;
        Object coroutineScope = CoroutineScopeKt.coroutineScope(new ChannelFlow$collect$2(flowCollector, channelFlow, null), continuation);
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        return coroutineScope == coroutine_suspended ? coroutineScope : Unit.INSTANCE;
    }

    public String toString() {
        String joinToString$default;
        ArrayList arrayList = new ArrayList(4);
        String additionalToStringProps = additionalToStringProps();
        if (additionalToStringProps != null) {
            arrayList.add(additionalToStringProps);
        }
        CoroutineContext coroutineContext = this.context;
        if (coroutineContext != EmptyCoroutineContext.INSTANCE) {
            arrayList.add("context=" + coroutineContext);
        }
        int i = this.capacity;
        if (i != -3) {
            arrayList.add("capacity=" + i);
        }
        BufferOverflow bufferOverflow = this.onBufferOverflow;
        if (bufferOverflow != BufferOverflow.SUSPEND) {
            arrayList.add("onBufferOverflow=" + bufferOverflow);
        }
        String classSimpleName = DebugStringsKt.getClassSimpleName(this);
        joinToString$default = CollectionsKt___CollectionsKt.joinToString$default(arrayList, ", ", null, null, 0, null, null, 62, null);
        return classSimpleName + "[" + joinToString$default + "]";
    }
}
