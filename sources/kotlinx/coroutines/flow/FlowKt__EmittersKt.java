package kotlinx.coroutines.flow;

import kotlin.jvm.internal.Intrinsics;
/* compiled from: Emitters.kt */
/* loaded from: classes2.dex */
final /* synthetic */ class FlowKt__EmittersKt {
    public static final void ensureActive(FlowCollector<?> flowCollector) {
        Intrinsics.checkNotNullParameter(flowCollector, "<this>");
        if (flowCollector instanceof ThrowingCollector) {
            throw ((ThrowingCollector) flowCollector).e;
        }
    }
}
