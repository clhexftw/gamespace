package androidx.lifecycle;

import java.util.ArrayList;
import java.util.List;
/* compiled from: SavedStateHandleSupport.kt */
/* loaded from: classes.dex */
public final class SavedStateHandlesVM extends ViewModel {
    private final List<SavedStateHandleController> controllers = new ArrayList();

    public final List<SavedStateHandleController> getControllers() {
        return this.controllers;
    }
}
