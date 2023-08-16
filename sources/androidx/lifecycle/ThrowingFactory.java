package androidx.lifecycle;

import androidx.lifecycle.ViewModelProvider;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: SavedStateHandleSupport.kt */
/* loaded from: classes.dex */
public final class ThrowingFactory implements ViewModelProvider.Factory {
    public static final ThrowingFactory INSTANCE = new ThrowingFactory();

    private ThrowingFactory() {
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public <T extends ViewModel> T create(Class<T> modelClass) {
        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
        throw new IllegalStateException("enableSavedStateHandles() wasn't called prior to createSavedStateHandle() call");
    }
}
