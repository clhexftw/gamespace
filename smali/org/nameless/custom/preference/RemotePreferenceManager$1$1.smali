.class Lorg/nameless/custom/preference/RemotePreferenceManager$1$1;
.super Ljava/lang/Object;
.source "RemotePreferenceManager.java"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lorg/nameless/custom/preference/RemotePreferenceManager$1;->onReceive(Landroid/content/Context;Landroid/content/Intent;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$1:Lorg/nameless/custom/preference/RemotePreferenceManager$1;

.field final synthetic val$bundle:Landroid/os/Bundle;

.field final synthetic val$key:Ljava/lang/String;


# direct methods
.method constructor <init>(Lorg/nameless/custom/preference/RemotePreferenceManager$1;Ljava/lang/String;Landroid/os/Bundle;)V
    .locals 0

    .line 159
    iput-object p1, p0, Lorg/nameless/custom/preference/RemotePreferenceManager$1$1;->this$1:Lorg/nameless/custom/preference/RemotePreferenceManager$1;

    iput-object p2, p0, Lorg/nameless/custom/preference/RemotePreferenceManager$1$1;->val$key:Ljava/lang/String;

    iput-object p3, p0, Lorg/nameless/custom/preference/RemotePreferenceManager$1$1;->val$bundle:Landroid/os/Bundle;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .locals 4

    .line 162
    iget-object v0, p0, Lorg/nameless/custom/preference/RemotePreferenceManager$1$1;->this$1:Lorg/nameless/custom/preference/RemotePreferenceManager$1;

    iget-object v0, v0, Lorg/nameless/custom/preference/RemotePreferenceManager$1;->this$0:Lorg/nameless/custom/preference/RemotePreferenceManager;

    invoke-static {v0}, Lorg/nameless/custom/preference/RemotePreferenceManager;->-$$Nest$fgetmCallbacks(Lorg/nameless/custom/preference/RemotePreferenceManager;)Ljava/util/Map;

    move-result-object v0

    monitor-enter v0

    .line 163
    :try_start_0
    iget-object v1, p0, Lorg/nameless/custom/preference/RemotePreferenceManager$1$1;->this$1:Lorg/nameless/custom/preference/RemotePreferenceManager$1;

    iget-object v1, v1, Lorg/nameless/custom/preference/RemotePreferenceManager$1;->this$0:Lorg/nameless/custom/preference/RemotePreferenceManager;

    invoke-static {v1}, Lorg/nameless/custom/preference/RemotePreferenceManager;->-$$Nest$fgetmCallbacks(Lorg/nameless/custom/preference/RemotePreferenceManager;)Ljava/util/Map;

    move-result-object v1

    iget-object v2, p0, Lorg/nameless/custom/preference/RemotePreferenceManager$1$1;->val$key:Ljava/lang/String;

    invoke-interface {v1, v2}, Ljava/util/Map;->containsKey(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_0

    .line 164
    iget-object v1, p0, Lorg/nameless/custom/preference/RemotePreferenceManager$1$1;->this$1:Lorg/nameless/custom/preference/RemotePreferenceManager$1;

    iget-object v1, v1, Lorg/nameless/custom/preference/RemotePreferenceManager$1;->this$0:Lorg/nameless/custom/preference/RemotePreferenceManager;

    invoke-static {v1}, Lorg/nameless/custom/preference/RemotePreferenceManager;->-$$Nest$fgetmCallbacks(Lorg/nameless/custom/preference/RemotePreferenceManager;)Ljava/util/Map;

    move-result-object v1

    iget-object v2, p0, Lorg/nameless/custom/preference/RemotePreferenceManager$1$1;->val$key:Ljava/lang/String;

    invoke-interface {v1, v2}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/util/Set;

    if-eqz v1, :cond_0

    .line 166
    invoke-interface {v1}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :goto_0
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_0

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lorg/nameless/custom/preference/RemotePreferenceManager$OnRemoteUpdateListener;

    .line 167
    iget-object v3, p0, Lorg/nameless/custom/preference/RemotePreferenceManager$1$1;->val$bundle:Landroid/os/Bundle;

    invoke-interface {v2, v3}, Lorg/nameless/custom/preference/RemotePreferenceManager$OnRemoteUpdateListener;->onRemoteUpdated(Landroid/os/Bundle;)V

    goto :goto_0

    .line 171
    :cond_0
    monitor-exit v0

    return-void

    :catchall_0
    move-exception p0

    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw p0
.end method
