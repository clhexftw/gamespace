.class public final Lorg/nameless/gamespace/gamebar/SessionService$Companion;
.super Ljava/lang/Object;
.source "SessionService.kt"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/nameless/gamespace/gamebar/SessionService;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "Companion"
.end annotation

.annotation system Ldalvik/annotation/SourceDebugExtension;
    value = "SMAP\nSessionService.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SessionService.kt\norg/nameless/gamespace/gamebar/SessionService$Companion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,209:1\n1#2:210\n*E\n"
.end annotation


# direct methods
.method private constructor <init>()V
    .locals 0

    .line 187
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public synthetic constructor <init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V
    .locals 0

    invoke-direct {p0}, Lorg/nameless/gamespace/gamebar/SessionService$Companion;-><init>()V

    return-void
.end method


# virtual methods
.method public final isRunning()Z
    .locals 0

    .line 192
    invoke-static {}, Lorg/nameless/gamespace/gamebar/SessionService;->access$isRunning$cp()Z

    move-result p0

    return p0
.end method

.method public final start(Landroid/content/Context;Ljava/lang/String;)Landroid/content/ComponentName;
    .locals 1

    const-string p0, "context"

    invoke-static {p1, p0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    const-string p0, "app"

    invoke-static {p2, p0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 195
    new-instance p0, Landroid/content/Intent;

    const-class v0, Lorg/nameless/gamespace/gamebar/SessionService;

    invoke-direct {p0, p1, v0}, Landroid/content/Intent;-><init>(Landroid/content/Context;Ljava/lang/Class;)V

    const-string v0, "game_start"

    .line 197
    invoke-virtual {p0, v0}, Landroid/content/Intent;->setAction(Ljava/lang/String;)Landroid/content/Intent;

    const-string v0, "package_name"

    .line 198
    invoke-virtual {p0, v0, p2}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;

    .line 200
    sget-object p2, Lorg/nameless/gamespace/gamebar/SessionService;->Companion:Lorg/nameless/gamespace/gamebar/SessionService$Companion;

    invoke-virtual {p2}, Lorg/nameless/gamespace/gamebar/SessionService$Companion;->isRunning()Z

    move-result p2

    xor-int/lit8 p2, p2, 0x1

    const/4 v0, 0x0

    if-eqz p2, :cond_0

    goto :goto_0

    :cond_0
    move-object p0, v0

    :goto_0
    if-eqz p0, :cond_1

    .line 201
    sget-object p2, Landroid/os/UserHandle;->CURRENT:Landroid/os/UserHandle;

    invoke-virtual {p1, p0, p2}, Landroid/content/Context;->startServiceAsUser(Landroid/content/Intent;Landroid/os/UserHandle;)Landroid/content/ComponentName;

    move-result-object v0

    :cond_1
    return-object v0
.end method

.method public final stop(Landroid/content/Context;)Landroid/content/ComponentName;
    .locals 2

    const-string p0, "context"

    invoke-static {p1, p0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 203
    new-instance p0, Landroid/content/Intent;

    const-class v0, Lorg/nameless/gamespace/gamebar/SessionService;

    invoke-direct {p0, p1, v0}, Landroid/content/Intent;-><init>(Landroid/content/Context;Ljava/lang/Class;)V

    const-string v0, "game_stop"

    .line 204
    invoke-virtual {p0, v0}, Landroid/content/Intent;->setAction(Ljava/lang/String;)Landroid/content/Intent;

    .line 205
    sget-object v0, Lorg/nameless/gamespace/gamebar/SessionService;->Companion:Lorg/nameless/gamespace/gamebar/SessionService$Companion;

    invoke-virtual {v0}, Lorg/nameless/gamespace/gamebar/SessionService$Companion;->isRunning()Z

    move-result v0

    const/4 v1, 0x0

    if-eqz v0, :cond_0

    goto :goto_0

    :cond_0
    move-object p0, v1

    :goto_0
    if-eqz p0, :cond_1

    .line 206
    sget-object v0, Landroid/os/UserHandle;->CURRENT:Landroid/os/UserHandle;

    invoke-virtual {p1, p0, v0}, Landroid/content/Context;->startServiceAsUser(Landroid/content/Intent;Landroid/os/UserHandle;)Landroid/content/ComponentName;

    move-result-object v1

    :cond_1
    return-object v1
.end method
