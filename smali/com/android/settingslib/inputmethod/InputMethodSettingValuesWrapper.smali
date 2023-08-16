.class public Lcom/android/settingslib/inputmethod/InputMethodSettingValuesWrapper;
.super Ljava/lang/Object;
.source "InputMethodSettingValuesWrapper.java"


# static fields
.field private static sInstanceMap:Landroid/util/SparseArray;
    .annotation build Lcom/android/internal/annotations/GuardedBy;
        value = {
            "sInstanceMapLock"
        }
    .end annotation

    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroid/util/SparseArray<",
            "Lcom/android/settingslib/inputmethod/InputMethodSettingValuesWrapper;",
            ">;"
        }
    .end annotation
.end field

.field private static final sInstanceMapLock:Ljava/lang/Object;


# instance fields
.field private final mContentResolver:Landroid/content/ContentResolver;

.field private final mImm:Landroid/view/inputmethod/InputMethodManager;

.field private final mMethodList:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Landroid/view/inputmethod/InputMethodInfo;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .line 48
    new-instance v0, Ljava/lang/Object;

    invoke-direct {v0}, Ljava/lang/Object;-><init>()V

    sput-object v0, Lcom/android/settingslib/inputmethod/InputMethodSettingValuesWrapper;->sInstanceMapLock:Ljava/lang/Object;

    .line 54
    new-instance v0, Landroid/util/SparseArray;

    invoke-direct {v0}, Landroid/util/SparseArray;-><init>()V

    sput-object v0, Lcom/android/settingslib/inputmethod/InputMethodSettingValuesWrapper;->sInstanceMap:Landroid/util/SparseArray;

    return-void
.end method

.method private constructor <init>(Landroid/content/Context;)V
    .locals 1

    .line 84
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 55
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/android/settingslib/inputmethod/InputMethodSettingValuesWrapper;->mMethodList:Ljava/util/ArrayList;

    .line 85
    invoke-virtual {p1}, Landroid/content/Context;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object v0

    iput-object v0, p0, Lcom/android/settingslib/inputmethod/InputMethodSettingValuesWrapper;->mContentResolver:Landroid/content/ContentResolver;

    .line 86
    const-class v0, Landroid/view/inputmethod/InputMethodManager;

    invoke-virtual {p1, v0}, Landroid/content/Context;->getSystemService(Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Landroid/view/inputmethod/InputMethodManager;

    iput-object p1, p0, Lcom/android/settingslib/inputmethod/InputMethodSettingValuesWrapper;->mImm:Landroid/view/inputmethod/InputMethodManager;

    .line 87
    invoke-virtual {p0}, Lcom/android/settingslib/inputmethod/InputMethodSettingValuesWrapper;->refreshAllInputMethodAndSubtypes()V

    return-void
.end method

.method public static getInstance(Landroid/content/Context;)Lcom/android/settingslib/inputmethod/InputMethodSettingValuesWrapper;
    .locals 3

    .line 62
    invoke-virtual {p0}, Landroid/content/Context;->getUserId()I

    move-result v0

    .line 65
    sget-object v1, Lcom/android/settingslib/inputmethod/InputMethodSettingValuesWrapper;->sInstanceMapLock:Ljava/lang/Object;

    monitor-enter v1

    .line 66
    :try_start_0
    sget-object v2, Lcom/android/settingslib/inputmethod/InputMethodSettingValuesWrapper;->sInstanceMap:Landroid/util/SparseArray;

    invoke-virtual {v2}, Landroid/util/SparseArray;->size()I

    move-result v2

    if-nez v2, :cond_0

    .line 67
    new-instance v2, Lcom/android/settingslib/inputmethod/InputMethodSettingValuesWrapper;

    invoke-direct {v2, p0}, Lcom/android/settingslib/inputmethod/InputMethodSettingValuesWrapper;-><init>(Landroid/content/Context;)V

    .line 68
    sget-object p0, Lcom/android/settingslib/inputmethod/InputMethodSettingValuesWrapper;->sInstanceMap:Landroid/util/SparseArray;

    invoke-virtual {p0, v0, v2}, Landroid/util/SparseArray;->put(ILjava/lang/Object;)V

    .line 69
    monitor-exit v1

    return-object v2

    .line 72
    :cond_0
    sget-object v2, Lcom/android/settingslib/inputmethod/InputMethodSettingValuesWrapper;->sInstanceMap:Landroid/util/SparseArray;

    invoke-virtual {v2, v0}, Landroid/util/SparseArray;->indexOfKey(I)I

    move-result v2

    if-ltz v2, :cond_1

    .line 73
    sget-object p0, Lcom/android/settingslib/inputmethod/InputMethodSettingValuesWrapper;->sInstanceMap:Landroid/util/SparseArray;

    invoke-virtual {p0, v0}, Landroid/util/SparseArray;->get(I)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lcom/android/settingslib/inputmethod/InputMethodSettingValuesWrapper;

    monitor-exit v1

    return-object p0

    .line 76
    :cond_1
    new-instance v0, Lcom/android/settingslib/inputmethod/InputMethodSettingValuesWrapper;

    invoke-direct {v0, p0}, Lcom/android/settingslib/inputmethod/InputMethodSettingValuesWrapper;-><init>(Landroid/content/Context;)V

    .line 77
    sget-object v2, Lcom/android/settingslib/inputmethod/InputMethodSettingValuesWrapper;->sInstanceMap:Landroid/util/SparseArray;

    invoke-virtual {p0}, Landroid/content/Context;->getUserId()I

    move-result p0

    invoke-virtual {v2, p0, v0}, Landroid/util/SparseArray;->put(ILjava/lang/Object;)V

    .line 78
    monitor-exit v1

    return-object v0

    :catchall_0
    move-exception p0

    monitor-exit v1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw p0
.end method


# virtual methods
.method public refreshAllInputMethodAndSubtypes()V
    .locals 3

    .line 91
    iget-object v0, p0, Lcom/android/settingslib/inputmethod/InputMethodSettingValuesWrapper;->mMethodList:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->clear()V

    .line 92
    iget-object v0, p0, Lcom/android/settingslib/inputmethod/InputMethodSettingValuesWrapper;->mMethodList:Ljava/util/ArrayList;

    iget-object v1, p0, Lcom/android/settingslib/inputmethod/InputMethodSettingValuesWrapper;->mImm:Landroid/view/inputmethod/InputMethodManager;

    iget-object p0, p0, Lcom/android/settingslib/inputmethod/InputMethodSettingValuesWrapper;->mContentResolver:Landroid/content/ContentResolver;

    .line 93
    invoke-virtual {p0}, Landroid/content/ContentResolver;->getUserId()I

    move-result p0

    const/4 v2, 0x1

    .line 92
    invoke-virtual {v1, p0, v2}, Landroid/view/inputmethod/InputMethodManager;->getInputMethodListAsUser(II)Ljava/util/List;

    move-result-object p0

    invoke-virtual {v0, p0}, Ljava/util/ArrayList;->addAll(Ljava/util/Collection;)Z

    return-void
.end method
