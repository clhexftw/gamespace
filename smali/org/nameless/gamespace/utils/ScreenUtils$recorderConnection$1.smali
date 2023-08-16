.class public final Lorg/nameless/gamespace/utils/ScreenUtils$recorderConnection$1;
.super Ljava/lang/Object;
.source "ScreenUtils.kt"

# interfaces
.implements Landroid/content/ServiceConnection;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lorg/nameless/gamespace/utils/ScreenUtils;-><init>(Landroid/content/Context;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lorg/nameless/gamespace/utils/ScreenUtils;


# direct methods
.method constructor <init>(Lorg/nameless/gamespace/utils/ScreenUtils;)V
    .locals 0

    iput-object p1, p0, Lorg/nameless/gamespace/utils/ScreenUtils$recorderConnection$1;->this$0:Lorg/nameless/gamespace/utils/ScreenUtils;

    .line 46
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onServiceConnected(Landroid/content/ComponentName;Landroid/os/IBinder;)V
    .locals 0

    .line 49
    :try_start_0
    iget-object p0, p0, Lorg/nameless/gamespace/utils/ScreenUtils$recorderConnection$1;->this$0:Lorg/nameless/gamespace/utils/ScreenUtils;

    invoke-static {p2}, Lcom/android/systemui/screenrecord/IRemoteRecording$Stub;->asInterface(Landroid/os/IBinder;)Lcom/android/systemui/screenrecord/IRemoteRecording;

    move-result-object p1

    invoke-static {p0, p1}, Lorg/nameless/gamespace/utils/ScreenUtils;->access$setRemoteRecording$p(Lorg/nameless/gamespace/utils/ScreenUtils;Lcom/android/systemui/screenrecord/IRemoteRecording;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return-void

    :catch_0
    move-exception p0

    .line 51
    invoke-virtual {p0}, Ljava/lang/Exception;->printStackTrace()V

    const/4 p0, 0x1

    .line 52
    invoke-static {p0}, Ljava/lang/System;->exit(I)V

    new-instance p0, Ljava/lang/RuntimeException;

    const-string p1, "System.exit returned normally, while it was supposed to halt JVM."

    invoke-direct {p0, p1}, Ljava/lang/RuntimeException;-><init>(Ljava/lang/String;)V

    throw p0
.end method

.method public onServiceDisconnected(Landroid/content/ComponentName;)V
    .locals 0

    .line 57
    iget-object p0, p0, Lorg/nameless/gamespace/utils/ScreenUtils$recorderConnection$1;->this$0:Lorg/nameless/gamespace/utils/ScreenUtils;

    const/4 p1, 0x0

    invoke-static {p0, p1}, Lorg/nameless/gamespace/utils/ScreenUtils;->access$setRemoteRecording$p(Lorg/nameless/gamespace/utils/ScreenUtils;Lcom/android/systemui/screenrecord/IRemoteRecording;)V

    return-void
.end method
