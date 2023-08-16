.class final Lorg/nameless/gamespace/gamebar/DanmakuService$pushNotification$1$1$1;
.super Ljava/lang/Object;
.source "DanmakuService.kt"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lorg/nameless/gamespace/gamebar/DanmakuService;->pushNotification()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x18
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lorg/nameless/gamespace/gamebar/DanmakuService;


# direct methods
.method constructor <init>(Lorg/nameless/gamespace/gamebar/DanmakuService;)V
    .locals 0

    iput-object p1, p0, Lorg/nameless/gamespace/gamebar/DanmakuService$pushNotification$1$1$1;->this$0:Lorg/nameless/gamespace/gamebar/DanmakuService;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public final run()V
    .locals 0

    .line 187
    iget-object p0, p0, Lorg/nameless/gamespace/gamebar/DanmakuService$pushNotification$1$1$1;->this$0:Lorg/nameless/gamespace/gamebar/DanmakuService;

    invoke-static {p0}, Lorg/nameless/gamespace/gamebar/DanmakuService;->access$popNotification(Lorg/nameless/gamespace/gamebar/DanmakuService;)V

    return-void
.end method
