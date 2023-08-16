.class public Lorg/nameless/custom/colorpicker/AlphaPatternDrawable;
.super Landroid/graphics/drawable/Drawable;
.source "AlphaPatternDrawable.java"


# instance fields
.field private mBitmap:Landroid/graphics/Bitmap;

.field private mPaint:Landroid/graphics/Paint;

.field private mPaintGray:Landroid/graphics/Paint;

.field private mPaintWhite:Landroid/graphics/Paint;

.field private mRectangleSize:I

.field private numRectanglesHorizontal:I

.field private numRectanglesVertical:I


# direct methods
.method public constructor <init>(I)V
    .locals 1

    .line 49
    invoke-direct {p0}, Landroid/graphics/drawable/Drawable;-><init>()V

    const/16 v0, 0xa

    .line 35
    iput v0, p0, Lorg/nameless/custom/colorpicker/AlphaPatternDrawable;->mRectangleSize:I

    .line 37
    new-instance v0, Landroid/graphics/Paint;

    invoke-direct {v0}, Landroid/graphics/Paint;-><init>()V

    iput-object v0, p0, Lorg/nameless/custom/colorpicker/AlphaPatternDrawable;->mPaint:Landroid/graphics/Paint;

    .line 38
    new-instance v0, Landroid/graphics/Paint;

    invoke-direct {v0}, Landroid/graphics/Paint;-><init>()V

    iput-object v0, p0, Lorg/nameless/custom/colorpicker/AlphaPatternDrawable;->mPaintWhite:Landroid/graphics/Paint;

    .line 39
    new-instance v0, Landroid/graphics/Paint;

    invoke-direct {v0}, Landroid/graphics/Paint;-><init>()V

    iput-object v0, p0, Lorg/nameless/custom/colorpicker/AlphaPatternDrawable;->mPaintGray:Landroid/graphics/Paint;

    .line 50
    iput p1, p0, Lorg/nameless/custom/colorpicker/AlphaPatternDrawable;->mRectangleSize:I

    .line 51
    iget-object p1, p0, Lorg/nameless/custom/colorpicker/AlphaPatternDrawable;->mPaintWhite:Landroid/graphics/Paint;

    const/4 v0, -0x1

    invoke-virtual {p1, v0}, Landroid/graphics/Paint;->setColor(I)V

    .line 52
    iget-object p0, p0, Lorg/nameless/custom/colorpicker/AlphaPatternDrawable;->mPaintGray:Landroid/graphics/Paint;

    const p1, -0x343435

    invoke-virtual {p0, p1}, Landroid/graphics/Paint;->setColor(I)V

    return-void
.end method

.method private generatePatternBitmap()V
    .locals 10

    .line 100
    invoke-virtual {p0}, Landroid/graphics/drawable/Drawable;->getBounds()Landroid/graphics/Rect;

    move-result-object v0

    invoke-virtual {v0}, Landroid/graphics/Rect;->width()I

    move-result v0

    if-lez v0, :cond_3

    invoke-virtual {p0}, Landroid/graphics/drawable/Drawable;->getBounds()Landroid/graphics/Rect;

    move-result-object v0

    invoke-virtual {v0}, Landroid/graphics/Rect;->height()I

    move-result v0

    if-gtz v0, :cond_0

    goto :goto_3

    .line 104
    :cond_0
    invoke-virtual {p0}, Landroid/graphics/drawable/Drawable;->getBounds()Landroid/graphics/Rect;

    move-result-object v0

    invoke-virtual {v0}, Landroid/graphics/Rect;->width()I

    move-result v0

    invoke-virtual {p0}, Landroid/graphics/drawable/Drawable;->getBounds()Landroid/graphics/Rect;

    move-result-object v1

    invoke-virtual {v1}, Landroid/graphics/Rect;->height()I

    move-result v1

    sget-object v2, Landroid/graphics/Bitmap$Config;->ARGB_8888:Landroid/graphics/Bitmap$Config;

    invoke-static {v0, v1, v2}, Landroid/graphics/Bitmap;->createBitmap(IILandroid/graphics/Bitmap$Config;)Landroid/graphics/Bitmap;

    move-result-object v0

    iput-object v0, p0, Lorg/nameless/custom/colorpicker/AlphaPatternDrawable;->mBitmap:Landroid/graphics/Bitmap;

    .line 105
    new-instance v0, Landroid/graphics/Canvas;

    iget-object v1, p0, Lorg/nameless/custom/colorpicker/AlphaPatternDrawable;->mBitmap:Landroid/graphics/Bitmap;

    invoke-direct {v0, v1}, Landroid/graphics/Canvas;-><init>(Landroid/graphics/Bitmap;)V

    .line 107
    new-instance v1, Landroid/graphics/Rect;

    invoke-direct {v1}, Landroid/graphics/Rect;-><init>()V

    const/4 v2, 0x0

    const/4 v3, 0x1

    move v4, v2

    .line 109
    :goto_0
    iget v5, p0, Lorg/nameless/custom/colorpicker/AlphaPatternDrawable;->numRectanglesVertical:I

    if-gt v4, v5, :cond_3

    move v5, v2

    move v6, v3

    .line 112
    :goto_1
    iget v7, p0, Lorg/nameless/custom/colorpicker/AlphaPatternDrawable;->numRectanglesHorizontal:I

    if-gt v5, v7, :cond_2

    .line 114
    iget v7, p0, Lorg/nameless/custom/colorpicker/AlphaPatternDrawable;->mRectangleSize:I

    mul-int v8, v4, v7

    iput v8, v1, Landroid/graphics/Rect;->top:I

    mul-int v9, v5, v7

    .line 115
    iput v9, v1, Landroid/graphics/Rect;->left:I

    add-int/2addr v8, v7

    .line 116
    iput v8, v1, Landroid/graphics/Rect;->bottom:I

    add-int/2addr v9, v7

    .line 117
    iput v9, v1, Landroid/graphics/Rect;->right:I

    if-eqz v6, :cond_1

    .line 119
    iget-object v7, p0, Lorg/nameless/custom/colorpicker/AlphaPatternDrawable;->mPaintWhite:Landroid/graphics/Paint;

    goto :goto_2

    :cond_1
    iget-object v7, p0, Lorg/nameless/custom/colorpicker/AlphaPatternDrawable;->mPaintGray:Landroid/graphics/Paint;

    :goto_2
    invoke-virtual {v0, v1, v7}, Landroid/graphics/Canvas;->drawRect(Landroid/graphics/Rect;Landroid/graphics/Paint;)V

    xor-int/lit8 v6, v6, 0x1

    add-int/lit8 v5, v5, 0x1

    goto :goto_1

    :cond_2
    xor-int/lit8 v3, v3, 0x1

    add-int/lit8 v4, v4, 0x1

    goto :goto_0

    :cond_3
    :goto_3
    return-void
.end method


# virtual methods
.method public draw(Landroid/graphics/Canvas;)V
    .locals 3

    .line 57
    iget-object v0, p0, Lorg/nameless/custom/colorpicker/AlphaPatternDrawable;->mBitmap:Landroid/graphics/Bitmap;

    if-eqz v0, :cond_0

    const/4 v1, 0x0

    .line 58
    invoke-virtual {p0}, Landroid/graphics/drawable/Drawable;->getBounds()Landroid/graphics/Rect;

    move-result-object v2

    iget-object p0, p0, Lorg/nameless/custom/colorpicker/AlphaPatternDrawable;->mPaint:Landroid/graphics/Paint;

    invoke-virtual {p1, v0, v1, v2, p0}, Landroid/graphics/Canvas;->drawBitmap(Landroid/graphics/Bitmap;Landroid/graphics/Rect;Landroid/graphics/Rect;Landroid/graphics/Paint;)V

    :cond_0
    return-void
.end method

.method public getOpacity()I
    .locals 0

    const/4 p0, 0x0

    return p0
.end method

.method protected onBoundsChange(Landroid/graphics/Rect;)V
    .locals 3

    .line 79
    invoke-super {p0, p1}, Landroid/graphics/drawable/Drawable;->onBoundsChange(Landroid/graphics/Rect;)V

    .line 81
    invoke-virtual {p1}, Landroid/graphics/Rect;->height()I

    move-result v0

    .line 82
    invoke-virtual {p1}, Landroid/graphics/Rect;->width()I

    move-result p1

    .line 84
    iget v1, p0, Lorg/nameless/custom/colorpicker/AlphaPatternDrawable;->mRectangleSize:I

    div-int/2addr p1, v1

    int-to-double v1, p1

    invoke-static {v1, v2}, Ljava/lang/Math;->ceil(D)D

    move-result-wide v1

    double-to-int p1, v1

    iput p1, p0, Lorg/nameless/custom/colorpicker/AlphaPatternDrawable;->numRectanglesHorizontal:I

    .line 85
    iget p1, p0, Lorg/nameless/custom/colorpicker/AlphaPatternDrawable;->mRectangleSize:I

    div-int/2addr v0, p1

    int-to-double v0, v0

    invoke-static {v0, v1}, Ljava/lang/Math;->ceil(D)D

    move-result-wide v0

    double-to-int p1, v0

    iput p1, p0, Lorg/nameless/custom/colorpicker/AlphaPatternDrawable;->numRectanglesVertical:I

    .line 87
    invoke-direct {p0}, Lorg/nameless/custom/colorpicker/AlphaPatternDrawable;->generatePatternBitmap()V

    return-void
.end method

.method public setAlpha(I)V
    .locals 0

    .line 69
    new-instance p0, Ljava/lang/UnsupportedOperationException;

    const-string p1, "Alpha is not supported by this drawwable."

    invoke-direct {p0, p1}, Ljava/lang/UnsupportedOperationException;-><init>(Ljava/lang/String;)V

    throw p0
.end method

.method public setColorFilter(Landroid/graphics/ColorFilter;)V
    .locals 0

    .line 74
    new-instance p0, Ljava/lang/UnsupportedOperationException;

    const-string p1, "ColorFilter is not supported by this drawwable."

    invoke-direct {p0, p1}, Ljava/lang/UnsupportedOperationException;-><init>(Ljava/lang/String;)V

    throw p0
.end method
