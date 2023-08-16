.class public final Lorg/nameless/gamespace/settings/SettingsFragment;
.super Lorg/nameless/gamespace/settings/Hilt_SettingsFragment;
.source "SettingsFragment.kt"


# instance fields
.field private apps:Lorg/nameless/gamespace/preferences/AppListPreferences;

.field private final perAppResult:Landroidx/activity/result/ActivityResultLauncher;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroidx/activity/result/ActivityResultLauncher<",
            "Landroid/content/Intent;",
            ">;"
        }
    .end annotation
.end field

.field private final selectorResult:Landroidx/activity/result/ActivityResultLauncher;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroidx/activity/result/ActivityResultLauncher<",
            "Landroid/content/Intent;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method public constructor <init>()V
    .locals 2

    .line 32
    invoke-direct {p0}, Lorg/nameless/gamespace/settings/Hilt_SettingsFragment;-><init>()V

    .line 38
    new-instance v0, Landroidx/activity/result/contract/ActivityResultContracts$StartActivityForResult;

    invoke-direct {v0}, Landroidx/activity/result/contract/ActivityResultContracts$StartActivityForResult;-><init>()V

    .line 37
    new-instance v1, Lorg/nameless/gamespace/settings/SettingsFragment$selectorResult$1;

    invoke-direct {v1, p0}, Lorg/nameless/gamespace/settings/SettingsFragment$selectorResult$1;-><init>(Lorg/nameless/gamespace/settings/SettingsFragment;)V

    invoke-virtual {p0, v0, v1}, Landroidx/fragment/app/Fragment;->registerForActivityResult(Landroidx/activity/result/contract/ActivityResultContract;Landroidx/activity/result/ActivityResultCallback;)Landroidx/activity/result/ActivityResultLauncher;

    move-result-object v0

    const-string v1, "registerForActivityResul\u2026ectorResult(it)\n        }"

    invoke-static {v0, v1}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullExpressionValue(Ljava/lang/Object;Ljava/lang/String;)V

    iput-object v0, p0, Lorg/nameless/gamespace/settings/SettingsFragment;->selectorResult:Landroidx/activity/result/ActivityResultLauncher;

    .line 45
    new-instance v0, Landroidx/activity/result/contract/ActivityResultContracts$StartActivityForResult;

    invoke-direct {v0}, Landroidx/activity/result/contract/ActivityResultContracts$StartActivityForResult;-><init>()V

    .line 44
    new-instance v1, Lorg/nameless/gamespace/settings/SettingsFragment$perAppResult$1;

    invoke-direct {v1, p0}, Lorg/nameless/gamespace/settings/SettingsFragment$perAppResult$1;-><init>(Lorg/nameless/gamespace/settings/SettingsFragment;)V

    invoke-virtual {p0, v0, v1}, Landroidx/fragment/app/Fragment;->registerForActivityResult(Landroidx/activity/result/contract/ActivityResultContract;Landroidx/activity/result/ActivityResultCallback;)Landroidx/activity/result/ActivityResultLauncher;

    move-result-object v0

    const-string v1, "registerForActivityResul\u2026erAppResult(it)\n        }"

    invoke-static {v0, v1}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullExpressionValue(Ljava/lang/Object;Ljava/lang/String;)V

    iput-object v0, p0, Lorg/nameless/gamespace/settings/SettingsFragment;->perAppResult:Landroidx/activity/result/ActivityResultLauncher;

    return-void
.end method

.method public static final synthetic access$getApps$p(Lorg/nameless/gamespace/settings/SettingsFragment;)Lorg/nameless/gamespace/preferences/AppListPreferences;
    .locals 0

    .line 31
    iget-object p0, p0, Lorg/nameless/gamespace/settings/SettingsFragment;->apps:Lorg/nameless/gamespace/preferences/AppListPreferences;

    return-object p0
.end method

.method public static final synthetic access$getPerAppResult$p(Lorg/nameless/gamespace/settings/SettingsFragment;)Landroidx/activity/result/ActivityResultLauncher;
    .locals 0

    .line 31
    iget-object p0, p0, Lorg/nameless/gamespace/settings/SettingsFragment;->perAppResult:Landroidx/activity/result/ActivityResultLauncher;

    return-object p0
.end method

.method public static final synthetic access$getSelectorResult$p(Lorg/nameless/gamespace/settings/SettingsFragment;)Landroidx/activity/result/ActivityResultLauncher;
    .locals 0

    .line 31
    iget-object p0, p0, Lorg/nameless/gamespace/settings/SettingsFragment;->selectorResult:Landroidx/activity/result/ActivityResultLauncher;

    return-object p0
.end method


# virtual methods
.method public onCreatePreferences(Landroid/os/Bundle;Ljava/lang/String;)V
    .locals 0

    const p1, 0x7f150001

    .line 51
    invoke-virtual {p0, p1, p2}, Landroidx/preference/PreferenceFragmentCompat;->setPreferencesFromResource(ILjava/lang/String;)V

    return-void
.end method

.method public onResume()V
    .locals 0

    .line 71
    invoke-super {p0}, Landroidx/fragment/app/Fragment;->onResume()V

    .line 72
    iget-object p0, p0, Lorg/nameless/gamespace/settings/SettingsFragment;->apps:Lorg/nameless/gamespace/preferences/AppListPreferences;

    if-eqz p0, :cond_0

    invoke-virtual {p0}, Lorg/nameless/gamespace/preferences/AppListPreferences;->updateAppList()V

    :cond_0
    return-void
.end method

.method public onViewCreated(Landroid/view/View;Landroid/os/Bundle;)V
    .locals 1

    const-string v0, "view"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 55
    invoke-super {p0, p1, p2}, Landroidx/preference/PreferenceFragmentCompat;->onViewCreated(Landroid/view/View;Landroid/os/Bundle;)V

    const-string p1, "gamespace_game_list"

    .line 56
    invoke-virtual {p0, p1}, Landroidx/preference/PreferenceFragmentCompat;->findPreference(Ljava/lang/CharSequence;)Landroidx/preference/Preference;

    move-result-object p1

    check-cast p1, Lorg/nameless/gamespace/preferences/AppListPreferences;

    iput-object p1, p0, Lorg/nameless/gamespace/settings/SettingsFragment;->apps:Lorg/nameless/gamespace/preferences/AppListPreferences;

    if-eqz p1, :cond_0

    .line 57
    new-instance p2, Lorg/nameless/gamespace/settings/SettingsFragment$onViewCreated$1;

    invoke-direct {p2, p0}, Lorg/nameless/gamespace/settings/SettingsFragment$onViewCreated$1;-><init>(Lorg/nameless/gamespace/settings/SettingsFragment;)V

    invoke-virtual {p1, p2}, Lorg/nameless/gamespace/preferences/AppListPreferences;->onRegisteredAppClick(Lkotlin/jvm/functions/Function1;)V

    :cond_0
    const-string p1, "add_game"

    .line 63
    invoke-virtual {p0, p1}, Landroidx/preference/PreferenceFragmentCompat;->findPreference(Ljava/lang/CharSequence;)Landroidx/preference/Preference;

    move-result-object p1

    if-eqz p1, :cond_1

    .line 64
    new-instance p2, Lorg/nameless/gamespace/settings/SettingsFragment$onViewCreated$2;

    invoke-direct {p2, p0}, Lorg/nameless/gamespace/settings/SettingsFragment$onViewCreated$2;-><init>(Lorg/nameless/gamespace/settings/SettingsFragment;)V

    invoke-virtual {p1, p2}, Landroidx/preference/Preference;->setOnPreferenceClickListener(Landroidx/preference/Preference$OnPreferenceClickListener;)V

    :cond_1
    return-void
.end method
