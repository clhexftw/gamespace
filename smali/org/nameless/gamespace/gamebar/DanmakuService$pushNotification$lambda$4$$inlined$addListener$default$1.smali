.class public final Lorg/nameless/gamespace/gamebar/DanmakuService$pushNotification$lambda$4$$inlined$addListener$default$1;
.super Ljava/lang/Object;
.source "Animator.kt"

# interfaces
.implements Landroid/animation/Animator$AnimatorListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lorg/nameless/gamespace/gamebar/DanmakuService;->pushNotification()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = null
.end annotation

.annotation system Ldalvik/annotation/SourceDebugExtension;
    value = "SMAP\nAnimator.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Animator.kt\nandroidx/core/animation/AnimatorKt$addListener$listener$1\n+ 2 Animator.kt\nandroidx/core/animation/AnimatorKt$addListener$4\n+ 3 DanmakuService.kt\norg/nameless/gamespace/gamebar/DanmakuService\n+ 4 Animator.kt\nandroidx/core/animation/AnimatorKt$addListener$3\n+ 5 Animator.kt\nandroidx/core/animation/AnimatorKt$addListener$2\n*L\n1#1,127:1\n98#2:128\n186#3,4:129\n97#4:133\n96#5:134\n*E\n"
.end annotation


# instance fields
.field final synthetic this$0:Lorg/nameless/gamespace/gamebar/DanmakuService;


# direct methods
.method public constructor <init>(Lorg/nameless/gamespace/gamebar/DanmakuService;)V
    .locals 0

    iput-object p1, p0, Lorg/nameless/gamespace/gamebar/DanmakuService$pushNotification$lambda$4$$inlined$addListener$default$1;->this$0:Lorg/nameless/gamespace/gamebar/DanmakuService;

    .line 100
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onAnimationCancel(Landroid/animation/Animator;)V
    .locals 0

    const-string p0, "animator"

    invoke-static {p1, p0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    return-void
.end method

.method public onAnimationEnd(Landroid/animation/Animator;)V
    .locals 3

    const-string v0, "animator"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 129
    iget-object p1, p0, Lorg/nameless/gamespace/gamebar/DanmakuService$pushNotification$lambda$4$$inlined$addListener$default$1;->this$0:Lorg/nameless/gamespace/gamebar/DanmakuService;

    invoke-static {p1}, Lorg/nameless/gamespace/gamebar/DanmakuService;->access$getHandler$p(Lorg/nameless/gamespace/gamebar/DanmakuService;)Landroid/os/Handler;

    move-result-object p1

    new-instance v0, Lorg/nameless/gamespace/gamebar/DanmakuService$pushNotification$1$1$1;

    iget-object p0, p0, Lorg/nameless/gamespace/gamebar/DanmakuService$pushNotification$lambda$4$$inlined$addListener$default$1;->this$0:Lorg/nameless/gamespace/gamebar/DanmakuService;

    invoke-direct {v0, p0}, Lorg/nameless/gamespace/gamebar/DanmakuService$pushNotification$1$1$1;-><init>(Lorg/nameless/gamespace/gamebar/DanmakuService;)V

    const-wide/16 v1, 0x7d0

    invoke-virtual {p1, v0, v1, v2}, Landroid/os/Handler;->postDelayed(Ljava/lang/Runnable;J)Z

    return-void
.end method

.method public onAnimationRepeat(Landroid/animation/Animator;)V
    .locals 0

    const-string p0, "animator"

    invoke-static {p1, p0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    return-void
.end method

.method public onAnimationStart(Landroid/animation/Animator;)V
    .locals 0

    const-string p0, "animator"

    invoke-static {p1, p0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    return-void
.end method
