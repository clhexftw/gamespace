.class final Lorg/nameless/gamespace/data/SystemSettings$userGames$3;
.super Lkotlin/jvm/internal/Lambda;
.source "SystemSettings.kt"

# interfaces
.implements Lkotlin/jvm/functions/Function1;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lorg/nameless/gamespace/data/SystemSettings;->setUserGames(Ljava/util/List;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x18
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lkotlin/jvm/internal/Lambda;",
        "Lkotlin/jvm/functions/Function1<",
        "Lorg/nameless/gamespace/data/UserGame;",
        "Ljava/lang/CharSequence;",
        ">;"
    }
.end annotation


# static fields
.field public static final INSTANCE:Lorg/nameless/gamespace/data/SystemSettings$userGames$3;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    new-instance v0, Lorg/nameless/gamespace/data/SystemSettings$userGames$3;

    invoke-direct {v0}, Lorg/nameless/gamespace/data/SystemSettings$userGames$3;-><init>()V

    sput-object v0, Lorg/nameless/gamespace/data/SystemSettings$userGames$3;->INSTANCE:Lorg/nameless/gamespace/data/SystemSettings$userGames$3;

    return-void
.end method

.method constructor <init>()V
    .locals 1

    const/4 v0, 0x1

    invoke-direct {p0, v0}, Lkotlin/jvm/internal/Lambda;-><init>(I)V

    return-void
.end method


# virtual methods
.method public final invoke(Lorg/nameless/gamespace/data/UserGame;)Ljava/lang/CharSequence;
    .locals 0

    const-string p0, "it"

    invoke-static {p1, p0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 88
    invoke-virtual {p1}, Lorg/nameless/gamespace/data/UserGame;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public bridge synthetic invoke(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    .line 88
    check-cast p1, Lorg/nameless/gamespace/data/UserGame;

    invoke-virtual {p0, p1}, Lorg/nameless/gamespace/data/SystemSettings$userGames$3;->invoke(Lorg/nameless/gamespace/data/UserGame;)Ljava/lang/CharSequence;

    move-result-object p0

    return-object p0
.end method
