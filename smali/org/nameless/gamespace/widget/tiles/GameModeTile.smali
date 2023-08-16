.class public final Lorg/nameless/gamespace/widget/tiles/GameModeTile;
.super Lorg/nameless/gamespace/widget/tiles/BaseTile;
.source "GameModeTile.kt"


# instance fields
.field private activeMode:I

.field private final gameModeUtils$delegate:Lkotlin/Lazy;

.field private final modes:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "Ljava/lang/Integer;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method public constructor <init>(Landroid/content/Context;)V
    .locals 2

    const-string v0, "context"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    const/4 v0, 0x0

    const/4 v1, 0x2

    invoke-direct {p0, p1, v0, v1, v0}, Lorg/nameless/gamespace/widget/tiles/GameModeTile;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 3

    const-string v0, "context"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 29
    invoke-direct {p0, p1, p2}, Lorg/nameless/gamespace/widget/tiles/BaseTile;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    .line 31
    new-instance p2, Lorg/nameless/gamespace/widget/tiles/GameModeTile$gameModeUtils$2;

    invoke-direct {p2, p1}, Lorg/nameless/gamespace/widget/tiles/GameModeTile$gameModeUtils$2;-><init>(Landroid/content/Context;)V

    invoke-static {p2}, Lkotlin/LazyKt;->lazy(Lkotlin/jvm/functions/Function0;)Lkotlin/Lazy;

    move-result-object p1

    iput-object p1, p0, Lorg/nameless/gamespace/widget/tiles/GameModeTile;->gameModeUtils$delegate:Lkotlin/Lazy;

    const/4 p1, 0x3

    new-array p2, p1, [Ljava/lang/Integer;

    const/4 v0, 0x1

    .line 36
    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    const/4 v2, 0x0

    aput-object v1, p2, v2

    const/4 v1, 0x2

    .line 37
    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    aput-object v2, p2, v0

    .line 38
    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    aput-object p1, p2, v1

    .line 35
    invoke-static {p2}, Lkotlin/collections/CollectionsKt;->listOf([Ljava/lang/Object;)Ljava/util/List;

    move-result-object p1

    iput-object p1, p0, Lorg/nameless/gamespace/widget/tiles/GameModeTile;->modes:Ljava/util/List;

    .line 41
    iput v0, p0, Lorg/nameless/gamespace/widget/tiles/GameModeTile;->activeMode:I

    return-void
.end method

.method public synthetic constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;ILkotlin/jvm/internal/DefaultConstructorMarker;)V
    .locals 0

    and-int/lit8 p3, p3, 0x2

    if-eqz p3, :cond_0

    const/4 p2, 0x0

    .line 27
    :cond_0
    invoke-direct {p0, p1, p2}, Lorg/nameless/gamespace/widget/tiles/GameModeTile;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    return-void
.end method

.method private final getGameModeUtils()Lorg/nameless/gamespace/utils/GameModeUtils;
    .locals 0

    .line 31
    iget-object p0, p0, Lorg/nameless/gamespace/widget/tiles/GameModeTile;->gameModeUtils$delegate:Lkotlin/Lazy;

    invoke-interface {p0}, Lkotlin/Lazy;->getValue()Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lorg/nameless/gamespace/utils/GameModeUtils;

    return-object p0
.end method

.method private final setActiveMode(I)V
    .locals 4

    .line 43
    iput p1, p0, Lorg/nameless/gamespace/widget/tiles/GameModeTile;->activeMode:I

    .line 44
    invoke-virtual {p0}, Lorg/nameless/gamespace/widget/tiles/BaseTile;->getSummary()Landroid/widget/TextView;

    move-result-object v0

    if-nez v0, :cond_0

    goto :goto_0

    :cond_0
    sget-object v1, Lorg/nameless/gamespace/utils/GameModeUtils;->Companion:Lorg/nameless/gamespace/utils/GameModeUtils$Companion;

    invoke-virtual {p0}, Landroid/widget/LinearLayout;->getContext()Landroid/content/Context;

    move-result-object v2

    const-string v3, "context"

    invoke-static {v2, v3}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullExpressionValue(Ljava/lang/Object;Ljava/lang/String;)V

    invoke-virtual {v1, v2, p1}, Lorg/nameless/gamespace/utils/GameModeUtils$Companion;->describeGameMode(Landroid/content/Context;I)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    :goto_0
    const/4 v0, 0x1

    if-eq p1, v0, :cond_1

    goto :goto_1

    :cond_1
    const/4 v0, 0x0

    .line 45
    :goto_1
    invoke-virtual {p0, v0}, Landroid/widget/LinearLayout;->setSelected(Z)V

    .line 46
    invoke-direct {p0}, Lorg/nameless/gamespace/widget/tiles/GameModeTile;->getGameModeUtils()Lorg/nameless/gamespace/utils/GameModeUtils;

    move-result-object v0

    invoke-virtual {p0}, Lorg/nameless/gamespace/widget/tiles/BaseTile;->getSystemSettings()Lorg/nameless/gamespace/data/SystemSettings;

    move-result-object p0

    invoke-virtual {v0, p0, p1}, Lorg/nameless/gamespace/utils/GameModeUtils;->setActiveGameMode(Lorg/nameless/gamespace/data/SystemSettings;I)V

    return-void
.end method


# virtual methods
.method protected onAttachedToWindow()V
    .locals 3

    .line 50
    invoke-super {p0}, Landroid/widget/LinearLayout;->onAttachedToWindow()V

    .line 51
    invoke-virtual {p0}, Lorg/nameless/gamespace/widget/tiles/BaseTile;->getTitle()Landroid/widget/TextView;

    move-result-object v0

    if-nez v0, :cond_0

    goto :goto_0

    :cond_0
    invoke-virtual {p0}, Landroid/widget/LinearLayout;->getContext()Landroid/content/Context;

    move-result-object v1

    const v2, 0x7f12017b

    invoke-virtual {v1, v2}, Landroid/content/Context;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 52
    :goto_0
    invoke-direct {p0}, Lorg/nameless/gamespace/widget/tiles/GameModeTile;->getGameModeUtils()Lorg/nameless/gamespace/utils/GameModeUtils;

    move-result-object v0

    invoke-virtual {v0}, Lorg/nameless/gamespace/utils/GameModeUtils;->getActiveGame()Lorg/nameless/gamespace/data/UserGame;

    move-result-object v0

    if-eqz v0, :cond_1

    invoke-virtual {v0}, Lorg/nameless/gamespace/data/UserGame;->getMode()I

    move-result v0

    goto :goto_1

    :cond_1
    const/4 v0, 0x1

    :goto_1
    invoke-direct {p0, v0}, Lorg/nameless/gamespace/widget/tiles/GameModeTile;->setActiveMode(I)V

    .line 53
    invoke-virtual {p0}, Lorg/nameless/gamespace/widget/tiles/BaseTile;->getIcon()Landroid/widget/ImageView;

    move-result-object p0

    if-eqz p0, :cond_2

    const v0, 0x7f080191

    invoke-virtual {p0, v0}, Landroid/widget/ImageView;->setImageResource(I)V

    :cond_2
    return-void
.end method

.method public onClick(Landroid/view/View;)V
    .locals 2

    .line 57
    invoke-super {p0, p1}, Lorg/nameless/gamespace/widget/tiles/BaseTile;->onClick(Landroid/view/View;)V

    .line 58
    iget-object p1, p0, Lorg/nameless/gamespace/widget/tiles/GameModeTile;->modes:Ljava/util/List;

    iget v0, p0, Lorg/nameless/gamespace/widget/tiles/GameModeTile;->activeMode:I

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    invoke-interface {p1, v0}, Ljava/util/List;->indexOf(Ljava/lang/Object;)I

    move-result p1

    .line 59
    iget-object v0, p0, Lorg/nameless/gamespace/widget/tiles/GameModeTile;->modes:Ljava/util/List;

    invoke-interface {v0}, Ljava/util/List;->size()I

    move-result v1

    add-int/lit8 v1, v1, -0x1

    if-ne p1, v1, :cond_0

    const/4 p1, 0x0

    goto :goto_0

    :cond_0
    add-int/lit8 p1, p1, 0x1

    :goto_0
    invoke-interface {v0, p1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/Number;

    invoke-virtual {p1}, Ljava/lang/Number;->intValue()I

    move-result p1

    invoke-direct {p0, p1}, Lorg/nameless/gamespace/widget/tiles/GameModeTile;->setActiveMode(I)V

    return-void
.end method
