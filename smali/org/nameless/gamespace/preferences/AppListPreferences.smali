.class public final Lorg/nameless/gamespace/preferences/AppListPreferences;
.super Landroidx/preference/PreferenceCategory;
.source "AppListPreferences.kt"

# interfaces
.implements Landroidx/preference/Preference$OnPreferenceClickListener;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lorg/nameless/gamespace/preferences/AppListPreferences$Companion;
    }
.end annotation

.annotation system Ldalvik/annotation/SourceDebugExtension;
    value = "SMAP\nAppListPreferences.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AppListPreferences.kt\norg/nameless/gamespace/preferences/AppListPreferences\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,143:1\n766#2:144\n857#2,2:145\n1549#2:147\n1620#2,3:148\n1045#2:151\n1851#2,2:152\n1743#2,3:154\n1#3:157\n*S KotlinDebug\n*F\n+ 1 AppListPreferences.kt\norg/nameless/gamespace/preferences/AppListPreferences\n*L\n77#1:144\n77#1:145,2\n78#1:147\n78#1:148,3\n90#1:151\n91#1:152,2\n95#1:154,3\n*E\n"
.end annotation


# static fields
.field public static final Companion:Lorg/nameless/gamespace/preferences/AppListPreferences$Companion;


# instance fields
.field private final apps:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "Lorg/nameless/gamespace/data/UserGame;",
            ">;"
        }
    .end annotation
.end field

.field private final gameModeUtils$delegate:Lkotlin/Lazy;

.field private final makeAddPref$delegate:Lkotlin/Lazy;

.field private registeredAppClickAction:Lkotlin/jvm/functions/Function1;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lkotlin/jvm/functions/Function1<",
            "-",
            "Ljava/lang/String;",
            "Lkotlin/Unit;",
            ">;"
        }
    .end annotation
.end field

.field private final systemSettings$delegate:Lkotlin/Lazy;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    new-instance v0, Lorg/nameless/gamespace/preferences/AppListPreferences$Companion;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lorg/nameless/gamespace/preferences/AppListPreferences$Companion;-><init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V

    sput-object v0, Lorg/nameless/gamespace/preferences/AppListPreferences;->Companion:Lorg/nameless/gamespace/preferences/AppListPreferences$Companion;

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;)V
    .locals 2

    const-string v0, "context"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    const/4 v0, 0x0

    const/4 v1, 0x2

    invoke-direct {p0, p1, v0, v1, v0}, Lorg/nameless/gamespace/preferences/AppListPreferences;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 1

    const-string v0, "context"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 36
    invoke-direct {p0, p1, p2}, Landroidx/preference/PreferenceCategory;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    .line 38
    new-instance p2, Ljava/util/ArrayList;

    invoke-direct {p2}, Ljava/util/ArrayList;-><init>()V

    iput-object p2, p0, Lorg/nameless/gamespace/preferences/AppListPreferences;->apps:Ljava/util/List;

    .line 39
    new-instance p2, Lorg/nameless/gamespace/preferences/AppListPreferences$systemSettings$2;

    invoke-direct {p2, p1}, Lorg/nameless/gamespace/preferences/AppListPreferences$systemSettings$2;-><init>(Landroid/content/Context;)V

    invoke-static {p2}, Lkotlin/LazyKt;->lazy(Lkotlin/jvm/functions/Function0;)Lkotlin/Lazy;

    move-result-object p2

    iput-object p2, p0, Lorg/nameless/gamespace/preferences/AppListPreferences;->systemSettings$delegate:Lkotlin/Lazy;

    .line 43
    new-instance p2, Lorg/nameless/gamespace/preferences/AppListPreferences$gameModeUtils$2;

    invoke-direct {p2, p1}, Lorg/nameless/gamespace/preferences/AppListPreferences$gameModeUtils$2;-><init>(Landroid/content/Context;)V

    invoke-static {p2}, Lkotlin/LazyKt;->lazy(Lkotlin/jvm/functions/Function0;)Lkotlin/Lazy;

    move-result-object p2

    iput-object p2, p0, Lorg/nameless/gamespace/preferences/AppListPreferences;->gameModeUtils$delegate:Lkotlin/Lazy;

    const/4 p2, 0x0

    .line 50
    invoke-virtual {p0, p2}, Landroidx/preference/PreferenceGroup;->setOrderingAsAdded(Z)V

    .line 53
    new-instance p2, Lorg/nameless/gamespace/preferences/AppListPreferences$makeAddPref$2;

    invoke-direct {p2, p1, p0}, Lorg/nameless/gamespace/preferences/AppListPreferences$makeAddPref$2;-><init>(Landroid/content/Context;Lorg/nameless/gamespace/preferences/AppListPreferences;)V

    invoke-static {p2}, Lkotlin/LazyKt;->lazy(Lkotlin/jvm/functions/Function0;)Lkotlin/Lazy;

    move-result-object p1

    iput-object p1, p0, Lorg/nameless/gamespace/preferences/AppListPreferences;->makeAddPref$delegate:Lkotlin/Lazy;

    return-void
.end method

.method public synthetic constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;ILkotlin/jvm/internal/DefaultConstructorMarker;)V
    .locals 0

    and-int/lit8 p3, p3, 0x2

    if-eqz p3, :cond_0

    const/4 p2, 0x0

    .line 35
    :cond_0
    invoke-direct {p0, p1, p2}, Lorg/nameless/gamespace/preferences/AppListPreferences;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    return-void
.end method

.method private final getAppInfo(Ljava/lang/String;)Landroid/content/pm/ApplicationInfo;
    .locals 2

    const-wide/16 v0, 0x80

    .line 64
    :try_start_0
    invoke-static {v0, v1}, Landroid/content/pm/PackageManager$ApplicationInfoFlags;->of(J)Landroid/content/pm/PackageManager$ApplicationInfoFlags;

    move-result-object v0

    .line 65
    invoke-virtual {p0}, Landroidx/preference/Preference;->getContext()Landroid/content/Context;

    move-result-object p0

    invoke-virtual {p0}, Landroid/content/Context;->getPackageManager()Landroid/content/pm/PackageManager;

    move-result-object p0

    invoke-virtual {p0, p1, v0}, Landroid/content/pm/PackageManager;->getApplicationInfo(Ljava/lang/String;Landroid/content/pm/PackageManager$ApplicationInfoFlags;)Landroid/content/pm/ApplicationInfo;

    move-result-object p0
    :try_end_0
    .catch Landroid/content/pm/PackageManager$NameNotFoundException; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    const/4 p0, 0x0

    :goto_0
    return-object p0
.end method

.method private final getGameModeUtils()Lorg/nameless/gamespace/utils/GameModeUtils;
    .locals 0

    .line 43
    iget-object p0, p0, Lorg/nameless/gamespace/preferences/AppListPreferences;->gameModeUtils$delegate:Lkotlin/Lazy;

    invoke-interface {p0}, Lkotlin/Lazy;->getValue()Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lorg/nameless/gamespace/utils/GameModeUtils;

    return-object p0
.end method

.method private final getMakeAddPref()Landroidx/preference/Preference;
    .locals 0

    .line 53
    iget-object p0, p0, Lorg/nameless/gamespace/preferences/AppListPreferences;->makeAddPref$delegate:Lkotlin/Lazy;

    invoke-interface {p0}, Lkotlin/Lazy;->getValue()Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Landroidx/preference/Preference;

    return-object p0
.end method

.method private final getSystemSettings()Lorg/nameless/gamespace/data/SystemSettings;
    .locals 0

    .line 39
    iget-object p0, p0, Lorg/nameless/gamespace/preferences/AppListPreferences;->systemSettings$delegate:Lkotlin/Lazy;

    invoke-interface {p0}, Lkotlin/Lazy;->getValue()Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lorg/nameless/gamespace/data/SystemSettings;

    return-object p0
.end method

.method private final registerApp(Ljava/lang/String;)V
    .locals 5

    .line 95
    iget-object v0, p0, Lorg/nameless/gamespace/preferences/AppListPreferences;->apps:Ljava/util/List;

    check-cast v0, Ljava/lang/Iterable;

    .line 1743
    instance-of v1, v0, Ljava/util/Collection;

    const/4 v2, 0x0

    if-eqz v1, :cond_1

    move-object v1, v0

    check-cast v1, Ljava/util/Collection;

    invoke-interface {v1}, Ljava/util/Collection;->isEmpty()Z

    move-result v1

    if-eqz v1, :cond_1

    :cond_0
    move v0, v2

    goto :goto_0

    .line 1744
    :cond_1
    invoke-interface {v0}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :cond_2
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_0

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lorg/nameless/gamespace/data/UserGame;

    .line 95
    invoke-virtual {v1}, Lorg/nameless/gamespace/data/UserGame;->getPackageName()Ljava/lang/String;

    move-result-object v1

    invoke-static {v1, p1}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_2

    const/4 v0, 0x1

    :goto_0
    if-nez v0, :cond_3

    .line 96
    iget-object v0, p0, Lorg/nameless/gamespace/preferences/AppListPreferences;->apps:Ljava/util/List;

    new-instance v1, Lorg/nameless/gamespace/data/UserGame;

    const/4 v3, 0x2

    const/4 v4, 0x0

    invoke-direct {v1, p1, v2, v3, v4}, Lorg/nameless/gamespace/data/UserGame;-><init>(Ljava/lang/String;IILkotlin/jvm/internal/DefaultConstructorMarker;)V

    invoke-interface {v0, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 98
    :cond_3
    invoke-direct {p0}, Lorg/nameless/gamespace/preferences/AppListPreferences;->getSystemSettings()Lorg/nameless/gamespace/data/SystemSettings;

    move-result-object v0

    iget-object v1, p0, Lorg/nameless/gamespace/preferences/AppListPreferences;->apps:Ljava/util/List;

    invoke-virtual {v0, v1}, Lorg/nameless/gamespace/data/SystemSettings;->setUserGames(Ljava/util/List;)V

    .line 99
    invoke-direct {p0}, Lorg/nameless/gamespace/preferences/AppListPreferences;->getGameModeUtils()Lorg/nameless/gamespace/utils/GameModeUtils;

    move-result-object v0

    sget-object v1, Lorg/nameless/gamespace/data/GameConfig$ModeBuilder;->INSTANCE:Lorg/nameless/gamespace/data/GameConfig$ModeBuilder;

    invoke-virtual {v1}, Lorg/nameless/gamespace/data/GameConfig$ModeBuilder;->build()Ljava/util/List;

    move-result-object v1

    invoke-virtual {v0, p1, v1}, Lorg/nameless/gamespace/utils/GameModeUtils;->setIntervention(Ljava/lang/String;Ljava/util/List;)V

    .line 100
    invoke-virtual {p0}, Lorg/nameless/gamespace/preferences/AppListPreferences;->updateAppList()V

    return-void
.end method

.method private final unregisterApp(Ljava/lang/String;)V
    .locals 2

    .line 104
    iget-object v0, p0, Lorg/nameless/gamespace/preferences/AppListPreferences;->apps:Ljava/util/List;

    new-instance v1, Lorg/nameless/gamespace/preferences/AppListPreferences$unregisterApp$1;

    invoke-direct {v1, p1}, Lorg/nameless/gamespace/preferences/AppListPreferences$unregisterApp$1;-><init>(Ljava/lang/String;)V

    invoke-interface {v0, v1}, Ljava/util/List;->removeIf(Ljava/util/function/Predicate;)Z

    .line 105
    invoke-direct {p0}, Lorg/nameless/gamespace/preferences/AppListPreferences;->getSystemSettings()Lorg/nameless/gamespace/data/SystemSettings;

    move-result-object v0

    iget-object v1, p0, Lorg/nameless/gamespace/preferences/AppListPreferences;->apps:Ljava/util/List;

    invoke-virtual {v0, v1}, Lorg/nameless/gamespace/data/SystemSettings;->setUserGames(Ljava/util/List;)V

    .line 106
    invoke-direct {p0}, Lorg/nameless/gamespace/preferences/AppListPreferences;->getGameModeUtils()Lorg/nameless/gamespace/utils/GameModeUtils;

    move-result-object v0

    const/4 v1, 0x0

    invoke-virtual {v0, p1, v1}, Lorg/nameless/gamespace/utils/GameModeUtils;->setIntervention(Ljava/lang/String;Ljava/util/List;)V

    .line 107
    invoke-virtual {p0}, Lorg/nameless/gamespace/preferences/AppListPreferences;->updateAppList()V

    return-void
.end method


# virtual methods
.method public onAttached()V
    .locals 0

    .line 111
    invoke-super {p0}, Landroidx/preference/PreferenceGroup;->onAttached()V

    .line 112
    invoke-virtual {p0}, Lorg/nameless/gamespace/preferences/AppListPreferences;->updateAppList()V

    return-void
.end method

.method public onPreferenceClick(Landroidx/preference/Preference;)Z
    .locals 1

    const-string v0, "preference"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 116
    invoke-direct {p0}, Lorg/nameless/gamespace/preferences/AppListPreferences;->getMakeAddPref()Landroidx/preference/Preference;

    move-result-object v0

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_1

    iget-object p0, p0, Lorg/nameless/gamespace/preferences/AppListPreferences;->registeredAppClickAction:Lkotlin/jvm/functions/Function1;

    if-eqz p0, :cond_1

    if-nez p0, :cond_0

    const-string p0, "registeredAppClickAction"

    .line 117
    invoke-static {p0}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    const/4 p0, 0x0

    :cond_0
    invoke-virtual {p1}, Landroidx/preference/Preference;->getKey()Ljava/lang/String;

    move-result-object p1

    const-string v0, "preference.key"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullExpressionValue(Ljava/lang/Object;Ljava/lang/String;)V

    invoke-interface {p0, p1}, Lkotlin/jvm/functions/Function1;->invoke(Ljava/lang/Object;)Ljava/lang/Object;

    :cond_1
    const/4 p0, 0x1

    return p0
.end method

.method public final onRegisteredAppClick(Lkotlin/jvm/functions/Function1;)V
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lkotlin/jvm/functions/Function1<",
            "-",
            "Ljava/lang/String;",
            "Lkotlin/Unit;",
            ">;)V"
        }
    .end annotation

    const-string v0, "action"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 123
    iput-object p1, p0, Lorg/nameless/gamespace/preferences/AppListPreferences;->registeredAppClickAction:Lkotlin/jvm/functions/Function1;

    return-void
.end method

.method public final updateAppList()V
    .locals 10

    .line 71
    iget-object v0, p0, Lorg/nameless/gamespace/preferences/AppListPreferences;->apps:Ljava/util/List;

    invoke-interface {v0}, Ljava/util/List;->clear()V

    .line 72
    invoke-direct {p0}, Lorg/nameless/gamespace/preferences/AppListPreferences;->getSystemSettings()Lorg/nameless/gamespace/data/SystemSettings;

    move-result-object v0

    invoke-virtual {v0}, Lorg/nameless/gamespace/data/SystemSettings;->getUserGames()Ljava/util/List;

    move-result-object v0

    check-cast v0, Ljava/util/Collection;

    const/4 v1, 0x1

    const/4 v2, 0x0

    if-eqz v0, :cond_1

    invoke-interface {v0}, Ljava/util/Collection;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_0

    :cond_0
    move v0, v2

    goto :goto_1

    :cond_1
    :goto_0
    move v0, v1

    :goto_1
    if-nez v0, :cond_2

    .line 73
    iget-object v0, p0, Lorg/nameless/gamespace/preferences/AppListPreferences;->apps:Ljava/util/List;

    invoke-direct {p0}, Lorg/nameless/gamespace/preferences/AppListPreferences;->getSystemSettings()Lorg/nameless/gamespace/data/SystemSettings;

    move-result-object v3

    invoke-virtual {v3}, Lorg/nameless/gamespace/data/SystemSettings;->getUserGames()Ljava/util/List;

    move-result-object v3

    check-cast v3, Ljava/util/Collection;

    invoke-interface {v0, v3}, Ljava/util/List;->addAll(Ljava/util/Collection;)Z

    .line 75
    :cond_2
    invoke-virtual {p0}, Landroidx/preference/PreferenceGroup;->removeAll()V

    .line 76
    invoke-direct {p0}, Lorg/nameless/gamespace/preferences/AppListPreferences;->getMakeAddPref()Landroidx/preference/Preference;

    move-result-object v0

    invoke-virtual {p0, v0}, Landroidx/preference/PreferenceGroup;->addPreference(Landroidx/preference/Preference;)Z

    .line 77
    iget-object v0, p0, Lorg/nameless/gamespace/preferences/AppListPreferences;->apps:Ljava/util/List;

    check-cast v0, Ljava/lang/Iterable;

    .line 766
    new-instance v3, Ljava/util/ArrayList;

    invoke-direct {v3}, Ljava/util/ArrayList;-><init>()V

    .line 857
    invoke-interface {v0}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :cond_3
    :goto_2
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v4

    if-eqz v4, :cond_5

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v4

    move-object v5, v4

    check-cast v5, Lorg/nameless/gamespace/data/UserGame;

    .line 77
    invoke-virtual {v5}, Lorg/nameless/gamespace/data/UserGame;->getPackageName()Ljava/lang/String;

    move-result-object v5

    invoke-direct {p0, v5}, Lorg/nameless/gamespace/preferences/AppListPreferences;->getAppInfo(Ljava/lang/String;)Landroid/content/pm/ApplicationInfo;

    move-result-object v5

    if-eqz v5, :cond_4

    move v5, v1

    goto :goto_3

    :cond_4
    move v5, v2

    :goto_3
    if-eqz v5, :cond_3

    .line 857
    invoke-interface {v3, v4}, Ljava/util/Collection;->add(Ljava/lang/Object;)Z

    goto :goto_2

    .line 1549
    :cond_5
    new-instance v0, Ljava/util/ArrayList;

    const/16 v1, 0xa

    invoke-static {v3, v1}, Lkotlin/collections/CollectionsKt;->collectionSizeOrDefault(Ljava/lang/Iterable;I)I

    move-result v1

    invoke-direct {v0, v1}, Ljava/util/ArrayList;-><init>(I)V

    .line 1620
    invoke-interface {v3}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :goto_4
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    if-eqz v3, :cond_8

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v3

    .line 1621
    check-cast v3, Lorg/nameless/gamespace/data/UserGame;

    .line 79
    invoke-virtual {v3}, Lorg/nameless/gamespace/data/UserGame;->getPackageName()Ljava/lang/String;

    move-result-object v4

    invoke-direct {p0, v4}, Lorg/nameless/gamespace/preferences/AppListPreferences;->getAppInfo(Ljava/lang/String;)Landroid/content/pm/ApplicationInfo;

    move-result-object v4

    .line 80
    new-instance v5, Landroidx/preference/Preference;

    invoke-virtual {p0}, Landroidx/preference/Preference;->getContext()Landroid/content/Context;

    move-result-object v6

    invoke-direct {v5, v6}, Landroidx/preference/Preference;-><init>(Landroid/content/Context;)V

    .line 81
    invoke-virtual {v3}, Lorg/nameless/gamespace/data/UserGame;->getPackageName()Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v5, v6}, Landroidx/preference/Preference;->setKey(Ljava/lang/String;)V

    const/4 v6, 0x0

    if-eqz v4, :cond_6

    .line 82
    invoke-virtual {v5}, Landroidx/preference/Preference;->getContext()Landroid/content/Context;

    move-result-object v7

    invoke-virtual {v7}, Landroid/content/Context;->getPackageManager()Landroid/content/pm/PackageManager;

    move-result-object v7

    invoke-virtual {v4, v7}, Landroid/content/pm/ApplicationInfo;->loadLabel(Landroid/content/pm/PackageManager;)Ljava/lang/CharSequence;

    move-result-object v7

    goto :goto_5

    :cond_6
    move-object v7, v6

    :goto_5
    invoke-virtual {v5, v7}, Landroidx/preference/Preference;->setTitle(Ljava/lang/CharSequence;)V

    .line 83
    sget-object v7, Lorg/nameless/gamespace/utils/GameModeUtils;->Companion:Lorg/nameless/gamespace/utils/GameModeUtils$Companion;

    invoke-virtual {v5}, Landroidx/preference/Preference;->getContext()Landroid/content/Context;

    move-result-object v8

    const-string v9, "context"

    invoke-static {v8, v9}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullExpressionValue(Ljava/lang/Object;Ljava/lang/String;)V

    invoke-virtual {v3}, Lorg/nameless/gamespace/data/UserGame;->getMode()I

    move-result v3

    invoke-virtual {v7, v8, v3}, Lorg/nameless/gamespace/utils/GameModeUtils$Companion;->describeGameMode(Landroid/content/Context;I)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v5, v3}, Landroidx/preference/Preference;->setSummary(Ljava/lang/CharSequence;)V

    if-eqz v4, :cond_7

    .line 84
    invoke-virtual {v5}, Landroidx/preference/Preference;->getContext()Landroid/content/Context;

    move-result-object v3

    invoke-virtual {v3}, Landroid/content/Context;->getPackageManager()Landroid/content/pm/PackageManager;

    move-result-object v3

    invoke-virtual {v4, v3}, Landroid/content/pm/ApplicationInfo;->loadIcon(Landroid/content/pm/PackageManager;)Landroid/graphics/drawable/Drawable;

    move-result-object v6

    :cond_7
    invoke-virtual {v5, v6}, Landroidx/preference/Preference;->setIcon(Landroid/graphics/drawable/Drawable;)V

    const v3, 0x7f0d003f

    .line 85
    invoke-virtual {v5, v3}, Landroidx/preference/Preference;->setLayoutResource(I)V

    .line 86
    invoke-virtual {v5, v2}, Landroidx/preference/Preference;->setPersistent(Z)V

    .line 87
    invoke-virtual {v5, p0}, Landroidx/preference/Preference;->setOnPreferenceClickListener(Landroidx/preference/Preference$OnPreferenceClickListener;)V

    .line 1621
    invoke-interface {v0, v5}, Ljava/util/Collection;->add(Ljava/lang/Object;)Z

    goto :goto_4

    .line 1045
    :cond_8
    new-instance v1, Lorg/nameless/gamespace/preferences/AppListPreferences$updateAppList$$inlined$sortedBy$1;

    invoke-direct {v1}, Lorg/nameless/gamespace/preferences/AppListPreferences$updateAppList$$inlined$sortedBy$1;-><init>()V

    invoke-static {v0, v1}, Lkotlin/collections/CollectionsKt;->sortedWith(Ljava/lang/Iterable;Ljava/util/Comparator;)Ljava/util/List;

    move-result-object v0

    check-cast v0, Ljava/lang/Iterable;

    .line 1851
    invoke-interface {v0}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :goto_6
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_9

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Landroidx/preference/Preference;

    .line 91
    invoke-virtual {p0, v1}, Landroidx/preference/PreferenceGroup;->addPreference(Landroidx/preference/Preference;)Z

    goto :goto_6

    :cond_9
    return-void
.end method

.method public final usePerAppResult(Landroidx/activity/result/ActivityResult;)V
    .locals 2

    if-eqz p1, :cond_2

    .line 127
    invoke-virtual {p1}, Landroidx/activity/result/ActivityResult;->getResultCode()I

    move-result v0

    const/4 v1, -0x1

    if-ne v0, v1, :cond_0

    const/4 v0, 0x1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    if-eqz v0, :cond_1

    goto :goto_1

    :cond_1
    const/4 p1, 0x0

    :goto_1
    if-eqz p1, :cond_2

    .line 128
    invoke-virtual {p1}, Landroidx/activity/result/ActivityResult;->getData()Landroid/content/Intent;

    move-result-object p1

    if-eqz p1, :cond_2

    const-string v0, "per_app_unregister"

    invoke-virtual {p1, v0}, Landroid/content/Intent;->getStringExtra(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    if-eqz p1, :cond_2

    .line 129
    invoke-direct {p0, p1}, Lorg/nameless/gamespace/preferences/AppListPreferences;->unregisterApp(Ljava/lang/String;)V

    :cond_2
    return-void
.end method

.method public final useSelectorResult(Landroidx/activity/result/ActivityResult;)V
    .locals 2

    if-eqz p1, :cond_2

    .line 133
    invoke-virtual {p1}, Landroidx/activity/result/ActivityResult;->getResultCode()I

    move-result v0

    const/4 v1, -0x1

    if-ne v0, v1, :cond_0

    const/4 v0, 0x1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    if-eqz v0, :cond_1

    goto :goto_1

    :cond_1
    const/4 p1, 0x0

    :goto_1
    if-eqz p1, :cond_2

    .line 134
    invoke-virtual {p1}, Landroidx/activity/result/ActivityResult;->getData()Landroid/content/Intent;

    move-result-object p1

    if-eqz p1, :cond_2

    const-string v0, "selected_app"

    invoke-virtual {p1, v0}, Landroid/content/Intent;->getStringExtra(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    if-eqz p1, :cond_2

    .line 135
    invoke-direct {p0, p1}, Lorg/nameless/gamespace/preferences/AppListPreferences;->registerApp(Ljava/lang/String;)V

    :cond_2
    return-void
.end method
