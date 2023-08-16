.class public final Lorg/nameless/gamespace/gamebar/DanmakuService;
.super Ljava/lang/Object;
.source "DanmakuService.kt"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lorg/nameless/gamespace/gamebar/DanmakuService$Listener;,
        Lorg/nameless/gamespace/gamebar/DanmakuService$Companion;
    }
.end annotation

.annotation system Ldalvik/annotation/SourceDebugExtension;
    value = "SMAP\nDanmakuService.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DanmakuService.kt\norg/nameless/gamespace/gamebar/DanmakuService\n+ 2 Animator.kt\nandroidx/core/animation/AnimatorKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,299:1\n94#2,14:300\n94#2,14:314\n1#3:328\n*S KotlinDebug\n*F\n+ 1 DanmakuService.kt\norg/nameless/gamespace/gamebar/DanmakuService\n*L\n185#1:300,14\n200#1:314,14\n*E\n"
.end annotation


# static fields
.field public static final Companion:Lorg/nameless/gamespace/gamebar/DanmakuService$Companion;


# instance fields
.field private final appSettings:Lorg/nameless/gamespace/data/AppSettings;

.field private final context:Landroid/content/Context;

.field private final customResolutionSwitcher:Z

.field private final handler:Landroid/os/Handler;

.field private isPortrait:Z

.field private layoutParams:Landroid/view/WindowManager$LayoutParams;

.field private final notificationListener:Lorg/nameless/gamespace/gamebar/DanmakuService$Listener;

.field private final notificationOverlay:Landroid/widget/TextView;

.field private final notificationStack:Ljava/util/LinkedList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/LinkedList<",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation
.end field

.field private overlayAlphaAnimator:Landroid/animation/ValueAnimator;

.field private overlayPositionAnimator:Landroid/animation/ValueAnimator;

.field private verticalOffsetLandscape:I

.field private verticalOffsetPortrait:I

.field private final windowManager:Landroid/view/WindowManager;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    new-instance v0, Lorg/nameless/gamespace/gamebar/DanmakuService$Companion;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lorg/nameless/gamespace/gamebar/DanmakuService$Companion;-><init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V

    sput-object v0, Lorg/nameless/gamespace/gamebar/DanmakuService;->Companion:Lorg/nameless/gamespace/gamebar/DanmakuService$Companion;

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Lorg/nameless/gamespace/data/AppSettings;)V
    .locals 4

    const-string v0, "context"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    const-string v0, "appSettings"

    invoke-static {p2, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 57
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 59
    iput-object p1, p0, Lorg/nameless/gamespace/gamebar/DanmakuService;->context:Landroid/content/Context;

    .line 60
    iput-object p2, p0, Lorg/nameless/gamespace/gamebar/DanmakuService;->appSettings:Lorg/nameless/gamespace/data/AppSettings;

    .line 63
    new-instance p2, Landroid/widget/TextView;

    invoke-direct {p2, p1}, Landroid/widget/TextView;-><init>(Landroid/content/Context;)V

    const/16 v0, 0x11

    .line 64
    invoke-virtual {p2, v0}, Landroid/widget/TextView;->setGravity(I)V

    const/4 v0, 0x2

    .line 65
    invoke-virtual {p2, v0}, Landroid/widget/TextView;->setMaxLines(I)V

    const/4 v0, -0x1

    .line 66
    invoke-virtual {p2, v0}, Landroid/widget/TextView;->setTextColor(I)V

    const/4 v0, 0x0

    .line 67
    invoke-virtual {p2, v0}, Landroid/widget/TextView;->setFocusable(Z)V

    .line 68
    invoke-virtual {p2, v0}, Landroid/widget/TextView;->setClickable(Z)V

    .line 63
    iput-object p2, p0, Lorg/nameless/gamespace/gamebar/DanmakuService;->notificationOverlay:Landroid/widget/TextView;

    .line 72
    invoke-static {}, Lorg/nameless/wm/DisplayResolutionManager;->getCustomResolutionSwitcherType()I

    move-result p2

    const/4 v1, 0x1

    if-eqz p2, :cond_0

    move p2, v1

    goto :goto_0

    :cond_0
    move p2, v0

    :goto_0
    iput-boolean p2, p0, Lorg/nameless/gamespace/gamebar/DanmakuService;->customResolutionSwitcher:Z

    .line 74
    const-class p2, Landroid/view/WindowManager;

    invoke-virtual {p1, p2}, Landroid/content/Context;->getSystemService(Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object p2

    const-string v2, "context.getSystemService\u2026indowManager::class.java)"

    invoke-static {p2, v2}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullExpressionValue(Ljava/lang/Object;Ljava/lang/String;)V

    check-cast p2, Landroid/view/WindowManager;

    iput-object p2, p0, Lorg/nameless/gamespace/gamebar/DanmakuService;->windowManager:Landroid/view/WindowManager;

    .line 76
    new-instance p2, Landroid/os/Handler;

    invoke-static {}, Landroid/os/Looper;->getMainLooper()Landroid/os/Looper;

    move-result-object v2

    invoke-direct {p2, v2}, Landroid/os/Handler;-><init>(Landroid/os/Looper;)V

    iput-object p2, p0, Lorg/nameless/gamespace/gamebar/DanmakuService;->handler:Landroid/os/Handler;

    .line 78
    new-instance p2, Ljava/util/LinkedList;

    invoke-direct {p2}, Ljava/util/LinkedList;-><init>()V

    iput-object p2, p0, Lorg/nameless/gamespace/gamebar/DanmakuService;->notificationStack:Ljava/util/LinkedList;

    .line 80
    new-instance p2, Lorg/nameless/gamespace/gamebar/DanmakuService$Listener;

    invoke-direct {p2, p0}, Lorg/nameless/gamespace/gamebar/DanmakuService$Listener;-><init>(Lorg/nameless/gamespace/gamebar/DanmakuService;)V

    iput-object p2, p0, Lorg/nameless/gamespace/gamebar/DanmakuService;->notificationListener:Lorg/nameless/gamespace/gamebar/DanmakuService$Listener;

    .line 82
    new-instance p2, Landroid/view/WindowManager$LayoutParams;

    invoke-direct {p2}, Landroid/view/WindowManager$LayoutParams;-><init>()V

    const/4 v2, -0x2

    .line 83
    iput v2, p2, Landroid/view/WindowManager$LayoutParams;->height:I

    .line 84
    iget v2, p2, Landroid/view/WindowManager$LayoutParams;->flags:I

    or-int/lit8 v2, v2, 0x8

    or-int/lit8 v2, v2, 0x10

    const/high16 v3, 0x1000000

    or-int/2addr v2, v3

    iput v2, p2, Landroid/view/WindowManager$LayoutParams;->flags:I

    const/16 v2, 0x7df

    .line 87
    iput v2, p2, Landroid/view/WindowManager$LayoutParams;->type:I

    const/4 v2, -0x3

    .line 88
    iput v2, p2, Landroid/view/WindowManager$LayoutParams;->format:I

    const/16 v2, 0x30

    .line 89
    iput v2, p2, Landroid/view/WindowManager$LayoutParams;->gravity:I

    .line 82
    iput-object p2, p0, Lorg/nameless/gamespace/gamebar/DanmakuService;->layoutParams:Landroid/view/WindowManager$LayoutParams;

    .line 93
    invoke-virtual {p1}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object p1

    invoke-virtual {p1}, Landroid/content/res/Resources;->getConfiguration()Landroid/content/res/Configuration;

    move-result-object p1

    iget p1, p1, Landroid/content/res/Configuration;->orientation:I

    if-ne p1, v1, :cond_1

    move v0, v1

    :cond_1
    iput-boolean v0, p0, Lorg/nameless/gamespace/gamebar/DanmakuService;->isPortrait:Z

    return-void
.end method

.method public static final synthetic access$getAppSettings$p(Lorg/nameless/gamespace/gamebar/DanmakuService;)Lorg/nameless/gamespace/data/AppSettings;
    .locals 0

    .line 57
    iget-object p0, p0, Lorg/nameless/gamespace/gamebar/DanmakuService;->appSettings:Lorg/nameless/gamespace/data/AppSettings;

    return-object p0
.end method

.method public static final synthetic access$getHandler$p(Lorg/nameless/gamespace/gamebar/DanmakuService;)Landroid/os/Handler;
    .locals 0

    .line 57
    iget-object p0, p0, Lorg/nameless/gamespace/gamebar/DanmakuService;->handler:Landroid/os/Handler;

    return-object p0
.end method

.method public static final synthetic access$getNotificationOverlay$p(Lorg/nameless/gamespace/gamebar/DanmakuService;)Landroid/widget/TextView;
    .locals 0

    .line 57
    iget-object p0, p0, Lorg/nameless/gamespace/gamebar/DanmakuService;->notificationOverlay:Landroid/widget/TextView;

    return-object p0
.end method

.method public static final synthetic access$getNotificationStack$p(Lorg/nameless/gamespace/gamebar/DanmakuService;)Ljava/util/LinkedList;
    .locals 0

    .line 57
    iget-object p0, p0, Lorg/nameless/gamespace/gamebar/DanmakuService;->notificationStack:Ljava/util/LinkedList;

    return-object p0
.end method

.method public static final synthetic access$popNotification(Lorg/nameless/gamespace/gamebar/DanmakuService;)V
    .locals 0

    .line 57
    invoke-direct {p0}, Lorg/nameless/gamespace/gamebar/DanmakuService;->popNotification()V

    return-void
.end method

.method public static final synthetic access$pushNotification(Lorg/nameless/gamespace/gamebar/DanmakuService;)V
    .locals 0

    .line 57
    invoke-direct {p0}, Lorg/nameless/gamespace/gamebar/DanmakuService;->pushNotification()V

    return-void
.end method

.method public static final synthetic access$removeViewSafely(Lorg/nameless/gamespace/gamebar/DanmakuService;)V
    .locals 0

    .line 57
    invoke-direct {p0}, Lorg/nameless/gamespace/gamebar/DanmakuService;->removeViewSafely()V

    return-void
.end method

.method public static final synthetic access$showNotificationAsOverlay(Lorg/nameless/gamespace/gamebar/DanmakuService;Ljava/lang/String;)V
    .locals 0

    .line 57
    invoke-direct {p0, p1}, Lorg/nameless/gamespace/gamebar/DanmakuService;->showNotificationAsOverlay(Ljava/lang/String;)V

    return-void
.end method

.method public static final synthetic access$updateViewLayoutSafely(Lorg/nameless/gamespace/gamebar/DanmakuService;Landroid/view/WindowManager$LayoutParams;)V
    .locals 0

    .line 57
    invoke-direct {p0, p1}, Lorg/nameless/gamespace/gamebar/DanmakuService;->updateViewLayoutSafely(Landroid/view/WindowManager$LayoutParams;)V

    return-void
.end method

.method private final getOffsetForPosition()I
    .locals 1

    .line 158
    iget-boolean v0, p0, Lorg/nameless/gamespace/gamebar/DanmakuService;->isPortrait:Z

    if-eqz v0, :cond_0

    iget p0, p0, Lorg/nameless/gamespace/gamebar/DanmakuService;->verticalOffsetPortrait:I

    goto :goto_0

    :cond_0
    iget p0, p0, Lorg/nameless/gamespace/gamebar/DanmakuService;->verticalOffsetLandscape:I

    :goto_0
    return p0
.end method

.method private final varargs getPositionAnimator(J[F)Landroid/animation/ValueAnimator;
    .locals 2

    .line 215
    new-instance v0, Landroid/view/WindowManager$LayoutParams;

    invoke-direct {v0}, Landroid/view/WindowManager$LayoutParams;-><init>()V

    iget-object v1, p0, Lorg/nameless/gamespace/gamebar/DanmakuService;->layoutParams:Landroid/view/WindowManager$LayoutParams;

    invoke-virtual {v0, v1}, Landroid/view/WindowManager$LayoutParams;->copyFrom(Landroid/view/WindowManager$LayoutParams;)I

    .line 216
    array-length v1, p3

    invoke-static {p3, v1}, Ljava/util/Arrays;->copyOf([FI)[F

    move-result-object p3

    invoke-static {p3}, Landroid/animation/ValueAnimator;->ofFloat([F)Landroid/animation/ValueAnimator;

    move-result-object p3

    .line 217
    invoke-virtual {p3, p1, p2}, Landroid/animation/ValueAnimator;->setDuration(J)Landroid/animation/ValueAnimator;

    .line 218
    new-instance p1, Lorg/nameless/gamespace/gamebar/DanmakuService$getPositionAnimator$1$1;

    invoke-direct {p1, v0, p0}, Lorg/nameless/gamespace/gamebar/DanmakuService$getPositionAnimator$1$1;-><init>(Landroid/view/WindowManager$LayoutParams;Lorg/nameless/gamespace/gamebar/DanmakuService;)V

    invoke-virtual {p3, p1}, Landroid/animation/ValueAnimator;->addUpdateListener(Landroid/animation/ValueAnimator$AnimatorUpdateListener;)V

    const-string p0, "ofFloat(*values).apply {\u2026)\n            }\n        }"

    .line 216
    invoke-static {p3, p0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullExpressionValue(Ljava/lang/Object;Ljava/lang/String;)V

    return-object p3
.end method

.method private final getScale()F
    .locals 3

    .line 162
    iget-boolean v0, p0, Lorg/nameless/gamespace/gamebar/DanmakuService;->customResolutionSwitcher:Z

    const/high16 v1, 0x3f800000    # 1.0f

    if-nez v0, :cond_0

    return v1

    .line 165
    :cond_0
    iget-object p0, p0, Lorg/nameless/gamespace/gamebar/DanmakuService;->context:Landroid/content/Context;

    invoke-virtual {p0}, Landroid/content/Context;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object p0

    const-string v0, "display_resolution_width"

    const/16 v2, 0x438

    invoke-static {p0, v0, v2}, Landroid/provider/Settings$System;->getInt(Landroid/content/ContentResolver;Ljava/lang/String;I)I

    move-result p0

    if-ne p0, v2, :cond_1

    const/high16 v1, 0x3f400000    # 0.75f

    :cond_1
    return v1
.end method

.method private final popNotification()V
    .locals 5

    .line 196
    invoke-direct {p0}, Lorg/nameless/gamespace/gamebar/DanmakuService;->getOffsetForPosition()I

    move-result v0

    int-to-float v0, v0

    const/high16 v1, 0x3fc00000    # 1.5f

    mul-float/2addr v1, v0

    const/4 v2, 0x2

    new-array v3, v2, [F

    const/4 v4, 0x0

    aput v0, v3, v4

    const/4 v0, 0x1

    aput v1, v3, v0

    const-wide/16 v0, 0x12c

    .line 199
    invoke-direct {p0, v0, v1, v3}, Lorg/nameless/gamespace/gamebar/DanmakuService;->getPositionAnimator(J[F)Landroid/animation/ValueAnimator;

    move-result-object v3

    .line 100
    new-instance v4, Lorg/nameless/gamespace/gamebar/DanmakuService$popNotification$lambda$6$$inlined$addListener$default$1;

    invoke-direct {v4, p0}, Lorg/nameless/gamespace/gamebar/DanmakuService$popNotification$lambda$6$$inlined$addListener$default$1;-><init>(Lorg/nameless/gamespace/gamebar/DanmakuService;)V

    .line 106
    invoke-virtual {v3, v4}, Landroid/animation/Animator;->addListener(Landroid/animation/Animator$AnimatorListener;)V

    .line 209
    invoke-virtual {v3}, Landroid/animation/ValueAnimator;->start()V

    .line 198
    iput-object v3, p0, Lorg/nameless/gamespace/gamebar/DanmakuService;->overlayPositionAnimator:Landroid/animation/ValueAnimator;

    new-array v2, v2, [F

    .line 211
    fill-array-data v2, :array_0

    invoke-direct {p0, v0, v1, v2}, Lorg/nameless/gamespace/gamebar/DanmakuService;->startAlphaAnimation(J[F)V

    return-void

    nop

    :array_0
    .array-data 4
        0x3f800000    # 1.0f
        0x0
    .end array-data
.end method

.method private final pushNotification()V
    .locals 5

    .line 182
    invoke-direct {p0}, Lorg/nameless/gamespace/gamebar/DanmakuService;->getOffsetForPosition()I

    move-result v0

    int-to-float v0, v0

    const/high16 v1, 0x3f000000    # 0.5f

    mul-float/2addr v1, v0

    const/4 v2, 0x2

    new-array v3, v2, [F

    const/4 v4, 0x0

    aput v1, v3, v4

    const/4 v1, 0x1

    aput v0, v3, v1

    const-wide/16 v0, 0x1f4

    .line 184
    invoke-direct {p0, v0, v1, v3}, Lorg/nameless/gamespace/gamebar/DanmakuService;->getPositionAnimator(J[F)Landroid/animation/ValueAnimator;

    move-result-object v3

    .line 100
    new-instance v4, Lorg/nameless/gamespace/gamebar/DanmakuService$pushNotification$lambda$4$$inlined$addListener$default$1;

    invoke-direct {v4, p0}, Lorg/nameless/gamespace/gamebar/DanmakuService$pushNotification$lambda$4$$inlined$addListener$default$1;-><init>(Lorg/nameless/gamespace/gamebar/DanmakuService;)V

    .line 106
    invoke-virtual {v3, v4}, Landroid/animation/Animator;->addListener(Landroid/animation/Animator$AnimatorListener;)V

    .line 190
    invoke-virtual {v3}, Landroid/animation/ValueAnimator;->start()V

    .line 184
    iput-object v3, p0, Lorg/nameless/gamespace/gamebar/DanmakuService;->overlayPositionAnimator:Landroid/animation/ValueAnimator;

    new-array v2, v2, [F

    .line 192
    fill-array-data v2, :array_0

    invoke-direct {p0, v0, v1, v2}, Lorg/nameless/gamespace/gamebar/DanmakuService;->startAlphaAnimation(J[F)V

    return-void

    nop

    :array_0
    .array-data 4
        0x0
        0x3f800000    # 1.0f
    .end array-data
.end method

.method private final registerListener()V
    .locals 3

    .line 122
    new-instance v0, Landroid/content/ComponentName;

    iget-object v1, p0, Lorg/nameless/gamespace/gamebar/DanmakuService;->context:Landroid/content/Context;

    const-class v2, Lorg/nameless/gamespace/gamebar/DanmakuService;

    invoke-direct {v0, v1, v2}, Landroid/content/ComponentName;-><init>(Landroid/content/Context;Ljava/lang/Class;)V

    .line 124
    :try_start_0
    iget-object v1, p0, Lorg/nameless/gamespace/gamebar/DanmakuService;->notificationListener:Lorg/nameless/gamespace/gamebar/DanmakuService$Listener;

    .line 125
    iget-object p0, p0, Lorg/nameless/gamespace/gamebar/DanmakuService;->context:Landroid/content/Context;

    const/4 v2, -0x2

    .line 124
    invoke-virtual {v1, p0, v0, v2}, Landroid/service/notification/NotificationListenerService;->registerAsSystemService(Landroid/content/Context;Landroid/content/ComponentName;I)V
    :try_end_0
    .catch Landroid/os/RemoteException; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    const-string p0, "DanmakuService"

    const-string v0, "RemoteException while registering danmaku service"

    .line 130
    invoke-static {p0, v0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    :goto_0
    return-void
.end method

.method private final removeViewSafely()V
    .locals 1

    .line 240
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/DanmakuService;->notificationOverlay:Landroid/widget/TextView;

    invoke-virtual {v0}, Landroid/widget/TextView;->getParent()Landroid/view/ViewParent;

    move-result-object v0

    if-eqz v0, :cond_0

    .line 241
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/DanmakuService;->windowManager:Landroid/view/WindowManager;

    iget-object p0, p0, Lorg/nameless/gamespace/gamebar/DanmakuService;->notificationOverlay:Landroid/widget/TextView;

    invoke-interface {v0, p0}, Landroid/view/WindowManager;->removeViewImmediate(Landroid/view/View;)V

    :cond_0
    return-void
.end method

.method private final showNotificationAsOverlay(Ljava/lang/String;)V
    .locals 2

    .line 171
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/DanmakuService;->notificationOverlay:Landroid/widget/TextView;

    invoke-virtual {v0}, Landroid/widget/TextView;->getParent()Landroid/view/ViewParent;

    move-result-object v0

    if-nez v0, :cond_0

    .line 172
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/DanmakuService;->notificationOverlay:Landroid/widget/TextView;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setAlpha(F)V

    .line 173
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/DanmakuService;->notificationOverlay:Landroid/widget/TextView;

    invoke-virtual {v0, p1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 174
    iget-object p1, p0, Lorg/nameless/gamespace/gamebar/DanmakuService;->windowManager:Landroid/view/WindowManager;

    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/DanmakuService;->notificationOverlay:Landroid/widget/TextView;

    iget-object v1, p0, Lorg/nameless/gamespace/gamebar/DanmakuService;->layoutParams:Landroid/view/WindowManager$LayoutParams;

    invoke-interface {p1, v0, v1}, Landroid/view/WindowManager;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 175
    invoke-direct {p0}, Lorg/nameless/gamespace/gamebar/DanmakuService;->pushNotification()V

    goto :goto_0

    .line 177
    :cond_0
    iget-object p0, p0, Lorg/nameless/gamespace/gamebar/DanmakuService;->notificationStack:Ljava/util/LinkedList;

    invoke-virtual {p0, p1}, Ljava/util/LinkedList;->add(Ljava/lang/Object;)Z

    :goto_0
    return-void
.end method

.method private final varargs startAlphaAnimation(J[F)V
    .locals 1

    .line 226
    array-length v0, p3

    invoke-static {p3, v0}, Ljava/util/Arrays;->copyOf([FI)[F

    move-result-object p3

    invoke-static {p3}, Landroid/animation/ValueAnimator;->ofFloat([F)Landroid/animation/ValueAnimator;

    move-result-object p3

    .line 227
    invoke-virtual {p3, p1, p2}, Landroid/animation/ValueAnimator;->setDuration(J)Landroid/animation/ValueAnimator;

    .line 228
    new-instance p1, Lorg/nameless/gamespace/gamebar/DanmakuService$startAlphaAnimation$1$1;

    invoke-direct {p1, p0}, Lorg/nameless/gamespace/gamebar/DanmakuService$startAlphaAnimation$1$1;-><init>(Lorg/nameless/gamespace/gamebar/DanmakuService;)V

    invoke-virtual {p3, p1}, Landroid/animation/ValueAnimator;->addUpdateListener(Landroid/animation/ValueAnimator$AnimatorUpdateListener;)V

    .line 231
    invoke-virtual {p3}, Landroid/animation/ValueAnimator;->start()V

    .line 226
    iput-object p3, p0, Lorg/nameless/gamespace/gamebar/DanmakuService;->overlayAlphaAnimator:Landroid/animation/ValueAnimator;

    return-void
.end method

.method private final unregisterListener()V
    .locals 1

    .line 136
    :try_start_0
    iget-object p0, p0, Lorg/nameless/gamespace/gamebar/DanmakuService;->notificationListener:Lorg/nameless/gamespace/gamebar/DanmakuService$Listener;

    invoke-virtual {p0}, Landroid/service/notification/NotificationListenerService;->unregisterAsSystemService()V
    :try_end_0
    .catch Landroid/os/RemoteException; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    const-string p0, "DanmakuService"

    const-string v0, "RemoteException while registering danmaku service"

    .line 138
    invoke-static {p0, v0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    :goto_0
    return-void
.end method

.method private final updateParams()V
    .locals 2

    .line 143
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/DanmakuService;->context:Landroid/content/Context;

    invoke-virtual {v0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    const v1, 0x7f070299

    .line 145
    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v1

    .line 144
    iput v1, p0, Lorg/nameless/gamespace/gamebar/DanmakuService;->verticalOffsetLandscape:I

    const v1, 0x7f07029a

    .line 147
    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v0

    .line 146
    iput v0, p0, Lorg/nameless/gamespace/gamebar/DanmakuService;->verticalOffsetPortrait:I

    .line 149
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/DanmakuService;->layoutParams:Landroid/view/WindowManager$LayoutParams;

    invoke-direct {p0}, Lorg/nameless/gamespace/gamebar/DanmakuService;->getOffsetForPosition()I

    move-result v1

    iput v1, v0, Landroid/view/WindowManager$LayoutParams;->y:I

    .line 150
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/DanmakuService;->layoutParams:Landroid/view/WindowManager$LayoutParams;

    iget-object v1, p0, Lorg/nameless/gamespace/gamebar/DanmakuService;->windowManager:Landroid/view/WindowManager;

    invoke-interface {v1}, Landroid/view/WindowManager;->getCurrentWindowMetrics()Landroid/view/WindowMetrics;

    move-result-object v1

    invoke-virtual {v1}, Landroid/view/WindowMetrics;->getBounds()Landroid/graphics/Rect;

    move-result-object v1

    invoke-virtual {v1}, Landroid/graphics/Rect;->width()I

    move-result v1

    mul-int/lit8 v1, v1, 0x4b

    div-int/lit8 v1, v1, 0x64

    iput v1, v0, Landroid/view/WindowManager$LayoutParams;->width:I

    .line 151
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/DanmakuService;->notificationOverlay:Landroid/widget/TextView;

    const/16 v1, 0x46

    int-to-float v1, v1

    .line 153
    invoke-direct {p0}, Lorg/nameless/gamespace/gamebar/DanmakuService;->getScale()F

    move-result p0

    mul-float/2addr v1, p0

    const/4 p0, 0x0

    .line 151
    invoke-virtual {v0, p0, v1}, Landroid/widget/TextView;->setTextSize(IF)V

    return-void
.end method

.method private final updateViewLayoutSafely(Landroid/view/WindowManager$LayoutParams;)V
    .locals 1

    .line 235
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/DanmakuService;->notificationOverlay:Landroid/widget/TextView;

    invoke-virtual {v0}, Landroid/widget/TextView;->getParent()Landroid/view/ViewParent;

    move-result-object v0

    if-eqz v0, :cond_0

    .line 236
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/DanmakuService;->windowManager:Landroid/view/WindowManager;

    iget-object p0, p0, Lorg/nameless/gamespace/gamebar/DanmakuService;->notificationOverlay:Landroid/widget/TextView;

    invoke-interface {v0, p0, p1}, Landroid/view/WindowManager;->updateViewLayout(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    :cond_0
    return-void
.end method


# virtual methods
.method public final destroy()V
    .locals 1

    .line 115
    invoke-direct {p0}, Lorg/nameless/gamespace/gamebar/DanmakuService;->unregisterListener()V

    .line 116
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/DanmakuService;->overlayAlphaAnimator:Landroid/animation/ValueAnimator;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Landroid/animation/ValueAnimator;->cancel()V

    .line 117
    :cond_0
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/DanmakuService;->overlayPositionAnimator:Landroid/animation/ValueAnimator;

    if-eqz v0, :cond_1

    invoke-virtual {v0}, Landroid/animation/ValueAnimator;->cancel()V

    .line 118
    :cond_1
    invoke-direct {p0}, Lorg/nameless/gamespace/gamebar/DanmakuService;->removeViewSafely()V

    return-void
.end method

.method public final init()V
    .locals 0

    .line 102
    invoke-direct {p0}, Lorg/nameless/gamespace/gamebar/DanmakuService;->updateParams()V

    .line 103
    invoke-direct {p0}, Lorg/nameless/gamespace/gamebar/DanmakuService;->registerListener()V

    return-void
.end method

.method public final updateConfiguration(Landroid/content/res/Configuration;)V
    .locals 1

    const-string v0, "newConfig"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 107
    iget p1, p1, Landroid/content/res/Configuration;->orientation:I

    const/4 v0, 0x1

    if-ne p1, v0, :cond_0

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    iput-boolean v0, p0, Lorg/nameless/gamespace/gamebar/DanmakuService;->isPortrait:Z

    .line 108
    iget-object p1, p0, Lorg/nameless/gamespace/gamebar/DanmakuService;->overlayAlphaAnimator:Landroid/animation/ValueAnimator;

    if-eqz p1, :cond_1

    invoke-virtual {p1}, Landroid/animation/ValueAnimator;->end()V

    .line 109
    :cond_1
    iget-object p1, p0, Lorg/nameless/gamespace/gamebar/DanmakuService;->overlayPositionAnimator:Landroid/animation/ValueAnimator;

    if-eqz p1, :cond_2

    invoke-virtual {p1}, Landroid/animation/ValueAnimator;->end()V

    .line 110
    :cond_2
    invoke-direct {p0}, Lorg/nameless/gamespace/gamebar/DanmakuService;->updateParams()V

    .line 111
    iget-object p1, p0, Lorg/nameless/gamespace/gamebar/DanmakuService;->layoutParams:Landroid/view/WindowManager$LayoutParams;

    invoke-direct {p0, p1}, Lorg/nameless/gamespace/gamebar/DanmakuService;->updateViewLayoutSafely(Landroid/view/WindowManager$LayoutParams;)V

    return-void
.end method
