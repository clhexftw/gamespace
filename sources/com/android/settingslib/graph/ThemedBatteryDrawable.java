package com.android.settingslib.graph;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.BlendMode;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.PathParser;
import com.android.settingslib.R$array;
import com.android.settingslib.R$color;
import com.android.settingslib.Utils;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: ThemedBatteryDrawable.kt */
/* loaded from: classes2.dex */
public class ThemedBatteryDrawable extends Drawable {
    public static final Companion Companion = new Companion(null);
    private int backgroundColor;
    private int batteryLevel;
    private final Path boltPath;
    private boolean charging;
    private int[] colorLevels;
    private final Context context;
    private int criticalLevel;
    private boolean dualTone;
    private final Paint dualToneBackgroundFill;
    private final Paint errorPaint;
    private final Path errorPerimeterPath;
    private int fillColor;
    private final Paint fillColorStrokePaint;
    private final Paint fillColorStrokeProtection;
    private final Path fillMask;
    private final Paint fillPaint;
    private final RectF fillRect;
    private int intrinsicHeight;
    private int intrinsicWidth;
    private final Function0<Unit> invalidateRunnable;
    private boolean invertFillIcon;
    private int levelColor;
    private final Path levelPath;
    private final RectF levelRect;
    private final Rect padding;
    private final Path perimeterPath;
    private final Path plusPath;
    private boolean powerSaveEnabled;
    private final Matrix scaleMatrix;
    private final Path scaledBolt;
    private final Path scaledErrorPerimeter;
    private final Path scaledFill;
    private final Path scaledPerimeter;
    private final Path scaledPlus;
    private boolean showPercent;
    private final Paint textPaint;
    private final Path unifiedPath;

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -1;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
    }

    public ThemedBatteryDrawable(Context context, int i) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.perimeterPath = new Path();
        this.scaledPerimeter = new Path();
        this.errorPerimeterPath = new Path();
        this.scaledErrorPerimeter = new Path();
        this.fillMask = new Path();
        this.scaledFill = new Path();
        this.fillRect = new RectF();
        this.levelRect = new RectF();
        this.levelPath = new Path();
        this.scaleMatrix = new Matrix();
        this.padding = new Rect();
        this.unifiedPath = new Path();
        this.boltPath = new Path();
        this.scaledBolt = new Path();
        this.plusPath = new Path();
        this.scaledPlus = new Path();
        this.fillColor = -65281;
        this.backgroundColor = -65281;
        this.levelColor = -65281;
        this.invalidateRunnable = new Function0<Unit>() { // from class: com.android.settingslib.graph.ThemedBatteryDrawable$invalidateRunnable$1
            /* JADX INFO: Access modifiers changed from: package-private */
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
                ThemedBatteryDrawable.this.invalidateSelf();
            }
        };
        this.criticalLevel = context.getResources().getInteger(17694777);
        Paint paint = new Paint(1);
        paint.setColor(i);
        paint.setAlpha(255);
        paint.setDither(true);
        paint.setStrokeWidth(5.0f);
        paint.setStyle(Paint.Style.STROKE);
        paint.setBlendMode(BlendMode.SRC);
        paint.setStrokeMiter(5.0f);
        paint.setStrokeJoin(Paint.Join.ROUND);
        this.fillColorStrokePaint = paint;
        Paint paint2 = new Paint(1);
        paint2.setDither(true);
        paint2.setStrokeWidth(5.0f);
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setBlendMode(BlendMode.CLEAR);
        paint2.setStrokeMiter(5.0f);
        paint2.setStrokeJoin(Paint.Join.ROUND);
        this.fillColorStrokeProtection = paint2;
        Paint paint3 = new Paint(1);
        paint3.setColor(i);
        paint3.setAlpha(255);
        paint3.setDither(true);
        paint3.setStrokeWidth(0.0f);
        paint3.setStyle(Paint.Style.FILL_AND_STROKE);
        this.fillPaint = paint3;
        Paint paint4 = new Paint(1);
        paint4.setColor(Utils.getColorStateListDefaultColor(context, R$color.batterymeter_plus_color));
        paint4.setAlpha(255);
        paint4.setDither(true);
        paint4.setStrokeWidth(0.0f);
        paint4.setStyle(Paint.Style.FILL_AND_STROKE);
        paint4.setBlendMode(BlendMode.SRC);
        this.errorPaint = paint4;
        Paint paint5 = new Paint(1);
        paint5.setColor(i);
        paint5.setAlpha(85);
        paint5.setDither(true);
        paint5.setStrokeWidth(0.0f);
        paint5.setStyle(Paint.Style.FILL_AND_STROKE);
        this.dualToneBackgroundFill = paint5;
        Paint paint6 = new Paint(1);
        paint6.setTypeface(Typeface.create("sans-serif-condensed", 1));
        paint6.setTextAlign(Paint.Align.CENTER);
        this.textPaint = paint6;
        float f = context.getResources().getDisplayMetrics().density;
        this.intrinsicHeight = (int) (20.0f * f);
        this.intrinsicWidth = (int) (f * 12.0f);
        Resources resources = context.getResources();
        TypedArray obtainTypedArray = resources.obtainTypedArray(R$array.batterymeter_color_levels);
        TypedArray obtainTypedArray2 = resources.obtainTypedArray(R$array.batterymeter_color_values);
        int length = obtainTypedArray.length();
        this.colorLevels = new int[length * 2];
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i2 * 2;
            this.colorLevels[i3] = obtainTypedArray.getInt(i2, 0);
            if (obtainTypedArray2.getType(i2) == 2) {
                this.colorLevels[i3 + 1] = Utils.getColorAttrDefaultColor(this.context, obtainTypedArray2.getThemeAttributeId(i2, 0));
            } else {
                this.colorLevels[i3 + 1] = obtainTypedArray2.getColor(i2, 0);
            }
        }
        obtainTypedArray.recycle();
        obtainTypedArray2.recycle();
        loadPaths();
    }

    public int getCriticalLevel() {
        return this.criticalLevel;
    }

    public final boolean getCharging() {
        return this.charging;
    }

    public final void setCharging(boolean z) {
        this.charging = z;
        postInvalidate();
    }

    public final boolean getPowerSaveEnabled() {
        return this.powerSaveEnabled;
    }

    public final void setPowerSaveEnabled(boolean z) {
        this.powerSaveEnabled = z;
        postInvalidate();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas c) {
        float height;
        Intrinsics.checkNotNullParameter(c, "c");
        c.saveLayer(null, null);
        this.unifiedPath.reset();
        this.levelPath.reset();
        this.levelRect.set(this.fillRect);
        int i = this.batteryLevel;
        float f = i / 100.0f;
        if (i >= 95) {
            height = this.fillRect.top;
        } else {
            RectF rectF = this.fillRect;
            height = (rectF.height() * (1 - f)) + rectF.top;
        }
        this.levelRect.top = (float) Math.floor(height);
        this.levelPath.addRect(this.levelRect, Path.Direction.CCW);
        this.unifiedPath.addPath(this.scaledPerimeter);
        if (!this.dualTone) {
            this.unifiedPath.op(this.levelPath, Path.Op.UNION);
        }
        this.fillPaint.setColor(this.levelColor);
        if (this.charging) {
            this.unifiedPath.op(this.scaledBolt, Path.Op.DIFFERENCE);
            if (!this.invertFillIcon) {
                c.drawPath(this.scaledBolt, this.fillPaint);
            }
        }
        if (this.dualTone) {
            c.drawPath(this.unifiedPath, this.dualToneBackgroundFill);
            c.save();
            c.clipRect(0.0f, getBounds().bottom - (getBounds().height() * f), getBounds().right, getBounds().bottom);
            c.drawPath(this.unifiedPath, this.fillPaint);
            c.restore();
        } else {
            this.fillPaint.setColor(this.fillColor);
            c.drawPath(this.unifiedPath, this.fillPaint);
            this.fillPaint.setColor(this.levelColor);
            if (this.batteryLevel <= 15 && !this.charging) {
                c.save();
                c.clipPath(this.scaledFill);
                c.drawPath(this.levelPath, this.fillPaint);
                c.restore();
            }
        }
        if (this.charging) {
            c.clipOutPath(this.scaledBolt);
            if (this.invertFillIcon) {
                c.drawPath(this.scaledBolt, this.fillColorStrokePaint);
            } else {
                c.drawPath(this.scaledBolt, this.fillColorStrokeProtection);
            }
        } else if (this.powerSaveEnabled) {
            c.drawPath(this.scaledErrorPerimeter, this.errorPaint);
            c.drawPath(this.scaledPlus, this.errorPaint);
        }
        c.restore();
        if (this.charging || this.batteryLevel >= 100 || !this.showPercent) {
            return;
        }
        this.textPaint.setTextSize(getBounds().height() * 0.38f);
        float width = getBounds().width() * 0.5f;
        float height2 = (getBounds().height() + (-this.textPaint.getFontMetrics().ascent)) * 0.5f;
        this.textPaint.setColor(this.fillColor);
        c.drawText(String.valueOf(this.batteryLevel), width, height2, this.textPaint);
        this.textPaint.setColor((~this.fillColor) | (-16777216));
        c.save();
        RectF rectF2 = this.fillRect;
        float f2 = rectF2.left;
        float height3 = rectF2.top + (rectF2.height() * (1 - f));
        RectF rectF3 = this.fillRect;
        c.clipRect(f2, height3, rectF3.right, rectF3.bottom);
        c.drawText(String.valueOf(this.batteryLevel), width, height2, this.textPaint);
        c.restore();
    }

    private final int batteryColorForLevel(int i) {
        if (this.charging || this.powerSaveEnabled) {
            return this.fillColor;
        }
        return getColorForLevel(i);
    }

    private final int getColorForLevel(int i) {
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int[] iArr = this.colorLevels;
            if (i2 >= iArr.length) {
                return i3;
            }
            int i4 = iArr[i2];
            int i5 = iArr[i2 + 1];
            if (i <= i4) {
                return i2 == iArr.length + (-2) ? this.fillColor : i5;
            }
            i2 += 2;
            i3 = i5;
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.fillPaint.setColorFilter(colorFilter);
        this.fillColorStrokePaint.setColorFilter(colorFilter);
        this.dualToneBackgroundFill.setColorFilter(colorFilter);
    }

    public void setBatteryLevel(int i) {
        boolean z;
        if (i >= 67) {
            z = true;
        } else {
            z = i <= 33 ? false : this.invertFillIcon;
        }
        this.invertFillIcon = z;
        this.batteryLevel = i;
        this.levelColor = batteryColorForLevel(i);
        invalidateSelf();
    }

    public final int getBatteryLevel() {
        return this.batteryLevel;
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        updateSize();
    }

    private final void postInvalidate() {
        final Function0<Unit> function0 = this.invalidateRunnable;
        unscheduleSelf(new Runnable(function0) { // from class: com.android.settingslib.graph.ThemedBatteryDrawable$sam$java_lang_Runnable$0
            private final /* synthetic */ Function0 function;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                Intrinsics.checkNotNullParameter(function0, "function");
                this.function = function0;
            }

            @Override // java.lang.Runnable
            public final /* synthetic */ void run() {
                this.function.invoke();
            }
        });
        final Function0<Unit> function02 = this.invalidateRunnable;
        scheduleSelf(new Runnable(function02) { // from class: com.android.settingslib.graph.ThemedBatteryDrawable$sam$java_lang_Runnable$0
            private final /* synthetic */ Function0 function;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                Intrinsics.checkNotNullParameter(function02, "function");
                this.function = function02;
            }

            @Override // java.lang.Runnable
            public final /* synthetic */ void run() {
                this.function.invoke();
            }
        }, 0L);
    }

    private final void updateSize() {
        Rect bounds = getBounds();
        if (bounds.isEmpty()) {
            this.scaleMatrix.setScale(1.0f, 1.0f);
        } else {
            this.scaleMatrix.setScale(bounds.right / 12.0f, bounds.bottom / 20.0f);
        }
        this.perimeterPath.transform(this.scaleMatrix, this.scaledPerimeter);
        this.errorPerimeterPath.transform(this.scaleMatrix, this.scaledErrorPerimeter);
        this.fillMask.transform(this.scaleMatrix, this.scaledFill);
        this.scaledFill.computeBounds(this.fillRect, true);
        this.boltPath.transform(this.scaleMatrix, this.scaledBolt);
        this.plusPath.transform(this.scaleMatrix, this.scaledPlus);
        float max = Math.max((bounds.right / 12.0f) * 3.0f, 6.0f);
        this.fillColorStrokePaint.setStrokeWidth(max);
        this.fillColorStrokeProtection.setStrokeWidth(max);
    }

    private final void loadPaths() {
        this.perimeterPath.set(PathParser.createPathFromPathData(this.context.getResources().getString(17039893)));
        this.perimeterPath.computeBounds(new RectF(), true);
        this.errorPerimeterPath.set(PathParser.createPathFromPathData(this.context.getResources().getString(17039891)));
        this.errorPerimeterPath.computeBounds(new RectF(), true);
        this.fillMask.set(PathParser.createPathFromPathData(this.context.getResources().getString(17039892)));
        this.fillMask.computeBounds(this.fillRect, true);
        this.boltPath.set(PathParser.createPathFromPathData(this.context.getResources().getString(17039890)));
        this.plusPath.set(PathParser.createPathFromPathData(this.context.getResources().getString(17039894)));
        this.dualTone = this.context.getResources().getBoolean(17891386);
    }

    /* compiled from: ThemedBatteryDrawable.kt */
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
