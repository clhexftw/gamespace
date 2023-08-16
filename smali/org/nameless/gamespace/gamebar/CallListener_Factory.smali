.class public final Lorg/nameless/gamespace/gamebar/CallListener_Factory;
.super Ljava/lang/Object;
.source "CallListener_Factory.java"

# interfaces
.implements Ljavax/inject/Provider;


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Object;",
        "Ljavax/inject/Provider;"
    }
.end annotation


# instance fields
.field private final appSettingsProvider:Ljavax/inject/Provider;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljavax/inject/Provider<",
            "Lorg/nameless/gamespace/data/AppSettings;",
            ">;"
        }
    .end annotation
.end field

.field private final contextProvider:Ljavax/inject/Provider;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljavax/inject/Provider<",
            "Landroid/content/Context;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method public constructor <init>(Ljavax/inject/Provider;Ljavax/inject/Provider;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljavax/inject/Provider<",
            "Landroid/content/Context;",
            ">;",
            "Ljavax/inject/Provider<",
            "Lorg/nameless/gamespace/data/AppSettings;",
            ">;)V"
        }
    .end annotation

    .line 25
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 26
    iput-object p1, p0, Lorg/nameless/gamespace/gamebar/CallListener_Factory;->contextProvider:Ljavax/inject/Provider;

    .line 27
    iput-object p2, p0, Lorg/nameless/gamespace/gamebar/CallListener_Factory;->appSettingsProvider:Ljavax/inject/Provider;

    return-void
.end method

.method public static create(Ljavax/inject/Provider;Ljavax/inject/Provider;)Lorg/nameless/gamespace/gamebar/CallListener_Factory;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljavax/inject/Provider<",
            "Landroid/content/Context;",
            ">;",
            "Ljavax/inject/Provider<",
            "Lorg/nameless/gamespace/data/AppSettings;",
            ">;)",
            "Lorg/nameless/gamespace/gamebar/CallListener_Factory;"
        }
    .end annotation

    .line 37
    new-instance v0, Lorg/nameless/gamespace/gamebar/CallListener_Factory;

    invoke-direct {v0, p0, p1}, Lorg/nameless/gamespace/gamebar/CallListener_Factory;-><init>(Ljavax/inject/Provider;Ljavax/inject/Provider;)V

    return-object v0
.end method

.method public static newInstance(Landroid/content/Context;Lorg/nameless/gamespace/data/AppSettings;)Lorg/nameless/gamespace/gamebar/CallListener;
    .locals 1

    .line 41
    new-instance v0, Lorg/nameless/gamespace/gamebar/CallListener;

    invoke-direct {v0, p0, p1}, Lorg/nameless/gamespace/gamebar/CallListener;-><init>(Landroid/content/Context;Lorg/nameless/gamespace/data/AppSettings;)V

    return-object v0
.end method


# virtual methods
.method public bridge synthetic get()Ljava/lang/Object;
    .locals 0

    .line 10
    invoke-virtual {p0}, Lorg/nameless/gamespace/gamebar/CallListener_Factory;->get()Lorg/nameless/gamespace/gamebar/CallListener;

    move-result-object p0

    return-object p0
.end method

.method public get()Lorg/nameless/gamespace/gamebar/CallListener;
    .locals 1

    .line 32
    iget-object v0, p0, Lorg/nameless/gamespace/gamebar/CallListener_Factory;->contextProvider:Ljavax/inject/Provider;

    invoke-interface {v0}, Ljavax/inject/Provider;->get()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/content/Context;

    iget-object p0, p0, Lorg/nameless/gamespace/gamebar/CallListener_Factory;->appSettingsProvider:Ljavax/inject/Provider;

    invoke-interface {p0}, Ljavax/inject/Provider;->get()Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lorg/nameless/gamespace/data/AppSettings;

    invoke-static {v0, p0}, Lorg/nameless/gamespace/gamebar/CallListener_Factory;->newInstance(Landroid/content/Context;Lorg/nameless/gamespace/data/AppSettings;)Lorg/nameless/gamespace/gamebar/CallListener;

    move-result-object p0

    return-object p0
.end method
