.class public final Lkotlinx/atomicfu/AtomicLong;
.super Ljava/lang/Object;
.source "AtomicFU.kt"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lkotlinx/atomicfu/AtomicLong$Companion;
    }
.end annotation

.annotation system Ldalvik/annotation/SourceDebugExtension;
    value = "SMAP\nAtomicFU.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AtomicFU.kt\nkotlinx/atomicfu/AtomicLong\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,497:1\n1#2:498\n*E\n"
.end annotation


# static fields
.field private static final Companion:Lkotlinx/atomicfu/AtomicLong$Companion;

.field private static final FU:Ljava/util/concurrent/atomic/AtomicLongFieldUpdater;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/concurrent/atomic/AtomicLongFieldUpdater<",
            "Lkotlinx/atomicfu/AtomicLong;",
            ">;"
        }
    .end annotation

    .annotation runtime Ljava/lang/Deprecated;
    .end annotation
.end field


# instance fields
.field private final trace:Lkotlinx/atomicfu/TraceBase;

.field private volatile value:J


# direct methods
.method static constructor <clinit>()V
    .locals 2

    new-instance v0, Lkotlinx/atomicfu/AtomicLong$Companion;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lkotlinx/atomicfu/AtomicLong$Companion;-><init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V

    sput-object v0, Lkotlinx/atomicfu/AtomicLong;->Companion:Lkotlinx/atomicfu/AtomicLong$Companion;

    .line 495
    const-class v0, Lkotlinx/atomicfu/AtomicLong;

    const-string v1, "value"

    invoke-static {v0, v1}, Ljava/util/concurrent/atomic/AtomicLongFieldUpdater;->newUpdater(Ljava/lang/Class;Ljava/lang/String;)Ljava/util/concurrent/atomic/AtomicLongFieldUpdater;

    move-result-object v0

    sput-object v0, Lkotlinx/atomicfu/AtomicLong;->FU:Ljava/util/concurrent/atomic/AtomicLongFieldUpdater;

    return-void
.end method

.method public constructor <init>(JLkotlinx/atomicfu/TraceBase;)V
    .locals 1

    const-string v0, "trace"

    invoke-static {p3, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 361
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p3, p0, Lkotlinx/atomicfu/AtomicLong;->trace:Lkotlinx/atomicfu/TraceBase;

    .line 366
    iput-wide p1, p0, Lkotlinx/atomicfu/AtomicLong;->value:J

    return-void
.end method


# virtual methods
.method public final addAndGet(J)J
    .locals 9

    .line 449
    invoke-static {}, Lkotlinx/atomicfu/InterceptorKt;->getInterceptor()Lkotlinx/atomicfu/AtomicOperationInterceptor;

    move-result-object v0

    invoke-virtual {v0, p0}, Lkotlinx/atomicfu/AtomicOperationInterceptor;->beforeUpdate(Lkotlinx/atomicfu/AtomicLong;)V

    .line 450
    sget-object v0, Lkotlinx/atomicfu/AtomicLong;->FU:Ljava/util/concurrent/atomic/AtomicLongFieldUpdater;

    invoke-virtual {v0, p0, p1, p2}, Ljava/util/concurrent/atomic/AtomicLongFieldUpdater;->addAndGet(Ljava/lang/Object;J)J

    move-result-wide v7

    .line 451
    iget-object v0, p0, Lkotlinx/atomicfu/AtomicLong;->trace:Lkotlinx/atomicfu/TraceBase;

    sget-object v1, Lkotlinx/atomicfu/TraceBase$None;->INSTANCE:Lkotlinx/atomicfu/TraceBase$None;

    if-eq v0, v1, :cond_0

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "addAndGet("

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p1, p2}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v2, "):"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, v7, v8}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Lkotlinx/atomicfu/TraceBase;->append(Ljava/lang/Object;)V

    .line 452
    :cond_0
    invoke-static {}, Lkotlinx/atomicfu/InterceptorKt;->getInterceptor()Lkotlinx/atomicfu/AtomicOperationInterceptor;

    move-result-object v1

    sub-long v3, v7, p1

    move-object v2, p0

    move-wide v5, v7

    invoke-virtual/range {v1 .. v6}, Lkotlinx/atomicfu/AtomicOperationInterceptor;->afterRMW(Lkotlinx/atomicfu/AtomicLong;JJ)V

    return-wide v7
.end method

.method public final compareAndSet(JJ)Z
    .locals 9

    .line 392
    invoke-static {}, Lkotlinx/atomicfu/InterceptorKt;->getInterceptor()Lkotlinx/atomicfu/AtomicOperationInterceptor;

    move-result-object v0

    invoke-virtual {v0, p0}, Lkotlinx/atomicfu/AtomicOperationInterceptor;->beforeUpdate(Lkotlinx/atomicfu/AtomicLong;)V

    .line 393
    sget-object v1, Lkotlinx/atomicfu/AtomicLong;->FU:Ljava/util/concurrent/atomic/AtomicLongFieldUpdater;

    move-object v2, p0

    move-wide v3, p1

    move-wide v5, p3

    invoke-virtual/range {v1 .. v6}, Ljava/util/concurrent/atomic/AtomicLongFieldUpdater;->compareAndSet(Ljava/lang/Object;JJ)Z

    move-result v0

    if-eqz v0, :cond_1

    .line 395
    iget-object v1, p0, Lkotlinx/atomicfu/AtomicLong;->trace:Lkotlinx/atomicfu/TraceBase;

    sget-object v2, Lkotlinx/atomicfu/TraceBase$None;->INSTANCE:Lkotlinx/atomicfu/TraceBase$None;

    if-eq v1, v2, :cond_0

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "CAS("

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, p1, p2}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v3, ", "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, p3, p4}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v3, ")"

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Lkotlinx/atomicfu/TraceBase;->append(Ljava/lang/Object;)V

    .line 396
    :cond_0
    invoke-static {}, Lkotlinx/atomicfu/InterceptorKt;->getInterceptor()Lkotlinx/atomicfu/AtomicOperationInterceptor;

    move-result-object v3

    move-object v4, p0

    move-wide v5, p1

    move-wide v7, p3

    invoke-virtual/range {v3 .. v8}, Lkotlinx/atomicfu/AtomicOperationInterceptor;->afterRMW(Lkotlinx/atomicfu/AtomicLong;JJ)V

    :cond_1
    return v0
.end method

.method public final getAndDecrement()J
    .locals 9

    .line 427
    invoke-static {}, Lkotlinx/atomicfu/InterceptorKt;->getInterceptor()Lkotlinx/atomicfu/AtomicOperationInterceptor;

    move-result-object v0

    invoke-virtual {v0, p0}, Lkotlinx/atomicfu/AtomicOperationInterceptor;->beforeUpdate(Lkotlinx/atomicfu/AtomicLong;)V

    .line 428
    sget-object v0, Lkotlinx/atomicfu/AtomicLong;->FU:Ljava/util/concurrent/atomic/AtomicLongFieldUpdater;

    invoke-virtual {v0, p0}, Ljava/util/concurrent/atomic/AtomicLongFieldUpdater;->getAndDecrement(Ljava/lang/Object;)J

    move-result-wide v7

    .line 429
    iget-object v0, p0, Lkotlinx/atomicfu/AtomicLong;->trace:Lkotlinx/atomicfu/TraceBase;

    sget-object v1, Lkotlinx/atomicfu/TraceBase$None;->INSTANCE:Lkotlinx/atomicfu/TraceBase$None;

    if-eq v0, v1, :cond_0

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "getAndDec():"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, v7, v8}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Lkotlinx/atomicfu/TraceBase;->append(Ljava/lang/Object;)V

    .line 430
    :cond_0
    invoke-static {}, Lkotlinx/atomicfu/InterceptorKt;->getInterceptor()Lkotlinx/atomicfu/AtomicOperationInterceptor;

    move-result-object v1

    const-wide/16 v2, 0x1

    sub-long v5, v7, v2

    move-object v2, p0

    move-wide v3, v7

    invoke-virtual/range {v1 .. v6}, Lkotlinx/atomicfu/AtomicOperationInterceptor;->afterRMW(Lkotlinx/atomicfu/AtomicLong;JJ)V

    return-wide v7
.end method

.method public final getValue()J
    .locals 2

    .line 366
    iget-wide v0, p0, Lkotlinx/atomicfu/AtomicLong;->value:J

    return-wide v0
.end method

.method public final incrementAndGet()J
    .locals 9

    .line 460
    invoke-static {}, Lkotlinx/atomicfu/InterceptorKt;->getInterceptor()Lkotlinx/atomicfu/AtomicOperationInterceptor;

    move-result-object v0

    invoke-virtual {v0, p0}, Lkotlinx/atomicfu/AtomicOperationInterceptor;->beforeUpdate(Lkotlinx/atomicfu/AtomicLong;)V

    .line 461
    sget-object v0, Lkotlinx/atomicfu/AtomicLong;->FU:Ljava/util/concurrent/atomic/AtomicLongFieldUpdater;

    invoke-virtual {v0, p0}, Ljava/util/concurrent/atomic/AtomicLongFieldUpdater;->incrementAndGet(Ljava/lang/Object;)J

    move-result-wide v7

    .line 462
    iget-object v0, p0, Lkotlinx/atomicfu/AtomicLong;->trace:Lkotlinx/atomicfu/TraceBase;

    sget-object v1, Lkotlinx/atomicfu/TraceBase$None;->INSTANCE:Lkotlinx/atomicfu/TraceBase$None;

    if-eq v0, v1, :cond_0

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "incAndGet():"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, v7, v8}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Lkotlinx/atomicfu/TraceBase;->append(Ljava/lang/Object;)V

    .line 463
    :cond_0
    invoke-static {}, Lkotlinx/atomicfu/InterceptorKt;->getInterceptor()Lkotlinx/atomicfu/AtomicOperationInterceptor;

    move-result-object v1

    const-wide/16 v2, 0x1

    sub-long v3, v7, v2

    move-object v2, p0

    move-wide v5, v7

    invoke-virtual/range {v1 .. v6}, Lkotlinx/atomicfu/AtomicOperationInterceptor;->afterRMW(Lkotlinx/atomicfu/AtomicLong;JJ)V

    return-wide v7
.end method

.method public final setValue(J)V
    .locals 3

    .line 368
    invoke-static {}, Lkotlinx/atomicfu/InterceptorKt;->getInterceptor()Lkotlinx/atomicfu/AtomicOperationInterceptor;

    move-result-object v0

    invoke-virtual {v0, p0}, Lkotlinx/atomicfu/AtomicOperationInterceptor;->beforeUpdate(Lkotlinx/atomicfu/AtomicLong;)V

    .line 369
    iput-wide p1, p0, Lkotlinx/atomicfu/AtomicLong;->value:J

    .line 370
    iget-object v0, p0, Lkotlinx/atomicfu/AtomicLong;->trace:Lkotlinx/atomicfu/TraceBase;

    sget-object v1, Lkotlinx/atomicfu/TraceBase$None;->INSTANCE:Lkotlinx/atomicfu/TraceBase$None;

    if-eq v0, v1, :cond_0

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "set("

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p1, p2}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v2, ")"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Lkotlinx/atomicfu/TraceBase;->append(Ljava/lang/Object;)V

    .line 371
    :cond_0
    invoke-static {}, Lkotlinx/atomicfu/InterceptorKt;->getInterceptor()Lkotlinx/atomicfu/AtomicOperationInterceptor;

    move-result-object v0

    invoke-virtual {v0, p0, p1, p2}, Lkotlinx/atomicfu/AtomicOperationInterceptor;->afterSet(Lkotlinx/atomicfu/AtomicLong;J)V

    return-void
.end method

.method public toString()Ljava/lang/String;
    .locals 2

    .line 492
    iget-wide v0, p0, Lkotlinx/atomicfu/AtomicLong;->value:J

    invoke-static {v0, v1}, Ljava/lang/String;->valueOf(J)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method
