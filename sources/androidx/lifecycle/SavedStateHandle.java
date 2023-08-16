package androidx.lifecycle;

import android.os.Binder;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Size;
import android.util.SizeF;
import android.util.SparseArray;
import androidx.core.os.BundleKt;
import androidx.savedstate.SavedStateRegistry;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: SavedStateHandle.kt */
/* loaded from: classes.dex */
public final class SavedStateHandle {
    private final Map<String, Object> liveDatas;
    private final Map<String, Object> regular;
    private final SavedStateRegistry.SavedStateProvider savedStateProvider;
    private final Map<String, SavedStateRegistry.SavedStateProvider> savedStateProviders;
    public static final Companion Companion = new Companion(null);
    private static final Class<? extends Object>[] ACCEPTABLE_CLASSES = {Boolean.TYPE, boolean[].class, Double.TYPE, double[].class, Integer.TYPE, int[].class, Long.TYPE, long[].class, String.class, String[].class, Binder.class, Bundle.class, Byte.TYPE, byte[].class, Character.TYPE, char[].class, CharSequence.class, CharSequence[].class, ArrayList.class, Float.TYPE, float[].class, Parcelable.class, Parcelable[].class, Serializable.class, Short.TYPE, short[].class, SparseArray.class, Size.class, SizeF.class};

    public static final SavedStateHandle createHandle(Bundle bundle, Bundle bundle2) {
        return Companion.createHandle(bundle, bundle2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: savedStateProvider$lambda-0  reason: not valid java name */
    public static final Bundle m18savedStateProvider$lambda0(SavedStateHandle this$0) {
        Map map;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        map = MapsKt__MapsKt.toMap(this$0.savedStateProviders);
        for (Map.Entry entry : map.entrySet()) {
            Bundle saveState = ((SavedStateRegistry.SavedStateProvider) entry.getValue()).saveState();
            Intrinsics.checkNotNullExpressionValue(saveState, "value.saveState()");
            this$0.set((String) entry.getKey(), saveState);
        }
        Set<String> keySet = this$0.regular.keySet();
        ArrayList arrayList = new ArrayList(keySet.size());
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        for (String str : keySet) {
            arrayList.add(str);
            arrayList2.add(this$0.regular.get(str));
        }
        return BundleKt.bundleOf(TuplesKt.to("keys", arrayList), TuplesKt.to("values", arrayList2));
    }

    public SavedStateHandle(Map<String, ? extends Object> initialState) {
        Intrinsics.checkNotNullParameter(initialState, "initialState");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        this.regular = linkedHashMap;
        this.savedStateProviders = new LinkedHashMap();
        this.liveDatas = new LinkedHashMap();
        this.savedStateProvider = new SavedStateRegistry.SavedStateProvider() { // from class: androidx.lifecycle.SavedStateHandle$$ExternalSyntheticLambda0
            @Override // androidx.savedstate.SavedStateRegistry.SavedStateProvider
            public final Bundle saveState() {
                Bundle m18savedStateProvider$lambda0;
                m18savedStateProvider$lambda0 = SavedStateHandle.m18savedStateProvider$lambda0(SavedStateHandle.this);
                return m18savedStateProvider$lambda0;
            }
        };
        linkedHashMap.putAll(initialState);
    }

    public SavedStateHandle() {
        this.regular = new LinkedHashMap();
        this.savedStateProviders = new LinkedHashMap();
        this.liveDatas = new LinkedHashMap();
        this.savedStateProvider = new SavedStateRegistry.SavedStateProvider() { // from class: androidx.lifecycle.SavedStateHandle$$ExternalSyntheticLambda0
            @Override // androidx.savedstate.SavedStateRegistry.SavedStateProvider
            public final Bundle saveState() {
                Bundle m18savedStateProvider$lambda0;
                m18savedStateProvider$lambda0 = SavedStateHandle.m18savedStateProvider$lambda0(SavedStateHandle.this);
                return m18savedStateProvider$lambda0;
            }
        };
    }

    public final SavedStateRegistry.SavedStateProvider savedStateProvider() {
        return this.savedStateProvider;
    }

    public final <T> void set(String key, T t) {
        Intrinsics.checkNotNullParameter(key, "key");
        Companion.validateValue$lifecycle_viewmodel_savedstate_release(t);
        Object obj = this.liveDatas.get(key);
        MutableLiveData mutableLiveData = obj instanceof MutableLiveData ? (MutableLiveData) obj : null;
        if (mutableLiveData != null) {
            mutableLiveData.setValue(t);
        } else {
            this.regular.put(key, t);
        }
    }

    /* compiled from: SavedStateHandle.kt */
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final SavedStateHandle createHandle(Bundle bundle, Bundle bundle2) {
            if (bundle == null) {
                if (bundle2 == null) {
                    return new SavedStateHandle();
                }
                HashMap hashMap = new HashMap();
                for (String key : bundle2.keySet()) {
                    Intrinsics.checkNotNullExpressionValue(key, "key");
                    hashMap.put(key, bundle2.get(key));
                }
                return new SavedStateHandle(hashMap);
            }
            ArrayList parcelableArrayList = bundle.getParcelableArrayList("keys");
            ArrayList parcelableArrayList2 = bundle.getParcelableArrayList("values");
            int i = 0;
            if (!((parcelableArrayList == null || parcelableArrayList2 == null || parcelableArrayList.size() != parcelableArrayList2.size()) ? false : true)) {
                throw new IllegalStateException("Invalid bundle passed as restored state".toString());
            }
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            int size = parcelableArrayList.size();
            while (i < size) {
                int i2 = i + 1;
                Object obj = parcelableArrayList.get(i);
                if (obj == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                }
                linkedHashMap.put((String) obj, parcelableArrayList2.get(i));
                i = i2;
            }
            return new SavedStateHandle(linkedHashMap);
        }

        public final void validateValue$lifecycle_viewmodel_savedstate_release(Object obj) {
            if (obj == null) {
                return;
            }
            Class[] clsArr = SavedStateHandle.ACCEPTABLE_CLASSES;
            int i = 0;
            int length = clsArr.length;
            while (i < length) {
                Class cls = clsArr[i];
                i++;
                Intrinsics.checkNotNull(cls);
                if (cls.isInstance(obj)) {
                    return;
                }
            }
            throw new IllegalArgumentException("Can't put value with type " + obj.getClass() + " into saved state");
        }
    }
}
