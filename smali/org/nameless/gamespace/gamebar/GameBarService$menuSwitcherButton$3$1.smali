.class final Lorg/nameless/gamespace/gamebar/GameBarService$menuSwitcherButton$3$1;
.super Lkotlin/jvm/internal/Lambda;
.source "GameBarService.kt"

# interfaces
.implements Lkotlin/jvm/functions/Function1;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lorg/nameless/gamespace/gamebar/GameBarService$menuSwitcherButton$3;->invoke(II)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x18
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lkotlin/jvm/internal/Lambda;",
        "Lkotlin/jvm/functions/Function1<",
        "Landroid/view/WindowManager$LayoutParams;",
        "Lkotlin/Unit;",
        ">;"
    }
.end annotation


# instance fields
.field final synthetic $x:I

.field final synthetic $y:I


# direct methods
.method constructor <init>(II)V
    .locals 0

    iput p1, p0, Lorg/nameless/gamespace/gamebar/GameBarService$menuSwitcherButton$3$1;->$x:I

    iput p2, p0, Lorg/nameless/gamespace/gamebar/GameBarService$menuSwitcherButton$3$1;->$y:I

    const/4 p1, 0x1

    invoke-direct {p0, p1}, Lkotlin/jvm/internal/Lambda;-><init>(I)V

    return-void
.end method


# virtual methods
.method public bridge synthetic invoke(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    .line 351
    check-cast p1, Landroid/view/WindowManager$LayoutParams;

    invoke-virtual {p0, p1}, Lorg/nameless/gamespace/gamebar/GameBarService$menuSwitcherButton$3$1;->invoke(Landroid/view/WindowManager$LayoutParams;)V

    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0
.end method

.method public final invoke(Landroid/view/WindowManager$LayoutParams;)V
    .locals 1

    const-string v0, "it"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 352
    iget v0, p0, Lorg/nameless/gamespace/gamebar/GameBarService$menuSwitcherButton$3$1;->$x:I

    iput v0, p1, Landroid/view/WindowManager$LayoutParams;->x:I

    .line 353
    iget p0, p0, Lorg/nameless/gamespace/gamebar/GameBarService$menuSwitcherButton$3$1;->$y:I

    iput p0, p1, Landroid/view/WindowManager$LayoutParams;->y:I

    return-void
.end method
