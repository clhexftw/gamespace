package androidx.lifecycle.viewmodel;

import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
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

    public abstract <T> T get(Key<T> key);

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

        @Override // androidx.lifecycle.viewmodel.CreationExtras
        public <T> T get(Key<T> key) {
            Intrinsics.checkNotNullParameter(key, "key");
            return null;
        }

        private Empty() {
            super(null);
        }
    }
}
