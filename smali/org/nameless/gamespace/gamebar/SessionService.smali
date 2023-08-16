.class public final Lorg/nameless/gamespace/gamebar/SessionService;
.super Lorg/nameless/gamespace/gamebar/Hilt_SessionService;
.source "SessionService.kt"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lorg/nameless/gamespace/gamebar/SessionService$Companion;
    }
.end annotation

.annotation system Ldalvik/annotation/SourceDebugExtension;
    value = "SMAP\nSessionService.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SessionService.kt\norg/nameless/gamespace/gamebar/SessionService\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,209:1\n1#2:210\n1743#3,3:211\n288#3,2:214\n288#3,2:216\n*S KotlinDebug\n*F\n+ 1 SessionService.kt\norg/nameless/gamespace/gamebar/SessionService\n*L\n167#1:211,3\n177#1:214,2\n179#1:216,2\n*E\n"
.end annotation


# static fields
.field public static final Companion:Lorg/nameless/gamespace/gamebar/SessionService$Companion;

.field private static isRunning:Z


# instance fields
.field public appSettings:Lorg/nameless/gamespace/data/AppSettings;

.field public callListener:Lorg/nameless/gamespace/gamebar/CallListener;

.field private commandIntent:Landroid/content/Intent;

.field private gameBar:Lorg/nameless/gamespace/gamebar/GameBarService;

.field private final gameBarConnection:Lorg/nameless/gamespace/gamebar/SessionService$gameBarConnection$1;

.field private gameManager:Landroid/app/GameManager;

.field public gameModeUtils:Lorg/nameless/gamespace/utils/GameModeUtils;

.field private isBarConnected:Z

.field private final scope:Lkotlinx/coroutines/CoroutineScope;

.field public screenUtils:Lorg/nameless/gamespace/utils/ScreenUtils;

.field public session:Lorg/nameless/gamespace/data/GameSession;

.field public settings:Lorg/nameless/gamespace/data/SystemSettings;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    new-instance v0, Lorg/nameless/gamespace/gamebar/SessionService$Companion;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lorg/nameless/gamespace/gamebar/SessionService$Companion;-><init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V

    sput-object v0, Lorg/nameless/gamespace/gamebar/SessionService;->Companion:Lorg/nameless/gamespace/gamebar/SessionService$Companion;

    return-void
.end method

.method public constructor <init>()V
    .locals 2

    .line 46
    invoke-direct {p0}, Lorg/nameless/gamespace/gamebar/Hilt_SessionService;-><init>()V

    const/4 v0, 0x0

    const/4 v1, 0x1

    .line 65
    invoke-static {v0, v1, v0}, Lkotlinx/coroutines/JobKt;->Job$default(Lkotlinx/coroutines/Job;ILjava/lang/Object;)Lkotlinx/coroutines/CompletableJob;

    move-result-object v0

    invoke-static {}, Lkotlinx/coroutines/Dispatchers;->getIO()Lkotlinx/coroutines/CoroutineDispatcher;

    move-result-object v1

    invoke-interface {v0, v1}, Lkotlin/coroutines/CoroutineContext;->plus(Lkotlin/coroutines/CoroutineContext;)Lkotlin/coroutines/CoroutineContext;

    move-result-object v0

    invoke-static {v0}, Lkotlinx/coroutines/CoroutineScopeKt;->CoroutineScope(Lkotlin/coroutines/CoroutineContext;)Lkotlinx/coroutines/CoroutineScope;

    move-result-object v0

    iput-object v0, p0, Lorg/nameless/gamespace/gamebar/SessionService;->scope:Lkotlinx/coroutines/CoroutineScope;

    .line 67
    new-instance v0, Lorg/nameless/gamespace/gamebar/SessionService$gameBarConnection$1;

    invoke-direct {v0, p0}, Lorg/nameless/gamespace/gamebar/SessionService$gameBarConnection$1;-><init>(Lorg/nameless/gamespace/gamebar/SessionService;)V

    iput-object v0, p0, Lorg/nameless/gamespace/gamebar/SessionService;->gameBarConnection:Lorg/nameless/gamespace/gamebar/SessionService$gameBarConnection$1;

    return-void
.end method

.method public static final synthetic access$getGameManager$p(Lorg/nameless/gamespace/gamebar/SessionService;)Landroid/app/GameManager;
    .locals 0

    .line 45
    iget-object p0, p0, Lorg/nameless/gamespace/gamebar/SessionService;->gameManager:Landroid/app/GameManager;

    return-object p0
.end method

.method public static final synthetic access$isRunning$cp()Z
    .locals 1

    .line 45
    sget-boolean v0, Lorg/nameless/gamespace/gamebar/SessionService;->isRunning:Z

    return v0
.end method

.method public static final synthetic access$onGameBarReady(Lorg/nameless/gamespace/gamebar/SessionService;)V
    .locals 0

    .line 45
    invoke-direct {p0}, Lorg/nameless/gamespace/gamebar/SessionService;->onGameBarReady()V

    return-void
.end method

.method public static final synthetic access$setBarConnected$p(Lorg/nameless/gamespace/gamebar/SessionService;Z)V
    .locals 0

    .line 45
    iput-boolean p1, p0, Lorg/nameless/gamespace/gamebar/SessionService;->isBarConnected:Z

    return-void
.end method

.method public static final synthetic access$setGameBar$p(Lorg/nameless/gamespace/gamebar/SessionService;Lorg/nameless/gamespace/gamebar/GameBarService;)V
    .locals 0

    .line 45
    iput-object p1, p0, Lorg/nameless/gamespace/gamebar/SessionService;->gameBar:Lorg/nameless/gamespace/gamebar/GameBarService;

    return-void
.end method

.method private final applyGameModeConfig(Ljava/lang/String;)V
    .locals 11

    .line 177
    invoke-virtual {p0}, Lorg/nameless/gamespace/gamebar/SessionService;->getSettings()Lorg/nameless/gamespace/data/SystemSettings;

    move-result-object v0

    invoke-virtual {v0}, Lorg/nameless/gamespace/data/SystemSettings;->getUserGames()Ljava/util/List;

    move-result-object v0

    check-cast v0, Ljava/lang/Iterable;

    .line 288
    invoke-interface {v0}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :cond_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    const/4 v2, 0x0

    if-eqz v1, :cond_1

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    move-object v3, v1

    check-cast v3, Lorg/nameless/gamespace/data/UserGame;

    .line 177
    invoke-virtual {v3}, Lorg/nameless/gamespace/data/UserGame;->getPackageName()Ljava/lang/String;

    move-result-object v3

    invoke-static {v3, p1}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_0

    goto :goto_0

    :cond_1
    move-object v1, v2

    :goto_0
    check-cast v1, Lorg/nameless/gamespace/data/UserGame;

    if-eqz v1, :cond_2

    .line 178
    invoke-virtual {v1}, Lorg/nameless/gamespace/data/UserGame;->getMode()I

    move-result v0

    goto :goto_1

    :cond_2
    const/4 v0, 0x1

    .line 179
    :goto_1
    invoke-virtual {p0}, Lorg/nameless/gamespace/gamebar/SessionService;->getGameModeUtils()Lorg/nameless/gamespace/utils/GameModeUtils;

    move-result-object v1

    invoke-virtual {p0}, Lorg/nameless/gamespace/gamebar/SessionService;->getSettings()Lorg/nameless/gamespace/data/SystemSettings;

    move-result-object v3

    invoke-virtual {v3}, Lorg/nameless/gamespace/data/SystemSettings;->getUserGames()Ljava/util/List;

    move-result-object v3

    check-cast v3, Ljava/lang/Iterable;

    .line 288
    invoke-interface {v3}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object v3

    :cond_3
    invoke-interface {v3}, Ljava/util/Iterator;->hasNext()Z

    move-result v4

    if-eqz v4, :cond_4

    invoke-interface {v3}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v4

    move-object v5, v4

    check-cast v5, Lorg/nameless/gamespace/data/UserGame;

    .line 179
    invoke-virtual {v5}, Lorg/nameless/gamespace/data/UserGame;->getPackageName()Ljava/lang/String;

    move-result-object v5

    invoke-static {v5, p1}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v5

    if-eqz v5, :cond_3

    goto :goto_2

    :cond_4
    move-object v4, v2

    .line 289
    :goto_2
    check-cast v4, Lorg/nameless/gamespace/data/UserGame;

    .line 179
    invoke-virtual {v1, v4}, Lorg/nameless/gamespace/utils/GameModeUtils;->setActiveGame(Lorg/nameless/gamespace/data/UserGame;)V

    .line 180
    iget-object v5, p0, Lorg/nameless/gamespace/gamebar/SessionService;->scope:Lkotlinx/coroutines/CoroutineScope;

    const/4 v6, 0x0

    const/4 v7, 0x0

    new-instance v8, Lorg/nameless/gamespace/gamebar/SessionService$applyGameModeConfig$2;

    invoke-direct {v8, p0, p1, v0, v2}, Lorg/nameless/gamespace/gamebar/SessionService$applyGameModeConfig$2;-><init>(Lorg/nameless/gamespace/gamebar/SessionService;Ljava/lang/String;ILkotlin/coroutines/Continuation;)V

    const/4 v9, 0x3

    const/4 v10, 0x0

    invoke-static/range {v5 .. v10}, Lkotlinx/coroutines/BuildersKt;->launch$default(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function2;ILjava/lang/Object;)Lkotlinx/coroutines/Job;

    return-void
.end method

.method private final onGameBarReady()V
    .locals 4

    .line 135
    iget-boolean v0, p0, Lorg/nameless/gamespace/gamebar/SessionService;->isBarConnected:Z

    if-nez v0, :cond_0

    .line 136
    invoke-direct {p0}, Lorg/nameless/gamespace/gamebar/SessionService;->startGameBar()V

    return-void

    .line 141
    :cond_0
    :try_start_0
    invoke-virtual {p0}, Lorg/nameless/gamespace/gamebar/SessionService;->getSession()Lorg/nameless/gamespace/data/GameSession;

    move-result-object v0

    invoke-virtual {v0}, Lorg/nameless/gamespace/data/GameSession;->unregister()V

    .line 142
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/SessionService;->commandIntent:Landroid/content/Intent;

    if-nez v0, :cond_1

    .line 144
    invoke-virtual {p0}, Landroid/app/Service;->stopSelf()V

    .line 146
    :cond_1
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/SessionService;->commandIntent:Landroid/content/Intent;

    const/4 v1, 0x0

    if-nez v0, :cond_2

    const-string v0, "commandIntent"

    invoke-static {v0}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    move-object v0, v1

    :cond_2
    const-string v2, "package_name"

    invoke-virtual {v0, v2}, Landroid/content/Intent;->getStringExtra(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 147
    invoke-virtual {p0}, Lorg/nameless/gamespace/gamebar/SessionService;->getSession()Lorg/nameless/gamespace/data/GameSession;

    move-result-object v2

    const-string v3, "app"

    invoke-static {v0, v3}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullExpressionValue(Ljava/lang/Object;Ljava/lang/String;)V

    invoke-virtual {v2, v0}, Lorg/nameless/gamespace/data/GameSession;->register(Ljava/lang/String;)V

    .line 148
    invoke-direct {p0, v0}, Lorg/nameless/gamespace/gamebar/SessionService;->applyGameModeConfig(Ljava/lang/String;)V

    .line 149
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/SessionService;->gameBar:Lorg/nameless/gamespace/gamebar/GameBarService;

    if-nez v0, :cond_3

    const-string v0, "gameBar"

    invoke-static {v0}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    goto :goto_0

    :cond_3
    move-object v1, v0

    :goto_0
    invoke-virtual {v1}, Lorg/nameless/gamespace/gamebar/GameBarService;->onGameStart()V

    .line 150
    invoke-virtual {p0}, Lorg/nameless/gamespace/gamebar/SessionService;->getScreenUtils()Lorg/nameless/gamespace/utils/ScreenUtils;

    move-result-object v0

    invoke-virtual {p0}, Lorg/nameless/gamespace/gamebar/SessionService;->getAppSettings()Lorg/nameless/gamespace/data/AppSettings;

    move-result-object v1

    invoke-virtual {v1}, Lorg/nameless/gamespace/data/AppSettings;->getStayAwake()Z

    move-result v1

    invoke-virtual {v0, v1}, Lorg/nameless/gamespace/utils/ScreenUtils;->setStayAwake(Z)V

    .line 151
    invoke-virtual {p0}, Lorg/nameless/gamespace/gamebar/SessionService;->getScreenUtils()Lorg/nameless/gamespace/utils/ScreenUtils;

    move-result-object v0

    .line 152
    invoke-virtual {p0}, Lorg/nameless/gamespace/gamebar/SessionService;->getAppSettings()Lorg/nameless/gamespace/data/AppSettings;

    move-result-object v1

    invoke-virtual {v1}, Lorg/nameless/gamespace/data/AppSettings;->getLockGesture()Z

    move-result v1

    const/4 v2, 0x0

    if-eqz v1, :cond_4

    const/4 v1, 0x1

    goto :goto_1

    :cond_4
    move v1, v2

    .line 153
    :goto_1
    invoke-virtual {p0}, Lorg/nameless/gamespace/gamebar/SessionService;->getAppSettings()Lorg/nameless/gamespace/data/AppSettings;

    move-result-object v3

    invoke-virtual {v3}, Lorg/nameless/gamespace/data/AppSettings;->getLockStatusBar()Z

    move-result v3

    if-eqz v3, :cond_5

    const/4 v2, 0x2

    :cond_5
    or-int/2addr v1, v2

    .line 151
    invoke-virtual {v0, v1}, Lorg/nameless/gamespace/utils/ScreenUtils;->setLockGesture(I)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_2

    :catch_0
    move-exception v0

    .line 155
    invoke-virtual {v0}, Ljava/lang/Exception;->toString()Ljava/lang/String;

    move-result-object v0

    const-string v1, "SessionService"

    invoke-static {v1, v0}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 158
    :goto_2
    invoke-virtual {p0}, Lorg/nameless/gamespace/gamebar/SessionService;->getCallListener()Lorg/nameless/gamespace/gamebar/CallListener;

    move-result-object p0

    invoke-virtual {p0}, Lorg/nameless/gamespace/gamebar/CallListener;->init()V

    return-void
.end method

.method private final startGameBar()V
    .locals 4

    .line 113
    new-instance v0, Landroid/content/Intent;

    const-class v1, Lorg/nameless/gamespace/gamebar/GameBarService;

    invoke-direct {v0, p0, v1}, Landroid/content/Intent;-><init>(Landroid/content/Context;Ljava/lang/Class;)V

    .line 114
    iget-object v1, p0, Lorg/nameless/gamespace/gamebar/SessionService;->gameBarConnection:Lorg/nameless/gamespace/gamebar/SessionService$gameBarConnection$1;

    sget-object v2, Landroid/os/UserHandle;->CURRENT:Landroid/os/UserHandle;

    const/4 v3, 0x1

    invoke-virtual {p0, v0, v1, v3, v2}, Landroid/app/Service;->bindServiceAsUser(Landroid/content/Intent;Landroid/content/ServiceConnection;ILandroid/os/UserHandle;)Z

    return-void
.end method

.method private final tryStartFromDeath()I
    .locals 6

    .line 162
    invoke-static {}, Landroid/app/ActivityTaskManager;->getService()Landroid/app/IActivityTaskManager;

    move-result-object v0

    if-eqz v0, :cond_0

    .line 163
    invoke-interface {v0}, Landroid/app/IActivityTaskManager;->getFocusedRootTaskInfo()Landroid/app/ActivityTaskManager$RootTaskInfo;

    move-result-object v0

    if-eqz v0, :cond_0

    iget-object v0, v0, Landroid/app/ActivityTaskManager$RootTaskInfo;->topActivity:Landroid/content/ComponentName;

    if-eqz v0, :cond_0

    .line 164
    invoke-virtual {v0}, Landroid/content/ComponentName;->getPackageName()Ljava/lang/String;

    move-result-object v0

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    const/4 v1, 0x2

    if-nez v0, :cond_1

    return v1

    .line 167
    :cond_1
    invoke-virtual {p0}, Lorg/nameless/gamespace/gamebar/SessionService;->getSettings()Lorg/nameless/gamespace/data/SystemSettings;

    move-result-object v2

    invoke-virtual {v2}, Lorg/nameless/gamespace/data/SystemSettings;->getUserGames()Ljava/util/List;

    move-result-object v2

    check-cast v2, Ljava/lang/Iterable;

    .line 1743
    instance-of v3, v2, Ljava/util/Collection;

    const/4 v4, 0x1

    const/4 v5, 0x0

    if-eqz v3, :cond_2

    move-object v3, v2

    check-cast v3, Ljava/util/Collection;

    invoke-interface {v3}, Ljava/util/Collection;->isEmpty()Z

    move-result v3

    if-eqz v3, :cond_2

    goto :goto_1

    .line 1744
    :cond_2
    invoke-interface {v2}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object v2

    :cond_3
    invoke-interface {v2}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    if-eqz v3, :cond_4

    invoke-interface {v2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lorg/nameless/gamespace/data/UserGame;

    .line 167
    invoke-virtual {v3}, Lorg/nameless/gamespace/data/UserGame;->getPackageName()Ljava/lang/String;

    move-result-object v3

    invoke-static {v3, v0}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_3

    move v5, v4

    :cond_4
    :goto_1
    if-nez v5, :cond_5

    return v1

    .line 171
    :cond_5
    new-instance v1, Landroid/content/Intent;

    const-string v2, "game_start"

    invoke-direct {v1, v2}, Landroid/content/Intent;-><init>(Ljava/lang/String;)V

    const-string v2, "package_name"

    invoke-virtual {v1, v2, v0}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;

    move-result-object v0

    const-string v1, "Intent(START).putExtra(EXTRA_PACKAGE_NAME, game)"

    invoke-static {v0, v1}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullExpressionValue(Ljava/lang/Object;Ljava/lang/String;)V

    iput-object v0, p0, Lorg/nameless/gamespace/gamebar/SessionService;->commandIntent:Landroid/content/Intent;

    .line 172
    invoke-direct {p0}, Lorg/nameless/gamespace/gamebar/SessionService;->startGameBar()V

    return v4
.end method


# virtual methods
.method public final getAppSettings()Lorg/nameless/gamespace/data/AppSettings;
    .locals 0

    .line 48
    iget-object p0, p0, Lorg/nameless/gamespace/gamebar/SessionService;->appSettings:Lorg/nameless/gamespace/data/AppSettings;

    if-eqz p0, :cond_0

    return-object p0

    :cond_0
    const-string p0, "appSettings"

    invoke-static {p0}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0
.end method

.method public final getCallListener()Lorg/nameless/gamespace/gamebar/CallListener;
    .locals 0

    .line 63
    iget-object p0, p0, Lorg/nameless/gamespace/gamebar/SessionService;->callListener:Lorg/nameless/gamespace/gamebar/CallListener;

    if-eqz p0, :cond_0

    return-object p0

    :cond_0
    const-string p0, "callListener"

    invoke-static {p0}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0
.end method

.method public final getGameModeUtils()Lorg/nameless/gamespace/utils/GameModeUtils;
    .locals 0

    .line 60
    iget-object p0, p0, Lorg/nameless/gamespace/gamebar/SessionService;->gameModeUtils:Lorg/nameless/gamespace/utils/GameModeUtils;

    if-eqz p0, :cond_0

    return-object p0

    :cond_0
    const-string p0, "gameModeUtils"

    invoke-static {p0}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0
.end method

.method public final getScreenUtils()Lorg/nameless/gamespace/utils/ScreenUtils;
    .locals 0

    .line 57
    iget-object p0, p0, Lorg/nameless/gamespace/gamebar/SessionService;->screenUtils:Lorg/nameless/gamespace/utils/ScreenUtils;

    if-eqz p0, :cond_0

    return-object p0

    :cond_0
    const-string p0, "screenUtils"

    invoke-static {p0}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0
.end method

.method public final getSession()Lorg/nameless/gamespace/data/GameSession;
    .locals 0

    .line 54
    iget-object p0, p0, Lorg/nameless/gamespace/gamebar/SessionService;->session:Lorg/nameless/gamespace/data/GameSession;

    if-eqz p0, :cond_0

    return-object p0

    :cond_0
    const-string p0, "session"

    invoke-static {p0}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0
.end method

.method public final getSettings()Lorg/nameless/gamespace/data/SystemSettings;
    .locals 0

    .line 51
    iget-object p0, p0, Lorg/nameless/gamespace/gamebar/SessionService;->settings:Lorg/nameless/gamespace/data/SystemSettings;

    if-eqz p0, :cond_0

    return-object p0

    :cond_0
    const-string p0, "settings"

    invoke-static {p0}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0
.end method

.method public onBind(Landroid/content/Intent;)Landroid/os/IBinder;
    .locals 0

    const/4 p0, 0x0

    return-object p0
.end method

.method public onCreate()V
    .locals 2
    .annotation build Landroid/annotation/SuppressLint;
        value = {
            "WrongConstant"
        }
    .end annotation

    .line 87
    invoke-super {p0}, Lorg/nameless/gamespace/gamebar/Hilt_SessionService;->onCreate()V

    .line 89
    :try_start_0
    invoke-virtual {p0}, Lorg/nameless/gamespace/gamebar/SessionService;->getScreenUtils()Lorg/nameless/gamespace/utils/ScreenUtils;

    move-result-object v0

    invoke-virtual {v0}, Lorg/nameless/gamespace/utils/ScreenUtils;->bind()V
    :try_end_0
    .catch Landroid/os/RemoteException; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception v0

    .line 91
    invoke-virtual {v0}, Landroid/os/RemoteException;->toString()Ljava/lang/String;

    move-result-object v0

    const-string v1, "SessionService"

    invoke-static {v1, v0}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    :goto_0
    const-string v0, "game"

    .line 93
    invoke-virtual {p0, v0}, Landroid/app/Service;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    const-string v1, "null cannot be cast to non-null type android.app.GameManager"

    invoke-static {v0, v1}, Lkotlin/jvm/internal/Intrinsics;->checkNotNull(Ljava/lang/Object;Ljava/lang/String;)V

    check-cast v0, Landroid/app/GameManager;

    iput-object v0, p0, Lorg/nameless/gamespace/gamebar/SessionService;->gameManager:Landroid/app/GameManager;

    .line 94
    invoke-virtual {p0}, Lorg/nameless/gamespace/gamebar/SessionService;->getGameModeUtils()Lorg/nameless/gamespace/utils/GameModeUtils;

    move-result-object v0

    iget-object p0, p0, Lorg/nameless/gamespace/gamebar/SessionService;->gameManager:Landroid/app/GameManager;

    if-nez p0, :cond_0

    const-string p0, "gameManager"

    invoke-static {p0}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    const/4 p0, 0x0

    :cond_0
    invoke-virtual {v0, p0}, Lorg/nameless/gamespace/utils/GameModeUtils;->bind(Landroid/app/GameManager;)V

    const/4 p0, 0x1

    .line 95
    sput-boolean p0, Lorg/nameless/gamespace/gamebar/SessionService;->isRunning:Z

    return-void
.end method

.method public onDestroy()V
    .locals 1

    .line 121
    invoke-virtual {p0}, Lorg/nameless/gamespace/gamebar/SessionService;->getCallListener()Lorg/nameless/gamespace/gamebar/CallListener;

    move-result-object v0

    invoke-virtual {v0}, Lorg/nameless/gamespace/gamebar/CallListener;->destory()V

    .line 123
    iget-boolean v0, p0, Lorg/nameless/gamespace/gamebar/SessionService;->isBarConnected:Z

    if-eqz v0, :cond_1

    .line 124
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/SessionService;->gameBar:Lorg/nameless/gamespace/gamebar/GameBarService;

    if-nez v0, :cond_0

    const-string v0, "gameBar"

    invoke-static {v0}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    const/4 v0, 0x0

    :cond_0
    invoke-virtual {v0}, Lorg/nameless/gamespace/gamebar/GameBarService;->onGameLeave()V

    .line 125
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/SessionService;->gameBarConnection:Lorg/nameless/gamespace/gamebar/SessionService$gameBarConnection$1;

    invoke-virtual {p0, v0}, Landroid/app/Service;->unbindService(Landroid/content/ServiceConnection;)V

    .line 127
    :cond_1
    invoke-virtual {p0}, Lorg/nameless/gamespace/gamebar/SessionService;->getSession()Lorg/nameless/gamespace/data/GameSession;

    move-result-object v0

    invoke-virtual {v0}, Lorg/nameless/gamespace/data/GameSession;->unregister()V

    .line 128
    invoke-virtual {p0}, Lorg/nameless/gamespace/gamebar/SessionService;->getGameModeUtils()Lorg/nameless/gamespace/utils/GameModeUtils;

    move-result-object v0

    invoke-virtual {v0}, Lorg/nameless/gamespace/utils/GameModeUtils;->unbind()V

    .line 129
    invoke-virtual {p0}, Lorg/nameless/gamespace/gamebar/SessionService;->getScreenUtils()Lorg/nameless/gamespace/utils/ScreenUtils;

    move-result-object v0

    invoke-virtual {v0}, Lorg/nameless/gamespace/utils/ScreenUtils;->unbind()V

    const/4 v0, 0x0

    .line 130
    sput-boolean v0, Lorg/nameless/gamespace/gamebar/SessionService;->isRunning:Z

    .line 131
    invoke-super {p0}, Landroid/app/Service;->onDestroy()V

    return-void
.end method

.method public onStartCommand(Landroid/content/Intent;II)I
    .locals 1

    if-eqz p1, :cond_0

    .line 99
    iput-object p1, p0, Lorg/nameless/gamespace/gamebar/SessionService;->commandIntent:Landroid/content/Intent;

    .line 100
    :cond_0
    invoke-super {p0, p1, p2, p3}, Landroid/app/Service;->onStartCommand(Landroid/content/Intent;II)I

    const/4 v0, 0x1

    if-nez p1, :cond_1

    if-nez p2, :cond_1

    if-le p3, v0, :cond_1

    .line 102
    invoke-direct {p0}, Lorg/nameless/gamespace/gamebar/SessionService;->tryStartFromDeath()I

    move-result p0

    return p0

    :cond_1
    if-eqz p1, :cond_2

    .line 105
    invoke-virtual {p1}, Landroid/content/Intent;->getAction()Ljava/lang/String;

    move-result-object p1

    goto :goto_0

    :cond_2
    const/4 p1, 0x0

    :goto_0
    const-string p2, "game_start"

    .line 106
    invoke-static {p1, p2}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result p2

    if-eqz p2, :cond_3

    invoke-direct {p0}, Lorg/nameless/gamespace/gamebar/SessionService;->startGameBar()V

    goto :goto_1

    :cond_3
    const-string p2, "game_stop"

    .line 107
    invoke-static {p1, p2}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_4

    invoke-virtual {p0}, Landroid/app/Service;->stopSelf()V

    :cond_4
    :goto_1
    return v0
.end method
