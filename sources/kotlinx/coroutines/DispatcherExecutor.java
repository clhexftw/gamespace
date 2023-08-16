package kotlinx.coroutines;

import java.util.concurrent.Executor;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.internal.Intrinsics;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Executors.kt */
/* loaded from: classes2.dex */
public final class DispatcherExecutor implements Executor {
    public final CoroutineDispatcher dispatcher;

    @Override // java.util.concurrent.Executor
    public void execute(Runnable block) {
        Intrinsics.checkNotNullParameter(block, "block");
        this.dispatcher.mo2186dispatch(EmptyCoroutineContext.INSTANCE, block);
    }

    public String toString() {
        return this.dispatcher.toString();
    }
}
