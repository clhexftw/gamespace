.class public abstract Lorg/nameless/custom/preference/SelfRemovingSwitchPreference;
.super Lorg/nameless/custom/preference/SwitchPreference;
.source "SelfRemovingSwitchPreference.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lorg/nameless/custom/preference/SelfRemovingSwitchPreference$DataStore;
    }
.end annotation


# instance fields
.field private final mConstraints:Lorg/nameless/custom/preference/ConstraintsHelper;


# direct methods
.method public constructor <init>(Landroid/content/Context;)V
    .locals 2

    .line 46
    invoke-direct {p0, p1}, Lorg/nameless/custom/preference/SwitchPreference;-><init>(Landroid/content/Context;)V

    .line 47
    new-instance v0, Lorg/nameless/custom/preference/ConstraintsHelper;

    const/4 v1, 0x0

    invoke-direct {v0, p1, v1, p0}, Lorg/nameless/custom/preference/ConstraintsHelper;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;Landroidx/preference/Preference;)V

    iput-object v0, p0, Lorg/nameless/custom/preference/SelfRemovingSwitchPreference;->mConstraints:Lorg/nameless/custom/preference/ConstraintsHelper;

    .line 48
    new-instance p1, Lorg/nameless/custom/preference/SelfRemovingSwitchPreference$DataStore;

    invoke-direct {p1, p0, v1}, Lorg/nameless/custom/preference/SelfRemovingSwitchPreference$DataStore;-><init>(Lorg/nameless/custom/preference/SelfRemovingSwitchPreference;Lorg/nameless/custom/preference/SelfRemovingSwitchPreference$DataStore-IA;)V

    invoke-virtual {p0, p1}, Landroidx/preference/Preference;->setPreferenceDataStore(Landroidx/preference/PreferenceDataStore;)V

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 1

    .line 40
    invoke-direct {p0, p1, p2}, Lorg/nameless/custom/preference/SwitchPreference;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    .line 41
    new-instance v0, Lorg/nameless/custom/preference/ConstraintsHelper;

    invoke-direct {v0, p1, p2, p0}, Lorg/nameless/custom/preference/ConstraintsHelper;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;Landroidx/preference/Preference;)V

    iput-object v0, p0, Lorg/nameless/custom/preference/SelfRemovingSwitchPreference;->mConstraints:Lorg/nameless/custom/preference/ConstraintsHelper;

    .line 42
    new-instance p1, Lorg/nameless/custom/preference/SelfRemovingSwitchPreference$DataStore;

    const/4 p2, 0x0

    invoke-direct {p1, p0, p2}, Lorg/nameless/custom/preference/SelfRemovingSwitchPreference$DataStore;-><init>(Lorg/nameless/custom/preference/SelfRemovingSwitchPreference;Lorg/nameless/custom/preference/SelfRemovingSwitchPreference$DataStore-IA;)V

    invoke-virtual {p0, p1}, Landroidx/preference/Preference;->setPreferenceDataStore(Landroidx/preference/PreferenceDataStore;)V

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V
    .locals 0

    .line 34
    invoke-direct {p0, p1, p2, p3}, Lorg/nameless/custom/preference/SwitchPreference;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    .line 35
    new-instance p3, Lorg/nameless/custom/preference/ConstraintsHelper;

    invoke-direct {p3, p1, p2, p0}, Lorg/nameless/custom/preference/ConstraintsHelper;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;Landroidx/preference/Preference;)V

    iput-object p3, p0, Lorg/nameless/custom/preference/SelfRemovingSwitchPreference;->mConstraints:Lorg/nameless/custom/preference/ConstraintsHelper;

    .line 36
    new-instance p1, Lorg/nameless/custom/preference/SelfRemovingSwitchPreference$DataStore;

    const/4 p2, 0x0

    invoke-direct {p1, p0, p2}, Lorg/nameless/custom/preference/SelfRemovingSwitchPreference$DataStore;-><init>(Lorg/nameless/custom/preference/SelfRemovingSwitchPreference;Lorg/nameless/custom/preference/SelfRemovingSwitchPreference$DataStore-IA;)V

    invoke-virtual {p0, p1}, Landroidx/preference/Preference;->setPreferenceDataStore(Landroidx/preference/PreferenceDataStore;)V

    return-void
.end method


# virtual methods
.method protected abstract getBoolean(Ljava/lang/String;Z)Z
.end method

.method protected abstract isPersisted()Z
.end method

.method public onAttached()V
    .locals 0

    .line 53
    invoke-super {p0}, Landroidx/preference/Preference;->onAttached()V

    .line 54
    iget-object p0, p0, Lorg/nameless/custom/preference/SelfRemovingSwitchPreference;->mConstraints:Lorg/nameless/custom/preference/ConstraintsHelper;

    invoke-virtual {p0}, Lorg/nameless/custom/preference/ConstraintsHelper;->onAttached()V

    return-void
.end method

.method public onBindViewHolder(Landroidx/preference/PreferenceViewHolder;)V
    .locals 0

    .line 59
    invoke-super {p0, p1}, Landroidx/preference/SwitchPreference;->onBindViewHolder(Landroidx/preference/PreferenceViewHolder;)V

    .line 60
    iget-object p0, p0, Lorg/nameless/custom/preference/SelfRemovingSwitchPreference;->mConstraints:Lorg/nameless/custom/preference/ConstraintsHelper;

    invoke-virtual {p0, p1}, Lorg/nameless/custom/preference/ConstraintsHelper;->onBindViewHolder(Landroidx/preference/PreferenceViewHolder;)V

    return-void
.end method

.method protected onSetInitialValue(ZLjava/lang/Object;)V
    .locals 0

    if-eqz p1, :cond_1

    .line 78
    invoke-virtual {p0}, Lorg/nameless/custom/preference/SelfRemovingSwitchPreference;->isPersisted()Z

    move-result p1

    if-nez p1, :cond_0

    goto :goto_0

    .line 89
    :cond_0
    invoke-virtual {p0}, Landroidx/preference/Preference;->getKey()Ljava/lang/String;

    move-result-object p1

    const/4 p2, 0x0

    invoke-virtual {p0, p1, p2}, Lorg/nameless/custom/preference/SelfRemovingSwitchPreference;->getBoolean(Ljava/lang/String;Z)Z

    move-result p1

    goto :goto_1

    :cond_1
    :goto_0
    if-nez p2, :cond_2

    return-void

    .line 82
    :cond_2
    check-cast p2, Ljava/lang/Boolean;

    invoke-virtual {p2}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p1

    .line 83
    invoke-virtual {p0}, Landroidx/preference/Preference;->shouldPersist()Z

    move-result p2

    if-eqz p2, :cond_3

    .line 84
    invoke-virtual {p0, p1}, Landroidx/preference/Preference;->persistBoolean(Z)Z

    .line 91
    :cond_3
    :goto_1
    invoke-virtual {p0, p1}, Landroidx/preference/TwoStatePreference;->setChecked(Z)V

    return-void
.end method

.method protected abstract putBoolean(Ljava/lang/String;Z)V
.end method
