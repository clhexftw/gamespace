package kotlinx.coroutines.channels;

import java.util.concurrent.CancellationException;
import kotlin.coroutines.Continuation;
/* compiled from: Channel.kt */
/* loaded from: classes2.dex */
public interface ReceiveChannel<E> {
    void cancel(CancellationException cancellationException);

    /* renamed from: receiveCatching-JP2dKIU */
    Object mo2168receiveCatchingJP2dKIU(Continuation<? super ChannelResult<? extends E>> continuation);
}
