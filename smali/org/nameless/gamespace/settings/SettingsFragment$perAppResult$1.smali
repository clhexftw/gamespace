.class final Lorg/nameless/gamespace/settings/SettingsFragment$perAppResult$1;
.super Ljava/lang/Object;
.source "SettingsFragment.kt"

# interfaces
.implements Landroidx/activity/result/ActivityResultCallback;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lorg/nameless/gamespace/settings/SettingsFragment;-><init>()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x18
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "<O:",
        "Ljava/lang/Object;",
        ">",
        "Ljava/lang/Object;",
        "Landroidx/activity/result/ActivityResultCallback;"
    }
.end annotation


# instance fields
.field final synthetic this$0:Lorg/nameless/gamespace/settings/SettingsFragment;


# direct methods
.method constructor <init>(Lorg/nameless/gamespace/settings/SettingsFragment;)V
    .locals 0

    iput-object p1, p0, Lorg/nameless/gamespace/settings/SettingsFragment$perAppResult$1;->this$0:Lorg/nameless/gamespace/settings/SettingsFragment;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public final onActivityResult(Landroidx/activity/result/ActivityResult;)V
    .locals 0

    .line 47
    iget-object p0, p0, Lorg/nameless/gamespace/settings/SettingsFragment$perAppResult$1;->this$0:Lorg/nameless/gamespace/settings/SettingsFragment;

    invoke-static {p0}, Lorg/nameless/gamespace/settings/SettingsFragment;->access$getApps$p(Lorg/nameless/gamespace/settings/SettingsFragment;)Lorg/nameless/gamespace/preferences/AppListPreferences;

    move-result-object p0

    if-eqz p0, :cond_0

    invoke-virtual {p0, p1}, Lorg/nameless/gamespace/preferences/AppListPreferences;->usePerAppResult(Landroidx/activity/result/ActivityResult;)V

    :cond_0
    return-void
.end method

.method public bridge synthetic onActivityResult(Ljava/lang/Object;)V
    .locals 0

    .line 44
    check-cast p1, Landroidx/activity/result/ActivityResult;

    invoke-virtual {p0, p1}, Lorg/nameless/gamespace/settings/SettingsFragment$perAppResult$1;->onActivityResult(Landroidx/activity/result/ActivityResult;)V

    return-void
.end method
