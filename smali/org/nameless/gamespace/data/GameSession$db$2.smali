.class final Lorg/nameless/gamespace/data/GameSession$db$2;
.super Lkotlin/jvm/internal/Lambda;
.source "GameSession.kt"

# interfaces
.implements Lkotlin/jvm/functions/Function0;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lorg/nameless/gamespace/data/GameSession;-><init>(Landroid/content/Context;Lorg/nameless/gamespace/data/AppSettings;Lorg/nameless/gamespace/data/SystemSettings;Lcom/google/gson/Gson;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x18
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lkotlin/jvm/internal/Lambda;",
        "Lkotlin/jvm/functions/Function0<",
        "Landroid/content/SharedPreferences;",
        ">;"
    }
.end annotation


# instance fields
.field final synthetic this$0:Lorg/nameless/gamespace/data/GameSession;


# direct methods
.method constructor <init>(Lorg/nameless/gamespace/data/GameSession;)V
    .locals 0

    iput-object p1, p0, Lorg/nameless/gamespace/data/GameSession$db$2;->this$0:Lorg/nameless/gamespace/data/GameSession;

    const/4 p1, 0x0

    invoke-direct {p0, p1}, Lkotlin/jvm/internal/Lambda;-><init>(I)V

    return-void
.end method


# virtual methods
.method public final invoke()Landroid/content/SharedPreferences;
    .locals 2

    .line 31
    iget-object p0, p0, Lorg/nameless/gamespace/data/GameSession$db$2;->this$0:Lorg/nameless/gamespace/data/GameSession;

    invoke-static {p0}, Lorg/nameless/gamespace/data/GameSession;->access$getContext$p(Lorg/nameless/gamespace/data/GameSession;)Landroid/content/Context;

    move-result-object p0

    const-string v0, "persisted_session"

    const/4 v1, 0x0

    invoke-virtual {p0, v0, v1}, Landroid/content/Context;->getSharedPreferences(Ljava/lang/String;I)Landroid/content/SharedPreferences;

    move-result-object p0

    return-object p0
.end method

.method public bridge synthetic invoke()Ljava/lang/Object;
    .locals 0

    .line 31
    invoke-virtual {p0}, Lorg/nameless/gamespace/data/GameSession$db$2;->invoke()Landroid/content/SharedPreferences;

    move-result-object p0

    return-object p0
.end method
