.class final Lorg/nameless/gamespace/utils/di/MainModule_ProvideBaseGsonFactory$InstanceHolder;
.super Ljava/lang/Object;
.source "MainModule_ProvideBaseGsonFactory.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/nameless/gamespace/utils/di/MainModule_ProvideBaseGsonFactory;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x1a
    name = "InstanceHolder"
.end annotation


# static fields
.field private static final INSTANCE:Lorg/nameless/gamespace/utils/di/MainModule_ProvideBaseGsonFactory;


# direct methods
.method static bridge synthetic -$$Nest$sfgetINSTANCE()Lorg/nameless/gamespace/utils/di/MainModule_ProvideBaseGsonFactory;
    .locals 1

    sget-object v0, Lorg/nameless/gamespace/utils/di/MainModule_ProvideBaseGsonFactory$InstanceHolder;->INSTANCE:Lorg/nameless/gamespace/utils/di/MainModule_ProvideBaseGsonFactory;

    return-object v0
.end method

.method static constructor <clinit>()V
    .locals 1

    .line 33
    new-instance v0, Lorg/nameless/gamespace/utils/di/MainModule_ProvideBaseGsonFactory;

    invoke-direct {v0}, Lorg/nameless/gamespace/utils/di/MainModule_ProvideBaseGsonFactory;-><init>()V

    sput-object v0, Lorg/nameless/gamespace/utils/di/MainModule_ProvideBaseGsonFactory$InstanceHolder;->INSTANCE:Lorg/nameless/gamespace/utils/di/MainModule_ProvideBaseGsonFactory;

    return-void
.end method
