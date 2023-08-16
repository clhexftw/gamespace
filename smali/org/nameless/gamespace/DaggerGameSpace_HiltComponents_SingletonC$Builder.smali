.class public final Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$Builder;
.super Ljava/lang/Object;
.source "DaggerGameSpace_HiltComponents_SingletonC.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "Builder"
.end annotation


# instance fields
.field private applicationContextModule:Ldagger/hilt/android/internal/modules/ApplicationContextModule;


# direct methods
.method private constructor <init>()V
    .locals 0

    .line 143
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method synthetic constructor <init>(Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$Builder-IA;)V
    .locals 0

    invoke-direct {p0}, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$Builder;-><init>()V

    return-void
.end method


# virtual methods
.method public applicationContextModule(Ldagger/hilt/android/internal/modules/ApplicationContextModule;)Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$Builder;
    .locals 0

    .line 147
    invoke-static {p1}, Ldagger/internal/Preconditions;->checkNotNull(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ldagger/hilt/android/internal/modules/ApplicationContextModule;

    iput-object p1, p0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$Builder;->applicationContextModule:Ldagger/hilt/android/internal/modules/ApplicationContextModule;

    return-object p0
.end method

.method public build()Lorg/nameless/gamespace/GameSpace_HiltComponents$SingletonC;
    .locals 2

    .line 161
    iget-object v0, p0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$Builder;->applicationContextModule:Ldagger/hilt/android/internal/modules/ApplicationContextModule;

    const-class v1, Ldagger/hilt/android/internal/modules/ApplicationContextModule;

    invoke-static {v0, v1}, Ldagger/internal/Preconditions;->checkBuilderRequirement(Ljava/lang/Object;Ljava/lang/Class;)V

    .line 162
    new-instance v0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;

    iget-object p0, p0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$Builder;->applicationContextModule:Ldagger/hilt/android/internal/modules/ApplicationContextModule;

    const/4 v1, 0x0

    invoke-direct {v0, p0, v1}, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;-><init>(Ldagger/hilt/android/internal/modules/ApplicationContextModule;Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC-IA;)V

    return-object v0
.end method
