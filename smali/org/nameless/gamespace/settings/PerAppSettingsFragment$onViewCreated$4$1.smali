.class final Lorg/nameless/gamespace/settings/PerAppSettingsFragment$onViewCreated$4$1;
.super Ljava/lang/Object;
.source "PerAppSettingsFragment.kt"

# interfaces
.implements Landroidx/preference/Preference$OnPreferenceClickListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lorg/nameless/gamespace/settings/PerAppSettingsFragment;->onViewCreated(Landroid/view/View;Landroid/os/Bundle;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x18
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lorg/nameless/gamespace/settings/PerAppSettingsFragment;


# direct methods
.method constructor <init>(Lorg/nameless/gamespace/settings/PerAppSettingsFragment;)V
    .locals 0

    iput-object p1, p0, Lorg/nameless/gamespace/settings/PerAppSettingsFragment$onViewCreated$4$1;->this$0:Lorg/nameless/gamespace/settings/PerAppSettingsFragment;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public final onPreferenceClick(Landroidx/preference/Preference;)Z
    .locals 4

    .line 96
    iget-object p1, p0, Lorg/nameless/gamespace/settings/PerAppSettingsFragment$onViewCreated$4$1;->this$0:Lorg/nameless/gamespace/settings/PerAppSettingsFragment;

    invoke-virtual {p1}, Landroidx/fragment/app/Fragment;->getActivity()Landroidx/fragment/app/FragmentActivity;

    move-result-object p1

    if-eqz p1, :cond_1

    const/4 v0, -0x1

    new-instance v1, Landroid/content/Intent;

    invoke-direct {v1}, Landroid/content/Intent;-><init>()V

    iget-object v2, p0, Lorg/nameless/gamespace/settings/PerAppSettingsFragment$onViewCreated$4$1;->this$0:Lorg/nameless/gamespace/settings/PerAppSettingsFragment;

    .line 97
    invoke-static {v2}, Lorg/nameless/gamespace/settings/PerAppSettingsFragment;->access$getCurrentGame(Lorg/nameless/gamespace/settings/PerAppSettingsFragment;)Landroid/content/pm/ApplicationInfo;

    move-result-object v2

    if-eqz v2, :cond_0

    iget-object v2, v2, Landroid/content/pm/ApplicationInfo;->packageName:Ljava/lang/String;

    goto :goto_0

    :cond_0
    const/4 v2, 0x0

    :goto_0
    const-string v3, "per_app_unregister"

    invoke-virtual {v1, v3, v2}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;

    .line 98
    sget-object v2, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    .line 96
    invoke-virtual {p1, v0, v1}, Landroid/app/Activity;->setResult(ILandroid/content/Intent;)V

    .line 99
    :cond_1
    iget-object p0, p0, Lorg/nameless/gamespace/settings/PerAppSettingsFragment$onViewCreated$4$1;->this$0:Lorg/nameless/gamespace/settings/PerAppSettingsFragment;

    invoke-virtual {p0}, Landroidx/fragment/app/Fragment;->getActivity()Landroidx/fragment/app/FragmentActivity;

    move-result-object p0

    if-eqz p0, :cond_2

    invoke-virtual {p0}, Landroid/app/Activity;->finish()V

    :cond_2
    const/4 p0, 0x1

    return p0
.end method
