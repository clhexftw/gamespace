.class final Lorg/nameless/gamespace/preferences/AppListPreferences$gameModeUtils$2;
.super Lkotlin/jvm/internal/Lambda;
.source "AppListPreferences.kt"

# interfaces
.implements Lkotlin/jvm/functions/Function0;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lorg/nameless/gamespace/preferences/AppListPreferences;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x18
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lkotlin/jvm/internal/Lambda;",
        "Lkotlin/jvm/functions/Function0<",
        "Lorg/nameless/gamespace/utils/GameModeUtils;",
        ">;"
    }
.end annotation

.annotation system Ldalvik/annotation/SourceDebugExtension;
    value = "SMAP\nAppListPreferences.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AppListPreferences.kt\norg/nameless/gamespace/preferences/AppListPreferences$gameModeUtils$2\n+ 2 Extensions.kt\norg/nameless/gamespace/utils/ExtensionsKt\n*L\n1#1,143:1\n47#2:144\n*S KotlinDebug\n*F\n+ 1 AppListPreferences.kt\norg/nameless/gamespace/preferences/AppListPreferences$gameModeUtils$2\n*L\n44#1:144\n*E\n"
.end annotation


# instance fields
.field final synthetic $context:Landroid/content/Context;


# direct methods
.method constructor <init>(Landroid/content/Context;)V
    .locals 0

    iput-object p1, p0, Lorg/nameless/gamespace/preferences/AppListPreferences$gameModeUtils$2;->$context:Landroid/content/Context;

    const/4 p1, 0x0

    invoke-direct {p0, p1}, Lkotlin/jvm/internal/Lambda;-><init>(I)V

    return-void
.end method


# virtual methods
.method public bridge synthetic invoke()Ljava/lang/Object;
    .locals 0

    .line 43
    invoke-virtual {p0}, Lorg/nameless/gamespace/preferences/AppListPreferences$gameModeUtils$2;->invoke()Lorg/nameless/gamespace/utils/GameModeUtils;

    move-result-object p0

    return-object p0
.end method

.method public final invoke()Lorg/nameless/gamespace/utils/GameModeUtils;
    .locals 1

    .line 44
    iget-object p0, p0, Lorg/nameless/gamespace/preferences/AppListPreferences$gameModeUtils$2;->$context:Landroid/content/Context;

    .line 47
    invoke-virtual {p0}, Landroid/content/Context;->getApplicationContext()Landroid/content/Context;

    move-result-object p0

    const-class v0, Lorg/nameless/gamespace/utils/di/ServiceViewEntryPoint;

    invoke-static {p0, v0}, Ldagger/hilt/EntryPoints;->get(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object p0

    const-string v0, "get(applicationContext, T::class.java)"

    invoke-static {p0, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullExpressionValue(Ljava/lang/Object;Ljava/lang/String;)V

    check-cast p0, Lorg/nameless/gamespace/utils/di/ServiceViewEntryPoint;

    .line 44
    invoke-interface {p0}, Lorg/nameless/gamespace/utils/di/ServiceViewEntryPoint;->gameModeUtils()Lorg/nameless/gamespace/utils/GameModeUtils;

    move-result-object p0

    return-object p0
.end method
