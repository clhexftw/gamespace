.class public Lcom/google/android/setupdesign/GlifPreferenceLayout;
.super Lcom/google/android/setupdesign/GlifRecyclerLayout;
.source "GlifPreferenceLayout.java"


# virtual methods
.method protected findContainer(I)Landroid/view/ViewGroup;
    .locals 0

    if-nez p1, :cond_0

    .line 87
    sget p1, Lcom/google/android/setupdesign/R$id;->sud_layout_content:I

    .line 89
    :cond_0
    invoke-super {p0, p1}, Lcom/google/android/setupdesign/GlifRecyclerLayout;->findContainer(I)Landroid/view/ViewGroup;

    move-result-object p0

    return-object p0
.end method

.method protected onInflateTemplate(Landroid/view/LayoutInflater;I)Landroid/view/View;
    .locals 0

    if-nez p2, :cond_0

    .line 101
    sget p2, Lcom/google/android/setupdesign/R$layout;->sud_glif_preference_template:I

    .line 103
    :cond_0
    invoke-super {p0, p1, p2}, Lcom/google/android/setupdesign/GlifRecyclerLayout;->onInflateTemplate(Landroid/view/LayoutInflater;I)Landroid/view/View;

    move-result-object p0

    return-object p0
.end method

.method protected onTemplateInflated()V
    .locals 3

    .line 110
    invoke-virtual {p0}, Landroid/widget/FrameLayout;->getContext()Landroid/content/Context;

    move-result-object v0

    invoke-static {v0}, Landroid/view/LayoutInflater;->from(Landroid/content/Context;)Landroid/view/LayoutInflater;

    move-result-object v0

    .line 111
    sget v1, Lcom/google/android/setupdesign/R$layout;->sud_glif_preference_recycler_view:I

    const/4 v2, 0x0

    .line 112
    invoke-virtual {v0, v1, p0, v2}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;Z)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroidx/recyclerview/widget/RecyclerView;

    .line 113
    new-instance v1, Lcom/google/android/setupdesign/template/RecyclerMixin;

    invoke-direct {v1, p0, v0}, Lcom/google/android/setupdesign/template/RecyclerMixin;-><init>(Lcom/google/android/setupcompat/internal/TemplateLayout;Landroidx/recyclerview/widget/RecyclerView;)V

    iput-object v1, p0, Lcom/google/android/setupdesign/GlifRecyclerLayout;->recyclerMixin:Lcom/google/android/setupdesign/template/RecyclerMixin;

    return-void
.end method
