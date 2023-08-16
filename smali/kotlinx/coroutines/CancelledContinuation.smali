.class public final Lkotlinx/coroutines/CancelledContinuation;
.super Lkotlinx/coroutines/CompletedExceptionally;
.source "CompletionState.kt"


# instance fields
.field private final _resumed:Lkotlinx/atomicfu/AtomicBoolean;


# virtual methods
.method public final makeResumed()Z
    .locals 2

    .line 65
    iget-object p0, p0, Lkotlinx/coroutines/CancelledContinuation;->_resumed:Lkotlinx/atomicfu/AtomicBoolean;

    const/4 v0, 0x0

    const/4 v1, 0x1

    invoke-virtual {p0, v0, v1}, Lkotlinx/atomicfu/AtomicBoolean;->compareAndSet(ZZ)Z

    move-result p0

    return p0
.end method
