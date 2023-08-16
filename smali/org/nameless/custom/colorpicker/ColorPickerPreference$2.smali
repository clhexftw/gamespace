.class Lorg/nameless/custom/colorpicker/ColorPickerPreference$2;
.super Ljava/lang/Object;
.source "ColorPickerPreference.java"

# interfaces
.implements Landroid/view/View$OnClickListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lorg/nameless/custom/colorpicker/ColorPickerPreference;->setDefaultButton()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lorg/nameless/custom/colorpicker/ColorPickerPreference;


# direct methods
.method constructor <init>(Lorg/nameless/custom/colorpicker/ColorPickerPreference;)V
    .locals 0

    .line 189
    iput-object p1, p0, Lorg/nameless/custom/colorpicker/ColorPickerPreference$2;->this$0:Lorg/nameless/custom/colorpicker/ColorPickerPreference;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onClick(Landroid/view/View;)V
    .locals 1

    .line 192
    iget-object p1, p0, Lorg/nameless/custom/colorpicker/ColorPickerPreference$2;->this$0:Lorg/nameless/custom/colorpicker/ColorPickerPreference;

    invoke-static {p1}, Lorg/nameless/custom/colorpicker/ColorPickerPreference;->-$$Nest$fgetmDefaultValue(Lorg/nameless/custom/colorpicker/ColorPickerPreference;)I

    move-result v0

    invoke-virtual {p1, v0}, Lorg/nameless/custom/colorpicker/ColorPickerPreference;->onColorChanged(I)V

    .line 193
    iget-object p0, p0, Lorg/nameless/custom/colorpicker/ColorPickerPreference$2;->this$0:Lorg/nameless/custom/colorpicker/ColorPickerPreference;

    invoke-static {p0}, Lorg/nameless/custom/colorpicker/ColorPickerPreference;->-$$Nest$mdoHapticFeedback(Lorg/nameless/custom/colorpicker/ColorPickerPreference;)V

    return-void
.end method
