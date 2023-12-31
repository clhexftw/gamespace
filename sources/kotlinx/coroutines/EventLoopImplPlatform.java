package kotlinx.coroutines;

import java.util.concurrent.locks.LockSupport;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.EventLoopImplBase;
/* compiled from: EventLoop.kt */
/* loaded from: classes2.dex */
public abstract class EventLoopImplPlatform extends EventLoop {
    protected abstract Thread getThread();

    /* JADX INFO: Access modifiers changed from: protected */
    public final void unpark() {
        Thread thread = getThread();
        if (Thread.currentThread() != thread) {
            AbstractTimeSourceKt.getTimeSource();
            LockSupport.unpark(thread);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void reschedule(long j, EventLoopImplBase.DelayedTask delayedTask) {
        Intrinsics.checkNotNullParameter(delayedTask, "delayedTask");
        DefaultExecutor.INSTANCE.schedule(j, delayedTask);
    }
}
