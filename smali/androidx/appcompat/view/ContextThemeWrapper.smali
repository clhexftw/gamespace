.class public Landroidx/appcompat/view/ContextThemeWrapper;
.super Landroid/content/ContextWrapper;
.source "ContextThemeWrapper.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Landroidx/appcompat/view/ContextThemeWrapper$Api17Impl;
    }
.end annotation


# instance fields
.field private mInflater:Landroid/view/LayoutInflater;

.field private mOverrideConfiguration:Landroid/content/res/Configuration;

.field private mResources:Landroid/content/res/Resources;

.field private mTheme:Landroid/content/res/Resources$Theme;

.field private mThemeResource:I


# direct methods
.method public constructor <init>()V
    .locals 1

    const/4 v0, 0x0

    .line 50
    invoke-direct {p0, v0}, Landroid/content/ContextWrapper;-><init>(Landroid/content/Context;)V

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;I)V
    .locals 0

    .line 65
    invoke-direct {p0, p1}, Landroid/content/ContextWrapper;-><init>(Landroid/content/Context;)V

    .line 66
    iput p2, p0, Landroidx/appcompat/view/ContextThemeWrapper;->mThemeResource:I

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/content/res/Resources$Theme;)V
    .locals 0

    .line 79
    invoke-direct {p0, p1}, Landroid/content/ContextWrapper;-><init>(Landroid/content/Context;)V

    .line 80
    iput-object p2, p0, Landroidx/appcompat/view/ContextThemeWrapper;->mTheme:Landroid/content/res/Resources$Theme;

    return-void
.end method

.method private getResourcesInternal()Landroid/content/res/Resources;
    .locals 1

    .line 115
    iget-object v0, p0, Landroidx/appcompat/view/ContextThemeWrapper;->mResources:Landroid/content/res/Resources;

    if-nez v0, :cond_1

    .line 116
    iget-object v0, p0, Landroidx/appcompat/view/ContextThemeWrapper;->mOverrideConfiguration:Landroid/content/res/Configuration;

    if-nez v0, :cond_0

    .line 117
    invoke-super {p0}, Landroid/content/ContextWrapper;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    iput-object v0, p0, Landroidx/appcompat/view/ContextThemeWrapper;->mResources:Landroid/content/res/Resources;

    goto :goto_0

    .line 120
    :cond_0
    invoke-static {p0, v0}, Landroidx/appcompat/view/ContextThemeWrapper$Api17Impl;->createConfigurationContext(Landroidx/appcompat/view/ContextThemeWrapper;Landroid/content/res/Configuration;)Landroid/content/Context;

    move-result-object v0

    .line 121
    invoke-virtual {v0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    iput-object v0, p0, Landroidx/appcompat/view/ContextThemeWrapper;->mResources:Landroid/content/res/Resources;

    .line 129
    :cond_1
    :goto_0
    iget-object p0, p0, Landroidx/appcompat/view/ContextThemeWrapper;->mResources:Landroid/content/res/Resources;

    return-object p0
.end method

.method private initializeTheme()V
    .locals 3

    .line 189
    iget-object v0, p0, Landroidx/appcompat/view/ContextThemeWrapper;->mTheme:Landroid/content/res/Resources$Theme;

    if-nez v0, :cond_0

    const/4 v0, 0x1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    if-eqz v0, :cond_1

    .line 191
    invoke-virtual {p0}, Landroidx/appcompat/view/ContextThemeWrapper;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    invoke-virtual {v1}, Landroid/content/res/Resources;->newTheme()Landroid/content/res/Resources$Theme;

    move-result-object v1

    iput-object v1, p0, Landroidx/appcompat/view/ContextThemeWrapper;->mTheme:Landroid/content/res/Resources$Theme;

    .line 192
    invoke-virtual {p0}, Landroid/content/ContextWrapper;->getBaseContext()Landroid/content/Context;

    move-result-object v1

    invoke-virtual {v1}, Landroid/content/Context;->getTheme()Landroid/content/res/Resources$Theme;

    move-result-object v1

    if-eqz v1, :cond_1

    .line 194
    iget-object v2, p0, Landroidx/appcompat/view/ContextThemeWrapper;->mTheme:Landroid/content/res/Resources$Theme;

    invoke-virtual {v2, v1}, Landroid/content/res/Resources$Theme;->setTo(Landroid/content/res/Resources$Theme;)V

    .line 197
    :cond_1
    iget-object v1, p0, Landroidx/appcompat/view/ContextThemeWrapper;->mTheme:Landroid/content/res/Resources$Theme;

    iget v2, p0, Landroidx/appcompat/view/ContextThemeWrapper;->mThemeResource:I

    invoke-virtual {p0, v1, v2, v0}, Landroidx/appcompat/view/ContextThemeWrapper;->onApplyThemeResource(Landroid/content/res/Resources$Theme;IZ)V

    return-void
.end method


# virtual methods
.method public applyOverrideConfiguration(Landroid/content/res/Configuration;)V
    .locals 1

    .line 99
    iget-object v0, p0, Landroidx/appcompat/view/ContextThemeWrapper;->mResources:Landroid/content/res/Resources;

    if-nez v0, :cond_1

    .line 103
    iget-object v0, p0, Landroidx/appcompat/view/ContextThemeWrapper;->mOverrideConfiguration:Landroid/content/res/Configuration;

    if-nez v0, :cond_0

    .line 106
    new-instance v0, Landroid/content/res/Configuration;

    invoke-direct {v0, p1}, Landroid/content/res/Configuration;-><init>(Landroid/content/res/Configuration;)V

    iput-object v0, p0, Landroidx/appcompat/view/ContextThemeWrapper;->mOverrideConfiguration:Landroid/content/res/Configuration;

    return-void

    .line 104
    :cond_0
    new-instance p0, Ljava/lang/IllegalStateException;

    const-string p1, "Override configuration has already been set"

    invoke-direct {p0, p1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw p0

    .line 100
    :cond_1
    new-instance p0, Ljava/lang/IllegalStateException;

    const-string p1, "getResources() or getAssets() has already been called"

    invoke-direct {p0, p1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw p0
.end method

.method protected attachBaseContext(Landroid/content/Context;)V
    .locals 0

    .line 85
    invoke-super {p0, p1}, Landroid/content/ContextWrapper;->attachBaseContext(Landroid/content/Context;)V

    return-void
.end method

.method public getAssets()Landroid/content/res/AssetManager;
    .locals 0

    .line 203
    invoke-virtual {p0}, Landroidx/appcompat/view/ContextThemeWrapper;->getResources()Landroid/content/res/Resources;

    move-result-object p0

    invoke-virtual {p0}, Landroid/content/res/Resources;->getAssets()Landroid/content/res/AssetManager;

    move-result-object p0

    return-object p0
.end method

.method public getResources()Landroid/content/res/Resources;
    .locals 0

    .line 111
    invoke-direct {p0}, Landroidx/appcompat/view/ContextThemeWrapper;->getResourcesInternal()Landroid/content/res/Resources;

    move-result-object p0

    return-object p0
.end method

.method public getSystemService(Ljava/lang/String;)Ljava/lang/Object;
    .locals 1

    const-string v0, "layout_inflater"

    .line 164
    invoke-virtual {v0, p1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_1

    .line 165
    iget-object p1, p0, Landroidx/appcompat/view/ContextThemeWrapper;->mInflater:Landroid/view/LayoutInflater;

    if-nez p1, :cond_0

    .line 166
    invoke-virtual {p0}, Landroid/content/ContextWrapper;->getBaseContext()Landroid/content/Context;

    move-result-object p1

    invoke-static {p1}, Landroid/view/LayoutInflater;->from(Landroid/content/Context;)Landroid/view/LayoutInflater;

    move-result-object p1

    invoke-virtual {p1, p0}, Landroid/view/LayoutInflater;->cloneInContext(Landroid/content/Context;)Landroid/view/LayoutInflater;

    move-result-object p1

    iput-object p1, p0, Landroidx/appcompat/view/ContextThemeWrapper;->mInflater:Landroid/view/LayoutInflater;

    .line 168
    :cond_0
    iget-object p0, p0, Landroidx/appcompat/view/ContextThemeWrapper;->mInflater:Landroid/view/LayoutInflater;

    return-object p0

    .line 170
    :cond_1
    invoke-virtual {p0}, Landroid/content/ContextWrapper;->getBaseContext()Landroid/content/Context;

    move-result-object p0

    invoke-virtual {p0, p1}, Landroid/content/Context;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public getTheme()Landroid/content/res/Resources$Theme;
    .locals 1

    .line 150
    iget-object v0, p0, Landroidx/appcompat/view/ContextThemeWrapper;->mTheme:Landroid/content/res/Resources$Theme;

    if-eqz v0, :cond_0

    return-object v0

    .line 154
    :cond_0
    iget v0, p0, Landroidx/appcompat/view/ContextThemeWrapper;->mThemeResource:I

    if-nez v0, :cond_1

    .line 155
    sget v0, Landroidx/appcompat/R$style;->Theme_AppCompat_Light:I

    iput v0, p0, Landroidx/appcompat/view/ContextThemeWrapper;->mThemeResource:I

    .line 157
    :cond_1
    invoke-direct {p0}, Landroidx/appcompat/view/ContextThemeWrapper;->initializeTheme()V

    .line 159
    iget-object p0, p0, Landroidx/appcompat/view/ContextThemeWrapper;->mTheme:Landroid/content/res/Resources$Theme;

    return-object p0
.end method

.method public getThemeResId()I
    .locals 0

    .line 145
    iget p0, p0, Landroidx/appcompat/view/ContextThemeWrapper;->mThemeResource:I

    return p0
.end method

.method protected onApplyThemeResource(Landroid/content/res/Resources$Theme;IZ)V
    .locals 0

    const/4 p0, 0x1

    .line 185
    invoke-virtual {p1, p2, p0}, Landroid/content/res/Resources$Theme;->applyStyle(IZ)V

    return-void
.end method

.method public setTheme(I)V
    .locals 1

    .line 134
    iget v0, p0, Landroidx/appcompat/view/ContextThemeWrapper;->mThemeResource:I

    if-eq v0, p1, :cond_0

    .line 135
    iput p1, p0, Landroidx/appcompat/view/ContextThemeWrapper;->mThemeResource:I

    .line 136
    invoke-direct {p0}, Landroidx/appcompat/view/ContextThemeWrapper;->initializeTheme()V

    :cond_0
    return-void
.end method
