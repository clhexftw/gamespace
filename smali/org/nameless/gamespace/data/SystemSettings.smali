.class public final Lorg/nameless/gamespace/data/SystemSettings;
.super Ljava/lang/Object;
.source "SystemSettings.kt"


# annotations
.annotation system Ldalvik/annotation/SourceDebugExtension;
    value = "SMAP\nSystemSettings.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SystemSettings.kt\norg/nameless/gamespace/data/SystemSettings\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,107:1\n766#2:108\n857#2,2:109\n1549#2:111\n1620#2,3:112\n*S KotlinDebug\n*F\n+ 1 SystemSettings.kt\norg/nameless/gamespace/data/SystemSettings\n*L\n81#1:108\n81#1:109,2\n82#1:111\n82#1:112,3\n*E\n"
.end annotation


# instance fields
.field private final gameModeUtils:Lorg/nameless/gamespace/utils/GameModeUtils;

.field private final resolver:Landroid/content/ContentResolver;


# direct methods
.method public constructor <init>(Landroid/content/Context;Lorg/nameless/gamespace/utils/GameModeUtils;)V
    .locals 1

    const-string v0, "context"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    const-string v0, "gameModeUtils"

    invoke-static {p2, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 25
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 27
    iput-object p2, p0, Lorg/nameless/gamespace/data/SystemSettings;->gameModeUtils:Lorg/nameless/gamespace/utils/GameModeUtils;

    .line 30
    invoke-virtual {p1}, Landroid/content/Context;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object p1

    iput-object p1, p0, Lorg/nameless/gamespace/data/SystemSettings;->resolver:Landroid/content/ContentResolver;

    return-void
.end method

.method private final toInt(Z)I
    .locals 0

    return p1
.end method


# virtual methods
.method public final getAdbEnabled()Z
    .locals 2

    .line 96
    iget-object p0, p0, Lorg/nameless/gamespace/data/SystemSettings;->resolver:Landroid/content/ContentResolver;

    const-string v0, "adb_enabled"

    const/4 v1, 0x0

    .line 95
    invoke-static {p0, v0, v1}, Landroid/provider/Settings$Global;->getInt(Landroid/content/ContentResolver;Ljava/lang/String;I)I

    move-result p0

    const/4 v0, 0x1

    if-ne p0, v0, :cond_0

    move v1, v0

    :cond_0
    return v1
.end method

.method public final getAutoBrightness()Z
    .locals 3

    .line 46
    iget-object p0, p0, Lorg/nameless/gamespace/data/SystemSettings;->resolver:Landroid/content/ContentResolver;

    const-string v0, "screen_brightness_mode"

    const/4 v1, 0x1

    const/4 v2, -0x2

    .line 45
    invoke-static {p0, v0, v1, v2}, Landroid/provider/Settings$System;->getIntForUser(Landroid/content/ContentResolver;Ljava/lang/String;II)I

    move-result p0

    if-ne p0, v1, :cond_0

    goto :goto_0

    :cond_0
    const/4 v1, 0x0

    :goto_0
    return v1
.end method

.method public final getHeadsUp()Z
    .locals 2

    .line 34
    iget-object p0, p0, Lorg/nameless/gamespace/data/SystemSettings;->resolver:Landroid/content/ContentResolver;

    const-string v0, "heads_up_notifications_enabled"

    const/4 v1, 0x1

    invoke-static {p0, v0, v1}, Landroid/provider/Settings$Global;->getInt(Landroid/content/ContentResolver;Ljava/lang/String;I)I

    move-result p0

    if-ne p0, v1, :cond_0

    goto :goto_0

    :cond_0
    const/4 v1, 0x0

    :goto_0
    return v1
.end method

.method public final getThreeScreenshot()Z
    .locals 3

    .line 64
    iget-object p0, p0, Lorg/nameless/gamespace/data/SystemSettings;->resolver:Landroid/content/ContentResolver;

    const-string v0, "three_finger_gesture"

    const/4 v1, 0x0

    const/4 v2, -0x2

    .line 63
    invoke-static {p0, v0, v1, v2}, Landroid/provider/Settings$System;->getIntForUser(Landroid/content/ContentResolver;Ljava/lang/String;II)I

    move-result p0

    const/4 v0, 0x1

    if-ne p0, v0, :cond_0

    move v1, v0

    :cond_0
    return v1
.end method

.method public final getUserGames()Ljava/util/List;
    .locals 8
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/List<",
            "Lorg/nameless/gamespace/data/UserGame;",
            ">;"
        }
    .end annotation

    .line 77
    iget-object p0, p0, Lorg/nameless/gamespace/data/SystemSettings;->resolver:Landroid/content/ContentResolver;

    const-string v0, "gamespace_game_list"

    const/4 v1, -0x2

    .line 76
    invoke-static {p0, v0, v1}, Landroid/provider/Settings$System;->getStringForUser(Landroid/content/ContentResolver;Ljava/lang/String;I)Ljava/lang/String;

    move-result-object v2

    if-eqz v2, :cond_3

    const-string p0, ";"

    .line 80
    filled-new-array {p0}, [Ljava/lang/String;

    move-result-object v3

    const/4 v4, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x6

    const/4 v7, 0x0

    invoke-static/range {v2 .. v7}, Lkotlin/text/StringsKt;->split$default(Ljava/lang/CharSequence;[Ljava/lang/String;ZIILjava/lang/Object;)Ljava/util/List;

    move-result-object p0

    if-eqz p0, :cond_3

    check-cast p0, Ljava/lang/Iterable;

    .line 81
    invoke-static {p0}, Lkotlin/collections/CollectionsKt;->toList(Ljava/lang/Iterable;)Ljava/util/List;

    move-result-object p0

    if-eqz p0, :cond_3

    check-cast p0, Ljava/lang/Iterable;

    .line 766
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    .line 857
    invoke-interface {p0}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :cond_0
    :goto_0
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_2

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    move-object v2, v1

    check-cast v2, Ljava/lang/String;

    .line 81
    invoke-interface {v2}, Ljava/lang/CharSequence;->length()I

    move-result v2

    if-lez v2, :cond_1

    const/4 v2, 0x1

    goto :goto_1

    :cond_1
    const/4 v2, 0x0

    :goto_1
    if-eqz v2, :cond_0

    .line 857
    invoke-interface {v0, v1}, Ljava/util/Collection;->add(Ljava/lang/Object;)Z

    goto :goto_0

    .line 1549
    :cond_2
    new-instance p0, Ljava/util/ArrayList;

    const/16 v1, 0xa

    invoke-static {v0, v1}, Lkotlin/collections/CollectionsKt;->collectionSizeOrDefault(Ljava/lang/Iterable;I)I

    move-result v1

    invoke-direct {p0, v1}, Ljava/util/ArrayList;-><init>(I)V

    .line 1620
    invoke-interface {v0}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :goto_2
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_4

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    .line 1621
    check-cast v1, Ljava/lang/String;

    .line 82
    sget-object v2, Lorg/nameless/gamespace/data/UserGame;->Companion:Lorg/nameless/gamespace/data/UserGame$Companion;

    invoke-virtual {v2, v1}, Lorg/nameless/gamespace/data/UserGame$Companion;->fromSettings(Ljava/lang/String;)Lorg/nameless/gamespace/data/UserGame;

    move-result-object v1

    .line 1621
    invoke-interface {p0, v1}, Ljava/util/Collection;->add(Ljava/lang/Object;)Z

    goto :goto_2

    .line 82
    :cond_3
    invoke-static {}, Lkotlin/collections/CollectionsKt;->emptyList()Ljava/util/List;

    move-result-object p0

    :cond_4
    return-object p0
.end method

.method public final setAdbEnabled(Z)V
    .locals 1

    .line 100
    iget-object v0, p0, Lorg/nameless/gamespace/data/SystemSettings;->resolver:Landroid/content/ContentResolver;

    .line 101
    invoke-direct {p0, p1}, Lorg/nameless/gamespace/data/SystemSettings;->toInt(Z)I

    move-result p0

    const-string p1, "adb_enabled"

    .line 99
    invoke-static {v0, p1, p0}, Landroid/provider/Settings$Global;->putInt(Landroid/content/ContentResolver;Ljava/lang/String;I)Z

    return-void
.end method

.method public final setAutoBrightness(Z)V
    .locals 2

    .line 54
    iget-object p0, p0, Lorg/nameless/gamespace/data/SystemSettings;->resolver:Landroid/content/ContentResolver;

    const/4 v0, -0x2

    const-string v1, "screen_brightness_mode"

    .line 53
    invoke-static {p0, v1, p1, v0}, Landroid/provider/Settings$System;->putIntForUser(Landroid/content/ContentResolver;Ljava/lang/String;II)Z

    return-void
.end method

.method public final setHeadsUp(Z)V
    .locals 1

    .line 37
    iget-object v0, p0, Lorg/nameless/gamespace/data/SystemSettings;->resolver:Landroid/content/ContentResolver;

    .line 39
    invoke-direct {p0, p1}, Lorg/nameless/gamespace/data/SystemSettings;->toInt(Z)I

    move-result p0

    const-string p1, "heads_up_notifications_enabled"

    .line 36
    invoke-static {v0, p1, p0}, Landroid/provider/Settings$Global;->putInt(Landroid/content/ContentResolver;Ljava/lang/String;I)Z

    return-void
.end method

.method public final setThreeScreenshot(Z)V
    .locals 2

    .line 69
    iget-object v0, p0, Lorg/nameless/gamespace/data/SystemSettings;->resolver:Landroid/content/ContentResolver;

    .line 70
    invoke-direct {p0, p1}, Lorg/nameless/gamespace/data/SystemSettings;->toInt(Z)I

    move-result p0

    const-string p1, "three_finger_gesture"

    const/4 v1, -0x2

    .line 68
    invoke-static {v0, p1, p0, v1}, Landroid/provider/Settings$System;->putIntForUser(Landroid/content/ContentResolver;Ljava/lang/String;II)Z

    return-void
.end method

.method public final setUserGames(Ljava/util/List;)V
    .locals 11
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Lorg/nameless/gamespace/data/UserGame;",
            ">;)V"
        }
    .end annotation

    const-string v0, "games"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 85
    iget-object v0, p0, Lorg/nameless/gamespace/data/SystemSettings;->resolver:Landroid/content/ContentResolver;

    .line 87
    invoke-interface {p1}, Ljava/util/List;->isEmpty()Z

    move-result v1

    if-eqz v1, :cond_0

    const-string v1, ""

    goto :goto_0

    .line 88
    :cond_0
    move-object v2, p1

    check-cast v2, Ljava/lang/Iterable;

    const/4 v4, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    const/4 v7, 0x0

    sget-object v8, Lorg/nameless/gamespace/data/SystemSettings$userGames$3;->INSTANCE:Lorg/nameless/gamespace/data/SystemSettings$userGames$3;

    const/16 v9, 0x1e

    const/4 v10, 0x0

    const-string v3, ";"

    invoke-static/range {v2 .. v10}, Lkotlin/collections/CollectionsKt;->joinToString$default(Ljava/lang/Iterable;Ljava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Lkotlin/jvm/functions/Function1;ILjava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    :goto_0
    const/4 v2, -0x2

    const-string v3, "gamespace_game_list"

    .line 84
    invoke-static {v0, v3, v1, v2}, Landroid/provider/Settings$System;->putStringForUser(Landroid/content/ContentResolver;Ljava/lang/String;Ljava/lang/String;I)Z

    .line 91
    iget-object p0, p0, Lorg/nameless/gamespace/data/SystemSettings;->gameModeUtils:Lorg/nameless/gamespace/utils/GameModeUtils;

    check-cast p1, Ljava/util/Collection;

    invoke-interface {p1}, Ljava/util/Collection;->isEmpty()Z

    move-result p1

    xor-int/lit8 p1, p1, 0x1

    invoke-virtual {p0, p1}, Lorg/nameless/gamespace/utils/GameModeUtils;->setupBatteryMode(Z)V

    return-void
.end method
