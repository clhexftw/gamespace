.class public final Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;
.super Lorg/nameless/gamespace/GameSpace_HiltComponents$SingletonC;
.source "DaggerGameSpace_HiltComponents_SingletonC.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ServiceCImpl;,
        Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ServiceCBuilder;,
        Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCImpl;,
        Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCBuilder;,
        Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$Builder;
    }
.end annotation


# instance fields
.field private final applicationContextModule:Ldagger/hilt/android/internal/modules/ApplicationContextModule;

.field private provideAppSettingsProvider:Ljavax/inject/Provider;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljavax/inject/Provider<",
            "Lorg/nameless/gamespace/data/AppSettings;",
            ">;"
        }
    .end annotation
.end field

.field private provideContextProvider:Ljavax/inject/Provider;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljavax/inject/Provider<",
            "Landroid/content/Context;",
            ">;"
        }
    .end annotation
.end field

.field private provideGameModeUtilsProvider:Ljavax/inject/Provider;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljavax/inject/Provider<",
            "Lorg/nameless/gamespace/utils/GameModeUtils;",
            ">;"
        }
    .end annotation
.end field

.field private provideGameSessionProvider:Ljavax/inject/Provider;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljavax/inject/Provider<",
            "Lorg/nameless/gamespace/data/GameSession;",
            ">;"
        }
    .end annotation
.end field

.field private provideScreenUtilsProvider:Ljavax/inject/Provider;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljavax/inject/Provider<",
            "Lorg/nameless/gamespace/utils/ScreenUtils;",
            ">;"
        }
    .end annotation
.end field

.field private provideSystemSettingsProvider:Ljavax/inject/Provider;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljavax/inject/Provider<",
            "Lorg/nameless/gamespace/data/SystemSettings;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method static bridge synthetic -$$Nest$fgetprovideAppSettingsProvider(Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;)Ljavax/inject/Provider;
    .locals 0

    iget-object p0, p0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;->provideAppSettingsProvider:Ljavax/inject/Provider;

    return-object p0
.end method

.method static bridge synthetic -$$Nest$fgetprovideContextProvider(Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;)Ljavax/inject/Provider;
    .locals 0

    iget-object p0, p0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;->provideContextProvider:Ljavax/inject/Provider;

    return-object p0
.end method

.method static bridge synthetic -$$Nest$fgetprovideGameModeUtilsProvider(Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;)Ljavax/inject/Provider;
    .locals 0

    iget-object p0, p0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;->provideGameModeUtilsProvider:Ljavax/inject/Provider;

    return-object p0
.end method

.method static bridge synthetic -$$Nest$fgetprovideGameSessionProvider(Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;)Ljavax/inject/Provider;
    .locals 0

    iget-object p0, p0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;->provideGameSessionProvider:Ljavax/inject/Provider;

    return-object p0
.end method

.method static bridge synthetic -$$Nest$fgetprovideScreenUtilsProvider(Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;)Ljavax/inject/Provider;
    .locals 0

    iget-object p0, p0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;->provideScreenUtilsProvider:Ljavax/inject/Provider;

    return-object p0
.end method

.method static bridge synthetic -$$Nest$fgetprovideSystemSettingsProvider(Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;)Ljavax/inject/Provider;
    .locals 0

    iget-object p0, p0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;->provideSystemSettingsProvider:Ljavax/inject/Provider;

    return-object p0
.end method

.method private constructor <init>(Ldagger/hilt/android/internal/modules/ApplicationContextModule;)V
    .locals 0

    .line 87
    invoke-direct {p0}, Lorg/nameless/gamespace/GameSpace_HiltComponents$SingletonC;-><init>()V

    .line 88
    iput-object p1, p0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;->applicationContextModule:Ldagger/hilt/android/internal/modules/ApplicationContextModule;

    .line 89
    invoke-direct {p0, p1}, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;->initialize(Ldagger/hilt/android/internal/modules/ApplicationContextModule;)V

    return-void
.end method

.method synthetic constructor <init>(Ldagger/hilt/android/internal/modules/ApplicationContextModule;Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC-IA;)V
    .locals 0

    invoke-direct {p0, p1}, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;-><init>(Ldagger/hilt/android/internal/modules/ApplicationContextModule;)V

    return-void
.end method

.method public static builder()Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$Builder;
    .locals 2

    .line 93
    new-instance v0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$Builder;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$Builder;-><init>(Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$Builder-IA;)V

    return-object v0
.end method

.method private initialize(Ldagger/hilt/android/internal/modules/ApplicationContextModule;)V
    .locals 3

    .line 98
    invoke-static {p1}, Ldagger/hilt/android/internal/modules/ApplicationContextModule_ProvideContextFactory;->create(Ldagger/hilt/android/internal/modules/ApplicationContextModule;)Ldagger/hilt/android/internal/modules/ApplicationContextModule_ProvideContextFactory;

    move-result-object p1

    iput-object p1, p0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;->provideContextProvider:Ljavax/inject/Provider;

    .line 99
    invoke-static {p1}, Lorg/nameless/gamespace/utils/di/MainModule_ProvideAppSettingsFactory;->create(Ljavax/inject/Provider;)Lorg/nameless/gamespace/utils/di/MainModule_ProvideAppSettingsFactory;

    move-result-object p1

    invoke-static {p1}, Ldagger/internal/DoubleCheck;->provider(Ljavax/inject/Provider;)Ljavax/inject/Provider;

    move-result-object p1

    iput-object p1, p0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;->provideAppSettingsProvider:Ljavax/inject/Provider;

    .line 100
    iget-object p1, p0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;->provideContextProvider:Ljavax/inject/Provider;

    invoke-static {p1}, Lorg/nameless/gamespace/utils/di/MainModule_ProvideGameModeUtilsFactory;->create(Ljavax/inject/Provider;)Lorg/nameless/gamespace/utils/di/MainModule_ProvideGameModeUtilsFactory;

    move-result-object p1

    invoke-static {p1}, Ldagger/internal/DoubleCheck;->provider(Ljavax/inject/Provider;)Ljavax/inject/Provider;

    move-result-object p1

    iput-object p1, p0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;->provideGameModeUtilsProvider:Ljavax/inject/Provider;

    .line 101
    iget-object v0, p0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;->provideContextProvider:Ljavax/inject/Provider;

    invoke-static {v0, p1}, Lorg/nameless/gamespace/utils/di/MainModule_ProvideSystemSettingsFactory;->create(Ljavax/inject/Provider;Ljavax/inject/Provider;)Lorg/nameless/gamespace/utils/di/MainModule_ProvideSystemSettingsFactory;

    move-result-object p1

    invoke-static {p1}, Ldagger/internal/DoubleCheck;->provider(Ljavax/inject/Provider;)Ljavax/inject/Provider;

    move-result-object p1

    iput-object p1, p0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;->provideSystemSettingsProvider:Ljavax/inject/Provider;

    .line 102
    iget-object p1, p0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;->provideContextProvider:Ljavax/inject/Provider;

    invoke-static {p1}, Lorg/nameless/gamespace/utils/di/MainModule_ProvideScreenUtilsFactory;->create(Ljavax/inject/Provider;)Lorg/nameless/gamespace/utils/di/MainModule_ProvideScreenUtilsFactory;

    move-result-object p1

    invoke-static {p1}, Ldagger/internal/DoubleCheck;->provider(Ljavax/inject/Provider;)Ljavax/inject/Provider;

    move-result-object p1

    iput-object p1, p0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;->provideScreenUtilsProvider:Ljavax/inject/Provider;

    .line 103
    iget-object p1, p0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;->provideContextProvider:Ljavax/inject/Provider;

    iget-object v0, p0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;->provideAppSettingsProvider:Ljavax/inject/Provider;

    iget-object v1, p0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;->provideSystemSettingsProvider:Ljavax/inject/Provider;

    invoke-static {}, Lorg/nameless/gamespace/utils/di/MainModule_ProvideBaseGsonFactory;->create()Lorg/nameless/gamespace/utils/di/MainModule_ProvideBaseGsonFactory;

    move-result-object v2

    invoke-static {p1, v0, v1, v2}, Lorg/nameless/gamespace/utils/di/MainModule_ProvideGameSessionFactory;->create(Ljavax/inject/Provider;Ljavax/inject/Provider;Ljavax/inject/Provider;Ljavax/inject/Provider;)Lorg/nameless/gamespace/utils/di/MainModule_ProvideGameSessionFactory;

    move-result-object p1

    invoke-static {p1}, Ldagger/internal/DoubleCheck;->provider(Ljavax/inject/Provider;)Ljavax/inject/Provider;

    move-result-object p1

    iput-object p1, p0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;->provideGameSessionProvider:Ljavax/inject/Provider;

    return-void
.end method


# virtual methods
.method public appSettings()Lorg/nameless/gamespace/data/AppSettings;
    .locals 0

    .line 122
    iget-object p0, p0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;->provideAppSettingsProvider:Ljavax/inject/Provider;

    invoke-interface {p0}, Ljavax/inject/Provider;->get()Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lorg/nameless/gamespace/data/AppSettings;

    return-object p0
.end method

.method public gameModeUtils()Lorg/nameless/gamespace/utils/GameModeUtils;
    .locals 0

    .line 137
    iget-object p0, p0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;->provideGameModeUtilsProvider:Ljavax/inject/Provider;

    invoke-interface {p0}, Ljavax/inject/Provider;->get()Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lorg/nameless/gamespace/utils/GameModeUtils;

    return-object p0
.end method

.method public injectGameSpace(Lorg/nameless/gamespace/GameSpace;)V
    .locals 0

    return-void
.end method

.method public retainedComponentBuilder()Ldagger/hilt/android/internal/builders/ActivityRetainedComponentBuilder;
    .locals 2

    .line 108
    new-instance v0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCBuilder;

    const/4 v1, 0x0

    invoke-direct {v0, p0, v1}, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCBuilder;-><init>(Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ActivityRetainedCBuilder-IA;)V

    return-object v0
.end method

.method public screenUtils()Lorg/nameless/gamespace/utils/ScreenUtils;
    .locals 0

    .line 132
    iget-object p0, p0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;->provideScreenUtilsProvider:Ljavax/inject/Provider;

    invoke-interface {p0}, Ljavax/inject/Provider;->get()Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lorg/nameless/gamespace/utils/ScreenUtils;

    return-object p0
.end method

.method public serviceComponentBuilder()Ldagger/hilt/android/internal/builders/ServiceComponentBuilder;
    .locals 2

    .line 113
    new-instance v0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ServiceCBuilder;

    const/4 v1, 0x0

    invoke-direct {v0, p0, v1}, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ServiceCBuilder;-><init>(Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ServiceCBuilder-IA;)V

    return-object v0
.end method

.method public systemSettings()Lorg/nameless/gamespace/data/SystemSettings;
    .locals 0

    .line 127
    iget-object p0, p0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;->provideSystemSettingsProvider:Ljavax/inject/Provider;

    invoke-interface {p0}, Ljavax/inject/Provider;->get()Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lorg/nameless/gamespace/data/SystemSettings;

    return-object p0
.end method
