.class public Lorg/nameless/custom/preference/SwitchPreference;
.super Landroidx/preference/SwitchPreference;
.source "SwitchPreference.java"


# static fields
.field private static final EFFECT_CLICK:Landroid/os/VibrationEffect;


# instance fields
.field private final mVibrator:Landroid/os/Vibrator;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    const/4 v0, 0x0

    .line 31
    invoke-static {v0}, Landroid/os/VibrationEffect;->createPredefined(I)Landroid/os/VibrationEffect;

    move-result-object v0

    sput-object v0, Lorg/nameless/custom/preference/SwitchPreference;->EFFECT_CLICK:Landroid/os/VibrationEffect;

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;)V
    .locals 1

    const/4 v0, 0x0

    .line 52
    invoke-direct {p0, p1, v0}, Lorg/nameless/custom/preference/SwitchPreference;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 2

    .line 46
    sget v0, Landroidx/preference/R$attr;->switchPreferenceStyle:I

    const v1, 0x101036d

    invoke-static {p1, v0, v1}, Landroidx/core/content/res/TypedArrayUtils;->getAttr(Landroid/content/Context;II)I

    move-result v0

    invoke-direct {p0, p1, p2, v0}, Lorg/nameless/custom/preference/SwitchPreference;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V
    .locals 1

    const/4 v0, 0x0

    .line 42
    invoke-direct {p0, p1, p2, p3, v0}, Lorg/nameless/custom/preference/SwitchPreference;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;II)V

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;II)V
    .locals 0

    .line 37
    invoke-direct {p0, p1, p2, p3, p4}, Landroidx/preference/SwitchPreference;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;II)V

    const-string p2, "vibrator"

    .line 38
    invoke-virtual {p1, p2}, Landroid/content/Context;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Landroid/os/Vibrator;

    iput-object p1, p0, Lorg/nameless/custom/preference/SwitchPreference;->mVibrator:Landroid/os/Vibrator;

    return-void
.end method


# virtual methods
.method protected performClick(Landroid/view/View;)V
    .locals 1

    .line 57
    invoke-super {p0, p1}, Landroidx/preference/SwitchPreference;->performClick(Landroid/view/View;)V

    .line 58
    iget-object p0, p0, Lorg/nameless/custom/preference/SwitchPreference;->mVibrator:Landroid/os/Vibrator;

    sget-object p1, Lorg/nameless/custom/preference/SwitchPreference;->EFFECT_CLICK:Landroid/os/VibrationEffect;

    sget-object v0, Lorg/nameless/vibrator/CustomVibrationAttributes;->VIBRATION_ATTRIBUTES_SWITCH:Landroid/os/VibrationAttributes;

    invoke-virtual {p0, p1, v0}, Landroid/os/Vibrator;->vibrate(Landroid/os/VibrationEffect;Landroid/os/VibrationAttributes;)V

    return-void
.end method
