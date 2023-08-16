.class final Lorg/nameless/gamespace/widget/MenuSwitcher$onFrameUpdated$1;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "MenuSwitcher.kt"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lorg/nameless/gamespace/widget/MenuSwitcher;->onFrameUpdated(F)Lkotlinx/coroutines/Job;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x18
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lkotlin/coroutines/jvm/internal/SuspendLambda;",
        "Lkotlin/jvm/functions/Function2<",
        "Lkotlinx/coroutines/CoroutineScope;",
        "Lkotlin/coroutines/Continuation<",
        "-",
        "Lkotlin/Unit;",
        ">;",
        "Ljava/lang/Object;",
        ">;"
    }
.end annotation

.annotation runtime Lkotlin/coroutines/jvm/internal/DebugMetadata;
    c = "org.nameless.gamespace.widget.MenuSwitcher$onFrameUpdated$1"
    f = "MenuSwitcher.kt"
    l = {}
    m = "invokeSuspend"
.end annotation


# instance fields
.field final synthetic $newValue:F

.field label:I

.field final synthetic this$0:Lorg/nameless/gamespace/widget/MenuSwitcher;


# direct methods
.method constructor <init>(Lorg/nameless/gamespace/widget/MenuSwitcher;FLkotlin/coroutines/Continuation;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lorg/nameless/gamespace/widget/MenuSwitcher;",
            "F",
            "Lkotlin/coroutines/Continuation<",
            "-",
            "Lorg/nameless/gamespace/widget/MenuSwitcher$onFrameUpdated$1;",
            ">;)V"
        }
    .end annotation

    iput-object p1, p0, Lorg/nameless/gamespace/widget/MenuSwitcher$onFrameUpdated$1;->this$0:Lorg/nameless/gamespace/widget/MenuSwitcher;

    iput p2, p0, Lorg/nameless/gamespace/widget/MenuSwitcher$onFrameUpdated$1;->$newValue:F

    const/4 p1, 0x2

    invoke-direct {p0, p1, p3}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/Object;",
            "Lkotlin/coroutines/Continuation<",
            "*>;)",
            "Lkotlin/coroutines/Continuation<",
            "Lkotlin/Unit;",
            ">;"
        }
    .end annotation

    new-instance p1, Lorg/nameless/gamespace/widget/MenuSwitcher$onFrameUpdated$1;

    iget-object v0, p0, Lorg/nameless/gamespace/widget/MenuSwitcher$onFrameUpdated$1;->this$0:Lorg/nameless/gamespace/widget/MenuSwitcher;

    iget p0, p0, Lorg/nameless/gamespace/widget/MenuSwitcher$onFrameUpdated$1;->$newValue:F

    invoke-direct {p1, v0, p0, p2}, Lorg/nameless/gamespace/widget/MenuSwitcher$onFrameUpdated$1;-><init>(Lorg/nameless/gamespace/widget/MenuSwitcher;FLkotlin/coroutines/Continuation;)V

    return-object p1
.end method

.method public bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lkotlinx/coroutines/CoroutineScope;

    check-cast p2, Lkotlin/coroutines/Continuation;

    invoke-virtual {p0, p1, p2}, Lorg/nameless/gamespace/widget/MenuSwitcher$onFrameUpdated$1;->invoke(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invoke(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lkotlinx/coroutines/CoroutineScope;",
            "Lkotlin/coroutines/Continuation<",
            "-",
            "Lkotlin/Unit;",
            ">;)",
            "Ljava/lang/Object;"
        }
    .end annotation

    invoke-virtual {p0, p1, p2}, Lorg/nameless/gamespace/widget/MenuSwitcher$onFrameUpdated$1;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lorg/nameless/gamespace/widget/MenuSwitcher$onFrameUpdated$1;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lorg/nameless/gamespace/widget/MenuSwitcher$onFrameUpdated$1;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 2

    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    .line 70
    iget v0, p0, Lorg/nameless/gamespace/widget/MenuSwitcher$onFrameUpdated$1;->label:I

    if-nez v0, :cond_1

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    .line 71
    new-instance p1, Ljava/text/DecimalFormat;

    const-string v0, "#"

    invoke-direct {p1, v0}, Ljava/text/DecimalFormat;-><init>(Ljava/lang/String;)V

    iget-object v0, p0, Lorg/nameless/gamespace/widget/MenuSwitcher$onFrameUpdated$1;->this$0:Lorg/nameless/gamespace/widget/MenuSwitcher;

    iget p0, p0, Lorg/nameless/gamespace/widget/MenuSwitcher$onFrameUpdated$1;->$newValue:F

    .line 72
    sget-object v1, Ljava/math/RoundingMode;->HALF_EVEN:Ljava/math/RoundingMode;

    invoke-virtual {p1, v1}, Ljava/text/DecimalFormat;->setRoundingMode(Ljava/math/RoundingMode;)V

    .line 73
    invoke-static {v0}, Lorg/nameless/gamespace/widget/MenuSwitcher;->access$getContent(Lorg/nameless/gamespace/widget/MenuSwitcher;)Landroid/widget/TextView;

    move-result-object v0

    if-nez v0, :cond_0

    goto :goto_0

    :cond_0
    invoke-static {p0}, Lkotlin/coroutines/jvm/internal/Boxing;->boxFloat(F)Ljava/lang/Float;

    move-result-object p0

    invoke-virtual {p1, p0}, Ljava/text/DecimalFormat;->format(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    invoke-virtual {v0, p0}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 75
    :goto_0
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0

    .line 70
    :cond_1
    new-instance p0, Ljava/lang/IllegalStateException;

    const-string p1, "call to \'resume\' before \'invoke\' with coroutine"

    invoke-direct {p0, p1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw p0
.end method
