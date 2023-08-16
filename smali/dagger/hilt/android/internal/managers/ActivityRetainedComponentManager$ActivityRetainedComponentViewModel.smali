.class final Ldagger/hilt/android/internal/managers/ActivityRetainedComponentManager$ActivityRetainedComponentViewModel;
.super Landroidx/lifecycle/ViewModel;
.source "ActivityRetainedComponentManager.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Ldagger/hilt/android/internal/managers/ActivityRetainedComponentManager;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x18
    name = "ActivityRetainedComponentViewModel"
.end annotation


# instance fields
.field private final component:Ldagger/hilt/android/components/ActivityRetainedComponent;


# direct methods
.method constructor <init>(Ldagger/hilt/android/components/ActivityRetainedComponent;)V
    .locals 0

    .line 63
    invoke-direct {p0}, Landroidx/lifecycle/ViewModel;-><init>()V

    .line 64
    iput-object p1, p0, Ldagger/hilt/android/internal/managers/ActivityRetainedComponentManager$ActivityRetainedComponentViewModel;->component:Ldagger/hilt/android/components/ActivityRetainedComponent;

    return-void
.end method


# virtual methods
.method getComponent()Ldagger/hilt/android/components/ActivityRetainedComponent;
    .locals 0

    .line 68
    iget-object p0, p0, Ldagger/hilt/android/internal/managers/ActivityRetainedComponentManager$ActivityRetainedComponentViewModel;->component:Ldagger/hilt/android/components/ActivityRetainedComponent;

    return-object p0
.end method

.method protected onCleared()V
    .locals 1

    .line 73
    invoke-super {p0}, Landroidx/lifecycle/ViewModel;->onCleared()V

    .line 74
    iget-object p0, p0, Ldagger/hilt/android/internal/managers/ActivityRetainedComponentManager$ActivityRetainedComponentViewModel;->component:Ldagger/hilt/android/components/ActivityRetainedComponent;

    const-class v0, Ldagger/hilt/android/internal/managers/ActivityRetainedComponentManager$ActivityRetainedLifecycleEntryPoint;

    .line 75
    invoke-static {p0, v0}, Ldagger/hilt/EntryPoints;->get(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ldagger/hilt/android/internal/managers/ActivityRetainedComponentManager$ActivityRetainedLifecycleEntryPoint;

    .line 76
    invoke-interface {p0}, Ldagger/hilt/android/internal/managers/ActivityRetainedComponentManager$ActivityRetainedLifecycleEntryPoint;->getActivityRetainedLifecycle()Ldagger/hilt/android/ActivityRetainedLifecycle;

    move-result-object p0

    .line 77
    check-cast p0, Ldagger/hilt/android/internal/managers/ActivityRetainedComponentManager$Lifecycle;

    invoke-virtual {p0}, Ldagger/hilt/android/internal/managers/ActivityRetainedComponentManager$Lifecycle;->dispatchOnCleared()V

    return-void
.end method
