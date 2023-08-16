package com.android.systemui.animation.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import com.android.systemui.animation.LaunchableViewDelegate;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
/* compiled from: LaunchableTextView.kt */
/* loaded from: classes2.dex */
public class LaunchableTextView extends TextView {
    private final LaunchableViewDelegate delegate;

    public LaunchableTextView(Context context) {
        super(context);
        this.delegate = new LaunchableViewDelegate(this, new Function1<Integer, Unit>() { // from class: com.android.systemui.animation.view.LaunchableTextView$delegate$1
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
                super/*android.widget.TextView*/.setVisibility(i);
            }
        });
    }

    public LaunchableTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.delegate = new LaunchableViewDelegate(this, new Function1<Integer, Unit>() { // from class: com.android.systemui.animation.view.LaunchableTextView$delegate$1
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
                super/*android.widget.TextView*/.setVisibility(i);
            }
        });
    }

    public LaunchableTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.delegate = new LaunchableViewDelegate(this, new Function1<Integer, Unit>() { // from class: com.android.systemui.animation.view.LaunchableTextView$delegate$1
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
                super/*android.widget.TextView*/.setVisibility(i2);
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
