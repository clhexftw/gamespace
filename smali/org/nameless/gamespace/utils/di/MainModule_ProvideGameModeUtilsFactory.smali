.class public final Lorg/nameless/gamespace/utils/di/MainModule_ProvideGameModeUtilsFactory;
.super Ljava/lang/Object;
.source "MainModule_ProvideGameModeUtilsFactory.java"

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


# direct methods
.method public constructor <init>(Ljavax/inject/Provider;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljavax/inject/Provider<",
            "Landroid/content/Context;",
            ">;)V"
        }
    .end annotation

    .line 23
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 24
    iput-object p1, p0, Lorg/nameless/gamespace/utils/di/MainModule_ProvideGameModeUtilsFactory;->contextProvider:Ljavax/inject/Provider;

    return-void
.end method

.method public static create(Ljavax/inject/Provider;)Lorg/nameless/gamespace/utils/di/MainModule_ProvideGameModeUtilsFactory;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljavax/inject/Provider<",
            "Landroid/content/Context;",
            ">;)",
            "Lorg/nameless/gamespace/utils/di/MainModule_ProvideGameModeUtilsFactory;"
        }
    .end annotation

    .line 33
    new-instance v0, Lorg/nameless/gamespace/utils/di/MainModule_ProvideGameModeUtilsFactory;

    invoke-direct {v0, p0}, Lorg/nameless/gamespace/utils/di/MainModule_ProvideGameModeUtilsFactory;-><init>(Ljavax/inject/Provider;)V

    return-object v0
.end method

.method public static provideGameModeUtils(Landroid/content/Context;)Lorg/nameless/gamespace/utils/GameModeUtils;
    .locals 1

    .line 37
    sget-object v0, Lorg/nameless/gamespace/utils/di/MainModule;->INSTANCE:Lorg/nameless/gamespace/utils/di/MainModule;

    invoke-virtual {v0, p0}, Lorg/nameless/gamespace/utils/di/MainModule;->provideGameModeUtils(Landroid/content/Context;)Lorg/nameless/gamespace/utils/GameModeUtils;

    move-result-object p0

    invoke-static {p0}, Ldagger/internal/Preconditions;->checkNotNullFromProvides(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lorg/nameless/gamespace/utils/GameModeUtils;

    return-object p0
.end method


# virtual methods
.method public bridge synthetic get()Ljava/lang/Object;
    .locals 0

    .line 11
    invoke-virtual {p0}, Lorg/nameless/gamespace/utils/di/MainModule_ProvideGameModeUtilsFactory;->get()Lorg/nameless/gamespace/utils/GameModeUtils;

    move-result-object p0

    return-object p0
.end method

.method public get()Lorg/nameless/gamespace/utils/GameModeUtils;
    .locals 0

    .line 29
    iget-object p0, p0, Lorg/nameless/gamespace/utils/di/MainModule_ProvideGameModeUtilsFactory;->contextProvider:Ljavax/inject/Provider;

    invoke-interface {p0}, Ljavax/inject/Provider;->get()Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Landroid/content/Context;

    invoke-static {p0}, Lorg/nameless/gamespace/utils/di/MainModule_ProvideGameModeUtilsFactory;->provideGameModeUtils(Landroid/content/Context;)Lorg/nameless/gamespace/utils/GameModeUtils;

    move-result-object p0

    return-object p0
.end method