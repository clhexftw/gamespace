.class public abstract Lorg/nameless/gamespace/widget/tiles/BaseTile;
.super Landroid/widget/LinearLayout;
.source "BaseTile.kt"

# interfaces
.implements Landroid/view/View$OnClickListener;


# instance fields
.field private final appSettings$delegate:Lkotlin/Lazy;

.field private final clickEffect:Landroid/os/VibrationEffect;

.field private final systemSettings$delegate:Lkotlin/Lazy;

.field private final vibrator:Landroid/os/Vibrator;


# direct methods
.method public constructor <init>(Landroid/content/Context;)V
    .locals 2

    const-string v0, "context"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    const/4 v0, 0x0

    const/4 v1, 0x2

    invoke-direct {p0, p1, v0, v1, v0}, Lorg/nameless/gamespace/widget/tiles/BaseTile;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 1

    const-string v0, "context"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 34
    invoke-direct {p0, p1, p2}, Landroid/widget/LinearLayout;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    const/4 p2, 0x1

    .line 36
    invoke-virtual {p0, p2}, Landroid/widget/LinearLayout;->setClickable(Z)V

    .line 37
    invoke-virtual {p0, p2}, Landroid/widget/LinearLayout;->setFocusable(Z)V

    .line 38
    invoke-direct {p0}, Lorg/nameless/gamespace/widget/tiles/BaseTile;->prepareLayout()V

    .line 41
    new-instance p2, Lorg/nameless/gamespace/widget/tiles/BaseTile$appSettings$2;

    invoke-direct {p2, p1}, Lorg/nameless/gamespace/widget/tiles/BaseTile$appSettings$2;-><init>(Landroid/content/Context;)V

    invoke-static {p2}, Lkotlin/LazyKt;->lazy(Lkotlin/jvm/functions/Function0;)Lkotlin/Lazy;

    move-result-object p2

    iput-object p2, p0, Lorg/nameless/gamespace/widget/tiles/BaseTile;->appSettings$delegate:Lkotlin/Lazy;

    .line 42
    new-instance p2, Lorg/nameless/gamespace/widget/tiles/BaseTile$systemSettings$2;

    invoke-direct {p2, p1}, Lorg/nameless/gamespace/widget/tiles/BaseTile$systemSettings$2;-><init>(Landroid/content/Context;)V

    invoke-static {p2}, Lkotlin/LazyKt;->lazy(Lkotlin/jvm/functions/Function0;)Lkotlin/Lazy;

    move-result-object p2

    iput-object p2, p0, Lorg/nameless/gamespace/widget/tiles/BaseTile;->systemSettings$delegate:Lkotlin/Lazy;

    const-string p2, "vibrator"

    .line 44
    invoke-virtual {p1, p2}, Landroid/content/Context;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object p1

    const-string p2, "null cannot be cast to non-null type android.os.Vibrator"

    invoke-static {p1, p2}, Lkotlin/jvm/internal/Intrinsics;->checkNotNull(Ljava/lang/Object;Ljava/lang/String;)V

    check-cast p1, Landroid/os/Vibrator;

    iput-object p1, p0, Lorg/nameless/gamespace/widget/tiles/BaseTile;->vibrator:Landroid/os/Vibrator;

    const/4 p1, 0x0

    .line 45
    invoke-static {p1}, Landroid/os/VibrationEffect;->createPredefined(I)Landroid/os/VibrationEffect;

    move-result-object p1

    iput-object p1, p0, Lorg/nameless/gamespace/widget/tiles/BaseTile;->clickEffect:Landroid/os/VibrationEffect;

    return-void
.end method

.method public synthetic constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;ILkotlin/jvm/internal/DefaultConstructorMarker;)V
    .locals 0

    and-int/lit8 p3, p3, 0x2

    if-eqz p3, :cond_0

    const/4 p2, 0x0

    .line 32
    :cond_0
    invoke-direct {p0, p1, p2}, Lorg/nameless/gamespace/widget/tiles/BaseTile;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    return-void
.end method

.method private final prepareLayout()V
    .locals 3

    .line 57
    invoke-virtual {p0}, Landroid/widget/LinearLayout;->getContext()Landroid/content/Context;

    move-result-object v0

    invoke-static {v0}, Landroid/view/LayoutInflater;->from(Landroid/content/Context;)Landroid/view/LayoutInflater;

    move-result-object v0

    const v1, 0x7f0d008e

    const/4 v2, 0x1

    .line 58
    invoke-virtual {v0, v1, p0, v2}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;Z)Landroid/view/View;

    .line 59
    invoke-virtual {p0, p0}, Landroid/widget/LinearLayout;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    return-void
.end method


# virtual methods
.method public final getAppSettings()Lorg/nameless/gamespace/data/AppSettings;
    .locals 0

    .line 41
    iget-object p0, p0, Lorg/nameless/gamespace/widget/tiles/BaseTile;->appSettings$delegate:Lkotlin/Lazy;

    invoke-interface {p0}, Lkotlin/Lazy;->getValue()Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lorg/nameless/gamespace/data/AppSettings;

    return-object p0
.end method

.method public final getIcon()Landroid/widget/ImageView;
    .locals 1

    const v0, 0x7f0a02b0

    .line 54
    invoke-virtual {p0, v0}, Landroid/widget/LinearLayout;->findViewById(I)Landroid/view/View;

    move-result-object p0

    check-cast p0, Landroid/widget/ImageView;

    return-object p0
.end method

.method public final getSummary()Landroid/widget/TextView;
    .locals 1

    const v0, 0x7f0a02b1

    .line 51
    invoke-virtual {p0, v0}, Landroid/widget/LinearLayout;->findViewById(I)Landroid/view/View;

    move-result-object p0

    check-cast p0, Landroid/widget/TextView;

    return-object p0
.end method

.method public final getSystemSettings()Lorg/nameless/gamespace/data/SystemSettings;
    .locals 0

    .line 42
    iget-object p0, p0, Lorg/nameless/gamespace/widget/tiles/BaseTile;->systemSettings$delegate:Lkotlin/Lazy;

    invoke-interface {p0}, Lkotlin/Lazy;->getValue()Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lorg/nameless/gamespace/data/SystemSettings;

    return-object p0
.end method

.method public final getTitle()Landroid/widget/TextView;
    .locals 1

    const v0, 0x7f0a02b2

    .line 48
    invoke-virtual {p0, v0}, Landroid/widget/LinearLayout;->findViewById(I)Landroid/view/View;

    move-result-object p0

    check-cast p0, Landroid/widget/TextView;

    return-object p0
.end method

.method public onClick(Landroid/view/View;)V
    .locals 1

    .line 63
    invoke-virtual {p0}, Landroid/widget/LinearLayout;->isSelected()Z

    move-result p1

    xor-int/lit8 p1, p1, 0x1

    invoke-virtual {p0, p1}, Landroid/widget/LinearLayout;->setSelected(Z)V

    .line 64
    iget-object p1, p0, Lorg/nameless/gamespace/widget/tiles/BaseTile;->vibrator:Landroid/os/Vibrator;

    iget-object p0, p0, Lorg/nameless/gamespace/widget/tiles/BaseTile;->clickEffect:Landroid/os/VibrationEffect;

    sget-object v0, Lorg/nameless/vibrator/CustomVibrationAttributes;->VIBRATION_ATTRIBUTES_QS_TILE:Landroid/os/VibrationAttributes;

    invoke-virtual {p1, p0, v0}, Landroid/os/Vibrator;->vibrate(Landroid/os/VibrationEffect;Landroid/os/VibrationAttributes;)V

    return-void
.end method
