.class public Lkotlin/jvm/internal/PropertyReference0Impl;
.super Lkotlin/jvm/internal/PropertyReference0;
.source "PropertyReference0Impl.java"


# direct methods
.method public constructor <init>(Ljava/lang/Object;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;I)V
    .locals 0

    .line 29
    invoke-direct/range {p0 .. p5}, Lkotlin/jvm/internal/PropertyReference0;-><init>(Ljava/lang/Object;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;I)V

    return-void
.end method


# virtual methods
.method public get()Ljava/lang/Object;
    .locals 1

    .line 34
    invoke-virtual {p0}, Lkotlin/jvm/internal/PropertyReference0;->getGetter()Lkotlin/reflect/KProperty0$Getter;

    move-result-object p0

    const/4 v0, 0x0

    new-array v0, v0, [Ljava/lang/Object;

    invoke-interface {p0, v0}, Lkotlin/reflect/KCallable;->call([Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method
