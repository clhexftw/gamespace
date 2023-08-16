.class public Lcom/android/launcher3/util/override/MainThreadInitializedObject;
.super Ljava/lang/Object;
.source "MainThreadInitializedObject.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/android/launcher3/util/override/MainThreadInitializedObject$ObjectProvider;
    }
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "<T:",
        "Ljava/lang/Object;",
        ">",
        "Ljava/lang/Object;"
    }
.end annotation


# instance fields
.field private final mProvider:Lcom/android/launcher3/util/override/MainThreadInitializedObject$ObjectProvider;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lcom/android/launcher3/util/override/MainThreadInitializedObject$ObjectProvider<",
            "TT;>;"
        }
    .end annotation
.end field

.field private mValue:Ljava/lang/Object;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "TT;"
        }
    .end annotation
.end field


# direct methods
.method public constructor <init>(Lcom/android/launcher3/util/override/MainThreadInitializedObject$ObjectProvider;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/android/launcher3/util/override/MainThreadInitializedObject$ObjectProvider<",
            "TT;>;)V"
        }
    .end annotation

    .line 47
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 48
    iput-object p1, p0, Lcom/android/launcher3/util/override/MainThreadInitializedObject;->mProvider:Lcom/android/launcher3/util/override/MainThreadInitializedObject$ObjectProvider;

    return-void
.end method

.method public static forOverride(Ljava/lang/Class;I)Lcom/android/launcher3/util/override/MainThreadInitializedObject;
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "<T:",
            "Ljava/lang/Object;",
            ">(",
            "Ljava/lang/Class<",
            "TT;>;I)",
            "Lcom/android/launcher3/util/override/MainThreadInitializedObject<",
            "TT;>;"
        }
    .end annotation

    .line 85
    new-instance v0, Lcom/android/launcher3/util/override/MainThreadInitializedObject;

    new-instance v1, Lcom/android/launcher3/util/override/MainThreadInitializedObject$$ExternalSyntheticLambda0;

    invoke-direct {v1, p0, p1}, Lcom/android/launcher3/util/override/MainThreadInitializedObject$$ExternalSyntheticLambda0;-><init>(Ljava/lang/Class;I)V

    invoke-direct {v0, v1}, Lcom/android/launcher3/util/override/MainThreadInitializedObject;-><init>(Lcom/android/launcher3/util/override/MainThreadInitializedObject$ObjectProvider;)V

    return-object v0
.end method


# virtual methods
.method public initializeForTesting(Ljava/lang/Object;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(TT;)V"
        }
    .end annotation

    .line 77
    iput-object p1, p0, Lcom/android/launcher3/util/override/MainThreadInitializedObject;->mValue:Ljava/lang/Object;

    return-void
.end method
