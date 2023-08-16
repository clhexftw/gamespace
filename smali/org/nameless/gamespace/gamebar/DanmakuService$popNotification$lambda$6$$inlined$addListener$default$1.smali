.class public final Lorg/nameless/gamespace/gamebar/DanmakuService$popNotification$lambda$6$$inlined$addListener$default$1;
.super Ljava/lang/Object;
.source "Animator.kt"

# interfaces
.implements Landroid/animation/Animator$AnimatorListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lorg/nameless/gamespace/gamebar/DanmakuService;->popNotification()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = null
.end annotation

.annotation system Ldalvik/annotation/SourceDebugExtension;
    value = "SMAP\nAnimator.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Animator.kt\nandroidx/core/animation/AnimatorKt$addListener$listener$1\n+ 2 Animator.kt\nandroidx/core/animation/AnimatorKt$addListener$4\n+ 3 DanmakuService.kt\norg/nameless/gamespace/gamebar/DanmakuService\n+ 4 Animator.kt\nandroidx/core/animation/AnimatorKt$addListener$3\n+ 5 Animator.kt\nandroidx/core/animation/AnimatorKt$addListener$2\n*L\n1#1,127:1\n98#2:128\n201#3,8:129\n97#4:137\n96#5:138\n*E\n"
.end annotation


# instance fields
.field final synthetic this$0:Lorg/nameless/gamespace/gamebar/DanmakuService;


# direct methods
.method public constructor <init>(Lorg/nameless/gamespace/gamebar/DanmakuService;)V
    .locals 0

    iput-object p1, p0, Lorg/nameless/gamespace/gamebar/DanmakuService$popNotification$lambda$6$$inlined$addListener$default$1;->this$0:Lorg/nameless/gamespace/gamebar/DanmakuService;

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
    .locals 1

    const-string v0, "animator"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 129
    iget-object p1, p0, Lorg/nameless/gamespace/gamebar/DanmakuService$popNotification$lambda$6$$inlined$addListener$default$1;->this$0:Lorg/nameless/gamespace/gamebar/DanmakuService;

    invoke-static {p1}, Lorg/nameless/gamespace/gamebar/DanmakuService;->access$getNotificationStack$p(Lorg/nameless/gamespace/gamebar/DanmakuService;)Ljava/util/LinkedList;

    move-result-object p1

    invoke-virtual {p1}, Ljava/util/LinkedList;->isEmpty()Z

    move-result p1

    if-eqz p1, :cond_0

    .line 130
    iget-object p0, p0, Lorg/nameless/gamespace/gamebar/DanmakuService$popNotification$lambda$6$$inlined$addListener$default$1;->this$0:Lorg/nameless/gamespace/gamebar/DanmakuService;

    invoke-static {p0}, Lorg/nameless/gamespace/gamebar/DanmakuService;->access$removeViewSafely(Lorg/nameless/gamespace/gamebar/DanmakuService;)V

    goto :goto_0

    .line 132
    :cond_0
    iget-object p1, p0, Lorg/nameless/gamespace/gamebar/DanmakuService$popNotification$lambda$6$$inlined$addListener$default$1;->this$0:Lorg/nameless/gamespace/gamebar/DanmakuService;

    invoke-static {p1}, Lorg/nameless/gamespace/gamebar/DanmakuService;->access$getNotificationOverlay$p(Lorg/nameless/gamespace/gamebar/DanmakuService;)Landroid/widget/TextView;

    move-result-object p1

    const/4 v0, 0x0

    invoke-virtual {p1, v0}, Landroid/widget/TextView;->setAlpha(F)V

    .line 133
    iget-object p1, p0, Lorg/nameless/gamespace/gamebar/DanmakuService$popNotification$lambda$6$$inlined$addListener$default$1;->this$0:Lorg/nameless/gamespace/gamebar/DanmakuService;

    invoke-static {p1}, Lorg/nameless/gamespace/gamebar/DanmakuService;->access$getNotificationOverlay$p(Lorg/nameless/gamespace/gamebar/DanmakuService;)Landroid/widget/TextView;

    move-result-object p1

    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/DanmakuService$popNotification$lambda$6$$inlined$addListener$default$1;->this$0:Lorg/nameless/gamespace/gamebar/DanmakuService;

    invoke-static {v0}, Lorg/nameless/gamespace/gamebar/DanmakuService;->access$getNotificationStack$p(Lorg/nameless/gamespace/gamebar/DanmakuService;)Ljava/util/LinkedList;

    move-result-object v0

    invoke-virtual {v0}, Ljava/util/LinkedList;->pop()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/CharSequence;

    invoke-virtual {p1, v0}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 134
    iget-object p0, p0, Lorg/nameless/gamespace/gamebar/DanmakuService$popNotification$lambda$6$$inlined$addListener$default$1;->this$0:Lorg/nameless/gamespace/gamebar/DanmakuService;

    invoke-static {p0}, Lorg/nameless/gamespace/gamebar/DanmakuService;->access$pushNotification(Lorg/nameless/gamespace/gamebar/DanmakuService;)V

    :goto_0
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
