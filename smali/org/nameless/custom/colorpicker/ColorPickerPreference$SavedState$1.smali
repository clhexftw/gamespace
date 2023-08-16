.class Lorg/nameless/custom/colorpicker/ColorPickerPreference$SavedState$1;
.super Ljava/lang/Object;
.source "ColorPickerPreference.java"

# interfaces
.implements Landroid/os/Parcelable$Creator;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/nameless/custom/colorpicker/ColorPickerPreference$SavedState;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Object;",
        "Landroid/os/Parcelable$Creator<",
        "Lorg/nameless/custom/colorpicker/ColorPickerPreference$SavedState;",
        ">;"
    }
.end annotation


# direct methods
.method constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public bridge synthetic createFromParcel(Landroid/os/Parcel;)Ljava/lang/Object;
    .locals 0

    .line 418
    invoke-virtual {p0, p1}, Lorg/nameless/custom/colorpicker/ColorPickerPreference$SavedState$1;->createFromParcel(Landroid/os/Parcel;)Lorg/nameless/custom/colorpicker/ColorPickerPreference$SavedState;

    move-result-object p0

    return-object p0
.end method

.method public createFromParcel(Landroid/os/Parcel;)Lorg/nameless/custom/colorpicker/ColorPickerPreference$SavedState;
    .locals 0

    .line 420
    new-instance p0, Lorg/nameless/custom/colorpicker/ColorPickerPreference$SavedState;

    invoke-direct {p0, p1}, Lorg/nameless/custom/colorpicker/ColorPickerPreference$SavedState;-><init>(Landroid/os/Parcel;)V

    return-object p0
.end method

.method public bridge synthetic newArray(I)[Ljava/lang/Object;
    .locals 0

    .line 418
    invoke-virtual {p0, p1}, Lorg/nameless/custom/colorpicker/ColorPickerPreference$SavedState$1;->newArray(I)[Lorg/nameless/custom/colorpicker/ColorPickerPreference$SavedState;

    move-result-object p0

    return-object p0
.end method

.method public newArray(I)[Lorg/nameless/custom/colorpicker/ColorPickerPreference$SavedState;
    .locals 0

    .line 424
    new-array p0, p1, [Lorg/nameless/custom/colorpicker/ColorPickerPreference$SavedState;

    return-object p0
.end method
