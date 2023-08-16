.class public final Lorg/nameless/gamespace/widget/MenuSwitcher;
.super Landroid/widget/LinearLayout;
.source "MenuSwitcher.kt"


# annotations
.annotation system Ldalvik/annotation/SourceDebugExtension;
    value = "SMAP\nMenuSwitcher.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MenuSwitcher.kt\norg/nameless/gamespace/widget/MenuSwitcher\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,102:1\n1#2:103\n*E\n"
.end annotation


# instance fields
.field private final appSettings$delegate:Lkotlin/Lazy;

.field private isDragged:Z

.field private final scope:Lkotlinx/coroutines/CoroutineScope;

.field private showFps:Z

.field private final taskFpsCallback:Lorg/nameless/gamespace/widget/MenuSwitcher$taskFpsCallback$1;

.field private final taskManager$delegate:Lkotlin/Lazy;


# direct methods
.method public constructor <init>(Landroid/content/Context;)V
    .locals 2

    const-string v0, "context"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    const/4 v0, 0x0

    const/4 v1, 0x2

    invoke-direct {p0, p1, v0, v1, v0}, Lorg/nameless/gamespace/widget/MenuSwitcher;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 2

    const-string v0, "context"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 24
    invoke-direct {p0, p1, p2}, Landroid/widget/LinearLayout;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    .line 27
    invoke-static {p1}, Landroid/view/LayoutInflater;->from(Landroid/content/Context;)Landroid/view/LayoutInflater;

    move-result-object p2

    const v0, 0x7f0d0025

    const/4 v1, 0x1

    invoke-virtual {p2, v0, p0, v1}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;Z)Landroid/view/View;

    .line 30
    new-instance p2, Lorg/nameless/gamespace/widget/MenuSwitcher$appSettings$2;

    invoke-direct {p2, p1}, Lorg/nameless/gamespace/widget/MenuSwitcher$appSettings$2;-><init>(Landroid/content/Context;)V

    invoke-static {p2}, Lkotlin/LazyKt;->lazy(Lkotlin/jvm/functions/Function0;)Lkotlin/Lazy;

    move-result-object p1

    iput-object p1, p0, Lorg/nameless/gamespace/widget/MenuSwitcher;->appSettings$delegate:Lkotlin/Lazy;

    const/4 p1, 0x0

    .line 31
    invoke-static {p1, v1, p1}, Lkotlinx/coroutines/JobKt;->Job$default(Lkotlinx/coroutines/Job;ILjava/lang/Object;)Lkotlinx/coroutines/CompletableJob;

    move-result-object p1

    invoke-static {}, Lkotlinx/coroutines/Dispatchers;->getMain()Lkotlinx/coroutines/MainCoroutineDispatcher;

    move-result-object p2

    invoke-interface {p1, p2}, Lkotlin/coroutines/CoroutineContext;->plus(Lkotlin/coroutines/CoroutineContext;)Lkotlin/coroutines/CoroutineContext;

    move-result-object p1

    invoke-static {p1}, Lkotlinx/coroutines/CoroutineScopeKt;->CoroutineScope(Lkotlin/coroutines/CoroutineContext;)Lkotlinx/coroutines/CoroutineScope;

    move-result-object p1

    iput-object p1, p0, Lorg/nameless/gamespace/widget/MenuSwitcher;->scope:Lkotlinx/coroutines/CoroutineScope;

    .line 32
    sget-object p1, Lorg/nameless/gamespace/widget/MenuSwitcher$taskManager$2;->INSTANCE:Lorg/nameless/gamespace/widget/MenuSwitcher$taskManager$2;

    invoke-static {p1}, Lkotlin/LazyKt;->lazy(Lkotlin/jvm/functions/Function0;)Lkotlin/Lazy;

    move-result-object p1

    iput-object p1, p0, Lorg/nameless/gamespace/widget/MenuSwitcher;->taskManager$delegate:Lkotlin/Lazy;

    .line 34
    new-instance p1, Lorg/nameless/gamespace/widget/MenuSwitcher$taskFpsCallback$1;

    invoke-direct {p1, p0}, Lorg/nameless/gamespace/widget/MenuSwitcher$taskFpsCallback$1;-><init>(Lorg/nameless/gamespace/widget/MenuSwitcher;)V

    iput-object p1, p0, Lorg/nameless/gamespace/widget/MenuSwitcher;->taskFpsCallback:Lorg/nameless/gamespace/widget/MenuSwitcher$taskFpsCallback$1;

    return-void
.end method

.method public synthetic constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;ILkotlin/jvm/internal/DefaultConstructorMarker;)V
    .locals 0

    and-int/lit8 p3, p3, 0x2

    if-eqz p3, :cond_0

    const/4 p2, 0x0

    .line 22
    :cond_0
    invoke-direct {p0, p1, p2}, Lorg/nameless/gamespace/widget/MenuSwitcher;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    return-void
.end method

.method public static final synthetic access$getContent(Lorg/nameless/gamespace/widget/MenuSwitcher;)Landroid/widget/TextView;
    .locals 0

    .line 22
    invoke-direct {p0}, Lorg/nameless/gamespace/widget/MenuSwitcher;->getContent()Landroid/widget/TextView;

    move-result-object p0

    return-object p0
.end method

.method public static final synthetic access$onFrameUpdated(Lorg/nameless/gamespace/widget/MenuSwitcher;F)Lkotlinx/coroutines/Job;
    .locals 0

    .line 22
    invoke-direct {p0, p1}, Lorg/nameless/gamespace/widget/MenuSwitcher;->onFrameUpdated(F)Lkotlinx/coroutines/Job;

    move-result-object p0

    return-object p0
.end method

.method private final getAppSettings()Lorg/nameless/gamespace/data/AppSettings;
    .locals 0

    .line 30
    iget-object p0, p0, Lorg/nameless/gamespace/widget/MenuSwitcher;->appSettings$delegate:Lkotlin/Lazy;

    invoke-interface {p0}, Lkotlin/Lazy;->getValue()Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lorg/nameless/gamespace/data/AppSettings;

    return-object p0
.end method

.method private final getContent()Landroid/widget/TextView;
    .locals 1

    const v0, 0x7f0a016a

    .line 46
    invoke-virtual {p0, v0}, Landroid/widget/LinearLayout;->findViewById(I)Landroid/view/View;

    move-result-object p0

    check-cast p0, Landroid/widget/TextView;

    return-object p0
.end method

.method private final getTaskManager()Landroid/app/IActivityTaskManager;
    .locals 0

    .line 32
    iget-object p0, p0, Lorg/nameless/gamespace/widget/MenuSwitcher;->taskManager$delegate:Lkotlin/Lazy;

    invoke-interface {p0}, Lkotlin/Lazy;->getValue()Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Landroid/app/IActivityTaskManager;

    return-object p0
.end method

.method private final getWm()Landroid/view/WindowManager;
    .locals 1

    .line 43
    invoke-virtual {p0}, Landroid/widget/LinearLayout;->getContext()Landroid/content/Context;

    move-result-object p0

    const-string v0, "window"

    invoke-virtual {p0, v0}, Landroid/content/Context;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object p0

    const-string v0, "null cannot be cast to non-null type android.view.WindowManager"

    invoke-static {p0, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNull(Ljava/lang/Object;Ljava/lang/String;)V

    check-cast p0, Landroid/view/WindowManager;

    return-object p0
.end method

.method private final onFrameUpdated(F)Lkotlinx/coroutines/Job;
    .locals 6

    .line 70
    iget-object v0, p0, Lorg/nameless/gamespace/widget/MenuSwitcher;->scope:Lkotlinx/coroutines/CoroutineScope;

    new-instance v3, Lorg/nameless/gamespace/widget/MenuSwitcher$onFrameUpdated$1;

    const/4 v1, 0x0

    invoke-direct {v3, p0, p1, v1}, Lorg/nameless/gamespace/widget/MenuSwitcher$onFrameUpdated$1;-><init>(Lorg/nameless/gamespace/widget/MenuSwitcher;FLkotlin/coroutines/Continuation;)V

    const/4 v2, 0x0

    const/4 v4, 0x3

    const/4 v5, 0x0

    invoke-static/range {v0 .. v5}, Lkotlinx/coroutines/BuildersKt;->launch$default(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function2;ILjava/lang/Object;)Lkotlinx/coroutines/Job;

    move-result-object p0

    return-object p0
.end method

.method private final setMenuIcon(Ljava/lang/Integer;)V
    .locals 3

    const/4 v0, 0x1

    if-nez p1, :cond_0

    goto :goto_1

    .line 89
    :cond_0
    invoke-virtual {p1}, Ljava/lang/Integer;->intValue()I

    move-result v1

    const v2, 0x7f0800b3

    if-ne v1, v2, :cond_1

    :goto_0
    move v1, v0

    goto :goto_3

    :cond_1
    :goto_1
    const v1, 0x7f0800b9

    if-nez p1, :cond_2

    goto :goto_2

    :cond_2
    invoke-virtual {p1}, Ljava/lang/Integer;->intValue()I

    move-result v2

    if-ne v2, v1, :cond_3

    goto :goto_0

    :cond_3
    :goto_2
    const/4 v1, 0x0

    :goto_3
    if-eqz v1, :cond_4

    invoke-virtual {p0}, Landroid/widget/LinearLayout;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v1

    const/16 v2, 0x24

    invoke-static {v2}, Lorg/nameless/gamespace/utils/ExtensionsKt;->getDp(I)I

    move-result v2

    iput v2, v1, Landroid/view/ViewGroup$LayoutParams;->width:I

    goto :goto_4

    .line 90
    :cond_4
    invoke-virtual {p0}, Landroid/widget/LinearLayout;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v1

    const/4 v2, -0x2

    iput v2, v1, Landroid/view/ViewGroup$LayoutParams;->width:I

    :goto_4
    const/4 v1, 0x0

    if-eqz p1, :cond_6

    .line 92
    invoke-virtual {p1}, Ljava/lang/Number;->intValue()I

    iget-boolean v2, p0, Lorg/nameless/gamespace/widget/MenuSwitcher;->showFps:Z

    xor-int/2addr v0, v2

    if-eqz v0, :cond_5

    goto :goto_5

    :cond_5
    move-object p1, v1

    :goto_5
    if-eqz p1, :cond_6

    invoke-virtual {p1}, Ljava/lang/Number;->intValue()I

    move-result p1

    invoke-virtual {p0}, Landroid/widget/LinearLayout;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    invoke-virtual {p0}, Landroid/widget/LinearLayout;->getContext()Landroid/content/Context;

    move-result-object v2

    invoke-virtual {v2}, Landroid/content/Context;->getTheme()Landroid/content/res/Resources$Theme;

    move-result-object v2

    invoke-virtual {v0, p1, v2}, Landroid/content/res/Resources;->getDrawable(ILandroid/content/res/Resources$Theme;)Landroid/graphics/drawable/Drawable;

    move-result-object p1

    goto :goto_6

    :cond_6
    move-object p1, v1

    .line 93
    :goto_6
    invoke-direct {p0}, Lorg/nameless/gamespace/widget/MenuSwitcher;->getContent()Landroid/widget/TextView;

    move-result-object v0

    if-nez v0, :cond_7

    goto :goto_8

    :cond_7
    iget-boolean v2, p0, Lorg/nameless/gamespace/widget/MenuSwitcher;->showFps:Z

    if-eqz v2, :cond_8

    const/high16 v2, 0x3f800000    # 1.0f

    goto :goto_7

    :cond_8
    const/4 v2, 0x0

    :goto_7
    invoke-virtual {v0, v2}, Landroid/widget/TextView;->setTextScaleX(F)V

    .line 94
    :goto_8
    invoke-direct {p0}, Lorg/nameless/gamespace/widget/MenuSwitcher;->getContent()Landroid/widget/TextView;

    move-result-object p0

    if-eqz p0, :cond_9

    invoke-virtual {p0, v1, p1, v1, v1}, Landroid/widget/TextView;->setCompoundDrawablesRelativeWithIntrinsicBounds(Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;)V

    :cond_9
    return-void
.end method

.method private final updateFrameRateBinding()V
    .locals 3

    .line 78
    iget-boolean v0, p0, Lorg/nameless/gamespace/widget/MenuSwitcher;->showFps:Z

    if-eqz v0, :cond_0

    .line 79
    invoke-direct {p0}, Lorg/nameless/gamespace/widget/MenuSwitcher;->getTaskManager()Landroid/app/IActivityTaskManager;

    move-result-object v0

    if-eqz v0, :cond_1

    invoke-interface {v0}, Landroid/app/IActivityTaskManager;->getFocusedRootTaskInfo()Landroid/app/ActivityTaskManager$RootTaskInfo;

    move-result-object v0

    if-eqz v0, :cond_1

    iget v0, v0, Landroid/app/ActivityTaskManager$RootTaskInfo;->taskId:I

    .line 80
    invoke-direct {p0}, Lorg/nameless/gamespace/widget/MenuSwitcher;->getWm()Landroid/view/WindowManager;

    move-result-object v1

    sget-object v2, Lorg/nameless/gamespace/widget/MenuSwitcher$updateFrameRateBinding$1$1;->INSTANCE:Lorg/nameless/gamespace/widget/MenuSwitcher$updateFrameRateBinding$1$1;

    iget-object p0, p0, Lorg/nameless/gamespace/widget/MenuSwitcher;->taskFpsCallback:Lorg/nameless/gamespace/widget/MenuSwitcher$taskFpsCallback$1;

    invoke-interface {v1, v0, v2, p0}, Landroid/view/WindowManager;->registerTaskFpsCallback(ILjava/util/concurrent/Executor;Landroid/window/TaskFpsCallback;)V

    goto :goto_0

    .line 83
    :cond_0
    invoke-direct {p0}, Lorg/nameless/gamespace/widget/MenuSwitcher;->getWm()Landroid/view/WindowManager;

    move-result-object v0

    iget-object p0, p0, Lorg/nameless/gamespace/widget/MenuSwitcher;->taskFpsCallback:Lorg/nameless/gamespace/widget/MenuSwitcher$taskFpsCallback$1;

    invoke-interface {v0, p0}, Landroid/view/WindowManager;->unregisterTaskFpsCallback(Landroid/window/TaskFpsCallback;)V

    :cond_1
    :goto_0
    return-void
.end method


# virtual methods
.method public final isDragged()Z
    .locals 0

    .line 54
    iget-boolean p0, p0, Lorg/nameless/gamespace/widget/MenuSwitcher;->isDragged:Z

    return p0
.end method

.method protected onDetachedFromWindow()V
    .locals 1

    .line 98
    invoke-super {p0}, Landroid/widget/LinearLayout;->onDetachedFromWindow()V

    .line 99
    invoke-direct {p0}, Lorg/nameless/gamespace/widget/MenuSwitcher;->getWm()Landroid/view/WindowManager;

    move-result-object v0

    iget-object p0, p0, Lorg/nameless/gamespace/widget/MenuSwitcher;->taskFpsCallback:Lorg/nameless/gamespace/widget/MenuSwitcher$taskFpsCallback$1;

    invoke-interface {v0, p0}, Landroid/view/WindowManager;->unregisterTaskFpsCallback(Landroid/window/TaskFpsCallback;)V

    return-void
.end method

.method public final setDragged(Z)V
    .locals 1

    if-eqz p1, :cond_0

    .line 56
    iget-boolean v0, p0, Lorg/nameless/gamespace/widget/MenuSwitcher;->showFps:Z

    if-nez v0, :cond_0

    const v0, 0x7f0800b9

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    invoke-direct {p0, v0}, Lorg/nameless/gamespace/widget/MenuSwitcher;->setMenuIcon(Ljava/lang/Integer;)V

    .line 57
    :cond_0
    iput-boolean p1, p0, Lorg/nameless/gamespace/widget/MenuSwitcher;->isDragged:Z

    return-void
.end method

.method public final setShowFps(Z)V
    .locals 1

    const/4 v0, 0x0

    .line 50
    invoke-direct {p0, v0}, Lorg/nameless/gamespace/widget/MenuSwitcher;->setMenuIcon(Ljava/lang/Integer;)V

    .line 51
    iput-boolean p1, p0, Lorg/nameless/gamespace/widget/MenuSwitcher;->showFps:Z

    return-void
.end method

.method public final updateIconState(ZI)V
    .locals 1

    if-eqz p1, :cond_0

    const/4 v0, 0x0

    goto :goto_0

    .line 61
    :cond_0
    invoke-direct {p0}, Lorg/nameless/gamespace/widget/MenuSwitcher;->getAppSettings()Lorg/nameless/gamespace/data/AppSettings;

    move-result-object v0

    invoke-virtual {v0}, Lorg/nameless/gamespace/data/AppSettings;->getShowFps()Z

    move-result v0

    :goto_0
    invoke-virtual {p0, v0}, Lorg/nameless/gamespace/widget/MenuSwitcher;->setShowFps(Z)V

    if-eqz p1, :cond_1

    const p1, 0x7f0800b3

    goto :goto_1

    :cond_1
    if-lez p2, :cond_2

    const p1, 0x7f0800a7

    goto :goto_1

    :cond_2
    const p1, 0x7f0800a6

    .line 66
    :goto_1
    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    invoke-direct {p0, p1}, Lorg/nameless/gamespace/widget/MenuSwitcher;->setMenuIcon(Ljava/lang/Integer;)V

    .line 67
    invoke-direct {p0}, Lorg/nameless/gamespace/widget/MenuSwitcher;->updateFrameRateBinding()V

    return-void
.end method
