package kotlinx.coroutines;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: CancellableContinuationImpl.kt */
/* loaded from: classes2.dex */
final class CompletedContinuation {
    public final Throwable cancelCause;
    public final CancelHandler cancelHandler;
    public final Object idempotentResume;
    public final Function1<Throwable, Unit> onCancellation;
    public final Object result;

    public static /* synthetic */ CompletedContinuation copy$default(CompletedContinuation completedContinuation, Object obj, CancelHandler cancelHandler, Function1 function1, Object obj2, Throwable th, int i, Object obj3) {
        if ((i & 1) != 0) {
            obj = completedContinuation.result;
        }
        if ((i & 2) != 0) {
            cancelHandler = completedContinuation.cancelHandler;
        }
        CancelHandler cancelHandler2 = cancelHandler;
        Function1<Throwable, Unit> function12 = function1;
        if ((i & 4) != 0) {
            function12 = completedContinuation.onCancellation;
        }
        Function1 function13 = function12;
        if ((i & 8) != 0) {
            obj2 = completedContinuation.idempotentResume;
        }
        Object obj4 = obj2;
        if ((i & 16) != 0) {
            th = completedContinuation.cancelCause;
        }
        return completedContinuation.copy(obj, cancelHandler2, function13, obj4, th);
    }

    public final CompletedContinuation copy(Object obj, CancelHandler cancelHandler, Function1<? super Throwable, Unit> function1, Object obj2, Throwable th) {
        return new CompletedContinuation(obj, cancelHandler, function1, obj2, th);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof CompletedContinuation) {
            CompletedContinuation completedContinuation = (CompletedContinuation) obj;
            return Intrinsics.areEqual(this.result, completedContinuation.result) && Intrinsics.areEqual(this.cancelHandler, completedContinuation.cancelHandler) && Intrinsics.areEqual(this.onCancellation, completedContinuation.onCancellation) && Intrinsics.areEqual(this.idempotentResume, completedContinuation.idempotentResume) && Intrinsics.areEqual(this.cancelCause, completedContinuation.cancelCause);
        }
        return false;
    }

    public int hashCode() {
        Object obj = this.result;
        int hashCode = (obj == null ? 0 : obj.hashCode()) * 31;
        CancelHandler cancelHandler = this.cancelHandler;
        int hashCode2 = (hashCode + (cancelHandler == null ? 0 : cancelHandler.hashCode())) * 31;
        Function1<Throwable, Unit> function1 = this.onCancellation;
        int hashCode3 = (hashCode2 + (function1 == null ? 0 : function1.hashCode())) * 31;
        Object obj2 = this.idempotentResume;
        int hashCode4 = (hashCode3 + (obj2 == null ? 0 : obj2.hashCode())) * 31;
        Throwable th = this.cancelCause;
        return hashCode4 + (th != null ? th.hashCode() : 0);
    }

    public String toString() {
        Object obj = this.result;
        CancelHandler cancelHandler = this.cancelHandler;
        Function1<Throwable, Unit> function1 = this.onCancellation;
        Object obj2 = this.idempotentResume;
        Throwable th = this.cancelCause;
        return "CompletedContinuation(result=" + obj + ", cancelHandler=" + cancelHandler + ", onCancellation=" + function1 + ", idempotentResume=" + obj2 + ", cancelCause=" + th + ")";
    }

    /* JADX WARN: Multi-variable type inference failed */
    public CompletedContinuation(Object obj, CancelHandler cancelHandler, Function1<? super Throwable, Unit> function1, Object obj2, Throwable th) {
        this.result = obj;
        this.cancelHandler = cancelHandler;
        this.onCancellation = function1;
        this.idempotentResume = obj2;
        this.cancelCause = th;
    }

    public /* synthetic */ CompletedContinuation(Object obj, CancelHandler cancelHandler, Function1 function1, Object obj2, Throwable th, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(obj, (i & 2) != 0 ? null : cancelHandler, (i & 4) != 0 ? null : function1, (i & 8) != 0 ? null : obj2, (i & 16) != 0 ? null : th);
    }

    public final boolean getCancelled() {
        return this.cancelCause != null;
    }

    public final void invokeHandlers(CancellableContinuationImpl<?> cont, Throwable cause) {
        Intrinsics.checkNotNullParameter(cont, "cont");
        Intrinsics.checkNotNullParameter(cause, "cause");
        CancelHandler cancelHandler = this.cancelHandler;
        if (cancelHandler != null) {
            cont.callCancelHandler(cancelHandler, cause);
        }
        Function1<Throwable, Unit> function1 = this.onCancellation;
        if (function1 != null) {
            cont.callOnCancellation(function1, cause);
        }
    }
}
