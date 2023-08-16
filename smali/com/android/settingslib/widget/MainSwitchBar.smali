.class public Lcom/android/settingslib/widget/MainSwitchBar;
.super Landroid/widget/LinearLayout;
.source "MainSwitchBar.java"

# interfaces
.implements Landroid/widget/CompoundButton$OnCheckedChangeListener;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/android/settingslib/widget/MainSwitchBar$SavedState;
    }
.end annotation


# static fields
.field private static final EFFECT_CLICK:Landroid/os/VibrationEffect;

.field private static final VIBRATION_ATTRIBUTES_SWITCH:Landroid/os/VibrationAttributes;


# instance fields
.field private mBackgroundActivatedColor:I

.field private mBackgroundColor:I

.field private mBackgroundDisabled:Landroid/graphics/drawable/Drawable;

.field private mBackgroundOff:Landroid/graphics/drawable/Drawable;

.field private mBackgroundOn:Landroid/graphics/drawable/Drawable;

.field private mFrameView:Landroid/view/View;

.field private mShouldVibrate:Z

.field protected mSwitch:Landroid/widget/Switch;

.field private final mSwitchChangeListeners:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "Lcom/android/settingslib/widget/OnMainSwitchChangeListener;",
            ">;"
        }
    .end annotation
.end field

.field protected mTextView:Landroid/widget/TextView;

.field private final mVibrator:Landroid/os/Vibrator;


# direct methods
.method public static synthetic $r8$lambda$rN8bSl54RQDz28q33LXHW78-FT8(Lcom/android/settingslib/widget/MainSwitchBar;Landroid/widget/Switch;Z)V
    .locals 0

    invoke-direct {p0, p1, p2}, Lcom/android/settingslib/widget/MainSwitchBar;->lambda$new$0(Landroid/widget/Switch;Z)V

    return-void
.end method

.method static constructor <clinit>()V
    .locals 1

    const/16 v0, 0xd4

    .line 50
    invoke-static {v0}, Landroid/os/VibrationAttributes;->createForUsage(I)Landroid/os/VibrationAttributes;

    move-result-object v0

    sput-object v0, Lcom/android/settingslib/widget/MainSwitchBar;->VIBRATION_ATTRIBUTES_SWITCH:Landroid/os/VibrationAttributes;

    const/4 v0, 0x0

    .line 52
    invoke-static {v0}, Landroid/os/VibrationEffect;->createPredefined(I)Landroid/os/VibrationEffect;

    move-result-object v0

    sput-object v0, Lcom/android/settingslib/widget/MainSwitchBar;->EFFECT_CLICK:Landroid/os/VibrationEffect;

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;)V
    .locals 1

    const/4 v0, 0x0

    .line 73
    invoke-direct {p0, p1, v0}, Lcom/android/settingslib/widget/MainSwitchBar;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 1

    const/4 v0, 0x0

    .line 77
    invoke-direct {p0, p1, p2, v0}, Lcom/android/settingslib/widget/MainSwitchBar;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V
    .locals 1

    const/4 v0, 0x0

    .line 81
    invoke-direct {p0, p1, p2, p3, v0}, Lcom/android/settingslib/widget/MainSwitchBar;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;II)V

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;II)V
    .locals 2

    .line 86
    invoke-direct {p0, p1, p2, p3, p4}, Landroid/widget/LinearLayout;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;II)V

    .line 56
    new-instance p3, Ljava/util/ArrayList;

    invoke-direct {p3}, Ljava/util/ArrayList;-><init>()V

    iput-object p3, p0, Lcom/android/settingslib/widget/MainSwitchBar;->mSwitchChangeListeners:Ljava/util/List;

    const/4 p3, 0x1

    .line 70
    iput-boolean p3, p0, Lcom/android/settingslib/widget/MainSwitchBar;->mShouldVibrate:Z

    .line 88
    invoke-static {p1}, Landroid/view/LayoutInflater;->from(Landroid/content/Context;)Landroid/view/LayoutInflater;

    move-result-object p4

    sget v0, Lcom/android/settingslib/widget/R$layout;->settingslib_main_switch_bar:I

    invoke-virtual {p4, v0, p0}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;)Landroid/view/View;

    .line 90
    invoke-static {}, Lcom/android/settingslib/utils/BuildCompatUtils;->isAtLeastS()Z

    move-result p4

    const/4 v0, 0x0

    if-nez p4, :cond_0

    new-array p4, p3, [I

    const v1, 0x1010435

    aput v1, p4, v0

    .line 91
    invoke-virtual {p1, p4}, Landroid/content/Context;->obtainStyledAttributes([I)Landroid/content/res/TypedArray;

    move-result-object p4

    .line 93
    invoke-virtual {p4, v0, v0}, Landroid/content/res/TypedArray;->getColor(II)I

    move-result v1

    iput v1, p0, Lcom/android/settingslib/widget/MainSwitchBar;->mBackgroundActivatedColor:I

    .line 94
    sget v1, Lcom/android/settingslib/widget/R$color;->material_grey_600:I

    invoke-virtual {p1, v1}, Landroid/content/Context;->getColor(I)I

    move-result v1

    iput v1, p0, Lcom/android/settingslib/widget/MainSwitchBar;->mBackgroundColor:I

    .line 95
    invoke-virtual {p4}, Landroid/content/res/TypedArray;->recycle()V

    .line 98
    :cond_0
    invoke-virtual {p0, p3}, Landroid/widget/LinearLayout;->setFocusable(Z)V

    .line 99
    invoke-virtual {p0, p3}, Landroid/widget/LinearLayout;->setClickable(Z)V

    .line 101
    sget p3, Lcom/android/settingslib/widget/R$id;->frame:I

    invoke-virtual {p0, p3}, Landroid/widget/LinearLayout;->findViewById(I)Landroid/view/View;

    move-result-object p3

    iput-object p3, p0, Lcom/android/settingslib/widget/MainSwitchBar;->mFrameView:Landroid/view/View;

    .line 102
    sget p3, Lcom/android/settingslib/widget/R$id;->switch_text:I

    invoke-virtual {p0, p3}, Landroid/widget/LinearLayout;->findViewById(I)Landroid/view/View;

    move-result-object p3

    check-cast p3, Landroid/widget/TextView;

    iput-object p3, p0, Lcom/android/settingslib/widget/MainSwitchBar;->mTextView:Landroid/widget/TextView;

    const p3, 0x1020040

    .line 103
    invoke-virtual {p0, p3}, Landroid/widget/LinearLayout;->findViewById(I)Landroid/view/View;

    move-result-object p3

    check-cast p3, Landroid/widget/Switch;

    iput-object p3, p0, Lcom/android/settingslib/widget/MainSwitchBar;->mSwitch:Landroid/widget/Switch;

    .line 104
    invoke-static {}, Lcom/android/settingslib/utils/BuildCompatUtils;->isAtLeastS()Z

    move-result p3

    if-eqz p3, :cond_1

    .line 105
    invoke-virtual {p0}, Landroid/widget/LinearLayout;->getContext()Landroid/content/Context;

    move-result-object p3

    sget p4, Lcom/android/settingslib/widget/R$drawable;->settingslib_switch_bar_bg_on:I

    invoke-virtual {p3, p4}, Landroid/content/Context;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object p3

    iput-object p3, p0, Lcom/android/settingslib/widget/MainSwitchBar;->mBackgroundOn:Landroid/graphics/drawable/Drawable;

    .line 106
    invoke-virtual {p0}, Landroid/widget/LinearLayout;->getContext()Landroid/content/Context;

    move-result-object p3

    sget p4, Lcom/android/settingslib/widget/R$drawable;->settingslib_switch_bar_bg_off:I

    invoke-virtual {p3, p4}, Landroid/content/Context;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object p3

    iput-object p3, p0, Lcom/android/settingslib/widget/MainSwitchBar;->mBackgroundOff:Landroid/graphics/drawable/Drawable;

    .line 107
    invoke-virtual {p0}, Landroid/widget/LinearLayout;->getContext()Landroid/content/Context;

    move-result-object p3

    sget p4, Lcom/android/settingslib/widget/R$drawable;->settingslib_switch_bar_bg_disabled:I

    invoke-virtual {p3, p4}, Landroid/content/Context;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object p3

    iput-object p3, p0, Lcom/android/settingslib/widget/MainSwitchBar;->mBackgroundDisabled:Landroid/graphics/drawable/Drawable;

    .line 110
    :cond_1
    new-instance p3, Lcom/android/settingslib/widget/MainSwitchBar$$ExternalSyntheticLambda0;

    invoke-direct {p3, p0}, Lcom/android/settingslib/widget/MainSwitchBar$$ExternalSyntheticLambda0;-><init>(Lcom/android/settingslib/widget/MainSwitchBar;)V

    invoke-virtual {p0, p3}, Lcom/android/settingslib/widget/MainSwitchBar;->addOnSwitchChangeListener(Lcom/android/settingslib/widget/OnMainSwitchChangeListener;)V

    .line 112
    iget-object p3, p0, Lcom/android/settingslib/widget/MainSwitchBar;->mSwitch:Landroid/widget/Switch;

    invoke-virtual {p3}, Landroid/widget/Switch;->getVisibility()I

    move-result p3

    if-nez p3, :cond_2

    .line 113
    iget-object p3, p0, Lcom/android/settingslib/widget/MainSwitchBar;->mSwitch:Landroid/widget/Switch;

    invoke-virtual {p3, p0}, Landroid/widget/Switch;->setOnCheckedChangeListener(Landroid/widget/CompoundButton$OnCheckedChangeListener;)V

    .line 116
    :cond_2
    iget-object p3, p0, Lcom/android/settingslib/widget/MainSwitchBar;->mSwitch:Landroid/widget/Switch;

    invoke-virtual {p3}, Landroid/widget/Switch;->isChecked()Z

    move-result p3

    invoke-virtual {p0, p3}, Lcom/android/settingslib/widget/MainSwitchBar;->setChecked(Z)V

    if-eqz p2, :cond_3

    .line 119
    sget-object p3, Landroidx/preference/R$styleable;->Preference:[I

    invoke-virtual {p1, p2, p3, v0, v0}, Landroid/content/Context;->obtainStyledAttributes(Landroid/util/AttributeSet;[III)Landroid/content/res/TypedArray;

    move-result-object p2

    .line 122
    sget p3, Landroidx/preference/R$styleable;->Preference_android_title:I

    invoke-virtual {p2, p3}, Landroid/content/res/TypedArray;->getText(I)Ljava/lang/CharSequence;

    move-result-object p3

    .line 124
    invoke-virtual {p0, p3}, Lcom/android/settingslib/widget/MainSwitchBar;->setTitle(Ljava/lang/CharSequence;)V

    .line 125
    invoke-virtual {p2}, Landroid/content/res/TypedArray;->recycle()V

    .line 128
    :cond_3
    iget-object p2, p0, Lcom/android/settingslib/widget/MainSwitchBar;->mSwitch:Landroid/widget/Switch;

    invoke-virtual {p2}, Landroid/widget/Switch;->isChecked()Z

    move-result p2

    invoke-direct {p0, p2}, Lcom/android/settingslib/widget/MainSwitchBar;->setBackground(Z)V

    .line 130
    const-class p2, Landroid/os/Vibrator;

    invoke-virtual {p1, p2}, Landroid/content/Context;->getSystemService(Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Landroid/os/Vibrator;

    iput-object p1, p0, Lcom/android/settingslib/widget/MainSwitchBar;->mVibrator:Landroid/os/Vibrator;

    return-void
.end method

.method private synthetic lambda$new$0(Landroid/widget/Switch;Z)V
    .locals 0

    .line 110
    invoke-virtual {p0, p2}, Lcom/android/settingslib/widget/MainSwitchBar;->setChecked(Z)V

    return-void
.end method

.method private propagateChecked(Z)V
    .locals 4

    .line 246
    invoke-direct {p0, p1}, Lcom/android/settingslib/widget/MainSwitchBar;->setBackground(Z)V

    .line 248
    iget-object v0, p0, Lcom/android/settingslib/widget/MainSwitchBar;->mSwitchChangeListeners:Ljava/util/List;

    invoke-interface {v0}, Ljava/util/List;->size()I

    move-result v0

    const/4 v1, 0x0

    :goto_0
    if-ge v1, v0, :cond_0

    .line 250
    iget-object v2, p0, Lcom/android/settingslib/widget/MainSwitchBar;->mSwitchChangeListeners:Ljava/util/List;

    invoke-interface {v2, v1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/android/settingslib/widget/OnMainSwitchChangeListener;

    iget-object v3, p0, Lcom/android/settingslib/widget/MainSwitchBar;->mSwitch:Landroid/widget/Switch;

    invoke-interface {v2, v3, p1}, Lcom/android/settingslib/widget/OnMainSwitchChangeListener;->onSwitchChanged(Landroid/widget/Switch;Z)V

    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    :cond_0
    return-void
.end method

.method private setBackground(Z)V
    .locals 1

    .line 255
    invoke-static {}, Lcom/android/settingslib/utils/BuildCompatUtils;->isAtLeastS()Z

    move-result v0

    if-nez v0, :cond_1

    if-eqz p1, :cond_0

    .line 256
    iget p1, p0, Lcom/android/settingslib/widget/MainSwitchBar;->mBackgroundActivatedColor:I

    goto :goto_0

    :cond_0
    iget p1, p0, Lcom/android/settingslib/widget/MainSwitchBar;->mBackgroundColor:I

    :goto_0
    invoke-virtual {p0, p1}, Landroid/widget/LinearLayout;->setBackgroundColor(I)V

    goto :goto_2

    .line 258
    :cond_1
    iget-object v0, p0, Lcom/android/settingslib/widget/MainSwitchBar;->mFrameView:Landroid/view/View;

    if-eqz p1, :cond_2

    iget-object p0, p0, Lcom/android/settingslib/widget/MainSwitchBar;->mBackgroundOn:Landroid/graphics/drawable/Drawable;

    goto :goto_1

    :cond_2
    iget-object p0, p0, Lcom/android/settingslib/widget/MainSwitchBar;->mBackgroundOff:Landroid/graphics/drawable/Drawable;

    :goto_1
    invoke-virtual {v0, p0}, Landroid/view/View;->setBackground(Landroid/graphics/drawable/Drawable;)V

    :goto_2
    return-void
.end method


# virtual methods
.method public addOnSwitchChangeListener(Lcom/android/settingslib/widget/OnMainSwitchChangeListener;)V
    .locals 1

    .line 216
    iget-object v0, p0, Lcom/android/settingslib/widget/MainSwitchBar;->mSwitchChangeListeners:Ljava/util/List;

    invoke-interface {v0, p1}, Ljava/util/List;->contains(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_0

    .line 217
    iget-object p0, p0, Lcom/android/settingslib/widget/MainSwitchBar;->mSwitchChangeListeners:Ljava/util/List;

    invoke-interface {p0, p1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    :cond_0
    return-void
.end method

.method public isChecked()Z
    .locals 0

    .line 161
    iget-object p0, p0, Lcom/android/settingslib/widget/MainSwitchBar;->mSwitch:Landroid/widget/Switch;

    invoke-virtual {p0}, Landroid/widget/Switch;->isChecked()Z

    move-result p0

    return p0
.end method

.method public isShowing()Z
    .locals 0

    .line 209
    invoke-virtual {p0}, Landroid/widget/LinearLayout;->getVisibility()I

    move-result p0

    if-nez p0, :cond_0

    const/4 p0, 0x1

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    :goto_0
    return p0
.end method

.method public onCheckedChanged(Landroid/widget/CompoundButton;Z)V
    .locals 0

    .line 135
    invoke-direct {p0, p2}, Lcom/android/settingslib/widget/MainSwitchBar;->propagateChecked(Z)V

    return-void
.end method

.method public onRestoreInstanceState(Landroid/os/Parcelable;)V
    .locals 2

    .line 320
    check-cast p1, Lcom/android/settingslib/widget/MainSwitchBar$SavedState;

    .line 322
    invoke-virtual {p1}, Landroid/view/View$BaseSavedState;->getSuperState()Landroid/os/Parcelable;

    move-result-object v0

    invoke-super {p0, v0}, Landroid/widget/LinearLayout;->onRestoreInstanceState(Landroid/os/Parcelable;)V

    .line 324
    iget-object v0, p0, Lcom/android/settingslib/widget/MainSwitchBar;->mSwitch:Landroid/widget/Switch;

    iget-boolean v1, p1, Lcom/android/settingslib/widget/MainSwitchBar$SavedState;->mChecked:Z

    invoke-virtual {v0, v1}, Landroid/widget/Switch;->setChecked(Z)V

    .line 325
    iget-boolean v0, p1, Lcom/android/settingslib/widget/MainSwitchBar$SavedState;->mChecked:Z

    invoke-virtual {p0, v0}, Lcom/android/settingslib/widget/MainSwitchBar;->setChecked(Z)V

    .line 326
    iget-boolean v0, p1, Lcom/android/settingslib/widget/MainSwitchBar$SavedState;->mChecked:Z

    invoke-direct {p0, v0}, Lcom/android/settingslib/widget/MainSwitchBar;->setBackground(Z)V

    .line 327
    iget-boolean v0, p1, Lcom/android/settingslib/widget/MainSwitchBar$SavedState;->mVisible:Z

    if-eqz v0, :cond_0

    const/4 v0, 0x0

    goto :goto_0

    :cond_0
    const/16 v0, 0x8

    :goto_0
    invoke-virtual {p0, v0}, Landroid/widget/LinearLayout;->setVisibility(I)V

    .line 328
    iget-object v0, p0, Lcom/android/settingslib/widget/MainSwitchBar;->mSwitch:Landroid/widget/Switch;

    iget-boolean p1, p1, Lcom/android/settingslib/widget/MainSwitchBar$SavedState;->mVisible:Z

    if-eqz p1, :cond_1

    move-object p1, p0

    goto :goto_1

    :cond_1
    const/4 p1, 0x0

    :goto_1
    invoke-virtual {v0, p1}, Landroid/widget/Switch;->setOnCheckedChangeListener(Landroid/widget/CompoundButton$OnCheckedChangeListener;)V

    .line 330
    invoke-virtual {p0}, Landroid/widget/LinearLayout;->requestLayout()V

    return-void
.end method

.method public onSaveInstanceState()Landroid/os/Parcelable;
    .locals 2

    .line 310
    invoke-super {p0}, Landroid/widget/LinearLayout;->onSaveInstanceState()Landroid/os/Parcelable;

    move-result-object v0

    .line 312
    new-instance v1, Lcom/android/settingslib/widget/MainSwitchBar$SavedState;

    invoke-direct {v1, v0}, Lcom/android/settingslib/widget/MainSwitchBar$SavedState;-><init>(Landroid/os/Parcelable;)V

    .line 313
    iget-object v0, p0, Lcom/android/settingslib/widget/MainSwitchBar;->mSwitch:Landroid/widget/Switch;

    invoke-virtual {v0}, Landroid/widget/Switch;->isChecked()Z

    move-result v0

    iput-boolean v0, v1, Lcom/android/settingslib/widget/MainSwitchBar$SavedState;->mChecked:Z

    .line 314
    invoke-virtual {p0}, Lcom/android/settingslib/widget/MainSwitchBar;->isShowing()Z

    move-result p0

    iput-boolean p0, v1, Lcom/android/settingslib/widget/MainSwitchBar$SavedState;->mVisible:Z

    return-object v1
.end method

.method public performClick()Z
    .locals 3

    .line 140
    iget-object v0, p0, Lcom/android/settingslib/widget/MainSwitchBar;->mSwitch:Landroid/widget/Switch;

    invoke-virtual {v0}, Landroid/widget/Switch;->performClick()Z

    .line 141
    iget-boolean v0, p0, Lcom/android/settingslib/widget/MainSwitchBar;->mShouldVibrate:Z

    if-eqz v0, :cond_0

    .line 142
    iget-object v0, p0, Lcom/android/settingslib/widget/MainSwitchBar;->mVibrator:Landroid/os/Vibrator;

    sget-object v1, Lcom/android/settingslib/widget/MainSwitchBar;->EFFECT_CLICK:Landroid/os/VibrationEffect;

    sget-object v2, Lcom/android/settingslib/widget/MainSwitchBar;->VIBRATION_ATTRIBUTES_SWITCH:Landroid/os/VibrationAttributes;

    invoke-virtual {v0, v1, v2}, Landroid/os/Vibrator;->vibrate(Landroid/os/VibrationEffect;Landroid/os/VibrationAttributes;)V

    .line 144
    :cond_0
    invoke-super {p0}, Landroid/widget/LinearLayout;->performClick()Z

    move-result p0

    return p0
.end method

.method public setChecked(Z)V
    .locals 1

    .line 151
    iget-object v0, p0, Lcom/android/settingslib/widget/MainSwitchBar;->mSwitch:Landroid/widget/Switch;

    if-eqz v0, :cond_0

    .line 152
    invoke-virtual {v0, p1}, Landroid/widget/Switch;->setChecked(Z)V

    .line 154
    :cond_0
    invoke-direct {p0, p1}, Lcom/android/settingslib/widget/MainSwitchBar;->setBackground(Z)V

    return-void
.end method

.method public setEnabled(Z)V
    .locals 1

    .line 232
    invoke-super {p0, p1}, Landroid/widget/LinearLayout;->setEnabled(Z)V

    .line 233
    iget-object v0, p0, Lcom/android/settingslib/widget/MainSwitchBar;->mTextView:Landroid/widget/TextView;

    invoke-virtual {v0, p1}, Landroid/widget/TextView;->setEnabled(Z)V

    .line 234
    iget-object v0, p0, Lcom/android/settingslib/widget/MainSwitchBar;->mSwitch:Landroid/widget/Switch;

    invoke-virtual {v0, p1}, Landroid/widget/Switch;->setEnabled(Z)V

    .line 236
    invoke-static {}, Lcom/android/settingslib/utils/BuildCompatUtils;->isAtLeastS()Z

    move-result v0

    if-eqz v0, :cond_2

    if-eqz p1, :cond_1

    .line 238
    iget-object p1, p0, Lcom/android/settingslib/widget/MainSwitchBar;->mFrameView:Landroid/view/View;

    invoke-virtual {p0}, Lcom/android/settingslib/widget/MainSwitchBar;->isChecked()Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object p0, p0, Lcom/android/settingslib/widget/MainSwitchBar;->mBackgroundOn:Landroid/graphics/drawable/Drawable;

    goto :goto_0

    :cond_0
    iget-object p0, p0, Lcom/android/settingslib/widget/MainSwitchBar;->mBackgroundOff:Landroid/graphics/drawable/Drawable;

    :goto_0
    invoke-virtual {p1, p0}, Landroid/view/View;->setBackground(Landroid/graphics/drawable/Drawable;)V

    goto :goto_1

    .line 240
    :cond_1
    iget-object p1, p0, Lcom/android/settingslib/widget/MainSwitchBar;->mFrameView:Landroid/view/View;

    iget-object p0, p0, Lcom/android/settingslib/widget/MainSwitchBar;->mBackgroundDisabled:Landroid/graphics/drawable/Drawable;

    invoke-virtual {p1, p0}, Landroid/view/View;->setBackground(Landroid/graphics/drawable/Drawable;)V

    :cond_2
    :goto_1
    return-void
.end method

.method public setShouldVibrate(Z)V
    .locals 0

    .line 184
    iput-boolean p1, p0, Lcom/android/settingslib/widget/MainSwitchBar;->mShouldVibrate:Z

    return-void
.end method

.method public setTitle(Ljava/lang/CharSequence;)V
    .locals 0

    .line 175
    iget-object p0, p0, Lcom/android/settingslib/widget/MainSwitchBar;->mTextView:Landroid/widget/TextView;

    if-eqz p0, :cond_0

    .line 176
    invoke-virtual {p0, p1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    :cond_0
    return-void
.end method

.method public show()V
    .locals 1

    const/4 v0, 0x0

    .line 191
    invoke-virtual {p0, v0}, Landroid/widget/LinearLayout;->setVisibility(I)V

    .line 192
    iget-object v0, p0, Lcom/android/settingslib/widget/MainSwitchBar;->mSwitch:Landroid/widget/Switch;

    invoke-virtual {v0, p0}, Landroid/widget/Switch;->setOnCheckedChangeListener(Landroid/widget/CompoundButton$OnCheckedChangeListener;)V

    return-void
.end method
