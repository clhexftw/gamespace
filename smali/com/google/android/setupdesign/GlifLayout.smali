.class public Lcom/google/android/setupdesign/GlifLayout;
.super Lcom/google/android/setupcompat/PartnerCustomizationLayout;
.source "GlifLayout.java"


# instance fields
.field private applyPartnerHeavyThemeResource:Z

.field private backgroundBaseColor:Landroid/content/res/ColorStateList;

.field private backgroundPatterned:Z

.field private primaryColor:Landroid/content/res/ColorStateList;


# direct methods
.method public constructor <init>(Landroid/content/Context;)V
    .locals 1

    const/4 v0, 0x0

    .line 86
    invoke-direct {p0, p1, v0, v0}, Lcom/google/android/setupdesign/GlifLayout;-><init>(Landroid/content/Context;II)V

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;I)V
    .locals 1

    const/4 v0, 0x0

    .line 90
    invoke-direct {p0, p1, p2, v0}, Lcom/google/android/setupdesign/GlifLayout;-><init>(Landroid/content/Context;II)V

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;II)V
    .locals 0

    .line 94
    invoke-direct {p0, p1, p2, p3}, Lcom/google/android/setupcompat/PartnerCustomizationLayout;-><init>(Landroid/content/Context;II)V

    const/4 p1, 0x1

    .line 78
    iput-boolean p1, p0, Lcom/google/android/setupdesign/GlifLayout;->backgroundPatterned:Z

    const/4 p1, 0x0

    .line 80
    iput-boolean p1, p0, Lcom/google/android/setupdesign/GlifLayout;->applyPartnerHeavyThemeResource:Z

    .line 95
    sget p1, Lcom/google/android/setupdesign/R$attr;->sudLayoutTheme:I

    const/4 p2, 0x0

    invoke-direct {p0, p2, p1}, Lcom/google/android/setupdesign/GlifLayout;->init(Landroid/util/AttributeSet;I)V

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 0

    .line 99
    invoke-direct {p0, p1, p2}, Lcom/google/android/setupcompat/PartnerCustomizationLayout;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    const/4 p1, 0x1

    .line 78
    iput-boolean p1, p0, Lcom/google/android/setupdesign/GlifLayout;->backgroundPatterned:Z

    const/4 p1, 0x0

    .line 80
    iput-boolean p1, p0, Lcom/google/android/setupdesign/GlifLayout;->applyPartnerHeavyThemeResource:Z

    .line 100
    sget p1, Lcom/google/android/setupdesign/R$attr;->sudLayoutTheme:I

    invoke-direct {p0, p2, p1}, Lcom/google/android/setupdesign/GlifLayout;->init(Landroid/util/AttributeSet;I)V

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V
    .locals 0
    .annotation build Landroid/annotation/TargetApi;
        value = 0xb
    .end annotation

    .line 105
    invoke-direct {p0, p1, p2, p3}, Lcom/google/android/setupcompat/PartnerCustomizationLayout;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    const/4 p1, 0x1

    .line 78
    iput-boolean p1, p0, Lcom/google/android/setupdesign/GlifLayout;->backgroundPatterned:Z

    const/4 p1, 0x0

    .line 80
    iput-boolean p1, p0, Lcom/google/android/setupdesign/GlifLayout;->applyPartnerHeavyThemeResource:Z

    .line 106
    invoke-direct {p0, p2, p3}, Lcom/google/android/setupdesign/GlifLayout;->init(Landroid/util/AttributeSet;I)V

    return-void
.end method

.method private init(Landroid/util/AttributeSet;I)V
    .locals 5

    .line 112
    invoke-virtual {p0}, Landroid/widget/FrameLayout;->isInEditMode()Z

    move-result v0

    if-eqz v0, :cond_0

    return-void

    .line 117
    :cond_0
    invoke-virtual {p0}, Landroid/widget/FrameLayout;->getContext()Landroid/content/Context;

    move-result-object v0

    sget-object v1, Lcom/google/android/setupdesign/R$styleable;->SudGlifLayout:[I

    const/4 v2, 0x0

    invoke-virtual {v0, p1, v1, p2, v2}, Landroid/content/Context;->obtainStyledAttributes(Landroid/util/AttributeSet;[III)Landroid/content/res/TypedArray;

    move-result-object v0

    .line 118
    sget v1, Lcom/google/android/setupdesign/R$styleable;->SudGlifLayout_sudUsePartnerHeavyTheme:I

    .line 119
    invoke-virtual {v0, v1, v2}, Landroid/content/res/TypedArray;->getBoolean(IZ)Z

    move-result v1

    .line 120
    invoke-virtual {p0}, Lcom/google/android/setupcompat/PartnerCustomizationLayout;->shouldApplyPartnerResource()Z

    move-result v3

    const/4 v4, 0x1

    if-eqz v3, :cond_1

    if-eqz v1, :cond_1

    move v1, v4

    goto :goto_0

    :cond_1
    move v1, v2

    :goto_0
    iput-boolean v1, p0, Lcom/google/android/setupdesign/GlifLayout;->applyPartnerHeavyThemeResource:Z

    .line 122
    const-class v1, Lcom/google/android/setupdesign/template/HeaderMixin;

    new-instance v3, Lcom/google/android/setupdesign/template/HeaderMixin;

    invoke-direct {v3, p0, p1, p2}, Lcom/google/android/setupdesign/template/HeaderMixin;-><init>(Lcom/google/android/setupcompat/internal/TemplateLayout;Landroid/util/AttributeSet;I)V

    invoke-virtual {p0, v1, v3}, Lcom/google/android/setupcompat/internal/TemplateLayout;->registerMixin(Ljava/lang/Class;Lcom/google/android/setupcompat/template/Mixin;)V

    .line 123
    const-class v1, Lcom/google/android/setupdesign/template/DescriptionMixin;

    new-instance v3, Lcom/google/android/setupdesign/template/DescriptionMixin;

    invoke-direct {v3, p0, p1, p2}, Lcom/google/android/setupdesign/template/DescriptionMixin;-><init>(Lcom/google/android/setupcompat/internal/TemplateLayout;Landroid/util/AttributeSet;I)V

    invoke-virtual {p0, v1, v3}, Lcom/google/android/setupcompat/internal/TemplateLayout;->registerMixin(Ljava/lang/Class;Lcom/google/android/setupcompat/template/Mixin;)V

    .line 124
    const-class v1, Lcom/google/android/setupdesign/template/IconMixin;

    new-instance v3, Lcom/google/android/setupdesign/template/IconMixin;

    invoke-direct {v3, p0, p1, p2}, Lcom/google/android/setupdesign/template/IconMixin;-><init>(Lcom/google/android/setupcompat/internal/TemplateLayout;Landroid/util/AttributeSet;I)V

    invoke-virtual {p0, v1, v3}, Lcom/google/android/setupcompat/internal/TemplateLayout;->registerMixin(Ljava/lang/Class;Lcom/google/android/setupcompat/template/Mixin;)V

    .line 125
    const-class v1, Lcom/google/android/setupdesign/template/ProfileMixin;

    new-instance v3, Lcom/google/android/setupdesign/template/ProfileMixin;

    invoke-direct {v3, p0, p1, p2}, Lcom/google/android/setupdesign/template/ProfileMixin;-><init>(Lcom/google/android/setupcompat/internal/TemplateLayout;Landroid/util/AttributeSet;I)V

    invoke-virtual {p0, v1, v3}, Lcom/google/android/setupcompat/internal/TemplateLayout;->registerMixin(Ljava/lang/Class;Lcom/google/android/setupcompat/template/Mixin;)V

    .line 126
    const-class v1, Lcom/google/android/setupdesign/template/ProgressBarMixin;

    new-instance v3, Lcom/google/android/setupdesign/template/ProgressBarMixin;

    invoke-direct {v3, p0, p1, p2}, Lcom/google/android/setupdesign/template/ProgressBarMixin;-><init>(Lcom/google/android/setupcompat/internal/TemplateLayout;Landroid/util/AttributeSet;I)V

    invoke-virtual {p0, v1, v3}, Lcom/google/android/setupcompat/internal/TemplateLayout;->registerMixin(Ljava/lang/Class;Lcom/google/android/setupcompat/template/Mixin;)V

    .line 127
    const-class p1, Lcom/google/android/setupdesign/template/IllustrationProgressMixin;

    new-instance p2, Lcom/google/android/setupdesign/template/IllustrationProgressMixin;

    invoke-direct {p2, p0}, Lcom/google/android/setupdesign/template/IllustrationProgressMixin;-><init>(Lcom/google/android/setupdesign/GlifLayout;)V

    invoke-virtual {p0, p1, p2}, Lcom/google/android/setupcompat/internal/TemplateLayout;->registerMixin(Ljava/lang/Class;Lcom/google/android/setupcompat/template/Mixin;)V

    .line 128
    new-instance p1, Lcom/google/android/setupdesign/template/RequireScrollMixin;

    invoke-direct {p1, p0}, Lcom/google/android/setupdesign/template/RequireScrollMixin;-><init>(Lcom/google/android/setupcompat/internal/TemplateLayout;)V

    .line 129
    const-class p2, Lcom/google/android/setupdesign/template/RequireScrollMixin;

    invoke-virtual {p0, p2, p1}, Lcom/google/android/setupcompat/internal/TemplateLayout;->registerMixin(Ljava/lang/Class;Lcom/google/android/setupcompat/template/Mixin;)V

    .line 131
    invoke-virtual {p0}, Lcom/google/android/setupdesign/GlifLayout;->getScrollView()Landroid/widget/ScrollView;

    move-result-object p2

    if-eqz p2, :cond_2

    .line 133
    new-instance v1, Lcom/google/android/setupdesign/template/ScrollViewScrollHandlingDelegate;

    invoke-direct {v1, p1, p2}, Lcom/google/android/setupdesign/template/ScrollViewScrollHandlingDelegate;-><init>(Lcom/google/android/setupdesign/template/RequireScrollMixin;Landroid/widget/ScrollView;)V

    invoke-virtual {p1, v1}, Lcom/google/android/setupdesign/template/RequireScrollMixin;->setScrollHandlingDelegate(Lcom/google/android/setupdesign/template/RequireScrollMixin$ScrollHandlingDelegate;)V

    .line 137
    :cond_2
    sget p1, Lcom/google/android/setupdesign/R$styleable;->SudGlifLayout_sudColorPrimary:I

    invoke-virtual {v0, p1}, Landroid/content/res/TypedArray;->getColorStateList(I)Landroid/content/res/ColorStateList;

    move-result-object p1

    if-eqz p1, :cond_3

    .line 139
    invoke-virtual {p0, p1}, Lcom/google/android/setupdesign/GlifLayout;->setPrimaryColor(Landroid/content/res/ColorStateList;)V

    .line 141
    :cond_3
    invoke-virtual {p0}, Lcom/google/android/setupdesign/GlifLayout;->shouldApplyPartnerHeavyThemeResource()Z

    move-result p1

    if-eqz p1, :cond_4

    .line 142
    invoke-direct {p0}, Lcom/google/android/setupdesign/GlifLayout;->updateContentBackgroundColorWithPartnerConfig()V

    .line 145
    :cond_4
    sget p1, Lcom/google/android/setupdesign/R$id;->sud_layout_content:I

    invoke-virtual {p0, p1}, Lcom/google/android/setupcompat/internal/TemplateLayout;->findManagedViewById(I)Landroid/view/View;

    move-result-object p1

    if-eqz p1, :cond_6

    .line 147
    invoke-virtual {p0}, Lcom/google/android/setupcompat/PartnerCustomizationLayout;->shouldApplyPartnerResource()Z

    move-result p2

    if-eqz p2, :cond_5

    .line 154
    invoke-static {p1}, Lcom/google/android/setupdesign/util/LayoutStyler;->applyPartnerCustomizationExtraPaddingStyle(Landroid/view/View;)V

    .line 160
    :cond_5
    instance-of p2, p0, Lcom/google/android/setupdesign/GlifPreferenceLayout;

    if-nez p2, :cond_6

    .line 161
    invoke-virtual {p0, p1}, Lcom/google/android/setupdesign/GlifLayout;->tryApplyPartnerCustomizationContentPaddingTopStyle(Landroid/view/View;)V

    .line 165
    :cond_6
    invoke-virtual {p0}, Lcom/google/android/setupdesign/GlifLayout;->updateLandscapeMiddleHorizontalSpacing()V

    .line 167
    sget p1, Lcom/google/android/setupdesign/R$styleable;->SudGlifLayout_sudBackgroundBaseColor:I

    .line 168
    invoke-virtual {v0, p1}, Landroid/content/res/TypedArray;->getColorStateList(I)Landroid/content/res/ColorStateList;

    move-result-object p1

    .line 169
    invoke-virtual {p0, p1}, Lcom/google/android/setupdesign/GlifLayout;->setBackgroundBaseColor(Landroid/content/res/ColorStateList;)V

    .line 171
    sget p1, Lcom/google/android/setupdesign/R$styleable;->SudGlifLayout_sudBackgroundPatterned:I

    .line 172
    invoke-virtual {v0, p1, v4}, Landroid/content/res/TypedArray;->getBoolean(IZ)Z

    move-result p1

    .line 173
    invoke-virtual {p0, p1}, Lcom/google/android/setupdesign/GlifLayout;->setBackgroundPatterned(Z)V

    .line 175
    sget p1, Lcom/google/android/setupdesign/R$styleable;->SudGlifLayout_sudStickyHeader:I

    invoke-virtual {v0, p1, v2}, Landroid/content/res/TypedArray;->getResourceId(II)I

    move-result p1

    if-eqz p1, :cond_7

    .line 177
    invoke-virtual {p0, p1}, Lcom/google/android/setupdesign/GlifLayout;->inflateStickyHeader(I)Landroid/view/View;

    .line 179
    :cond_7
    invoke-virtual {v0}, Landroid/content/res/TypedArray;->recycle()V

    return-void
.end method

.method private tryApplyPartnerCustomizationStyleToShortDescription()V
    .locals 2

    .line 196
    sget v0, Lcom/google/android/setupdesign/R$id;->sud_layout_description:I

    invoke-virtual {p0, v0}, Lcom/google/android/setupcompat/internal/TemplateLayout;->findManagedViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/TextView;

    if-eqz v0, :cond_1

    .line 198
    iget-boolean v1, p0, Lcom/google/android/setupdesign/GlifLayout;->applyPartnerHeavyThemeResource:Z

    if-eqz v1, :cond_0

    .line 199
    invoke-static {v0}, Lcom/google/android/setupdesign/util/DescriptionStyler;->applyPartnerCustomizationHeavyStyle(Landroid/widget/TextView;)V

    goto :goto_0

    .line 200
    :cond_0
    invoke-virtual {p0}, Lcom/google/android/setupcompat/PartnerCustomizationLayout;->shouldApplyPartnerResource()Z

    move-result p0

    if-eqz p0, :cond_1

    .line 201
    invoke-static {v0}, Lcom/google/android/setupdesign/util/DescriptionStyler;->applyPartnerCustomizationLightStyle(Landroid/widget/TextView;)V

    :cond_1
    :goto_0
    return-void
.end method

.method private updateBackground()V
    .locals 2

    .line 449
    sget v0, Lcom/google/android/setupdesign/R$id;->suc_layout_status:I

    invoke-virtual {p0, v0}, Lcom/google/android/setupcompat/internal/TemplateLayout;->findManagedViewById(I)Landroid/view/View;

    move-result-object v0

    if-eqz v0, :cond_3

    const/4 v0, 0x0

    .line 452
    iget-object v1, p0, Lcom/google/android/setupdesign/GlifLayout;->backgroundBaseColor:Landroid/content/res/ColorStateList;

    if-eqz v1, :cond_0

    .line 453
    invoke-virtual {v1}, Landroid/content/res/ColorStateList;->getDefaultColor()I

    move-result v0

    goto :goto_0

    .line 454
    :cond_0
    iget-object v1, p0, Lcom/google/android/setupdesign/GlifLayout;->primaryColor:Landroid/content/res/ColorStateList;

    if-eqz v1, :cond_1

    .line 455
    invoke-virtual {v1}, Landroid/content/res/ColorStateList;->getDefaultColor()I

    move-result v0

    .line 458
    :cond_1
    :goto_0
    iget-boolean v1, p0, Lcom/google/android/setupdesign/GlifLayout;->backgroundPatterned:Z

    if-eqz v1, :cond_2

    .line 459
    new-instance v1, Lcom/google/android/setupdesign/GlifPatternDrawable;

    invoke-direct {v1, v0}, Lcom/google/android/setupdesign/GlifPatternDrawable;-><init>(I)V

    goto :goto_1

    .line 460
    :cond_2
    new-instance v1, Landroid/graphics/drawable/ColorDrawable;

    invoke-direct {v1, v0}, Landroid/graphics/drawable/ColorDrawable;-><init>(I)V

    .line 461
    :goto_1
    const-class v0, Lcom/google/android/setupcompat/template/StatusBarMixin;

    invoke-virtual {p0, v0}, Lcom/google/android/setupcompat/internal/TemplateLayout;->getMixin(Ljava/lang/Class;)Lcom/google/android/setupcompat/template/Mixin;

    move-result-object p0

    check-cast p0, Lcom/google/android/setupcompat/template/StatusBarMixin;

    invoke-virtual {p0, v1}, Lcom/google/android/setupcompat/template/StatusBarMixin;->setStatusBarBackground(Landroid/graphics/drawable/Drawable;)V

    :cond_3
    return-void
.end method

.method private updateContentBackgroundColorWithPartnerConfig()V
    .locals 3

    .line 491
    invoke-virtual {p0}, Lcom/google/android/setupcompat/PartnerCustomizationLayout;->useFullDynamicColor()Z

    move-result v0

    if-eqz v0, :cond_0

    return-void

    .line 497
    :cond_0
    invoke-virtual {p0}, Landroid/widget/FrameLayout;->getContext()Landroid/content/Context;

    move-result-object v0

    invoke-static {v0}, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->get(Landroid/content/Context;)Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;

    move-result-object v0

    .line 498
    invoke-virtual {p0}, Landroid/widget/FrameLayout;->getContext()Landroid/content/Context;

    move-result-object v1

    sget-object v2, Lcom/google/android/setupcompat/partnerconfig/PartnerConfig;->CONFIG_LAYOUT_BACKGROUND_COLOR:Lcom/google/android/setupcompat/partnerconfig/PartnerConfig;

    invoke-virtual {v0, v1, v2}, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->getColor(Landroid/content/Context;Lcom/google/android/setupcompat/partnerconfig/PartnerConfig;)I

    move-result v0

    .line 499
    invoke-virtual {p0}, Landroid/widget/FrameLayout;->getRootView()Landroid/view/View;

    move-result-object p0

    invoke-virtual {p0, v0}, Landroid/view/View;->setBackgroundColor(I)V

    return-void
.end method


# virtual methods
.method protected findContainer(I)Landroid/view/ViewGroup;
    .locals 0

    if-nez p1, :cond_0

    .line 295
    sget p1, Lcom/google/android/setupdesign/R$id;->sud_layout_content:I

    .line 297
    :cond_0
    invoke-super {p0, p1}, Lcom/google/android/setupcompat/PartnerCustomizationLayout;->findContainer(I)Landroid/view/ViewGroup;

    move-result-object p0

    return-object p0
.end method

.method public getScrollView()Landroid/widget/ScrollView;
    .locals 1

    .line 315
    sget v0, Lcom/google/android/setupdesign/R$id;->sud_scroll_view:I

    invoke-virtual {p0, v0}, Lcom/google/android/setupcompat/internal/TemplateLayout;->findManagedViewById(I)Landroid/view/View;

    move-result-object p0

    .line 316
    instance-of v0, p0, Landroid/widget/ScrollView;

    if-eqz v0, :cond_0

    check-cast p0, Landroid/widget/ScrollView;

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    :goto_0
    return-object p0
.end method

.method public inflateStickyHeader(I)Landroid/view/View;
    .locals 1

    .line 309
    sget v0, Lcom/google/android/setupdesign/R$id;->sud_layout_sticky_header:I

    invoke-virtual {p0, v0}, Lcom/google/android/setupcompat/internal/TemplateLayout;->findManagedViewById(I)Landroid/view/View;

    move-result-object p0

    check-cast p0, Landroid/view/ViewStub;

    .line 310
    invoke-virtual {p0, p1}, Landroid/view/ViewStub;->setLayoutResource(I)V

    .line 311
    invoke-virtual {p0}, Landroid/view/ViewStub;->inflate()Landroid/view/View;

    move-result-object p0

    return-object p0
.end method

.method protected onFinishInflate()V
    .locals 1

    .line 184
    invoke-super {p0}, Landroid/widget/FrameLayout;->onFinishInflate()V

    .line 185
    const-class v0, Lcom/google/android/setupdesign/template/IconMixin;

    invoke-virtual {p0, v0}, Lcom/google/android/setupcompat/internal/TemplateLayout;->getMixin(Ljava/lang/Class;)Lcom/google/android/setupcompat/template/Mixin;

    move-result-object v0

    check-cast v0, Lcom/google/android/setupdesign/template/IconMixin;

    invoke-virtual {v0}, Lcom/google/android/setupdesign/template/IconMixin;->tryApplyPartnerCustomizationStyle()V

    .line 186
    const-class v0, Lcom/google/android/setupdesign/template/HeaderMixin;

    invoke-virtual {p0, v0}, Lcom/google/android/setupcompat/internal/TemplateLayout;->getMixin(Ljava/lang/Class;)Lcom/google/android/setupcompat/template/Mixin;

    move-result-object v0

    check-cast v0, Lcom/google/android/setupdesign/template/HeaderMixin;

    invoke-virtual {v0}, Lcom/google/android/setupdesign/template/HeaderMixin;->tryApplyPartnerCustomizationStyle()V

    .line 187
    const-class v0, Lcom/google/android/setupdesign/template/DescriptionMixin;

    invoke-virtual {p0, v0}, Lcom/google/android/setupcompat/internal/TemplateLayout;->getMixin(Ljava/lang/Class;)Lcom/google/android/setupcompat/template/Mixin;

    move-result-object v0

    check-cast v0, Lcom/google/android/setupdesign/template/DescriptionMixin;

    invoke-virtual {v0}, Lcom/google/android/setupdesign/template/DescriptionMixin;->tryApplyPartnerCustomizationStyle()V

    .line 188
    const-class v0, Lcom/google/android/setupdesign/template/ProgressBarMixin;

    invoke-virtual {p0, v0}, Lcom/google/android/setupcompat/internal/TemplateLayout;->getMixin(Ljava/lang/Class;)Lcom/google/android/setupcompat/template/Mixin;

    move-result-object v0

    check-cast v0, Lcom/google/android/setupdesign/template/ProgressBarMixin;

    invoke-virtual {v0}, Lcom/google/android/setupdesign/template/ProgressBarMixin;->tryApplyPartnerCustomizationStyle()V

    .line 189
    const-class v0, Lcom/google/android/setupdesign/template/ProfileMixin;

    invoke-virtual {p0, v0}, Lcom/google/android/setupcompat/internal/TemplateLayout;->getMixin(Ljava/lang/Class;)Lcom/google/android/setupcompat/template/Mixin;

    move-result-object v0

    check-cast v0, Lcom/google/android/setupdesign/template/ProfileMixin;

    invoke-virtual {v0}, Lcom/google/android/setupdesign/template/ProfileMixin;->tryApplyPartnerCustomizationStyle()V

    .line 190
    invoke-direct {p0}, Lcom/google/android/setupdesign/GlifLayout;->tryApplyPartnerCustomizationStyleToShortDescription()V

    return-void
.end method

.method protected onInflateTemplate(Landroid/view/LayoutInflater;I)Landroid/view/View;
    .locals 1

    if-nez p2, :cond_0

    .line 287
    sget p2, Lcom/google/android/setupdesign/R$layout;->sud_glif_template:I

    .line 289
    :cond_0
    sget v0, Lcom/google/android/setupdesign/R$style;->SudThemeGlif_Light:I

    invoke-virtual {p0, p1, v0, p2}, Lcom/google/android/setupcompat/internal/TemplateLayout;->inflateTemplate(Landroid/view/LayoutInflater;II)Landroid/view/View;

    move-result-object p0

    return-object p0
.end method

.method public setBackgroundBaseColor(Landroid/content/res/ColorStateList;)V
    .locals 0

    .line 421
    iput-object p1, p0, Lcom/google/android/setupdesign/GlifLayout;->backgroundBaseColor:Landroid/content/res/ColorStateList;

    .line 422
    invoke-direct {p0}, Lcom/google/android/setupdesign/GlifLayout;->updateBackground()V

    return-void
.end method

.method public setBackgroundPatterned(Z)V
    .locals 0

    .line 439
    iput-boolean p1, p0, Lcom/google/android/setupdesign/GlifLayout;->backgroundPatterned:Z

    .line 440
    invoke-direct {p0}, Lcom/google/android/setupdesign/GlifLayout;->updateBackground()V

    return-void
.end method

.method public setPrimaryColor(Landroid/content/res/ColorStateList;)V
    .locals 1

    .line 403
    iput-object p1, p0, Lcom/google/android/setupdesign/GlifLayout;->primaryColor:Landroid/content/res/ColorStateList;

    .line 404
    invoke-direct {p0}, Lcom/google/android/setupdesign/GlifLayout;->updateBackground()V

    .line 405
    const-class v0, Lcom/google/android/setupdesign/template/ProgressBarMixin;

    invoke-virtual {p0, v0}, Lcom/google/android/setupcompat/internal/TemplateLayout;->getMixin(Ljava/lang/Class;)Lcom/google/android/setupcompat/template/Mixin;

    move-result-object p0

    check-cast p0, Lcom/google/android/setupdesign/template/ProgressBarMixin;

    invoke-virtual {p0, p1}, Lcom/google/android/setupdesign/template/ProgressBarMixin;->setColor(Landroid/content/res/ColorStateList;)V

    return-void
.end method

.method public shouldApplyPartnerHeavyThemeResource()Z
    .locals 1

    .line 482
    iget-boolean v0, p0, Lcom/google/android/setupdesign/GlifLayout;->applyPartnerHeavyThemeResource:Z

    if-nez v0, :cond_1

    .line 483
    invoke-virtual {p0}, Lcom/google/android/setupcompat/PartnerCustomizationLayout;->shouldApplyPartnerResource()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 484
    invoke-virtual {p0}, Landroid/widget/FrameLayout;->getContext()Landroid/content/Context;

    move-result-object p0

    invoke-static {p0}, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->shouldApplyExtendedPartnerConfig(Landroid/content/Context;)Z

    move-result p0

    if-eqz p0, :cond_0

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    goto :goto_1

    :cond_1
    :goto_0
    const/4 p0, 0x1

    :goto_1
    return p0
.end method

.method protected tryApplyPartnerCustomizationContentPaddingTopStyle(Landroid/view/View;)V
    .locals 3
    .annotation build Landroid/annotation/TargetApi;
        value = 0x11
    .end annotation

    .line 504
    invoke-virtual {p1}, Landroid/view/View;->getContext()Landroid/content/Context;

    move-result-object v0

    .line 506
    invoke-static {v0}, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->get(Landroid/content/Context;)Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;

    move-result-object v1

    sget-object v2, Lcom/google/android/setupcompat/partnerconfig/PartnerConfig;->CONFIG_CONTENT_PADDING_TOP:Lcom/google/android/setupcompat/partnerconfig/PartnerConfig;

    .line 507
    invoke-virtual {v1, v2}, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->isPartnerConfigAvailable(Lcom/google/android/setupcompat/partnerconfig/PartnerConfig;)Z

    move-result v1

    .line 509
    invoke-virtual {p0}, Lcom/google/android/setupcompat/PartnerCustomizationLayout;->shouldApplyPartnerResource()Z

    move-result p0

    if-eqz p0, :cond_0

    if-eqz v1, :cond_0

    .line 512
    invoke-static {v0}, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->get(Landroid/content/Context;)Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;

    move-result-object p0

    .line 513
    invoke-virtual {p0, v0, v2}, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->getDimension(Landroid/content/Context;Lcom/google/android/setupcompat/partnerconfig/PartnerConfig;)F

    move-result p0

    float-to-int p0, p0

    .line 515
    invoke-virtual {p1}, Landroid/view/View;->getPaddingTop()I

    move-result v0

    if-eq p0, v0, :cond_0

    .line 517
    invoke-virtual {p1}, Landroid/view/View;->getPaddingStart()I

    move-result v0

    invoke-virtual {p1}, Landroid/view/View;->getPaddingEnd()I

    move-result v1

    invoke-virtual {p1}, Landroid/view/View;->getPaddingBottom()I

    move-result v2

    .line 516
    invoke-virtual {p1, v0, p0, v1, v2}, Landroid/view/View;->setPadding(IIII)V

    :cond_0
    return-void
.end method

.method protected updateLandscapeMiddleHorizontalSpacing()V
    .locals 8

    .line 208
    invoke-virtual {p0}, Landroid/widget/FrameLayout;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    sget v1, Lcom/google/android/setupdesign/R$dimen;->sud_glif_land_middle_horizontal_spacing:I

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v0

    .line 209
    invoke-virtual {p0}, Lcom/google/android/setupcompat/PartnerCustomizationLayout;->shouldApplyPartnerResource()Z

    move-result v1

    if-eqz v1, :cond_0

    .line 210
    invoke-virtual {p0}, Landroid/widget/FrameLayout;->getContext()Landroid/content/Context;

    move-result-object v1

    invoke-static {v1}, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->get(Landroid/content/Context;)Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;

    move-result-object v1

    sget-object v2, Lcom/google/android/setupcompat/partnerconfig/PartnerConfig;->CONFIG_LAND_MIDDLE_HORIZONTAL_SPACING:Lcom/google/android/setupcompat/partnerconfig/PartnerConfig;

    .line 211
    invoke-virtual {v1, v2}, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->isPartnerConfigAvailable(Lcom/google/android/setupcompat/partnerconfig/PartnerConfig;)Z

    move-result v1

    if-eqz v1, :cond_0

    .line 214
    invoke-virtual {p0}, Landroid/widget/FrameLayout;->getContext()Landroid/content/Context;

    move-result-object v0

    invoke-static {v0}, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->get(Landroid/content/Context;)Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;

    move-result-object v0

    .line 215
    invoke-virtual {p0}, Landroid/widget/FrameLayout;->getContext()Landroid/content/Context;

    move-result-object v1

    invoke-virtual {v0, v1, v2}, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->getDimension(Landroid/content/Context;Lcom/google/android/setupcompat/partnerconfig/PartnerConfig;)F

    move-result v0

    float-to-int v0, v0

    .line 218
    :cond_0
    sget v1, Lcom/google/android/setupdesign/R$id;->sud_landscape_header_area:I

    invoke-virtual {p0, v1}, Lcom/google/android/setupcompat/internal/TemplateLayout;->findManagedViewById(I)Landroid/view/View;

    move-result-object v1

    const/4 v2, 0x1

    const/4 v3, 0x0

    if-eqz v1, :cond_2

    .line 221
    invoke-virtual {p0}, Lcom/google/android/setupcompat/PartnerCustomizationLayout;->shouldApplyPartnerResource()Z

    move-result v4

    if-eqz v4, :cond_1

    .line 222
    invoke-virtual {p0}, Landroid/widget/FrameLayout;->getContext()Landroid/content/Context;

    move-result-object v4

    invoke-static {v4}, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->get(Landroid/content/Context;)Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;

    move-result-object v4

    sget-object v5, Lcom/google/android/setupcompat/partnerconfig/PartnerConfig;->CONFIG_LAYOUT_MARGIN_END:Lcom/google/android/setupcompat/partnerconfig/PartnerConfig;

    .line 223
    invoke-virtual {v4, v5}, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->isPartnerConfigAvailable(Lcom/google/android/setupcompat/partnerconfig/PartnerConfig;)Z

    move-result v4

    if-eqz v4, :cond_1

    .line 226
    invoke-virtual {p0}, Landroid/widget/FrameLayout;->getContext()Landroid/content/Context;

    move-result-object v4

    invoke-static {v4}, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->get(Landroid/content/Context;)Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;

    move-result-object v4

    .line 227
    invoke-virtual {p0}, Landroid/widget/FrameLayout;->getContext()Landroid/content/Context;

    move-result-object v6

    invoke-virtual {v4, v6, v5}, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->getDimension(Landroid/content/Context;Lcom/google/android/setupcompat/partnerconfig/PartnerConfig;)F

    move-result v4

    float-to-int v4, v4

    goto :goto_0

    .line 229
    :cond_1
    invoke-virtual {p0}, Landroid/widget/FrameLayout;->getContext()Landroid/content/Context;

    move-result-object v4

    new-array v5, v2, [I

    sget v6, Lcom/google/android/setupdesign/R$attr;->sudMarginEnd:I

    aput v6, v5, v3

    invoke-virtual {v4, v5}, Landroid/content/Context;->obtainStyledAttributes([I)Landroid/content/res/TypedArray;

    move-result-object v4

    .line 230
    invoke-virtual {v4, v3, v3}, Landroid/content/res/TypedArray;->getDimensionPixelSize(II)I

    move-result v5

    .line 231
    invoke-virtual {v4}, Landroid/content/res/TypedArray;->recycle()V

    move v4, v5

    .line 233
    :goto_0
    div-int/lit8 v5, v0, 0x2

    sub-int/2addr v5, v4

    .line 236
    invoke-virtual {v1}, Landroid/view/View;->getPaddingStart()I

    move-result v4

    .line 237
    invoke-virtual {v1}, Landroid/view/View;->getPaddingTop()I

    move-result v6

    .line 239
    invoke-virtual {v1}, Landroid/view/View;->getPaddingBottom()I

    move-result v7

    .line 235
    invoke-virtual {v1, v4, v6, v5, v7}, Landroid/view/View;->setPadding(IIII)V

    .line 249
    :cond_2
    sget v4, Lcom/google/android/setupdesign/R$id;->sud_landscape_content_area:I

    invoke-virtual {p0, v4}, Lcom/google/android/setupcompat/internal/TemplateLayout;->findManagedViewById(I)Landroid/view/View;

    move-result-object v4

    if-eqz v4, :cond_5

    .line 252
    invoke-virtual {p0}, Lcom/google/android/setupcompat/PartnerCustomizationLayout;->shouldApplyPartnerResource()Z

    move-result v5

    if-eqz v5, :cond_3

    .line 253
    invoke-virtual {p0}, Landroid/widget/FrameLayout;->getContext()Landroid/content/Context;

    move-result-object v5

    invoke-static {v5}, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->get(Landroid/content/Context;)Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;

    move-result-object v5

    sget-object v6, Lcom/google/android/setupcompat/partnerconfig/PartnerConfig;->CONFIG_LAYOUT_MARGIN_START:Lcom/google/android/setupcompat/partnerconfig/PartnerConfig;

    .line 254
    invoke-virtual {v5, v6}, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->isPartnerConfigAvailable(Lcom/google/android/setupcompat/partnerconfig/PartnerConfig;)Z

    move-result v5

    if-eqz v5, :cond_3

    .line 257
    invoke-virtual {p0}, Landroid/widget/FrameLayout;->getContext()Landroid/content/Context;

    move-result-object v2

    invoke-static {v2}, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->get(Landroid/content/Context;)Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;

    move-result-object v2

    .line 258
    invoke-virtual {p0}, Landroid/widget/FrameLayout;->getContext()Landroid/content/Context;

    move-result-object p0

    invoke-virtual {v2, p0, v6}, Lcom/google/android/setupcompat/partnerconfig/PartnerConfigHelper;->getDimension(Landroid/content/Context;Lcom/google/android/setupcompat/partnerconfig/PartnerConfig;)F

    move-result p0

    float-to-int p0, p0

    goto :goto_1

    .line 260
    :cond_3
    invoke-virtual {p0}, Landroid/widget/FrameLayout;->getContext()Landroid/content/Context;

    move-result-object p0

    new-array v2, v2, [I

    sget v5, Lcom/google/android/setupdesign/R$attr;->sudMarginStart:I

    aput v5, v2, v3

    invoke-virtual {p0, v2}, Landroid/content/Context;->obtainStyledAttributes([I)Landroid/content/res/TypedArray;

    move-result-object p0

    .line 261
    invoke-virtual {p0, v3, v3}, Landroid/content/res/TypedArray;->getDimensionPixelSize(II)I

    move-result v2

    .line 262
    invoke-virtual {p0}, Landroid/content/res/TypedArray;->recycle()V

    move p0, v2

    :goto_1
    if-eqz v1, :cond_4

    .line 266
    div-int/lit8 v0, v0, 0x2

    sub-int v3, v0, p0

    .line 271
    :cond_4
    invoke-virtual {v4}, Landroid/view/View;->getPaddingTop()I

    move-result p0

    .line 272
    invoke-virtual {v4}, Landroid/view/View;->getPaddingEnd()I

    move-result v0

    .line 273
    invoke-virtual {v4}, Landroid/view/View;->getPaddingBottom()I

    move-result v1

    .line 269
    invoke-virtual {v4, v3, p0, v0, v1}, Landroid/view/View;->setPadding(IIII)V

    :cond_5
    return-void
.end method
