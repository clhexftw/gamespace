.class public final Lorg/nameless/gamespace/settings/PerAppSettingsFragment;
.super Lorg/nameless/gamespace/settings/Hilt_PerAppSettingsFragment;
.source "PerAppSettingsFragment.kt"

# interfaces
.implements Landroidx/preference/Preference$OnPreferenceChangeListener;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lorg/nameless/gamespace/settings/PerAppSettingsFragment$Companion;
    }
.end annotation

.annotation system Ldalvik/annotation/SourceDebugExtension;
    value = "SMAP\nPerAppSettingsFragment.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PerAppSettingsFragment.kt\norg/nameless/gamespace/settings/PerAppSettingsFragment\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,130:1\n288#2,2:131\n1#3:133\n*S KotlinDebug\n*F\n+ 1 PerAppSettingsFragment.kt\norg/nameless/gamespace/settings/PerAppSettingsFragment\n*L\n53#1:131,2\n*E\n"
.end annotation


# static fields
.field public static final Companion:Lorg/nameless/gamespace/settings/PerAppSettingsFragment$Companion;


# instance fields
.field private final currentGame$delegate:Lkotlin/Lazy;

.field public gameModeUtils:Lorg/nameless/gamespace/utils/GameModeUtils;

.field public settings:Lorg/nameless/gamespace/data/SystemSettings;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    new-instance v0, Lorg/nameless/gamespace/settings/PerAppSettingsFragment$Companion;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lorg/nameless/gamespace/settings/PerAppSettingsFragment$Companion;-><init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V

    sput-object v0, Lorg/nameless/gamespace/settings/PerAppSettingsFragment;->Companion:Lorg/nameless/gamespace/settings/PerAppSettingsFragment$Companion;

    return-void
.end method

.method public constructor <init>()V
    .locals 1

    .line 36
    invoke-direct {p0}, Lorg/nameless/gamespace/settings/Hilt_PerAppSettingsFragment;-><init>()V

    .line 45
    new-instance v0, Lorg/nameless/gamespace/settings/PerAppSettingsFragment$currentGame$2;

    invoke-direct {v0, p0}, Lorg/nameless/gamespace/settings/PerAppSettingsFragment$currentGame$2;-><init>(Lorg/nameless/gamespace/settings/PerAppSettingsFragment;)V

    invoke-static {v0}, Lkotlin/LazyKt;->lazy(Lkotlin/jvm/functions/Function0;)Lkotlin/Lazy;

    move-result-object v0

    iput-object v0, p0, Lorg/nameless/gamespace/settings/PerAppSettingsFragment;->currentGame$delegate:Lkotlin/Lazy;

    return-void
.end method

.method public static final synthetic access$getCurrentGame(Lorg/nameless/gamespace/settings/PerAppSettingsFragment;)Landroid/content/pm/ApplicationInfo;
    .locals 0

    .line 35
    invoke-direct {p0}, Lorg/nameless/gamespace/settings/PerAppSettingsFragment;->getCurrentGame()Landroid/content/pm/ApplicationInfo;

    move-result-object p0

    return-object p0
.end method

.method private final getCurrentConfig()Lorg/nameless/gamespace/data/UserGame;
    .locals 5

    .line 53
    invoke-virtual {p0}, Lorg/nameless/gamespace/settings/PerAppSettingsFragment;->getSettings()Lorg/nameless/gamespace/data/SystemSettings;

    move-result-object v0

    invoke-virtual {v0}, Lorg/nameless/gamespace/data/SystemSettings;->getUserGames()Ljava/util/List;

    move-result-object v0

    check-cast v0, Ljava/lang/Iterable;

    .line 288
    invoke-interface {v0}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :cond_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    const/4 v2, 0x0

    if-eqz v1, :cond_2

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    move-object v3, v1

    check-cast v3, Lorg/nameless/gamespace/data/UserGame;

    .line 53
    invoke-virtual {v3}, Lorg/nameless/gamespace/data/UserGame;->getPackageName()Ljava/lang/String;

    move-result-object v3

    invoke-direct {p0}, Lorg/nameless/gamespace/settings/PerAppSettingsFragment;->getCurrentGame()Landroid/content/pm/ApplicationInfo;

    move-result-object v4

    if-eqz v4, :cond_1

    iget-object v2, v4, Landroid/content/pm/ApplicationInfo;->packageName:Ljava/lang/String;

    :cond_1
    invoke-static {v3, v2}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_0

    move-object v2, v1

    .line 289
    :cond_2
    check-cast v2, Lorg/nameless/gamespace/data/UserGame;

    return-object v2
.end method

.method private final getCurrentGame()Landroid/content/pm/ApplicationInfo;
    .locals 0

    .line 45
    iget-object p0, p0, Lorg/nameless/gamespace/settings/PerAppSettingsFragment;->currentGame$delegate:Lkotlin/Lazy;

    invoke-interface {p0}, Lkotlin/Lazy;->getValue()Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Landroid/content/pm/ApplicationInfo;

    return-object p0
.end method


# virtual methods
.method public final getGameModeUtils()Lorg/nameless/gamespace/utils/GameModeUtils;
    .locals 0

    .line 43
    iget-object p0, p0, Lorg/nameless/gamespace/settings/PerAppSettingsFragment;->gameModeUtils:Lorg/nameless/gamespace/utils/GameModeUtils;

    if-eqz p0, :cond_0

    return-object p0

    :cond_0
    const-string p0, "gameModeUtils"

    invoke-static {p0}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0
.end method

.method public final getSettings()Lorg/nameless/gamespace/data/SystemSettings;
    .locals 0

    .line 40
    iget-object p0, p0, Lorg/nameless/gamespace/settings/PerAppSettingsFragment;->settings:Lorg/nameless/gamespace/data/SystemSettings;

    if-eqz p0, :cond_0

    return-object p0

    :cond_0
    const-string p0, "settings"

    invoke-static {p0}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0
.end method

.method public onCreate(Landroid/os/Bundle;)V
    .locals 1

    .line 56
    invoke-super {p0, p1}, Landroidx/preference/PreferenceFragmentCompat;->onCreate(Landroid/os/Bundle;)V

    .line 57
    invoke-virtual {p0}, Landroidx/fragment/app/Fragment;->getActivity()Landroidx/fragment/app/FragmentActivity;

    move-result-object p1

    if-nez p1, :cond_0

    goto :goto_1

    :cond_0
    invoke-virtual {p0}, Lorg/nameless/gamespace/settings/Hilt_PerAppSettingsFragment;->getContext()Landroid/content/Context;

    move-result-object p0

    if-eqz p0, :cond_1

    const v0, 0x7f120259

    invoke-virtual {p0, v0}, Landroid/content/Context;->getString(I)Ljava/lang/String;

    move-result-object p0

    goto :goto_0

    :cond_1
    const/4 p0, 0x0

    :goto_0
    invoke-virtual {p1, p0}, Landroid/app/Activity;->setTitle(Ljava/lang/CharSequence;)V

    :goto_1
    return-void
.end method

.method public onCreatePreferences(Landroid/os/Bundle;Ljava/lang/String;)V
    .locals 0

    const/high16 p1, 0x7f150000

    .line 61
    invoke-virtual {p0, p1, p2}, Landroidx/preference/PreferenceFragmentCompat;->setPreferencesFromResource(ILjava/lang/String;)V

    return-void
.end method

.method public onPreferenceChange(Landroidx/preference/Preference;Ljava/lang/Object;)Z
    .locals 5

    const-string v0, "preference"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 106
    invoke-direct {p0}, Lorg/nameless/gamespace/settings/PerAppSettingsFragment;->getCurrentGame()Landroid/content/pm/ApplicationInfo;

    move-result-object v0

    const/4 v1, 0x0

    if-nez v0, :cond_0

    return v1

    .line 107
    :cond_0
    invoke-virtual {p1}, Landroidx/preference/Preference;->getKey()Ljava/lang/String;

    move-result-object p1

    const-string v2, "per_app_preferred_mode"

    .line 108
    invoke-static {p1, v2}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v2

    const-string v3, "gameInfo.packageName"

    const/4 v4, 0x1

    if-eqz v2, :cond_2

    const-string p1, "null cannot be cast to non-null type kotlin.String"

    .line 109
    invoke-static {p2, p1}, Lkotlin/jvm/internal/Intrinsics;->checkNotNull(Ljava/lang/Object;Ljava/lang/String;)V

    check-cast p2, Ljava/lang/String;

    invoke-static {p2}, Lkotlin/text/StringsKt;->toIntOrNull(Ljava/lang/String;)Ljava/lang/Integer;

    move-result-object p1

    if-eqz p1, :cond_1

    invoke-virtual {p1}, Ljava/lang/Integer;->intValue()I

    move-result p1

    goto :goto_0

    :cond_1
    move p1, v4

    .line 110
    :goto_0
    invoke-virtual {p0}, Lorg/nameless/gamespace/settings/PerAppSettingsFragment;->getGameModeUtils()Lorg/nameless/gamespace/utils/GameModeUtils;

    move-result-object p2

    iget-object v0, v0, Landroid/content/pm/ApplicationInfo;->packageName:Ljava/lang/String;

    invoke-static {v0, v3}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullExpressionValue(Ljava/lang/Object;Ljava/lang/String;)V

    invoke-virtual {p0}, Lorg/nameless/gamespace/settings/PerAppSettingsFragment;->getSettings()Lorg/nameless/gamespace/data/SystemSettings;

    move-result-object p0

    invoke-virtual {p2, v0, p0, p1}, Lorg/nameless/gamespace/utils/GameModeUtils;->setGameModeFor(Ljava/lang/String;Lorg/nameless/gamespace/data/SystemSettings;I)Lorg/nameless/gamespace/data/UserGame;

    return v4

    :cond_2
    const-string v2, "per_app_use_angle"

    .line 113
    invoke-static {p1, v2}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_3

    .line 114
    sget-object p1, Lorg/nameless/gamespace/data/GameConfig$ModeBuilder;->INSTANCE:Lorg/nameless/gamespace/data/GameConfig$ModeBuilder;

    const-string v1, "null cannot be cast to non-null type kotlin.Boolean"

    .line 115
    invoke-static {p2, v1}, Lkotlin/jvm/internal/Intrinsics;->checkNotNull(Ljava/lang/Object;Ljava/lang/String;)V

    check-cast p2, Ljava/lang/Boolean;

    invoke-virtual {p2}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p2

    invoke-virtual {p1, p2}, Lorg/nameless/gamespace/data/GameConfig$ModeBuilder;->setUseAngle(Z)V

    .line 116
    invoke-virtual {p1}, Lorg/nameless/gamespace/data/GameConfig$ModeBuilder;->build()Ljava/util/List;

    move-result-object p1

    .line 117
    invoke-virtual {p0}, Lorg/nameless/gamespace/settings/PerAppSettingsFragment;->getGameModeUtils()Lorg/nameless/gamespace/utils/GameModeUtils;

    move-result-object p0

    iget-object p2, v0, Landroid/content/pm/ApplicationInfo;->packageName:Ljava/lang/String;

    invoke-static {p2, v3}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullExpressionValue(Ljava/lang/Object;Ljava/lang/String;)V

    invoke-virtual {p0, p2, p1}, Lorg/nameless/gamespace/utils/GameModeUtils;->setIntervention(Ljava/lang/String;Ljava/util/List;)V

    return v4

    :cond_3
    return v1
.end method

.method public onViewCreated(Landroid/view/View;Landroid/os/Bundle;)V
    .locals 5

    const-string v0, "view"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 65
    invoke-super {p0, p1, p2}, Landroidx/preference/PreferenceFragmentCompat;->onViewCreated(Landroid/view/View;Landroid/os/Bundle;)V

    const-string p1, "headers"

    .line 66
    invoke-virtual {p0, p1}, Landroidx/preference/PreferenceFragmentCompat;->findPreference(Ljava/lang/CharSequence;)Landroidx/preference/Preference;

    move-result-object p1

    const/4 p2, 0x0

    if-eqz p1, :cond_2

    const v0, 0x7f0d0091

    .line 67
    invoke-virtual {p1, v0}, Landroidx/preference/Preference;->setLayoutResource(I)V

    .line 68
    invoke-direct {p0}, Lorg/nameless/gamespace/settings/PerAppSettingsFragment;->getCurrentGame()Landroid/content/pm/ApplicationInfo;

    move-result-object v0

    if-eqz v0, :cond_0

    invoke-virtual {p1}, Landroidx/preference/Preference;->getContext()Landroid/content/Context;

    move-result-object v1

    invoke-virtual {v1}, Landroid/content/Context;->getPackageManager()Landroid/content/pm/PackageManager;

    move-result-object v1

    invoke-virtual {v0, v1}, Landroid/content/pm/ApplicationInfo;->loadIcon(Landroid/content/pm/PackageManager;)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    goto :goto_0

    :cond_0
    move-object v0, p2

    :goto_0
    invoke-virtual {p1, v0}, Landroidx/preference/Preference;->setIcon(Landroid/graphics/drawable/Drawable;)V

    .line 69
    invoke-virtual {p1}, Landroidx/preference/Preference;->getContext()Landroid/content/Context;

    move-result-object v0

    invoke-virtual {v0}, Landroid/content/Context;->getPackageManager()Landroid/content/pm/PackageManager;

    move-result-object v0

    if-eqz v0, :cond_1

    const-string v1, "packageManager"

    invoke-static {v0, v1}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullExpressionValue(Ljava/lang/Object;Ljava/lang/String;)V

    invoke-direct {p0}, Lorg/nameless/gamespace/settings/PerAppSettingsFragment;->getCurrentGame()Landroid/content/pm/ApplicationInfo;

    move-result-object v1

    if-eqz v1, :cond_1

    invoke-virtual {v1, v0}, Landroid/content/pm/ApplicationInfo;->loadLabel(Landroid/content/pm/PackageManager;)Ljava/lang/CharSequence;

    move-result-object v0

    goto :goto_1

    :cond_1
    move-object v0, p2

    :goto_1
    invoke-virtual {p1, v0}, Landroidx/preference/Preference;->setTitle(Ljava/lang/CharSequence;)V

    :cond_2
    const-string p1, "per_app_preferred_mode"

    .line 71
    invoke-virtual {p0, p1}, Landroidx/preference/PreferenceFragmentCompat;->findPreference(Ljava/lang/CharSequence;)Landroidx/preference/Preference;

    move-result-object p1

    check-cast p1, Landroidx/preference/ListPreference;

    if-eqz p1, :cond_4

    .line 72
    invoke-direct {p0}, Lorg/nameless/gamespace/settings/PerAppSettingsFragment;->getCurrentConfig()Lorg/nameless/gamespace/data/UserGame;

    move-result-object v0

    if-eqz v0, :cond_3

    invoke-virtual {v0}, Lorg/nameless/gamespace/data/UserGame;->getMode()I

    move-result v0

    invoke-static {v0}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p1, v0}, Landroidx/preference/ListPreference;->setValue(Ljava/lang/String;)V

    .line 73
    :cond_3
    invoke-virtual {p1, p0}, Landroidx/preference/Preference;->setOnPreferenceChangeListener(Landroidx/preference/Preference$OnPreferenceChangeListener;)V

    :cond_4
    const-string p1, "per_app_use_angle"

    .line 75
    invoke-virtual {p0, p1}, Landroidx/preference/PreferenceFragmentCompat;->findPreference(Ljava/lang/CharSequence;)Landroidx/preference/Preference;

    move-result-object p1

    check-cast p1, Lorg/nameless/custom/preference/SwitchPreference;

    const/4 v0, 0x1

    const/4 v1, 0x0

    if-eqz p1, :cond_9

    .line 76
    invoke-virtual {p1}, Landroidx/preference/Preference;->getContext()Landroid/content/Context;

    move-result-object v2

    invoke-virtual {v2}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v2

    if-eqz v2, :cond_5

    const v3, 0x7f050002

    invoke-virtual {v2, v3}, Landroid/content/res/Resources;->getBoolean(I)Z

    move-result v2

    .line 77
    invoke-virtual {p1, v2}, Landroidx/preference/Preference;->setVisible(Z)V

    if-eqz v2, :cond_9

    .line 81
    :cond_5
    invoke-virtual {p0}, Lorg/nameless/gamespace/settings/PerAppSettingsFragment;->getGameModeUtils()Lorg/nameless/gamespace/utils/GameModeUtils;

    move-result-object v2

    invoke-virtual {v2}, Lorg/nameless/gamespace/utils/GameModeUtils;->findAnglePackage()Landroid/content/pm/ActivityInfo;

    move-result-object v2

    if-eqz v2, :cond_6

    invoke-virtual {v2}, Landroid/content/pm/ActivityInfo;->isEnabled()Z

    move-result v2

    if-ne v2, v0, :cond_6

    move v2, v0

    goto :goto_2

    :cond_6
    move v2, v1

    :goto_2
    if-nez v2, :cond_7

    .line 82
    invoke-virtual {p1, v1}, Landroidx/preference/Preference;->setEnabled(Z)V

    .line 83
    invoke-virtual {p1}, Landroidx/preference/Preference;->getContext()Landroid/content/Context;

    move-result-object v2

    const v3, 0x7f1200f5

    invoke-virtual {v2, v3}, Landroid/content/Context;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {p1, v2}, Landroidx/preference/Preference;->setSummary(Ljava/lang/CharSequence;)V

    goto :goto_4

    .line 86
    :cond_7
    invoke-virtual {p0}, Lorg/nameless/gamespace/settings/PerAppSettingsFragment;->getGameModeUtils()Lorg/nameless/gamespace/utils/GameModeUtils;

    move-result-object v2

    invoke-direct {p0}, Lorg/nameless/gamespace/settings/PerAppSettingsFragment;->getCurrentGame()Landroid/content/pm/ApplicationInfo;

    move-result-object v3

    if-eqz v3, :cond_8

    iget-object v3, v3, Landroid/content/pm/ApplicationInfo;->packageName:Ljava/lang/String;

    goto :goto_3

    :cond_8
    move-object v3, p2

    :goto_3
    invoke-virtual {v2, v3}, Lorg/nameless/gamespace/utils/GameModeUtils;->isAngleUsed(Ljava/lang/String;)Z

    move-result v2

    invoke-virtual {p1, v2}, Landroidx/preference/TwoStatePreference;->setChecked(Z)V

    .line 87
    invoke-virtual {p1, p0}, Landroidx/preference/Preference;->setOnPreferenceChangeListener(Landroidx/preference/Preference$OnPreferenceChangeListener;)V

    :cond_9
    :goto_4
    const-string p1, "per_app_unregister"

    .line 90
    invoke-virtual {p0, p1}, Landroidx/preference/PreferenceFragmentCompat;->findPreference(Ljava/lang/CharSequence;)Landroidx/preference/Preference;

    move-result-object p1

    if-eqz p1, :cond_b

    .line 91
    invoke-virtual {p1}, Landroidx/preference/Preference;->getContext()Landroid/content/Context;

    move-result-object v2

    const v3, 0x7f12025a

    new-array v0, v0, [Ljava/lang/Object;

    .line 93
    invoke-direct {p0}, Lorg/nameless/gamespace/settings/PerAppSettingsFragment;->getCurrentGame()Landroid/content/pm/ApplicationInfo;

    move-result-object v4

    if-eqz v4, :cond_a

    invoke-virtual {p1}, Landroidx/preference/Preference;->getContext()Landroid/content/Context;

    move-result-object p2

    invoke-virtual {p2}, Landroid/content/Context;->getPackageManager()Landroid/content/pm/PackageManager;

    move-result-object p2

    invoke-virtual {v4, p2}, Landroid/content/pm/ApplicationInfo;->loadLabel(Landroid/content/pm/PackageManager;)Ljava/lang/CharSequence;

    move-result-object p2

    :cond_a
    aput-object p2, v0, v1

    .line 91
    invoke-virtual {v2, v3, v0}, Landroid/content/Context;->getString(I[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p1, p2}, Landroidx/preference/Preference;->setSummary(Ljava/lang/CharSequence;)V

    .line 95
    new-instance p2, Lorg/nameless/gamespace/settings/PerAppSettingsFragment$onViewCreated$4$1;

    invoke-direct {p2, p0}, Lorg/nameless/gamespace/settings/PerAppSettingsFragment$onViewCreated$4$1;-><init>(Lorg/nameless/gamespace/settings/PerAppSettingsFragment;)V

    invoke-virtual {p1, p2}, Landroidx/preference/Preference;->setOnPreferenceClickListener(Landroidx/preference/Preference$OnPreferenceClickListener;)V

    :cond_b
    return-void
.end method
