.class public final Lorg/nameless/gamespace/preferences/appselector/adapter/AppsAdapter;
.super Landroidx/recyclerview/widget/ListAdapter;
.source "AppsAdapter.kt"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lorg/nameless/gamespace/preferences/appselector/adapter/AppsAdapter$DiffCallback;
    }
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Landroidx/recyclerview/widget/ListAdapter<",
        "Landroid/content/pm/ApplicationInfo;",
        "Lorg/nameless/gamespace/preferences/appselector/adapter/AppsItemViewHolder;",
        ">;"
    }
.end annotation

.annotation system Ldalvik/annotation/SourceDebugExtension;
    value = "SMAP\nAppsAdapter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AppsAdapter.kt\norg/nameless/gamespace/preferences/appselector/adapter/AppsAdapter\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,74:1\n766#2:75\n857#2,2:76\n1#3:78\n*S KotlinDebug\n*F\n+ 1 AppsAdapter.kt\norg/nameless/gamespace/preferences/appselector/adapter/AppsAdapter\n*L\n60#1:75\n60#1:76,2\n*E\n"
.end annotation


# instance fields
.field private final apps:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "Landroid/content/pm/ApplicationInfo;",
            ">;"
        }
    .end annotation
.end field

.field private onClick:Lkotlin/jvm/functions/Function1;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lkotlin/jvm/functions/Function1<",
            "-",
            "Landroid/content/pm/ApplicationInfo;",
            "Lkotlin/Unit;",
            ">;"
        }
    .end annotation
.end field

.field private final pm:Landroid/content/pm/PackageManager;


# direct methods
.method public constructor <init>(Landroid/content/pm/PackageManager;Ljava/util/List;)V
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Landroid/content/pm/PackageManager;",
            "Ljava/util/List<",
            "+",
            "Landroid/content/pm/ApplicationInfo;",
            ">;)V"
        }
    .end annotation

    const-string v0, "pm"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    const-string v0, "apps"

    invoke-static {p2, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 27
    new-instance v0, Lorg/nameless/gamespace/preferences/appselector/adapter/AppsAdapter$DiffCallback;

    invoke-direct {v0, p1}, Lorg/nameless/gamespace/preferences/appselector/adapter/AppsAdapter$DiffCallback;-><init>(Landroid/content/pm/PackageManager;)V

    invoke-direct {p0, v0}, Landroidx/recyclerview/widget/ListAdapter;-><init>(Landroidx/recyclerview/widget/DiffUtil$ItemCallback;)V

    .line 26
    iput-object p1, p0, Lorg/nameless/gamespace/preferences/appselector/adapter/AppsAdapter;->pm:Landroid/content/pm/PackageManager;

    iput-object p2, p0, Lorg/nameless/gamespace/preferences/appselector/adapter/AppsAdapter;->apps:Ljava/util/List;

    .line 32
    invoke-virtual {p0, p2}, Landroidx/recyclerview/widget/ListAdapter;->submitList(Ljava/util/List;)V

    return-void
.end method

.method public static final synthetic access$getOnClick$p(Lorg/nameless/gamespace/preferences/appselector/adapter/AppsAdapter;)Lkotlin/jvm/functions/Function1;
    .locals 0

    .line 26
    iget-object p0, p0, Lorg/nameless/gamespace/preferences/appselector/adapter/AppsAdapter;->onClick:Lkotlin/jvm/functions/Function1;

    return-object p0
.end method


# virtual methods
.method public final filterWith(Ljava/lang/String;)V
    .locals 5

    new-instance v0, Lkotlin/text/Regex;

    .line 59
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, ".*"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    sget-object v1, Lkotlin/text/RegexOption;->IGNORE_CASE:Lkotlin/text/RegexOption;

    invoke-direct {v0, p1, v1}, Lkotlin/text/Regex;-><init>(Ljava/lang/String;Lkotlin/text/RegexOption;)V

    .line 60
    iget-object p1, p0, Lorg/nameless/gamespace/preferences/appselector/adapter/AppsAdapter;->apps:Ljava/util/List;

    check-cast p1, Ljava/lang/Iterable;

    .line 766
    new-instance v1, Ljava/util/ArrayList;

    invoke-direct {v1}, Ljava/util/ArrayList;-><init>()V

    .line 857
    invoke-interface {p1}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object p1

    :cond_0
    :goto_0
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_1

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    move-object v3, v2

    check-cast v3, Landroid/content/pm/ApplicationInfo;

    .line 60
    iget-object v4, p0, Lorg/nameless/gamespace/preferences/appselector/adapter/AppsAdapter;->pm:Landroid/content/pm/PackageManager;

    invoke-virtual {v3, v4}, Landroid/content/pm/ApplicationInfo;->loadLabel(Landroid/content/pm/PackageManager;)Ljava/lang/CharSequence;

    move-result-object v3

    const-string v4, "it.loadLabel(pm)"

    invoke-static {v3, v4}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullExpressionValue(Ljava/lang/Object;Ljava/lang/String;)V

    invoke-virtual {v0, v3}, Lkotlin/text/Regex;->containsMatchIn(Ljava/lang/CharSequence;)Z

    move-result v3

    if-eqz v3, :cond_0

    .line 857
    invoke-interface {v1, v2}, Ljava/util/Collection;->add(Ljava/lang/Object;)Z

    goto :goto_0

    .line 61
    :cond_1
    invoke-interface {v1}, Ljava/util/Collection;->isEmpty()Z

    move-result p1

    xor-int/lit8 p1, p1, 0x1

    const/4 v0, 0x0

    if-eqz p1, :cond_2

    goto :goto_1

    :cond_2
    move-object v1, v0

    :goto_1
    if-eqz v1, :cond_3

    .line 62
    invoke-virtual {p0, v1}, Landroidx/recyclerview/widget/ListAdapter;->submitList(Ljava/util/List;)V

    sget-object v0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    :cond_3
    if-nez v0, :cond_4

    iget-object p1, p0, Lorg/nameless/gamespace/preferences/appselector/adapter/AppsAdapter;->apps:Ljava/util/List;

    invoke-virtual {p0, p1}, Landroidx/recyclerview/widget/ListAdapter;->submitList(Ljava/util/List;)V

    :cond_4
    return-void
.end method

.method public getItemCount()I
    .locals 0

    .line 36
    invoke-virtual {p0}, Landroidx/recyclerview/widget/ListAdapter;->getCurrentList()Ljava/util/List;

    move-result-object p0

    invoke-interface {p0}, Ljava/util/List;->size()I

    move-result p0

    return p0
.end method

.method public bridge synthetic onBindViewHolder(Landroidx/recyclerview/widget/RecyclerView$ViewHolder;I)V
    .locals 0

    .line 26
    check-cast p1, Lorg/nameless/gamespace/preferences/appselector/adapter/AppsItemViewHolder;

    invoke-virtual {p0, p1, p2}, Lorg/nameless/gamespace/preferences/appselector/adapter/AppsAdapter;->onBindViewHolder(Lorg/nameless/gamespace/preferences/appselector/adapter/AppsItemViewHolder;I)V

    return-void
.end method

.method public onBindViewHolder(Lorg/nameless/gamespace/preferences/appselector/adapter/AppsItemViewHolder;I)V
    .locals 1

    const-string v0, "holder"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 47
    invoke-virtual {p0}, Landroidx/recyclerview/widget/ListAdapter;->getCurrentList()Ljava/util/List;

    move-result-object v0

    invoke-interface {v0, p2}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p2

    const-string v0, "currentList[position]"

    invoke-static {p2, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullExpressionValue(Ljava/lang/Object;Ljava/lang/String;)V

    check-cast p2, Landroid/content/pm/ApplicationInfo;

    new-instance v0, Lorg/nameless/gamespace/preferences/appselector/adapter/AppsAdapter$onBindViewHolder$1;

    invoke-direct {v0, p0}, Lorg/nameless/gamespace/preferences/appselector/adapter/AppsAdapter$onBindViewHolder$1;-><init>(Lorg/nameless/gamespace/preferences/appselector/adapter/AppsAdapter;)V

    invoke-virtual {p1, p2, v0}, Lorg/nameless/gamespace/preferences/appselector/adapter/AppsItemViewHolder;->bind(Landroid/content/pm/ApplicationInfo;Lkotlin/jvm/functions/Function1;)V

    return-void
.end method

.method public bridge synthetic onCreateViewHolder(Landroid/view/ViewGroup;I)Landroidx/recyclerview/widget/RecyclerView$ViewHolder;
    .locals 0

    .line 26
    invoke-virtual {p0, p1, p2}, Lorg/nameless/gamespace/preferences/appselector/adapter/AppsAdapter;->onCreateViewHolder(Landroid/view/ViewGroup;I)Lorg/nameless/gamespace/preferences/appselector/adapter/AppsItemViewHolder;

    move-result-object p0

    return-object p0
.end method

.method public onCreateViewHolder(Landroid/view/ViewGroup;I)Lorg/nameless/gamespace/preferences/appselector/adapter/AppsItemViewHolder;
    .locals 2

    const-string p0, "parent"

    invoke-static {p1, p0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 40
    new-instance p0, Lorg/nameless/gamespace/preferences/appselector/adapter/AppsItemViewHolder;

    .line 41
    invoke-virtual {p1}, Landroid/view/ViewGroup;->getContext()Landroid/content/Context;

    move-result-object p2

    invoke-static {p2}, Landroid/view/LayoutInflater;->from(Landroid/content/Context;)Landroid/view/LayoutInflater;

    move-result-object p2

    const v0, 0x7f0d001f

    const/4 v1, 0x0

    .line 42
    invoke-virtual {p2, v0, p1, v1}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;Z)Landroid/view/View;

    move-result-object p1

    const-string p2, "from(parent.context)\n   \u2026ctor_item, parent, false)"

    invoke-static {p1, p2}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullExpressionValue(Ljava/lang/Object;Ljava/lang/String;)V

    .line 40
    invoke-direct {p0, p1}, Lorg/nameless/gamespace/preferences/appselector/adapter/AppsItemViewHolder;-><init>(Landroid/view/View;)V

    return-object p0
.end method

.method public final onItemClick(Lkotlin/jvm/functions/Function1;)V
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lkotlin/jvm/functions/Function1<",
            "-",
            "Landroid/content/pm/ApplicationInfo;",
            "Lkotlin/Unit;",
            ">;)V"
        }
    .end annotation

    const-string v0, "action"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 55
    iput-object p1, p0, Lorg/nameless/gamespace/preferences/appselector/adapter/AppsAdapter;->onClick:Lkotlin/jvm/functions/Function1;

    return-void
.end method
