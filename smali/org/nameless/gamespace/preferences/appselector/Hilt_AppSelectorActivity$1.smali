.class Lorg/nameless/gamespace/preferences/appselector/Hilt_AppSelectorActivity$1;
.super Ljava/lang/Object;
.source "Hilt_AppSelectorActivity.java"

# interfaces
.implements Landroidx/activity/contextaware/OnContextAvailableListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lorg/nameless/gamespace/preferences/appselector/Hilt_AppSelectorActivity;->_initHiltInternal()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lorg/nameless/gamespace/preferences/appselector/Hilt_AppSelectorActivity;


# direct methods
.method constructor <init>(Lorg/nameless/gamespace/preferences/appselector/Hilt_AppSelectorActivity;)V
    .locals 0

    .line 32
    iput-object p1, p0, Lorg/nameless/gamespace/preferences/appselector/Hilt_AppSelectorActivity$1;->this$0:Lorg/nameless/gamespace/preferences/appselector/Hilt_AppSelectorActivity;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onContextAvailable(Landroid/content/Context;)V
    .locals 0

    .line 35
    iget-object p0, p0, Lorg/nameless/gamespace/preferences/appselector/Hilt_AppSelectorActivity$1;->this$0:Lorg/nameless/gamespace/preferences/appselector/Hilt_AppSelectorActivity;

    invoke-virtual {p0}, Lorg/nameless/gamespace/preferences/appselector/Hilt_AppSelectorActivity;->inject()V

    return-void
.end method
