package kotlin.reflect;

import kotlin.Function;
import kotlin.jvm.functions.Function0;
/* compiled from: KProperty.kt */
/* loaded from: classes.dex */
public interface KProperty0<V> extends KProperty<V>, Function0<V> {

    /* compiled from: KProperty.kt */
    /* loaded from: classes.dex */
    public interface Getter<V> extends KCallable, Function, Function0<V> {
    }

    V get();

    Getter<V> getGetter();
}
