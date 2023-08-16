.class public final Lorg/nameless/gamespace/data/AppSettings;
.super Ljava/lang/Object;
.source "AppSettings.kt"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lorg/nameless/gamespace/data/AppSettings$Companion;
    }
.end annotation


# static fields
.field public static final Companion:Lorg/nameless/gamespace/data/AppSettings$Companion;


# instance fields
.field private final context:Landroid/content/Context;

.field private final db$delegate:Lkotlin/Lazy;

.field private final wm$delegate:Lkotlin/Lazy;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    new-instance v0, Lorg/nameless/gamespace/data/AppSettings$Companion;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lorg/nameless/gamespace/data/AppSettings$Companion;-><init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V

    sput-object v0, Lorg/nameless/gamespace/data/AppSettings;->Companion:Lorg/nameless/gamespace/data/AppSettings$Companion;

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;)V
    .locals 1

    const-string v0, "context"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 27
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lorg/nameless/gamespace/data/AppSettings;->context:Landroid/content/Context;

    .line 29
    new-instance p1, Lorg/nameless/gamespace/data/AppSettings$db$2;

    invoke-direct {p1, p0}, Lorg/nameless/gamespace/data/AppSettings$db$2;-><init>(Lorg/nameless/gamespace/data/AppSettings;)V

    invoke-static {p1}, Lkotlin/LazyKt;->lazy(Lkotlin/jvm/functions/Function0;)Lkotlin/Lazy;

    move-result-object p1

    iput-object p1, p0, Lorg/nameless/gamespace/data/AppSettings;->db$delegate:Lkotlin/Lazy;

    .line 30
    new-instance p1, Lorg/nameless/gamespace/data/AppSettings$wm$2;

    invoke-direct {p1, p0}, Lorg/nameless/gamespace/data/AppSettings$wm$2;-><init>(Lorg/nameless/gamespace/data/AppSettings;)V

    invoke-static {p1}, Lkotlin/LazyKt;->lazy(Lkotlin/jvm/functions/Function0;)Lkotlin/Lazy;

    move-result-object p1

    iput-object p1, p0, Lorg/nameless/gamespace/data/AppSettings;->wm$delegate:Lkotlin/Lazy;

    return-void
.end method

.method public static final synthetic access$getContext$p(Lorg/nameless/gamespace/data/AppSettings;)Landroid/content/Context;
    .locals 0

    .line 27
    iget-object p0, p0, Lorg/nameless/gamespace/data/AppSettings;->context:Landroid/content/Context;

    return-object p0
.end method

.method private final getDb()Landroid/content/SharedPreferences;
    .locals 0

    .line 29
    iget-object p0, p0, Lorg/nameless/gamespace/data/AppSettings;->db$delegate:Lkotlin/Lazy;

    invoke-interface {p0}, Lkotlin/Lazy;->getValue()Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Landroid/content/SharedPreferences;

    return-object p0
.end method

.method private final getWm()Landroid/view/WindowManager;
    .locals 0

    .line 30
    iget-object p0, p0, Lorg/nameless/gamespace/data/AppSettings;->wm$delegate:Lkotlin/Lazy;

    invoke-interface {p0}, Lkotlin/Lazy;->getValue()Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Landroid/view/WindowManager;

    return-object p0
.end method


# virtual methods
.method public final getCallsMode()I
    .locals 2

    .line 57
    invoke-direct {p0}, Lorg/nameless/gamespace/data/AppSettings;->getDb()Landroid/content/SharedPreferences;

    move-result-object p0

    const-string v0, "gamespace_calls_mode"

    const-string v1, "0"

    invoke-interface {p0, v0, v1}, Landroid/content/SharedPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    const-string v0, "db.getString(KEY_CALLS_MODE, \"0\")"

    invoke-static {p0, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullExpressionValue(Ljava/lang/Object;Ljava/lang/String;)V

    invoke-static {p0}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;)I

    move-result p0

    return p0
.end method

.method public final getLockGesture()Z
    .locals 2

    .line 77
    invoke-direct {p0}, Lorg/nameless/gamespace/data/AppSettings;->getDb()Landroid/content/SharedPreferences;

    move-result-object p0

    const-string v0, "gamespace_lock_gesture"

    const/4 v1, 0x0

    invoke-interface {p0, v0, v1}, Landroid/content/SharedPreferences;->getBoolean(Ljava/lang/String;Z)Z

    move-result p0

    return p0
.end method

.method public final getLockStatusBar()Z
    .locals 2

    .line 81
    invoke-direct {p0}, Lorg/nameless/gamespace/data/AppSettings;->getDb()Landroid/content/SharedPreferences;

    move-result-object p0

    const-string v0, "gamespace_lock_statusbar"

    const/4 v1, 0x0

    invoke-interface {p0, v0, v1}, Landroid/content/SharedPreferences;->getBoolean(Ljava/lang/String;Z)Z

    move-result p0

    return p0
.end method

.method public final getMenuOpacity()I
    .locals 2

    .line 69
    invoke-direct {p0}, Lorg/nameless/gamespace/data/AppSettings;->getDb()Landroid/content/SharedPreferences;

    move-result-object p0

    const-string v0, "gamespace_menu_opacity"

    const/16 v1, 0x4b

    invoke-interface {p0, v0, v1}, Landroid/content/SharedPreferences;->getInt(Ljava/lang/String;I)I

    move-result p0

    return p0
.end method

.method public final getNoAdbEnabled()Z
    .locals 2

    .line 73
    invoke-direct {p0}, Lorg/nameless/gamespace/data/AppSettings;->getDb()Landroid/content/SharedPreferences;

    move-result-object p0

    const-string v0, "gamespace_adb_disabled"

    const/4 v1, 0x0

    invoke-interface {p0, v0, v1}, Landroid/content/SharedPreferences;->getBoolean(Ljava/lang/String;Z)Z

    move-result p0

    return p0
.end method

.method public final getNoAutoBrightness()Z
    .locals 2

    .line 45
    invoke-direct {p0}, Lorg/nameless/gamespace/data/AppSettings;->getDb()Landroid/content/SharedPreferences;

    move-result-object p0

    const-string v0, "gamespace_auto_brightness_disabled"

    const/4 v1, 0x1

    invoke-interface {p0, v0, v1}, Landroid/content/SharedPreferences;->getBoolean(Ljava/lang/String;Z)Z

    move-result p0

    return p0
.end method

.method public final getNoThreeScreenshot()Z
    .locals 2

    .line 49
    invoke-direct {p0}, Lorg/nameless/gamespace/data/AppSettings;->getDb()Landroid/content/SharedPreferences;

    move-result-object p0

    const-string v0, "gamespace_tfgesture_disabled"

    const/4 v1, 0x0

    invoke-interface {p0, v0, v1}, Landroid/content/SharedPreferences;->getBoolean(Ljava/lang/String;Z)Z

    move-result p0

    return p0
.end method

.method public final getNotificationsMode()I
    .locals 2

    .line 61
    invoke-direct {p0}, Lorg/nameless/gamespace/data/AppSettings;->getDb()Landroid/content/SharedPreferences;

    move-result-object p0

    const-string v0, "gamespace_notifications_mode"

    const-string v1, "2"

    invoke-interface {p0, v0, v1}, Landroid/content/SharedPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    const-string v0, "db.getString(KEY_NOTIFICAITONS_MODE, \"2\")"

    invoke-static {p0, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullExpressionValue(Ljava/lang/Object;Ljava/lang/String;)V

    invoke-static {p0}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;)I

    move-result p0

    return p0
.end method

.method public final getRingerMode()I
    .locals 2

    .line 65
    invoke-direct {p0}, Lorg/nameless/gamespace/data/AppSettings;->getDb()Landroid/content/SharedPreferences;

    move-result-object p0

    const-string v0, "gamespace_ringer_mode"

    const-string v1, "0"

    invoke-interface {p0, v0, v1}, Landroid/content/SharedPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    const-string v0, "db.getString(KEY_RINGER_MODE, \"0\")"

    invoke-static {p0, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullExpressionValue(Ljava/lang/Object;Ljava/lang/String;)V

    invoke-static {p0}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;)I

    move-result p0

    return p0
.end method

.method public final getShowFps()Z
    .locals 2

    .line 41
    invoke-direct {p0}, Lorg/nameless/gamespace/data/AppSettings;->getDb()Landroid/content/SharedPreferences;

    move-result-object p0

    const-string v0, "show_fps"

    const/4 v1, 0x0

    invoke-interface {p0, v0, v1}, Landroid/content/SharedPreferences;->getBoolean(Ljava/lang/String;Z)Z

    move-result p0

    return p0
.end method

.method public final getShowOverlay()Z
    .locals 2

    .line 85
    invoke-direct {p0}, Lorg/nameless/gamespace/data/AppSettings;->getDb()Landroid/content/SharedPreferences;

    move-result-object p0

    const-string v0, "gamespace_show_overlay"

    const/4 v1, 0x1

    invoke-interface {p0, v0, v1}, Landroid/content/SharedPreferences;->getBoolean(Ljava/lang/String;Z)Z

    move-result p0

    return p0
.end method

.method public final getStayAwake()Z
    .locals 2

    .line 53
    invoke-direct {p0}, Lorg/nameless/gamespace/data/AppSettings;->getDb()Landroid/content/SharedPreferences;

    move-result-object p0

    const-string v0, "gamespace_stay_awake"

    const/4 v1, 0x0

    invoke-interface {p0, v0, v1}, Landroid/content/SharedPreferences;->getBoolean(Ljava/lang/String;Z)Z

    move-result p0

    return p0
.end method

.method public final getX()I
    .locals 2

    .line 33
    invoke-direct {p0}, Lorg/nameless/gamespace/data/AppSettings;->getDb()Landroid/content/SharedPreferences;

    move-result-object v0

    invoke-direct {p0}, Lorg/nameless/gamespace/data/AppSettings;->getWm()Landroid/view/WindowManager;

    move-result-object p0

    invoke-interface {p0}, Landroid/view/WindowManager;->getMaximumWindowMetrics()Landroid/view/WindowMetrics;

    move-result-object p0

    invoke-virtual {p0}, Landroid/view/WindowMetrics;->getBounds()Landroid/graphics/Rect;

    move-result-object p0

    invoke-virtual {p0}, Landroid/graphics/Rect;->width()I

    move-result p0

    div-int/lit8 p0, p0, 0x2

    const-string v1, "offset_x"

    invoke-interface {v0, v1, p0}, Landroid/content/SharedPreferences;->getInt(Ljava/lang/String;I)I

    move-result p0

    return p0
.end method

.method public final getY()I
    .locals 2

    .line 37
    invoke-direct {p0}, Lorg/nameless/gamespace/data/AppSettings;->getDb()Landroid/content/SharedPreferences;

    move-result-object v0

    iget-object p0, p0, Lorg/nameless/gamespace/data/AppSettings;->context:Landroid/content/Context;

    invoke-static {p0}, Lorg/nameless/gamespace/utils/ExtensionsKt;->getStatusbarHeight(Landroid/content/Context;)I

    move-result p0

    const/16 v1, 0x8

    invoke-static {v1}, Lorg/nameless/gamespace/utils/ExtensionsKt;->getDp(I)I

    move-result v1

    add-int/2addr p0, v1

    const-string v1, "offset_y"

    invoke-interface {v0, v1, p0}, Landroid/content/SharedPreferences;->getInt(Ljava/lang/String;I)I

    move-result p0

    return p0
.end method

.method public final setNotificationsMode(I)V
    .locals 1

    .line 62
    invoke-direct {p0}, Lorg/nameless/gamespace/data/AppSettings;->getDb()Landroid/content/SharedPreferences;

    move-result-object p0

    invoke-interface {p0}, Landroid/content/SharedPreferences;->edit()Landroid/content/SharedPreferences$Editor;

    move-result-object p0

    invoke-static {p1}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object p1

    const-string v0, "gamespace_notifications_mode"

    invoke-interface {p0, v0, p1}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    move-result-object p0

    invoke-interface {p0}, Landroid/content/SharedPreferences$Editor;->apply()V

    return-void
.end method

.method public final setShowFps(Z)V
    .locals 1

    .line 42
    invoke-direct {p0}, Lorg/nameless/gamespace/data/AppSettings;->getDb()Landroid/content/SharedPreferences;

    move-result-object p0

    invoke-interface {p0}, Landroid/content/SharedPreferences;->edit()Landroid/content/SharedPreferences$Editor;

    move-result-object p0

    const-string v0, "show_fps"

    invoke-interface {p0, v0, p1}, Landroid/content/SharedPreferences$Editor;->putBoolean(Ljava/lang/String;Z)Landroid/content/SharedPreferences$Editor;

    move-result-object p0

    invoke-interface {p0}, Landroid/content/SharedPreferences$Editor;->apply()V

    return-void
.end method

.method public final setStayAwake(Z)V
    .locals 1

    .line 54
    invoke-direct {p0}, Lorg/nameless/gamespace/data/AppSettings;->getDb()Landroid/content/SharedPreferences;

    move-result-object p0

    invoke-interface {p0}, Landroid/content/SharedPreferences;->edit()Landroid/content/SharedPreferences$Editor;

    move-result-object p0

    const-string v0, "gamespace_stay_awake"

    invoke-interface {p0, v0, p1}, Landroid/content/SharedPreferences$Editor;->putBoolean(Ljava/lang/String;Z)Landroid/content/SharedPreferences$Editor;

    move-result-object p0

    invoke-interface {p0}, Landroid/content/SharedPreferences$Editor;->apply()V

    return-void
.end method

.method public final setX(I)V
    .locals 1

    .line 34
    invoke-direct {p0}, Lorg/nameless/gamespace/data/AppSettings;->getDb()Landroid/content/SharedPreferences;

    move-result-object p0

    invoke-interface {p0}, Landroid/content/SharedPreferences;->edit()Landroid/content/SharedPreferences$Editor;

    move-result-object p0

    const-string v0, "offset_x"

    invoke-interface {p0, v0, p1}, Landroid/content/SharedPreferences$Editor;->putInt(Ljava/lang/String;I)Landroid/content/SharedPreferences$Editor;

    move-result-object p0

    invoke-interface {p0}, Landroid/content/SharedPreferences$Editor;->apply()V

    return-void
.end method

.method public final setY(I)V
    .locals 1

    .line 38
    invoke-direct {p0}, Lorg/nameless/gamespace/data/AppSettings;->getDb()Landroid/content/SharedPreferences;

    move-result-object p0

    invoke-interface {p0}, Landroid/content/SharedPreferences;->edit()Landroid/content/SharedPreferences$Editor;

    move-result-object p0

    const-string v0, "offset_y"

    invoke-interface {p0, v0, p1}, Landroid/content/SharedPreferences$Editor;->putInt(Ljava/lang/String;I)Landroid/content/SharedPreferences$Editor;

    move-result-object p0

    invoke-interface {p0}, Landroid/content/SharedPreferences$Editor;->apply()V

    return-void
.end method
