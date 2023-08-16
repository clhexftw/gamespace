.class public abstract Lorg/nameless/custom/preference/SelfRemovingDropDownPreference;
.super Landroidx/preference/DropDownPreference;
.source "SelfRemovingDropDownPreference.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lorg/nameless/custom/preference/SelfRemovingDropDownPreference$DataStore;
    }
.end annotation


# instance fields
.field private final mConstraints:Lorg/nameless/custom/preference/ConstraintsHelper;


# direct methods
.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 1

    .line 41
    invoke-direct {p0, p1, p2}, Landroidx/preference/DropDownPreference;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    .line 42
    new-instance v0, Lorg/nameless/custom/preference/ConstraintsHelper;

    invoke-direct {v0, p1, p2, p0}, Lorg/nameless/custom/preference/ConstraintsHelper;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;Landroidx/preference/Preference;)V

    iput-object v0, p0, Lorg/nameless/custom/preference/SelfRemovingDropDownPreference;->mConstraints:Lorg/nameless/custom/preference/ConstraintsHelper;

    .line 43
    new-instance p1, Lorg/nameless/custom/preference/SelfRemovingDropDownPreference$DataStore;

    const/4 p2, 0x0

    invoke-direct {p1, p0, p2}, Lorg/nameless/custom/preference/SelfRemovingDropDownPreference$DataStore;-><init>(Lorg/nameless/custom/preference/SelfRemovingDropDownPreference;Lorg/nameless/custom/preference/SelfRemovingDropDownPreference$DataStore-IA;)V

    invoke-virtual {p0, p1}, Landroidx/preference/Preference;->setPreferenceDataStore(Landroidx/preference/PreferenceDataStore;)V

    return-void
.end method


# virtual methods
.method protected abstract getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
.end method

.method protected abstract isPersisted()Z
.end method

.method public onAttached()V
    .locals 0

    .line 54
    invoke-super {p0}, Landroidx/preference/Preference;->onAttached()V

    .line 55
    iget-object p0, p0, Lorg/nameless/custom/preference/SelfRemovingDropDownPreference;->mConstraints:Lorg/nameless/custom/preference/ConstraintsHelper;

    invoke-virtual {p0}, Lorg/nameless/custom/preference/ConstraintsHelper;->onAttached()V

    return-void
.end method

.method public onBindViewHolder(Landroidx/preference/PreferenceViewHolder;)V
    .locals 0

    .line 60
    invoke-super {p0, p1}, Landroidx/preference/DropDownPreference;->onBindViewHolder(Landroidx/preference/PreferenceViewHolder;)V

    .line 61
    iget-object p0, p0, Lorg/nameless/custom/preference/SelfRemovingDropDownPreference;->mConstraints:Lorg/nameless/custom/preference/ConstraintsHelper;

    invoke-virtual {p0, p1}, Lorg/nameless/custom/preference/ConstraintsHelper;->onBindViewHolder(Landroidx/preference/PreferenceViewHolder;)V

    return-void
.end method

.method protected onSetInitialValue(ZLjava/lang/Object;)V
    .locals 0

    if-eqz p1, :cond_1

    .line 79
    invoke-virtual {p0}, Lorg/nameless/custom/preference/SelfRemovingDropDownPreference;->isPersisted()Z

    move-result p1

    if-nez p1, :cond_0

    goto :goto_0

    .line 90
    :cond_0
    invoke-virtual {p0}, Landroidx/preference/Preference;->getKey()Ljava/lang/String;

    move-result-object p1

    const/4 p2, 0x0

    invoke-virtual {p0, p1, p2}, Lorg/nameless/custom/preference/SelfRemovingDropDownPreference;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    goto :goto_1

    :cond_1
    :goto_0
    if-nez p2, :cond_2

    return-void

    .line 83
    :cond_2
    move-object p1, p2

    check-cast p1, Ljava/lang/String;

    .line 84
    invoke-virtual {p0}, Landroidx/preference/Preference;->shouldPersist()Z

    move-result p2

    if-eqz p2, :cond_3

    .line 85
    invoke-virtual {p0, p1}, Landroidx/preference/Preference;->persistString(Ljava/lang/String;)Z

    .line 92
    :cond_3
    :goto_1
    invoke-virtual {p0, p1}, Landroidx/preference/ListPreference;->setValue(Ljava/lang/String;)V

    return-void
.end method

.method protected abstract putString(Ljava/lang/String;Ljava/lang/String;)V
.end method
