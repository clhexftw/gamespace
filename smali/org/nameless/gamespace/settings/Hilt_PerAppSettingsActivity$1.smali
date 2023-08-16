.class Lorg/nameless/gamespace/settings/Hilt_PerAppSettingsActivity$1;
.super Ljava/lang/Object;
.source "Hilt_PerAppSettingsActivity.java"

# interfaces
.implements Landroidx/activity/contextaware/OnContextAvailableListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lorg/nameless/gamespace/settings/Hilt_PerAppSettingsActivity;->_initHiltInternal()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lorg/nameless/gamespace/settings/Hilt_PerAppSettingsActivity;


# direct methods
.method constructor <init>(Lorg/nameless/gamespace/settings/Hilt_PerAppSettingsActivity;)V
    .locals 0

    .line 32
    iput-object p1, p0, Lorg/nameless/gamespace/settings/Hilt_PerAppSettingsActivity$1;->this$0:Lorg/nameless/gamespace/settings/Hilt_PerAppSettingsActivity;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onContextAvailable(Landroid/content/Context;)V
    .locals 0

    .line 35
    iget-object p0, p0, Lorg/nameless/gamespace/settings/Hilt_PerAppSettingsActivity$1;->this$0:Lorg/nameless/gamespace/settings/Hilt_PerAppSettingsActivity;

    invoke-virtual {p0}, Lorg/nameless/gamespace/settings/Hilt_PerAppSettingsActivity;->inject()V

    return-void
.end method
