package kotlin.collections;

import java.util.Comparator;
import java.util.List;
/* loaded from: classes.dex */
public final class CollectionsKt extends CollectionsKt___CollectionsKt {
    public static /* bridge */ /* synthetic */ int binarySearch$default(List list, Comparable comparable, int i, int i2, int i3, Object obj) {
        return CollectionsKt__CollectionsKt.binarySearch$default(list, comparable, i, i2, i3, obj);
    }

    public static /* bridge */ /* synthetic */ int collectionSizeOrDefault(Iterable iterable, int i) {
        return CollectionsKt__IterablesKt.collectionSizeOrDefault(iterable, i);
    }

    public static /* bridge */ /* synthetic */ List listOf(Object obj) {
        return CollectionsKt__CollectionsJVMKt.listOf(obj);
    }

    public static /* bridge */ /* synthetic */ List mutableListOf(Object... objArr) {
        return CollectionsKt__CollectionsKt.mutableListOf(objArr);
    }

    public static /* bridge */ /* synthetic */ Object single(Iterable iterable) {
        return CollectionsKt___CollectionsKt.single(iterable);
    }

    public static /* bridge */ /* synthetic */ void sort(List list) {
        CollectionsKt__MutableCollectionsJVMKt.sort(list);
    }

    public static /* bridge */ /* synthetic */ List sortedWith(Iterable iterable, Comparator comparator) {
        return CollectionsKt___CollectionsKt.sortedWith(iterable, comparator);
    }
}
