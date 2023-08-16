.class public Lorg/nameless/custom/preference/CustomSeekBarPreference;
.super Landroidx/preference/Preference;
.source "CustomSeekBarPreference.java"

# interfaces
.implements Landroid/widget/SeekBar$OnSeekBarChangeListener;
.implements Landroid/view/View$OnClickListener;
.implements Landroid/view/View$OnLongClickListener;


# static fields
.field private static final EFFECT_TICK:Landroid/os/VibrationEffect;


# instance fields
.field protected final TAG:Ljava/lang/String;

.field protected mContinuousUpdates:Z

.field protected mDefaultValue:I

.field protected mDefaultValueExists:Z

.field protected mInterval:I

.field protected mMaxValue:I

.field protected mMinValue:I

.field protected mMinusImageView:Landroid/widget/ImageView;

.field protected mPlusImageView:Landroid/widget/ImageView;

.field protected mResetImageView:Landroid/widget/ImageView;

.field protected mSeekBar:Landroid/widget/SeekBar;

.field protected mShowSign:Z

.field protected mTrackingTouch:Z

.field protected mTrackingValue:I

.field protected mUnits:Ljava/lang/String;

.field protected mValue:I

.field protected mValueTextView:Landroid/widget/TextView;

.field private final mVibrator:Landroid/os/Vibrator;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    const/4 v0, 0x2

    .line 48
    invoke-static {v0}, Landroid/os/VibrationEffect;->createPredefined(I)Landroid/os/VibrationEffect;

    move-result-object v0

    sput-object v0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->EFFECT_TICK:Landroid/os/VibrationEffect;

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;)V
    .locals 1

    const/4 v0, 0x0

    .line 124
    invoke-direct {p0, p1, v0}, Lorg/nameless/custom/preference/CustomSeekBarPreference;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 2

    .line 118
    sget v0, Landroidx/preference/R$attr;->preferenceStyle:I

    const v1, 0x101008e

    invoke-static {p1, v0, v1}, Landroidx/core/content/res/TypedArrayUtils;->getAttr(Landroid/content/Context;II)I

    move-result v0

    invoke-direct {p0, p1, p2, v0}, Lorg/nameless/custom/preference/CustomSeekBarPreference;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V
    .locals 1

    const/4 v0, 0x0

    .line 114
    invoke-direct {p0, p1, p2, p3, v0}, Lorg/nameless/custom/preference/CustomSeekBarPreference;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;II)V

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;II)V
    .locals 5

    const-string v0, "http://schemas.android.com/apk/res/com.android.settings"

    .line 74
    invoke-direct {p0, p1, p2, p3, p4}, Landroidx/preference/Preference;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;II)V

    .line 43
    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object p3

    invoke-virtual {p3}, Ljava/lang/Class;->getName()Ljava/lang/String;

    move-result-object p3

    iput-object p3, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->TAG:Ljava/lang/String;

    const/4 p3, 0x1

    .line 50
    iput p3, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mInterval:I

    const/4 p4, 0x0

    .line 51
    iput-boolean p4, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mShowSign:Z

    const-string v1, ""

    .line 52
    iput-object v1, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mUnits:Ljava/lang/String;

    .line 53
    iput-boolean p4, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mContinuousUpdates:Z

    .line 55
    iput p4, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mMinValue:I

    const/16 v1, 0x64

    .line 56
    iput v1, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mMaxValue:I

    .line 57
    iput-boolean p4, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mDefaultValueExists:Z

    .line 68
    iput-boolean p4, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mTrackingTouch:Z

    .line 76
    sget-object v1, Lorg/nameless/custom/R$styleable;->CustomSeekBarPreference:[I

    invoke-virtual {p1, p2, v1}, Landroid/content/Context;->obtainStyledAttributes(Landroid/util/AttributeSet;[I)Landroid/content/res/TypedArray;

    move-result-object v1

    .line 78
    :try_start_0
    sget v2, Lorg/nameless/custom/R$styleable;->CustomSeekBarPreference_showSign:I

    iget-boolean v3, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mShowSign:Z

    invoke-virtual {v1, v2, v3}, Landroid/content/res/TypedArray;->getBoolean(IZ)Z

    move-result v2

    iput-boolean v2, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mShowSign:Z

    .line 79
    sget v2, Lorg/nameless/custom/R$styleable;->CustomSeekBarPreference_units:I

    invoke-virtual {v1, v2}, Landroid/content/res/TypedArray;->getString(I)Ljava/lang/String;

    move-result-object v2

    if-eqz v2, :cond_0

    .line 81
    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    const-string v4, " "

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    iput-object v2, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mUnits:Ljava/lang/String;

    .line 82
    :cond_0
    sget v2, Lorg/nameless/custom/R$styleable;->CustomSeekBarPreference_continuousUpdates:I

    iget-boolean v3, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mContinuousUpdates:Z

    invoke-virtual {v1, v2, v3}, Landroid/content/res/TypedArray;->getBoolean(IZ)Z

    move-result v2

    iput-boolean v2, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mContinuousUpdates:Z
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 84
    invoke-virtual {v1}, Landroid/content/res/TypedArray;->recycle()V

    :try_start_1
    const-string v1, "interval"

    .line 88
    invoke-interface {p2, v0, v1}, Landroid/util/AttributeSet;->getAttributeValue(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    if-eqz v1, :cond_1

    .line 90
    invoke-static {v1}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;)I

    move-result v1

    iput v1, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mInterval:I
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_0

    goto :goto_0

    :catch_0
    move-exception v1

    .line 92
    iget-object v2, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->TAG:Ljava/lang/String;

    const-string v3, "Invalid interval value"

    invoke-static {v2, v3, v1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    .line 94
    :cond_1
    :goto_0
    iget v1, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mMinValue:I

    const-string v2, "min"

    invoke-interface {p2, v0, v2, v1}, Landroid/util/AttributeSet;->getAttributeIntValue(Ljava/lang/String;Ljava/lang/String;I)I

    move-result v0

    iput v0, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mMinValue:I

    .line 95
    iget v0, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mMaxValue:I

    const-string v1, "http://schemas.android.com/apk/res/android"

    const-string v2, "max"

    invoke-interface {p2, v1, v2, v0}, Landroid/util/AttributeSet;->getAttributeIntValue(Ljava/lang/String;Ljava/lang/String;I)I

    move-result v0

    iput v0, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mMaxValue:I

    .line 96
    iget v2, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mMinValue:I

    if-ge v0, v2, :cond_2

    .line 97
    iput v2, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mMaxValue:I

    :cond_2
    const-string v0, "defaultValue"

    .line 98
    invoke-interface {p2, v1, v0}, Landroid/util/AttributeSet;->getAttributeValue(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    if-eqz v0, :cond_3

    .line 99
    invoke-virtual {v0}, Ljava/lang/String;->isEmpty()Z

    move-result v1

    if-nez v1, :cond_3

    goto :goto_1

    :cond_3
    move p3, p4

    :goto_1
    iput-boolean p3, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mDefaultValueExists:Z

    if-eqz p3, :cond_4

    .line 101
    invoke-static {v0}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;)I

    move-result p3

    invoke-virtual {p0, p3}, Lorg/nameless/custom/preference/CustomSeekBarPreference;->getLimitedValue(I)I

    move-result p3

    iput p3, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mDefaultValue:I

    .line 102
    iput p3, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mValue:I

    goto :goto_2

    .line 104
    :cond_4
    iget p3, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mMinValue:I

    iput p3, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mValue:I

    .line 107
    :goto_2
    new-instance p3, Landroid/widget/SeekBar;

    invoke-direct {p3, p1, p2}, Landroid/widget/SeekBar;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    iput-object p3, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mSeekBar:Landroid/widget/SeekBar;

    .line 108
    sget p2, Lorg/nameless/custom/R$layout;->preference_custom_seekbar:I

    invoke-virtual {p0, p2}, Landroidx/preference/Preference;->setLayoutResource(I)V

    const-string p2, "vibrator"

    .line 110
    invoke-virtual {p1, p2}, Landroid/content/Context;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Landroid/os/Vibrator;

    iput-object p1, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mVibrator:Landroid/os/Vibrator;

    return-void

    :catchall_0
    move-exception p0

    .line 84
    invoke-virtual {v1}, Landroid/content/res/TypedArray;->recycle()V

    .line 85
    throw p0
.end method

.method private doHapticFeedback(Z)V
    .locals 1

    .line 364
    iget-object p0, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mVibrator:Landroid/os/Vibrator;

    sget-object v0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->EFFECT_TICK:Landroid/os/VibrationEffect;

    if-eqz p1, :cond_0

    .line 365
    sget-object p1, Lorg/nameless/vibrator/CustomVibrationAttributes;->VIBRATION_ATTRIBUTES_SLIDER_EDGE:Landroid/os/VibrationAttributes;

    goto :goto_0

    :cond_0
    sget-object p1, Lorg/nameless/vibrator/CustomVibrationAttributes;->VIBRATION_ATTRIBUTES_SLIDER:Landroid/os/VibrationAttributes;

    .line 364
    :goto_0
    invoke-virtual {p0, v0, p1}, Landroid/os/Vibrator;->vibrate(Landroid/os/VibrationEffect;Landroid/os/VibrationAttributes;)V

    return-void
.end method


# virtual methods
.method protected changeValue(I)V
    .locals 0

    return-void
.end method

.method protected getLimitedValue(I)I
    .locals 1

    .line 170
    iget v0, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mMinValue:I

    if-ge p1, v0, :cond_0

    move p1, v0

    goto :goto_0

    :cond_0
    iget p0, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mMaxValue:I

    if-le p1, p0, :cond_1

    move p1, p0

    :cond_1
    :goto_0
    return p1
.end method

.method protected getSeekValue(I)I
    .locals 1

    .line 174
    iget v0, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mMinValue:I

    sub-int/2addr v0, p1

    iget p0, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mInterval:I

    invoke-static {v0, p0}, Ljava/lang/Math;->floorDiv(II)I

    move-result p0

    rsub-int/lit8 p0, p0, 0x0

    return p0
.end method

.method protected getTextValue(I)Ljava/lang/String;
    .locals 2

    .line 178
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    iget-boolean v1, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mShowSign:Z

    if-eqz v1, :cond_0

    if-lez p1, :cond_0

    const-string v1, "+"

    goto :goto_0

    :cond_0
    const-string v1, ""

    :goto_0
    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-static {p1}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object p0, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mUnits:Ljava/lang/String;

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public onBindViewHolder(Landroidx/preference/PreferenceViewHolder;)V
    .locals 4

    .line 129
    invoke-super {p0, p1}, Landroidx/preference/Preference;->onBindViewHolder(Landroidx/preference/PreferenceViewHolder;)V

    .line 133
    :try_start_0
    iget-object v0, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mSeekBar:Landroid/widget/SeekBar;

    invoke-virtual {v0}, Landroid/widget/SeekBar;->getParent()Landroid/view/ViewParent;

    move-result-object v0

    .line 134
    sget v1, Lorg/nameless/custom/R$id;->seekbar:I

    invoke-virtual {p1, v1}, Landroidx/preference/PreferenceViewHolder;->findViewById(I)Landroid/view/View;

    move-result-object v1

    check-cast v1, Landroid/view/ViewGroup;

    if-eq v0, v1, :cond_1

    if-eqz v0, :cond_0

    .line 138
    check-cast v0, Landroid/view/ViewGroup;

    iget-object v2, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mSeekBar:Landroid/widget/SeekBar;

    invoke-virtual {v0, v2}, Landroid/view/ViewGroup;->removeView(Landroid/view/View;)V

    .line 141
    :cond_0
    invoke-virtual {v1}, Landroid/view/ViewGroup;->removeAllViews()V

    .line 142
    iget-object v0, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mSeekBar:Landroid/widget/SeekBar;

    const/4 v2, -0x1

    const/4 v3, -0x2

    invoke-virtual {v1, v0, v2, v3}, Landroid/view/ViewGroup;->addView(Landroid/view/View;II)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception v0

    .line 146
    iget-object v1, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->TAG:Ljava/lang/String;

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "Error binding view: "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/Exception;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-static {v1, v0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 149
    :cond_1
    :goto_0
    iget-object v0, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mSeekBar:Landroid/widget/SeekBar;

    iget v1, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mMaxValue:I

    invoke-virtual {p0, v1}, Lorg/nameless/custom/preference/CustomSeekBarPreference;->getSeekValue(I)I

    move-result v1

    invoke-virtual {v0, v1}, Landroid/widget/SeekBar;->setMax(I)V

    .line 150
    iget-object v0, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mSeekBar:Landroid/widget/SeekBar;

    iget v1, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mValue:I

    invoke-virtual {p0, v1}, Lorg/nameless/custom/preference/CustomSeekBarPreference;->getSeekValue(I)I

    move-result v1

    invoke-virtual {v0, v1}, Landroid/widget/SeekBar;->setProgress(I)V

    .line 151
    iget-object v0, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mSeekBar:Landroid/widget/SeekBar;

    invoke-virtual {p0}, Landroidx/preference/Preference;->isEnabled()Z

    move-result v1

    invoke-virtual {v0, v1}, Landroid/widget/SeekBar;->setEnabled(Z)V

    .line 153
    sget v0, Lorg/nameless/custom/R$id;->value:I

    invoke-virtual {p1, v0}, Landroidx/preference/PreferenceViewHolder;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/TextView;

    iput-object v0, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mValueTextView:Landroid/widget/TextView;

    .line 154
    sget v0, Lorg/nameless/custom/R$id;->reset:I

    invoke-virtual {p1, v0}, Landroidx/preference/PreferenceViewHolder;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/ImageView;

    iput-object v0, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mResetImageView:Landroid/widget/ImageView;

    .line 155
    sget v0, Lorg/nameless/custom/R$id;->minus:I

    invoke-virtual {p1, v0}, Landroidx/preference/PreferenceViewHolder;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/ImageView;

    iput-object v0, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mMinusImageView:Landroid/widget/ImageView;

    .line 156
    sget v0, Lorg/nameless/custom/R$id;->plus:I

    invoke-virtual {p1, v0}, Landroidx/preference/PreferenceViewHolder;->findViewById(I)Landroid/view/View;

    move-result-object p1

    check-cast p1, Landroid/widget/ImageView;

    iput-object p1, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mPlusImageView:Landroid/widget/ImageView;

    .line 158
    invoke-virtual {p0}, Lorg/nameless/custom/preference/CustomSeekBarPreference;->updateValueViews()V

    .line 160
    iget-object p1, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mSeekBar:Landroid/widget/SeekBar;

    invoke-virtual {p1, p0}, Landroid/widget/SeekBar;->setOnSeekBarChangeListener(Landroid/widget/SeekBar$OnSeekBarChangeListener;)V

    .line 161
    iget-object p1, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mResetImageView:Landroid/widget/ImageView;

    invoke-virtual {p1, p0}, Landroid/widget/ImageView;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 162
    iget-object p1, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mMinusImageView:Landroid/widget/ImageView;

    invoke-virtual {p1, p0}, Landroid/widget/ImageView;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 163
    iget-object p1, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mPlusImageView:Landroid/widget/ImageView;

    invoke-virtual {p1, p0}, Landroid/widget/ImageView;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 164
    iget-object p1, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mResetImageView:Landroid/widget/ImageView;

    invoke-virtual {p1, p0}, Landroid/widget/ImageView;->setOnLongClickListener(Landroid/view/View$OnLongClickListener;)V

    .line 165
    iget-object p1, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mMinusImageView:Landroid/widget/ImageView;

    invoke-virtual {p1, p0}, Landroid/widget/ImageView;->setOnLongClickListener(Landroid/view/View$OnLongClickListener;)V

    .line 166
    iget-object p1, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mPlusImageView:Landroid/widget/ImageView;

    invoke-virtual {p1, p0}, Landroid/widget/ImageView;->setOnLongClickListener(Landroid/view/View$OnLongClickListener;)V

    return-void
.end method

.method public onClick(Landroid/view/View;)V
    .locals 6

    .line 262
    invoke-virtual {p1}, Landroid/view/View;->getId()I

    move-result p1

    .line 263
    sget v0, Lorg/nameless/custom/R$id;->reset:I

    const/4 v1, 0x0

    const/4 v2, 0x1

    if-ne p1, v0, :cond_0

    .line 264
    invoke-virtual {p0}, Landroidx/preference/Preference;->getContext()Landroid/content/Context;

    move-result-object p1

    invoke-virtual {p0}, Landroidx/preference/Preference;->getContext()Landroid/content/Context;

    move-result-object v0

    sget v3, Lorg/nameless/custom/R$string;->custom_seekbar_default_value_to_set:I

    new-array v4, v2, [Ljava/lang/Object;

    iget v5, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mDefaultValue:I

    invoke-virtual {p0, v5}, Lorg/nameless/custom/preference/CustomSeekBarPreference;->getTextValue(I)Ljava/lang/String;

    move-result-object v5

    aput-object v5, v4, v1

    invoke-virtual {v0, v3, v4}, Landroid/content/Context;->getString(I[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    invoke-static {p1, v0, v2}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object p1

    .line 265
    invoke-virtual {p1}, Landroid/widget/Toast;->show()V

    goto :goto_0

    .line 266
    :cond_0
    sget v0, Lorg/nameless/custom/R$id;->minus:I

    if-ne p1, v0, :cond_1

    .line 267
    iget p1, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mValue:I

    iget v0, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mInterval:I

    sub-int/2addr p1, v0

    invoke-virtual {p0, p1, v2}, Lorg/nameless/custom/preference/CustomSeekBarPreference;->setValue(IZ)V

    goto :goto_0

    .line 268
    :cond_1
    sget v0, Lorg/nameless/custom/R$id;->plus:I

    if-ne p1, v0, :cond_2

    .line 269
    iget p1, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mValue:I

    iget v0, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mInterval:I

    add-int/2addr p1, v0

    invoke-virtual {p0, p1, v2}, Lorg/nameless/custom/preference/CustomSeekBarPreference;->setValue(IZ)V

    .line 271
    :cond_2
    :goto_0
    iget p1, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mValue:I

    iget v0, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mMinValue:I

    if-eq p1, v0, :cond_3

    iget v3, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mMaxValue:I

    if-eq p1, v3, :cond_3

    if-ne v0, v3, :cond_4

    :cond_3
    move v1, v2

    :cond_4
    invoke-direct {p0, v1}, Lorg/nameless/custom/preference/CustomSeekBarPreference;->doHapticFeedback(Z)V

    return-void
.end method

.method public onLongClick(Landroid/view/View;)Z
    .locals 5

    .line 276
    invoke-virtual {p1}, Landroid/view/View;->getId()I

    move-result p1

    .line 277
    sget v0, Lorg/nameless/custom/R$id;->reset:I

    const/4 v1, 0x1

    if-ne p1, v0, :cond_0

    .line 278
    iget p1, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mDefaultValue:I

    invoke-virtual {p0, p1, v1}, Lorg/nameless/custom/preference/CustomSeekBarPreference;->setValue(IZ)V

    goto :goto_0

    .line 281
    :cond_0
    sget v0, Lorg/nameless/custom/R$id;->minus:I

    const/4 v2, 0x2

    if-ne p1, v0, :cond_2

    .line 282
    iget p1, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mMaxValue:I

    iget v0, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mMinValue:I

    sub-int v3, p1, v0

    iget v4, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mInterval:I

    mul-int/2addr v4, v2

    if-le v3, v4, :cond_1

    add-int v3, p1, v0

    iget v4, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mValue:I

    mul-int/2addr v4, v2

    if-ge v3, v4, :cond_1

    add-int/2addr p1, v0

    invoke-static {p1, v2}, Ljava/lang/Math;->floorDiv(II)I

    move-result v0

    :cond_1
    invoke-virtual {p0, v0, v1}, Lorg/nameless/custom/preference/CustomSeekBarPreference;->setValue(IZ)V

    goto :goto_0

    .line 283
    :cond_2
    sget v0, Lorg/nameless/custom/R$id;->plus:I

    if-ne p1, v0, :cond_4

    .line 284
    iget p1, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mMaxValue:I

    iget v0, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mMinValue:I

    sub-int v3, p1, v0

    iget v4, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mInterval:I

    mul-int/2addr v4, v2

    if-le v3, v4, :cond_3

    add-int v3, p1, v0

    iget v4, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mValue:I

    mul-int/2addr v4, v2

    if-le v3, v4, :cond_3

    add-int/2addr p1, v0

    mul-int/lit8 p1, p1, -0x1

    invoke-static {p1, v2}, Ljava/lang/Math;->floorDiv(II)I

    move-result p1

    mul-int/lit8 p1, p1, -0x1

    :cond_3
    invoke-virtual {p0, p1, v1}, Lorg/nameless/custom/preference/CustomSeekBarPreference;->setValue(IZ)V

    :cond_4
    :goto_0
    return v1
.end method

.method public onProgressChanged(Landroid/widget/SeekBar;IZ)V
    .locals 1

    .line 222
    iget p1, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mMinValue:I

    iget p3, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mInterval:I

    mul-int/2addr p2, p3

    add-int/2addr p1, p2

    invoke-virtual {p0, p1}, Lorg/nameless/custom/preference/CustomSeekBarPreference;->getLimitedValue(I)I

    move-result p1

    .line 223
    iget-boolean p2, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mTrackingTouch:Z

    const/4 p3, 0x0

    const/4 v0, 0x1

    if-eqz p2, :cond_2

    iget-boolean p2, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mContinuousUpdates:Z

    if-nez p2, :cond_2

    .line 224
    iput p1, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mTrackingValue:I

    .line 225
    invoke-virtual {p0}, Lorg/nameless/custom/preference/CustomSeekBarPreference;->updateValueViews()V

    .line 226
    iget p2, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mMinValue:I

    if-eq p1, p2, :cond_0

    iget p2, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mMaxValue:I

    if-ne p1, p2, :cond_1

    :cond_0
    move p3, v0

    :cond_1
    invoke-direct {p0, p3}, Lorg/nameless/custom/preference/CustomSeekBarPreference;->doHapticFeedback(Z)V

    goto :goto_0

    .line 227
    :cond_2
    iget p2, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mValue:I

    if-eq p2, p1, :cond_6

    .line 229
    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p2

    invoke-virtual {p0, p2}, Landroidx/preference/Preference;->callChangeListener(Ljava/lang/Object;)Z

    move-result p2

    if-nez p2, :cond_3

    .line 230
    iget-object p1, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mSeekBar:Landroid/widget/SeekBar;

    iget p2, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mValue:I

    invoke-virtual {p0, p2}, Lorg/nameless/custom/preference/CustomSeekBarPreference;->getSeekValue(I)I

    move-result p0

    invoke-virtual {p1, p0}, Landroid/widget/SeekBar;->setProgress(I)V

    return-void

    .line 234
    :cond_3
    invoke-virtual {p0, p1}, Lorg/nameless/custom/preference/CustomSeekBarPreference;->changeValue(I)V

    .line 235
    invoke-virtual {p0, p1}, Landroidx/preference/Preference;->persistInt(I)Z

    .line 237
    iput p1, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mValue:I

    .line 238
    invoke-virtual {p0}, Lorg/nameless/custom/preference/CustomSeekBarPreference;->updateValueViews()V

    .line 240
    iget-boolean p2, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mTrackingTouch:Z

    if-eqz p2, :cond_6

    .line 241
    iget p2, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mMinValue:I

    if-eq p1, p2, :cond_4

    iget p2, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mMaxValue:I

    if-ne p1, p2, :cond_5

    :cond_4
    move p3, v0

    :cond_5
    invoke-direct {p0, p3}, Lorg/nameless/custom/preference/CustomSeekBarPreference;->doHapticFeedback(Z)V

    :cond_6
    :goto_0
    return-void
.end method

.method protected onSetInitialValue(ZLjava/lang/Object;)V
    .locals 0

    if-eqz p1, :cond_0

    .line 295
    iget p1, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mValue:I

    invoke-virtual {p0, p1}, Landroidx/preference/Preference;->getPersistedInt(I)I

    move-result p1

    iput p1, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mValue:I

    :cond_0
    return-void
.end method

.method public onStartTrackingTouch(Landroid/widget/SeekBar;)V
    .locals 0

    .line 248
    iget p1, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mValue:I

    iput p1, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mTrackingValue:I

    const/4 p1, 0x1

    .line 249
    iput-boolean p1, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mTrackingTouch:Z

    return-void
.end method

.method public onStopTrackingTouch(Landroid/widget/SeekBar;)V
    .locals 2

    const/4 p1, 0x0

    .line 254
    iput-boolean p1, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mTrackingTouch:Z

    .line 255
    iget-boolean v0, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mContinuousUpdates:Z

    if-nez v0, :cond_0

    .line 256
    iget-object v0, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mSeekBar:Landroid/widget/SeekBar;

    iget v1, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mTrackingValue:I

    invoke-virtual {p0, v1}, Lorg/nameless/custom/preference/CustomSeekBarPreference;->getSeekValue(I)I

    move-result v1

    invoke-virtual {p0, v0, v1, p1}, Lorg/nameless/custom/preference/CustomSeekBarPreference;->onProgressChanged(Landroid/widget/SeekBar;IZ)V

    .line 257
    :cond_0
    invoke-virtual {p0}, Landroidx/preference/Preference;->notifyChanged()V

    return-void
.end method

.method public setValue(IZ)V
    .locals 1

    .line 342
    invoke-virtual {p0, p1}, Lorg/nameless/custom/preference/CustomSeekBarPreference;->getLimitedValue(I)I

    move-result p1

    .line 343
    iget v0, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mValue:I

    if-eq v0, p1, :cond_1

    if-eqz p2, :cond_0

    .line 345
    iget-object p2, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mSeekBar:Landroid/widget/SeekBar;

    invoke-virtual {p0, p1}, Lorg/nameless/custom/preference/CustomSeekBarPreference;->getSeekValue(I)I

    move-result p0

    invoke-virtual {p2, p0}, Landroid/widget/SeekBar;->setProgress(I)V

    goto :goto_0

    .line 347
    :cond_0
    iput p1, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mValue:I

    :cond_1
    :goto_0
    return-void
.end method

.method protected updateValueViews()V
    .locals 10

    .line 182
    iget-object v0, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mValueTextView:Landroid/widget/TextView;

    const/4 v1, 0x1

    const/4 v2, 0x0

    if-eqz v0, :cond_3

    .line 183
    invoke-virtual {p0}, Landroidx/preference/Preference;->getContext()Landroid/content/Context;

    move-result-object v3

    sget v4, Lorg/nameless/custom/R$string;->custom_seekbar_value:I

    new-array v5, v1, [Ljava/lang/Object;

    .line 184
    iget-boolean v6, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mTrackingTouch:Z

    if-eqz v6, :cond_1

    iget-boolean v6, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mContinuousUpdates:Z

    if-eqz v6, :cond_0

    goto :goto_0

    .line 187
    :cond_0
    iget v6, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mTrackingValue:I

    invoke-virtual {p0, v6}, Lorg/nameless/custom/preference/CustomSeekBarPreference;->getTextValue(I)Ljava/lang/String;

    move-result-object v6

    goto :goto_2

    .line 184
    :cond_1
    :goto_0
    new-instance v6, Ljava/lang/StringBuilder;

    invoke-direct {v6}, Ljava/lang/StringBuilder;-><init>()V

    iget v7, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mValue:I

    invoke-virtual {p0, v7}, Lorg/nameless/custom/preference/CustomSeekBarPreference;->getTextValue(I)Ljava/lang/String;

    move-result-object v7

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 185
    iget-boolean v7, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mDefaultValueExists:Z

    if-eqz v7, :cond_2

    iget v7, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mValue:I

    iget v8, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mDefaultValue:I

    if-ne v7, v8, :cond_2

    .line 186
    new-instance v7, Ljava/lang/StringBuilder;

    invoke-direct {v7}, Ljava/lang/StringBuilder;-><init>()V

    const-string v8, " ("

    invoke-virtual {v7, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p0}, Landroidx/preference/Preference;->getContext()Landroid/content/Context;

    move-result-object v8

    sget v9, Lorg/nameless/custom/R$string;->custom_seekbar_default_value:I

    invoke-virtual {v8, v9}, Landroid/content/Context;->getString(I)Ljava/lang/String;

    move-result-object v8

    invoke-virtual {v7, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v8, ")"

    invoke-virtual {v7, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v7}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v7

    goto :goto_1

    :cond_2
    const-string v7, ""

    :goto_1
    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v6}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v6

    :goto_2
    aput-object v6, v5, v2

    .line 183
    invoke-virtual {v3, v4, v5}, Landroid/content/Context;->getString(I[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v0, v3}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 189
    :cond_3
    iget-object v0, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mResetImageView:Landroid/widget/ImageView;

    if-eqz v0, :cond_6

    .line 190
    iget-boolean v3, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mDefaultValueExists:Z

    if-eqz v3, :cond_5

    iget v3, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mValue:I

    iget v4, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mDefaultValue:I

    if-eq v3, v4, :cond_5

    iget-boolean v3, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mTrackingTouch:Z

    if-eqz v3, :cond_4

    goto :goto_3

    .line 193
    :cond_4
    invoke-virtual {v0, v2}, Landroid/widget/ImageView;->setVisibility(I)V

    goto :goto_4

    :cond_5
    :goto_3
    const/4 v3, 0x4

    .line 191
    invoke-virtual {v0, v3}, Landroid/widget/ImageView;->setVisibility(I)V

    .line 195
    :cond_6
    :goto_4
    iget-object v0, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mMinusImageView:Landroid/widget/ImageView;

    if-eqz v0, :cond_9

    .line 196
    iget v3, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mValue:I

    iget v4, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mMinValue:I

    if-eq v3, v4, :cond_8

    iget-boolean v3, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mTrackingTouch:Z

    if-eqz v3, :cond_7

    goto :goto_5

    .line 201
    :cond_7
    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setClickable(Z)V

    .line 202
    iget-object v0, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mMinusImageView:Landroid/widget/ImageView;

    invoke-virtual {v0}, Landroid/widget/ImageView;->clearColorFilter()V

    goto :goto_6

    .line 197
    :cond_8
    :goto_5
    invoke-virtual {v0, v2}, Landroid/widget/ImageView;->setClickable(Z)V

    .line 198
    iget-object v0, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mMinusImageView:Landroid/widget/ImageView;

    invoke-virtual {p0}, Landroidx/preference/Preference;->getContext()Landroid/content/Context;

    move-result-object v3

    sget v4, Lorg/nameless/custom/R$color;->disabled_text_color:I

    invoke-virtual {v3, v4}, Landroid/content/Context;->getColor(I)I

    move-result v3

    sget-object v4, Landroid/graphics/PorterDuff$Mode;->MULTIPLY:Landroid/graphics/PorterDuff$Mode;

    invoke-virtual {v0, v3, v4}, Landroid/widget/ImageView;->setColorFilter(ILandroid/graphics/PorterDuff$Mode;)V

    .line 205
    :cond_9
    :goto_6
    iget-object v0, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mPlusImageView:Landroid/widget/ImageView;

    if-eqz v0, :cond_c

    .line 206
    iget v3, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mValue:I

    iget v4, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mMaxValue:I

    if-eq v3, v4, :cond_b

    iget-boolean v3, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mTrackingTouch:Z

    if-eqz v3, :cond_a

    goto :goto_7

    .line 210
    :cond_a
    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setClickable(Z)V

    .line 211
    iget-object p0, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mPlusImageView:Landroid/widget/ImageView;

    invoke-virtual {p0}, Landroid/widget/ImageView;->clearColorFilter()V

    goto :goto_8

    .line 207
    :cond_b
    :goto_7
    invoke-virtual {v0, v2}, Landroid/widget/ImageView;->setClickable(Z)V

    .line 208
    iget-object v0, p0, Lorg/nameless/custom/preference/CustomSeekBarPreference;->mPlusImageView:Landroid/widget/ImageView;

    invoke-virtual {p0}, Landroidx/preference/Preference;->getContext()Landroid/content/Context;

    move-result-object p0

    sget v1, Lorg/nameless/custom/R$color;->disabled_text_color:I

    invoke-virtual {p0, v1}, Landroid/content/Context;->getColor(I)I

    move-result p0

    sget-object v1, Landroid/graphics/PorterDuff$Mode;->MULTIPLY:Landroid/graphics/PorterDuff$Mode;

    invoke-virtual {v0, p0, v1}, Landroid/widget/ImageView;->setColorFilter(ILandroid/graphics/PorterDuff$Mode;)V

    :cond_c
    :goto_8
    return-void
.end method
