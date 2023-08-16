.class public final Lorg/nameless/gamespace/widget/tiles/NotificationTile;
.super Lorg/nameless/gamespace/widget/tiles/BaseTile;
.source "NotificationTile.kt"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lorg/nameless/gamespace/widget/tiles/NotificationTile$Companion;
    }
.end annotation


# static fields
.field public static final Companion:Lorg/nameless/gamespace/widget/tiles/NotificationTile$Companion;


# instance fields
.field private activeMode:I


# direct methods
.method static constructor <clinit>()V
    .locals 2

    new-instance v0, Lorg/nameless/gamespace/widget/tiles/NotificationTile$Companion;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lorg/nameless/gamespace/widget/tiles/NotificationTile$Companion;-><init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V

    sput-object v0, Lorg/nameless/gamespace/widget/tiles/NotificationTile;->Companion:Lorg/nameless/gamespace/widget/tiles/NotificationTile$Companion;

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;)V
    .locals 2

    const-string v0, "context"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    const/4 v0, 0x0

    const/4 v1, 0x2

    invoke-direct {p0, p1, v0, v1, v0}, Lorg/nameless/gamespace/widget/tiles/NotificationTile;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 1

    const-string v0, "context"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 26
    invoke-direct {p0, p1, p2}, Lorg/nameless/gamespace/widget/tiles/BaseTile;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    const/4 p1, 0x2

    .line 28
    iput p1, p0, Lorg/nameless/gamespace/widget/tiles/NotificationTile;->activeMode:I

    return-void
.end method

.method public synthetic constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;ILkotlin/jvm/internal/DefaultConstructorMarker;)V
    .locals 0

    and-int/lit8 p3, p3, 0x2

    if-eqz p3, :cond_0

    const/4 p2, 0x0

    .line 24
    :cond_0
    invoke-direct {p0, p1, p2}, Lorg/nameless/gamespace/widget/tiles/NotificationTile;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    return-void
.end method

.method private final setActiveMode(I)V
    .locals 5

    .line 30
    iput p1, p0, Lorg/nameless/gamespace/widget/tiles/NotificationTile;->activeMode:I

    .line 31
    invoke-virtual {p0}, Lorg/nameless/gamespace/widget/tiles/BaseTile;->getAppSettings()Lorg/nameless/gamespace/data/AppSettings;

    move-result-object v0

    invoke-virtual {v0, p1}, Lorg/nameless/gamespace/data/AppSettings;->setNotificationsMode(I)V

    const/4 v0, 0x0

    const/4 v1, 0x1

    if-eqz p1, :cond_4

    if-eq p1, v1, :cond_2

    const/4 v2, 0x2

    if-eq p1, v2, :cond_0

    goto :goto_0

    .line 42
    :cond_0
    invoke-virtual {p0}, Lorg/nameless/gamespace/widget/tiles/BaseTile;->getSystemSettings()Lorg/nameless/gamespace/data/SystemSettings;

    move-result-object v2

    invoke-virtual {v2, v0}, Lorg/nameless/gamespace/data/SystemSettings;->setHeadsUp(Z)V

    .line 43
    invoke-virtual {p0}, Lorg/nameless/gamespace/widget/tiles/BaseTile;->getSummary()Landroid/widget/TextView;

    move-result-object v2

    if-nez v2, :cond_1

    goto :goto_0

    :cond_1
    invoke-virtual {p0}, Landroid/widget/LinearLayout;->getContext()Landroid/content/Context;

    move-result-object v3

    const v4, 0x7f120243

    invoke-virtual {v3, v4}, Landroid/content/Context;->getString(I)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v2, v3}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    goto :goto_0

    .line 38
    :cond_2
    invoke-virtual {p0}, Lorg/nameless/gamespace/widget/tiles/BaseTile;->getSystemSettings()Lorg/nameless/gamespace/data/SystemSettings;

    move-result-object v2

    invoke-virtual {v2, v1}, Lorg/nameless/gamespace/data/SystemSettings;->setHeadsUp(Z)V

    .line 39
    invoke-virtual {p0}, Lorg/nameless/gamespace/widget/tiles/BaseTile;->getSummary()Landroid/widget/TextView;

    move-result-object v2

    if-nez v2, :cond_3

    goto :goto_0

    :cond_3
    invoke-virtual {p0}, Landroid/widget/LinearLayout;->getContext()Landroid/content/Context;

    move-result-object v3

    const v4, 0x7f120244

    invoke-virtual {v3, v4}, Landroid/content/Context;->getString(I)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v2, v3}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    goto :goto_0

    .line 34
    :cond_4
    invoke-virtual {p0}, Lorg/nameless/gamespace/widget/tiles/BaseTile;->getSystemSettings()Lorg/nameless/gamespace/data/SystemSettings;

    move-result-object v2

    invoke-virtual {v2, v0}, Lorg/nameless/gamespace/data/SystemSettings;->setHeadsUp(Z)V

    .line 35
    invoke-virtual {p0}, Lorg/nameless/gamespace/widget/tiles/BaseTile;->getSummary()Landroid/widget/TextView;

    move-result-object v2

    if-nez v2, :cond_5

    goto :goto_0

    :cond_5
    invoke-virtual {p0}, Landroid/widget/LinearLayout;->getContext()Landroid/content/Context;

    move-result-object v3

    const v4, 0x7f120245

    invoke-virtual {v3, v4}, Landroid/content/Context;->getString(I)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v2, v3}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    :goto_0
    if-eqz p1, :cond_6

    move v0, v1

    .line 46
    :cond_6
    invoke-virtual {p0, v0}, Landroid/widget/LinearLayout;->setSelected(Z)V

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

    const v2, 0x7f120246

    invoke-virtual {v1, v2}, Landroid/content/Context;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 52
    :goto_0
    invoke-virtual {p0}, Lorg/nameless/gamespace/widget/tiles/BaseTile;->getAppSettings()Lorg/nameless/gamespace/data/AppSettings;

    move-result-object v0

    invoke-virtual {v0}, Lorg/nameless/gamespace/data/AppSettings;->getNotificationsMode()I

    move-result v0

    invoke-direct {p0, v0}, Lorg/nameless/gamespace/widget/tiles/NotificationTile;->setActiveMode(I)V

    .line 53
    invoke-virtual {p0}, Lorg/nameless/gamespace/widget/tiles/BaseTile;->getIcon()Landroid/widget/ImageView;

    move-result-object p0

    if-eqz p0, :cond_1

    const v0, 0x7f08009f

    invoke-virtual {p0, v0}, Landroid/widget/ImageView;->setImageResource(I)V

    :cond_1
    return-void
.end method

.method public onClick(Landroid/view/View;)V
    .locals 1

    .line 57
    invoke-super {p0, p1}, Lorg/nameless/gamespace/widget/tiles/BaseTile;->onClick(Landroid/view/View;)V

    .line 58
    iget p1, p0, Lorg/nameless/gamespace/widget/tiles/NotificationTile;->activeMode:I

    const/4 v0, 0x2

    if-ne p1, v0, :cond_0

    const/4 p1, 0x0

    goto :goto_0

    :cond_0
    add-int/lit8 p1, p1, 0x1

    :goto_0
    invoke-direct {p0, p1}, Lorg/nameless/gamespace/widget/tiles/NotificationTile;->setActiveMode(I)V

    return-void
.end method
