package kotlinx.coroutines;

import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;
import kotlinx.atomicfu.AtomicBoolean;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicRef;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.internal.ThreadSafeHeap;
import kotlinx.coroutines.internal.ThreadSafeHeapNode;
/* compiled from: EventLoop.common.kt */
/* loaded from: classes.dex */
public abstract class EventLoopImplBase extends EventLoopImplPlatform implements Delay {
    private final AtomicRef<Object> _queue = AtomicFU.atomic((Object) null);
    private final AtomicRef<DelayedTaskQueue> _delayed = AtomicFU.atomic((Object) null);
    private final AtomicBoolean _isCompleted = AtomicFU.atomic(false);

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isCompleted() {
        return this._isCompleted.getValue();
    }

    private final void setCompleted(boolean z) {
        this._isCompleted.setValue(z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isEmpty() {
        Symbol symbol;
        if (isUnconfinedQueueEmpty()) {
            DelayedTaskQueue value = this._delayed.getValue();
            if (value == null || value.isEmpty()) {
                Object value2 = this._queue.getValue();
                if (value2 != null) {
                    if (value2 instanceof LockFreeTaskQueueCore) {
                        return ((LockFreeTaskQueueCore) value2).isEmpty();
                    }
                    symbol = EventLoop_commonKt.CLOSED_EMPTY;
                    if (value2 != symbol) {
                        return false;
                    }
                }
                return true;
            }
            return false;
        }
        return false;
    }

    @Override // kotlinx.coroutines.EventLoop
    protected long getNextTime() {
        DelayedTask peek;
        long coerceAtLeast;
        Symbol symbol;
        if (super.getNextTime() == 0) {
            return 0L;
        }
        Object value = this._queue.getValue();
        if (value != null) {
            if (!(value instanceof LockFreeTaskQueueCore)) {
                symbol = EventLoop_commonKt.CLOSED_EMPTY;
                return value == symbol ? Long.MAX_VALUE : 0L;
            } else if (!((LockFreeTaskQueueCore) value).isEmpty()) {
                return 0L;
            }
        }
        DelayedTaskQueue value2 = this._delayed.getValue();
        if (value2 == null || (peek = value2.peek()) == null) {
            return Long.MAX_VALUE;
        }
        long j = peek.nanoTime;
        AbstractTimeSourceKt.getTimeSource();
        coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(j - System.nanoTime(), 0L);
        return coerceAtLeast;
    }

    @Override // kotlinx.coroutines.EventLoop
    public void shutdown() {
        ThreadLocalEventLoop.INSTANCE.resetEventLoop$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host();
        setCompleted(true);
        closeQueue();
        do {
        } while (processNextEvent() <= 0);
        rescheduleAllDelayed();
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0053  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public long processNextEvent() {
        /*
            r9 = this;
            boolean r0 = r9.processUnconfinedEvent()
            r1 = 0
            if (r0 == 0) goto L9
            return r1
        L9:
            kotlinx.atomicfu.AtomicRef<kotlinx.coroutines.EventLoopImplBase$DelayedTaskQueue> r0 = r9._delayed
            java.lang.Object r0 = r0.getValue()
            kotlinx.coroutines.EventLoopImplBase$DelayedTaskQueue r0 = (kotlinx.coroutines.EventLoopImplBase.DelayedTaskQueue) r0
            if (r0 == 0) goto L49
            boolean r3 = r0.isEmpty()
            if (r3 != 0) goto L49
            kotlinx.coroutines.AbstractTimeSourceKt.getTimeSource()
            long r3 = java.lang.System.nanoTime()
        L20:
            monitor-enter(r0)
            kotlinx.coroutines.internal.ThreadSafeHeapNode r5 = r0.firstImpl()     // Catch: java.lang.Throwable -> L46
            r6 = 0
            if (r5 != 0) goto L2a
            monitor-exit(r0)
            goto L41
        L2a:
            kotlinx.coroutines.EventLoopImplBase$DelayedTask r5 = (kotlinx.coroutines.EventLoopImplBase.DelayedTask) r5     // Catch: java.lang.Throwable -> L46
            boolean r7 = r5.timeToExecute(r3)     // Catch: java.lang.Throwable -> L46
            r8 = 0
            if (r7 == 0) goto L38
            boolean r5 = r9.enqueueImpl(r5)     // Catch: java.lang.Throwable -> L46
            goto L39
        L38:
            r5 = r8
        L39:
            if (r5 == 0) goto L40
            kotlinx.coroutines.internal.ThreadSafeHeapNode r5 = r0.removeAtImpl(r8)     // Catch: java.lang.Throwable -> L46
            r6 = r5
        L40:
            monitor-exit(r0)
        L41:
            kotlinx.coroutines.EventLoopImplBase$DelayedTask r6 = (kotlinx.coroutines.EventLoopImplBase.DelayedTask) r6
            if (r6 != 0) goto L20
            goto L49
        L46:
            r9 = move-exception
            monitor-exit(r0)
            throw r9
        L49:
            java.lang.Runnable r0 = r9.dequeue()
            if (r0 == 0) goto L53
            r0.run()
            return r1
        L53:
            long r0 = r9.getNextTime()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.EventLoopImplBase.processNextEvent():long");
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    /* renamed from: dispatch */
    public final void mo112dispatch(CoroutineContext context, Runnable block) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(block, "block");
        enqueue(block);
    }

    public void enqueue(Runnable task) {
        Intrinsics.checkNotNullParameter(task, "task");
        if (enqueueImpl(task)) {
            unpark();
        } else {
            DefaultExecutor.INSTANCE.enqueue(task);
        }
    }

    private final boolean enqueueImpl(Runnable runnable) {
        Symbol symbol;
        AtomicRef<Object> atomicRef = this._queue;
        while (true) {
            Object value = atomicRef.getValue();
            if (isCompleted()) {
                return false;
            }
            if (value == null) {
                if (this._queue.compareAndSet(null, runnable)) {
                    return true;
                }
            } else if (!(value instanceof LockFreeTaskQueueCore)) {
                symbol = EventLoop_commonKt.CLOSED_EMPTY;
                if (value == symbol) {
                    return false;
                }
                LockFreeTaskQueueCore lockFreeTaskQueueCore = new LockFreeTaskQueueCore(8, true);
                Intrinsics.checkNotNull(value, "null cannot be cast to non-null type java.lang.Runnable{ kotlinx.coroutines.RunnableKt.Runnable }");
                lockFreeTaskQueueCore.addLast((Runnable) value);
                lockFreeTaskQueueCore.addLast(runnable);
                if (this._queue.compareAndSet(value, lockFreeTaskQueueCore)) {
                    return true;
                }
            } else {
                Intrinsics.checkNotNull(value, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeTaskQueueCore<java.lang.Runnable{ kotlinx.coroutines.RunnableKt.Runnable }>{ kotlinx.coroutines.EventLoop_commonKt.Queue<java.lang.Runnable{ kotlinx.coroutines.RunnableKt.Runnable }> }");
                LockFreeTaskQueueCore lockFreeTaskQueueCore2 = (LockFreeTaskQueueCore) value;
                int addLast = lockFreeTaskQueueCore2.addLast(runnable);
                if (addLast == 0) {
                    return true;
                }
                if (addLast == 1) {
                    this._queue.compareAndSet(value, lockFreeTaskQueueCore2.next());
                } else if (addLast == 2) {
                    return false;
                }
            }
        }
    }

    private final Runnable dequeue() {
        Symbol symbol;
        AtomicRef<Object> atomicRef = this._queue;
        while (true) {
            Object value = atomicRef.getValue();
            if (value == null) {
                return null;
            }
            if (!(value instanceof LockFreeTaskQueueCore)) {
                symbol = EventLoop_commonKt.CLOSED_EMPTY;
                if (value == symbol) {
                    return null;
                }
                if (this._queue.compareAndSet(value, null)) {
                    Intrinsics.checkNotNull(value, "null cannot be cast to non-null type java.lang.Runnable{ kotlinx.coroutines.RunnableKt.Runnable }");
                    return (Runnable) value;
                }
            } else {
                Intrinsics.checkNotNull(value, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeTaskQueueCore<java.lang.Runnable{ kotlinx.coroutines.RunnableKt.Runnable }>{ kotlinx.coroutines.EventLoop_commonKt.Queue<java.lang.Runnable{ kotlinx.coroutines.RunnableKt.Runnable }> }");
                LockFreeTaskQueueCore lockFreeTaskQueueCore = (LockFreeTaskQueueCore) value;
                Object removeFirstOrNull = lockFreeTaskQueueCore.removeFirstOrNull();
                if (removeFirstOrNull != LockFreeTaskQueueCore.REMOVE_FROZEN) {
                    return (Runnable) removeFirstOrNull;
                }
                this._queue.compareAndSet(value, lockFreeTaskQueueCore.next());
            }
        }
    }

    private final void closeQueue() {
        Symbol symbol;
        Symbol symbol2;
        if (DebugKt.getASSERTIONS_ENABLED() && !isCompleted()) {
            throw new AssertionError();
        }
        AtomicRef<Object> atomicRef = this._queue;
        while (true) {
            Object value = atomicRef.getValue();
            if (value == null) {
                AtomicRef<Object> atomicRef2 = this._queue;
                symbol = EventLoop_commonKt.CLOSED_EMPTY;
                if (atomicRef2.compareAndSet(null, symbol)) {
                    return;
                }
            } else if (!(value instanceof LockFreeTaskQueueCore)) {
                symbol2 = EventLoop_commonKt.CLOSED_EMPTY;
                if (value == symbol2) {
                    return;
                }
                LockFreeTaskQueueCore lockFreeTaskQueueCore = new LockFreeTaskQueueCore(8, true);
                Intrinsics.checkNotNull(value, "null cannot be cast to non-null type java.lang.Runnable{ kotlinx.coroutines.RunnableKt.Runnable }");
                lockFreeTaskQueueCore.addLast((Runnable) value);
                if (this._queue.compareAndSet(value, lockFreeTaskQueueCore)) {
                    return;
                }
            } else {
                ((LockFreeTaskQueueCore) value).close();
                return;
            }
        }
    }

    public final void schedule(long j, DelayedTask delayedTask) {
        Intrinsics.checkNotNullParameter(delayedTask, "delayedTask");
        int scheduleImpl = scheduleImpl(j, delayedTask);
        if (scheduleImpl == 0) {
            if (shouldUnpark(delayedTask)) {
                unpark();
            }
        } else if (scheduleImpl == 1) {
            reschedule(j, delayedTask);
        } else if (scheduleImpl != 2) {
            throw new IllegalStateException("unexpected result".toString());
        }
    }

    private final boolean shouldUnpark(DelayedTask delayedTask) {
        DelayedTaskQueue value = this._delayed.getValue();
        return (value != null ? value.peek() : null) == delayedTask;
    }

    private final int scheduleImpl(long j, DelayedTask delayedTask) {
        if (isCompleted()) {
            return 1;
        }
        DelayedTaskQueue value = this._delayed.getValue();
        if (value == null) {
            this._delayed.compareAndSet(null, new DelayedTaskQueue(j));
            DelayedTaskQueue value2 = this._delayed.getValue();
            Intrinsics.checkNotNull(value2);
            value = value2;
        }
        return delayedTask.scheduleTask(j, value, this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void resetAll() {
        this._queue.setValue(null);
        this._delayed.setValue(null);
    }

    private final void rescheduleAllDelayed() {
        DelayedTask removeFirstOrNull;
        AbstractTimeSourceKt.getTimeSource();
        long nanoTime = System.nanoTime();
        while (true) {
            DelayedTaskQueue value = this._delayed.getValue();
            if (value == null || (removeFirstOrNull = value.removeFirstOrNull()) == null) {
                return;
            }
            reschedule(nanoTime, removeFirstOrNull);
        }
    }

    /* compiled from: EventLoop.common.kt */
    /* loaded from: classes.dex */
    public static abstract class DelayedTask implements Runnable, Comparable<DelayedTask>, DisposableHandle, ThreadSafeHeapNode {
        private volatile Object _heap;
        private int index;
        public long nanoTime;

        @Override // kotlinx.coroutines.internal.ThreadSafeHeapNode
        public ThreadSafeHeap<?> getHeap() {
            Object obj = this._heap;
            if (obj instanceof ThreadSafeHeap) {
                return (ThreadSafeHeap) obj;
            }
            return null;
        }

        @Override // kotlinx.coroutines.internal.ThreadSafeHeapNode
        public void setHeap(ThreadSafeHeap<?> threadSafeHeap) {
            Symbol symbol;
            Object obj = this._heap;
            symbol = EventLoop_commonKt.DISPOSED_TASK;
            if (!(obj != symbol)) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            this._heap = threadSafeHeap;
        }

        @Override // kotlinx.coroutines.internal.ThreadSafeHeapNode
        public int getIndex() {
            return this.index;
        }

        @Override // kotlinx.coroutines.internal.ThreadSafeHeapNode
        public void setIndex(int i) {
            this.index = i;
        }

        @Override // java.lang.Comparable
        public int compareTo(DelayedTask other) {
            Intrinsics.checkNotNullParameter(other, "other");
            int i = ((this.nanoTime - other.nanoTime) > 0L ? 1 : ((this.nanoTime - other.nanoTime) == 0L ? 0 : -1));
            if (i > 0) {
                return 1;
            }
            return i < 0 ? -1 : 0;
        }

        public final boolean timeToExecute(long j) {
            return j - this.nanoTime >= 0;
        }

        public final synchronized int scheduleTask(long j, DelayedTaskQueue delayed, EventLoopImplBase eventLoop) {
            Symbol symbol;
            Intrinsics.checkNotNullParameter(delayed, "delayed");
            Intrinsics.checkNotNullParameter(eventLoop, "eventLoop");
            Object obj = this._heap;
            symbol = EventLoop_commonKt.DISPOSED_TASK;
            if (obj == symbol) {
                return 2;
            }
            synchronized (delayed) {
                DelayedTask firstImpl = delayed.firstImpl();
                if (eventLoop.isCompleted()) {
                    return 1;
                }
                if (firstImpl == null) {
                    delayed.timeNow = j;
                } else {
                    long j2 = firstImpl.nanoTime;
                    if (j2 - j < 0) {
                        j = j2;
                    }
                    if (j - delayed.timeNow > 0) {
                        delayed.timeNow = j;
                    }
                }
                long j3 = this.nanoTime;
                long j4 = delayed.timeNow;
                if (j3 - j4 < 0) {
                    this.nanoTime = j4;
                }
                delayed.addImpl(this);
                return 0;
            }
        }

        @Override // kotlinx.coroutines.DisposableHandle
        public final synchronized void dispose() {
            Symbol symbol;
            Symbol symbol2;
            Object obj = this._heap;
            symbol = EventLoop_commonKt.DISPOSED_TASK;
            if (obj == symbol) {
                return;
            }
            DelayedTaskQueue delayedTaskQueue = obj instanceof DelayedTaskQueue ? (DelayedTaskQueue) obj : null;
            if (delayedTaskQueue != null) {
                delayedTaskQueue.remove(this);
            }
            symbol2 = EventLoop_commonKt.DISPOSED_TASK;
            this._heap = symbol2;
        }

        public String toString() {
            long j = this.nanoTime;
            return "Delayed[nanos=" + j + "]";
        }
    }

    /* compiled from: EventLoop.common.kt */
    /* loaded from: classes.dex */
    public static final class DelayedTaskQueue extends ThreadSafeHeap<DelayedTask> {
        public long timeNow;

        public DelayedTaskQueue(long j) {
            this.timeNow = j;
        }
    }
}
