.class final Lorg/nameless/gamespace/utils/ScreenUtils$takeScreenshot$1$1;
.super Ljava/lang/Object;
.source "ScreenUtils.kt"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lorg/nameless/gamespace/utils/ScreenUtils$takeScreenshot$1;->accept(Landroid/net/Uri;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x18
    name = null
.end annotation


# instance fields
.field final synthetic $it:Landroid/net/Uri;

.field final synthetic $onComplete:Lkotlin/jvm/functions/Function1;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lkotlin/jvm/functions/Function1<",
            "Landroid/net/Uri;",
            "Lkotlin/Unit;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method constructor <init>(Lkotlin/jvm/functions/Function1;Landroid/net/Uri;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lkotlin/jvm/functions/Function1<",
            "-",
            "Landroid/net/Uri;",
            "Lkotlin/Unit;",
            ">;",
            "Landroid/net/Uri;",
            ")V"
        }
    .end annotation

    iput-object p1, p0, Lorg/nameless/gamespace/utils/ScreenUtils$takeScreenshot$1$1;->$onComplete:Lkotlin/jvm/functions/Function1;

    iput-object p2, p0, Lorg/nameless/gamespace/utils/ScreenUtils$takeScreenshot$1$1;->$it:Landroid/net/Uri;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public final run()V
    .locals 1

    .line 99
    iget-object v0, p0, Lorg/nameless/gamespace/utils/ScreenUtils$takeScreenshot$1$1;->$onComplete:Lkotlin/jvm/functions/Function1;

    if-eqz v0, :cond_0

    iget-object p0, p0, Lorg/nameless/gamespace/utils/ScreenUtils$takeScreenshot$1$1;->$it:Landroid/net/Uri;

    invoke-interface {v0, p0}, Lkotlin/jvm/functions/Function1;->invoke(Ljava/lang/Object;)Ljava/lang/Object;

    :cond_0
    return-void
.end method
