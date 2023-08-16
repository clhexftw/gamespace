.class public final Lorg/nameless/gamespace/data/GameSession;
.super Ljava/lang/Object;
.source "GameSession.kt"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lorg/nameless/gamespace/data/GameSession$Companion;
    }
.end annotation

.annotation system Ldalvik/annotation/SourceDebugExtension;
    value = "SMAP\nGameSession.kt\nKotlin\n*S Kotlin\n*F\n+ 1 GameSession.kt\norg/nameless/gamespace/data/GameSession\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,111:1\n1#2:112\n*E\n"
.end annotation


# static fields
.field public static final Companion:Lorg/nameless/gamespace/data/GameSession$Companion;


# instance fields
.field private final appSettings:Lorg/nameless/gamespace/data/AppSettings;

.field private final audioManager$delegate:Lkotlin/Lazy;

.field private final context:Landroid/content/Context;

.field private final db$delegate:Lkotlin/Lazy;

.field private final gson:Lcom/google/gson/Gson;

.field private final systemSettings:Lorg/nameless/gamespace/data/SystemSettings;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    new-instance v0, Lorg/nameless/gamespace/data/GameSession$Companion;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lorg/nameless/gamespace/data/GameSession$Companion;-><init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V

    sput-object v0, Lorg/nameless/gamespace/data/GameSession;->Companion:Lorg/nameless/gamespace/data/GameSession$Companion;

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Lorg/nameless/gamespace/data/AppSettings;Lorg/nameless/gamespace/data/SystemSettings;Lcom/google/gson/Gson;)V
    .locals 1

    const-string v0, "context"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    const-string v0, "appSettings"

    invoke-static {p2, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    const-string v0, "systemSettings"

    invoke-static {p3, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    const-string v0, "gson"

    invoke-static {p4, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 24
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 25
    iput-object p1, p0, Lorg/nameless/gamespace/data/GameSession;->context:Landroid/content/Context;

    .line 26
    iput-object p2, p0, Lorg/nameless/gamespace/data/GameSession;->appSettings:Lorg/nameless/gamespace/data/AppSettings;

    .line 27
    iput-object p3, p0, Lorg/nameless/gamespace/data/GameSession;->systemSettings:Lorg/nameless/gamespace/data/SystemSettings;

    .line 28
    iput-object p4, p0, Lorg/nameless/gamespace/data/GameSession;->gson:Lcom/google/gson/Gson;

    .line 31
    new-instance p1, Lorg/nameless/gamespace/data/GameSession$db$2;

    invoke-direct {p1, p0}, Lorg/nameless/gamespace/data/GameSession$db$2;-><init>(Lorg/nameless/gamespace/data/GameSession;)V

    invoke-static {p1}, Lkotlin/LazyKt;->lazy(Lkotlin/jvm/functions/Function0;)Lkotlin/Lazy;

    move-result-object p1

    iput-object p1, p0, Lorg/nameless/gamespace/data/GameSession;->db$delegate:Lkotlin/Lazy;

    .line 32
    new-instance p1, Lorg/nameless/gamespace/data/GameSession$audioManager$2;

    invoke-direct {p1, p0}, Lorg/nameless/gamespace/data/GameSession$audioManager$2;-><init>(Lorg/nameless/gamespace/data/GameSession;)V

    invoke-static {p1}, Lkotlin/LazyKt;->lazy(Lkotlin/jvm/functions/Function0;)Lkotlin/Lazy;

    move-result-object p1

    iput-object p1, p0, Lorg/nameless/gamespace/data/GameSession;->audioManager$delegate:Lkotlin/Lazy;

    return-void
.end method

.method public static final synthetic access$getContext$p(Lorg/nameless/gamespace/data/GameSession;)Landroid/content/Context;
    .locals 0

    .line 24
    iget-object p0, p0, Lorg/nameless/gamespace/data/GameSession;->context:Landroid/content/Context;

    return-object p0
.end method

.method private final getAudioManager()Landroid/media/AudioManager;
    .locals 0

    .line 32
    iget-object p0, p0, Lorg/nameless/gamespace/data/GameSession;->audioManager$delegate:Lkotlin/Lazy;

    invoke-interface {p0}, Lkotlin/Lazy;->getValue()Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Landroid/media/AudioManager;

    return-object p0
.end method

.method private final getDb()Landroid/content/SharedPreferences;
    .locals 0

    .line 31
    iget-object p0, p0, Lorg/nameless/gamespace/data/GameSession;->db$delegate:Lkotlin/Lazy;

    invoke-interface {p0}, Lkotlin/Lazy;->getValue()Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Landroid/content/SharedPreferences;

    return-object p0
.end method

.method private final getState()Lorg/nameless/gamespace/data/SessionState;
    .locals 3

    .line 35
    invoke-direct {p0}, Lorg/nameless/gamespace/data/GameSession;->getDb()Landroid/content/SharedPreferences;

    move-result-object v0

    const-string v1, "session"

    const-string v2, ""

    invoke-interface {v0, v1, v2}, Landroid/content/SharedPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    const/4 v1, 0x1

    if-eqz v0, :cond_1

    .line 36
    invoke-interface {v0}, Ljava/lang/CharSequence;->length()I

    move-result v2

    if-nez v2, :cond_0

    goto :goto_0

    :cond_0
    const/4 v2, 0x0

    goto :goto_1

    :cond_1
    :goto_0
    move v2, v1

    :goto_1
    xor-int/2addr v1, v2

    const/4 v2, 0x0

    if-eqz v1, :cond_2

    goto :goto_2

    :cond_2
    move-object v0, v2

    :goto_2
    if-eqz v0, :cond_3

    .line 39
    :try_start_0
    iget-object p0, p0, Lorg/nameless/gamespace/data/GameSession;->gson:Lcom/google/gson/Gson;

    const-class v1, Lorg/nameless/gamespace/data/SessionState;

    invoke-virtual {p0, v0, v1}, Lcom/google/gson/Gson;->fromJson(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lorg/nameless/gamespace/data/SessionState;
    :try_end_0
    .catch Ljava/lang/RuntimeException; {:try_start_0 .. :try_end_0} :catch_0

    move-object v2, p0

    :catch_0
    :cond_3
    return-object v2
.end method

.method private final setState(Lorg/nameless/gamespace/data/SessionState;)V
    .locals 2

    .line 44
    invoke-direct {p0}, Lorg/nameless/gamespace/data/GameSession;->getDb()Landroid/content/SharedPreferences;

    move-result-object v0

    invoke-interface {v0}, Landroid/content/SharedPreferences;->edit()Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    const-string v1, ""

    if-eqz p1, :cond_0

    .line 47
    :try_start_0
    iget-object p0, p0, Lorg/nameless/gamespace/data/GameSession;->gson:Lcom/google/gson/Gson;

    invoke-virtual {p0, p1}, Lcom/google/gson/Gson;->toJson(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0
    :try_end_0
    .catch Ljava/lang/RuntimeException; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-object p0, v1

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    :goto_0
    if-nez p0, :cond_1

    goto :goto_1

    :cond_1
    move-object v1, p0

    :goto_1
    const-string p0, "session"

    .line 45
    invoke-interface {v0, p0, v1}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    move-result-object p0

    .line 52
    invoke-interface {p0}, Landroid/content/SharedPreferences$Editor;->apply()V

    return-void
.end method


# virtual methods
.method public final finalize()V
    .locals 0

    .line 103
    invoke-virtual {p0}, Lorg/nameless/gamespace/data/GameSession;->unregister()V

    return-void
.end method

.method public final register(Ljava/lang/String;)V
    .locals 8

    const-string v0, "sessionName"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 55
    invoke-direct {p0}, Lorg/nameless/gamespace/data/GameSession;->getState()Lorg/nameless/gamespace/data/SessionState;

    move-result-object v0

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Lorg/nameless/gamespace/data/SessionState;->getPackageName()Ljava/lang/String;

    move-result-object v0

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    invoke-static {v0, p1}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_1

    invoke-virtual {p0}, Lorg/nameless/gamespace/data/GameSession;->unregister()V

    .line 57
    :cond_1
    new-instance v0, Lorg/nameless/gamespace/data/SessionState;

    .line 59
    iget-object v1, p0, Lorg/nameless/gamespace/data/GameSession;->systemSettings:Lorg/nameless/gamespace/data/SystemSettings;

    invoke-virtual {v1}, Lorg/nameless/gamespace/data/SystemSettings;->getAutoBrightness()Z

    move-result v1

    invoke-static {v1}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v3

    .line 60
    iget-object v1, p0, Lorg/nameless/gamespace/data/GameSession;->systemSettings:Lorg/nameless/gamespace/data/SystemSettings;

    invoke-virtual {v1}, Lorg/nameless/gamespace/data/SystemSettings;->getThreeScreenshot()Z

    move-result v1

    invoke-static {v1}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v4

    .line 61
    iget-object v1, p0, Lorg/nameless/gamespace/data/GameSession;->systemSettings:Lorg/nameless/gamespace/data/SystemSettings;

    invoke-virtual {v1}, Lorg/nameless/gamespace/data/SystemSettings;->getHeadsUp()Z

    move-result v1

    invoke-static {v1}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v5

    .line 62
    invoke-direct {p0}, Lorg/nameless/gamespace/data/GameSession;->getAudioManager()Landroid/media/AudioManager;

    move-result-object v1

    invoke-virtual {v1}, Landroid/media/AudioManager;->getRingerModeInternal()I

    move-result v6

    .line 63
    iget-object v1, p0, Lorg/nameless/gamespace/data/GameSession;->systemSettings:Lorg/nameless/gamespace/data/SystemSettings;

    invoke-virtual {v1}, Lorg/nameless/gamespace/data/SystemSettings;->getAdbEnabled()Z

    move-result v1

    invoke-static {v1}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v7

    move-object v1, v0

    move-object v2, p1

    .line 57
    invoke-direct/range {v1 .. v7}, Lorg/nameless/gamespace/data/SessionState;-><init>(Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;ILjava/lang/Boolean;)V

    invoke-direct {p0, v0}, Lorg/nameless/gamespace/data/GameSession;->setState(Lorg/nameless/gamespace/data/SessionState;)V

    .line 65
    iget-object p1, p0, Lorg/nameless/gamespace/data/GameSession;->appSettings:Lorg/nameless/gamespace/data/AppSettings;

    invoke-virtual {p1}, Lorg/nameless/gamespace/data/AppSettings;->getNoAutoBrightness()Z

    move-result p1

    const/4 v0, 0x0

    if-eqz p1, :cond_2

    .line 66
    iget-object p1, p0, Lorg/nameless/gamespace/data/GameSession;->systemSettings:Lorg/nameless/gamespace/data/SystemSettings;

    invoke-virtual {p1, v0}, Lorg/nameless/gamespace/data/SystemSettings;->setAutoBrightness(Z)V

    .line 68
    :cond_2
    iget-object p1, p0, Lorg/nameless/gamespace/data/GameSession;->appSettings:Lorg/nameless/gamespace/data/AppSettings;

    invoke-virtual {p1}, Lorg/nameless/gamespace/data/AppSettings;->getNoThreeScreenshot()Z

    move-result p1

    if-eqz p1, :cond_3

    .line 69
    iget-object p1, p0, Lorg/nameless/gamespace/data/GameSession;->systemSettings:Lorg/nameless/gamespace/data/SystemSettings;

    invoke-virtual {p1, v0}, Lorg/nameless/gamespace/data/SystemSettings;->setThreeScreenshot(Z)V

    .line 71
    :cond_3
    iget-object p1, p0, Lorg/nameless/gamespace/data/GameSession;->appSettings:Lorg/nameless/gamespace/data/AppSettings;

    invoke-virtual {p1}, Lorg/nameless/gamespace/data/AppSettings;->getNoAdbEnabled()Z

    move-result p1

    if-eqz p1, :cond_4

    .line 72
    iget-object p1, p0, Lorg/nameless/gamespace/data/GameSession;->systemSettings:Lorg/nameless/gamespace/data/SystemSettings;

    invoke-virtual {p1, v0}, Lorg/nameless/gamespace/data/SystemSettings;->setAdbEnabled(Z)V

    .line 74
    :cond_4
    iget-object p1, p0, Lorg/nameless/gamespace/data/GameSession;->appSettings:Lorg/nameless/gamespace/data/AppSettings;

    invoke-virtual {p1}, Lorg/nameless/gamespace/data/AppSettings;->getNotificationsMode()I

    move-result p1

    if-eqz p1, :cond_6

    iget-object p1, p0, Lorg/nameless/gamespace/data/GameSession;->appSettings:Lorg/nameless/gamespace/data/AppSettings;

    invoke-virtual {p1}, Lorg/nameless/gamespace/data/AppSettings;->getNotificationsMode()I

    move-result p1

    const/4 v1, 0x2

    if-ne p1, v1, :cond_5

    goto :goto_1

    .line 77
    :cond_5
    iget-object p1, p0, Lorg/nameless/gamespace/data/GameSession;->systemSettings:Lorg/nameless/gamespace/data/SystemSettings;

    const/4 v0, 0x1

    invoke-virtual {p1, v0}, Lorg/nameless/gamespace/data/SystemSettings;->setHeadsUp(Z)V

    goto :goto_2

    .line 75
    :cond_6
    :goto_1
    iget-object p1, p0, Lorg/nameless/gamespace/data/GameSession;->systemSettings:Lorg/nameless/gamespace/data/SystemSettings;

    invoke-virtual {p1, v0}, Lorg/nameless/gamespace/data/SystemSettings;->setHeadsUp(Z)V

    .line 79
    :goto_2
    iget-object p1, p0, Lorg/nameless/gamespace/data/GameSession;->appSettings:Lorg/nameless/gamespace/data/AppSettings;

    invoke-virtual {p1}, Lorg/nameless/gamespace/data/AppSettings;->getRingerMode()I

    move-result p1

    const/4 v0, 0x3

    if-eq p1, v0, :cond_7

    .line 80
    invoke-direct {p0}, Lorg/nameless/gamespace/data/GameSession;->getAudioManager()Landroid/media/AudioManager;

    move-result-object p1

    iget-object p0, p0, Lorg/nameless/gamespace/data/GameSession;->appSettings:Lorg/nameless/gamespace/data/AppSettings;

    invoke-virtual {p0}, Lorg/nameless/gamespace/data/AppSettings;->getRingerMode()I

    move-result p0

    invoke-virtual {p1, p0}, Landroid/media/AudioManager;->setRingerModeInternal(I)V

    :cond_7
    return-void
.end method

.method public final unregister()V
    .locals 9

    .line 85
    invoke-direct {p0}, Lorg/nameless/gamespace/data/GameSession;->getState()Lorg/nameless/gamespace/data/SessionState;

    move-result-object v0

    if-eqz v0, :cond_6

    const/4 v1, 0x0

    const/4 v2, 0x0

    const/4 v3, 0x0

    const/4 v4, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    const/16 v7, 0x3f

    const/4 v8, 0x0

    invoke-static/range {v0 .. v8}, Lorg/nameless/gamespace/data/SessionState;->copy$default(Lorg/nameless/gamespace/data/SessionState;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;ILjava/lang/Boolean;ILjava/lang/Object;)Lorg/nameless/gamespace/data/SessionState;

    move-result-object v0

    if-nez v0, :cond_0

    goto :goto_0

    .line 86
    :cond_0
    iget-object v1, p0, Lorg/nameless/gamespace/data/GameSession;->appSettings:Lorg/nameless/gamespace/data/AppSettings;

    invoke-virtual {v1}, Lorg/nameless/gamespace/data/AppSettings;->getNoAutoBrightness()Z

    move-result v1

    if-eqz v1, :cond_1

    .line 87
    invoke-virtual {v0}, Lorg/nameless/gamespace/data/SessionState;->getAutoBrightness()Ljava/lang/Boolean;

    move-result-object v1

    if-eqz v1, :cond_1

    invoke-virtual {v1}, Ljava/lang/Boolean;->booleanValue()Z

    move-result v1

    iget-object v2, p0, Lorg/nameless/gamespace/data/GameSession;->systemSettings:Lorg/nameless/gamespace/data/SystemSettings;

    invoke-virtual {v2, v1}, Lorg/nameless/gamespace/data/SystemSettings;->setAutoBrightness(Z)V

    .line 89
    :cond_1
    iget-object v1, p0, Lorg/nameless/gamespace/data/GameSession;->appSettings:Lorg/nameless/gamespace/data/AppSettings;

    invoke-virtual {v1}, Lorg/nameless/gamespace/data/AppSettings;->getNoThreeScreenshot()Z

    move-result v1

    if-eqz v1, :cond_2

    .line 90
    invoke-virtual {v0}, Lorg/nameless/gamespace/data/SessionState;->getThreeScreenshot()Ljava/lang/Boolean;

    move-result-object v1

    if-eqz v1, :cond_2

    invoke-virtual {v1}, Ljava/lang/Boolean;->booleanValue()Z

    move-result v1

    iget-object v2, p0, Lorg/nameless/gamespace/data/GameSession;->systemSettings:Lorg/nameless/gamespace/data/SystemSettings;

    invoke-virtual {v2, v1}, Lorg/nameless/gamespace/data/SystemSettings;->setThreeScreenshot(Z)V

    .line 92
    :cond_2
    iget-object v1, p0, Lorg/nameless/gamespace/data/GameSession;->appSettings:Lorg/nameless/gamespace/data/AppSettings;

    invoke-virtual {v1}, Lorg/nameless/gamespace/data/AppSettings;->getNoAdbEnabled()Z

    move-result v1

    if-eqz v1, :cond_3

    .line 93
    invoke-virtual {v0}, Lorg/nameless/gamespace/data/SessionState;->getAdbEnabled()Ljava/lang/Boolean;

    move-result-object v1

    if-eqz v1, :cond_3

    invoke-virtual {v1}, Ljava/lang/Boolean;->booleanValue()Z

    move-result v1

    iget-object v2, p0, Lorg/nameless/gamespace/data/GameSession;->systemSettings:Lorg/nameless/gamespace/data/SystemSettings;

    invoke-virtual {v2, v1}, Lorg/nameless/gamespace/data/SystemSettings;->setAdbEnabled(Z)V

    .line 95
    :cond_3
    invoke-virtual {v0}, Lorg/nameless/gamespace/data/SessionState;->getHeadsUp()Ljava/lang/Boolean;

    move-result-object v1

    if-eqz v1, :cond_4

    invoke-virtual {v1}, Ljava/lang/Boolean;->booleanValue()Z

    move-result v1

    iget-object v2, p0, Lorg/nameless/gamespace/data/GameSession;->systemSettings:Lorg/nameless/gamespace/data/SystemSettings;

    invoke-virtual {v2, v1}, Lorg/nameless/gamespace/data/SystemSettings;->setHeadsUp(Z)V

    .line 96
    :cond_4
    iget-object v1, p0, Lorg/nameless/gamespace/data/GameSession;->appSettings:Lorg/nameless/gamespace/data/AppSettings;

    invoke-virtual {v1}, Lorg/nameless/gamespace/data/AppSettings;->getRingerMode()I

    move-result v1

    const/4 v2, 0x3

    if-eq v1, v2, :cond_5

    .line 97
    invoke-direct {p0}, Lorg/nameless/gamespace/data/GameSession;->getAudioManager()Landroid/media/AudioManager;

    move-result-object v1

    invoke-virtual {v0}, Lorg/nameless/gamespace/data/SessionState;->getRingerMode()I

    move-result v0

    invoke-virtual {v1, v0}, Landroid/media/AudioManager;->setRingerModeInternal(I)V

    :cond_5
    const/4 v0, 0x0

    .line 99
    invoke-direct {p0, v0}, Lorg/nameless/gamespace/data/GameSession;->setState(Lorg/nameless/gamespace/data/SessionState;)V

    :cond_6
    :goto_0
    return-void
.end method
