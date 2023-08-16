.class public Lorg/nameless/custom/colorpicker/ColorPickerDialog;
.super Landroid/app/AlertDialog;
.source "ColorPickerDialog.java"

# interfaces
.implements Lorg/nameless/custom/colorpicker/ColorPickerView$OnColorChangedListener;
.implements Landroid/view/View$OnClickListener;
.implements Landroid/view/View$OnKeyListener;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lorg/nameless/custom/colorpicker/ColorPickerDialog$OnColorChangedListener;
    }
.end annotation


# instance fields
.field private mColorPicker:Lorg/nameless/custom/colorpicker/ColorPickerView;

.field private final mContext:Landroid/content/Context;

.field private mHex:Landroid/widget/EditText;

.field private mListener:Lorg/nameless/custom/colorpicker/ColorPickerDialog$OnColorChangedListener;

.field private mNewColor:Lorg/nameless/custom/colorpicker/ColorPickerPanelView;

.field private mOldColor:Lorg/nameless/custom/colorpicker/ColorPickerPanelView;

.field private final mVibrator:Landroid/os/Vibrator;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .line 40
    const-class v0, Lorg/nameless/custom/colorpicker/ColorPickerDialog;

    return-void
.end method

.method constructor <init>(Landroid/content/Context;I)V
    .locals 0

    .line 57
    invoke-direct {p0, p1}, Landroid/app/AlertDialog;-><init>(Landroid/content/Context;)V

    .line 59
    invoke-direct {p0, p2}, Lorg/nameless/custom/colorpicker/ColorPickerDialog;->init(I)V

    .line 61
    iput-object p1, p0, Lorg/nameless/custom/colorpicker/ColorPickerDialog;->mContext:Landroid/content/Context;

    const-string p2, "vibrator"

    .line 62
    invoke-virtual {p1, p2}, Landroid/content/Context;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Landroid/os/Vibrator;

    iput-object p1, p0, Lorg/nameless/custom/colorpicker/ColorPickerDialog;->mVibrator:Landroid/os/Vibrator;

    return-void
.end method

.method private doHapticFeedback()V
    .locals 3

    .line 181
    iget-object v0, p0, Lorg/nameless/custom/colorpicker/ColorPickerDialog;->mContext:Landroid/content/Context;

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

    .line 185
    iget-object p0, p0, Lorg/nameless/custom/colorpicker/ColorPickerDialog;->mVibrator:Landroid/os/Vibrator;

    invoke-static {v1}, Landroid/os/VibrationEffect;->get(I)Landroid/os/VibrationEffect;

    move-result-object v0

    invoke-virtual {p0, v0}, Landroid/os/Vibrator;->vibrate(Landroid/os/VibrationEffect;)V

    :cond_1
    return-void
.end method

.method private init(I)V
    .locals 2

    .line 66
    invoke-virtual {p0}, Landroid/app/AlertDialog;->getWindow()Landroid/view/Window;

    move-result-object v0

    if-eqz v0, :cond_0

    .line 67
    invoke-virtual {p0}, Landroid/app/AlertDialog;->getWindow()Landroid/view/Window;

    move-result-object v0

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Landroid/view/Window;->setFormat(I)V

    .line 68
    invoke-virtual {p0, v1}, Landroid/app/AlertDialog;->requestWindowFeature(I)Z

    .line 69
    invoke-direct {p0, p1}, Lorg/nameless/custom/colorpicker/ColorPickerDialog;->setUp(I)V

    :cond_0
    return-void
.end method

.method private setColorFromHex()V
    .locals 2

    .line 74
    iget-object v0, p0, Lorg/nameless/custom/colorpicker/ColorPickerDialog;->mHex:Landroid/widget/EditText;

    invoke-virtual {v0}, Landroid/widget/EditText;->getText()Landroid/text/Editable;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v0

    .line 76
    :try_start_0
    invoke-static {v0}, Lorg/nameless/custom/colorpicker/ColorPickerPreference;->convertToColorInt(Ljava/lang/String;)I

    move-result v0

    .line 77
    iget-object p0, p0, Lorg/nameless/custom/colorpicker/ColorPickerDialog;->mColorPicker:Lorg/nameless/custom/colorpicker/ColorPickerView;

    const/4 v1, 0x1

    invoke-virtual {p0, v0, v1}, Lorg/nameless/custom/colorpicker/ColorPickerView;->setColor(IZ)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    :catch_0
    return-void
.end method

.method private setUp(I)V
    .locals 5

    .line 84
    invoke-virtual {p0}, Landroid/app/AlertDialog;->getContext()Landroid/content/Context;

    move-result-object v0

    const-string v1, "layout_inflater"

    invoke-virtual {v0, v1}, Landroid/content/Context;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/view/LayoutInflater;

    .line 88
    sget v1, Lorg/nameless/custom/R$layout;->preference_color_picker:I

    const/4 v2, 0x0

    invoke-virtual {v0, v1, v2}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;)Landroid/view/View;

    move-result-object v0

    .line 90
    sget v1, Lorg/nameless/custom/R$id;->color_picker_view:I

    invoke-virtual {v0, v1}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v1

    check-cast v1, Lorg/nameless/custom/colorpicker/ColorPickerView;

    iput-object v1, p0, Lorg/nameless/custom/colorpicker/ColorPickerDialog;->mColorPicker:Lorg/nameless/custom/colorpicker/ColorPickerView;

    .line 91
    sget v1, Lorg/nameless/custom/R$id;->old_color_panel:I

    invoke-virtual {v0, v1}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v1

    check-cast v1, Lorg/nameless/custom/colorpicker/ColorPickerPanelView;

    iput-object v1, p0, Lorg/nameless/custom/colorpicker/ColorPickerDialog;->mOldColor:Lorg/nameless/custom/colorpicker/ColorPickerPanelView;

    .line 92
    sget v1, Lorg/nameless/custom/R$id;->new_color_panel:I

    invoke-virtual {v0, v1}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v1

    check-cast v1, Lorg/nameless/custom/colorpicker/ColorPickerPanelView;

    iput-object v1, p0, Lorg/nameless/custom/colorpicker/ColorPickerDialog;->mNewColor:Lorg/nameless/custom/colorpicker/ColorPickerPanelView;

    .line 94
    sget v1, Lorg/nameless/custom/R$id;->hex:I

    invoke-virtual {v0, v1}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v1

    check-cast v1, Landroid/widget/EditText;

    iput-object v1, p0, Lorg/nameless/custom/colorpicker/ColorPickerDialog;->mHex:Landroid/widget/EditText;

    .line 96
    iget-object v1, p0, Lorg/nameless/custom/colorpicker/ColorPickerDialog;->mOldColor:Lorg/nameless/custom/colorpicker/ColorPickerPanelView;

    invoke-virtual {v1}, Landroid/view/View;->getParent()Landroid/view/ViewParent;

    move-result-object v1

    check-cast v1, Landroid/widget/LinearLayout;

    iget-object v2, p0, Lorg/nameless/custom/colorpicker/ColorPickerDialog;->mColorPicker:Lorg/nameless/custom/colorpicker/ColorPickerView;

    invoke-virtual {v2}, Lorg/nameless/custom/colorpicker/ColorPickerView;->getDrawingOffset()F

    move-result v2

    invoke-static {v2}, Ljava/lang/Math;->round(F)I

    move-result v2

    iget-object v3, p0, Lorg/nameless/custom/colorpicker/ColorPickerDialog;->mColorPicker:Lorg/nameless/custom/colorpicker/ColorPickerView;

    .line 97
    invoke-virtual {v3}, Lorg/nameless/custom/colorpicker/ColorPickerView;->getDrawingOffset()F

    move-result v3

    invoke-static {v3}, Ljava/lang/Math;->round(F)I

    move-result v3

    const/4 v4, 0x0

    .line 96
    invoke-virtual {v1, v2, v4, v3, v4}, Landroid/widget/LinearLayout;->setPadding(IIII)V

    .line 99
    iget-object v1, p0, Lorg/nameless/custom/colorpicker/ColorPickerDialog;->mOldColor:Lorg/nameless/custom/colorpicker/ColorPickerPanelView;

    invoke-virtual {v1, p0}, Landroid/view/View;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 100
    iget-object v1, p0, Lorg/nameless/custom/colorpicker/ColorPickerDialog;->mNewColor:Lorg/nameless/custom/colorpicker/ColorPickerPanelView;

    invoke-virtual {v1, p0}, Landroid/view/View;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 101
    iget-object v1, p0, Lorg/nameless/custom/colorpicker/ColorPickerDialog;->mColorPicker:Lorg/nameless/custom/colorpicker/ColorPickerView;

    invoke-virtual {v1, p0}, Lorg/nameless/custom/colorpicker/ColorPickerView;->setOnColorChangedListener(Lorg/nameless/custom/colorpicker/ColorPickerView$OnColorChangedListener;)V

    .line 102
    iget-object v1, p0, Lorg/nameless/custom/colorpicker/ColorPickerDialog;->mOldColor:Lorg/nameless/custom/colorpicker/ColorPickerPanelView;

    invoke-virtual {v1, p1}, Lorg/nameless/custom/colorpicker/ColorPickerPanelView;->setColor(I)V

    .line 103
    iget-object v1, p0, Lorg/nameless/custom/colorpicker/ColorPickerDialog;->mColorPicker:Lorg/nameless/custom/colorpicker/ColorPickerView;

    const/4 v2, 0x1

    invoke-virtual {v1, p1, v2}, Lorg/nameless/custom/colorpicker/ColorPickerView;->setColor(IZ)V

    .line 105
    iget-object v1, p0, Lorg/nameless/custom/colorpicker/ColorPickerDialog;->mHex:Landroid/widget/EditText;

    if-eqz v1, :cond_0

    .line 106
    invoke-static {p1}, Lorg/nameless/custom/colorpicker/ColorPickerPreference;->convertToRGB(I)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {v1, p1}, Landroid/widget/EditText;->setText(Ljava/lang/CharSequence;)V

    .line 107
    iget-object p1, p0, Lorg/nameless/custom/colorpicker/ColorPickerDialog;->mHex:Landroid/widget/EditText;

    invoke-virtual {p1, p0}, Landroid/widget/EditText;->setOnKeyListener(Landroid/view/View$OnKeyListener;)V

    .line 110
    :cond_0
    invoke-virtual {p0, v0}, Landroid/app/AlertDialog;->setView(Landroid/view/View;)V

    return-void
.end method


# virtual methods
.method public onClick(Landroid/view/View;)V
    .locals 1

    .line 154
    invoke-virtual {p1}, Landroid/view/View;->getId()I

    move-result p1

    sget v0, Lorg/nameless/custom/R$id;->new_color_panel:I

    if-ne p1, v0, :cond_0

    .line 155
    iget-object p1, p0, Lorg/nameless/custom/colorpicker/ColorPickerDialog;->mListener:Lorg/nameless/custom/colorpicker/ColorPickerDialog$OnColorChangedListener;

    if-eqz p1, :cond_0

    .line 156
    iget-object v0, p0, Lorg/nameless/custom/colorpicker/ColorPickerDialog;->mNewColor:Lorg/nameless/custom/colorpicker/ColorPickerPanelView;

    invoke-virtual {v0}, Lorg/nameless/custom/colorpicker/ColorPickerPanelView;->getColor()I

    move-result v0

    invoke-interface {p1, v0}, Lorg/nameless/custom/colorpicker/ColorPickerDialog$OnColorChangedListener;->onColorChanged(I)V

    .line 159
    :cond_0
    invoke-direct {p0}, Lorg/nameless/custom/colorpicker/ColorPickerDialog;->doHapticFeedback()V

    .line 160
    invoke-virtual {p0}, Landroid/app/AlertDialog;->dismiss()V

    return-void
.end method

.method public onColorChanged(I)V
    .locals 1

    .line 116
    iget-object v0, p0, Lorg/nameless/custom/colorpicker/ColorPickerDialog;->mNewColor:Lorg/nameless/custom/colorpicker/ColorPickerPanelView;

    invoke-virtual {v0, p1}, Lorg/nameless/custom/colorpicker/ColorPickerPanelView;->setColor(I)V

    .line 118
    :try_start_0
    iget-object p0, p0, Lorg/nameless/custom/colorpicker/ColorPickerDialog;->mHex:Landroid/widget/EditText;

    if-eqz p0, :cond_0

    .line 119
    invoke-static {p1}, Lorg/nameless/custom/colorpicker/ColorPickerPreference;->convertToRGB(I)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p0, p1}, Landroid/widget/EditText;->setText(Ljava/lang/CharSequence;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    :catch_0
    :cond_0
    return-void
.end method

.method public onKey(Landroid/view/View;ILandroid/view/KeyEvent;)Z
    .locals 0

    .line 144
    invoke-virtual {p3}, Landroid/view/KeyEvent;->getAction()I

    move-result p1

    if-nez p1, :cond_0

    const/16 p1, 0x42

    if-ne p2, p1, :cond_0

    .line 146
    invoke-direct {p0}, Lorg/nameless/custom/colorpicker/ColorPickerDialog;->setColorFromHex()V

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method public onRestoreInstanceState(Landroid/os/Bundle;)V
    .locals 2

    .line 175
    invoke-super {p0, p1}, Landroid/app/AlertDialog;->onRestoreInstanceState(Landroid/os/Bundle;)V

    .line 176
    iget-object v0, p0, Lorg/nameless/custom/colorpicker/ColorPickerDialog;->mOldColor:Lorg/nameless/custom/colorpicker/ColorPickerPanelView;

    const-string v1, "old_color"

    invoke-virtual {p1, v1}, Landroid/os/Bundle;->getInt(Ljava/lang/String;)I

    move-result v1

    invoke-virtual {v0, v1}, Lorg/nameless/custom/colorpicker/ColorPickerPanelView;->setColor(I)V

    .line 177
    iget-object p0, p0, Lorg/nameless/custom/colorpicker/ColorPickerDialog;->mColorPicker:Lorg/nameless/custom/colorpicker/ColorPickerView;

    const-string v0, "new_color"

    invoke-virtual {p1, v0}, Landroid/os/Bundle;->getInt(Ljava/lang/String;)I

    move-result p1

    const/4 v0, 0x1

    invoke-virtual {p0, p1, v0}, Lorg/nameless/custom/colorpicker/ColorPickerView;->setColor(IZ)V

    return-void
.end method

.method public onSaveInstanceState()Landroid/os/Bundle;
    .locals 3

    .line 166
    invoke-super {p0}, Landroid/app/AlertDialog;->onSaveInstanceState()Landroid/os/Bundle;

    move-result-object v0

    .line 167
    iget-object v1, p0, Lorg/nameless/custom/colorpicker/ColorPickerDialog;->mOldColor:Lorg/nameless/custom/colorpicker/ColorPickerPanelView;

    invoke-virtual {v1}, Lorg/nameless/custom/colorpicker/ColorPickerPanelView;->getColor()I

    move-result v1

    const-string v2, "old_color"

    invoke-virtual {v0, v2, v1}, Landroid/os/Bundle;->putInt(Ljava/lang/String;I)V

    .line 168
    iget-object v1, p0, Lorg/nameless/custom/colorpicker/ColorPickerDialog;->mNewColor:Lorg/nameless/custom/colorpicker/ColorPickerPanelView;

    invoke-virtual {v1}, Lorg/nameless/custom/colorpicker/ColorPickerPanelView;->getColor()I

    move-result v1

    const-string v2, "new_color"

    invoke-virtual {v0, v2, v1}, Landroid/os/Bundle;->putInt(Ljava/lang/String;I)V

    .line 169
    invoke-virtual {p0}, Landroid/app/AlertDialog;->dismiss()V

    return-object v0
.end method

.method setAlphaSliderVisible(Z)V
    .locals 0

    .line 126
    iget-object p0, p0, Lorg/nameless/custom/colorpicker/ColorPickerDialog;->mColorPicker:Lorg/nameless/custom/colorpicker/ColorPickerView;

    invoke-virtual {p0, p1}, Lorg/nameless/custom/colorpicker/ColorPickerView;->setAlphaSliderVisible(Z)V

    return-void
.end method

.method setOnColorChangedListener(Lorg/nameless/custom/colorpicker/ColorPickerDialog$OnColorChangedListener;)V
    .locals 0

    .line 135
    iput-object p1, p0, Lorg/nameless/custom/colorpicker/ColorPickerDialog;->mListener:Lorg/nameless/custom/colorpicker/ColorPickerDialog$OnColorChangedListener;

    return-void
.end method
