.class final Lorg/nameless/gamespace/preferences/appselector/adapter/AppsItemViewHolder$pm$2;
.super Lkotlin/jvm/internal/Lambda;
.source "AppsItemViewHolder.kt"

# interfaces
.implements Lkotlin/jvm/functions/Function0;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lorg/nameless/gamespace/preferences/appselector/adapter/AppsItemViewHolder;-><init>(Landroid/view/View;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x18
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lkotlin/jvm/internal/Lambda;",
        "Lkotlin/jvm/functions/Function0<",
        "Landroid/content/pm/PackageManager;",
        ">;"
    }
.end annotation


# instance fields
.field final synthetic this$0:Lorg/nameless/gamespace/preferences/appselector/adapter/AppsItemViewHolder;


# direct methods
.method constructor <init>(Lorg/nameless/gamespace/preferences/appselector/adapter/AppsItemViewHolder;)V
    .locals 0

    iput-object p1, p0, Lorg/nameless/gamespace/preferences/appselector/adapter/AppsItemViewHolder$pm$2;->this$0:Lorg/nameless/gamespace/preferences/appselector/adapter/AppsItemViewHolder;

    const/4 p1, 0x0

    invoke-direct {p0, p1}, Lkotlin/jvm/internal/Lambda;-><init>(I)V

    return-void
.end method


# virtual methods
.method public final invoke()Landroid/content/pm/PackageManager;
    .locals 0

    .line 27
    iget-object p0, p0, Lorg/nameless/gamespace/preferences/appselector/adapter/AppsItemViewHolder$pm$2;->this$0:Lorg/nameless/gamespace/preferences/appselector/adapter/AppsItemViewHolder;

    invoke-static {p0}, Lorg/nameless/gamespace/preferences/appselector/adapter/AppsItemViewHolder;->access$getV$p(Lorg/nameless/gamespace/preferences/appselector/adapter/AppsItemViewHolder;)Landroid/view/View;

    move-result-object p0

    invoke-virtual {p0}, Landroid/view/View;->getContext()Landroid/content/Context;

    move-result-object p0

    invoke-virtual {p0}, Landroid/content/Context;->getPackageManager()Landroid/content/pm/PackageManager;

    move-result-object p0

    return-object p0
.end method

.method public bridge synthetic invoke()Ljava/lang/Object;
    .locals 0

    .line 27
    invoke-virtual {p0}, Lorg/nameless/gamespace/preferences/appselector/adapter/AppsItemViewHolder$pm$2;->invoke()Landroid/content/pm/PackageManager;

    move-result-object p0

    return-object p0
.end method
