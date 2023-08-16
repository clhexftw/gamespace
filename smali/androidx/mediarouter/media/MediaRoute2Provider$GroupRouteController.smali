.class Landroidx/mediarouter/media/MediaRoute2Provider$GroupRouteController;
.super Landroidx/mediarouter/media/MediaRouteProvider$DynamicGroupRouteController;
.source "MediaRoute2Provider.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Landroidx/mediarouter/media/MediaRoute2Provider;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x2
    name = "GroupRouteController"
.end annotation

.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Landroidx/mediarouter/media/MediaRoute2Provider$GroupRouteController$ReceiveHandler;
    }
.end annotation


# instance fields
.field private final mClearOptimisticVolumeRunnable:Ljava/lang/Runnable;

.field final mControllerHandler:Landroid/os/Handler;

.field mGroupRouteDescriptor:Landroidx/mediarouter/media/MediaRouteDescriptor;

.field final mInitialMemberRouteId:Ljava/lang/String;

.field mNextRequestId:Ljava/util/concurrent/atomic/AtomicInteger;

.field mOptimisticVolume:I

.field final mPendingCallbacks:Landroid/util/SparseArray;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroid/util/SparseArray<",
            "Landroidx/mediarouter/media/MediaRouter$ControlRequestCallback;",
            ">;"
        }
    .end annotation
.end field

.field final mReceiveMessenger:Landroid/os/Messenger;

.field final mRoutingController:Landroid/media/MediaRouter2$RoutingController;

.field final mServiceMessenger:Landroid/os/Messenger;

.field final synthetic this$0:Landroidx/mediarouter/media/MediaRoute2Provider;


# direct methods
.method public static synthetic $r8$lambda$40ZKNaXHWQD6e-__eeMxhJoprt0(Landroidx/mediarouter/media/MediaRoute2Provider$GroupRouteController;)V
    .locals 0

    invoke-direct {p0}, Landroidx/mediarouter/media/MediaRoute2Provider$GroupRouteController;->lambda$new$0()V

    return-void
.end method

.method constructor <init>(Landroidx/mediarouter/media/MediaRoute2Provider;Landroid/media/MediaRouter2$RoutingController;Ljava/lang/String;)V
    .locals 1

    .line 477
    iput-object p1, p0, Landroidx/mediarouter/media/MediaRoute2Provider$GroupRouteController;->this$0:Landroidx/mediarouter/media/MediaRoute2Provider;

    invoke-direct {p0}, Landroidx/mediarouter/media/MediaRouteProvider$DynamicGroupRouteController;-><init>()V

    .line 466
    new-instance p1, Landroid/util/SparseArray;

    invoke-direct {p1}, Landroid/util/SparseArray;-><init>()V

    iput-object p1, p0, Landroidx/mediarouter/media/MediaRoute2Provider$GroupRouteController;->mPendingCallbacks:Landroid/util/SparseArray;

    .line 468
    new-instance p1, Ljava/util/concurrent/atomic/AtomicInteger;

    const/4 v0, 0x1

    invoke-direct {p1, v0}, Ljava/util/concurrent/atomic/AtomicInteger;-><init>(I)V

    iput-object p1, p0, Landroidx/mediarouter/media/MediaRoute2Provider$GroupRouteController;->mNextRequestId:Ljava/util/concurrent/atomic/AtomicInteger;

    .line 470
    new-instance p1, Landroidx/mediarouter/media/MediaRoute2Provider$GroupRouteController$$ExternalSyntheticLambda0;

    invoke-direct {p1, p0}, Landroidx/mediarouter/media/MediaRoute2Provider$GroupRouteController$$ExternalSyntheticLambda0;-><init>(Landroidx/mediarouter/media/MediaRoute2Provider$GroupRouteController;)V

    iput-object p1, p0, Landroidx/mediarouter/media/MediaRoute2Provider$GroupRouteController;->mClearOptimisticVolumeRunnable:Ljava/lang/Runnable;

    const/4 p1, -0x1

    .line 472
    iput p1, p0, Landroidx/mediarouter/media/MediaRoute2Provider$GroupRouteController;->mOptimisticVolume:I

    .line 478
    iput-object p2, p0, Landroidx/mediarouter/media/MediaRoute2Provider$GroupRouteController;->mRoutingController:Landroid/media/MediaRouter2$RoutingController;

    .line 479
    iput-object p3, p0, Landroidx/mediarouter/media/MediaRoute2Provider$GroupRouteController;->mInitialMemberRouteId:Ljava/lang/String;

    .line 480
    invoke-static {p2}, Landroidx/mediarouter/media/MediaRoute2Provider;->getMessengerFromRoutingController(Landroid/media/MediaRouter2$RoutingController;)Landroid/os/Messenger;

    move-result-object p1

    iput-object p1, p0, Landroidx/mediarouter/media/MediaRoute2Provider$GroupRouteController;->mServiceMessenger:Landroid/os/Messenger;

    if-nez p1, :cond_0

    const/4 p1, 0x0

    goto :goto_0

    .line 482
    :cond_0
    new-instance p1, Landroid/os/Messenger;

    new-instance p2, Landroidx/mediarouter/media/MediaRoute2Provider$GroupRouteController$ReceiveHandler;

    invoke-direct {p2, p0}, Landroidx/mediarouter/media/MediaRoute2Provider$GroupRouteController$ReceiveHandler;-><init>(Landroidx/mediarouter/media/MediaRoute2Provider$GroupRouteController;)V

    invoke-direct {p1, p2}, Landroid/os/Messenger;-><init>(Landroid/os/Handler;)V

    :goto_0
    iput-object p1, p0, Landroidx/mediarouter/media/MediaRoute2Provider$GroupRouteController;->mReceiveMessenger:Landroid/os/Messenger;

    .line 483
    new-instance p1, Landroid/os/Handler;

    invoke-static {}, Landroid/os/Looper;->getMainLooper()Landroid/os/Looper;

    move-result-object p2

    invoke-direct {p1, p2}, Landroid/os/Handler;-><init>(Landroid/os/Looper;)V

    iput-object p1, p0, Landroidx/mediarouter/media/MediaRoute2Provider$GroupRouteController;->mControllerHandler:Landroid/os/Handler;

    return-void
.end method

.method private synthetic lambda$new$0()V
    .locals 1

    const/4 v0, -0x1

    .line 470
    iput v0, p0, Landroidx/mediarouter/media/MediaRoute2Provider$GroupRouteController;->mOptimisticVolume:I

    return-void
.end method

.method private scheduleClearOptimisticVolume()V
    .locals 3

    .line 598
    iget-object v0, p0, Landroidx/mediarouter/media/MediaRoute2Provider$GroupRouteController;->mControllerHandler:Landroid/os/Handler;

    iget-object v1, p0, Landroidx/mediarouter/media/MediaRoute2Provider$GroupRouteController;->mClearOptimisticVolumeRunnable:Ljava/lang/Runnable;

    invoke-virtual {v0, v1}, Landroid/os/Handler;->removeCallbacks(Ljava/lang/Runnable;)V

    .line 599
    iget-object v0, p0, Landroidx/mediarouter/media/MediaRoute2Provider$GroupRouteController;->mControllerHandler:Landroid/os/Handler;

    iget-object p0, p0, Landroidx/mediarouter/media/MediaRoute2Provider$GroupRouteController;->mClearOptimisticVolumeRunnable:Ljava/lang/Runnable;

    const-wide/16 v1, 0x3e8

    invoke-virtual {v0, p0, v1, v2}, Landroid/os/Handler;->postDelayed(Ljava/lang/Runnable;J)Z

    return-void
.end method


# virtual methods
.method public getGroupRouteId()Ljava/lang/String;
    .locals 1

    .line 487
    iget-object v0, p0, Landroidx/mediarouter/media/MediaRoute2Provider$GroupRouteController;->mGroupRouteDescriptor:Landroidx/mediarouter/media/MediaRouteDescriptor;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Landroidx/mediarouter/media/MediaRouteDescriptor;->getId()Ljava/lang/String;

    move-result-object p0

    goto :goto_0

    .line 488
    :cond_0
    iget-object p0, p0, Landroidx/mediarouter/media/MediaRoute2Provider$GroupRouteController;->mRoutingController:Landroid/media/MediaRouter2$RoutingController;

    invoke-virtual {p0}, Landroid/media/MediaRouter2$RoutingController;->getId()Ljava/lang/String;

    move-result-object p0

    :goto_0
    return-object p0
.end method

.method public onAddMemberRoute(Ljava/lang/String;)V
    .locals 2

    const-string v0, "MR2Provider"

    if-eqz p1, :cond_2

    .line 567
    invoke-virtual {p1}, Ljava/lang/String;->isEmpty()Z

    move-result v1

    if-eqz v1, :cond_0

    goto :goto_0

    .line 572
    :cond_0
    iget-object v1, p0, Landroidx/mediarouter/media/MediaRoute2Provider$GroupRouteController;->this$0:Landroidx/mediarouter/media/MediaRoute2Provider;

    invoke-virtual {v1, p1}, Landroidx/mediarouter/media/MediaRoute2Provider;->getRouteById(Ljava/lang/String;)Landroid/media/MediaRoute2Info;

    move-result-object v1

    if-nez v1, :cond_1

    .line 574
    new-instance p0, Ljava/lang/StringBuilder;

    invoke-direct {p0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "onAddMemberRoute: Specified route not found. routeId="

    invoke-virtual {p0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-static {v0, p0}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    return-void

    .line 578
    :cond_1
    iget-object p0, p0, Landroidx/mediarouter/media/MediaRoute2Provider$GroupRouteController;->mRoutingController:Landroid/media/MediaRouter2$RoutingController;

    invoke-virtual {p0, v1}, Landroid/media/MediaRouter2$RoutingController;->selectRoute(Landroid/media/MediaRoute2Info;)V

    return-void

    :cond_2
    :goto_0
    const-string p0, "onAddMemberRoute: Ignoring null or empty routeId."

    .line 568
    invoke-static {v0, p0}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    return-void
.end method

.method public onRelease()V
    .locals 0

    .line 544
    iget-object p0, p0, Landroidx/mediarouter/media/MediaRoute2Provider$GroupRouteController;->mRoutingController:Landroid/media/MediaRouter2$RoutingController;

    invoke-virtual {p0}, Landroid/media/MediaRouter2$RoutingController;->release()V

    return-void
.end method

.method public onRemoveMemberRoute(Ljava/lang/String;)V
    .locals 2

    const-string v0, "MR2Provider"

    if-eqz p1, :cond_2

    .line 583
    invoke-virtual {p1}, Ljava/lang/String;->isEmpty()Z

    move-result v1

    if-eqz v1, :cond_0

    goto :goto_0

    .line 588
    :cond_0
    iget-object v1, p0, Landroidx/mediarouter/media/MediaRoute2Provider$GroupRouteController;->this$0:Landroidx/mediarouter/media/MediaRoute2Provider;

    invoke-virtual {v1, p1}, Landroidx/mediarouter/media/MediaRoute2Provider;->getRouteById(Ljava/lang/String;)Landroid/media/MediaRoute2Info;

    move-result-object v1

    if-nez v1, :cond_1

    .line 590
    new-instance p0, Ljava/lang/StringBuilder;

    invoke-direct {p0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "onRemoveMemberRoute: Specified route not found. routeId="

    invoke-virtual {p0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-static {v0, p0}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    return-void

    .line 594
    :cond_1
    iget-object p0, p0, Landroidx/mediarouter/media/MediaRoute2Provider$GroupRouteController;->mRoutingController:Landroid/media/MediaRouter2$RoutingController;

    invoke-virtual {p0, v1}, Landroid/media/MediaRouter2$RoutingController;->deselectRoute(Landroid/media/MediaRoute2Info;)V

    return-void

    :cond_2
    :goto_0
    const-string p0, "onRemoveMemberRoute: Ignoring null or empty routeId."

    .line 584
    invoke-static {v0, p0}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    return-void
.end method

.method public onSetVolume(I)V
    .locals 1

    .line 493
    iget-object v0, p0, Landroidx/mediarouter/media/MediaRoute2Provider$GroupRouteController;->mRoutingController:Landroid/media/MediaRouter2$RoutingController;

    if-nez v0, :cond_0

    return-void

    .line 496
    :cond_0
    invoke-virtual {v0, p1}, Landroid/media/MediaRouter2$RoutingController;->setVolume(I)V

    .line 497
    iput p1, p0, Landroidx/mediarouter/media/MediaRoute2Provider$GroupRouteController;->mOptimisticVolume:I

    .line 498
    invoke-direct {p0}, Landroidx/mediarouter/media/MediaRoute2Provider$GroupRouteController;->scheduleClearOptimisticVolume()V

    return-void
.end method

.method public onUpdateMemberRoutes(Ljava/util/List;)V
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Ljava/lang/String;",
            ">;)V"
        }
    .end annotation

    const-string v0, "MR2Provider"

    if-eqz p1, :cond_2

    .line 550
    invoke-interface {p1}, Ljava/util/List;->isEmpty()Z

    move-result v1

    if-eqz v1, :cond_0

    goto :goto_0

    :cond_0
    const/4 v1, 0x0

    .line 555
    invoke-interface {p1, v1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/String;

    .line 556
    iget-object v1, p0, Landroidx/mediarouter/media/MediaRoute2Provider$GroupRouteController;->this$0:Landroidx/mediarouter/media/MediaRoute2Provider;

    invoke-virtual {v1, p1}, Landroidx/mediarouter/media/MediaRoute2Provider;->getRouteById(Ljava/lang/String;)Landroid/media/MediaRoute2Info;

    move-result-object v1

    if-nez v1, :cond_1

    .line 558
    new-instance p0, Ljava/lang/StringBuilder;

    invoke-direct {p0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "onUpdateMemberRoutes: Specified route not found. routeId="

    invoke-virtual {p0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-static {v0, p0}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    return-void

    .line 562
    :cond_1
    iget-object p0, p0, Landroidx/mediarouter/media/MediaRoute2Provider$GroupRouteController;->this$0:Landroidx/mediarouter/media/MediaRoute2Provider;

    iget-object p0, p0, Landroidx/mediarouter/media/MediaRoute2Provider;->mMediaRouter2:Landroid/media/MediaRouter2;

    invoke-virtual {p0, v1}, Landroid/media/MediaRouter2;->transferTo(Landroid/media/MediaRoute2Info;)V

    return-void

    :cond_2
    :goto_0
    const-string p0, "onUpdateMemberRoutes: Ignoring null or empty routeIds."

    .line 551
    invoke-static {v0, p0}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    return-void
.end method

.method public onUpdateVolume(I)V
    .locals 2

    .line 503
    iget-object v0, p0, Landroidx/mediarouter/media/MediaRoute2Provider$GroupRouteController;->mRoutingController:Landroid/media/MediaRouter2$RoutingController;

    if-nez v0, :cond_0

    return-void

    .line 506
    :cond_0
    iget v1, p0, Landroidx/mediarouter/media/MediaRoute2Provider$GroupRouteController;->mOptimisticVolume:I

    if-gez v1, :cond_1

    invoke-virtual {v0}, Landroid/media/MediaRouter2$RoutingController;->getVolume()I

    move-result v1

    :cond_1
    const/4 v0, 0x0

    add-int/2addr v1, p1

    .line 508
    iget-object p1, p0, Landroidx/mediarouter/media/MediaRoute2Provider$GroupRouteController;->mRoutingController:Landroid/media/MediaRouter2$RoutingController;

    .line 509
    invoke-virtual {p1}, Landroid/media/MediaRouter2$RoutingController;->getVolumeMax()I

    move-result p1

    .line 508
    invoke-static {v1, p1}, Ljava/lang/Math;->min(II)I

    move-result p1

    invoke-static {v0, p1}, Ljava/lang/Math;->max(II)I

    move-result p1

    iput p1, p0, Landroidx/mediarouter/media/MediaRoute2Provider$GroupRouteController;->mOptimisticVolume:I

    .line 510
    iget-object v0, p0, Landroidx/mediarouter/media/MediaRoute2Provider$GroupRouteController;->mRoutingController:Landroid/media/MediaRouter2$RoutingController;

    invoke-virtual {v0, p1}, Landroid/media/MediaRouter2$RoutingController;->setVolume(I)V

    .line 511
    invoke-direct {p0}, Landroidx/mediarouter/media/MediaRoute2Provider$GroupRouteController;->scheduleClearOptimisticVolume()V

    return-void
.end method

.method setGroupRouteDescriptor(Landroidx/mediarouter/media/MediaRouteDescriptor;)V
    .locals 0

    .line 656
    iput-object p1, p0, Landroidx/mediarouter/media/MediaRoute2Provider$GroupRouteController;->mGroupRouteDescriptor:Landroidx/mediarouter/media/MediaRouteDescriptor;

    return-void
.end method

.method setMemberRouteVolume(Ljava/lang/String;I)V
    .locals 3

    .line 604
    iget-object v0, p0, Landroidx/mediarouter/media/MediaRoute2Provider$GroupRouteController;->mRoutingController:Landroid/media/MediaRouter2$RoutingController;

    if-eqz v0, :cond_1

    invoke-virtual {v0}, Landroid/media/MediaRouter2$RoutingController;->isReleased()Z

    move-result v0

    if-nez v0, :cond_1

    iget-object v0, p0, Landroidx/mediarouter/media/MediaRoute2Provider$GroupRouteController;->mServiceMessenger:Landroid/os/Messenger;

    if-nez v0, :cond_0

    goto :goto_0

    .line 609
    :cond_0
    iget-object v0, p0, Landroidx/mediarouter/media/MediaRoute2Provider$GroupRouteController;->mNextRequestId:Ljava/util/concurrent/atomic/AtomicInteger;

    invoke-virtual {v0}, Ljava/util/concurrent/atomic/AtomicInteger;->getAndIncrement()I

    move-result v0

    .line 610
    invoke-static {}, Landroid/os/Message;->obtain()Landroid/os/Message;

    move-result-object v1

    const/4 v2, 0x7

    .line 611
    iput v2, v1, Landroid/os/Message;->what:I

    .line 612
    iput v0, v1, Landroid/os/Message;->arg1:I

    .line 614
    new-instance v0, Landroid/os/Bundle;

    invoke-direct {v0}, Landroid/os/Bundle;-><init>()V

    const-string v2, "volume"

    .line 615
    invoke-virtual {v0, v2, p2}, Landroid/os/Bundle;->putInt(Ljava/lang/String;I)V

    const-string p2, "routeId"

    .line 616
    invoke-virtual {v0, p2, p1}, Landroid/os/Bundle;->putString(Ljava/lang/String;Ljava/lang/String;)V

    .line 617
    invoke-virtual {v1, v0}, Landroid/os/Message;->setData(Landroid/os/Bundle;)V

    .line 619
    iget-object p1, p0, Landroidx/mediarouter/media/MediaRoute2Provider$GroupRouteController;->mReceiveMessenger:Landroid/os/Messenger;

    iput-object p1, v1, Landroid/os/Message;->replyTo:Landroid/os/Messenger;

    .line 621
    :try_start_0
    iget-object p0, p0, Landroidx/mediarouter/media/MediaRoute2Provider$GroupRouteController;->mServiceMessenger:Landroid/os/Messenger;

    invoke-virtual {p0, v1}, Landroid/os/Messenger;->send(Landroid/os/Message;)V
    :try_end_0
    .catch Landroid/os/DeadObjectException; {:try_start_0 .. :try_end_0} :catch_1
    .catch Landroid/os/RemoteException; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception p0

    const-string p1, "MR2Provider"

    const-string p2, "Could not send control request to service."

    .line 625
    invoke-static {p1, p2, p0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    :catch_1
    :cond_1
    :goto_0
    return-void
.end method

.method updateMemberRouteVolume(Ljava/lang/String;I)V
    .locals 3

    .line 630
    iget-object v0, p0, Landroidx/mediarouter/media/MediaRoute2Provider$GroupRouteController;->mRoutingController:Landroid/media/MediaRouter2$RoutingController;

    if-eqz v0, :cond_1

    invoke-virtual {v0}, Landroid/media/MediaRouter2$RoutingController;->isReleased()Z

    move-result v0

    if-nez v0, :cond_1

    iget-object v0, p0, Landroidx/mediarouter/media/MediaRoute2Provider$GroupRouteController;->mServiceMessenger:Landroid/os/Messenger;

    if-nez v0, :cond_0

    goto :goto_0

    .line 635
    :cond_0
    iget-object v0, p0, Landroidx/mediarouter/media/MediaRoute2Provider$GroupRouteController;->mNextRequestId:Ljava/util/concurrent/atomic/AtomicInteger;

    invoke-virtual {v0}, Ljava/util/concurrent/atomic/AtomicInteger;->getAndIncrement()I

    move-result v0

    .line 636
    invoke-static {}, Landroid/os/Message;->obtain()Landroid/os/Message;

    move-result-object v1

    const/16 v2, 0x8

    .line 637
    iput v2, v1, Landroid/os/Message;->what:I

    .line 638
    iput v0, v1, Landroid/os/Message;->arg1:I

    .line 640
    new-instance v0, Landroid/os/Bundle;

    invoke-direct {v0}, Landroid/os/Bundle;-><init>()V

    const-string v2, "volume"

    .line 641
    invoke-virtual {v0, v2, p2}, Landroid/os/Bundle;->putInt(Ljava/lang/String;I)V

    const-string p2, "routeId"

    .line 642
    invoke-virtual {v0, p2, p1}, Landroid/os/Bundle;->putString(Ljava/lang/String;Ljava/lang/String;)V

    .line 643
    invoke-virtual {v1, v0}, Landroid/os/Message;->setData(Landroid/os/Bundle;)V

    .line 645
    iget-object p1, p0, Landroidx/mediarouter/media/MediaRoute2Provider$GroupRouteController;->mReceiveMessenger:Landroid/os/Messenger;

    iput-object p1, v1, Landroid/os/Message;->replyTo:Landroid/os/Messenger;

    .line 647
    :try_start_0
    iget-object p0, p0, Landroidx/mediarouter/media/MediaRoute2Provider$GroupRouteController;->mServiceMessenger:Landroid/os/Messenger;

    invoke-virtual {p0, v1}, Landroid/os/Messenger;->send(Landroid/os/Message;)V
    :try_end_0
    .catch Landroid/os/DeadObjectException; {:try_start_0 .. :try_end_0} :catch_1
    .catch Landroid/os/RemoteException; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception p0

    const-string p1, "MR2Provider"

    const-string p2, "Could not send control request to service."

    .line 651
    invoke-static {p1, p2, p0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    :catch_1
    :cond_1
    :goto_0
    return-void
.end method
