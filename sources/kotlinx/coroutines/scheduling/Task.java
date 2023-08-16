package kotlinx.coroutines.scheduling;

import kotlin.jvm.internal.Intrinsics;
/* compiled from: Tasks.kt */
/* loaded from: classes.dex */
public abstract class Task implements Runnable {
    public long submissionTime;
    public TaskContext taskContext;

    public Task(long j, TaskContext taskContext) {
        Intrinsics.checkNotNullParameter(taskContext, "taskContext");
        this.submissionTime = j;
        this.taskContext = taskContext;
    }

    public Task() {
        this(0L, TasksKt.NonBlockingContext);
    }
}
