.class final Lorg/nameless/gamespace/widget/PanelView$tempUpdate;
.super Ljava/lang/Object;
.source "PanelView.kt"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/nameless/gamespace/widget/PanelView;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x12
    name = "tempUpdate"
.end annotation


# instance fields
.field final synthetic this$0:Lorg/nameless/gamespace/widget/PanelView;


# direct methods
.method public constructor <init>(Lorg/nameless/gamespace/widget/PanelView;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()V"
        }
    .end annotation

    .line 46
    iput-object p1, p0, Lorg/nameless/gamespace/widget/PanelView$tempUpdate;->this$0:Lorg/nameless/gamespace/widget/PanelView;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .locals 4

    .line 49
    iget-object v0, p0, Lorg/nameless/gamespace/widget/PanelView$tempUpdate;->this$0:Lorg/nameless/gamespace/widget/PanelView;

    invoke-virtual {v0}, Landroid/widget/LinearLayout;->getContext()Landroid/content/Context;

    move-result-object v0

    new-instance v1, Landroid/content/IntentFilter;

    const-string v2, "android.intent.action.BATTERY_CHANGED"

    invoke-direct {v1, v2}, Landroid/content/IntentFilter;-><init>(Ljava/lang/String;)V

    const/4 v2, 0x0

    invoke-virtual {v0, v2, v1}, Landroid/content/Context;->registerReceiver(Landroid/content/BroadcastReceiver;Landroid/content/IntentFilter;)Landroid/content/Intent;

    move-result-object v0

    const-string v1, "context.registerReceiver\u2026.ACTION_BATTERY_CHANGED))"

    invoke-static {v0, v1}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullExpressionValue(Ljava/lang/Object;Ljava/lang/String;)V

    const-string v1, "temperature"

    const/4 v2, 0x0

    .line 50
    invoke-virtual {v0, v1, v2}, Landroid/content/Intent;->getIntExtra(Ljava/lang/String;I)I

    move-result v0

    int-to-float v0, v0

    const/16 v1, 0xa

    int-to-float v1, v1

    div-float/2addr v0, v1

    .line 52
    iget-object v1, p0, Lorg/nameless/gamespace/widget/PanelView$tempUpdate;->this$0:Lorg/nameless/gamespace/widget/PanelView;

    const v3, 0x7f0a029f

    invoke-virtual {v1, v3}, Landroid/widget/LinearLayout;->findViewById(I)Landroid/view/View;

    move-result-object v1

    const-string v3, "findViewById(R.id.temp_info)"

    invoke-static {v1, v3}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullExpressionValue(Ljava/lang/Object;Ljava/lang/String;)V

    check-cast v1, Landroid/widget/TextView;

    .line 53
    iget-object p0, p0, Lorg/nameless/gamespace/widget/PanelView$tempUpdate;->this$0:Lorg/nameless/gamespace/widget/PanelView;

    invoke-virtual {p0}, Landroid/widget/LinearLayout;->getContext()Landroid/content/Context;

    move-result-object p0

    const/4 v3, 0x1

    new-array v3, v3, [Ljava/lang/Object;

    invoke-static {v0}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object v0

    aput-object v0, v3, v2

    const v0, 0x7f1202e7

    invoke-virtual {p0, v0, v3}, Landroid/content/Context;->getString(I[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    invoke-virtual {v1, p0}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    return-void
.end method
