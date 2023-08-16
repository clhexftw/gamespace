.class final Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl$ActivityCBuilder;
.super Ljava/lang/Object;
.source "DaggerGameSpace_HiltComponents_SingletonC.java"

# interfaces
.implements Ldagger/hilt/android/internal/builders/ActivityComponentBuilder;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x12
    name = "ActivityCBuilder"
.end annotation


# instance fields
.field private activity:Landroid/app/Activity;

.field final synthetic this$1:Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl;


# direct methods
.method private constructor <init>(Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl;)V
    .locals 0

    .line 197
    iput-object p1, p0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl$ActivityCBuilder;->this$1:Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method synthetic constructor <init>(Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl;Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl$ActivityCBuilder-IA;)V
    .locals 0

    invoke-direct {p0, p1}, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl$ActivityCBuilder;-><init>(Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl;)V

    return-void
.end method


# virtual methods
.method public bridge synthetic activity(Landroid/app/Activity;)Ldagger/hilt/android/internal/builders/ActivityComponentBuilder;
    .locals 0

    .line 197
    invoke-virtual {p0, p1}, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl$ActivityCBuilder;->activity(Landroid/app/Activity;)Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl$ActivityCBuilder;

    move-result-object p0

    return-object p0
.end method

.method public activity(Landroid/app/Activity;)Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl$ActivityCBuilder;
    .locals 0

    .line 202
    invoke-static {p1}, Ldagger/internal/Preconditions;->checkNotNull(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Landroid/app/Activity;

    iput-object p1, p0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl$ActivityCBuilder;->activity:Landroid/app/Activity;

    return-object p0
.end method

.method public bridge synthetic build()Ldagger/hilt/android/components/ActivityComponent;
    .locals 0

    .line 197
    invoke-virtual {p0}, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl$ActivityCBuilder;->build()Lorg/nameless/gamespace/GameSpace_HiltComponents$ActivityC;

    move-result-object p0

    return-object p0
.end method

.method public build()Lorg/nameless/gamespace/GameSpace_HiltComponents$ActivityC;
    .locals 3

    .line 208
    iget-object v0, p0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl$ActivityCBuilder;->activity:Landroid/app/Activity;

    const-class v1, Landroid/app/Activity;

    invoke-static {v0, v1}, Ldagger/internal/Preconditions;->checkBuilderRequirement(Ljava/lang/Object;Ljava/lang/Class;)V

    .line 209
    new-instance v0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl$ActivityCImpl;

    iget-object v1, p0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl$ActivityCBuilder;->this$1:Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl;

    iget-object p0, p0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl$ActivityCBuilder;->activity:Landroid/app/Activity;

    const/4 v2, 0x0

    invoke-direct {v0, v1, p0, v2}, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl$ActivityCImpl;-><init>(Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl;Landroid/app/Activity;Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl$ActivityCImpl-IA;)V

    return-object v0
.end method
