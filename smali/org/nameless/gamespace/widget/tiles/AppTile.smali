.class public final Lorg/nameless/gamespace/widget/tiles/AppTile;
.super Landroid/widget/LinearLayout;
.source "AppTile.kt"


# instance fields
.field private final key:Ljava/lang/String;


# direct methods
.method public constructor <init>(Landroid/content/Context;Ljava/lang/String;Landroid/util/AttributeSet;II)V
    .locals 1

    const-string v0, "context"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    const-string v0, "packageName"

    invoke-static {p2, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 32
    invoke-direct {p0, p1, p3, p4, p5}, Landroid/widget/LinearLayout;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;II)V

    .line 34
    iput-object p2, p0, Lorg/nameless/gamespace/widget/tiles/AppTile;->key:Ljava/lang/String;

    .line 37
    invoke-static {p1}, Landroid/view/LayoutInflater;->from(Landroid/content/Context;)Landroid/view/LayoutInflater;

    move-result-object p1

    const p3, 0x7f0d0020

    const/4 p4, 0x1

    invoke-virtual {p1, p3, p0, p4}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;Z)Landroid/view/View;

    const p1, 0x7f0a0063

    .line 38
    invoke-virtual {p0, p1}, Landroid/widget/LinearLayout;->findViewById(I)Landroid/view/View;

    move-result-object p0

    const-string p1, "findViewById(R.id.app_tile_icon)"

    invoke-static {p0, p1}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullExpressionValue(Ljava/lang/Object;Ljava/lang/String;)V

    check-cast p0, Landroid/widget/ImageView;

    .line 39
    sget-object p1, Lorg/nameless/gamespace/widget/QuickLaunchAppViewCache;->INSTANCE:Lorg/nameless/gamespace/widget/QuickLaunchAppViewCache;

    invoke-virtual {p1}, Lorg/nameless/gamespace/widget/QuickLaunchAppViewCache;->getIconMap()Ljava/util/Map;

    move-result-object p1

    invoke-static {p1, p2}, Lkotlin/collections/MapsKt;->getValue(Ljava/util/Map;Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Landroid/graphics/drawable/Drawable;

    if-eqz p1, :cond_0

    .line 40
    invoke-virtual {p0, p1}, Landroid/widget/ImageView;->setImageDrawable(Landroid/graphics/drawable/Drawable;)V

    :cond_0
    return-void
.end method

.method public synthetic constructor <init>(Landroid/content/Context;Ljava/lang/String;Landroid/util/AttributeSet;IIILkotlin/jvm/internal/DefaultConstructorMarker;)V
    .locals 6

    and-int/lit8 p7, p6, 0x4

    if-eqz p7, :cond_0

    const/4 p3, 0x0

    :cond_0
    move-object v3, p3

    and-int/lit8 p3, p6, 0x8

    const/4 p7, 0x0

    if-eqz p3, :cond_1

    move v4, p7

    goto :goto_0

    :cond_1
    move v4, p4

    :goto_0
    and-int/lit8 p3, p6, 0x10

    if-eqz p3, :cond_2

    move v5, p7

    goto :goto_1

    :cond_2
    move v5, p5

    :goto_1
    move-object v0, p0

    move-object v1, p1

    move-object v2, p2

    .line 29
    invoke-direct/range {v0 .. v5}, Lorg/nameless/gamespace/widget/tiles/AppTile;-><init>(Landroid/content/Context;Ljava/lang/String;Landroid/util/AttributeSet;II)V

    return-void
.end method


# virtual methods
.method public final getApp()Ljava/lang/String;
    .locals 0

    .line 45
    iget-object p0, p0, Lorg/nameless/gamespace/widget/tiles/AppTile;->key:Ljava/lang/String;

    return-object p0
.end method
