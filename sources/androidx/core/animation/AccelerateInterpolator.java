package androidx.core.animation;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
/* loaded from: classes.dex */
public class AccelerateInterpolator {
    private final double mDoubleFactor;
    private final float mFactor;

    public AccelerateInterpolator() {
        this.mFactor = 1.0f;
        this.mDoubleFactor = 2.0d;
    }

    public AccelerateInterpolator(Context context, AttributeSet attributeSet) {
        this(context.getResources(), context.getTheme(), attributeSet);
    }

    AccelerateInterpolator(Resources resources, Resources.Theme theme, AttributeSet attributeSet) {
        TypedArray obtainAttributes;
        if (theme != null) {
            obtainAttributes = theme.obtainStyledAttributes(attributeSet, AndroidResources.STYLEABLE_ACCELERATE_INTERPOLATOR, 0, 0);
        } else {
            obtainAttributes = resources.obtainAttributes(attributeSet, AndroidResources.STYLEABLE_ACCELERATE_INTERPOLATOR);
        }
        float f = obtainAttributes.getFloat(0, 1.0f);
        this.mFactor = f;
        this.mDoubleFactor = f * 2.0f;
        obtainAttributes.recycle();
    }
}
