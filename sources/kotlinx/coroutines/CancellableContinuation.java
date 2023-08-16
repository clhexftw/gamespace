package kotlinx.coroutines;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
/* compiled from: CancellableContinuation.kt */
/* loaded from: classes2.dex */
public interface CancellableContinuation<T> extends Continuation<T> {
    void completeResume(Object obj);

    void invokeOnCancellation(Function1<? super Throwable, Unit> function1);

    void resume(T t, Function1<? super Throwable, Unit> function1);

    Object tryResume(T t, Object obj, Function1<? super Throwable, Unit> function1);
}
