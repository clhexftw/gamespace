package com.android.systemui.shared.shadow;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextClock;
import com.android.systemui.shared.R$styleable;
import com.android.systemui.shared.shadow.DoubleShadowTextHelper;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: DoubleShadowTextClock.kt */
/* loaded from: classes2.dex */
public final class DoubleShadowTextClock extends TextClock {
    private final DoubleShadowTextHelper.ShadowInfo mAmbientShadowInfo;
    private final DoubleShadowTextHelper.ShadowInfo mKeyShadowInfo;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DoubleShadowTextClock(Context context) {
        this(context, null, 0, 0, 14, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DoubleShadowTextClock(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 0, 12, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DoubleShadowTextClock(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0, 8, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public /* synthetic */ DoubleShadowTextClock(Context context, AttributeSet attributeSet, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? null : attributeSet, (i3 & 4) != 0 ? 0 : i, (i3 & 8) != 0 ? 0 : i2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DoubleShadowTextClock(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        Intrinsics.checkNotNullParameter(context, "context");
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.DoubleShadowTextClock, i, i2);
        try {
            int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R$styleable.DoubleShadowTextClock_keyShadowBlur, 0);
            int dimensionPixelSize2 = obtainStyledAttributes.getDimensionPixelSize(R$styleable.DoubleShadowTextClock_keyShadowOffsetX, 0);
            this.mKeyShadowInfo = new DoubleShadowTextHelper.ShadowInfo(dimensionPixelSize, dimensionPixelSize2, obtainStyledAttributes.getDimensionPixelSize(R$styleable.DoubleShadowTextClock_keyShadowOffsetY, 0), obtainStyledAttributes.getFloat(R$styleable.DoubleShadowTextClock_keyShadowAlpha, 0.0f));
            int dimensionPixelSize3 = obtainStyledAttributes.getDimensionPixelSize(R$styleable.DoubleShadowTextClock_ambientShadowBlur, 0);
            int dimensionPixelSize4 = obtainStyledAttributes.getDimensionPixelSize(R$styleable.DoubleShadowTextClock_ambientShadowOffsetX, 0);
            this.mAmbientShadowInfo = new DoubleShadowTextHelper.ShadowInfo(dimensionPixelSize3, dimensionPixelSize4, obtainStyledAttributes.getDimensionPixelSize(R$styleable.DoubleShadowTextClock_ambientShadowOffsetY, 0), obtainStyledAttributes.getFloat(R$styleable.DoubleShadowTextClock_ambientShadowAlpha, 0.0f));
            boolean z = obtainStyledAttributes.getBoolean(R$styleable.DoubleShadowTextClock_removeTextDescent, false);
            int dimensionPixelSize5 = obtainStyledAttributes.getDimensionPixelSize(R$styleable.DoubleShadowTextClock_textDescentExtraPadding, 0);
            if (z) {
                setPaddingRelative(0, 0, 0, dimensionPixelSize5 - ((int) Math.floor(getPaint().getFontMetrics().descent)));
            }
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    @Override // android.widget.TextView, android.view.View
    public void onDraw(final Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        DoubleShadowTextHelper.INSTANCE.applyShadows(this.mKeyShadowInfo, this.mAmbientShadowInfo, this, canvas, new Function0<Unit>() { // from class: com.android.systemui.shared.shadow.DoubleShadowTextClock$onDraw$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2() {
                super/*android.widget.TextClock*/.onDraw(canvas);
            }
        });
    }
}
