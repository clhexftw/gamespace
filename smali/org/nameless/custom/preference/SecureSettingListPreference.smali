.class public Lorg/nameless/custom/preference/SecureSettingListPreference;
.super Landroidx/preference/ListPreference;
.source "SecureSettingListPreference.java"


# instance fields
.field private mAutoSummary:Z


# direct methods
.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 0

    .line 33
    invoke-direct {p0, p1, p2}, Landroidx/preference/ListPreference;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    const/4 p2, 0x0

    .line 25
    iput-boolean p2, p0, Lorg/nameless/custom/preference/SecureSettingListPreference;->mAutoSummary:Z

    .line 34
    new-instance p2, Lorg/nameless/custom/preference/SecureSettingsStore;

    invoke-virtual {p1}, Landroid/content/Context;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object p1

    invoke-direct {p2, p1}, Lorg/nameless/custom/preference/SecureSettingsStore;-><init>(Landroid/content/ContentResolver;)V

    invoke-virtual {p0, p2}, Landroidx/preference/Preference;->setPreferenceDataStore(Landroidx/preference/PreferenceDataStore;)V

    return-void
.end method

.method private setSummary(Ljava/lang/CharSequence;Z)V
    .locals 0

    .line 56
    iput-boolean p2, p0, Lorg/nameless/custom/preference/SecureSettingListPreference;->mAutoSummary:Z

    .line 57
    invoke-super {p0, p1}, Landroidx/preference/ListPreference;->setSummary(Ljava/lang/CharSequence;)V

    return-void
.end method


# virtual methods
.method protected onSetInitialValue(ZLjava/lang/Object;)V
    .locals 0

    if-eqz p1, :cond_0

    .line 66
    check-cast p2, Ljava/lang/String;

    invoke-virtual {p0, p2}, Landroidx/preference/Preference;->getPersistedString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    goto :goto_0

    :cond_0
    move-object p1, p2

    check-cast p1, Ljava/lang/String;

    :goto_0
    invoke-virtual {p0, p1}, Lorg/nameless/custom/preference/SecureSettingListPreference;->setValue(Ljava/lang/String;)V

    return-void
.end method

.method public setSummary(Ljava/lang/CharSequence;)V
    .locals 1

    const/4 v0, 0x0

    .line 52
    invoke-direct {p0, p1, v0}, Lorg/nameless/custom/preference/SecureSettingListPreference;->setSummary(Ljava/lang/CharSequence;Z)V

    return-void
.end method

.method public setValue(Ljava/lang/String;)V
    .locals 1

    .line 44
    invoke-super {p0, p1}, Landroidx/preference/ListPreference;->setValue(Ljava/lang/String;)V

    .line 45
    iget-boolean p1, p0, Lorg/nameless/custom/preference/SecureSettingListPreference;->mAutoSummary:Z

    if-nez p1, :cond_0

    invoke-virtual {p0}, Landroidx/preference/ListPreference;->getSummary()Ljava/lang/CharSequence;

    move-result-object p1

    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result p1

    if-eqz p1, :cond_1

    .line 46
    :cond_0
    invoke-virtual {p0}, Landroidx/preference/ListPreference;->getEntry()Ljava/lang/CharSequence;

    move-result-object p1

    const/4 v0, 0x1

    invoke-direct {p0, p1, v0}, Lorg/nameless/custom/preference/SecureSettingListPreference;->setSummary(Ljava/lang/CharSequence;Z)V

    :cond_1
    return-void
.end method
