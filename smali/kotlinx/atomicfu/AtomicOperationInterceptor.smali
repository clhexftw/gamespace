.class public Lkotlinx/atomicfu/AtomicOperationInterceptor;
.super Ljava/lang/Object;
.source "Interceptor.kt"


# direct methods
.method public constructor <init>()V
    .locals 0

    .line 29
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public afterRMW(Lkotlinx/atomicfu/AtomicBoolean;ZZ)V
    .locals 0

    const-string p0, "ref"

    invoke-static {p1, p0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    return-void
.end method

.method public afterRMW(Lkotlinx/atomicfu/AtomicInt;II)V
    .locals 0

    const-string p0, "ref"

    invoke-static {p1, p0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    return-void
.end method

.method public afterRMW(Lkotlinx/atomicfu/AtomicLong;JJ)V
    .locals 0

    const-string p0, "ref"

    invoke-static {p1, p0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    return-void
.end method

.method public afterRMW(Lkotlinx/atomicfu/AtomicRef;Ljava/lang/Object;Ljava/lang/Object;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "<T:",
            "Ljava/lang/Object;",
            ">(",
            "Lkotlinx/atomicfu/AtomicRef<",
            "TT;>;TT;TT;)V"
        }
    .end annotation

    const-string p0, "ref"

    invoke-static {p1, p0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    return-void
.end method

.method public afterSet(Lkotlinx/atomicfu/AtomicBoolean;Z)V
    .locals 0

    const-string p0, "ref"

    invoke-static {p1, p0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    return-void
.end method

.method public afterSet(Lkotlinx/atomicfu/AtomicInt;I)V
    .locals 0

    const-string p0, "ref"

    invoke-static {p1, p0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    return-void
.end method

.method public afterSet(Lkotlinx/atomicfu/AtomicLong;J)V
    .locals 0

    const-string p0, "ref"

    invoke-static {p1, p0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    return-void
.end method

.method public afterSet(Lkotlinx/atomicfu/AtomicRef;Ljava/lang/Object;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "<T:",
            "Ljava/lang/Object;",
            ">(",
            "Lkotlinx/atomicfu/AtomicRef<",
            "TT;>;TT;)V"
        }
    .end annotation

    const-string p0, "ref"

    invoke-static {p1, p0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    return-void
.end method

.method public beforeUpdate(Lkotlinx/atomicfu/AtomicBoolean;)V
    .locals 0

    const-string p0, "ref"

    invoke-static {p1, p0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    return-void
.end method

.method public beforeUpdate(Lkotlinx/atomicfu/AtomicInt;)V
    .locals 0

    const-string p0, "ref"

    invoke-static {p1, p0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    return-void
.end method

.method public beforeUpdate(Lkotlinx/atomicfu/AtomicLong;)V
    .locals 0

    const-string p0, "ref"

    invoke-static {p1, p0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    return-void
.end method

.method public beforeUpdate(Lkotlinx/atomicfu/AtomicRef;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "<T:",
            "Ljava/lang/Object;",
            ">(",
            "Lkotlinx/atomicfu/AtomicRef<",
            "TT;>;)V"
        }
    .end annotation

    const-string p0, "ref"

    invoke-static {p1, p0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    return-void
.end method
