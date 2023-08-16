package com.android.systemui.animation;

import android.view.View;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: LaunchableView.kt */
/* loaded from: classes2.dex */
public final class LaunchableViewDelegate {
    private boolean blockVisibilityChanges;
    private int lastVisibility;
    private final Function1<Integer, Unit> superSetVisibility;
    private final View view;

    /* JADX WARN: Multi-variable type inference failed */
    public LaunchableViewDelegate(View view, Function1<? super Integer, Unit> superSetVisibility) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(superSetVisibility, "superSetVisibility");
        this.view = view;
        this.superSetVisibility = superSetVisibility;
        this.lastVisibility = view.getVisibility();
    }

    public void setShouldBlockVisibilityChanges(boolean z) {
        if (z == this.blockVisibilityChanges) {
            return;
        }
        this.blockVisibilityChanges = z;
        if (z) {
            this.lastVisibility = this.view.getVisibility();
        } else if (this.lastVisibility == 0) {
            this.superSetVisibility.invoke(4);
            this.superSetVisibility.invoke(0);
        } else {
            this.superSetVisibility.invoke(0);
            this.superSetVisibility.invoke(Integer.valueOf(this.lastVisibility));
        }
    }

    public final void setVisibility(int i) {
        if (this.blockVisibilityChanges) {
            this.lastVisibility = i;
        } else {
            this.superSetVisibility.invoke(Integer.valueOf(i));
        }
    }
}
