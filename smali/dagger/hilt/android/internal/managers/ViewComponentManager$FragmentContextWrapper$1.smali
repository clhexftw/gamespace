.class Ldagger/hilt/android/internal/managers/ViewComponentManager$FragmentContextWrapper$1;
.super Ljava/lang/Object;
.source "ViewComponentManager.java"

# interfaces
.implements Landroidx/lifecycle/LifecycleEventObserver;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Ldagger/hilt/android/internal/managers/ViewComponentManager$FragmentContextWrapper;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Ldagger/hilt/android/internal/managers/ViewComponentManager$FragmentContextWrapper;


# direct methods
.method constructor <init>(Ldagger/hilt/android/internal/managers/ViewComponentManager$FragmentContextWrapper;)V
    .locals 0

    .line 176
    iput-object p1, p0, Ldagger/hilt/android/internal/managers/ViewComponentManager$FragmentContextWrapper$1;->this$0:Ldagger/hilt/android/internal/managers/ViewComponentManager$FragmentContextWrapper;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onStateChanged(Landroidx/lifecycle/LifecycleOwner;Landroidx/lifecycle/Lifecycle$Event;)V
    .locals 0

    .line 179
    sget-object p1, Landroidx/lifecycle/Lifecycle$Event;->ON_DESTROY:Landroidx/lifecycle/Lifecycle$Event;

    if-ne p2, p1, :cond_0

    .line 182
    iget-object p1, p0, Ldagger/hilt/android/internal/managers/ViewComponentManager$FragmentContextWrapper$1;->this$0:Ldagger/hilt/android/internal/managers/ViewComponentManager$FragmentContextWrapper;

    const/4 p2, 0x0

    invoke-static {p1, p2}, Ldagger/hilt/android/internal/managers/ViewComponentManager$FragmentContextWrapper;->-$$Nest$fputfragment(Ldagger/hilt/android/internal/managers/ViewComponentManager$FragmentContextWrapper;Landroidx/fragment/app/Fragment;)V

    .line 183
    iget-object p1, p0, Ldagger/hilt/android/internal/managers/ViewComponentManager$FragmentContextWrapper$1;->this$0:Ldagger/hilt/android/internal/managers/ViewComponentManager$FragmentContextWrapper;

    invoke-static {p1, p2}, Ldagger/hilt/android/internal/managers/ViewComponentManager$FragmentContextWrapper;->-$$Nest$fputbaseInflater(Ldagger/hilt/android/internal/managers/ViewComponentManager$FragmentContextWrapper;Landroid/view/LayoutInflater;)V

    .line 184
    iget-object p0, p0, Ldagger/hilt/android/internal/managers/ViewComponentManager$FragmentContextWrapper$1;->this$0:Ldagger/hilt/android/internal/managers/ViewComponentManager$FragmentContextWrapper;

    invoke-static {p0, p2}, Ldagger/hilt/android/internal/managers/ViewComponentManager$FragmentContextWrapper;->-$$Nest$fputinflater(Ldagger/hilt/android/internal/managers/ViewComponentManager$FragmentContextWrapper;Landroid/view/LayoutInflater;)V

    :cond_0
    return-void
.end method
