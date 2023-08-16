package com.android.systemui.shared.shadow;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.TextView;
import com.android.systemui.shared.R$styleable;
import com.android.systemui.shared.shadow.DoubleShadowTextHelper;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: DoubleShadowTextView.kt */
/* loaded from: classes2.dex */
public class DoubleShadowTextView extends TextView {
    private final DoubleShadowTextHelper.ShadowInfo mAmbientShadowInfo;
    private final DoubleShadowTextHelper.ShadowInfo mKeyShadowInfo;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DoubleShadowTextView(Context context) {
        this(context, null, 0, 0, 14, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DoubleShadowTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 0, 12, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DoubleShadowTextView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0, 8, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public /* synthetic */ DoubleShadowTextView(Context context, AttributeSet attributeSet, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? null : attributeSet, (i3 & 4) != 0 ? 0 : i, (i3 & 8) != 0 ? 0 : i2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DoubleShadowTextView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        int i3;
        Intrinsics.checkNotNullParameter(context, "context");
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.DoubleShadowTextView, i, i2);
        try {
            this.mKeyShadowInfo = new DoubleShadowTextHelper.ShadowInfo(obtainStyledAttributes.getDimension(R$styleable.DoubleShadowTextView_keyShadowBlur, 0.0f), obtainStyledAttributes.getDimension(R$styleable.DoubleShadowTextView_keyShadowOffsetX, 0.0f), obtainStyledAttributes.getDimension(R$styleable.DoubleShadowTextView_keyShadowOffsetY, 0.0f), obtainStyledAttributes.getFloat(R$styleable.DoubleShadowTextView_keyShadowAlpha, 0.0f));
            this.mAmbientShadowInfo = new DoubleShadowTextHelper.ShadowInfo(obtainStyledAttributes.getDimension(R$styleable.DoubleShadowTextView_ambientShadowBlur, 0.0f), obtainStyledAttributes.getDimension(R$styleable.DoubleShadowTextView_ambientShadowOffsetX, 0.0f), obtainStyledAttributes.getDimension(R$styleable.DoubleShadowTextView_ambientShadowOffsetY, 0.0f), obtainStyledAttributes.getFloat(R$styleable.DoubleShadowTextView_ambientShadowAlpha, 0.0f));
            int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R$styleable.DoubleShadowTextView_drawableIconSize, 0);
            int dimensionPixelSize2 = obtainStyledAttributes.getDimensionPixelSize(R$styleable.DoubleShadowTextView_drawableIconInsetSize, 0);
            obtainStyledAttributes.recycle();
            Drawable[] drawableArr = new Drawable[4];
            drawableArr[0] = null;
            drawableArr[1] = null;
            drawableArr[2] = null;
            drawableArr[3] = null;
            Drawable[] compoundDrawablesRelative = getCompoundDrawablesRelative();
            Intrinsics.checkNotNullExpressionValue(compoundDrawablesRelative, "compoundDrawablesRelative");
            int length = compoundDrawablesRelative.length;
            int i4 = 0;
            while (i4 < length) {
                Drawable drawable = compoundDrawablesRelative[i4];
                if (drawable != null) {
                    i3 = i4;
                    drawableArr[i3] = new DoubleShadowIconDrawable(this.mKeyShadowInfo, this.mAmbientShadowInfo, drawable, dimensionPixelSize, dimensionPixelSize2);
                } else {
                    i3 = i4;
                }
                i4 = i3 + 1;
            }
            setCompoundDrawablesRelative(drawableArr[0], drawableArr[1], drawableArr[2], drawableArr[3]);
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    @Override // android.widget.TextView, android.view.View
    public void onDraw(final Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        DoubleShadowTextHelper.INSTANCE.applyShadows(this.mKeyShadowInfo, this.mAmbientShadowInfo, this, canvas, new Function0<Unit>() { // from class: com.android.systemui.shared.shadow.DoubleShadowTextView$onDraw$1
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
                super/*android.widget.TextView*/.onDraw(canvas);
            }
        });
    }
}
