package androidx.lifecycle;

import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryOwner;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: SavedStateHandleSupport.kt */
/* loaded from: classes.dex */
public final class SavedStateHandleAttacher implements SavedStateRegistry.AutoRecreated {
    @Override // androidx.savedstate.SavedStateRegistry.AutoRecreated
    public void onRecreated(SavedStateRegistryOwner owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        if (!(owner instanceof ViewModelStoreOwner)) {
            throw new IllegalStateException("Internal error: SavedStateHandleAttacher should be registered only on componentsthat implement ViewModelStoreOwner");
        }
        ViewModelStoreOwner viewModelStoreOwner = (ViewModelStoreOwner) owner;
        ViewModelStore viewModelStore = viewModelStoreOwner.getViewModelStore();
        Intrinsics.checkNotNullExpressionValue(viewModelStore, "owner as ViewModelStoreOwner).viewModelStore");
        if (viewModelStore.keys().contains("androidx.lifecycle.internal.SavedStateHandlesVM")) {
            for (SavedStateHandleController savedStateHandleController : SavedStateHandleSupport.getSavedStateHandlesVM(viewModelStoreOwner).getControllers()) {
                savedStateHandleController.attachToLifecycle(owner.getSavedStateRegistry(), owner.getLifecycle());
            }
            owner.getSavedStateRegistry().runOnNextRecreation(SavedStateHandleAttacher.class);
        }
    }
}
