package kotlinx.coroutines.channels;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
/* compiled from: Channel.kt */
/* loaded from: classes2.dex */
public interface SendChannel<E> {
    boolean close(Throwable th);

    void invokeOnClose(Function1<? super Throwable, Unit> function1);

    boolean isClosedForSend();

    /* renamed from: trySend-JP2dKIU */
    Object mo2169trySendJP2dKIU(E e);

    /* compiled from: Channel.kt */
    /* loaded from: classes2.dex */
    public static final class DefaultImpls {
        public static /* synthetic */ boolean close$default(SendChannel sendChannel, Throwable th, int i, Object obj) {
            if (obj == null) {
                if ((i & 1) != 0) {
                    th = null;
                }
                return sendChannel.close(th);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: close");
        }
    }
}
