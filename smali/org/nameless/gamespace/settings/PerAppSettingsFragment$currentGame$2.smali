.class final Lorg/nameless/gamespace/settings/PerAppSettingsFragment$currentGame$2;
.super Lkotlin/jvm/internal/Lambda;
.source "PerAppSettingsFragment.kt"

# interfaces
.implements Lkotlin/jvm/functions/Function0;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lorg/nameless/gamespace/settings/PerAppSettingsFragment;-><init>()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x18
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lkotlin/jvm/internal/Lambda;",
        "Lkotlin/jvm/functions/Function0<",
        "Landroid/content/pm/ApplicationInfo;",
        ">;"
    }
.end annotation


# instance fields
.field final synthetic this$0:Lorg/nameless/gamespace/settings/PerAppSettingsFragment;


# direct methods
.method constructor <init>(Lorg/nameless/gamespace/settings/PerAppSettingsFragment;)V
    .locals 0

    iput-object p1, p0, Lorg/nameless/gamespace/settings/PerAppSettingsFragment$currentGame$2;->this$0:Lorg/nameless/gamespace/settings/PerAppSettingsFragment;

    const/4 p1, 0x0

    invoke-direct {p0, p1}, Lkotlin/jvm/internal/Lambda;-><init>(I)V

    return-void
.end method


# virtual methods
.method public final invoke()Landroid/content/pm/ApplicationInfo;
    .locals 4

    .line 46
    iget-object v0, p0, Lorg/nameless/gamespace/settings/PerAppSettingsFragment$currentGame$2;->this$0:Lorg/nameless/gamespace/settings/PerAppSettingsFragment;

    invoke-virtual {v0}, Landroidx/fragment/app/Fragment;->getActivity()Landroidx/fragment/app/FragmentActivity;

    move-result-object v0

    const/4 v1, 0x0

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Landroid/app/Activity;->getIntent()Landroid/content/Intent;

    move-result-object v0

    if-eqz v0, :cond_0

    const-string v2, "package_name"

    invoke-virtual {v0, v2}, Landroid/content/Intent;->getStringExtra(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    if-eqz v0, :cond_0

    iget-object p0, p0, Lorg/nameless/gamespace/settings/PerAppSettingsFragment$currentGame$2;->this$0:Lorg/nameless/gamespace/settings/PerAppSettingsFragment;

    const-wide/16 v2, 0x0

    .line 47
    invoke-static {v2, v3}, Landroid/content/pm/PackageManager$ApplicationInfoFlags;->of(J)Landroid/content/pm/PackageManager$ApplicationInfoFlags;

    move-result-object v2

    .line 48
    invoke-virtual {p0}, Lorg/nameless/gamespace/settings/Hilt_PerAppSettingsFragment;->getContext()Landroid/content/Context;

    move-result-object p0

    if-eqz p0, :cond_0

    invoke-virtual {p0}, Landroid/content/Context;->getPackageManager()Landroid/content/pm/PackageManager;

    move-result-object p0

    if-eqz p0, :cond_0

    invoke-virtual {p0, v0, v2}, Landroid/content/pm/PackageManager;->getApplicationInfo(Ljava/lang/String;Landroid/content/pm/PackageManager$ApplicationInfoFlags;)Landroid/content/pm/ApplicationInfo;

    move-result-object p0

    move-object v1, p0

    :cond_0
    return-object v1
.end method

.method public bridge synthetic invoke()Ljava/lang/Object;
    .locals 0

    .line 45
    invoke-virtual {p0}, Lorg/nameless/gamespace/settings/PerAppSettingsFragment$currentGame$2;->invoke()Landroid/content/pm/ApplicationInfo;

    move-result-object p0

    return-object p0
.end method
