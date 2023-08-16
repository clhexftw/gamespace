.class public final Lorg/nameless/gamespace/gamebar/GameBarService_MembersInjector;
.super Ljava/lang/Object;
.source "GameBarService_MembersInjector.java"


# direct methods
.method public static injectAppSettings(Lorg/nameless/gamespace/gamebar/GameBarService;Lorg/nameless/gamespace/data/AppSettings;)V
    .locals 0

    .line 48
    iput-object p1, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->appSettings:Lorg/nameless/gamespace/data/AppSettings;

    return-void
.end method

.method public static injectDanmakuService(Lorg/nameless/gamespace/gamebar/GameBarService;Lorg/nameless/gamespace/gamebar/DanmakuService;)V
    .locals 0

    .line 58
    iput-object p1, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->danmakuService:Lorg/nameless/gamespace/gamebar/DanmakuService;

    return-void
.end method

.method public static injectScreenUtils(Lorg/nameless/gamespace/gamebar/GameBarService;Lorg/nameless/gamespace/utils/ScreenUtils;)V
    .locals 0

    .line 53
    iput-object p1, p0, Lorg/nameless/gamespace/gamebar/GameBarService;->screenUtils:Lorg/nameless/gamespace/utils/ScreenUtils;

    return-void
.end method
