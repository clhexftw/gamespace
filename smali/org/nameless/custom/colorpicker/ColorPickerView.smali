.class public Lorg/nameless/custom/colorpicker/ColorPickerView;
.super Landroid/view/View;
.source "ColorPickerView.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lorg/nameless/custom/colorpicker/ColorPickerView$OnColorChangedListener;
    }
.end annotation


# instance fields
.field private ALPHA_PANEL_HEIGHT:F

.field private HUE_PANEL_WIDTH:F

.field private PALETTE_CIRCLE_TRACKER_RADIUS:F

.field private PANEL_SPACING:F

.field private RECTANGLE_TRACKER_OFFSET:F

.field private mAlpha:I

.field private mAlphaPaint:Landroid/graphics/Paint;

.field private mAlphaPattern:Lorg/nameless/custom/colorpicker/AlphaPatternDrawable;

.field private mAlphaRect:Landroid/graphics/RectF;

.field private mAlphaShader:Landroid/graphics/Shader;

.field private mAlphaSliderText:Ljava/lang/String;

.field private mAlphaTextPaint:Landroid/graphics/Paint;

.field private mBorderColor:I

.field private mBorderPaint:Landroid/graphics/Paint;

.field private mDensity:F

.field private mDrawingOffset:F

.field private mDrawingRect:Landroid/graphics/RectF;

.field private mHue:F

.field private mHuePaint:Landroid/graphics/Paint;

.field private mHueRect:Landroid/graphics/RectF;

.field private mHueShader:Landroid/graphics/Shader;

.field private mHueTrackerPaint:Landroid/graphics/Paint;

.field private mLastTouchedPanel:I

.field private mListener:Lorg/nameless/custom/colorpicker/ColorPickerView$OnColorChangedListener;

.field private mSat:F

.field private mSatShader:Landroid/graphics/Shader;

.field private mSatValPaint:Landroid/graphics/Paint;

.field private mSatValRect:Landroid/graphics/RectF;

.field private mSatValTrackerPaint:Landroid/graphics/Paint;

.field private mShowAlphaPanel:Z

.field private mSliderTrackerColor:I

.field private mStartTouchPoint:Landroid/graphics/Point;

.field private mVal:F

.field private mValShader:Landroid/graphics/Shader;


# direct methods
.method public constructor <init>(Landroid/content/Context;)V
    .locals 1

    const/4 v0, 0x0

    .line 143
    invoke-direct {p0, p1, v0}, Lorg/nameless/custom/colorpicker/ColorPickerView;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 1

    const/4 v0, 0x0

    .line 147
    invoke-direct {p0, p1, p2, v0}, Lorg/nameless/custom/colorpicker/ColorPickerView;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V
    .locals 0

    .line 151
    invoke-direct {p0, p1, p2, p3}, Landroid/view/View;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    const/high16 p1, 0x41f00000    # 30.0f

    .line 59
    iput p1, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->HUE_PANEL_WIDTH:F

    const/high16 p1, 0x41a00000    # 20.0f

    .line 63
    iput p1, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->ALPHA_PANEL_HEIGHT:F

    const/high16 p1, 0x41200000    # 10.0f

    .line 68
    iput p1, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->PANEL_SPACING:F

    const/high16 p1, 0x40a00000    # 5.0f

    .line 72
    iput p1, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->PALETTE_CIRCLE_TRACKER_RADIUS:F

    const/high16 p1, 0x40000000    # 2.0f

    .line 77
    iput p1, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->RECTANGLE_TRACKER_OFFSET:F

    const/high16 p1, 0x3f800000    # 1.0f

    .line 80
    iput p1, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mDensity:F

    const/16 p1, 0xff

    .line 100
    iput p1, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mAlpha:I

    const/high16 p1, 0x43b40000    # 360.0f

    .line 101
    iput p1, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mHue:F

    const/4 p1, 0x0

    .line 102
    iput p1, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mSat:F

    .line 103
    iput p1, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mVal:F

    const-string p1, ""

    .line 105
    iput-object p1, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mAlphaSliderText:Ljava/lang/String;

    const p1, -0xe3e3e4

    .line 106
    iput p1, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mSliderTrackerColor:I

    const p1, -0x919192

    .line 107
    iput p1, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mBorderColor:I

    const/4 p1, 0x0

    .line 108
    iput-boolean p1, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mShowAlphaPanel:Z

    .line 114
    iput p1, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mLastTouchedPanel:I

    const/4 p1, 0x0

    .line 136
    iput-object p1, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mStartTouchPoint:Landroid/graphics/Point;

    .line 152
    invoke-direct {p0}, Lorg/nameless/custom/colorpicker/ColorPickerView;->init()V

    return-void
.end method

.method private alphaToPoint(I)Landroid/graphics/Point;
    .locals 3

    .line 386
    iget-object p0, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mAlphaRect:Landroid/graphics/RectF;

    .line 387
    invoke-virtual {p0}, Landroid/graphics/RectF;->width()F

    move-result v0

    .line 389
    new-instance v1, Landroid/graphics/Point;

    invoke-direct {v1}, Landroid/graphics/Point;-><init>()V

    int-to-float p1, p1

    mul-float/2addr p1, v0

    const/high16 v2, 0x437f0000    # 255.0f

    div-float/2addr p1, v2

    sub-float/2addr v0, p1

    .line 391
    iget p1, p0, Landroid/graphics/RectF;->left:F

    add-float/2addr v0, p1

    float-to-int p1, v0

    iput p1, v1, Landroid/graphics/Point;->x:I

    .line 392
    iget p0, p0, Landroid/graphics/RectF;->top:F

    float-to-int p0, p0

    iput p0, v1, Landroid/graphics/Point;->y:I

    return-object v1
.end method

.method private buildHueColorArray()[I
    .locals 7

    const/16 p0, 0x169

    new-array p0, p0, [I

    const/4 v0, 0x0

    const/16 v1, 0x168

    move v2, v0

    :goto_0
    if-ltz v1, :cond_0

    const/4 v3, 0x3

    new-array v3, v3, [F

    int-to-float v4, v1

    aput v4, v3, v0

    const/4 v4, 0x1

    const/high16 v5, 0x3f800000    # 1.0f

    aput v5, v3, v4

    const/4 v6, 0x2

    aput v5, v3, v6

    .line 215
    invoke-static {v3}, Landroid/graphics/Color;->HSVToColor([F)I

    move-result v3

    aput v3, p0, v2

    add-int/lit8 v1, v1, -0x1

    add-int/2addr v2, v4

    goto :goto_0

    :cond_0
    return-object p0
.end method

.method private calculateRequiredOffset()F
    .locals 2

    .line 203
    iget v0, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->PALETTE_CIRCLE_TRACKER_RADIUS:F

    iget v1, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->RECTANGLE_TRACKER_OFFSET:F

    invoke-static {v0, v1}, Ljava/lang/Math;->max(FF)F

    move-result v0

    .line 204
    iget p0, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mDensity:F

    const/high16 v1, 0x3f800000    # 1.0f

    mul-float/2addr p0, v1

    invoke-static {v0, p0}, Ljava/lang/Math;->max(FF)F

    move-result p0

    const/high16 v0, 0x3fc00000    # 1.5f

    mul-float/2addr p0, v0

    return p0
.end method

.method private chooseHeight(II)I
    .locals 1

    const/high16 v0, -0x80000000

    if-eq p1, v0, :cond_1

    const/high16 v0, 0x40000000    # 2.0f

    if-ne p1, v0, :cond_0

    goto :goto_0

    .line 716
    :cond_0
    invoke-direct {p0}, Lorg/nameless/custom/colorpicker/ColorPickerView;->getPrefferedHeight()I

    move-result p0

    return p0

    :cond_1
    :goto_0
    return p2
.end method

.method private chooseWidth(II)I
    .locals 1

    const/high16 v0, -0x80000000

    if-eq p1, v0, :cond_1

    const/high16 v0, 0x40000000    # 2.0f

    if-ne p1, v0, :cond_0

    goto :goto_0

    .line 708
    :cond_0
    invoke-direct {p0}, Lorg/nameless/custom/colorpicker/ColorPickerView;->getPrefferedWidth()I

    move-result p0

    return p0

    :cond_1
    :goto_0
    return p2
.end method

.method private drawAlphaPanel(Landroid/graphics/Canvas;)V
    .locals 13

    .line 309
    iget-boolean v0, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mShowAlphaPanel:Z

    if-eqz v0, :cond_2

    iget-object v0, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mAlphaRect:Landroid/graphics/RectF;

    if-eqz v0, :cond_2

    iget-object v1, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mAlphaPattern:Lorg/nameless/custom/colorpicker/AlphaPatternDrawable;

    if-nez v1, :cond_0

    goto/16 :goto_0

    .line 314
    :cond_0
    iget-object v1, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mBorderPaint:Landroid/graphics/Paint;

    iget v2, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mBorderColor:I

    invoke-virtual {v1, v2}, Landroid/graphics/Paint;->setColor(I)V

    .line 315
    iget v1, v0, Landroid/graphics/RectF;->left:F

    const/high16 v2, 0x3f800000    # 1.0f

    sub-float v4, v1, v2

    iget v1, v0, Landroid/graphics/RectF;->top:F

    sub-float v5, v1, v2

    iget v1, v0, Landroid/graphics/RectF;->right:F

    add-float v6, v1, v2

    iget v1, v0, Landroid/graphics/RectF;->bottom:F

    add-float v7, v1, v2

    iget-object v8, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mBorderPaint:Landroid/graphics/Paint;

    move-object v3, p1

    invoke-virtual/range {v3 .. v8}, Landroid/graphics/Canvas;->drawRect(FFFFLandroid/graphics/Paint;)V

    .line 323
    iget-object v1, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mAlphaPattern:Lorg/nameless/custom/colorpicker/AlphaPatternDrawable;

    invoke-virtual {v1, p1}, Lorg/nameless/custom/colorpicker/AlphaPatternDrawable;->draw(Landroid/graphics/Canvas;)V

    const/4 v1, 0x3

    new-array v1, v1, [F

    .line 325
    iget v2, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mHue:F

    const/4 v3, 0x0

    aput v2, v1, v3

    const/4 v2, 0x1

    iget v4, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mSat:F

    aput v4, v1, v2

    const/4 v2, 0x2

    iget v4, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mVal:F

    aput v4, v1, v2

    .line 326
    invoke-static {v1}, Landroid/graphics/Color;->HSVToColor([F)I

    move-result v10

    .line 327
    invoke-static {v3, v1}, Landroid/graphics/Color;->HSVToColor(I[F)I

    move-result v11

    .line 329
    new-instance v1, Landroid/graphics/LinearGradient;

    iget v6, v0, Landroid/graphics/RectF;->left:F

    iget v9, v0, Landroid/graphics/RectF;->top:F

    iget v8, v0, Landroid/graphics/RectF;->right:F

    sget-object v12, Landroid/graphics/Shader$TileMode;->CLAMP:Landroid/graphics/Shader$TileMode;

    move-object v5, v1

    move v7, v9

    invoke-direct/range {v5 .. v12}, Landroid/graphics/LinearGradient;-><init>(FFFFIILandroid/graphics/Shader$TileMode;)V

    iput-object v1, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mAlphaShader:Landroid/graphics/Shader;

    .line 333
    iget-object v2, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mAlphaPaint:Landroid/graphics/Paint;

    invoke-virtual {v2, v1}, Landroid/graphics/Paint;->setShader(Landroid/graphics/Shader;)Landroid/graphics/Shader;

    .line 335
    iget-object v1, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mAlphaPaint:Landroid/graphics/Paint;

    invoke-virtual {p1, v0, v1}, Landroid/graphics/Canvas;->drawRect(Landroid/graphics/RectF;Landroid/graphics/Paint;)V

    .line 337
    iget-object v1, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mAlphaSliderText:Ljava/lang/String;

    const/high16 v2, 0x40800000    # 4.0f

    if-eqz v1, :cond_1

    const-string v3, ""

    if-eq v1, v3, :cond_1

    .line 338
    invoke-virtual {v0}, Landroid/graphics/RectF;->centerX()F

    move-result v3

    .line 339
    invoke-virtual {v0}, Landroid/graphics/RectF;->centerY()F

    move-result v4

    iget v5, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mDensity:F

    mul-float/2addr v5, v2

    add-float/2addr v4, v5

    iget-object v5, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mAlphaTextPaint:Landroid/graphics/Paint;

    .line 338
    invoke-virtual {p1, v1, v3, v4, v5}, Landroid/graphics/Canvas;->drawText(Ljava/lang/String;FFLandroid/graphics/Paint;)V

    .line 342
    :cond_1
    iget v1, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mDensity:F

    mul-float/2addr v1, v2

    const/high16 v2, 0x40000000    # 2.0f

    div-float/2addr v1, v2

    .line 344
    iget v3, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mAlpha:I

    invoke-direct {p0, v3}, Lorg/nameless/custom/colorpicker/ColorPickerView;->alphaToPoint(I)Landroid/graphics/Point;

    move-result-object v3

    .line 346
    new-instance v4, Landroid/graphics/RectF;

    invoke-direct {v4}, Landroid/graphics/RectF;-><init>()V

    .line 347
    iget v3, v3, Landroid/graphics/Point;->x:I

    int-to-float v5, v3

    sub-float/2addr v5, v1

    iput v5, v4, Landroid/graphics/RectF;->left:F

    int-to-float v3, v3

    add-float/2addr v3, v1

    .line 348
    iput v3, v4, Landroid/graphics/RectF;->right:F

    .line 349
    iget v1, v0, Landroid/graphics/RectF;->top:F

    iget v3, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->RECTANGLE_TRACKER_OFFSET:F

    sub-float/2addr v1, v3

    iput v1, v4, Landroid/graphics/RectF;->top:F

    .line 350
    iget v0, v0, Landroid/graphics/RectF;->bottom:F

    add-float/2addr v0, v3

    iput v0, v4, Landroid/graphics/RectF;->bottom:F

    .line 352
    iget-object p0, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mHueTrackerPaint:Landroid/graphics/Paint;

    invoke-virtual {p1, v4, v2, v2, p0}, Landroid/graphics/Canvas;->drawRoundRect(Landroid/graphics/RectF;FFLandroid/graphics/Paint;)V

    :cond_2
    :goto_0
    return-void
.end method

.method private drawHuePanel(Landroid/graphics/Canvas;)V
    .locals 10

    .line 272
    iget-object v0, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mHueRect:Landroid/graphics/RectF;

    .line 275
    iget-object v1, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mBorderPaint:Landroid/graphics/Paint;

    iget v2, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mBorderColor:I

    invoke-virtual {v1, v2}, Landroid/graphics/Paint;->setColor(I)V

    .line 276
    iget v1, v0, Landroid/graphics/RectF;->left:F

    const/high16 v2, 0x3f800000    # 1.0f

    sub-float v4, v1, v2

    iget v1, v0, Landroid/graphics/RectF;->top:F

    sub-float v5, v1, v2

    iget v1, v0, Landroid/graphics/RectF;->right:F

    add-float v6, v1, v2

    iget v1, v0, Landroid/graphics/RectF;->bottom:F

    add-float v7, v1, v2

    iget-object v8, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mBorderPaint:Landroid/graphics/Paint;

    move-object v3, p1

    invoke-virtual/range {v3 .. v8}, Landroid/graphics/Canvas;->drawRect(FFFFLandroid/graphics/Paint;)V

    .line 283
    iget-object v1, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mHueShader:Landroid/graphics/Shader;

    if-nez v1, :cond_0

    .line 284
    new-instance v1, Landroid/graphics/LinearGradient;

    iget v5, v0, Landroid/graphics/RectF;->left:F

    iget v4, v0, Landroid/graphics/RectF;->top:F

    iget v6, v0, Landroid/graphics/RectF;->bottom:F

    .line 286
    invoke-direct {p0}, Lorg/nameless/custom/colorpicker/ColorPickerView;->buildHueColorArray()[I

    move-result-object v7

    const/4 v8, 0x0

    sget-object v9, Landroid/graphics/Shader$TileMode;->CLAMP:Landroid/graphics/Shader$TileMode;

    move-object v2, v1

    move v3, v5

    invoke-direct/range {v2 .. v9}, Landroid/graphics/LinearGradient;-><init>(FFFF[I[FLandroid/graphics/Shader$TileMode;)V

    iput-object v1, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mHueShader:Landroid/graphics/Shader;

    .line 287
    iget-object v2, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mHuePaint:Landroid/graphics/Paint;

    invoke-virtual {v2, v1}, Landroid/graphics/Paint;->setShader(Landroid/graphics/Shader;)Landroid/graphics/Shader;

    .line 290
    :cond_0
    iget-object v1, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mHuePaint:Landroid/graphics/Paint;

    invoke-virtual {p1, v0, v1}, Landroid/graphics/Canvas;->drawRect(Landroid/graphics/RectF;Landroid/graphics/Paint;)V

    const/high16 v1, 0x40800000    # 4.0f

    .line 292
    iget v2, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mDensity:F

    mul-float/2addr v2, v1

    const/high16 v1, 0x40000000    # 2.0f

    div-float/2addr v2, v1

    .line 294
    iget v3, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mHue:F

    invoke-direct {p0, v3}, Lorg/nameless/custom/colorpicker/ColorPickerView;->hueToPoint(F)Landroid/graphics/Point;

    move-result-object v3

    .line 296
    new-instance v4, Landroid/graphics/RectF;

    invoke-direct {v4}, Landroid/graphics/RectF;-><init>()V

    .line 297
    iget v5, v0, Landroid/graphics/RectF;->left:F

    iget v6, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->RECTANGLE_TRACKER_OFFSET:F

    sub-float/2addr v5, v6

    iput v5, v4, Landroid/graphics/RectF;->left:F

    .line 298
    iget v0, v0, Landroid/graphics/RectF;->right:F

    add-float/2addr v0, v6

    iput v0, v4, Landroid/graphics/RectF;->right:F

    .line 299
    iget v0, v3, Landroid/graphics/Point;->y:I

    int-to-float v3, v0

    sub-float/2addr v3, v2

    iput v3, v4, Landroid/graphics/RectF;->top:F

    int-to-float v0, v0

    add-float/2addr v0, v2

    .line 300
    iput v0, v4, Landroid/graphics/RectF;->bottom:F

    .line 303
    iget-object p0, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mHueTrackerPaint:Landroid/graphics/Paint;

    invoke-virtual {p1, v4, v1, v1, p0}, Landroid/graphics/Canvas;->drawRoundRect(Landroid/graphics/RectF;FFLandroid/graphics/Paint;)V

    return-void
.end method

.method private drawSatValPanel(Landroid/graphics/Canvas;)V
    .locals 18

    move-object/from16 v0, p0

    move-object/from16 v7, p1

    .line 235
    iget-object v8, v0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mSatValRect:Landroid/graphics/RectF;

    .line 238
    iget-object v1, v0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mBorderPaint:Landroid/graphics/Paint;

    iget v2, v0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mBorderColor:I

    invoke-virtual {v1, v2}, Landroid/graphics/Paint;->setColor(I)V

    .line 239
    iget-object v1, v0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mDrawingRect:Landroid/graphics/RectF;

    iget v2, v1, Landroid/graphics/RectF;->left:F

    iget v3, v1, Landroid/graphics/RectF;->top:F

    iget v1, v8, Landroid/graphics/RectF;->right:F

    const/high16 v9, 0x3f800000    # 1.0f

    add-float v4, v1, v9

    iget v1, v8, Landroid/graphics/RectF;->bottom:F

    add-float v5, v1, v9

    iget-object v6, v0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mBorderPaint:Landroid/graphics/Paint;

    move-object/from16 v1, p1

    invoke-virtual/range {v1 .. v6}, Landroid/graphics/Canvas;->drawRect(FFFFLandroid/graphics/Paint;)V

    .line 244
    iget-object v1, v0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mValShader:Landroid/graphics/Shader;

    if-nez v1, :cond_0

    .line 245
    new-instance v1, Landroid/graphics/LinearGradient;

    iget v13, v8, Landroid/graphics/RectF;->left:F

    iget v12, v8, Landroid/graphics/RectF;->top:F

    iget v14, v8, Landroid/graphics/RectF;->bottom:F

    const/4 v15, -0x1

    const/high16 v16, -0x1000000

    sget-object v17, Landroid/graphics/Shader$TileMode;->CLAMP:Landroid/graphics/Shader$TileMode;

    move-object v10, v1

    move v11, v13

    invoke-direct/range {v10 .. v17}, Landroid/graphics/LinearGradient;-><init>(FFFFIILandroid/graphics/Shader$TileMode;)V

    iput-object v1, v0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mValShader:Landroid/graphics/Shader;

    :cond_0
    const/4 v1, 0x3

    new-array v1, v1, [F

    const/4 v2, 0x0

    .line 249
    iget v3, v0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mHue:F

    aput v3, v1, v2

    const/4 v2, 0x1

    aput v9, v1, v2

    const/4 v2, 0x2

    aput v9, v1, v2

    invoke-static {v1}, Landroid/graphics/Color;->HSVToColor([F)I

    move-result v16

    .line 251
    new-instance v1, Landroid/graphics/LinearGradient;

    iget v11, v8, Landroid/graphics/RectF;->left:F

    iget v14, v8, Landroid/graphics/RectF;->top:F

    iget v13, v8, Landroid/graphics/RectF;->right:F

    const/4 v15, -0x1

    sget-object v17, Landroid/graphics/Shader$TileMode;->CLAMP:Landroid/graphics/Shader$TileMode;

    move-object v10, v1

    move v12, v14

    invoke-direct/range {v10 .. v17}, Landroid/graphics/LinearGradient;-><init>(FFFFIILandroid/graphics/Shader$TileMode;)V

    iput-object v1, v0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mSatShader:Landroid/graphics/Shader;

    .line 253
    new-instance v1, Landroid/graphics/ComposeShader;

    iget-object v2, v0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mValShader:Landroid/graphics/Shader;

    iget-object v3, v0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mSatShader:Landroid/graphics/Shader;

    sget-object v4, Landroid/graphics/PorterDuff$Mode;->MULTIPLY:Landroid/graphics/PorterDuff$Mode;

    invoke-direct {v1, v2, v3, v4}, Landroid/graphics/ComposeShader;-><init>(Landroid/graphics/Shader;Landroid/graphics/Shader;Landroid/graphics/PorterDuff$Mode;)V

    .line 255
    iget-object v2, v0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mSatValPaint:Landroid/graphics/Paint;

    invoke-virtual {v2, v1}, Landroid/graphics/Paint;->setShader(Landroid/graphics/Shader;)Landroid/graphics/Shader;

    .line 257
    iget-object v1, v0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mSatValPaint:Landroid/graphics/Paint;

    invoke-virtual {v7, v8, v1}, Landroid/graphics/Canvas;->drawRect(Landroid/graphics/RectF;Landroid/graphics/Paint;)V

    .line 259
    iget v1, v0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mSat:F

    iget v2, v0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mVal:F

    invoke-direct {v0, v1, v2}, Lorg/nameless/custom/colorpicker/ColorPickerView;->satValToPoint(FF)Landroid/graphics/Point;

    move-result-object v1

    .line 261
    iget-object v2, v0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mSatValTrackerPaint:Landroid/graphics/Paint;

    const/high16 v3, -0x1000000

    invoke-virtual {v2, v3}, Landroid/graphics/Paint;->setColor(I)V

    .line 262
    iget v2, v1, Landroid/graphics/Point;->x:I

    int-to-float v2, v2

    iget v3, v1, Landroid/graphics/Point;->y:I

    int-to-float v3, v3

    iget v4, v0, Lorg/nameless/custom/colorpicker/ColorPickerView;->PALETTE_CIRCLE_TRACKER_RADIUS:F

    iget v5, v0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mDensity:F

    mul-float/2addr v5, v9

    sub-float/2addr v4, v5

    iget-object v5, v0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mSatValTrackerPaint:Landroid/graphics/Paint;

    invoke-virtual {v7, v2, v3, v4, v5}, Landroid/graphics/Canvas;->drawCircle(FFFLandroid/graphics/Paint;)V

    .line 265
    iget-object v2, v0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mSatValTrackerPaint:Landroid/graphics/Paint;

    const v3, -0x222223

    invoke-virtual {v2, v3}, Landroid/graphics/Paint;->setColor(I)V

    .line 266
    iget v2, v1, Landroid/graphics/Point;->x:I

    int-to-float v2, v2

    iget v1, v1, Landroid/graphics/Point;->y:I

    int-to-float v1, v1

    iget v3, v0, Lorg/nameless/custom/colorpicker/ColorPickerView;->PALETTE_CIRCLE_TRACKER_RADIUS:F

    iget-object v0, v0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mSatValTrackerPaint:Landroid/graphics/Paint;

    invoke-virtual {v7, v2, v1, v3, v0}, Landroid/graphics/Canvas;->drawCircle(FFFLandroid/graphics/Paint;)V

    return-void
.end method

.method private getPrefferedHeight()I
    .locals 2

    .line 735
    iget v0, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mDensity:F

    const/high16 v1, 0x43480000    # 200.0f

    mul-float/2addr v0, v1

    float-to-int v0, v0

    .line 737
    iget-boolean v1, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mShowAlphaPanel:Z

    if-eqz v1, :cond_0

    int-to-float v0, v0

    .line 738
    iget v1, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->PANEL_SPACING:F

    iget p0, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->ALPHA_PANEL_HEIGHT:F

    add-float/2addr v1, p0

    add-float/2addr v0, v1

    float-to-int v0, v0

    :cond_0
    return v0
.end method

.method private getPrefferedWidth()I
    .locals 3

    .line 722
    invoke-direct {p0}, Lorg/nameless/custom/colorpicker/ColorPickerView;->getPrefferedHeight()I

    move-result v0

    .line 724
    iget-boolean v1, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mShowAlphaPanel:Z

    if-eqz v1, :cond_0

    int-to-float v0, v0

    .line 725
    iget v1, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->PANEL_SPACING:F

    iget v2, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->ALPHA_PANEL_HEIGHT:F

    add-float/2addr v1, v2

    sub-float/2addr v0, v1

    float-to-int v0, v0

    :cond_0
    int-to-float v0, v0

    .line 729
    iget v1, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->HUE_PANEL_WIDTH:F

    add-float/2addr v0, v1

    iget p0, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->PANEL_SPACING:F

    add-float/2addr v0, p0

    float-to-int p0, v0

    return p0
.end method

.method private hueToPoint(F)Landroid/graphics/Point;
    .locals 3

    .line 359
    iget-object p0, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mHueRect:Landroid/graphics/RectF;

    .line 360
    invoke-virtual {p0}, Landroid/graphics/RectF;->height()F

    move-result v0

    .line 362
    new-instance v1, Landroid/graphics/Point;

    invoke-direct {v1}, Landroid/graphics/Point;-><init>()V

    mul-float/2addr p1, v0

    const/high16 v2, 0x43b40000    # 360.0f

    div-float/2addr p1, v2

    sub-float/2addr v0, p1

    .line 364
    iget p1, p0, Landroid/graphics/RectF;->top:F

    add-float/2addr v0, p1

    float-to-int p1, v0

    iput p1, v1, Landroid/graphics/Point;->y:I

    .line 365
    iget p0, p0, Landroid/graphics/RectF;->left:F

    float-to-int p0, p0

    iput p0, v1, Landroid/graphics/Point;->x:I

    return-object v1
.end method

.method private init()V
    .locals 3

    const/4 v0, 0x1

    const/4 v1, 0x0

    .line 156
    invoke-virtual {p0, v0, v1}, Landroid/view/View;->setLayerType(ILandroid/graphics/Paint;)V

    .line 157
    invoke-virtual {p0}, Landroid/view/View;->getContext()Landroid/content/Context;

    move-result-object v1

    invoke-virtual {v1}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    invoke-virtual {v1}, Landroid/content/res/Resources;->getDisplayMetrics()Landroid/util/DisplayMetrics;

    move-result-object v1

    iget v1, v1, Landroid/util/DisplayMetrics;->density:F

    iput v1, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mDensity:F

    .line 158
    iget v2, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->PALETTE_CIRCLE_TRACKER_RADIUS:F

    mul-float/2addr v2, v1

    iput v2, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->PALETTE_CIRCLE_TRACKER_RADIUS:F

    .line 159
    iget v2, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->RECTANGLE_TRACKER_OFFSET:F

    mul-float/2addr v2, v1

    iput v2, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->RECTANGLE_TRACKER_OFFSET:F

    .line 160
    iget v2, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->HUE_PANEL_WIDTH:F

    mul-float/2addr v2, v1

    iput v2, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->HUE_PANEL_WIDTH:F

    .line 161
    iget v2, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->ALPHA_PANEL_HEIGHT:F

    mul-float/2addr v2, v1

    iput v2, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->ALPHA_PANEL_HEIGHT:F

    .line 162
    iget v2, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->PANEL_SPACING:F

    mul-float/2addr v2, v1

    iput v2, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->PANEL_SPACING:F

    .line 164
    invoke-direct {p0}, Lorg/nameless/custom/colorpicker/ColorPickerView;->calculateRequiredOffset()F

    move-result v1

    iput v1, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mDrawingOffset:F

    .line 166
    invoke-direct {p0}, Lorg/nameless/custom/colorpicker/ColorPickerView;->initPaintTools()V

    .line 169
    invoke-virtual {p0, v0}, Landroid/view/View;->setFocusable(Z)V

    .line 170
    invoke-virtual {p0, v0}, Landroid/view/View;->setFocusableInTouchMode(Z)V

    return-void
.end method

.method private initPaintTools()V
    .locals 4

    .line 175
    new-instance v0, Landroid/graphics/Paint;

    invoke-direct {v0}, Landroid/graphics/Paint;-><init>()V

    iput-object v0, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mSatValPaint:Landroid/graphics/Paint;

    .line 176
    new-instance v0, Landroid/graphics/Paint;

    invoke-direct {v0}, Landroid/graphics/Paint;-><init>()V

    iput-object v0, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mSatValTrackerPaint:Landroid/graphics/Paint;

    .line 177
    new-instance v0, Landroid/graphics/Paint;

    invoke-direct {v0}, Landroid/graphics/Paint;-><init>()V

    iput-object v0, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mHuePaint:Landroid/graphics/Paint;

    .line 178
    new-instance v0, Landroid/graphics/Paint;

    invoke-direct {v0}, Landroid/graphics/Paint;-><init>()V

    iput-object v0, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mHueTrackerPaint:Landroid/graphics/Paint;

    .line 179
    new-instance v0, Landroid/graphics/Paint;

    invoke-direct {v0}, Landroid/graphics/Paint;-><init>()V

    iput-object v0, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mAlphaPaint:Landroid/graphics/Paint;

    .line 180
    new-instance v0, Landroid/graphics/Paint;

    invoke-direct {v0}, Landroid/graphics/Paint;-><init>()V

    iput-object v0, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mAlphaTextPaint:Landroid/graphics/Paint;

    .line 181
    new-instance v0, Landroid/graphics/Paint;

    invoke-direct {v0}, Landroid/graphics/Paint;-><init>()V

    iput-object v0, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mBorderPaint:Landroid/graphics/Paint;

    .line 184
    iget-object v0, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mSatValTrackerPaint:Landroid/graphics/Paint;

    sget-object v1, Landroid/graphics/Paint$Style;->STROKE:Landroid/graphics/Paint$Style;

    invoke-virtual {v0, v1}, Landroid/graphics/Paint;->setStyle(Landroid/graphics/Paint$Style;)V

    .line 185
    iget-object v0, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mSatValTrackerPaint:Landroid/graphics/Paint;

    iget v1, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mDensity:F

    const/high16 v2, 0x40000000    # 2.0f

    mul-float/2addr v1, v2

    invoke-virtual {v0, v1}, Landroid/graphics/Paint;->setStrokeWidth(F)V

    .line 186
    iget-object v0, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mSatValTrackerPaint:Landroid/graphics/Paint;

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Landroid/graphics/Paint;->setAntiAlias(Z)V

    .line 188
    iget-object v0, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mHueTrackerPaint:Landroid/graphics/Paint;

    iget v3, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mSliderTrackerColor:I

    invoke-virtual {v0, v3}, Landroid/graphics/Paint;->setColor(I)V

    .line 189
    iget-object v0, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mHueTrackerPaint:Landroid/graphics/Paint;

    sget-object v3, Landroid/graphics/Paint$Style;->STROKE:Landroid/graphics/Paint$Style;

    invoke-virtual {v0, v3}, Landroid/graphics/Paint;->setStyle(Landroid/graphics/Paint$Style;)V

    .line 190
    iget-object v0, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mHueTrackerPaint:Landroid/graphics/Paint;

    iget v3, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mDensity:F

    mul-float/2addr v3, v2

    invoke-virtual {v0, v3}, Landroid/graphics/Paint;->setStrokeWidth(F)V

    .line 191
    iget-object v0, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mHueTrackerPaint:Landroid/graphics/Paint;

    invoke-virtual {v0, v1}, Landroid/graphics/Paint;->setAntiAlias(Z)V

    .line 193
    iget-object v0, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mAlphaTextPaint:Landroid/graphics/Paint;

    const v2, -0xe3e3e4

    invoke-virtual {v0, v2}, Landroid/graphics/Paint;->setColor(I)V

    .line 194
    iget-object v0, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mAlphaTextPaint:Landroid/graphics/Paint;

    iget v2, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mDensity:F

    const/high16 v3, 0x41600000    # 14.0f

    mul-float/2addr v2, v3

    invoke-virtual {v0, v2}, Landroid/graphics/Paint;->setTextSize(F)V

    .line 195
    iget-object v0, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mAlphaTextPaint:Landroid/graphics/Paint;

    invoke-virtual {v0, v1}, Landroid/graphics/Paint;->setAntiAlias(Z)V

    .line 196
    iget-object v0, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mAlphaTextPaint:Landroid/graphics/Paint;

    sget-object v2, Landroid/graphics/Paint$Align;->CENTER:Landroid/graphics/Paint$Align;

    invoke-virtual {v0, v2}, Landroid/graphics/Paint;->setTextAlign(Landroid/graphics/Paint$Align;)V

    .line 197
    iget-object p0, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mAlphaTextPaint:Landroid/graphics/Paint;

    invoke-virtual {p0, v1}, Landroid/graphics/Paint;->setFakeBoldText(Z)V

    return-void
.end method

.method private moveTrackersIfNeeded(Landroid/view/MotionEvent;)Z
    .locals 5

    .line 620
    iget-object v0, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mStartTouchPoint:Landroid/graphics/Point;

    const/4 v1, 0x0

    if-nez v0, :cond_0

    return v1

    .line 624
    :cond_0
    iget v2, v0, Landroid/graphics/Point;->x:I

    .line 625
    iget v0, v0, Landroid/graphics/Point;->y:I

    .line 628
    iget-object v3, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mHueRect:Landroid/graphics/RectF;

    int-to-float v2, v2

    int-to-float v0, v0

    invoke-virtual {v3, v2, v0}, Landroid/graphics/RectF;->contains(FF)Z

    move-result v3

    const/4 v4, 0x1

    if-eqz v3, :cond_1

    .line 629
    iput v4, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mLastTouchedPanel:I

    .line 631
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getY()F

    move-result p1

    invoke-direct {p0, p1}, Lorg/nameless/custom/colorpicker/ColorPickerView;->pointToHue(F)F

    move-result p1

    iput p1, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mHue:F

    :goto_0
    move v1, v4

    goto :goto_1

    .line 635
    :cond_1
    iget-object v3, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mSatValRect:Landroid/graphics/RectF;

    invoke-virtual {v3, v2, v0}, Landroid/graphics/RectF;->contains(FF)Z

    move-result v3

    if-eqz v3, :cond_2

    .line 637
    iput v1, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mLastTouchedPanel:I

    .line 639
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getX()F

    move-result v0

    invoke-virtual {p1}, Landroid/view/MotionEvent;->getY()F

    move-result p1

    invoke-direct {p0, v0, p1}, Lorg/nameless/custom/colorpicker/ColorPickerView;->pointToSatVal(FF)[F

    move-result-object p1

    .line 641
    aget v0, p1, v1

    iput v0, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mSat:F

    .line 642
    aget p1, p1, v4

    iput p1, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mVal:F

    goto :goto_0

    .line 646
    :cond_2
    iget-object v3, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mAlphaRect:Landroid/graphics/RectF;

    if-eqz v3, :cond_3

    invoke-virtual {v3, v2, v0}, Landroid/graphics/RectF;->contains(FF)Z

    move-result v0

    if-eqz v0, :cond_3

    const/4 v0, 0x2

    .line 648
    iput v0, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mLastTouchedPanel:I

    .line 650
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getX()F

    move-result p1

    float-to-int p1, p1

    invoke-direct {p0, p1}, Lorg/nameless/custom/colorpicker/ColorPickerView;->pointToAlpha(I)I

    move-result p1

    iput p1, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mAlpha:I

    goto :goto_0

    :cond_3
    :goto_1
    return v1
.end method

.method private pointToAlpha(I)I
    .locals 4

    .line 454
    iget-object p0, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mAlphaRect:Landroid/graphics/RectF;

    .line 455
    invoke-virtual {p0}, Landroid/graphics/RectF;->width()F

    move-result v0

    float-to-int v0, v0

    int-to-float v1, p1

    .line 457
    iget v2, p0, Landroid/graphics/RectF;->left:F

    cmpg-float v3, v1, v2

    if-gez v3, :cond_0

    const/4 p0, 0x0

    goto :goto_0

    .line 460
    :cond_0
    iget p0, p0, Landroid/graphics/RectF;->right:F

    cmpl-float p0, v1, p0

    if-lez p0, :cond_1

    move p0, v0

    goto :goto_0

    :cond_1
    float-to-int p0, v2

    sub-int p0, p1, p0

    :goto_0
    mul-int/lit16 p0, p0, 0xff

    .line 467
    div-int/2addr p0, v0

    rsub-int p0, p0, 0xff

    return p0
.end method

.method private pointToHue(F)F
    .locals 3

    .line 435
    iget-object p0, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mHueRect:Landroid/graphics/RectF;

    .line 437
    invoke-virtual {p0}, Landroid/graphics/RectF;->height()F

    move-result v0

    .line 439
    iget v1, p0, Landroid/graphics/RectF;->top:F

    cmpg-float v2, p1, v1

    if-gez v2, :cond_0

    const/4 p0, 0x0

    goto :goto_0

    .line 442
    :cond_0
    iget p0, p0, Landroid/graphics/RectF;->bottom:F

    cmpl-float p0, p1, p0

    if-lez p0, :cond_1

    move p0, v0

    goto :goto_0

    :cond_1
    sub-float p0, p1, v1

    :goto_0
    const/high16 p1, 0x43b40000    # 360.0f

    mul-float/2addr p0, p1

    div-float/2addr p0, v0

    sub-float/2addr p1, p0

    return p1
.end method

.method private pointToSatVal(FF)[F
    .locals 6

    .line 400
    iget-object p0, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mSatValRect:Landroid/graphics/RectF;

    const/4 v0, 0x2

    new-array v0, v0, [F

    .line 403
    invoke-virtual {p0}, Landroid/graphics/RectF;->width()F

    move-result v1

    .line 404
    invoke-virtual {p0}, Landroid/graphics/RectF;->height()F

    move-result v2

    .line 406
    iget v3, p0, Landroid/graphics/RectF;->left:F

    cmpg-float v4, p1, v3

    const/4 v5, 0x0

    if-gez v4, :cond_0

    move p1, v5

    goto :goto_0

    .line 409
    :cond_0
    iget v4, p0, Landroid/graphics/RectF;->right:F

    cmpl-float v4, p1, v4

    if-lez v4, :cond_1

    move p1, v1

    goto :goto_0

    :cond_1
    sub-float/2addr p1, v3

    .line 416
    :goto_0
    iget v3, p0, Landroid/graphics/RectF;->top:F

    cmpg-float v4, p2, v3

    if-gez v4, :cond_2

    goto :goto_1

    .line 419
    :cond_2
    iget p0, p0, Landroid/graphics/RectF;->bottom:F

    cmpl-float p0, p2, p0

    if-lez p0, :cond_3

    move v5, v2

    goto :goto_1

    :cond_3
    sub-float v5, p2, v3

    :goto_1
    const/4 p0, 0x0

    const/high16 p2, 0x3f800000    # 1.0f

    div-float v1, p2, v1

    mul-float/2addr v1, p1

    aput v1, v0, p0

    const/4 p0, 0x1

    div-float p1, p2, v2

    mul-float/2addr p1, v5

    sub-float/2addr p2, p1

    aput p2, v0, p0

    return-object v0
.end method

.method private satValToPoint(FF)Landroid/graphics/Point;
    .locals 3

    .line 372
    iget-object p0, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mSatValRect:Landroid/graphics/RectF;

    .line 373
    invoke-virtual {p0}, Landroid/graphics/RectF;->height()F

    move-result v0

    .line 374
    invoke-virtual {p0}, Landroid/graphics/RectF;->width()F

    move-result v1

    .line 376
    new-instance v2, Landroid/graphics/Point;

    invoke-direct {v2}, Landroid/graphics/Point;-><init>()V

    mul-float/2addr p1, v1

    .line 378
    iget v1, p0, Landroid/graphics/RectF;->left:F

    add-float/2addr p1, v1

    float-to-int p1, p1

    iput p1, v2, Landroid/graphics/Point;->x:I

    const/high16 p1, 0x3f800000    # 1.0f

    sub-float/2addr p1, p2

    mul-float/2addr p1, v0

    .line 379
    iget p0, p0, Landroid/graphics/RectF;->top:F

    add-float/2addr p1, p0

    float-to-int p0, p1

    iput p0, v2, Landroid/graphics/Point;->y:I

    return-object v2
.end method

.method private setUpAlphaRect()V
    .locals 5

    .line 792
    iget-boolean v0, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mShowAlphaPanel:Z

    if-nez v0, :cond_0

    return-void

    .line 794
    :cond_0
    iget-object v0, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mDrawingRect:Landroid/graphics/RectF;

    .line 796
    iget v1, v0, Landroid/graphics/RectF;->left:F

    const/high16 v2, 0x3f800000    # 1.0f

    add-float/2addr v1, v2

    .line 797
    iget v3, v0, Landroid/graphics/RectF;->bottom:F

    iget v4, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->ALPHA_PANEL_HEIGHT:F

    sub-float v4, v3, v4

    add-float/2addr v4, v2

    sub-float/2addr v3, v2

    .line 799
    iget v0, v0, Landroid/graphics/RectF;->right:F

    sub-float/2addr v0, v2

    .line 801
    new-instance v2, Landroid/graphics/RectF;

    invoke-direct {v2, v1, v4, v0, v3}, Landroid/graphics/RectF;-><init>(FFFF)V

    iput-object v2, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mAlphaRect:Landroid/graphics/RectF;

    .line 803
    new-instance v0, Lorg/nameless/custom/colorpicker/AlphaPatternDrawable;

    const/high16 v1, 0x40a00000    # 5.0f

    iget v2, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mDensity:F

    mul-float/2addr v2, v1

    float-to-int v1, v2

    invoke-direct {v0, v1}, Lorg/nameless/custom/colorpicker/AlphaPatternDrawable;-><init>(I)V

    iput-object v0, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mAlphaPattern:Lorg/nameless/custom/colorpicker/AlphaPatternDrawable;

    .line 804
    iget-object v1, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mAlphaRect:Landroid/graphics/RectF;

    iget v1, v1, Landroid/graphics/RectF;->left:F

    .line 805
    invoke-static {v1}, Ljava/lang/Math;->round(F)I

    move-result v1

    iget-object v2, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mAlphaRect:Landroid/graphics/RectF;

    iget v2, v2, Landroid/graphics/RectF;->top:F

    .line 806
    invoke-static {v2}, Ljava/lang/Math;->round(F)I

    move-result v2

    iget-object v3, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mAlphaRect:Landroid/graphics/RectF;

    iget v3, v3, Landroid/graphics/RectF;->right:F

    .line 807
    invoke-static {v3}, Ljava/lang/Math;->round(F)I

    move-result v3

    iget-object p0, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mAlphaRect:Landroid/graphics/RectF;

    iget p0, p0, Landroid/graphics/RectF;->bottom:F

    .line 808
    invoke-static {p0}, Ljava/lang/Math;->round(F)I

    move-result p0

    .line 804
    invoke-virtual {v0, v1, v2, v3, p0}, Landroid/graphics/drawable/Drawable;->setBounds(IIII)V

    return-void
.end method

.method private setUpHueRect()V
    .locals 7

    .line 779
    iget-object v0, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mDrawingRect:Landroid/graphics/RectF;

    .line 781
    iget v1, v0, Landroid/graphics/RectF;->right:F

    iget v2, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->HUE_PANEL_WIDTH:F

    sub-float v2, v1, v2

    const/high16 v3, 0x3f800000    # 1.0f

    add-float/2addr v2, v3

    .line 782
    iget v4, v0, Landroid/graphics/RectF;->top:F

    add-float/2addr v4, v3

    .line 783
    iget v0, v0, Landroid/graphics/RectF;->bottom:F

    sub-float/2addr v0, v3

    iget-boolean v5, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mShowAlphaPanel:Z

    if-eqz v5, :cond_0

    .line 784
    iget v5, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->PANEL_SPACING:F

    iget v6, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->ALPHA_PANEL_HEIGHT:F

    add-float/2addr v5, v6

    goto :goto_0

    :cond_0
    const/4 v5, 0x0

    :goto_0
    sub-float/2addr v0, v5

    sub-float/2addr v1, v3

    .line 787
    new-instance v3, Landroid/graphics/RectF;

    invoke-direct {v3, v2, v4, v1, v0}, Landroid/graphics/RectF;-><init>(FFFF)V

    iput-object v3, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mHueRect:Landroid/graphics/RectF;

    return-void
.end method

.method private setUpSatValRect()V
    .locals 5

    .line 763
    iget-object v0, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mDrawingRect:Landroid/graphics/RectF;

    .line 764
    invoke-virtual {v0}, Landroid/graphics/RectF;->height()F

    move-result v1

    const/high16 v2, 0x40000000    # 2.0f

    sub-float/2addr v1, v2

    .line 766
    iget-boolean v2, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mShowAlphaPanel:Z

    if-eqz v2, :cond_0

    .line 767
    iget v2, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->PANEL_SPACING:F

    iget v3, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->ALPHA_PANEL_HEIGHT:F

    add-float/2addr v2, v3

    sub-float/2addr v1, v2

    .line 770
    :cond_0
    iget v2, v0, Landroid/graphics/RectF;->left:F

    const/high16 v3, 0x3f800000    # 1.0f

    add-float/2addr v2, v3

    .line 771
    iget v0, v0, Landroid/graphics/RectF;->top:F

    add-float/2addr v0, v3

    add-float v3, v0, v1

    add-float/2addr v1, v2

    .line 775
    new-instance v4, Landroid/graphics/RectF;

    invoke-direct {v4, v2, v0, v1, v3}, Landroid/graphics/RectF;-><init>(FFFF)V

    iput-object v4, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mSatValRect:Landroid/graphics/RectF;

    return-void
.end method


# virtual methods
.method public getDrawingOffset()F
    .locals 0

    .line 893
    iget p0, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mDrawingOffset:F

    return p0
.end method

.method protected onDraw(Landroid/graphics/Canvas;)V
    .locals 2

    .line 225
    iget-object v0, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mDrawingRect:Landroid/graphics/RectF;

    invoke-virtual {v0}, Landroid/graphics/RectF;->width()F

    move-result v0

    const/4 v1, 0x0

    cmpg-float v0, v0, v1

    if-lez v0, :cond_1

    iget-object v0, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mDrawingRect:Landroid/graphics/RectF;

    invoke-virtual {v0}, Landroid/graphics/RectF;->height()F

    move-result v0

    cmpg-float v0, v0, v1

    if-gtz v0, :cond_0

    goto :goto_0

    .line 227
    :cond_0
    invoke-direct {p0, p1}, Lorg/nameless/custom/colorpicker/ColorPickerView;->drawSatValPanel(Landroid/graphics/Canvas;)V

    .line 228
    invoke-direct {p0, p1}, Lorg/nameless/custom/colorpicker/ColorPickerView;->drawHuePanel(Landroid/graphics/Canvas;)V

    .line 229
    invoke-direct {p0, p1}, Lorg/nameless/custom/colorpicker/ColorPickerView;->drawAlphaPanel(Landroid/graphics/Canvas;)V

    :cond_1
    :goto_0
    return-void
.end method

.method protected onMeasure(II)V
    .locals 3

    .line 665
    invoke-static {p1}, Landroid/view/View$MeasureSpec;->getMode(I)I

    move-result v0

    .line 666
    invoke-static {p2}, Landroid/view/View$MeasureSpec;->getMode(I)I

    move-result v1

    .line 668
    invoke-static {p1}, Landroid/view/View$MeasureSpec;->getSize(I)I

    move-result p1

    .line 669
    invoke-static {p2}, Landroid/view/View$MeasureSpec;->getSize(I)I

    move-result p2

    .line 671
    invoke-direct {p0, v0, p1}, Lorg/nameless/custom/colorpicker/ColorPickerView;->chooseWidth(II)I

    move-result p1

    .line 672
    invoke-direct {p0, v1, p2}, Lorg/nameless/custom/colorpicker/ColorPickerView;->chooseHeight(II)I

    move-result p2

    .line 674
    iget-boolean v0, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mShowAlphaPanel:Z

    if-nez v0, :cond_1

    int-to-float v0, p1

    .line 676
    iget v1, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->PANEL_SPACING:F

    sub-float/2addr v0, v1

    iget v2, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->HUE_PANEL_WIDTH:F

    sub-float/2addr v0, v2

    float-to-int v0, v0

    if-le v0, p2, :cond_0

    int-to-float p1, p2

    add-float/2addr p1, v1

    add-float/2addr p1, v2

    float-to-int p1, p1

    goto :goto_0

    :cond_0
    move p2, v0

    goto :goto_0

    :cond_1
    int-to-float v0, p2

    .line 689
    iget v1, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->ALPHA_PANEL_HEIGHT:F

    sub-float/2addr v0, v1

    iget v2, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->HUE_PANEL_WIDTH:F

    add-float/2addr v0, v2

    float-to-int v0, v0

    if-le v0, p1, :cond_2

    int-to-float p2, p1

    sub-float/2addr p2, v2

    add-float/2addr p2, v1

    float-to-int p2, p2

    goto :goto_0

    :cond_2
    move p1, v0

    .line 701
    :goto_0
    invoke-virtual {p0, p1, p2}, Landroid/view/View;->setMeasuredDimension(II)V

    return-void
.end method

.method protected onSizeChanged(IIII)V
    .locals 1

    .line 748
    invoke-super {p0, p1, p2, p3, p4}, Landroid/view/View;->onSizeChanged(IIII)V

    .line 750
    new-instance p3, Landroid/graphics/RectF;

    invoke-direct {p3}, Landroid/graphics/RectF;-><init>()V

    iput-object p3, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mDrawingRect:Landroid/graphics/RectF;

    .line 751
    iget p4, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mDrawingOffset:F

    invoke-virtual {p0}, Landroid/view/View;->getPaddingLeft()I

    move-result v0

    int-to-float v0, v0

    add-float/2addr p4, v0

    iput p4, p3, Landroid/graphics/RectF;->left:F

    .line 752
    iget-object p3, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mDrawingRect:Landroid/graphics/RectF;

    int-to-float p1, p1

    iget p4, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mDrawingOffset:F

    sub-float/2addr p1, p4

    invoke-virtual {p0}, Landroid/view/View;->getPaddingRight()I

    move-result p4

    int-to-float p4, p4

    sub-float/2addr p1, p4

    iput p1, p3, Landroid/graphics/RectF;->right:F

    .line 753
    iget-object p1, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mDrawingRect:Landroid/graphics/RectF;

    iget p3, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mDrawingOffset:F

    invoke-virtual {p0}, Landroid/view/View;->getPaddingTop()I

    move-result p4

    int-to-float p4, p4

    add-float/2addr p3, p4

    iput p3, p1, Landroid/graphics/RectF;->top:F

    .line 754
    iget-object p1, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mDrawingRect:Landroid/graphics/RectF;

    int-to-float p2, p2

    iget p3, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mDrawingOffset:F

    sub-float/2addr p2, p3

    invoke-virtual {p0}, Landroid/view/View;->getPaddingBottom()I

    move-result p3

    int-to-float p3, p3

    sub-float/2addr p2, p3

    iput p2, p1, Landroid/graphics/RectF;->bottom:F

    .line 756
    invoke-direct {p0}, Lorg/nameless/custom/colorpicker/ColorPickerView;->setUpSatValRect()V

    .line 757
    invoke-direct {p0}, Lorg/nameless/custom/colorpicker/ColorPickerView;->setUpHueRect()V

    .line 758
    invoke-direct {p0}, Lorg/nameless/custom/colorpicker/ColorPickerView;->setUpAlphaRect()V

    return-void
.end method

.method public onTouchEvent(Landroid/view/MotionEvent;)Z
    .locals 6

    .line 578
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getAction()I

    move-result v0

    const/4 v1, 0x2

    const/4 v2, 0x0

    const/4 v3, 0x1

    if-eqz v0, :cond_2

    if-eq v0, v3, :cond_1

    if-eq v0, v1, :cond_0

    move v0, v2

    goto :goto_0

    .line 590
    :cond_0
    invoke-direct {p0, p1}, Lorg/nameless/custom/colorpicker/ColorPickerView;->moveTrackersIfNeeded(Landroid/view/MotionEvent;)Z

    move-result v0

    goto :goto_0

    :cond_1
    const/4 v0, 0x0

    .line 596
    iput-object v0, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mStartTouchPoint:Landroid/graphics/Point;

    .line 598
    invoke-direct {p0, p1}, Lorg/nameless/custom/colorpicker/ColorPickerView;->moveTrackersIfNeeded(Landroid/view/MotionEvent;)Z

    move-result v0

    goto :goto_0

    .line 582
    :cond_2
    new-instance v0, Landroid/graphics/Point;

    invoke-virtual {p1}, Landroid/view/MotionEvent;->getX()F

    move-result v4

    float-to-int v4, v4

    invoke-virtual {p1}, Landroid/view/MotionEvent;->getY()F

    move-result v5

    float-to-int v5, v5

    invoke-direct {v0, v4, v5}, Landroid/graphics/Point;-><init>(II)V

    iput-object v0, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mStartTouchPoint:Landroid/graphics/Point;

    .line 584
    invoke-direct {p0, p1}, Lorg/nameless/custom/colorpicker/ColorPickerView;->moveTrackersIfNeeded(Landroid/view/MotionEvent;)Z

    move-result v0

    :goto_0
    if-eqz v0, :cond_4

    .line 606
    iget-object p1, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mListener:Lorg/nameless/custom/colorpicker/ColorPickerView$OnColorChangedListener;

    if-eqz p1, :cond_3

    .line 607
    iget v0, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mAlpha:I

    const/4 v4, 0x3

    new-array v4, v4, [F

    iget v5, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mHue:F

    aput v5, v4, v2

    iget v2, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mSat:F

    aput v2, v4, v3

    iget v2, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mVal:F

    aput v2, v4, v1

    invoke-static {v0, v4}, Landroid/graphics/Color;->HSVToColor(I[F)I

    move-result v0

    invoke-interface {p1, v0}, Lorg/nameless/custom/colorpicker/ColorPickerView$OnColorChangedListener;->onColorChanged(I)V

    .line 610
    :cond_3
    invoke-virtual {p0}, Landroid/view/View;->invalidate()V

    return v3

    .line 615
    :cond_4
    invoke-super {p0, p1}, Landroid/view/View;->onTouchEvent(Landroid/view/MotionEvent;)Z

    move-result p0

    return p0
.end method

.method public onTrackballEvent(Landroid/view/MotionEvent;)Z
    .locals 8

    .line 475
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getX()F

    move-result v0

    .line 476
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getY()F

    move-result v1

    .line 481
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getAction()I

    move-result v2

    const/4 v3, 0x2

    const/4 v4, 0x0

    const/4 v5, 0x1

    if-ne v2, v3, :cond_c

    .line 483
    iget v2, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mLastTouchedPanel:I

    const/4 v6, 0x0

    if-eqz v2, :cond_7

    const/high16 v7, 0x41200000    # 10.0f

    if-eq v2, v5, :cond_4

    if-eq v2, v3, :cond_0

    goto/16 :goto_5

    .line 532
    :cond_0
    iget-boolean v1, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mShowAlphaPanel:Z

    if-eqz v1, :cond_c

    iget-object v1, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mAlphaRect:Landroid/graphics/RectF;

    if-nez v1, :cond_1

    goto :goto_5

    .line 537
    :cond_1
    iget v1, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mAlpha:I

    int-to-float v1, v1

    mul-float/2addr v0, v7

    sub-float/2addr v1, v0

    float-to-int v0, v1

    const/16 v1, 0xff

    if-gez v0, :cond_2

    move v0, v4

    goto :goto_0

    :cond_2
    if-le v0, v1, :cond_3

    move v0, v1

    .line 546
    :cond_3
    :goto_0
    iput v0, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mAlpha:I

    goto :goto_2

    .line 515
    :cond_4
    iget v0, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mHue:F

    mul-float/2addr v1, v7

    sub-float/2addr v0, v1

    cmpg-float v1, v0, v6

    const/high16 v2, 0x43b40000    # 360.0f

    if-gez v1, :cond_5

    goto :goto_1

    :cond_5
    cmpl-float v1, v0, v2

    if-lez v1, :cond_6

    move v6, v2

    goto :goto_1

    :cond_6
    move v6, v0

    .line 524
    :goto_1
    iput v6, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mHue:F

    :goto_2
    move v0, v5

    goto :goto_6

    .line 489
    :cond_7
    iget v2, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mSat:F

    const/high16 v7, 0x42480000    # 50.0f

    div-float/2addr v0, v7

    add-float/2addr v2, v0

    .line 490
    iget v0, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mVal:F

    div-float/2addr v1, v7

    sub-float/2addr v0, v1

    cmpg-float v1, v2, v6

    const/high16 v7, 0x3f800000    # 1.0f

    if-gez v1, :cond_8

    move v2, v6

    goto :goto_3

    :cond_8
    cmpl-float v1, v2, v7

    if-lez v1, :cond_9

    move v2, v7

    :cond_9
    :goto_3
    cmpg-float v1, v0, v6

    if-gez v1, :cond_a

    goto :goto_4

    :cond_a
    cmpl-float v1, v0, v7

    if-lez v1, :cond_b

    move v6, v7

    goto :goto_4

    :cond_b
    move v6, v0

    .line 506
    :goto_4
    iput v2, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mSat:F

    .line 507
    iput v6, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mVal:F

    goto :goto_2

    :cond_c
    :goto_5
    move v0, v4

    :goto_6
    if-eqz v0, :cond_e

    .line 561
    iget-object p1, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mListener:Lorg/nameless/custom/colorpicker/ColorPickerView$OnColorChangedListener;

    if-eqz p1, :cond_d

    .line 562
    iget v0, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mAlpha:I

    const/4 v1, 0x3

    new-array v1, v1, [F

    iget v2, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mHue:F

    aput v2, v1, v4

    iget v2, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mSat:F

    aput v2, v1, v5

    iget v2, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mVal:F

    aput v2, v1, v3

    invoke-static {v0, v1}, Landroid/graphics/Color;->HSVToColor(I[F)I

    move-result v0

    invoke-interface {p1, v0}, Lorg/nameless/custom/colorpicker/ColorPickerView$OnColorChangedListener;->onColorChanged(I)V

    .line 565
    :cond_d
    invoke-virtual {p0}, Landroid/view/View;->invalidate()V

    return v5

    .line 570
    :cond_e
    invoke-super {p0, p1}, Landroid/view/View;->onTrackballEvent(Landroid/view/MotionEvent;)Z

    move-result p0

    return p0
.end method

.method public setAlphaSliderVisible(Z)V
    .locals 1

    .line 903
    iget-boolean v0, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mShowAlphaPanel:Z

    if-eq v0, p1, :cond_0

    .line 904
    iput-boolean p1, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mShowAlphaPanel:Z

    const/4 p1, 0x0

    .line 911
    iput-object p1, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mValShader:Landroid/graphics/Shader;

    .line 912
    iput-object p1, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mSatShader:Landroid/graphics/Shader;

    .line 913
    iput-object p1, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mHueShader:Landroid/graphics/Shader;

    .line 914
    iput-object p1, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mAlphaShader:Landroid/graphics/Shader;

    .line 916
    invoke-virtual {p0}, Landroid/view/View;->requestLayout()V

    :cond_0
    return-void
.end method

.method public setColor(IZ)V
    .locals 7

    .line 863
    invoke-static {p1}, Landroid/graphics/Color;->alpha(I)I

    move-result v0

    .line 864
    invoke-static {p1}, Landroid/graphics/Color;->red(I)I

    move-result v1

    .line 865
    invoke-static {p1}, Landroid/graphics/Color;->blue(I)I

    move-result v2

    .line 866
    invoke-static {p1}, Landroid/graphics/Color;->green(I)I

    move-result p1

    const/4 v3, 0x3

    new-array v4, v3, [F

    .line 870
    invoke-static {v1, p1, v2, v4}, Landroid/graphics/Color;->RGBToHSV(III[F)V

    .line 872
    iput v0, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mAlpha:I

    const/4 p1, 0x0

    aget v1, v4, p1

    .line 873
    iput v1, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mHue:F

    const/4 v2, 0x1

    aget v5, v4, v2

    .line 874
    iput v5, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mSat:F

    const/4 v6, 0x2

    aget v4, v4, v6

    .line 875
    iput v4, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mVal:F

    if-eqz p2, :cond_0

    .line 877
    iget-object p2, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mListener:Lorg/nameless/custom/colorpicker/ColorPickerView$OnColorChangedListener;

    if-eqz p2, :cond_0

    new-array v3, v3, [F

    aput v1, v3, p1

    aput v5, v3, v2

    aput v4, v3, v6

    .line 878
    invoke-static {v0, v3}, Landroid/graphics/Color;->HSVToColor(I[F)I

    move-result p1

    invoke-interface {p2, p1}, Lorg/nameless/custom/colorpicker/ColorPickerView$OnColorChangedListener;->onColorChanged(I)V

    .line 881
    :cond_0
    invoke-virtual {p0}, Landroid/view/View;->invalidate()V

    return-void
.end method

.method public setOnColorChangedListener(Lorg/nameless/custom/colorpicker/ColorPickerView$OnColorChangedListener;)V
    .locals 0

    .line 820
    iput-object p1, p0, Lorg/nameless/custom/colorpicker/ColorPickerView;->mListener:Lorg/nameless/custom/colorpicker/ColorPickerView$OnColorChangedListener;

    return-void
.end method
