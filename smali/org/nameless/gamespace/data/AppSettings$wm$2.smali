.class final Lorg/nameless/gamespace/data/AppSettings$wm$2;
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
        "Landroid/view/WindowManager;",
        ">;"
    }
.end annotation


# instance fields
.field final synthetic this$0:Lorg/nameless/gamespace/data/AppSettings;


# direct methods
.method constructor <init>(Lorg/nameless/gamespace/data/AppSettings;)V
    .locals 0

    iput-object p1, p0, Lorg/nameless/gamespace/data/AppSettings$wm$2;->this$0:Lorg/nameless/gamespace/data/AppSettings;

    const/4 p1, 0x0

    invoke-direct {p0, p1}, Lkotlin/jvm/internal/Lambda;-><init>(I)V

    return-void
.end method


# virtual methods
.method public final invoke()Landroid/view/WindowManager;
    .locals 1

    .line 30
    iget-object p0, p0, Lorg/nameless/gamespace/data/AppSettings$wm$2;->this$0:Lorg/nameless/gamespace/data/AppSettings;

    invoke-static {p0}, Lorg/nameless/gamespace/data/AppSettings;->access$getContext$p(Lorg/nameless/gamespace/data/AppSettings;)Landroid/content/Context;

    move-result-object p0

    const-string v0, "window"

    invoke-virtual {p0, v0}, Landroid/content/Context;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object p0

    const-string v0, "null cannot be cast to non-null type android.view.WindowManager"

    invoke-static {p0, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNull(Ljava/lang/Object;Ljava/lang/String;)V

    check-cast p0, Landroid/view/WindowManager;

    return-object p0
.end method

.method public bridge synthetic invoke()Ljava/lang/Object;
    .locals 0

    .line 30
    invoke-virtual {p0}, Lorg/nameless/gamespace/data/AppSettings$wm$2;->invoke()Landroid/view/WindowManager;

    move-result-object p0

    return-object p0
.end method
