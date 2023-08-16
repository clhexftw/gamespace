.class public abstract Lorg/nameless/gamespace/preferences/appselector/Hilt_AppSelectorFragment;
.super Landroidx/fragment/app/Fragment;
.source "Hilt_AppSelectorFragment.java"

# interfaces
.implements Ldagger/hilt/internal/GeneratedComponentManager;


# instance fields
.field private componentContext:Landroid/content/ContextWrapper;

.field private volatile componentManager:Ldagger/hilt/android/internal/managers/FragmentComponentManager;

.field private final componentManagerLock:Ljava/lang/Object;

.field private injected:Z


# direct methods
.method constructor <init>()V
    .locals 1

    .line 37
    invoke-direct {p0}, Landroidx/fragment/app/Fragment;-><init>()V

    .line 32
    new-instance v0, Ljava/lang/Object;

    invoke-direct {v0}, Ljava/lang/Object;-><init>()V

    iput-object v0, p0, Lorg/nameless/gamespace/preferences/appselector/Hilt_AppSelectorFragment;->componentManagerLock:Ljava/lang/Object;

    const/4 v0, 0x0

    .line 34
    iput-boolean v0, p0, Lorg/nameless/gamespace/preferences/appselector/Hilt_AppSelectorFragment;->injected:Z

    return-void
.end method

.method private initializeComponentContext()V
    .locals 1

    .line 62
    iget-object v0, p0, Lorg/nameless/gamespace/preferences/appselector/Hilt_AppSelectorFragment;->componentContext:Landroid/content/ContextWrapper;

    if-nez v0, :cond_0

    .line 64
    invoke-super {p0}, Landroidx/fragment/app/Fragment;->getContext()Landroid/content/Context;

    move-result-object v0

    invoke-static {v0, p0}, Ldagger/hilt/android/internal/managers/FragmentComponentManager;->createContextWrapper(Landroid/content/Context;Landroidx/fragment/app/Fragment;)Landroid/content/ContextWrapper;

    move-result-object v0

    iput-object v0, p0, Lorg/nameless/gamespace/preferences/appselector/Hilt_AppSelectorFragment;->componentContext:Landroid/content/ContextWrapper;

    .line 65
    invoke-virtual {p0}, Lorg/nameless/gamespace/preferences/appselector/Hilt_AppSelectorFragment;->inject()V

    :cond_0
    return-void
.end method


# virtual methods
.method public final componentManager()Ldagger/hilt/android/internal/managers/FragmentComponentManager;
    .locals 2

    .line 91
    iget-object v0, p0, Lorg/nameless/gamespace/preferences/appselector/Hilt_AppSelectorFragment;->componentManager:Ldagger/hilt/android/internal/managers/FragmentComponentManager;

    if-nez v0, :cond_1

    .line 92
    iget-object v0, p0, Lorg/nameless/gamespace/preferences/appselector/Hilt_AppSelectorFragment;->componentManagerLock:Ljava/lang/Object;

    monitor-enter v0

    .line 93
    :try_start_0
    iget-object v1, p0, Lorg/nameless/gamespace/preferences/appselector/Hilt_AppSelectorFragment;->componentManager:Ldagger/hilt/android/internal/managers/FragmentComponentManager;

    if-nez v1, :cond_0

    .line 94
    invoke-virtual {p0}, Lorg/nameless/gamespace/preferences/appselector/Hilt_AppSelectorFragment;->createComponentManager()Ldagger/hilt/android/internal/managers/FragmentComponentManager;

    move-result-object v1

    iput-object v1, p0, Lorg/nameless/gamespace/preferences/appselector/Hilt_AppSelectorFragment;->componentManager:Ldagger/hilt/android/internal/managers/FragmentComponentManager;

    .line 96
    :cond_0
    monitor-exit v0

    goto :goto_0

    :catchall_0
    move-exception p0

    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw p0

    .line 98
    :cond_1
    :goto_0
    iget-object p0, p0, Lorg/nameless/gamespace/preferences/appselector/Hilt_AppSelectorFragment;->componentManager:Ldagger/hilt/android/internal/managers/FragmentComponentManager;

    return-object p0
.end method

.method protected createComponentManager()Ldagger/hilt/android/internal/managers/FragmentComponentManager;
    .locals 1

    .line 86
    new-instance v0, Ldagger/hilt/android/internal/managers/FragmentComponentManager;

    invoke-direct {v0, p0}, Ldagger/hilt/android/internal/managers/FragmentComponentManager;-><init>(Landroidx/fragment/app/Fragment;)V

    return-object v0
.end method

.method public final generatedComponent()Ljava/lang/Object;
    .locals 0

    .line 82
    invoke-virtual {p0}, Lorg/nameless/gamespace/preferences/appselector/Hilt_AppSelectorFragment;->componentManager()Ldagger/hilt/android/internal/managers/FragmentComponentManager;

    move-result-object p0

    invoke-virtual {p0}, Ldagger/hilt/android/internal/managers/FragmentComponentManager;->generatedComponent()Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public getContext()Landroid/content/Context;
    .locals 0

    .line 71
    iget-object p0, p0, Lorg/nameless/gamespace/preferences/appselector/Hilt_AppSelectorFragment;->componentContext:Landroid/content/ContextWrapper;

    return-object p0
.end method

.method protected inject()V
    .locals 1

    .line 102
    iget-boolean v0, p0, Lorg/nameless/gamespace/preferences/appselector/Hilt_AppSelectorFragment;->injected:Z

    if-nez v0, :cond_0

    const/4 v0, 0x1

    .line 103
    iput-boolean v0, p0, Lorg/nameless/gamespace/preferences/appselector/Hilt_AppSelectorFragment;->injected:Z

    .line 104
    invoke-virtual {p0}, Lorg/nameless/gamespace/preferences/appselector/Hilt_AppSelectorFragment;->generatedComponent()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lorg/nameless/gamespace/preferences/appselector/AppSelectorFragment_GeneratedInjector;

    invoke-static {p0}, Ldagger/hilt/internal/UnsafeCasts;->unsafeCast(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lorg/nameless/gamespace/preferences/appselector/AppSelectorFragment;

    invoke-interface {v0, p0}, Lorg/nameless/gamespace/preferences/appselector/AppSelectorFragment_GeneratedInjector;->injectAppSelectorFragment(Lorg/nameless/gamespace/preferences/appselector/AppSelectorFragment;)V

    :cond_0
    return-void
.end method

.method public onAttach(Landroid/app/Activity;)V
    .locals 2

    .line 55
    invoke-super {p0, p1}, Landroidx/fragment/app/Fragment;->onAttach(Landroid/app/Activity;)V

    .line 56
    iget-object v0, p0, Lorg/nameless/gamespace/preferences/appselector/Hilt_AppSelectorFragment;->componentContext:Landroid/content/ContextWrapper;

    const/4 v1, 0x0

    if-eqz v0, :cond_1

    invoke-static {v0}, Ldagger/hilt/android/internal/managers/FragmentComponentManager;->findActivity(Landroid/content/Context;)Landroid/content/Context;

    move-result-object v0

    if-ne v0, p1, :cond_0

    goto :goto_0

    :cond_0
    move p1, v1

    goto :goto_1

    :cond_1
    :goto_0
    const/4 p1, 0x1

    :goto_1
    new-array v0, v1, [Ljava/lang/Object;

    const-string v1, "onAttach called multiple times with different Context! Hilt Fragments should not be retained."

    invoke-static {p1, v1, v0}, Ldagger/hilt/internal/Preconditions;->checkState(ZLjava/lang/String;[Ljava/lang/Object;)V

    .line 57
    invoke-direct {p0}, Lorg/nameless/gamespace/preferences/appselector/Hilt_AppSelectorFragment;->initializeComponentContext()V

    return-void
.end method

.method public onAttach(Landroid/content/Context;)V
    .locals 0

    .line 47
    invoke-super {p0, p1}, Landroidx/fragment/app/Fragment;->onAttach(Landroid/content/Context;)V

    .line 48
    invoke-direct {p0}, Lorg/nameless/gamespace/preferences/appselector/Hilt_AppSelectorFragment;->initializeComponentContext()V

    return-void
.end method

.method public onGetLayoutInflater(Landroid/os/Bundle;)Landroid/view/LayoutInflater;
    .locals 0

    .line 76
    invoke-super {p0, p1}, Landroidx/fragment/app/Fragment;->onGetLayoutInflater(Landroid/os/Bundle;)Landroid/view/LayoutInflater;

    move-result-object p1

    .line 77
    invoke-static {p1, p0}, Ldagger/hilt/android/internal/managers/FragmentComponentManager;->createContextWrapper(Landroid/view/LayoutInflater;Landroidx/fragment/app/Fragment;)Landroid/content/ContextWrapper;

    move-result-object p0

    invoke-static {p0}, Landroid/view/LayoutInflater;->from(Landroid/content/Context;)Landroid/view/LayoutInflater;

    move-result-object p0

    return-object p0
.end method
