package kotlinx.coroutines;

import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Builders.common.kt */
/* loaded from: classes2.dex */
public class StandaloneCoroutine extends AbstractCoroutine<Unit> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StandaloneCoroutine(CoroutineContext parentContext, boolean z) {
        super(parentContext, true, z);
        Intrinsics.checkNotNullParameter(parentContext, "parentContext");
    }

    @Override // kotlinx.coroutines.JobSupport
    protected boolean handleJobException(Throwable exception) {
        Intrinsics.checkNotNullParameter(exception, "exception");
        CoroutineExceptionHandlerKt.handleCoroutineException(getContext(), exception);
        return true;
    }
}
