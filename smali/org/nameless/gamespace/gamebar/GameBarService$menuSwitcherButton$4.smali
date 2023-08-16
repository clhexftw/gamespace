.class final Lorg/nameless/gamespace/gamebar/GameBarService$menuSwitcherButton$4;
.super Lkotlin/jvm/internal/Lambda;
.source "GameBarService.kt"

# interfaces
.implements Lkotlin/jvm/functions/Function0;


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
        "Lkotlin/jvm/functions/Function0<",
        "Lkotlin/Unit;",
        ">;"
    }
.end annotation


# instance fields
.field final synthetic this$0:Lorg/nameless/gamespace/gamebar/GameBarService;


# direct methods
.method constructor <init>(Lorg/nameless/gamespace/gamebar/GameBarService;)V
    .locals 0

    iput-object p1, p0, Lorg/nameless/gamespace/gamebar/GameBarService$menuSwitcherButton$4;->this$0:Lorg/nameless/gamespace/gamebar/GameBarService;

    const/4 p1, 0x0

    invoke-direct {p0, p1}, Lkotlin/jvm/internal/Lambda;-><init>(I)V

    return-void
.end method


# virtual methods
.method public bridge synthetic invoke()Ljava/lang/Object;
    .locals 0

    .line 344
    invoke-virtual {p0}, Lorg/nameless/gamespace/gamebar/GameBarService$menuSwitcherButton$4;->invoke()V

    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0
.end method

.method public final invoke()V
    .locals 2

    .line 358
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/GameBarService$menuSwitcherButton$4;->this$0:Lorg/nameless/gamespace/gamebar/GameBarService;

    invoke-static {v0}, Lorg/nameless/gamespace/gamebar/GameBarService;->access$getMenuSwitcher$p(Lorg/nameless/gamespace/gamebar/GameBarService;)Lorg/nameless/gamespace/widget/MenuSwitcher;

    move-result-object v0

    if-nez v0, :cond_0

    const-string v0, "menuSwitcher"

    invoke-static {v0}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    const/4 v0, 0x0

    :cond_0
    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Lorg/nameless/gamespace/widget/MenuSwitcher;->setDragged(Z)V

    .line 359
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/GameBarService$menuSwitcherButton$4;->this$0:Lorg/nameless/gamespace/gamebar/GameBarService;

    invoke-static {v0}, Lorg/nameless/gamespace/gamebar/GameBarService;->access$dockCollapsedMenu(Lorg/nameless/gamespace/gamebar/GameBarService;)V

    .line 360
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/GameBarService$menuSwitcherButton$4;->this$0:Lorg/nameless/gamespace/gamebar/GameBarService;

    invoke-static {v0}, Lorg/nameless/gamespace/gamebar/GameBarService;->access$updateBackground(Lorg/nameless/gamespace/gamebar/GameBarService;)V

    .line 361
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/GameBarService$menuSwitcherButton$4;->this$0:Lorg/nameless/gamespace/gamebar/GameBarService;

    invoke-virtual {v0}, Lorg/nameless/gamespace/gamebar/GameBarService;->getAppSettings()Lorg/nameless/gamespace/data/AppSettings;

    move-result-object v0

    iget-object v1, p0, Lorg/nameless/gamespace/gamebar/GameBarService$menuSwitcherButton$4;->this$0:Lorg/nameless/gamespace/gamebar/GameBarService;

    invoke-static {v1}, Lorg/nameless/gamespace/gamebar/GameBarService;->access$getBarLayoutParam$p(Lorg/nameless/gamespace/gamebar/GameBarService;)Landroid/view/WindowManager$LayoutParams;

    move-result-object v1

    iget v1, v1, Landroid/view/WindowManager$LayoutParams;->x:I

    invoke-virtual {v0, v1}, Lorg/nameless/gamespace/data/AppSettings;->setX(I)V

    .line 362
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/GameBarService$menuSwitcherButton$4;->this$0:Lorg/nameless/gamespace/gamebar/GameBarService;

    invoke-virtual {v0}, Lorg/nameless/gamespace/gamebar/GameBarService;->getAppSettings()Lorg/nameless/gamespace/data/AppSettings;

    move-result-object v0

    iget-object p0, p0, Lorg/nameless/gamespace/gamebar/GameBarService$menuSwitcherButton$4;->this$0:Lorg/nameless/gamespace/gamebar/GameBarService;

    invoke-static {p0}, Lorg/nameless/gamespace/gamebar/GameBarService;->access$getBarLayoutParam$p(Lorg/nameless/gamespace/gamebar/GameBarService;)Landroid/view/WindowManager$LayoutParams;

    move-result-object p0

    iget p0, p0, Landroid/view/WindowManager$LayoutParams;->y:I

    invoke-virtual {v0, p0}, Lorg/nameless/gamespace/data/AppSettings;->setY(I)V

    return-void
.end method
