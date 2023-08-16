.class public Lcom/android/settingslib/widget/AppSwitchPreference;
.super Landroidx/preference/SwitchPreference;
.source "AppSwitchPreference.java"


# static fields
.field private static final EFFECT_CLICK:Landroid/os/VibrationEffect;

.field private static final VIBRATION_ATTRIBUTES_SWITCH:Landroid/os/VibrationAttributes;


# instance fields
.field private final mVibrator:Landroid/os/Vibrator;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    const/16 v0, 0xd4

    .line 35
    invoke-static {v0}, Landroid/os/VibrationAttributes;->createForUsage(I)Landroid/os/VibrationAttributes;

    move-result-object v0

    sput-object v0, Lcom/android/settingslib/widget/AppSwitchPreference;->VIBRATION_ATTRIBUTES_SWITCH:Landroid/os/VibrationAttributes;

    const/4 v0, 0x0

    .line 37
    invoke-static {v0}, Landroid/os/VibrationEffect;->createPredefined(I)Landroid/os/VibrationEffect;

    move-result-object v0

    sput-object v0, Lcom/android/settingslib/widget/AppSwitchPreference;->EFFECT_CLICK:Landroid/os/VibrationEffect;

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 0

    .line 55
    invoke-direct {p0, p1, p2}, Landroidx/preference/SwitchPreference;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    .line 56
    sget p2, Lcom/android/settingslib/widget/R$layout;->preference_app:I

    invoke-virtual {p0, p2}, Landroidx/preference/Preference;->setLayoutResource(I)V

    .line 57
    const-class p2, Landroid/os/Vibrator;

    invoke-virtual {p1, p2}, Landroid/content/Context;->getSystemService(Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Landroid/os/Vibrator;

    iput-object p1, p0, Lcom/android/settingslib/widget/AppSwitchPreference;->mVibrator:Landroid/os/Vibrator;

    return-void
.end method


# virtual methods
.method public onBindViewHolder(Landroidx/preference/PreferenceViewHolder;)V
    .locals 0

    .line 68
    invoke-super {p0, p1}, Landroidx/preference/SwitchPreference;->onBindViewHolder(Landroidx/preference/PreferenceViewHolder;)V

    const p0, 0x1020040

    .line 69
    invoke-virtual {p1, p0}, Landroidx/preference/PreferenceViewHolder;->findViewById(I)Landroid/view/View;

    move-result-object p0

    if-eqz p0, :cond_0

    .line 71
    invoke-virtual {p0}, Landroid/view/View;->getRootView()Landroid/view/View;

    move-result-object p0

    const/4 p1, 0x1

    .line 72
    invoke-virtual {p0, p1}, Landroid/view/View;->setFilterTouchesWhenObscured(Z)V

    :cond_0
    return-void
.end method

.method protected performClick(Landroid/view/View;)V
    .locals 1

    .line 78
    invoke-super {p0, p1}, Landroidx/preference/SwitchPreference;->performClick(Landroid/view/View;)V

    .line 79
    iget-object p0, p0, Lcom/android/settingslib/widget/AppSwitchPreference;->mVibrator:Landroid/os/Vibrator;

    sget-object p1, Lcom/android/settingslib/widget/AppSwitchPreference;->EFFECT_CLICK:Landroid/os/VibrationEffect;

    sget-object v0, Lcom/android/settingslib/widget/AppSwitchPreference;->VIBRATION_ATTRIBUTES_SWITCH:Landroid/os/VibrationAttributes;

    invoke-virtual {p0, p1, v0}, Landroid/os/Vibrator;->vibrate(Landroid/os/VibrationEffect;Landroid/os/VibrationAttributes;)V

    return-void
.end method
