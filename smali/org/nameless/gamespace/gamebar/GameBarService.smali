.class public final Lorg/nameless/gamespace/gamebar/GameBarService;
.super Lorg/nameless/gamespace/gamebar/Hilt_GameBarService;
.source "GameBarService.kt"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lorg/nameless/gamespace/gamebar/GameBarService$GameBarBinder;,
        Lorg/nameless/gamespace/gamebar/GameBarService$ClosePanelReceiver;,
        Lorg/nameless/gamespace/gamebar/GameBarService$Companion;
    }
.end annotation

.annotation system Ldalvik/annotation/SourceDebugExtension;
    value = "SMAP\nGameBarService.kt\nKotlin\n*S Kotlin\n*F\n+ 1 GameBarService.kt\norg/nameless/gamespace/gamebar/GameBarService\n+ 2 _Sequences.kt\nkotlin/sequences/SequencesKt___SequencesKt\n+ 3 View.kt\nandroidx/core/view/ViewKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 5 ViewGroup.kt\nandroidx/core/view/ViewGroupKt\n*L\n1#1,423:1\n1291#2:424\n1292#2:427\n254#3,2:425\n252#3:428\n254#3,2:429\n254#3,2:431\n160#3,2:434\n160#3,2:444\n371#3,2:454\n254#3,2:456\n1#4:433\n141#5,8:436\n141#5,8:446\n*S KotlinDebug\n*F\n+ 1 GameBarService.kt\norg/nameless/gamespace/gamebar/GameBarService\n*L\n116#1:424\n116#1:427\n118#1:425,2\n184#1:428\n198#1:429,2\n239#1:431,2\n271#1:434,2\n275#1:444,2\n310#1:454,2\n387#1:456,2\n273#1:436,8\n277#1:446,8\n*E\n"
.end annotation


# static fields
.field public static final Companion:Lorg/nameless/gamespace/gamebar/GameBarService$Companion;


# instance fields
.field public appSettings:Lorg/nameless/gamespace/data/AppSettings;

.field private barExpanded:Z

.field private final barLayoutParam:Landroid/view/WindowManager$LayoutParams;

.field private barView:Landroid/widget/LinearLayout;

.field private final binder:Lorg/nameless/gamespace/gamebar/GameBarService$GameBarBinder;

.field public danmakuService:Lorg/nameless/gamespace/gamebar/DanmakuService;

.field private final firstPaint:Ljava/lang/Runnable;

.field private final handler$delegate:Lkotlin/Lazy;

.field private menuSwitcher:Lorg/nameless/gamespace/widget/MenuSwitcher;

.field private final panelLayoutParam:Landroid/view/WindowManager$LayoutParams;

.field private panelView:Lorg/nameless/gamespace/widget/PanelView;

.field private final receiver:Lorg/nameless/gamespace/gamebar/GameBarService$ClosePanelReceiver;

.field private rootBarView:Landroid/view/View;

.field private rootPanelView:Landroid/widget/LinearLayout;

.field public screenUtils:Lorg/nameless/gamespace/utils/ScreenUtils;

.field private showPanel:Z

.field private final wm$delegate:Lkotlin/Lazy;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    new-instance v0, Lorg/nameless/gamespace/gamebar/GameBarService$Companion;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lorg/nameless/gamespace/gamebar/GameBarService$Companion;-><init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V

    sput-object v0, Lorg/nameless/gamespace/gamebar/GameBarService;->Companion:Lorg/nameless/gamespace/gamebar/GameBarService$Companion;

    return-void
.end method

.method public constructor <init>()V
    .locals 5

    .line 58
    invoke-direct {p0}, Lorg/nameless/gamespace/gamebar/Hilt_GameBarService;-><init>()V

    .line 68
    new-instance v0, Lorg/nameless/gamespace/gamebar/GameBarService$wm$2;

    invoke-direct {v0, p0}, Lorg/nameless/gamespace/gamebar/GameBarService$wm$2;-><init>(Lorg/nameless/gamespace/gamebar/GameBarService;)V

    invoke-static {v0}, Lkotlin/LazyKt;->lazy(Lkotlin/jvm/functions/Function0;)Lkotlin/Lazy;

    move-result-object v0

    iput-object v0, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->wm$delegate:Lkotlin/Lazy;

    .line 69
    sget-object v0, Lorg/nameless/gamespace/gamebar/GameBarService$handler$2;->INSTANCE:Lorg/nameless/gamespace/gamebar/GameBarService$handler$2;

    invoke-static {v0}, Lkotlin/LazyKt;->lazy(Lkotlin/jvm/functions/Function0;)Lkotlin/Lazy;

    move-result-object v0

    iput-object v0, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->handler$delegate:Lkotlin/Lazy;

    .line 72
    new-instance v0, Landroid/view/WindowManager$LayoutParams;

    const/16 v1, 0x7d8

    const/16 v2, 0x128

    const/4 v3, -0x3

    invoke-direct {v0, v1, v2, v3}, Landroid/view/WindowManager$LayoutParams;-><init>(III)V

    const/4 v1, -0x2

    .line 79
    iput v1, v0, Landroid/view/WindowManager$LayoutParams;->width:I

    .line 80
    iput v1, v0, Landroid/view/WindowManager$LayoutParams;->height:I

    const/4 v1, 0x3

    .line 81
    iput v1, v0, Landroid/view/WindowManager$LayoutParams;->layoutInDisplayCutoutMode:I

    const/4 v2, 0x1

    .line 83
    iput-boolean v2, v0, Landroid/view/WindowManager$LayoutParams;->preferMinimalPostProcessing:Z

    const/16 v2, 0x30

    .line 84
    iput v2, v0, Landroid/view/WindowManager$LayoutParams;->gravity:I

    .line 78
    iput-object v0, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->barLayoutParam:Landroid/view/WindowManager$LayoutParams;

    .line 88
    new-instance v0, Landroid/view/WindowManager$LayoutParams;

    const/16 v2, 0x7f6

    const/16 v4, 0x2a

    invoke-direct {v0, v2, v4, v3}, Landroid/view/WindowManager$LayoutParams;-><init>(III)V

    const v2, 0x3f333333    # 0.7f

    .line 95
    iput v2, v0, Landroid/view/WindowManager$LayoutParams;->dimAmount:F

    const/4 v2, -0x1

    .line 96
    iput v2, v0, Landroid/view/WindowManager$LayoutParams;->width:I

    .line 97
    iput v2, v0, Landroid/view/WindowManager$LayoutParams;->height:I

    .line 98
    iput v1, v0, Landroid/view/WindowManager$LayoutParams;->layoutInDisplayCutoutMode:I

    const/16 v1, 0x10

    .line 100
    iput v1, v0, Landroid/view/WindowManager$LayoutParams;->gravity:I

    .line 94
    iput-object v0, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->panelLayoutParam:Landroid/view/WindowManager$LayoutParams;

    .line 109
    new-instance v0, Lorg/nameless/gamespace/gamebar/GameBarService$GameBarBinder;

    invoke-direct {v0, p0}, Lorg/nameless/gamespace/gamebar/GameBarService$GameBarBinder;-><init>(Lorg/nameless/gamespace/gamebar/GameBarService;)V

    iput-object v0, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->binder:Lorg/nameless/gamespace/gamebar/GameBarService$GameBarBinder;

    .line 110
    new-instance v0, Lorg/nameless/gamespace/gamebar/GameBarService$ClosePanelReceiver;

    invoke-direct {v0, p0}, Lorg/nameless/gamespace/gamebar/GameBarService$ClosePanelReceiver;-><init>(Lorg/nameless/gamespace/gamebar/GameBarService;)V

    iput-object v0, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->receiver:Lorg/nameless/gamespace/gamebar/GameBarService$ClosePanelReceiver;

    .line 111
    new-instance v0, Lorg/nameless/gamespace/gamebar/GameBarService$firstPaint$1;

    invoke-direct {v0, p0}, Lorg/nameless/gamespace/gamebar/GameBarService$firstPaint$1;-><init>(Lorg/nameless/gamespace/gamebar/GameBarService;)V

    iput-object v0, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->firstPaint:Ljava/lang/Runnable;

    return-void
.end method

.method public static final synthetic access$dockCollapsedMenu(Lorg/nameless/gamespace/gamebar/GameBarService;)V
    .locals 0

    .line 57
    invoke-direct {p0}, Lorg/nameless/gamespace/gamebar/GameBarService;->dockCollapsedMenu()V

    return-void
.end method

.method public static final synthetic access$getBarExpanded$p(Lorg/nameless/gamespace/gamebar/GameBarService;)Z
    .locals 0

    .line 57
    iget-boolean p0, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->barExpanded:Z

    return p0
.end method

.method public static final synthetic access$getBarLayoutParam$p(Lorg/nameless/gamespace/gamebar/GameBarService;)Landroid/view/WindowManager$LayoutParams;
    .locals 0

    .line 57
    iget-object p0, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->barLayoutParam:Landroid/view/WindowManager$LayoutParams;

    return-object p0
.end method

.method public static final synthetic access$getBarView$p(Lorg/nameless/gamespace/gamebar/GameBarService;)Landroid/widget/LinearLayout;
    .locals 0

    .line 57
    iget-object p0, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->barView:Landroid/widget/LinearLayout;

    return-object p0
.end method

.method public static final synthetic access$getFirstPaint$p(Lorg/nameless/gamespace/gamebar/GameBarService;)Ljava/lang/Runnable;
    .locals 0

    .line 57
    iget-object p0, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->firstPaint:Ljava/lang/Runnable;

    return-object p0
.end method

.method public static final synthetic access$getHandler(Lorg/nameless/gamespace/gamebar/GameBarService;)Landroid/os/Handler;
    .locals 0

    .line 57
    invoke-direct {p0}, Lorg/nameless/gamespace/gamebar/GameBarService;->getHandler()Landroid/os/Handler;

    move-result-object p0

    return-object p0
.end method

.method public static final synthetic access$getMenuSwitcher$p(Lorg/nameless/gamespace/gamebar/GameBarService;)Lorg/nameless/gamespace/widget/MenuSwitcher;
    .locals 0

    .line 57
    iget-object p0, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->menuSwitcher:Lorg/nameless/gamespace/widget/MenuSwitcher;

    return-object p0
.end method

.method public static final synthetic access$getShowPanel$p(Lorg/nameless/gamespace/gamebar/GameBarService;)Z
    .locals 0

    .line 57
    iget-boolean p0, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->showPanel:Z

    return p0
.end method

.method public static final synthetic access$initActions(Lorg/nameless/gamespace/gamebar/GameBarService;)V
    .locals 0

    .line 57
    invoke-direct {p0}, Lorg/nameless/gamespace/gamebar/GameBarService;->initActions()V

    return-void
.end method

.method public static final synthetic access$setBarExpanded(Lorg/nameless/gamespace/gamebar/GameBarService;Z)V
    .locals 0

    .line 57
    invoke-direct {p0, p1}, Lorg/nameless/gamespace/gamebar/GameBarService;->setBarExpanded(Z)V

    return-void
.end method

.method public static final synthetic access$setShowPanel(Lorg/nameless/gamespace/gamebar/GameBarService;Z)V
    .locals 0

    .line 57
    invoke-direct {p0, p1}, Lorg/nameless/gamespace/gamebar/GameBarService;->setShowPanel(Z)V

    return-void
.end method

.method public static final synthetic access$takeShot(Lorg/nameless/gamespace/gamebar/GameBarService;)V
    .locals 0

    .line 57
    invoke-direct {p0}, Lorg/nameless/gamespace/gamebar/GameBarService;->takeShot()V

    return-void
.end method

.method public static final synthetic access$updateBackground(Lorg/nameless/gamespace/gamebar/GameBarService;)V
    .locals 0

    .line 57
    invoke-direct {p0}, Lorg/nameless/gamespace/gamebar/GameBarService;->updateBackground()V

    return-void
.end method

.method public static final synthetic access$updateLayout(Lorg/nameless/gamespace/gamebar/GameBarService;Lkotlin/jvm/functions/Function1;)V
    .locals 0

    .line 57
    invoke-direct {p0, p1}, Lorg/nameless/gamespace/gamebar/GameBarService;->updateLayout(Lkotlin/jvm/functions/Function1;)V

    return-void
.end method

.method private final dockCollapsedMenu()V
    .locals 5

    .line 282
    invoke-direct {p0}, Lorg/nameless/gamespace/gamebar/GameBarService;->getWm()Landroid/view/WindowManager;

    move-result-object v0

    invoke-interface {v0}, Landroid/view/WindowManager;->getMaximumWindowMetrics()Landroid/view/WindowMetrics;

    move-result-object v0

    invoke-virtual {v0}, Landroid/view/WindowMetrics;->getBounds()Landroid/graphics/Rect;

    move-result-object v0

    invoke-virtual {v0}, Landroid/graphics/Rect;->width()I

    move-result v0

    div-int/lit8 v0, v0, 0x2

    .line 283
    iget-object v1, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->barLayoutParam:Landroid/view/WindowManager$LayoutParams;

    iget v1, v1, Landroid/view/WindowManager$LayoutParams;->x:I

    const-string v2, "barView"

    const/4 v3, 0x0

    if-gez v1, :cond_1

    .line 284
    iget-object v1, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->barView:Landroid/widget/LinearLayout;

    if-nez v1, :cond_0

    invoke-static {v2}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    move-object v1, v3

    :cond_0
    const/high16 v2, -0x3e500000    # -22.0f

    invoke-virtual {v1, v2}, Landroid/widget/LinearLayout;->setTranslationX(F)V

    .line 285
    iget-object v1, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->barLayoutParam:Landroid/view/WindowManager$LayoutParams;

    neg-int v0, v0

    iput v0, v1, Landroid/view/WindowManager$LayoutParams;->x:I

    goto :goto_0

    .line 287
    :cond_1
    iget-object v1, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->barView:Landroid/widget/LinearLayout;

    if-nez v1, :cond_2

    invoke-static {v2}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    move-object v1, v3

    :cond_2
    const/high16 v2, 0x41b00000    # 22.0f

    invoke-virtual {v1, v2}, Landroid/widget/LinearLayout;->setTranslationX(F)V

    .line 288
    iget-object v1, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->barLayoutParam:Landroid/view/WindowManager$LayoutParams;

    iput v0, v1, Landroid/view/WindowManager$LayoutParams;->x:I

    .line 291
    :goto_0
    invoke-static {p0}, Lorg/nameless/gamespace/utils/ExtensionsKt;->getStatusbarHeight(Landroid/content/Context;)I

    move-result v0

    const/4 v1, 0x4

    invoke-static {v1}, Lorg/nameless/gamespace/utils/ExtensionsKt;->getDp(I)I

    move-result v1

    add-int/2addr v0, v1

    .line 292
    invoke-direct {p0}, Lorg/nameless/gamespace/gamebar/GameBarService;->getWm()Landroid/view/WindowManager;

    move-result-object v1

    invoke-interface {v1}, Landroid/view/WindowManager;->getMaximumWindowMetrics()Landroid/view/WindowMetrics;

    move-result-object v1

    invoke-virtual {v1}, Landroid/view/WindowMetrics;->getBounds()Landroid/graphics/Rect;

    move-result-object v1

    invoke-virtual {v1}, Landroid/graphics/Rect;->height()I

    move-result v1

    sub-int/2addr v1, v0

    .line 293
    iget-object v2, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->barLayoutParam:Landroid/view/WindowManager$LayoutParams;

    iget v4, v2, Landroid/view/WindowManager$LayoutParams;->y:I

    invoke-static {v4, v0, v1}, Lkotlin/ranges/RangesKt;->coerceIn(III)I

    move-result v0

    iput v0, v2, Landroid/view/WindowManager$LayoutParams;->y:I

    .line 295
    invoke-direct {p0}, Lorg/nameless/gamespace/gamebar/GameBarService;->updateBackground()V

    .line 296
    invoke-direct {p0}, Lorg/nameless/gamespace/gamebar/GameBarService;->updateContainerGaps()V

    .line 297
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->menuSwitcher:Lorg/nameless/gamespace/widget/MenuSwitcher;

    const-string v1, "menuSwitcher"

    if-nez v0, :cond_3

    invoke-static {v1}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    move-object v0, v3

    :cond_3
    iget-boolean v2, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->barExpanded:Z

    if-eqz v2, :cond_4

    const/4 v2, 0x0

    goto :goto_1

    :cond_4
    invoke-virtual {p0}, Lorg/nameless/gamespace/gamebar/GameBarService;->getAppSettings()Lorg/nameless/gamespace/data/AppSettings;

    move-result-object v2

    invoke-virtual {v2}, Lorg/nameless/gamespace/data/AppSettings;->getShowFps()Z

    move-result v2

    :goto_1
    invoke-virtual {v0, v2}, Lorg/nameless/gamespace/widget/MenuSwitcher;->setShowFps(Z)V

    .line 298
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->menuSwitcher:Lorg/nameless/gamespace/widget/MenuSwitcher;

    if-nez v0, :cond_5

    invoke-static {v1}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    goto :goto_2

    :cond_5
    move-object v3, v0

    :goto_2
    iget-boolean v0, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->barExpanded:Z

    iget-object v1, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->barLayoutParam:Landroid/view/WindowManager$LayoutParams;

    iget v1, v1, Landroid/view/WindowManager$LayoutParams;->x:I

    invoke-virtual {v3, v0, v1}, Lorg/nameless/gamespace/widget/MenuSwitcher;->updateIconState(ZI)V

    .line 299
    invoke-direct {p0}, Lorg/nameless/gamespace/gamebar/GameBarService;->updateRootBarView()V

    return-void
.end method

.method private final getHandler()Landroid/os/Handler;
    .locals 0

    .line 69
    iget-object p0, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->handler$delegate:Lkotlin/Lazy;

    invoke-interface {p0}, Lkotlin/Lazy;->getValue()Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Landroid/os/Handler;

    return-object p0
.end method

.method private final getWm()Landroid/view/WindowManager;
    .locals 0

    .line 68
    iget-object p0, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->wm$delegate:Lkotlin/Lazy;

    invoke-interface {p0}, Lkotlin/Lazy;->getValue()Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Landroid/view/WindowManager;

    return-object p0
.end method

.method private final initActions()V
    .locals 4

    .line 239
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->rootBarView:Landroid/view/View;

    const/4 v1, 0x0

    const-string v2, "rootBarView"

    if-nez v0, :cond_0

    invoke-static {v2}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    move-object v0, v1

    :cond_0
    const/4 v3, 0x0

    .line 254
    invoke-virtual {v0, v3}, Landroid/view/View;->setVisibility(I)V

    .line 240
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->rootBarView:Landroid/view/View;

    if-nez v0, :cond_1

    invoke-static {v2}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    goto :goto_0

    :cond_1
    move-object v1, v0

    :goto_0
    invoke-virtual {v1}, Landroid/view/View;->animate()Landroid/view/ViewPropertyAnimator;

    move-result-object v0

    const/high16 v1, 0x3f800000    # 1.0f

    .line 241
    invoke-virtual {v0, v1}, Landroid/view/ViewPropertyAnimator;->alpha(F)Landroid/view/ViewPropertyAnimator;

    move-result-object v0

    const-wide/16 v1, 0x12c

    .line 242
    invoke-virtual {v0, v1, v2}, Landroid/view/ViewPropertyAnimator;->setDuration(J)Landroid/view/ViewPropertyAnimator;

    .line 243
    invoke-virtual {v0}, Landroid/view/ViewPropertyAnimator;->start()V

    .line 244
    invoke-direct {p0, v3}, Lorg/nameless/gamespace/gamebar/GameBarService;->setBarExpanded(Z)V

    .line 245
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->barLayoutParam:Landroid/view/WindowManager$LayoutParams;

    invoke-virtual {p0}, Lorg/nameless/gamespace/gamebar/GameBarService;->getAppSettings()Lorg/nameless/gamespace/data/AppSettings;

    move-result-object v1

    invoke-virtual {v1}, Lorg/nameless/gamespace/data/AppSettings;->getX()I

    move-result v1

    iput v1, v0, Landroid/view/WindowManager$LayoutParams;->x:I

    .line 246
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->barLayoutParam:Landroid/view/WindowManager$LayoutParams;

    invoke-virtual {p0}, Lorg/nameless/gamespace/gamebar/GameBarService;->getAppSettings()Lorg/nameless/gamespace/data/AppSettings;

    move-result-object v1

    invoke-virtual {v1}, Lorg/nameless/gamespace/data/AppSettings;->getY()I

    move-result v1

    iput v1, v0, Landroid/view/WindowManager$LayoutParams;->y:I

    .line 247
    invoke-direct {p0}, Lorg/nameless/gamespace/gamebar/GameBarService;->dockCollapsedMenu()V

    .line 249
    invoke-direct {p0}, Lorg/nameless/gamespace/gamebar/GameBarService;->menuSwitcherButton()V

    .line 250
    invoke-direct {p0}, Lorg/nameless/gamespace/gamebar/GameBarService;->panelButton()V

    .line 251
    invoke-direct {p0}, Lorg/nameless/gamespace/gamebar/GameBarService;->screenshotButton()V

    .line 252
    invoke-direct {p0}, Lorg/nameless/gamespace/gamebar/GameBarService;->recorderButton()V

    return-void
.end method

.method private final menuSwitcherButton()V
    .locals 4

    .line 341
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->menuSwitcher:Lorg/nameless/gamespace/widget/MenuSwitcher;

    const/4 v1, 0x0

    const-string v2, "menuSwitcher"

    if-nez v0, :cond_0

    invoke-static {v2}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    move-object v0, v1

    :cond_0
    new-instance v3, Lorg/nameless/gamespace/gamebar/GameBarService$menuSwitcherButton$1;

    invoke-direct {v3, p0}, Lorg/nameless/gamespace/gamebar/GameBarService$menuSwitcherButton$1;-><init>(Lorg/nameless/gamespace/gamebar/GameBarService;)V

    invoke-virtual {v0, v3}, Landroid/widget/LinearLayout;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 344
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->menuSwitcher:Lorg/nameless/gamespace/widget/MenuSwitcher;

    if-nez v0, :cond_1

    invoke-static {v2}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    goto :goto_0

    :cond_1
    move-object v1, v0

    :goto_0
    new-instance v0, Lorg/nameless/gamespace/gamebar/GameBarService$menuSwitcherButton$2;

    invoke-direct {v0, p0}, Lorg/nameless/gamespace/gamebar/GameBarService$menuSwitcherButton$2;-><init>(Lorg/nameless/gamespace/gamebar/GameBarService;)V

    new-instance v2, Lorg/nameless/gamespace/gamebar/GameBarService$menuSwitcherButton$3;

    invoke-direct {v2, p0}, Lorg/nameless/gamespace/gamebar/GameBarService$menuSwitcherButton$3;-><init>(Lorg/nameless/gamespace/gamebar/GameBarService;)V

    new-instance v3, Lorg/nameless/gamespace/gamebar/GameBarService$menuSwitcherButton$4;

    invoke-direct {v3, p0}, Lorg/nameless/gamespace/gamebar/GameBarService$menuSwitcherButton$4;-><init>(Lorg/nameless/gamespace/gamebar/GameBarService;)V

    invoke-static {v1, v0, v2, v3}, Lorg/nameless/gamespace/utils/ExtensionsKt;->registerDraggableTouchListener(Landroid/view/View;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function0;)Lorg/nameless/gamespace/gamebar/DraggableTouchListener;

    return-void
.end method

.method private final panelButton()V
    .locals 2

    .line 368
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->rootBarView:Landroid/view/View;

    if-nez v0, :cond_0

    const-string v0, "rootBarView"

    invoke-static {v0}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    const/4 v0, 0x0

    :cond_0
    const v1, 0x7f0a0045

    invoke-virtual {v0, v1}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/ImageButton;

    .line 369
    new-instance v1, Lorg/nameless/gamespace/gamebar/GameBarService$panelButton$1;

    invoke-direct {v1, p0}, Lorg/nameless/gamespace/gamebar/GameBarService$panelButton$1;-><init>(Lorg/nameless/gamespace/gamebar/GameBarService;)V

    invoke-virtual {v0, v1}, Landroid/widget/ImageButton;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 372
    new-instance v1, Lorg/nameless/gamespace/gamebar/GameBarService$panelButton$2;

    invoke-direct {v1, p0}, Lorg/nameless/gamespace/gamebar/GameBarService$panelButton$2;-><init>(Lorg/nameless/gamespace/gamebar/GameBarService;)V

    invoke-virtual {v0, v1}, Landroid/widget/ImageButton;->setOnLongClickListener(Landroid/view/View$OnLongClickListener;)V

    return-void
.end method

.method private final recorderButton()V
    .locals 3

    .line 386
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->rootBarView:Landroid/view/View;

    if-nez v0, :cond_0

    const-string v0, "rootBarView"

    invoke-static {v0}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    const/4 v0, 0x0

    :cond_0
    const v1, 0x7f0a0046

    invoke-virtual {v0, v1}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/ImageButton;

    .line 387
    invoke-virtual {p0}, Lorg/nameless/gamespace/gamebar/GameBarService;->getScreenUtils()Lorg/nameless/gamespace/utils/ScreenUtils;

    move-result-object v1

    invoke-virtual {v1}, Lorg/nameless/gamespace/utils/ScreenUtils;->getRecorder()Lcom/android/systemui/screenrecord/IRemoteRecording;

    move-result-object v1

    if-nez v1, :cond_1

    const-string p0, "actionRecorder"

    invoke-static {v0, p0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullExpressionValue(Ljava/lang/Object;Ljava/lang/String;)V

    const/16 p0, 0x8

    .line 254
    invoke-virtual {v0, p0}, Landroid/view/View;->setVisibility(I)V

    return-void

    .line 388
    :cond_1
    new-instance v2, Lorg/nameless/gamespace/gamebar/GameBarService$recorderButton$1;

    invoke-direct {v2, p0, v0}, Lorg/nameless/gamespace/gamebar/GameBarService$recorderButton$1;-><init>(Lorg/nameless/gamespace/gamebar/GameBarService;Landroid/widget/ImageButton;)V

    invoke-interface {v1, v2}, Lcom/android/systemui/screenrecord/IRemoteRecording;->addRecordingCallback(Lcom/android/systemui/screenrecord/IRecordingCallback;)V

    .line 401
    new-instance v2, Lorg/nameless/gamespace/gamebar/GameBarService$recorderButton$2;

    invoke-direct {v2, v1, p0}, Lorg/nameless/gamespace/gamebar/GameBarService$recorderButton$2;-><init>(Lcom/android/systemui/screenrecord/IRemoteRecording;Lorg/nameless/gamespace/gamebar/GameBarService;)V

    invoke-virtual {v0, v2}, Landroid/widget/ImageButton;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    return-void
.end method

.method private final screenshotButton()V
    .locals 2

    .line 379
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->rootBarView:Landroid/view/View;

    if-nez v0, :cond_0

    const-string v0, "rootBarView"

    invoke-static {v0}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    const/4 v0, 0x0

    :cond_0
    const v1, 0x7f0a0047

    invoke-virtual {v0, v1}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/ImageButton;

    .line 380
    new-instance v1, Lorg/nameless/gamespace/gamebar/GameBarService$screenshotButton$1;

    invoke-direct {v1, p0}, Lorg/nameless/gamespace/gamebar/GameBarService$screenshotButton$1;-><init>(Lorg/nameless/gamespace/gamebar/GameBarService;)V

    invoke-virtual {v0, v1}, Landroid/widget/ImageButton;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    return-void
.end method

.method private final setBarExpanded(Z)V
    .locals 4

    .line 114
    iput-boolean p1, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->barExpanded:Z

    .line 115
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->menuSwitcher:Lorg/nameless/gamespace/widget/MenuSwitcher;

    const/4 v1, 0x0

    if-nez v0, :cond_0

    const-string v0, "menuSwitcher"

    invoke-static {v0}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    move-object v0, v1

    :cond_0
    iget-object v2, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->barLayoutParam:Landroid/view/WindowManager$LayoutParams;

    iget v2, v2, Landroid/view/WindowManager$LayoutParams;->x:I

    invoke-virtual {v0, p1, v2}, Lorg/nameless/gamespace/widget/MenuSwitcher;->updateIconState(ZI)V

    .line 116
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->barView:Landroid/widget/LinearLayout;

    if-nez v0, :cond_1

    const-string v0, "barView"

    invoke-static {v0}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    goto :goto_0

    :cond_1
    move-object v1, v0

    :goto_0
    invoke-static {v1}, Landroidx/core/view/ViewGroupKt;->getChildren(Landroid/view/ViewGroup;)Lkotlin/sequences/Sequence;

    move-result-object v0

    .line 1291
    invoke-interface {v0}, Lkotlin/sequences/Sequence;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :cond_2
    :goto_1
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_4

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Landroid/view/View;

    .line 117
    invoke-virtual {v1}, Landroid/view/View;->getId()I

    move-result v2

    const v3, 0x7f0a0041

    if-eq v2, v3, :cond_2

    if-eqz p1, :cond_3

    const/4 v2, 0x0

    goto :goto_2

    :cond_3
    const/16 v2, 0x8

    .line 254
    :goto_2
    invoke-virtual {v1, v2}, Landroid/view/View;->setVisibility(I)V

    goto :goto_1

    .line 121
    :cond_4
    invoke-direct {p0}, Lorg/nameless/gamespace/gamebar/GameBarService;->updateBackground()V

    .line 122
    invoke-direct {p0}, Lorg/nameless/gamespace/gamebar/GameBarService;->updateContainerGaps()V

    return-void
.end method

.method private final setShowPanel(Z)V
    .locals 3

    .line 127
    iput-boolean p1, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->showPanel:Z

    const/4 v0, 0x0

    const-string v1, "rootPanelView"

    if-eqz p1, :cond_0

    .line 128
    iget-object v2, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->rootPanelView:Landroid/widget/LinearLayout;

    if-eqz v2, :cond_2

    :cond_0
    iget-object v2, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->rootPanelView:Landroid/widget/LinearLayout;

    if-nez v2, :cond_1

    invoke-static {v1}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    move-object v2, v0

    :cond_1
    invoke-virtual {v2}, Landroid/widget/LinearLayout;->isAttachedToWindow()Z

    move-result v2

    if-nez v2, :cond_4

    .line 129
    :cond_2
    invoke-direct {p0}, Lorg/nameless/gamespace/gamebar/GameBarService;->setupPanelView()V

    .line 130
    invoke-direct {p0}, Lorg/nameless/gamespace/gamebar/GameBarService;->getWm()Landroid/view/WindowManager;

    move-result-object p1

    iget-object v2, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->rootPanelView:Landroid/widget/LinearLayout;

    if-nez v2, :cond_3

    invoke-static {v1}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    goto :goto_0

    :cond_3
    move-object v0, v2

    :goto_0
    iget-object p0, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->panelLayoutParam:Landroid/view/WindowManager$LayoutParams;

    invoke-interface {p1, v0, p0}, Landroid/view/WindowManager;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    goto :goto_2

    :cond_4
    if-nez p1, :cond_7

    .line 131
    iget-object p1, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->rootPanelView:Landroid/widget/LinearLayout;

    if-eqz p1, :cond_7

    if-nez p1, :cond_5

    invoke-static {v1}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    move-object p1, v0

    :cond_5
    invoke-virtual {p1}, Landroid/widget/LinearLayout;->isAttachedToWindow()Z

    move-result p1

    if-eqz p1, :cond_7

    .line 132
    invoke-direct {p0}, Lorg/nameless/gamespace/gamebar/GameBarService;->getWm()Landroid/view/WindowManager;

    move-result-object p1

    iget-object p0, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->rootPanelView:Landroid/widget/LinearLayout;

    if-nez p0, :cond_6

    invoke-static {v1}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    goto :goto_1

    :cond_6
    move-object v0, p0

    :goto_1
    invoke-interface {p1, v0}, Landroid/view/WindowManager;->removeView(Landroid/view/View;)V

    :cond_7
    :goto_2
    return-void
.end method

.method private final setupPanelView()V
    .locals 8

    .line 303
    invoke-static {p0}, Landroid/view/LayoutInflater;->from(Landroid/content/Context;)Landroid/view/LayoutInflater;

    move-result-object v0

    .line 304
    new-instance v1, Landroid/widget/FrameLayout;

    invoke-direct {v1, p0}, Landroid/widget/FrameLayout;-><init>(Landroid/content/Context;)V

    const v2, 0x7f0d0116

    const/4 v3, 0x0

    invoke-virtual {v0, v2, v1, v3}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;Z)Landroid/view/View;

    move-result-object v0

    const-string v1, "null cannot be cast to non-null type android.widget.LinearLayout"

    .line 303
    invoke-static {v0, v1}, Lkotlin/jvm/internal/Intrinsics;->checkNotNull(Ljava/lang/Object;Ljava/lang/String;)V

    check-cast v0, Landroid/widget/LinearLayout;

    iput-object v0, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->rootPanelView:Landroid/widget/LinearLayout;

    const-string v1, "rootPanelView"

    const/4 v2, 0x0

    if-nez v0, :cond_0

    .line 305
    invoke-static {v1}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    move-object v0, v2

    :cond_0
    const v4, 0x7f0a01e4

    invoke-virtual {v0, v4}, Landroid/widget/LinearLayout;->findViewById(I)Landroid/view/View;

    move-result-object v0

    const-string v4, "rootPanelView.findViewById(R.id.panel_view)"

    invoke-static {v0, v4}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullExpressionValue(Ljava/lang/Object;Ljava/lang/String;)V

    check-cast v0, Lorg/nameless/gamespace/widget/PanelView;

    iput-object v0, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->panelView:Lorg/nameless/gamespace/widget/PanelView;

    const-string v4, "panelView"

    if-nez v0, :cond_1

    .line 306
    invoke-static {v4}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    move-object v0, v2

    :cond_1
    invoke-virtual {p0}, Lorg/nameless/gamespace/gamebar/GameBarService;->getAppSettings()Lorg/nameless/gamespace/data/AppSettings;

    move-result-object v5

    invoke-virtual {v5}, Lorg/nameless/gamespace/data/AppSettings;->getMenuOpacity()I

    move-result v5

    int-to-float v5, v5

    const/high16 v6, 0x42c80000    # 100.0f

    div-float/2addr v5, v6

    invoke-virtual {v0, v5}, Landroid/widget/LinearLayout;->setAlpha(F)V

    .line 307
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->rootPanelView:Landroid/widget/LinearLayout;

    if-nez v0, :cond_2

    invoke-static {v1}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    move-object v0, v2

    :cond_2
    new-instance v5, Lorg/nameless/gamespace/gamebar/GameBarService$setupPanelView$1;

    invoke-direct {v5, p0}, Lorg/nameless/gamespace/gamebar/GameBarService$setupPanelView$1;-><init>(Lorg/nameless/gamespace/gamebar/GameBarService;)V

    invoke-virtual {v0, v5}, Landroid/widget/LinearLayout;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 310
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->barView:Landroid/widget/LinearLayout;

    const-string v5, "barView"

    if-nez v0, :cond_3

    invoke-static {v5}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    move-object v0, v2

    :cond_3
    invoke-virtual {v0}, Landroid/widget/LinearLayout;->getWidth()I

    move-result v0

    iget-object v6, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->barView:Landroid/widget/LinearLayout;

    if-nez v6, :cond_4

    invoke-static {v5}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    move-object v6, v2

    .line 371
    :cond_4
    invoke-virtual {v6}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v6

    .line 372
    instance-of v7, v6, Landroid/view/ViewGroup$MarginLayoutParams;

    if-eqz v7, :cond_5

    check-cast v6, Landroid/view/ViewGroup$MarginLayoutParams;

    invoke-static {v6}, Landroidx/core/view/MarginLayoutParamsCompat;->getMarginStart(Landroid/view/ViewGroup$MarginLayoutParams;)I

    move-result v3

    :cond_5
    add-int/2addr v0, v3

    .line 311
    iget-object v3, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->barLayoutParam:Landroid/view/WindowManager$LayoutParams;

    iget v3, v3, Landroid/view/WindowManager$LayoutParams;->x:I

    const/16 v6, 0x10

    if-gez v3, :cond_8

    .line 312
    iget-object v3, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->rootPanelView:Landroid/widget/LinearLayout;

    if-nez v3, :cond_6

    invoke-static {v1}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    move-object v3, v2

    :cond_6
    const v7, 0x800003

    invoke-virtual {v3, v7}, Landroid/widget/LinearLayout;->setGravity(I)V

    .line 313
    iget-object v3, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->rootPanelView:Landroid/widget/LinearLayout;

    if-nez v3, :cond_7

    invoke-static {v1}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    move-object v3, v2

    :cond_7
    invoke-virtual {v3, v0, v6, v6, v6}, Landroid/widget/LinearLayout;->setPaddingRelative(IIII)V

    goto :goto_0

    .line 315
    :cond_8
    iget-object v3, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->rootPanelView:Landroid/widget/LinearLayout;

    if-nez v3, :cond_9

    invoke-static {v1}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    move-object v3, v2

    :cond_9
    const v7, 0x800005

    invoke-virtual {v3, v7}, Landroid/widget/LinearLayout;->setGravity(I)V

    .line 316
    iget-object v3, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->rootPanelView:Landroid/widget/LinearLayout;

    if-nez v3, :cond_a

    invoke-static {v1}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    move-object v3, v2

    :cond_a
    invoke-virtual {v3, v6, v6, v0, v6}, Landroid/widget/LinearLayout;->setPaddingRelative(IIII)V

    .line 318
    :goto_0
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->panelView:Lorg/nameless/gamespace/widget/PanelView;

    if-nez v0, :cond_b

    invoke-static {v4}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    move-object v0, v2

    :cond_b
    iget-object v1, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->barView:Landroid/widget/LinearLayout;

    if-nez v1, :cond_c

    invoke-static {v5}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    move-object v1, v2

    :cond_c
    invoke-virtual {v1}, Landroid/widget/LinearLayout;->getLocationOnScreen()[I

    move-result-object v1

    const-string v3, "barView.locationOnScreen"

    invoke-static {v1, v3}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullExpressionValue(Ljava/lang/Object;Ljava/lang/String;)V

    invoke-static {v1}, Lkotlin/collections/ArraysKt;->last([I)I

    move-result v1

    iget-object p0, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->barView:Landroid/widget/LinearLayout;

    if-nez p0, :cond_d

    invoke-static {v5}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    goto :goto_1

    :cond_d
    move-object v2, p0

    :goto_1
    invoke-virtual {v2}, Landroid/widget/LinearLayout;->getHeight()I

    move-result p0

    sub-int/2addr v1, p0

    invoke-virtual {v0, v1}, Lorg/nameless/gamespace/widget/PanelView;->setRelativeY(I)V

    return-void
.end method

.method private final takeShot()V
    .locals 5

    .line 322
    new-instance v0, Lorg/nameless/gamespace/gamebar/GameBarService$takeShot$afterShot$1;

    invoke-direct {v0, p0}, Lorg/nameless/gamespace/gamebar/GameBarService$takeShot$afterShot$1;-><init>(Lorg/nameless/gamespace/gamebar/GameBarService;)V

    .line 329
    sget-object v1, Lorg/nameless/gamespace/gamebar/GameBarService$takeShot$1;->INSTANCE:Lorg/nameless/gamespace/gamebar/GameBarService$takeShot$1;

    invoke-direct {p0, v1}, Lorg/nameless/gamespace/gamebar/GameBarService;->updateLayout(Lkotlin/jvm/functions/Function1;)V

    .line 330
    invoke-direct {p0}, Lorg/nameless/gamespace/gamebar/GameBarService;->getHandler()Landroid/os/Handler;

    move-result-object v1

    new-instance v2, Lorg/nameless/gamespace/gamebar/GameBarService$takeShot$2;

    invoke-direct {v2, p0, v0}, Lorg/nameless/gamespace/gamebar/GameBarService$takeShot$2;-><init>(Lorg/nameless/gamespace/gamebar/GameBarService;Lkotlin/jvm/functions/Function0;)V

    const-wide/16 v3, 0xfa

    invoke-virtual {v1, v2, v3, v4}, Landroid/os/Handler;->postDelayed(Ljava/lang/Runnable;J)Z

    return-void
.end method

.method private final updateBackground()V
    .locals 6

    .line 256
    iget-boolean v0, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->barExpanded:Z

    const/4 v1, 0x0

    const-string v2, "barView"

    const/4 v3, 0x1

    const/4 v4, 0x0

    if-nez v0, :cond_2

    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->barView:Landroid/widget/LinearLayout;

    if-nez v0, :cond_0

    invoke-static {v2}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    move-object v0, v1

    :cond_0
    invoke-virtual {v0}, Landroid/widget/LinearLayout;->getTranslationX()F

    move-result v0

    const/4 v5, 0x0

    cmpg-float v0, v0, v5

    if-nez v0, :cond_1

    move v0, v3

    goto :goto_0

    :cond_1
    move v0, v4

    :goto_0
    if-eqz v0, :cond_2

    move v0, v3

    goto :goto_1

    :cond_2
    move v0, v4

    :goto_1
    if-nez v0, :cond_3

    .line 257
    iget-object v5, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->barLayoutParam:Landroid/view/WindowManager$LayoutParams;

    iget v5, v5, Landroid/view/WindowManager$LayoutParams;->x:I

    if-gez v5, :cond_3

    move v5, v3

    goto :goto_2

    :cond_3
    move v5, v4

    :goto_2
    if-nez v0, :cond_4

    .line 258
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->barLayoutParam:Landroid/view/WindowManager$LayoutParams;

    iget v0, v0, Landroid/view/WindowManager$LayoutParams;->x:I

    if-lez v0, :cond_4

    goto :goto_3

    :cond_4
    move v3, v4

    .line 259
    :goto_3
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->barView:Landroid/widget/LinearLayout;

    if-nez v0, :cond_5

    invoke-static {v2}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    goto :goto_4

    :cond_5
    move-object v1, v0

    .line 261
    :goto_4
    iget-boolean p0, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->barExpanded:Z

    const v0, 0x7f08007f

    if-eqz p0, :cond_6

    goto :goto_5

    :cond_6
    if-eqz v5, :cond_7

    const v0, 0x7f08007e

    goto :goto_5

    :cond_7
    if-eqz v3, :cond_8

    const v0, 0x7f08007d

    .line 259
    :cond_8
    :goto_5
    invoke-virtual {v1, v0}, Landroid/widget/LinearLayout;->setBackgroundResource(I)V

    return-void
.end method

.method private final updateContainerGaps()V
    .locals 5

    .line 270
    iget-boolean v0, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->barExpanded:Z

    const-string v1, "null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams"

    const/4 v2, 0x0

    const-string v3, "barView"

    if-eqz v0, :cond_2

    .line 271
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->barView:Landroid/widget/LinearLayout;

    if-nez v0, :cond_0

    invoke-static {v3}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    move-object v0, v2

    :cond_0
    const/16 v4, 0x8

    .line 160
    invoke-virtual {v0, v4, v4, v4, v4}, Landroid/view/View;->setPadding(IIII)V

    .line 272
    iget-object p0, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->barView:Landroid/widget/LinearLayout;

    if-nez p0, :cond_1

    invoke-static {v3}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    goto :goto_0

    :cond_1
    move-object v2, p0

    :goto_0
    invoke-virtual {v2}, Landroid/widget/LinearLayout;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object p0

    invoke-static {p0, v1}, Lkotlin/jvm/internal/Intrinsics;->checkNotNull(Ljava/lang/Object;Ljava/lang/String;)V

    check-cast p0, Landroid/view/ViewGroup$MarginLayoutParams;

    .line 143
    iget v0, p0, Landroid/view/ViewGroup$MarginLayoutParams;->topMargin:I

    .line 145
    iget v1, p0, Landroid/view/ViewGroup$MarginLayoutParams;->bottomMargin:I

    const/16 v2, 0x30

    .line 147
    invoke-virtual {p0, v2, v0, v2, v1}, Landroid/view/ViewGroup$MarginLayoutParams;->setMargins(IIII)V

    goto :goto_2

    .line 275
    :cond_2
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->barView:Landroid/widget/LinearLayout;

    if-nez v0, :cond_3

    invoke-static {v3}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    move-object v0, v2

    :cond_3
    const/4 v4, 0x0

    .line 160
    invoke-virtual {v0, v4, v4, v4, v4}, Landroid/view/View;->setPadding(IIII)V

    .line 276
    iget-object p0, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->barView:Landroid/widget/LinearLayout;

    if-nez p0, :cond_4

    invoke-static {v3}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    goto :goto_1

    :cond_4
    move-object v2, p0

    :goto_1
    invoke-virtual {v2}, Landroid/widget/LinearLayout;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object p0

    invoke-static {p0, v1}, Lkotlin/jvm/internal/Intrinsics;->checkNotNull(Ljava/lang/Object;Ljava/lang/String;)V

    check-cast p0, Landroid/view/ViewGroup$MarginLayoutParams;

    .line 143
    iget v0, p0, Landroid/view/ViewGroup$MarginLayoutParams;->topMargin:I

    .line 145
    iget v1, p0, Landroid/view/ViewGroup$MarginLayoutParams;->bottomMargin:I

    .line 147
    invoke-virtual {p0, v4, v0, v4, v1}, Landroid/view/ViewGroup$MarginLayoutParams;->setMargins(IIII)V

    :goto_2
    return-void
.end method

.method private final updateLayout(Lkotlin/jvm/functions/Function1;)V
    .locals 4
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lkotlin/jvm/functions/Function1<",
            "-",
            "Landroid/view/WindowManager$LayoutParams;",
            "Lkotlin/Unit;",
            ">;)V"
        }
    .end annotation

    .line 233
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->rootBarView:Landroid/view/View;

    const/4 v1, 0x0

    const-string v2, "rootBarView"

    if-nez v0, :cond_0

    invoke-static {v2}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    move-object v0, v1

    :cond_0
    invoke-virtual {v0}, Landroid/view/View;->isAttachedToWindow()Z

    move-result v0

    if-eqz v0, :cond_2

    .line 234
    invoke-direct {p0}, Lorg/nameless/gamespace/gamebar/GameBarService;->getWm()Landroid/view/WindowManager;

    move-result-object v0

    iget-object v3, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->rootBarView:Landroid/view/View;

    if-nez v3, :cond_1

    invoke-static {v2}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    goto :goto_0

    :cond_1
    move-object v1, v3

    :goto_0
    iget-object p0, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->barLayoutParam:Landroid/view/WindowManager$LayoutParams;

    invoke-interface {p1, p0}, Lkotlin/jvm/functions/Function1;->invoke(Ljava/lang/Object;)Ljava/lang/Object;

    invoke-interface {v0, v1, p0}, Landroid/view/WindowManager;->updateViewLayout(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    :cond_2
    return-void
.end method

.method private final updateRootBarView()V
    .locals 5

    .line 214
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->rootBarView:Landroid/view/View;

    if-nez v0, :cond_0

    return-void

    :cond_0
    const/4 v1, 0x0

    const-string v2, "rootBarView"

    if-nez v0, :cond_1

    .line 219
    :try_start_0
    invoke-static {v2}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    move-object v0, v1

    :cond_1
    invoke-virtual {v0}, Landroid/view/View;->isAttachedToWindow()Z

    move-result v0

    if-eqz v0, :cond_3

    .line 220
    invoke-direct {p0}, Lorg/nameless/gamespace/gamebar/GameBarService;->getWm()Landroid/view/WindowManager;

    move-result-object v0

    iget-object v3, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->rootBarView:Landroid/view/View;

    if-nez v3, :cond_2

    invoke-static {v2}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    move-object v3, v1

    :cond_2
    invoke-interface {v0, v3}, Landroid/view/WindowManager;->removeViewImmediate(Landroid/view/View;)V

    .line 222
    :cond_3
    invoke-virtual {p0}, Lorg/nameless/gamespace/gamebar/GameBarService;->getAppSettings()Lorg/nameless/gamespace/data/AppSettings;

    move-result-object v0

    invoke-virtual {v0}, Lorg/nameless/gamespace/data/AppSettings;->getShowOverlay()Z

    move-result v0

    if-eqz v0, :cond_7

    .line 223
    invoke-direct {p0}, Lorg/nameless/gamespace/gamebar/GameBarService;->getWm()Landroid/view/WindowManager;

    move-result-object v0

    iget-object v3, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->rootBarView:Landroid/view/View;

    if-nez v3, :cond_4

    invoke-static {v2}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    move-object v3, v1

    :cond_4
    iget-object v4, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->barLayoutParam:Landroid/view/WindowManager$LayoutParams;

    invoke-interface {v0, v3, v4}, Landroid/view/WindowManager;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V
    :try_end_0
    .catch Ljava/lang/RuntimeException; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_1

    .line 226
    :catch_0
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->rootBarView:Landroid/view/View;

    if-nez v0, :cond_5

    invoke-static {v2}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    move-object v0, v1

    :cond_5
    invoke-virtual {v0}, Landroid/view/View;->isAttachedToWindow()Z

    move-result v0

    if-eqz v0, :cond_7

    .line 227
    invoke-direct {p0}, Lorg/nameless/gamespace/gamebar/GameBarService;->getWm()Landroid/view/WindowManager;

    move-result-object v0

    iget-object v3, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->rootBarView:Landroid/view/View;

    if-nez v3, :cond_6

    invoke-static {v2}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    goto :goto_0

    :cond_6
    move-object v1, v3

    :goto_0
    iget-object p0, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->barLayoutParam:Landroid/view/WindowManager$LayoutParams;

    invoke-interface {v0, v1, p0}, Landroid/view/WindowManager;->updateViewLayout(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    :cond_7
    :goto_1
    return-void
.end method


# virtual methods
.method public final getAppSettings()Lorg/nameless/gamespace/data/AppSettings;
    .locals 0

    .line 60
    iget-object p0, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->appSettings:Lorg/nameless/gamespace/data/AppSettings;

    if-eqz p0, :cond_0

    return-object p0

    :cond_0
    const-string p0, "appSettings"

    invoke-static {p0}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0
.end method

.method public final getDanmakuService()Lorg/nameless/gamespace/gamebar/DanmakuService;
    .locals 0

    .line 66
    iget-object p0, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->danmakuService:Lorg/nameless/gamespace/gamebar/DanmakuService;

    if-eqz p0, :cond_0

    return-object p0

    :cond_0
    const-string p0, "danmakuService"

    invoke-static {p0}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0
.end method

.method public final getScreenUtils()Lorg/nameless/gamespace/utils/ScreenUtils;
    .locals 0

    .line 63
    iget-object p0, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->screenUtils:Lorg/nameless/gamespace/utils/ScreenUtils;

    if-eqz p0, :cond_0

    return-object p0

    :cond_0
    const-string p0, "screenUtils"

    invoke-static {p0}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0
.end method

.method public bridge synthetic onBind(Landroid/content/Intent;)Landroid/os/IBinder;
    .locals 0

    .line 57
    invoke-virtual {p0, p1}, Lorg/nameless/gamespace/gamebar/GameBarService;->onBind(Landroid/content/Intent;)Lorg/nameless/gamespace/gamebar/GameBarService$GameBarBinder;

    move-result-object p0

    return-object p0
.end method

.method public onBind(Landroid/content/Intent;)Lorg/nameless/gamespace/gamebar/GameBarService$GameBarBinder;
    .locals 1

    const-string v0, "intent"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 162
    iget-object p0, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->binder:Lorg/nameless/gamespace/gamebar/GameBarService$GameBarBinder;

    return-object p0
.end method

.method public onConfigurationChanged(Landroid/content/res/Configuration;)V
    .locals 4

    const-string v0, "newConfig"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 183
    invoke-super {p0, p1}, Landroid/app/Service;->onConfigurationChanged(Landroid/content/res/Configuration;)V

    .line 184
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->rootBarView:Landroid/view/View;

    if-nez v0, :cond_0

    const-string v0, "rootBarView"

    invoke-static {v0}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    const/4 v0, 0x0

    .line 252
    :cond_0
    invoke-virtual {v0}, Landroid/view/View;->getVisibility()I

    move-result v0

    if-nez v0, :cond_1

    const/4 v0, 0x1

    goto :goto_0

    :cond_1
    const/4 v0, 0x0

    :goto_0
    if-nez v0, :cond_2

    .line 185
    invoke-direct {p0}, Lorg/nameless/gamespace/gamebar/GameBarService;->getHandler()Landroid/os/Handler;

    move-result-object v0

    iget-object v1, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->firstPaint:Ljava/lang/Runnable;

    invoke-virtual {v0, v1}, Landroid/os/Handler;->removeCallbacks(Ljava/lang/Runnable;)V

    .line 186
    invoke-direct {p0}, Lorg/nameless/gamespace/gamebar/GameBarService;->getHandler()Landroid/os/Handler;

    move-result-object v0

    new-instance v1, Lorg/nameless/gamespace/gamebar/GameBarService$onConfigurationChanged$1;

    invoke-direct {v1, p0}, Lorg/nameless/gamespace/gamebar/GameBarService$onConfigurationChanged$1;-><init>(Lorg/nameless/gamespace/gamebar/GameBarService;)V

    const-wide/16 v2, 0x64

    invoke-virtual {v0, v1, v2, v3}, Landroid/os/Handler;->postDelayed(Ljava/lang/Runnable;J)Z

    goto :goto_1

    .line 191
    :cond_2
    invoke-direct {p0}, Lorg/nameless/gamespace/gamebar/GameBarService;->dockCollapsedMenu()V

    .line 193
    :goto_1
    invoke-virtual {p0}, Lorg/nameless/gamespace/gamebar/GameBarService;->getDanmakuService()Lorg/nameless/gamespace/gamebar/DanmakuService;

    move-result-object p0

    invoke-virtual {p0, p1}, Lorg/nameless/gamespace/gamebar/DanmakuService;->updateConfiguration(Landroid/content/res/Configuration;)V

    return-void
.end method

.method public onCreate()V
    .locals 4

    .line 137
    invoke-super {p0}, Lorg/nameless/gamespace/gamebar/Hilt_GameBarService;->onCreate()V

    .line 138
    new-instance v0, Landroid/widget/FrameLayout;

    invoke-direct {v0, p0}, Landroid/widget/FrameLayout;-><init>(Landroid/content/Context;)V

    .line 139
    invoke-static {p0}, Landroid/view/LayoutInflater;->from(Landroid/content/Context;)Landroid/view/LayoutInflater;

    move-result-object v1

    const v2, 0x7f0d0117

    const/4 v3, 0x0

    .line 140
    invoke-virtual {v1, v2, v0, v3}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;Z)Landroid/view/View;

    move-result-object v0

    const-string v1, "from(this)\n            .\u2026indow_util, frame, false)"

    invoke-static {v0, v1}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullExpressionValue(Ljava/lang/Object;Ljava/lang/String;)V

    .line 139
    iput-object v0, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->rootBarView:Landroid/view/View;

    const/4 v1, 0x0

    const-string v2, "rootBarView"

    if-nez v0, :cond_0

    .line 141
    invoke-static {v2}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    move-object v0, v1

    :cond_0
    const v3, 0x7f0a00b6

    invoke-virtual {v0, v3}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    const-string v3, "rootBarView.findViewById(R.id.container_bar)"

    invoke-static {v0, v3}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullExpressionValue(Ljava/lang/Object;Ljava/lang/String;)V

    check-cast v0, Landroid/widget/LinearLayout;

    iput-object v0, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->barView:Landroid/widget/LinearLayout;

    .line 142
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->rootBarView:Landroid/view/View;

    if-nez v0, :cond_1

    invoke-static {v2}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    goto :goto_0

    :cond_1
    move-object v1, v0

    :goto_0
    const v0, 0x7f0a0041

    invoke-virtual {v1, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    const-string v1, "rootBarView.findViewById\u2026.id.action_menu_switcher)"

    invoke-static {v0, v1}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullExpressionValue(Ljava/lang/Object;Ljava/lang/String;)V

    check-cast v0, Lorg/nameless/gamespace/widget/MenuSwitcher;

    iput-object v0, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->menuSwitcher:Lorg/nameless/gamespace/widget/MenuSwitcher;

    .line 143
    invoke-virtual {p0}, Lorg/nameless/gamespace/gamebar/GameBarService;->getDanmakuService()Lorg/nameless/gamespace/gamebar/DanmakuService;

    move-result-object v0

    invoke-virtual {v0}, Lorg/nameless/gamespace/gamebar/DanmakuService;->init()V

    .line 145
    invoke-direct {p0}, Lorg/nameless/gamespace/gamebar/GameBarService;->getHandler()Landroid/os/Handler;

    move-result-object v0

    new-instance v1, Lorg/nameless/gamespace/gamebar/GameBarService$onCreate$1;

    invoke-direct {v1, p0}, Lorg/nameless/gamespace/gamebar/GameBarService$onCreate$1;-><init>(Lorg/nameless/gamespace/gamebar/GameBarService;)V

    invoke-virtual {v0, v1}, Landroid/os/Handler;->post(Ljava/lang/Runnable;)Z

    .line 149
    new-instance v0, Landroid/content/IntentFilter;

    const-string v1, "org.nameless.gamespace.CLONE_PANEL"

    invoke-direct {v0, v1}, Landroid/content/IntentFilter;-><init>(Ljava/lang/String;)V

    .line 150
    iget-object v1, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->receiver:Lorg/nameless/gamespace/gamebar/GameBarService$ClosePanelReceiver;

    invoke-virtual {p0, v1, v0}, Landroid/app/Service;->registerReceiver(Landroid/content/BroadcastReceiver;Landroid/content/IntentFilter;)Landroid/content/Intent;

    return-void
.end method

.method public onDestroy()V
    .locals 1

    .line 176
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->receiver:Lorg/nameless/gamespace/gamebar/GameBarService$ClosePanelReceiver;

    invoke-virtual {p0, v0}, Landroid/app/Service;->unregisterReceiver(Landroid/content/BroadcastReceiver;)V

    .line 177
    invoke-virtual {p0}, Lorg/nameless/gamespace/gamebar/GameBarService;->getDanmakuService()Lorg/nameless/gamespace/gamebar/DanmakuService;

    move-result-object v0

    invoke-virtual {v0}, Lorg/nameless/gamespace/gamebar/DanmakuService;->destroy()V

    .line 178
    invoke-virtual {p0}, Lorg/nameless/gamespace/gamebar/GameBarService;->onGameLeave()V

    .line 179
    invoke-super {p0}, Landroid/app/Service;->onDestroy()V

    return-void
.end method

.method public final onGameLeave()V
    .locals 4

    .line 205
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->rootPanelView:Landroid/widget/LinearLayout;

    const/4 v1, 0x0

    if-eqz v0, :cond_2

    const-string v2, "rootPanelView"

    if-nez v0, :cond_0

    invoke-static {v2}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    move-object v0, v1

    :cond_0
    invoke-virtual {v0}, Landroid/widget/LinearLayout;->isAttachedToWindow()Z

    move-result v0

    if-eqz v0, :cond_2

    .line 206
    invoke-direct {p0}, Lorg/nameless/gamespace/gamebar/GameBarService;->getWm()Landroid/view/WindowManager;

    move-result-object v0

    iget-object v3, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->rootPanelView:Landroid/widget/LinearLayout;

    if-nez v3, :cond_1

    invoke-static {v2}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    move-object v3, v1

    :cond_1
    invoke-interface {v0, v3}, Landroid/view/WindowManager;->removeViewImmediate(Landroid/view/View;)V

    .line 208
    :cond_2
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->rootBarView:Landroid/view/View;

    if-eqz v0, :cond_5

    const-string v2, "rootBarView"

    if-nez v0, :cond_3

    invoke-static {v2}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    move-object v0, v1

    :cond_3
    invoke-virtual {v0}, Landroid/view/View;->isAttachedToWindow()Z

    move-result v0

    if-eqz v0, :cond_5

    .line 209
    invoke-direct {p0}, Lorg/nameless/gamespace/gamebar/GameBarService;->getWm()Landroid/view/WindowManager;

    move-result-object v0

    iget-object p0, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->rootBarView:Landroid/view/View;

    if-nez p0, :cond_4

    invoke-static {v2}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    goto :goto_0

    :cond_4
    move-object v1, p0

    :goto_0
    invoke-interface {v0, v1}, Landroid/view/WindowManager;->removeViewImmediate(Landroid/view/View;)V

    :cond_5
    return-void
.end method

.method public final onGameStart()V
    .locals 4

    .line 198
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->rootBarView:Landroid/view/View;

    const/4 v1, 0x0

    const-string v2, "rootBarView"

    if-nez v0, :cond_0

    invoke-static {v2}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    move-object v0, v1

    :cond_0
    const/16 v3, 0x8

    .line 254
    invoke-virtual {v0, v3}, Landroid/view/View;->setVisibility(I)V

    .line 199
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->rootBarView:Landroid/view/View;

    if-nez v0, :cond_1

    invoke-static {v2}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    goto :goto_0

    :cond_1
    move-object v1, v0

    :goto_0
    const/4 v0, 0x0

    invoke-virtual {v1, v0}, Landroid/view/View;->setAlpha(F)V

    .line 200
    invoke-direct {p0}, Lorg/nameless/gamespace/gamebar/GameBarService;->updateRootBarView()V

    .line 201
    invoke-direct {p0}, Lorg/nameless/gamespace/gamebar/GameBarService;->getHandler()Landroid/os/Handler;

    move-result-object v0

    iget-object p0, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->firstPaint:Ljava/lang/Runnable;

    const-wide/16 v1, 0x1f4

    invoke-virtual {v0, p0, v1, v2}, Landroid/os/Handler;->postDelayed(Ljava/lang/Runnable;J)Z

    return-void
.end method

.method public onStartCommand(Landroid/content/Intent;II)I
    .locals 0

    .line 154
    invoke-super {p0, p1, p2, p3}, Landroid/app/Service;->onStartCommand(Landroid/content/Intent;II)I

    if-eqz p1, :cond_0

    .line 155
    invoke-virtual {p1}, Landroid/content/Intent;->getAction()Ljava/lang/String;

    move-result-object p1

    goto :goto_0

    :cond_0
    const/4 p1, 0x0

    :goto_0
    const-string p2, "GameBar.ACTION_STOP"

    .line 156
    invoke-static {p1, p2}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result p2

    if-eqz p2, :cond_1

    invoke-virtual {p0}, Lorg/nameless/gamespace/gamebar/GameBarService;->onGameLeave()V

    goto :goto_1

    :cond_1
    const-string p2, "GameBar.ACTION_START"

    .line 157
    invoke-static {p1, p2}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_2

    invoke-virtual {p0}, Lorg/nameless/gamespace/gamebar/GameBarService;->onGameStart()V

    :cond_2
    :goto_1
    const/4 p0, 0x1

    return p0
.end method
