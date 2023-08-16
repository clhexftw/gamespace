.class public abstract Lorg/nameless/gamespace/Hilt_GameSpace;
.super Landroid/app/Application;
.source "Hilt_GameSpace.java"

# interfaces
.implements Ldagger/hilt/internal/GeneratedComponentManager;


# instance fields
.field private final componentManager:Ldagger/hilt/android/internal/managers/ApplicationComponentManager;


# direct methods
.method public constructor <init>()V
    .locals 2

    .line 18
    invoke-direct {p0}, Landroid/app/Application;-><init>()V

    .line 19
    new-instance v0, Ldagger/hilt/android/internal/managers/ApplicationComponentManager;

    new-instance v1, Lorg/nameless/gamespace/Hilt_GameSpace$1;

    invoke-direct {v1, p0}, Lorg/nameless/gamespace/Hilt_GameSpace$1;-><init>(Lorg/nameless/gamespace/Hilt_GameSpace;)V

    invoke-direct {v0, v1}, Ldagger/hilt/android/internal/managers/ApplicationComponentManager;-><init>(Ldagger/hilt/android/internal/managers/ComponentSupplier;)V

    iput-object v0, p0, Lorg/nameless/gamespace/Hilt_GameSpace;->componentManager:Ldagger/hilt/android/internal/managers/ApplicationComponentManager;

    return-void
.end method


# virtual methods
.method public final componentManager()Ldagger/hilt/android/internal/managers/ApplicationComponentManager;
    .locals 0

    .line 30
    iget-object p0, p0, Lorg/nameless/gamespace/Hilt_GameSpace;->componentManager:Ldagger/hilt/android/internal/managers/ApplicationComponentManager;

    return-object p0
.end method

.method public final generatedComponent()Ljava/lang/Object;
    .locals 0

    .line 35
    invoke-virtual {p0}, Lorg/nameless/gamespace/Hilt_GameSpace;->componentManager()Ldagger/hilt/android/internal/managers/ApplicationComponentManager;

    move-result-object p0

    invoke-virtual {p0}, Ldagger/hilt/android/internal/managers/ApplicationComponentManager;->generatedComponent()Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public onCreate()V
    .locals 2

    .line 43
    invoke-virtual {p0}, Lorg/nameless/gamespace/Hilt_GameSpace;->generatedComponent()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lorg/nameless/gamespace/GameSpace_GeneratedInjector;

    invoke-static {p0}, Ldagger/hilt/internal/UnsafeCasts;->unsafeCast(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lorg/nameless/gamespace/GameSpace;

    invoke-interface {v0, v1}, Lorg/nameless/gamespace/GameSpace_GeneratedInjector;->injectGameSpace(Lorg/nameless/gamespace/GameSpace;)V

    .line 44
    invoke-super {p0}, Landroid/app/Application;->onCreate()V

    return-void
.end method
