package androidx.lifecycle;

import android.app.Application;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.savedstate.SavedStateRegistry;
import java.lang.reflect.Constructor;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: SavedStateViewModelFactory.kt */
/* loaded from: classes.dex */
public final class SavedStateViewModelFactory extends ViewModelProvider.OnRequeryFactory implements ViewModelProvider.Factory {
    private Application application;
    private Bundle defaultArgs;
    private final ViewModelProvider.Factory factory = new ViewModelProvider.AndroidViewModelFactory();
    private Lifecycle lifecycle;
    private SavedStateRegistry savedStateRegistry;

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public <T extends ViewModel> T create(Class<T> modelClass, CreationExtras extras) {
        List list;
        Constructor findMatchingConstructor;
        List list2;
        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
        Intrinsics.checkNotNullParameter(extras, "extras");
        String str = (String) extras.get(ViewModelProvider.NewInstanceFactory.VIEW_MODEL_KEY);
        if (str == null) {
            throw new IllegalStateException("VIEW_MODEL_KEY must always be provided by ViewModelProvider");
        }
        if (this.lifecycle != null) {
            return (T) create(str, modelClass);
        }
        Application application = (Application) extras.get(ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY);
        boolean isAssignableFrom = AndroidViewModel.class.isAssignableFrom(modelClass);
        if (!isAssignableFrom || application == null) {
            list = SavedStateViewModelFactoryKt.VIEWMODEL_SIGNATURE;
            findMatchingConstructor = SavedStateViewModelFactoryKt.findMatchingConstructor(modelClass, list);
        } else {
            list2 = SavedStateViewModelFactoryKt.ANDROID_VIEWMODEL_SIGNATURE;
            findMatchingConstructor = SavedStateViewModelFactoryKt.findMatchingConstructor(modelClass, list2);
        }
        if (findMatchingConstructor == null) {
            return (T) this.factory.create(modelClass, extras);
        }
        return (!isAssignableFrom || application == null) ? (T) SavedStateViewModelFactoryKt.newInstance(modelClass, findMatchingConstructor, SavedStateHandleSupport.createSavedStateHandle(extras)) : (T) SavedStateViewModelFactoryKt.newInstance(modelClass, findMatchingConstructor, application, SavedStateHandleSupport.createSavedStateHandle(extras));
    }

    public final <T extends ViewModel> T create(String key, Class<T> modelClass) {
        List list;
        Constructor findMatchingConstructor;
        T t;
        Application application;
        List list2;
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
        if (this.lifecycle == null) {
            throw new UnsupportedOperationException("SavedStateViewModelFactory constructed with empty constructor supports only calls to create(modelClass: Class<T>, extras: CreationExtras).");
        }
        boolean isAssignableFrom = AndroidViewModel.class.isAssignableFrom(modelClass);
        if (!isAssignableFrom || this.application == null) {
            list = SavedStateViewModelFactoryKt.VIEWMODEL_SIGNATURE;
            findMatchingConstructor = SavedStateViewModelFactoryKt.findMatchingConstructor(modelClass, list);
        } else {
            list2 = SavedStateViewModelFactoryKt.ANDROID_VIEWMODEL_SIGNATURE;
            findMatchingConstructor = SavedStateViewModelFactoryKt.findMatchingConstructor(modelClass, list2);
        }
        if (findMatchingConstructor == null) {
            return (T) this.factory.create(modelClass);
        }
        SavedStateHandleController create = LegacySavedStateHandleController.create(this.savedStateRegistry, this.lifecycle, key, this.defaultArgs);
        if (isAssignableFrom && (application = this.application) != null) {
            Intrinsics.checkNotNull(application);
            SavedStateHandle handle = create.getHandle();
            Intrinsics.checkNotNullExpressionValue(handle, "controller.handle");
            t = (T) SavedStateViewModelFactoryKt.newInstance(modelClass, findMatchingConstructor, application, handle);
        } else {
            SavedStateHandle handle2 = create.getHandle();
            Intrinsics.checkNotNullExpressionValue(handle2, "controller.handle");
            t = (T) SavedStateViewModelFactoryKt.newInstance(modelClass, findMatchingConstructor, handle2);
        }
        t.setTagIfAbsent("androidx.lifecycle.savedstate.vm.tag", create);
        return t;
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public <T extends ViewModel> T create(Class<T> modelClass) {
        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
        String canonicalName = modelClass.getCanonicalName();
        if (canonicalName == null) {
            throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
        }
        return (T) create(canonicalName, modelClass);
    }

    @Override // androidx.lifecycle.ViewModelProvider.OnRequeryFactory
    public void onRequery(ViewModel viewModel) {
        Intrinsics.checkNotNullParameter(viewModel, "viewModel");
        Lifecycle lifecycle = this.lifecycle;
        if (lifecycle != null) {
            LegacySavedStateHandleController.attachHandleIfNeeded(viewModel, this.savedStateRegistry, lifecycle);
        }
    }
}
