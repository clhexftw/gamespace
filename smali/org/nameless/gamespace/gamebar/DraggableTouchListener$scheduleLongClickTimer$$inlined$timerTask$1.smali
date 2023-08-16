.class public final Lorg/nameless/gamespace/gamebar/DraggableTouchListener$scheduleLongClickTimer$$inlined$timerTask$1;
.super Ljava/util/TimerTask;
.source "Timer.kt"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lorg/nameless/gamespace/gamebar/DraggableTouchListener;->scheduleLongClickTimer()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = null
.end annotation

.annotation system Ldalvik/annotation/SourceDebugExtension;
    value = "SMAP\nTimer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Timer.kt\nkotlin/concurrent/TimersKt$timerTask$1\n+ 2 DraggableTouchListener.kt\norg/nameless/gamespace/gamebar/DraggableTouchListener\n*L\n1#1,148:1\n55#2,8:149\n*E\n"
.end annotation


# instance fields
.field final synthetic this$0:Lorg/nameless/gamespace/gamebar/DraggableTouchListener;


# direct methods
.method public constructor <init>(Lorg/nameless/gamespace/gamebar/DraggableTouchListener;)V
    .locals 0

    iput-object p1, p0, Lorg/nameless/gamespace/gamebar/DraggableTouchListener$scheduleLongClickTimer$$inlined$timerTask$1;->this$0:Lorg/nameless/gamespace/gamebar/DraggableTouchListener;

    .line 146
    invoke-direct {p0}, Ljava/util/TimerTask;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .locals 3

    .line 149
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/DraggableTouchListener$scheduleLongClickTimer$$inlined$timerTask$1;->this$0:Lorg/nameless/gamespace/gamebar/DraggableTouchListener;

    invoke-static {v0}, Lorg/nameless/gamespace/gamebar/DraggableTouchListener;->access$getMoving$p(Lorg/nameless/gamespace/gamebar/DraggableTouchListener;)Z

    move-result v0

    if-nez v0, :cond_0

    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/DraggableTouchListener$scheduleLongClickTimer$$inlined$timerTask$1;->this$0:Lorg/nameless/gamespace/gamebar/DraggableTouchListener;

    invoke-static {v0}, Lorg/nameless/gamespace/gamebar/DraggableTouchListener;->access$getLongClickPerformed$p(Lorg/nameless/gamespace/gamebar/DraggableTouchListener;)Z

    move-result v0

    if-nez v0, :cond_0

    .line 150
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/DraggableTouchListener$scheduleLongClickTimer$$inlined$timerTask$1;->this$0:Lorg/nameless/gamespace/gamebar/DraggableTouchListener;

    invoke-static {v0}, Lorg/nameless/gamespace/gamebar/DraggableTouchListener;->access$getView$p(Lorg/nameless/gamespace/gamebar/DraggableTouchListener;)Landroid/view/View;

    move-result-object v0

    new-instance v1, Lorg/nameless/gamespace/gamebar/DraggableTouchListener$scheduleLongClickTimer$1$1;

    iget-object v2, p0, Lorg/nameless/gamespace/gamebar/DraggableTouchListener$scheduleLongClickTimer$$inlined$timerTask$1;->this$0:Lorg/nameless/gamespace/gamebar/DraggableTouchListener;

    invoke-direct {v1, v2}, Lorg/nameless/gamespace/gamebar/DraggableTouchListener$scheduleLongClickTimer$1$1;-><init>(Lorg/nameless/gamespace/gamebar/DraggableTouchListener;)V

    invoke-virtual {v0, v1}, Landroid/view/View;->post(Ljava/lang/Runnable;)Z

    .line 153
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/DraggableTouchListener$scheduleLongClickTimer$$inlined$timerTask$1;->this$0:Lorg/nameless/gamespace/gamebar/DraggableTouchListener;

    const/4 v1, 0x1

    invoke-static {v0, v1}, Lorg/nameless/gamespace/gamebar/DraggableTouchListener;->access$setLongClickPerformed$p(Lorg/nameless/gamespace/gamebar/DraggableTouchListener;Z)V

    .line 155
    :cond_0
    iget-object p0, p0, Lorg/nameless/gamespace/gamebar/DraggableTouchListener$scheduleLongClickTimer$$inlined$timerTask$1;->this$0:Lorg/nameless/gamespace/gamebar/DraggableTouchListener;

    invoke-static {p0}, Lorg/nameless/gamespace/gamebar/DraggableTouchListener;->access$cancelLongClickTimer(Lorg/nameless/gamespace/gamebar/DraggableTouchListener;)V

    return-void
.end method
