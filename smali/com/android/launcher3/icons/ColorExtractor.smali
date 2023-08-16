.class public Lcom/android/launcher3/icons/ColorExtractor;
.super Ljava/lang/Object;
.source "ColorExtractor.java"


# instance fields
.field private final NUM_SAMPLES:I

.field private final mTmpHsv:[F

.field private final mTmpHueScoreHistogram:[F

.field private final mTmpPixels:[I

.field private final mTmpRgbScores:Landroid/util/SparseArray;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroid/util/SparseArray<",
            "Ljava/lang/Float;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method public constructor <init>()V
    .locals 2

    .line 29
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const/16 v0, 0x14

    .line 31
    iput v0, p0, Lcom/android/launcher3/icons/ColorExtractor;->NUM_SAMPLES:I

    const/4 v1, 0x3

    new-array v1, v1, [F

    .line 33
    iput-object v1, p0, Lcom/android/launcher3/icons/ColorExtractor;->mTmpHsv:[F

    const/16 v1, 0x168

    new-array v1, v1, [F

    .line 36
    iput-object v1, p0, Lcom/android/launcher3/icons/ColorExtractor;->mTmpHueScoreHistogram:[F

    new-array v0, v0, [I

    .line 39
    iput-object v0, p0, Lcom/android/launcher3/icons/ColorExtractor;->mTmpPixels:[I

    .line 42
    new-instance v0, Landroid/util/SparseArray;

    invoke-direct {v0}, Landroid/util/SparseArray;-><init>()V

    iput-object v0, p0, Lcom/android/launcher3/icons/ColorExtractor;->mTmpRgbScores:Landroid/util/SparseArray;

    return-void
.end method


# virtual methods
.method public findDominantColorByHue(Landroid/graphics/Bitmap;)I
    .locals 1

    const/16 v0, 0x14

    .line 50
    invoke-virtual {p0, p1, v0}, Lcom/android/launcher3/icons/ColorExtractor;->findDominantColorByHue(Landroid/graphics/Bitmap;I)I

    move-result p0

    return p0
.end method

.method protected findDominantColorByHue(Landroid/graphics/Bitmap;I)I
    .locals 19

    move-object/from16 v0, p0

    move/from16 v1, p2

    .line 58
    invoke-virtual/range {p1 .. p1}, Landroid/graphics/Bitmap;->getHeight()I

    move-result v2

    .line 59
    invoke-virtual/range {p1 .. p1}, Landroid/graphics/Bitmap;->getWidth()I

    move-result v3

    mul-int v4, v2, v3

    .line 60
    div-int/2addr v4, v1

    int-to-double v4, v4

    invoke-static {v4, v5}, Ljava/lang/Math;->sqrt(D)D

    move-result-wide v4

    double-to-int v4, v4

    const/4 v5, 0x1

    if-ge v4, v5, :cond_0

    move v4, v5

    .line 66
    :cond_0
    iget-object v6, v0, Lcom/android/launcher3/icons/ColorExtractor;->mTmpHsv:[F

    const/4 v7, 0x0

    .line 67
    invoke-static {v6, v7}, Ljava/util/Arrays;->fill([FF)V

    .line 71
    iget-object v8, v0, Lcom/android/launcher3/icons/ColorExtractor;->mTmpHueScoreHistogram:[F

    .line 72
    invoke-static {v8, v7}, Ljava/util/Arrays;->fill([FF)V

    const/4 v7, -0x1

    .line 76
    iget-object v9, v0, Lcom/android/launcher3/icons/ColorExtractor;->mTmpPixels:[I

    array-length v10, v9

    if-gt v10, v1, :cond_1

    goto :goto_0

    :cond_1
    new-array v9, v1, [I

    :goto_0
    const/4 v10, 0x0

    .line 77
    invoke-static {v9, v10}, Ljava/util/Arrays;->fill([II)V

    move v12, v10

    move v13, v12

    const/high16 v14, -0x40800000    # -1.0f

    :goto_1
    const/high16 v16, -0x1000000

    if-ge v12, v2, :cond_7

    move v11, v10

    :goto_2
    if-ge v11, v3, :cond_6

    move-object/from16 v15, p1

    .line 82
    invoke-virtual {v15, v11, v12}, Landroid/graphics/Bitmap;->getPixel(II)I

    move-result v17

    shr-int/lit8 v5, v17, 0x18

    and-int/lit16 v5, v5, 0xff

    const/16 v10, 0x80

    if-ge v5, v10, :cond_2

    move/from16 v17, v2

    goto :goto_3

    :cond_2
    or-int v5, v17, v16

    .line 90
    invoke-static {v5, v6}, Landroid/graphics/Color;->colorToHSV(I[F)V

    move/from16 v17, v2

    const/4 v10, 0x0

    .line 92
    aget v2, v6, v10

    float-to-int v2, v2

    if-ltz v2, :cond_5

    .line 93
    array-length v10, v8

    if-lt v2, v10, :cond_3

    goto :goto_3

    :cond_3
    if-ge v13, v1, :cond_4

    add-int/lit8 v10, v13, 0x1

    .line 98
    aput v5, v9, v13

    move v13, v10

    :cond_4
    const/4 v5, 0x1

    .line 100
    aget v10, v6, v5

    const/4 v5, 0x2

    aget v18, v6, v5

    mul-float v10, v10, v18

    .line 101
    aget v5, v8, v2

    add-float/2addr v5, v10

    aput v5, v8, v2

    cmpl-float v10, v5, v14

    if-lez v10, :cond_5

    move v7, v2

    move v14, v5

    :cond_5
    :goto_3
    add-int/2addr v11, v4

    move/from16 v2, v17

    const/4 v5, 0x1

    const/4 v10, 0x0

    goto :goto_2

    :cond_6
    move-object/from16 v15, p1

    move/from16 v17, v2

    add-int/2addr v12, v4

    const/4 v5, 0x1

    const/4 v10, 0x0

    goto :goto_1

    .line 109
    :cond_7
    iget-object v0, v0, Lcom/android/launcher3/icons/ColorExtractor;->mTmpRgbScores:Landroid/util/SparseArray;

    .line 110
    invoke-virtual {v0}, Landroid/util/SparseArray;->clear()V

    const/4 v10, 0x0

    const/high16 v11, -0x40800000    # -1.0f

    :goto_4
    if-ge v10, v13, :cond_b

    .line 117
    aget v1, v9, v10

    .line 118
    invoke-static {v1, v6}, Landroid/graphics/Color;->colorToHSV(I[F)V

    const/4 v2, 0x0

    .line 119
    aget v3, v6, v2

    float-to-int v3, v3

    if-ne v3, v7, :cond_9

    const/4 v3, 0x1

    .line 121
    aget v4, v6, v3

    const/4 v5, 0x2

    .line 122
    aget v8, v6, v5

    const/high16 v12, 0x42c80000    # 100.0f

    mul-float/2addr v12, v4

    float-to-int v12, v12

    const v14, 0x461c4000    # 10000.0f

    mul-float/2addr v14, v8

    float-to-int v14, v14

    add-int/2addr v12, v14

    mul-float/2addr v4, v8

    .line 126
    invoke-virtual {v0, v12}, Landroid/util/SparseArray;->get(I)Ljava/lang/Object;

    move-result-object v8

    check-cast v8, Ljava/lang/Float;

    if-nez v8, :cond_8

    goto :goto_5

    .line 127
    :cond_8
    invoke-virtual {v8}, Ljava/lang/Float;->floatValue()F

    move-result v8

    add-float/2addr v4, v8

    .line 128
    :goto_5
    invoke-static {v4}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object v8

    invoke-virtual {v0, v12, v8}, Landroid/util/SparseArray;->put(ILjava/lang/Object;)V

    cmpl-float v8, v4, v11

    if-lez v8, :cond_a

    move/from16 v16, v1

    move v11, v4

    goto :goto_6

    :cond_9
    const/4 v3, 0x1

    const/4 v5, 0x2

    :cond_a
    :goto_6
    add-int/lit8 v10, v10, 0x1

    goto :goto_4

    :cond_b
    return v16
.end method
