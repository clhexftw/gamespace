package androidx.core.animation;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
/* loaded from: classes.dex */
public class CycleInterpolator {
    private float mCycles;

    public CycleInterpolator(Context context, AttributeSet attributeSet) {
        this(context.getResources(), context.getTheme(), attributeSet);
    }

    CycleInterpolator(Resources resources, Resources.Theme theme, AttributeSet attributeSet) {
        TypedArray obtainAttributes;
        if (theme != null) {
            obtainAttributes = theme.obtainStyledAttributes(attributeSet, AndroidResources.STYLEABLE_CYCLE_INTERPOLATOR, 0, 0);
        } else {
            obtainAttributes = resources.obtainAttributes(attributeSet, AndroidResources.STYLEABLE_CYCLE_INTERPOLATOR);
        }
        this.mCycles = obtainAttributes.getFloat(0, 1.0f);
        obtainAttributes.recycle();
    }
}
