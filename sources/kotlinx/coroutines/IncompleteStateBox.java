package kotlinx.coroutines;

import kotlin.jvm.internal.Intrinsics;
/* compiled from: JobSupport.kt */
/* loaded from: classes2.dex */
final class IncompleteStateBox {
    public final Incomplete state;

    public IncompleteStateBox(Incomplete state) {
        Intrinsics.checkNotNullParameter(state, "state");
        this.state = state;
    }
}
