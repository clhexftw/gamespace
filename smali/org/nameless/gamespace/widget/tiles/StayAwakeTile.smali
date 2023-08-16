.class public final Lorg/nameless/gamespace/widget/tiles/StayAwakeTile;
.super Lorg/nameless/gamespace/widget/tiles/BaseTile;
.source "StayAwakeTile.kt"


# instance fields
.field private final screenUtils$delegate:Lkotlin/Lazy;

.field private shouldStayAwake:Z


# direct methods
.method public constructor <init>(Landroid/content/Context;)V
    .locals 2

    const-string v0, "context"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    const/4 v0, 0x0

    const/4 v1, 0x2

    invoke-direct {p0, p1, v0, v1, v0}, Lorg/nameless/gamespace/widget/tiles/StayAwakeTile;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 1

    const-string v0, "context"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 27
    invoke-direct {p0, p1, p2}, Lorg/nameless/gamespace/widget/tiles/BaseTile;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    .line 29
    new-instance p2, Lorg/nameless/gamespace/widget/tiles/StayAwakeTile$screenUtils$2;

    invoke-direct {p2, p1}, Lorg/nameless/gamespace/widget/tiles/StayAwakeTile$screenUtils$2;-><init>(Landroid/content/Context;)V

    invoke-static {p2}, Lkotlin/LazyKt;->lazy(Lkotlin/jvm/functions/Function0;)Lkotlin/Lazy;

    move-result-object p1

    iput-object p1, p0, Lorg/nameless/gamespace/widget/tiles/StayAwakeTile;->screenUtils$delegate:Lkotlin/Lazy;

    return-void
.end method

.method public synthetic constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;ILkotlin/jvm/internal/DefaultConstructorMarker;)V
    .locals 0

    and-int/lit8 p3, p3, 0x2

    if-eqz p3, :cond_0

    const/4 p2, 0x0

    .line 25
    :cond_0
    invoke-direct {p0, p1, p2}, Lorg/nameless/gamespace/widget/tiles/StayAwakeTile;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    return-void
.end method

.method private final getScreenUtils()Lorg/nameless/gamespace/utils/ScreenUtils;
    .locals 0

    .line 29
    iget-object p0, p0, Lorg/nameless/gamespace/widget/tiles/StayAwakeTile;->screenUtils$delegate:Lkotlin/Lazy;

    invoke-interface {p0}, Lkotlin/Lazy;->getValue()Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lorg/nameless/gamespace/utils/ScreenUtils;

    return-object p0
.end method

.method private final setShouldStayAwake(Z)V
    .locals 3

    .line 40
    iput-boolean p1, p0, Lorg/nameless/gamespace/widget/tiles/StayAwakeTile;->shouldStayAwake:Z

    if-eqz p1, :cond_1

    .line 42
    invoke-virtual {p0}, Lorg/nameless/gamespace/widget/tiles/BaseTile;->getSummary()Landroid/widget/TextView;

    move-result-object v0

    if-nez v0, :cond_0

    goto :goto_0

    :cond_0
    invoke-virtual {p0}, Landroid/widget/LinearLayout;->getContext()Landroid/content/Context;

    move-result-object v1

    const v2, 0x7f1202ce

    invoke-virtual {v1, v2}, Landroid/content/Context;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    goto :goto_0

    .line 44
    :cond_1
    invoke-virtual {p0}, Lorg/nameless/gamespace/widget/tiles/BaseTile;->getSummary()Landroid/widget/TextView;

    move-result-object v0

    if-nez v0, :cond_2

    goto :goto_0

    :cond_2
    invoke-virtual {p0}, Landroid/widget/LinearLayout;->getContext()Landroid/content/Context;

    move-result-object v1

    const v2, 0x7f1202cd

    invoke-virtual {v1, v2}, Landroid/content/Context;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 46
    :goto_0
    invoke-virtual {p0}, Lorg/nameless/gamespace/widget/tiles/BaseTile;->getAppSettings()Lorg/nameless/gamespace/data/AppSettings;

    move-result-object v0

    invoke-virtual {v0, p1}, Lorg/nameless/gamespace/data/AppSettings;->setStayAwake(Z)V

    .line 47
    invoke-virtual {p0, p1}, Landroid/widget/LinearLayout;->setSelected(Z)V

    .line 48
    invoke-direct {p0}, Lorg/nameless/gamespace/widget/tiles/StayAwakeTile;->getScreenUtils()Lorg/nameless/gamespace/utils/ScreenUtils;

    move-result-object p0

    invoke-virtual {p0, p1}, Lorg/nameless/gamespace/utils/ScreenUtils;->setStayAwake(Z)V

    return-void
.end method


# virtual methods
.method protected onAttachedToWindow()V
    .locals 3

    .line 32
    invoke-super {p0}, Landroid/widget/LinearLayout;->onAttachedToWindow()V

    .line 33
    invoke-virtual {p0}, Lorg/nameless/gamespace/widget/tiles/BaseTile;->getAppSettings()Lorg/nameless/gamespace/data/AppSettings;

    move-result-object v0

    invoke-virtual {v0}, Lorg/nameless/gamespace/data/AppSettings;->getStayAwake()Z

    move-result v0

    invoke-direct {p0, v0}, Lorg/nameless/gamespace/widget/tiles/StayAwakeTile;->setShouldStayAwake(Z)V

    .line 34
    invoke-virtual {p0}, Lorg/nameless/gamespace/widget/tiles/BaseTile;->getTitle()Landroid/widget/TextView;

    move-result-object v0

    if-nez v0, :cond_0

    goto :goto_0

    :cond_0
    invoke-virtual {p0}, Landroid/widget/LinearLayout;->getContext()Landroid/content/Context;

    move-result-object v1

    const v2, 0x7f1202d3

    invoke-virtual {v1, v2}, Landroid/content/Context;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 35
    :goto_0
    invoke-virtual {p0}, Lorg/nameless/gamespace/widget/tiles/BaseTile;->getIcon()Landroid/widget/ImageView;

    move-result-object p0

    if-eqz p0, :cond_1

    const v0, 0x7f0800ac

    invoke-virtual {p0, v0}, Landroid/widget/ImageView;->setImageResource(I)V

    :cond_1
    return-void
.end method

.method public onClick(Landroid/view/View;)V
    .locals 0

    .line 52
    invoke-super {p0, p1}, Lorg/nameless/gamespace/widget/tiles/BaseTile;->onClick(Landroid/view/View;)V

    .line 53
    iget-boolean p1, p0, Lorg/nameless/gamespace/widget/tiles/StayAwakeTile;->shouldStayAwake:Z

    xor-int/lit8 p1, p1, 0x1

    invoke-direct {p0, p1}, Lorg/nameless/gamespace/widget/tiles/StayAwakeTile;->setShouldStayAwake(Z)V

    return-void
.end method
