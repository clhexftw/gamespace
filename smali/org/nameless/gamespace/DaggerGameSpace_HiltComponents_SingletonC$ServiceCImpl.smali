.class final Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ServiceCImpl;
.super Lorg/nameless/gamespace/GameSpace_HiltComponents$ServiceC;
.source "DaggerGameSpace_HiltComponents_SingletonC.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x12
    name = "ServiceCImpl"
.end annotation


# instance fields
.field private callListenerProvider:Ljavax/inject/Provider;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljavax/inject/Provider<",
            "Lorg/nameless/gamespace/gamebar/CallListener;",
            ">;"
        }
    .end annotation
.end field

.field private danmakuServiceProvider:Ljavax/inject/Provider;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljavax/inject/Provider<",
            "Lorg/nameless/gamespace/gamebar/DanmakuService;",
            ">;"
        }
    .end annotation
.end field

.field final synthetic this$0:Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;


# direct methods
.method private constructor <init>(Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;Landroid/app/Service;)V
    .locals 0

    .line 409
    iput-object p1, p0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ServiceCImpl;->this$0:Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;

    invoke-direct {p0}, Lorg/nameless/gamespace/GameSpace_HiltComponents$ServiceC;-><init>()V

    .line 411
    invoke-direct {p0, p2}, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ServiceCImpl;->initialize(Landroid/app/Service;)V

    return-void
.end method

.method synthetic constructor <init>(Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;Landroid/app/Service;Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ServiceCImpl-IA;)V
    .locals 0

    invoke-direct {p0, p1, p2}, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ServiceCImpl;-><init>(Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;Landroid/app/Service;)V

    return-void
.end method

.method private initialize(Landroid/app/Service;)V
    .locals 1

    .line 416
    iget-object p1, p0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ServiceCImpl;->this$0:Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;

    invoke-static {p1}, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;->-$$Nest$fgetprovideContextProvider(Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;)Ljavax/inject/Provider;

    move-result-object p1

    iget-object v0, p0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ServiceCImpl;->this$0:Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;

    invoke-static {v0}, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;->-$$Nest$fgetprovideAppSettingsProvider(Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;)Ljavax/inject/Provider;

    move-result-object v0

    invoke-static {p1, v0}, Lorg/nameless/gamespace/gamebar/DanmakuService_Factory;->create(Ljavax/inject/Provider;Ljavax/inject/Provider;)Lorg/nameless/gamespace/gamebar/DanmakuService_Factory;

    move-result-object p1

    invoke-static {p1}, Ldagger/internal/DoubleCheck;->provider(Ljavax/inject/Provider;)Ljavax/inject/Provider;

    move-result-object p1

    iput-object p1, p0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ServiceCImpl;->danmakuServiceProvider:Ljavax/inject/Provider;

    .line 417
    iget-object p1, p0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ServiceCImpl;->this$0:Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;

    invoke-static {p1}, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;->-$$Nest$fgetprovideContextProvider(Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;)Ljavax/inject/Provider;

    move-result-object p1

    iget-object v0, p0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ServiceCImpl;->this$0:Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;

    invoke-static {v0}, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;->-$$Nest$fgetprovideAppSettingsProvider(Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;)Ljavax/inject/Provider;

    move-result-object v0

    invoke-static {p1, v0}, Lorg/nameless/gamespace/gamebar/CallListener_Factory;->create(Ljavax/inject/Provider;Ljavax/inject/Provider;)Lorg/nameless/gamespace/gamebar/CallListener_Factory;

    move-result-object p1

    invoke-static {p1}, Ldagger/internal/DoubleCheck;->provider(Ljavax/inject/Provider;)Ljavax/inject/Provider;

    move-result-object p1

    iput-object p1, p0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ServiceCImpl;->callListenerProvider:Ljavax/inject/Provider;

    return-void
.end method

.method private injectGameBarService2(Lorg/nameless/gamespace/gamebar/GameBarService;)Lorg/nameless/gamespace/gamebar/GameBarService;
    .locals 1

    .line 432
    iget-object v0, p0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ServiceCImpl;->this$0:Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;

    invoke-static {v0}, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;->-$$Nest$fgetprovideAppSettingsProvider(Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;)Ljavax/inject/Provider;

    move-result-object v0

    invoke-interface {v0}, Ljavax/inject/Provider;->get()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lorg/nameless/gamespace/data/AppSettings;

    invoke-static {p1, v0}, Lorg/nameless/gamespace/gamebar/GameBarService_MembersInjector;->injectAppSettings(Lorg/nameless/gamespace/gamebar/GameBarService;Lorg/nameless/gamespace/data/AppSettings;)V

    .line 433
    iget-object v0, p0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ServiceCImpl;->this$0:Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;

    invoke-static {v0}, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;->-$$Nest$fgetprovideScreenUtilsProvider(Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;)Ljavax/inject/Provider;

    move-result-object v0

    invoke-interface {v0}, Ljavax/inject/Provider;->get()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lorg/nameless/gamespace/utils/ScreenUtils;

    invoke-static {p1, v0}, Lorg/nameless/gamespace/gamebar/GameBarService_MembersInjector;->injectScreenUtils(Lorg/nameless/gamespace/gamebar/GameBarService;Lorg/nameless/gamespace/utils/ScreenUtils;)V

    .line 434
    iget-object p0, p0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ServiceCImpl;->danmakuServiceProvider:Ljavax/inject/Provider;

    invoke-interface {p0}, Ljavax/inject/Provider;->get()Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lorg/nameless/gamespace/gamebar/DanmakuService;

    invoke-static {p1, p0}, Lorg/nameless/gamespace/gamebar/GameBarService_MembersInjector;->injectDanmakuService(Lorg/nameless/gamespace/gamebar/GameBarService;Lorg/nameless/gamespace/gamebar/DanmakuService;)V

    return-object p1
.end method

.method private injectSessionService2(Lorg/nameless/gamespace/gamebar/SessionService;)Lorg/nameless/gamespace/gamebar/SessionService;
    .locals 1

    .line 440
    iget-object v0, p0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ServiceCImpl;->this$0:Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;

    invoke-static {v0}, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;->-$$Nest$fgetprovideAppSettingsProvider(Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;)Ljavax/inject/Provider;

    move-result-object v0

    invoke-interface {v0}, Ljavax/inject/Provider;->get()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lorg/nameless/gamespace/data/AppSettings;

    invoke-static {p1, v0}, Lorg/nameless/gamespace/gamebar/SessionService_MembersInjector;->injectAppSettings(Lorg/nameless/gamespace/gamebar/SessionService;Lorg/nameless/gamespace/data/AppSettings;)V

    .line 441
    iget-object v0, p0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ServiceCImpl;->this$0:Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;

    invoke-static {v0}, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;->-$$Nest$fgetprovideSystemSettingsProvider(Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;)Ljavax/inject/Provider;

    move-result-object v0

    invoke-interface {v0}, Ljavax/inject/Provider;->get()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lorg/nameless/gamespace/data/SystemSettings;

    invoke-static {p1, v0}, Lorg/nameless/gamespace/gamebar/SessionService_MembersInjector;->injectSettings(Lorg/nameless/gamespace/gamebar/SessionService;Lorg/nameless/gamespace/data/SystemSettings;)V

    .line 442
    iget-object v0, p0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ServiceCImpl;->this$0:Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;

    invoke-static {v0}, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;->-$$Nest$fgetprovideGameSessionProvider(Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;)Ljavax/inject/Provider;

    move-result-object v0

    invoke-interface {v0}, Ljavax/inject/Provider;->get()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lorg/nameless/gamespace/data/GameSession;

    invoke-static {p1, v0}, Lorg/nameless/gamespace/gamebar/SessionService_MembersInjector;->injectSession(Lorg/nameless/gamespace/gamebar/SessionService;Lorg/nameless/gamespace/data/GameSession;)V

    .line 443
    iget-object v0, p0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ServiceCImpl;->this$0:Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;

    invoke-static {v0}, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;->-$$Nest$fgetprovideScreenUtilsProvider(Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;)Ljavax/inject/Provider;

    move-result-object v0

    invoke-interface {v0}, Ljavax/inject/Provider;->get()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lorg/nameless/gamespace/utils/ScreenUtils;

    invoke-static {p1, v0}, Lorg/nameless/gamespace/gamebar/SessionService_MembersInjector;->injectScreenUtils(Lorg/nameless/gamespace/gamebar/SessionService;Lorg/nameless/gamespace/utils/ScreenUtils;)V

    .line 444
    iget-object v0, p0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ServiceCImpl;->this$0:Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;

    invoke-static {v0}, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;->-$$Nest$fgetprovideGameModeUtilsProvider(Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC;)Ljavax/inject/Provider;

    move-result-object v0

    invoke-interface {v0}, Ljavax/inject/Provider;->get()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lorg/nameless/gamespace/utils/GameModeUtils;

    invoke-static {p1, v0}, Lorg/nameless/gamespace/gamebar/SessionService_MembersInjector;->injectGameModeUtils(Lorg/nameless/gamespace/gamebar/SessionService;Lorg/nameless/gamespace/utils/GameModeUtils;)V

    .line 445
    iget-object p0, p0, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ServiceCImpl;->callListenerProvider:Ljavax/inject/Provider;

    invoke-interface {p0}, Ljavax/inject/Provider;->get()Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lorg/nameless/gamespace/gamebar/CallListener;

    invoke-static {p1, p0}, Lorg/nameless/gamespace/gamebar/SessionService_MembersInjector;->injectCallListener(Lorg/nameless/gamespace/gamebar/SessionService;Lorg/nameless/gamespace/gamebar/CallListener;)V

    return-object p1
.end method


# virtual methods
.method public injectGameBarService(Lorg/nameless/gamespace/gamebar/GameBarService;)V
    .locals 0

    .line 422
    invoke-direct {p0, p1}, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ServiceCImpl;->injectGameBarService2(Lorg/nameless/gamespace/gamebar/GameBarService;)Lorg/nameless/gamespace/gamebar/GameBarService;

    return-void
.end method

.method public injectSessionService(Lorg/nameless/gamespace/gamebar/SessionService;)V
    .locals 0

    .line 427
    invoke-direct {p0, p1}, Lorg/nameless/gamespace/DaggerGameSpace_HiltComponents_SingletonC$ServiceCImpl;->injectSessionService2(Lorg/nameless/gamespace/gamebar/SessionService;)Lorg/nameless/gamespace/gamebar/SessionService;

    return-void
.end method
