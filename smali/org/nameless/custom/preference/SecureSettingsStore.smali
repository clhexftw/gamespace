.class public Lorg/nameless/custom/preference/SecureSettingsStore;
.super Landroidx/preference/PreferenceDataStore;
.source "SecureSettingsStore.java"

# interfaces
.implements Landroid/preference/PreferenceDataStore;


# instance fields
.field private mContentResolver:Landroid/content/ContentResolver;


# direct methods
.method public constructor <init>(Landroid/content/ContentResolver;)V
    .locals 0

    .line 29
    invoke-direct {p0}, Landroidx/preference/PreferenceDataStore;-><init>()V

    .line 30
    iput-object p1, p0, Lorg/nameless/custom/preference/SecureSettingsStore;->mContentResolver:Landroid/content/ContentResolver;

    return-void
.end method


# virtual methods
.method public getBoolean(Ljava/lang/String;Z)Z
    .locals 0

    .line 34
    invoke-virtual {p0, p1, p2}, Lorg/nameless/custom/preference/SecureSettingsStore;->getInt(Ljava/lang/String;I)I

    move-result p0

    if-eqz p0, :cond_0

    const/4 p0, 0x1

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    :goto_0
    return p0
.end method

.method public getFloat(Ljava/lang/String;F)F
    .locals 0

    .line 38
    iget-object p0, p0, Lorg/nameless/custom/preference/SecureSettingsStore;->mContentResolver:Landroid/content/ContentResolver;

    invoke-static {p0, p1, p2}, Landroid/provider/Settings$Secure;->getFloat(Landroid/content/ContentResolver;Ljava/lang/String;F)F

    move-result p0

    return p0
.end method

.method public getInt(Ljava/lang/String;I)I
    .locals 0

    .line 42
    iget-object p0, p0, Lorg/nameless/custom/preference/SecureSettingsStore;->mContentResolver:Landroid/content/ContentResolver;

    invoke-static {p0, p1, p2}, Landroid/provider/Settings$Secure;->getInt(Landroid/content/ContentResolver;Ljava/lang/String;I)I

    move-result p0

    return p0
.end method

.method public getLong(Ljava/lang/String;J)J
    .locals 0

    .line 46
    iget-object p0, p0, Lorg/nameless/custom/preference/SecureSettingsStore;->mContentResolver:Landroid/content/ContentResolver;

    invoke-static {p0, p1, p2, p3}, Landroid/provider/Settings$Secure;->getLong(Landroid/content/ContentResolver;Ljava/lang/String;J)J

    move-result-wide p0

    return-wide p0
.end method

.method public getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    .locals 0

    .line 50
    iget-object p0, p0, Lorg/nameless/custom/preference/SecureSettingsStore;->mContentResolver:Landroid/content/ContentResolver;

    invoke-static {p0, p1}, Landroid/provider/Settings$Secure;->getString(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    if-nez p0, :cond_0

    goto :goto_0

    :cond_0
    move-object p2, p0

    :goto_0
    return-object p2
.end method

.method public putBoolean(Ljava/lang/String;Z)V
    .locals 0

    .line 55
    invoke-virtual {p0, p1, p2}, Lorg/nameless/custom/preference/SecureSettingsStore;->putInt(Ljava/lang/String;I)V

    return-void
.end method

.method public putFloat(Ljava/lang/String;F)V
    .locals 0

    .line 59
    iget-object p0, p0, Lorg/nameless/custom/preference/SecureSettingsStore;->mContentResolver:Landroid/content/ContentResolver;

    invoke-static {p0, p1, p2}, Landroid/provider/Settings$Secure;->putFloat(Landroid/content/ContentResolver;Ljava/lang/String;F)Z

    return-void
.end method

.method public putInt(Ljava/lang/String;I)V
    .locals 0

    .line 63
    iget-object p0, p0, Lorg/nameless/custom/preference/SecureSettingsStore;->mContentResolver:Landroid/content/ContentResolver;

    invoke-static {p0, p1, p2}, Landroid/provider/Settings$Secure;->putInt(Landroid/content/ContentResolver;Ljava/lang/String;I)Z

    return-void
.end method

.method public putLong(Ljava/lang/String;J)V
    .locals 0

    .line 67
    iget-object p0, p0, Lorg/nameless/custom/preference/SecureSettingsStore;->mContentResolver:Landroid/content/ContentResolver;

    invoke-static {p0, p1, p2, p3}, Landroid/provider/Settings$Secure;->putLong(Landroid/content/ContentResolver;Ljava/lang/String;J)Z

    return-void
.end method

.method public putString(Ljava/lang/String;Ljava/lang/String;)V
    .locals 0

    .line 71
    iget-object p0, p0, Lorg/nameless/custom/preference/SecureSettingsStore;->mContentResolver:Landroid/content/ContentResolver;

    invoke-static {p0, p1, p2}, Landroid/provider/Settings$Secure;->putString(Landroid/content/ContentResolver;Ljava/lang/String;Ljava/lang/String;)Z

    return-void
.end method
