.class public final Lorg/nameless/gamespace/gamebar/GameBarService$GameBarBinder;
.super Landroid/os/Binder;
.source "GameBarService.kt"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/nameless/gamespace/gamebar/GameBarService;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x11
    name = "GameBarBinder"
.end annotation


# instance fields
.field final synthetic this$0:Lorg/nameless/gamespace/gamebar/GameBarService;


# direct methods
.method public constructor <init>(Lorg/nameless/gamespace/gamebar/GameBarService;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()V"
        }
    .end annotation

    .line 164
    iput-object p1, p0, Lorg/nameless/gamespace/gamebar/GameBarService$GameBarBinder;->this$0:Lorg/nameless/gamespace/gamebar/GameBarService;

    invoke-direct {p0}, Landroid/os/Binder;-><init>()V

    return-void
.end method


# virtual methods
.method public final getService()Lorg/nameless/gamespace/gamebar/GameBarService;
    .locals 0

    .line 165
    iget-object p0, p0, Lorg/nameless/gamespace/gamebar/GameBarService$GameBarBinder;->this$0:Lorg/nameless/gamespace/gamebar/GameBarService;

    return-object p0
.end method
