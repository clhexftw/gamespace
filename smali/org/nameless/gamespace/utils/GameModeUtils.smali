.class public final Lorg/nameless/gamespace/utils/GameModeUtils;
.super Ljava/lang/Object;
.source "GameModeUtils.kt"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lorg/nameless/gamespace/utils/GameModeUtils$Companion;
    }
.end annotation

.annotation system Ldalvik/annotation/SourceDebugExtension;
    value = "SMAP\nGameModeUtils.kt\nKotlin\n*S Kotlin\n*F\n+ 1 GameModeUtils.kt\norg/nameless/gamespace/utils/GameModeUtils\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,106:1\n766#2:107\n857#2,2:108\n1#3:110\n*S KotlinDebug\n*F\n+ 1 GameModeUtils.kt\norg/nameless/gamespace/utils/GameModeUtils\n*L\n62#1:107\n62#1:108,2\n*E\n"
.end annotation


# static fields
.field public static final Companion:Lorg/nameless/gamespace/utils/GameModeUtils$Companion;


# instance fields
.field private activeGame:Lorg/nameless/gamespace/data/UserGame;

.field private final context:Landroid/content/Context;

.field private manager:Landroid/app/GameManager;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    new-instance v0, Lorg/nameless/gamespace/utils/GameModeUtils$Companion;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lorg/nameless/gamespace/utils/GameModeUtils$Companion;-><init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V

    sput-object v0, Lorg/nameless/gamespace/utils/GameModeUtils;->Companion:Lorg/nameless/gamespace/utils/GameModeUtils$Companion;

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;)V
    .locals 1

    const-string v0, "context"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 34
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lorg/nameless/gamespace/utils/GameModeUtils;->context:Landroid/content/Context;

    return-void
.end method


# virtual methods
.method public final bind(Landroid/app/GameManager;)V
    .locals 1

    const-string v0, "manager"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 40
    iput-object p1, p0, Lorg/nameless/gamespace/utils/GameModeUtils;->manager:Landroid/app/GameManager;

    return-void
.end method

.method public final findAnglePackage()Landroid/content/pm/ActivityInfo;
    .locals 3

    .line 87
    new-instance v0, Landroid/content/Intent;

    const-string v1, "android.app.action.ANGLE_FOR_ANDROID"

    invoke-direct {v0, v1}, Landroid/content/Intent;-><init>(Ljava/lang/String;)V

    const-wide/32 v1, 0x100000

    .line 88
    invoke-static {v1, v2}, Landroid/content/pm/PackageManager$ResolveInfoFlags;->of(J)Landroid/content/pm/PackageManager$ResolveInfoFlags;

    move-result-object v1

    .line 89
    iget-object p0, p0, Lorg/nameless/gamespace/utils/GameModeUtils;->context:Landroid/content/Context;

    invoke-virtual {p0}, Landroid/content/Context;->getPackageManager()Landroid/content/pm/PackageManager;

    move-result-object p0

    invoke-virtual {p0, v0, v1}, Landroid/content/pm/PackageManager;->queryIntentActivities(Landroid/content/Intent;Landroid/content/pm/PackageManager$ResolveInfoFlags;)Ljava/util/List;

    move-result-object p0

    const-string v0, "info"

    .line 90
    invoke-static {p0, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullExpressionValue(Ljava/lang/Object;Ljava/lang/String;)V

    invoke-static {p0}, Lkotlin/collections/CollectionsKt;->firstOrNull(Ljava/util/List;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Landroid/content/pm/ResolveInfo;

    if-eqz p0, :cond_0

    iget-object p0, p0, Landroid/content/pm/ResolveInfo;->activityInfo:Landroid/content/pm/ActivityInfo;

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    :goto_0
    return-object p0
.end method

.method public final getActiveGame()Lorg/nameless/gamespace/data/UserGame;
    .locals 0

    .line 37
    iget-object p0, p0, Lorg/nameless/gamespace/utils/GameModeUtils;->activeGame:Lorg/nameless/gamespace/data/UserGame;

    return-object p0
.end method

.method public final isAngleUsed(Ljava/lang/String;)Z
    .locals 3

    const/4 p0, 0x0

    if-eqz p1, :cond_1

    const-string v0, "game_overlay"

    const/4 v1, 0x0

    .line 94
    invoke-static {v0, p1, v1}, Landroid/provider/DeviceConfig;->getString(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    if-eqz p1, :cond_0

    const/4 v0, 0x2

    const-string v2, "useAngle=true"

    .line 95
    invoke-static {p1, v2, p0, v0, v1}, Lkotlin/text/StringsKt;->contains$default(Ljava/lang/CharSequence;Ljava/lang/CharSequence;ZILjava/lang/Object;)Z

    move-result p1

    invoke-static {p1}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v1

    :cond_0
    if-eqz v1, :cond_1

    .line 93
    invoke-virtual {v1}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p0

    :cond_1
    return p0
.end method

.method public final setActiveGame(Lorg/nameless/gamespace/data/UserGame;)V
    .locals 0

    .line 37
    iput-object p1, p0, Lorg/nameless/gamespace/utils/GameModeUtils;->activeGame:Lorg/nameless/gamespace/data/UserGame;

    return-void
.end method

.method public final setActiveGameMode(Lorg/nameless/gamespace/data/SystemSettings;I)V
    .locals 2

    const-string v0, "systemSettings"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 54
    iget-object v0, p0, Lorg/nameless/gamespace/utils/GameModeUtils;->activeGame:Lorg/nameless/gamespace/data/UserGame;

    if-eqz v0, :cond_2

    invoke-virtual {v0}, Lorg/nameless/gamespace/data/UserGame;->getPackageName()Ljava/lang/String;

    move-result-object v0

    if-nez v0, :cond_0

    goto :goto_0

    .line 55
    :cond_0
    iget-object v1, p0, Lorg/nameless/gamespace/utils/GameModeUtils;->manager:Landroid/app/GameManager;

    if-eqz v1, :cond_1

    invoke-virtual {v1, v0, p2}, Landroid/app/GameManager;->setGameMode(Ljava/lang/String;I)V

    .line 56
    :cond_1
    invoke-virtual {p0, v0, p1, p2}, Lorg/nameless/gamespace/utils/GameModeUtils;->setGameModeFor(Ljava/lang/String;Lorg/nameless/gamespace/data/SystemSettings;I)Lorg/nameless/gamespace/data/UserGame;

    move-result-object p1

    iput-object p1, p0, Lorg/nameless/gamespace/utils/GameModeUtils;->activeGame:Lorg/nameless/gamespace/data/UserGame;

    :cond_2
    :goto_0
    return-void
.end method

.method public final setGameModeFor(Ljava/lang/String;Lorg/nameless/gamespace/data/SystemSettings;I)Lorg/nameless/gamespace/data/UserGame;
    .locals 3

    const-string p0, "packageName"

    invoke-static {p1, p0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    const-string p0, "systemSettings"

    invoke-static {p2, p0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 60
    new-instance p0, Lorg/nameless/gamespace/data/UserGame;

    invoke-direct {p0, p1, p3}, Lorg/nameless/gamespace/data/UserGame;-><init>(Ljava/lang/String;I)V

    .line 61
    invoke-virtual {p2}, Lorg/nameless/gamespace/data/SystemSettings;->getUserGames()Ljava/util/List;

    move-result-object p3

    check-cast p3, Ljava/lang/Iterable;

    .line 766
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    .line 857
    invoke-interface {p3}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object p3

    :cond_0
    :goto_0
    invoke-interface {p3}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_1

    invoke-interface {p3}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    move-object v2, v1

    check-cast v2, Lorg/nameless/gamespace/data/UserGame;

    .line 62
    invoke-virtual {v2}, Lorg/nameless/gamespace/data/UserGame;->getPackageName()Ljava/lang/String;

    move-result-object v2

    invoke-static {v2, p1}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v2

    xor-int/lit8 v2, v2, 0x1

    if-eqz v2, :cond_0

    .line 857
    invoke-interface {v0, v1}, Ljava/util/Collection;->add(Ljava/lang/Object;)Z

    goto :goto_0

    .line 63
    :cond_1
    invoke-static {v0}, Lkotlin/collections/CollectionsKt;->toMutableList(Ljava/util/Collection;)Ljava/util/List;

    move-result-object p1

    .line 64
    invoke-interface {p1, p0}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 61
    invoke-virtual {p2, p1}, Lorg/nameless/gamespace/data/SystemSettings;->setUserGames(Ljava/util/List;)V

    return-object p0
.end method

.method public final setIntervention(Ljava/lang/String;Ljava/util/List;)V
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            "Ljava/util/List<",
            "Lorg/nameless/gamespace/data/GameConfig;",
            ">;)V"
        }
    .end annotation

    const-string p0, "packageName"

    invoke-static {p1, p0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    if-eqz p2, :cond_0

    .line 49
    sget-object p0, Lorg/nameless/gamespace/data/GameConfig;->Companion:Lorg/nameless/gamespace/data/GameConfig$Companion;

    check-cast p2, Ljava/lang/Iterable;

    invoke-virtual {p0, p2}, Lorg/nameless/gamespace/data/GameConfig$Companion;->asConfig(Ljava/lang/Iterable;)Ljava/lang/String;

    move-result-object p0

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    :goto_0
    const/4 p2, 0x0

    const-string v0, "game_overlay"

    .line 48
    invoke-static {v0, p1, p0, p2}, Landroid/provider/DeviceConfig;->setProperty(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)Z

    return-void
.end method

.method public final setupBatteryMode(Z)V
    .locals 2

    const-string v0, "deviceidle"

    .line 71
    invoke-static {v0}, Landroid/os/ServiceManager;->getService(Ljava/lang/String;)Landroid/os/IBinder;

    move-result-object v0

    .line 70
    invoke-static {v0}, Landroid/os/IDeviceIdleController$Stub;->asInterface(Landroid/os/IBinder;)Landroid/os/IDeviceIdleController;

    move-result-object v0

    if-eqz v0, :cond_0

    .line 74
    :try_start_0
    iget-object v1, p0, Lorg/nameless/gamespace/utils/GameModeUtils;->context:Landroid/content/Context;

    invoke-virtual {v1}, Landroid/content/Context;->getPackageName()Ljava/lang/String;

    move-result-object v1

    invoke-interface {v0, v1}, Landroid/os/IDeviceIdleController;->isPowerSaveWhitelistApp(Ljava/lang/String;)Z

    move-result v1

    goto :goto_0

    :catch_0
    move-exception p0

    goto :goto_1

    :cond_0
    const/4 v1, 0x0

    :goto_0
    if-eqz p1, :cond_1

    if-nez v1, :cond_1

    if-eqz v0, :cond_2

    .line 76
    iget-object p0, p0, Lorg/nameless/gamespace/utils/GameModeUtils;->context:Landroid/content/Context;

    invoke-virtual {p0}, Landroid/content/Context;->getPackageName()Ljava/lang/String;

    move-result-object p0

    invoke-interface {v0, p0}, Landroid/os/IDeviceIdleController;->addPowerSaveWhitelistApp(Ljava/lang/String;)V

    goto :goto_2

    :cond_1
    if-nez p1, :cond_2

    if-eqz v1, :cond_2

    if-eqz v0, :cond_2

    .line 78
    iget-object p0, p0, Lorg/nameless/gamespace/utils/GameModeUtils;->context:Landroid/content/Context;

    invoke-virtual {p0}, Landroid/content/Context;->getPackageName()Ljava/lang/String;

    move-result-object p0

    invoke-interface {v0, p0}, Landroid/os/IDeviceIdleController;->removePowerSaveWhitelistApp(Ljava/lang/String;)V
    :try_end_0
    .catch Landroid/os/RemoteException; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_2

    .line 81
    :goto_1
    invoke-virtual {p0}, Landroid/os/RemoteException;->printStackTrace()V

    :cond_2
    :goto_2
    return-void
.end method

.method public final unbind()V
    .locals 1

    const/4 v0, 0x0

    .line 44
    iput-object v0, p0, Lorg/nameless/gamespace/utils/GameModeUtils;->manager:Landroid/app/GameManager;

    return-void
.end method
