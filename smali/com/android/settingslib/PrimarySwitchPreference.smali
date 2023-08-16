.class public Lcom/android/settingslib/PrimarySwitchPreference;
.super Lcom/android/settingslib/RestrictedPreference;
.source "PrimarySwitchPreference.java"


# static fields
.field private static final EFFECT_CLICK:Landroid/os/VibrationEffect;

.field private static final VIBRATION_ATTRIBUTES_SWITCH:Landroid/os/VibrationAttributes;


# instance fields
.field private mChecked:Z

.field private mCheckedSet:Z

.field private mEnableSwitch:Z

.field private mSwitch:Landroid/widget/Switch;

.field private final mVibrator:Landroid/os/Vibrator;


# direct methods
.method public static synthetic $r8$lambda$aYN-0qoGuHaLgHlf-CATsBamDlY(Landroid/view/View;Landroid/view/MotionEvent;)Z
    .locals 0

    invoke-static {p0, p1}, Lcom/android/settingslib/PrimarySwitchPreference;->lambda$onBindViewHolder$1(Landroid/view/View;Landroid/view/MotionEvent;)Z

    move-result p0

    return p0
.end method

.method public static synthetic $r8$lambda$ktK7wLFjV7KlikuDOlpGJ_i3dkY(Lcom/android/settingslib/PrimarySwitchPreference;Landroid/view/View;)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/android/settingslib/PrimarySwitchPreference;->lambda$onBindViewHolder$0(Landroid/view/View;)V

    return-void
.end method

.method static constructor <clinit>()V
    .locals 1

    const/16 v0, 0xd4

    .line 41
    invoke-static {v0}, Landroid/os/VibrationAttributes;->createForUsage(I)Landroid/os/VibrationAttributes;

    move-result-object v0

    sput-object v0, Lcom/android/settingslib/PrimarySwitchPreference;->VIBRATION_ATTRIBUTES_SWITCH:Landroid/os/VibrationAttributes;

    const/4 v0, 0x0

    .line 43
    invoke-static {v0}, Landroid/os/VibrationEffect;->createPredefined(I)Landroid/os/VibrationEffect;

    move-result-object v0

    sput-object v0, Lcom/android/settingslib/PrimarySwitchPreference;->EFFECT_CLICK:Landroid/os/VibrationEffect;

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;)V
    .locals 1

    .line 68
    invoke-direct {p0, p1}, Lcom/android/settingslib/RestrictedPreference;-><init>(Landroid/content/Context;)V

    const/4 v0, 0x1

    .line 49
    iput-boolean v0, p0, Lcom/android/settingslib/PrimarySwitchPreference;->mEnableSwitch:Z

    .line 69
    const-class v0, Landroid/os/Vibrator;

    invoke-virtual {p1, v0}, Landroid/content/Context;->getSystemService(Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Landroid/os/Vibrator;

    iput-object p1, p0, Lcom/android/settingslib/PrimarySwitchPreference;->mVibrator:Landroid/os/Vibrator;

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 0

    .line 63
    invoke-direct {p0, p1, p2}, Lcom/android/settingslib/RestrictedPreference;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    const/4 p2, 0x1

    .line 49
    iput-boolean p2, p0, Lcom/android/settingslib/PrimarySwitchPreference;->mEnableSwitch:Z

    .line 64
    const-class p2, Landroid/os/Vibrator;

    invoke-virtual {p1, p2}, Landroid/content/Context;->getSystemService(Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Landroid/os/Vibrator;

    iput-object p1, p0, Lcom/android/settingslib/PrimarySwitchPreference;->mVibrator:Landroid/os/Vibrator;

    return-void
.end method

.method private synthetic lambda$onBindViewHolder$0(Landroid/view/View;)V
    .locals 2

    .line 83
    iget-object p1, p0, Lcom/android/settingslib/PrimarySwitchPreference;->mSwitch:Landroid/widget/Switch;

    if-eqz p1, :cond_0

    invoke-virtual {p1}, Landroid/widget/Switch;->isEnabled()Z

    move-result p1

    if-nez p1, :cond_0

    return-void

    .line 86
    :cond_0
    iget-boolean p1, p0, Lcom/android/settingslib/PrimarySwitchPreference;->mChecked:Z

    xor-int/lit8 p1, p1, 0x1

    .line 87
    invoke-static {p1}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v0

    invoke-virtual {p0, v0}, Landroidx/preference/Preference;->callChangeListener(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_1

    .line 88
    invoke-virtual {p0}, Landroidx/preference/Preference;->getKey()Ljava/lang/String;

    move-result-object v0

    iget-object v1, p0, Lcom/android/settingslib/PrimarySwitchPreference;->mSwitch:Landroid/widget/Switch;

    invoke-static {v0, v1}, Lcom/android/settingslib/core/instrumentation/SettingsJankMonitor;->detectToggleJank(Ljava/lang/String;Landroid/view/View;)V

    .line 89
    invoke-virtual {p0, p1}, Lcom/android/settingslib/PrimarySwitchPreference;->setChecked(Z)V

    .line 90
    invoke-virtual {p0, p1}, Landroidx/preference/Preference;->persistBoolean(Z)Z

    .line 92
    :cond_1
    iget-object p0, p0, Lcom/android/settingslib/PrimarySwitchPreference;->mVibrator:Landroid/os/Vibrator;

    sget-object p1, Lcom/android/settingslib/PrimarySwitchPreference;->EFFECT_CLICK:Landroid/os/VibrationEffect;

    sget-object v0, Lcom/android/settingslib/PrimarySwitchPreference;->VIBRATION_ATTRIBUTES_SWITCH:Landroid/os/VibrationAttributes;

    invoke-virtual {p0, p1, v0}, Landroid/os/Vibrator;->vibrate(Landroid/os/VibrationEffect;Landroid/os/VibrationAttributes;)V

    return-void
.end method

.method private static synthetic lambda$onBindViewHolder$1(Landroid/view/View;Landroid/view/MotionEvent;)Z
    .locals 0

    .line 97
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getActionMasked()I

    move-result p0

    const/4 p1, 0x2

    if-ne p0, p1, :cond_0

    const/4 p0, 0x1

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    :goto_0
    return p0
.end method


# virtual methods
.method public getCheckedState()Ljava/lang/Boolean;
    .locals 1
    .annotation build Landroidx/annotation/Keep;
    .end annotation

    .line 117
    iget-boolean v0, p0, Lcom/android/settingslib/PrimarySwitchPreference;->mCheckedSet:Z

    if-eqz v0, :cond_0

    iget-boolean p0, p0, Lcom/android/settingslib/PrimarySwitchPreference;->mChecked:Z

    invoke-static {p0}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object p0

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    :goto_0
    return-object p0
.end method

.method protected getSecondTargetResId()I
    .locals 0

    .line 74
    sget p0, Lcom/android/settingslib/R$layout;->preference_widget_primary_switch:I

    return p0
.end method

.method public getSwitch()Landroid/widget/Switch;
    .locals 0

    .line 159
    iget-object p0, p0, Lcom/android/settingslib/PrimarySwitchPreference;->mSwitch:Landroid/widget/Switch;

    return-object p0
.end method

.method public isChecked()Z
    .locals 1

    .line 107
    iget-object v0, p0, Lcom/android/settingslib/PrimarySwitchPreference;->mSwitch:Landroid/widget/Switch;

    if-eqz v0, :cond_0

    iget-boolean p0, p0, Lcom/android/settingslib/PrimarySwitchPreference;->mChecked:Z

    if-eqz p0, :cond_0

    const/4 p0, 0x1

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    :goto_0
    return p0
.end method

.method public onBindViewHolder(Landroidx/preference/PreferenceViewHolder;)V
    .locals 1

    .line 79
    invoke-super {p0, p1}, Lcom/android/settingslib/RestrictedPreference;->onBindViewHolder(Landroidx/preference/PreferenceViewHolder;)V

    .line 80
    sget v0, Lcom/android/settingslib/R$id;->switchWidget:I

    invoke-virtual {p1, v0}, Landroidx/preference/PreferenceViewHolder;->findViewById(I)Landroid/view/View;

    move-result-object p1

    check-cast p1, Landroid/widget/Switch;

    iput-object p1, p0, Lcom/android/settingslib/PrimarySwitchPreference;->mSwitch:Landroid/widget/Switch;

    if-eqz p1, :cond_0

    .line 82
    new-instance v0, Lcom/android/settingslib/PrimarySwitchPreference$$ExternalSyntheticLambda0;

    invoke-direct {v0, p0}, Lcom/android/settingslib/PrimarySwitchPreference$$ExternalSyntheticLambda0;-><init>(Lcom/android/settingslib/PrimarySwitchPreference;)V

    invoke-virtual {p1, v0}, Landroid/widget/Switch;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 96
    iget-object p1, p0, Lcom/android/settingslib/PrimarySwitchPreference;->mSwitch:Landroid/widget/Switch;

    new-instance v0, Lcom/android/settingslib/PrimarySwitchPreference$$ExternalSyntheticLambda1;

    invoke-direct {v0}, Lcom/android/settingslib/PrimarySwitchPreference$$ExternalSyntheticLambda1;-><init>()V

    invoke-virtual {p1, v0}, Landroid/widget/Switch;->setOnTouchListener(Landroid/view/View$OnTouchListener;)V

    .line 100
    iget-object p1, p0, Lcom/android/settingslib/PrimarySwitchPreference;->mSwitch:Landroid/widget/Switch;

    invoke-virtual {p0}, Landroidx/preference/Preference;->getTitle()Ljava/lang/CharSequence;

    move-result-object v0

    invoke-virtual {p1, v0}, Landroid/widget/Switch;->setContentDescription(Ljava/lang/CharSequence;)V

    .line 101
    iget-object p1, p0, Lcom/android/settingslib/PrimarySwitchPreference;->mSwitch:Landroid/widget/Switch;

    iget-boolean v0, p0, Lcom/android/settingslib/PrimarySwitchPreference;->mChecked:Z

    invoke-virtual {p1, v0}, Landroid/widget/Switch;->setChecked(Z)V

    .line 102
    iget-object p1, p0, Lcom/android/settingslib/PrimarySwitchPreference;->mSwitch:Landroid/widget/Switch;

    iget-boolean p0, p0, Lcom/android/settingslib/PrimarySwitchPreference;->mEnableSwitch:Z

    invoke-virtual {p1, p0}, Landroid/widget/Switch;->setEnabled(Z)V

    :cond_0
    return-void
.end method

.method public setChecked(Z)V
    .locals 2

    .line 127
    iget-boolean v0, p0, Lcom/android/settingslib/PrimarySwitchPreference;->mChecked:Z

    const/4 v1, 0x1

    if-eq v0, p1, :cond_0

    move v0, v1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    if-nez v0, :cond_1

    .line 128
    iget-boolean v0, p0, Lcom/android/settingslib/PrimarySwitchPreference;->mCheckedSet:Z

    if-nez v0, :cond_2

    .line 129
    :cond_1
    iput-boolean p1, p0, Lcom/android/settingslib/PrimarySwitchPreference;->mChecked:Z

    .line 130
    iput-boolean v1, p0, Lcom/android/settingslib/PrimarySwitchPreference;->mCheckedSet:Z

    .line 131
    iget-object p0, p0, Lcom/android/settingslib/PrimarySwitchPreference;->mSwitch:Landroid/widget/Switch;

    if-eqz p0, :cond_2

    .line 132
    invoke-virtual {p0, p1}, Landroid/widget/Switch;->setChecked(Z)V

    :cond_2
    return-void
.end method

.method public setSwitchEnabled(Z)V
    .locals 0

    .line 143
    iput-boolean p1, p0, Lcom/android/settingslib/PrimarySwitchPreference;->mEnableSwitch:Z

    .line 144
    iget-object p0, p0, Lcom/android/settingslib/PrimarySwitchPreference;->mSwitch:Landroid/widget/Switch;

    if-eqz p0, :cond_0

    .line 145
    invoke-virtual {p0, p1}, Landroid/widget/Switch;->setEnabled(Z)V

    :cond_0
    return-void
.end method

.method protected shouldHideSecondTarget()Z
    .locals 0

    .line 164
    invoke-virtual {p0}, Lcom/android/settingslib/PrimarySwitchPreference;->getSecondTargetResId()I

    move-result p0

    if-nez p0, :cond_0

    const/4 p0, 0x1

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    :goto_0
    return p0
.end method
