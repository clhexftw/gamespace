.class final Lorg/nameless/gamespace/gamebar/GameBarService$panelButton$2;
.super Ljava/lang/Object;
.source "GameBarService.kt"

# interfaces
.implements Landroid/view/View$OnLongClickListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lorg/nameless/gamespace/gamebar/GameBarService;->panelButton()V
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

    iput-object p1, p0, Lorg/nameless/gamespace/gamebar/GameBarService$panelButton$2;->this$0:Lorg/nameless/gamespace/gamebar/GameBarService;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public final onLongClick(Landroid/view/View;)Z
    .locals 2

    .line 373
    iget-object p1, p0, Lorg/nameless/gamespace/gamebar/GameBarService$panelButton$2;->this$0:Lorg/nameless/gamespace/gamebar/GameBarService;

    new-instance v0, Landroid/content/Intent;

    iget-object p0, p0, Lorg/nameless/gamespace/gamebar/GameBarService$panelButton$2;->this$0:Lorg/nameless/gamespace/gamebar/GameBarService;

    const-class v1, Lorg/nameless/gamespace/settings/SettingsActivity;

    invoke-direct {v0, p0, v1}, Landroid/content/Intent;-><init>(Landroid/content/Context;Ljava/lang/Class;)V

    const/high16 p0, 0x10000000

    invoke-virtual {v0, p0}, Landroid/content/Intent;->setFlags(I)Landroid/content/Intent;

    move-result-object p0

    invoke-virtual {p1, p0}, Landroid/app/Service;->startActivity(Landroid/content/Intent;)V

    const/4 p0, 0x1

    return p0
.end method
