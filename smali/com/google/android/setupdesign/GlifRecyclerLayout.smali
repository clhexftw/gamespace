.class public Lcom/google/android/setupdesign/GlifRecyclerLayout;
.super Lcom/google/android/setupdesign/GlifLayout;
.source "GlifRecyclerLayout.java"


# instance fields
.field protected recyclerMixin:Lcom/google/android/setupdesign/template/RecyclerMixin;


# virtual methods
.method protected findContainer(I)Landroid/view/ViewGroup;
    .locals 0

    if-nez p1, :cond_0

    .line 113
    sget p1, Lcom/google/android/setupdesign/R$id;->sud_recycler_view:I

    .line 115
    :cond_0
    invoke-super {p0, p1}, Lcom/google/android/setupdesign/GlifLayout;->findContainer(I)Landroid/view/ViewGroup;

    move-result-object p0

    return-object p0
.end method

.method public findManagedViewById(I)Landroid/view/View;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "<T:",
            "Landroid/view/View;",
            ">(I)TT;"
        }
    .end annotation

    .line 122
    iget-object v0, p0, Lcom/google/android/setupdesign/GlifRecyclerLayout;->recyclerMixin:Lcom/google/android/setupdesign/template/RecyclerMixin;

    invoke-virtual {v0}, Lcom/google/android/setupdesign/template/RecyclerMixin;->getHeader()Landroid/view/View;

    move-result-object v0

    if-eqz v0, :cond_0

    .line 124
    invoke-virtual {v0, p1}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    if-eqz v0, :cond_0

    return-object v0

    .line 129
    :cond_0
    invoke-super {p0, p1}, Landroid/widget/FrameLayout;->findViewById(I)Landroid/view/View;

    move-result-object p0

    return-object p0
.end method

.method protected onInflateTemplate(Landroid/view/LayoutInflater;I)Landroid/view/View;
    .locals 0

    if-nez p2, :cond_0

    .line 94
    sget p2, Lcom/google/android/setupdesign/R$layout;->sud_glif_recycler_template:I

    .line 96
    :cond_0
    invoke-super {p0, p1, p2}, Lcom/google/android/setupdesign/GlifLayout;->onInflateTemplate(Landroid/view/LayoutInflater;I)Landroid/view/View;

    move-result-object p0

    return-object p0
.end method

.method protected onLayout(ZIIII)V
    .locals 0

    .line 87
    invoke-super/range {p0 .. p5}, Landroid/widget/FrameLayout;->onLayout(ZIIII)V

    .line 88
    iget-object p0, p0, Lcom/google/android/setupdesign/GlifRecyclerLayout;->recyclerMixin:Lcom/google/android/setupdesign/template/RecyclerMixin;

    invoke-virtual {p0}, Lcom/google/android/setupdesign/template/RecyclerMixin;->onLayout()V

    return-void
.end method

.method protected onTemplateInflated()V
    .locals 2

    .line 101
    sget v0, Lcom/google/android/setupdesign/R$id;->sud_recycler_view:I

    invoke-virtual {p0, v0}, Landroid/widget/FrameLayout;->findViewById(I)Landroid/view/View;

    move-result-object v0

    .line 102
    instance-of v1, v0, Landroidx/recyclerview/widget/RecyclerView;

    if-eqz v1, :cond_0

    .line 103
    new-instance v1, Lcom/google/android/setupdesign/template/RecyclerMixin;

    check-cast v0, Landroidx/recyclerview/widget/RecyclerView;

    invoke-direct {v1, p0, v0}, Lcom/google/android/setupdesign/template/RecyclerMixin;-><init>(Lcom/google/android/setupcompat/internal/TemplateLayout;Landroidx/recyclerview/widget/RecyclerView;)V

    iput-object v1, p0, Lcom/google/android/setupdesign/GlifRecyclerLayout;->recyclerMixin:Lcom/google/android/setupdesign/template/RecyclerMixin;

    return-void

    .line 105
    :cond_0
    new-instance p0, Ljava/lang/IllegalStateException;

    const-string v0, "GlifRecyclerLayout should use a template with recycler view"

    invoke-direct {p0, v0}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw p0
.end method
