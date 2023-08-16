.class final Lkotlinx/coroutines/internal/ClassValueCtorCache;
.super Lkotlinx/coroutines/internal/CtorCache;
.source "ExceptionsConstructor.kt"


# annotations
.annotation build Landroid/annotation/SuppressLint;
    value = {
        "NewApi"
    }
.end annotation


# static fields
.field public static final INSTANCE:Lkotlinx/coroutines/internal/ClassValueCtorCache;

.field private static final cache:Lkotlinx/coroutines/internal/ClassValueCtorCache$cache$1;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    new-instance v0, Lkotlinx/coroutines/internal/ClassValueCtorCache;

    invoke-direct {v0}, Lkotlinx/coroutines/internal/ClassValueCtorCache;-><init>()V

    sput-object v0, Lkotlinx/coroutines/internal/ClassValueCtorCache;->INSTANCE:Lkotlinx/coroutines/internal/ClassValueCtorCache;

    .line 103
    new-instance v0, Lkotlinx/coroutines/internal/ClassValueCtorCache$cache$1;

    invoke-direct {v0}, Lkotlinx/coroutines/internal/ClassValueCtorCache$cache$1;-><init>()V

    sput-object v0, Lkotlinx/coroutines/internal/ClassValueCtorCache;->cache:Lkotlinx/coroutines/internal/ClassValueCtorCache$cache$1;

    return-void
.end method

.method private constructor <init>()V
    .locals 0

    .line 102
    invoke-direct {p0}, Lkotlinx/coroutines/internal/CtorCache;-><init>()V

    return-void
.end method


# virtual methods
.method public get(Ljava/lang/Class;)Lkotlin/jvm/functions/Function1;
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/Class<",
            "+",
            "Ljava/lang/Throwable;",
            ">;)",
            "Lkotlin/jvm/functions/Function1<",
            "Ljava/lang/Throwable;",
            "Ljava/lang/Throwable;",
            ">;"
        }
    .end annotation

    const-string p0, "key"

    invoke-static {p1, p0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 110
    sget-object p0, Lkotlinx/coroutines/internal/ClassValueCtorCache;->cache:Lkotlinx/coroutines/internal/ClassValueCtorCache$cache$1;

    invoke-virtual {p0, p1}, Lkotlinx/coroutines/internal/ClassValueCtorCache$cache$1;->get(Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object p0

    const-string p1, "cache.get(key)"

    invoke-static {p0, p1}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullExpressionValue(Ljava/lang/Object;Ljava/lang/String;)V

    check-cast p0, Lkotlin/jvm/functions/Function1;

    return-object p0
.end method
