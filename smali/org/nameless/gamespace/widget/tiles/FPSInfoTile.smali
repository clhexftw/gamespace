.class public final Lorg/nameless/gamespace/widget/tiles/FPSInfoTile;
.super Lorg/nameless/gamespace/widget/tiles/BaseTile;
.source "FPSInfoTile.kt"


# instance fields
.field private showFpsInfo:Z


# direct methods
.method public constructor <init>(Landroid/content/Context;)V
    .locals 2

    const-string v0, "context"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    const/4 v0, 0x0

    const/4 v1, 0x2

    invoke-direct {p0, p1, v0, v1, v0}, Lorg/nameless/gamespace/widget/tiles/FPSInfoTile;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 1

    const-string v0, "context"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 25
    invoke-direct {p0, p1, p2}, Lorg/nameless/gamespace/widget/tiles/BaseTile;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    return-void
.end method

.method public synthetic constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;ILkotlin/jvm/internal/DefaultConstructorMarker;)V
    .locals 0

    and-int/lit8 p3, p3, 0x2

    if-eqz p3, :cond_0

    const/4 p2, 0x0

    .line 23
    :cond_0
    invoke-direct {p0, p1, p2}, Lorg/nameless/gamespace/widget/tiles/FPSInfoTile;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    return-void
.end method

.method private final setShowFpsInfo(Z)V
    .locals 3

    .line 37
    iput-boolean p1, p0, Lorg/nameless/gamespace/widget/tiles/FPSInfoTile;->showFpsInfo:Z

    if-eqz p1, :cond_1

    .line 39
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

    .line 41
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

    .line 43
    :goto_0
    invoke-virtual {p0}, Lorg/nameless/gamespace/widget/tiles/BaseTile;->getAppSettings()Lorg/nameless/gamespace/data/AppSettings;

    move-result-object v0

    invoke-virtual {v0, p1}, Lorg/nameless/gamespace/data/AppSettings;->setShowFps(Z)V

    .line 44
    invoke-virtual {p0, p1}, Landroid/widget/LinearLayout;->setSelected(Z)V

    return-void
.end method


# virtual methods
.method protected onAttachedToWindow()V
    .locals 3

    .line 29
    invoke-super {p0}, Landroid/widget/LinearLayout;->onAttachedToWindow()V

    .line 30
    invoke-virtual {p0}, Lorg/nameless/gamespace/widget/tiles/BaseTile;->getAppSettings()Lorg/nameless/gamespace/data/AppSettings;

    move-result-object v0

    invoke-virtual {v0}, Lorg/nameless/gamespace/data/AppSettings;->getShowFps()Z

    move-result v0

    invoke-direct {p0, v0}, Lorg/nameless/gamespace/widget/tiles/FPSInfoTile;->setShowFpsInfo(Z)V

    .line 31
    invoke-virtual {p0}, Lorg/nameless/gamespace/widget/tiles/BaseTile;->getTitle()Landroid/widget/TextView;

    move-result-object v0

    if-nez v0, :cond_0

    goto :goto_0

    :cond_0
    invoke-virtual {p0}, Landroid/widget/LinearLayout;->getContext()Landroid/content/Context;

    move-result-object v1

    const v2, 0x7f120175

    invoke-virtual {v1, v2}, Landroid/content/Context;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 32
    :goto_0
    invoke-virtual {p0}, Lorg/nameless/gamespace/widget/tiles/BaseTile;->getIcon()Landroid/widget/ImageView;

    move-result-object p0

    if-eqz p0, :cond_1

    const v0, 0x7f0800bc

    invoke-virtual {p0, v0}, Landroid/widget/ImageView;->setImageResource(I)V

    :cond_1
    return-void
.end method

.method public onClick(Landroid/view/View;)V
    .locals 0

    .line 48
    invoke-super {p0, p1}, Lorg/nameless/gamespace/widget/tiles/BaseTile;->onClick(Landroid/view/View;)V

    .line 49
    iget-boolean p1, p0, Lorg/nameless/gamespace/widget/tiles/FPSInfoTile;->showFpsInfo:Z

    xor-int/lit8 p1, p1, 0x1

    invoke-direct {p0, p1}, Lorg/nameless/gamespace/widget/tiles/FPSInfoTile;->setShowFpsInfo(Z)V

    return-void
.end method
