.class public final Lorg/nameless/gamespace/settings/PerAppSettingsActivity;
.super Lorg/nameless/gamespace/settings/Hilt_PerAppSettingsActivity;
.source "PerAppSettingsActivity.kt"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lorg/nameless/gamespace/settings/PerAppSettingsActivity$Companion;
    }
.end annotation


# static fields
.field public static final Companion:Lorg/nameless/gamespace/settings/PerAppSettingsActivity$Companion;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    new-instance v0, Lorg/nameless/gamespace/settings/PerAppSettingsActivity$Companion;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lorg/nameless/gamespace/settings/PerAppSettingsActivity$Companion;-><init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V

    sput-object v0, Lorg/nameless/gamespace/settings/PerAppSettingsActivity;->Companion:Lorg/nameless/gamespace/settings/PerAppSettingsActivity$Companion;

    return-void
.end method

.method public constructor <init>()V
    .locals 0

    .line 25
    invoke-direct {p0}, Lorg/nameless/gamespace/settings/Hilt_PerAppSettingsActivity;-><init>()V

    return-void
.end method


# virtual methods
.method protected onCreate(Landroid/os/Bundle;)V
    .locals 1

    .line 28
    invoke-super {p0, p1}, Lcom/android/settingslib/collapsingtoolbar/CollapsingToolbarBaseActivity;->onCreate(Landroid/os/Bundle;)V

    if-nez p1, :cond_0

    .line 30
    invoke-virtual {p0}, Landroidx/fragment/app/FragmentActivity;->getSupportFragmentManager()Landroidx/fragment/app/FragmentManager;

    move-result-object p0

    .line 31
    invoke-virtual {p0}, Landroidx/fragment/app/FragmentManager;->beginTransaction()Landroidx/fragment/app/FragmentTransaction;

    move-result-object p0

    const p1, 0x7f0a00b9

    .line 32
    new-instance v0, Lorg/nameless/gamespace/settings/PerAppSettingsFragment;

    invoke-direct {v0}, Lorg/nameless/gamespace/settings/PerAppSettingsFragment;-><init>()V

    invoke-virtual {p0, p1, v0}, Landroidx/fragment/app/FragmentTransaction;->replace(ILandroidx/fragment/app/Fragment;)Landroidx/fragment/app/FragmentTransaction;

    move-result-object p0

    .line 33
    invoke-virtual {p0}, Landroidx/fragment/app/FragmentTransaction;->commit()I

    :cond_0
    return-void
.end method
