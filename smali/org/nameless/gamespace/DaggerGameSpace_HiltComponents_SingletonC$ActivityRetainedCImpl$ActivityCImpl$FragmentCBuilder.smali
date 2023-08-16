.class final Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl$ActivityCImpl$FragmentCBuilder;
.super Ljava/lang/Object;
.source "DaggerGameSpace_HiltComponents_SingletonC.java"

# interfaces
.implements Ldagger/hilt/android/internal/builders/FragmentComponentBuilder;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl$ActivityCImpl;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x12
    name = "FragmentCBuilder"
.end annotation


# instance fields
.field private fragment:Landroidx/fragment/app/Fragment;

.field final synthetic this$2:Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl$ActivityCImpl;


# direct methods
.method private constructor <init>(Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl$ActivityCImpl;)V
    .locals 0

    .line 255
    iput-object p1, p0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl$ActivityCImpl$FragmentCBuilder;->this$2:Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl$ActivityCImpl;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method synthetic constructor <init>(Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl$ActivityCImpl;Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl$ActivityCImpl$FragmentCBuilder-IA;)V
    .locals 0

    invoke-direct {p0, p1}, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl$ActivityCImpl$FragmentCBuilder;-><init>(Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl$ActivityCImpl;)V

    return-void
.end method


# virtual methods
.method public bridge synthetic build()Ldagger/hilt/android/components/FragmentComponent;
    .locals 0

    .line 255
    invoke-virtual {p0}, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl$ActivityCImpl$FragmentCBuilder;->build()Lorg/nameless/gamespace/GameSpace_HiltComponents$FragmentC;

    move-result-object p0

    return-object p0
.end method

.method public build()Lorg/nameless/gamespace/GameSpace_HiltComponents$FragmentC;
    .locals 3

    .line 266
    iget-object v0, p0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl$ActivityCImpl$FragmentCBuilder;->fragment:Landroidx/fragment/app/Fragment;

    const-class v1, Landroidx/fragment/app/Fragment;

    invoke-static {v0, v1}, Ldagger/internal/Preconditions;->checkBuilderRequirement(Ljava/lang/Object;Ljava/lang/Class;)V

    .line 267
    new-instance v0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl$ActivityCImpl$FragmentCI;

    iget-object v1, p0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl$ActivityCImpl$FragmentCBuilder;->this$2:Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl$ActivityCImpl;

    iget-object p0, p0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl$ActivityCImpl$FragmentCBuilder;->fragment:Landroidx/fragment/app/Fragment;

    const/4 v2, 0x0

    invoke-direct {v0, v1, p0, v2}, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl$ActivityCImpl$FragmentCI;-><init>(Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl$ActivityCImpl;Landroidx/fragment/app/Fragment;Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl$ActivityCImpl$FragmentCI-IA;)V

    return-object v0
.end method

.method public bridge synthetic fragment(Landroidx/fragment/app/Fragment;)Ldagger/hilt/android/internal/builders/FragmentComponentBuilder;
    .locals 0

    .line 255
    invoke-virtual {p0, p1}, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl$ActivityCImpl$FragmentCBuilder;->fragment(Landroidx/fragment/app/Fragment;)Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl$ActivityCImpl$FragmentCBuilder;

    move-result-object p0

    return-object p0
.end method

.method public fragment(Landroidx/fragment/app/Fragment;)Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl$ActivityCImpl$FragmentCBuilder;
    .locals 0

    .line 260
    invoke-static {p1}, Ldagger/internal/Preconditions;->checkNotNull(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Landroidx/fragment/app/Fragment;

    iput-object p1, p0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl$ActivityCImpl$FragmentCBuilder;->fragment:Landroidx/fragment/app/Fragment;

    return-object p0
.end method
