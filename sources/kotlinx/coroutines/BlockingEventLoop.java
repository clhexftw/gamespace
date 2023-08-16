package kotlinx.coroutines;

import kotlin.jvm.internal.Intrinsics;
/* compiled from: EventLoop.kt */
/* loaded from: classes.dex */
public final class BlockingEventLoop extends EventLoopImplBase {
    private final Thread thread;

    @Override // kotlinx.coroutines.EventLoopImplPlatform
    protected Thread getThread() {
        return this.thread;
    }

    public BlockingEventLoop(Thread thread) {
        Intrinsics.checkNotNullParameter(thread, "thread");
        this.thread = thread;
    }
}
