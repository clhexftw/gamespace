.class public Lorg/nameless/custom/preference/SelfRemovingPreference;
.super Landroidx/preference/Preference;
.source "SelfRemovingPreference.java"


# instance fields
.field private final mConstraints:Lorg/nameless/custom/preference/ConstraintsHelper;


# direct methods
.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 2

    .line 43
    sget v0, Landroidx/preference/R$attr;->preferenceStyle:I

    const v1, 0x101008e

    invoke-static {p1, v0, v1}, Lorg/nameless/custom/preference/ConstraintsHelper;->getAttr(Landroid/content/Context;II)I

    move-result v0

    invoke-direct {p0, p1, p2, v0}, Lorg/nameless/custom/preference/SelfRemovingPreference;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V
    .locals 1

    const/4 v0, 0x0

    .line 39
    invoke-direct {p0, p1, p2, p3, v0}, Lorg/nameless/custom/preference/SelfRemovingPreference;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;II)V

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;II)V
    .locals 0

    .line 34
    invoke-direct {p0, p1, p2, p3, p4}, Landroidx/preference/Preference;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;II)V

    .line 35
    new-instance p3, Lorg/nameless/custom/preference/ConstraintsHelper;

    invoke-direct {p3, p1, p2, p0}, Lorg/nameless/custom/preference/ConstraintsHelper;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;Landroidx/preference/Preference;)V

    iput-object p3, p0, Lorg/nameless/custom/preference/SelfRemovingPreference;->mConstraints:Lorg/nameless/custom/preference/ConstraintsHelper;

    return-void
.end method


# virtual methods
.method public isAvailable()Z
    .locals 0

    .line 69
    iget-object p0, p0, Lorg/nameless/custom/preference/SelfRemovingPreference;->mConstraints:Lorg/nameless/custom/preference/ConstraintsHelper;

    invoke-virtual {p0}, Lorg/nameless/custom/preference/ConstraintsHelper;->isAvailable()Z

    move-result p0

    return p0
.end method

.method public onAttached()V
    .locals 0

    .line 54
    invoke-super {p0}, Landroidx/preference/Preference;->onAttached()V

    .line 55
    iget-object p0, p0, Lorg/nameless/custom/preference/SelfRemovingPreference;->mConstraints:Lorg/nameless/custom/preference/ConstraintsHelper;

    invoke-virtual {p0}, Lorg/nameless/custom/preference/ConstraintsHelper;->onAttached()V

    return-void
.end method

.method public onBindViewHolder(Landroidx/preference/PreferenceViewHolder;)V
    .locals 0

    .line 60
    invoke-super {p0, p1}, Landroidx/preference/Preference;->onBindViewHolder(Landroidx/preference/PreferenceViewHolder;)V

    .line 61
    iget-object p0, p0, Lorg/nameless/custom/preference/SelfRemovingPreference;->mConstraints:Lorg/nameless/custom/preference/ConstraintsHelper;

    invoke-virtual {p0, p1}, Lorg/nameless/custom/preference/ConstraintsHelper;->onBindViewHolder(Landroidx/preference/PreferenceViewHolder;)V

    return-void
.end method

.method public setAvailable(Z)V
    .locals 0

    .line 65
    iget-object p0, p0, Lorg/nameless/custom/preference/SelfRemovingPreference;->mConstraints:Lorg/nameless/custom/preference/ConstraintsHelper;

    invoke-virtual {p0, p1}, Lorg/nameless/custom/preference/ConstraintsHelper;->setAvailable(Z)V

    return-void
.end method
