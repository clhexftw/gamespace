.class public final Landroidx/lifecycle/SavedStateHandlesVM;
.super Landroidx/lifecycle/ViewModel;
.source "SavedStateHandleSupport.kt"


# instance fields
.field private final controllers:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "Landroidx/lifecycle/SavedStateHandleController;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method public constructor <init>()V
    .locals 1

    .line 122
    invoke-direct {p0}, Landroidx/lifecycle/ViewModel;-><init>()V

    .line 123
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Landroidx/lifecycle/SavedStateHandlesVM;->controllers:Ljava/util/List;

    return-void
.end method


# virtual methods
.method public final getControllers()Ljava/util/List;
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/List<",
            "Landroidx/lifecycle/SavedStateHandleController;",
            ">;"
        }
    .end annotation

    .line 123
    iget-object p0, p0, Landroidx/lifecycle/SavedStateHandlesVM;->controllers:Ljava/util/List;

    return-object p0
.end method
