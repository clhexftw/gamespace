.class public final Lorg/nameless/gamespace/widget/PanelView$applyRelativeLocation$$inlined$doOnLayout$1;
.super Ljava/lang/Object;
.source "View.kt"

# interfaces
.implements Landroid/view/View$OnLayoutChangeListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lorg/nameless/gamespace/widget/PanelView;->applyRelativeLocation()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = null
.end annotation

.annotation system Ldalvik/annotation/SourceDebugExtension;
    value = "SMAP\nView.kt\nKotlin\n*S Kotlin\n*F\n+ 1 View.kt\nandroidx/core/view/ViewKt$doOnNextLayout$1\n+ 2 View.kt\nandroidx/core/view/ViewKt\n+ 3 PanelView.kt\norg/nameless/gamespace/widget/PanelView\n*L\n1#1,411:1\n70#2:412\n71#2:426\n74#3,13:413\n*E\n"
.end annotation


# instance fields
.field final synthetic $wm$inlined:Landroid/view/WindowManager;

.field final synthetic this$0:Lorg/nameless/gamespace/widget/PanelView;


# direct methods
.method public constructor <init>(Lorg/nameless/gamespace/widget/PanelView;Landroid/view/WindowManager;)V
    .locals 0

    iput-object p1, p0, Lorg/nameless/gamespace/widget/PanelView$applyRelativeLocation$$inlined$doOnLayout$1;->this$0:Lorg/nameless/gamespace/widget/PanelView;

    iput-object p2, p0, Lorg/nameless/gamespace/widget/PanelView$applyRelativeLocation$$inlined$doOnLayout$1;->$wm$inlined:Landroid/view/WindowManager;

    .line 38
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onLayoutChange(Landroid/view/View;IIIIIIII)V
    .locals 0

    const-string p2, "view"

    invoke-static {p1, p2}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 50
    invoke-virtual {p1, p0}, Landroid/view/View;->removeOnLayoutChangeListener(Landroid/view/View$OnLayoutChangeListener;)V

    .line 74
    iget-object p1, p0, Lorg/nameless/gamespace/widget/PanelView$applyRelativeLocation$$inlined$doOnLayout$1;->this$0:Lorg/nameless/gamespace/widget/PanelView;

    invoke-static {p1}, Lorg/nameless/gamespace/widget/PanelView;->access$getDefaultY$p(Lorg/nameless/gamespace/widget/PanelView;)Ljava/lang/Float;

    move-result-object p1

    if-nez p1, :cond_0

    .line 75
    iget-object p1, p0, Lorg/nameless/gamespace/widget/PanelView$applyRelativeLocation$$inlined$doOnLayout$1;->this$0:Lorg/nameless/gamespace/widget/PanelView;

    invoke-virtual {p1}, Landroid/widget/LinearLayout;->getY()F

    move-result p2

    invoke-static {p2}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object p2

    invoke-static {p1, p2}, Lorg/nameless/gamespace/widget/PanelView;->access$setDefaultY$p(Lorg/nameless/gamespace/widget/PanelView;Ljava/lang/Float;)V

    .line 77
    :cond_0
    iget-object p1, p0, Lorg/nameless/gamespace/widget/PanelView$applyRelativeLocation$$inlined$doOnLayout$1;->this$0:Lorg/nameless/gamespace/widget/PanelView;

    iget-object p2, p0, Lorg/nameless/gamespace/widget/PanelView$applyRelativeLocation$$inlined$doOnLayout$1;->$wm$inlined:Landroid/view/WindowManager;

    invoke-static {p2}, Lorg/nameless/gamespace/utils/ExtensionsKt;->isPortrait(Landroid/view/WindowManager;)Z

    move-result p2

    if-eqz p2, :cond_1

    .line 78
    iget-object p2, p0, Lorg/nameless/gamespace/widget/PanelView$applyRelativeLocation$$inlined$doOnLayout$1;->this$0:Lorg/nameless/gamespace/widget/PanelView;

    invoke-virtual {p2}, Landroid/widget/LinearLayout;->getRootWindowInsets()Landroid/view/WindowInsets;

    move-result-object p2

    invoke-static {}, Landroid/view/WindowInsets$Type;->systemBars()I

    move-result p3

    invoke-virtual {p2, p3}, Landroid/view/WindowInsets;->getInsets(I)Landroid/graphics/Insets;

    move-result-object p2

    .line 79
    iget p3, p2, Landroid/graphics/Insets;->top:I

    const/16 p4, 0x10

    invoke-static {p4}, Lorg/nameless/gamespace/utils/ExtensionsKt;->getDp(I)I

    move-result p5

    add-int/2addr p3, p5

    .line 80
    iget p5, p2, Landroid/graphics/Insets;->top:I

    iget-object p6, p0, Lorg/nameless/gamespace/widget/PanelView$applyRelativeLocation$$inlined$doOnLayout$1;->this$0:Lorg/nameless/gamespace/widget/PanelView;

    invoke-virtual {p6}, Landroid/widget/LinearLayout;->getParent()Landroid/view/ViewParent;

    move-result-object p6

    const-string p7, "null cannot be cast to non-null type android.view.View"

    invoke-static {p6, p7}, Lkotlin/jvm/internal/Intrinsics;->checkNotNull(Ljava/lang/Object;Ljava/lang/String;)V

    check-cast p6, Landroid/view/View;

    invoke-virtual {p6}, Landroid/view/View;->getHeight()I

    move-result p6

    add-int/2addr p5, p6

    iget p2, p2, Landroid/graphics/Insets;->bottom:I

    sub-int/2addr p5, p2

    iget-object p2, p0, Lorg/nameless/gamespace/widget/PanelView$applyRelativeLocation$$inlined$doOnLayout$1;->this$0:Lorg/nameless/gamespace/widget/PanelView;

    invoke-virtual {p2}, Landroid/widget/LinearLayout;->getHeight()I

    move-result p2

    sub-int/2addr p5, p2

    invoke-static {p4}, Lorg/nameless/gamespace/utils/ExtensionsKt;->getDp(I)I

    move-result p2

    sub-int/2addr p5, p2

    .line 81
    iget-object p0, p0, Lorg/nameless/gamespace/widget/PanelView$applyRelativeLocation$$inlined$doOnLayout$1;->this$0:Lorg/nameless/gamespace/widget/PanelView;

    invoke-virtual {p0}, Lorg/nameless/gamespace/widget/PanelView;->getRelativeY()I

    move-result p0

    invoke-static {p0, p3}, Ljava/lang/Math;->max(II)I

    move-result p0

    invoke-static {p0, p5}, Ljava/lang/Math;->min(II)I

    move-result p0

    int-to-float p0, p0

    goto :goto_0

    .line 83
    :cond_1
    iget-object p0, p0, Lorg/nameless/gamespace/widget/PanelView$applyRelativeLocation$$inlined$doOnLayout$1;->this$0:Lorg/nameless/gamespace/widget/PanelView;

    invoke-static {p0}, Lorg/nameless/gamespace/widget/PanelView;->access$getDefaultY$p(Lorg/nameless/gamespace/widget/PanelView;)Ljava/lang/Float;

    move-result-object p0

    if-eqz p0, :cond_2

    invoke-virtual {p0}, Ljava/lang/Float;->floatValue()F

    move-result p0

    goto :goto_0

    :cond_2
    const/high16 p0, 0x41800000    # 16.0f

    .line 77
    :goto_0
    invoke-virtual {p1, p0}, Landroid/widget/LinearLayout;->setY(F)V

    return-void
.end method
