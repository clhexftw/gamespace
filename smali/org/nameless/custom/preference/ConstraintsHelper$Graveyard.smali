.class Lorg/nameless/custom/preference/ConstraintsHelper$Graveyard;
.super Ljava/lang/Object;
.source "ConstraintsHelper.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/nameless/custom/preference/ConstraintsHelper;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0xa
    name = "Graveyard"
.end annotation


# static fields
.field private static sInstance:Lorg/nameless/custom/preference/ConstraintsHelper$Graveyard;


# instance fields
.field private final mContext:Landroid/content/Context;

.field private mDeathRow:Ljava/util/Set;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Set<",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method private constructor <init>(Landroid/content/Context;)V
    .locals 1

    .line 346
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 340
    new-instance v0, Landroid/util/ArraySet;

    invoke-direct {v0}, Landroid/util/ArraySet;-><init>()V

    iput-object v0, p0, Lorg/nameless/custom/preference/ConstraintsHelper$Graveyard;->mDeathRow:Ljava/util/Set;

    .line 347
    iput-object p1, p0, Lorg/nameless/custom/preference/ConstraintsHelper$Graveyard;->mContext:Landroid/content/Context;

    return-void
.end method

.method public static declared-synchronized get(Landroid/content/Context;)Lorg/nameless/custom/preference/ConstraintsHelper$Graveyard;
    .locals 2

    const-class v0, Lorg/nameless/custom/preference/ConstraintsHelper$Graveyard;

    monitor-enter v0

    .line 351
    :try_start_0
    sget-object v1, Lorg/nameless/custom/preference/ConstraintsHelper$Graveyard;->sInstance:Lorg/nameless/custom/preference/ConstraintsHelper$Graveyard;

    if-nez v1, :cond_0

    .line 352
    new-instance v1, Lorg/nameless/custom/preference/ConstraintsHelper$Graveyard;

    invoke-direct {v1, p0}, Lorg/nameless/custom/preference/ConstraintsHelper$Graveyard;-><init>(Landroid/content/Context;)V

    sput-object v1, Lorg/nameless/custom/preference/ConstraintsHelper$Graveyard;->sInstance:Lorg/nameless/custom/preference/ConstraintsHelper$Graveyard;

    .line 354
    :cond_0
    sget-object p0, Lorg/nameless/custom/preference/ConstraintsHelper$Graveyard;->sInstance:Lorg/nameless/custom/preference/ConstraintsHelper$Graveyard;
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    monitor-exit v0

    return-object p0

    :catchall_0
    move-exception p0

    monitor-exit v0

    throw p0
.end method

.method private getParent(Landroidx/preference/Preference;Landroidx/preference/Preference;)Landroidx/preference/PreferenceGroup;
    .locals 0

    .line 370
    invoke-virtual {p1}, Landroidx/preference/Preference;->getPreferenceManager()Landroidx/preference/PreferenceManager;

    move-result-object p1

    invoke-virtual {p1}, Landroidx/preference/PreferenceManager;->getPreferenceScreen()Landroidx/preference/PreferenceScreen;

    move-result-object p1

    invoke-direct {p0, p1, p2}, Lorg/nameless/custom/preference/ConstraintsHelper$Graveyard;->getParent(Landroidx/preference/PreferenceGroup;Landroidx/preference/Preference;)Landroidx/preference/PreferenceGroup;

    move-result-object p0

    return-object p0
.end method

.method private getParent(Landroidx/preference/PreferenceGroup;Landroidx/preference/Preference;)Landroidx/preference/PreferenceGroup;
    .locals 3

    const/4 v0, 0x0

    .line 374
    :goto_0
    invoke-virtual {p1}, Landroidx/preference/PreferenceGroup;->getPreferenceCount()I

    move-result v1

    if-ge v0, v1, :cond_2

    .line 375
    invoke-virtual {p1, v0}, Landroidx/preference/PreferenceGroup;->getPreference(I)Landroidx/preference/Preference;

    move-result-object v1

    if-ne v1, p2, :cond_0

    return-object p1

    .line 378
    :cond_0
    const-class v2, Landroidx/preference/PreferenceGroup;

    invoke-virtual {v2, v1}, Ljava/lang/Class;->isInstance(Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_1

    .line 379
    check-cast v1, Landroidx/preference/PreferenceGroup;

    invoke-direct {p0, v1, p2}, Lorg/nameless/custom/preference/ConstraintsHelper$Graveyard;->getParent(Landroidx/preference/PreferenceGroup;Landroidx/preference/Preference;)Landroidx/preference/PreferenceGroup;

    move-result-object v1

    if-eqz v1, :cond_1

    return-object v1

    :cond_1
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    :cond_2
    const/4 p0, 0x0

    return-object p0
.end method

.method private hidePreference(Landroidx/preference/PreferenceManager;Landroidx/preference/Preference;)V
    .locals 1

    const/4 p1, 0x0

    .line 388
    invoke-virtual {p2, p1}, Landroidx/preference/Preference;->setVisible(Z)V

    .line 390
    invoke-direct {p0, p2, p2}, Lorg/nameless/custom/preference/ConstraintsHelper$Graveyard;->getParent(Landroidx/preference/Preference;Landroidx/preference/Preference;)Landroidx/preference/PreferenceGroup;

    move-result-object p0

    move p2, p1

    .line 392
    :goto_0
    invoke-virtual {p0}, Landroidx/preference/PreferenceGroup;->getPreferenceCount()I

    move-result v0

    if-ge p2, v0, :cond_1

    .line 393
    invoke-virtual {p0, p2}, Landroidx/preference/PreferenceGroup;->getPreference(I)Landroidx/preference/Preference;

    move-result-object v0

    invoke-virtual {v0}, Landroidx/preference/Preference;->isVisible()Z

    move-result v0

    if-eqz v0, :cond_0

    move p2, p1

    goto :goto_1

    :cond_0
    add-int/lit8 p2, p2, 0x1

    goto :goto_0

    :cond_1
    const/4 p2, 0x1

    :goto_1
    if-eqz p2, :cond_2

    .line 399
    invoke-virtual {p0, p1}, Landroidx/preference/Preference;->setVisible(Z)V

    :cond_2
    return-void
.end method


# virtual methods
.method public addTombstone(Ljava/lang/String;)V
    .locals 1

    .line 358
    iget-object v0, p0, Lorg/nameless/custom/preference/ConstraintsHelper$Graveyard;->mDeathRow:Ljava/util/Set;

    monitor-enter v0

    .line 359
    :try_start_0
    iget-object p0, p0, Lorg/nameless/custom/preference/ConstraintsHelper$Graveyard;->mDeathRow:Ljava/util/Set;

    invoke-interface {p0, p1}, Ljava/util/Set;->add(Ljava/lang/Object;)Z

    .line 360
    monitor-exit v0

    return-void

    :catchall_0
    move-exception p0

    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw p0
.end method

.method public addTombstones([Ljava/lang/String;)V
    .locals 1

    .line 364
    iget-object v0, p0, Lorg/nameless/custom/preference/ConstraintsHelper$Graveyard;->mDeathRow:Ljava/util/Set;

    monitor-enter v0

    .line 365
    :try_start_0
    iget-object p0, p0, Lorg/nameless/custom/preference/ConstraintsHelper$Graveyard;->mDeathRow:Ljava/util/Set;

    invoke-static {p1}, Ljava/util/Arrays;->asList([Ljava/lang/Object;)Ljava/util/List;

    move-result-object p1

    invoke-interface {p0, p1}, Ljava/util/Set;->addAll(Ljava/util/Collection;)Z

    .line 366
    monitor-exit v0

    return-void

    :catchall_0
    move-exception p0

    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw p0
.end method

.method public summonReaper(Landroidx/preference/PreferenceManager;)V
    .locals 5

    .line 404
    iget-object v0, p0, Lorg/nameless/custom/preference/ConstraintsHelper$Graveyard;->mDeathRow:Ljava/util/Set;

    monitor-enter v0

    .line 405
    :try_start_0
    new-instance v1, Landroid/util/ArraySet;

    invoke-direct {v1}, Landroid/util/ArraySet;-><init>()V

    .line 406
    iget-object v2, p0, Lorg/nameless/custom/preference/ConstraintsHelper$Graveyard;->mDeathRow:Ljava/util/Set;

    invoke-interface {v2}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v2

    :goto_0
    invoke-interface {v2}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    if-eqz v3, :cond_1

    invoke-interface {v2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/lang/String;

    .line 407
    invoke-virtual {p1, v3}, Landroidx/preference/PreferenceManager;->findPreference(Ljava/lang/CharSequence;)Landroidx/preference/Preference;

    move-result-object v4

    if-eqz v4, :cond_0

    .line 409
    invoke-direct {p0, p1, v4}, Lorg/nameless/custom/preference/ConstraintsHelper$Graveyard;->hidePreference(Landroidx/preference/PreferenceManager;Landroidx/preference/Preference;)V

    goto :goto_0

    .line 411
    :cond_0
    invoke-interface {v1, v3}, Ljava/util/Set;->add(Ljava/lang/Object;)Z

    goto :goto_0

    .line 414
    :cond_1
    iput-object v1, p0, Lorg/nameless/custom/preference/ConstraintsHelper$Graveyard;->mDeathRow:Ljava/util/Set;

    .line 415
    monitor-exit v0

    return-void

    :catchall_0
    move-exception p0

    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw p0
.end method
