.class final Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ServiceCBuilder;
.super Ljava/lang/Object;
.source "DaggerGameSpace_HiltComponents_SingletonC.java"

# interfaces
.implements Ldagger/hilt/android/internal/builders/ServiceComponentBuilder;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x12
    name = "ServiceCBuilder"
.end annotation


# instance fields
.field private service:Landroid/app/Service;

.field final synthetic this$0:Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;


# direct methods
.method private constructor <init>(Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;)V
    .locals 0

    .line 388
    iput-object p1, p0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ServiceCBuilder;->this$0:Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method synthetic constructor <init>(Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ServiceCBuilder-IA;)V
    .locals 0

    invoke-direct {p0, p1}, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ServiceCBuilder;-><init>(Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;)V

    return-void
.end method


# virtual methods
.method public bridge synthetic build()Ldagger/hilt/android/components/ServiceComponent;
    .locals 0

    .line 388
    invoke-virtual {p0}, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ServiceCBuilder;->build()Lorg/nameless/gamespace/GameSpace_HiltComponents$ServiceC;

    move-result-object p0

    return-object p0
.end method

.method public build()Lorg/nameless/gamespace/GameSpace_HiltComponents$ServiceC;
    .locals 3

    .line 399
    iget-object v0, p0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ServiceCBuilder;->service:Landroid/app/Service;

    const-class v1, Landroid/app/Service;

    invoke-static {v0, v1}, Ldagger/internal/Preconditions;->checkBuilderRequirement(Ljava/lang/Object;Ljava/lang/Class;)V

    .line 400
    new-instance v0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ServiceCImpl;

    iget-object v1, p0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ServiceCBuilder;->this$0:Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;

    iget-object p0, p0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ServiceCBuilder;->service:Landroid/app/Service;

    const/4 v2, 0x0

    invoke-direct {v0, v1, p0, v2}, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ServiceCImpl;-><init>(Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;Landroid/app/Service;Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ServiceCImpl-IA;)V

    return-object v0
.end method

.method public bridge synthetic service(Landroid/app/Service;)Ldagger/hilt/android/internal/builders/ServiceComponentBuilder;
    .locals 0

    .line 388
    invoke-virtual {p0, p1}, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ServiceCBuilder;->service(Landroid/app/Service;)Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ServiceCBuilder;

    move-result-object p0

    return-object p0
.end method

.method public service(Landroid/app/Service;)Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ServiceCBuilder;
    .locals 0

    .line 393
    invoke-static {p1}, Ldagger/internal/Preconditions;->checkNotNull(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Landroid/app/Service;

    iput-object p1, p0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ServiceCBuilder;->service:Landroid/app/Service;

    return-object p0
.end method
