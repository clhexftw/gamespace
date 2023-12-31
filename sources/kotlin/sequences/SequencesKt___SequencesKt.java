package kotlin.sequences;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: _Sequences.kt */
/* loaded from: classes2.dex */
public class SequencesKt___SequencesKt extends SequencesKt___SequencesJvmKt {
    public static final <T, C extends Collection<? super T>> C toCollection(Sequence<? extends T> sequence, C destination) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        for (T t : sequence) {
            destination.add(t);
        }
        return destination;
    }

    public static <T> List<T> toList(Sequence<? extends T> sequence) {
        List<T> optimizeReadOnlyList;
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        optimizeReadOnlyList = CollectionsKt__CollectionsKt.optimizeReadOnlyList(toMutableList(sequence));
        return optimizeReadOnlyList;
    }

    public static final <T> List<T> toMutableList(Sequence<? extends T> sequence) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        return (List) toCollection(sequence, new ArrayList());
    }

    public static <T> Iterable<T> asIterable(final Sequence<? extends T> sequence) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        return new Iterable<T>() { // from class: kotlin.sequences.SequencesKt___SequencesKt$asIterable$$inlined$Iterable$1
            @Override // java.lang.Iterable
            public Iterator<T> iterator() {
                return Sequence.this.iterator();
            }
        };
    }
}
