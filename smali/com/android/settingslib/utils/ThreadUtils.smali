.class public Lcom/android/settingslib/utils/ThreadUtils;
.super Ljava/lang/Object;
.source "ThreadUtils.java"


# static fields
.field private static volatile sMainThreadHandler:Landroid/os/Handler;

.field private static volatile sThreadExecutor:Ljava/util/concurrent/ExecutorService;


# direct methods
.method private static declared-synchronized getThreadExecutor()Ljava/util/concurrent/ExecutorService;
    .locals 2

    const-class v0, Lcom/android/settingslib/utils/ThreadUtils;

    monitor-enter v0

    .line 95
    :try_start_0
    sget-object v1, Lcom/android/settingslib/utils/ThreadUtils;->sThreadExecutor:Ljava/util/concurrent/ExecutorService;

    if-nez v1, :cond_0

    .line 97
    invoke-static {}, Ljava/lang/Runtime;->getRuntime()Ljava/lang/Runtime;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/Runtime;->availableProcessors()I

    move-result v1

    .line 96
    invoke-static {v1}, Ljava/util/concurrent/Executors;->newFixedThreadPool(I)Ljava/util/concurrent/ExecutorService;

    move-result-object v1

    sput-object v1, Lcom/android/settingslib/utils/ThreadUtils;->sThreadExecutor:Ljava/util/concurrent/ExecutorService;

    .line 99
    :cond_0
    sget-object v1, Lcom/android/settingslib/utils/ThreadUtils;->sThreadExecutor:Ljava/util/concurrent/ExecutorService;
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    monitor-exit v0

    return-object v1

    :catchall_0
    move-exception v1

    monitor-exit v0

    throw v1
.end method

.method public static getUiThreadHandler()Landroid/os/Handler;
    .locals 2

    .line 46
    sget-object v0, Lcom/android/settingslib/utils/ThreadUtils;->sMainThreadHandler:Landroid/os/Handler;

    if-nez v0, :cond_0

    .line 47
    new-instance v0, Landroid/os/Handler;

    invoke-static {}, Landroid/os/Looper;->getMainLooper()Landroid/os/Looper;

    move-result-object v1

    invoke-direct {v0, v1}, Landroid/os/Handler;-><init>(Landroid/os/Looper;)V

    sput-object v0, Lcom/android/settingslib/utils/ThreadUtils;->sMainThreadHandler:Landroid/os/Handler;

    .line 50
    :cond_0
    sget-object v0, Lcom/android/settingslib/utils/ThreadUtils;->sMainThreadHandler:Landroid/os/Handler;

    return-object v0
.end method

.method public static postOnBackgroundThread(Ljava/lang/Runnable;)Ljava/util/concurrent/Future;
    .locals 1

    .line 68
    invoke-static {}, Lcom/android/settingslib/utils/ThreadUtils;->getThreadExecutor()Ljava/util/concurrent/ExecutorService;

    move-result-object v0

    invoke-interface {v0, p0}, Ljava/util/concurrent/ExecutorService;->submit(Ljava/lang/Runnable;)Ljava/util/concurrent/Future;

    move-result-object p0

    return-object p0
.end method

.method public static postOnMainThread(Ljava/lang/Runnable;)V
    .locals 1

    .line 84
    invoke-static {}, Lcom/android/settingslib/utils/ThreadUtils;->getUiThreadHandler()Landroid/os/Handler;

    move-result-object v0

    invoke-virtual {v0, p0}, Landroid/os/Handler;->post(Ljava/lang/Runnable;)Z

    return-void
.end method

.method public static postOnMainThreadDelayed(Ljava/lang/Runnable;J)V
    .locals 1

    .line 91
    invoke-static {}, Lcom/android/settingslib/utils/ThreadUtils;->getUiThreadHandler()Landroid/os/Handler;

    move-result-object v0

    invoke-virtual {v0, p0, p1, p2}, Landroid/os/Handler;->postDelayed(Ljava/lang/Runnable;J)Z

    return-void
.end method