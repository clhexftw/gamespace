.class final Lorg/nameless/gamespace/gamebar/GameBarService$menuSwitcherButton$2;
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
        "Landroid/graphics/Point;",
        ">;"
    }
.end annotation


# instance fields
.field final synthetic this$0:Lorg/nameless/gamespace/gamebar/GameBarService;


# direct methods
.method constructor <init>(Lorg/nameless/gamespace/gamebar/GameBarService;)V
    .locals 0

    iput-object p1, p0, Lorg/nameless/gamespace/gamebar/GameBarService$menuSwitcherButton$2;->this$0:Lorg/nameless/gamespace/gamebar/GameBarService;

    const/4 p1, 0x0

    invoke-direct {p0, p1}, Lkotlin/jvm/internal/Lambda;-><init>(I)V

    return-void
.end method


# virtual methods
.method public final invoke()Landroid/graphics/Point;
    .locals 2

    .line 345
    new-instance v0, Landroid/graphics/Point;

    iget-object v1, p0, Lorg/nameless/gamespace/gamebar/GameBarService$menuSwitcherButton$2;->this$0:Lorg/nameless/gamespace/gamebar/GameBarService;

    invoke-static {v1}, Lorg/nameless/gamespace/gamebar/GameBarService;->access$getBarLayoutParam$p(Lorg/nameless/gamespace/gamebar/GameBarService;)Landroid/view/WindowManager$LayoutParams;

    move-result-object v1

    iget v1, v1, Landroid/view/WindowManager$LayoutParams;->x:I

    iget-object p0, p0, Lorg/nameless/gamespace/gamebar/GameBarService$menuSwitcherButton$2;->this$0:Lorg/nameless/gamespace/gamebar/GameBarService;

    invoke-static {p0}, Lorg/nameless/gamespace/gamebar/GameBarService;->access$getBarLayoutParam$p(Lorg/nameless/gamespace/gamebar/GameBarService;)Landroid/view/WindowManager$LayoutParams;

    move-result-object p0

    iget p0, p0, Landroid/view/WindowManager$LayoutParams;->y:I

    invoke-direct {v0, v1, p0}, Landroid/graphics/Point;-><init>(II)V

    return-object v0
.end method

.method public bridge synthetic invoke()Ljava/lang/Object;
    .locals 0

    .line 344
    invoke-virtual {p0}, Lorg/nameless/gamespace/gamebar/GameBarService$menuSwitcherButton$2;->invoke()Landroid/graphics/Point;

    move-result-object p0

    return-object p0
.end method
