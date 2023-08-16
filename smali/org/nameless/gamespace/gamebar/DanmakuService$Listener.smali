.class final Lorg/nameless/gamespace/gamebar/DanmakuService$Listener;
.super Landroid/service/notification/NotificationListenerService;
.source "DanmakuService.kt"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/nameless/gamespace/gamebar/DanmakuService;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x12
    name = "Listener"
.end annotation


# instance fields
.field private final postedNotifications:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/lang/Long;",
            ">;"
        }
    .end annotation
.end field

.field final synthetic this$0:Lorg/nameless/gamespace/gamebar/DanmakuService;


# direct methods
.method public constructor <init>(Lorg/nameless/gamespace/gamebar/DanmakuService;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()V"
        }
    .end annotation

    .line 244
    iput-object p1, p0, Lorg/nameless/gamespace/gamebar/DanmakuService$Listener;->this$0:Lorg/nameless/gamespace/gamebar/DanmakuService;

    invoke-direct {p0}, Landroid/service/notification/NotificationListenerService;-><init>()V

    .line 246
    new-instance p1, Ljava/util/LinkedHashMap;

    invoke-direct {p1}, Ljava/util/LinkedHashMap;-><init>()V

    iput-object p1, p0, Lorg/nameless/gamespace/gamebar/DanmakuService$Listener;->postedNotifications:Ljava/util/Map;

    return-void
.end method

.method private final insertPostedNotification(Ljava/lang/String;J)V
    .locals 2

    .line 275
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/DanmakuService$Listener;->postedNotifications:Ljava/util/Map;

    invoke-interface {v0}, Ljava/util/Map;->size()I

    move-result v0

    const/16 v1, 0x63

    if-lt v0, v1, :cond_0

    .line 276
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/DanmakuService$Listener;->postedNotifications:Ljava/util/Map;

    invoke-interface {v0}, Ljava/util/Map;->clear()V

    .line 278
    :cond_0
    iget-object p0, p0, Lorg/nameless/gamespace/gamebar/DanmakuService$Listener;->postedNotifications:Ljava/util/Map;

    invoke-static {p2, p3}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p2

    invoke-interface {p0, p1, p2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    return-void
.end method


# virtual methods
.method public onNotificationPosted(Landroid/service/notification/StatusBarNotification;)V
    .locals 6

    const-string v0, "sbn"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 249
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/DanmakuService$Listener;->this$0:Lorg/nameless/gamespace/gamebar/DanmakuService;

    invoke-static {v0}, Lorg/nameless/gamespace/gamebar/DanmakuService;->access$getAppSettings$p(Lorg/nameless/gamespace/gamebar/DanmakuService;)Lorg/nameless/gamespace/data/AppSettings;

    move-result-object v0

    invoke-virtual {v0}, Lorg/nameless/gamespace/data/AppSettings;->getNotificationsMode()I

    move-result v0

    const/4 v1, 0x2

    if-eq v0, v1, :cond_0

    return-void

    .line 250
    :cond_0
    invoke-virtual {p1}, Landroid/service/notification/StatusBarNotification;->isClearable()Z

    move-result v0

    if-eqz v0, :cond_a

    invoke-virtual {p1}, Landroid/service/notification/StatusBarNotification;->isOngoing()Z

    move-result v0

    if-eqz v0, :cond_1

    goto/16 :goto_3

    .line 251
    :cond_1
    invoke-virtual {p1}, Landroid/service/notification/StatusBarNotification;->getNotification()Landroid/app/Notification;

    move-result-object v0

    iget-object v0, v0, Landroid/app/Notification;->extras:Landroid/os/Bundle;

    const-string v1, "android.title"

    .line 252
    invoke-virtual {v0, v1}, Landroid/os/Bundle;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    const/4 v2, 0x0

    const/4 v3, 0x1

    if-eqz v1, :cond_2

    .line 253
    invoke-static {v1}, Lkotlin/text/StringsKt;->isBlank(Ljava/lang/CharSequence;)Z

    move-result v4

    xor-int/2addr v4, v3

    if-ne v4, v3, :cond_2

    move v4, v3

    goto :goto_0

    :cond_2
    move v4, v2

    :goto_0
    if-nez v4, :cond_3

    const-string v1, "android.title.big"

    invoke-virtual {v0, v1}, Landroid/os/Bundle;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    :cond_3
    const-string v4, ""

    if-eqz v1, :cond_4

    .line 256
    invoke-static {v1}, Lkotlin/text/StringsKt;->isBlank(Ljava/lang/CharSequence;)Z

    move-result v5

    xor-int/2addr v5, v3

    if-ne v5, v3, :cond_4

    move v5, v3

    goto :goto_1

    :cond_4
    move v5, v2

    :goto_1
    if-eqz v5, :cond_5

    .line 257
    new-instance v5, Ljava/lang/StringBuilder;

    invoke-direct {v5}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v5, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v4, "["

    invoke-virtual {v5, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v5, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, "] "

    invoke-virtual {v5, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    :cond_5
    const-string v1, "android.text"

    .line 259
    invoke-virtual {v0, v1}, Landroid/os/Bundle;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    if-eqz v0, :cond_6

    .line 260
    invoke-static {v0}, Lkotlin/text/StringsKt;->isBlank(Ljava/lang/CharSequence;)Z

    move-result v1

    xor-int/2addr v1, v3

    if-ne v1, v3, :cond_6

    move v2, v3

    :cond_6
    if-eqz v2, :cond_7

    .line 261
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v1, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    .line 264
    :cond_7
    invoke-virtual {p1}, Landroid/service/notification/StatusBarNotification;->getNotification()Landroid/app/Notification;

    move-result-object p1

    iget-wide v0, p1, Landroid/app/Notification;->when:J

    .line 265
    invoke-static {v4}, Lkotlin/text/StringsKt;->isBlank(Ljava/lang/CharSequence;)Z

    move-result p1

    xor-int/2addr p1, v3

    if-eqz p1, :cond_a

    .line 266
    iget-object p1, p0, Lorg/nameless/gamespace/gamebar/DanmakuService$Listener;->postedNotifications:Ljava/util/Map;

    invoke-interface {p1, v4}, Ljava/util/Map;->containsKey(Ljava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_9

    .line 267
    iget-object p1, p0, Lorg/nameless/gamespace/gamebar/DanmakuService$Listener;->postedNotifications:Ljava/util/Map;

    invoke-interface {p1, v4}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/Long;

    if-nez p1, :cond_8

    goto :goto_2

    :cond_8
    invoke-virtual {p1}, Ljava/lang/Long;->longValue()J

    move-result-wide v2

    cmp-long p1, v2, v0

    if-eqz p1, :cond_a

    .line 269
    :cond_9
    :goto_2
    iget-object p1, p0, Lorg/nameless/gamespace/gamebar/DanmakuService$Listener;->this$0:Lorg/nameless/gamespace/gamebar/DanmakuService;

    invoke-static {p1, v4}, Lorg/nameless/gamespace/gamebar/DanmakuService;->access$showNotificationAsOverlay(Lorg/nameless/gamespace/gamebar/DanmakuService;Ljava/lang/String;)V

    .line 270
    invoke-direct {p0, v4, v0, v1}, Lorg/nameless/gamespace/gamebar/DanmakuService$Listener;->insertPostedNotification(Ljava/lang/String;J)V

    :cond_a
    :goto_3
    return-void
.end method
