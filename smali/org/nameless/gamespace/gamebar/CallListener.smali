.class public final Lorg/nameless/gamespace/gamebar/CallListener;
.super Ljava/lang/Object;
.source "CallListener.kt"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lorg/nameless/gamespace/gamebar/CallListener$Listener;,
        Lorg/nameless/gamespace/gamebar/CallListener$Companion;
    }
.end annotation

.annotation system Ldalvik/annotation/SourceDebugExtension;
    value = "SMAP\nCallListener.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CallListener.kt\norg/nameless/gamespace/gamebar/CallListener\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,150:1\n12708#2,2:151\n*S KotlinDebug\n*F\n+ 1 CallListener.kt\norg/nameless/gamespace/gamebar/CallListener\n*L\n71#1:151,2\n*E\n"
.end annotation


# static fields
.field public static final Companion:Lorg/nameless/gamespace/gamebar/CallListener$Companion;


# instance fields
.field private final appSettings:Lorg/nameless/gamespace/data/AppSettings;

.field private final audioManager:Landroid/media/AudioManager;

.field private callStatus:I

.field private final callsMode:I

.field private final context:Landroid/content/Context;

.field private final phoneStateListener:Lorg/nameless/gamespace/gamebar/CallListener$Listener;

.field private final telecomManager:Landroid/telecom/TelecomManager;

.field private final telephonyManager:Landroid/telephony/TelephonyManager;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    new-instance v0, Lorg/nameless/gamespace/gamebar/CallListener$Companion;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lorg/nameless/gamespace/gamebar/CallListener$Companion;-><init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V

    sput-object v0, Lorg/nameless/gamespace/gamebar/CallListener;->Companion:Lorg/nameless/gamespace/gamebar/CallListener$Companion;

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Lorg/nameless/gamespace/data/AppSettings;)V
    .locals 1

    const-string v0, "context"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    const-string v0, "appSettings"

    invoke-static {p2, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 43
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 46
    iput-object p1, p0, Lorg/nameless/gamespace/gamebar/CallListener;->context:Landroid/content/Context;

    .line 47
    iput-object p2, p0, Lorg/nameless/gamespace/gamebar/CallListener;->appSettings:Lorg/nameless/gamespace/data/AppSettings;

    .line 50
    const-class v0, Landroid/media/AudioManager;

    invoke-virtual {p1, v0}, Landroid/content/Context;->getSystemService(Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/media/AudioManager;

    iput-object v0, p0, Lorg/nameless/gamespace/gamebar/CallListener;->audioManager:Landroid/media/AudioManager;

    .line 51
    const-class v0, Landroid/telephony/TelephonyManager;

    invoke-virtual {p1, v0}, Landroid/content/Context;->getSystemService(Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/telephony/TelephonyManager;

    iput-object v0, p0, Lorg/nameless/gamespace/gamebar/CallListener;->telephonyManager:Landroid/telephony/TelephonyManager;

    .line 52
    const-class v0, Landroid/telecom/TelecomManager;

    invoke-virtual {p1, v0}, Landroid/content/Context;->getSystemService(Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Landroid/telecom/TelecomManager;

    iput-object p1, p0, Lorg/nameless/gamespace/gamebar/CallListener;->telecomManager:Landroid/telecom/TelecomManager;

    .line 54
    invoke-virtual {p2}, Lorg/nameless/gamespace/data/AppSettings;->getCallsMode()I

    move-result p1

    iput p1, p0, Lorg/nameless/gamespace/gamebar/CallListener;->callsMode:I

    .line 56
    new-instance p1, Lorg/nameless/gamespace/gamebar/CallListener$Listener;

    invoke-direct {p1, p0}, Lorg/nameless/gamespace/gamebar/CallListener$Listener;-><init>(Lorg/nameless/gamespace/gamebar/CallListener;)V

    iput-object p1, p0, Lorg/nameless/gamespace/gamebar/CallListener;->phoneStateListener:Lorg/nameless/gamespace/gamebar/CallListener$Listener;

    const/4 p1, 0x2

    .line 58
    iput p1, p0, Lorg/nameless/gamespace/gamebar/CallListener;->callStatus:I

    return-void
.end method

.method public static final synthetic access$checkPermission(Lorg/nameless/gamespace/gamebar/CallListener;)Z
    .locals 0

    .line 43
    invoke-direct {p0}, Lorg/nameless/gamespace/gamebar/CallListener;->checkPermission()Z

    move-result p0

    return p0
.end method

.method public static final synthetic access$getAudioManager$p(Lorg/nameless/gamespace/gamebar/CallListener;)Landroid/media/AudioManager;
    .locals 0

    .line 43
    iget-object p0, p0, Lorg/nameless/gamespace/gamebar/CallListener;->audioManager:Landroid/media/AudioManager;

    return-object p0
.end method

.method public static final synthetic access$getCallsMode$p(Lorg/nameless/gamespace/gamebar/CallListener;)I
    .locals 0

    .line 43
    iget p0, p0, Lorg/nameless/gamespace/gamebar/CallListener;->callsMode:I

    return p0
.end method

.method public static final synthetic access$getContext$p(Lorg/nameless/gamespace/gamebar/CallListener;)Landroid/content/Context;
    .locals 0

    .line 43
    iget-object p0, p0, Lorg/nameless/gamespace/gamebar/CallListener;->context:Landroid/content/Context;

    return-object p0
.end method

.method public static final synthetic access$getTelecomManager$p(Lorg/nameless/gamespace/gamebar/CallListener;)Landroid/telecom/TelecomManager;
    .locals 0

    .line 43
    iget-object p0, p0, Lorg/nameless/gamespace/gamebar/CallListener;->telecomManager:Landroid/telecom/TelecomManager;

    return-object p0
.end method

.method public static final synthetic access$isHeadsetPluggedIn(Lorg/nameless/gamespace/gamebar/CallListener;)Z
    .locals 0

    .line 43
    invoke-direct {p0}, Lorg/nameless/gamespace/gamebar/CallListener;->isHeadsetPluggedIn()Z

    move-result p0

    return p0
.end method

.method private final checkPermission()Z
    .locals 1

    .line 80
    iget-object p0, p0, Lorg/nameless/gamespace/gamebar/CallListener;->context:Landroid/content/Context;

    const-string v0, "android.permission.ANSWER_PHONE_CALLS"

    .line 79
    invoke-static {p0, v0}, Landroidx/core/content/ContextCompat;->checkSelfPermission(Landroid/content/Context;Ljava/lang/String;)I

    move-result p0

    if-eqz p0, :cond_0

    const-string p0, "CallListener"

    const-string v0, "App does not have required permission ANSWER_PHONE_CALLS"

    .line 84
    invoke-static {p0, v0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    const/4 p0, 0x0

    return p0

    :cond_0
    const/4 p0, 0x1

    return p0
.end method

.method private final isHeadsetPluggedIn()Z
    .locals 7

    .line 70
    iget-object p0, p0, Lorg/nameless/gamespace/gamebar/CallListener;->audioManager:Landroid/media/AudioManager;

    const/4 v0, 0x2

    invoke-virtual {p0, v0}, Landroid/media/AudioManager;->getDevices(I)[Landroid/media/AudioDeviceInfo;

    move-result-object p0

    const-string v0, "audioManager.getDevices(\u2026ager.GET_DEVICES_OUTPUTS)"

    invoke-static {p0, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullExpressionValue(Ljava/lang/Object;Ljava/lang/String;)V

    .line 12708
    array-length v0, p0

    const/4 v1, 0x0

    move v2, v1

    :goto_0
    const/4 v3, 0x1

    if-ge v2, v0, :cond_3

    aget-object v4, p0, v2

    .line 72
    invoke-virtual {v4}, Landroid/media/AudioDeviceInfo;->getType()I

    move-result v5

    const/4 v6, 0x4

    if-eq v5, v6, :cond_1

    .line 73
    invoke-virtual {v4}, Landroid/media/AudioDeviceInfo;->getType()I

    move-result v5

    const/4 v6, 0x3

    if-eq v5, v6, :cond_1

    .line 74
    invoke-virtual {v4}, Landroid/media/AudioDeviceInfo;->getType()I

    move-result v4

    const/16 v5, 0x16

    if-ne v4, v5, :cond_0

    goto :goto_1

    :cond_0
    move v4, v1

    goto :goto_2

    :cond_1
    :goto_1
    move v4, v3

    :goto_2
    if-eqz v4, :cond_2

    move v1, v3

    goto :goto_3

    :cond_2
    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    :cond_3
    :goto_3
    return v1
.end method


# virtual methods
.method public final destory()V
    .locals 2

    .line 65
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/CallListener;->telephonyManager:Landroid/telephony/TelephonyManager;

    iget-object p0, p0, Lorg/nameless/gamespace/gamebar/CallListener;->phoneStateListener:Lorg/nameless/gamespace/gamebar/CallListener$Listener;

    const/4 v1, 0x0

    invoke-virtual {v0, p0, v1}, Landroid/telephony/TelephonyManager;->listen(Landroid/telephony/PhoneStateListener;I)V

    return-void
.end method

.method public final init()V
    .locals 2

    .line 61
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/CallListener;->telephonyManager:Landroid/telephony/TelephonyManager;

    iget-object p0, p0, Lorg/nameless/gamespace/gamebar/CallListener;->phoneStateListener:Lorg/nameless/gamespace/gamebar/CallListener$Listener;

    const/16 v1, 0x20

    invoke-virtual {v0, p0, v1}, Landroid/telephony/TelephonyManager;->listen(Landroid/telephony/PhoneStateListener;I)V

    return-void
.end method
