package kotlinx.coroutines;

import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.DispatchedContinuation;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
/* compiled from: CancellableContinuation.kt */
/* loaded from: classes2.dex */
public final class CancellableContinuationKt {
    public static final <T> CancellableContinuationImpl<T> getOrCreateCancellableContinuation(Continuation<? super T> delegate) {
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        if (!(delegate instanceof DispatchedContinuation)) {
            return new CancellableContinuationImpl<>(delegate, 1);
        }
        CancellableContinuationImpl<T> claimReusableCancellableContinuation = ((DispatchedContinuation) delegate).claimReusableCancellableContinuation();
        if (claimReusableCancellableContinuation != null) {
            if (!claimReusableCancellableContinuation.resetStateReusable()) {
                claimReusableCancellableContinuation = null;
            }
            if (claimReusableCancellableContinuation != null) {
                return claimReusableCancellableContinuation;
            }
        }
        return new CancellableContinuationImpl<>(delegate, 2);
    }

    public static final void removeOnCancellation(CancellableContinuation<?> cancellableContinuation, LockFreeLinkedListNode node) {
        Intrinsics.checkNotNullParameter(cancellableContinuation, "<this>");
        Intrinsics.checkNotNullParameter(node, "node");
        cancellableContinuation.invokeOnCancellation(new RemoveOnCancel(node));
    }
}
