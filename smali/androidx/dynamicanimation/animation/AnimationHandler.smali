.class public Landroidx/dynamicanimation/animation/AnimationHandler;
.super Ljava/lang/Object;
.source "AnimationHandler.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Landroidx/dynamicanimation/animation/AnimationHandler$DurationScaleChangeListener;,
        Landroidx/dynamicanimation/animation/AnimationHandler$DurationScaleChangeListener33;,
        Landroidx/dynamicanimation/animation/AnimationHandler$FrameCallbackScheduler14;,
        Landroidx/dynamicanimation/animation/AnimationHandler$FrameCallbackScheduler16;
    }
.end annotation


# static fields
.field private static final sAnimatorHandler:Ljava/lang/ThreadLocal;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/lang/ThreadLocal<",
            "Landroidx/dynamicanimation/animation/AnimationHandler;",
            ">;"
        }
    .end annotation
.end field


# instance fields
.field public mDurationScale:F

.field public mDurationScaleChangeListener:Landroidx/dynamicanimation/animation/AnimationHandler$DurationScaleChangeListener;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .line 79
    new-instance v0, Ljava/lang/ThreadLocal;

    invoke-direct {v0}, Ljava/lang/ThreadLocal;-><init>()V

    sput-object v0, Landroidx/dynamicanimation/animation/AnimationHandler;->sAnimatorHandler:Ljava/lang/ThreadLocal;

    return-void
.end method


# virtual methods
.method public getDurationScale()F
    .locals 0

    .line 283
    iget p0, p0, Landroidx/dynamicanimation/animation/AnimationHandler;->mDurationScale:F

    return p0
.end method
