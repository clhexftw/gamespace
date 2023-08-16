.class public Lcom/android/settingslib/widget/MainSwitchPreference;
.super Landroidx/preference/TwoStatePreference;
.source "MainSwitchPreference.java"

# interfaces
.implements Lcom/android/settingslib/widget/OnMainSwitchChangeListener;


# instance fields
.field private mMainSwitchBar:Lcom/android/settingslib/widget/MainSwitchBar;

.field private mShouldVibrate:Z

.field private final mSwitchChangeListeners:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "Lcom/android/settingslib/widget/OnMainSwitchChangeListener;",
            ">;"
        }
    .end annotation
.end field

.field private mTitle:Ljava/lang/CharSequence;


# direct methods
.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 1

    .line 50
    invoke-direct {p0, p1, p2}, Landroidx/preference/TwoStatePreference;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    .line 37
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/android/settingslib/widget/MainSwitchPreference;->mSwitchChangeListeners:Ljava/util/List;

    .line 51
    invoke-direct {p0, p1, p2}, Lcom/android/settingslib/widget/MainSwitchPreference;->init(Landroid/content/Context;Landroid/util/AttributeSet;)V

    return-void
.end method

.method private init(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 3

    .line 79
    sget v0, Lcom/android/settingslib/widget/R$layout;->settingslib_main_switch_layout:I

    invoke-virtual {p0, v0}, Landroidx/preference/Preference;->setLayoutResource(I)V

    .line 80
    iget-object v0, p0, Lcom/android/settingslib/widget/MainSwitchPreference;->mSwitchChangeListeners:Ljava/util/List;

    invoke-interface {v0, p0}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    if-eqz p2, :cond_0

    .line 82
    sget-object v0, Landroidx/preference/R$styleable;->Preference:[I

    const/4 v1, 0x0

    invoke-virtual {p1, p2, v0, v1, v1}, Landroid/content/Context;->obtainStyledAttributes(Landroid/util/AttributeSet;[III)Landroid/content/res/TypedArray;

    move-result-object v0

    .line 85
    sget v2, Landroidx/preference/R$styleable;->Preference_android_title:I

    invoke-virtual {v0, v2}, Landroid/content/res/TypedArray;->getText(I)Ljava/lang/CharSequence;

    move-result-object v2

    .line 87
    invoke-virtual {p0, v2}, Lcom/android/settingslib/widget/MainSwitchPreference;->setTitle(Ljava/lang/CharSequence;)V

    .line 88
    invoke-virtual {v0}, Landroid/content/res/TypedArray;->recycle()V

    .line 90
    sget-object v0, Lcom/android/settingslib/widget/R$styleable;->customPreference:[I

    invoke-virtual {p1, p2, v0, v1, v1}, Landroid/content/Context;->obtainStyledAttributes(Landroid/util/AttributeSet;[III)Landroid/content/res/TypedArray;

    move-result-object p1

    .line 93
    sget p2, Lcom/android/settingslib/widget/R$styleable;->customPreference_vibrateOnClick:I

    const/4 v0, 0x1

    invoke-virtual {p1, p2, v0}, Landroid/content/res/TypedArray;->getBoolean(IZ)Z

    move-result p2

    iput-boolean p2, p0, Lcom/android/settingslib/widget/MainSwitchPreference;->mShouldVibrate:Z

    .line 94
    invoke-virtual {p1}, Landroid/content/res/TypedArray;->recycle()V

    :cond_0
    return-void
.end method

.method private registerListenerToSwitchBar()V
    .locals 3

    .line 153
    iget-object v0, p0, Lcom/android/settingslib/widget/MainSwitchPreference;->mSwitchChangeListeners:Ljava/util/List;

    invoke-interface {v0}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :goto_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_0

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/android/settingslib/widget/OnMainSwitchChangeListener;

    .line 154
    iget-object v2, p0, Lcom/android/settingslib/widget/MainSwitchPreference;->mMainSwitchBar:Lcom/android/settingslib/widget/MainSwitchBar;

    invoke-virtual {v2, v1}, Lcom/android/settingslib/widget/MainSwitchBar;->addOnSwitchChangeListener(Lcom/android/settingslib/widget/OnMainSwitchChangeListener;)V

    goto :goto_0

    :cond_0
    return-void
.end method


# virtual methods
.method public onBindViewHolder(Landroidx/preference/PreferenceViewHolder;)V
    .locals 1

    .line 67
    invoke-super {p0, p1}, Landroidx/preference/Preference;->onBindViewHolder(Landroidx/preference/PreferenceViewHolder;)V

    const/4 v0, 0x0

    .line 69
    invoke-virtual {p1, v0}, Landroidx/preference/PreferenceViewHolder;->setDividerAllowedAbove(Z)V

    .line 70
    invoke-virtual {p1, v0}, Landroidx/preference/PreferenceViewHolder;->setDividerAllowedBelow(Z)V

    .line 72
    sget v0, Lcom/android/settingslib/widget/R$id;->settingslib_main_switch_bar:I

    invoke-virtual {p1, v0}, Landroidx/preference/PreferenceViewHolder;->findViewById(I)Landroid/view/View;

    move-result-object p1

    check-cast p1, Lcom/android/settingslib/widget/MainSwitchBar;

    iput-object p1, p0, Lcom/android/settingslib/widget/MainSwitchPreference;->mMainSwitchBar:Lcom/android/settingslib/widget/MainSwitchBar;

    .line 73
    iget-boolean v0, p0, Lcom/android/settingslib/widget/MainSwitchPreference;->mShouldVibrate:Z

    invoke-virtual {p1, v0}, Lcom/android/settingslib/widget/MainSwitchBar;->setShouldVibrate(Z)V

    .line 74
    invoke-virtual {p0}, Landroidx/preference/TwoStatePreference;->isChecked()Z

    move-result p1

    invoke-virtual {p0, p1}, Lcom/android/settingslib/widget/MainSwitchPreference;->updateStatus(Z)V

    .line 75
    invoke-direct {p0}, Lcom/android/settingslib/widget/MainSwitchPreference;->registerListenerToSwitchBar()V

    return-void
.end method

.method public onSwitchChanged(Landroid/widget/Switch;Z)V
    .locals 0

    .line 116
    invoke-super {p0, p2}, Landroidx/preference/TwoStatePreference;->setChecked(Z)V

    return-void
.end method

.method public setChecked(Z)V
    .locals 1

    .line 100
    invoke-super {p0, p1}, Landroidx/preference/TwoStatePreference;->setChecked(Z)V

    .line 101
    iget-object v0, p0, Lcom/android/settingslib/widget/MainSwitchPreference;->mMainSwitchBar:Lcom/android/settingslib/widget/MainSwitchBar;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Lcom/android/settingslib/widget/MainSwitchBar;->isChecked()Z

    move-result v0

    if-eq v0, p1, :cond_0

    .line 102
    iget-object p0, p0, Lcom/android/settingslib/widget/MainSwitchPreference;->mMainSwitchBar:Lcom/android/settingslib/widget/MainSwitchBar;

    invoke-virtual {p0, p1}, Lcom/android/settingslib/widget/MainSwitchBar;->setChecked(Z)V

    :cond_0
    return-void
.end method

.method public setTitle(Ljava/lang/CharSequence;)V
    .locals 0

    .line 108
    iput-object p1, p0, Lcom/android/settingslib/widget/MainSwitchPreference;->mTitle:Ljava/lang/CharSequence;

    .line 109
    iget-object p0, p0, Lcom/android/settingslib/widget/MainSwitchPreference;->mMainSwitchBar:Lcom/android/settingslib/widget/MainSwitchBar;

    if-eqz p0, :cond_0

    .line 110
    invoke-virtual {p0, p1}, Lcom/android/settingslib/widget/MainSwitchBar;->setTitle(Ljava/lang/CharSequence;)V

    :cond_0
    return-void
.end method

.method public updateStatus(Z)V
    .locals 1

    .line 123
    invoke-virtual {p0, p1}, Lcom/android/settingslib/widget/MainSwitchPreference;->setChecked(Z)V

    .line 124
    iget-object p1, p0, Lcom/android/settingslib/widget/MainSwitchPreference;->mMainSwitchBar:Lcom/android/settingslib/widget/MainSwitchBar;

    if-eqz p1, :cond_0

    .line 125
    iget-object v0, p0, Lcom/android/settingslib/widget/MainSwitchPreference;->mTitle:Ljava/lang/CharSequence;

    invoke-virtual {p1, v0}, Lcom/android/settingslib/widget/MainSwitchBar;->setTitle(Ljava/lang/CharSequence;)V

    .line 126
    iget-object p0, p0, Lcom/android/settingslib/widget/MainSwitchPreference;->mMainSwitchBar:Lcom/android/settingslib/widget/MainSwitchBar;

    invoke-virtual {p0}, Lcom/android/settingslib/widget/MainSwitchBar;->show()V

    :cond_0
    return-void
.end method
