.class final Lorg/nameless/gamespace/gamebar/GameBarService$recorderButton$2;
.super Ljava/lang/Object;
.source "GameBarService.kt"

# interfaces
.implements Landroid/view/View$OnClickListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lorg/nameless/gamespace/gamebar/GameBarService;->recorderButton()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x18
    name = null
.end annotation


# instance fields
.field final synthetic $recorder:Lcom/android/systemui/screenrecord/IRemoteRecording;

.field final synthetic this$0:Lorg/nameless/gamespace/gamebar/GameBarService;


# direct methods
.method constructor <init>(Lcom/android/systemui/screenrecord/IRemoteRecording;Lorg/nameless/gamespace/gamebar/GameBarService;)V
    .locals 0

    iput-object p1, p0, Lorg/nameless/gamespace/gamebar/GameBarService$recorderButton$2;->$recorder:Lcom/android/systemui/screenrecord/IRemoteRecording;

    iput-object p2, p0, Lorg/nameless/gamespace/gamebar/GameBarService$recorderButton$2;->this$0:Lorg/nameless/gamespace/gamebar/GameBarService;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public final onClick(Landroid/view/View;)V
    .locals 0

    .line 402
    iget-object p1, p0, Lorg/nameless/gamespace/gamebar/GameBarService$recorderButton$2;->$recorder:Lcom/android/systemui/screenrecord/IRemoteRecording;

    invoke-interface {p1}, Lcom/android/systemui/screenrecord/IRemoteRecording;->isStarting()Z

    move-result p1

    if-eqz p1, :cond_0

    return-void

    .line 406
    :cond_0
    iget-object p1, p0, Lorg/nameless/gamespace/gamebar/GameBarService$recorderButton$2;->$recorder:Lcom/android/systemui/screenrecord/IRemoteRecording;

    invoke-interface {p1}, Lcom/android/systemui/screenrecord/IRemoteRecording;->isRecording()Z

    move-result p1

    if-nez p1, :cond_1

    .line 407
    iget-object p1, p0, Lorg/nameless/gamespace/gamebar/GameBarService$recorderButton$2;->$recorder:Lcom/android/systemui/screenrecord/IRemoteRecording;

    invoke-interface {p1}, Lcom/android/systemui/screenrecord/IRemoteRecording;->startRecording()V

    goto :goto_0

    .line 409
    :cond_1
    iget-object p1, p0, Lorg/nameless/gamespace/gamebar/GameBarService$recorderButton$2;->$recorder:Lcom/android/systemui/screenrecord/IRemoteRecording;

    invoke-interface {p1}, Lcom/android/systemui/screenrecord/IRemoteRecording;->stopRecording()V

    .line 412
    :goto_0
    iget-object p0, p0, Lorg/nameless/gamespace/gamebar/GameBarService$recorderButton$2;->this$0:Lorg/nameless/gamespace/gamebar/GameBarService;

    const/4 p1, 0x0

    invoke-static {p0, p1}, Lorg/nameless/gamespace/gamebar/GameBarService;->access$setBarExpanded(Lorg/nameless/gamespace/gamebar/GameBarService;Z)V

    return-void
.end method
