package kotlinx.coroutines.channels;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: Channel.kt */
/* loaded from: classes2.dex */
public final class ChannelKt {
    public static /* synthetic */ Channel Channel$default(int i, BufferOverflow bufferOverflow, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        if ((i2 & 2) != 0) {
            bufferOverflow = BufferOverflow.SUSPEND;
        }
        if ((i2 & 4) != 0) {
            function1 = null;
        }
        return Channel(i, bufferOverflow, function1);
    }

    public static final <E> Channel<E> Channel(int i, BufferOverflow onBufferOverflow, Function1<? super E, Unit> function1) {
        Intrinsics.checkNotNullParameter(onBufferOverflow, "onBufferOverflow");
        if (i == -2) {
            return new ArrayChannel(onBufferOverflow == BufferOverflow.SUSPEND ? Channel.Factory.getCHANNEL_DEFAULT_CAPACITY$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host() : 1, onBufferOverflow, function1);
        } else if (i == -1) {
            if ((onBufferOverflow != BufferOverflow.SUSPEND ? 0 : 1) == 0) {
                throw new IllegalArgumentException("CONFLATED capacity cannot be used with non-default onBufferOverflow".toString());
            }
            return new ConflatedChannel(function1);
        } else if (i == 0) {
            if (onBufferOverflow == BufferOverflow.SUSPEND) {
                return new RendezvousChannel(function1);
            }
            return new ArrayChannel(1, onBufferOverflow, function1);
        } else if (i == Integer.MAX_VALUE) {
            return new LinkedListChannel(function1);
        } else {
            if (i == 1 && onBufferOverflow == BufferOverflow.DROP_OLDEST) {
                return new ConflatedChannel(function1);
            }
            return new ArrayChannel(i, onBufferOverflow, function1);
        }
    }
}
