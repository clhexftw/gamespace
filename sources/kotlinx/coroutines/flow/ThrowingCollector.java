package kotlinx.coroutines.flow;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
/* compiled from: Emitters.kt */
/* loaded from: classes2.dex */
public final class ThrowingCollector implements FlowCollector<Object> {
    public final Throwable e;

    @Override // kotlinx.coroutines.flow.FlowCollector
    public Object emit(Object obj, Continuation<? super Unit> continuation) {
        throw this.e;
    }
}
