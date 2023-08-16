.class final Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl;
.super Lorg/nameless/gamespace/GameSpace_HiltComponents$ActivityRetainedC;
.source "DaggerGameSpace_HiltComponents_SingletonC.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x12
    name = "ActivityRetainedCImpl"
.end annotation

.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl$ActivityCImpl;,
        Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl$ActivityCBuilder;
    }
.end annotation


# instance fields
.field private lifecycleProvider:Ljavax/inject/Provider;

.field final synthetic this$0:Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;


# direct methods
.method private constructor <init>(Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;)V
    .locals 0

    .line 177
    iput-object p1, p0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl;->this$0:Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;

    invoke-direct {p0}, Lorg/nameless/gamespace/GameSpace_HiltComponents$ActivityRetainedC;-><init>()V

    .line 179
    invoke-direct {p0}, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl;->initialize()V

    return-void
.end method

.method synthetic constructor <init>(Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl-IA;)V
    .locals 0

    invoke-direct {p0, p1}, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl;-><init>(Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;)V

    return-void
.end method

.method private initialize()V
    .locals 1

    .line 184
    invoke-static {}, Ldagger/hilt/android/internal/managers/ActivityRetainedComponentManager_Lifecycle_Factory;->create()Ldagger/hilt/android/internal/managers/ActivityRetainedComponentManager_Lifecycle_Factory;

    move-result-object v0

    invoke-static {v0}, Ldagger/internal/DoubleCheck;->provider(Ljavax/inject/Provider;)Ljavax/inject/Provider;

    move-result-object v0

    iput-object v0, p0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl;->lifecycleProvider:Ljavax/inject/Provider;

    return-void
.end method


# virtual methods
.method public activityComponentBuilder()Ldagger/hilt/android/internal/builders/ActivityComponentBuilder;
    .locals 2

    .line 189
    new-instance v0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl$ActivityCBuilder;

    const/4 v1, 0x0

    invoke-direct {v0, p0, v1}, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl$ActivityCBuilder;-><init>(Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl;Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl$ActivityCBuilder-IA;)V

    return-object v0
.end method

.method public getActivityRetainedLifecycle()Ldagger/hilt/android/ActivityRetainedLifecycle;
    .locals 0

    .line 194
    iget-object p0, p0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl;->lifecycleProvider:Ljavax/inject/Provider;

    invoke-interface {p0}, Ljavax/inject/Provider;->get()Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ldagger/hilt/android/ActivityRetainedLifecycle;

    return-object p0
.end method
