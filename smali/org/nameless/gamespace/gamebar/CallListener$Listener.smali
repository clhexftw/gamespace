.class final Lorg/nameless/gamespace/gamebar/CallListener$Listener;
.super Landroid/telephony/PhoneStateListener;
.source "CallListener.kt"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/nameless/gamespace/gamebar/CallListener;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x12
    name = "Listener"
.end annotation


# instance fields
.field private previousAudioMode:I

.field private previousState:I

.field final synthetic this$0:Lorg/nameless/gamespace/gamebar/CallListener;


# direct methods
.method public constructor <init>(Lorg/nameless/gamespace/gamebar/CallListener;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()V"
        }
    .end annotation

    .line 90
    iput-object p1, p0, Lorg/nameless/gamespace/gamebar/CallListener$Listener;->this$0:Lorg/nameless/gamespace/gamebar/CallListener;

    invoke-direct {p0}, Landroid/telephony/PhoneStateListener;-><init>()V

    .line 92
    invoke-static {p1}, Lorg/nameless/gamespace/gamebar/CallListener;->access$getAudioManager$p(Lorg/nameless/gamespace/gamebar/CallListener;)Landroid/media/AudioManager;

    move-result-object p1

    invoke-virtual {p1}, Landroid/media/AudioManager;->getMode()I

    move-result p1

    iput p1, p0, Lorg/nameless/gamespace/gamebar/CallListener$Listener;->previousAudioMode:I

    return-void
.end method


# virtual methods
.method public onCallStateChanged(ILjava/lang/String;)V
    .locals 5

    const-string v0, "incomingNumber"

    invoke-static {p2, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 95
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/CallListener$Listener;->this$0:Lorg/nameless/gamespace/gamebar/CallListener;

    invoke-static {v0}, Lorg/nameless/gamespace/gamebar/CallListener;->access$getCallsMode$p(Lorg/nameless/gamespace/gamebar/CallListener;)I

    move-result v0

    if-nez v0, :cond_0

    return-void

    :cond_0
    const/4 v0, 0x2

    const/4 v1, 0x0

    if-eqz p1, :cond_7

    const/4 v2, 0x1

    if-eq p1, v2, :cond_4

    if-eq p1, v0, :cond_1

    goto/16 :goto_1

    .line 112
    :cond_1
    iget-object p2, p0, Lorg/nameless/gamespace/gamebar/CallListener$Listener;->this$0:Lorg/nameless/gamespace/gamebar/CallListener;

    invoke-static {p2}, Lorg/nameless/gamespace/gamebar/CallListener;->access$getCallsMode$p(Lorg/nameless/gamespace/gamebar/CallListener;)I

    move-result p2

    if-ne p2, v0, :cond_2

    return-void

    .line 113
    :cond_2
    iget p2, p0, Lorg/nameless/gamespace/gamebar/CallListener$Listener;->previousState:I

    if-ne p2, v2, :cond_9

    .line 114
    iget-object p2, p0, Lorg/nameless/gamespace/gamebar/CallListener$Listener;->this$0:Lorg/nameless/gamespace/gamebar/CallListener;

    invoke-static {p2}, Lorg/nameless/gamespace/gamebar/CallListener;->access$isHeadsetPluggedIn(Lorg/nameless/gamespace/gamebar/CallListener;)Z

    move-result p2

    if-eqz p2, :cond_3

    .line 115
    iget-object p2, p0, Lorg/nameless/gamespace/gamebar/CallListener$Listener;->this$0:Lorg/nameless/gamespace/gamebar/CallListener;

    invoke-static {p2}, Lorg/nameless/gamespace/gamebar/CallListener;->access$getAudioManager$p(Lorg/nameless/gamespace/gamebar/CallListener;)Landroid/media/AudioManager;

    move-result-object p2

    invoke-virtual {p2, v1}, Landroid/media/AudioManager;->setSpeakerphoneOn(Z)V

    .line 116
    invoke-static {v1, v1}, Landroid/media/AudioSystem;->setForceUse(II)I

    goto :goto_0

    .line 121
    :cond_3
    iget-object p2, p0, Lorg/nameless/gamespace/gamebar/CallListener$Listener;->this$0:Lorg/nameless/gamespace/gamebar/CallListener;

    invoke-static {p2}, Lorg/nameless/gamespace/gamebar/CallListener;->access$getAudioManager$p(Lorg/nameless/gamespace/gamebar/CallListener;)Landroid/media/AudioManager;

    move-result-object p2

    invoke-virtual {p2, v2}, Landroid/media/AudioManager;->setSpeakerphoneOn(Z)V

    .line 122
    invoke-static {v1, v2}, Landroid/media/AudioSystem;->setForceUse(II)I

    .line 127
    :goto_0
    iget-object p2, p0, Lorg/nameless/gamespace/gamebar/CallListener$Listener;->this$0:Lorg/nameless/gamespace/gamebar/CallListener;

    invoke-static {p2}, Lorg/nameless/gamespace/gamebar/CallListener;->access$getAudioManager$p(Lorg/nameless/gamespace/gamebar/CallListener;)Landroid/media/AudioManager;

    move-result-object p2

    const/4 v0, 0x3

    invoke-virtual {p2, v0}, Landroid/media/AudioManager;->setMode(I)V

    goto/16 :goto_1

    .line 98
    :cond_4
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/CallListener$Listener;->this$0:Lorg/nameless/gamespace/gamebar/CallListener;

    invoke-static {v0}, Lorg/nameless/gamespace/gamebar/CallListener;->access$checkPermission(Lorg/nameless/gamespace/gamebar/CallListener;)Z

    move-result v0

    if-nez v0, :cond_5

    return-void

    .line 99
    :cond_5
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/CallListener$Listener;->this$0:Lorg/nameless/gamespace/gamebar/CallListener;

    invoke-static {v0}, Lorg/nameless/gamespace/gamebar/CallListener;->access$getCallsMode$p(Lorg/nameless/gamespace/gamebar/CallListener;)I

    move-result v0

    if-ne v0, v2, :cond_6

    .line 100
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/CallListener$Listener;->this$0:Lorg/nameless/gamespace/gamebar/CallListener;

    invoke-static {v0}, Lorg/nameless/gamespace/gamebar/CallListener;->access$getTelecomManager$p(Lorg/nameless/gamespace/gamebar/CallListener;)Landroid/telecom/TelecomManager;

    move-result-object v0

    invoke-virtual {v0}, Landroid/telecom/TelecomManager;->acceptRingingCall()V

    .line 101
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/CallListener$Listener;->this$0:Lorg/nameless/gamespace/gamebar/CallListener;

    invoke-static {v0}, Lorg/nameless/gamespace/gamebar/CallListener;->access$getContext$p(Lorg/nameless/gamespace/gamebar/CallListener;)Landroid/content/Context;

    move-result-object v0

    iget-object v3, p0, Lorg/nameless/gamespace/gamebar/CallListener$Listener;->this$0:Lorg/nameless/gamespace/gamebar/CallListener;

    invoke-static {v3}, Lorg/nameless/gamespace/gamebar/CallListener;->access$getContext$p(Lorg/nameless/gamespace/gamebar/CallListener;)Landroid/content/Context;

    move-result-object v3

    const v4, 0x7f1201ab

    new-array v2, v2, [Ljava/lang/Object;

    aput-object p2, v2, v1

    invoke-virtual {v3, v4, v2}, Landroid/content/Context;->getString(I[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p2

    invoke-static {v0, p2, v1}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object p2

    .line 103
    invoke-virtual {p2}, Landroid/widget/Toast;->show()V

    goto :goto_1

    .line 105
    :cond_6
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/CallListener$Listener;->this$0:Lorg/nameless/gamespace/gamebar/CallListener;

    invoke-static {v0}, Lorg/nameless/gamespace/gamebar/CallListener;->access$getTelecomManager$p(Lorg/nameless/gamespace/gamebar/CallListener;)Landroid/telecom/TelecomManager;

    move-result-object v0

    invoke-virtual {v0}, Landroid/telecom/TelecomManager;->endCall()Z

    .line 106
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/CallListener$Listener;->this$0:Lorg/nameless/gamespace/gamebar/CallListener;

    invoke-static {v0}, Lorg/nameless/gamespace/gamebar/CallListener;->access$getContext$p(Lorg/nameless/gamespace/gamebar/CallListener;)Landroid/content/Context;

    move-result-object v0

    iget-object v3, p0, Lorg/nameless/gamespace/gamebar/CallListener$Listener;->this$0:Lorg/nameless/gamespace/gamebar/CallListener;

    invoke-static {v3}, Lorg/nameless/gamespace/gamebar/CallListener;->access$getContext$p(Lorg/nameless/gamespace/gamebar/CallListener;)Landroid/content/Context;

    move-result-object v3

    const v4, 0x7f1201ac

    new-array v2, v2, [Ljava/lang/Object;

    aput-object p2, v2, v1

    invoke-virtual {v3, v4, v2}, Landroid/content/Context;->getString(I[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p2

    invoke-static {v0, p2, v1}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object p2

    .line 108
    invoke-virtual {p2}, Landroid/widget/Toast;->show()V

    goto :goto_1

    .line 131
    :cond_7
    iget-object p2, p0, Lorg/nameless/gamespace/gamebar/CallListener$Listener;->this$0:Lorg/nameless/gamespace/gamebar/CallListener;

    invoke-static {p2}, Lorg/nameless/gamespace/gamebar/CallListener;->access$getCallsMode$p(Lorg/nameless/gamespace/gamebar/CallListener;)I

    move-result p2

    if-ne p2, v0, :cond_8

    return-void

    .line 132
    :cond_8
    iget p2, p0, Lorg/nameless/gamespace/gamebar/CallListener$Listener;->previousState:I

    if-ne p2, v0, :cond_9

    .line 133
    iget-object p2, p0, Lorg/nameless/gamespace/gamebar/CallListener$Listener;->this$0:Lorg/nameless/gamespace/gamebar/CallListener;

    invoke-static {p2}, Lorg/nameless/gamespace/gamebar/CallListener;->access$getAudioManager$p(Lorg/nameless/gamespace/gamebar/CallListener;)Landroid/media/AudioManager;

    move-result-object p2

    iget v0, p0, Lorg/nameless/gamespace/gamebar/CallListener$Listener;->previousAudioMode:I

    invoke-virtual {p2, v0}, Landroid/media/AudioManager;->setMode(I)V

    .line 134
    invoke-static {v1, v1}, Landroid/media/AudioSystem;->setForceUse(II)I

    .line 138
    iget-object p2, p0, Lorg/nameless/gamespace/gamebar/CallListener$Listener;->this$0:Lorg/nameless/gamespace/gamebar/CallListener;

    invoke-static {p2}, Lorg/nameless/gamespace/gamebar/CallListener;->access$getAudioManager$p(Lorg/nameless/gamespace/gamebar/CallListener;)Landroid/media/AudioManager;

    move-result-object p2

    invoke-virtual {p2, v1}, Landroid/media/AudioManager;->setSpeakerphoneOn(Z)V

    .line 142
    :cond_9
    :goto_1
    iput p1, p0, Lorg/nameless/gamespace/gamebar/CallListener$Listener;->previousState:I

    return-void
.end method
