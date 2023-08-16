.class public Lorg/nameless/custom/colorpicker/ColorPickerPanelView;
.super Landroid/view/View;
.source "ColorPickerPanelView.java"


# static fields
.field private static mDensity:F = 1.0f


# instance fields
.field private mAlphaPattern:Lorg/nameless/custom/colorpicker/AlphaPatternDrawable;

.field private mBorderColor:I

.field private mBorderPaint:Landroid/graphics/Paint;

.field private mColor:I

.field private mColorPaint:Landroid/graphics/Paint;

.field private mColorRect:Landroid/graphics/RectF;

.field private mDrawingRect:Landroid/graphics/RectF;


# direct methods
.method static constructor <clinit>()V
    .locals 0

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;)V
    .locals 1

    const/4 v0, 0x0

    .line 57
    invoke-direct {p0, p1, v0}, Lorg/nameless/custom/colorpicker/ColorPickerPanelView;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 1

    const/4 v0, 0x0

    .line 61
    invoke-direct {p0, p1, p2, v0}, Lorg/nameless/custom/colorpicker/ColorPickerPanelView;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V
    .locals 0

    .line 65
    invoke-direct {p0, p1, p2, p3}, Landroid/view/View;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    const p1, -0x919192

    .line 44
    iput p1, p0, Lorg/nameless/custom/colorpicker/ColorPickerPanelView;->mBorderColor:I

    const/high16 p1, -0x1000000

    .line 45
    iput p1, p0, Lorg/nameless/custom/colorpicker/ColorPickerPanelView;->mColor:I

    .line 66
    invoke-direct {p0}, Lorg/nameless/custom/colorpicker/ColorPickerPanelView;->init()V

    return-void
.end method

.method private init()V
    .locals 1

    .line 70
    new-instance v0, Landroid/graphics/Paint;

    invoke-direct {v0}, Landroid/graphics/Paint;-><init>()V

    iput-object v0, p0, Lorg/nameless/custom/colorpicker/ColorPickerPanelView;->mBorderPaint:Landroid/graphics/Paint;

    .line 71
    new-instance v0, Landroid/graphics/Paint;

    invoke-direct {v0}, Landroid/graphics/Paint;-><init>()V

    iput-object v0, p0, Lorg/nameless/custom/colorpicker/ColorPickerPanelView;->mColorPaint:Landroid/graphics/Paint;

    .line 72
    invoke-virtual {p0}, Landroid/view/View;->getContext()Landroid/content/Context;

    move-result-object p0

    invoke-virtual {p0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object p0

    invoke-virtual {p0}, Landroid/content/res/Resources;->getDisplayMetrics()Landroid/util/DisplayMetrics;

    move-result-object p0

    iget p0, p0, Landroid/util/DisplayMetrics;->density:F

    sput p0, Lorg/nameless/custom/colorpicker/ColorPickerPanelView;->mDensity:F

    return-void
.end method

.method private setUpColorRect()V
    .locals 5

    .line 119
    iget-object v0, p0, Lorg/nameless/custom/colorpicker/ColorPickerPanelView;->mDrawingRect:Landroid/graphics/RectF;

    .line 121
    iget v1, v0, Landroid/graphics/RectF;->left:F

    const/high16 v2, 0x3f800000    # 1.0f

    add-float/2addr v1, v2

    .line 122
    iget v3, v0, Landroid/graphics/RectF;->top:F

    add-float/2addr v3, v2

    .line 123
    iget v4, v0, Landroid/graphics/RectF;->bottom:F

    sub-float/2addr v4, v2

    .line 124
    iget v0, v0, Landroid/graphics/RectF;->right:F

    sub-float/2addr v0, v2

    .line 126
    new-instance v2, Landroid/graphics/RectF;

    invoke-direct {v2, v1, v3, v0, v4}, Landroid/graphics/RectF;-><init>(FFFF)V

    iput-object v2, p0, Lorg/nameless/custom/colorpicker/ColorPickerPanelView;->mColorRect:Landroid/graphics/RectF;

    .line 128
    new-instance v0, Lorg/nameless/custom/colorpicker/AlphaPatternDrawable;

    sget v1, Lorg/nameless/custom/colorpicker/ColorPickerPanelView;->mDensity:F

    const/high16 v2, 0x40a00000    # 5.0f

    mul-float/2addr v1, v2

    float-to-int v1, v1

    invoke-direct {v0, v1}, Lorg/nameless/custom/colorpicker/AlphaPatternDrawable;-><init>(I)V

    iput-object v0, p0, Lorg/nameless/custom/colorpicker/ColorPickerPanelView;->mAlphaPattern:Lorg/nameless/custom/colorpicker/AlphaPatternDrawable;

    .line 130
    iget-object v1, p0, Lorg/nameless/custom/colorpicker/ColorPickerPanelView;->mColorRect:Landroid/graphics/RectF;

    iget v1, v1, Landroid/graphics/RectF;->left:F

    .line 131
    invoke-static {v1}, Ljava/lang/Math;->round(F)I

    move-result v1

    iget-object v2, p0, Lorg/nameless/custom/colorpicker/ColorPickerPanelView;->mColorRect:Landroid/graphics/RectF;

    iget v2, v2, Landroid/graphics/RectF;->top:F

    .line 132
    invoke-static {v2}, Ljava/lang/Math;->round(F)I

    move-result v2

    iget-object v3, p0, Lorg/nameless/custom/colorpicker/ColorPickerPanelView;->mColorRect:Landroid/graphics/RectF;

    iget v3, v3, Landroid/graphics/RectF;->right:F

    .line 133
    invoke-static {v3}, Ljava/lang/Math;->round(F)I

    move-result v3

    iget-object p0, p0, Lorg/nameless/custom/colorpicker/ColorPickerPanelView;->mColorRect:Landroid/graphics/RectF;

    iget p0, p0, Landroid/graphics/RectF;->bottom:F

    .line 134
    invoke-static {p0}, Ljava/lang/Math;->round(F)I

    move-result p0

    .line 130
    invoke-virtual {v0, v1, v2, v3, p0}, Landroid/graphics/drawable/Drawable;->setBounds(IIII)V

    return-void
.end method


# virtual methods
.method public getColor()I
    .locals 0

    .line 153
    iget p0, p0, Lorg/nameless/custom/colorpicker/ColorPickerPanelView;->mColor:I

    return p0
.end method

.method protected onDraw(Landroid/graphics/Canvas;)V
    .locals 3

    .line 79
    iget-object v0, p0, Lorg/nameless/custom/colorpicker/ColorPickerPanelView;->mColorRect:Landroid/graphics/RectF;

    .line 82
    iget-object v1, p0, Lorg/nameless/custom/colorpicker/ColorPickerPanelView;->mBorderPaint:Landroid/graphics/Paint;

    iget v2, p0, Lorg/nameless/custom/colorpicker/ColorPickerPanelView;->mBorderColor:I

    invoke-virtual {v1, v2}, Landroid/graphics/Paint;->setColor(I)V

    .line 83
    iget-object v1, p0, Lorg/nameless/custom/colorpicker/ColorPickerPanelView;->mDrawingRect:Landroid/graphics/RectF;

    iget-object v2, p0, Lorg/nameless/custom/colorpicker/ColorPickerPanelView;->mBorderPaint:Landroid/graphics/Paint;

    invoke-virtual {p1, v1, v2}, Landroid/graphics/Canvas;->drawRect(Landroid/graphics/RectF;Landroid/graphics/Paint;)V

    .line 86
    iget-object v1, p0, Lorg/nameless/custom/colorpicker/ColorPickerPanelView;->mAlphaPattern:Lorg/nameless/custom/colorpicker/AlphaPatternDrawable;

    if-eqz v1, :cond_0

    .line 87
    invoke-virtual {v1, p1}, Lorg/nameless/custom/colorpicker/AlphaPatternDrawable;->draw(Landroid/graphics/Canvas;)V

    .line 90
    :cond_0
    iget-object v1, p0, Lorg/nameless/custom/colorpicker/ColorPickerPanelView;->mColorPaint:Landroid/graphics/Paint;

    iget v2, p0, Lorg/nameless/custom/colorpicker/ColorPickerPanelView;->mColor:I

    invoke-virtual {v1, v2}, Landroid/graphics/Paint;->setColor(I)V

    .line 92
    iget-object p0, p0, Lorg/nameless/custom/colorpicker/ColorPickerPanelView;->mColorPaint:Landroid/graphics/Paint;

    invoke-virtual {p1, v0, p0}, Landroid/graphics/Canvas;->drawRect(Landroid/graphics/RectF;Landroid/graphics/Paint;)V

    return-void
.end method

.method protected onMeasure(II)V
    .locals 0

    .line 98
    invoke-static {p1}, Landroid/view/View$MeasureSpec;->getSize(I)I

    move-result p1

    .line 99
    invoke-static {p2}, Landroid/view/View$MeasureSpec;->getSize(I)I

    move-result p2

    .line 101
    invoke-virtual {p0, p1, p2}, Landroid/view/View;->setMeasuredDimension(II)V

    return-void
.end method

.method protected onSizeChanged(IIII)V
    .locals 0

    .line 106
    invoke-super {p0, p1, p2, p3, p4}, Landroid/view/View;->onSizeChanged(IIII)V

    .line 108
    new-instance p3, Landroid/graphics/RectF;

    invoke-direct {p3}, Landroid/graphics/RectF;-><init>()V

    iput-object p3, p0, Lorg/nameless/custom/colorpicker/ColorPickerPanelView;->mDrawingRect:Landroid/graphics/RectF;

    .line 109
    invoke-virtual {p0}, Landroid/view/View;->getPaddingLeft()I

    move-result p4

    int-to-float p4, p4

    iput p4, p3, Landroid/graphics/RectF;->left:F

    .line 110
    iget-object p3, p0, Lorg/nameless/custom/colorpicker/ColorPickerPanelView;->mDrawingRect:Landroid/graphics/RectF;

    invoke-virtual {p0}, Landroid/view/View;->getPaddingRight()I

    move-result p4

    sub-int/2addr p1, p4

    int-to-float p1, p1

    iput p1, p3, Landroid/graphics/RectF;->right:F

    .line 111
    iget-object p1, p0, Lorg/nameless/custom/colorpicker/ColorPickerPanelView;->mDrawingRect:Landroid/graphics/RectF;

    invoke-virtual {p0}, Landroid/view/View;->getPaddingTop()I

    move-result p3

    int-to-float p3, p3

    iput p3, p1, Landroid/graphics/RectF;->top:F

    .line 112
    iget-object p1, p0, Lorg/nameless/custom/colorpicker/ColorPickerPanelView;->mDrawingRect:Landroid/graphics/RectF;

    invoke-virtual {p0}, Landroid/view/View;->getPaddingBottom()I

    move-result p3

    sub-int/2addr p2, p3

    int-to-float p2, p2

    iput p2, p1, Landroid/graphics/RectF;->bottom:F

    .line 114
    invoke-direct {p0}, Lorg/nameless/custom/colorpicker/ColorPickerPanelView;->setUpColorRect()V

    return-void
.end method

.method public setColor(I)V
    .locals 0

    .line 144
    iput p1, p0, Lorg/nameless/custom/colorpicker/ColorPickerPanelView;->mColor:I

    .line 145
    invoke-virtual {p0}, Landroid/view/View;->invalidate()V

    return-void
.end method
