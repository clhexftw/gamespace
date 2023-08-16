.class public Lorg/nameless/custom/preference/ConstraintsHelper;
.super Ljava/lang/Object;
.source "ConstraintsHelper.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lorg/nameless/custom/preference/ConstraintsHelper$Graveyard;
    }
.end annotation


# static fields
.field private static final DEBUG:Z


# instance fields
.field private final mAttrs:Landroid/util/AttributeSet;

.field private mAvailable:Z

.field private final mContext:Landroid/content/Context;

.field private final mPref:Landroidx/preference/Preference;

.field private mReplacesKey:[Ljava/lang/String;

.field private mSummaryMinLines:I

.field private mVerifyIntent:Z


# direct methods
.method static constructor <clinit>()V
    .locals 2

    const-string v0, "ConstraintsHelper"

    const/4 v1, 0x2

    .line 58
    invoke-static {v0, v1}, Landroid/util/Log;->isLoggable(Ljava/lang/String;I)Z

    move-result v0

    sput-boolean v0, Lorg/nameless/custom/preference/ConstraintsHelper;->DEBUG:Z

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;Landroidx/preference/Preference;)V
    .locals 2

    .line 74
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const/4 v0, 0x1

    .line 66
    iput-boolean v0, p0, Lorg/nameless/custom/preference/ConstraintsHelper;->mAvailable:Z

    .line 68
    iput-boolean v0, p0, Lorg/nameless/custom/preference/ConstraintsHelper;->mVerifyIntent:Z

    const/4 v0, -0x1

    .line 70
    iput v0, p0, Lorg/nameless/custom/preference/ConstraintsHelper;->mSummaryMinLines:I

    const/4 v1, 0x0

    .line 72
    iput-object v1, p0, Lorg/nameless/custom/preference/ConstraintsHelper;->mReplacesKey:[Ljava/lang/String;

    .line 75
    iput-object p1, p0, Lorg/nameless/custom/preference/ConstraintsHelper;->mContext:Landroid/content/Context;

    .line 76
    iput-object p2, p0, Lorg/nameless/custom/preference/ConstraintsHelper;->mAttrs:Landroid/util/AttributeSet;

    .line 77
    iput-object p3, p0, Lorg/nameless/custom/preference/ConstraintsHelper;->mPref:Landroidx/preference/Preference;

    .line 79
    invoke-virtual {p1}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object p1

    sget-object v1, Lorg/nameless/custom/R$styleable;->lineage_SelfRemovingPreference:[I

    invoke-virtual {p1, p2, v1}, Landroid/content/res/Resources;->obtainAttributes(Landroid/util/AttributeSet;[I)Landroid/content/res/TypedArray;

    move-result-object p1

    .line 81
    sget p2, Lorg/nameless/custom/R$styleable;->lineage_SelfRemovingPreference_minSummaryLines:I

    invoke-virtual {p1, p2, v0}, Landroid/content/res/TypedArray;->getInteger(II)I

    move-result p2

    iput p2, p0, Lorg/nameless/custom/preference/ConstraintsHelper;->mSummaryMinLines:I

    .line 82
    sget p2, Lorg/nameless/custom/R$styleable;->lineage_SelfRemovingPreference_replacesKey:I

    invoke-virtual {p1, p2}, Landroid/content/res/TypedArray;->getString(I)Ljava/lang/String;

    move-result-object p1

    if-eqz p1, :cond_0

    const-string p2, "\\|"

    .line 84
    invoke-virtual {p1, p2}, Ljava/lang/String;->split(Ljava/lang/String;)[Ljava/lang/String;

    move-result-object p1

    iput-object p1, p0, Lorg/nameless/custom/preference/ConstraintsHelper;->mReplacesKey:[Ljava/lang/String;

    .line 86
    :cond_0
    invoke-direct {p0}, Lorg/nameless/custom/preference/ConstraintsHelper;->checkConstraints()Z

    move-result p1

    invoke-virtual {p0, p1}, Lorg/nameless/custom/preference/ConstraintsHelper;->setAvailable(Z)V

    .line 88
    new-instance p1, Ljava/lang/StringBuilder;

    invoke-direct {p1}, Ljava/lang/StringBuilder;-><init>()V

    const-string p2, "construct key="

    invoke-virtual {p1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p3}, Landroidx/preference/Preference;->getKey()Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p2, " available="

    invoke-virtual {p1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-boolean p0, p0, Lorg/nameless/custom/preference/ConstraintsHelper;->mAvailable:Z

    invoke-virtual {p1, p0}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    const-string p1, "ConstraintsHelper"

    invoke-static {p1, p0}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    return-void
.end method

.method private checkConstraints()Z
    .locals 6

    .line 139
    iget-object v0, p0, Lorg/nameless/custom/preference/ConstraintsHelper;->mAttrs:Landroid/util/AttributeSet;

    const/4 v1, 0x1

    if-nez v0, :cond_0

    return v1

    .line 143
    :cond_0
    iget-object v0, p0, Lorg/nameless/custom/preference/ConstraintsHelper;->mContext:Landroid/content/Context;

    invoke-virtual {v0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    iget-object v2, p0, Lorg/nameless/custom/preference/ConstraintsHelper;->mAttrs:Landroid/util/AttributeSet;

    sget-object v3, Lorg/nameless/custom/R$styleable;->lineage_SelfRemovingPreference:[I

    invoke-virtual {v0, v2, v3}, Landroid/content/res/Resources;->obtainAttributes(Landroid/util/AttributeSet;[I)Landroid/content/res/TypedArray;

    move-result-object v0

    .line 149
    :try_start_0
    sget v2, Lorg/nameless/custom/R$styleable;->lineage_SelfRemovingPreference_requiresOwner:I

    const/4 v3, 0x0

    invoke-virtual {v0, v2, v3}, Landroid/content/res/TypedArray;->getBoolean(IZ)Z

    move-result v2

    if-eqz v2, :cond_1

    .line 150
    invoke-static {}, Landroid/os/UserHandle;->myUserId()I

    move-result v2
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    if-eqz v2, :cond_1

    .line 233
    invoke-virtual {v0}, Landroid/content/res/TypedArray;->recycle()V

    return v3

    .line 155
    :cond_1
    :try_start_1
    sget v2, Lorg/nameless/custom/R$styleable;->lineage_SelfRemovingPreference_requiresPackage:I

    invoke-virtual {v0, v2}, Landroid/content/res/TypedArray;->getString(I)Ljava/lang/String;

    move-result-object v2

    if-eqz v2, :cond_3

    .line 157
    invoke-direct {p0, v2}, Lorg/nameless/custom/preference/ConstraintsHelper;->isNegated(Ljava/lang/String;)Z

    move-result v4

    if-eqz v4, :cond_2

    .line 159
    invoke-virtual {v2, v1}, Ljava/lang/String;->substring(I)Ljava/lang/String;

    move-result-object v2

    .line 161
    :cond_2
    iget-object v5, p0, Lorg/nameless/custom/preference/ConstraintsHelper;->mContext:Landroid/content/Context;

    invoke-static {v5, v2, v3}, Lorg/nameless/custom/preference/ConstraintsHelper;->isPackageInstalled(Landroid/content/Context;Ljava/lang/String;Z)Z

    move-result v2
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    if-ne v2, v4, :cond_3

    .line 233
    invoke-virtual {v0}, Landroid/content/res/TypedArray;->recycle()V

    return v3

    .line 168
    :cond_3
    :try_start_2
    sget v2, Lorg/nameless/custom/R$styleable;->lineage_SelfRemovingPreference_requiresAction:I

    invoke-virtual {v0, v2}, Landroid/content/res/TypedArray;->getString(I)Ljava/lang/String;

    move-result-object v2

    if-eqz v2, :cond_5

    .line 170
    invoke-direct {p0, v2}, Lorg/nameless/custom/preference/ConstraintsHelper;->isNegated(Ljava/lang/String;)Z

    move-result v4

    if-eqz v4, :cond_4

    .line 172
    invoke-virtual {v2, v1}, Ljava/lang/String;->substring(I)Ljava/lang/String;

    move-result-object v2

    .line 174
    :cond_4
    iget-object v5, p0, Lorg/nameless/custom/preference/ConstraintsHelper;->mContext:Landroid/content/Context;

    invoke-static {v5, v2}, Lorg/nameless/custom/preference/ConstraintsHelper;->resolveIntent(Landroid/content/Context;Ljava/lang/String;)Z

    move-result v2
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    if-ne v2, v4, :cond_5

    .line 233
    invoke-virtual {v0}, Landroid/content/res/TypedArray;->recycle()V

    return v3

    .line 181
    :cond_5
    :try_start_3
    sget v2, Lorg/nameless/custom/R$styleable;->lineage_SelfRemovingPreference_requiresFeature:I

    invoke-virtual {v0, v2}, Landroid/content/res/TypedArray;->getString(I)Ljava/lang/String;

    move-result-object v2

    if-eqz v2, :cond_8

    .line 183
    invoke-direct {p0, v2}, Lorg/nameless/custom/preference/ConstraintsHelper;->isNegated(Ljava/lang/String;)Z

    move-result v4

    if-eqz v4, :cond_6

    .line 185
    invoke-virtual {v2, v1}, Ljava/lang/String;->substring(I)Ljava/lang/String;

    move-result-object v2

    :cond_6
    const-string v5, "lineagehardware:"

    .line 187
    invoke-virtual {v2, v5}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v5

    if-eqz v5, :cond_7

    move v2, v3

    goto :goto_0

    .line 190
    :cond_7
    iget-object v5, p0, Lorg/nameless/custom/preference/ConstraintsHelper;->mContext:Landroid/content/Context;

    invoke-static {v5, v2}, Lorg/nameless/custom/preference/ConstraintsHelper;->hasSystemFeature(Landroid/content/Context;Ljava/lang/String;)Z

    move-result v2
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_0

    :goto_0
    if-ne v2, v4, :cond_8

    .line 233
    invoke-virtual {v0}, Landroid/content/res/TypedArray;->recycle()V

    return v3

    .line 197
    :cond_8
    :try_start_4
    sget v2, Lorg/nameless/custom/R$styleable;->lineage_SelfRemovingPreference_requiresProperty:I

    invoke-virtual {v0, v2}, Landroid/content/res/TypedArray;->getString(I)Ljava/lang/String;

    move-result-object v2

    if-eqz v2, :cond_b

    .line 199
    invoke-direct {p0, v2}, Lorg/nameless/custom/preference/ConstraintsHelper;->isNegated(Ljava/lang/String;)Z

    move-result v4

    if-eqz v4, :cond_9

    .line 201
    invoke-virtual {v2, v1}, Ljava/lang/String;->substring(I)Ljava/lang/String;

    move-result-object v2

    .line 203
    :cond_9
    invoke-static {v2}, Landroid/os/SystemProperties;->get(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    if-eqz v2, :cond_a

    .line 204
    invoke-static {v2}, Ljava/lang/Boolean;->parseBoolean(Ljava/lang/String;)Z

    move-result v2
    :try_end_4
    .catchall {:try_start_4 .. :try_end_4} :catchall_0

    if-eqz v2, :cond_a

    move v2, v1

    goto :goto_1

    :cond_a
    move v2, v3

    :goto_1
    if-ne v2, v4, :cond_b

    .line 233
    invoke-virtual {v0}, Landroid/content/res/TypedArray;->recycle()V

    return v3

    .line 217
    :cond_b
    :try_start_5
    sget v2, Lorg/nameless/custom/R$styleable;->lineage_SelfRemovingPreference_requiresConfig:I

    invoke-virtual {v0, v2}, Landroid/content/res/TypedArray;->peekValue(I)Landroid/util/TypedValue;

    move-result-object v2

    if-eqz v2, :cond_f

    .line 218
    iget v4, v2, Landroid/util/TypedValue;->resourceId:I

    if-eqz v4, :cond_f

    .line 219
    iget v4, v2, Landroid/util/TypedValue;->type:I

    const/4 v5, 0x3

    if-ne v4, v5, :cond_c

    iget-object p0, p0, Lorg/nameless/custom/preference/ConstraintsHelper;->mContext:Landroid/content/Context;

    .line 220
    invoke-virtual {p0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object p0

    iget v4, v2, Landroid/util/TypedValue;->resourceId:I

    invoke-virtual {p0, v4}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object p0
    :try_end_5
    .catchall {:try_start_5 .. :try_end_5} :catchall_0

    if-nez p0, :cond_c

    .line 233
    invoke-virtual {v0}, Landroid/content/res/TypedArray;->recycle()V

    return v3

    .line 222
    :cond_c
    :try_start_6
    iget p0, v2, Landroid/util/TypedValue;->type:I

    const/16 v4, 0x12

    if-ne p0, v4, :cond_d

    iget v4, v2, Landroid/util/TypedValue;->data:I
    :try_end_6
    .catchall {:try_start_6 .. :try_end_6} :catchall_0

    if-nez v4, :cond_d

    .line 233
    invoke-virtual {v0}, Landroid/content/res/TypedArray;->recycle()V

    return v3

    :cond_d
    const/16 v4, 0x10

    if-ne p0, v4, :cond_f

    .line 225
    :try_start_7
    sget p0, Lorg/nameless/custom/R$styleable;->lineage_SelfRemovingPreference_requiresConfigMask:I

    const/4 v4, -0x1

    invoke-virtual {v0, p0, v4}, Landroid/content/res/TypedArray;->getInt(II)I

    move-result p0

    .line 227
    iget v2, v2, Landroid/util/TypedValue;->data:I
    :try_end_7
    .catchall {:try_start_7 .. :try_end_7} :catchall_0

    if-eqz v2, :cond_e

    if-ltz p0, :cond_f

    and-int/2addr p0, v2

    if-nez p0, :cond_f

    .line 233
    :cond_e
    invoke-virtual {v0}, Landroid/content/res/TypedArray;->recycle()V

    return v3

    :cond_f
    invoke-virtual {v0}, Landroid/content/res/TypedArray;->recycle()V

    return v1

    :catchall_0
    move-exception p0

    invoke-virtual {v0}, Landroid/content/res/TypedArray;->recycle()V

    .line 234
    throw p0
.end method

.method private checkIntent()V
    .locals 2

    .line 129
    iget-object v0, p0, Lorg/nameless/custom/preference/ConstraintsHelper;->mPref:Landroidx/preference/Preference;

    invoke-virtual {v0}, Landroidx/preference/Preference;->getIntent()Landroid/content/Intent;

    move-result-object v0

    if-eqz v0, :cond_0

    .line 131
    iget-object v1, p0, Lorg/nameless/custom/preference/ConstraintsHelper;->mContext:Landroid/content/Context;

    invoke-static {v1, v0}, Lorg/nameless/custom/preference/ConstraintsHelper;->resolveIntent(Landroid/content/Context;Landroid/content/Intent;)Z

    move-result v0

    if-nez v0, :cond_0

    .line 132
    iget-object v0, p0, Lorg/nameless/custom/preference/ConstraintsHelper;->mContext:Landroid/content/Context;

    invoke-static {v0}, Lorg/nameless/custom/preference/ConstraintsHelper$Graveyard;->get(Landroid/content/Context;)Lorg/nameless/custom/preference/ConstraintsHelper$Graveyard;

    move-result-object v0

    iget-object v1, p0, Lorg/nameless/custom/preference/ConstraintsHelper;->mPref:Landroidx/preference/Preference;

    invoke-virtual {v1}, Landroidx/preference/Preference;->getKey()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Lorg/nameless/custom/preference/ConstraintsHelper$Graveyard;->addTombstone(Ljava/lang/String;)V

    const/4 v0, 0x0

    .line 133
    iput-boolean v0, p0, Lorg/nameless/custom/preference/ConstraintsHelper;->mAvailable:Z

    :cond_0
    return-void
.end method

.method public static getAttr(Landroid/content/Context;II)I
    .locals 2

    .line 301
    new-instance v0, Landroid/util/TypedValue;

    invoke-direct {v0}, Landroid/util/TypedValue;-><init>()V

    .line 302
    invoke-virtual {p0}, Landroid/content/Context;->getTheme()Landroid/content/res/Resources$Theme;

    move-result-object p0

    const/4 v1, 0x1

    invoke-virtual {p0, p1, v0, v1}, Landroid/content/res/Resources$Theme;->resolveAttribute(ILandroid/util/TypedValue;Z)Z

    .line 303
    iget p0, v0, Landroid/util/TypedValue;->resourceId:I

    if-eqz p0, :cond_0

    return p1

    :cond_0
    return p2
.end method

.method public static hasSystemFeature(Landroid/content/Context;Ljava/lang/String;)Z
    .locals 0

    .line 243
    invoke-virtual {p0}, Landroid/content/Context;->getPackageManager()Landroid/content/pm/PackageManager;

    move-result-object p0

    invoke-virtual {p0, p1}, Landroid/content/pm/PackageManager;->hasSystemFeature(Ljava/lang/String;)Z

    move-result p0

    return p0
.end method

.method private isNegated(Ljava/lang/String;)Z
    .locals 0

    if-eqz p1, :cond_0

    const-string p0, "!"

    .line 125
    invoke-virtual {p1, p0}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result p0

    if-eqz p0, :cond_0

    const/4 p0, 0x1

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    :goto_0
    return p0
.end method

.method public static isPackageInstalled(Landroid/content/Context;Ljava/lang/String;Z)Z
    .locals 1

    if-eqz p1, :cond_0

    const/4 v0, 0x0

    .line 262
    :try_start_0
    invoke-virtual {p0}, Landroid/content/Context;->getPackageManager()Landroid/content/pm/PackageManager;

    move-result-object p0

    invoke-virtual {p0, p1, v0}, Landroid/content/pm/PackageManager;->getPackageInfo(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;

    move-result-object p0

    .line 263
    iget-object p0, p0, Landroid/content/pm/PackageInfo;->applicationInfo:Landroid/content/pm/ApplicationInfo;

    iget-boolean p0, p0, Landroid/content/pm/ApplicationInfo;->enabled:Z
    :try_end_0
    .catch Landroid/content/pm/PackageManager$NameNotFoundException; {:try_start_0 .. :try_end_0} :catch_0

    if-nez p0, :cond_0

    if-nez p2, :cond_0

    :catch_0
    return v0

    :cond_0
    const/4 p0, 0x1

    return p0
.end method

.method public static resolveIntent(Landroid/content/Context;Landroid/content/Intent;)Z
    .locals 3

    .line 278
    sget-boolean v0, Lorg/nameless/custom/preference/ConstraintsHelper;->DEBUG:Z

    const-string v1, "ConstraintsHelper"

    if-eqz v0, :cond_0

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "resolveIntent "

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-static {p1}, Ljava/util/Objects;->toString(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-static {v1, v0}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 280
    :cond_0
    invoke-virtual {p0}, Landroid/content/Context;->getPackageManager()Landroid/content/pm/PackageManager;

    move-result-object p0

    const/high16 v0, 0x100000

    .line 283
    invoke-static {}, Landroid/os/UserHandle;->myUserId()I

    move-result v2

    .line 281
    invoke-virtual {p0, p1, v0, v2}, Landroid/content/pm/PackageManager;->queryIntentActivitiesAsUser(Landroid/content/Intent;II)Ljava/util/List;

    move-result-object p0

    .line 284
    invoke-interface {p0}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :cond_1
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result p1

    if-eqz p1, :cond_3

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Landroid/content/pm/ResolveInfo;

    .line 287
    sget-boolean v0, Lorg/nameless/custom/preference/ConstraintsHelper;->DEBUG:Z

    if-eqz v0, :cond_2

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "resolveInfo: "

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-static {p1}, Ljava/util/Objects;->toString(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-static {v1, v0}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 288
    :cond_2
    iget-object p1, p1, Landroid/content/pm/ResolveInfo;->activityInfo:Landroid/content/pm/ActivityInfo;

    iget-object p1, p1, Landroid/content/pm/ActivityInfo;->applicationInfo:Landroid/content/pm/ApplicationInfo;

    iget p1, p1, Landroid/content/pm/ApplicationInfo;->flags:I

    const/4 v0, 0x1

    and-int/2addr p1, v0

    if-eqz p1, :cond_1

    return v0

    :cond_3
    const/4 p0, 0x0

    return p0
.end method

.method public static resolveIntent(Landroid/content/Context;Ljava/lang/String;)Z
    .locals 1

    .line 297
    new-instance v0, Landroid/content/Intent;

    invoke-direct {v0, p1}, Landroid/content/Intent;-><init>(Ljava/lang/String;)V

    invoke-static {p0, v0}, Lorg/nameless/custom/preference/ConstraintsHelper;->resolveIntent(Landroid/content/Context;Landroid/content/Intent;)Z

    move-result p0

    return p0
.end method


# virtual methods
.method public isAvailable()Z
    .locals 0

    .line 99
    iget-boolean p0, p0, Lorg/nameless/custom/preference/ConstraintsHelper;->mAvailable:Z

    return p0
.end method

.method public onAttached()V
    .locals 2

    .line 310
    invoke-direct {p0}, Lorg/nameless/custom/preference/ConstraintsHelper;->checkIntent()V

    .line 312
    invoke-virtual {p0}, Lorg/nameless/custom/preference/ConstraintsHelper;->isAvailable()Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lorg/nameless/custom/preference/ConstraintsHelper;->mReplacesKey:[Ljava/lang/String;

    if-eqz v0, :cond_0

    .line 313
    iget-object v0, p0, Lorg/nameless/custom/preference/ConstraintsHelper;->mContext:Landroid/content/Context;

    invoke-static {v0}, Lorg/nameless/custom/preference/ConstraintsHelper$Graveyard;->get(Landroid/content/Context;)Lorg/nameless/custom/preference/ConstraintsHelper$Graveyard;

    move-result-object v0

    iget-object v1, p0, Lorg/nameless/custom/preference/ConstraintsHelper;->mReplacesKey:[Ljava/lang/String;

    invoke-virtual {v0, v1}, Lorg/nameless/custom/preference/ConstraintsHelper$Graveyard;->addTombstones([Ljava/lang/String;)V

    .line 316
    :cond_0
    iget-object v0, p0, Lorg/nameless/custom/preference/ConstraintsHelper;->mContext:Landroid/content/Context;

    invoke-static {v0}, Lorg/nameless/custom/preference/ConstraintsHelper$Graveyard;->get(Landroid/content/Context;)Lorg/nameless/custom/preference/ConstraintsHelper$Graveyard;

    move-result-object v0

    iget-object p0, p0, Lorg/nameless/custom/preference/ConstraintsHelper;->mPref:Landroidx/preference/Preference;

    invoke-virtual {p0}, Landroidx/preference/Preference;->getPreferenceManager()Landroidx/preference/PreferenceManager;

    move-result-object p0

    invoke-virtual {v0, p0}, Lorg/nameless/custom/preference/ConstraintsHelper$Graveyard;->summonReaper(Landroidx/preference/PreferenceManager;)V

    return-void
.end method

.method public onBindViewHolder(Landroidx/preference/PreferenceViewHolder;)V
    .locals 1

    .line 320
    invoke-virtual {p0}, Lorg/nameless/custom/preference/ConstraintsHelper;->isAvailable()Z

    move-result v0

    if-nez v0, :cond_0

    return-void

    .line 324
    :cond_0
    iget v0, p0, Lorg/nameless/custom/preference/ConstraintsHelper;->mSummaryMinLines:I

    if-lez v0, :cond_1

    .line 325
    iget-object p1, p1, Landroidx/recyclerview/widget/RecyclerView$ViewHolder;->itemView:Landroid/view/View;

    const v0, 0x1020010

    invoke-virtual {p1, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object p1

    check-cast p1, Landroid/widget/TextView;

    if-eqz p1, :cond_1

    .line 327
    iget p0, p0, Lorg/nameless/custom/preference/ConstraintsHelper;->mSummaryMinLines:I

    invoke-virtual {p1, p0}, Landroid/widget/TextView;->setMinLines(I)V

    :cond_1
    return-void
.end method

.method public setAvailable(Z)V
    .locals 0

    .line 92
    iput-boolean p1, p0, Lorg/nameless/custom/preference/ConstraintsHelper;->mAvailable:Z

    if-nez p1, :cond_0

    .line 94
    iget-object p1, p0, Lorg/nameless/custom/preference/ConstraintsHelper;->mContext:Landroid/content/Context;

    invoke-static {p1}, Lorg/nameless/custom/preference/ConstraintsHelper$Graveyard;->get(Landroid/content/Context;)Lorg/nameless/custom/preference/ConstraintsHelper$Graveyard;

    move-result-object p1

    iget-object p0, p0, Lorg/nameless/custom/preference/ConstraintsHelper;->mPref:Landroidx/preference/Preference;

    invoke-virtual {p0}, Landroidx/preference/Preference;->getKey()Ljava/lang/String;

    move-result-object p0

    invoke-virtual {p1, p0}, Lorg/nameless/custom/preference/ConstraintsHelper$Graveyard;->addTombstone(Ljava/lang/String;)V

    :cond_0
    return-void
.end method
