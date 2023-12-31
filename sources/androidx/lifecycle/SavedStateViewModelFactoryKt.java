package androidx.lifecycle;

import android.app.Application;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: SavedStateViewModelFactory.kt */
/* loaded from: classes.dex */
public final class SavedStateViewModelFactoryKt {
    private static final List<Class<?>> ANDROID_VIEWMODEL_SIGNATURE;
    private static final List<Class<?>> VIEWMODEL_SIGNATURE;

    public static final <T extends ViewModel> T newInstance(Class<T> modelClass, Constructor<T> constructor, Object... params) {
        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
        Intrinsics.checkNotNullParameter(constructor, "constructor");
        Intrinsics.checkNotNullParameter(params, "params");
        try {
            return constructor.newInstance(Arrays.copyOf(params, params.length));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(Intrinsics.stringPlus("Failed to access ", modelClass), e);
        } catch (InstantiationException e2) {
            throw new RuntimeException("A " + modelClass + " cannot be instantiated.", e2);
        } catch (InvocationTargetException e3) {
            throw new RuntimeException(Intrinsics.stringPlus("An exception happened in constructor of ", modelClass), e3.getCause());
        }
    }

    static {
        List<Class<?>> listOf;
        List<Class<?>> listOf2;
        listOf = CollectionsKt__CollectionsKt.listOf((Object[]) new Class[]{Application.class, SavedStateHandle.class});
        ANDROID_VIEWMODEL_SIGNATURE = listOf;
        listOf2 = CollectionsKt__CollectionsJVMKt.listOf(SavedStateHandle.class);
        VIEWMODEL_SIGNATURE = listOf2;
    }

    public static final <T> Constructor<T> findMatchingConstructor(Class<T> modelClass, List<? extends Class<?>> signature) {
        List list;
        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
        Intrinsics.checkNotNullParameter(signature, "signature");
        Constructor<?>[] constructors = modelClass.getConstructors();
        Intrinsics.checkNotNullExpressionValue(constructors, "modelClass.constructors");
        int length = constructors.length;
        int i = 0;
        while (i < length) {
            Constructor<T> constructor = (Constructor<T>) constructors[i];
            i++;
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            Intrinsics.checkNotNullExpressionValue(parameterTypes, "constructor.parameterTypes");
            list = ArraysKt___ArraysKt.toList(parameterTypes);
            if (Intrinsics.areEqual(signature, list)) {
                return constructor;
            }
            if (signature.size() == list.size() && list.containsAll(signature)) {
                throw new UnsupportedOperationException("Class " + ((Object) modelClass.getSimpleName()) + " must have parameters in the proper order: " + signature);
            }
        }
        return null;
    }
}
