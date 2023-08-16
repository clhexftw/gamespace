.class public final Lorg/nameless/gamespace/preferences/appselector/AppSelectorFragment;
.super Lorg/nameless/gamespace/preferences/appselector/Hilt_AppSelectorFragment;
.source "AppSelectorFragment.kt"

# interfaces
.implements Landroid/widget/SearchView$OnQueryTextListener;
.implements Landroid/view/MenuItem$OnActionExpandListener;


# annotations
.annotation system Ldalvik/annotation/SourceDebugExtension;
    value = "SMAP\nAppSelectorFragment.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AppSelectorFragment.kt\norg/nameless/gamespace/preferences/appselector/AppSelectorFragment\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,127:1\n766#2:128\n857#2:129\n1743#2,3:130\n858#2:133\n1045#2:134\n1#3:135\n*S KotlinDebug\n*F\n+ 1 AppSelectorFragment.kt\norg/nameless/gamespace/preferences/appselector/AppSelectorFragment\n*L\n90#1:128\n90#1:129\n93#1:130,3\n90#1:133\n95#1:134\n*E\n"
.end annotation


# instance fields
.field private appBarLayout:Lcom/google/android/material/appbar/AppBarLayout;

.field private appListView:Landroidx/recyclerview/widget/RecyclerView;

.field private appsAdapter:Lorg/nameless/gamespace/preferences/appselector/adapter/AppsAdapter;

.field private final menuProvider:Lorg/nameless/gamespace/preferences/appselector/AppSelectorFragment$menuProvider$1;

.field public settings:Lorg/nameless/gamespace/data/SystemSettings;


# direct methods
.method public constructor <init>()V
    .locals 1

    .line 45
    invoke-direct {p0}, Lorg/nameless/gamespace/preferences/appselector/Hilt_AppSelectorFragment;-><init>()V

    .line 54
    new-instance v0, Lorg/nameless/gamespace/preferences/appselector/AppSelectorFragment$menuProvider$1;

    invoke-direct {v0, p0}, Lorg/nameless/gamespace/preferences/appselector/AppSelectorFragment$menuProvider$1;-><init>(Lorg/nameless/gamespace/preferences/appselector/AppSelectorFragment;)V

    iput-object v0, p0, Lorg/nameless/gamespace/preferences/appselector/AppSelectorFragment;->menuProvider:Lorg/nameless/gamespace/preferences/appselector/AppSelectorFragment$menuProvider$1;

    return-void
.end method

.method private final setupAppListView(Landroidx/recyclerview/widget/RecyclerView;)V
    .locals 9

    .line 86
    iput-object p1, p0, Lorg/nameless/gamespace/preferences/appselector/AppSelectorFragment;->appListView:Landroidx/recyclerview/widget/RecyclerView;

    const-wide/16 v0, 0x80

    .line 87
    invoke-static {v0, v1}, Landroid/content/pm/PackageManager$ApplicationInfoFlags;->of(J)Landroid/content/pm/PackageManager$ApplicationInfoFlags;

    move-result-object v0

    .line 88
    invoke-virtual {p1}, Landroid/view/ViewGroup;->getContext()Landroid/content/Context;

    move-result-object v1

    invoke-virtual {v1}, Landroid/content/Context;->getPackageManager()Landroid/content/pm/PackageManager;

    move-result-object v1

    .line 89
    invoke-virtual {v1, v0}, Landroid/content/pm/PackageManager;->getInstalledApplications(Landroid/content/pm/PackageManager$ApplicationInfoFlags;)Ljava/util/List;

    move-result-object v0

    const-string v1, "view.context.packageMana\u2026talledApplications(flags)"

    invoke-static {v0, v1}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullExpressionValue(Ljava/lang/Object;Ljava/lang/String;)V

    check-cast v0, Ljava/lang/Iterable;

    .line 766
    new-instance v1, Ljava/util/ArrayList;

    invoke-direct {v1}, Ljava/util/ArrayList;-><init>()V

    .line 857
    invoke-interface {v0}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :cond_0
    :goto_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_6

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    move-object v3, v2

    check-cast v3, Landroid/content/pm/ApplicationInfo;

    .line 91
    iget-object v4, v3, Landroid/content/pm/ApplicationInfo;->packageName:Ljava/lang/String;

    invoke-virtual {p0}, Lorg/nameless/gamespace/preferences/appselector/Hilt_AppSelectorFragment;->getContext()Landroid/content/Context;

    move-result-object v5

    if-eqz v5, :cond_1

    invoke-virtual {v5}, Landroid/content/Context;->getPackageName()Ljava/lang/String;

    move-result-object v5

    goto :goto_1

    :cond_1
    const/4 v5, 0x0

    :goto_1
    invoke-static {v4, v5}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v4

    const/4 v5, 0x1

    const/4 v6, 0x0

    if-nez v4, :cond_5

    .line 92
    iget v4, v3, Landroid/content/pm/ApplicationInfo;->flags:I

    and-int/2addr v4, v5

    if-nez v4, :cond_5

    .line 93
    invoke-virtual {p0}, Lorg/nameless/gamespace/preferences/appselector/AppSelectorFragment;->getSettings()Lorg/nameless/gamespace/data/SystemSettings;

    move-result-object v4

    invoke-virtual {v4}, Lorg/nameless/gamespace/data/SystemSettings;->getUserGames()Ljava/util/List;

    move-result-object v4

    check-cast v4, Ljava/lang/Iterable;

    .line 1743
    instance-of v7, v4, Ljava/util/Collection;

    if-eqz v7, :cond_3

    move-object v7, v4

    check-cast v7, Ljava/util/Collection;

    invoke-interface {v7}, Ljava/util/Collection;->isEmpty()Z

    move-result v7

    if-eqz v7, :cond_3

    :cond_2
    move v3, v6

    goto :goto_2

    .line 1744
    :cond_3
    invoke-interface {v4}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object v4

    :cond_4
    invoke-interface {v4}, Ljava/util/Iterator;->hasNext()Z

    move-result v7

    if-eqz v7, :cond_2

    invoke-interface {v4}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Lorg/nameless/gamespace/data/UserGame;

    .line 93
    invoke-virtual {v7}, Lorg/nameless/gamespace/data/UserGame;->getPackageName()Ljava/lang/String;

    move-result-object v7

    iget-object v8, v3, Landroid/content/pm/ApplicationInfo;->packageName:Ljava/lang/String;

    invoke-static {v7, v8}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v7

    if-eqz v7, :cond_4

    move v3, v5

    :goto_2
    if-nez v3, :cond_5

    goto :goto_3

    :cond_5
    move v5, v6

    :goto_3
    if-eqz v5, :cond_0

    .line 857
    invoke-interface {v1, v2}, Ljava/util/Collection;->add(Ljava/lang/Object;)Z

    goto :goto_0

    .line 1045
    :cond_6
    new-instance v0, Lorg/nameless/gamespace/preferences/appselector/AppSelectorFragment$setupAppListView$$inlined$sortedBy$1;

    invoke-direct {v0, p1}, Lorg/nameless/gamespace/preferences/appselector/AppSelectorFragment$setupAppListView$$inlined$sortedBy$1;-><init>(Landroidx/recyclerview/widget/RecyclerView;)V

    invoke-static {v1, v0}, Lkotlin/collections/CollectionsKt;->sortedWith(Ljava/lang/Iterable;Ljava/util/Comparator;)Ljava/util/List;

    move-result-object v0

    .line 97
    new-instance v1, Lorg/nameless/gamespace/preferences/appselector/adapter/AppsAdapter;

    invoke-virtual {p1}, Landroid/view/ViewGroup;->getContext()Landroid/content/Context;

    move-result-object v2

    invoke-virtual {v2}, Landroid/content/Context;->getPackageManager()Landroid/content/pm/PackageManager;

    move-result-object v2

    const-string v3, "view.context.packageManager"

    invoke-static {v2, v3}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullExpressionValue(Ljava/lang/Object;Ljava/lang/String;)V

    invoke-direct {v1, v2, v0}, Lorg/nameless/gamespace/preferences/appselector/adapter/AppsAdapter;-><init>(Landroid/content/pm/PackageManager;Ljava/util/List;)V

    iput-object v1, p0, Lorg/nameless/gamespace/preferences/appselector/AppSelectorFragment;->appsAdapter:Lorg/nameless/gamespace/preferences/appselector/adapter/AppsAdapter;

    .line 98
    invoke-virtual {p1, v1}, Landroidx/recyclerview/widget/RecyclerView;->setAdapter(Landroidx/recyclerview/widget/RecyclerView$Adapter;)V

    .line 99
    new-instance v0, Landroidx/recyclerview/widget/LinearLayoutManager;

    invoke-virtual {p1}, Landroid/view/ViewGroup;->getContext()Landroid/content/Context;

    move-result-object v1

    invoke-direct {v0, v1}, Landroidx/recyclerview/widget/LinearLayoutManager;-><init>(Landroid/content/Context;)V

    invoke-virtual {p1, v0}, Landroidx/recyclerview/widget/RecyclerView;->setLayoutManager(Landroidx/recyclerview/widget/RecyclerView$LayoutManager;)V

    .line 100
    iget-object p1, p0, Lorg/nameless/gamespace/preferences/appselector/AppSelectorFragment;->appsAdapter:Lorg/nameless/gamespace/preferences/appselector/adapter/AppsAdapter;

    if-eqz p1, :cond_7

    new-instance v0, Lorg/nameless/gamespace/preferences/appselector/AppSelectorFragment$setupAppListView$1;

    invoke-direct {v0, p0}, Lorg/nameless/gamespace/preferences/appselector/AppSelectorFragment$setupAppListView$1;-><init>(Lorg/nameless/gamespace/preferences/appselector/AppSelectorFragment;)V

    invoke-virtual {p1, v0}, Lorg/nameless/gamespace/preferences/appselector/adapter/AppsAdapter;->onItemClick(Lkotlin/jvm/functions/Function1;)V

    :cond_7
    return-void
.end method


# virtual methods
.method public final getSettings()Lorg/nameless/gamespace/data/SystemSettings;
    .locals 0

    .line 48
    iget-object p0, p0, Lorg/nameless/gamespace/preferences/appselector/AppSelectorFragment;->settings:Lorg/nameless/gamespace/data/SystemSettings;

    if-eqz p0, :cond_0

    return-object p0

    :cond_0
    const-string p0, "settings"

    invoke-static {p0}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0
.end method

.method public onCreateView(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Landroid/os/Bundle;)Landroid/view/View;
    .locals 1

    const-string v0, "inflater"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 72
    invoke-super {p0, p1, p2, p3}, Landroidx/fragment/app/Fragment;->onCreateView(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Landroid/os/Bundle;)Landroid/view/View;

    .line 73
    invoke-virtual {p0}, Landroidx/fragment/app/Fragment;->getActivity()Landroidx/fragment/app/FragmentActivity;

    move-result-object p3

    if-eqz p3, :cond_0

    const v0, 0x7f0a005b

    invoke-virtual {p3, v0}, Landroid/app/Activity;->findViewById(I)Landroid/view/View;

    move-result-object p3

    check-cast p3, Lcom/google/android/material/appbar/AppBarLayout;

    goto :goto_0

    :cond_0
    const/4 p3, 0x0

    :goto_0
    iput-object p3, p0, Lorg/nameless/gamespace/preferences/appselector/AppSelectorFragment;->appBarLayout:Lcom/google/android/material/appbar/AppBarLayout;

    const p0, 0x7f0d001e

    const/4 p3, 0x0

    .line 74
    invoke-virtual {p1, p0, p2, p3}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;Z)Landroid/view/View;

    move-result-object p0

    return-object p0
.end method

.method public onMenuItemActionCollapse(Landroid/view/MenuItem;)Z
    .locals 1

    const-string v0, "item"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 122
    iget-object p1, p0, Lorg/nameless/gamespace/preferences/appselector/AppSelectorFragment;->appBarLayout:Lcom/google/android/material/appbar/AppBarLayout;

    if-eqz p1, :cond_0

    const/4 v0, 0x0

    invoke-virtual {p1, v0, v0}, Lcom/google/android/material/appbar/AppBarLayout;->setExpanded(ZZ)V

    .line 123
    :cond_0
    iget-object p0, p0, Lorg/nameless/gamespace/preferences/appselector/AppSelectorFragment;->appListView:Landroidx/recyclerview/widget/RecyclerView;

    const/4 p1, 0x1

    if-eqz p0, :cond_1

    invoke-static {p0, p1}, Landroidx/core/view/ViewCompat;->setNestedScrollingEnabled(Landroid/view/View;Z)V

    :cond_1
    return p1
.end method

.method public onMenuItemActionExpand(Landroid/view/MenuItem;)Z
    .locals 1

    const-string v0, "item"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 116
    iget-object p1, p0, Lorg/nameless/gamespace/preferences/appselector/AppSelectorFragment;->appBarLayout:Lcom/google/android/material/appbar/AppBarLayout;

    const/4 v0, 0x0

    if-eqz p1, :cond_0

    invoke-virtual {p1, v0, v0}, Lcom/google/android/material/appbar/AppBarLayout;->setExpanded(ZZ)V

    .line 117
    :cond_0
    iget-object p0, p0, Lorg/nameless/gamespace/preferences/appselector/AppSelectorFragment;->appListView:Landroidx/recyclerview/widget/RecyclerView;

    if-eqz p0, :cond_1

    invoke-static {p0, v0}, Landroidx/core/view/ViewCompat;->setNestedScrollingEnabled(Landroid/view/View;Z)V

    :cond_1
    const/4 p0, 0x1

    return p0
.end method

.method public onQueryTextChange(Ljava/lang/String;)Z
    .locals 0

    .line 111
    iget-object p0, p0, Lorg/nameless/gamespace/preferences/appselector/AppSelectorFragment;->appsAdapter:Lorg/nameless/gamespace/preferences/appselector/adapter/AppsAdapter;

    if-eqz p0, :cond_0

    invoke-virtual {p0, p1}, Lorg/nameless/gamespace/preferences/appselector/adapter/AppsAdapter;->filterWith(Ljava/lang/String;)V

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method public onQueryTextSubmit(Ljava/lang/String;)Z
    .locals 0

    const/4 p0, 0x0

    return p0
.end method

.method public onViewCreated(Landroid/view/View;Landroid/os/Bundle;)V
    .locals 3

    const-string v0, "view"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 78
    invoke-super {p0, p1, p2}, Landroidx/fragment/app/Fragment;->onViewCreated(Landroid/view/View;Landroid/os/Bundle;)V

    .line 79
    invoke-virtual {p0}, Landroidx/fragment/app/Fragment;->getActivity()Landroidx/fragment/app/FragmentActivity;

    move-result-object p2

    if-eqz p2, :cond_0

    iget-object v0, p0, Lorg/nameless/gamespace/preferences/appselector/AppSelectorFragment;->menuProvider:Lorg/nameless/gamespace/preferences/appselector/AppSelectorFragment$menuProvider$1;

    invoke-virtual {p0}, Landroidx/fragment/app/Fragment;->getViewLifecycleOwner()Landroidx/lifecycle/LifecycleOwner;

    move-result-object v1

    sget-object v2, Landroidx/lifecycle/Lifecycle$State;->RESUMED:Landroidx/lifecycle/Lifecycle$State;

    invoke-virtual {p2, v0, v1, v2}, Landroidx/activity/ComponentActivity;->addMenuProvider(Landroidx/core/view/MenuProvider;Landroidx/lifecycle/LifecycleOwner;Landroidx/lifecycle/Lifecycle$State;)V

    :cond_0
    const p2, 0x7f0a005f

    .line 80
    invoke-virtual {p1, p2}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object p1

    check-cast p1, Landroidx/recyclerview/widget/RecyclerView;

    if-eqz p1, :cond_1

    .line 81
    invoke-direct {p0, p1}, Lorg/nameless/gamespace/preferences/appselector/AppSelectorFragment;->setupAppListView(Landroidx/recyclerview/widget/RecyclerView;)V

    :cond_1
    return-void
.end method
