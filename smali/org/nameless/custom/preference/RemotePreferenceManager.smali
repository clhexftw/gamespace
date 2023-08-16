.class public Lorg/nameless/custom/preference/RemotePreferenceManager;
.super Ljava/lang/Object;
.source "RemotePreferenceManager.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lorg/nameless/custom/preference/RemotePreferenceManager$OnRemoteUpdateListener;
    }
.end annotation


# static fields
.field private static final DEBUG:Z

.field private static final TAG:Ljava/lang/String; = "RemotePreferenceManager"

.field private static sInstance:Lorg/nameless/custom/preference/RemotePreferenceManager;


# instance fields
.field private final mCache:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Landroid/content/Intent;",
            ">;"
        }
    .end annotation
.end field

.field private final mCallbacks:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/util/Set<",
            "Lorg/nameless/custom/preference/RemotePreferenceManager$OnRemoteUpdateListener;",
            ">;>;"
        }
    .end annotation
.end field

.field private final mContext:Landroid/content/Context;

.field private mHandler:Landroid/os/Handler;

.field private final mListener:Landroid/content/BroadcastReceiver;

.field private final mMainHandler:Landroid/os/Handler;

.field private mThread:Landroid/os/HandlerThread;


# direct methods
.method static bridge synthetic -$$Nest$fgetmCallbacks(Lorg/nameless/custom/preference/RemotePreferenceManager;)Ljava/util/Map;
    .locals 0

    iget-object p0, p0, Lorg/nameless/custom/preference/RemotePreferenceManager;->mCallbacks:Ljava/util/Map;

    return-object p0
.end method

.method static bridge synthetic -$$Nest$fgetmMainHandler(Lorg/nameless/custom/preference/RemotePreferenceManager;)Landroid/os/Handler;
    .locals 0

    iget-object p0, p0, Lorg/nameless/custom/preference/RemotePreferenceManager;->mMainHandler:Landroid/os/Handler;

    return-object p0
.end method

.method static bridge synthetic -$$Nest$mrequestUpdate(Lorg/nameless/custom/preference/RemotePreferenceManager;Ljava/lang/String;)V
    .locals 0

    invoke-direct {p0, p1}, Lorg/nameless/custom/preference/RemotePreferenceManager;->requestUpdate(Ljava/lang/String;)V

    return-void
.end method

.method static bridge synthetic -$$Nest$sfgetDEBUG()Z
    .locals 1

    sget-boolean v0, Lorg/nameless/custom/preference/RemotePreferenceManager;->DEBUG:Z

    return v0
.end method

.method static bridge synthetic -$$Nest$sfgetTAG()Ljava/lang/String;
    .locals 1

    sget-object v0, Lorg/nameless/custom/preference/RemotePreferenceManager;->TAG:Ljava/lang/String;

    return-object v0
.end method

.method static constructor <clinit>()V
    .locals 2

    .line 50
    const-class v0, Lorg/nameless/custom/preference/RemotePreference;

    .line 51
    invoke-virtual {v0}, Ljava/lang/Class;->getSimpleName()Ljava/lang/String;

    move-result-object v0

    const/4 v1, 0x2

    .line 50
    invoke-static {v0, v1}, Landroid/util/Log;->isLoggable(Ljava/lang/String;I)Z

    move-result v0

    sput-boolean v0, Lorg/nameless/custom/preference/RemotePreferenceManager;->DEBUG:Z

    return-void
.end method

.method private constructor <init>(Landroid/content/Context;)V
    .locals 2

    .line 70
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 56
    new-instance v0, Landroid/util/ArrayMap;

    invoke-direct {v0}, Landroid/util/ArrayMap;-><init>()V

    iput-object v0, p0, Lorg/nameless/custom/preference/RemotePreferenceManager;->mCache:Ljava/util/Map;

    .line 57
    new-instance v0, Landroid/util/ArrayMap;

    invoke-direct {v0}, Landroid/util/ArrayMap;-><init>()V

    iput-object v0, p0, Lorg/nameless/custom/preference/RemotePreferenceManager;->mCallbacks:Ljava/util/Map;

    .line 59
    new-instance v0, Landroid/os/Handler;

    invoke-static {}, Landroid/os/Looper;->getMainLooper()Landroid/os/Looper;

    move-result-object v1

    invoke-direct {v0, v1}, Landroid/os/Handler;-><init>(Landroid/os/Looper;)V

    iput-object v0, p0, Lorg/nameless/custom/preference/RemotePreferenceManager;->mMainHandler:Landroid/os/Handler;

    .line 138
    new-instance v0, Lorg/nameless/custom/preference/RemotePreferenceManager$1;

    invoke-direct {v0, p0}, Lorg/nameless/custom/preference/RemotePreferenceManager$1;-><init>(Lorg/nameless/custom/preference/RemotePreferenceManager;)V

    iput-object v0, p0, Lorg/nameless/custom/preference/RemotePreferenceManager;->mListener:Landroid/content/BroadcastReceiver;

    .line 71
    iput-object p1, p0, Lorg/nameless/custom/preference/RemotePreferenceManager;->mContext:Landroid/content/Context;

    return-void
.end method

.method public static declared-synchronized get(Landroid/content/Context;)Lorg/nameless/custom/preference/RemotePreferenceManager;
    .locals 2

    const-class v0, Lorg/nameless/custom/preference/RemotePreferenceManager;

    monitor-enter v0

    .line 75
    :try_start_0
    sget-object v1, Lorg/nameless/custom/preference/RemotePreferenceManager;->sInstance:Lorg/nameless/custom/preference/RemotePreferenceManager;

    if-nez v1, :cond_0

    .line 76
    new-instance v1, Lorg/nameless/custom/preference/RemotePreferenceManager;

    invoke-virtual {p0}, Landroid/content/Context;->getApplicationContext()Landroid/content/Context;

    move-result-object p0

    invoke-direct {v1, p0}, Lorg/nameless/custom/preference/RemotePreferenceManager;-><init>(Landroid/content/Context;)V

    sput-object v1, Lorg/nameless/custom/preference/RemotePreferenceManager;->sInstance:Lorg/nameless/custom/preference/RemotePreferenceManager;

    .line 78
    :cond_0
    sget-object p0, Lorg/nameless/custom/preference/RemotePreferenceManager;->sInstance:Lorg/nameless/custom/preference/RemotePreferenceManager;
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    monitor-exit v0

    return-object p0

    :catchall_0
    move-exception p0

    monitor-exit v0

    throw p0
.end method

.method private requestUpdate(Ljava/lang/String;)V
    .locals 10

    .line 127
    iget-object v0, p0, Lorg/nameless/custom/preference/RemotePreferenceManager;->mCache:Ljava/util/Map;

    monitor-enter v0

    .line 128
    :try_start_0
    iget-object v1, p0, Lorg/nameless/custom/preference/RemotePreferenceManager;->mCache:Ljava/util/Map;

    invoke-interface {v1, p1}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p1

    move-object v2, p1

    check-cast v2, Landroid/content/Intent;

    if-nez v2, :cond_0

    .line 130
    monitor-exit v0

    return-void

    .line 132
    :cond_0
    iget-object v1, p0, Lorg/nameless/custom/preference/RemotePreferenceManager;->mContext:Landroid/content/Context;

    sget-object v3, Landroid/os/UserHandle;->CURRENT:Landroid/os/UserHandle;

    const-string v4, "lineageos.permission.MANAGE_REMOTE_PREFERENCES"

    iget-object v5, p0, Lorg/nameless/custom/preference/RemotePreferenceManager;->mListener:Landroid/content/BroadcastReceiver;

    iget-object v6, p0, Lorg/nameless/custom/preference/RemotePreferenceManager;->mHandler:Landroid/os/Handler;

    const/4 v7, -0x1

    const/4 v8, 0x0

    const/4 v9, 0x0

    invoke-virtual/range {v1 .. v9}, Landroid/content/Context;->sendOrderedBroadcastAsUser(Landroid/content/Intent;Landroid/os/UserHandle;Ljava/lang/String;Landroid/content/BroadcastReceiver;Landroid/os/Handler;ILjava/lang/String;Landroid/os/Bundle;)V

    .line 135
    monitor-exit v0

    return-void

    :catchall_0
    move-exception p0

    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw p0
.end method


# virtual methods
.method public attach(Ljava/lang/String;Lorg/nameless/custom/preference/RemotePreferenceManager$OnRemoteUpdateListener;)V
    .locals 7

    .line 83
    iget-object v0, p0, Lorg/nameless/custom/preference/RemotePreferenceManager;->mCache:Ljava/util/Map;

    monitor-enter v0

    .line 84
    :try_start_0
    iget-object v1, p0, Lorg/nameless/custom/preference/RemotePreferenceManager;->mCache:Ljava/util/Map;

    invoke-interface {v1, p1}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Landroid/content/Intent;

    if-nez v1, :cond_0

    .line 85
    iget-object v2, p0, Lorg/nameless/custom/preference/RemotePreferenceManager;->mCache:Ljava/util/Map;

    invoke-interface {v2, p1}, Ljava/util/Map;->containsKey(Ljava/lang/Object;)Z

    move-result v2

    if-nez v2, :cond_0

    .line 86
    invoke-interface {p2}, Lorg/nameless/custom/preference/RemotePreferenceManager$OnRemoteUpdateListener;->getReceiverIntent()Landroid/content/Intent;

    move-result-object v1

    .line 87
    iget-object v2, p0, Lorg/nameless/custom/preference/RemotePreferenceManager;->mCache:Ljava/util/Map;

    invoke-interface {v2, p1, v1}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 89
    :cond_0
    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_1

    .line 90
    iget-object v2, p0, Lorg/nameless/custom/preference/RemotePreferenceManager;->mCallbacks:Ljava/util/Map;

    monitor-enter v2

    if-eqz v1, :cond_2

    .line 92
    :try_start_1
    iget-object v0, p0, Lorg/nameless/custom/preference/RemotePreferenceManager;->mCallbacks:Ljava/util/Map;

    invoke-interface {v0, p1}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/util/Set;

    if-nez v0, :cond_1

    .line 94
    new-instance v0, Ljava/util/HashSet;

    invoke-direct {v0}, Ljava/util/HashSet;-><init>()V

    .line 95
    iget-object v1, p0, Lorg/nameless/custom/preference/RemotePreferenceManager;->mCallbacks:Ljava/util/Map;

    invoke-interface {v1, p1, v0}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 96
    iget-object v1, p0, Lorg/nameless/custom/preference/RemotePreferenceManager;->mCallbacks:Ljava/util/Map;

    invoke-interface {v1}, Ljava/util/Map;->size()I

    move-result v1

    const/4 v3, 0x1

    if-ne v1, v3, :cond_1

    .line 97
    new-instance v1, Landroid/os/HandlerThread;

    const-string v3, "RemotePreference"

    invoke-direct {v1, v3}, Landroid/os/HandlerThread;-><init>(Ljava/lang/String;)V

    iput-object v1, p0, Lorg/nameless/custom/preference/RemotePreferenceManager;->mThread:Landroid/os/HandlerThread;

    .line 98
    invoke-virtual {v1}, Landroid/os/HandlerThread;->start()V

    .line 99
    new-instance v1, Landroid/os/Handler;

    iget-object v3, p0, Lorg/nameless/custom/preference/RemotePreferenceManager;->mThread:Landroid/os/HandlerThread;

    invoke-virtual {v3}, Landroid/os/HandlerThread;->getLooper()Landroid/os/Looper;

    move-result-object v3

    invoke-direct {v1, v3}, Landroid/os/Handler;-><init>(Landroid/os/Looper;)V

    iput-object v1, p0, Lorg/nameless/custom/preference/RemotePreferenceManager;->mHandler:Landroid/os/Handler;

    .line 100
    iget-object v1, p0, Lorg/nameless/custom/preference/RemotePreferenceManager;->mContext:Landroid/content/Context;

    iget-object v3, p0, Lorg/nameless/custom/preference/RemotePreferenceManager;->mListener:Landroid/content/BroadcastReceiver;

    new-instance v4, Landroid/content/IntentFilter;

    const-string v5, "lineageos.intent.action.REFRESH_PREFERENCE"

    invoke-direct {v4, v5}, Landroid/content/IntentFilter;-><init>(Ljava/lang/String;)V

    const-string v5, "lineageos.permission.MANAGE_REMOTE_PREFERENCES"

    iget-object v6, p0, Lorg/nameless/custom/preference/RemotePreferenceManager;->mHandler:Landroid/os/Handler;

    invoke-virtual {v1, v3, v4, v5, v6}, Landroid/content/Context;->registerReceiver(Landroid/content/BroadcastReceiver;Landroid/content/IntentFilter;Ljava/lang/String;Landroid/os/Handler;)Landroid/content/Intent;

    .line 105
    :cond_1
    invoke-interface {v0, p2}, Ljava/util/Set;->add(Ljava/lang/Object;)Z

    .line 106
    invoke-direct {p0, p1}, Lorg/nameless/custom/preference/RemotePreferenceManager;->requestUpdate(Ljava/lang/String;)V

    .line 108
    :cond_2
    monitor-exit v2

    return-void

    :catchall_0
    move-exception p0

    monitor-exit v2
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw p0

    :catchall_1
    move-exception p0

    .line 89
    :try_start_2
    monitor-exit v0
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_1

    throw p0
.end method

.method public detach(Ljava/lang/String;Lorg/nameless/custom/preference/RemotePreferenceManager$OnRemoteUpdateListener;)V
    .locals 2

    .line 112
    iget-object v0, p0, Lorg/nameless/custom/preference/RemotePreferenceManager;->mCallbacks:Ljava/util/Map;

    monitor-enter v0

    .line 113
    :try_start_0
    iget-object v1, p0, Lorg/nameless/custom/preference/RemotePreferenceManager;->mCallbacks:Ljava/util/Map;

    invoke-interface {v1, p1}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/util/Set;

    if-eqz v1, :cond_1

    .line 114
    invoke-interface {v1, p2}, Ljava/util/Set;->remove(Ljava/lang/Object;)Z

    move-result p2

    if-eqz p2, :cond_1

    invoke-interface {v1}, Ljava/util/Set;->isEmpty()Z

    move-result p2

    if-eqz p2, :cond_1

    iget-object p2, p0, Lorg/nameless/custom/preference/RemotePreferenceManager;->mCallbacks:Ljava/util/Map;

    .line 115
    invoke-interface {p2, p1}, Ljava/util/Map;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p1

    if-eqz p1, :cond_1

    iget-object p1, p0, Lorg/nameless/custom/preference/RemotePreferenceManager;->mCallbacks:Ljava/util/Map;

    invoke-interface {p1}, Ljava/util/Map;->isEmpty()Z

    move-result p1

    if-eqz p1, :cond_1

    .line 116
    iget-object p1, p0, Lorg/nameless/custom/preference/RemotePreferenceManager;->mContext:Landroid/content/Context;

    iget-object p2, p0, Lorg/nameless/custom/preference/RemotePreferenceManager;->mListener:Landroid/content/BroadcastReceiver;

    invoke-virtual {p1, p2}, Landroid/content/Context;->unregisterReceiver(Landroid/content/BroadcastReceiver;)V

    .line 117
    iget-object p1, p0, Lorg/nameless/custom/preference/RemotePreferenceManager;->mThread:Landroid/os/HandlerThread;

    const/4 p2, 0x0

    if-eqz p1, :cond_0

    .line 118
    invoke-virtual {p1}, Landroid/os/HandlerThread;->quit()Z

    .line 119
    iput-object p2, p0, Lorg/nameless/custom/preference/RemotePreferenceManager;->mThread:Landroid/os/HandlerThread;

    .line 121
    :cond_0
    iput-object p2, p0, Lorg/nameless/custom/preference/RemotePreferenceManager;->mHandler:Landroid/os/Handler;

    .line 123
    :cond_1
    monitor-exit v0

    return-void

    :catchall_0
    move-exception p0

    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw p0
.end method
