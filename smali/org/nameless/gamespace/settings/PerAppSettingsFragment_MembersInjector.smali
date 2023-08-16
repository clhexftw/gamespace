.class public final Lorg/nameless/gamespace/settings/PerAppSettingsFragment_MembersInjector;
.super Ljava/lang/Object;
.source "PerAppSettingsFragment_MembersInjector.java"


# direct methods
.method public static injectGameModeUtils(Lorg/nameless/gamespace/settings/PerAppSettingsFragment;Lorg/nameless/gamespace/utils/GameModeUtils;)V
    .locals 0

    .line 50
    iput-object p1, p0, Lorg/nameless/gamespace/settings/PerAppSettingsFragment;->gameModeUtils:Lorg/nameless/gamespace/utils/GameModeUtils;

    return-void
.end method

.method public static injectSettings(Lorg/nameless/gamespace/settings/PerAppSettingsFragment;Lorg/nameless/gamespace/data/SystemSettings;)V
    .locals 0

    .line 44
    iput-object p1, p0, Lorg/nameless/gamespace/settings/PerAppSettingsFragment;->settings:Lorg/nameless/gamespace/data/SystemSettings;

    return-void
.end method
