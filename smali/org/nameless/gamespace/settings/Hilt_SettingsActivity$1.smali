.class Lorg/nameless/gamespace/settings/Hilt_SettingsActivity$1;
.super Ljava/lang/Object;
.source "Hilt_SettingsActivity.java"

# interfaces
.implements Landroidx/activity/contextaware/OnContextAvailableListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lorg/nameless/gamespace/settings/Hilt_SettingsActivity;->_initHiltInternal()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lorg/nameless/gamespace/settings/Hilt_SettingsActivity;


# direct methods
.method constructor <init>(Lorg/nameless/gamespace/settings/Hilt_SettingsActivity;)V
    .locals 0

    .line 32
    iput-object p1, p0, Lorg/nameless/gamespace/settings/Hilt_SettingsActivity$1;->this$0:Lorg/nameless/gamespace/settings/Hilt_SettingsActivity;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onContextAvailable(Landroid/content/Context;)V
    .locals 0

    .line 35
    iget-object p0, p0, Lorg/nameless/gamespace/settings/Hilt_SettingsActivity$1;->this$0:Lorg/nameless/gamespace/settings/Hilt_SettingsActivity;

    invoke-virtual {p0}, Lorg/nameless/gamespace/settings/Hilt_SettingsActivity;->inject()V

    return-void
.end method
