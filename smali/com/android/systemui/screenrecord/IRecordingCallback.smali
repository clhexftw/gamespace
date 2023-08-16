.class public interface abstract Lcom/android/systemui/screenrecord/IRecordingCallback;
.super Ljava/lang/Object;
.source "IRecordingCallback.java"

# interfaces
.implements Landroid/os/IInterface;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/android/systemui/screenrecord/IRecordingCallback$Stub;
    }
.end annotation


# virtual methods
.method public abstract onRecordingEnd()V
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Landroid/os/RemoteException;
        }
    .end annotation
.end method

.method public abstract onRecordingStart()V
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Landroid/os/RemoteException;
        }
    .end annotation
.end method
