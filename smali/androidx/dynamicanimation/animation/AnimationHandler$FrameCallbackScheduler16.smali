.class final Landroidx/dynamicanimation/animation/AnimationHandler$FrameCallbackScheduler16;
.super Ljava/lang/Object;
.source "AnimationHandler.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Landroidx/dynamicanimation/animation/AnimationHandler;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x18
    name = "FrameCallbackScheduler16"
.end annotation


# instance fields
.field private final mChoreographer:Landroid/view/Choreographer;

.field private final mLooper:Landroid/os/Looper;


# direct methods
.method constructor <init>()V
    .locals 1

    .line 236
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 238
    invoke-static {}, Landroid/view/Choreographer;->getInstance()Landroid/view/Choreographer;

    move-result-object v0

    iput-object v0, p0, Landroidx/dynamicanimation/animation/AnimationHandler$FrameCallbackScheduler16;->mChoreographer:Landroid/view/Choreographer;

    .line 239
    invoke-static {}, Landroid/os/Looper;->myLooper()Landroid/os/Looper;

    move-result-object v0

    iput-object v0, p0, Landroidx/dynamicanimation/animation/AnimationHandler$FrameCallbackScheduler16;->mLooper:Landroid/os/Looper;

    return-void
.end method
