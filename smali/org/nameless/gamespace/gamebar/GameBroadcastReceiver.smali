.class public final Lorg/nameless/gamespace/gamebar/GameBroadcastReceiver;
.super Landroid/content/BroadcastReceiver;
.source "GameBroadcastReceiver.kt"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lorg/nameless/gamespace/gamebar/GameBroadcastReceiver$Companion;
    }
.end annotation


# static fields
.field public static final Companion:Lorg/nameless/gamespace/gamebar/GameBroadcastReceiver$Companion;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    new-instance v0, Lorg/nameless/gamespace/gamebar/GameBroadcastReceiver$Companion;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lorg/nameless/gamespace/gamebar/GameBroadcastReceiver$Companion;-><init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V

    sput-object v0, Lorg/nameless/gamespace/gamebar/GameBroadcastReceiver;->Companion:Lorg/nameless/gamespace/gamebar/GameBroadcastReceiver$Companion;

    return-void
.end method

.method public constructor <init>()V
    .locals 0

    .line 22
    invoke-direct {p0}, Landroid/content/BroadcastReceiver;-><init>()V

    return-void
.end method

.method private final onGameStart(Landroid/content/Context;Landroid/content/Intent;)V
    .locals 1

    const-string p0, "package_name"

    .line 31
    invoke-virtual {p2, p0}, Landroid/content/Intent;->getStringExtra(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    .line 32
    sget-object p2, Lorg/nameless/gamespace/gamebar/SessionService;->Companion:Lorg/nameless/gamespace/gamebar/SessionService$Companion;

    const-string v0, "app"

    invoke-static {p0, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullExpressionValue(Ljava/lang/Object;Ljava/lang/String;)V

    invoke-virtual {p2, p1, p0}, Lorg/nameless/gamespace/gamebar/SessionService$Companion;->start(Landroid/content/Context;Ljava/lang/String;)Landroid/content/ComponentName;

    return-void
.end method

.method private final onGameStop(Landroid/content/Context;)V
    .locals 0

    .line 36
    sget-object p0, Lorg/nameless/gamespace/gamebar/SessionService;->Companion:Lorg/nameless/gamespace/gamebar/SessionService$Companion;

    invoke-virtual {p0, p1}, Lorg/nameless/gamespace/gamebar/SessionService$Companion;->stop(Landroid/content/Context;)Landroid/content/ComponentName;

    return-void
.end method


# virtual methods
.method public onReceive(Landroid/content/Context;Landroid/content/Intent;)V
    .locals 2

    const-string v0, "context"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    const-string v0, "intent"

    invoke-static {p2, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 24
    invoke-virtual {p2}, Landroid/content/Intent;->getAction()Ljava/lang/String;

    move-result-object v0

    const-string v1, "org.nameless.gamespace.action.GAME_START"

    .line 25
    invoke-static {v0, v1}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_0

    invoke-direct {p0, p1, p2}, Lorg/nameless/gamespace/gamebar/GameBroadcastReceiver;->onGameStart(Landroid/content/Context;Landroid/content/Intent;)V

    goto :goto_0

    :cond_0
    const-string p2, "org.nameless.gamespace.action.GAME_STOP"

    .line 26
    invoke-static {v0, p2}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result p2

    if-eqz p2, :cond_1

    invoke-direct {p0, p1}, Lorg/nameless/gamespace/gamebar/GameBroadcastReceiver;->onGameStop(Landroid/content/Context;)V

    :cond_1
    :goto_0
    return-void
.end method
