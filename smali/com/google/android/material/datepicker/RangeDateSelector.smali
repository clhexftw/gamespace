.class public Lcom/google/android/material/datepicker/RangeDateSelector;
.super Ljava/lang/Object;
.source "RangeDateSelector.java"

# interfaces
.implements Lcom/google/android/material/datepicker/DateSelector;


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Object;",
        "Lcom/google/android/material/datepicker/DateSelector<",
        "Landroidx/core/util/Pair<",
        "Ljava/lang/Long;",
        "Ljava/lang/Long;",
        ">;>;"
    }
.end annotation


# static fields
.field public static final CREATOR:Landroid/os/Parcelable$Creator;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroid/os/Parcelable$Creator<",
            "Lcom/google/android/material/datepicker/RangeDateSelector;",
            ">;"
        }
    .end annotation
.end field


# instance fields
.field private final invalidRangeEndError:Ljava/lang/String;

.field private invalidRangeStartError:Ljava/lang/String;

.field private proposedTextEnd:Ljava/lang/Long;

.field private proposedTextStart:Ljava/lang/Long;

.field private selectedEndItem:Ljava/lang/Long;

.field private selectedStartItem:Ljava/lang/Long;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .line 279
    new-instance v0, Lcom/google/android/material/datepicker/RangeDateSelector$3;

    invoke-direct {v0}, Lcom/google/android/material/datepicker/RangeDateSelector$3;-><init>()V

    sput-object v0, Lcom/google/android/material/datepicker/RangeDateSelector;->CREATOR:Landroid/os/Parcelable$Creator;

    return-void
.end method

.method public constructor <init>()V
    .locals 1

    .line 51
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const-string v0, " "

    .line 55
    iput-object v0, p0, Lcom/google/android/material/datepicker/RangeDateSelector;->invalidRangeEndError:Ljava/lang/String;

    const/4 v0, 0x0

    .line 56
    iput-object v0, p0, Lcom/google/android/material/datepicker/RangeDateSelector;->selectedStartItem:Ljava/lang/Long;

    .line 57
    iput-object v0, p0, Lcom/google/android/material/datepicker/RangeDateSelector;->selectedEndItem:Ljava/lang/Long;

    .line 58
    iput-object v0, p0, Lcom/google/android/material/datepicker/RangeDateSelector;->proposedTextStart:Ljava/lang/Long;

    .line 59
    iput-object v0, p0, Lcom/google/android/material/datepicker/RangeDateSelector;->proposedTextEnd:Ljava/lang/Long;

    return-void
.end method

.method static synthetic access$002(Lcom/google/android/material/datepicker/RangeDateSelector;Ljava/lang/Long;)Ljava/lang/Long;
    .locals 0

    .line 51
    iput-object p1, p0, Lcom/google/android/material/datepicker/RangeDateSelector;->proposedTextStart:Ljava/lang/Long;

    return-object p1
.end method

.method static synthetic access$100(Lcom/google/android/material/datepicker/RangeDateSelector;Lcom/google/android/material/textfield/TextInputLayout;Lcom/google/android/material/textfield/TextInputLayout;Lcom/google/android/material/datepicker/OnSelectionChangedListener;)V
    .locals 0

    .line 51
    invoke-direct {p0, p1, p2, p3}, Lcom/google/android/material/datepicker/RangeDateSelector;->updateIfValidTextProposal(Lcom/google/android/material/textfield/TextInputLayout;Lcom/google/android/material/textfield/TextInputLayout;Lcom/google/android/material/datepicker/OnSelectionChangedListener;)V

    return-void
.end method

.method static synthetic access$202(Lcom/google/android/material/datepicker/RangeDateSelector;Ljava/lang/Long;)Ljava/lang/Long;
    .locals 0

    .line 51
    iput-object p1, p0, Lcom/google/android/material/datepicker/RangeDateSelector;->proposedTextEnd:Ljava/lang/Long;

    return-object p1
.end method

.method static synthetic access$302(Lcom/google/android/material/datepicker/RangeDateSelector;Ljava/lang/Long;)Ljava/lang/Long;
    .locals 0

    .line 51
    iput-object p1, p0, Lcom/google/android/material/datepicker/RangeDateSelector;->selectedStartItem:Ljava/lang/Long;

    return-object p1
.end method

.method static synthetic access$402(Lcom/google/android/material/datepicker/RangeDateSelector;Ljava/lang/Long;)Ljava/lang/Long;
    .locals 0

    .line 51
    iput-object p1, p0, Lcom/google/android/material/datepicker/RangeDateSelector;->selectedEndItem:Ljava/lang/Long;

    return-object p1
.end method

.method private clearInvalidRange(Lcom/google/android/material/textfield/TextInputLayout;Lcom/google/android/material/textfield/TextInputLayout;)V
    .locals 2

    .line 263
    invoke-virtual {p1}, Lcom/google/android/material/textfield/TextInputLayout;->getError()Ljava/lang/CharSequence;

    move-result-object v0

    const/4 v1, 0x0

    if-eqz v0, :cond_0

    iget-object p0, p0, Lcom/google/android/material/datepicker/RangeDateSelector;->invalidRangeStartError:Ljava/lang/String;

    invoke-virtual {p1}, Lcom/google/android/material/textfield/TextInputLayout;->getError()Ljava/lang/CharSequence;

    move-result-object v0

    invoke-virtual {p0, v0}, Ljava/lang/String;->contentEquals(Ljava/lang/CharSequence;)Z

    move-result p0

    if-eqz p0, :cond_0

    .line 264
    invoke-virtual {p1, v1}, Lcom/google/android/material/textfield/TextInputLayout;->setError(Ljava/lang/CharSequence;)V

    .line 266
    :cond_0
    invoke-virtual {p2}, Lcom/google/android/material/textfield/TextInputLayout;->getError()Ljava/lang/CharSequence;

    move-result-object p0

    if-eqz p0, :cond_1

    invoke-virtual {p2}, Lcom/google/android/material/textfield/TextInputLayout;->getError()Ljava/lang/CharSequence;

    move-result-object p0

    const-string p1, " "

    invoke-virtual {p1, p0}, Ljava/lang/String;->contentEquals(Ljava/lang/CharSequence;)Z

    move-result p0

    if-eqz p0, :cond_1

    .line 267
    invoke-virtual {p2, v1}, Lcom/google/android/material/textfield/TextInputLayout;->setError(Ljava/lang/CharSequence;)V

    :cond_1
    return-void
.end method

.method private isValidRange(JJ)Z
    .locals 0

    cmp-long p0, p1, p3

    if-gtz p0, :cond_0

    const/4 p0, 0x1

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    :goto_0
    return p0
.end method

.method private setInvalidRange(Lcom/google/android/material/textfield/TextInputLayout;Lcom/google/android/material/textfield/TextInputLayout;)V
    .locals 0

    .line 272
    iget-object p0, p0, Lcom/google/android/material/datepicker/RangeDateSelector;->invalidRangeStartError:Ljava/lang/String;

    invoke-virtual {p1, p0}, Lcom/google/android/material/textfield/TextInputLayout;->setError(Ljava/lang/CharSequence;)V

    const-string p0, " "

    .line 273
    invoke-virtual {p2, p0}, Lcom/google/android/material/textfield/TextInputLayout;->setError(Ljava/lang/CharSequence;)V

    return-void
.end method

.method private updateIfValidTextProposal(Lcom/google/android/material/textfield/TextInputLayout;Lcom/google/android/material/textfield/TextInputLayout;Lcom/google/android/material/datepicker/OnSelectionChangedListener;)V
    .locals 4
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/google/android/material/textfield/TextInputLayout;",
            "Lcom/google/android/material/textfield/TextInputLayout;",
            "Lcom/google/android/material/datepicker/OnSelectionChangedListener<",
            "Landroidx/core/util/Pair<",
            "Ljava/lang/Long;",
            "Ljava/lang/Long;",
            ">;>;)V"
        }
    .end annotation

    .line 247
    iget-object v0, p0, Lcom/google/android/material/datepicker/RangeDateSelector;->proposedTextStart:Ljava/lang/Long;

    if-eqz v0, :cond_2

    iget-object v1, p0, Lcom/google/android/material/datepicker/RangeDateSelector;->proposedTextEnd:Ljava/lang/Long;

    if-nez v1, :cond_0

    goto :goto_1

    .line 252
    :cond_0
    invoke-virtual {v0}, Ljava/lang/Long;->longValue()J

    move-result-wide v0

    iget-object v2, p0, Lcom/google/android/material/datepicker/RangeDateSelector;->proposedTextEnd:Ljava/lang/Long;

    invoke-virtual {v2}, Ljava/lang/Long;->longValue()J

    move-result-wide v2

    invoke-direct {p0, v0, v1, v2, v3}, Lcom/google/android/material/datepicker/RangeDateSelector;->isValidRange(JJ)Z

    move-result v0

    if-eqz v0, :cond_1

    .line 253
    iget-object p1, p0, Lcom/google/android/material/datepicker/RangeDateSelector;->proposedTextStart:Ljava/lang/Long;

    iput-object p1, p0, Lcom/google/android/material/datepicker/RangeDateSelector;->selectedStartItem:Ljava/lang/Long;

    .line 254
    iget-object p1, p0, Lcom/google/android/material/datepicker/RangeDateSelector;->proposedTextEnd:Ljava/lang/Long;

    iput-object p1, p0, Lcom/google/android/material/datepicker/RangeDateSelector;->selectedEndItem:Ljava/lang/Long;

    .line 255
    invoke-virtual {p0}, Lcom/google/android/material/datepicker/RangeDateSelector;->getSelection()Landroidx/core/util/Pair;

    move-result-object p0

    invoke-virtual {p3, p0}, Lcom/google/android/material/datepicker/OnSelectionChangedListener;->onSelectionChanged(Ljava/lang/Object;)V

    goto :goto_0

    .line 257
    :cond_1
    invoke-direct {p0, p1, p2}, Lcom/google/android/material/datepicker/RangeDateSelector;->setInvalidRange(Lcom/google/android/material/textfield/TextInputLayout;Lcom/google/android/material/textfield/TextInputLayout;)V

    .line 258
    invoke-virtual {p3}, Lcom/google/android/material/datepicker/OnSelectionChangedListener;->onIncompleteSelectionChanged()V

    :goto_0
    return-void

    .line 248
    :cond_2
    :goto_1
    invoke-direct {p0, p1, p2}, Lcom/google/android/material/datepicker/RangeDateSelector;->clearInvalidRange(Lcom/google/android/material/textfield/TextInputLayout;Lcom/google/android/material/textfield/TextInputLayout;)V

    .line 249
    invoke-virtual {p3}, Lcom/google/android/material/datepicker/OnSelectionChangedListener;->onIncompleteSelectionChanged()V

    return-void
.end method


# virtual methods
.method public describeContents()I
    .locals 0

    const/4 p0, 0x0

    return p0
.end method

.method public getDefaultThemeResId(Landroid/content/Context;)I
    .locals 2

    .line 124
    invoke-virtual {p1}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object p0

    .line 125
    invoke-virtual {p0}, Landroid/content/res/Resources;->getDisplayMetrics()Landroid/util/DisplayMetrics;

    move-result-object v0

    .line 126
    sget v1, Lcom/google/android/material/R$dimen;->mtrl_calendar_maximum_default_fullscreen_minor_axis:I

    .line 127
    invoke-virtual {p0, v1}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result p0

    .line 128
    iget v1, v0, Landroid/util/DisplayMetrics;->widthPixels:I

    iget v0, v0, Landroid/util/DisplayMetrics;->heightPixels:I

    invoke-static {v1, v0}, Ljava/lang/Math;->min(II)I

    move-result v0

    if-le v0, p0, :cond_0

    .line 131
    sget p0, Lcom/google/android/material/R$attr;->materialCalendarTheme:I

    goto :goto_0

    .line 132
    :cond_0
    sget p0, Lcom/google/android/material/R$attr;->materialCalendarFullscreenTheme:I

    .line 133
    :goto_0
    const-class v0, Lcom/google/android/material/datepicker/MaterialDatePicker;

    .line 134
    invoke-virtual {v0}, Ljava/lang/Class;->getCanonicalName()Ljava/lang/String;

    move-result-object v0

    .line 133
    invoke-static {p1, p0, v0}, Lcom/google/android/material/resources/MaterialAttributes;->resolveOrThrow(Landroid/content/Context;ILjava/lang/String;)I

    move-result p0

    return p0
.end method

.method public getSelectedDays()Ljava/util/Collection;
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/Collection<",
            "Ljava/lang/Long;",
            ">;"
        }
    .end annotation

    .line 112
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    .line 113
    iget-object v1, p0, Lcom/google/android/material/datepicker/RangeDateSelector;->selectedStartItem:Ljava/lang/Long;

    if-eqz v1, :cond_0

    .line 114
    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 116
    :cond_0
    iget-object p0, p0, Lcom/google/android/material/datepicker/RangeDateSelector;->selectedEndItem:Ljava/lang/Long;

    if-eqz p0, :cond_1

    .line 117
    invoke-virtual {v0, p0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    :cond_1
    return-object v0
.end method

.method public getSelectedRanges()Ljava/util/Collection;
    .locals 3
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/Collection<",
            "Landroidx/core/util/Pair<",
            "Ljava/lang/Long;",
            "Ljava/lang/Long;",
            ">;>;"
        }
    .end annotation

    .line 100
    iget-object v0, p0, Lcom/google/android/material/datepicker/RangeDateSelector;->selectedStartItem:Ljava/lang/Long;

    if-eqz v0, :cond_1

    iget-object v0, p0, Lcom/google/android/material/datepicker/RangeDateSelector;->selectedEndItem:Ljava/lang/Long;

    if-nez v0, :cond_0

    goto :goto_0

    .line 103
    :cond_0
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    .line 104
    new-instance v1, Landroidx/core/util/Pair;

    iget-object v2, p0, Lcom/google/android/material/datepicker/RangeDateSelector;->selectedStartItem:Ljava/lang/Long;

    iget-object p0, p0, Lcom/google/android/material/datepicker/RangeDateSelector;->selectedEndItem:Ljava/lang/Long;

    invoke-direct {v1, v2, p0}, Landroidx/core/util/Pair;-><init>(Ljava/lang/Object;Ljava/lang/Object;)V

    .line 105
    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    return-object v0

    .line 101
    :cond_1
    :goto_0
    new-instance p0, Ljava/util/ArrayList;

    invoke-direct {p0}, Ljava/util/ArrayList;-><init>()V

    return-object p0
.end method

.method public getSelection()Landroidx/core/util/Pair;
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Landroidx/core/util/Pair<",
            "Ljava/lang/Long;",
            "Ljava/lang/Long;",
            ">;"
        }
    .end annotation

    .line 94
    new-instance v0, Landroidx/core/util/Pair;

    iget-object v1, p0, Lcom/google/android/material/datepicker/RangeDateSelector;->selectedStartItem:Ljava/lang/Long;

    iget-object p0, p0, Lcom/google/android/material/datepicker/RangeDateSelector;->selectedEndItem:Ljava/lang/Long;

    invoke-direct {v0, v1, p0}, Landroidx/core/util/Pair;-><init>(Ljava/lang/Object;Ljava/lang/Object;)V

    return-object v0
.end method

.method public bridge synthetic getSelection()Ljava/lang/Object;
    .locals 0

    .line 50
    invoke-virtual {p0}, Lcom/google/android/material/datepicker/RangeDateSelector;->getSelection()Landroidx/core/util/Pair;

    move-result-object p0

    return-object p0
.end method

.method public getSelectionDisplayString(Landroid/content/Context;)Ljava/lang/String;
    .locals 5

    .line 140
    invoke-virtual {p1}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object p1

    .line 141
    iget-object v0, p0, Lcom/google/android/material/datepicker/RangeDateSelector;->selectedStartItem:Ljava/lang/Long;

    if-nez v0, :cond_0

    iget-object v1, p0, Lcom/google/android/material/datepicker/RangeDateSelector;->selectedEndItem:Ljava/lang/Long;

    if-nez v1, :cond_0

    .line 142
    sget p0, Lcom/google/android/material/R$string;->mtrl_picker_range_header_unselected:I

    invoke-virtual {p1, p0}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object p0

    return-object p0

    .line 144
    :cond_0
    iget-object p0, p0, Lcom/google/android/material/datepicker/RangeDateSelector;->selectedEndItem:Ljava/lang/Long;

    const/4 v1, 0x0

    const/4 v2, 0x1

    if-nez p0, :cond_1

    .line 145
    sget p0, Lcom/google/android/material/R$string;->mtrl_picker_range_header_only_start_selected:I

    new-array v2, v2, [Ljava/lang/Object;

    .line 147
    invoke-virtual {v0}, Ljava/lang/Long;->longValue()J

    move-result-wide v3

    invoke-static {v3, v4}, Lcom/google/android/material/datepicker/DateStrings;->getDateString(J)Ljava/lang/String;

    move-result-object v0

    aput-object v0, v2, v1

    .line 145
    invoke-virtual {p1, p0, v2}, Landroid/content/res/Resources;->getString(I[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    return-object p0

    :cond_1
    if-nez v0, :cond_2

    .line 150
    sget v0, Lcom/google/android/material/R$string;->mtrl_picker_range_header_only_end_selected:I

    new-array v2, v2, [Ljava/lang/Object;

    .line 152
    invoke-virtual {p0}, Ljava/lang/Long;->longValue()J

    move-result-wide v3

    invoke-static {v3, v4}, Lcom/google/android/material/datepicker/DateStrings;->getDateString(J)Ljava/lang/String;

    move-result-object p0

    aput-object p0, v2, v1

    .line 150
    invoke-virtual {p1, v0, v2}, Landroid/content/res/Resources;->getString(I[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    return-object p0

    .line 155
    :cond_2
    invoke-static {v0, p0}, Lcom/google/android/material/datepicker/DateStrings;->getDateRangeString(Ljava/lang/Long;Ljava/lang/Long;)Landroidx/core/util/Pair;

    move-result-object p0

    .line 156
    sget v0, Lcom/google/android/material/R$string;->mtrl_picker_range_header_selected:I

    const/4 v3, 0x2

    new-array v3, v3, [Ljava/lang/Object;

    iget-object v4, p0, Landroidx/core/util/Pair;->first:Ljava/lang/Object;

    aput-object v4, v3, v1

    iget-object p0, p0, Landroidx/core/util/Pair;->second:Ljava/lang/Object;

    aput-object p0, v3, v2

    invoke-virtual {p1, v0, v3}, Landroid/content/res/Resources;->getString(I[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public isSelectionComplete()Z
    .locals 4

    .line 75
    iget-object v0, p0, Lcom/google/android/material/datepicker/RangeDateSelector;->selectedStartItem:Ljava/lang/Long;

    if-eqz v0, :cond_0

    iget-object v1, p0, Lcom/google/android/material/datepicker/RangeDateSelector;->selectedEndItem:Ljava/lang/Long;

    if-eqz v1, :cond_0

    .line 77
    invoke-virtual {v0}, Ljava/lang/Long;->longValue()J

    move-result-wide v0

    iget-object v2, p0, Lcom/google/android/material/datepicker/RangeDateSelector;->selectedEndItem:Ljava/lang/Long;

    invoke-virtual {v2}, Ljava/lang/Long;->longValue()J

    move-result-wide v2

    invoke-direct {p0, v0, v1, v2, v3}, Lcom/google/android/material/datepicker/RangeDateSelector;->isValidRange(JJ)Z

    move-result p0

    if-eqz p0, :cond_0

    const/4 p0, 0x1

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    :goto_0
    return p0
.end method

.method public onCreateTextInputView(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Landroid/os/Bundle;Lcom/google/android/material/datepicker/CalendarConstraints;Lcom/google/android/material/datepicker/OnSelectionChangedListener;)Landroid/view/View;
    .locals 18
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Landroid/view/LayoutInflater;",
            "Landroid/view/ViewGroup;",
            "Landroid/os/Bundle;",
            "Lcom/google/android/material/datepicker/CalendarConstraints;",
            "Lcom/google/android/material/datepicker/OnSelectionChangedListener<",
            "Landroidx/core/util/Pair<",
            "Ljava/lang/Long;",
            "Ljava/lang/Long;",
            ">;>;)",
            "Landroid/view/View;"
        }
    .end annotation

    move-object/from16 v9, p0

    .line 174
    sget v0, Lcom/google/android/material/R$layout;->mtrl_picker_text_input_date_range:I

    const/4 v10, 0x0

    move-object/from16 v1, p1

    move-object/from16 v2, p2

    .line 175
    invoke-virtual {v1, v0, v2, v10}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;Z)Landroid/view/View;

    move-result-object v11

    .line 177
    sget v0, Lcom/google/android/material/R$id;->mtrl_picker_text_input_range_start:I

    .line 178
    invoke-virtual {v11, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    move-object v12, v0

    check-cast v12, Lcom/google/android/material/textfield/TextInputLayout;

    .line 179
    sget v0, Lcom/google/android/material/R$id;->mtrl_picker_text_input_range_end:I

    invoke-virtual {v11, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    move-object v13, v0

    check-cast v13, Lcom/google/android/material/textfield/TextInputLayout;

    .line 180
    invoke-virtual {v12}, Lcom/google/android/material/textfield/TextInputLayout;->getEditText()Landroid/widget/EditText;

    move-result-object v14

    .line 181
    invoke-virtual {v13}, Lcom/google/android/material/textfield/TextInputLayout;->getEditText()Landroid/widget/EditText;

    move-result-object v15

    .line 182
    invoke-static {}, Lcom/google/android/material/internal/ManufacturerUtils;->isDateInputKeyboardMissingSeparatorCharacters()Z

    move-result v0

    if-eqz v0, :cond_0

    const/16 v0, 0x11

    .line 184
    invoke-virtual {v14, v0}, Landroid/widget/EditText;->setInputType(I)V

    .line 185
    invoke-virtual {v15, v0}, Landroid/widget/EditText;->setInputType(I)V

    .line 188
    :cond_0
    invoke-virtual {v11}, Landroid/view/View;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    sget v1, Lcom/google/android/material/R$string;->mtrl_picker_invalid_range:I

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object v0

    iput-object v0, v9, Lcom/google/android/material/datepicker/RangeDateSelector;->invalidRangeStartError:Ljava/lang/String;

    .line 190
    invoke-static {}, Lcom/google/android/material/datepicker/UtcDates;->getTextInputFormat()Ljava/text/SimpleDateFormat;

    move-result-object v8

    .line 192
    iget-object v0, v9, Lcom/google/android/material/datepicker/RangeDateSelector;->selectedStartItem:Ljava/lang/Long;

    if-eqz v0, :cond_1

    .line 193
    invoke-virtual {v8, v0}, Ljava/text/SimpleDateFormat;->format(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v14, v0}, Landroid/widget/EditText;->setText(Ljava/lang/CharSequence;)V

    .line 194
    iget-object v0, v9, Lcom/google/android/material/datepicker/RangeDateSelector;->selectedStartItem:Ljava/lang/Long;

    iput-object v0, v9, Lcom/google/android/material/datepicker/RangeDateSelector;->proposedTextStart:Ljava/lang/Long;

    .line 196
    :cond_1
    iget-object v0, v9, Lcom/google/android/material/datepicker/RangeDateSelector;->selectedEndItem:Ljava/lang/Long;

    if-eqz v0, :cond_2

    .line 197
    invoke-virtual {v8, v0}, Ljava/text/SimpleDateFormat;->format(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v15, v0}, Landroid/widget/EditText;->setText(Ljava/lang/CharSequence;)V

    .line 198
    iget-object v0, v9, Lcom/google/android/material/datepicker/RangeDateSelector;->selectedEndItem:Ljava/lang/Long;

    iput-object v0, v9, Lcom/google/android/material/datepicker/RangeDateSelector;->proposedTextEnd:Ljava/lang/Long;

    .line 201
    :cond_2
    invoke-virtual {v11}, Landroid/view/View;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    invoke-static {v0, v8}, Lcom/google/android/material/datepicker/UtcDates;->getTextInputHint(Landroid/content/res/Resources;Ljava/text/SimpleDateFormat;)Ljava/lang/String;

    move-result-object v7

    .line 202
    invoke-virtual {v12, v7}, Lcom/google/android/material/textfield/TextInputLayout;->setPlaceholderText(Ljava/lang/CharSequence;)V

    .line 203
    invoke-virtual {v13, v7}, Lcom/google/android/material/textfield/TextInputLayout;->setPlaceholderText(Ljava/lang/CharSequence;)V

    .line 205
    new-instance v6, Lcom/google/android/material/datepicker/RangeDateSelector$1;

    move-object v0, v6

    move-object/from16 v1, p0

    move-object v2, v7

    move-object v3, v8

    move-object v4, v12

    move-object/from16 v5, p4

    move-object v10, v6

    move-object v6, v12

    move-object/from16 v16, v7

    move-object v7, v13

    move-object/from16 v17, v8

    move-object/from16 v8, p5

    invoke-direct/range {v0 .. v8}, Lcom/google/android/material/datepicker/RangeDateSelector$1;-><init>(Lcom/google/android/material/datepicker/RangeDateSelector;Ljava/lang/String;Ljava/text/DateFormat;Lcom/google/android/material/textfield/TextInputLayout;Lcom/google/android/material/datepicker/CalendarConstraints;Lcom/google/android/material/textfield/TextInputLayout;Lcom/google/android/material/textfield/TextInputLayout;Lcom/google/android/material/datepicker/OnSelectionChangedListener;)V

    invoke-virtual {v14, v10}, Landroid/widget/EditText;->addTextChangedListener(Landroid/text/TextWatcher;)V

    .line 221
    new-instance v10, Lcom/google/android/material/datepicker/RangeDateSelector$2;

    move-object v0, v10

    move-object/from16 v2, v16

    move-object/from16 v3, v17

    move-object v4, v13

    invoke-direct/range {v0 .. v8}, Lcom/google/android/material/datepicker/RangeDateSelector$2;-><init>(Lcom/google/android/material/datepicker/RangeDateSelector;Ljava/lang/String;Ljava/text/DateFormat;Lcom/google/android/material/textfield/TextInputLayout;Lcom/google/android/material/datepicker/CalendarConstraints;Lcom/google/android/material/textfield/TextInputLayout;Lcom/google/android/material/textfield/TextInputLayout;Lcom/google/android/material/datepicker/OnSelectionChangedListener;)V

    invoke-virtual {v15, v10}, Landroid/widget/EditText;->addTextChangedListener(Landroid/text/TextWatcher;)V

    const/4 v0, 0x2

    new-array v0, v0, [Landroid/widget/EditText;

    const/4 v1, 0x0

    aput-object v14, v0, v1

    const/4 v1, 0x1

    aput-object v15, v0, v1

    .line 234
    invoke-static {v0}, Lcom/google/android/material/datepicker/DateSelector;->showKeyboardWithAutoHideBehavior([Landroid/widget/EditText;)V

    return-object v11
.end method

.method public select(J)V
    .locals 2

    .line 63
    iget-object v0, p0, Lcom/google/android/material/datepicker/RangeDateSelector;->selectedStartItem:Ljava/lang/Long;

    if-nez v0, :cond_0

    .line 64
    invoke-static {p1, p2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p1

    iput-object p1, p0, Lcom/google/android/material/datepicker/RangeDateSelector;->selectedStartItem:Ljava/lang/Long;

    goto :goto_0

    .line 65
    :cond_0
    iget-object v1, p0, Lcom/google/android/material/datepicker/RangeDateSelector;->selectedEndItem:Ljava/lang/Long;

    if-nez v1, :cond_1

    invoke-virtual {v0}, Ljava/lang/Long;->longValue()J

    move-result-wide v0

    invoke-direct {p0, v0, v1, p1, p2}, Lcom/google/android/material/datepicker/RangeDateSelector;->isValidRange(JJ)Z

    move-result v0

    if-eqz v0, :cond_1

    .line 66
    invoke-static {p1, p2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p1

    iput-object p1, p0, Lcom/google/android/material/datepicker/RangeDateSelector;->selectedEndItem:Ljava/lang/Long;

    goto :goto_0

    :cond_1
    const/4 v0, 0x0

    .line 68
    iput-object v0, p0, Lcom/google/android/material/datepicker/RangeDateSelector;->selectedEndItem:Ljava/lang/Long;

    .line 69
    invoke-static {p1, p2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p1

    iput-object p1, p0, Lcom/google/android/material/datepicker/RangeDateSelector;->selectedStartItem:Ljava/lang/Long;

    :goto_0
    return-void
.end method

.method public writeToParcel(Landroid/os/Parcel;I)V
    .locals 0

    .line 305
    iget-object p2, p0, Lcom/google/android/material/datepicker/RangeDateSelector;->selectedStartItem:Ljava/lang/Long;

    invoke-virtual {p1, p2}, Landroid/os/Parcel;->writeValue(Ljava/lang/Object;)V

    .line 306
    iget-object p0, p0, Lcom/google/android/material/datepicker/RangeDateSelector;->selectedEndItem:Ljava/lang/Long;

    invoke-virtual {p1, p0}, Landroid/os/Parcel;->writeValue(Ljava/lang/Object;)V

    return-void
.end method
