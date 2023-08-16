.class Ldagger/hilt/android/internal/managers/ActivityRetainedComponentManager$1;
.super Ljava/lang/Object;
.source "ActivityRetainedComponentManager.java"

# interfaces
.implements Landroidx/lifecycle/ViewModelProvider$Factory;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Ldagger/hilt/android/internal/managers/ActivityRetainedComponentManager;->getViewModelProvider(Landroidx/lifecycle/ViewModelStoreOwner;Landroid/content/Context;)Landroidx/lifecycle/ViewModelProvider;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Ldagger/hilt/android/internal/managers/ActivityRetainedComponentManager;

.field final synthetic val$applicationContext:Landroid/content/Context;


# direct methods
.method constructor <init>(Ldagger/hilt/android/internal/managers/ActivityRetainedComponentManager;Landroid/content/Context;)V
    .locals 0

    .line 94
    iput-object p1, p0, Ldagger/hilt/android/internal/managers/ActivityRetainedComponentManager$1;->this$0:Ldagger/hilt/android/internal/managers/ActivityRetainedComponentManager;

    iput-object p2, p0, Ldagger/hilt/android/internal/managers/ActivityRetainedComponentManager$1;->val$applicationContext:Landroid/content/Context;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public create(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "<T:",
            "Landroidx/lifecycle/ViewModel;",
            ">(",
            "Ljava/lang/Class<",
            "TT;>;)TT;"
        }
    .end annotation

    .line 99
    iget-object p0, p0, Ldagger/hilt/android/internal/managers/ActivityRetainedComponentManager$1;->val$applicationContext:Landroid/content/Context;

    const-class p1, Ldagger/hilt/android/internal/managers/ActivityRetainedComponentManager$ActivityRetainedComponentBuilderEntryPoint;

    .line 100
    invoke-static {p0, p1}, Ldagger/hilt/EntryPoints;->get(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ldagger/hilt/android/internal/managers/ActivityRetainedComponentManager$ActivityRetainedComponentBuilderEntryPoint;

    .line 102
    invoke-interface {p0}, Ldagger/hilt/android/internal/managers/ActivityRetainedComponentManager$ActivityRetainedComponentBuilderEntryPoint;->retainedComponentBuilder()Ldagger/hilt/android/internal/builders/ActivityRetainedComponentBuilder;

    move-result-object p0

    .line 103
    invoke-interface {p0}, Ldagger/hilt/android/internal/builders/ActivityRetainedComponentBuilder;->build()Ldagger/hilt/android/components/ActivityRetainedComponent;

    move-result-object p0

    .line 104
    new-instance p1, Ldagger/hilt/android/internal/managers/ActivityRetainedComponentManager$ActivityRetainedComponentViewModel;

    invoke-direct {p1, p0}, Ldagger/hilt/android/internal/managers/ActivityRetainedComponentManager$ActivityRetainedComponentViewModel;-><init>(Ldagger/hilt/android/components/ActivityRetainedComponent;)V

    return-object p1
.end method
