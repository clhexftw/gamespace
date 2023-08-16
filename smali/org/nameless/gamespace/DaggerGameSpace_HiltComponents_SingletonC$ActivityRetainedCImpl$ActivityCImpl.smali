.class final Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl$ActivityCImpl;
.super Lorg/nameless/gamespace/GameSpace_HiltComponents$ActivityC;
.source "DaggerGameSpace_HiltComponents_SingletonC.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x12
    name = "ActivityCImpl"
.end annotation

.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl$ActivityCImpl$FragmentCI;,
        Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl$ActivityCImpl$FragmentCBuilder;
    }
.end annotation


# instance fields
.field final synthetic this$1:Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl;


# direct methods
.method private constructor <init>(Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl;Landroid/app/Activity;)V
    .locals 0

    .line 214
    iput-object p1, p0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl$ActivityCImpl;->this$1:Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl;

    invoke-direct {p0}, Lorg/nameless/gamespace/GameSpace_HiltComponents$ActivityC;-><init>()V

    return-void
.end method

.method synthetic constructor <init>(Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl;Landroid/app/Activity;Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl$ActivityCImpl-IA;)V
    .locals 0

    invoke-direct {p0, p1, p2}, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl$ActivityCImpl;-><init>(Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl;Landroid/app/Activity;)V

    return-void
.end method


# virtual methods
.method public fragmentComponentBuilder()Ldagger/hilt/android/internal/builders/FragmentComponentBuilder;
    .locals 2

    .line 235
    new-instance v0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl$ActivityCImpl$FragmentCBuilder;

    const/4 v1, 0x0

    invoke-direct {v0, p0, v1}, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl$ActivityCImpl$FragmentCBuilder;-><init>(Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl$ActivityCImpl;Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl$ActivityCImpl$FragmentCBuilder-IA;)V

    return-object v0
.end method

.method public injectAppSelectorActivity(Lorg/nameless/gamespace/preferences/appselector/AppSelectorActivity;)V
    .locals 0

    return-void
.end method

.method public injectPerAppSettingsActivity(Lorg/nameless/gamespace/settings/PerAppSettingsActivity;)V
    .locals 0

    return-void
.end method

.method public injectSettingsActivity(Lorg/nameless/gamespace/settings/SettingsActivity;)V
    .locals 0

    return-void
.end method
