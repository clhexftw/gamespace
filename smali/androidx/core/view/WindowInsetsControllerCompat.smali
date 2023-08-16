.class public final Landroidx/core/view/WindowInsetsControllerCompat;
.super Ljava/lang/Object;
.source "WindowInsetsControllerCompat.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Landroidx/core/view/WindowInsetsControllerCompat$Impl30;,
        Landroidx/core/view/WindowInsetsControllerCompat$Impl;
    }
.end annotation


# instance fields
.field private final mImpl:Landroidx/core/view/WindowInsetsControllerCompat$Impl;


# direct methods
.method public constructor <init>(Landroid/view/Window;Landroid/view/View;)V
    .locals 0

    .line 100
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 102
    new-instance p2, Landroidx/core/view/WindowInsetsControllerCompat$Impl30;

    invoke-direct {p2, p1, p0}, Landroidx/core/view/WindowInsetsControllerCompat$Impl30;-><init>(Landroid/view/Window;Landroidx/core/view/WindowInsetsControllerCompat;)V

    iput-object p2, p0, Landroidx/core/view/WindowInsetsControllerCompat;->mImpl:Landroidx/core/view/WindowInsetsControllerCompat$Impl;

    return-void
.end method


# virtual methods
.method public setAppearanceLightNavigationBars(Z)V
    .locals 0

    .line 217
    iget-object p0, p0, Landroidx/core/view/WindowInsetsControllerCompat;->mImpl:Landroidx/core/view/WindowInsetsControllerCompat$Impl;

    invoke-virtual {p0, p1}, Landroidx/core/view/WindowInsetsControllerCompat$Impl;->setAppearanceLightNavigationBars(Z)V

    return-void
.end method

.method public setAppearanceLightStatusBars(Z)V
    .locals 0

    .line 193
    iget-object p0, p0, Landroidx/core/view/WindowInsetsControllerCompat;->mImpl:Landroidx/core/view/WindowInsetsControllerCompat$Impl;

    invoke-virtual {p0, p1}, Landroidx/core/view/WindowInsetsControllerCompat$Impl;->setAppearanceLightStatusBars(Z)V

    return-void
.end method
