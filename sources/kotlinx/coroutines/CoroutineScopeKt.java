package kotlinx.coroutines;

import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.ContextScope;
/* compiled from: CoroutineScope.kt */
/* loaded from: classes.dex */
public final class CoroutineScopeKt {
    public static final CoroutineScope CoroutineScope(CoroutineContext context) {
        CompletableJob Job$default;
        Intrinsics.checkNotNullParameter(context, "context");
        if (context.get(Job.Key) == null) {
            Job$default = JobKt__JobKt.Job$default(null, 1, null);
            context = context.plus(Job$default);
        }
        return new ContextScope(context);
    }
}
