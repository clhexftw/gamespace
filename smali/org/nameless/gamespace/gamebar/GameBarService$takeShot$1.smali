.class final Lorg/nameless/gamespace/gamebar/GameBarService$takeShot$1;
.super Lkotlin/jvm/internal/Lambda;
.source "GameBarService.kt"

# interfaces
.implements Lkotlin/jvm/functions/Function1;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lorg/nameless/gamespace/gamebar/GameBarService;->takeShot()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x18
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lkotlin/jvm/internal/Lambda;",
        "Lkotlin/jvm/functions/Function1<",
        "Landroid/view/WindowManager$LayoutParams;",
        "Lkotlin/Unit;",
        ">;"
    }
.end annotation


# static fields
.field public static final INSTANCE:Lorg/nameless/gamespace/gamebar/GameBarService$takeShot$1;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    new-instance v0, Lorg/nameless/gamespace/gamebar/GameBarService$takeShot$1;

    invoke-direct {v0}, Lorg/nameless/gamespace/gamebar/GameBarService$takeShot$1;-><init>()V

    sput-object v0, Lorg/nameless/gamespace/gamebar/GameBarService$takeShot$1;->INSTANCE:Lorg/nameless/gamespace/gamebar/GameBarService$takeShot$1;

    return-void
.end method

.method constructor <init>()V
    .locals 1

    const/4 v0, 0x1

    invoke-direct {p0, v0}, Lkotlin/jvm/internal/Lambda;-><init>(I)V

    return-void
.end method


# virtual methods
.method public bridge synthetic invoke(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    .line 329
    check-cast p1, Landroid/view/WindowManager$LayoutParams;

    invoke-virtual {p0, p1}, Lorg/nameless/gamespace/gamebar/GameBarService$takeShot$1;->invoke(Landroid/view/WindowManager$LayoutParams;)V

    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0
.end method

.method public final invoke(Landroid/view/WindowManager$LayoutParams;)V
    .locals 0

    const-string p0, "it"

    invoke-static {p1, p0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    const/4 p0, 0x0

    .line 329
    iput p0, p1, Landroid/view/WindowManager$LayoutParams;->alpha:F

    return-void
.end method
