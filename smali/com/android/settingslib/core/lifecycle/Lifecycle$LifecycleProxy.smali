.class Lcom/android/settingslib/core/lifecycle/Lifecycle$LifecycleProxy;
.super Ljava/lang/Object;
.source "Lifecycle.java"

# interfaces
.implements Landroidx/lifecycle/LifecycleObserver;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/android/settingslib/core/lifecycle/Lifecycle;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x2
    name = "LifecycleProxy"
.end annotation


# virtual methods
.method public onLifecycleEvent(Landroidx/lifecycle/LifecycleOwner;Landroidx/lifecycle/Lifecycle$Event;)V
    .locals 0
    .annotation runtime Landroidx/lifecycle/OnLifecycleEvent;
        value = .enum Landroidx/lifecycle/Lifecycle$Event;->ON_ANY:Landroidx/lifecycle/Lifecycle$Event;
    .end annotation

    .line 217
    sget-object p0, Lcom/android/settingslib/core/lifecycle/Lifecycle$1;->$SwitchMap$androidx$lifecycle$Lifecycle$Event:[I

    invoke-virtual {p2}, Ljava/lang/Enum;->ordinal()I

    move-result p1

    aget p0, p0, p1

    const/4 p1, 0x0

    packed-switch p0, :pswitch_data_0

    goto :goto_0

    :pswitch_0
    const-string p0, "LifecycleObserver"

    const-string p1, "Should not receive an \'ANY\' event!"

    .line 237
    invoke-static {p0, p1}, Landroid/util/Log;->wtf(Ljava/lang/String;Ljava/lang/String;)I

    goto :goto_0

    .line 234
    :pswitch_1
    invoke-static {p1}, Lcom/android/settingslib/core/lifecycle/Lifecycle;->-$$Nest$monDestroy(Lcom/android/settingslib/core/lifecycle/Lifecycle;)V

    goto :goto_0

    .line 231
    :pswitch_2
    invoke-static {p1}, Lcom/android/settingslib/core/lifecycle/Lifecycle;->-$$Nest$monStop(Lcom/android/settingslib/core/lifecycle/Lifecycle;)V

    goto :goto_0

    .line 228
    :pswitch_3
    invoke-static {p1}, Lcom/android/settingslib/core/lifecycle/Lifecycle;->-$$Nest$monPause(Lcom/android/settingslib/core/lifecycle/Lifecycle;)V

    goto :goto_0

    .line 225
    :pswitch_4
    invoke-static {p1}, Lcom/android/settingslib/core/lifecycle/Lifecycle;->-$$Nest$monResume(Lcom/android/settingslib/core/lifecycle/Lifecycle;)V

    goto :goto_0

    .line 222
    :pswitch_5
    invoke-static {p1}, Lcom/android/settingslib/core/lifecycle/Lifecycle;->-$$Nest$monStart(Lcom/android/settingslib/core/lifecycle/Lifecycle;)V

    :goto_0
    return-void

    nop

    :pswitch_data_0
    .packed-switch 0x2
        :pswitch_5
        :pswitch_4
        :pswitch_3
        :pswitch_2
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method
