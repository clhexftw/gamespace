.class final Lorg/nameless/gamespace/settings/SettingsFragment$onViewCreated$2;
.super Ljava/lang/Object;
.source "SettingsFragment.kt"

# interfaces
.implements Landroidx/preference/Preference$OnPreferenceClickListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lorg/nameless/gamespace/settings/SettingsFragment;->onViewCreated(Landroid/view/View;Landroid/os/Bundle;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x18
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lorg/nameless/gamespace/settings/SettingsFragment;


# direct methods
.method constructor <init>(Lorg/nameless/gamespace/settings/SettingsFragment;)V
    .locals 0

    iput-object p1, p0, Lorg/nameless/gamespace/settings/SettingsFragment$onViewCreated$2;->this$0:Lorg/nameless/gamespace/settings/SettingsFragment;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public final onPreferenceClick(Landroidx/preference/Preference;)Z
    .locals 2

    .line 65
    iget-object p1, p0, Lorg/nameless/gamespace/settings/SettingsFragment$onViewCreated$2;->this$0:Lorg/nameless/gamespace/settings/SettingsFragment;

    invoke-static {p1}, Lorg/nameless/gamespace/settings/SettingsFragment;->access$getSelectorResult$p(Lorg/nameless/gamespace/settings/SettingsFragment;)Landroidx/activity/result/ActivityResultLauncher;

    move-result-object p1

    new-instance v0, Landroid/content/Intent;

    iget-object p0, p0, Lorg/nameless/gamespace/settings/SettingsFragment$onViewCreated$2;->this$0:Lorg/nameless/gamespace/settings/SettingsFragment;

    invoke-virtual {p0}, Lorg/nameless/gamespace/settings/Hilt_SettingsFragment;->getContext()Landroid/content/Context;

    move-result-object p0

    const-class v1, Lorg/nameless/gamespace/preferences/appselector/AppSelectorActivity;

    invoke-direct {v0, p0, v1}, Landroid/content/Intent;-><init>(Landroid/content/Context;Ljava/lang/Class;)V

    invoke-virtual {p1, v0}, Landroidx/activity/result/ActivityResultLauncher;->launch(Ljava/lang/Object;)V

    const/4 p0, 0x1

    return p0
.end method
