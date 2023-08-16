.class Lorg/nameless/custom/preference/RemotePreferenceManager$1;
.super Landroid/content/BroadcastReceiver;
.source "RemotePreferenceManager.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/nameless/custom/preference/RemotePreferenceManager;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lorg/nameless/custom/preference/RemotePreferenceManager;


# direct methods
.method constructor <init>(Lorg/nameless/custom/preference/RemotePreferenceManager;)V
    .locals 0

    .line 138
    iput-object p1, p0, Lorg/nameless/custom/preference/RemotePreferenceManager$1;->this$0:Lorg/nameless/custom/preference/RemotePreferenceManager;

    invoke-direct {p0}, Landroid/content/BroadcastReceiver;-><init>()V

    return-void
.end method


# virtual methods
.method public onReceive(Landroid/content/Context;Landroid/content/Intent;)V
    .locals 3

    .line 141
    invoke-static {}, Lorg/nameless/custom/preference/RemotePreferenceManager;->-$$Nest$sfgetDEBUG()Z

    move-result p1

    if-eqz p1, :cond_0

    invoke-static {}, Lorg/nameless/custom/preference/RemotePreferenceManager;->-$$Nest$sfgetTAG()Ljava/lang/String;

    move-result-object p1

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "onReceive: intent="

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-static {p2}, Ljava/util/Objects;->toString(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-static {p1, v0}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    :cond_0
    const-string p1, "lineageos.intent.action.REFRESH_PREFERENCE"

    .line 143
    invoke-virtual {p2}, Landroid/content/Intent;->getAction()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_2

    const-string p1, ":lineage:pref_key"

    .line 144
    invoke-virtual {p2, p1}, Landroid/content/Intent;->getStringExtra(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    .line 145
    iget-object p2, p0, Lorg/nameless/custom/preference/RemotePreferenceManager$1;->this$0:Lorg/nameless/custom/preference/RemotePreferenceManager;

    invoke-static {p2}, Lorg/nameless/custom/preference/RemotePreferenceManager;->-$$Nest$fgetmCallbacks(Lorg/nameless/custom/preference/RemotePreferenceManager;)Ljava/util/Map;

    move-result-object v0

    monitor-enter v0

    if-eqz p1, :cond_1

    .line 146
    :try_start_0
    iget-object p2, p0, Lorg/nameless/custom/preference/RemotePreferenceManager$1;->this$0:Lorg/nameless/custom/preference/RemotePreferenceManager;

    invoke-static {p2}, Lorg/nameless/custom/preference/RemotePreferenceManager;->-$$Nest$fgetmCallbacks(Lorg/nameless/custom/preference/RemotePreferenceManager;)Ljava/util/Map;

    move-result-object p2

    invoke-interface {p2, p1}, Ljava/util/Map;->containsKey(Ljava/lang/Object;)Z

    move-result p2

    if-eqz p2, :cond_1

    .line 147
    iget-object p0, p0, Lorg/nameless/custom/preference/RemotePreferenceManager$1;->this$0:Lorg/nameless/custom/preference/RemotePreferenceManager;

    invoke-static {p0, p1}, Lorg/nameless/custom/preference/RemotePreferenceManager;->-$$Nest$mrequestUpdate(Lorg/nameless/custom/preference/RemotePreferenceManager;Ljava/lang/String;)V

    .line 149
    :cond_1
    monitor-exit v0

    goto :goto_0

    :catchall_0
    move-exception p0

    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw p0

    :cond_2
    const-string p1, "lineageos.intent.action.UPDATE_PREFERENCE"

    .line 150
    invoke-virtual {p2}, Landroid/content/Intent;->getAction()Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p1, p2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_5

    .line 151
    invoke-virtual {p0}, Landroid/content/BroadcastReceiver;->getAbortBroadcast()Z

    move-result p1

    if-eqz p1, :cond_3

    .line 152
    invoke-static {}, Lorg/nameless/custom/preference/RemotePreferenceManager;->-$$Nest$sfgetTAG()Ljava/lang/String;

    move-result-object p1

    new-instance p2, Ljava/lang/StringBuilder;

    invoke-direct {p2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v0, "Broadcast aborted, code="

    invoke-virtual {p2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p0}, Landroid/content/BroadcastReceiver;->getResultCode()I

    move-result p0

    invoke-virtual {p2, p0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {p2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-static {p1, p0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    return-void

    :cond_3
    const/4 p1, 0x1

    .line 155
    invoke-virtual {p0, p1}, Landroid/content/BroadcastReceiver;->getResultExtras(Z)Landroid/os/Bundle;

    move-result-object p1

    const-string p2, ":lineage:pref_key"

    .line 156
    invoke-virtual {p1, p2}, Landroid/os/Bundle;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p2

    .line 157
    iget-object v0, p0, Lorg/nameless/custom/preference/RemotePreferenceManager$1;->this$0:Lorg/nameless/custom/preference/RemotePreferenceManager;

    invoke-static {v0}, Lorg/nameless/custom/preference/RemotePreferenceManager;->-$$Nest$fgetmCallbacks(Lorg/nameless/custom/preference/RemotePreferenceManager;)Ljava/util/Map;

    move-result-object v0

    monitor-enter v0

    if-eqz p2, :cond_4

    .line 158
    :try_start_1
    iget-object v1, p0, Lorg/nameless/custom/preference/RemotePreferenceManager$1;->this$0:Lorg/nameless/custom/preference/RemotePreferenceManager;

    invoke-static {v1}, Lorg/nameless/custom/preference/RemotePreferenceManager;->-$$Nest$fgetmCallbacks(Lorg/nameless/custom/preference/RemotePreferenceManager;)Ljava/util/Map;

    move-result-object v1

    invoke-interface {v1, p2}, Ljava/util/Map;->containsKey(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_4

    .line 159
    iget-object v1, p0, Lorg/nameless/custom/preference/RemotePreferenceManager$1;->this$0:Lorg/nameless/custom/preference/RemotePreferenceManager;

    invoke-static {v1}, Lorg/nameless/custom/preference/RemotePreferenceManager;->-$$Nest$fgetmMainHandler(Lorg/nameless/custom/preference/RemotePreferenceManager;)Landroid/os/Handler;

    move-result-object v1

    new-instance v2, Lorg/nameless/custom/preference/RemotePreferenceManager$1$1;

    invoke-direct {v2, p0, p2, p1}, Lorg/nameless/custom/preference/RemotePreferenceManager$1$1;-><init>(Lorg/nameless/custom/preference/RemotePreferenceManager$1;Ljava/lang/String;Landroid/os/Bundle;)V

    invoke-virtual {v1, v2}, Landroid/os/Handler;->post(Ljava/lang/Runnable;)Z

    .line 175
    :cond_4
    monitor-exit v0

    goto :goto_0

    :catchall_1
    move-exception p0

    monitor-exit v0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    throw p0

    :cond_5
    :goto_0
    return-void
.end method
