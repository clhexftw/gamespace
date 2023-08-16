package androidx.savedstate;

import android.os.Bundle;
import androidx.lifecycle.Lifecycle;
/* loaded from: classes.dex */
public final class SavedStateRegistryController {
    private final SavedStateRegistryOwner mOwner;
    private boolean mAttached = false;
    private final SavedStateRegistry mRegistry = new SavedStateRegistry();

    private SavedStateRegistryController(SavedStateRegistryOwner savedStateRegistryOwner) {
        this.mOwner = savedStateRegistryOwner;
    }

    public SavedStateRegistry getSavedStateRegistry() {
        return this.mRegistry;
    }

    public void performAttach() {
        Lifecycle lifecycle = this.mOwner.getLifecycle();
        if (lifecycle.getCurrentState() != Lifecycle.State.INITIALIZED) {
            throw new IllegalStateException("Restarter must be created only during owner's initialization stage");
        }
        lifecycle.addObserver(new Recreator(this.mOwner));
        this.mRegistry.performAttach(lifecycle);
        this.mAttached = true;
    }

    public void performRestore(Bundle bundle) {
        if (!this.mAttached) {
            performAttach();
        }
        Lifecycle lifecycle = this.mOwner.getLifecycle();
        if (lifecycle.getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            throw new IllegalStateException("performRestore cannot be called when owner  is " + lifecycle.getCurrentState());
        }
        this.mRegistry.performRestore(bundle);
    }

    public void performSave(Bundle bundle) {
        this.mRegistry.performSave(bundle);
    }

    public static SavedStateRegistryController create(SavedStateRegistryOwner savedStateRegistryOwner) {
        return new SavedStateRegistryController(savedStateRegistryOwner);
    }
}
