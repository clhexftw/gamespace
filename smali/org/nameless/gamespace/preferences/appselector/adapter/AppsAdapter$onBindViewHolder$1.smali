.class final Lorg/nameless/gamespace/preferences/appselector/adapter/AppsAdapter$onBindViewHolder$1;
.super Lkotlin/jvm/internal/Lambda;
.source "AppsAdapter.kt"

# interfaces
.implements Lkotlin/jvm/functions/Function1;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lorg/nameless/gamespace/preferences/appselector/adapter/AppsAdapter;->onBindViewHolder(Lorg/nameless/gamespace/preferences/appselector/adapter/AppsItemViewHolder;I)V
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
.field final synthetic this$0:Lorg/nameless/gamespace/preferences/appselector/adapter/AppsAdapter;


# direct methods
.method constructor <init>(Lorg/nameless/gamespace/preferences/appselector/adapter/AppsAdapter;)V
    .locals 0

    iput-object p1, p0, Lorg/nameless/gamespace/preferences/appselector/adapter/AppsAdapter$onBindViewHolder$1;->this$0:Lorg/nameless/gamespace/preferences/appselector/adapter/AppsAdapter;

    const/4 p1, 0x1

    invoke-direct {p0, p1}, Lkotlin/jvm/internal/Lambda;-><init>(I)V

    return-void
.end method


# virtual methods
.method public bridge synthetic invoke(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    .line 47
    check-cast p1, Landroid/content/pm/ApplicationInfo;

    invoke-virtual {p0, p1}, Lorg/nameless/gamespace/preferences/appselector/adapter/AppsAdapter$onBindViewHolder$1;->invoke(Landroid/content/pm/ApplicationInfo;)V

    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0
.end method

.method public final invoke(Landroid/content/pm/ApplicationInfo;)V
    .locals 1

    const-string v0, "it"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 48
    iget-object v0, p0, Lorg/nameless/gamespace/preferences/appselector/adapter/AppsAdapter$onBindViewHolder$1;->this$0:Lorg/nameless/gamespace/preferences/appselector/adapter/AppsAdapter;

    invoke-static {v0}, Lorg/nameless/gamespace/preferences/appselector/adapter/AppsAdapter;->access$getOnClick$p(Lorg/nameless/gamespace/preferences/appselector/adapter/AppsAdapter;)Lkotlin/jvm/functions/Function1;

    move-result-object v0

    if-eqz v0, :cond_1

    .line 49
    iget-object p0, p0, Lorg/nameless/gamespace/preferences/appselector/adapter/AppsAdapter$onBindViewHolder$1;->this$0:Lorg/nameless/gamespace/preferences/appselector/adapter/AppsAdapter;

    invoke-static {p0}, Lorg/nameless/gamespace/preferences/appselector/adapter/AppsAdapter;->access$getOnClick$p(Lorg/nameless/gamespace/preferences/appselector/adapter/AppsAdapter;)Lkotlin/jvm/functions/Function1;

    move-result-object p0

    if-nez p0, :cond_0

    const-string p0, "onClick"

    invoke-static {p0}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    const/4 p0, 0x0

    :cond_0
    invoke-interface {p0, p1}, Lkotlin/jvm/functions/Function1;->invoke(Ljava/lang/Object;)Ljava/lang/Object;

    :cond_1
    return-void
.end method
