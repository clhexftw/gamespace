.class public final Lorg/nameless/gamespace/widget/PanelView;
.super Landroid/widget/LinearLayout;
.source "PanelView.kt"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lorg/nameless/gamespace/widget/PanelView$tempUpdate;
    }
.end annotation

.annotation system Ldalvik/annotation/SourceDebugExtension;
    value = "SMAP\nPanelView.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PanelView.kt\norg/nameless/gamespace/widget/PanelView\n+ 2 View.kt\nandroidx/core/view/ViewKt\n*L\n1#1,89:1\n66#2,4:90\n38#2:94\n54#2:95\n73#2:96\n*S KotlinDebug\n*F\n+ 1 PanelView.kt\norg/nameless/gamespace/widget/PanelView\n*L\n73#1:90,4\n73#1:94\n73#1:95\n73#1:96\n*E\n"
.end annotation


# instance fields
.field private defaultY:Ljava/lang/Float;

.field private final handler:Landroid/os/Handler;

.field private relativeY:I


# direct methods
.method public constructor <init>(Landroid/content/Context;)V
    .locals 2

    const-string v0, "context"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    const/4 v0, 0x0

    const/4 v1, 0x2

    invoke-direct {p0, p1, v0, v1, v0}, Lorg/nameless/gamespace/widget/PanelView;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 1

    const-string v0, "context"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 39
    invoke-direct {p0, p1, p2}, Landroid/widget/LinearLayout;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    .line 44
    new-instance p2, Landroid/os/Handler;

    invoke-direct {p2}, Landroid/os/Handler;-><init>()V

    iput-object p2, p0, Lorg/nameless/gamespace/widget/PanelView;->handler:Landroid/os/Handler;

    .line 58
    invoke-static {p1}, Landroid/view/LayoutInflater;->from(Landroid/content/Context;)Landroid/view/LayoutInflater;

    move-result-object p1

    const p2, 0x7f0d008f

    const/4 v0, 0x1

    invoke-virtual {p1, p2, p0, v0}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;Z)Landroid/view/View;

    .line 59
    invoke-virtual {p0, v0}, Landroid/widget/LinearLayout;->setClickable(Z)V

    .line 60
    invoke-virtual {p0, v0}, Landroid/widget/LinearLayout;->setFocusable(Z)V

    return-void
.end method

.method public synthetic constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;ILkotlin/jvm/internal/DefaultConstructorMarker;)V
    .locals 0

    and-int/lit8 p3, p3, 0x2

    if-eqz p3, :cond_0

    const/4 p2, 0x0

    .line 37
    :cond_0
    invoke-direct {p0, p1, p2}, Lorg/nameless/gamespace/widget/PanelView;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    return-void
.end method

.method public static final synthetic access$getDefaultY$p(Lorg/nameless/gamespace/widget/PanelView;)Ljava/lang/Float;
    .locals 0

    .line 37
    iget-object p0, p0, Lorg/nameless/gamespace/widget/PanelView;->defaultY:Ljava/lang/Float;

    return-object p0
.end method

.method public static final synthetic access$setDefaultY$p(Lorg/nameless/gamespace/widget/PanelView;Ljava/lang/Float;)V
    .locals 0

    .line 37
    iput-object p1, p0, Lorg/nameless/gamespace/widget/PanelView;->defaultY:Ljava/lang/Float;

    return-void
.end method

.method private final applyRelativeLocation()V
    .locals 6

    .line 70
    invoke-virtual {p0}, Landroid/widget/LinearLayout;->getContext()Landroid/content/Context;

    move-result-object v0

    const-string v1, "window"

    invoke-virtual {v0, v1}, Landroid/content/Context;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    const-string v1, "null cannot be cast to non-null type android.view.WindowManager"

    invoke-static {v0, v1}, Lkotlin/jvm/internal/Intrinsics;->checkNotNull(Ljava/lang/Object;Ljava/lang/String;)V

    check-cast v0, Landroid/view/WindowManager;

    .line 71
    invoke-virtual {p0}, Landroid/widget/LinearLayout;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v1

    const/4 v2, -0x2

    iput v2, v1, Landroid/view/ViewGroup$LayoutParams;->height:I

    .line 66
    invoke-static {p0}, Landroidx/core/view/ViewCompat;->isLaidOut(Landroid/view/View;)Z

    move-result v1

    if-eqz v1, :cond_3

    invoke-virtual {p0}, Landroid/view/View;->isLayoutRequested()Z

    move-result v1

    if-nez v1, :cond_3

    .line 74
    invoke-static {p0}, Lorg/nameless/gamespace/widget/PanelView;->access$getDefaultY$p(Lorg/nameless/gamespace/widget/PanelView;)Ljava/lang/Float;

    move-result-object v1

    if-nez v1, :cond_0

    .line 75
    invoke-virtual {p0}, Landroid/widget/LinearLayout;->getY()F

    move-result v1

    invoke-static {v1}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object v1

    invoke-static {p0, v1}, Lorg/nameless/gamespace/widget/PanelView;->access$setDefaultY$p(Lorg/nameless/gamespace/widget/PanelView;Ljava/lang/Float;)V

    .line 77
    :cond_0
    invoke-static {v0}, Lorg/nameless/gamespace/utils/ExtensionsKt;->isPortrait(Landroid/view/WindowManager;)Z

    move-result v0

    if-eqz v0, :cond_1

    .line 78
    invoke-virtual {p0}, Landroid/widget/LinearLayout;->getRootWindowInsets()Landroid/view/WindowInsets;

    move-result-object v0

    invoke-static {}, Landroid/view/WindowInsets$Type;->systemBars()I

    move-result v1

    invoke-virtual {v0, v1}, Landroid/view/WindowInsets;->getInsets(I)Landroid/graphics/Insets;

    move-result-object v0

    .line 79
    iget v1, v0, Landroid/graphics/Insets;->top:I

    const/16 v2, 0x10

    invoke-static {v2}, Lorg/nameless/gamespace/utils/ExtensionsKt;->getDp(I)I

    move-result v3

    add-int/2addr v1, v3

    .line 80
    iget v3, v0, Landroid/graphics/Insets;->top:I

    invoke-virtual {p0}, Landroid/widget/LinearLayout;->getParent()Landroid/view/ViewParent;

    move-result-object v4

    const-string v5, "null cannot be cast to non-null type android.view.View"

    invoke-static {v4, v5}, Lkotlin/jvm/internal/Intrinsics;->checkNotNull(Ljava/lang/Object;Ljava/lang/String;)V

    check-cast v4, Landroid/view/View;

    invoke-virtual {v4}, Landroid/view/View;->getHeight()I

    move-result v4

    add-int/2addr v3, v4

    iget v0, v0, Landroid/graphics/Insets;->bottom:I

    sub-int/2addr v3, v0

    invoke-virtual {p0}, Landroid/widget/LinearLayout;->getHeight()I

    move-result v0

    sub-int/2addr v3, v0

    invoke-static {v2}, Lorg/nameless/gamespace/utils/ExtensionsKt;->getDp(I)I

    move-result v0

    sub-int/2addr v3, v0

    .line 81
    invoke-virtual {p0}, Lorg/nameless/gamespace/widget/PanelView;->getRelativeY()I

    move-result v0

    invoke-static {v0, v1}, Ljava/lang/Math;->max(II)I

    move-result v0

    invoke-static {v0, v3}, Ljava/lang/Math;->min(II)I

    move-result v0

    int-to-float v0, v0

    goto :goto_0

    .line 83
    :cond_1
    invoke-static {p0}, Lorg/nameless/gamespace/widget/PanelView;->access$getDefaultY$p(Lorg/nameless/gamespace/widget/PanelView;)Ljava/lang/Float;

    move-result-object v0

    if-eqz v0, :cond_2

    invoke-virtual {v0}, Ljava/lang/Float;->floatValue()F

    move-result v0

    goto :goto_0

    :cond_2
    const/high16 v0, 0x41800000    # 16.0f

    .line 77
    :goto_0
    invoke-virtual {p0, v0}, Landroid/widget/LinearLayout;->setY(F)V

    goto :goto_1

    .line 38
    :cond_3
    new-instance v1, Lorg/nameless/gamespace/widget/PanelView$applyRelativeLocation$$inlined$doOnLayout$1;

    invoke-direct {v1, p0, v0}, Lorg/nameless/gamespace/widget/PanelView$applyRelativeLocation$$inlined$doOnLayout$1;-><init>(Lorg/nameless/gamespace/widget/PanelView;Landroid/view/WindowManager;)V

    invoke-virtual {p0, v1}, Landroid/view/View;->addOnLayoutChangeListener(Landroid/view/View$OnLayoutChangeListener;)V

    :goto_1
    return-void
.end method


# virtual methods
.method public final getRelativeY()I
    .locals 0

    .line 42
    iget p0, p0, Lorg/nameless/gamespace/widget/PanelView;->relativeY:I

    return p0
.end method

.method protected onAttachedToWindow()V
    .locals 2

    .line 64
    invoke-super {p0}, Landroid/widget/LinearLayout;->onAttachedToWindow()V

    .line 65
    invoke-direct {p0}, Lorg/nameless/gamespace/widget/PanelView;->applyRelativeLocation()V

    .line 66
    iget-object v0, p0, Lorg/nameless/gamespace/widget/PanelView;->handler:Landroid/os/Handler;

    new-instance v1, Lorg/nameless/gamespace/widget/PanelView$tempUpdate;

    invoke-direct {v1, p0}, Lorg/nameless/gamespace/widget/PanelView$tempUpdate;-><init>(Lorg/nameless/gamespace/widget/PanelView;)V

    invoke-virtual {v0, v1}, Landroid/os/Handler;->post(Ljava/lang/Runnable;)Z

    return-void
.end method

.method public final setRelativeY(I)V
    .locals 0

    .line 42
    iput p1, p0, Lorg/nameless/gamespace/widget/PanelView;->relativeY:I

    return-void
.end method
