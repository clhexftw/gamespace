.class public final Landroidx/savedstate/SavedStateRegistryController;
.super Ljava/lang/Object;
.source "SavedStateRegistryController.java"


# instance fields
.field private mAttached:Z

.field private final mOwner:Landroidx/savedstate/SavedStateRegistryOwner;

.field private final mRegistry:Landroidx/savedstate/SavedStateRegistry;


# direct methods
.method private constructor <init>(Landroidx/savedstate/SavedStateRegistryOwner;)V
    .locals 1

    .line 38
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const/4 v0, 0x0

    .line 36
    iput-boolean v0, p0, Landroidx/savedstate/SavedStateRegistryController;->mAttached:Z

    .line 39
    iput-object p1, p0, Landroidx/savedstate/SavedStateRegistryController;->mOwner:Landroidx/savedstate/SavedStateRegistryOwner;

    .line 40
    new-instance p1, Landroidx/savedstate/SavedStateRegistry;

    invoke-direct {p1}, Landroidx/savedstate/SavedStateRegistry;-><init>()V

    iput-object p1, p0, Landroidx/savedstate/SavedStateRegistryController;->mRegistry:Landroidx/savedstate/SavedStateRegistry;

    return-void
.end method

.method public static create(Landroidx/savedstate/SavedStateRegistryOwner;)Landroidx/savedstate/SavedStateRegistryController;
    .locals 1

    .line 109
    new-instance v0, Landroidx/savedstate/SavedStateRegistryController;

    invoke-direct {v0, p0}, Landroidx/savedstate/SavedStateRegistryController;-><init>(Landroidx/savedstate/SavedStateRegistryOwner;)V

    return-object v0
.end method


# virtual methods
.method public getSavedStateRegistry()Landroidx/savedstate/SavedStateRegistry;
    .locals 0

    .line 48
    iget-object p0, p0, Landroidx/savedstate/SavedStateRegistryController;->mRegistry:Landroidx/savedstate/SavedStateRegistry;

    return-object p0
.end method

.method public performAttach()V
    .locals 3

    .line 59
    iget-object v0, p0, Landroidx/savedstate/SavedStateRegistryController;->mOwner:Landroidx/savedstate/SavedStateRegistryOwner;

    invoke-interface {v0}, Landroidx/lifecycle/LifecycleOwner;->getLifecycle()Landroidx/lifecycle/Lifecycle;

    move-result-object v0

    .line 60
    invoke-virtual {v0}, Landroidx/lifecycle/Lifecycle;->getCurrentState()Landroidx/lifecycle/Lifecycle$State;

    move-result-object v1

    sget-object v2, Landroidx/lifecycle/Lifecycle$State;->INITIALIZED:Landroidx/lifecycle/Lifecycle$State;

    if-ne v1, v2, :cond_0

    .line 64
    new-instance v1, Landroidx/savedstate/Recreator;

    iget-object v2, p0, Landroidx/savedstate/SavedStateRegistryController;->mOwner:Landroidx/savedstate/SavedStateRegistryOwner;

    invoke-direct {v1, v2}, Landroidx/savedstate/Recreator;-><init>(Landroidx/savedstate/SavedStateRegistryOwner;)V

    invoke-virtual {v0, v1}, Landroidx/lifecycle/Lifecycle;->addObserver(Landroidx/lifecycle/LifecycleObserver;)V

    .line 65
    iget-object v1, p0, Landroidx/savedstate/SavedStateRegistryController;->mRegistry:Landroidx/savedstate/SavedStateRegistry;

    invoke-virtual {v1, v0}, Landroidx/savedstate/SavedStateRegistry;->performAttach(Landroidx/lifecycle/Lifecycle;)V

    const/4 v0, 0x1

    .line 67
    iput-boolean v0, p0, Landroidx/savedstate/SavedStateRegistryController;->mAttached:Z

    return-void

    .line 61
    :cond_0
    new-instance p0, Ljava/lang/IllegalStateException;

    const-string v0, "Restarter must be created only during owner\'s initialization stage"

    invoke-direct {p0, v0}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw p0
.end method

.method public performRestore(Landroid/os/Bundle;)V
    .locals 3

    .line 79
    iget-boolean v0, p0, Landroidx/savedstate/SavedStateRegistryController;->mAttached:Z

    if-nez v0, :cond_0

    .line 80
    invoke-virtual {p0}, Landroidx/savedstate/SavedStateRegistryController;->performAttach()V

    .line 82
    :cond_0
    iget-object v0, p0, Landroidx/savedstate/SavedStateRegistryController;->mOwner:Landroidx/savedstate/SavedStateRegistryOwner;

    invoke-interface {v0}, Landroidx/lifecycle/LifecycleOwner;->getLifecycle()Landroidx/lifecycle/Lifecycle;

    move-result-object v0

    .line 83
    invoke-virtual {v0}, Landroidx/lifecycle/Lifecycle;->getCurrentState()Landroidx/lifecycle/Lifecycle$State;

    move-result-object v1

    sget-object v2, Landroidx/lifecycle/Lifecycle$State;->STARTED:Landroidx/lifecycle/Lifecycle$State;

    invoke-virtual {v1, v2}, Landroidx/lifecycle/Lifecycle$State;->isAtLeast(Landroidx/lifecycle/Lifecycle$State;)Z

    move-result v1

    if-nez v1, :cond_1

    .line 87
    iget-object p0, p0, Landroidx/savedstate/SavedStateRegistryController;->mRegistry:Landroidx/savedstate/SavedStateRegistry;

    invoke-virtual {p0, p1}, Landroidx/savedstate/SavedStateRegistry;->performRestore(Landroid/os/Bundle;)V

    return-void

    .line 84
    :cond_1
    new-instance p0, Ljava/lang/IllegalStateException;

    new-instance p1, Ljava/lang/StringBuilder;

    invoke-direct {p1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "performRestore cannot be called when owner  is "

    invoke-virtual {p1, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 85
    invoke-virtual {v0}, Landroidx/lifecycle/Lifecycle;->getCurrentState()Landroidx/lifecycle/Lifecycle$State;

    move-result-object v0

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-direct {p0, p1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw p0
.end method

.method public performSave(Landroid/os/Bundle;)V
    .locals 0

    .line 99
    iget-object p0, p0, Landroidx/savedstate/SavedStateRegistryController;->mRegistry:Landroidx/savedstate/SavedStateRegistry;

    invoke-virtual {p0, p1}, Landroidx/savedstate/SavedStateRegistry;->performSave(Landroid/os/Bundle;)V

    return-void
.end method
