package kotlinx.coroutines.scheduling;

import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
/* compiled from: Dispatcher.kt */
/* loaded from: classes2.dex */
final class UnlimitedIoScheduler extends CoroutineDispatcher {
    public static final UnlimitedIoScheduler INSTANCE = new UnlimitedIoScheduler();

    private UnlimitedIoScheduler() {
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    /* renamed from: dispatch */
    public void mo2186dispatch(CoroutineContext context, Runnable block) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(block, "block");
        DefaultScheduler.INSTANCE.dispatchWithContext$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(block, TasksKt.BlockingContext, false);
    }
}
