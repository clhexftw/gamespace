.class public final Lorg/nameless/gamespace/widget/MenuSwitcher$taskFpsCallback$1;
.super Landroid/window/TaskFpsCallback;
.source "MenuSwitcher.kt"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lorg/nameless/gamespace/widget/MenuSwitcher;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lorg/nameless/gamespace/widget/MenuSwitcher;


# direct methods
.method constructor <init>(Lorg/nameless/gamespace/widget/MenuSwitcher;)V
    .locals 0

    iput-object p1, p0, Lorg/nameless/gamespace/widget/MenuSwitcher$taskFpsCallback$1;->this$0:Lorg/nameless/gamespace/widget/MenuSwitcher;

    .line 34
    invoke-direct {p0}, Landroid/window/TaskFpsCallback;-><init>()V

    return-void
.end method


# virtual methods
.method public onFpsReported(F)V
    .locals 1

    .line 36
    iget-object v0, p0, Lorg/nameless/gamespace/widget/MenuSwitcher$taskFpsCallback$1;->this$0:Lorg/nameless/gamespace/widget/MenuSwitcher;

    invoke-virtual {v0}, Landroid/widget/LinearLayout;->isAttachedToWindow()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 37
    iget-object p0, p0, Lorg/nameless/gamespace/widget/MenuSwitcher$taskFpsCallback$1;->this$0:Lorg/nameless/gamespace/widget/MenuSwitcher;

    invoke-static {p0, p1}, Lorg/nameless/gamespace/widget/MenuSwitcher;->access$onFrameUpdated(Lorg/nameless/gamespace/widget/MenuSwitcher;F)Lkotlinx/coroutines/Job;

    :cond_0
    return-void
.end method
