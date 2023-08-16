package androidx.window.area;

import androidx.window.core.ExperimentalWindowApi;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: WindowAreaController.kt */
@ExperimentalWindowApi
/* loaded from: classes.dex */
final class EmptyDecorator implements WindowAreaControllerDecorator {
    public static final EmptyDecorator INSTANCE = new EmptyDecorator();

    @Override // androidx.window.area.WindowAreaControllerDecorator
    public WindowAreaController decorate(WindowAreaController controller) {
        Intrinsics.checkNotNullParameter(controller, "controller");
        return controller;
    }

    private EmptyDecorator() {
    }
}
