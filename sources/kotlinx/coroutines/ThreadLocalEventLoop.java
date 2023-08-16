package kotlinx.coroutines;

import kotlin.jvm.internal.Intrinsics;
/* compiled from: EventLoop.common.kt */
/* loaded from: classes.dex */
public final class ThreadLocalEventLoop {
    public static final ThreadLocalEventLoop INSTANCE = new ThreadLocalEventLoop();
    private static final ThreadLocal<EventLoop> ref = new ThreadLocal<>();

    private ThreadLocalEventLoop() {
    }

    public final EventLoop getEventLoop$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host() {
        ThreadLocal<EventLoop> threadLocal = ref;
        EventLoop eventLoop = threadLocal.get();
        if (eventLoop == null) {
            EventLoop createEventLoop = EventLoopKt.createEventLoop();
            threadLocal.set(createEventLoop);
            return createEventLoop;
        }
        return eventLoop;
    }

    public final void resetEventLoop$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host() {
        ref.set(null);
    }

    public final void setEventLoop$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(EventLoop eventLoop) {
        Intrinsics.checkNotNullParameter(eventLoop, "eventLoop");
        ref.set(eventLoop);
    }
}
