package kotlinx.coroutines;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: AbstractCoroutine.kt */
/* loaded from: classes2.dex */
public abstract class AbstractCoroutine<T> extends JobSupport implements Continuation<T>, CoroutineScope {
    private final CoroutineContext context;

    protected void onCancelled(Throwable cause, boolean z) {
        Intrinsics.checkNotNullParameter(cause, "cause");
    }

    protected void onCompleted(T t) {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AbstractCoroutine(CoroutineContext parentContext, boolean z, boolean z2) {
        super(z2);
        Intrinsics.checkNotNullParameter(parentContext, "parentContext");
        if (z) {
            initParentJob((Job) parentContext.get(Job.Key));
        }
        this.context = parentContext.plus(this);
    }

    @Override // kotlin.coroutines.Continuation
    public final CoroutineContext getContext() {
        return this.context;
    }

    @Override // kotlinx.coroutines.CoroutineScope
    public CoroutineContext getCoroutineContext() {
        return this.context;
    }

    @Override // kotlinx.coroutines.JobSupport, kotlinx.coroutines.Job
    public boolean isActive() {
        return super.isActive();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.JobSupport
    public String cancellationExceptionMessage() {
        String classSimpleName = DebugStringsKt.getClassSimpleName(this);
        return classSimpleName + " was cancelled";
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.coroutines.JobSupport
    protected final void onCompletionInternal(Object obj) {
        if (obj instanceof CompletedExceptionally) {
            CompletedExceptionally completedExceptionally = (CompletedExceptionally) obj;
            onCancelled(completedExceptionally.cause, completedExceptionally.getHandled());
            return;
        }
        onCompleted(obj);
    }

    @Override // kotlin.coroutines.Continuation
    public final void resumeWith(Object obj) {
        Object makeCompletingOnce$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host = makeCompletingOnce$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(CompletionStateKt.toState$default(obj, null, 1, null));
        if (makeCompletingOnce$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host == JobSupportKt.COMPLETING_WAITING_CHILDREN) {
            return;
        }
        afterResume(makeCompletingOnce$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host);
    }

    protected void afterResume(Object obj) {
        afterCompletion(obj);
    }

    @Override // kotlinx.coroutines.JobSupport
    public final void handleOnCompletionException$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(Throwable exception) {
        Intrinsics.checkNotNullParameter(exception, "exception");
        CoroutineExceptionHandlerKt.handleCoroutineException(this.context, exception);
    }

    @Override // kotlinx.coroutines.JobSupport
    public String nameString$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host() {
        String coroutineName = CoroutineContextKt.getCoroutineName(this.context);
        if (coroutineName == null) {
            return super.nameString$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host();
        }
        String nameString$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host = super.nameString$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host();
        return "\"" + coroutineName + "\":" + nameString$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host;
    }

    public final <R> void start(CoroutineStart start, R r, Function2<? super R, ? super Continuation<? super T>, ? extends Object> block) {
        Intrinsics.checkNotNullParameter(start, "start");
        Intrinsics.checkNotNullParameter(block, "block");
        start.invoke(block, r, this);
    }
}
