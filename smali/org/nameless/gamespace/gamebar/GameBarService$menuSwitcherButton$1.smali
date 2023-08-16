.class final Lorg/nameless/gamespace/gamebar/GameBarService$menuSwitcherButton$1;
.super Ljava/lang/Object;
.source "GameBarService.kt"

# interfaces
.implements Landroid/view/View$OnClickListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lorg/nameless/gamespace/gamebar/GameBarService;->menuSwitcherButton()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x18
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lorg/nameless/gamespace/gamebar/GameBarService;


# direct methods
.method constructor <init>(Lorg/nameless/gamespace/gamebar/GameBarService;)V
    .locals 0

    iput-object p1, p0, Lorg/nameless/gamespace/gamebar/GameBarService$menuSwitcherButton$1;->this$0:Lorg/nameless/gamespace/gamebar/GameBarService;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public final onClick(Landroid/view/View;)V
    .locals 0

    .line 342
    iget-object p0, p0, Lorg/nameless/gamespace/gamebar/GameBarService$menuSwitcherButton$1;->this$0:Lorg/nameless/gamespace/gamebar/GameBarService;

    invoke-static {p0}, Lorg/nameless/gamespace/gamebar/GameBarService;->access$getBarExpanded$p(Lorg/nameless/gamespace/gamebar/GameBarService;)Z

    move-result p1

    xor-int/lit8 p1, p1, 0x1

    invoke-static {p0, p1}, Lorg/nameless/gamespace/gamebar/GameBarService;->access$setBarExpanded(Lorg/nameless/gamespace/gamebar/GameBarService;Z)V

    return-void
.end method
