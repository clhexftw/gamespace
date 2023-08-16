.class public final Lorg/nameless/gamespace/preferences/appselector/adapter/AppsItemViewHolder;
.super Landroidx/recyclerview/widget/RecyclerView$ViewHolder;
.source "AppsItemViewHolder.kt"


# instance fields
.field private final pm$delegate:Lkotlin/Lazy;

.field private final v:Landroid/view/View;


# direct methods
.method public constructor <init>(Landroid/view/View;)V
    .locals 1

    const-string v0, "v"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 26
    invoke-direct {p0, p1}, Landroidx/recyclerview/widget/RecyclerView$ViewHolder;-><init>(Landroid/view/View;)V

    iput-object p1, p0, Lorg/nameless/gamespace/preferences/appselector/adapter/AppsItemViewHolder;->v:Landroid/view/View;

    .line 27
    new-instance p1, Lorg/nameless/gamespace/preferences/appselector/adapter/AppsItemViewHolder$pm$2;

    invoke-direct {p1, p0}, Lorg/nameless/gamespace/preferences/appselector/adapter/AppsItemViewHolder$pm$2;-><init>(Lorg/nameless/gamespace/preferences/appselector/adapter/AppsItemViewHolder;)V

    invoke-static {p1}, Lkotlin/LazyKt;->lazy(Lkotlin/jvm/functions/Function0;)Lkotlin/Lazy;

    move-result-object p1

    iput-object p1, p0, Lorg/nameless/gamespace/preferences/appselector/adapter/AppsItemViewHolder;->pm$delegate:Lkotlin/Lazy;

    return-void
.end method

.method public static final synthetic access$getV$p(Lorg/nameless/gamespace/preferences/appselector/adapter/AppsItemViewHolder;)Landroid/view/View;
    .locals 0

    .line 26
    iget-object p0, p0, Lorg/nameless/gamespace/preferences/appselector/adapter/AppsItemViewHolder;->v:Landroid/view/View;

    return-object p0
.end method

.method private final getPm()Landroid/content/pm/PackageManager;
    .locals 0

    .line 27
    iget-object p0, p0, Lorg/nameless/gamespace/preferences/appselector/adapter/AppsItemViewHolder;->pm$delegate:Lkotlin/Lazy;

    invoke-interface {p0}, Lkotlin/Lazy;->getValue()Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Landroid/content/pm/PackageManager;

    return-object p0
.end method


# virtual methods
.method public final bind(Landroid/content/pm/ApplicationInfo;Lkotlin/jvm/functions/Function1;)V
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Landroid/content/pm/ApplicationInfo;",
            "Lkotlin/jvm/functions/Function1<",
            "-",
            "Landroid/content/pm/ApplicationInfo;",
            "Lkotlin/Unit;",
            ">;)V"
        }
    .end annotation

    const-string v0, "app"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    const-string v0, "onClick"

    invoke-static {p2, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 30
    iget-object v0, p0, Lorg/nameless/gamespace/preferences/appselector/adapter/AppsItemViewHolder;->v:Landroid/view/View;

    const v1, 0x7f0a0060

    invoke-virtual {v0, v1}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/TextView;

    if-nez v0, :cond_0

    goto :goto_0

    :cond_0
    invoke-direct {p0}, Lorg/nameless/gamespace/preferences/appselector/adapter/AppsItemViewHolder;->getPm()Landroid/content/pm/PackageManager;

    move-result-object v1

    invoke-virtual {p1, v1}, Landroid/content/pm/ApplicationInfo;->loadLabel(Landroid/content/pm/PackageManager;)Ljava/lang/CharSequence;

    move-result-object v1

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 31
    :goto_0
    iget-object v0, p0, Lorg/nameless/gamespace/preferences/appselector/adapter/AppsItemViewHolder;->v:Landroid/view/View;

    const v1, 0x7f0a0062

    invoke-virtual {v0, v1}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/TextView;

    if-nez v0, :cond_1

    goto :goto_1

    :cond_1
    iget-object v1, p1, Landroid/content/pm/ApplicationInfo;->packageName:Ljava/lang/String;

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 32
    :goto_1
    iget-object v0, p0, Lorg/nameless/gamespace/preferences/appselector/adapter/AppsItemViewHolder;->v:Landroid/view/View;

    const v1, 0x7f0a005d

    invoke-virtual {v0, v1}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/ImageView;

    if-eqz v0, :cond_2

    invoke-direct {p0}, Lorg/nameless/gamespace/preferences/appselector/adapter/AppsItemViewHolder;->getPm()Landroid/content/pm/PackageManager;

    move-result-object v1

    invoke-virtual {p1, v1}, Landroid/content/pm/ApplicationInfo;->loadIcon(Landroid/content/pm/PackageManager;)Landroid/graphics/drawable/Drawable;

    move-result-object v1

    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setImageDrawable(Landroid/graphics/drawable/Drawable;)V

    .line 33
    :cond_2
    iget-object p0, p0, Lorg/nameless/gamespace/preferences/appselector/adapter/AppsItemViewHolder;->v:Landroid/view/View;

    const v0, 0x7f0a005e

    invoke-virtual {p0, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object p0

    check-cast p0, Landroid/view/ViewGroup;

    if-eqz p0, :cond_3

    new-instance v0, Lorg/nameless/gamespace/preferences/appselector/adapter/AppsItemViewHolder$bind$1;

    invoke-direct {v0, p2, p1}, Lorg/nameless/gamespace/preferences/appselector/adapter/AppsItemViewHolder$bind$1;-><init>(Lkotlin/jvm/functions/Function1;Landroid/content/pm/ApplicationInfo;)V

    invoke-virtual {p0, v0}, Landroid/view/ViewGroup;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    :cond_3
    return-void
.end method
