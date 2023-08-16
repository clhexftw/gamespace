.class final Lorg/nameless/gamespace/preferences/AppListPreferences$unregisterApp$1;
.super Ljava/lang/Object;
.source "AppListPreferences.kt"

# interfaces
.implements Ljava/util/function/Predicate;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lorg/nameless/gamespace/preferences/AppListPreferences;->unregisterApp(Ljava/lang/String;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x18
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "<T:",
        "Ljava/lang/Object;",
        ">",
        "Ljava/lang/Object;",
        "Ljava/util/function/Predicate;"
    }
.end annotation


# instance fields
.field final synthetic $packageName:Ljava/lang/String;


# direct methods
.method constructor <init>(Ljava/lang/String;)V
    .locals 0

    iput-object p1, p0, Lorg/nameless/gamespace/preferences/AppListPreferences$unregisterApp$1;->$packageName:Ljava/lang/String;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public bridge synthetic test(Ljava/lang/Object;)Z
    .locals 0

    .line 104
    check-cast p1, Lorg/nameless/gamespace/data/UserGame;

    invoke-virtual {p0, p1}, Lorg/nameless/gamespace/preferences/AppListPreferences$unregisterApp$1;->test(Lorg/nameless/gamespace/data/UserGame;)Z

    move-result p0

    return p0
.end method

.method public final test(Lorg/nameless/gamespace/data/UserGame;)Z
    .locals 1

    const-string v0, "it"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 104
    invoke-virtual {p1}, Lorg/nameless/gamespace/data/UserGame;->getPackageName()Ljava/lang/String;

    move-result-object p1

    iget-object p0, p0, Lorg/nameless/gamespace/preferences/AppListPreferences$unregisterApp$1;->$packageName:Ljava/lang/String;

    invoke-static {p1, p0}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result p0

    return p0
.end method
