.class public final Lorg/nameless/gamespace/utils/di/MainModule_ProvideBaseGsonFactory;
.super Ljava/lang/Object;
.source "MainModule_ProvideBaseGsonFactory.java"

# interfaces
.implements Ljavax/inject/Provider;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lorg/nameless/gamespace/utils/di/MainModule_ProvideBaseGsonFactory$InstanceHolder;
    }
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Object;",
        "Ljavax/inject/Provider;"
    }
.end annotation


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public static create()Lorg/nameless/gamespace/utils/di/MainModule_ProvideBaseGsonFactory;
    .locals 1

    .line 25
    invoke-static {}, Lorg/nameless/gamespace/utils/di/MainModule_ProvideBaseGsonFactory$InstanceHolder;->-$$Nest$sfgetINSTANCE()Lorg/nameless/gamespace/utils/di/MainModule_ProvideBaseGsonFactory;

    move-result-object v0

    return-object v0
.end method

.method public static provideBaseGson()Lcom/google/gson/Gson;
    .locals 1

    .line 29
    sget-object v0, Lorg/nameless/gamespace/utils/di/MainModule;->INSTANCE:Lorg/nameless/gamespace/utils/di/MainModule;

    invoke-virtual {v0}, Lorg/nameless/gamespace/utils/di/MainModule;->provideBaseGson()Lcom/google/gson/Gson;

    move-result-object v0

    invoke-static {v0}, Ldagger/internal/Preconditions;->checkNotNullFromProvides(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/google/gson/Gson;

    return-object v0
.end method


# virtual methods
.method public get()Lcom/google/gson/Gson;
    .locals 0

    .line 21
    invoke-static {}, Lorg/nameless/gamespace/utils/di/MainModule_ProvideBaseGsonFactory;->provideBaseGson()Lcom/google/gson/Gson;

    move-result-object p0

    return-object p0
.end method

.method public bridge synthetic get()Ljava/lang/Object;
    .locals 0

    .line 9
    invoke-virtual {p0}, Lorg/nameless/gamespace/utils/di/MainModule_ProvideBaseGsonFactory;->get()Lcom/google/gson/Gson;

    move-result-object p0

    return-object p0
.end method
