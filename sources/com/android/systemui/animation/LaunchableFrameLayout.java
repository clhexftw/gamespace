package com.android.systemui.animation;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: LaunchableFrameLayout.kt */
/* loaded from: classes2.dex */
public class LaunchableFrameLayout extends FrameLayout {
    private final LaunchableViewDelegate delegate;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LaunchableFrameLayout(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.delegate = new LaunchableViewDelegate(this, new Function1<Integer, Unit>() { // from class: com.android.systemui.animation.LaunchableFrameLayout$delegate$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                super/*android.widget.FrameLayout*/.setVisibility(i);
            }
        });
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LaunchableFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        this.delegate = new LaunchableViewDelegate(this, new Function1<Integer, Unit>() { // from class: com.android.systemui.animation.LaunchableFrameLayout$delegate$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                super/*android.widget.FrameLayout*/.setVisibility(i);
            }
        });
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LaunchableFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.delegate = new LaunchableViewDelegate(this, new Function1<Integer, Unit>() { // from class: com.android.systemui.animation.LaunchableFrameLayout$delegate$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i2) {
                super/*android.widget.FrameLayout*/.setVisibility(i2);
            }
        });
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LaunchableFrameLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        Intrinsics.checkNotNullParameter(context, "context");
        this.delegate = new LaunchableViewDelegate(this, new Function1<Integer, Unit>() { // from class: com.android.systemui.animation.LaunchableFrameLayout$delegate$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i22) {
                super/*android.widget.FrameLayout*/.setVisibility(i22);
            }
        });
    }

    public void setShouldBlockVisibilityChanges(boolean z) {
        this.delegate.setShouldBlockVisibilityChanges(z);
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        this.delegate.setVisibility(i);
    }
}
