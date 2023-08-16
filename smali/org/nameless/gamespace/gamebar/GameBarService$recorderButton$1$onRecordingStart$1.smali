.class final Lorg/nameless/gamespace/gamebar/GameBarService$recorderButton$1$onRecordingStart$1;
.super Ljava/lang/Object;
.source "GameBarService.kt"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lorg/nameless/gamespace/gamebar/GameBarService$recorderButton$1;->onRecordingStart()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x18
    name = null
.end annotation


# instance fields
.field final synthetic $actionRecorder:Landroid/widget/ImageButton;


# direct methods
.method constructor <init>(Landroid/widget/ImageButton;)V
    .locals 0

    iput-object p1, p0, Lorg/nameless/gamespace/gamebar/GameBarService$recorderButton$1$onRecordingStart$1;->$actionRecorder:Landroid/widget/ImageButton;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public final run()V
    .locals 1

    .line 391
    iget-object p0, p0, Lorg/nameless/gamespace/gamebar/GameBarService$recorderButton$1$onRecordingStart$1;->$actionRecorder:Landroid/widget/ImageButton;

    const/4 v0, 0x1

    invoke-virtual {p0, v0}, Landroid/widget/ImageButton;->setSelected(Z)V

    return-void
.end method
