.class public final synthetic Landroidx/fragment/app/FragmentManager$$ExternalSyntheticLambda2;
.super Ljava/lang/Object;
.source "R8$$SyntheticClass"

# interfaces
.implements Landroidx/core/util/Consumer;


# instance fields
.field public final synthetic f$0:Landroidx/fragment/app/FragmentManager;


# direct methods
.method public synthetic constructor <init>(Landroidx/fragment/app/FragmentManager;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Landroidx/fragment/app/FragmentManager$$ExternalSyntheticLambda2;->f$0:Landroidx/fragment/app/FragmentManager;

    return-void
.end method


# virtual methods
.method public final accept(Ljava/lang/Object;)V
    .locals 0

    iget-object p0, p0, Landroidx/fragment/app/FragmentManager$$ExternalSyntheticLambda2;->f$0:Landroidx/fragment/app/FragmentManager;

    check-cast p1, Landroidx/core/app/MultiWindowModeChangedInfo;

    invoke-static {p0, p1}, Landroidx/fragment/app/FragmentManager;->$r8$lambda$VJusB-xtSf6gkO7njmGWkIPixRc(Landroidx/fragment/app/FragmentManager;Landroidx/core/app/MultiWindowModeChangedInfo;)V

    return-void
.end method
