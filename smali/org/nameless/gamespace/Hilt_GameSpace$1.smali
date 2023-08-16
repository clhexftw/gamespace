.class Lorg/nameless/gamespace/Hilt_GameSpace$1;
.super Ljava/lang/Object;
.source "Hilt_GameSpace.java"

# interfaces
.implements Ldagger/hilt/android/internal/managers/ComponentSupplier;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/nameless/gamespace/Hilt_GameSpace;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lorg/nameless/gamespace/Hilt_GameSpace;


# direct methods
.method constructor <init>(Lorg/nameless/gamespace/Hilt_GameSpace;)V
    .locals 0

    .line 19
    iput-object p1, p0, Lorg/nameless/gamespace/Hilt_GameSpace$1;->this$0:Lorg/nameless/gamespace/Hilt_GameSpace;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public get()Ljava/lang/Object;
    .locals 2

    .line 22
    invoke-static {}, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;->builder()Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$Builder;

    move-result-object v0

    new-instance v1, Ldagger/hilt/android/internal/modules/ApplicationContextModule;

    iget-object p0, p0, Lorg/nameless/gamespace/Hilt_GameSpace$1;->this$0:Lorg/nameless/gamespace/Hilt_GameSpace;

    invoke-direct {v1, p0}, Ldagger/hilt/android/internal/modules/ApplicationContextModule;-><init>(Landroid/content/Context;)V

    .line 23
    invoke-virtual {v0, v1}, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$Builder;->applicationContextModule(Ldagger/hilt/android/internal/modules/ApplicationContextModule;)Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$Builder;

    move-result-object p0

    .line 24
    invoke-virtual {p0}, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$Builder;->build()Lorg/nameless/gamespace/GameSpace_HiltComponents$SingletonC;

    move-result-object p0

    return-object p0
.end method
