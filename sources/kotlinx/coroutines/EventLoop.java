package kotlinx.coroutines;

import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.ArrayQueue;
/* compiled from: EventLoop.common.kt */
/* loaded from: classes2.dex */
public abstract class EventLoop extends CoroutineDispatcher {
    private boolean shared;
    private ArrayQueue<DispatchedTask<?>> unconfinedQueue;
    private long useCount;

    private final long delta(boolean z) {
        return z ? 4294967296L : 1L;
    }

    public void shutdown() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public long getNextTime() {
        ArrayQueue<DispatchedTask<?>> arrayQueue = this.unconfinedQueue;
        return (arrayQueue == null || arrayQueue.isEmpty()) ? Long.MAX_VALUE : 0L;
    }

    public final boolean processUnconfinedEvent() {
        DispatchedTask<?> removeFirstOrNull;
        ArrayQueue<DispatchedTask<?>> arrayQueue = this.unconfinedQueue;
        if (arrayQueue == null || (removeFirstOrNull = arrayQueue.removeFirstOrNull()) == null) {
            return false;
        }
        removeFirstOrNull.run();
        return true;
    }

    public final void dispatchUnconfined(DispatchedTask<?> task) {
        Intrinsics.checkNotNullParameter(task, "task");
        ArrayQueue<DispatchedTask<?>> arrayQueue = this.unconfinedQueue;
        if (arrayQueue == null) {
            arrayQueue = new ArrayQueue<>();
            this.unconfinedQueue = arrayQueue;
        }
        arrayQueue.addLast(task);
    }

    public final boolean isUnconfinedLoopActive() {
        return this.useCount >= delta(true);
    }

    public final boolean isUnconfinedQueueEmpty() {
        ArrayQueue<DispatchedTask<?>> arrayQueue = this.unconfinedQueue;
        if (arrayQueue != null) {
            return arrayQueue.isEmpty();
        }
        return true;
    }

    public static /* synthetic */ void incrementUseCount$default(EventLoop eventLoop, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: incrementUseCount");
        }
        if ((i & 1) != 0) {
            z = false;
        }
        eventLoop.incrementUseCount(z);
    }

    public final void incrementUseCount(boolean z) {
        this.useCount += delta(z);
        if (z) {
            return;
        }
        this.shared = true;
    }

    public final void decrementUseCount(boolean z) {
        long delta = this.useCount - delta(z);
        this.useCount = delta;
        if (delta > 0) {
            return;
        }
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(this.useCount == 0)) {
                throw new AssertionError();
            }
        }
        if (this.shared) {
            shutdown();
        }
    }
}
