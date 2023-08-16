.class public final Lorg/nameless/gamespace/utils/di/MainModule_ProvideSystemSettingsFactory;
.super Ljava/lang/Object;
.source "MainModule_ProvideSystemSettingsFactory.java"

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
.field private final contextProvider:Ljavax/inject/Provider;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljavax/inject/Provider<",
            "Landroid/content/Context;",
            ">;"
        }
    .end annotation
.end field

.field private final gameModeUtilsProvider:Ljavax/inject/Provider;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljavax/inject/Provider<",
            "Lorg/nameless/gamespace/utils/GameModeUtils;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method public constructor <init>(Ljavax/inject/Provider;Ljavax/inject/Provider;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljavax/inject/Provider<",
            "Landroid/content/Context;",
            ">;",
            "Ljavax/inject/Provider<",
            "Lorg/nameless/gamespace/utils/GameModeUtils;",
            ">;)V"
        }
    .end annotation

    .line 27
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 28
    iput-object p1, p0, Lorg/nameless/gamespace/utils/di/MainModule_ProvideSystemSettingsFactory;->contextProvider:Ljavax/inject/Provider;

    .line 29
    iput-object p2, p0, Lorg/nameless/gamespace/utils/di/MainModule_ProvideSystemSettingsFactory;->gameModeUtilsProvider:Ljavax/inject/Provider;

    return-void
.end method

.method public static create(Ljavax/inject/Provider;Ljavax/inject/Provider;)Lorg/nameless/gamespace/utils/di/MainModule_ProvideSystemSettingsFactory;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljavax/inject/Provider<",
            "Landroid/content/Context;",
            ">;",
            "Ljavax/inject/Provider<",
            "Lorg/nameless/gamespace/utils/GameModeUtils;",
            ">;)",
            "Lorg/nameless/gamespace/utils/di/MainModule_ProvideSystemSettingsFactory;"
        }
    .end annotation

    .line 39
    new-instance v0, Lorg/nameless/gamespace/utils/di/MainModule_ProvideSystemSettingsFactory;

    invoke-direct {v0, p0, p1}, Lorg/nameless/gamespace/utils/di/MainModule_ProvideSystemSettingsFactory;-><init>(Ljavax/inject/Provider;Ljavax/inject/Provider;)V

    return-object v0
.end method

.method public static provideSystemSettings(Landroid/content/Context;Lorg/nameless/gamespace/utils/GameModeUtils;)Lorg/nameless/gamespace/data/SystemSettings;
    .locals 1

    .line 43
    sget-object v0, Lorg/nameless/gamespace/utils/di/MainModule;->INSTANCE:Lorg/nameless/gamespace/utils/di/MainModule;

    invoke-virtual {v0, p0, p1}, Lorg/nameless/gamespace/utils/di/MainModule;->provideSystemSettings(Landroid/content/Context;Lorg/nameless/gamespace/utils/GameModeUtils;)Lorg/nameless/gamespace/data/SystemSettings;

    move-result-object p0

    invoke-static {p0}, Ldagger/internal/Preconditions;->checkNotNullFromProvides(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lorg/nameless/gamespace/data/SystemSettings;

    return-object p0
.end method


# virtual methods
.method public bridge synthetic get()Ljava/lang/Object;
    .locals 0

    .line 12
    invoke-virtual {p0}, Lorg/nameless/gamespace/utils/di/MainModule_ProvideSystemSettingsFactory;->get()Lorg/nameless/gamespace/data/SystemSettings;

    move-result-object p0

    return-object p0
.end method

.method public get()Lorg/nameless/gamespace/data/SystemSettings;
    .locals 1

    .line 34
    iget-object v0, p0, Lorg/nameless/gamespace/utils/di/MainModule_ProvideSystemSettingsFactory;->contextProvider:Ljavax/inject/Provider;

    invoke-interface {v0}, Ljavax/inject/Provider;->get()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/content/Context;

    iget-object p0, p0, Lorg/nameless/gamespace/utils/di/MainModule_ProvideSystemSettingsFactory;->gameModeUtilsProvider:Ljavax/inject/Provider;

    invoke-interface {p0}, Ljavax/inject/Provider;->get()Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lorg/nameless/gamespace/utils/GameModeUtils;

    invoke-static {v0, p0}, Lorg/nameless/gamespace/utils/di/MainModule_ProvideSystemSettingsFactory;->provideSystemSettings(Landroid/content/Context;Lorg/nameless/gamespace/utils/GameModeUtils;)Lorg/nameless/gamespace/data/SystemSettings;

    move-result-object p0

    return-object p0
.end method
