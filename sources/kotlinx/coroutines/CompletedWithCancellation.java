package kotlinx.coroutines;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: CompletionState.kt */
/* loaded from: classes2.dex */
public final class CompletedWithCancellation {
    public final Function1<Throwable, Unit> onCancellation;
    public final Object result;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof CompletedWithCancellation) {
            CompletedWithCancellation completedWithCancellation = (CompletedWithCancellation) obj;
            return Intrinsics.areEqual(this.result, completedWithCancellation.result) && Intrinsics.areEqual(this.onCancellation, completedWithCancellation.onCancellation);
        }
        return false;
    }

    public int hashCode() {
        Object obj = this.result;
        return ((obj == null ? 0 : obj.hashCode()) * 31) + this.onCancellation.hashCode();
    }

    public String toString() {
        Object obj = this.result;
        Function1<Throwable, Unit> function1 = this.onCancellation;
        return "CompletedWithCancellation(result=" + obj + ", onCancellation=" + function1 + ")";
    }

    /* JADX WARN: Multi-variable type inference failed */
    public CompletedWithCancellation(Object obj, Function1<? super Throwable, Unit> onCancellation) {
        Intrinsics.checkNotNullParameter(onCancellation, "onCancellation");
        this.result = obj;
        this.onCancellation = onCancellation;
    }
}
