.class public abstract Landroidx/appcompat/app/AppCompatDelegate;
.super Ljava/lang/Object;
.source "AppCompatDelegate.java"


# static fields
.field private static final sActivityDelegates:Landroidx/collection/ArraySet;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroidx/collection/ArraySet<",
            "Ljava/lang/ref/WeakReference<",
            "Landroidx/appcompat/app/AppCompatDelegate;",
            ">;>;"
        }
    .end annotation
.end field

.field private static final sActivityDelegatesLock:Ljava/lang/Object;

.field private static final sAppLocalesStorageSyncLock:Ljava/lang/Object;

.field private static sDefaultNightMode:I

.field private static sIsAutoStoreLocalesOptedIn:Ljava/lang/Boolean;

.field private static sLocaleManager:Ljava/lang/Object;

.field private static sRequestedAppLocales:Landroidx/core/os/LocaleListCompat;

.field static sSerialExecutorForLocalesStorage:Landroidx/appcompat/app/AppLocalesStorageHelper$SerialExecutor;

.field private static sStoredAppLocales:Landroidx/core/os/LocaleListCompat;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    .line 110
    new-instance v0, Landroidx/appcompat/app/AppLocalesStorageHelper$SerialExecutor;

    new-instance v1, Landroidx/appcompat/app/AppLocalesStorageHelper$ThreadPerTaskExecutor;

    invoke-direct {v1}, Landroidx/appcompat/app/AppLocalesStorageHelper$ThreadPerTaskExecutor;-><init>()V

    invoke-direct {v0, v1}, Landroidx/appcompat/app/AppLocalesStorageHelper$SerialExecutor;-><init>(Ljava/util/concurrent/Executor;)V

    sput-object v0, Landroidx/appcompat/app/AppCompatDelegate;->sSerialExecutorForLocalesStorage:Landroidx/appcompat/app/AppLocalesStorageHelper$SerialExecutor;

    const/16 v0, -0x64

    .line 182
    sput v0, Landroidx/appcompat/app/AppCompatDelegate;->sDefaultNightMode:I

    const/4 v0, 0x0

    .line 184
    sput-object v0, Landroidx/appcompat/app/AppCompatDelegate;->sRequestedAppLocales:Landroidx/core/os/LocaleListCompat;

    .line 185
    sput-object v0, Landroidx/appcompat/app/AppCompatDelegate;->sStoredAppLocales:Landroidx/core/os/LocaleListCompat;

    .line 186
    sput-object v0, Landroidx/appcompat/app/AppCompatDelegate;->sIsAutoStoreLocalesOptedIn:Ljava/lang/Boolean;

    .line 187
    sput-object v0, Landroidx/appcompat/app/AppCompatDelegate;->sLocaleManager:Ljava/lang/Object;

    .line 194
    new-instance v0, Landroidx/collection/ArraySet;

    invoke-direct {v0}, Landroidx/collection/ArraySet;-><init>()V

    sput-object v0, Landroidx/appcompat/app/AppCompatDelegate;->sActivityDelegates:Landroidx/collection/ArraySet;

    .line 196
    new-instance v0, Ljava/lang/Object;

    invoke-direct {v0}, Ljava/lang/Object;-><init>()V

    sput-object v0, Landroidx/appcompat/app/AppCompatDelegate;->sActivityDelegatesLock:Ljava/lang/Object;

    .line 197
    new-instance v0, Ljava/lang/Object;

    invoke-direct {v0}, Ljava/lang/Object;-><init>()V

    sput-object v0, Landroidx/appcompat/app/AppCompatDelegate;->sAppLocalesStorageSyncLock:Ljava/lang/Object;

    return-void
.end method

.method constructor <init>()V
    .locals 0

    .line 293
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method static addActiveDelegate(Landroidx/appcompat/app/AppCompatDelegate;)V
    .locals 3

    .line 920
    sget-object v0, Landroidx/appcompat/app/AppCompatDelegate;->sActivityDelegatesLock:Ljava/lang/Object;

    monitor-enter v0

    .line 923
    :try_start_0
    invoke-static {p0}, Landroidx/appcompat/app/AppCompatDelegate;->removeDelegateFromActives(Landroidx/appcompat/app/AppCompatDelegate;)V

    .line 925
    sget-object v1, Landroidx/appcompat/app/AppCompatDelegate;->sActivityDelegates:Landroidx/collection/ArraySet;

    new-instance v2, Ljava/lang/ref/WeakReference;

    invoke-direct {v2, p0}, Ljava/lang/ref/WeakReference;-><init>(Ljava/lang/Object;)V

    invoke-virtual {v1, v2}, Landroidx/collection/ArraySet;->add(Ljava/lang/Object;)Z

    .line 926
    monitor-exit v0

    return-void

    :catchall_0
    move-exception p0

    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw p0
.end method

.method public static create(Landroid/app/Activity;Landroidx/appcompat/app/AppCompatCallback;)Landroidx/appcompat/app/AppCompatDelegate;
    .locals 1

    .line 252
    new-instance v0, Landroidx/appcompat/app/AppCompatDelegateImpl;

    invoke-direct {v0, p0, p1}, Landroidx/appcompat/app/AppCompatDelegateImpl;-><init>(Landroid/app/Activity;Landroidx/appcompat/app/AppCompatCallback;)V

    return-object v0
.end method

.method public static create(Landroid/app/Dialog;Landroidx/appcompat/app/AppCompatCallback;)Landroidx/appcompat/app/AppCompatDelegate;
    .locals 1

    .line 263
    new-instance v0, Landroidx/appcompat/app/AppCompatDelegateImpl;

    invoke-direct {v0, p0, p1}, Landroidx/appcompat/app/AppCompatDelegateImpl;-><init>(Landroid/app/Dialog;Landroidx/appcompat/app/AppCompatCallback;)V

    return-object v0
.end method

.method public static getDefaultNightMode()I
    .locals 1

    .line 743
    sget v0, Landroidx/appcompat/app/AppCompatDelegate;->sDefaultNightMode:I

    return v0
.end method

.method static isAutoStorageOptedIn(Landroid/content/Context;)Z
    .locals 1

    .line 799
    sget-object v0, Landroidx/appcompat/app/AppCompatDelegate;->sIsAutoStoreLocalesOptedIn:Ljava/lang/Boolean;

    if-nez v0, :cond_0

    .line 801
    :try_start_0
    invoke-static {p0}, Landroidx/appcompat/app/AppLocalesMetadataHolderService;->getServiceInfo(Landroid/content/Context;)Landroid/content/pm/ServiceInfo;

    move-result-object p0

    .line 803
    iget-object p0, p0, Landroid/content/pm/ServiceInfo;->metaData:Landroid/os/Bundle;

    if-eqz p0, :cond_0

    const-string v0, "autoStoreLocales"

    .line 804
    invoke-virtual {p0, v0}, Landroid/os/Bundle;->getBoolean(Ljava/lang/String;)Z

    move-result p0

    invoke-static {p0}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object p0

    sput-object p0, Landroidx/appcompat/app/AppCompatDelegate;->sIsAutoStoreLocalesOptedIn:Ljava/lang/Boolean;
    :try_end_0
    .catch Landroid/content/pm/PackageManager$NameNotFoundException; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    const-string p0, "AppCompatDelegate"

    const-string v0, "Checking for metadata for AppLocalesMetadataHolderService : Service not found"

    .line 808
    invoke-static {p0, v0}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 810
    sget-object p0, Ljava/lang/Boolean;->FALSE:Ljava/lang/Boolean;

    sput-object p0, Landroidx/appcompat/app/AppCompatDelegate;->sIsAutoStoreLocalesOptedIn:Ljava/lang/Boolean;

    .line 813
    :cond_0
    :goto_0
    sget-object p0, Landroidx/appcompat/app/AppCompatDelegate;->sIsAutoStoreLocalesOptedIn:Ljava/lang/Boolean;

    invoke-virtual {p0}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p0

    return p0
.end method

.method static removeActivityDelegate(Landroidx/appcompat/app/AppCompatDelegate;)V
    .locals 1

    .line 930
    sget-object v0, Landroidx/appcompat/app/AppCompatDelegate;->sActivityDelegatesLock:Ljava/lang/Object;

    monitor-enter v0

    .line 932
    :try_start_0
    invoke-static {p0}, Landroidx/appcompat/app/AppCompatDelegate;->removeDelegateFromActives(Landroidx/appcompat/app/AppCompatDelegate;)V

    .line 933
    monitor-exit v0

    return-void

    :catchall_0
    move-exception p0

    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw p0
.end method

.method private static removeDelegateFromActives(Landroidx/appcompat/app/AppCompatDelegate;)V
    .locals 3

    .line 937
    sget-object v0, Landroidx/appcompat/app/AppCompatDelegate;->sActivityDelegatesLock:Ljava/lang/Object;

    monitor-enter v0

    .line 938
    :try_start_0
    sget-object v1, Landroidx/appcompat/app/AppCompatDelegate;->sActivityDelegates:Landroidx/collection/ArraySet;

    invoke-virtual {v1}, Landroidx/collection/ArraySet;->iterator()Ljava/util/Iterator;

    move-result-object v1

    .line 939
    :cond_0
    :goto_0
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_2

    .line 940
    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/ref/WeakReference;

    invoke-virtual {v2}, Ljava/lang/ref/WeakReference;->get()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Landroidx/appcompat/app/AppCompatDelegate;

    if-eq v2, p0, :cond_1

    if-nez v2, :cond_0

    .line 944
    :cond_1
    invoke-interface {v1}, Ljava/util/Iterator;->remove()V

    goto :goto_0

    .line 947
    :cond_2
    monitor-exit v0

    return-void

    :catchall_0
    move-exception p0

    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw p0
.end method

.method static syncRequestedAndStoredLocales(Landroid/content/Context;)V
    .locals 3

    .line 847
    invoke-static {}, Landroidx/core/os/BuildCompat;->isAtLeastT()Z

    move-result v0

    if-nez v0, :cond_5

    invoke-static {p0}, Landroidx/appcompat/app/AppCompatDelegate;->isAutoStorageOptedIn(Landroid/content/Context;)Z

    move-result v0

    if-nez v0, :cond_0

    goto :goto_1

    .line 850
    :cond_0
    sget-object v0, Landroidx/appcompat/app/AppCompatDelegate;->sAppLocalesStorageSyncLock:Ljava/lang/Object;

    monitor-enter v0

    .line 851
    :try_start_0
    sget-object v1, Landroidx/appcompat/app/AppCompatDelegate;->sRequestedAppLocales:Landroidx/core/os/LocaleListCompat;

    if-nez v1, :cond_3

    .line 852
    sget-object v1, Landroidx/appcompat/app/AppCompatDelegate;->sStoredAppLocales:Landroidx/core/os/LocaleListCompat;

    if-nez v1, :cond_1

    .line 854
    invoke-static {p0}, Landroidx/appcompat/app/AppLocalesStorageHelper;->readLocales(Landroid/content/Context;)Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Landroidx/core/os/LocaleListCompat;->forLanguageTags(Ljava/lang/String;)Landroidx/core/os/LocaleListCompat;

    move-result-object p0

    sput-object p0, Landroidx/appcompat/app/AppCompatDelegate;->sStoredAppLocales:Landroidx/core/os/LocaleListCompat;

    .line 856
    :cond_1
    sget-object p0, Landroidx/appcompat/app/AppCompatDelegate;->sStoredAppLocales:Landroidx/core/os/LocaleListCompat;

    invoke-virtual {p0}, Landroidx/core/os/LocaleListCompat;->isEmpty()Z

    move-result p0

    if-eqz p0, :cond_2

    .line 860
    monitor-exit v0

    return-void

    .line 862
    :cond_2
    sget-object p0, Landroidx/appcompat/app/AppCompatDelegate;->sStoredAppLocales:Landroidx/core/os/LocaleListCompat;

    sput-object p0, Landroidx/appcompat/app/AppCompatDelegate;->sRequestedAppLocales:Landroidx/core/os/LocaleListCompat;

    goto :goto_0

    .line 863
    :cond_3
    sget-object v2, Landroidx/appcompat/app/AppCompatDelegate;->sStoredAppLocales:Landroidx/core/os/LocaleListCompat;

    invoke-virtual {v1, v2}, Landroidx/core/os/LocaleListCompat;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_4

    .line 866
    sget-object v1, Landroidx/appcompat/app/AppCompatDelegate;->sRequestedAppLocales:Landroidx/core/os/LocaleListCompat;

    sput-object v1, Landroidx/appcompat/app/AppCompatDelegate;->sStoredAppLocales:Landroidx/core/os/LocaleListCompat;

    .line 867
    invoke-virtual {v1}, Landroidx/core/os/LocaleListCompat;->toLanguageTags()Ljava/lang/String;

    move-result-object v1

    invoke-static {p0, v1}, Landroidx/appcompat/app/AppLocalesStorageHelper;->persistLocales(Landroid/content/Context;Ljava/lang/String;)V

    .line 869
    :cond_4
    :goto_0
    monitor-exit v0

    return-void

    :catchall_0
    move-exception p0

    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw p0

    :cond_5
    :goto_1
    return-void
.end method


# virtual methods
.method public abstract addContentView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V
.end method

.method public attachBaseContext(Landroid/content/Context;)V
    .locals 0
    .annotation runtime Ljava/lang/Deprecated;
    .end annotation

    return-void
.end method

.method public attachBaseContext2(Landroid/content/Context;)Landroid/content/Context;
    .locals 0

    .line 417
    invoke-virtual {p0, p1}, Landroidx/appcompat/app/AppCompatDelegate;->attachBaseContext(Landroid/content/Context;)V

    return-object p1
.end method

.method public abstract findViewById(I)Landroid/view/View;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "<T:",
            "Landroid/view/View;",
            ">(I)TT;"
        }
    .end annotation
.end method

.method public getLocalNightMode()I
    .locals 0

    const/16 p0, -0x64

    return p0
.end method

.method public abstract getMenuInflater()Landroid/view/MenuInflater;
.end method

.method public abstract getSupportActionBar()Landroidx/appcompat/app/ActionBar;
.end method

.method public abstract installViewFactory()V
.end method

.method public abstract invalidateOptionsMenu()V
.end method

.method public abstract onConfigurationChanged(Landroid/content/res/Configuration;)V
.end method

.method public abstract onCreate(Landroid/os/Bundle;)V
.end method

.method public abstract onDestroy()V
.end method

.method public abstract onPostCreate(Landroid/os/Bundle;)V
.end method

.method public abstract onPostResume()V
.end method

.method public abstract onSaveInstanceState(Landroid/os/Bundle;)V
.end method

.method public abstract onStart()V
.end method

.method public abstract onStop()V
.end method

.method public abstract requestWindowFeature(I)Z
.end method

.method public abstract setContentView(I)V
.end method

.method public abstract setContentView(Landroid/view/View;)V
.end method

.method public abstract setContentView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V
.end method

.method public setTheme(I)V
    .locals 0

    return-void
.end method

.method public abstract setTitle(Ljava/lang/CharSequence;)V
.end method
