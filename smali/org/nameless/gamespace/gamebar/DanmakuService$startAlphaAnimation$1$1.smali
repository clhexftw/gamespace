.class final Lorg/nameless/gamespace/gamebar/DanmakuService$startAlphaAnimation$1$1;
.super Ljava/lang/Object;
.source "DanmakuService.kt"

# interfaces
.implements Landroid/animation/ValueAnimator$AnimatorUpdateListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lorg/nameless/gamespace/gamebar/DanmakuService;->startAlphaAnimation(J[F)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x18
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lorg/nameless/gamespace/gamebar/DanmakuService;


# direct methods
.method constructor <init>(Lorg/nameless/gamespace/gamebar/DanmakuService;)V
    .locals 0

    iput-object p1, p0, Lorg/nameless/gamespace/gamebar/DanmakuService$startAlphaAnimation$1$1;->this$0:Lorg/nameless/gamespace/gamebar/DanmakuService;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public final onAnimationUpdate(Landroid/animation/ValueAnimator;)V
    .locals 1

    .line 229
    iget-object p0, p0, Lorg/nameless/gamespace/gamebar/DanmakuService$startAlphaAnimation$1$1;->this$0:Lorg/nameless/gamespace/gamebar/DanmakuService;

    invoke-static {p0}, Lorg/nameless/gamespace/gamebar/DanmakuService;->access$getNotificationOverlay$p(Lorg/nameless/gamespace/gamebar/DanmakuService;)Landroid/widget/TextView;

    move-result-object p0

    invoke-virtual {p1}, Landroid/animation/ValueAnimator;->getAnimatedValue()Ljava/lang/Object;

    move-result-object p1

    const-string v0, "null cannot be cast to non-null type kotlin.Float"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNull(Ljava/lang/Object;Ljava/lang/String;)V

    check-cast p1, Ljava/lang/Float;

    invoke-virtual {p1}, Ljava/lang/Float;->floatValue()F

    move-result p1

    invoke-virtual {p0, p1}, Landroid/widget/TextView;->setAlpha(F)V

    return-void
.end method
