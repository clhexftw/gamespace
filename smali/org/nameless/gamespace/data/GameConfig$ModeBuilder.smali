.class public final Lorg/nameless/gamespace/data/GameConfig$ModeBuilder;
.super Ljava/lang/Object;
.source "GameConfig.kt"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/nameless/gamespace/data/GameConfig;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "ModeBuilder"
.end annotation


# static fields
.field public static final INSTANCE:Lorg/nameless/gamespace/data/GameConfig$ModeBuilder;

.field private static useAngle:Z


# direct methods
.method static constructor <clinit>()V
    .locals 1

    new-instance v0, Lorg/nameless/gamespace/data/GameConfig$ModeBuilder;

    invoke-direct {v0}, Lorg/nameless/gamespace/data/GameConfig$ModeBuilder;-><init>()V

    sput-object v0, Lorg/nameless/gamespace/data/GameConfig$ModeBuilder;->INSTANCE:Lorg/nameless/gamespace/data/GameConfig$ModeBuilder;

    return-void
.end method

.method private constructor <init>()V
    .locals 0

    .line 36
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public final build()Ljava/util/List;
    .locals 4
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/List<",
            "Lorg/nameless/gamespace/data/GameConfig;",
            ">;"
        }
    .end annotation

    const/4 p0, 0x2

    new-array v0, p0, [Lorg/nameless/gamespace/data/GameConfig;

    .line 40
    new-instance v1, Lorg/nameless/gamespace/data/GameConfig;

    sget-boolean v2, Lorg/nameless/gamespace/data/GameConfig$ModeBuilder;->useAngle:Z

    const v3, 0x3f333333    # 0.7f

    invoke-direct {v1, p0, v3, v2}, Lorg/nameless/gamespace/data/GameConfig;-><init>(IFZ)V

    const/4 p0, 0x0

    aput-object v1, v0, p0

    .line 41
    new-instance p0, Lorg/nameless/gamespace/data/GameConfig;

    sget-boolean v1, Lorg/nameless/gamespace/data/GameConfig$ModeBuilder;->useAngle:Z

    const/4 v2, 0x3

    const v3, 0x3f4ccccd    # 0.8f

    invoke-direct {p0, v2, v3, v1}, Lorg/nameless/gamespace/data/GameConfig;-><init>(IFZ)V

    const/4 v1, 0x1

    aput-object p0, v0, v1

    .line 39
    invoke-static {v0}, Lkotlin/collections/CollectionsKt;->listOf([Ljava/lang/Object;)Ljava/util/List;

    move-result-object p0

    return-object p0
.end method

.method public final setUseAngle(Z)V
    .locals 0

    .line 37
    sput-boolean p1, Lorg/nameless/gamespace/data/GameConfig$ModeBuilder;->useAngle:Z

    return-void
.end method
