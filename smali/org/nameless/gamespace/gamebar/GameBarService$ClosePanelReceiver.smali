.class public final Lorg/nameless/gamespace/gamebar/GameBarService$ClosePanelReceiver;
.super Landroid/content/BroadcastReceiver;
.source "GameBarService.kt"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/nameless/gamespace/gamebar/GameBarService;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x11
    name = "ClosePanelReceiver"
.end annotation


# instance fields
.field final synthetic this$0:Lorg/nameless/gamespace/gamebar/GameBarService;


# direct methods
.method public constructor <init>(Lorg/nameless/gamespace/gamebar/GameBarService;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()V"
        }
    .end annotation

    .line 168
    iput-object p1, p0, Lorg/nameless/gamespace/gamebar/GameBarService$ClosePanelReceiver;->this$0:Lorg/nameless/gamespace/gamebar/GameBarService;

    invoke-direct {p0}, Landroid/content/BroadcastReceiver;-><init>()V

    return-void
.end method


# virtual methods
.method public onReceive(Landroid/content/Context;Landroid/content/Intent;)V
    .locals 1

    const-string v0, "context"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    const-string p1, "intent"

    invoke-static {p2, p1}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 170
    iget-object p1, p0, Lorg/nameless/gamespace/gamebar/GameBarService$ClosePanelReceiver;->this$0:Lorg/nameless/gamespace/gamebar/GameBarService;

    const/4 p2, 0x0

    invoke-static {p1, p2}, Lorg/nameless/gamespace/gamebar/GameBarService;->access$setShowPanel(Lorg/nameless/gamespace/gamebar/GameBarService;Z)V

    .line 171
    iget-object p0, p0, Lorg/nameless/gamespace/gamebar/GameBarService$ClosePanelReceiver;->this$0:Lorg/nameless/gamespace/gamebar/GameBarService;

    invoke-static {p0, p2}, Lorg/nameless/gamespace/gamebar/GameBarService;->access$setBarExpanded(Lorg/nameless/gamespace/gamebar/GameBarService;Z)V

    return-void
.end method
