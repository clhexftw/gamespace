.class final Lorg/nameless/gamespace/data/AppSettings$db$2;
.super Lkotlin/jvm/internal/Lambda;
.source "AppSettings.kt"

# interfaces
.implements Lkotlin/jvm/functions/Function0;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lorg/nameless/gamespace/data/AppSettings;-><init>(Landroid/content/Context;)V
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
.field final synthetic this$0:Lorg/nameless/gamespace/data/AppSettings;


# direct methods
.method constructor <init>(Lorg/nameless/gamespace/data/AppSettings;)V
    .locals 0

    iput-object p1, p0, Lorg/nameless/gamespace/data/AppSettings$db$2;->this$0:Lorg/nameless/gamespace/data/AppSettings;

    const/4 p1, 0x0

    invoke-direct {p0, p1}, Lkotlin/jvm/internal/Lambda;-><init>(I)V

    return-void
.end method


# virtual methods
.method public final invoke()Landroid/content/SharedPreferences;
    .locals 0

    .line 29
    iget-object p0, p0, Lorg/nameless/gamespace/data/AppSettings$db$2;->this$0:Lorg/nameless/gamespace/data/AppSettings;

    invoke-static {p0}, Lorg/nameless/gamespace/data/AppSettings;->access$getContext$p(Lorg/nameless/gamespace/data/AppSettings;)Landroid/content/Context;

    move-result-object p0

    invoke-static {p0}, Landroidx/preference/PreferenceManager;->getDefaultSharedPreferences(Landroid/content/Context;)Landroid/content/SharedPreferences;

    move-result-object p0

    return-object p0
.end method

.method public bridge synthetic invoke()Ljava/lang/Object;
    .locals 0

    .line 29
    invoke-virtual {p0}, Lorg/nameless/gamespace/data/AppSettings$db$2;->invoke()Landroid/content/SharedPreferences;

    move-result-object p0

    return-object p0
.end method
