.class final Lorg/nameless/gamespace/settings/SettingsFragment$onViewCreated$1;
.super Lkotlin/jvm/internal/Lambda;
.source "SettingsFragment.kt"

# interfaces
.implements Lkotlin/jvm/functions/Function1;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lorg/nameless/gamespace/settings/SettingsFragment;->onViewCreated(Landroid/view/View;Landroid/os/Bundle;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x18
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lkotlin/jvm/internal/Lambda;",
        "Lkotlin/jvm/functions/Function1<",
        "Ljava/lang/String;",
        "Lkotlin/Unit;",
        ">;"
    }
.end annotation


# instance fields
.field final synthetic this$0:Lorg/nameless/gamespace/settings/SettingsFragment;


# direct methods
.method constructor <init>(Lorg/nameless/gamespace/settings/SettingsFragment;)V
    .locals 0

    iput-object p1, p0, Lorg/nameless/gamespace/settings/SettingsFragment$onViewCreated$1;->this$0:Lorg/nameless/gamespace/settings/SettingsFragment;

    const/4 p1, 0x1

    invoke-direct {p0, p1}, Lkotlin/jvm/internal/Lambda;-><init>(I)V

    return-void
.end method


# virtual methods
.method public bridge synthetic invoke(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    .line 57
    check-cast p1, Ljava/lang/String;

    invoke-virtual {p0, p1}, Lorg/nameless/gamespace/settings/SettingsFragment$onViewCreated$1;->invoke(Ljava/lang/String;)V

    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0
.end method

.method public final invoke(Ljava/lang/String;)V
    .locals 3

    const-string v0, "it"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 58
    iget-object v0, p0, Lorg/nameless/gamespace/settings/SettingsFragment$onViewCreated$1;->this$0:Lorg/nameless/gamespace/settings/SettingsFragment;

    invoke-static {v0}, Lorg/nameless/gamespace/settings/SettingsFragment;->access$getPerAppResult$p(Lorg/nameless/gamespace/settings/SettingsFragment;)Landroidx/activity/result/ActivityResultLauncher;

    move-result-object v0

    new-instance v1, Landroid/content/Intent;

    iget-object p0, p0, Lorg/nameless/gamespace/settings/SettingsFragment$onViewCreated$1;->this$0:Lorg/nameless/gamespace/settings/SettingsFragment;

    invoke-virtual {p0}, Lorg/nameless/gamespace/settings/Hilt_SettingsFragment;->getContext()Landroid/content/Context;

    move-result-object p0

    const-class v2, Lorg/nameless/gamespace/settings/PerAppSettingsActivity;

    invoke-direct {v1, p0, v2}, Landroid/content/Intent;-><init>(Landroid/content/Context;Ljava/lang/Class;)V

    const-string p0, "package_name"

    .line 59
    invoke-virtual {v1, p0, p1}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;

    .line 58
    invoke-virtual {v0, v1}, Landroidx/activity/result/ActivityResultLauncher;->launch(Ljava/lang/Object;)V

    return-void
.end method
