.class final Lorg/nameless/gamespace/gamebar/GameBarService$menuSwitcherButton$3;
.super Lkotlin/jvm/internal/Lambda;
.source "GameBarService.kt"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lorg/nameless/gamespace/gamebar/GameBarService;->menuSwitcherButton()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x18
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lkotlin/jvm/internal/Lambda;",
        "Lkotlin/jvm/functions/Function2<",
        "Ljava/lang/Integer;",
        "Ljava/lang/Integer;",
        "Lkotlin/Unit;",
        ">;"
    }
.end annotation


# instance fields
.field final synthetic this$0:Lorg/nameless/gamespace/gamebar/GameBarService;


# direct methods
.method constructor <init>(Lorg/nameless/gamespace/gamebar/GameBarService;)V
    .locals 0

    iput-object p1, p0, Lorg/nameless/gamespace/gamebar/GameBarService$menuSwitcherButton$3;->this$0:Lorg/nameless/gamespace/gamebar/GameBarService;

    const/4 p1, 0x2

    invoke-direct {p0, p1}, Lkotlin/jvm/internal/Lambda;-><init>(I)V

    return-void
.end method


# virtual methods
.method public bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    .line 344
    check-cast p1, Ljava/lang/Number;

    invoke-virtual {p1}, Ljava/lang/Number;->intValue()I

    move-result p1

    check-cast p2, Ljava/lang/Number;

    invoke-virtual {p2}, Ljava/lang/Number;->intValue()I

    move-result p2

    invoke-virtual {p0, p1, p2}, Lorg/nameless/gamespace/gamebar/GameBarService$menuSwitcherButton$3;->invoke(II)V

    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0
.end method

.method public final invoke(II)V
    .locals 3

    .line 347
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/GameBarService$menuSwitcherButton$3;->this$0:Lorg/nameless/gamespace/gamebar/GameBarService;

    invoke-static {v0}, Lorg/nameless/gamespace/gamebar/GameBarService;->access$getMenuSwitcher$p(Lorg/nameless/gamespace/gamebar/GameBarService;)Lorg/nameless/gamespace/widget/MenuSwitcher;

    move-result-object v0

    const-string v1, "menuSwitcher"

    const/4 v2, 0x0

    if-nez v0, :cond_0

    invoke-static {v1}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    move-object v0, v2

    :cond_0
    invoke-virtual {v0}, Lorg/nameless/gamespace/widget/MenuSwitcher;->isDragged()Z

    move-result v0

    if-nez v0, :cond_3

    .line 348
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/GameBarService$menuSwitcherButton$3;->this$0:Lorg/nameless/gamespace/gamebar/GameBarService;

    invoke-static {v0}, Lorg/nameless/gamespace/gamebar/GameBarService;->access$getMenuSwitcher$p(Lorg/nameless/gamespace/gamebar/GameBarService;)Lorg/nameless/gamespace/widget/MenuSwitcher;

    move-result-object v0

    if-nez v0, :cond_1

    invoke-static {v1}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    move-object v0, v2

    :cond_1
    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Lorg/nameless/gamespace/widget/MenuSwitcher;->setDragged(Z)V

    .line 349
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/GameBarService$menuSwitcherButton$3;->this$0:Lorg/nameless/gamespace/gamebar/GameBarService;

    invoke-static {v0}, Lorg/nameless/gamespace/gamebar/GameBarService;->access$getBarView$p(Lorg/nameless/gamespace/gamebar/GameBarService;)Landroid/widget/LinearLayout;

    move-result-object v0

    if-nez v0, :cond_2

    const-string v0, "barView"

    invoke-static {v0}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    goto :goto_0

    :cond_2
    move-object v2, v0

    :goto_0
    const/4 v0, 0x0

    invoke-virtual {v2, v0}, Landroid/widget/LinearLayout;->setTranslationX(F)V

    .line 351
    :cond_3
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/GameBarService$menuSwitcherButton$3;->this$0:Lorg/nameless/gamespace/gamebar/GameBarService;

    new-instance v1, Lorg/nameless/gamespace/gamebar/GameBarService$menuSwitcherButton$3$1;

    invoke-direct {v1, p1, p2}, Lorg/nameless/gamespace/gamebar/GameBarService$menuSwitcherButton$3$1;-><init>(II)V

    invoke-static {v0, v1}, Lorg/nameless/gamespace/gamebar/GameBarService;->access$updateLayout(Lorg/nameless/gamespace/gamebar/GameBarService;Lkotlin/jvm/functions/Function1;)V

    .line 355
    iget-object p0, p0, Lorg/nameless/gamespace/gamebar/GameBarService$menuSwitcherButton$3;->this$0:Lorg/nameless/gamespace/gamebar/GameBarService;

    invoke-static {p0}, Lorg/nameless/gamespace/gamebar/GameBarService;->access$updateBackground(Lorg/nameless/gamespace/gamebar/GameBarService;)V

    return-void
.end method
