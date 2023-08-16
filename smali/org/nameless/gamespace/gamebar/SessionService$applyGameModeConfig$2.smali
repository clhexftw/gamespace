.class final Lorg/nameless/gamespace/gamebar/SessionService$applyGameModeConfig$2;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SessionService.kt"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lorg/nameless/gamespace/gamebar/SessionService;->applyGameModeConfig(Ljava/lang/String;)V
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

.annotation system Ldalvik/annotation/SourceDebugExtension;
    value = "SMAP\nSessionService.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SessionService.kt\norg/nameless/gamespace/gamebar/SessionService$applyGameModeConfig$2\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,209:1\n1#2:210\n*E\n"
.end annotation

.annotation runtime Lkotlin/coroutines/jvm/internal/DebugMetadata;
    c = "org.nameless.gamespace.gamebar.SessionService$applyGameModeConfig$2"
    f = "SessionService.kt"
    l = {}
    m = "invokeSuspend"
.end annotation


# instance fields
.field final synthetic $app:Ljava/lang/String;

.field final synthetic $preferred:I

.field label:I

.field final synthetic this$0:Lorg/nameless/gamespace/gamebar/SessionService;


# direct methods
.method constructor <init>(Lorg/nameless/gamespace/gamebar/SessionService;Ljava/lang/String;ILkotlin/coroutines/Continuation;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lorg/nameless/gamespace/gamebar/SessionService;",
            "Ljava/lang/String;",
            "I",
            "Lkotlin/coroutines/Continuation<",
            "-",
            "Lorg/nameless/gamespace/gamebar/SessionService$applyGameModeConfig$2;",
            ">;)V"
        }
    .end annotation

    iput-object p1, p0, Lorg/nameless/gamespace/gamebar/SessionService$applyGameModeConfig$2;->this$0:Lorg/nameless/gamespace/gamebar/SessionService;

    iput-object p2, p0, Lorg/nameless/gamespace/gamebar/SessionService$applyGameModeConfig$2;->$app:Ljava/lang/String;

    iput p3, p0, Lorg/nameless/gamespace/gamebar/SessionService$applyGameModeConfig$2;->$preferred:I

    const/4 p1, 0x2

    invoke-direct {p0, p1, p4}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 2
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

    new-instance p1, Lorg/nameless/gamespace/gamebar/SessionService$applyGameModeConfig$2;

    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/SessionService$applyGameModeConfig$2;->this$0:Lorg/nameless/gamespace/gamebar/SessionService;

    iget-object v1, p0, Lorg/nameless/gamespace/gamebar/SessionService$applyGameModeConfig$2;->$app:Ljava/lang/String;

    iget p0, p0, Lorg/nameless/gamespace/gamebar/SessionService$applyGameModeConfig$2;->$preferred:I

    invoke-direct {p1, v0, v1, p0, p2}, Lorg/nameless/gamespace/gamebar/SessionService$applyGameModeConfig$2;-><init>(Lorg/nameless/gamespace/gamebar/SessionService;Ljava/lang/String;ILkotlin/coroutines/Continuation;)V

    return-object p1
.end method

.method public bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lkotlinx/coroutines/CoroutineScope;

    check-cast p2, Lkotlin/coroutines/Continuation;

    invoke-virtual {p0, p1, p2}, Lorg/nameless/gamespace/gamebar/SessionService$applyGameModeConfig$2;->invoke(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

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

    invoke-virtual {p0, p1, p2}, Lorg/nameless/gamespace/gamebar/SessionService$applyGameModeConfig$2;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lorg/nameless/gamespace/gamebar/SessionService$applyGameModeConfig$2;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lorg/nameless/gamespace/gamebar/SessionService$applyGameModeConfig$2;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 4

    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    .line 180
    iget v0, p0, Lorg/nameless/gamespace/gamebar/SessionService$applyGameModeConfig$2;->label:I

    if-nez v0, :cond_4

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    .line 181
    iget-object p1, p0, Lorg/nameless/gamespace/gamebar/SessionService$applyGameModeConfig$2;->this$0:Lorg/nameless/gamespace/gamebar/SessionService;

    invoke-static {p1}, Lorg/nameless/gamespace/gamebar/SessionService;->access$getGameManager$p(Lorg/nameless/gamespace/gamebar/SessionService;)Landroid/app/GameManager;

    move-result-object p1

    const-string v0, "gameManager"

    const/4 v1, 0x0

    if-nez p1, :cond_0

    invoke-static {v0}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    move-object p1, v1

    :cond_0
    iget-object v2, p0, Lorg/nameless/gamespace/gamebar/SessionService$applyGameModeConfig$2;->$app:Ljava/lang/String;

    invoke-virtual {p1, v2}, Landroid/app/GameManager;->getAvailableGameModes(Ljava/lang/String;)[I

    move-result-object p1

    .line 182
    iget v2, p0, Lorg/nameless/gamespace/gamebar/SessionService$applyGameModeConfig$2;->$preferred:I

    const-string v3, "it"

    invoke-static {p1, v3}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullExpressionValue(Ljava/lang/Object;Ljava/lang/String;)V

    invoke-static {p1, v2}, Lkotlin/collections/ArraysKt;->contains([II)Z

    move-result v2

    if-eqz v2, :cond_1

    goto :goto_0

    :cond_1
    move-object p1, v1

    :goto_0
    if-eqz p1, :cond_3

    .line 183
    iget-object p1, p0, Lorg/nameless/gamespace/gamebar/SessionService$applyGameModeConfig$2;->this$0:Lorg/nameless/gamespace/gamebar/SessionService;

    iget-object v2, p0, Lorg/nameless/gamespace/gamebar/SessionService$applyGameModeConfig$2;->$app:Ljava/lang/String;

    iget p0, p0, Lorg/nameless/gamespace/gamebar/SessionService$applyGameModeConfig$2;->$preferred:I

    invoke-static {p1}, Lorg/nameless/gamespace/gamebar/SessionService;->access$getGameManager$p(Lorg/nameless/gamespace/gamebar/SessionService;)Landroid/app/GameManager;

    move-result-object p1

    if-nez p1, :cond_2

    invoke-static {v0}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    goto :goto_1

    :cond_2
    move-object v1, p1

    :goto_1
    invoke-virtual {v1, v2, p0}, Landroid/app/GameManager;->setGameMode(Ljava/lang/String;I)V

    .line 184
    :cond_3
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0

    .line 180
    :cond_4
    new-instance p0, Ljava/lang/IllegalStateException;

    const-string p1, "call to \'resume\' before \'invoke\' with coroutine"

    invoke-direct {p0, p1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw p0
.end method
