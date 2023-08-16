.class final Lorg/nameless/gamespace/gamebar/GameBarService$onCreate$1;
.super Ljava/lang/Object;
.source "GameBarService.kt"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lorg/nameless/gamespace/gamebar/GameBarService;->onCreate()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x18
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lorg/nameless/gamespace/gamebar/GameBarService;


# direct methods
.method constructor <init>(Lorg/nameless/gamespace/gamebar/GameBarService;)V
    .locals 0

    iput-object p1, p0, Lorg/nameless/gamespace/gamebar/GameBarService$onCreate$1;->this$0:Lorg/nameless/gamespace/gamebar/GameBarService;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public final run()V
    .locals 1

    .line 146
    sget-object v0, Lorg/nameless/gamespace/widget/QuickLaunchAppViewCache;->INSTANCE:Lorg/nameless/gamespace/widget/QuickLaunchAppViewCache;

    iget-object p0, p0, Lorg/nameless/gamespace/gamebar/GameBarService$onCreate$1;->this$0:Lorg/nameless/gamespace/gamebar/GameBarService;

    invoke-virtual {v0, p0}, Lorg/nameless/gamespace/widget/QuickLaunchAppViewCache;->updateAppList(Landroid/content/Context;)V

    return-void
.end method
