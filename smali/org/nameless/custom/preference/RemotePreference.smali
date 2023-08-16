.class public Lorg/nameless/custom/preference/RemotePreference;
.super Lorg/nameless/custom/preference/SelfRemovingPreference;
.source "RemotePreference.java"

# interfaces
.implements Lorg/nameless/custom/preference/RemotePreferenceManager$OnRemoteUpdateListener;


# static fields
.field private static final DEBUG:Z

.field private static final TAG:Ljava/lang/String;


# instance fields
.field protected final mContext:Landroid/content/Context;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    .line 63
    const-class v0, Lorg/nameless/custom/preference/RemotePreference;

    invoke-virtual {v0}, Ljava/lang/Class;->getSimpleName()Ljava/lang/String;

    move-result-object v0

    sput-object v0, Lorg/nameless/custom/preference/RemotePreference;->TAG:Ljava/lang/String;

    const/4 v1, 0x2

    .line 65
    invoke-static {v0, v1}, Landroid/util/Log;->isLoggable(Ljava/lang/String;I)Z

    move-result v0

    sput-boolean v0, Lorg/nameless/custom/preference/RemotePreference;->DEBUG:Z

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 2

    .line 96
    sget v0, Landroidx/preference/R$attr;->preferenceStyle:I

    const v1, 0x101008e

    invoke-static {p1, v0, v1}, Lorg/nameless/custom/preference/ConstraintsHelper;->getAttr(Landroid/content/Context;II)I

    move-result v0

    invoke-direct {p0, p1, p2, v0}, Lorg/nameless/custom/preference/RemotePreference;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V
    .locals 1

    const/4 v0, 0x0

    .line 92
    invoke-direct {p0, p1, p2, p3, v0}, Lorg/nameless/custom/preference/RemotePreference;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;II)V

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;II)V
    .locals 0

    .line 87
    invoke-direct {p0, p1, p2, p3, p4}, Lorg/nameless/custom/preference/SelfRemovingPreference;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;II)V

    .line 88
    iput-object p1, p0, Lorg/nameless/custom/preference/RemotePreference;->mContext:Landroid/content/Context;

    return-void
.end method


# virtual methods
.method public getReceiverIntent()Landroid/content/Intent;
    .locals 8

    .line 137
    invoke-virtual {p0}, Landroidx/preference/Preference;->getIntent()Landroid/content/Intent;

    move-result-object v0

    const/4 v1, 0x0

    if-nez v0, :cond_0

    .line 139
    sget-object p0, Lorg/nameless/custom/preference/RemotePreference;->TAG:Ljava/lang/String;

    const-string v0, "No target intent specified in preference!"

    invoke-static {p0, v0}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    return-object v1

    .line 143
    :cond_0
    iget-object v2, p0, Lorg/nameless/custom/preference/RemotePreference;->mContext:Landroid/content/Context;

    invoke-virtual {v2}, Landroid/content/Context;->getPackageManager()Landroid/content/pm/PackageManager;

    move-result-object v2

    const v3, 0x100080

    .line 146
    invoke-static {}, Landroid/os/UserHandle;->myUserId()I

    move-result v4

    .line 144
    invoke-virtual {v2, v0, v3, v4}, Landroid/content/pm/PackageManager;->queryIntentActivitiesAsUser(Landroid/content/Intent;II)Ljava/util/List;

    move-result-object v2

    .line 149
    invoke-interface {v2}, Ljava/util/List;->size()I

    move-result v3

    if-nez v3, :cond_1

    .line 150
    sget-object v3, Lorg/nameless/custom/preference/RemotePreference;->TAG:Ljava/lang/String;

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    const-string v5, "No activity found for: "

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-static {v0}, Ljava/util/Objects;->toString(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v4, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-static {v3, v0}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    .line 153
    :cond_1
    invoke-interface {v2}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :cond_2
    :goto_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_6

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Landroid/content/pm/ResolveInfo;

    .line 154
    iget-object v3, v2, Landroid/content/pm/ResolveInfo;->activityInfo:Landroid/content/pm/ActivityInfo;

    .line 155
    sget-object v4, Lorg/nameless/custom/preference/RemotePreference;->TAG:Ljava/lang/String;

    new-instance v5, Ljava/lang/StringBuilder;

    invoke-direct {v5}, Ljava/lang/StringBuilder;-><init>()V

    const-string v6, "ResolveInfo "

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-static {v2}, Ljava/util/Objects;->toString(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v5, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-static {v4, v2}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 157
    iget-object v2, v3, Landroid/content/pm/ActivityInfo;->metaData:Landroid/os/Bundle;

    if-eqz v2, :cond_2

    const-string v5, "com.android.settings.summary.receiver"

    .line 158
    invoke-virtual {v2, v5}, Landroid/os/Bundle;->containsKey(Ljava/lang/String;)Z

    move-result v6

    if-nez v6, :cond_3

    goto :goto_0

    .line 162
    :cond_3
    invoke-virtual {v2, v5}, Landroid/os/Bundle;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v5

    .line 163
    iget-object v3, v3, Landroid/content/pm/ActivityInfo;->packageName:Ljava/lang/String;

    .line 164
    invoke-virtual {p0, v2}, Lorg/nameless/custom/preference/RemotePreference;->getRemoteKey(Landroid/os/Bundle;)Ljava/lang/String;

    move-result-object v2

    .line 166
    sget-boolean v6, Lorg/nameless/custom/preference/RemotePreference;->DEBUG:Z

    if-eqz v6, :cond_4

    new-instance v6, Ljava/lang/StringBuilder;

    invoke-direct {v6}, Ljava/lang/StringBuilder;-><init>()V

    const-string v7, "getReceiverIntent class="

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v6, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v7, " package="

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v6, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v7, " key="

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v6, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v6}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v6

    invoke-static {v4, v6}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    :cond_4
    if-nez v2, :cond_5

    goto :goto_0

    .line 173
    :cond_5
    new-instance p0, Landroid/content/Intent;

    const-string v0, "lineageos.intent.action.UPDATE_PREFERENCE"

    invoke-direct {p0, v0}, Landroid/content/Intent;-><init>(Ljava/lang/String;)V

    .line 174
    new-instance v0, Landroid/content/ComponentName;

    invoke-direct {v0, v3, v5}, Landroid/content/ComponentName;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {p0, v0}, Landroid/content/Intent;->setComponent(Landroid/content/ComponentName;)Landroid/content/Intent;

    const-string v0, ":lineage:pref_key"

    .line 175
    invoke-virtual {p0, v0, v2}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;

    return-object p0

    :cond_6
    return-object v1
.end method

.method protected getRemoteKey(Landroid/os/Bundle;)Ljava/lang/String;
    .locals 1

    const-string v0, "com.android.settings.summary.key"

    .line 131
    invoke-virtual {p1, v0}, Landroid/os/Bundle;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    if-eqz p1, :cond_0

    .line 132
    invoke-virtual {p0}, Landroidx/preference/Preference;->getKey()Ljava/lang/String;

    move-result-object p0

    invoke-virtual {p1, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_1

    :cond_0
    const/4 p1, 0x0

    :cond_1
    return-object p1
.end method

.method public onAttached()V
    .locals 2

    .line 118
    invoke-super {p0}, Lorg/nameless/custom/preference/SelfRemovingPreference;->onAttached()V

    .line 119
    invoke-virtual {p0}, Lorg/nameless/custom/preference/SelfRemovingPreference;->isAvailable()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 120
    iget-object v0, p0, Lorg/nameless/custom/preference/RemotePreference;->mContext:Landroid/content/Context;

    invoke-static {v0}, Lorg/nameless/custom/preference/RemotePreferenceManager;->get(Landroid/content/Context;)Lorg/nameless/custom/preference/RemotePreferenceManager;

    move-result-object v0

    invoke-virtual {p0}, Landroidx/preference/Preference;->getKey()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1, p0}, Lorg/nameless/custom/preference/RemotePreferenceManager;->attach(Ljava/lang/String;Lorg/nameless/custom/preference/RemotePreferenceManager$OnRemoteUpdateListener;)V

    :cond_0
    return-void
.end method

.method public onDetached()V
    .locals 2

    .line 126
    invoke-super {p0}, Landroidx/preference/Preference;->onDetached()V

    .line 127
    iget-object v0, p0, Lorg/nameless/custom/preference/RemotePreference;->mContext:Landroid/content/Context;

    invoke-static {v0}, Lorg/nameless/custom/preference/RemotePreferenceManager;->get(Landroid/content/Context;)Lorg/nameless/custom/preference/RemotePreferenceManager;

    move-result-object v0

    invoke-virtual {p0}, Landroidx/preference/Preference;->getKey()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1, p0}, Lorg/nameless/custom/preference/RemotePreferenceManager;->detach(Ljava/lang/String;Lorg/nameless/custom/preference/RemotePreferenceManager$OnRemoteUpdateListener;)V

    return-void
.end method

.method public onRemoteUpdated(Landroid/os/Bundle;)V
    .locals 3

    .line 103
    sget-boolean v0, Lorg/nameless/custom/preference/RemotePreference;->DEBUG:Z

    if-eqz v0, :cond_0

    sget-object v0, Lorg/nameless/custom/preference/RemotePreference;->TAG:Ljava/lang/String;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "onRemoteUpdated: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Landroid/os/Bundle;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    :cond_0
    const-string v0, ":lineage:pref_enabled"

    .line 105
    invoke-virtual {p1, v0}, Landroid/os/Bundle;->containsKey(Ljava/lang/String;)Z

    move-result v1

    if-eqz v1, :cond_1

    const/4 v1, 0x1

    .line 106
    invoke-virtual {p1, v0, v1}, Landroid/os/Bundle;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    .line 107
    invoke-virtual {p0}, Lorg/nameless/custom/preference/SelfRemovingPreference;->isAvailable()Z

    move-result v1

    if-eq v0, v1, :cond_1

    .line 108
    invoke-virtual {p0, v0}, Lorg/nameless/custom/preference/SelfRemovingPreference;->setAvailable(Z)V

    .line 111
    :cond_1
    invoke-virtual {p0}, Lorg/nameless/custom/preference/SelfRemovingPreference;->isAvailable()Z

    move-result v0

    if-eqz v0, :cond_2

    const-string v0, ":lineage:pref_summary"

    .line 112
    invoke-virtual {p1, v0}, Landroid/os/Bundle;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p0, p1}, Landroidx/preference/Preference;->setSummary(Ljava/lang/CharSequence;)V

    :cond_2
    return-void
.end method
