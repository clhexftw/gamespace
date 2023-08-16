.class final Lorg/nameless/gamespace/gamebar/GameBarService$takeShot$afterShot$1$1;
.super Ljava/lang/Object;
.source "GameBarService.kt"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lorg/nameless/gamespace/gamebar/GameBarService$takeShot$afterShot$1;->invoke()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x18
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lorg/nameless/gamespace/gamebar/GameBarService;


# direct methods
.method constructor <init>(Lorg/nameless/gamespace/gamebar/GameBarService;)V
    .locals 0

    iput-object p1, p0, Lorg/nameless/gamespace/gamebar/GameBarService$takeShot$afterShot$1$1;->this$0:Lorg/nameless/gamespace/gamebar/GameBarService;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public final run()V
    .locals 1

    .line 325
    iget-object p0, p0, Lorg/nameless/gamespace/gamebar/GameBarService$takeShot$afterShot$1$1;->this$0:Lorg/nameless/gamespace/gamebar/GameBarService;

    sget-object v0, Lorg/nameless/gamespace/gamebar/GameBarService$takeShot$afterShot$1$1$1;->INSTANCE:Lorg/nameless/gamespace/gamebar/GameBarService$takeShot$afterShot$1$1$1;

    invoke-static {p0, v0}, Lorg/nameless/gamespace/gamebar/GameBarService;->access$updateLayout(Lorg/nameless/gamespace/gamebar/GameBarService;Lkotlin/jvm/functions/Function1;)V

    return-void
.end method
