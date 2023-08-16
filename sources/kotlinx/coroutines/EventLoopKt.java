package kotlinx.coroutines;

import kotlin.jvm.internal.Intrinsics;
/* compiled from: EventLoop.kt */
/* loaded from: classes.dex */
public final class EventLoopKt {
    public static final EventLoop createEventLoop() {
        Thread currentThread = Thread.currentThread();
        Intrinsics.checkNotNullExpressionValue(currentThread, "currentThread()");
        return new BlockingEventLoop(currentThread);
    }
}
