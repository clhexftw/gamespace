.class public final Ldagger/hilt/android/internal/modules/ApplicationContextModule;
.super Ljava/lang/Object;
.source "ApplicationContextModule.java"


# instance fields
.field private final applicationContext:Landroid/content/Context;


# direct methods
.method public constructor <init>(Landroid/content/Context;)V
    .locals 0

    .line 33
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 34
    iput-object p1, p0, Ldagger/hilt/android/internal/modules/ApplicationContextModule;->applicationContext:Landroid/content/Context;

    return-void
.end method


# virtual methods
.method provideContext()Landroid/content/Context;
    .locals 0

    .line 40
    iget-object p0, p0, Ldagger/hilt/android/internal/modules/ApplicationContextModule;->applicationContext:Landroid/content/Context;

    return-object p0
.end method
