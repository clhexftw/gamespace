package kotlinx.coroutines.channels;

import java.util.ArrayList;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.channels.AbstractSendChannel;
import kotlinx.coroutines.internal.OnUndeliveredElementKt;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.internal.UndeliveredElementException;
/* compiled from: LinkedListChannel.kt */
/* loaded from: classes2.dex */
public class LinkedListChannel<E> extends AbstractChannel<E> {
    @Override // kotlinx.coroutines.channels.AbstractChannel
    protected final boolean isBufferAlwaysEmpty() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.channels.AbstractChannel
    public final boolean isBufferEmpty() {
        return true;
    }

    public LinkedListChannel(Function1<? super E, Unit> function1) {
        super(function1);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    public Object offerInternal(E e) {
        ReceiveOrClosed<?> sendBuffered;
        do {
            Object offerInternal = super.offerInternal(e);
            Symbol symbol = AbstractChannelKt.OFFER_SUCCESS;
            if (offerInternal == symbol) {
                return symbol;
            }
            if (offerInternal == AbstractChannelKt.OFFER_FAILED) {
                sendBuffered = sendBuffered(e);
                if (sendBuffered == null) {
                    return symbol;
                }
            } else if (offerInternal instanceof Closed) {
                return offerInternal;
            } else {
                throw new IllegalStateException(("Invalid offerInternal result " + offerInternal).toString());
            }
        } while (!(sendBuffered instanceof Closed));
        return sendBuffered;
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    /* renamed from: onCancelIdempotentList-w-w6eGU */
    protected void mo2167onCancelIdempotentListww6eGU(Object obj, Closed<?> closed) {
        Intrinsics.checkNotNullParameter(closed, "closed");
        UndeliveredElementException undeliveredElementException = null;
        if (obj != null) {
            if (obj instanceof ArrayList) {
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type java.util.ArrayList<E of kotlinx.coroutines.internal.InlineList>{ kotlin.collections.TypeAliasesKt.ArrayList<E of kotlinx.coroutines.internal.InlineList> }");
                ArrayList arrayList = (ArrayList) obj;
                UndeliveredElementException undeliveredElementException2 = null;
                for (int size = arrayList.size() - 1; -1 < size; size--) {
                    Send send = (Send) arrayList.get(size);
                    if (send instanceof AbstractSendChannel.SendBuffered) {
                        Function1<E, Unit> function1 = this.onUndeliveredElement;
                        undeliveredElementException2 = function1 != null ? OnUndeliveredElementKt.callUndeliveredElementCatchingException(function1, ((AbstractSendChannel.SendBuffered) send).element, undeliveredElementException2) : null;
                    } else {
                        send.resumeSendClosed(closed);
                    }
                }
                undeliveredElementException = undeliveredElementException2;
            } else {
                Send send2 = (Send) obj;
                if (send2 instanceof AbstractSendChannel.SendBuffered) {
                    Function1<E, Unit> function12 = this.onUndeliveredElement;
                    if (function12 != null) {
                        undeliveredElementException = OnUndeliveredElementKt.callUndeliveredElementCatchingException(function12, ((AbstractSendChannel.SendBuffered) send2).element, null);
                    }
                } else {
                    send2.resumeSendClosed(closed);
                }
            }
        }
        if (undeliveredElementException != null) {
            throw undeliveredElementException;
        }
    }
}
