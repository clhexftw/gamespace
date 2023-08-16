.class final Lorg/nameless/gamespace/preferences/appselector/adapter/AppsItemViewHolder$bind$1;
.super Ljava/lang/Object;
.source "AppsItemViewHolder.kt"

# interfaces
.implements Landroid/view/View$OnClickListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lorg/nameless/gamespace/preferences/appselector/adapter/AppsItemViewHolder;->bind(Landroid/content/pm/ApplicationInfo;Lkotlin/jvm/functions/Function1;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x18
    name = null
.end annotation


# instance fields
.field final synthetic $app:Landroid/content/pm/ApplicationInfo;

.field final synthetic $onClick:Lkotlin/jvm/functions/Function1;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lkotlin/jvm/functions/Function1<",
            "Landroid/content/pm/ApplicationInfo;",
            "Lkotlin/Unit;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method constructor <init>(Lkotlin/jvm/functions/Function1;Landroid/content/pm/ApplicationInfo;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lkotlin/jvm/functions/Function1<",
            "-",
            "Landroid/content/pm/ApplicationInfo;",
            "Lkotlin/Unit;",
            ">;",
            "Landroid/content/pm/ApplicationInfo;",
            ")V"
        }
    .end annotation

    iput-object p1, p0, Lorg/nameless/gamespace/preferences/appselector/adapter/AppsItemViewHolder$bind$1;->$onClick:Lkotlin/jvm/functions/Function1;

    iput-object p2, p0, Lorg/nameless/gamespace/preferences/appselector/adapter/AppsItemViewHolder$bind$1;->$app:Landroid/content/pm/ApplicationInfo;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public final onClick(Landroid/view/View;)V
    .locals 0

    .line 34
    iget-object p1, p0, Lorg/nameless/gamespace/preferences/appselector/adapter/AppsItemViewHolder$bind$1;->$onClick:Lkotlin/jvm/functions/Function1;

    iget-object p0, p0, Lorg/nameless/gamespace/preferences/appselector/adapter/AppsItemViewHolder$bind$1;->$app:Landroid/content/pm/ApplicationInfo;

    invoke-interface {p1, p0}, Lkotlin/jvm/functions/Function1;->invoke(Ljava/lang/Object;)Ljava/lang/Object;

    return-void
.end method
