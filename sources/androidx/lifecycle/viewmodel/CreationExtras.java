package androidx.lifecycle.viewmodel;

import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
/* compiled from: CreationExtras.kt */
/* loaded from: classes.dex */
public abstract class CreationExtras {
    private final Map<Key<?>, Object> map;

    /* compiled from: CreationExtras.kt */
    /* loaded from: classes.dex */
    public interface Key<T> {
    }

    public /* synthetic */ CreationExtras(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private CreationExtras() {
        this.map = new LinkedHashMap();
    }

    public final Map<Key<?>, Object> getMap$lifecycle_viewmodel_release() {
        return this.map;
    }

    /* compiled from: CreationExtras.kt */
    /* loaded from: classes.dex */
    public static final class Empty extends CreationExtras {
        public static final Empty INSTANCE = new Empty();

        private Empty() {
            super(null);
        }
    }
}
