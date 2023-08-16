.class public Lorg/nameless/custom/preference/SystemSettingSeekBarPreference;
.super Lorg/nameless/custom/preference/CustomSeekBarPreference;
.source "SystemSettingSeekBarPreference.java"


# direct methods
.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 0

    .line 29
    invoke-direct {p0, p1, p2}, Lorg/nameless/custom/preference/CustomSeekBarPreference;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    .line 30
    new-instance p2, Lorg/nameless/custom/preference/SystemSettingsStore;

    invoke-virtual {p1}, Landroid/content/Context;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object p1

    invoke-direct {p2, p1}, Lorg/nameless/custom/preference/SystemSettingsStore;-><init>(Landroid/content/ContentResolver;)V

    invoke-virtual {p0, p2}, Landroidx/preference/Preference;->setPreferenceDataStore(Landroidx/preference/PreferenceDataStore;)V

    return-void
.end method
