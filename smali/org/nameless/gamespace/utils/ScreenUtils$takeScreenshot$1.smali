.class final Lorg/nameless/gamespace/utils/ScreenUtils$takeScreenshot$1;
.super Ljava/lang/Object;
.source "ScreenUtils.kt"

# interfaces
.implements Ljava/util/function/Consumer;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lorg/nameless/gamespace/utils/ScreenUtils;->takeScreenshot(Lkotlin/jvm/functions/Function1;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x18
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "<T:",
        "Ljava/lang/Object;",
        ">",
        "Ljava/lang/Object;",
        "Ljava/util/function/Consumer;"
    }
.end annotation


# instance fields
.field final synthetic $handler:Landroid/os/Handler;

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
.method constructor <init>(Landroid/os/Handler;Lkotlin/jvm/functions/Function1;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Landroid/os/Handler;",
            "Lkotlin/jvm/functions/Function1<",
            "-",
            "Landroid/net/Uri;",
            "Lkotlin/Unit;",
            ">;)V"
        }
    .end annotation

    iput-object p1, p0, Lorg/nameless/gamespace/utils/ScreenUtils$takeScreenshot$1;->$handler:Landroid/os/Handler;

    iput-object p2, p0, Lorg/nameless/gamespace/utils/ScreenUtils$takeScreenshot$1;->$onComplete:Lkotlin/jvm/functions/Function1;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public final accept(Landroid/net/Uri;)V
    .locals 2

    .line 99
    iget-object v0, p0, Lorg/nameless/gamespace/utils/ScreenUtils$takeScreenshot$1;->$handler:Landroid/os/Handler;

    new-instance v1, Lorg/nameless/gamespace/utils/ScreenUtils$takeScreenshot$1$1;

    iget-object p0, p0, Lorg/nameless/gamespace/utils/ScreenUtils$takeScreenshot$1;->$onComplete:Lkotlin/jvm/functions/Function1;

    invoke-direct {v1, p0, p1}, Lorg/nameless/gamespace/utils/ScreenUtils$takeScreenshot$1$1;-><init>(Lkotlin/jvm/functions/Function1;Landroid/net/Uri;)V

    invoke-virtual {v0, v1}, Landroid/os/Handler;->post(Ljava/lang/Runnable;)Z

    return-void
.end method

.method public bridge synthetic accept(Ljava/lang/Object;)V
    .locals 0

    .line 96
    check-cast p1, Landroid/net/Uri;

    invoke-virtual {p0, p1}, Lorg/nameless/gamespace/utils/ScreenUtils$takeScreenshot$1;->accept(Landroid/net/Uri;)V

    return-void
.end method
