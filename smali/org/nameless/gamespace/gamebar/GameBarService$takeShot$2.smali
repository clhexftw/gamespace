.class final Lorg/nameless/gamespace/gamebar/GameBarService$takeShot$2;
.super Ljava/lang/Object;
.source "GameBarService.kt"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lorg/nameless/gamespace/gamebar/GameBarService;->takeShot()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x18
    name = null
.end annotation


# instance fields
.field final synthetic $afterShot:Lkotlin/jvm/functions/Function0;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lkotlin/jvm/functions/Function0<",
            "Lkotlin/Unit;",
            ">;"
        }
    .end annotation
.end field

.field final synthetic this$0:Lorg/nameless/gamespace/gamebar/GameBarService;


# direct methods
.method constructor <init>(Lorg/nameless/gamespace/gamebar/GameBarService;Lkotlin/jvm/functions/Function0;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lorg/nameless/gamespace/gamebar/GameBarService;",
            "Lkotlin/jvm/functions/Function0<",
            "Lkotlin/Unit;",
            ">;)V"
        }
    .end annotation

    iput-object p1, p0, Lorg/nameless/gamespace/gamebar/GameBarService$takeShot$2;->this$0:Lorg/nameless/gamespace/gamebar/GameBarService;

    iput-object p2, p0, Lorg/nameless/gamespace/gamebar/GameBarService$takeShot$2;->$afterShot:Lkotlin/jvm/functions/Function0;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public final run()V
    .locals 3

    .line 332
    :try_start_0
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/GameBarService$takeShot$2;->this$0:Lorg/nameless/gamespace/gamebar/GameBarService;

    invoke-virtual {v0}, Lorg/nameless/gamespace/gamebar/GameBarService;->getScreenUtils()Lorg/nameless/gamespace/utils/ScreenUtils;

    move-result-object v0

    new-instance v1, Lorg/nameless/gamespace/gamebar/GameBarService$takeShot$2$1;

    iget-object v2, p0, Lorg/nameless/gamespace/gamebar/GameBarService$takeShot$2;->$afterShot:Lkotlin/jvm/functions/Function0;

    invoke-direct {v1, v2}, Lorg/nameless/gamespace/gamebar/GameBarService$takeShot$2$1;-><init>(Lkotlin/jvm/functions/Function0;)V

    invoke-virtual {v0, v1}, Lorg/nameless/gamespace/utils/ScreenUtils;->takeScreenshot(Lkotlin/jvm/functions/Function1;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception v0

    .line 334
    invoke-virtual {v0}, Ljava/lang/Exception;->printStackTrace()V

    .line 335
    iget-object p0, p0, Lorg/nameless/gamespace/gamebar/GameBarService$takeShot$2;->$afterShot:Lkotlin/jvm/functions/Function0;

    invoke-interface {p0}, Lkotlin/jvm/functions/Function0;->invoke()Ljava/lang/Object;

    :goto_0
    return-void
.end method
