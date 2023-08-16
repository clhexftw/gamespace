.class public final Landroidx/lifecycle/SavedStateHandleSupport;
.super Ljava/lang/Object;
.source "SavedStateHandleSupport.kt"


# static fields
.field public static final DEFAULT_ARGS_KEY:Landroidx/lifecycle/viewmodel/CreationExtras$Key;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroidx/lifecycle/viewmodel/CreationExtras$Key<",
            "Landroid/os/Bundle;",
            ">;"
        }
    .end annotation
.end field

.field public static final SAVED_STATE_REGISTRY_OWNER_KEY:Landroidx/lifecycle/viewmodel/CreationExtras$Key;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroidx/lifecycle/viewmodel/CreationExtras$Key<",
            "Landroidx/savedstate/SavedStateRegistryOwner;",
            ">;"
        }
    .end annotation
.end field

.field public static final VIEW_MODEL_STORE_OWNER_KEY:Landroidx/lifecycle/viewmodel/CreationExtras$Key;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroidx/lifecycle/viewmodel/CreationExtras$Key<",
            "Landroidx/lifecycle/ViewModelStoreOwner;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .line 150
    new-instance v0, Landroidx/lifecycle/SavedStateHandleSupport$SAVED_STATE_REGISTRY_OWNER_KEY$1;

    invoke-direct {v0}, Landroidx/lifecycle/SavedStateHandleSupport$SAVED_STATE_REGISTRY_OWNER_KEY$1;-><init>()V

    sput-object v0, Landroidx/lifecycle/SavedStateHandleSupport;->SAVED_STATE_REGISTRY_OWNER_KEY:Landroidx/lifecycle/viewmodel/CreationExtras$Key;

    .line 156
    new-instance v0, Landroidx/lifecycle/SavedStateHandleSupport$VIEW_MODEL_STORE_OWNER_KEY$1;

    invoke-direct {v0}, Landroidx/lifecycle/SavedStateHandleSupport$VIEW_MODEL_STORE_OWNER_KEY$1;-><init>()V

    sput-object v0, Landroidx/lifecycle/SavedStateHandleSupport;->VIEW_MODEL_STORE_OWNER_KEY:Landroidx/lifecycle/viewmodel/CreationExtras$Key;

    .line 162
    new-instance v0, Landroidx/lifecycle/SavedStateHandleSupport$DEFAULT_ARGS_KEY$1;

    invoke-direct {v0}, Landroidx/lifecycle/SavedStateHandleSupport$DEFAULT_ARGS_KEY$1;-><init>()V

    sput-object v0, Landroidx/lifecycle/SavedStateHandleSupport;->DEFAULT_ARGS_KEY:Landroidx/lifecycle/viewmodel/CreationExtras$Key;

    return-void
.end method

.method public static final enableSavedStateHandles(Landroidx/savedstate/SavedStateRegistryOwner;)V
    .locals 3
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "<T::",
            "Landroidx/savedstate/SavedStateRegistryOwner;",
            ":",
            "Landroidx/lifecycle/ViewModelStoreOwner;",
            ">(TT;)V"
        }
    .end annotation

    const-string v0, "<this>"

    invoke-static {p0, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 42
    invoke-interface {p0}, Landroidx/lifecycle/LifecycleOwner;->getLifecycle()Landroidx/lifecycle/Lifecycle;

    move-result-object v0

    invoke-virtual {v0}, Landroidx/lifecycle/Lifecycle;->getCurrentState()Landroidx/lifecycle/Lifecycle$State;

    move-result-object v0

    const-string v1, "lifecycle.currentState"

    invoke-static {v0, v1}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullExpressionValue(Ljava/lang/Object;Ljava/lang/String;)V

    .line 44
    sget-object v1, Landroidx/lifecycle/Lifecycle$State;->INITIALIZED:Landroidx/lifecycle/Lifecycle$State;

    if-eq v0, v1, :cond_1

    sget-object v1, Landroidx/lifecycle/Lifecycle$State;->CREATED:Landroidx/lifecycle/Lifecycle$State;

    if-ne v0, v1, :cond_0

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    goto :goto_1

    :cond_1
    :goto_0
    const/4 v0, 0x1

    :goto_1
    if-eqz v0, :cond_2

    .line 48
    new-instance v0, Landroidx/lifecycle/ViewModelProvider;

    move-object v1, p0

    check-cast v1, Landroidx/lifecycle/ViewModelStoreOwner;

    new-instance v2, Landroidx/lifecycle/SavedStateHandleSupport$enableSavedStateHandles$1;

    invoke-direct {v2}, Landroidx/lifecycle/SavedStateHandleSupport$enableSavedStateHandles$1;-><init>()V

    invoke-direct {v0, v1, v2}, Landroidx/lifecycle/ViewModelProvider;-><init>(Landroidx/lifecycle/ViewModelStoreOwner;Landroidx/lifecycle/ViewModelProvider$Factory;)V

    .line 53
    const-class v1, Landroidx/lifecycle/SavedStateHandlesVM;

    const-string v2, "androidx.lifecycle.internal.SavedStateHandlesVM"

    .line 48
    invoke-virtual {v0, v2, v1}, Landroidx/lifecycle/ViewModelProvider;->get(Ljava/lang/String;Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;

    .line 55
    invoke-interface {p0}, Landroidx/savedstate/SavedStateRegistryOwner;->getSavedStateRegistry()Landroidx/savedstate/SavedStateRegistry;

    move-result-object p0

    const-class v0, Landroidx/lifecycle/SavedStateHandleAttacher;

    invoke-virtual {p0, v0}, Landroidx/savedstate/SavedStateRegistry;->runOnNextRecreation(Ljava/lang/Class;)V

    return-void

    .line 44
    :cond_2
    new-instance p0, Ljava/lang/IllegalArgumentException;

    const-string v0, "Failed requirement."

    invoke-virtual {v0}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-direct {p0, v0}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw p0
.end method

.method public static final getSavedStateHandlesVM(Landroidx/lifecycle/ViewModelStoreOwner;)Landroidx/lifecycle/SavedStateHandlesVM;
    .locals 2

    const-string v0, "<this>"

    invoke-static {p0, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 120
    new-instance v0, Landroidx/lifecycle/ViewModelProvider;

    sget-object v1, Landroidx/lifecycle/ThrowingFactory;->INSTANCE:Landroidx/lifecycle/ThrowingFactory;

    invoke-direct {v0, p0, v1}, Landroidx/lifecycle/ViewModelProvider;-><init>(Landroidx/lifecycle/ViewModelStoreOwner;Landroidx/lifecycle/ViewModelProvider$Factory;)V

    const-class p0, Landroidx/lifecycle/SavedStateHandlesVM;

    const-string v1, "androidx.lifecycle.internal.SavedStateHandlesVM"

    invoke-virtual {v0, v1, p0}, Landroidx/lifecycle/ViewModelProvider;->get(Ljava/lang/String;Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;

    move-result-object p0

    check-cast p0, Landroidx/lifecycle/SavedStateHandlesVM;

    return-object p0
.end method
