package kotlinx.coroutines.internal;

import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.ThreadContextElement;
/* compiled from: ThreadContext.kt */
/* loaded from: classes2.dex */
final class ThreadState {
    public final CoroutineContext context;
    private final ThreadContextElement<Object>[] elements;
    private int i;
    private final Object[] values;

    public ThreadState(CoroutineContext context, int i) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.values = new Object[i];
        this.elements = new ThreadContextElement[i];
    }

    public final void append(ThreadContextElement<?> element, Object obj) {
        Intrinsics.checkNotNullParameter(element, "element");
        Object[] objArr = this.values;
        int i = this.i;
        objArr[i] = obj;
        ThreadContextElement<Object>[] threadContextElementArr = this.elements;
        this.i = i + 1;
        threadContextElementArr[i] = element;
    }

    public final void restore(CoroutineContext context) {
        Intrinsics.checkNotNullParameter(context, "context");
        int length = this.elements.length - 1;
        if (length < 0) {
            return;
        }
        while (true) {
            int i = length - 1;
            ThreadContextElement<Object> threadContextElement = this.elements[length];
            Intrinsics.checkNotNull(threadContextElement);
            threadContextElement.restoreThreadContext(context, this.values[length]);
            if (i < 0) {
                return;
            }
            length = i;
        }
    }
}
