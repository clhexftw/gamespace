package kotlin.collections;

import java.util.Map;
/* compiled from: MapWithDefault.kt */
/* loaded from: classes.dex */
interface MapWithDefault<K, V> extends Map<K, V> {
    V getOrImplicitDefault(K k);
}
