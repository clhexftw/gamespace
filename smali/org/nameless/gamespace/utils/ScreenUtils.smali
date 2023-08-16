.class public final Lorg/nameless/gamespace/utils/ScreenUtils;
.super Ljava/lang/Object;
.source "ScreenUtils.kt"


# annotations
.annotation system Ldalvik/annotation/SourceDebugExtension;
    value = "SMAP\nScreenUtils.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ScreenUtils.kt\norg/nameless/gamespace/utils/ScreenUtils\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,122:1\n1#2:123\n*E\n"
.end annotation


# instance fields
.field private final context:Landroid/content/Context;

.field private gestureLockedType:I

.field private isRecorderBound:Z

.field private lockGesture:I

.field private final recorderConnection:Lorg/nameless/gamespace/utils/ScreenUtils$recorderConnection$1;

.field private remoteRecording:Lcom/android/systemui/screenrecord/IRemoteRecording;

.field private stayAwake:Z

.field private wakelock:Landroid/os/PowerManager$WakeLock;

.field private final windowManager:Landroid/view/IWindowManager;


# direct methods
.method public constructor <init>(Landroid/content/Context;)V
    .locals 1

    const-string v0, "context"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 41
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lorg/nameless/gamespace/utils/ScreenUtils;->context:Landroid/content/Context;

    .line 46
    new-instance p1, Lorg/nameless/gamespace/utils/ScreenUtils$recorderConnection$1;

    invoke-direct {p1, p0}, Lorg/nameless/gamespace/utils/ScreenUtils$recorderConnection$1;-><init>(Lorg/nameless/gamespace/utils/ScreenUtils;)V

    iput-object p1, p0, Lorg/nameless/gamespace/utils/ScreenUtils;->recorderConnection:Lorg/nameless/gamespace/utils/ScreenUtils$recorderConnection$1;

    const-string p1, "window"

    .line 65
    invoke-static {p1}, Landroid/os/ServiceManager;->getService(Ljava/lang/String;)Landroid/os/IBinder;

    move-result-object p1

    .line 64
    invoke-static {p1}, Landroid/view/IWindowManager$Stub;->asInterface(Landroid/os/IBinder;)Landroid/view/IWindowManager;

    move-result-object p1

    iput-object p1, p0, Lorg/nameless/gamespace/utils/ScreenUtils;->windowManager:Landroid/view/IWindowManager;

    return-void
.end method

.method public static final synthetic access$setRemoteRecording$p(Lorg/nameless/gamespace/utils/ScreenUtils;Lcom/android/systemui/screenrecord/IRemoteRecording;)V
    .locals 0

    .line 41
    iput-object p1, p0, Lorg/nameless/gamespace/utils/ScreenUtils;->remoteRecording:Lcom/android/systemui/screenrecord/IRemoteRecording;

    return-void
.end method


# virtual methods
.method public final bind()V
    .locals 5

    .line 68
    iget-object v0, p0, Lorg/nameless/gamespace/utils/ScreenUtils;->context:Landroid/content/Context;

    new-instance v1, Landroid/content/Intent;

    invoke-direct {v1}, Landroid/content/Intent;-><init>()V

    .line 69
    new-instance v2, Landroid/content/ComponentName;

    const-string v3, "com.android.systemui"

    const-string v4, "com.android.systemui.screenrecord.RecordingService"

    invoke-direct {v2, v3, v4}, Landroid/content/ComponentName;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Landroid/content/Intent;->setComponent(Landroid/content/ComponentName;)Landroid/content/Intent;

    .line 73
    sget-object v2, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    iget-object v2, p0, Lorg/nameless/gamespace/utils/ScreenUtils;->recorderConnection:Lorg/nameless/gamespace/utils/ScreenUtils$recorderConnection$1;

    sget-object v3, Landroid/os/UserHandle;->CURRENT:Landroid/os/UserHandle;

    const/4 v4, 0x1

    .line 68
    invoke-virtual {v0, v1, v2, v4, v3}, Landroid/content/Context;->bindServiceAsUser(Landroid/content/Intent;Landroid/content/ServiceConnection;ILandroid/os/UserHandle;)Z

    move-result v0

    iput-boolean v0, p0, Lorg/nameless/gamespace/utils/ScreenUtils;->isRecorderBound:Z

    if-eqz v0, :cond_0

    .line 78
    iget-object v0, p0, Lorg/nameless/gamespace/utils/ScreenUtils;->context:Landroid/content/Context;

    const-string v1, "power"

    invoke-virtual {v0, v1}, Landroid/content/Context;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    const-string v1, "null cannot be cast to non-null type android.os.PowerManager"

    invoke-static {v0, v1}, Lkotlin/jvm/internal/Intrinsics;->checkNotNull(Ljava/lang/Object;Ljava/lang/String;)V

    check-cast v0, Landroid/os/PowerManager;

    const/16 v1, 0x1a

    const-string v2, "GameSpace:ScreenUtils"

    .line 79
    invoke-virtual {v0, v1, v2}, Landroid/os/PowerManager;->newWakeLock(ILjava/lang/String;)Landroid/os/PowerManager$WakeLock;

    move-result-object v0

    .line 78
    iput-object v0, p0, Lorg/nameless/gamespace/utils/ScreenUtils;->wakelock:Landroid/os/PowerManager$WakeLock;

    return-void

    .line 75
    :cond_0
    invoke-static {v4}, Ljava/lang/System;->exit(I)V

    new-instance p0, Ljava/lang/RuntimeException;

    const-string v0, "System.exit returned normally, while it was supposed to halt JVM."

    invoke-direct {p0, v0}, Ljava/lang/RuntimeException;-><init>(Ljava/lang/String;)V

    throw p0
.end method

.method public final getRecorder()Lcom/android/systemui/screenrecord/IRemoteRecording;
    .locals 0

    .line 61
    iget-object p0, p0, Lorg/nameless/gamespace/utils/ScreenUtils;->remoteRecording:Lcom/android/systemui/screenrecord/IRemoteRecording;

    return-object p0
.end method

.method public final setLockGesture(I)V
    .locals 1

    .line 117
    iget-object v0, p0, Lorg/nameless/gamespace/utils/ScreenUtils;->windowManager:Landroid/view/IWindowManager;

    invoke-interface {v0, p1}, Landroid/view/IWindowManager;->setGesturesLocked(I)V

    .line 118
    iput p1, p0, Lorg/nameless/gamespace/utils/ScreenUtils;->lockGesture:I

    .line 119
    iput p1, p0, Lorg/nameless/gamespace/utils/ScreenUtils;->gestureLockedType:I

    return-void
.end method

.method public final setStayAwake(Z)V
    .locals 1
    .annotation build Landroid/annotation/SuppressLint;
        value = {
            "WakelockTimeout"
        }
    .end annotation

    .line 106
    iput-boolean p1, p0, Lorg/nameless/gamespace/utils/ScreenUtils;->stayAwake:Z

    const/4 v0, 0x0

    if-eqz p1, :cond_1

    .line 108
    iget-object p0, p0, Lorg/nameless/gamespace/utils/ScreenUtils;->wakelock:Landroid/os/PowerManager$WakeLock;

    if-eqz p0, :cond_3

    invoke-virtual {p0}, Landroid/os/PowerManager$WakeLock;->isHeld()Z

    move-result p1

    xor-int/lit8 p1, p1, 0x1

    if-eqz p1, :cond_0

    move-object v0, p0

    :cond_0
    if-eqz v0, :cond_3

    invoke-virtual {v0}, Landroid/os/PowerManager$WakeLock;->acquire()V

    goto :goto_0

    .line 110
    :cond_1
    iget-object p0, p0, Lorg/nameless/gamespace/utils/ScreenUtils;->wakelock:Landroid/os/PowerManager$WakeLock;

    if-eqz p0, :cond_3

    invoke-virtual {p0}, Landroid/os/PowerManager$WakeLock;->isHeld()Z

    move-result p1

    if-eqz p1, :cond_2

    move-object v0, p0

    :cond_2
    if-eqz v0, :cond_3

    invoke-virtual {v0}, Landroid/os/PowerManager$WakeLock;->release()V

    :cond_3
    :goto_0
    return-void
.end method

.method public final takeScreenshot(Lkotlin/jvm/functions/Function1;)V
    .locals 3
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lkotlin/jvm/functions/Function1<",
            "-",
            "Landroid/net/Uri;",
            "Lkotlin/Unit;",
            ">;)V"
        }
    .end annotation

    .line 95
    new-instance v0, Landroid/os/Handler;

    invoke-static {}, Landroid/os/Looper;->getMainLooper()Landroid/os/Looper;

    move-result-object v1

    invoke-direct {v0, v1}, Landroid/os/Handler;-><init>(Landroid/os/Looper;)V

    .line 96
    new-instance v1, Lcom/android/internal/util/ScreenshotHelper;

    iget-object p0, p0, Lorg/nameless/gamespace/utils/ScreenUtils;->context:Landroid/content/Context;

    invoke-direct {v1, p0}, Lcom/android/internal/util/ScreenshotHelper;-><init>(Landroid/content/Context;)V

    new-instance p0, Lorg/nameless/gamespace/utils/ScreenUtils$takeScreenshot$1;

    invoke-direct {p0, v0, p1}, Lorg/nameless/gamespace/utils/ScreenUtils$takeScreenshot$1;-><init>(Landroid/os/Handler;Lkotlin/jvm/functions/Function1;)V

    const/4 p1, 0x1

    const/4 v2, 0x0

    invoke-virtual {v1, p1, v2, v0, p0}, Lcom/android/internal/util/ScreenshotHelper;->takeScreenshot(IILandroid/os/Handler;Ljava/util/function/Consumer;)V

    return-void
.end method

.method public final unbind()V
    .locals 3

    .line 83
    iget-object v0, p0, Lorg/nameless/gamespace/utils/ScreenUtils;->wakelock:Landroid/os/PowerManager$WakeLock;

    const/4 v1, 0x0

    if-eqz v0, :cond_1

    invoke-virtual {v0}, Landroid/os/PowerManager$WakeLock;->isHeld()Z

    move-result v2

    if-eqz v2, :cond_0

    goto :goto_0

    :cond_0
    move-object v0, v1

    :goto_0
    if-eqz v0, :cond_1

    invoke-virtual {v0}, Landroid/os/PowerManager$WakeLock;->release()V

    .line 84
    :cond_1
    iget-boolean v0, p0, Lorg/nameless/gamespace/utils/ScreenUtils;->isRecorderBound:Z

    if-eqz v0, :cond_2

    .line 85
    iget-object v0, p0, Lorg/nameless/gamespace/utils/ScreenUtils;->context:Landroid/content/Context;

    iget-object v2, p0, Lorg/nameless/gamespace/utils/ScreenUtils;->recorderConnection:Lorg/nameless/gamespace/utils/ScreenUtils$recorderConnection$1;

    invoke-virtual {v0, v2}, Landroid/content/Context;->unbindService(Landroid/content/ServiceConnection;)V

    .line 87
    :cond_2
    iput-object v1, p0, Lorg/nameless/gamespace/utils/ScreenUtils;->remoteRecording:Lcom/android/systemui/screenrecord/IRemoteRecording;

    .line 88
    iget v0, p0, Lorg/nameless/gamespace/utils/ScreenUtils;->gestureLockedType:I

    if-eqz v0, :cond_3

    .line 89
    iget-object v0, p0, Lorg/nameless/gamespace/utils/ScreenUtils;->windowManager:Landroid/view/IWindowManager;

    const/4 v1, 0x0

    invoke-interface {v0, v1}, Landroid/view/IWindowManager;->setGesturesLocked(I)V

    .line 90
    iput v1, p0, Lorg/nameless/gamespace/utils/ScreenUtils;->gestureLockedType:I

    :cond_3
    return-void
.end method
