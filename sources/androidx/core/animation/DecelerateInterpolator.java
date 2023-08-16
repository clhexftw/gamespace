package androidx.core.animation;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
/* loaded from: classes.dex */
public class DecelerateInterpolator {
    private float mFactor;

    public DecelerateInterpolator() {
        this.mFactor = 1.0f;
    }

    public DecelerateInterpolator(Context context, AttributeSet attributeSet) {
        this(context.getResources(), context.getTheme(), attributeSet);
    }

    DecelerateInterpolator(Resources resources, Resources.Theme theme, AttributeSet attributeSet) {
        TypedArray obtainAttributes;
        this.mFactor = 1.0f;
        if (theme != null) {
            obtainAttributes = theme.obtainStyledAttributes(attributeSet, AndroidResources.STYLEABLE_DECELERATE_INTERPOLATOR, 0, 0);
        } else {
            obtainAttributes = resources.obtainAttributes(attributeSet, AndroidResources.STYLEABLE_DECELERATE_INTERPOLATOR);
        }
        this.mFactor = obtainAttributes.getFloat(0, 1.0f);
        obtainAttributes.recycle();
    }
}
