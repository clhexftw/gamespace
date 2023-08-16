.class final Lorg/nameless/gamespace/gamebar/DanmakuService$getPositionAnimator$1$1;
.super Ljava/lang/Object;
.source "DanmakuService.kt"

# interfaces
.implements Landroid/animation/ValueAnimator$AnimatorUpdateListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lorg/nameless/gamespace/gamebar/DanmakuService;->getPositionAnimator(J[F)Landroid/animation/ValueAnimator;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x18
    name = null
.end annotation


# instance fields
.field final synthetic $lpCopy:Landroid/view/WindowManager$LayoutParams;

.field final synthetic this$0:Lorg/nameless/gamespace/gamebar/DanmakuService;


# direct methods
.method constructor <init>(Landroid/view/WindowManager$LayoutParams;Lorg/nameless/gamespace/gamebar/DanmakuService;)V
    .locals 0

    iput-object p1, p0, Lorg/nameless/gamespace/gamebar/DanmakuService$getPositionAnimator$1$1;->$lpCopy:Landroid/view/WindowManager$LayoutParams;

    iput-object p2, p0, Lorg/nameless/gamespace/gamebar/DanmakuService$getPositionAnimator$1$1;->this$0:Lorg/nameless/gamespace/gamebar/DanmakuService;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public final onAnimationUpdate(Landroid/animation/ValueAnimator;)V
    .locals 2

    .line 219
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/DanmakuService$getPositionAnimator$1$1;->$lpCopy:Landroid/view/WindowManager$LayoutParams;

    invoke-virtual {p1}, Landroid/animation/ValueAnimator;->getAnimatedValue()Ljava/lang/Object;

    move-result-object p1

    const-string v1, "null cannot be cast to non-null type kotlin.Float"

    invoke-static {p1, v1}, Lkotlin/jvm/internal/Intrinsics;->checkNotNull(Ljava/lang/Object;Ljava/lang/String;)V

    check-cast p1, Ljava/lang/Float;

    invoke-virtual {p1}, Ljava/lang/Float;->floatValue()F

    move-result p1

    float-to-int p1, p1

    iput p1, v0, Landroid/view/WindowManager$LayoutParams;->y:I

    .line 220
    iget-object p1, p0, Lorg/nameless/gamespace/gamebar/DanmakuService$getPositionAnimator$1$1;->this$0:Lorg/nameless/gamespace/gamebar/DanmakuService;

    iget-object p0, p0, Lorg/nameless/gamespace/gamebar/DanmakuService$getPositionAnimator$1$1;->$lpCopy:Landroid/view/WindowManager$LayoutParams;

    invoke-static {p1, p0}, Lorg/nameless/gamespace/gamebar/DanmakuService;->access$updateViewLayoutSafely(Lorg/nameless/gamespace/gamebar/DanmakuService;Landroid/view/WindowManager$LayoutParams;)V

    return-void
.end method
