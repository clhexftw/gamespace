.class public final Lorg/nameless/gamespace/gamebar/GameBarService$recorderButton$1;
.super Lcom/android/systemui/screenrecord/IRecordingCallback$Stub;
.source "GameBarService.kt"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lorg/nameless/gamespace/gamebar/GameBarService;->recorderButton()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = null
.end annotation


# instance fields
.field final synthetic $actionRecorder:Landroid/widget/ImageButton;

.field final synthetic this$0:Lorg/nameless/gamespace/gamebar/GameBarService;


# direct methods
.method constructor <init>(Lorg/nameless/gamespace/gamebar/GameBarService;Landroid/widget/ImageButton;)V
    .locals 0

    iput-object p1, p0, Lorg/nameless/gamespace/gamebar/GameBarService$recorderButton$1;->this$0:Lorg/nameless/gamespace/gamebar/GameBarService;

    iput-object p2, p0, Lorg/nameless/gamespace/gamebar/GameBarService$recorderButton$1;->$actionRecorder:Landroid/widget/ImageButton;

    .line 388
    invoke-direct {p0}, Lcom/android/systemui/screenrecord/IRecordingCallback$Stub;-><init>()V

    return-void
.end method


# virtual methods
.method public onRecordingEnd()V
    .locals 2

    .line 396
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/GameBarService$recorderButton$1;->this$0:Lorg/nameless/gamespace/gamebar/GameBarService;

    invoke-static {v0}, Lorg/nameless/gamespace/gamebar/GameBarService;->access$getHandler(Lorg/nameless/gamespace/gamebar/GameBarService;)Landroid/os/Handler;

    move-result-object v0

    new-instance v1, Lorg/nameless/gamespace/gamebar/GameBarService$recorderButton$1$onRecordingEnd$1;

    iget-object p0, p0, Lorg/nameless/gamespace/gamebar/GameBarService$recorderButton$1;->$actionRecorder:Landroid/widget/ImageButton;

    invoke-direct {v1, p0}, Lorg/nameless/gamespace/gamebar/GameBarService$recorderButton$1$onRecordingEnd$1;-><init>(Landroid/widget/ImageButton;)V

    invoke-virtual {v0, v1}, Landroid/os/Handler;->post(Ljava/lang/Runnable;)Z

    return-void
.end method

.method public onRecordingStart()V
    .locals 2

    .line 390
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/GameBarService$recorderButton$1;->this$0:Lorg/nameless/gamespace/gamebar/GameBarService;

    invoke-static {v0}, Lorg/nameless/gamespace/gamebar/GameBarService;->access$getHandler(Lorg/nameless/gamespace/gamebar/GameBarService;)Landroid/os/Handler;

    move-result-object v0

    new-instance v1, Lorg/nameless/gamespace/gamebar/GameBarService$recorderButton$1$onRecordingStart$1;

    iget-object p0, p0, Lorg/nameless/gamespace/gamebar/GameBarService$recorderButton$1;->$actionRecorder:Landroid/widget/ImageButton;

    invoke-direct {v1, p0}, Lorg/nameless/gamespace/gamebar/GameBarService$recorderButton$1$onRecordingStart$1;-><init>(Landroid/widget/ImageButton;)V

    invoke-virtual {v0, v1}, Landroid/os/Handler;->post(Ljava/lang/Runnable;)Z

    return-void
.end method
