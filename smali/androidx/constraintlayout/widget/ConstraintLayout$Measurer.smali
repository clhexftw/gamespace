.class Landroidx/constraintlayout/widget/ConstraintLayout$Measurer;
.super Ljava/lang/Object;
.source "ConstraintLayout.java"

# interfaces
.implements Landroidx/constraintlayout/core/widgets/analyzer/BasicMeasure$Measurer;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Landroidx/constraintlayout/widget/ConstraintLayout;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = "Measurer"
.end annotation


# instance fields
.field mLayout:Landroidx/constraintlayout/widget/ConstraintLayout;

.field mLayoutHeightSpec:I

.field mLayoutWidthSpec:I

.field mPaddingBottom:I

.field mPaddingHeight:I

.field mPaddingTop:I

.field mPaddingWidth:I

.field final synthetic this$0:Landroidx/constraintlayout/widget/ConstraintLayout;


# direct methods
.method constructor <init>(Landroidx/constraintlayout/widget/ConstraintLayout;Landroidx/constraintlayout/widget/ConstraintLayout;)V
    .locals 0

    .line 723
    iput-object p1, p0, Landroidx/constraintlayout/widget/ConstraintLayout$Measurer;->this$0:Landroidx/constraintlayout/widget/ConstraintLayout;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 724
    iput-object p2, p0, Landroidx/constraintlayout/widget/ConstraintLayout$Measurer;->mLayout:Landroidx/constraintlayout/widget/ConstraintLayout;

    return-void
.end method

.method private isSimilarSpec(III)Z
    .locals 2

    const/4 p0, 0x1

    if-ne p1, p2, :cond_0

    return p0

    .line 1033
    :cond_0
    invoke-static {p1}, Landroid/view/View$MeasureSpec;->getMode(I)I

    move-result p1

    .line 1034
    invoke-static {p2}, Landroid/view/View$MeasureSpec;->getMode(I)I

    move-result v0

    .line 1035
    invoke-static {p2}, Landroid/view/View$MeasureSpec;->getSize(I)I

    move-result p2

    const/high16 v1, 0x40000000    # 2.0f

    if-ne v0, v1, :cond_2

    const/high16 v0, -0x80000000

    if-eq p1, v0, :cond_1

    if-nez p1, :cond_2

    :cond_1
    if-ne p3, p2, :cond_2

    goto :goto_0

    :cond_2
    const/4 p0, 0x0

    :goto_0
    return p0
.end method


# virtual methods
.method public captureLayoutInfo(IIIIII)V
    .locals 0

    .line 715
    iput p3, p0, Landroidx/constraintlayout/widget/ConstraintLayout$Measurer;->mPaddingTop:I

    .line 716
    iput p4, p0, Landroidx/constraintlayout/widget/ConstraintLayout$Measurer;->mPaddingBottom:I

    .line 717
    iput p5, p0, Landroidx/constraintlayout/widget/ConstraintLayout$Measurer;->mPaddingWidth:I

    .line 718
    iput p6, p0, Landroidx/constraintlayout/widget/ConstraintLayout$Measurer;->mPaddingHeight:I

    .line 719
    iput p1, p0, Landroidx/constraintlayout/widget/ConstraintLayout$Measurer;->mLayoutWidthSpec:I

    .line 720
    iput p2, p0, Landroidx/constraintlayout/widget/ConstraintLayout$Measurer;->mLayoutHeightSpec:I

    return-void
.end method

.method public final didMeasures()V
    .locals 5

    .line 1043
    iget-object v0, p0, Landroidx/constraintlayout/widget/ConstraintLayout$Measurer;->mLayout:Landroidx/constraintlayout/widget/ConstraintLayout;

    invoke-virtual {v0}, Landroid/view/ViewGroup;->getChildCount()I

    move-result v0

    const/4 v1, 0x0

    move v2, v1

    :goto_0
    if-ge v2, v0, :cond_1

    .line 1045
    iget-object v3, p0, Landroidx/constraintlayout/widget/ConstraintLayout$Measurer;->mLayout:Landroidx/constraintlayout/widget/ConstraintLayout;

    invoke-virtual {v3, v2}, Landroid/view/ViewGroup;->getChildAt(I)Landroid/view/View;

    move-result-object v3

    .line 1046
    instance-of v4, v3, Landroidx/constraintlayout/widget/Placeholder;

    if-eqz v4, :cond_0

    .line 1047
    check-cast v3, Landroidx/constraintlayout/widget/Placeholder;

    iget-object v4, p0, Landroidx/constraintlayout/widget/ConstraintLayout$Measurer;->mLayout:Landroidx/constraintlayout/widget/ConstraintLayout;

    invoke-virtual {v3, v4}, Landroidx/constraintlayout/widget/Placeholder;->updatePostMeasure(Landroidx/constraintlayout/widget/ConstraintLayout;)V

    :cond_0
    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    .line 1051
    :cond_1
    iget-object v0, p0, Landroidx/constraintlayout/widget/ConstraintLayout$Measurer;->mLayout:Landroidx/constraintlayout/widget/ConstraintLayout;

    invoke-static {v0}, Landroidx/constraintlayout/widget/ConstraintLayout;->access$200(Landroidx/constraintlayout/widget/ConstraintLayout;)Ljava/util/ArrayList;

    move-result-object v0

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    if-lez v0, :cond_2

    :goto_1
    if-ge v1, v0, :cond_2

    .line 1054
    iget-object v2, p0, Landroidx/constraintlayout/widget/ConstraintLayout$Measurer;->mLayout:Landroidx/constraintlayout/widget/ConstraintLayout;

    invoke-static {v2}, Landroidx/constraintlayout/widget/ConstraintLayout;->access$200(Landroidx/constraintlayout/widget/ConstraintLayout;)Ljava/util/ArrayList;

    move-result-object v2

    invoke-virtual {v2, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Landroidx/constraintlayout/widget/ConstraintHelper;

    .line 1055
    iget-object v3, p0, Landroidx/constraintlayout/widget/ConstraintLayout$Measurer;->mLayout:Landroidx/constraintlayout/widget/ConstraintLayout;

    invoke-virtual {v2, v3}, Landroidx/constraintlayout/widget/ConstraintHelper;->updatePostMeasure(Landroidx/constraintlayout/widget/ConstraintLayout;)V

    add-int/lit8 v1, v1, 0x1

    goto :goto_1

    :cond_2
    return-void
.end method

.method public final measure(Landroidx/constraintlayout/core/widgets/ConstraintWidget;Landroidx/constraintlayout/core/widgets/analyzer/BasicMeasure$Measure;)V
    .locals 18
    .annotation build Landroid/annotation/SuppressLint;
        value = {
            "WrongCall"
        }
    .end annotation

    move-object/from16 v0, p0

    move-object/from16 v1, p1

    move-object/from16 v2, p2

    if-nez v1, :cond_0

    return-void

    .line 734
    :cond_0
    invoke-virtual/range {p1 .. p1}, Landroidx/constraintlayout/core/widgets/ConstraintWidget;->getVisibility()I

    move-result v3

    const/16 v4, 0x8

    const/4 v5, 0x0

    if-ne v3, v4, :cond_1

    invoke-virtual/range {p1 .. p1}, Landroidx/constraintlayout/core/widgets/ConstraintWidget;->isInPlaceholder()Z

    move-result v3

    if-nez v3, :cond_1

    .line 735
    iput v5, v2, Landroidx/constraintlayout/core/widgets/analyzer/BasicMeasure$Measure;->measuredWidth:I

    .line 736
    iput v5, v2, Landroidx/constraintlayout/core/widgets/analyzer/BasicMeasure$Measure;->measuredHeight:I

    .line 737
    iput v5, v2, Landroidx/constraintlayout/core/widgets/analyzer/BasicMeasure$Measure;->measuredBaseline:I

    return-void

    .line 740
    :cond_1
    invoke-virtual/range {p1 .. p1}, Landroidx/constraintlayout/core/widgets/ConstraintWidget;->getParent()Landroidx/constraintlayout/core/widgets/ConstraintWidget;

    move-result-object v3

    if-nez v3, :cond_2

    return-void

    .line 747
    :cond_2
    iget-object v3, v0, Landroidx/constraintlayout/widget/ConstraintLayout$Measurer;->this$0:Landroidx/constraintlayout/widget/ConstraintLayout;

    invoke-static {v3}, Landroidx/constraintlayout/widget/ConstraintLayout;->access$000(Landroidx/constraintlayout/widget/ConstraintLayout;)Landroidx/constraintlayout/core/Metrics;

    .line 752
    iget-object v3, v2, Landroidx/constraintlayout/core/widgets/analyzer/BasicMeasure$Measure;->horizontalBehavior:Landroidx/constraintlayout/core/widgets/ConstraintWidget$DimensionBehaviour;

    .line 753
    iget-object v4, v2, Landroidx/constraintlayout/core/widgets/analyzer/BasicMeasure$Measure;->verticalBehavior:Landroidx/constraintlayout/core/widgets/ConstraintWidget$DimensionBehaviour;

    .line 755
    iget v6, v2, Landroidx/constraintlayout/core/widgets/analyzer/BasicMeasure$Measure;->horizontalDimension:I

    .line 756
    iget v7, v2, Landroidx/constraintlayout/core/widgets/analyzer/BasicMeasure$Measure;->verticalDimension:I

    .line 761
    iget v8, v0, Landroidx/constraintlayout/widget/ConstraintLayout$Measurer;->mPaddingTop:I

    iget v9, v0, Landroidx/constraintlayout/widget/ConstraintLayout$Measurer;->mPaddingBottom:I

    add-int/2addr v8, v9

    .line 762
    iget v9, v0, Landroidx/constraintlayout/widget/ConstraintLayout$Measurer;->mPaddingWidth:I

    .line 764
    invoke-virtual/range {p1 .. p1}, Landroidx/constraintlayout/core/widgets/ConstraintWidget;->getCompanionWidget()Ljava/lang/Object;

    move-result-object v10

    check-cast v10, Landroid/view/View;

    .line 766
    sget-object v11, Landroidx/constraintlayout/widget/ConstraintLayout$1;->$SwitchMap$androidx$constraintlayout$core$widgets$ConstraintWidget$DimensionBehaviour:[I

    invoke-virtual {v3}, Ljava/lang/Enum;->ordinal()I

    move-result v12

    aget v12, v11, v12

    const/4 v13, 0x4

    const/4 v14, 0x3

    const/4 v15, 0x2

    const/4 v5, 0x1

    if-eq v12, v5, :cond_c

    if-eq v12, v15, :cond_b

    if-eq v12, v14, :cond_a

    if-eq v12, v13, :cond_3

    const/4 v6, 0x0

    goto/16 :goto_4

    .line 785
    :cond_3
    iget v6, v0, Landroidx/constraintlayout/widget/ConstraintLayout$Measurer;->mLayoutWidthSpec:I

    const/4 v12, -0x2

    invoke-static {v6, v9, v12}, Landroid/view/ViewGroup;->getChildMeasureSpec(III)I

    move-result v6

    .line 787
    iget v9, v1, Landroidx/constraintlayout/core/widgets/ConstraintWidget;->mMatchConstraintDefaultWidth:I

    if-ne v9, v5, :cond_4

    move v9, v5

    goto :goto_0

    :cond_4
    const/4 v9, 0x0

    .line 789
    :goto_0
    iget v12, v2, Landroidx/constraintlayout/core/widgets/analyzer/BasicMeasure$Measure;->measureStrategy:I

    sget v13, Landroidx/constraintlayout/core/widgets/analyzer/BasicMeasure$Measure;->TRY_GIVEN_DIMENSIONS:I

    if-eq v12, v13, :cond_5

    sget v13, Landroidx/constraintlayout/core/widgets/analyzer/BasicMeasure$Measure;->USE_GIVEN_DIMENSIONS:I

    if-ne v12, v13, :cond_d

    .line 798
    :cond_5
    invoke-virtual {v10}, Landroid/view/View;->getMeasuredHeight()I

    move-result v12

    .line 799
    invoke-virtual/range {p1 .. p1}, Landroidx/constraintlayout/core/widgets/ConstraintWidget;->getHeight()I

    move-result v13

    if-ne v12, v13, :cond_6

    move v12, v5

    goto :goto_1

    :cond_6
    const/4 v12, 0x0

    .line 800
    :goto_1
    iget v13, v2, Landroidx/constraintlayout/core/widgets/analyzer/BasicMeasure$Measure;->measureStrategy:I

    sget v14, Landroidx/constraintlayout/core/widgets/analyzer/BasicMeasure$Measure;->USE_GIVEN_DIMENSIONS:I

    if-eq v13, v14, :cond_9

    if-eqz v9, :cond_9

    if-eqz v9, :cond_7

    if-nez v12, :cond_9

    :cond_7
    instance-of v9, v10, Landroidx/constraintlayout/widget/Placeholder;

    if-nez v9, :cond_9

    .line 805
    invoke-virtual/range {p1 .. p1}, Landroidx/constraintlayout/core/widgets/ConstraintWidget;->isResolvedHorizontally()Z

    move-result v9

    if-eqz v9, :cond_8

    goto :goto_2

    :cond_8
    const/4 v9, 0x0

    goto :goto_3

    :cond_9
    :goto_2
    move v9, v5

    :goto_3
    if-eqz v9, :cond_d

    .line 807
    invoke-virtual/range {p1 .. p1}, Landroidx/constraintlayout/core/widgets/ConstraintWidget;->getWidth()I

    move-result v6

    const/high16 v9, 0x40000000    # 2.0f

    invoke-static {v6, v9}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result v6

    goto :goto_4

    .line 779
    :cond_a
    iget v6, v0, Landroidx/constraintlayout/widget/ConstraintLayout$Measurer;->mLayoutWidthSpec:I

    .line 780
    invoke-virtual/range {p1 .. p1}, Landroidx/constraintlayout/core/widgets/ConstraintWidget;->getHorizontalMargin()I

    move-result v12

    add-int/2addr v9, v12

    const/4 v12, -0x1

    .line 779
    invoke-static {v6, v9, v12}, Landroid/view/ViewGroup;->getChildMeasureSpec(III)I

    move-result v6

    goto :goto_4

    .line 773
    :cond_b
    iget v6, v0, Landroidx/constraintlayout/widget/ConstraintLayout$Measurer;->mLayoutWidthSpec:I

    const/4 v12, -0x2

    invoke-static {v6, v9, v12}, Landroid/view/ViewGroup;->getChildMeasureSpec(III)I

    move-result v6

    goto :goto_4

    :cond_c
    const/high16 v9, 0x40000000    # 2.0f

    .line 768
    invoke-static {v6, v9}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result v6

    .line 815
    :cond_d
    :goto_4
    invoke-virtual {v4}, Ljava/lang/Enum;->ordinal()I

    move-result v9

    aget v9, v11, v9

    if-eq v9, v5, :cond_17

    if-eq v9, v15, :cond_16

    const/4 v7, 0x3

    if-eq v9, v7, :cond_15

    const/4 v7, 0x4

    if-eq v9, v7, :cond_e

    const/4 v7, 0x0

    goto/16 :goto_9

    .line 834
    :cond_e
    iget v7, v0, Landroidx/constraintlayout/widget/ConstraintLayout$Measurer;->mLayoutHeightSpec:I

    const/4 v9, -0x2

    invoke-static {v7, v8, v9}, Landroid/view/ViewGroup;->getChildMeasureSpec(III)I

    move-result v7

    .line 836
    iget v8, v1, Landroidx/constraintlayout/core/widgets/ConstraintWidget;->mMatchConstraintDefaultHeight:I

    if-ne v8, v5, :cond_f

    move v8, v5

    goto :goto_5

    :cond_f
    const/4 v8, 0x0

    .line 838
    :goto_5
    iget v9, v2, Landroidx/constraintlayout/core/widgets/analyzer/BasicMeasure$Measure;->measureStrategy:I

    sget v11, Landroidx/constraintlayout/core/widgets/analyzer/BasicMeasure$Measure;->TRY_GIVEN_DIMENSIONS:I

    if-eq v9, v11, :cond_10

    sget v11, Landroidx/constraintlayout/core/widgets/analyzer/BasicMeasure$Measure;->USE_GIVEN_DIMENSIONS:I

    if-ne v9, v11, :cond_18

    .line 847
    :cond_10
    invoke-virtual {v10}, Landroid/view/View;->getMeasuredWidth()I

    move-result v9

    .line 848
    invoke-virtual/range {p1 .. p1}, Landroidx/constraintlayout/core/widgets/ConstraintWidget;->getWidth()I

    move-result v11

    if-ne v9, v11, :cond_11

    move v9, v5

    goto :goto_6

    :cond_11
    const/4 v9, 0x0

    .line 849
    :goto_6
    iget v11, v2, Landroidx/constraintlayout/core/widgets/analyzer/BasicMeasure$Measure;->measureStrategy:I

    sget v12, Landroidx/constraintlayout/core/widgets/analyzer/BasicMeasure$Measure;->USE_GIVEN_DIMENSIONS:I

    if-eq v11, v12, :cond_14

    if-eqz v8, :cond_14

    if-eqz v8, :cond_12

    if-nez v9, :cond_14

    :cond_12
    instance-of v8, v10, Landroidx/constraintlayout/widget/Placeholder;

    if-nez v8, :cond_14

    .line 854
    invoke-virtual/range {p1 .. p1}, Landroidx/constraintlayout/core/widgets/ConstraintWidget;->isResolvedVertically()Z

    move-result v8

    if-eqz v8, :cond_13

    goto :goto_7

    :cond_13
    const/4 v8, 0x0

    goto :goto_8

    :cond_14
    :goto_7
    move v8, v5

    :goto_8
    if-eqz v8, :cond_18

    .line 856
    invoke-virtual/range {p1 .. p1}, Landroidx/constraintlayout/core/widgets/ConstraintWidget;->getHeight()I

    move-result v7

    const/high16 v8, 0x40000000    # 2.0f

    invoke-static {v7, v8}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result v7

    goto :goto_9

    .line 828
    :cond_15
    iget v7, v0, Landroidx/constraintlayout/widget/ConstraintLayout$Measurer;->mLayoutHeightSpec:I

    .line 829
    invoke-virtual/range {p1 .. p1}, Landroidx/constraintlayout/core/widgets/ConstraintWidget;->getVerticalMargin()I

    move-result v9

    add-int/2addr v8, v9

    const/4 v9, -0x1

    .line 828
    invoke-static {v7, v8, v9}, Landroid/view/ViewGroup;->getChildMeasureSpec(III)I

    move-result v7

    goto :goto_9

    .line 822
    :cond_16
    iget v7, v0, Landroidx/constraintlayout/widget/ConstraintLayout$Measurer;->mLayoutHeightSpec:I

    const/4 v9, -0x2

    invoke-static {v7, v8, v9}, Landroid/view/ViewGroup;->getChildMeasureSpec(III)I

    move-result v7

    goto :goto_9

    :cond_17
    const/high16 v8, 0x40000000    # 2.0f

    .line 817
    invoke-static {v7, v8}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result v7

    .line 864
    :cond_18
    :goto_9
    invoke-virtual/range {p1 .. p1}, Landroidx/constraintlayout/core/widgets/ConstraintWidget;->getParent()Landroidx/constraintlayout/core/widgets/ConstraintWidget;

    move-result-object v8

    check-cast v8, Landroidx/constraintlayout/core/widgets/ConstraintWidgetContainer;

    if-eqz v8, :cond_1a

    .line 865
    iget-object v9, v0, Landroidx/constraintlayout/widget/ConstraintLayout$Measurer;->this$0:Landroidx/constraintlayout/widget/ConstraintLayout;

    invoke-static {v9}, Landroidx/constraintlayout/widget/ConstraintLayout;->access$100(Landroidx/constraintlayout/widget/ConstraintLayout;)I

    move-result v9

    const/16 v11, 0x100

    invoke-static {v9, v11}, Landroidx/constraintlayout/core/widgets/Optimizer;->enabled(II)Z

    move-result v9

    if-eqz v9, :cond_1a

    .line 867
    invoke-virtual {v10}, Landroid/view/View;->getMeasuredWidth()I

    move-result v9

    invoke-virtual/range {p1 .. p1}, Landroidx/constraintlayout/core/widgets/ConstraintWidget;->getWidth()I

    move-result v11

    if-ne v9, v11, :cond_1a

    .line 870
    invoke-virtual {v10}, Landroid/view/View;->getMeasuredWidth()I

    move-result v9

    invoke-virtual {v8}, Landroidx/constraintlayout/core/widgets/ConstraintWidget;->getWidth()I

    move-result v11

    if-ge v9, v11, :cond_1a

    .line 871
    invoke-virtual {v10}, Landroid/view/View;->getMeasuredHeight()I

    move-result v9

    invoke-virtual/range {p1 .. p1}, Landroidx/constraintlayout/core/widgets/ConstraintWidget;->getHeight()I

    move-result v11

    if-ne v9, v11, :cond_1a

    .line 872
    invoke-virtual {v10}, Landroid/view/View;->getMeasuredHeight()I

    move-result v9

    invoke-virtual {v8}, Landroidx/constraintlayout/core/widgets/ConstraintWidget;->getHeight()I

    move-result v8

    if-ge v9, v8, :cond_1a

    .line 873
    invoke-virtual {v10}, Landroid/view/View;->getBaseline()I

    move-result v8

    invoke-virtual/range {p1 .. p1}, Landroidx/constraintlayout/core/widgets/ConstraintWidget;->getBaselineDistance()I

    move-result v9

    if-ne v8, v9, :cond_1a

    .line 874
    invoke-virtual/range {p1 .. p1}, Landroidx/constraintlayout/core/widgets/ConstraintWidget;->isMeasureRequested()Z

    move-result v8

    if-nez v8, :cond_1a

    .line 876
    invoke-virtual/range {p1 .. p1}, Landroidx/constraintlayout/core/widgets/ConstraintWidget;->getLastHorizontalMeasureSpec()I

    move-result v8

    .line 877
    invoke-virtual/range {p1 .. p1}, Landroidx/constraintlayout/core/widgets/ConstraintWidget;->getWidth()I

    move-result v9

    .line 876
    invoke-direct {v0, v8, v6, v9}, Landroidx/constraintlayout/widget/ConstraintLayout$Measurer;->isSimilarSpec(III)Z

    move-result v8

    if-eqz v8, :cond_19

    .line 878
    invoke-virtual/range {p1 .. p1}, Landroidx/constraintlayout/core/widgets/ConstraintWidget;->getLastVerticalMeasureSpec()I

    move-result v8

    .line 879
    invoke-virtual/range {p1 .. p1}, Landroidx/constraintlayout/core/widgets/ConstraintWidget;->getHeight()I

    move-result v9

    .line 878
    invoke-direct {v0, v8, v7, v9}, Landroidx/constraintlayout/widget/ConstraintLayout$Measurer;->isSimilarSpec(III)Z

    move-result v8

    if-eqz v8, :cond_19

    move v8, v5

    goto :goto_a

    :cond_19
    const/4 v8, 0x0

    :goto_a
    if-eqz v8, :cond_1a

    .line 881
    invoke-virtual/range {p1 .. p1}, Landroidx/constraintlayout/core/widgets/ConstraintWidget;->getWidth()I

    move-result v0

    iput v0, v2, Landroidx/constraintlayout/core/widgets/analyzer/BasicMeasure$Measure;->measuredWidth:I

    .line 882
    invoke-virtual/range {p1 .. p1}, Landroidx/constraintlayout/core/widgets/ConstraintWidget;->getHeight()I

    move-result v0

    iput v0, v2, Landroidx/constraintlayout/core/widgets/analyzer/BasicMeasure$Measure;->measuredHeight:I

    .line 883
    invoke-virtual/range {p1 .. p1}, Landroidx/constraintlayout/core/widgets/ConstraintWidget;->getBaselineDistance()I

    move-result v0

    iput v0, v2, Landroidx/constraintlayout/core/widgets/analyzer/BasicMeasure$Measure;->measuredBaseline:I

    return-void

    .line 894
    :cond_1a
    sget-object v8, Landroidx/constraintlayout/core/widgets/ConstraintWidget$DimensionBehaviour;->MATCH_CONSTRAINT:Landroidx/constraintlayout/core/widgets/ConstraintWidget$DimensionBehaviour;

    if-ne v3, v8, :cond_1b

    move v9, v5

    goto :goto_b

    :cond_1b
    const/4 v9, 0x0

    :goto_b
    if-ne v4, v8, :cond_1c

    move v8, v5

    goto :goto_c

    :cond_1c
    const/4 v8, 0x0

    .line 899
    :goto_c
    sget-object v11, Landroidx/constraintlayout/core/widgets/ConstraintWidget$DimensionBehaviour;->MATCH_PARENT:Landroidx/constraintlayout/core/widgets/ConstraintWidget$DimensionBehaviour;

    if-eq v4, v11, :cond_1e

    sget-object v12, Landroidx/constraintlayout/core/widgets/ConstraintWidget$DimensionBehaviour;->FIXED:Landroidx/constraintlayout/core/widgets/ConstraintWidget$DimensionBehaviour;

    if-ne v4, v12, :cond_1d

    goto :goto_d

    :cond_1d
    const/4 v4, 0x0

    goto :goto_e

    :cond_1e
    :goto_d
    move v4, v5

    :goto_e
    if-eq v3, v11, :cond_20

    .line 902
    sget-object v11, Landroidx/constraintlayout/core/widgets/ConstraintWidget$DimensionBehaviour;->FIXED:Landroidx/constraintlayout/core/widgets/ConstraintWidget$DimensionBehaviour;

    if-ne v3, v11, :cond_1f

    goto :goto_f

    :cond_1f
    const/4 v3, 0x0

    goto :goto_10

    :cond_20
    :goto_f
    move v3, v5

    :goto_10
    const/4 v11, 0x0

    if-eqz v9, :cond_21

    .line 905
    iget v12, v1, Landroidx/constraintlayout/core/widgets/ConstraintWidget;->mDimensionRatio:F

    cmpl-float v12, v12, v11

    if-lez v12, :cond_21

    move v12, v5

    goto :goto_11

    :cond_21
    const/4 v12, 0x0

    :goto_11
    if-eqz v8, :cond_22

    .line 906
    iget v13, v1, Landroidx/constraintlayout/core/widgets/ConstraintWidget;->mDimensionRatio:F

    cmpl-float v11, v13, v11

    if-lez v11, :cond_22

    move v11, v5

    goto :goto_12

    :cond_22
    const/4 v11, 0x0

    :goto_12
    if-nez v10, :cond_23

    return-void

    .line 911
    :cond_23
    invoke-virtual {v10}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v13

    check-cast v13, Landroidx/constraintlayout/widget/ConstraintLayout$LayoutParams;

    .line 917
    iget v14, v2, Landroidx/constraintlayout/core/widgets/analyzer/BasicMeasure$Measure;->measureStrategy:I

    sget v15, Landroidx/constraintlayout/core/widgets/analyzer/BasicMeasure$Measure;->TRY_GIVEN_DIMENSIONS:I

    if-eq v14, v15, :cond_25

    sget v15, Landroidx/constraintlayout/core/widgets/analyzer/BasicMeasure$Measure;->USE_GIVEN_DIMENSIONS:I

    if-eq v14, v15, :cond_25

    if-eqz v9, :cond_25

    iget v9, v1, Landroidx/constraintlayout/core/widgets/ConstraintWidget;->mMatchConstraintDefaultWidth:I

    if-nez v9, :cond_25

    if-eqz v8, :cond_25

    iget v8, v1, Landroidx/constraintlayout/core/widgets/ConstraintWidget;->mMatchConstraintDefaultHeight:I

    if-eqz v8, :cond_24

    goto :goto_13

    :cond_24
    const/4 v3, -0x1

    const/4 v5, 0x0

    const/4 v14, 0x0

    const/4 v15, 0x0

    goto/16 :goto_1c

    .line 924
    :cond_25
    :goto_13
    instance-of v8, v10, Landroidx/constraintlayout/widget/VirtualLayout;

    if-eqz v8, :cond_26

    instance-of v8, v1, Landroidx/constraintlayout/core/widgets/VirtualLayout;

    if-eqz v8, :cond_26

    .line 926
    move-object v8, v1

    check-cast v8, Landroidx/constraintlayout/core/widgets/VirtualLayout;

    .line 928
    move-object v9, v10

    check-cast v9, Landroidx/constraintlayout/widget/VirtualLayout;

    invoke-virtual {v9, v8, v6, v7}, Landroidx/constraintlayout/widget/VirtualLayout;->onMeasure(Landroidx/constraintlayout/core/widgets/VirtualLayout;II)V

    goto :goto_14

    .line 930
    :cond_26
    invoke-virtual {v10, v6, v7}, Landroid/view/View;->measure(II)V

    .line 932
    :goto_14
    invoke-virtual {v1, v6, v7}, Landroidx/constraintlayout/core/widgets/ConstraintWidget;->setLastMeasureSpec(II)V

    .line 934
    invoke-virtual {v10}, Landroid/view/View;->getMeasuredWidth()I

    move-result v8

    .line 935
    invoke-virtual {v10}, Landroid/view/View;->getMeasuredHeight()I

    move-result v9

    .line 936
    invoke-virtual {v10}, Landroid/view/View;->getBaseline()I

    move-result v14

    .line 949
    iget v15, v1, Landroidx/constraintlayout/core/widgets/ConstraintWidget;->mMatchConstraintMinWidth:I

    if-lez v15, :cond_27

    .line 950
    invoke-static {v15, v8}, Ljava/lang/Math;->max(II)I

    move-result v15

    goto :goto_15

    :cond_27
    move v15, v8

    .line 952
    :goto_15
    iget v5, v1, Landroidx/constraintlayout/core/widgets/ConstraintWidget;->mMatchConstraintMaxWidth:I

    if-lez v5, :cond_28

    .line 953
    invoke-static {v5, v15}, Ljava/lang/Math;->min(II)I

    move-result v15

    .line 955
    :cond_28
    iget v5, v1, Landroidx/constraintlayout/core/widgets/ConstraintWidget;->mMatchConstraintMinHeight:I

    if-lez v5, :cond_29

    .line 956
    invoke-static {v5, v9}, Ljava/lang/Math;->max(II)I

    move-result v5

    move/from16 v16, v6

    goto :goto_16

    :cond_29
    move/from16 v16, v6

    move v5, v9

    .line 958
    :goto_16
    iget v6, v1, Landroidx/constraintlayout/core/widgets/ConstraintWidget;->mMatchConstraintMaxHeight:I

    if-lez v6, :cond_2a

    .line 959
    invoke-static {v6, v5}, Ljava/lang/Math;->min(II)I

    move-result v5

    .line 962
    :cond_2a
    iget-object v6, v0, Landroidx/constraintlayout/widget/ConstraintLayout$Measurer;->this$0:Landroidx/constraintlayout/widget/ConstraintLayout;

    invoke-static {v6}, Landroidx/constraintlayout/widget/ConstraintLayout;->access$100(Landroidx/constraintlayout/widget/ConstraintLayout;)I

    move-result v6

    move/from16 v17, v7

    const/4 v7, 0x1

    invoke-static {v6, v7}, Landroidx/constraintlayout/core/widgets/Optimizer;->enabled(II)Z

    move-result v6

    if-nez v6, :cond_2c

    const/high16 v6, 0x3f000000    # 0.5f

    if-eqz v12, :cond_2b

    if-eqz v4, :cond_2b

    .line 966
    iget v3, v1, Landroidx/constraintlayout/core/widgets/ConstraintWidget;->mDimensionRatio:F

    int-to-float v4, v5

    mul-float/2addr v4, v3

    add-float/2addr v4, v6

    float-to-int v15, v4

    goto :goto_17

    :cond_2b
    if-eqz v11, :cond_2c

    if-eqz v3, :cond_2c

    .line 969
    iget v3, v1, Landroidx/constraintlayout/core/widgets/ConstraintWidget;->mDimensionRatio:F

    int-to-float v4, v15

    div-float/2addr v4, v3

    add-float/2addr v4, v6

    float-to-int v5, v4

    :cond_2c
    :goto_17
    if-ne v8, v15, :cond_2e

    if-eq v9, v5, :cond_2d

    goto :goto_19

    :cond_2d
    :goto_18
    const/4 v3, -0x1

    goto :goto_1c

    :cond_2e
    :goto_19
    const/high16 v3, 0x40000000    # 2.0f

    if-eq v8, v15, :cond_2f

    .line 976
    invoke-static {v15, v3}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result v6

    goto :goto_1a

    :cond_2f
    move/from16 v6, v16

    :goto_1a
    if-eq v9, v5, :cond_30

    .line 979
    invoke-static {v5, v3}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result v7

    goto :goto_1b

    :cond_30
    move/from16 v7, v17

    .line 981
    :goto_1b
    invoke-virtual {v10, v6, v7}, Landroid/view/View;->measure(II)V

    .line 983
    invoke-virtual {v1, v6, v7}, Landroidx/constraintlayout/core/widgets/ConstraintWidget;->setLastMeasureSpec(II)V

    .line 984
    invoke-virtual {v10}, Landroid/view/View;->getMeasuredWidth()I

    move-result v15

    .line 985
    invoke-virtual {v10}, Landroid/view/View;->getMeasuredHeight()I

    move-result v5

    .line 986
    invoke-virtual {v10}, Landroid/view/View;->getBaseline()I

    move-result v14

    goto :goto_18

    :goto_1c
    if-eq v14, v3, :cond_31

    const/4 v7, 0x1

    goto :goto_1d

    :cond_31
    const/4 v7, 0x0

    .line 1000
    :goto_1d
    iget v3, v2, Landroidx/constraintlayout/core/widgets/analyzer/BasicMeasure$Measure;->horizontalDimension:I

    if-ne v15, v3, :cond_33

    iget v3, v2, Landroidx/constraintlayout/core/widgets/analyzer/BasicMeasure$Measure;->verticalDimension:I

    if-eq v5, v3, :cond_32

    goto :goto_1e

    :cond_32
    const/4 v3, 0x0

    goto :goto_1f

    :cond_33
    :goto_1e
    const/4 v3, 0x1

    :goto_1f
    iput-boolean v3, v2, Landroidx/constraintlayout/core/widgets/analyzer/BasicMeasure$Measure;->measuredNeedsSolverPass:Z

    .line 1002
    iget-boolean v3, v13, Landroidx/constraintlayout/widget/ConstraintLayout$LayoutParams;->mNeedsBaseline:Z

    if-eqz v3, :cond_34

    const/4 v7, 0x1

    :cond_34
    if-eqz v7, :cond_35

    const/4 v3, -0x1

    if-eq v14, v3, :cond_35

    .line 1005
    invoke-virtual/range {p1 .. p1}, Landroidx/constraintlayout/core/widgets/ConstraintWidget;->getBaselineDistance()I

    move-result v1

    if-eq v1, v14, :cond_35

    const/4 v1, 0x1

    .line 1006
    iput-boolean v1, v2, Landroidx/constraintlayout/core/widgets/analyzer/BasicMeasure$Measure;->measuredNeedsSolverPass:Z

    .line 1008
    :cond_35
    iput v15, v2, Landroidx/constraintlayout/core/widgets/analyzer/BasicMeasure$Measure;->measuredWidth:I

    .line 1009
    iput v5, v2, Landroidx/constraintlayout/core/widgets/analyzer/BasicMeasure$Measure;->measuredHeight:I

    .line 1010
    iput-boolean v7, v2, Landroidx/constraintlayout/core/widgets/analyzer/BasicMeasure$Measure;->measuredHasBaseline:Z

    .line 1011
    iput v14, v2, Landroidx/constraintlayout/core/widgets/analyzer/BasicMeasure$Measure;->measuredBaseline:I

    .line 1012
    iget-object v0, v0, Landroidx/constraintlayout/widget/ConstraintLayout$Measurer;->this$0:Landroidx/constraintlayout/widget/ConstraintLayout;

    invoke-static {v0}, Landroidx/constraintlayout/widget/ConstraintLayout;->access$000(Landroidx/constraintlayout/widget/ConstraintLayout;)Landroidx/constraintlayout/core/Metrics;

    return-void
.end method
