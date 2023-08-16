.class public final Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;
.super Ljava/lang/Object;
.source "LockFreeTaskQueue.kt"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lkotlinx/coroutines/internal/LockFreeTaskQueueCore$Placeholder;,
        Lkotlinx/coroutines/internal/LockFreeTaskQueueCore$Companion;
    }
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "<E:",
        "Ljava/lang/Object;",
        ">",
        "Ljava/lang/Object;"
    }
.end annotation

.annotation system Ldalvik/annotation/SourceDebugExtension;
    value = "SMAP\nLockFreeTaskQueue.kt\nKotlin\n*S Kotlin\n*F\n+ 1 LockFreeTaskQueue.kt\nkotlinx/coroutines/internal/LockFreeTaskQueueCore\n+ 2 LockFreeTaskQueue.kt\nkotlinx/coroutines/internal/LockFreeTaskQueueCore$Companion\n+ 3 AtomicFU.common.kt\nkotlinx/atomicfu/AtomicFU_commonKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,308:1\n299#2,3:309\n299#2,3:312\n299#2,3:321\n299#2,3:326\n299#2,3:331\n299#2,3:341\n299#2,3:344\n477#3,4:315\n468#3,2:319\n468#3,2:324\n468#3,2:329\n499#3,4:335\n155#3,2:339\n1#4:334\n*S KotlinDebug\n*F\n+ 1 LockFreeTaskQueue.kt\nkotlinx/coroutines/internal/LockFreeTaskQueueCore\n*L\n91#1:309,3\n92#1:312,3\n107#1:321,3\n167#1:326,3\n200#1:331,3\n231#1:341,3\n247#1:344,3\n95#1:315,4\n105#1:319,2\n165#1:324,2\n199#1:329,2\n217#1:335,4\n223#1:339,2\n*E\n"
.end annotation


# static fields
.field public static final Companion:Lkotlinx/coroutines/internal/LockFreeTaskQueueCore$Companion;

.field public static final REMOVE_FROZEN:Lkotlinx/coroutines/internal/Symbol;


# instance fields
.field private final _next:Lkotlinx/atomicfu/AtomicRef;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lkotlinx/atomicfu/AtomicRef<",
            "Lkotlinx/coroutines/internal/LockFreeTaskQueueCore<",
            "TE;>;>;"
        }
    .end annotation
.end field

.field private final _state:Lkotlinx/atomicfu/AtomicLong;

.field private final array:Lkotlinx/atomicfu/AtomicArray;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lkotlinx/atomicfu/AtomicArray<",
            "Ljava/lang/Object;",
            ">;"
        }
    .end annotation
.end field

.field private final capacity:I

.field private final mask:I

.field private final singleConsumer:Z


# direct methods
.method static constructor <clinit>()V
    .locals 2

    new-instance v0, Lkotlinx/coroutines/internal/LockFreeTaskQueueCore$Companion;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lkotlinx/coroutines/internal/LockFreeTaskQueueCore$Companion;-><init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V

    sput-object v0, Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;->Companion:Lkotlinx/coroutines/internal/LockFreeTaskQueueCore$Companion;

    .line 288
    new-instance v0, Lkotlinx/coroutines/internal/Symbol;

    const-string v1, "REMOVE_FROZEN"

    invoke-direct {v0, v1}, Lkotlinx/coroutines/internal/Symbol;-><init>(Ljava/lang/String;)V

    sput-object v0, Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;->REMOVE_FROZEN:Lkotlinx/coroutines/internal/Symbol;

    return-void
.end method

.method public constructor <init>(IZ)V
    .locals 3

    .line 76
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 77
    iput p1, p0, Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;->capacity:I

    .line 78
    iput-boolean p2, p0, Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;->singleConsumer:Z

    add-int/lit8 p2, p1, -0x1

    .line 80
    iput p2, p0, Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;->mask:I

    const/4 v0, 0x0

    .line 81
    invoke-static {v0}, Lkotlinx/atomicfu/AtomicFU;->atomic(Ljava/lang/Object;)Lkotlinx/atomicfu/AtomicRef;

    move-result-object v0

    iput-object v0, p0, Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;->_next:Lkotlinx/atomicfu/AtomicRef;

    const-wide/16 v0, 0x0

    .line 82
    invoke-static {v0, v1}, Lkotlinx/atomicfu/AtomicFU;->atomic(J)Lkotlinx/atomicfu/AtomicLong;

    move-result-object v0

    iput-object v0, p0, Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;->_state:Lkotlinx/atomicfu/AtomicLong;

    .line 83
    invoke-static {p1}, Lkotlinx/atomicfu/AtomicFU_commonKt;->atomicArrayOfNulls(I)Lkotlinx/atomicfu/AtomicArray;

    move-result-object v0

    iput-object v0, p0, Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;->array:Lkotlinx/atomicfu/AtomicArray;

    const/4 p0, 0x0

    const/4 v0, 0x1

    const v1, 0x3fffffff    # 1.9999999f

    if-gt p2, v1, :cond_0

    move v1, v0

    goto :goto_0

    :cond_0
    move v1, p0

    :goto_0
    const-string v2, "Check failed."

    if-eqz v1, :cond_3

    and-int/2addr p1, p2

    if-nez p1, :cond_1

    move p0, v0

    :cond_1
    if-eqz p0, :cond_2

    return-void

    .line 87
    :cond_2
    new-instance p0, Ljava/lang/IllegalStateException;

    invoke-virtual {v2}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-direct {p0, p1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw p0

    .line 86
    :cond_3
    new-instance p0, Ljava/lang/IllegalStateException;

    invoke-virtual {v2}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-direct {p0, p1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw p0
.end method

.method private final allocateNextCopy(J)Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;
    .locals 6
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(J)",
            "Lkotlinx/coroutines/internal/LockFreeTaskQueueCore<",
            "TE;>;"
        }
    .end annotation

    .line 230
    new-instance v0, Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;

    iget v1, p0, Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;->capacity:I

    mul-int/lit8 v1, v1, 0x2

    iget-boolean v2, p0, Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;->singleConsumer:Z

    invoke-direct {v0, v1, v2}, Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;-><init>(IZ)V

    const-wide/32 v1, 0x3fffffff

    and-long/2addr v1, p1

    const/4 v3, 0x0

    shr-long/2addr v1, v3

    long-to-int v1, v1

    const-wide v2, 0xfffffffc0000000L

    and-long/2addr v2, p1

    const/16 v4, 0x1e

    shr-long/2addr v2, v4

    long-to-int v2, v2

    .line 233
    :goto_0
    iget v3, p0, Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;->mask:I

    and-int v4, v1, v3

    and-int v5, v2, v3

    if-eq v4, v5, :cond_1

    .line 235
    iget-object v4, p0, Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;->array:Lkotlinx/atomicfu/AtomicArray;

    and-int/2addr v3, v1

    invoke-virtual {v4, v3}, Lkotlinx/atomicfu/AtomicArray;->get(I)Lkotlinx/atomicfu/AtomicRef;

    move-result-object v3

    invoke-virtual {v3}, Lkotlinx/atomicfu/AtomicRef;->getValue()Ljava/lang/Object;

    move-result-object v3

    if-nez v3, :cond_0

    new-instance v3, Lkotlinx/coroutines/internal/LockFreeTaskQueueCore$Placeholder;

    invoke-direct {v3, v1}, Lkotlinx/coroutines/internal/LockFreeTaskQueueCore$Placeholder;-><init>(I)V

    .line 236
    :cond_0
    iget-object v4, v0, Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;->array:Lkotlinx/atomicfu/AtomicArray;

    iget v5, v0, Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;->mask:I

    and-int/2addr v5, v1

    invoke-virtual {v4, v5}, Lkotlinx/atomicfu/AtomicArray;->get(I)Lkotlinx/atomicfu/AtomicRef;

    move-result-object v4

    invoke-virtual {v4, v3}, Lkotlinx/atomicfu/AtomicRef;->setValue(Ljava/lang/Object;)V

    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    .line 239
    :cond_1
    iget-object p0, v0, Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;->_state:Lkotlinx/atomicfu/AtomicLong;

    sget-object v1, Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;->Companion:Lkotlinx/coroutines/internal/LockFreeTaskQueueCore$Companion;

    const-wide/high16 v2, 0x1000000000000000L

    invoke-virtual {v1, p1, p2, v2, v3}, Lkotlinx/coroutines/internal/LockFreeTaskQueueCore$Companion;->wo(JJ)J

    move-result-wide p1

    invoke-virtual {p0, p1, p2}, Lkotlinx/atomicfu/AtomicLong;->setValue(J)V

    return-object v0
.end method

.method private final allocateOrGetNextCopy(J)Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;
    .locals 4
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(J)",
            "Lkotlinx/coroutines/internal/LockFreeTaskQueueCore<",
            "TE;>;"
        }
    .end annotation

    .line 223
    iget-object v0, p0, Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;->_next:Lkotlinx/atomicfu/AtomicRef;

    .line 156
    :goto_0
    invoke-virtual {v0}, Lkotlinx/atomicfu/AtomicRef;->getValue()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;

    if-eqz v1, :cond_0

    return-object v1

    .line 225
    :cond_0
    iget-object v1, p0, Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;->_next:Lkotlinx/atomicfu/AtomicRef;

    const/4 v2, 0x0

    invoke-direct {p0, p1, p2}, Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;->allocateNextCopy(J)Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;

    move-result-object v3

    invoke-virtual {v1, v2, v3}, Lkotlinx/atomicfu/AtomicRef;->compareAndSet(Ljava/lang/Object;Ljava/lang/Object;)Z

    goto :goto_0
.end method

.method private final fillPlaceholder(ILjava/lang/Object;)Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(ITE;)",
            "Lkotlinx/coroutines/internal/LockFreeTaskQueueCore<",
            "TE;>;"
        }
    .end annotation

    .line 143
    iget-object v0, p0, Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;->array:Lkotlinx/atomicfu/AtomicArray;

    iget v1, p0, Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;->mask:I

    and-int/2addr v1, p1

    invoke-virtual {v0, v1}, Lkotlinx/atomicfu/AtomicArray;->get(I)Lkotlinx/atomicfu/AtomicRef;

    move-result-object v0

    invoke-virtual {v0}, Lkotlinx/atomicfu/AtomicRef;->getValue()Ljava/lang/Object;

    move-result-object v0

    .line 154
    instance-of v1, v0, Lkotlinx/coroutines/internal/LockFreeTaskQueueCore$Placeholder;

    if-eqz v1, :cond_0

    check-cast v0, Lkotlinx/coroutines/internal/LockFreeTaskQueueCore$Placeholder;

    iget v0, v0, Lkotlinx/coroutines/internal/LockFreeTaskQueueCore$Placeholder;->index:I

    if-ne v0, p1, :cond_0

    .line 155
    iget-object v0, p0, Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;->array:Lkotlinx/atomicfu/AtomicArray;

    iget v1, p0, Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;->mask:I

    and-int/2addr p1, v1

    invoke-virtual {v0, p1}, Lkotlinx/atomicfu/AtomicArray;->get(I)Lkotlinx/atomicfu/AtomicRef;

    move-result-object p1

    invoke-virtual {p1, p2}, Lkotlinx/atomicfu/AtomicRef;->setValue(Ljava/lang/Object;)V

    return-object p0

    :cond_0
    const/4 p0, 0x0

    return-object p0
.end method

.method private final markFrozen()J
    .locals 8

    .line 217
    iget-object p0, p0, Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;->_state:Lkotlinx/atomicfu/AtomicLong;

    .line 500
    :cond_0
    invoke-virtual {p0}, Lkotlinx/atomicfu/AtomicLong;->getValue()J

    move-result-wide v0

    const-wide/high16 v2, 0x1000000000000000L

    and-long v4, v0, v2

    const-wide/16 v6, 0x0

    cmp-long v4, v4, v6

    if-eqz v4, :cond_1

    return-wide v0

    :cond_1
    or-long/2addr v2, v0

    .line 502
    invoke-virtual {p0, v0, v1, v2, v3}, Lkotlinx/atomicfu/AtomicLong;->compareAndSet(JJ)Z

    move-result v0

    if-eqz v0, :cond_0

    return-wide v2
.end method

.method private final removeSlowPath(II)Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;
    .locals 9
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(II)",
            "Lkotlinx/coroutines/internal/LockFreeTaskQueueCore<",
            "TE;>;"
        }
    .end annotation

    .line 199
    iget-object v0, p0, Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;->_state:Lkotlinx/atomicfu/AtomicLong;

    .line 469
    :cond_0
    invoke-virtual {v0}, Lkotlinx/atomicfu/AtomicLong;->getValue()J

    move-result-wide v1

    .line 200
    sget-object v3, Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;->Companion:Lkotlinx/coroutines/internal/LockFreeTaskQueueCore$Companion;

    const-wide/32 v4, 0x3fffffff

    and-long/2addr v4, v1

    const/4 v6, 0x0

    shr-long/2addr v4, v6

    long-to-int v4, v4

    .line 201
    invoke-static {}, Lkotlinx/coroutines/DebugKt;->getASSERTIONS_ENABLED()Z

    move-result v5

    if-eqz v5, :cond_3

    if-ne v4, p1, :cond_1

    const/4 v6, 0x1

    :cond_1
    if-eqz v6, :cond_2

    goto :goto_0

    :cond_2
    new-instance p0, Ljava/lang/AssertionError;

    invoke-direct {p0}, Ljava/lang/AssertionError;-><init>()V

    throw p0

    :cond_3
    :goto_0
    const-wide/high16 v5, 0x1000000000000000L

    and-long/2addr v5, v1

    const-wide/16 v7, 0x0

    cmp-long v5, v5, v7

    if-eqz v5, :cond_4

    .line 204
    invoke-virtual {p0}, Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;->next()Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;

    move-result-object p0

    return-object p0

    .line 206
    :cond_4
    iget-object v5, p0, Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;->_state:Lkotlinx/atomicfu/AtomicLong;

    invoke-virtual {v3, v1, v2, p2}, Lkotlinx/coroutines/internal/LockFreeTaskQueueCore$Companion;->updateHead(JI)J

    move-result-wide v6

    invoke-virtual {v5, v1, v2, v6, v7}, Lkotlinx/atomicfu/AtomicLong;->compareAndSet(JJ)Z

    move-result v1

    if-eqz v1, :cond_0

    .line 207
    iget-object p1, p0, Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;->array:Lkotlinx/atomicfu/AtomicArray;

    iget p0, p0, Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;->mask:I

    and-int/2addr p0, v4

    invoke-virtual {p1, p0}, Lkotlinx/atomicfu/AtomicArray;->get(I)Lkotlinx/atomicfu/AtomicRef;

    move-result-object p0

    const/4 p1, 0x0

    invoke-virtual {p0, p1}, Lkotlinx/atomicfu/AtomicRef;->setValue(Ljava/lang/Object;)V

    return-object p1
.end method


# virtual methods
.method public final addLast(Ljava/lang/Object;)I
    .locals 14
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(TE;)I"
        }
    .end annotation

    const-string v0, "element"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 105
    iget-object v0, p0, Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;->_state:Lkotlinx/atomicfu/AtomicLong;

    .line 469
    :cond_0
    invoke-virtual {v0}, Lkotlinx/atomicfu/AtomicLong;->getValue()J

    move-result-wide v1

    const-wide/high16 v3, 0x3000000000000000L    # 1.727233711018889E-77

    and-long/2addr v3, v1

    const-wide/16 v5, 0x0

    cmp-long v3, v3, v5

    if-eqz v3, :cond_1

    .line 106
    sget-object p0, Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;->Companion:Lkotlinx/coroutines/internal/LockFreeTaskQueueCore$Companion;

    invoke-virtual {p0, v1, v2}, Lkotlinx/coroutines/internal/LockFreeTaskQueueCore$Companion;->addFailReason(J)I

    move-result p0

    return p0

    .line 107
    :cond_1
    sget-object v3, Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;->Companion:Lkotlinx/coroutines/internal/LockFreeTaskQueueCore$Companion;

    const-wide/32 v7, 0x3fffffff

    and-long/2addr v7, v1

    const/4 v4, 0x0

    shr-long/2addr v7, v4

    long-to-int v7, v7

    const-wide v8, 0xfffffffc0000000L

    and-long/2addr v8, v1

    const/16 v10, 0x1e

    shr-long/2addr v8, v10

    long-to-int v8, v8

    .line 108
    iget v9, p0, Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;->mask:I

    add-int/lit8 v10, v8, 0x2

    and-int/2addr v10, v9

    and-int v11, v7, v9

    const/4 v12, 0x1

    if-ne v10, v11, :cond_2

    return v12

    .line 114
    :cond_2
    iget-boolean v10, p0, Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;->singleConsumer:Z

    const v11, 0x3fffffff    # 1.9999999f

    if-nez v10, :cond_4

    iget-object v10, p0, Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;->array:Lkotlinx/atomicfu/AtomicArray;

    and-int v13, v8, v9

    invoke-virtual {v10, v13}, Lkotlinx/atomicfu/AtomicArray;->get(I)Lkotlinx/atomicfu/AtomicRef;

    move-result-object v10

    invoke-virtual {v10}, Lkotlinx/atomicfu/AtomicRef;->getValue()Ljava/lang/Object;

    move-result-object v10

    if-eqz v10, :cond_4

    .line 120
    iget v1, p0, Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;->capacity:I

    const/16 v2, 0x400

    if-lt v1, v2, :cond_3

    sub-int/2addr v8, v7

    and-int v2, v8, v11

    shr-int/lit8 v1, v1, 0x1

    if-le v2, v1, :cond_0

    :cond_3
    return v12

    :cond_4
    add-int/lit8 v7, v8, 0x1

    and-int/2addr v7, v11

    .line 127
    iget-object v10, p0, Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;->_state:Lkotlinx/atomicfu/AtomicLong;

    invoke-virtual {v3, v1, v2, v7}, Lkotlinx/coroutines/internal/LockFreeTaskQueueCore$Companion;->updateTail(JI)J

    move-result-wide v11

    invoke-virtual {v10, v1, v2, v11, v12}, Lkotlinx/atomicfu/AtomicLong;->compareAndSet(JJ)Z

    move-result v1

    if-eqz v1, :cond_0

    .line 129
    iget-object v0, p0, Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;->array:Lkotlinx/atomicfu/AtomicArray;

    and-int v1, v8, v9

    invoke-virtual {v0, v1}, Lkotlinx/atomicfu/AtomicArray;->get(I)Lkotlinx/atomicfu/AtomicRef;

    move-result-object v0

    invoke-virtual {v0, p1}, Lkotlinx/atomicfu/AtomicRef;->setValue(Ljava/lang/Object;)V

    .line 133
    :cond_5
    iget-object v0, p0, Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;->_state:Lkotlinx/atomicfu/AtomicLong;

    invoke-virtual {v0}, Lkotlinx/atomicfu/AtomicLong;->getValue()J

    move-result-wide v0

    const-wide/high16 v2, 0x1000000000000000L

    and-long/2addr v0, v2

    cmp-long v0, v0, v5

    if-eqz v0, :cond_6

    .line 134
    invoke-virtual {p0}, Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;->next()Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;

    move-result-object p0

    invoke-direct {p0, v8, p1}, Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;->fillPlaceholder(ILjava/lang/Object;)Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;

    move-result-object p0

    if-nez p0, :cond_5

    :cond_6
    return v4
.end method

.method public final close()Z
    .locals 10

    .line 95
    iget-object p0, p0, Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;->_state:Lkotlinx/atomicfu/AtomicLong;

    .line 478
    :cond_0
    invoke-virtual {p0}, Lkotlinx/atomicfu/AtomicLong;->getValue()J

    move-result-wide v0

    const-wide/high16 v2, 0x2000000000000000L

    and-long v4, v0, v2

    const-wide/16 v6, 0x0

    cmp-long v4, v4, v6

    const/4 v5, 0x1

    if-eqz v4, :cond_1

    return v5

    :cond_1
    const-wide/high16 v8, 0x1000000000000000L

    and-long/2addr v8, v0

    cmp-long v4, v8, v6

    if-eqz v4, :cond_2

    const/4 p0, 0x0

    return p0

    :cond_2
    or-long/2addr v2, v0

    .line 480
    invoke-virtual {p0, v0, v1, v2, v3}, Lkotlinx/atomicfu/AtomicLong;->compareAndSet(JJ)Z

    move-result v0

    if-eqz v0, :cond_0

    return v5
.end method

.method public final getSize()I
    .locals 4

    .line 92
    iget-object p0, p0, Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;->_state:Lkotlinx/atomicfu/AtomicLong;

    invoke-virtual {p0}, Lkotlinx/atomicfu/AtomicLong;->getValue()J

    move-result-wide v0

    const-wide/32 v2, 0x3fffffff

    and-long/2addr v2, v0

    const/4 p0, 0x0

    shr-long/2addr v2, p0

    long-to-int p0, v2

    const-wide v2, 0xfffffffc0000000L

    and-long/2addr v0, v2

    const/16 v2, 0x1e

    shr-long/2addr v0, v2

    long-to-int v0, v0

    sub-int/2addr v0, p0

    const p0, 0x3fffffff    # 1.9999999f

    and-int/2addr p0, v0

    return p0
.end method

.method public final isEmpty()Z
    .locals 5

    .line 91
    iget-object p0, p0, Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;->_state:Lkotlinx/atomicfu/AtomicLong;

    invoke-virtual {p0}, Lkotlinx/atomicfu/AtomicLong;->getValue()J

    move-result-wide v0

    const-wide/32 v2, 0x3fffffff

    and-long/2addr v2, v0

    const/4 p0, 0x0

    shr-long/2addr v2, p0

    long-to-int v2, v2

    const-wide v3, 0xfffffffc0000000L

    and-long/2addr v0, v3

    const/16 v3, 0x1e

    shr-long/2addr v0, v3

    long-to-int v0, v0

    if-ne v2, v0, :cond_0

    const/4 p0, 0x1

    :cond_0
    return p0
.end method

.method public final next()Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Lkotlinx/coroutines/internal/LockFreeTaskQueueCore<",
            "TE;>;"
        }
    .end annotation

    .line 214
    invoke-direct {p0}, Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;->markFrozen()J

    move-result-wide v0

    invoke-direct {p0, v0, v1}, Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;->allocateOrGetNextCopy(J)Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;

    move-result-object p0

    return-object p0
.end method

.method public final removeFirstOrNull()Ljava/lang/Object;
    .locals 11

    .line 165
    iget-object v0, p0, Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;->_state:Lkotlinx/atomicfu/AtomicLong;

    .line 469
    :cond_0
    invoke-virtual {v0}, Lkotlinx/atomicfu/AtomicLong;->getValue()J

    move-result-wide v1

    const-wide/high16 v3, 0x1000000000000000L

    and-long/2addr v3, v1

    const-wide/16 v5, 0x0

    cmp-long v3, v3, v5

    if-eqz v3, :cond_1

    .line 166
    sget-object p0, Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;->REMOVE_FROZEN:Lkotlinx/coroutines/internal/Symbol;

    return-object p0

    .line 167
    :cond_1
    sget-object v3, Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;->Companion:Lkotlinx/coroutines/internal/LockFreeTaskQueueCore$Companion;

    const-wide/32 v4, 0x3fffffff

    and-long/2addr v4, v1

    const/4 v6, 0x0

    shr-long/2addr v4, v6

    long-to-int v4, v4

    const-wide v5, 0xfffffffc0000000L

    and-long/2addr v5, v1

    const/16 v7, 0x1e

    shr-long/2addr v5, v7

    long-to-int v5, v5

    .line 168
    iget v6, p0, Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;->mask:I

    and-int/2addr v5, v6

    and-int v7, v4, v6

    const/4 v8, 0x0

    if-ne v5, v7, :cond_2

    return-object v8

    .line 169
    :cond_2
    iget-object v5, p0, Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;->array:Lkotlinx/atomicfu/AtomicArray;

    and-int/2addr v6, v4

    invoke-virtual {v5, v6}, Lkotlinx/atomicfu/AtomicArray;->get(I)Lkotlinx/atomicfu/AtomicRef;

    move-result-object v5

    invoke-virtual {v5}, Lkotlinx/atomicfu/AtomicRef;->getValue()Ljava/lang/Object;

    move-result-object v5

    if-nez v5, :cond_3

    .line 172
    iget-boolean v1, p0, Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;->singleConsumer:Z

    if-eqz v1, :cond_0

    return-object v8

    .line 177
    :cond_3
    instance-of v6, v5, Lkotlinx/coroutines/internal/LockFreeTaskQueueCore$Placeholder;

    if-eqz v6, :cond_4

    return-object v8

    :cond_4
    add-int/lit8 v6, v4, 0x1

    const v7, 0x3fffffff    # 1.9999999f

    and-int/2addr v6, v7

    .line 180
    iget-object v7, p0, Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;->_state:Lkotlinx/atomicfu/AtomicLong;

    invoke-virtual {v3, v1, v2, v6}, Lkotlinx/coroutines/internal/LockFreeTaskQueueCore$Companion;->updateHead(JI)J

    move-result-wide v9

    invoke-virtual {v7, v1, v2, v9, v10}, Lkotlinx/atomicfu/AtomicLong;->compareAndSet(JJ)Z

    move-result v1

    if-eqz v1, :cond_5

    .line 183
    iget-object v0, p0, Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;->array:Lkotlinx/atomicfu/AtomicArray;

    iget p0, p0, Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;->mask:I

    and-int/2addr p0, v4

    invoke-virtual {v0, p0}, Lkotlinx/atomicfu/AtomicArray;->get(I)Lkotlinx/atomicfu/AtomicRef;

    move-result-object p0

    invoke-virtual {p0, v8}, Lkotlinx/atomicfu/AtomicRef;->setValue(Ljava/lang/Object;)V

    return-object v5

    .line 187
    :cond_5
    iget-boolean v1, p0, Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;->singleConsumer:Z

    if-eqz v1, :cond_0

    .line 192
    :cond_6
    invoke-direct {p0, v4, v6}, Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;->removeSlowPath(II)Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;

    move-result-object p0

    if-nez p0, :cond_6

    return-object v5
.end method
