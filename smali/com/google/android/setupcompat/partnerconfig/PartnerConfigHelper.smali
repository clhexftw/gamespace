.class public Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;
.super Ljava/lang/Object;
.source "PartnerConfigHelper.java"


# static fields
.field public static final GET_SUW_DEFAULT_THEME_STRING_METHOD:Ljava/lang/String; = "suwDefaultThemeString"

.field public static final IS_DYNAMIC_COLOR_ENABLED_METHOD:Ljava/lang/String; = "isDynamicColorEnabled"

.field public static final IS_EXTENDED_PARTNER_CONFIG_ENABLED_METHOD:Ljava/lang/String; = "isExtendedPartnerConfigEnabled"

.field public static final IS_MATERIAL_YOU_STYLE_ENABLED_METHOD:Ljava/lang/String; = "IsMaterialYouStyleEnabled"

.field public static final IS_NEUTRAL_BUTTON_STYLE_ENABLED_METHOD:Ljava/lang/String; = "isNeutralButtonStyleEnabled"

.field public static final IS_SUW_DAY_NIGHT_ENABLED_METHOD:Ljava/lang/String; = "isSuwDayNightEnabled"

.field public static final KEY_FALLBACK_CONFIG:Ljava/lang/String; = "fallbackConfig"

.field public static final MATERIAL_YOU_RESOURCE_SUFFIX:Ljava/lang/String; = "_material_you"

.field public static final SUW_GET_PARTNER_CONFIG_METHOD:Ljava/lang/String; = "getOverlayConfig"

.field public static final SUW_PACKAGE_NAME:Ljava/lang/String; = "com.google.android.setupwizard"

.field private static final TAG:Ljava/lang/String; = "PartnerConfigHelper"

.field public static applyDynamicColorBundle:Landroid/os/Bundle; = null

.field public static applyExtendedPartnerConfigBundle:Landroid/os/Bundle; = null

.field public static applyMaterialYouConfigBundle:Landroid/os/Bundle; = null

.field public static applyNeutralButtonStyleBundle:Landroid/os/Bundle; = null

.field private static contentObserver:Landroid/database/ContentObserver; = null

.field private static instance:Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper; = null

.field private static savedConfigUiMode:I = 0x0

.field public static savedOrientation:I = 0x1

.field public static savedScreenHeight:I

.field public static savedScreenWidth:I

.field static suwDayNightEnabledBundle:Landroid/os/Bundle;

.field public static suwDefaultThemeBundle:Landroid/os/Bundle;


# instance fields
.field final partnerResourceCache:Ljava/util/EnumMap;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/EnumMap<",
            "Lcom/google/android/setupcompat/partnerconfig/PartnerConfig;",
            "Ljava/lang/Object;",
            ">;"
        }
    .end annotation
.end field

.field resultBundle:Landroid/os/Bundle;


# direct methods
.method static constructor <clinit>()V
    .locals 0

    return-void
.end method

.method private constructor <init>(Landroid/content/Context;)V
    .locals 2

    .line 144
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const/4 v0, 0x0

    .line 91
    iput-object v0, p0, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->resultBundle:Landroid/os/Bundle;

    .line 93
    new-instance v0, Ljava/util/EnumMap;

    const-class v1, Lcom/google/android/setupcompat/partnerconfig/PartnerConfig;

    invoke-direct {v0, v1}, Ljava/util/EnumMap;-><init>(Ljava/lang/Class;)V

    iput-object v0, p0, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->partnerResourceCache:Ljava/util/EnumMap;

    .line 145
    invoke-direct {p0, p1}, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->getPartnerConfigBundle(Landroid/content/Context;)V

    .line 147
    invoke-static {p1}, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->registerContentObserver(Landroid/content/Context;)V

    return-void
.end method

.method private static adjustResourceEntryDayNightMode(Landroid/content/Context;Lcom/google/android/setupcompat/partnerconfig/ResourceEntry;)Lcom/google/android/setupcompat/partnerconfig/ResourceEntry;
    .locals 2

    .line 566
    invoke-virtual {p1}, Lcom/google/android/setupcompat/partnerconfig/ResourceEntry;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    .line 567
    invoke-virtual {v0}, Landroid/content/res/Resources;->getConfiguration()Landroid/content/res/Configuration;

    move-result-object v1

    .line 568
    invoke-static {p0}, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->isSetupWizardDayNightEnabled(Landroid/content/Context;)Z

    move-result p0

    if-nez p0, :cond_0

    invoke-static {v1}, Lcom/google/android/setupcompat/partnerconfig/Util;->isNightMode(Landroid/content/res/Configuration;)Z

    move-result p0

    if-eqz p0, :cond_0

    .line 573
    iget p0, v1, Landroid/content/res/Configuration;->uiMode:I

    and-int/lit8 p0, p0, -0x31

    or-int/lit8 p0, p0, 0x10

    iput p0, v1, Landroid/content/res/Configuration;->uiMode:I

    .line 576
    invoke-virtual {v0}, Landroid/content/res/Resources;->getDisplayMetrics()Landroid/util/DisplayMetrics;

    move-result-object p0

    invoke-virtual {v0, v1, p0}, Landroid/content/res/Resources;->updateConfiguration(Landroid/content/res/Configuration;Landroid/util/DisplayMetrics;)V

    :cond_0
    return-object p1
.end method

.method public static declared-synchronized get(Landroid/content/Context;)Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;
    .locals 2

    const-class v0, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;

    monitor-enter v0

    .line 111
    :try_start_0
    invoke-static {p0}, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->isValidInstance(Landroid/content/Context;)Z

    move-result v1

    if-nez v1, :cond_0

    .line 112
    new-instance v1, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;

    invoke-direct {v1, p0}, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;-><init>(Landroid/content/Context;)V

    sput-object v1, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->instance:Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;

    .line 114
    :cond_0
    sget-object p0, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->instance:Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    monitor-exit v0

    return-object p0

    :catchall_0
    move-exception p0

    monitor-exit v0

    throw p0
.end method

.method static getContentUri()Landroid/net/Uri;
    .locals 2

    .line 794
    new-instance v0, Landroid/net/Uri$Builder;

    invoke-direct {v0}, Landroid/net/Uri$Builder;-><init>()V

    const-string v1, "content"

    .line 795
    invoke-virtual {v0, v1}, Landroid/net/Uri$Builder;->scheme(Ljava/lang/String;)Landroid/net/Uri$Builder;

    move-result-object v0

    const-string v1, "com.google.android.setupwizard.partner"

    .line 796
    invoke-virtual {v0, v1}, Landroid/net/Uri$Builder;->authority(Ljava/lang/String;)Landroid/net/Uri$Builder;

    move-result-object v0

    .line 797
    invoke-virtual {v0}, Landroid/net/Uri$Builder;->build()Landroid/net/Uri;

    move-result-object v0

    return-object v0
.end method

.method private static getDimensionFromTypedValue(Landroid/content/Context;Landroid/util/TypedValue;)F
    .locals 0

    .line 815
    invoke-virtual {p0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object p0

    invoke-virtual {p0}, Landroid/content/res/Resources;->getDisplayMetrics()Landroid/util/DisplayMetrics;

    move-result-object p0

    .line 816
    invoke-virtual {p1, p0}, Landroid/util/TypedValue;->getDimension(Landroid/util/DisplayMetrics;)F

    move-result p0

    return p0
.end method

.method private getPartnerConfigBundle(Landroid/content/Context;)V
    .locals 3

    .line 527
    iget-object v0, p0, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->resultBundle:Landroid/os/Bundle;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Landroid/os/Bundle;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_2

    .line 531
    :cond_0
    :try_start_0
    invoke-virtual {p1}, Landroid/content/Context;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object p1

    .line 533
    invoke-static {}, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->getContentUri()Landroid/net/Uri;

    move-result-object v0

    const-string v1, "getOverlayConfig"

    const/4 v2, 0x0

    .line 532
    invoke-virtual {p1, v0, v1, v2, v2}, Landroid/content/ContentResolver;->call(Landroid/net/Uri;Ljava/lang/String;Ljava/lang/String;Landroid/os/Bundle;)Landroid/os/Bundle;

    move-result-object p1

    iput-object p1, p0, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->resultBundle:Landroid/os/Bundle;

    .line 537
    iget-object p1, p0, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->partnerResourceCache:Ljava/util/EnumMap;

    invoke-virtual {p1}, Ljava/util/EnumMap;->clear()V

    .line 538
    sget-object p1, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->TAG:Ljava/lang/String;

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "PartnerConfigsBundle="

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 539
    iget-object p0, p0, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->resultBundle:Landroid/os/Bundle;

    if-eqz p0, :cond_1

    invoke-virtual {p0}, Landroid/os/Bundle;->size()I

    move-result p0

    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    goto :goto_0

    :cond_1
    const-string p0, "(null)"

    :goto_0
    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    .line 538
    invoke-static {p1, p0}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I
    :try_end_0
    .catch Ljava/lang/IllegalArgumentException; {:try_start_0 .. :try_end_0} :catch_0
    .catch Ljava/lang/SecurityException; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_1

    .line 541
    :catch_0
    sget-object p0, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->TAG:Ljava/lang/String;

    const-string p1, "Fail to get config from suw provider"

    invoke-static {p0, p1}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    :cond_2
    :goto_1
    return-void
.end method

.method private static getTypedValueFromResource(Landroid/content/res/Resources;II)Landroid/util/TypedValue;
    .locals 2

    .line 801
    new-instance v0, Landroid/util/TypedValue;

    invoke-direct {v0}, Landroid/util/TypedValue;-><init>()V

    const/4 v1, 0x1

    .line 802
    invoke-virtual {p0, p1, v0, v1}, Landroid/content/res/Resources;->getValue(ILandroid/util/TypedValue;Z)V

    .line 803
    iget p0, v0, Landroid/util/TypedValue;->type:I

    if-ne p0, p2, :cond_0

    return-object v0

    .line 804
    :cond_0
    new-instance p0, Landroid/content/res/Resources$NotFoundException;

    new-instance p2, Ljava/lang/StringBuilder;

    invoke-direct {p2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "Resource ID #0x"

    invoke-virtual {p2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 806
    invoke-static {p1}, Ljava/lang/Integer;->toHexString(I)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p1, " type #0x"

    invoke-virtual {p2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget p1, v0, Landroid/util/TypedValue;->type:I

    .line 808
    invoke-static {p1}, Ljava/lang/Integer;->toHexString(I)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p1, " is not valid"

    invoke-virtual {p2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-direct {p0, p1}, Landroid/content/res/Resources$NotFoundException;-><init>(Ljava/lang/String;)V

    throw p0
.end method

.method public static isNeutralButtonStyleEnabled(Landroid/content/Context;)Z
    .locals 4

    .line 771
    sget-object v0, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->applyNeutralButtonStyleBundle:Landroid/os/Bundle;

    const-string v1, "isNeutralButtonStyleEnabled"

    const/4 v2, 0x0

    if-nez v0, :cond_0

    const/4 v0, 0x0

    .line 775
    :try_start_0
    invoke-virtual {p0}, Landroid/content/Context;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object p0

    .line 777
    invoke-static {}, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->getContentUri()Landroid/net/Uri;

    move-result-object v3

    .line 776
    invoke-virtual {p0, v3, v1, v0, v0}, Landroid/content/ContentResolver;->call(Landroid/net/Uri;Ljava/lang/String;Ljava/lang/String;Landroid/os/Bundle;)Landroid/os/Bundle;

    move-result-object p0

    sput-object p0, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->applyNeutralButtonStyleBundle:Landroid/os/Bundle;
    :try_end_0
    .catch Ljava/lang/IllegalArgumentException; {:try_start_0 .. :try_end_0} :catch_0
    .catch Ljava/lang/SecurityException; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    .line 782
    :catch_0
    sget-object p0, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->TAG:Ljava/lang/String;

    const-string v1, "Neutral button style supporting status unknown; return as false."

    invoke-static {p0, v1}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    .line 783
    sput-object v0, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->applyNeutralButtonStyleBundle:Landroid/os/Bundle;

    return v2

    .line 788
    :cond_0
    :goto_0
    sget-object p0, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->applyNeutralButtonStyleBundle:Landroid/os/Bundle;

    if-eqz p0, :cond_1

    .line 789
    invoke-virtual {p0, v1, v2}, Landroid/os/Bundle;->getBoolean(Ljava/lang/String;Z)Z

    move-result p0

    if-eqz p0, :cond_1

    const/4 v2, 0x1

    :cond_1
    return v2
.end method

.method public static isSetupWizardDayNightEnabled(Landroid/content/Context;)Z
    .locals 4

    .line 638
    sget-object v0, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->suwDayNightEnabledBundle:Landroid/os/Bundle;

    const-string v1, "isSuwDayNightEnabled"

    const/4 v2, 0x0

    if-nez v0, :cond_0

    const/4 v0, 0x0

    .line 642
    :try_start_0
    invoke-virtual {p0}, Landroid/content/Context;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object p0

    .line 644
    invoke-static {}, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->getContentUri()Landroid/net/Uri;

    move-result-object v3

    .line 643
    invoke-virtual {p0, v3, v1, v0, v0}, Landroid/content/ContentResolver;->call(Landroid/net/Uri;Ljava/lang/String;Ljava/lang/String;Landroid/os/Bundle;)Landroid/os/Bundle;

    move-result-object p0

    sput-object p0, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->suwDayNightEnabledBundle:Landroid/os/Bundle;
    :try_end_0
    .catch Ljava/lang/IllegalArgumentException; {:try_start_0 .. :try_end_0} :catch_0
    .catch Ljava/lang/SecurityException; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    .line 649
    :catch_0
    sget-object p0, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->TAG:Ljava/lang/String;

    const-string v1, "SetupWizard DayNight supporting status unknown; return as false."

    invoke-static {p0, v1}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    .line 650
    sput-object v0, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->suwDayNightEnabledBundle:Landroid/os/Bundle;

    return v2

    .line 655
    :cond_0
    :goto_0
    sget-object p0, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->suwDayNightEnabledBundle:Landroid/os/Bundle;

    if-eqz p0, :cond_1

    .line 656
    invoke-virtual {p0, v1, v2}, Landroid/os/Bundle;->getBoolean(Ljava/lang/String;Z)Z

    move-result p0

    if-eqz p0, :cond_1

    const/4 v2, 0x1

    :cond_1
    return v2
.end method

.method public static isSetupWizardDynamicColorEnabled(Landroid/content/Context;)Z
    .locals 4

    .line 748
    sget-object v0, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->applyDynamicColorBundle:Landroid/os/Bundle;

    const-string v1, "isDynamicColorEnabled"

    const/4 v2, 0x0

    if-nez v0, :cond_0

    const/4 v0, 0x0

    .line 752
    :try_start_0
    invoke-virtual {p0}, Landroid/content/Context;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object p0

    .line 754
    invoke-static {}, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->getContentUri()Landroid/net/Uri;

    move-result-object v3

    .line 753
    invoke-virtual {p0, v3, v1, v0, v0}, Landroid/content/ContentResolver;->call(Landroid/net/Uri;Ljava/lang/String;Ljava/lang/String;Landroid/os/Bundle;)Landroid/os/Bundle;

    move-result-object p0

    sput-object p0, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->applyDynamicColorBundle:Landroid/os/Bundle;
    :try_end_0
    .catch Ljava/lang/IllegalArgumentException; {:try_start_0 .. :try_end_0} :catch_0
    .catch Ljava/lang/SecurityException; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    .line 759
    :catch_0
    sget-object p0, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->TAG:Ljava/lang/String;

    const-string v1, "SetupWizard dynamic color supporting status unknown; return as false."

    invoke-static {p0, v1}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    .line 760
    sput-object v0, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->applyDynamicColorBundle:Landroid/os/Bundle;

    return v2

    .line 765
    :cond_0
    :goto_0
    sget-object p0, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->applyDynamicColorBundle:Landroid/os/Bundle;

    if-eqz p0, :cond_1

    .line 766
    invoke-virtual {p0, v1, v2}, Landroid/os/Bundle;->getBoolean(Ljava/lang/String;Z)Z

    move-result p0

    if-eqz p0, :cond_1

    const/4 v2, 0x1

    :cond_1
    return v2
.end method

.method private static isValidInstance(Landroid/content/Context;)Z
    .locals 4

    .line 118
    invoke-virtual {p0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    invoke-virtual {v0}, Landroid/content/res/Resources;->getConfiguration()Landroid/content/res/Configuration;

    move-result-object v0

    .line 119
    sget-object v1, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->instance:Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;

    const/4 v2, 0x0

    if-nez v1, :cond_0

    .line 120
    iget p0, v0, Landroid/content/res/Configuration;->uiMode:I

    and-int/lit8 p0, p0, 0x30

    sput p0, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->savedConfigUiMode:I

    .line 121
    iget p0, v0, Landroid/content/res/Configuration;->orientation:I

    sput p0, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->savedOrientation:I

    .line 122
    iget p0, v0, Landroid/content/res/Configuration;->screenWidthDp:I

    sput p0, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->savedScreenWidth:I

    .line 123
    iget p0, v0, Landroid/content/res/Configuration;->screenHeightDp:I

    sput p0, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->savedScreenHeight:I

    return v2

    .line 127
    :cond_0
    invoke-static {p0}, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->isSetupWizardDayNightEnabled(Landroid/content/Context;)Z

    move-result p0

    const/4 v1, 0x1

    if-eqz p0, :cond_1

    iget p0, v0, Landroid/content/res/Configuration;->uiMode:I

    and-int/lit8 p0, p0, 0x30

    sget v3, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->savedConfigUiMode:I

    if-eq p0, v3, :cond_1

    move p0, v1

    goto :goto_0

    :cond_1
    move p0, v2

    :goto_0
    if-nez p0, :cond_3

    .line 129
    iget p0, v0, Landroid/content/res/Configuration;->orientation:I

    sget v3, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->savedOrientation:I

    if-ne p0, v3, :cond_3

    iget p0, v0, Landroid/content/res/Configuration;->screenWidthDp:I

    sget v3, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->savedScreenWidth:I

    if-ne p0, v3, :cond_3

    iget p0, v0, Landroid/content/res/Configuration;->screenHeightDp:I

    sget v3, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->savedScreenHeight:I

    if-eq p0, v3, :cond_2

    goto :goto_1

    :cond_2
    return v1

    .line 133
    :cond_3
    :goto_1
    iget p0, v0, Landroid/content/res/Configuration;->uiMode:I

    and-int/lit8 p0, p0, 0x30

    sput p0, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->savedConfigUiMode:I

    .line 134
    iget p0, v0, Landroid/content/res/Configuration;->orientation:I

    sput p0, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->savedOrientation:I

    .line 135
    iget p0, v0, Landroid/content/res/Configuration;->screenHeightDp:I

    sput p0, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->savedScreenHeight:I

    .line 136
    iget p0, v0, Landroid/content/res/Configuration;->screenWidthDp:I

    sput p0, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->savedScreenWidth:I

    .line 137
    invoke-static {}, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->resetInstance()V

    return v2
.end method

.method private static registerContentObserver(Landroid/content/Context;)V
    .locals 4

    .line 820
    invoke-static {p0}, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->isSetupWizardDayNightEnabled(Landroid/content/Context;)Z

    move-result v0

    if-eqz v0, :cond_1

    .line 821
    sget-object v0, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->contentObserver:Landroid/database/ContentObserver;

    if-eqz v0, :cond_0

    .line 822
    invoke-static {p0}, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->unregisterContentObserver(Landroid/content/Context;)V

    .line 825
    :cond_0
    invoke-static {}, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->getContentUri()Landroid/net/Uri;

    move-result-object v0

    .line 827
    :try_start_0
    new-instance v1, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper$1;

    const/4 v2, 0x0

    invoke-direct {v1, v2}, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper$1;-><init>(Landroid/os/Handler;)V

    sput-object v1, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->contentObserver:Landroid/database/ContentObserver;

    .line 836
    invoke-virtual {p0}, Landroid/content/Context;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object p0

    const/4 v1, 0x1

    sget-object v2, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->contentObserver:Landroid/database/ContentObserver;

    .line 837
    invoke-virtual {p0, v0, v1, v2}, Landroid/content/ContentResolver;->registerContentObserver(Landroid/net/Uri;ZLandroid/database/ContentObserver;)V
    :try_end_0
    .catch Ljava/lang/SecurityException; {:try_start_0 .. :try_end_0} :catch_0
    .catch Ljava/lang/NullPointerException; {:try_start_0 .. :try_end_0} :catch_0
    .catch Ljava/lang/IllegalArgumentException; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception p0

    .line 839
    sget-object v1, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->TAG:Ljava/lang/String;

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "Failed to register content observer for "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v0, ": "

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-static {v1, p0}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    :cond_1
    :goto_0
    return-void
.end method

.method public static declared-synchronized resetInstance()V
    .locals 2

    const-class v0, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;

    monitor-enter v0

    const/4 v1, 0x0

    .line 622
    :try_start_0
    sput-object v1, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->instance:Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;

    .line 623
    sput-object v1, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->suwDayNightEnabledBundle:Landroid/os/Bundle;

    .line 624
    sput-object v1, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->applyExtendedPartnerConfigBundle:Landroid/os/Bundle;

    .line 625
    sput-object v1, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->applyMaterialYouConfigBundle:Landroid/os/Bundle;

    .line 626
    sput-object v1, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->applyDynamicColorBundle:Landroid/os/Bundle;

    .line 627
    sput-object v1, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->applyNeutralButtonStyleBundle:Landroid/os/Bundle;

    .line 628
    sput-object v1, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->suwDefaultThemeBundle:Landroid/os/Bundle;
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 629
    monitor-exit v0

    return-void

    :catchall_0
    move-exception v1

    monitor-exit v0

    throw v1
.end method

.method public static shouldApplyExtendedPartnerConfig(Landroid/content/Context;)Z
    .locals 4

    .line 661
    sget-object v0, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->applyExtendedPartnerConfigBundle:Landroid/os/Bundle;

    const-string v1, "isExtendedPartnerConfigEnabled"

    const/4 v2, 0x0

    if-nez v0, :cond_0

    const/4 v0, 0x0

    .line 665
    :try_start_0
    invoke-virtual {p0}, Landroid/content/Context;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object p0

    .line 667
    invoke-static {}, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->getContentUri()Landroid/net/Uri;

    move-result-object v3

    .line 666
    invoke-virtual {p0, v3, v1, v0, v0}, Landroid/content/ContentResolver;->call(Landroid/net/Uri;Ljava/lang/String;Ljava/lang/String;Landroid/os/Bundle;)Landroid/os/Bundle;

    move-result-object p0

    sput-object p0, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->applyExtendedPartnerConfigBundle:Landroid/os/Bundle;
    :try_end_0
    .catch Ljava/lang/IllegalArgumentException; {:try_start_0 .. :try_end_0} :catch_0
    .catch Ljava/lang/SecurityException; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    .line 672
    :catch_0
    sget-object p0, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->TAG:Ljava/lang/String;

    const-string v1, "SetupWizard extended partner configs supporting status unknown; return as false."

    invoke-static {p0, v1}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    .line 675
    sput-object v0, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->applyExtendedPartnerConfigBundle:Landroid/os/Bundle;

    return v2

    .line 680
    :cond_0
    :goto_0
    sget-object p0, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->applyExtendedPartnerConfigBundle:Landroid/os/Bundle;

    if-eqz p0, :cond_1

    .line 681
    invoke-virtual {p0, v1, v2}, Landroid/os/Bundle;->getBoolean(Ljava/lang/String;Z)Z

    move-result p0

    if-eqz p0, :cond_1

    const/4 v2, 0x1

    :cond_1
    return v2
.end method

.method public static shouldApplyMaterialYouStyle(Landroid/content/Context;)Z
    .locals 5

    .line 690
    sget-object v0, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->applyMaterialYouConfigBundle:Landroid/os/Bundle;

    const-string v1, "IsMaterialYouStyleEnabled"

    const/4 v2, 0x0

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Landroid/os/Bundle;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_1

    :cond_0
    const/4 v0, 0x0

    .line 694
    :try_start_0
    invoke-virtual {p0}, Landroid/content/Context;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object v3

    .line 696
    invoke-static {}, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->getContentUri()Landroid/net/Uri;

    move-result-object v4

    .line 695
    invoke-virtual {v3, v4, v1, v0, v0}, Landroid/content/ContentResolver;->call(Landroid/net/Uri;Ljava/lang/String;Ljava/lang/String;Landroid/os/Bundle;)Landroid/os/Bundle;

    move-result-object v3

    sput-object v3, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->applyMaterialYouConfigBundle:Landroid/os/Bundle;

    if-eqz v3, :cond_1

    .line 703
    invoke-virtual {v3}, Landroid/os/Bundle;->isEmpty()Z

    move-result v3

    if-eqz v3, :cond_1

    .line 704
    invoke-static {}, Lcom/google/android/setupcompat/util/BuildCompatUtils;->isAtLeastT()Z

    move-result v3

    if-nez v3, :cond_1

    .line 705
    invoke-static {p0}, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->shouldApplyExtendedPartnerConfig(Landroid/content/Context;)Z

    move-result p0
    :try_end_0
    .catch Ljava/lang/IllegalArgumentException; {:try_start_0 .. :try_end_0} :catch_0
    .catch Ljava/lang/SecurityException; {:try_start_0 .. :try_end_0} :catch_0

    return p0

    .line 714
    :cond_1
    sget-object p0, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->applyMaterialYouConfigBundle:Landroid/os/Bundle;

    if-eqz p0, :cond_2

    .line 715
    invoke-virtual {p0, v1, v2}, Landroid/os/Bundle;->getBoolean(Ljava/lang/String;Z)Z

    move-result p0

    if-eqz p0, :cond_2

    const/4 v2, 0x1

    :cond_2
    return v2

    .line 708
    :catch_0
    sget-object p0, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->TAG:Ljava/lang/String;

    const-string v1, "SetupWizard Material You configs supporting status unknown; return as false."

    invoke-static {p0, v1}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    .line 709
    sput-object v0, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->applyMaterialYouConfigBundle:Landroid/os/Bundle;

    return v2
.end method

.method private static unregisterContentObserver(Landroid/content/Context;)V
    .locals 3

    .line 846
    :try_start_0
    invoke-virtual {p0}, Landroid/content/Context;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object p0

    sget-object v0, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->contentObserver:Landroid/database/ContentObserver;

    invoke-virtual {p0, v0}, Landroid/content/ContentResolver;->unregisterContentObserver(Landroid/database/ContentObserver;)V

    const/4 p0, 0x0

    .line 847
    sput-object p0, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->contentObserver:Landroid/database/ContentObserver;
    :try_end_0
    .catch Ljava/lang/SecurityException; {:try_start_0 .. :try_end_0} :catch_0
    .catch Ljava/lang/NullPointerException; {:try_start_0 .. :try_end_0} :catch_0
    .catch Ljava/lang/IllegalArgumentException; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception p0

    .line 849
    sget-object v0, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->TAG:Ljava/lang/String;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "Failed to unregister content observer: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-static {v0, p0}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    :goto_0
    return-void
.end method


# virtual methods
.method adjustResourceEntryDefaultValue(Landroid/content/Context;Lcom/google/android/setupcompat/partnerconfig/ResourceEntry;)Lcom/google/android/setupcompat/partnerconfig/ResourceEntry;
    .locals 3

    .line 586
    invoke-static {}, Lcom/google/android/setupcompat/util/BuildCompatUtils;->isAtLeastT()Z

    move-result p0

    if-eqz p0, :cond_0

    invoke-static {p1}, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->shouldApplyMaterialYouStyle(Landroid/content/Context;)Z

    move-result p0

    if-eqz p0, :cond_0

    :try_start_0
    const-string p0, "com.google.android.setupwizard"

    .line 589
    invoke-virtual {p2}, Lcom/google/android/setupcompat/partnerconfig/ResourceEntry;->getPackageName()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p0, p1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_0

    .line 592
    invoke-virtual {p2}, Lcom/google/android/setupcompat/partnerconfig/ResourceEntry;->getResources()Landroid/content/res/Resources;

    move-result-object p0

    .line 593
    invoke-virtual {p2}, Lcom/google/android/setupcompat/partnerconfig/ResourceEntry;->getResourceId()I

    move-result p1

    invoke-virtual {p0, p1}, Landroid/content/res/Resources;->getResourceTypeName(I)Ljava/lang/String;

    move-result-object p0

    .line 596
    invoke-virtual {p2}, Lcom/google/android/setupcompat/partnerconfig/ResourceEntry;->getResourceName()Ljava/lang/String;

    move-result-object p1

    const-string v0, "_material_you"

    invoke-virtual {p1, v0}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    .line 599
    invoke-virtual {p2}, Lcom/google/android/setupcompat/partnerconfig/ResourceEntry;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    .line 603
    invoke-virtual {p2}, Lcom/google/android/setupcompat/partnerconfig/ResourceEntry;->getPackageName()Ljava/lang/String;

    move-result-object v1

    .line 600
    invoke-virtual {v0, p1, p0, v1}, Landroid/content/res/Resources;->getIdentifier(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I

    move-result p0

    if-eqz p0, :cond_0

    .line 605
    sget-object v0, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->TAG:Ljava/lang/String;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "use material you resource:"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    .line 606
    new-instance v0, Lcom/google/android/setupcompat/partnerconfig/ResourceEntry;

    .line 607
    invoke-virtual {p2}, Lcom/google/android/setupcompat/partnerconfig/ResourceEntry;->getPackageName()Ljava/lang/String;

    move-result-object v1

    .line 610
    invoke-virtual {p2}, Lcom/google/android/setupcompat/partnerconfig/ResourceEntry;->getResources()Landroid/content/res/Resources;

    move-result-object v2

    invoke-direct {v0, v1, p1, p0, v2}, Lcom/google/android/setupcompat/partnerconfig/ResourceEntry;-><init>(Ljava/lang/String;Ljava/lang/String;ILandroid/content/res/Resources;)V
    :try_end_0
    .catch Landroid/content/res/Resources$NotFoundException; {:try_start_0 .. :try_end_0} :catch_0

    return-object v0

    :catch_0
    :cond_0
    return-object p2
.end method

.method public getBoolean(Landroid/content/Context;Lcom/google/android/setupcompat/partnerconfig/PartnerConfig;Z)Z
    .locals 2

    .line 333
    invoke-virtual {p2}, Lcom/google/android/setupcompat/partnerconfig/PartnerConfig;->getResourceType()Lcom/google/android/setupcompat/partnerconfig/PartnerConfig$ResourceType;

    move-result-object v0

    sget-object v1, Lcom/google/android/setupcompat/partnerconfig/PartnerConfig$ResourceType;->BOOL:Lcom/google/android/setupcompat/partnerconfig/PartnerConfig$ResourceType;

    if-ne v0, v1, :cond_1

    .line 337
    iget-object v0, p0, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->partnerResourceCache:Ljava/util/EnumMap;

    invoke-virtual {v0, p2}, Ljava/util/EnumMap;->containsKey(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 338
    iget-object p0, p0, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->partnerResourceCache:Ljava/util/EnumMap;

    invoke-virtual {p0, p2}, Ljava/util/EnumMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/lang/Boolean;

    invoke-virtual {p0}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p0

    return p0

    .line 344
    :cond_0
    :try_start_0
    invoke-virtual {p2}, Lcom/google/android/setupcompat/partnerconfig/PartnerConfig;->getResourceName()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, p1, v0}, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->getResourceEntryFromKey(Landroid/content/Context;Ljava/lang/String;)Lcom/google/android/setupcompat/partnerconfig/ResourceEntry;

    move-result-object p1

    .line 345
    invoke-virtual {p1}, Lcom/google/android/setupcompat/partnerconfig/ResourceEntry;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    .line 346
    invoke-virtual {p1}, Lcom/google/android/setupcompat/partnerconfig/ResourceEntry;->getResourceId()I

    move-result p1

    .line 348
    invoke-virtual {v0, p1}, Landroid/content/res/Resources;->getBoolean(I)Z

    move-result p3

    .line 349
    iget-object p0, p0, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->partnerResourceCache:Ljava/util/EnumMap;

    invoke-static {p3}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object p1

    invoke-virtual {p0, p2, p1}, Ljava/util/EnumMap;->put(Ljava/lang/Enum;Ljava/lang/Object;)Ljava/lang/Object;
    :try_end_0
    .catch Ljava/lang/NullPointerException; {:try_start_0 .. :try_end_0} :catch_0
    .catch Landroid/content/res/Resources$NotFoundException; {:try_start_0 .. :try_end_0} :catch_0

    :catch_0
    return p3

    .line 334
    :cond_1
    new-instance p0, Ljava/lang/IllegalArgumentException;

    const-string p1, "Not a bool resource"

    invoke-direct {p0, p1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw p0
.end method

.method public getColor(Landroid/content/Context;Lcom/google/android/setupcompat/partnerconfig/PartnerConfig;)I
    .locals 5

    .line 179
    invoke-virtual {p2}, Lcom/google/android/setupcompat/partnerconfig/PartnerConfig;->getResourceType()Lcom/google/android/setupcompat/partnerconfig/PartnerConfig$ResourceType;

    move-result-object v0

    sget-object v1, Lcom/google/android/setupcompat/partnerconfig/PartnerConfig$ResourceType;->COLOR:Lcom/google/android/setupcompat/partnerconfig/PartnerConfig$ResourceType;

    if-ne v0, v1, :cond_2

    .line 183
    iget-object v0, p0, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->partnerResourceCache:Ljava/util/EnumMap;

    invoke-virtual {v0, p2}, Ljava/util/EnumMap;->containsKey(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 184
    iget-object p0, p0, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->partnerResourceCache:Ljava/util/EnumMap;

    invoke-virtual {p0, p2}, Ljava/util/EnumMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/lang/Integer;

    invoke-virtual {p0}, Ljava/lang/Integer;->intValue()I

    move-result p0

    return p0

    :cond_0
    const/4 v0, 0x0

    .line 190
    :try_start_0
    invoke-virtual {p2}, Lcom/google/android/setupcompat/partnerconfig/PartnerConfig;->getResourceName()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {p0, p1, v1}, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->getResourceEntryFromKey(Landroid/content/Context;Ljava/lang/String;)Lcom/google/android/setupcompat/partnerconfig/ResourceEntry;

    move-result-object p1

    .line 191
    invoke-virtual {p1}, Lcom/google/android/setupcompat/partnerconfig/ResourceEntry;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    .line 192
    invoke-virtual {p1}, Lcom/google/android/setupcompat/partnerconfig/ResourceEntry;->getResourceId()I

    move-result p1

    .line 195
    new-instance v2, Landroid/util/TypedValue;

    invoke-direct {v2}, Landroid/util/TypedValue;-><init>()V

    const/4 v3, 0x1

    .line 196
    invoke-virtual {v1, p1, v2, v3}, Landroid/content/res/Resources;->getValue(ILandroid/util/TypedValue;Z)V

    .line 197
    iget v4, v2, Landroid/util/TypedValue;->type:I

    if-ne v4, v3, :cond_1

    iget v2, v2, Landroid/util/TypedValue;->data:I

    if-nez v2, :cond_1

    return v0

    :cond_1
    const/4 v2, 0x0

    .line 202
    invoke-virtual {v1, p1, v2}, Landroid/content/res/Resources;->getColor(ILandroid/content/res/Resources$Theme;)I

    move-result v0

    .line 206
    iget-object p0, p0, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->partnerResourceCache:Ljava/util/EnumMap;

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    invoke-virtual {p0, p2, p1}, Ljava/util/EnumMap;->put(Ljava/lang/Enum;Ljava/lang/Object;)Ljava/lang/Object;
    :try_end_0
    .catch Ljava/lang/NullPointerException; {:try_start_0 .. :try_end_0} :catch_0

    :catch_0
    return v0

    .line 180
    :cond_2
    new-instance p0, Ljava/lang/IllegalArgumentException;

    const-string p1, "Not a color resource"

    invoke-direct {p0, p1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw p0
.end method

.method public getDimension(Landroid/content/Context;Lcom/google/android/setupcompat/partnerconfig/PartnerConfig;)F
    .locals 1

    const/4 v0, 0x0

    .line 363
    invoke-virtual {p0, p1, p2, v0}, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->getDimension(Landroid/content/Context;Lcom/google/android/setupcompat/partnerconfig/PartnerConfig;F)F

    move-result p0

    return p0
.end method

.method public getDimension(Landroid/content/Context;Lcom/google/android/setupcompat/partnerconfig/PartnerConfig;F)F
    .locals 3

    .line 377
    invoke-virtual {p2}, Lcom/google/android/setupcompat/partnerconfig/PartnerConfig;->getResourceType()Lcom/google/android/setupcompat/partnerconfig/PartnerConfig$ResourceType;

    move-result-object v0

    sget-object v1, Lcom/google/android/setupcompat/partnerconfig/PartnerConfig$ResourceType;->DIMENSION:Lcom/google/android/setupcompat/partnerconfig/PartnerConfig$ResourceType;

    if-ne v0, v1, :cond_1

    .line 381
    iget-object v0, p0, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->partnerResourceCache:Ljava/util/EnumMap;

    invoke-virtual {v0, p2}, Ljava/util/EnumMap;->containsKey(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 382
    iget-object p0, p0, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->partnerResourceCache:Ljava/util/EnumMap;

    .line 383
    invoke-virtual {p0, p2}, Ljava/util/EnumMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Landroid/util/TypedValue;

    .line 382
    invoke-static {p1, p0}, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->getDimensionFromTypedValue(Landroid/content/Context;Landroid/util/TypedValue;)F

    move-result p0

    return p0

    .line 389
    :cond_0
    :try_start_0
    invoke-virtual {p2}, Lcom/google/android/setupcompat/partnerconfig/PartnerConfig;->getResourceName()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, p1, v0}, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->getResourceEntryFromKey(Landroid/content/Context;Ljava/lang/String;)Lcom/google/android/setupcompat/partnerconfig/ResourceEntry;

    move-result-object v0

    .line 390
    invoke-virtual {v0}, Lcom/google/android/setupcompat/partnerconfig/ResourceEntry;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    .line 391
    invoke-virtual {v0}, Lcom/google/android/setupcompat/partnerconfig/ResourceEntry;->getResourceId()I

    move-result v0

    .line 393
    invoke-virtual {v1, v0}, Landroid/content/res/Resources;->getDimension(I)F

    move-result p3

    const/4 v2, 0x5

    .line 394
    invoke-static {v1, v0, v2}, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->getTypedValueFromResource(Landroid/content/res/Resources;II)Landroid/util/TypedValue;

    move-result-object v0

    .line 395
    iget-object v1, p0, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->partnerResourceCache:Ljava/util/EnumMap;

    invoke-virtual {v1, p2, v0}, Ljava/util/EnumMap;->put(Ljava/lang/Enum;Ljava/lang/Object;)Ljava/lang/Object;

    .line 396
    iget-object p0, p0, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->partnerResourceCache:Ljava/util/EnumMap;

    .line 398
    invoke-virtual {p0, p2}, Ljava/util/EnumMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Landroid/util/TypedValue;

    .line 397
    invoke-static {p1, p0}, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->getDimensionFromTypedValue(Landroid/content/Context;Landroid/util/TypedValue;)F

    move-result p0
    :try_end_0
    .catch Ljava/lang/NullPointerException; {:try_start_0 .. :try_end_0} :catch_0
    .catch Landroid/content/res/Resources$NotFoundException; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move p0, p3

    :goto_0
    return p0

    .line 378
    :cond_1
    new-instance p0, Ljava/lang/IllegalArgumentException;

    const-string p1, "Not a dimension resource"

    invoke-direct {p0, p1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw p0
.end method

.method public getDrawable(Landroid/content/Context;Lcom/google/android/setupcompat/partnerconfig/PartnerConfig;)Landroid/graphics/drawable/Drawable;
    .locals 5

    .line 223
    invoke-virtual {p2}, Lcom/google/android/setupcompat/partnerconfig/PartnerConfig;->getResourceType()Lcom/google/android/setupcompat/partnerconfig/PartnerConfig$ResourceType;

    move-result-object v0

    sget-object v1, Lcom/google/android/setupcompat/partnerconfig/PartnerConfig$ResourceType;->DRAWABLE:Lcom/google/android/setupcompat/partnerconfig/PartnerConfig$ResourceType;

    if-ne v0, v1, :cond_2

    .line 227
    iget-object v0, p0, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->partnerResourceCache:Ljava/util/EnumMap;

    invoke-virtual {v0, p2}, Ljava/util/EnumMap;->containsKey(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 228
    iget-object p0, p0, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->partnerResourceCache:Ljava/util/EnumMap;

    invoke-virtual {p0, p2}, Ljava/util/EnumMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Landroid/graphics/drawable/Drawable;

    return-object p0

    :cond_0
    const/4 v0, 0x0

    .line 234
    :try_start_0
    invoke-virtual {p2}, Lcom/google/android/setupcompat/partnerconfig/PartnerConfig;->getResourceName()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {p0, p1, v1}, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->getResourceEntryFromKey(Landroid/content/Context;Ljava/lang/String;)Lcom/google/android/setupcompat/partnerconfig/ResourceEntry;

    move-result-object p1

    .line 235
    invoke-virtual {p1}, Lcom/google/android/setupcompat/partnerconfig/ResourceEntry;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    .line 236
    invoke-virtual {p1}, Lcom/google/android/setupcompat/partnerconfig/ResourceEntry;->getResourceId()I

    move-result p1

    .line 239
    new-instance v2, Landroid/util/TypedValue;

    invoke-direct {v2}, Landroid/util/TypedValue;-><init>()V

    const/4 v3, 0x1

    .line 240
    invoke-virtual {v1, p1, v2, v3}, Landroid/content/res/Resources;->getValue(ILandroid/util/TypedValue;Z)V

    .line 241
    iget v4, v2, Landroid/util/TypedValue;->type:I

    if-ne v4, v3, :cond_1

    iget v2, v2, Landroid/util/TypedValue;->data:I

    if-nez v2, :cond_1

    return-object v0

    .line 246
    :cond_1
    invoke-virtual {v1, p1, v0}, Landroid/content/res/Resources;->getDrawable(ILandroid/content/res/Resources$Theme;)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    .line 250
    iget-object p0, p0, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->partnerResourceCache:Ljava/util/EnumMap;

    invoke-virtual {p0, p2, v0}, Ljava/util/EnumMap;->put(Ljava/lang/Enum;Ljava/lang/Object;)Ljava/lang/Object;
    :try_end_0
    .catch Ljava/lang/NullPointerException; {:try_start_0 .. :try_end_0} :catch_0
    .catch Landroid/content/res/Resources$NotFoundException; {:try_start_0 .. :try_end_0} :catch_0

    :catch_0
    return-object v0

    .line 224
    :cond_2
    new-instance p0, Ljava/lang/IllegalArgumentException;

    const-string p1, "Not a drawable resource"

    invoke-direct {p0, p1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw p0
.end method

.method public getFraction(Landroid/content/Context;Lcom/google/android/setupcompat/partnerconfig/PartnerConfig;)F
    .locals 1

    const/4 v0, 0x0

    .line 412
    invoke-virtual {p0, p1, p2, v0}, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->getFraction(Landroid/content/Context;Lcom/google/android/setupcompat/partnerconfig/PartnerConfig;F)F

    move-result p0

    return p0
.end method

.method public getFraction(Landroid/content/Context;Lcom/google/android/setupcompat/partnerconfig/PartnerConfig;F)F
    .locals 2

    .line 426
    invoke-virtual {p2}, Lcom/google/android/setupcompat/partnerconfig/PartnerConfig;->getResourceType()Lcom/google/android/setupcompat/partnerconfig/PartnerConfig$ResourceType;

    move-result-object v0

    sget-object v1, Lcom/google/android/setupcompat/partnerconfig/PartnerConfig$ResourceType;->FRACTION:Lcom/google/android/setupcompat/partnerconfig/PartnerConfig$ResourceType;

    if-ne v0, v1, :cond_1

    .line 430
    iget-object v0, p0, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->partnerResourceCache:Ljava/util/EnumMap;

    invoke-virtual {v0, p2}, Ljava/util/EnumMap;->containsKey(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 431
    iget-object p0, p0, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->partnerResourceCache:Ljava/util/EnumMap;

    invoke-virtual {p0, p2}, Ljava/util/EnumMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/lang/Float;

    invoke-virtual {p0}, Ljava/lang/Float;->floatValue()F

    move-result p0

    return p0

    .line 437
    :cond_0
    :try_start_0
    invoke-virtual {p2}, Lcom/google/android/setupcompat/partnerconfig/PartnerConfig;->getResourceName()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, p1, v0}, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->getResourceEntryFromKey(Landroid/content/Context;Ljava/lang/String;)Lcom/google/android/setupcompat/partnerconfig/ResourceEntry;

    move-result-object p1

    .line 438
    invoke-virtual {p1}, Lcom/google/android/setupcompat/partnerconfig/ResourceEntry;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    .line 439
    invoke-virtual {p1}, Lcom/google/android/setupcompat/partnerconfig/ResourceEntry;->getResourceId()I

    move-result p1

    const/4 v1, 0x1

    .line 441
    invoke-virtual {v0, p1, v1, v1}, Landroid/content/res/Resources;->getFraction(III)F

    move-result p3

    .line 442
    iget-object p0, p0, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->partnerResourceCache:Ljava/util/EnumMap;

    invoke-static {p3}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object p1

    invoke-virtual {p0, p2, p1}, Ljava/util/EnumMap;->put(Ljava/lang/Enum;Ljava/lang/Object;)Ljava/lang/Object;
    :try_end_0
    .catch Ljava/lang/NullPointerException; {:try_start_0 .. :try_end_0} :catch_0
    .catch Landroid/content/res/Resources$NotFoundException; {:try_start_0 .. :try_end_0} :catch_0

    :catch_0
    return p3

    .line 427
    :cond_1
    new-instance p0, Ljava/lang/IllegalArgumentException;

    const-string p1, "Not a fraction resource"

    invoke-direct {p0, p1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw p0
.end method

.method public getInteger(Landroid/content/Context;Lcom/google/android/setupcompat/partnerconfig/PartnerConfig;I)I
    .locals 2

    .line 459
    invoke-virtual {p2}, Lcom/google/android/setupcompat/partnerconfig/PartnerConfig;->getResourceType()Lcom/google/android/setupcompat/partnerconfig/PartnerConfig$ResourceType;

    move-result-object v0

    sget-object v1, Lcom/google/android/setupcompat/partnerconfig/PartnerConfig$ResourceType;->INTEGER:Lcom/google/android/setupcompat/partnerconfig/PartnerConfig$ResourceType;

    if-ne v0, v1, :cond_1

    .line 463
    iget-object v0, p0, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->partnerResourceCache:Ljava/util/EnumMap;

    invoke-virtual {v0, p2}, Ljava/util/EnumMap;->containsKey(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 464
    iget-object p0, p0, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->partnerResourceCache:Ljava/util/EnumMap;

    invoke-virtual {p0, p2}, Ljava/util/EnumMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/lang/Integer;

    invoke-virtual {p0}, Ljava/lang/Integer;->intValue()I

    move-result p0

    return p0

    .line 470
    :cond_0
    :try_start_0
    invoke-virtual {p2}, Lcom/google/android/setupcompat/partnerconfig/PartnerConfig;->getResourceName()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, p1, v0}, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->getResourceEntryFromKey(Landroid/content/Context;Ljava/lang/String;)Lcom/google/android/setupcompat/partnerconfig/ResourceEntry;

    move-result-object p1

    .line 471
    invoke-virtual {p1}, Lcom/google/android/setupcompat/partnerconfig/ResourceEntry;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    .line 472
    invoke-virtual {p1}, Lcom/google/android/setupcompat/partnerconfig/ResourceEntry;->getResourceId()I

    move-result p1

    .line 474
    invoke-virtual {v0, p1}, Landroid/content/res/Resources;->getInteger(I)I

    move-result p3

    .line 475
    iget-object p0, p0, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->partnerResourceCache:Ljava/util/EnumMap;

    invoke-static {p3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    invoke-virtual {p0, p2, p1}, Ljava/util/EnumMap;->put(Ljava/lang/Enum;Ljava/lang/Object;)Ljava/lang/Object;
    :try_end_0
    .catch Ljava/lang/NullPointerException; {:try_start_0 .. :try_end_0} :catch_0
    .catch Landroid/content/res/Resources$NotFoundException; {:try_start_0 .. :try_end_0} :catch_0

    :catch_0
    return p3

    .line 460
    :cond_1
    new-instance p0, Ljava/lang/IllegalArgumentException;

    const-string p1, "Not a integer resource"

    invoke-direct {p0, p1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw p0
.end method

.method getResourceEntryFromKey(Landroid/content/Context;Ljava/lang/String;)Lcom/google/android/setupcompat/partnerconfig/ResourceEntry;
    .locals 3

    .line 549
    iget-object v0, p0, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->resultBundle:Landroid/os/Bundle;

    invoke-virtual {v0, p2}, Landroid/os/Bundle;->getBundle(Ljava/lang/String;)Landroid/os/Bundle;

    move-result-object v0

    .line 550
    iget-object v1, p0, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->resultBundle:Landroid/os/Bundle;

    const-string v2, "fallbackConfig"

    invoke-virtual {v1, v2}, Landroid/os/Bundle;->getBundle(Ljava/lang/String;)Landroid/os/Bundle;

    move-result-object v1

    if-eqz v1, :cond_0

    .line 552
    invoke-virtual {v1, p2}, Landroid/os/Bundle;->getBundle(Ljava/lang/String;)Landroid/os/Bundle;

    move-result-object p2

    invoke-virtual {v0, v2, p2}, Landroid/os/Bundle;->putBundle(Ljava/lang/String;Landroid/os/Bundle;)V

    .line 556
    :cond_0
    invoke-static {p1, v0}, Lcom/google/android/setupcompat/partnerconfig/ResourceEntry;->fromBundle(Landroid/content/Context;Landroid/os/Bundle;)Lcom/google/android/setupcompat/partnerconfig/ResourceEntry;

    move-result-object p2

    .line 555
    invoke-virtual {p0, p1, p2}, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->adjustResourceEntryDefaultValue(Landroid/content/Context;Lcom/google/android/setupcompat/partnerconfig/ResourceEntry;)Lcom/google/android/setupcompat/partnerconfig/ResourceEntry;

    move-result-object p0

    .line 557
    invoke-static {p1, p0}, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->adjustResourceEntryDayNightMode(Landroid/content/Context;Lcom/google/android/setupcompat/partnerconfig/ResourceEntry;)Lcom/google/android/setupcompat/partnerconfig/ResourceEntry;

    move-result-object p0

    return-object p0
.end method

.method public getString(Landroid/content/Context;Lcom/google/android/setupcompat/partnerconfig/PartnerConfig;)Ljava/lang/String;
    .locals 2

    .line 267
    invoke-virtual {p2}, Lcom/google/android/setupcompat/partnerconfig/PartnerConfig;->getResourceType()Lcom/google/android/setupcompat/partnerconfig/PartnerConfig$ResourceType;

    move-result-object v0

    sget-object v1, Lcom/google/android/setupcompat/partnerconfig/PartnerConfig$ResourceType;->STRING:Lcom/google/android/setupcompat/partnerconfig/PartnerConfig$ResourceType;

    if-ne v0, v1, :cond_1

    .line 271
    iget-object v0, p0, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->partnerResourceCache:Ljava/util/EnumMap;

    invoke-virtual {v0, p2}, Ljava/util/EnumMap;->containsKey(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 272
    iget-object p0, p0, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->partnerResourceCache:Ljava/util/EnumMap;

    invoke-virtual {p0, p2}, Ljava/util/EnumMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/lang/String;

    return-object p0

    :cond_0
    const/4 v0, 0x0

    .line 278
    :try_start_0
    invoke-virtual {p2}, Lcom/google/android/setupcompat/partnerconfig/PartnerConfig;->getResourceName()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {p0, p1, v1}, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->getResourceEntryFromKey(Landroid/content/Context;Ljava/lang/String;)Lcom/google/android/setupcompat/partnerconfig/ResourceEntry;

    move-result-object p1

    .line 279
    invoke-virtual {p1}, Lcom/google/android/setupcompat/partnerconfig/ResourceEntry;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    .line 280
    invoke-virtual {p1}, Lcom/google/android/setupcompat/partnerconfig/ResourceEntry;->getResourceId()I

    move-result p1

    .line 282
    invoke-virtual {v1, p1}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object v0

    .line 283
    iget-object p0, p0, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->partnerResourceCache:Ljava/util/EnumMap;

    invoke-virtual {p0, p2, v0}, Ljava/util/EnumMap;->put(Ljava/lang/Enum;Ljava/lang/Object;)Ljava/lang/Object;
    :try_end_0
    .catch Ljava/lang/NullPointerException; {:try_start_0 .. :try_end_0} :catch_0

    :catch_0
    return-object v0

    .line 268
    :cond_1
    new-instance p0, Ljava/lang/IllegalArgumentException;

    const-string p1, "Not a string resource"

    invoke-direct {p0, p1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw p0
.end method

.method public isAvailable()Z
    .locals 0

    .line 156
    iget-object p0, p0, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->resultBundle:Landroid/os/Bundle;

    if-eqz p0, :cond_0

    invoke-virtual {p0}, Landroid/os/Bundle;->isEmpty()Z

    move-result p0

    if-nez p0, :cond_0

    const/4 p0, 0x1

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    :goto_0
    return p0
.end method

.method public isPartnerConfigAvailable(Lcom/google/android/setupcompat/partnerconfig/PartnerConfig;)Z
    .locals 1

    .line 166
    invoke-virtual {p0}, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->isAvailable()Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object p0, p0, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->resultBundle:Landroid/os/Bundle;

    invoke-virtual {p1}, Lcom/google/android/setupcompat/partnerconfig/PartnerConfig;->getResourceName()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p0, p1}, Landroid/os/Bundle;->containsKey(Ljava/lang/String;)Z

    move-result p0

    if-eqz p0, :cond_0

    const/4 p0, 0x1

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    :goto_0
    return p0
.end method
