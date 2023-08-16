.class public final Lorg/nameless/gamespace/utils/di/MainModule;
.super Ljava/lang/Object;
.source "MainModule.kt"


# static fields
.field public static final INSTANCE:Lorg/nameless/gamespace/utils/di/MainModule;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    new-instance v0, Lorg/nameless/gamespace/utils/di/MainModule;

    invoke-direct {v0}, Lorg/nameless/gamespace/utils/di/MainModule;-><init>()V

    sput-object v0, Lorg/nameless/gamespace/utils/di/MainModule;->INSTANCE:Lorg/nameless/gamespace/utils/di/MainModule;

    return-void
.end method

.method private constructor <init>()V
    .locals 0

    .line 33
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public final provideAppSettings(Landroid/content/Context;)Lorg/nameless/gamespace/data/AppSettings;
    .locals 0

    const-string p0, "context"

    invoke-static {p1, p0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 49
    new-instance p0, Lorg/nameless/gamespace/data/AppSettings;

    invoke-direct {p0, p1}, Lorg/nameless/gamespace/data/AppSettings;-><init>(Landroid/content/Context;)V

    return-object p0
.end method

.method public final provideBaseGson()Lcom/google/gson/Gson;
    .locals 0

    .line 37
    new-instance p0, Lcom/google/gson/Gson;

    invoke-direct {p0}, Lcom/google/gson/Gson;-><init>()V

    return-object p0
.end method

.method public final provideGameModeUtils(Landroid/content/Context;)Lorg/nameless/gamespace/utils/GameModeUtils;
    .locals 0

    const-string p0, "context"

    invoke-static {p1, p0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 45
    new-instance p0, Lorg/nameless/gamespace/utils/GameModeUtils;

    invoke-direct {p0, p1}, Lorg/nameless/gamespace/utils/GameModeUtils;-><init>(Landroid/content/Context;)V

    return-object p0
.end method

.method public final provideGameSession(Landroid/content/Context;Lorg/nameless/gamespace/data/AppSettings;Lorg/nameless/gamespace/data/SystemSettings;Lcom/google/gson/Gson;)Lorg/nameless/gamespace/data/GameSession;
    .locals 0

    const-string p0, "context"

    invoke-static {p1, p0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    const-string p0, "appSettings"

    invoke-static {p2, p0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    const-string p0, "systemSettings"

    invoke-static {p3, p0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    const-string p0, "gson"

    invoke-static {p4, p0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 63
    new-instance p0, Lorg/nameless/gamespace/data/GameSession;

    invoke-direct {p0, p1, p2, p3, p4}, Lorg/nameless/gamespace/data/GameSession;-><init>(Landroid/content/Context;Lorg/nameless/gamespace/data/AppSettings;Lorg/nameless/gamespace/data/SystemSettings;Lcom/google/gson/Gson;)V

    return-object p0
.end method

.method public final provideScreenUtils(Landroid/content/Context;)Lorg/nameless/gamespace/utils/ScreenUtils;
    .locals 0

    const-string p0, "context"

    invoke-static {p1, p0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 41
    new-instance p0, Lorg/nameless/gamespace/utils/ScreenUtils;

    invoke-direct {p0, p1}, Lorg/nameless/gamespace/utils/ScreenUtils;-><init>(Landroid/content/Context;)V

    return-object p0
.end method

.method public final provideSystemSettings(Landroid/content/Context;Lorg/nameless/gamespace/utils/GameModeUtils;)Lorg/nameless/gamespace/data/SystemSettings;
    .locals 0

    const-string p0, "context"

    invoke-static {p1, p0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    const-string p0, "gameModeUtils"

    invoke-static {p2, p0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 54
    new-instance p0, Lorg/nameless/gamespace/data/SystemSettings;

    invoke-direct {p0, p1, p2}, Lorg/nameless/gamespace/data/SystemSettings;-><init>(Landroid/content/Context;Lorg/nameless/gamespace/utils/GameModeUtils;)V

    return-object p0
.end method
