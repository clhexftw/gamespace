.class final Lorg/nameless/gamespace/preferences/appselector/AppSelectorFragment$setupAppListView$1;
.super Lkotlin/jvm/internal/Lambda;
.source "AppSelectorFragment.kt"

# interfaces
.implements Lkotlin/jvm/functions/Function1;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lorg/nameless/gamespace/preferences/appselector/AppSelectorFragment;->setupAppListView(Landroidx/recyclerview/widget/RecyclerView;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x18
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lkotlin/jvm/internal/Lambda;",
        "Lkotlin/jvm/functions/Function1<",
        "Landroid/content/pm/ApplicationInfo;",
        "Lkotlin/Unit;",
        ">;"
    }
.end annotation


# instance fields
.field final synthetic this$0:Lorg/nameless/gamespace/preferences/appselector/AppSelectorFragment;


# direct methods
.method constructor <init>(Lorg/nameless/gamespace/preferences/appselector/AppSelectorFragment;)V
    .locals 0

    iput-object p1, p0, Lorg/nameless/gamespace/preferences/appselector/AppSelectorFragment$setupAppListView$1;->this$0:Lorg/nameless/gamespace/preferences/appselector/AppSelectorFragment;

    const/4 p1, 0x1

    invoke-direct {p0, p1}, Lkotlin/jvm/internal/Lambda;-><init>(I)V

    return-void
.end method


# virtual methods
.method public bridge synthetic invoke(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    .line 100
    check-cast p1, Landroid/content/pm/ApplicationInfo;

    invoke-virtual {p0, p1}, Lorg/nameless/gamespace/preferences/appselector/AppSelectorFragment$setupAppListView$1;->invoke(Landroid/content/pm/ApplicationInfo;)V

    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0
.end method

.method public final invoke(Landroid/content/pm/ApplicationInfo;)V
    .locals 4

    const-string v0, "it"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 101
    iget-object v0, p0, Lorg/nameless/gamespace/preferences/appselector/AppSelectorFragment$setupAppListView$1;->this$0:Lorg/nameless/gamespace/preferences/appselector/AppSelectorFragment;

    invoke-virtual {v0}, Landroidx/fragment/app/Fragment;->getActivity()Landroidx/fragment/app/FragmentActivity;

    move-result-object v0

    if-eqz v0, :cond_0

    const/4 v1, -0x1

    new-instance v2, Landroid/content/Intent;

    invoke-direct {v2}, Landroid/content/Intent;-><init>()V

    .line 102
    iget-object p1, p1, Landroid/content/pm/ApplicationInfo;->packageName:Ljava/lang/String;

    const-string v3, "selected_app"

    invoke-virtual {v2, v3, p1}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;

    .line 103
    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    .line 101
    invoke-virtual {v0, v1, v2}, Landroid/app/Activity;->setResult(ILandroid/content/Intent;)V

    .line 104
    :cond_0
    iget-object p0, p0, Lorg/nameless/gamespace/preferences/appselector/AppSelectorFragment$setupAppListView$1;->this$0:Lorg/nameless/gamespace/preferences/appselector/AppSelectorFragment;

    invoke-virtual {p0}, Landroidx/fragment/app/Fragment;->getActivity()Landroidx/fragment/app/FragmentActivity;

    move-result-object p0

    if-eqz p0, :cond_1

    invoke-virtual {p0}, Landroid/app/Activity;->finish()V

    :cond_1
    return-void
.end method
