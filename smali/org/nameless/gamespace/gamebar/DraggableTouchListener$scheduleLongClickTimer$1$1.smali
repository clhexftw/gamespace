.class final Lorg/nameless/gamespace/gamebar/DraggableTouchListener$scheduleLongClickTimer$1$1;
.super Ljava/lang/Object;
.source "DraggableTouchListener.kt"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lorg/nameless/gamespace/gamebar/DraggableTouchListener;->scheduleLongClickTimer()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x18
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lorg/nameless/gamespace/gamebar/DraggableTouchListener;


# direct methods
.method constructor <init>(Lorg/nameless/gamespace/gamebar/DraggableTouchListener;)V
    .locals 0

    iput-object p1, p0, Lorg/nameless/gamespace/gamebar/DraggableTouchListener$scheduleLongClickTimer$1$1;->this$0:Lorg/nameless/gamespace/gamebar/DraggableTouchListener;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public final run()V
    .locals 0

    .line 57
    iget-object p0, p0, Lorg/nameless/gamespace/gamebar/DraggableTouchListener$scheduleLongClickTimer$1$1;->this$0:Lorg/nameless/gamespace/gamebar/DraggableTouchListener;

    invoke-static {p0}, Lorg/nameless/gamespace/gamebar/DraggableTouchListener;->access$getView$p(Lorg/nameless/gamespace/gamebar/DraggableTouchListener;)Landroid/view/View;

    move-result-object p0

    invoke-virtual {p0}, Landroid/view/View;->performLongClick()Z

    return-void
.end method
