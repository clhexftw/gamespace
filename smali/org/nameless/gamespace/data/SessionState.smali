.class public final Lorg/nameless/gamespace/data/SessionState;
.super Ljava/lang/Object;
.source "SessionState.kt"


# annotations
.annotation build Landroidx/annotation/Keep;
.end annotation


# instance fields
.field private adbEnabled:Ljava/lang/Boolean;

.field private autoBrightness:Ljava/lang/Boolean;

.field private headsUp:Ljava/lang/Boolean;

.field private packageName:Ljava/lang/String;

.field private ringerMode:I

.field private threeScreenshot:Ljava/lang/Boolean;


# direct methods
.method public constructor <init>(Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;ILjava/lang/Boolean;)V
    .locals 1

    const-string v0, "packageName"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 22
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 24
    iput-object p1, p0, Lorg/nameless/gamespace/data/SessionState;->packageName:Ljava/lang/String;

    .line 25
    iput-object p2, p0, Lorg/nameless/gamespace/data/SessionState;->autoBrightness:Ljava/lang/Boolean;

    .line 26
    iput-object p3, p0, Lorg/nameless/gamespace/data/SessionState;->threeScreenshot:Ljava/lang/Boolean;

    .line 27
    iput-object p4, p0, Lorg/nameless/gamespace/data/SessionState;->headsUp:Ljava/lang/Boolean;

    .line 28
    iput p5, p0, Lorg/nameless/gamespace/data/SessionState;->ringerMode:I

    .line 29
    iput-object p6, p0, Lorg/nameless/gamespace/data/SessionState;->adbEnabled:Ljava/lang/Boolean;

    return-void
.end method

.method public synthetic constructor <init>(Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;ILjava/lang/Boolean;ILkotlin/jvm/internal/DefaultConstructorMarker;)V
    .locals 6

    and-int/lit8 v0, p7, 0x2

    const/4 v1, 0x0

    if-eqz v0, :cond_0

    move-object v0, v1

    goto :goto_0

    :cond_0
    move-object v0, p2

    :goto_0
    and-int/lit8 v2, p7, 0x4

    if-eqz v2, :cond_1

    move-object v2, v1

    goto :goto_1

    :cond_1
    move-object v2, p3

    :goto_1
    and-int/lit8 v3, p7, 0x8

    if-eqz v3, :cond_2

    move-object v3, v1

    goto :goto_2

    :cond_2
    move-object v3, p4

    :goto_2
    and-int/lit8 v4, p7, 0x10

    if-eqz v4, :cond_3

    const/4 v4, 0x2

    goto :goto_3

    :cond_3
    move v4, p5

    :goto_3
    and-int/lit8 v5, p7, 0x20

    if-eqz v5, :cond_4

    goto :goto_4

    :cond_4
    move-object v1, p6

    :goto_4
    move-object p2, p0

    move-object p3, p1

    move-object p4, v0

    move-object p5, v2

    move-object p6, v3

    move p7, v4

    move-object p8, v1

    .line 23
    invoke-direct/range {p2 .. p8}, Lorg/nameless/gamespace/data/SessionState;-><init>(Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;ILjava/lang/Boolean;)V

    return-void
.end method

.method public static synthetic copy$default(Lorg/nameless/gamespace/data/SessionState;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;ILjava/lang/Boolean;ILjava/lang/Object;)Lorg/nameless/gamespace/data/SessionState;
    .locals 4

    and-int/lit8 p8, p7, 0x1

    if-eqz p8, :cond_0

    iget-object p1, p0, Lorg/nameless/gamespace/data/SessionState;->packageName:Ljava/lang/String;

    :cond_0
    and-int/lit8 p8, p7, 0x2

    if-eqz p8, :cond_1

    iget-object p2, p0, Lorg/nameless/gamespace/data/SessionState;->autoBrightness:Ljava/lang/Boolean;

    :cond_1
    move-object p8, p2

    and-int/lit8 p2, p7, 0x4

    if-eqz p2, :cond_2

    iget-object p3, p0, Lorg/nameless/gamespace/data/SessionState;->threeScreenshot:Ljava/lang/Boolean;

    :cond_2
    move-object v0, p3

    and-int/lit8 p2, p7, 0x8

    if-eqz p2, :cond_3

    iget-object p4, p0, Lorg/nameless/gamespace/data/SessionState;->headsUp:Ljava/lang/Boolean;

    :cond_3
    move-object v1, p4

    and-int/lit8 p2, p7, 0x10

    if-eqz p2, :cond_4

    iget p5, p0, Lorg/nameless/gamespace/data/SessionState;->ringerMode:I

    :cond_4
    move v2, p5

    and-int/lit8 p2, p7, 0x20

    if-eqz p2, :cond_5

    iget-object p6, p0, Lorg/nameless/gamespace/data/SessionState;->adbEnabled:Ljava/lang/Boolean;

    :cond_5
    move-object v3, p6

    move-object p2, p0

    move-object p3, p1

    move-object p4, p8

    move-object p5, v0

    move-object p6, v1

    move p7, v2

    move-object p8, v3

    invoke-virtual/range {p2 .. p8}, Lorg/nameless/gamespace/data/SessionState;->copy(Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;ILjava/lang/Boolean;)Lorg/nameless/gamespace/data/SessionState;

    move-result-object p0

    return-object p0
.end method


# virtual methods
.method public final component1()Ljava/lang/String;
    .locals 0

    iget-object p0, p0, Lorg/nameless/gamespace/data/SessionState;->packageName:Ljava/lang/String;

    return-object p0
.end method

.method public final component2()Ljava/lang/Boolean;
    .locals 0

    iget-object p0, p0, Lorg/nameless/gamespace/data/SessionState;->autoBrightness:Ljava/lang/Boolean;

    return-object p0
.end method

.method public final component3()Ljava/lang/Boolean;
    .locals 0

    iget-object p0, p0, Lorg/nameless/gamespace/data/SessionState;->threeScreenshot:Ljava/lang/Boolean;

    return-object p0
.end method

.method public final component4()Ljava/lang/Boolean;
    .locals 0

    iget-object p0, p0, Lorg/nameless/gamespace/data/SessionState;->headsUp:Ljava/lang/Boolean;

    return-object p0
.end method

.method public final component5()I
    .locals 0

    iget p0, p0, Lorg/nameless/gamespace/data/SessionState;->ringerMode:I

    return p0
.end method

.method public final component6()Ljava/lang/Boolean;
    .locals 0

    iget-object p0, p0, Lorg/nameless/gamespace/data/SessionState;->adbEnabled:Ljava/lang/Boolean;

    return-object p0
.end method

.method public final copy(Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;ILjava/lang/Boolean;)Lorg/nameless/gamespace/data/SessionState;
    .locals 7

    const-string p0, "packageName"

    invoke-static {p1, p0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    new-instance p0, Lorg/nameless/gamespace/data/SessionState;

    move-object v0, p0

    move-object v1, p1

    move-object v2, p2

    move-object v3, p3

    move-object v4, p4

    move v5, p5

    move-object v6, p6

    invoke-direct/range {v0 .. v6}, Lorg/nameless/gamespace/data/SessionState;-><init>(Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;ILjava/lang/Boolean;)V

    return-object p0
.end method

.method public equals(Ljava/lang/Object;)Z
    .locals 4

    const/4 v0, 0x1

    if-ne p0, p1, :cond_0

    return v0

    :cond_0
    instance-of v1, p1, Lorg/nameless/gamespace/data/SessionState;

    const/4 v2, 0x0

    if-nez v1, :cond_1

    return v2

    :cond_1
    check-cast p1, Lorg/nameless/gamespace/data/SessionState;

    iget-object v1, p0, Lorg/nameless/gamespace/data/SessionState;->packageName:Ljava/lang/String;

    iget-object v3, p1, Lorg/nameless/gamespace/data/SessionState;->packageName:Ljava/lang/String;

    invoke-static {v1, v3}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_2

    return v2

    :cond_2
    iget-object v1, p0, Lorg/nameless/gamespace/data/SessionState;->autoBrightness:Ljava/lang/Boolean;

    iget-object v3, p1, Lorg/nameless/gamespace/data/SessionState;->autoBrightness:Ljava/lang/Boolean;

    invoke-static {v1, v3}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_3

    return v2

    :cond_3
    iget-object v1, p0, Lorg/nameless/gamespace/data/SessionState;->threeScreenshot:Ljava/lang/Boolean;

    iget-object v3, p1, Lorg/nameless/gamespace/data/SessionState;->threeScreenshot:Ljava/lang/Boolean;

    invoke-static {v1, v3}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_4

    return v2

    :cond_4
    iget-object v1, p0, Lorg/nameless/gamespace/data/SessionState;->headsUp:Ljava/lang/Boolean;

    iget-object v3, p1, Lorg/nameless/gamespace/data/SessionState;->headsUp:Ljava/lang/Boolean;

    invoke-static {v1, v3}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_5

    return v2

    :cond_5
    iget v1, p0, Lorg/nameless/gamespace/data/SessionState;->ringerMode:I

    iget v3, p1, Lorg/nameless/gamespace/data/SessionState;->ringerMode:I

    if-eq v1, v3, :cond_6

    return v2

    :cond_6
    iget-object p0, p0, Lorg/nameless/gamespace/data/SessionState;->adbEnabled:Ljava/lang/Boolean;

    iget-object p1, p1, Lorg/nameless/gamespace/data/SessionState;->adbEnabled:Ljava/lang/Boolean;

    invoke-static {p0, p1}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_7

    return v2

    :cond_7
    return v0
.end method

.method public final getAdbEnabled()Ljava/lang/Boolean;
    .locals 0

    .line 29
    iget-object p0, p0, Lorg/nameless/gamespace/data/SessionState;->adbEnabled:Ljava/lang/Boolean;

    return-object p0
.end method

.method public final getAutoBrightness()Ljava/lang/Boolean;
    .locals 0

    .line 25
    iget-object p0, p0, Lorg/nameless/gamespace/data/SessionState;->autoBrightness:Ljava/lang/Boolean;

    return-object p0
.end method

.method public final getHeadsUp()Ljava/lang/Boolean;
    .locals 0

    .line 27
    iget-object p0, p0, Lorg/nameless/gamespace/data/SessionState;->headsUp:Ljava/lang/Boolean;

    return-object p0
.end method

.method public final getPackageName()Ljava/lang/String;
    .locals 0

    .line 24
    iget-object p0, p0, Lorg/nameless/gamespace/data/SessionState;->packageName:Ljava/lang/String;

    return-object p0
.end method

.method public final getRingerMode()I
    .locals 0

    .line 28
    iget p0, p0, Lorg/nameless/gamespace/data/SessionState;->ringerMode:I

    return p0
.end method

.method public final getThreeScreenshot()Ljava/lang/Boolean;
    .locals 0

    .line 26
    iget-object p0, p0, Lorg/nameless/gamespace/data/SessionState;->threeScreenshot:Ljava/lang/Boolean;

    return-object p0
.end method

.method public hashCode()I
    .locals 3

    iget-object v0, p0, Lorg/nameless/gamespace/data/SessionState;->packageName:Ljava/lang/String;

    invoke-virtual {v0}, Ljava/lang/String;->hashCode()I

    move-result v0

    mul-int/lit8 v0, v0, 0x1f

    iget-object v1, p0, Lorg/nameless/gamespace/data/SessionState;->autoBrightness:Ljava/lang/Boolean;

    const/4 v2, 0x0

    if-nez v1, :cond_0

    move v1, v2

    goto :goto_0

    :cond_0
    invoke-virtual {v1}, Ljava/lang/Object;->hashCode()I

    move-result v1

    :goto_0
    add-int/2addr v0, v1

    mul-int/lit8 v0, v0, 0x1f

    iget-object v1, p0, Lorg/nameless/gamespace/data/SessionState;->threeScreenshot:Ljava/lang/Boolean;

    if-nez v1, :cond_1

    move v1, v2

    goto :goto_1

    :cond_1
    invoke-virtual {v1}, Ljava/lang/Object;->hashCode()I

    move-result v1

    :goto_1
    add-int/2addr v0, v1

    mul-int/lit8 v0, v0, 0x1f

    iget-object v1, p0, Lorg/nameless/gamespace/data/SessionState;->headsUp:Ljava/lang/Boolean;

    if-nez v1, :cond_2

    move v1, v2

    goto :goto_2

    :cond_2
    invoke-virtual {v1}, Ljava/lang/Object;->hashCode()I

    move-result v1

    :goto_2
    add-int/2addr v0, v1

    mul-int/lit8 v0, v0, 0x1f

    iget v1, p0, Lorg/nameless/gamespace/data/SessionState;->ringerMode:I

    invoke-static {v1}, Ljava/lang/Integer;->hashCode(I)I

    move-result v1

    add-int/2addr v0, v1

    mul-int/lit8 v0, v0, 0x1f

    iget-object p0, p0, Lorg/nameless/gamespace/data/SessionState;->adbEnabled:Ljava/lang/Boolean;

    if-nez p0, :cond_3

    goto :goto_3

    :cond_3
    invoke-virtual {p0}, Ljava/lang/Object;->hashCode()I

    move-result v2

    :goto_3
    add-int/2addr v0, v2

    return v0
.end method

.method public final setAdbEnabled(Ljava/lang/Boolean;)V
    .locals 0

    .line 29
    iput-object p1, p0, Lorg/nameless/gamespace/data/SessionState;->adbEnabled:Ljava/lang/Boolean;

    return-void
.end method

.method public final setAutoBrightness(Ljava/lang/Boolean;)V
    .locals 0

    .line 25
    iput-object p1, p0, Lorg/nameless/gamespace/data/SessionState;->autoBrightness:Ljava/lang/Boolean;

    return-void
.end method

.method public final setHeadsUp(Ljava/lang/Boolean;)V
    .locals 0

    .line 27
    iput-object p1, p0, Lorg/nameless/gamespace/data/SessionState;->headsUp:Ljava/lang/Boolean;

    return-void
.end method

.method public final setPackageName(Ljava/lang/String;)V
    .locals 1

    const-string v0, "<set-?>"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 24
    iput-object p1, p0, Lorg/nameless/gamespace/data/SessionState;->packageName:Ljava/lang/String;

    return-void
.end method

.method public final setRingerMode(I)V
    .locals 0

    .line 28
    iput p1, p0, Lorg/nameless/gamespace/data/SessionState;->ringerMode:I

    return-void
.end method

.method public final setThreeScreenshot(Ljava/lang/Boolean;)V
    .locals 0

    .line 26
    iput-object p1, p0, Lorg/nameless/gamespace/data/SessionState;->threeScreenshot:Ljava/lang/Boolean;

    return-void
.end method

.method public toString()Ljava/lang/String;
    .locals 7

    iget-object v0, p0, Lorg/nameless/gamespace/data/SessionState;->packageName:Ljava/lang/String;

    iget-object v1, p0, Lorg/nameless/gamespace/data/SessionState;->autoBrightness:Ljava/lang/Boolean;

    iget-object v2, p0, Lorg/nameless/gamespace/data/SessionState;->threeScreenshot:Ljava/lang/Boolean;

    iget-object v3, p0, Lorg/nameless/gamespace/data/SessionState;->headsUp:Ljava/lang/Boolean;

    iget v4, p0, Lorg/nameless/gamespace/data/SessionState;->ringerMode:I

    iget-object p0, p0, Lorg/nameless/gamespace/data/SessionState;->adbEnabled:Ljava/lang/Boolean;

    new-instance v5, Ljava/lang/StringBuilder;

    invoke-direct {v5}, Ljava/lang/StringBuilder;-><init>()V

    const-string v6, "SessionState(packageName="

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v5, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v0, ", autoBrightness="

    invoke-virtual {v5, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v5, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v0, ", threeScreenshot="

    invoke-virtual {v5, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v5, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v0, ", headsUp="

    invoke-virtual {v5, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v5, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v0, ", ringerMode="

    invoke-virtual {v5, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v5, v4}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v0, ", adbEnabled="

    invoke-virtual {v5, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v5, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string p0, ")"

    invoke-virtual {v5, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method
