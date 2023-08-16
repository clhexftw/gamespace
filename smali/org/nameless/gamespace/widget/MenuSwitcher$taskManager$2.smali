.class final Lorg/nameless/gamespace/widget/MenuSwitcher$taskManager$2;
.super Lkotlin/jvm/internal/Lambda;
.source "MenuSwitcher.kt"

# interfaces
.implements Lkotlin/jvm/functions/Function0;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lorg/nameless/gamespace/widget/MenuSwitcher;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x18
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lkotlin/jvm/internal/Lambda;",
        "Lkotlin/jvm/functions/Function0<",
        "Landroid/app/IActivityTaskManager;",
        ">;"
    }
.end annotation


# static fields
.field public static final INSTANCE:Lorg/nameless/gamespace/widget/MenuSwitcher$taskManager$2;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    new-instance v0, Lorg/nameless/gamespace/widget/MenuSwitcher$taskManager$2;

    invoke-direct {v0}, Lorg/nameless/gamespace/widget/MenuSwitcher$taskManager$2;-><init>()V

    sput-object v0, Lorg/nameless/gamespace/widget/MenuSwitcher$taskManager$2;->INSTANCE:Lorg/nameless/gamespace/widget/MenuSwitcher$taskManager$2;

    return-void
.end method

.method constructor <init>()V
    .locals 1

    const/4 v0, 0x0

    invoke-direct {p0, v0}, Lkotlin/jvm/internal/Lambda;-><init>(I)V

    return-void
.end method


# virtual methods
.method public final invoke()Landroid/app/IActivityTaskManager;
    .locals 0

    .line 32
    invoke-static {}, Landroid/app/ActivityTaskManager;->getService()Landroid/app/IActivityTaskManager;

    move-result-object p0

    return-object p0
.end method

.method public bridge synthetic invoke()Ljava/lang/Object;
    .locals 0

    .line 32
    invoke-virtual {p0}, Lorg/nameless/gamespace/widget/MenuSwitcher$taskManager$2;->invoke()Landroid/app/IActivityTaskManager;

    move-result-object p0

    return-object p0
.end method
