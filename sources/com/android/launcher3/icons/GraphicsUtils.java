package com.android.launcher3.icons;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.RegionIterator;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.ColorDrawable;
import androidx.core.graphics.PathParser;
/* loaded from: classes.dex */
public class GraphicsUtils {
    public static Runnable sOnNewBitmapRunnable = new Runnable() { // from class: com.android.launcher3.icons.GraphicsUtils$$ExternalSyntheticLambda0
        @Override // java.lang.Runnable
        public final void run() {
            GraphicsUtils.lambda$static$0();
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$static$0() {
    }

    public static int getArea(Region region) {
        RegionIterator regionIterator = new RegionIterator(region);
        Rect rect = new Rect();
        int i = 0;
        while (regionIterator.next(rect)) {
            i += rect.width() * rect.height();
        }
        return i;
    }

    public static void noteNewBitmapCreated() {
        sOnNewBitmapRunnable.run();
    }

    public static Path getShapePath(Context context, int i) {
        Path createPathFromPathData;
        int i2 = IconProvider.CONFIG_ICON_MASK_RES_ID;
        if (i2 == 0 || (createPathFromPathData = PathParser.createPathFromPathData(context.getString(i2))) == null) {
            AdaptiveIconDrawable adaptiveIconDrawable = new AdaptiveIconDrawable(new ColorDrawable(-16777216), new ColorDrawable(-16777216));
            adaptiveIconDrawable.setBounds(0, 0, i, i);
            return new Path(adaptiveIconDrawable.getIconMask());
        }
        float f = i;
        if (f != 100.0f) {
            Matrix matrix = new Matrix();
            float f2 = f / 100.0f;
            matrix.setScale(f2, f2);
            createPathFromPathData.transform(matrix);
        }
        return createPathFromPathData;
    }

    public static int getAttrColor(Context context, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(new int[]{i});
        int color = obtainStyledAttributes.getColor(0, 0);
        obtainStyledAttributes.recycle();
        return color;
    }

    public static float getFloat(Context context, int i, float f) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(new int[]{i});
        float f2 = obtainStyledAttributes.getFloat(0, f);
        obtainStyledAttributes.recycle();
        return f2;
    }
}
