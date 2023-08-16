.class public final Lorg/nameless/gamespace/gamebar/SessionService$gameBarConnection$1;
.super Ljava/lang/Object;
.source "SessionService.kt"

# interfaces
.implements Landroid/content/ServiceConnection;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lorg/nameless/gamespace/gamebar/SessionService;-><init>()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lorg/nameless/gamespace/gamebar/SessionService;


# direct methods
.method constructor <init>(Lorg/nameless/gamespace/gamebar/SessionService;)V
    .locals 0

    iput-object p1, p0, Lorg/nameless/gamespace/gamebar/SessionService$gameBarConnection$1;->this$0:Lorg/nameless/gamespace/gamebar/SessionService;

    .line 67
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onServiceConnected(Landroid/content/ComponentName;Landroid/os/IBinder;)V
    .locals 1

    .line 69
    iget-object p1, p0, Lorg/nameless/gamespace/gamebar/SessionService$gameBarConnection$1;->this$0:Lorg/nameless/gamespace/gamebar/SessionService;

    const/4 v0, 0x1

    invoke-static {p1, v0}, Lorg/nameless/gamespace/gamebar/SessionService;->access$setBarConnected$p(Lorg/nameless/gamespace/gamebar/SessionService;Z)V

    .line 70
    iget-object p1, p0, Lorg/nameless/gamespace/gamebar/SessionService$gameBarConnection$1;->this$0:Lorg/nameless/gamespace/gamebar/SessionService;

    const-string v0, "null cannot be cast to non-null type org.nameless.gamespace.gamebar.GameBarService.GameBarBinder"

    invoke-static {p2, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNull(Ljava/lang/Object;Ljava/lang/String;)V

    check-cast p2, Lorg/nameless/gamespace/gamebar/GameBarService$GameBarBinder;

    invoke-virtual {p2}, Lorg/nameless/gamespace/gamebar/GameBarService$GameBarBinder;->getService()Lorg/nameless/gamespace/gamebar/GameBarService;

    move-result-object p2

    invoke-static {p1, p2}, Lorg/nameless/gamespace/gamebar/SessionService;->access$setGameBar$p(Lorg/nameless/gamespace/gamebar/SessionService;Lorg/nameless/gamespace/gamebar/GameBarService;)V

    .line 71
    iget-object p0, p0, Lorg/nameless/gamespace/gamebar/SessionService$gameBarConnection$1;->this$0:Lorg/nameless/gamespace/gamebar/SessionService;

    invoke-static {p0}, Lorg/nameless/gamespace/gamebar/SessionService;->access$onGameBarReady(Lorg/nameless/gamespace/gamebar/SessionService;)V

    return-void
.end method

.method public onServiceDisconnected(Landroid/content/ComponentName;)V
    .locals 1

    .line 75
    iget-object p1, p0, Lorg/nameless/gamespace/gamebar/SessionService$gameBarConnection$1;->this$0:Lorg/nameless/gamespace/gamebar/SessionService;

    const/4 v0, 0x0

    invoke-static {p1, v0}, Lorg/nameless/gamespace/gamebar/SessionService;->access$setBarConnected$p(Lorg/nameless/gamespace/gamebar/SessionService;Z)V

    .line 76
    iget-object p0, p0, Lorg/nameless/gamespace/gamebar/SessionService$gameBarConnection$1;->this$0:Lorg/nameless/gamespace/gamebar/SessionService;

    invoke-virtual {p0}, Landroid/app/Service;->stopSelf()V

    return-void
.end method
