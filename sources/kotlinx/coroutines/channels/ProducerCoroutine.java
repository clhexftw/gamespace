package kotlinx.coroutines.channels;

import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineExceptionHandlerKt;
import kotlinx.coroutines.channels.SendChannel;
/* compiled from: Produce.kt */
/* loaded from: classes2.dex */
final class ProducerCoroutine<E> extends ChannelCoroutine<E> implements ProducerScope<E> {
    @Override // kotlinx.coroutines.channels.ProducerScope
    public /* bridge */ /* synthetic */ SendChannel getChannel() {
        return getChannel();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProducerCoroutine(CoroutineContext parentContext, Channel<E> channel) {
        super(parentContext, channel, true, true);
        Intrinsics.checkNotNullParameter(parentContext, "parentContext");
        Intrinsics.checkNotNullParameter(channel, "channel");
    }

    @Override // kotlinx.coroutines.AbstractCoroutine, kotlinx.coroutines.JobSupport, kotlinx.coroutines.Job
    public boolean isActive() {
        return super.isActive();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.AbstractCoroutine
    public void onCompleted(Unit value) {
        Intrinsics.checkNotNullParameter(value, "value");
        SendChannel.DefaultImpls.close$default(get_channel(), null, 1, null);
    }

    @Override // kotlinx.coroutines.AbstractCoroutine
    protected void onCancelled(Throwable cause, boolean z) {
        Intrinsics.checkNotNullParameter(cause, "cause");
        if (get_channel().close(cause) || z) {
            return;
        }
        CoroutineExceptionHandlerKt.handleCoroutineException(getContext(), cause);
    }
}
