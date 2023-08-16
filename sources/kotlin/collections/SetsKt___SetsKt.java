package kotlin.collections;

import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.jvm.internal.Intrinsics;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: _Sets.kt */
/* loaded from: classes2.dex */
public class SetsKt___SetsKt extends SetsKt__SetsKt {
    public static <T> Set<T> plus(Set<? extends T> set, T t) {
        int mapCapacity;
        Intrinsics.checkNotNullParameter(set, "<this>");
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(set.size() + 1);
        LinkedHashSet linkedHashSet = new LinkedHashSet(mapCapacity);
        linkedHashSet.addAll(set);
        linkedHashSet.add(t);
        return linkedHashSet;
    }
}
