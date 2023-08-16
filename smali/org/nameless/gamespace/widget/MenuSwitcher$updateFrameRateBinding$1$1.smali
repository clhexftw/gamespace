.class final synthetic Lorg/nameless/gamespace/widget/MenuSwitcher$updateFrameRateBinding$1$1;
.super Ljava/lang/Object;
.source "MenuSwitcher.kt"

# interfaces
.implements Ljava/util/concurrent/Executor;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lorg/nameless/gamespace/widget/MenuSwitcher;->updateFrameRateBinding()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x1000
    name = null
.end annotation


# static fields
.field public static final INSTANCE:Lorg/nameless/gamespace/widget/MenuSwitcher$updateFrameRateBinding$1$1;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    new-instance v0, Lorg/nameless/gamespace/widget/MenuSwitcher$updateFrameRateBinding$1$1;

    invoke-direct {v0}, Lorg/nameless/gamespace/widget/MenuSwitcher$updateFrameRateBinding$1$1;-><init>()V

    sput-object v0, Lorg/nameless/gamespace/widget/MenuSwitcher$updateFrameRateBinding$1$1;->INSTANCE:Lorg/nameless/gamespace/widget/MenuSwitcher$updateFrameRateBinding$1$1;

    return-void
.end method

.method constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public final execute(Ljava/lang/Runnable;)V
    .locals 0

    const-string p0, "p0"

    invoke-static {p1, p0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 80
    invoke-interface {p1}, Ljava/lang/Runnable;->run()V

    return-void
.end method
