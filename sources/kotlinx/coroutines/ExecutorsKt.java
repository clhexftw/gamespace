package kotlinx.coroutines;

import java.util.concurrent.Executor;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: Executors.kt */
/* loaded from: classes2.dex */
public final class ExecutorsKt {
    public static final CoroutineDispatcher from(Executor executor) {
        CoroutineDispatcher coroutineDispatcher;
        Intrinsics.checkNotNullParameter(executor, "<this>");
        DispatcherExecutor dispatcherExecutor = executor instanceof DispatcherExecutor ? (DispatcherExecutor) executor : null;
        return (dispatcherExecutor == null || (coroutineDispatcher = dispatcherExecutor.dispatcher) == null) ? new ExecutorCoroutineDispatcherImpl(executor) : coroutineDispatcher;
    }
}
