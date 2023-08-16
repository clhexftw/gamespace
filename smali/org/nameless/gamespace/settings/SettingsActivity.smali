.class public final Lorg/nameless/gamespace/settings/SettingsActivity;
.super Lorg/nameless/gamespace/settings/Hilt_SettingsActivity;
.source "SettingsActivity.kt"


# direct methods
.method public constructor <init>()V
    .locals 0

    .line 24
    invoke-direct {p0}, Lorg/nameless/gamespace/settings/Hilt_SettingsActivity;-><init>()V

    return-void
.end method


# virtual methods
.method protected onCreate(Landroid/os/Bundle;)V
    .locals 1

    .line 27
    invoke-super {p0, p1}, Lcom/android/settingslib/collapsingtoolbar/CollapsingToolbarBaseActivity;->onCreate(Landroid/os/Bundle;)V

    if-nez p1, :cond_0

    .line 29
    invoke-virtual {p0}, Landroidx/fragment/app/FragmentActivity;->getSupportFragmentManager()Landroidx/fragment/app/FragmentManager;

    move-result-object p0

    .line 30
    invoke-virtual {p0}, Landroidx/fragment/app/FragmentManager;->beginTransaction()Landroidx/fragment/app/FragmentTransaction;

    move-result-object p0

    const p1, 0x7f0a00b9

    .line 31
    new-instance v0, Lorg/nameless/gamespace/settings/SettingsFragment;

    invoke-direct {v0}, Lorg/nameless/gamespace/settings/SettingsFragment;-><init>()V

    invoke-virtual {p0, p1, v0}, Landroidx/fragment/app/FragmentTransaction;->replace(ILandroidx/fragment/app/Fragment;)Landroidx/fragment/app/FragmentTransaction;

    move-result-object p0

    .line 32
    invoke-virtual {p0}, Landroidx/fragment/app/FragmentTransaction;->commit()I

    :cond_0
    return-void
.end method
