package kotlinx.coroutines;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicInt;
import kotlinx.coroutines.internal.DispatchedContinuationKt;
import kotlinx.coroutines.internal.ScopeCoroutine;
/* compiled from: Builders.common.kt */
/* loaded from: classes2.dex */
public final class DispatchedCoroutine<T> extends ScopeCoroutine<T> {
    private final AtomicInt _decision;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DispatchedCoroutine(CoroutineContext context, Continuation<? super T> uCont) {
        super(context, uCont);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(uCont, "uCont");
        this._decision = AtomicFU.atomic(0);
    }

    private final boolean trySuspend() {
        AtomicInt atomicInt = this._decision;
        do {
            int value = atomicInt.getValue();
            if (value != 0) {
                if (value == 2) {
                    return false;
                }
                throw new IllegalStateException("Already suspended".toString());
            }
        } while (!this._decision.compareAndSet(0, 1));
        return true;
    }

    private final boolean tryResume() {
        AtomicInt atomicInt = this._decision;
        do {
            int value = atomicInt.getValue();
            if (value != 0) {
                if (value == 1) {
                    return false;
                }
                throw new IllegalStateException("Already resumed".toString());
            }
        } while (!this._decision.compareAndSet(0, 2));
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.internal.ScopeCoroutine, kotlinx.coroutines.JobSupport
    public void afterCompletion(Object obj) {
        afterResume(obj);
    }

    @Override // kotlinx.coroutines.internal.ScopeCoroutine, kotlinx.coroutines.AbstractCoroutine
    protected void afterResume(Object obj) {
        Continuation intercepted;
        if (tryResume()) {
            return;
        }
        intercepted = IntrinsicsKt__IntrinsicsJvmKt.intercepted(this.uCont);
        DispatchedContinuationKt.resumeCancellableWith$default(intercepted, CompletionStateKt.recoverResult(obj, this.uCont), null, 2, null);
    }

    public final Object getResult() {
        Object coroutine_suspended;
        if (trySuspend()) {
            coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            return coroutine_suspended;
        }
        Object unboxState = JobSupportKt.unboxState(getState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host());
        if (unboxState instanceof CompletedExceptionally) {
            throw ((CompletedExceptionally) unboxState).cause;
        }
        return unboxState;
    }
}
