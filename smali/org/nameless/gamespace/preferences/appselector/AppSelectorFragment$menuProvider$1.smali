.class public final Lorg/nameless/gamespace/preferences/appselector/AppSelectorFragment$menuProvider$1;
.super Ljava/lang/Object;
.source "AppSelectorFragment.kt"

# interfaces
.implements Landroidx/core/view/MenuProvider;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lorg/nameless/gamespace/preferences/appselector/AppSelectorFragment;-><init>()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lorg/nameless/gamespace/preferences/appselector/AppSelectorFragment;


# direct methods
.method constructor <init>(Lorg/nameless/gamespace/preferences/appselector/AppSelectorFragment;)V
    .locals 0

    iput-object p1, p0, Lorg/nameless/gamespace/preferences/appselector/AppSelectorFragment$menuProvider$1;->this$0:Lorg/nameless/gamespace/preferences/appselector/AppSelectorFragment;

    .line 54
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onCreateMenu(Landroid/view/Menu;Landroid/view/MenuInflater;)V
    .locals 2

    const-string v0, "menu"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    const-string v0, "menuInflater"

    invoke-static {p2, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    const/high16 v0, 0x7f0f0000

    .line 56
    invoke-virtual {p2, v0, p1}, Landroid/view/MenuInflater;->inflate(ILandroid/view/Menu;)V

    const p2, 0x7f0a0061

    .line 57
    invoke-interface {p1, p2}, Landroid/view/Menu;->findItem(I)Landroid/view/MenuItem;

    move-result-object p1

    .line 58
    invoke-interface {p1}, Landroid/view/MenuItem;->getActionView()Landroid/view/View;

    move-result-object p2

    instance-of v0, p2, Landroid/widget/SearchView;

    if-eqz v0, :cond_0

    check-cast p2, Landroid/widget/SearchView;

    goto :goto_0

    :cond_0
    const/4 p2, 0x0

    :goto_0
    if-eqz p2, :cond_1

    .line 59
    iget-object v0, p0, Lorg/nameless/gamespace/preferences/appselector/AppSelectorFragment$menuProvider$1;->this$0:Lorg/nameless/gamespace/preferences/appselector/AppSelectorFragment;

    invoke-virtual {p2, v0}, Landroid/widget/SearchView;->setOnQueryTextListener(Landroid/widget/SearchView$OnQueryTextListener;)V

    :cond_1
    if-nez p2, :cond_2

    goto :goto_1

    .line 60
    :cond_2
    iget-object v0, p0, Lorg/nameless/gamespace/preferences/appselector/AppSelectorFragment$menuProvider$1;->this$0:Lorg/nameless/gamespace/preferences/appselector/AppSelectorFragment;

    const v1, 0x7f12006a

    invoke-virtual {v0, v1}, Landroidx/fragment/app/Fragment;->getString(I)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p2, v0}, Landroid/widget/SearchView;->setQueryHint(Ljava/lang/CharSequence;)V

    .line 61
    :goto_1
    iget-object p0, p0, Lorg/nameless/gamespace/preferences/appselector/AppSelectorFragment$menuProvider$1;->this$0:Lorg/nameless/gamespace/preferences/appselector/AppSelectorFragment;

    invoke-interface {p1, p0}, Landroid/view/MenuItem;->setOnActionExpandListener(Landroid/view/MenuItem$OnActionExpandListener;)Landroid/view/MenuItem;

    return-void
.end method

.method public onMenuItemSelected(Landroid/view/MenuItem;)Z
    .locals 0

    const-string p0, "menuItem"

    invoke-static {p1, p0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    const/4 p0, 0x0

    return p0
.end method
