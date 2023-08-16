.class public Lcom/android/launcher3/icons/BaseIconFactory;
.super Ljava/lang/Object;
.source "BaseIconFactory.java"

# interfaces
.implements Ljava/lang/AutoCloseable;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/android/launcher3/icons/BaseIconFactory$ClippedMonoDrawable;,
        Lcom/android/launcher3/icons/BaseIconFactory$NoopDrawable;,
        Lcom/android/launcher3/icons/BaseIconFactory$IconOptions;
    }
.end annotation


# static fields
.field private static PLACEHOLDER_BACKGROUND_COLOR:I


# instance fields
.field private final mCanvas:Landroid/graphics/Canvas;

.field private final mColorExtractor:Lcom/android/launcher3/icons/ColorExtractor;

.field protected final mContext:Landroid/content/Context;

.field protected final mFillResIconDpi:I

.field protected final mIconBitmapSize:I

.field private final mIsUserBadged:Landroid/util/SparseBooleanArray;

.field protected mMonoIconEnabled:Z

.field private mNormalizer:Lcom/android/launcher3/icons/IconNormalizer;

.field private final mOldBounds:Landroid/graphics/Rect;

.field private final mPm:Landroid/content/pm/PackageManager;

.field private mShadowGenerator:Lcom/android/launcher3/icons/ShadowGenerator;

.field private final mShapeDetection:Z

.field private mWhiteShadowLayer:Landroid/graphics/Bitmap;

.field private mWrapperBackgroundColor:I

.field private mWrapperIcon:Landroid/graphics/drawable/Drawable;


# direct methods
.method public static synthetic $r8$lambda$TecH-7wvmZxjLpVNWshedXWce_w(Lcom/android/launcher3/icons/BaseIconFactory;Landroid/graphics/drawable/Drawable;FILandroid/graphics/Canvas;)V
    .locals 0

    invoke-direct {p0, p1, p2, p3, p4}, Lcom/android/launcher3/icons/BaseIconFactory;->lambda$createIconBitmap$0(Landroid/graphics/drawable/Drawable;FILandroid/graphics/Canvas;)V

    return-void
.end method

.method static constructor <clinit>()V
    .locals 1

    const/16 v0, 0xf5

    .line 107
    invoke-static {v0, v0, v0}, Landroid/graphics/Color;->rgb(III)I

    move-result v0

    sput v0, Lcom/android/launcher3/icons/BaseIconFactory;->PLACEHOLDER_BACKGROUND_COLOR:I

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;II)V
    .locals 1

    const/4 v0, 0x0

    .line 125
    invoke-direct {p0, p1, p2, p3, v0}, Lcom/android/launcher3/icons/BaseIconFactory;-><init>(Landroid/content/Context;IIZ)V

    return-void
.end method

.method protected constructor <init>(Landroid/content/Context;IIZ)V
    .locals 1

    .line 110
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 70
    new-instance v0, Landroid/graphics/Rect;

    invoke-direct {v0}, Landroid/graphics/Rect;-><init>()V

    iput-object v0, p0, Lcom/android/launcher3/icons/BaseIconFactory;->mOldBounds:Landroid/graphics/Rect;

    .line 73
    new-instance v0, Landroid/util/SparseBooleanArray;

    invoke-direct {v0}, Landroid/util/SparseBooleanArray;-><init>()V

    iput-object v0, p0, Lcom/android/launcher3/icons/BaseIconFactory;->mIsUserBadged:Landroid/util/SparseBooleanArray;

    const/4 v0, -0x1

    .line 105
    iput v0, p0, Lcom/android/launcher3/icons/BaseIconFactory;->mWrapperBackgroundColor:I

    .line 111
    invoke-virtual {p1}, Landroid/content/Context;->getApplicationContext()Landroid/content/Context;

    move-result-object p1

    iput-object p1, p0, Lcom/android/launcher3/icons/BaseIconFactory;->mContext:Landroid/content/Context;

    .line 112
    iput-boolean p4, p0, Lcom/android/launcher3/icons/BaseIconFactory;->mShapeDetection:Z

    .line 113
    iput p2, p0, Lcom/android/launcher3/icons/BaseIconFactory;->mFillResIconDpi:I

    .line 114
    iput p3, p0, Lcom/android/launcher3/icons/BaseIconFactory;->mIconBitmapSize:I

    .line 116
    invoke-virtual {p1}, Landroid/content/Context;->getPackageManager()Landroid/content/pm/PackageManager;

    move-result-object p1

    iput-object p1, p0, Lcom/android/launcher3/icons/BaseIconFactory;->mPm:Landroid/content/pm/PackageManager;

    .line 117
    new-instance p1, Lcom/android/launcher3/icons/ColorExtractor;

    invoke-direct {p1}, Lcom/android/launcher3/icons/ColorExtractor;-><init>()V

    iput-object p1, p0, Lcom/android/launcher3/icons/BaseIconFactory;->mColorExtractor:Lcom/android/launcher3/icons/ColorExtractor;

    .line 119
    new-instance p1, Landroid/graphics/Canvas;

    invoke-direct {p1}, Landroid/graphics/Canvas;-><init>()V

    iput-object p1, p0, Lcom/android/launcher3/icons/BaseIconFactory;->mCanvas:Landroid/graphics/Canvas;

    .line 120
    new-instance p2, Landroid/graphics/PaintFlagsDrawFilter;

    const/4 p3, 0x4

    const/4 p4, 0x2

    invoke-direct {p2, p3, p4}, Landroid/graphics/PaintFlagsDrawFilter;-><init>(II)V

    invoke-virtual {p1, p2}, Landroid/graphics/Canvas;->setDrawFilter(Landroid/graphics/DrawFilter;)V

    .line 121
    invoke-virtual {p0}, Lcom/android/launcher3/icons/BaseIconFactory;->clear()V

    return-void
.end method

.method private drawIconBitmap(Landroid/graphics/Canvas;Landroid/graphics/drawable/Drawable;FILandroid/graphics/Bitmap;)V
    .locals 6

    .line 381
    iget v0, p0, Lcom/android/launcher3/icons/BaseIconFactory;->mIconBitmapSize:I

    .line 382
    iget-object v1, p0, Lcom/android/launcher3/icons/BaseIconFactory;->mOldBounds:Landroid/graphics/Rect;

    invoke-virtual {p2}, Landroid/graphics/drawable/Drawable;->getBounds()Landroid/graphics/Rect;

    move-result-object v2

    invoke-virtual {v1, v2}, Landroid/graphics/Rect;->set(Landroid/graphics/Rect;)V

    .line 384
    instance-of v1, p2, Landroid/graphics/drawable/AdaptiveIconDrawable;

    const/4 v2, 0x2

    if-eqz v1, :cond_3

    const p5, 0x3d0f5c29    # 0.035f

    int-to-float v1, v0

    mul-float/2addr p5, v1

    float-to-double v3, p5

    .line 385
    invoke-static {v3, v4}, Ljava/lang/Math;->ceil(D)D

    move-result-wide v3

    double-to-int p5, v3

    const/high16 v3, 0x3f800000    # 1.0f

    sub-float/2addr v3, p3

    mul-float/2addr v1, v3

    const/high16 p3, 0x40000000    # 2.0f

    div-float/2addr v1, p3

    .line 386
    invoke-static {v1}, Ljava/lang/Math;->round(F)I

    move-result p3

    .line 385
    invoke-static {p5, p3}, Ljava/lang/Math;->max(II)I

    move-result p3

    sub-int/2addr v0, p3

    sub-int/2addr v0, p3

    const/4 p5, 0x0

    .line 388
    invoke-virtual {p2, p5, p5, v0, v0}, Landroid/graphics/drawable/Drawable;->setBounds(IIII)V

    .line 389
    invoke-virtual {p1}, Landroid/graphics/Canvas;->save()I

    move-result p5

    int-to-float p3, p3

    .line 390
    invoke-virtual {p1, p3, p3}, Landroid/graphics/Canvas;->translate(FF)V

    if-eq p4, v2, :cond_0

    const/4 p3, 0x4

    if-ne p4, p3, :cond_1

    .line 393
    :cond_0
    invoke-virtual {p0}, Lcom/android/launcher3/icons/BaseIconFactory;->getShadowGenerator()Lcom/android/launcher3/icons/ShadowGenerator;

    move-result-object p3

    move-object p4, p2

    check-cast p4, Landroid/graphics/drawable/AdaptiveIconDrawable;

    .line 394
    invoke-virtual {p4}, Landroid/graphics/drawable/AdaptiveIconDrawable;->getIconMask()Landroid/graphics/Path;

    move-result-object p4

    .line 393
    invoke-virtual {p3, p4, p1}, Lcom/android/launcher3/icons/ShadowGenerator;->addPathShadow(Landroid/graphics/Path;Landroid/graphics/Canvas;)V

    .line 397
    :cond_1
    instance-of p3, p2, Lcom/android/launcher3/icons/BitmapInfo$Extender;

    if-eqz p3, :cond_2

    .line 398
    move-object p3, p2

    check-cast p3, Lcom/android/launcher3/icons/BitmapInfo$Extender;

    invoke-interface {p3, p1}, Lcom/android/launcher3/icons/BitmapInfo$Extender;->drawForPersistence(Landroid/graphics/Canvas;)V

    goto :goto_0

    .line 400
    :cond_2
    invoke-virtual {p2, p1}, Landroid/graphics/drawable/Drawable;->draw(Landroid/graphics/Canvas;)V

    .line 402
    :goto_0
    invoke-virtual {p1, p5}, Landroid/graphics/Canvas;->restoreToCount(I)V

    goto/16 :goto_2

    .line 404
    :cond_3
    instance-of v1, p2, Landroid/graphics/drawable/BitmapDrawable;

    if-eqz v1, :cond_4

    .line 405
    move-object v1, p2

    check-cast v1, Landroid/graphics/drawable/BitmapDrawable;

    .line 406
    invoke-virtual {v1}, Landroid/graphics/drawable/BitmapDrawable;->getBitmap()Landroid/graphics/Bitmap;

    move-result-object v3

    if-eqz v3, :cond_4

    .line 407
    invoke-virtual {v3}, Landroid/graphics/Bitmap;->getDensity()I

    move-result v3

    if-nez v3, :cond_4

    .line 408
    iget-object v3, p0, Lcom/android/launcher3/icons/BaseIconFactory;->mContext:Landroid/content/Context;

    invoke-virtual {v3}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v3

    invoke-virtual {v3}, Landroid/content/res/Resources;->getDisplayMetrics()Landroid/util/DisplayMetrics;

    move-result-object v3

    invoke-virtual {v1, v3}, Landroid/graphics/drawable/BitmapDrawable;->setTargetDensity(Landroid/util/DisplayMetrics;)V

    .line 414
    :cond_4
    invoke-virtual {p2}, Landroid/graphics/drawable/Drawable;->getIntrinsicWidth()I

    move-result v1

    .line 415
    invoke-virtual {p2}, Landroid/graphics/drawable/Drawable;->getIntrinsicHeight()I

    move-result v3

    if-lez v1, :cond_6

    if-lez v3, :cond_6

    int-to-float v4, v1

    int-to-float v5, v3

    div-float/2addr v4, v5

    if-le v1, v3, :cond_5

    int-to-float v1, v0

    div-float/2addr v1, v4

    float-to-int v1, v1

    move v3, v1

    move v1, v0

    goto :goto_1

    :cond_5
    if-le v3, v1, :cond_6

    int-to-float v1, v0

    mul-float/2addr v1, v4

    float-to-int v1, v1

    move v3, v0

    goto :goto_1

    :cond_6
    move v1, v0

    move v3, v1

    :goto_1
    sub-int v4, v0, v1

    .line 425
    div-int/2addr v4, v2

    sub-int v5, v0, v3

    .line 426
    div-int/2addr v5, v2

    add-int/2addr v1, v4

    add-int/2addr v3, v5

    .line 427
    invoke-virtual {p2, v4, v5, v1, v3}, Landroid/graphics/drawable/Drawable;->setBounds(IIII)V

    .line 429
    invoke-virtual {p1}, Landroid/graphics/Canvas;->save()I

    .line 430
    div-int/2addr v0, v2

    int-to-float v0, v0

    invoke-virtual {p1, p3, p3, v0, v0}, Landroid/graphics/Canvas;->scale(FFFF)V

    .line 431
    invoke-virtual {p2, p1}, Landroid/graphics/drawable/Drawable;->draw(Landroid/graphics/Canvas;)V

    .line 432
    invoke-virtual {p1}, Landroid/graphics/Canvas;->restore()V

    if-ne p4, v2, :cond_7

    if-eqz p5, :cond_7

    .line 436
    invoke-virtual {p0}, Lcom/android/launcher3/icons/BaseIconFactory;->getShadowGenerator()Lcom/android/launcher3/icons/ShadowGenerator;

    move-result-object p4

    invoke-virtual {p4, p5, p1}, Lcom/android/launcher3/icons/ShadowGenerator;->drawShadow(Landroid/graphics/Bitmap;Landroid/graphics/Canvas;)V

    .line 439
    invoke-virtual {p1}, Landroid/graphics/Canvas;->save()I

    .line 440
    invoke-virtual {p1, p3, p3, v0, v0}, Landroid/graphics/Canvas;->scale(FFFF)V

    .line 441
    invoke-virtual {p2, p1}, Landroid/graphics/drawable/Drawable;->draw(Landroid/graphics/Canvas;)V

    .line 442
    invoke-virtual {p1}, Landroid/graphics/Canvas;->restore()V

    .line 445
    :cond_7
    :goto_2
    iget-object p0, p0, Lcom/android/launcher3/icons/BaseIconFactory;->mOldBounds:Landroid/graphics/Rect;

    invoke-virtual {p2, p0}, Landroid/graphics/drawable/Drawable;->setBounds(Landroid/graphics/Rect;)V

    return-void
.end method

.method public static getBadgeSizeForIconSize(I)I
    .locals 1

    int-to-float p0, p0

    const v0, 0x3ee353f8    # 0.444f

    mul-float/2addr p0, v0

    float-to-int p0, p0

    return p0
.end method

.method private synthetic lambda$createIconBitmap$0(Landroid/graphics/drawable/Drawable;FILandroid/graphics/Canvas;)V
    .locals 6

    const/4 v5, 0x0

    move-object v0, p0

    move-object v1, p4

    move-object v2, p1

    move v3, p2

    move v4, p3

    .line 362
    invoke-direct/range {v0 .. v5}, Lcom/android/launcher3/icons/BaseIconFactory;->drawIconBitmap(Landroid/graphics/Canvas;Landroid/graphics/drawable/Drawable;FILandroid/graphics/Bitmap;)V

    return-void
.end method


# virtual methods
.method protected clear()V
    .locals 1

    const/4 v0, -0x1

    .line 129
    iput v0, p0, Lcom/android/launcher3/icons/BaseIconFactory;->mWrapperBackgroundColor:I

    return-void
.end method

.method public createBadgedIconBitmap(Landroid/graphics/drawable/Drawable;Lcom/android/launcher3/icons/BaseIconFactory$IconOptions;)Lcom/android/launcher3/icons/BitmapInfo;
    .locals 7
    .annotation build Landroid/annotation/TargetApi;
        value = 0x21
    .end annotation

    const/4 v0, 0x1

    const/4 v1, 0x0

    if-eqz p2, :cond_1

    .line 215
    iget-boolean v2, p2, Lcom/android/launcher3/icons/BaseIconFactory$IconOptions;->mShrinkNonAdaptiveIcons:Z

    if-eqz v2, :cond_0

    goto :goto_0

    :cond_0
    move v2, v1

    goto :goto_1

    :cond_1
    :goto_0
    move v2, v0

    :goto_1
    new-array v3, v0, [F

    const/4 v4, 0x0

    .line 217
    invoke-virtual {p0, p1, v2, v4, v3}, Lcom/android/launcher3/icons/BaseIconFactory;->normalizeAndWrapToAdaptiveIcon(Landroid/graphics/drawable/Drawable;ZLandroid/graphics/RectF;[F)Landroid/graphics/drawable/Drawable;

    move-result-object p1

    aget v2, v3, v1

    if-nez p2, :cond_2

    const/4 v4, 0x2

    goto :goto_2

    .line 219
    :cond_2
    iget v4, p2, Lcom/android/launcher3/icons/BaseIconFactory$IconOptions;->mGenerationMode:I

    .line 218
    :goto_2
    invoke-virtual {p0, p1, v2, v4}, Lcom/android/launcher3/icons/BaseIconFactory;->createIconBitmap(Landroid/graphics/drawable/Drawable;FI)Landroid/graphics/Bitmap;

    move-result-object v2

    if-eqz p2, :cond_3

    .line 221
    iget-object v4, p2, Lcom/android/launcher3/icons/BaseIconFactory$IconOptions;->mExtractedColor:Ljava/lang/Integer;

    if-eqz v4, :cond_3

    .line 222
    invoke-virtual {v4}, Ljava/lang/Integer;->intValue()I

    move-result v4

    goto :goto_3

    :cond_3
    iget-object v4, p0, Lcom/android/launcher3/icons/BaseIconFactory;->mColorExtractor:Lcom/android/launcher3/icons/ColorExtractor;

    invoke-virtual {v4, v2}, Lcom/android/launcher3/icons/ColorExtractor;->findDominantColorByHue(Landroid/graphics/Bitmap;)I

    move-result v4

    .line 223
    :goto_3
    invoke-static {v2, v4}, Lcom/android/launcher3/icons/BitmapInfo;->of(Landroid/graphics/Bitmap;I)Lcom/android/launcher3/icons/BitmapInfo;

    move-result-object v5

    .line 225
    instance-of v6, p1, Lcom/android/launcher3/icons/BitmapInfo$Extender;

    if-eqz v6, :cond_4

    .line 226
    check-cast p1, Lcom/android/launcher3/icons/BitmapInfo$Extender;

    aget v0, v3, v1

    invoke-interface {p1, v2, v4, p0, v0}, Lcom/android/launcher3/icons/BitmapInfo$Extender;->getExtendedInfo(Landroid/graphics/Bitmap;ILcom/android/launcher3/icons/BaseIconFactory;F)Lcom/android/launcher3/icons/BitmapInfo;

    move-result-object v5

    goto :goto_4

    .line 227
    :cond_4
    sget-boolean v2, Lcom/android/launcher3/icons/IconProvider;->ATLEAST_T:Z

    if-eqz v2, :cond_5

    iget-boolean v2, p0, Lcom/android/launcher3/icons/BaseIconFactory;->mMonoIconEnabled:Z

    if-eqz v2, :cond_5

    .line 228
    invoke-virtual {p0, p1}, Lcom/android/launcher3/icons/BaseIconFactory;->getMonochromeDrawable(Landroid/graphics/drawable/Drawable;)Landroid/graphics/drawable/Drawable;

    move-result-object p1

    if-eqz p1, :cond_5

    aget v1, v3, v1

    .line 230
    invoke-virtual {p0, p1, v1, v0}, Lcom/android/launcher3/icons/BaseIconFactory;->createIconBitmap(Landroid/graphics/drawable/Drawable;FI)Landroid/graphics/Bitmap;

    move-result-object p1

    invoke-virtual {v5, p1, p0}, Lcom/android/launcher3/icons/BitmapInfo;->setMonoIcon(Landroid/graphics/Bitmap;Lcom/android/launcher3/icons/BaseIconFactory;)V

    .line 233
    :cond_5
    :goto_4
    invoke-virtual {p0, p2}, Lcom/android/launcher3/icons/BaseIconFactory;->getBitmapFlagOp(Lcom/android/launcher3/icons/BaseIconFactory$IconOptions;)Lcom/android/launcher3/util/FlagOp;

    move-result-object p0

    invoke-virtual {v5, p0}, Lcom/android/launcher3/icons/BitmapInfo;->withFlags(Lcom/android/launcher3/util/FlagOp;)Lcom/android/launcher3/icons/BitmapInfo;

    move-result-object p0

    return-object p0
.end method

.method protected createIconBitmap(Landroid/graphics/drawable/Drawable;FI)Landroid/graphics/Bitmap;
    .locals 7

    .line 353
    iget v0, p0, Lcom/android/launcher3/icons/BaseIconFactory;->mIconBitmapSize:I

    const/4 v1, 0x1

    if-eq p3, v1, :cond_1

    const/4 v1, 0x3

    if-eq p3, v1, :cond_0

    const/4 v1, 0x4

    if-eq p3, v1, :cond_0

    .line 366
    sget-object v1, Landroid/graphics/Bitmap$Config;->ARGB_8888:Landroid/graphics/Bitmap$Config;

    invoke-static {v0, v0, v1}, Landroid/graphics/Bitmap;->createBitmap(IILandroid/graphics/Bitmap$Config;)Landroid/graphics/Bitmap;

    move-result-object v0

    goto :goto_0

    .line 361
    :cond_0
    new-instance v1, Lcom/android/launcher3/icons/BaseIconFactory$$ExternalSyntheticLambda0;

    invoke-direct {v1, p0, p1, p2, p3}, Lcom/android/launcher3/icons/BaseIconFactory$$ExternalSyntheticLambda0;-><init>(Lcom/android/launcher3/icons/BaseIconFactory;Landroid/graphics/drawable/Drawable;FI)V

    invoke-static {v0, v0, v1}, Lcom/android/launcher3/icons/BitmapRenderer;->createHardwareBitmap(IILcom/android/launcher3/icons/BitmapRenderer;)Landroid/graphics/Bitmap;

    move-result-object p0

    return-object p0

    .line 357
    :cond_1
    sget-object v1, Landroid/graphics/Bitmap$Config;->ALPHA_8:Landroid/graphics/Bitmap$Config;

    invoke-static {v0, v0, v1}, Landroid/graphics/Bitmap;->createBitmap(IILandroid/graphics/Bitmap$Config;)Landroid/graphics/Bitmap;

    move-result-object v0

    :goto_0
    if-nez p1, :cond_2

    return-object v0

    .line 372
    :cond_2
    iget-object v1, p0, Lcom/android/launcher3/icons/BaseIconFactory;->mCanvas:Landroid/graphics/Canvas;

    invoke-virtual {v1, v0}, Landroid/graphics/Canvas;->setBitmap(Landroid/graphics/Bitmap;)V

    .line 373
    iget-object v2, p0, Lcom/android/launcher3/icons/BaseIconFactory;->mCanvas:Landroid/graphics/Canvas;

    move-object v1, p0

    move-object v3, p1

    move v4, p2

    move v5, p3

    move-object v6, v0

    invoke-direct/range {v1 .. v6}, Lcom/android/launcher3/icons/BaseIconFactory;->drawIconBitmap(Landroid/graphics/Canvas;Landroid/graphics/drawable/Drawable;FILandroid/graphics/Bitmap;)V

    .line 374
    iget-object p0, p0, Lcom/android/launcher3/icons/BaseIconFactory;->mCanvas:Landroid/graphics/Canvas;

    const/4 p1, 0x0

    invoke-virtual {p0, p1}, Landroid/graphics/Canvas;->setBitmap(Landroid/graphics/Bitmap;)V

    return-object v0
.end method

.method public createScaledBitmap(Landroid/graphics/drawable/Drawable;I)Landroid/graphics/Bitmap;
    .locals 3

    .line 293
    new-instance v0, Landroid/graphics/RectF;

    invoke-direct {v0}, Landroid/graphics/RectF;-><init>()V

    const/4 v1, 0x1

    new-array v2, v1, [F

    .line 295
    invoke-virtual {p0, p1, v1, v0, v2}, Lcom/android/launcher3/icons/BaseIconFactory;->normalizeAndWrapToAdaptiveIcon(Landroid/graphics/drawable/Drawable;ZLandroid/graphics/RectF;[F)Landroid/graphics/drawable/Drawable;

    move-result-object p1

    const/4 v1, 0x0

    aget v1, v2, v1

    .line 297
    invoke-static {v0}, Lcom/android/launcher3/icons/ShadowGenerator;->getScaleForBounds(Landroid/graphics/RectF;)F

    move-result v0

    invoke-static {v1, v0}, Ljava/lang/Math;->min(FF)F

    move-result v0

    .line 296
    invoke-virtual {p0, p1, v0, p2}, Lcom/android/launcher3/icons/BaseIconFactory;->createIconBitmap(Landroid/graphics/drawable/Drawable;FI)Landroid/graphics/Bitmap;

    move-result-object p0

    return-object p0
.end method

.method public getBitmapFlagOp(Lcom/android/launcher3/icons/BaseIconFactory$IconOptions;)Lcom/android/launcher3/util/FlagOp;
    .locals 7

    .line 254
    sget-object v0, Lcom/android/launcher3/util/FlagOp;->NO_OP:Lcom/android/launcher3/util/FlagOp;

    if-eqz p1, :cond_5

    .line 256
    iget-boolean v1, p1, Lcom/android/launcher3/icons/BaseIconFactory$IconOptions;->mIsInstantApp:Z

    if-eqz v1, :cond_0

    const/4 v1, 0x2

    .line 257
    invoke-interface {v0, v1}, Lcom/android/launcher3/util/FlagOp;->addFlag(I)Lcom/android/launcher3/util/FlagOp;

    move-result-object v0

    .line 260
    :cond_0
    iget-object v1, p1, Lcom/android/launcher3/icons/BaseIconFactory$IconOptions;->mUserHandle:Landroid/os/UserHandle;

    if-eqz v1, :cond_5

    .line 261
    invoke-virtual {v1}, Landroid/os/UserHandle;->hashCode()I

    move-result v1

    .line 264
    iget-object v2, p0, Lcom/android/launcher3/icons/BaseIconFactory;->mIsUserBadged:Landroid/util/SparseBooleanArray;

    invoke-virtual {v2, v1}, Landroid/util/SparseBooleanArray;->indexOfKey(I)I

    move-result v2

    const/4 v3, 0x0

    const/4 v4, 0x1

    if-ltz v2, :cond_1

    .line 265
    iget-object p0, p0, Lcom/android/launcher3/icons/BaseIconFactory;->mIsUserBadged:Landroid/util/SparseBooleanArray;

    invoke-virtual {p0, v2}, Landroid/util/SparseBooleanArray;->valueAt(I)Z

    move-result p0

    goto :goto_1

    .line 268
    :cond_1
    new-instance v2, Lcom/android/launcher3/icons/BaseIconFactory$NoopDrawable;

    const/4 v5, 0x0

    invoke-direct {v2, v5}, Lcom/android/launcher3/icons/BaseIconFactory$NoopDrawable;-><init>(Lcom/android/launcher3/icons/BaseIconFactory$NoopDrawable-IA;)V

    .line 269
    iget-object v5, p0, Lcom/android/launcher3/icons/BaseIconFactory;->mPm:Landroid/content/pm/PackageManager;

    iget-object v6, p1, Lcom/android/launcher3/icons/BaseIconFactory$IconOptions;->mUserHandle:Landroid/os/UserHandle;

    invoke-virtual {v5, v2, v6}, Landroid/content/pm/PackageManager;->getUserBadgedIcon(Landroid/graphics/drawable/Drawable;Landroid/os/UserHandle;)Landroid/graphics/drawable/Drawable;

    move-result-object v5

    if-eq v2, v5, :cond_2

    move v2, v4

    goto :goto_0

    :cond_2
    move v2, v3

    .line 270
    :goto_0
    iget-object p0, p0, Lcom/android/launcher3/icons/BaseIconFactory;->mIsUserBadged:Landroid/util/SparseBooleanArray;

    invoke-virtual {p0, v1, v2}, Landroid/util/SparseBooleanArray;->put(IZ)V

    move p0, v2

    :goto_1
    const/4 v1, 0x4

    if-eqz p0, :cond_3

    .line 273
    iget-boolean v2, p1, Lcom/android/launcher3/icons/BaseIconFactory$IconOptions;->mIsCloneProfile:Z

    if-eqz v2, :cond_3

    move v2, v4

    goto :goto_2

    :cond_3
    move v2, v3

    :goto_2
    invoke-interface {v0, v1, v2}, Lcom/android/launcher3/util/FlagOp;->setFlag(IZ)Lcom/android/launcher3/util/FlagOp;

    move-result-object v0

    if-eqz p0, :cond_4

    .line 275
    iget-boolean p0, p1, Lcom/android/launcher3/icons/BaseIconFactory$IconOptions;->mIsCloneProfile:Z

    if-nez p0, :cond_4

    move v3, v4

    :cond_4
    invoke-interface {v0, v4, v3}, Lcom/android/launcher3/util/FlagOp;->setFlag(IZ)Lcom/android/launcher3/util/FlagOp;

    move-result-object v0

    :cond_5
    return-object v0
.end method

.method protected getMonochromeDrawable(Landroid/graphics/drawable/Drawable;)Landroid/graphics/drawable/Drawable;
    .locals 0
    .annotation build Landroid/annotation/TargetApi;
        value = 0x21
    .end annotation

    .line 243
    instance-of p0, p1, Landroid/graphics/drawable/AdaptiveIconDrawable;

    if-eqz p0, :cond_0

    .line 244
    check-cast p1, Landroid/graphics/drawable/AdaptiveIconDrawable;

    invoke-virtual {p1}, Landroid/graphics/drawable/AdaptiveIconDrawable;->getMonochrome()Landroid/graphics/drawable/Drawable;

    move-result-object p0

    if-eqz p0, :cond_0

    .line 246
    new-instance p1, Lcom/android/launcher3/icons/BaseIconFactory$ClippedMonoDrawable;

    invoke-direct {p1, p0}, Lcom/android/launcher3/icons/BaseIconFactory$ClippedMonoDrawable;-><init>(Landroid/graphics/drawable/Drawable;)V

    return-object p1

    :cond_0
    const/4 p0, 0x0

    return-object p0
.end method

.method public getNormalizer()Lcom/android/launcher3/icons/IconNormalizer;
    .locals 4

    .line 142
    iget-object v0, p0, Lcom/android/launcher3/icons/BaseIconFactory;->mNormalizer:Lcom/android/launcher3/icons/IconNormalizer;

    if-nez v0, :cond_0

    .line 143
    new-instance v0, Lcom/android/launcher3/icons/IconNormalizer;

    iget-object v1, p0, Lcom/android/launcher3/icons/BaseIconFactory;->mContext:Landroid/content/Context;

    iget v2, p0, Lcom/android/launcher3/icons/BaseIconFactory;->mIconBitmapSize:I

    iget-boolean v3, p0, Lcom/android/launcher3/icons/BaseIconFactory;->mShapeDetection:Z

    invoke-direct {v0, v1, v2, v3}, Lcom/android/launcher3/icons/IconNormalizer;-><init>(Landroid/content/Context;IZ)V

    iput-object v0, p0, Lcom/android/launcher3/icons/BaseIconFactory;->mNormalizer:Lcom/android/launcher3/icons/IconNormalizer;

    .line 145
    :cond_0
    iget-object p0, p0, Lcom/android/launcher3/icons/BaseIconFactory;->mNormalizer:Lcom/android/launcher3/icons/IconNormalizer;

    return-object p0
.end method

.method public getShadowGenerator()Lcom/android/launcher3/icons/ShadowGenerator;
    .locals 2

    .line 134
    iget-object v0, p0, Lcom/android/launcher3/icons/BaseIconFactory;->mShadowGenerator:Lcom/android/launcher3/icons/ShadowGenerator;

    if-nez v0, :cond_0

    .line 135
    new-instance v0, Lcom/android/launcher3/icons/ShadowGenerator;

    iget v1, p0, Lcom/android/launcher3/icons/BaseIconFactory;->mIconBitmapSize:I

    invoke-direct {v0, v1}, Lcom/android/launcher3/icons/ShadowGenerator;-><init>(I)V

    iput-object v0, p0, Lcom/android/launcher3/icons/BaseIconFactory;->mShadowGenerator:Lcom/android/launcher3/icons/ShadowGenerator;

    .line 137
    :cond_0
    iget-object p0, p0, Lcom/android/launcher3/icons/BaseIconFactory;->mShadowGenerator:Lcom/android/launcher3/icons/ShadowGenerator;

    return-object p0
.end method

.method public getWhiteShadowLayer()Landroid/graphics/Bitmap;
    .locals 3

    .line 283
    iget-object v0, p0, Lcom/android/launcher3/icons/BaseIconFactory;->mWhiteShadowLayer:Landroid/graphics/Bitmap;

    if-nez v0, :cond_0

    .line 284
    new-instance v0, Landroid/graphics/drawable/AdaptiveIconDrawable;

    new-instance v1, Landroid/graphics/drawable/ColorDrawable;

    const/4 v2, -0x1

    invoke-direct {v1, v2}, Landroid/graphics/drawable/ColorDrawable;-><init>(I)V

    const/4 v2, 0x0

    invoke-direct {v0, v1, v2}, Landroid/graphics/drawable/AdaptiveIconDrawable;-><init>(Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;)V

    const/4 v1, 0x4

    invoke-virtual {p0, v0, v1}, Lcom/android/launcher3/icons/BaseIconFactory;->createScaledBitmap(Landroid/graphics/drawable/Drawable;I)Landroid/graphics/Bitmap;

    move-result-object v0

    iput-object v0, p0, Lcom/android/launcher3/icons/BaseIconFactory;->mWhiteShadowLayer:Landroid/graphics/Bitmap;

    .line 288
    :cond_0
    iget-object p0, p0, Lcom/android/launcher3/icons/BaseIconFactory;->mWhiteShadowLayer:Landroid/graphics/Bitmap;

    return-object p0
.end method

.method protected normalizeAndWrapToAdaptiveIcon(Landroid/graphics/drawable/Drawable;ZLandroid/graphics/RectF;[F)Landroid/graphics/drawable/Drawable;
    .locals 5

    const/4 v0, 0x0

    if-nez p1, :cond_0

    return-object v0

    :cond_0
    const/4 v1, 0x0

    if-eqz p2, :cond_2

    .line 316
    instance-of p2, p1, Landroid/graphics/drawable/AdaptiveIconDrawable;

    if-nez p2, :cond_2

    .line 317
    iget-object p2, p0, Lcom/android/launcher3/icons/BaseIconFactory;->mWrapperIcon:Landroid/graphics/drawable/Drawable;

    if-nez p2, :cond_1

    .line 318
    iget-object p2, p0, Lcom/android/launcher3/icons/BaseIconFactory;->mContext:Landroid/content/Context;

    sget v2, Lcom/android/launcher3/icons/R$drawable;->adaptive_icon_drawable_wrapper:I

    invoke-virtual {p2, v2}, Landroid/content/Context;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object p2

    .line 319
    invoke-virtual {p2}, Landroid/graphics/drawable/Drawable;->mutate()Landroid/graphics/drawable/Drawable;

    move-result-object p2

    iput-object p2, p0, Lcom/android/launcher3/icons/BaseIconFactory;->mWrapperIcon:Landroid/graphics/drawable/Drawable;

    .line 321
    :cond_1
    iget-object p2, p0, Lcom/android/launcher3/icons/BaseIconFactory;->mWrapperIcon:Landroid/graphics/drawable/Drawable;

    check-cast p2, Landroid/graphics/drawable/AdaptiveIconDrawable;

    const/4 v2, 0x1

    .line 322
    invoke-virtual {p2, v1, v1, v2, v2}, Landroid/graphics/drawable/AdaptiveIconDrawable;->setBounds(IIII)V

    new-array v2, v2, [Z

    .line 324
    invoke-virtual {p0}, Lcom/android/launcher3/icons/BaseIconFactory;->getNormalizer()Lcom/android/launcher3/icons/IconNormalizer;

    move-result-object v3

    invoke-virtual {p2}, Landroid/graphics/drawable/AdaptiveIconDrawable;->getIconMask()Landroid/graphics/Path;

    move-result-object v4

    invoke-virtual {v3, p1, p3, v4, v2}, Lcom/android/launcher3/icons/IconNormalizer;->getScale(Landroid/graphics/drawable/Drawable;Landroid/graphics/RectF;Landroid/graphics/Path;[Z)F

    move-result v3

    aget-boolean v2, v2, v1

    if-nez v2, :cond_3

    .line 325
    invoke-virtual {p1}, Landroid/graphics/drawable/Drawable;->getChangingConfigurations()I

    move-result v2

    const/high16 v4, 0x1000000

    and-int/2addr v2, v4

    if-nez v2, :cond_3

    .line 327
    invoke-virtual {p1}, Landroid/graphics/drawable/Drawable;->getAlpha()I

    move-result v2

    invoke-virtual {p2, v2}, Landroid/graphics/drawable/AdaptiveIconDrawable;->setAlpha(I)V

    const/16 v2, 0xff

    .line 328
    invoke-virtual {p1, v2}, Landroid/graphics/drawable/Drawable;->setAlpha(I)V

    .line 330
    invoke-virtual {p2}, Landroid/graphics/drawable/AdaptiveIconDrawable;->getForeground()Landroid/graphics/drawable/Drawable;

    move-result-object v2

    check-cast v2, Lcom/android/launcher3/icons/FixedScaleDrawable;

    .line 331
    invoke-virtual {v2, p1}, Landroid/graphics/drawable/DrawableWrapper;->setDrawable(Landroid/graphics/drawable/Drawable;)V

    .line 332
    invoke-virtual {v2, v3}, Lcom/android/launcher3/icons/FixedScaleDrawable;->setScale(F)V

    .line 334
    invoke-virtual {p0}, Lcom/android/launcher3/icons/BaseIconFactory;->getNormalizer()Lcom/android/launcher3/icons/IconNormalizer;

    move-result-object p1

    invoke-virtual {p1, p2, p3, v0, v0}, Lcom/android/launcher3/icons/IconNormalizer;->getScale(Landroid/graphics/drawable/Drawable;Landroid/graphics/RectF;Landroid/graphics/Path;[Z)F

    move-result v3

    .line 335
    invoke-virtual {p2}, Landroid/graphics/drawable/AdaptiveIconDrawable;->getBackground()Landroid/graphics/drawable/Drawable;

    move-result-object p1

    check-cast p1, Landroid/graphics/drawable/ColorDrawable;

    iget p0, p0, Lcom/android/launcher3/icons/BaseIconFactory;->mWrapperBackgroundColor:I

    invoke-virtual {p1, p0}, Landroid/graphics/drawable/ColorDrawable;->setColor(I)V

    move-object p1, p2

    goto :goto_0

    .line 338
    :cond_2
    invoke-virtual {p0}, Lcom/android/launcher3/icons/BaseIconFactory;->getNormalizer()Lcom/android/launcher3/icons/IconNormalizer;

    move-result-object p0

    invoke-virtual {p0, p1, p3, v0, v0}, Lcom/android/launcher3/icons/IconNormalizer;->getScale(Landroid/graphics/drawable/Drawable;Landroid/graphics/RectF;Landroid/graphics/Path;[Z)F

    move-result v3

    .line 341
    :cond_3
    :goto_0
    aput v3, p4, v1

    return-object p1
.end method
