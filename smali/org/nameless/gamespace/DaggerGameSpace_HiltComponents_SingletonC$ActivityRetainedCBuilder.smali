.class final Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCBuilder;
.super Ljava/lang/Object;
.source "DaggerGameSpace_HiltComponents_SingletonC.java"

# interfaces
.implements Ldagger/hilt/android/internal/builders/ActivityRetainedComponentBuilder;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x12
    name = "ActivityRetainedCBuilder"
.end annotation


# instance fields
.field final synthetic this$0:Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;


# direct methods
.method private constructor <init>(Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;)V
    .locals 0

    .line 166
    iput-object p1, p0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCBuilder;->this$0:Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method synthetic constructor <init>(Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCBuilder-IA;)V
    .locals 0

    invoke-direct {p0, p1}, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCBuilder;-><init>(Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;)V

    return-void
.end method


# virtual methods
.method public bridge synthetic build()Ldagger/hilt/android/components/ActivityRetainedComponent;
    .locals 0

    .line 166
    invoke-virtual {p0}, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCBuilder;->build()Lorg/nameless/gamespace/GameSpace_HiltComponents$ActivityRetainedC;

    move-result-object p0

    return-object p0
.end method

.method public build()Lorg/nameless/gamespace/GameSpace_HiltComponents$ActivityRetainedC;
    .locals 2

    .line 169
    new-instance v0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl;

    iget-object p0, p0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCBuilder;->this$0:Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;

    const/4 v1, 0x0

    invoke-direct {v0, p0, v1}, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl;-><init>(Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl-IA;)V

    return-object v0
.end method
