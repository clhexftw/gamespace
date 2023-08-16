.class public abstract Lorg/nameless/gamespace/settings/Hilt_SettingsActivity;
.super Lcom/android/settingslib/collapsingtoolbar/CollapsingToolbarBaseActivity;
.source "Hilt_SettingsActivity.java"

# interfaces
.implements Ldagger/hilt/internal/GeneratedComponentManager;


# instance fields
.field private volatile componentManager:Ldagger/hilt/android/internal/managers/ActivityComponentManager;

.field private final componentManagerLock:Ljava/lang/Object;

.field private injected:Z


# direct methods
.method constructor <init>()V
    .locals 1

    .line 27
    invoke-direct {p0}, Lcom/android/settingslib/collapsingtoolbar/CollapsingToolbarBaseActivity;-><init>()V

    .line 22
    new-instance v0, Ljava/lang/Object;

    invoke-direct {v0}, Ljava/lang/Object;-><init>()V

    iput-object v0, p0, Lorg/nameless/gamespace/settings/Hilt_SettingsActivity;->componentManagerLock:Ljava/lang/Object;

    const/4 v0, 0x0

    .line 24
    iput-boolean v0, p0, Lorg/nameless/gamespace/settings/Hilt_SettingsActivity;->injected:Z

    .line 28
    invoke-direct {p0}, Lorg/nameless/gamespace/settings/Hilt_SettingsActivity;->_initHiltInternal()V

    return-void
.end method

.method private _initHiltInternal()V
    .locals 1

    .line 32
    new-instance v0, Lorg/nameless/gamespace/settings/Hilt_SettingsActivity$1;

    invoke-direct {v0, p0}, Lorg/nameless/gamespace/settings/Hilt_SettingsActivity$1;-><init>(Lorg/nameless/gamespace/settings/Hilt_SettingsActivity;)V

    invoke-virtual {p0, v0}, Landroidx/activity/ComponentActivity;->addOnContextAvailableListener(Landroidx/activity/contextaware/OnContextAvailableListener;)V

    return-void
.end method


# virtual methods
.method public final componentManager()Ldagger/hilt/android/internal/managers/ActivityComponentManager;
    .locals 2

    .line 51
    iget-object v0, p0, Lorg/nameless/gamespace/settings/Hilt_SettingsActivity;->componentManager:Ldagger/hilt/android/internal/managers/ActivityComponentManager;

    if-nez v0, :cond_1

    .line 52
    iget-object v0, p0, Lorg/nameless/gamespace/settings/Hilt_SettingsActivity;->componentManagerLock:Ljava/lang/Object;

    monitor-enter v0

    .line 53
    :try_start_0
    iget-object v1, p0, Lorg/nameless/gamespace/settings/Hilt_SettingsActivity;->componentManager:Ldagger/hilt/android/internal/managers/ActivityComponentManager;

    if-nez v1, :cond_0

    .line 54
    invoke-virtual {p0}, Lorg/nameless/gamespace/settings/Hilt_SettingsActivity;->createComponentManager()Ldagger/hilt/android/internal/managers/ActivityComponentManager;

    move-result-object v1

    iput-object v1, p0, Lorg/nameless/gamespace/settings/Hilt_SettingsActivity;->componentManager:Ldagger/hilt/android/internal/managers/ActivityComponentManager;

    .line 56
    :cond_0
    monitor-exit v0

    goto :goto_0

    :catchall_0
    move-exception p0

    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw p0

    .line 58
    :cond_1
    :goto_0
    iget-object p0, p0, Lorg/nameless/gamespace/settings/Hilt_SettingsActivity;->componentManager:Ldagger/hilt/android/internal/managers/ActivityComponentManager;

    return-object p0
.end method

.method protected createComponentManager()Ldagger/hilt/android/internal/managers/ActivityComponentManager;
    .locals 1

    .line 46
    new-instance v0, Ldagger/hilt/android/internal/managers/ActivityComponentManager;

    invoke-direct {v0, p0}, Ldagger/hilt/android/internal/managers/ActivityComponentManager;-><init>(Landroid/app/Activity;)V

    return-object v0
.end method

.method public final generatedComponent()Ljava/lang/Object;
    .locals 0

    .line 42
    invoke-virtual {p0}, Lorg/nameless/gamespace/settings/Hilt_SettingsActivity;->componentManager()Ldagger/hilt/android/internal/managers/ActivityComponentManager;

    move-result-object p0

    invoke-virtual {p0}, Ldagger/hilt/android/internal/managers/ActivityComponentManager;->generatedComponent()Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method protected inject()V
    .locals 1

    .line 62
    iget-boolean v0, p0, Lorg/nameless/gamespace/settings/Hilt_SettingsActivity;->injected:Z

    if-nez v0, :cond_0

    const/4 v0, 0x1

    .line 63
    iput-boolean v0, p0, Lorg/nameless/gamespace/settings/Hilt_SettingsActivity;->injected:Z

    .line 64
    invoke-virtual {p0}, Lorg/nameless/gamespace/settings/Hilt_SettingsActivity;->generatedComponent()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lorg/nameless/gamespace/settings/SettingsActivity_GeneratedInjector;

    invoke-static {p0}, Ldagger/hilt/internal/UnsafeCasts;->unsafeCast(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lorg/nameless/gamespace/settings/SettingsActivity;

    invoke-interface {v0, p0}, Lorg/nameless/gamespace/settings/SettingsActivity_GeneratedInjector;->injectSettingsActivity(Lorg/nameless/gamespace/settings/SettingsActivity;)V

    :cond_0
    return-void
.end method
