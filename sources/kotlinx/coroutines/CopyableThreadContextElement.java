package kotlinx.coroutines;

import kotlin.coroutines.CoroutineContext;
/* compiled from: ThreadContextElement.kt */
/* loaded from: classes2.dex */
public interface CopyableThreadContextElement<S> extends ThreadContextElement<S> {
    CopyableThreadContextElement<S> copyForChild();

    CoroutineContext mergeForChild(CoroutineContext.Element element);
}
