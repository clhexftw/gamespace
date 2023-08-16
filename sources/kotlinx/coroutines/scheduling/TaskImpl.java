package kotlinx.coroutines.scheduling;

import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.DebugStringsKt;
/* compiled from: Tasks.kt */
/* loaded from: classes.dex */
public final class TaskImpl extends Task {
    public final Runnable block;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TaskImpl(Runnable block, long j, TaskContext taskContext) {
        super(j, taskContext);
        Intrinsics.checkNotNullParameter(block, "block");
        Intrinsics.checkNotNullParameter(taskContext, "taskContext");
        this.block = block;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.block.run();
        } finally {
            this.taskContext.afterTask();
        }
    }

    public String toString() {
        String classSimpleName = DebugStringsKt.getClassSimpleName(this.block);
        String hexAddress = DebugStringsKt.getHexAddress(this.block);
        long j = this.submissionTime;
        TaskContext taskContext = this.taskContext;
        return "Task[" + classSimpleName + "@" + hexAddress + ", " + j + ", " + taskContext + "]";
    }
}
