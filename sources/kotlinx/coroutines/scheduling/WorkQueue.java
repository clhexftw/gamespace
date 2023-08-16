package kotlinx.coroutines.scheduling;

import java.util.concurrent.atomic.AtomicReferenceArray;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicInt;
import kotlinx.atomicfu.AtomicRef;
import kotlinx.coroutines.DebugKt;
/* compiled from: WorkQueue.kt */
/* loaded from: classes.dex */
public final class WorkQueue {
    private final AtomicReferenceArray<Task> buffer = new AtomicReferenceArray<>(128);
    private final AtomicRef<Task> lastScheduledTask = AtomicFU.atomic((Object) null);
    private final AtomicInt producerIndex = AtomicFU.atomic(0);
    private final AtomicInt consumerIndex = AtomicFU.atomic(0);
    private final AtomicInt blockingTasksInBuffer = AtomicFU.atomic(0);

    public final int getBufferSize$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host() {
        return this.producerIndex.getValue() - this.consumerIndex.getValue();
    }

    public final int getSize$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host() {
        Task value = this.lastScheduledTask.getValue();
        int bufferSize$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host = getBufferSize$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host();
        return value != null ? bufferSize$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host + 1 : bufferSize$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host;
    }

    public final Task poll() {
        Task andSet = this.lastScheduledTask.getAndSet(null);
        return andSet == null ? pollBuffer() : andSet;
    }

    public static /* synthetic */ Task add$default(WorkQueue workQueue, Task task, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return workQueue.add(task, z);
    }

    public final Task add(Task task, boolean z) {
        Intrinsics.checkNotNullParameter(task, "task");
        if (z) {
            return addLast(task);
        }
        Task andSet = this.lastScheduledTask.getAndSet(task);
        if (andSet == null) {
            return null;
        }
        return addLast(andSet);
    }

    private final Task addLast(Task task) {
        if (task.taskContext.getTaskMode() == 1) {
            this.blockingTasksInBuffer.incrementAndGet();
        }
        if (getBufferSize$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host() == 127) {
            return task;
        }
        int value = this.producerIndex.getValue() & 127;
        while (this.buffer.get(value) != null) {
            Thread.yield();
        }
        this.buffer.lazySet(value, task);
        this.producerIndex.incrementAndGet();
        return null;
    }

    private final void decrementIfBlocking(Task task) {
        if (task != null) {
            if (task.taskContext.getTaskMode() == 1) {
                int decrementAndGet = this.blockingTasksInBuffer.decrementAndGet();
                if (DebugKt.getASSERTIONS_ENABLED()) {
                    if (!(decrementAndGet >= 0)) {
                        throw new AssertionError();
                    }
                }
            }
        }
    }

    public final long tryStealFrom(WorkQueue victim) {
        Intrinsics.checkNotNullParameter(victim, "victim");
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(getBufferSize$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host() == 0)) {
                throw new AssertionError();
            }
        }
        Task pollBuffer = victim.pollBuffer();
        if (pollBuffer != null) {
            Task add$default = add$default(this, pollBuffer, false, 2, null);
            if (DebugKt.getASSERTIONS_ENABLED()) {
                if (add$default == null) {
                    return -1L;
                }
                throw new AssertionError();
            }
            return -1L;
        }
        return tryStealLastScheduled(victim, false);
    }

    public final long tryStealBlockingFrom(WorkQueue victim) {
        Intrinsics.checkNotNullParameter(victim, "victim");
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(getBufferSize$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host() == 0)) {
                throw new AssertionError();
            }
        }
        int value = victim.producerIndex.getValue();
        AtomicReferenceArray<Task> atomicReferenceArray = victim.buffer;
        for (int value2 = victim.consumerIndex.getValue(); value2 != value; value2++) {
            int i = value2 & 127;
            if (victim.blockingTasksInBuffer.getValue() == 0) {
                break;
            }
            Task task = atomicReferenceArray.get(i);
            if (task != null) {
                if ((task.taskContext.getTaskMode() == 1) && atomicReferenceArray.compareAndSet(i, task, null)) {
                    victim.blockingTasksInBuffer.decrementAndGet();
                    add$default(this, task, false, 2, null);
                    return -1L;
                }
            }
        }
        return tryStealLastScheduled(victim, true);
    }

    public final void offloadAllWorkTo(GlobalQueue globalQueue) {
        Intrinsics.checkNotNullParameter(globalQueue, "globalQueue");
        Task andSet = this.lastScheduledTask.getAndSet(null);
        if (andSet != null) {
            globalQueue.addLast(andSet);
        }
        do {
        } while (pollTo(globalQueue));
    }

    private final long tryStealLastScheduled(WorkQueue workQueue, boolean z) {
        Task value;
        do {
            value = workQueue.lastScheduledTask.getValue();
            if (value == null) {
                return -2L;
            }
            if (z) {
                if (!(value.taskContext.getTaskMode() == 1)) {
                    return -2L;
                }
            }
            long nanoTime = TasksKt.schedulerTimeSource.nanoTime() - value.submissionTime;
            long j = TasksKt.WORK_STEALING_TIME_RESOLUTION_NS;
            if (nanoTime < j) {
                return j - nanoTime;
            }
        } while (!workQueue.lastScheduledTask.compareAndSet(value, null));
        add$default(this, value, false, 2, null);
        return -1L;
    }

    private final boolean pollTo(GlobalQueue globalQueue) {
        Task pollBuffer = pollBuffer();
        if (pollBuffer == null) {
            return false;
        }
        globalQueue.addLast(pollBuffer);
        return true;
    }

    private final Task pollBuffer() {
        Task andSet;
        while (true) {
            int value = this.consumerIndex.getValue();
            if (value - this.producerIndex.getValue() == 0) {
                return null;
            }
            int i = value & 127;
            if (this.consumerIndex.compareAndSet(value, value + 1) && (andSet = this.buffer.getAndSet(i, null)) != null) {
                decrementIfBlocking(andSet);
                return andSet;
            }
        }
    }
}
