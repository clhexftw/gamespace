.class public final Lorg/nameless/gamespace/widget/QuickLaunchAppView;
.super Landroid/widget/LinearLayout;
.source "QuickLaunchAppView.kt"

# interfaces
.implements Landroid/view/View$OnClickListener;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lorg/nameless/gamespace/widget/QuickLaunchAppView$Companion;
    }
.end annotation

.annotation system Ldalvik/annotation/SourceDebugExtension;
    value = "SMAP\nQuickLaunchAppView.kt\nKotlin\n*S Kotlin\n*F\n+ 1 QuickLaunchAppView.kt\norg/nameless/gamespace/widget/QuickLaunchAppView\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,136:1\n1851#2,2:137\n*S KotlinDebug\n*F\n+ 1 QuickLaunchAppView.kt\norg/nameless/gamespace/widget/QuickLaunchAppView\n*L\n62#1:137,2\n*E\n"
.end annotation


# static fields
.field public static final Companion:Lorg/nameless/gamespace/widget/QuickLaunchAppView$Companion;


# instance fields
.field private final activityManager:Landroid/app/ActivityManager;

.field private final activityOptions:Landroid/app/ActivityOptions;

.field private final activityTaskManager:Landroid/app/IActivityTaskManager;

.field private final packageManager:Landroid/content/pm/PackageManager;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    new-instance v0, Lorg/nameless/gamespace/widget/QuickLaunchAppView$Companion;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lorg/nameless/gamespace/widget/QuickLaunchAppView$Companion;-><init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V

    sput-object v0, Lorg/nameless/gamespace/widget/QuickLaunchAppView;->Companion:Lorg/nameless/gamespace/widget/QuickLaunchAppView$Companion;

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;)V
    .locals 8

    const-string v0, "context"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    const/4 v3, 0x0

    const/4 v4, 0x0

    const/4 v5, 0x0

    const/16 v6, 0xe

    const/4 v7, 0x0

    move-object v1, p0

    move-object v2, p1

    invoke-direct/range {v1 .. v7}, Lorg/nameless/gamespace/widget/QuickLaunchAppView;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;IIILkotlin/jvm/internal/DefaultConstructorMarker;)V

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 8

    const-string v0, "context"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    const/4 v4, 0x0

    const/4 v5, 0x0

    const/16 v6, 0xc

    const/4 v7, 0x0

    move-object v1, p0

    move-object v2, p1

    move-object v3, p2

    invoke-direct/range {v1 .. v7}, Lorg/nameless/gamespace/widget/QuickLaunchAppView;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;IIILkotlin/jvm/internal/DefaultConstructorMarker;)V

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V
    .locals 8

    const-string v0, "context"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    const/4 v5, 0x0

    const/16 v6, 0x8

    const/4 v7, 0x0

    move-object v1, p0

    move-object v2, p1

    move-object v3, p2

    move v4, p3

    invoke-direct/range {v1 .. v7}, Lorg/nameless/gamespace/widget/QuickLaunchAppView;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;IIILkotlin/jvm/internal/DefaultConstructorMarker;)V

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;II)V
    .locals 1

    const-string v0, "context"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 44
    invoke-direct {p0, p1, p2, p3, p4}, Landroid/widget/LinearLayout;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;II)V

    const-string p2, "activity"

    .line 47
    invoke-virtual {p1, p2}, Landroid/content/Context;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object p2

    const-string p3, "null cannot be cast to non-null type android.app.ActivityManager"

    invoke-static {p2, p3}, Lkotlin/jvm/internal/Intrinsics;->checkNotNull(Ljava/lang/Object;Ljava/lang/String;)V

    check-cast p2, Landroid/app/ActivityManager;

    iput-object p2, p0, Lorg/nameless/gamespace/widget/QuickLaunchAppView;->activityManager:Landroid/app/ActivityManager;

    .line 48
    invoke-static {}, Landroid/app/ActivityTaskManager;->getService()Landroid/app/IActivityTaskManager;

    move-result-object p2

    iput-object p2, p0, Lorg/nameless/gamespace/widget/QuickLaunchAppView;->activityTaskManager:Landroid/app/IActivityTaskManager;

    .line 49
    invoke-static {}, Landroid/app/ActivityOptions;->makeBasic()Landroid/app/ActivityOptions;

    move-result-object p2

    iput-object p2, p0, Lorg/nameless/gamespace/widget/QuickLaunchAppView;->activityOptions:Landroid/app/ActivityOptions;

    .line 50
    invoke-virtual {p1}, Landroid/content/Context;->getPackageManager()Landroid/content/pm/PackageManager;

    move-result-object p1

    iput-object p1, p0, Lorg/nameless/gamespace/widget/QuickLaunchAppView;->packageManager:Landroid/content/pm/PackageManager;

    .line 53
    invoke-direct {p0}, Lorg/nameless/gamespace/widget/QuickLaunchAppView;->updateAppList()V

    .line 54
    invoke-direct {p0}, Lorg/nameless/gamespace/widget/QuickLaunchAppView;->ensureFreeformEnabled()V

    return-void
.end method

.method public synthetic constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;IIILkotlin/jvm/internal/DefaultConstructorMarker;)V
    .locals 1

    and-int/lit8 p6, p5, 0x2

    if-eqz p6, :cond_0

    const/4 p2, 0x0

    :cond_0
    and-int/lit8 p6, p5, 0x4

    const/4 v0, 0x0

    if-eqz p6, :cond_1

    move p3, v0

    :cond_1
    and-int/lit8 p5, p5, 0x8

    if-eqz p5, :cond_2

    move p4, v0

    .line 41
    :cond_2
    invoke-direct {p0, p1, p2, p3, p4}, Lorg/nameless/gamespace/widget/QuickLaunchAppView;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;II)V

    return-void
.end method

.method private final ensureFreeformEnabled()V
    .locals 3

    .line 74
    invoke-virtual {p0}, Landroid/widget/LinearLayout;->getContext()Landroid/content/Context;

    move-result-object v0

    invoke-virtual {v0}, Landroid/content/Context;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object v0

    const-string v1, "force_resizable_activities"

    const/4 v2, 0x1

    invoke-static {v0, v1, v2}, Landroid/provider/Settings$Global;->putInt(Landroid/content/ContentResolver;Ljava/lang/String;I)Z

    .line 76
    invoke-virtual {p0}, Landroid/widget/LinearLayout;->getContext()Landroid/content/Context;

    move-result-object p0

    invoke-virtual {p0}, Landroid/content/Context;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object p0

    const-string v0, "enable_non_resizable_multi_window"

    invoke-static {p0, v0, v2}, Landroid/provider/Settings$Global;->putInt(Landroid/content/ContentResolver;Ljava/lang/String;I)Z

    return-void
.end method

.method private final isPortrait()Z
    .locals 1

    .line 81
    invoke-virtual {p0}, Landroid/widget/LinearLayout;->getContext()Landroid/content/Context;

    move-result-object p0

    invoke-virtual {p0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object p0

    invoke-virtual {p0}, Landroid/content/res/Resources;->getConfiguration()Landroid/content/res/Configuration;

    move-result-object p0

    iget p0, p0, Landroid/content/res/Configuration;->orientation:I

    const/4 v0, 0x1

    if-ne p0, v0, :cond_0

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    return v0
.end method

.method private final startActivity(Ljava/lang/String;)V
    .locals 6

    .line 85
    invoke-virtual {p0}, Landroid/widget/LinearLayout;->getContext()Landroid/content/Context;

    move-result-object v0

    invoke-virtual {v0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    invoke-virtual {v0}, Landroid/content/res/Resources;->getDisplayMetrics()Landroid/util/DisplayMetrics;

    move-result-object v0

    .line 86
    invoke-direct {p0}, Lorg/nameless/gamespace/widget/QuickLaunchAppView;->isPortrait()Z

    move-result v1

    const/16 v2, 0x190

    const/16 v3, 0xc8

    if-eqz v1, :cond_0

    .line 89
    iget v0, v0, Landroid/util/DisplayMetrics;->widthPixels:I

    sub-int/2addr v0, v2

    mul-int/lit8 v4, v0, 0x4

    .line 90
    div-int/lit8 v4, v4, 0x3

    goto :goto_0

    .line 92
    :cond_0
    iget v0, v0, Landroid/util/DisplayMetrics;->heightPixels:I

    add-int/lit16 v4, v0, -0xc8

    mul-int/lit8 v0, v4, 0x3

    .line 93
    div-int/lit8 v0, v0, 0x4

    :goto_0
    add-int/2addr v0, v3

    if-eqz v1, :cond_1

    goto :goto_1

    :cond_1
    const/16 v2, 0x32

    :goto_1
    add-int/2addr v4, v2

    .line 99
    iget-object v1, p0, Lorg/nameless/gamespace/widget/QuickLaunchAppView;->activityOptions:Landroid/app/ActivityOptions;

    const/4 v5, 0x5

    invoke-virtual {v1, v5}, Landroid/app/ActivityOptions;->setLaunchWindowingMode(I)V

    .line 100
    iget-object v1, p0, Lorg/nameless/gamespace/widget/QuickLaunchAppView;->activityOptions:Landroid/app/ActivityOptions;

    new-instance v5, Landroid/graphics/Rect;

    invoke-direct {v5, v3, v2, v0, v4}, Landroid/graphics/Rect;-><init>(IIII)V

    invoke-virtual {v1, v5}, Landroid/app/ActivityOptions;->setLaunchBounds(Landroid/graphics/Rect;)Landroid/app/ActivityOptions;

    .line 104
    :try_start_0
    iget-object v0, p0, Lorg/nameless/gamespace/widget/QuickLaunchAppView;->activityManager:Landroid/app/ActivityManager;

    const/16 v1, 0x64

    const/4 v2, 0x2

    invoke-virtual {v0, v1, v2}, Landroid/app/ActivityManager;->getRecentTasks(II)Ljava/util/List;

    move-result-object v0

    const-string v1, "activityManager.getRecen\u2026ECENT_IGNORE_UNAVAILABLE)"

    invoke-static {v0, v1}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullExpressionValue(Ljava/lang/Object;Ljava/lang/String;)V

    .line 105
    invoke-interface {v0}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :cond_2
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_3

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Landroid/app/ActivityManager$RecentTaskInfo;

    .line 106
    iget-boolean v2, v1, Landroid/app/ActivityManager$RecentTaskInfo;->isRunning:Z

    if-eqz v2, :cond_2

    iget-object v2, v1, Landroid/app/ActivityManager$RecentTaskInfo;->topActivity:Landroid/content/ComponentName;

    if-eqz v2, :cond_2

    invoke-virtual {v2}, Landroid/content/ComponentName;->getPackageName()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {p1, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_2

    .line 107
    iget-object v0, p0, Lorg/nameless/gamespace/widget/QuickLaunchAppView;->activityTaskManager:Landroid/app/IActivityTaskManager;

    iget v1, v1, Landroid/app/ActivityManager$RecentTaskInfo;->taskId:I

    iget-object v2, p0, Lorg/nameless/gamespace/widget/QuickLaunchAppView;->activityOptions:Landroid/app/ActivityOptions;

    invoke-virtual {v2}, Landroid/app/ActivityOptions;->toBundle()Landroid/os/Bundle;

    move-result-object v2

    invoke-interface {v0, v1, v2}, Landroid/app/IActivityTaskManager;->startActivityFromRecents(ILandroid/os/Bundle;)I
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return-void

    :catch_0
    move-exception v0

    .line 112
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "Failed to start "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    const-string v2, "QuickLaunchAppView"

    invoke-static {v2, v1, v0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    .line 115
    :cond_3
    iget-object v0, p0, Lorg/nameless/gamespace/widget/QuickLaunchAppView;->packageManager:Landroid/content/pm/PackageManager;

    invoke-virtual {v0, p1}, Landroid/content/pm/PackageManager;->getLaunchIntentForPackage(Ljava/lang/String;)Landroid/content/Intent;

    move-result-object p1

    const/high16 v0, 0x10200000

    .line 116
    invoke-virtual {p1, v0}, Landroid/content/Intent;->addFlags(I)Landroid/content/Intent;

    const/4 v0, 0x0

    .line 119
    invoke-virtual {p1, v0}, Landroid/content/Intent;->setPackage(Ljava/lang/String;)Landroid/content/Intent;

    .line 120
    invoke-virtual {p0}, Landroid/widget/LinearLayout;->getContext()Landroid/content/Context;

    move-result-object v0

    iget-object p0, p0, Lorg/nameless/gamespace/widget/QuickLaunchAppView;->activityOptions:Landroid/app/ActivityOptions;

    invoke-virtual {p0}, Landroid/app/ActivityOptions;->toBundle()Landroid/os/Bundle;

    move-result-object p0

    sget-object v1, Landroid/os/UserHandle;->CURRENT:Landroid/os/UserHandle;

    invoke-virtual {v0, p1, p0, v1}, Landroid/content/Context;->startActivityAsUser(Landroid/content/Intent;Landroid/os/Bundle;Landroid/os/UserHandle;)V

    return-void
.end method

.method private final updateAppList()V
    .locals 10

    .line 58
    invoke-virtual {p0}, Landroid/widget/LinearLayout;->removeAllViewsInLayout()V

    .line 60
    sget-object v0, Lorg/nameless/gamespace/widget/QuickLaunchAppViewCache;->INSTANCE:Lorg/nameless/gamespace/widget/QuickLaunchAppViewCache;

    invoke-virtual {v0}, Lorg/nameless/gamespace/widget/QuickLaunchAppViewCache;->getAppCount()I

    move-result v1

    if-eqz v1, :cond_0

    const/4 v1, 0x0

    .line 61
    invoke-virtual {p0, v1}, Landroid/widget/LinearLayout;->setVisibility(I)V

    .line 62
    invoke-virtual {v0}, Lorg/nameless/gamespace/widget/QuickLaunchAppViewCache;->getAppSet()Ljava/util/Set;

    move-result-object v0

    check-cast v0, Ljava/lang/Iterable;

    .line 1851
    invoke-interface {v0}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :goto_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_1

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    move-object v4, v1

    check-cast v4, Ljava/lang/String;

    .line 63
    new-instance v1, Lorg/nameless/gamespace/widget/tiles/AppTile;

    invoke-virtual {p0}, Landroid/widget/LinearLayout;->getContext()Landroid/content/Context;

    move-result-object v3

    const-string v2, "context"

    invoke-static {v3, v2}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullExpressionValue(Ljava/lang/Object;Ljava/lang/String;)V

    const/4 v5, 0x0

    const/4 v6, 0x0

    const/4 v7, 0x0

    const/16 v8, 0x1c

    const/4 v9, 0x0

    move-object v2, v1

    invoke-direct/range {v2 .. v9}, Lorg/nameless/gamespace/widget/tiles/AppTile;-><init>(Landroid/content/Context;Ljava/lang/String;Landroid/util/AttributeSet;IIILkotlin/jvm/internal/DefaultConstructorMarker;)V

    .line 64
    invoke-virtual {v1, p0}, Landroid/widget/LinearLayout;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 65
    invoke-virtual {p0, v1}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    goto :goto_0

    :cond_0
    const/16 v0, 0x8

    .line 69
    invoke-virtual {p0, v0}, Landroid/widget/LinearLayout;->setVisibility(I)V

    :cond_1
    return-void
.end method


# virtual methods
.method public onClick(Landroid/view/View;)V
    .locals 1

    const-string v0, "v"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 124
    instance-of v0, p1, Lorg/nameless/gamespace/widget/tiles/AppTile;

    if-eqz v0, :cond_0

    .line 125
    check-cast p1, Lorg/nameless/gamespace/widget/tiles/AppTile;

    invoke-virtual {p1}, Lorg/nameless/gamespace/widget/tiles/AppTile;->getApp()Ljava/lang/String;

    move-result-object p1

    .line 126
    invoke-direct {p0, p1}, Lorg/nameless/gamespace/widget/QuickLaunchAppView;->startActivity(Ljava/lang/String;)V

    .line 127
    invoke-virtual {p0}, Landroid/widget/LinearLayout;->getContext()Landroid/content/Context;

    move-result-object p0

    new-instance p1, Landroid/content/Intent;

    const-string v0, "org.nameless.gamespace.CLONE_PANEL"

    invoke-direct {p1, v0}, Landroid/content/Intent;-><init>(Ljava/lang/String;)V

    sget-object v0, Landroid/os/UserHandle;->CURRENT:Landroid/os/UserHandle;

    invoke-virtual {p0, p1, v0}, Landroid/content/Context;->sendBroadcastAsUser(Landroid/content/Intent;Landroid/os/UserHandle;)V

    :cond_0
    return-void
.end method
