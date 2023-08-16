package kotlinx.coroutines;

import java.io.Closeable;
import kotlin.coroutines.AbstractCoroutineContextKey;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: Executors.kt */
/* loaded from: classes.dex */
public abstract class ExecutorCoroutineDispatcher extends CoroutineDispatcher implements Closeable {
    public static final Key Key = new Key(null);

    /* compiled from: Executors.kt */
    /* loaded from: classes.dex */
    public static final class Key extends AbstractCoroutineContextKey<CoroutineDispatcher, ExecutorCoroutineDispatcher> {
        public /* synthetic */ Key(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Key() {
            super(CoroutineDispatcher.Key, new Function1<CoroutineContext.Element, ExecutorCoroutineDispatcher>() { // from class: kotlinx.coroutines.ExecutorCoroutineDispatcher.Key.1
                @Override // kotlin.jvm.functions.Function1
                public final ExecutorCoroutineDispatcher invoke(CoroutineContext.Element it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    if (it instanceof ExecutorCoroutineDispatcher) {
                        return (ExecutorCoroutineDispatcher) it;
                    }
                    return null;
                }
            });
        }
    }
}
