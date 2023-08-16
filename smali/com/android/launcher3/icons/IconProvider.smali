.class public Lcom/android/launcher3/icons/IconProvider;
.super Ljava/lang/Object;
.source "IconProvider.java"


# static fields
.field public static final ATLEAST_T:Z

.field static final CONFIG_ICON_MASK_RES_ID:I

.field public static INSTANCE:Lcom/android/launcher3/util/override/MainThreadInitializedObject;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lcom/android/launcher3/util/override/MainThreadInitializedObject<",
            "Lcom/android/launcher3/icons/IconProvider;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method static constructor <clinit>()V
    .locals 4

    .line 69
    invoke-static {}, Landroid/content/res/Resources;->getSystem()Landroid/content/res/Resources;

    move-result-object v0

    const-string v1, "config_icon_mask"

    const-string v2, "string"

    const-string v3, "android"

    invoke-virtual {v0, v1, v2, v3}, Landroid/content/res/Resources;->getIdentifier(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I

    move-result v0

    sput v0, Lcom/android/launcher3/icons/IconProvider;->CONFIG_ICON_MASK_RES_ID:I

    .line 74
    invoke-static {}, Landroidx/core/os/BuildCompat;->isAtLeastT()Z

    move-result v0

    sput-boolean v0, Lcom/android/launcher3/icons/IconProvider;->ATLEAST_T:Z

    .line 84
    const-class v0, Lcom/android/launcher3/icons/IconProvider;

    sget v1, Lcom/android/launcher3/icons/R$string;->icon_provider_class:I

    .line 85
    invoke-static {v0, v1}, Lcom/android/launcher3/util/override/MainThreadInitializedObject;->forOverride(Ljava/lang/Class;I)Lcom/android/launcher3/util/override/MainThreadInitializedObject;

    move-result-object v0

    sput-object v0, Lcom/android/launcher3/icons/IconProvider;->INSTANCE:Lcom/android/launcher3/util/override/MainThreadInitializedObject;

    return-void
.end method
