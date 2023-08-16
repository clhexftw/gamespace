.class public final Lorg/nameless/gamespace/utils/di/MainModule_ProvideGameSessionFactory;
.super Ljava/lang/Object;
.source "MainModule_ProvideGameSessionFactory.java"

# interfaces
.implements Ljavax/inject/Provider;


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Object;",
        "Ljavax/inject/Provider;"
    }
.end annotation


# instance fields
.field private final appSettingsProvider:Ljavax/inject/Provider;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljavax/inject/Provider<",
            "Lorg/nameless/gamespace/data/AppSettings;",
            ">;"
        }
    .end annotation
.end field

.field private final contextProvider:Ljavax/inject/Provider;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljavax/inject/Provider<",
            "Landroid/content/Context;",
            ">;"
        }
    .end annotation
.end field

.field private final gsonProvider:Ljavax/inject/Provider;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljavax/inject/Provider<",
            "Lcom/google/gson/Gson;",
            ">;"
        }
    .end annotation
.end field

.field private final systemSettingsProvider:Ljavax/inject/Provider;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljavax/inject/Provider<",
            "Lorg/nameless/gamespace/data/SystemSettings;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method public constructor <init>(Ljavax/inject/Provider;Ljavax/inject/Provider;Ljavax/inject/Provider;Ljavax/inject/Provider;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljavax/inject/Provider<",
            "Landroid/content/Context;",
            ">;",
            "Ljavax/inject/Provider<",
            "Lorg/nameless/gamespace/data/AppSettings;",
            ">;",
            "Ljavax/inject/Provider<",
            "Lorg/nameless/gamespace/data/SystemSettings;",
            ">;",
            "Ljavax/inject/Provider<",
            "Lcom/google/gson/Gson;",
            ">;)V"
        }
    .end annotation

    .line 34
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 35
    iput-object p1, p0, Lorg/nameless/gamespace/utils/di/MainModule_ProvideGameSessionFactory;->contextProvider:Ljavax/inject/Provider;

    .line 36
    iput-object p2, p0, Lorg/nameless/gamespace/utils/di/MainModule_ProvideGameSessionFactory;->appSettingsProvider:Ljavax/inject/Provider;

    .line 37
    iput-object p3, p0, Lorg/nameless/gamespace/utils/di/MainModule_ProvideGameSessionFactory;->systemSettingsProvider:Ljavax/inject/Provider;

    .line 38
    iput-object p4, p0, Lorg/nameless/gamespace/utils/di/MainModule_ProvideGameSessionFactory;->gsonProvider:Ljavax/inject/Provider;

    return-void
.end method

.method public static create(Ljavax/inject/Provider;Ljavax/inject/Provider;Ljavax/inject/Provider;Ljavax/inject/Provider;)Lorg/nameless/gamespace/utils/di/MainModule_ProvideGameSessionFactory;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljavax/inject/Provider<",
            "Landroid/content/Context;",
            ">;",
            "Ljavax/inject/Provider<",
            "Lorg/nameless/gamespace/data/AppSettings;",
            ">;",
            "Ljavax/inject/Provider<",
            "Lorg/nameless/gamespace/data/SystemSettings;",
            ">;",
            "Ljavax/inject/Provider<",
            "Lcom/google/gson/Gson;",
            ">;)",
            "Lorg/nameless/gamespace/utils/di/MainModule_ProvideGameSessionFactory;"
        }
    .end annotation

    .line 49
    new-instance v0, Lorg/nameless/gamespace/utils/di/MainModule_ProvideGameSessionFactory;

    invoke-direct {v0, p0, p1, p2, p3}, Lorg/nameless/gamespace/utils/di/MainModule_ProvideGameSessionFactory;-><init>(Ljavax/inject/Provider;Ljavax/inject/Provider;Ljavax/inject/Provider;Ljavax/inject/Provider;)V

    return-object v0
.end method

.method public static provideGameSession(Landroid/content/Context;Lorg/nameless/gamespace/data/AppSettings;Lorg/nameless/gamespace/data/SystemSettings;Lcom/google/gson/Gson;)Lorg/nameless/gamespace/data/GameSession;
    .locals 1

    .line 54
    sget-object v0, Lorg/nameless/gamespace/utils/di/MainModule;->INSTANCE:Lorg/nameless/gamespace/utils/di/MainModule;

    invoke-virtual {v0, p0, p1, p2, p3}, Lorg/nameless/gamespace/utils/di/MainModule;->provideGameSession(Landroid/content/Context;Lorg/nameless/gamespace/data/AppSettings;Lorg/nameless/gamespace/data/SystemSettings;Lcom/google/gson/Gson;)Lorg/nameless/gamespace/data/GameSession;

    move-result-object p0

    invoke-static {p0}, Ldagger/internal/Preconditions;->checkNotNullFromProvides(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lorg/nameless/gamespace/data/GameSession;

    return-object p0
.end method


# virtual methods
.method public bridge synthetic get()Ljava/lang/Object;
    .locals 0

    .line 14
    invoke-virtual {p0}, Lorg/nameless/gamespace/utils/di/MainModule_ProvideGameSessionFactory;->get()Lorg/nameless/gamespace/data/GameSession;

    move-result-object p0

    return-object p0
.end method

.method public get()Lorg/nameless/gamespace/data/GameSession;
    .locals 3

    .line 43
    iget-object v0, p0, Lorg/nameless/gamespace/utils/di/MainModule_ProvideGameSessionFactory;->contextProvider:Ljavax/inject/Provider;

    invoke-interface {v0}, Ljavax/inject/Provider;->get()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/content/Context;

    iget-object v1, p0, Lorg/nameless/gamespace/utils/di/MainModule_ProvideGameSessionFactory;->appSettingsProvider:Ljavax/inject/Provider;

    invoke-interface {v1}, Ljavax/inject/Provider;->get()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lorg/nameless/gamespace/data/AppSettings;

    iget-object v2, p0, Lorg/nameless/gamespace/utils/di/MainModule_ProvideGameSessionFactory;->systemSettingsProvider:Ljavax/inject/Provider;

    invoke-interface {v2}, Ljavax/inject/Provider;->get()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lorg/nameless/gamespace/data/SystemSettings;

    iget-object p0, p0, Lorg/nameless/gamespace/utils/di/MainModule_ProvideGameSessionFactory;->gsonProvider:Ljavax/inject/Provider;

    invoke-interface {p0}, Ljavax/inject/Provider;->get()Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lcom/google/gson/Gson;

    invoke-static {v0, v1, v2, p0}, Lorg/nameless/gamespace/utils/di/MainModule_ProvideGameSessionFactory;->provideGameSession(Landroid/content/Context;Lorg/nameless/gamespace/data/AppSettings;Lorg/nameless/gamespace/data/SystemSettings;Lcom/google/gson/Gson;)Lorg/nameless/gamespace/data/GameSession;

    move-result-object p0

    return-object p0
.end method
