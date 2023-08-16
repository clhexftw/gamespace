.class final Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl$ActivityCImpl$FragmentCI;
.super Lorg/nameless/gamespace/GameSpace_HiltComponents$FragmentC;
.source "DaggerGameSpace_HiltComponents_SingletonC.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl$ActivityCImpl;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x12
    name = "FragmentCI"
.end annotation


# instance fields
.field final synthetic this$2:Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl$ActivityCImpl;


# direct methods
.method private constructor <init>(Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl$ActivityCImpl;Landroidx/fragment/app/Fragment;)V
    .locals 0

    .line 272
    iput-object p1, p0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl$ActivityCImpl$FragmentCI;->this$2:Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl$ActivityCImpl;

    invoke-direct {p0}, Lorg/nameless/gamespace/GameSpace_HiltComponents$FragmentC;-><init>()V

    return-void
.end method

.method synthetic constructor <init>(Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl$ActivityCImpl;Landroidx/fragment/app/Fragment;Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl$ActivityCImpl$FragmentCI-IA;)V
    .locals 0

    invoke-direct {p0, p1, p2}, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl$ActivityCImpl$FragmentCI;-><init>(Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl$ActivityCImpl;Landroidx/fragment/app/Fragment;)V

    return-void
.end method

.method private injectAppSelectorFragment2(Lorg/nameless/gamespace/preferences/appselector/AppSelectorFragment;)Lorg/nameless/gamespace/preferences/appselector/AppSelectorFragment;
    .locals 0

    .line 302
    iget-object p0, p0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl$ActivityCImpl$FragmentCI;->this$2:Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl$ActivityCImpl;

    iget-object p0, p0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl$ActivityCImpl;->this$1:Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl;

    iget-object p0, p0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl;->this$0:Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;

    invoke-static {p0}, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;->-$$Nest$fgetprovideSystemSettingsProvider(Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;)Ljavax/inject/Provider;

    move-result-object p0

    invoke-interface {p0}, Ljavax/inject/Provider;->get()Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lorg/nameless/gamespace/data/SystemSettings;

    invoke-static {p1, p0}, Lorg/nameless/gamespace/preferences/appselector/AppSelectorFragment_MembersInjector;->injectSettings(Lorg/nameless/gamespace/preferences/appselector/AppSelectorFragment;Lorg/nameless/gamespace/data/SystemSettings;)V

    return-object p1
.end method

.method private injectPerAppSettingsFragment2(Lorg/nameless/gamespace/settings/PerAppSettingsFragment;)Lorg/nameless/gamespace/settings/PerAppSettingsFragment;
    .locals 1

    .line 309
    iget-object v0, p0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl$ActivityCImpl$FragmentCI;->this$2:Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl$ActivityCImpl;

    iget-object v0, v0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl$ActivityCImpl;->this$1:Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl;

    iget-object v0, v0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl;->this$0:Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;

    invoke-static {v0}, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;->-$$Nest$fgetprovideSystemSettingsProvider(Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;)Ljavax/inject/Provider;

    move-result-object v0

    invoke-interface {v0}, Ljavax/inject/Provider;->get()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lorg/nameless/gamespace/data/SystemSettings;

    invoke-static {p1, v0}, Lorg/nameless/gamespace/settings/PerAppSettingsFragment_MembersInjector;->injectSettings(Lorg/nameless/gamespace/settings/PerAppSettingsFragment;Lorg/nameless/gamespace/data/SystemSettings;)V

    .line 310
    iget-object p0, p0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl$ActivityCImpl$FragmentCI;->this$2:Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl$ActivityCImpl;

    iget-object p0, p0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl$ActivityCImpl;->this$1:Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl;

    iget-object p0, p0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl;->this$0:Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;

    invoke-static {p0}, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;->-$$Nest$fgetprovideGameModeUtilsProvider(Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;)Ljavax/inject/Provider;

    move-result-object p0

    invoke-interface {p0}, Ljavax/inject/Provider;->get()Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lorg/nameless/gamespace/utils/GameModeUtils;

    invoke-static {p1, p0}, Lorg/nameless/gamespace/settings/PerAppSettingsFragment_MembersInjector;->injectGameModeUtils(Lorg/nameless/gamespace/settings/PerAppSettingsFragment;Lorg/nameless/gamespace/utils/GameModeUtils;)V

    return-object p1
.end method


# virtual methods
.method public injectAppSelectorFragment(Lorg/nameless/gamespace/preferences/appselector/AppSelectorFragment;)V
    .locals 0

    .line 288
    invoke-direct {p0, p1}, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl$ActivityCImpl$FragmentCI;->injectAppSelectorFragment2(Lorg/nameless/gamespace/preferences/appselector/AppSelectorFragment;)Lorg/nameless/gamespace/preferences/appselector/AppSelectorFragment;

    return-void
.end method

.method public injectPerAppSettingsFragment(Lorg/nameless/gamespace/settings/PerAppSettingsFragment;)V
    .locals 0

    .line 293
    invoke-direct {p0, p1}, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl$ActivityCImpl$FragmentCI;->injectPerAppSettingsFragment2(Lorg/nameless/gamespace/settings/PerAppSettingsFragment;)Lorg/nameless/gamespace/settings/PerAppSettingsFragment;

    return-void
.end method

.method public injectSettingsFragment(Lorg/nameless/gamespace/settings/SettingsFragment;)V
    .locals 0

    return-void
.end method
