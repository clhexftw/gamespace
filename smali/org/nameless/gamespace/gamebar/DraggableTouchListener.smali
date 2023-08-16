.class public final Lorg/nameless/gamespace/gamebar/DraggableTouchListener;
.super Ljava/lang/Object;
.source "DraggableTouchListener.kt"

# interfaces
.implements Landroid/view/View$OnTouchListener;


# instance fields
.field private final initialPosition:Lkotlin/jvm/functions/Function0;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lkotlin/jvm/functions/Function0<",
            "Landroid/graphics/Point;",
            ">;"
        }
    .end annotation
.end field

.field private initialX:I

.field private initialY:I

.field private final longClickInterval:I

.field private longClickPerformed:Z

.field private moving:Z

.field private final onDragComplete:Lkotlin/jvm/functions/Function0;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lkotlin/jvm/functions/Function0<",
            "Lkotlin/Unit;",
            ">;"
        }
    .end annotation
.end field

.field private pointerStartX:I

.field private pointerStartY:I

.field private final positionListener:Lkotlin/jvm/functions/Function2;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lkotlin/jvm/functions/Function2<",
            "Ljava/lang/Integer;",
            "Ljava/lang/Integer;",
            "Lkotlin/Unit;",
            ">;"
        }
    .end annotation
.end field

.field private timer:Ljava/util/Timer;

.field private final touchSlop:I

.field private final view:Landroid/view/View;


# direct methods
.method public constructor <init>(Landroid/content/Context;Landroid/view/View;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function0;)V
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Landroid/content/Context;",
            "Landroid/view/View;",
            "Lkotlin/jvm/functions/Function0<",
            "+",
            "Landroid/graphics/Point;",
            ">;",
            "Lkotlin/jvm/functions/Function2<",
            "-",
            "Ljava/lang/Integer;",
            "-",
            "Ljava/lang/Integer;",
            "Lkotlin/Unit;",
            ">;",
            "Lkotlin/jvm/functions/Function0<",
            "Lkotlin/Unit;",
            ">;)V"
        }
    .end annotation

    const-string v0, "context"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    const-string v0, "view"

    invoke-static {p2, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    const-string v0, "initialPosition"

    invoke-static {p3, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    const-string v0, "positionListener"

    invoke-static {p4, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    const-string v0, "onDragComplete"

    invoke-static {p5, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 29
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 31
    iput-object p2, p0, Lorg/nameless/gamespace/gamebar/DraggableTouchListener;->view:Landroid/view/View;

    .line 32
    iput-object p3, p0, Lorg/nameless/gamespace/gamebar/DraggableTouchListener;->initialPosition:Lkotlin/jvm/functions/Function0;

    .line 33
    iput-object p4, p0, Lorg/nameless/gamespace/gamebar/DraggableTouchListener;->positionListener:Lkotlin/jvm/functions/Function2;

    .line 34
    iput-object p5, p0, Lorg/nameless/gamespace/gamebar/DraggableTouchListener;->onDragComplete:Lkotlin/jvm/functions/Function0;

    .line 37
    invoke-static {p1}, Landroid/view/ViewConfiguration;->get(Landroid/content/Context;)Landroid/view/ViewConfiguration;

    move-result-object p1

    invoke-virtual {p1}, Landroid/view/ViewConfiguration;->getScaledTouchSlop()I

    move-result p1

    iput p1, p0, Lorg/nameless/gamespace/gamebar/DraggableTouchListener;->touchSlop:I

    .line 38
    invoke-static {}, Landroid/view/ViewConfiguration;->getLongPressTimeout()I

    move-result p1

    iput p1, p0, Lorg/nameless/gamespace/gamebar/DraggableTouchListener;->longClickInterval:I

    .line 48
    invoke-virtual {p2, p0}, Landroid/view/View;->setOnTouchListener(Landroid/view/View$OnTouchListener;)V

    return-void
.end method

.method public static final synthetic access$cancelLongClickTimer(Lorg/nameless/gamespace/gamebar/DraggableTouchListener;)V
    .locals 0

    .line 29
    invoke-direct {p0}, Lorg/nameless/gamespace/gamebar/DraggableTouchListener;->cancelLongClickTimer()V

    return-void
.end method

.method public static final synthetic access$getLongClickPerformed$p(Lorg/nameless/gamespace/gamebar/DraggableTouchListener;)Z
    .locals 0

    .line 29
    iget-boolean p0, p0, Lorg/nameless/gamespace/gamebar/DraggableTouchListener;->longClickPerformed:Z

    return p0
.end method

.method public static final synthetic access$getMoving$p(Lorg/nameless/gamespace/gamebar/DraggableTouchListener;)Z
    .locals 0

    .line 29
    iget-boolean p0, p0, Lorg/nameless/gamespace/gamebar/DraggableTouchListener;->moving:Z

    return p0
.end method

.method public static final synthetic access$getView$p(Lorg/nameless/gamespace/gamebar/DraggableTouchListener;)Landroid/view/View;
    .locals 0

    .line 29
    iget-object p0, p0, Lorg/nameless/gamespace/gamebar/DraggableTouchListener;->view:Landroid/view/View;

    return-object p0
.end method

.method public static final synthetic access$setLongClickPerformed$p(Lorg/nameless/gamespace/gamebar/DraggableTouchListener;Z)V
    .locals 0

    .line 29
    iput-boolean p1, p0, Lorg/nameless/gamespace/gamebar/DraggableTouchListener;->longClickPerformed:Z

    return-void
.end method

.method private final cancelLongClickTimer()V
    .locals 1

    .line 67
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/DraggableTouchListener;->timer:Ljava/util/Timer;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Ljava/util/Timer;->cancel()V

    :cond_0
    const/4 v0, 0x0

    .line 68
    iput-object v0, p0, Lorg/nameless/gamespace/gamebar/DraggableTouchListener;->timer:Ljava/util/Timer;

    return-void
.end method

.method private final scheduleLongClickTimer()V
    .locals 4

    .line 52
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/DraggableTouchListener;->timer:Ljava/util/Timer;

    if-nez v0, :cond_0

    .line 53
    new-instance v0, Ljava/util/Timer;

    invoke-direct {v0}, Ljava/util/Timer;-><init>()V

    iput-object v0, p0, Lorg/nameless/gamespace/gamebar/DraggableTouchListener;->timer:Ljava/util/Timer;

    .line 54
    new-instance v1, Lorg/nameless/gamespace/gamebar/DraggableTouchListener$scheduleLongClickTimer$$inlined$timerTask$1;

    invoke-direct {v1, p0}, Lorg/nameless/gamespace/gamebar/DraggableTouchListener$scheduleLongClickTimer$$inlined$timerTask$1;-><init>(Lorg/nameless/gamespace/gamebar/DraggableTouchListener;)V

    .line 62
    iget p0, p0, Lorg/nameless/gamespace/gamebar/DraggableTouchListener;->longClickInterval:I

    int-to-long v2, p0

    .line 54
    invoke-virtual {v0, v1, v2, v3}, Ljava/util/Timer;->schedule(Ljava/util/TimerTask;J)V

    :cond_0
    return-void
.end method


# virtual methods
.method public onTouch(Landroid/view/View;Landroid/view/MotionEvent;)Z
    .locals 6

    const-string v0, "view"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    const-string v0, "motionEvent"

    invoke-static {p2, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 72
    invoke-virtual {p2}, Landroid/view/MotionEvent;->getAction()I

    move-result v0

    const/4 v1, 0x1

    if-eqz v0, :cond_4

    if-eq v0, v1, :cond_2

    const/4 p1, 0x2

    if-eq v0, p1, :cond_0

    goto/16 :goto_0

    .line 85
    :cond_0
    iget-boolean p1, p0, Lorg/nameless/gamespace/gamebar/DraggableTouchListener;->longClickPerformed:Z

    if-nez p1, :cond_5

    .line 86
    invoke-virtual {p2}, Landroid/view/MotionEvent;->getRawX()F

    move-result p1

    iget v0, p0, Lorg/nameless/gamespace/gamebar/DraggableTouchListener;->pointerStartX:I

    int-to-float v0, v0

    sub-float/2addr p1, v0

    .line 87
    invoke-virtual {p2}, Landroid/view/MotionEvent;->getRawY()F

    move-result p2

    iget v0, p0, Lorg/nameless/gamespace/gamebar/DraggableTouchListener;->pointerStartY:I

    int-to-float v0, v0

    sub-float/2addr p2, v0

    .line 88
    iget-boolean v0, p0, Lorg/nameless/gamespace/gamebar/DraggableTouchListener;->moving:Z

    if-nez v0, :cond_1

    float-to-double v2, p1

    float-to-double v4, p2

    invoke-static {v2, v3, v4, v5}, Ljava/lang/Math;->hypot(DD)D

    move-result-wide v2

    double-to-float v0, v2

    iget v2, p0, Lorg/nameless/gamespace/gamebar/DraggableTouchListener;->touchSlop:I

    int-to-float v2, v2

    cmpl-float v0, v0, v2

    if-lez v0, :cond_5

    .line 89
    :cond_1
    invoke-direct {p0}, Lorg/nameless/gamespace/gamebar/DraggableTouchListener;->cancelLongClickTimer()V

    .line 90
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/DraggableTouchListener;->positionListener:Lkotlin/jvm/functions/Function2;

    iget v2, p0, Lorg/nameless/gamespace/gamebar/DraggableTouchListener;->initialX:I

    float-to-int p1, p1

    add-int/2addr v2, p1

    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    iget v2, p0, Lorg/nameless/gamespace/gamebar/DraggableTouchListener;->initialY:I

    float-to-int p2, p2

    add-int/2addr v2, p2

    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p2

    invoke-interface {v0, p1, p2}, Lkotlin/jvm/functions/Function2;->invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 91
    iput-boolean v1, p0, Lorg/nameless/gamespace/gamebar/DraggableTouchListener;->moving:Z

    goto :goto_0

    .line 96
    :cond_2
    invoke-direct {p0}, Lorg/nameless/gamespace/gamebar/DraggableTouchListener;->cancelLongClickTimer()V

    .line 97
    iget-boolean p2, p0, Lorg/nameless/gamespace/gamebar/DraggableTouchListener;->moving:Z

    if-nez p2, :cond_3

    iget-boolean p2, p0, Lorg/nameless/gamespace/gamebar/DraggableTouchListener;->longClickPerformed:Z

    if-nez p2, :cond_3

    .line 98
    invoke-virtual {p1}, Landroid/view/View;->performClick()Z

    .line 100
    :cond_3
    iget-boolean p1, p0, Lorg/nameless/gamespace/gamebar/DraggableTouchListener;->moving:Z

    if-eqz p1, :cond_5

    iget-boolean p1, p0, Lorg/nameless/gamespace/gamebar/DraggableTouchListener;->longClickPerformed:Z

    if-nez p1, :cond_5

    .line 101
    iget-object p0, p0, Lorg/nameless/gamespace/gamebar/DraggableTouchListener;->onDragComplete:Lkotlin/jvm/functions/Function0;

    invoke-interface {p0}, Lkotlin/jvm/functions/Function0;->invoke()Ljava/lang/Object;

    goto :goto_0

    .line 74
    :cond_4
    invoke-virtual {p2}, Landroid/view/MotionEvent;->getRawX()F

    move-result p1

    float-to-int p1, p1

    iput p1, p0, Lorg/nameless/gamespace/gamebar/DraggableTouchListener;->pointerStartX:I

    .line 75
    invoke-virtual {p2}, Landroid/view/MotionEvent;->getRawY()F

    move-result p1

    float-to-int p1, p1

    iput p1, p0, Lorg/nameless/gamespace/gamebar/DraggableTouchListener;->pointerStartY:I

    .line 76
    iget-object p1, p0, Lorg/nameless/gamespace/gamebar/DraggableTouchListener;->initialPosition:Lkotlin/jvm/functions/Function0;

    invoke-interface {p1}, Lkotlin/jvm/functions/Function0;->invoke()Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Landroid/graphics/Point;

    .line 77
    iget p2, p1, Landroid/graphics/Point;->x:I

    iput p2, p0, Lorg/nameless/gamespace/gamebar/DraggableTouchListener;->initialX:I

    .line 78
    iget p1, p1, Landroid/graphics/Point;->y:I

    iput p1, p0, Lorg/nameless/gamespace/gamebar/DraggableTouchListener;->initialY:I

    const/4 p1, 0x0

    .line 80
    iput-boolean p1, p0, Lorg/nameless/gamespace/gamebar/DraggableTouchListener;->moving:Z

    .line 81
    iput-boolean p1, p0, Lorg/nameless/gamespace/gamebar/DraggableTouchListener;->longClickPerformed:Z

    .line 82
    invoke-direct {p0}, Lorg/nameless/gamespace/gamebar/DraggableTouchListener;->scheduleLongClickTimer()V

    :cond_5
    :goto_0
    return v1
.end method
