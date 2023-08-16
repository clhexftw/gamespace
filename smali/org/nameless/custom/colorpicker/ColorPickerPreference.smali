.class public Lorg/nameless/custom/colorpicker/ColorPickerPreference;
.super Landroidx/preference/Preference;
.source "ColorPickerPreference.java"

# interfaces
.implements Landroidx/preference/Preference$OnPreferenceClickListener;
.implements Lorg/nameless/custom/colorpicker/ColorPickerDialog$OnColorChangedListener;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lorg/nameless/custom/colorpicker/ColorPickerPreference$SavedState;
    }
.end annotation


# instance fields
.field private mAlphaSliderEnabled:Z

.field private final mContext:Landroid/content/Context;

.field private mCurrentValue:I

.field private mDefaultValue:I

.field private mDensity:F

.field mDialog:Lorg/nameless/custom/colorpicker/ColorPickerDialog;

.field private mDividerAbove:Z

.field private mDividerBelow:Z

.field private mEditText:Landroid/widget/EditText;

.field private mShowPreview:Z

.field private mShowReset:Z

.field private final mVibrator:Landroid/os/Vibrator;

.field mView:Landroidx/preference/PreferenceViewHolder;

.field mWidgetFrameView:Landroid/widget/LinearLayout;


# direct methods
.method static bridge synthetic -$$Nest$fgetmDefaultValue(Lorg/nameless/custom/colorpicker/ColorPickerPreference;)I
    .locals 0

    iget p0, p0, Lorg/nameless/custom/colorpicker/ColorPickerPreference;->mDefaultValue:I

    return p0
.end method

.method static bridge synthetic -$$Nest$mdoHapticFeedback(Lorg/nameless/custom/colorpicker/ColorPickerPreference;)V
    .locals 0

    invoke-direct {p0}, Lorg/nameless/custom/colorpicker/ColorPickerPreference;->doHapticFeedback()V

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 1

    const/4 v0, 0x0

    .line 83
    invoke-direct {p0, p1, p2, v0}, Lorg/nameless/custom/colorpicker/ColorPickerPreference;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V
    .locals 1

    const/4 v0, 0x0

    .line 79
    invoke-direct {p0, p1, p2, p3, v0}, Lorg/nameless/custom/colorpicker/ColorPickerPreference;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;II)V

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;II)V
    .locals 0

    .line 92
    invoke-direct {p0, p1, p2, p3, p4}, Landroidx/preference/Preference;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;II)V

    const/high16 p3, -0x1000000

    .line 61
    iput p3, p0, Lorg/nameless/custom/colorpicker/ColorPickerPreference;->mDefaultValue:I

    .line 62
    iput p3, p0, Lorg/nameless/custom/colorpicker/ColorPickerPreference;->mCurrentValue:I

    const/4 p3, 0x0

    .line 63
    iput p3, p0, Lorg/nameless/custom/colorpicker/ColorPickerPreference;->mDensity:F

    const/4 p3, 0x0

    .line 64
    iput-boolean p3, p0, Lorg/nameless/custom/colorpicker/ColorPickerPreference;->mAlphaSliderEnabled:Z

    .line 93
    sget p3, Lorg/nameless/custom/R$layout;->preference_material_settings:I

    invoke-virtual {p0, p3}, Landroidx/preference/Preference;->setLayoutResource(I)V

    .line 94
    invoke-direct {p0, p1, p2}, Lorg/nameless/custom/colorpicker/ColorPickerPreference;->init(Landroid/content/Context;Landroid/util/AttributeSet;)V

    .line 96
    iput-object p1, p0, Lorg/nameless/custom/colorpicker/ColorPickerPreference;->mContext:Landroid/content/Context;

    const-string p2, "vibrator"

    .line 97
    invoke-virtual {p1, p2}, Landroid/content/Context;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Landroid/os/Vibrator;

    iput-object p1, p0, Lorg/nameless/custom/colorpicker/ColorPickerPreference;->mVibrator:Landroid/os/Vibrator;

    return-void
.end method

.method public static convertToColorInt(Ljava/lang/String;)I
    .locals 8
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/NumberFormatException;
        }
    .end annotation

    const-string v0, "#"

    .line 351
    invoke-virtual {p0, v0}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v1

    if-eqz v1, :cond_0

    const-string v1, ""

    .line 352
    invoke-virtual {p0, v0, v1}, Ljava/lang/String;->replace(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;

    move-result-object p0

    .line 357
    :cond_0
    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result v0

    const/4 v1, 0x0

    const/16 v2, 0x8

    const/4 v3, 0x4

    const/4 v4, 0x2

    const/4 v5, 0x6

    const/4 v6, -0x1

    const/16 v7, 0x10

    if-ne v0, v2, :cond_1

    .line 358
    invoke-virtual {p0, v1, v4}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0, v7}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;I)I

    move-result v6

    .line 359
    invoke-virtual {p0, v4, v3}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0, v7}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;I)I

    move-result v0

    .line 360
    invoke-virtual {p0, v3, v5}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object v1

    invoke-static {v1, v7}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;I)I

    move-result v1

    .line 361
    invoke-virtual {p0, v5, v2}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object p0

    invoke-static {p0, v7}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;I)I

    move-result p0

    goto :goto_0

    .line 363
    :cond_1
    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result v0

    if-ne v0, v5, :cond_2

    const/16 v6, 0xff

    .line 365
    invoke-virtual {p0, v1, v4}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0, v7}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;I)I

    move-result v0

    .line 366
    invoke-virtual {p0, v4, v3}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object v1

    invoke-static {v1, v7}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;I)I

    move-result v1

    .line 367
    invoke-virtual {p0, v3, v5}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object p0

    invoke-static {p0, v7}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;I)I

    move-result p0

    goto :goto_0

    :cond_2
    move p0, v6

    move v0, p0

    move v1, v0

    .line 370
    :goto_0
    invoke-static {v6, v0, v1, p0}, Landroid/graphics/Color;->argb(IIII)I

    move-result p0

    return p0
.end method

.method public static convertToRGB(I)Ljava/lang/String;
    .locals 5

    .line 323
    invoke-static {p0}, Landroid/graphics/Color;->red(I)I

    move-result v0

    invoke-static {v0}, Ljava/lang/Integer;->toHexString(I)Ljava/lang/String;

    move-result-object v0

    .line 324
    invoke-static {p0}, Landroid/graphics/Color;->green(I)I

    move-result v1

    invoke-static {v1}, Ljava/lang/Integer;->toHexString(I)Ljava/lang/String;

    move-result-object v1

    .line 325
    invoke-static {p0}, Landroid/graphics/Color;->blue(I)I

    move-result p0

    invoke-static {p0}, Ljava/lang/Integer;->toHexString(I)Ljava/lang/String;

    move-result-object p0

    .line 327
    invoke-virtual {v0}, Ljava/lang/String;->length()I

    move-result v2

    const-string v3, "0"

    const/4 v4, 0x1

    if-ne v2, v4, :cond_0

    .line 328
    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    .line 331
    :cond_0
    invoke-virtual {v1}, Ljava/lang/String;->length()I

    move-result v2

    if-ne v2, v4, :cond_1

    .line 332
    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    .line 335
    :cond_1
    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result v2

    if-ne v2, v4, :cond_2

    .line 336
    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    .line 339
    :cond_2
    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "#"

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method private static createOvalShape(II)Landroid/graphics/drawable/ShapeDrawable;
    .locals 2

    .line 430
    new-instance v0, Landroid/graphics/drawable/ShapeDrawable;

    new-instance v1, Landroid/graphics/drawable/shapes/OvalShape;

    invoke-direct {v1}, Landroid/graphics/drawable/shapes/OvalShape;-><init>()V

    invoke-direct {v0, v1}, Landroid/graphics/drawable/ShapeDrawable;-><init>(Landroid/graphics/drawable/shapes/Shape;)V

    .line 431
    invoke-virtual {v0, p0}, Landroid/graphics/drawable/ShapeDrawable;->setIntrinsicHeight(I)V

    .line 432
    invoke-virtual {v0, p0}, Landroid/graphics/drawable/ShapeDrawable;->setIntrinsicWidth(I)V

    .line 433
    invoke-virtual {v0}, Landroid/graphics/drawable/ShapeDrawable;->getPaint()Landroid/graphics/Paint;

    move-result-object p0

    invoke-virtual {p0, p1}, Landroid/graphics/Paint;->setColor(I)V

    return-object v0
.end method

.method private doHapticFeedback()V
    .locals 3

    .line 438
    iget-object v0, p0, Lorg/nameless/custom/colorpicker/ColorPickerPreference;->mContext:Landroid/content/Context;

    invoke-virtual {v0}, Landroid/content/Context;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object v0

    const-string v1, "haptic_feedback_enabled"

    const/4 v2, 0x1

    invoke-static {v0, v1, v2}, Landroid/provider/Settings$System;->getInt(Landroid/content/ContentResolver;Ljava/lang/String;I)I

    move-result v0

    const/4 v1, 0x0

    if-eqz v0, :cond_0

    goto :goto_0

    :cond_0
    move v2, v1

    :goto_0
    if-eqz v2, :cond_1

    .line 442
    iget-object p0, p0, Lorg/nameless/custom/colorpicker/ColorPickerPreference;->mVibrator:Landroid/os/Vibrator;

    invoke-static {v1}, Landroid/os/VibrationEffect;->get(I)Landroid/os/VibrationEffect;

    move-result-object v0

    invoke-virtual {p0, v0}, Landroid/os/Vibrator;->vibrate(Landroid/os/VibrationEffect;)V

    :cond_1
    return-void
.end method

.method private init(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 3

    .line 120
    invoke-virtual {p0}, Landroidx/preference/Preference;->getContext()Landroid/content/Context;

    move-result-object p1

    invoke-virtual {p1}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object p1

    invoke-virtual {p1}, Landroid/content/res/Resources;->getDisplayMetrics()Landroid/util/DisplayMetrics;

    move-result-object p1

    iget p1, p1, Landroid/util/DisplayMetrics;->density:F

    iput p1, p0, Lorg/nameless/custom/colorpicker/ColorPickerPreference;->mDensity:F

    .line 121
    invoke-virtual {p0, p0}, Landroidx/preference/Preference;->setOnPreferenceClickListener(Landroidx/preference/Preference$OnPreferenceClickListener;)V

    if-eqz p2, :cond_0

    const/4 p1, 0x0

    const-string v0, "alphaSlider"

    const/4 v1, 0x0

    .line 123
    invoke-interface {p2, p1, v0, v1}, Landroid/util/AttributeSet;->getAttributeBooleanValue(Ljava/lang/String;Ljava/lang/String;Z)Z

    move-result p1

    iput-boolean p1, p0, Lorg/nameless/custom/colorpicker/ColorPickerPreference;->mAlphaSliderEnabled:Z

    const/high16 p1, -0x1000000

    const-string v0, "http://schemas.android.com/apk/res/android"

    const-string v2, "defaultValue"

    .line 124
    invoke-interface {p2, v0, v2, p1}, Landroid/util/AttributeSet;->getAttributeIntValue(Ljava/lang/String;Ljava/lang/String;I)I

    move-result p1

    iput p1, p0, Lorg/nameless/custom/colorpicker/ColorPickerPreference;->mDefaultValue:I

    const-string p1, "http://schemas.android.com/apk/res/com.android.settings"

    const-string v0, "showReset"

    const/4 v2, 0x1

    .line 125
    invoke-interface {p2, p1, v0, v2}, Landroid/util/AttributeSet;->getAttributeBooleanValue(Ljava/lang/String;Ljava/lang/String;Z)Z

    move-result v0

    iput-boolean v0, p0, Lorg/nameless/custom/colorpicker/ColorPickerPreference;->mShowReset:Z

    const-string v0, "showPreview"

    .line 126
    invoke-interface {p2, p1, v0, v2}, Landroid/util/AttributeSet;->getAttributeBooleanValue(Ljava/lang/String;Ljava/lang/String;Z)Z

    move-result v0

    iput-boolean v0, p0, Lorg/nameless/custom/colorpicker/ColorPickerPreference;->mShowPreview:Z

    const-string v0, "dividerAbove"

    .line 127
    invoke-interface {p2, p1, v0, v1}, Landroid/util/AttributeSet;->getAttributeBooleanValue(Ljava/lang/String;Ljava/lang/String;Z)Z

    move-result v0

    iput-boolean v0, p0, Lorg/nameless/custom/colorpicker/ColorPickerPreference;->mDividerAbove:Z

    const-string v0, "dividerBelow"

    .line 128
    invoke-interface {p2, p1, v0, v1}, Landroid/util/AttributeSet;->getAttributeBooleanValue(Ljava/lang/String;Ljava/lang/String;Z)Z

    move-result p1

    iput-boolean p1, p0, Lorg/nameless/custom/colorpicker/ColorPickerPreference;->mDividerBelow:Z

    :cond_0
    return-void
.end method

.method private setDefaultButton()V
    .locals 5

    .line 167
    iget-boolean v0, p0, Lorg/nameless/custom/colorpicker/ColorPickerPreference;->mShowReset:Z

    if-eqz v0, :cond_4

    iget-object v0, p0, Lorg/nameless/custom/colorpicker/ColorPickerPreference;->mView:Landroidx/preference/PreferenceViewHolder;

    if-eqz v0, :cond_4

    iget-object v0, p0, Lorg/nameless/custom/colorpicker/ColorPickerPreference;->mWidgetFrameView:Landroid/widget/LinearLayout;

    if-nez v0, :cond_0

    goto :goto_0

    .line 171
    :cond_0
    invoke-virtual {v0}, Landroid/widget/LinearLayout;->getChildCount()I

    move-result v0

    const-string v1, "spacer"

    const-string v2, "default"

    if-lez v0, :cond_2

    .line 173
    iget-object v0, p0, Lorg/nameless/custom/colorpicker/ColorPickerPreference;->mWidgetFrameView:Landroid/widget/LinearLayout;

    invoke-virtual {v0, v2}, Landroid/widget/LinearLayout;->findViewWithTag(Ljava/lang/Object;)Landroid/view/View;

    move-result-object v0

    .line 174
    iget-object v3, p0, Lorg/nameless/custom/colorpicker/ColorPickerPreference;->mWidgetFrameView:Landroid/widget/LinearLayout;

    invoke-virtual {v3, v1}, Landroid/widget/LinearLayout;->findViewWithTag(Ljava/lang/Object;)Landroid/view/View;

    move-result-object v3

    if-eqz v0, :cond_1

    .line 176
    iget-object v4, p0, Lorg/nameless/custom/colorpicker/ColorPickerPreference;->mWidgetFrameView:Landroid/widget/LinearLayout;

    invoke-virtual {v4, v0}, Landroid/widget/LinearLayout;->removeView(Landroid/view/View;)V

    :cond_1
    if-eqz v3, :cond_2

    .line 179
    iget-object v0, p0, Lorg/nameless/custom/colorpicker/ColorPickerPreference;->mWidgetFrameView:Landroid/widget/LinearLayout;

    invoke-virtual {v0, v3}, Landroid/widget/LinearLayout;->removeView(Landroid/view/View;)V

    .line 183
    :cond_2
    invoke-virtual {p0}, Landroidx/preference/Preference;->isEnabled()Z

    move-result v0

    if-nez v0, :cond_3

    return-void

    .line 185
    :cond_3
    new-instance v0, Landroid/widget/ImageView;

    invoke-virtual {p0}, Landroidx/preference/Preference;->getContext()Landroid/content/Context;

    move-result-object v3

    invoke-direct {v0, v3}, Landroid/widget/ImageView;-><init>(Landroid/content/Context;)V

    .line 186
    iget-object v3, p0, Lorg/nameless/custom/colorpicker/ColorPickerPreference;->mWidgetFrameView:Landroid/widget/LinearLayout;

    invoke-virtual {v3, v0}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    .line 187
    invoke-virtual {p0}, Landroidx/preference/Preference;->getContext()Landroid/content/Context;

    move-result-object v3

    sget v4, Lorg/nameless/custom/R$drawable;->ic_settings_backup_restore:I

    invoke-virtual {v3, v4}, Landroid/content/Context;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v3

    invoke-virtual {v0, v3}, Landroid/widget/ImageView;->setImageDrawable(Landroid/graphics/drawable/Drawable;)V

    .line 188
    invoke-virtual {v0, v2}, Landroid/widget/ImageView;->setTag(Ljava/lang/Object;)V

    .line 189
    new-instance v2, Lorg/nameless/custom/colorpicker/ColorPickerPreference$2;

    invoke-direct {v2, p0}, Lorg/nameless/custom/colorpicker/ColorPickerPreference$2;-><init>(Lorg/nameless/custom/colorpicker/ColorPickerPreference;)V

    invoke-virtual {v0, v2}, Landroid/widget/ImageView;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 197
    new-instance v0, Landroid/view/View;

    invoke-virtual {p0}, Landroidx/preference/Preference;->getContext()Landroid/content/Context;

    move-result-object v2

    invoke-direct {v0, v2}, Landroid/view/View;-><init>(Landroid/content/Context;)V

    .line 198
    invoke-virtual {v0, v1}, Landroid/view/View;->setTag(Ljava/lang/Object;)V

    .line 199
    new-instance v1, Landroid/widget/LinearLayout$LayoutParams;

    iget v2, p0, Lorg/nameless/custom/colorpicker/ColorPickerPreference;->mDensity:F

    const/high16 v3, 0x41800000    # 16.0f

    mul-float/2addr v2, v3

    float-to-int v2, v2

    const/4 v3, -0x1

    invoke-direct {v1, v2, v3}, Landroid/widget/LinearLayout$LayoutParams;-><init>(II)V

    invoke-virtual {v0, v1}, Landroid/view/View;->setLayoutParams(Landroid/view/ViewGroup$LayoutParams;)V

    .line 201
    iget-object p0, p0, Lorg/nameless/custom/colorpicker/ColorPickerPreference;->mWidgetFrameView:Landroid/widget/LinearLayout;

    invoke-virtual {p0, v0}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    :cond_4
    :goto_0
    return-void
.end method

.method private setPreviewColor()V
    .locals 5

    .line 205
    iget-boolean v0, p0, Lorg/nameless/custom/colorpicker/ColorPickerPreference;->mShowPreview:Z

    if-eqz v0, :cond_4

    iget-object v0, p0, Lorg/nameless/custom/colorpicker/ColorPickerPreference;->mView:Landroidx/preference/PreferenceViewHolder;

    if-eqz v0, :cond_4

    iget-object v0, p0, Lorg/nameless/custom/colorpicker/ColorPickerPreference;->mWidgetFrameView:Landroid/widget/LinearLayout;

    if-nez v0, :cond_0

    goto :goto_0

    .line 209
    :cond_0
    invoke-virtual {v0}, Landroid/widget/LinearLayout;->getChildCount()I

    move-result v0

    const-string v1, "preview"

    if-lez v0, :cond_1

    .line 211
    iget-object v0, p0, Lorg/nameless/custom/colorpicker/ColorPickerPreference;->mWidgetFrameView:Landroid/widget/LinearLayout;

    invoke-virtual {v0, v1}, Landroid/widget/LinearLayout;->findViewWithTag(Ljava/lang/Object;)Landroid/view/View;

    move-result-object v0

    if-eqz v0, :cond_1

    .line 213
    iget-object v2, p0, Lorg/nameless/custom/colorpicker/ColorPickerPreference;->mWidgetFrameView:Landroid/widget/LinearLayout;

    invoke-virtual {v2, v0}, Landroid/widget/LinearLayout;->removeView(Landroid/view/View;)V

    .line 217
    :cond_1
    invoke-virtual {p0}, Landroidx/preference/Preference;->isEnabled()Z

    move-result v0

    if-nez v0, :cond_2

    return-void

    .line 219
    :cond_2
    new-instance v0, Landroid/widget/ImageView;

    invoke-virtual {p0}, Landroidx/preference/Preference;->getContext()Landroid/content/Context;

    move-result-object v2

    invoke-direct {v0, v2}, Landroid/widget/ImageView;-><init>(Landroid/content/Context;)V

    .line 220
    iget-object v2, p0, Lorg/nameless/custom/colorpicker/ColorPickerPreference;->mWidgetFrameView:Landroid/widget/LinearLayout;

    invoke-virtual {v2, v0}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    .line 221
    invoke-virtual {p0}, Landroidx/preference/Preference;->getContext()Landroid/content/Context;

    move-result-object v2

    invoke-virtual {v2}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v2

    sget v3, Lorg/nameless/custom/R$dimen;->oval_notification_size:I

    invoke-virtual {v2, v3}, Landroid/content/res/Resources;->getDimension(I)F

    move-result v2

    float-to-int v2, v2

    .line 222
    iget p0, p0, Lorg/nameless/custom/colorpicker/ColorPickerPreference;->mCurrentValue:I

    const v3, 0xf0f0f0

    and-int v4, p0, v3

    if-ne v4, v3, :cond_3

    const v3, 0x101010

    sub-int/2addr p0, v3

    :cond_3
    const/high16 v3, -0x1000000

    add-int/2addr p0, v3

    .line 224
    invoke-static {v2, p0}, Lorg/nameless/custom/colorpicker/ColorPickerPreference;->createOvalShape(II)Landroid/graphics/drawable/ShapeDrawable;

    move-result-object p0

    invoke-virtual {v0, p0}, Landroid/widget/ImageView;->setImageDrawable(Landroid/graphics/drawable/Drawable;)V

    .line 225
    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setTag(Ljava/lang/Object;)V

    :cond_4
    :goto_0
    return-void
.end method


# virtual methods
.method public onBindViewHolder(Landroidx/preference/PreferenceViewHolder;)V
    .locals 4

    .line 134
    iput-object p1, p0, Lorg/nameless/custom/colorpicker/ColorPickerPreference;->mView:Landroidx/preference/PreferenceViewHolder;

    .line 135
    invoke-super {p0, p1}, Landroidx/preference/Preference;->onBindViewHolder(Landroidx/preference/PreferenceViewHolder;)V

    .line 136
    iget-boolean v0, p0, Lorg/nameless/custom/colorpicker/ColorPickerPreference;->mDividerAbove:Z

    invoke-virtual {p1, v0}, Landroidx/preference/PreferenceViewHolder;->setDividerAllowedAbove(Z)V

    .line 137
    iget-boolean v0, p0, Lorg/nameless/custom/colorpicker/ColorPickerPreference;->mDividerBelow:Z

    invoke-virtual {p1, v0}, Landroidx/preference/PreferenceViewHolder;->setDividerAllowedBelow(Z)V

    .line 139
    iget-object v0, p1, Landroidx/recyclerview/widget/RecyclerView$ViewHolder;->itemView:Landroid/view/View;

    new-instance v1, Lorg/nameless/custom/colorpicker/ColorPickerPreference$1;

    invoke-direct {v1, p0}, Lorg/nameless/custom/colorpicker/ColorPickerPreference$1;-><init>(Lorg/nameless/custom/colorpicker/ColorPickerPreference;)V

    invoke-virtual {v0, v1}, Landroid/view/View;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    const v0, 0x1020018

    .line 146
    invoke-virtual {p1, v0}, Landroidx/preference/PreferenceViewHolder;->findViewById(I)Landroid/view/View;

    move-result-object p1

    check-cast p1, Landroid/widget/LinearLayout;

    iput-object p1, p0, Lorg/nameless/custom/colorpicker/ColorPickerPreference;->mWidgetFrameView:Landroid/widget/LinearLayout;

    const/4 v0, 0x0

    .line 147
    invoke-virtual {p1, v0}, Landroid/widget/LinearLayout;->setOrientation(I)V

    .line 148
    iget-object p1, p0, Lorg/nameless/custom/colorpicker/ColorPickerPreference;->mWidgetFrameView:Landroid/widget/LinearLayout;

    invoke-virtual {p1, v0}, Landroid/widget/LinearLayout;->setVisibility(I)V

    .line 149
    iget-object p1, p0, Lorg/nameless/custom/colorpicker/ColorPickerPreference;->mWidgetFrameView:Landroid/widget/LinearLayout;

    invoke-virtual {p1, v0}, Landroid/widget/LinearLayout;->setMinimumWidth(I)V

    .line 150
    iget-object p1, p0, Lorg/nameless/custom/colorpicker/ColorPickerPreference;->mWidgetFrameView:Landroid/widget/LinearLayout;

    .line 151
    invoke-virtual {p1}, Landroid/widget/LinearLayout;->getPaddingLeft()I

    move-result v0

    iget-object v1, p0, Lorg/nameless/custom/colorpicker/ColorPickerPreference;->mWidgetFrameView:Landroid/widget/LinearLayout;

    .line 152
    invoke-virtual {v1}, Landroid/widget/LinearLayout;->getPaddingTop()I

    move-result v1

    iget v2, p0, Lorg/nameless/custom/colorpicker/ColorPickerPreference;->mDensity:F

    const/high16 v3, 0x41000000    # 8.0f

    mul-float/2addr v2, v3

    float-to-int v2, v2

    iget-object v3, p0, Lorg/nameless/custom/colorpicker/ColorPickerPreference;->mWidgetFrameView:Landroid/widget/LinearLayout;

    .line 154
    invoke-virtual {v3}, Landroid/widget/LinearLayout;->getPaddingBottom()I

    move-result v3

    .line 150
    invoke-virtual {p1, v0, v1, v2, v3}, Landroid/widget/LinearLayout;->setPadding(IIII)V

    .line 156
    invoke-direct {p0}, Lorg/nameless/custom/colorpicker/ColorPickerPreference;->setDefaultButton()V

    .line 157
    invoke-direct {p0}, Lorg/nameless/custom/colorpicker/ColorPickerPreference;->setPreviewColor()V

    return-void
.end method

.method public onColorChanged(I)V
    .locals 2

    .line 237
    iput p1, p0, Lorg/nameless/custom/colorpicker/ColorPickerPreference;->mCurrentValue:I

    .line 238
    invoke-direct {p0}, Lorg/nameless/custom/colorpicker/ColorPickerPreference;->setPreviewColor()V

    .line 239
    invoke-virtual {p0, p1}, Landroidx/preference/Preference;->persistInt(I)Z

    .line 241
    :try_start_0
    invoke-virtual {p0}, Landroidx/preference/Preference;->getOnPreferenceChangeListener()Landroidx/preference/Preference$OnPreferenceChangeListener;

    move-result-object v0

    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    invoke-interface {v0, p0, v1}, Landroidx/preference/Preference$OnPreferenceChangeListener;->onPreferenceChange(Landroidx/preference/Preference;Ljava/lang/Object;)Z
    :try_end_0
    .catch Ljava/lang/NullPointerException; {:try_start_0 .. :try_end_0} :catch_0

    .line 245
    :catch_0
    :try_start_1
    iget-object p0, p0, Lorg/nameless/custom/colorpicker/ColorPickerPreference;->mEditText:Landroid/widget/EditText;

    const/16 v0, 0x10

    invoke-static {p1, v0}, Ljava/lang/Integer;->toString(II)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p0, p1}, Landroid/widget/EditText;->setText(Ljava/lang/CharSequence;)V
    :try_end_1
    .catch Ljava/lang/NullPointerException; {:try_start_1 .. :try_end_1} :catch_1

    :catch_1
    return-void
.end method

.method protected onGetDefaultValue(Landroid/content/res/TypedArray;I)Ljava/lang/Object;
    .locals 0

    const/high16 p0, -0x1000000

    .line 102
    invoke-virtual {p1, p2, p0}, Landroid/content/res/TypedArray;->getInt(II)I

    move-result p0

    .line 103
    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    return-object p0
.end method

.method public onPreferenceClick(Landroidx/preference/Preference;)Z
    .locals 0

    const/4 p0, 0x0

    return p0
.end method

.method protected onRestoreInstanceState(Landroid/os/Parcelable;)V
    .locals 1

    if-eqz p1, :cond_1

    .line 387
    instance-of v0, p1, Lorg/nameless/custom/colorpicker/ColorPickerPreference$SavedState;

    if-nez v0, :cond_0

    goto :goto_0

    .line 393
    :cond_0
    check-cast p1, Lorg/nameless/custom/colorpicker/ColorPickerPreference$SavedState;

    .line 394
    invoke-virtual {p1}, Landroid/view/AbsSavedState;->getSuperState()Landroid/os/Parcelable;

    move-result-object v0

    invoke-super {p0, v0}, Landroidx/preference/Preference;->onRestoreInstanceState(Landroid/os/Parcelable;)V

    .line 395
    iget-object p1, p1, Lorg/nameless/custom/colorpicker/ColorPickerPreference$SavedState;->dialogBundle:Landroid/os/Bundle;

    invoke-virtual {p0, p1}, Lorg/nameless/custom/colorpicker/ColorPickerPreference;->showDialog(Landroid/os/Bundle;)V

    return-void

    .line 389
    :cond_1
    :goto_0
    invoke-super {p0, p1}, Landroidx/preference/Preference;->onRestoreInstanceState(Landroid/os/Parcelable;)V

    return-void
.end method

.method protected onSaveInstanceState()Landroid/os/Parcelable;
    .locals 2

    .line 375
    invoke-super {p0}, Landroidx/preference/Preference;->onSaveInstanceState()Landroid/os/Parcelable;

    move-result-object v0

    .line 376
    iget-object v1, p0, Lorg/nameless/custom/colorpicker/ColorPickerPreference;->mDialog:Lorg/nameless/custom/colorpicker/ColorPickerDialog;

    if-eqz v1, :cond_1

    invoke-virtual {v1}, Landroid/app/AlertDialog;->isShowing()Z

    move-result v1

    if-nez v1, :cond_0

    goto :goto_0

    .line 380
    :cond_0
    new-instance v1, Lorg/nameless/custom/colorpicker/ColorPickerPreference$SavedState;

    invoke-direct {v1, v0}, Lorg/nameless/custom/colorpicker/ColorPickerPreference$SavedState;-><init>(Landroid/os/Parcelable;)V

    .line 381
    iget-object p0, p0, Lorg/nameless/custom/colorpicker/ColorPickerPreference;->mDialog:Lorg/nameless/custom/colorpicker/ColorPickerDialog;

    invoke-virtual {p0}, Lorg/nameless/custom/colorpicker/ColorPickerDialog;->onSaveInstanceState()Landroid/os/Bundle;

    move-result-object p0

    iput-object p0, v1, Lorg/nameless/custom/colorpicker/ColorPickerPreference$SavedState;->dialogBundle:Landroid/os/Bundle;

    return-object v1

    :cond_1
    :goto_0
    return-object v0
.end method

.method protected onSetInitialValue(ZLjava/lang/Object;)V
    .locals 0

    if-nez p2, :cond_0

    const/high16 p1, -0x1000000

    .line 113
    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p2

    .line 115
    :cond_0
    check-cast p2, Ljava/lang/Integer;

    invoke-virtual {p2}, Ljava/lang/Integer;->intValue()I

    move-result p1

    invoke-virtual {p0, p1}, Landroidx/preference/Preference;->getPersistedInt(I)I

    move-result p1

    iput p1, p0, Lorg/nameless/custom/colorpicker/ColorPickerPreference;->mCurrentValue:I

    .line 116
    invoke-virtual {p0, p1}, Lorg/nameless/custom/colorpicker/ColorPickerPreference;->onColorChanged(I)V

    return-void
.end method

.method public setEnabled(Z)V
    .locals 0

    .line 230
    invoke-super {p0, p1}, Landroidx/preference/Preference;->setEnabled(Z)V

    .line 231
    invoke-direct {p0}, Lorg/nameless/custom/colorpicker/ColorPickerPreference;->setPreviewColor()V

    .line 232
    invoke-direct {p0}, Lorg/nameless/custom/colorpicker/ColorPickerPreference;->setDefaultButton()V

    return-void
.end method

.method protected showDialog(Landroid/os/Bundle;)V
    .locals 3

    .line 255
    new-instance v0, Lorg/nameless/custom/colorpicker/ColorPickerDialog;

    invoke-virtual {p0}, Landroidx/preference/Preference;->getContext()Landroid/content/Context;

    move-result-object v1

    iget v2, p0, Lorg/nameless/custom/colorpicker/ColorPickerPreference;->mCurrentValue:I

    invoke-direct {v0, v1, v2}, Lorg/nameless/custom/colorpicker/ColorPickerDialog;-><init>(Landroid/content/Context;I)V

    iput-object v0, p0, Lorg/nameless/custom/colorpicker/ColorPickerPreference;->mDialog:Lorg/nameless/custom/colorpicker/ColorPickerDialog;

    .line 256
    invoke-virtual {v0, p0}, Lorg/nameless/custom/colorpicker/ColorPickerDialog;->setOnColorChangedListener(Lorg/nameless/custom/colorpicker/ColorPickerDialog$OnColorChangedListener;)V

    .line 257
    iget-boolean v0, p0, Lorg/nameless/custom/colorpicker/ColorPickerPreference;->mAlphaSliderEnabled:Z

    if-eqz v0, :cond_0

    .line 258
    iget-object v0, p0, Lorg/nameless/custom/colorpicker/ColorPickerPreference;->mDialog:Lorg/nameless/custom/colorpicker/ColorPickerDialog;

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Lorg/nameless/custom/colorpicker/ColorPickerDialog;->setAlphaSliderVisible(Z)V

    :cond_0
    if-eqz p1, :cond_1

    .line 261
    iget-object v0, p0, Lorg/nameless/custom/colorpicker/ColorPickerPreference;->mDialog:Lorg/nameless/custom/colorpicker/ColorPickerDialog;

    invoke-virtual {v0, p1}, Lorg/nameless/custom/colorpicker/ColorPickerDialog;->onRestoreInstanceState(Landroid/os/Bundle;)V

    .line 263
    :cond_1
    iget-object p1, p0, Lorg/nameless/custom/colorpicker/ColorPickerPreference;->mDialog:Lorg/nameless/custom/colorpicker/ColorPickerDialog;

    invoke-virtual {p1}, Landroid/app/AlertDialog;->show()V

    .line 264
    iget-object p0, p0, Lorg/nameless/custom/colorpicker/ColorPickerPreference;->mDialog:Lorg/nameless/custom/colorpicker/ColorPickerDialog;

    invoke-virtual {p0}, Landroid/app/AlertDialog;->getWindow()Landroid/view/Window;

    move-result-object p0

    const/4 p1, 0x2

    invoke-virtual {p0, p1}, Landroid/view/Window;->setSoftInputMode(I)V

    return-void
.end method
