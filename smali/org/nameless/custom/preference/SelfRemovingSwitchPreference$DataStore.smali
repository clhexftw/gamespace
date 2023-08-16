.class Lorg/nameless/custom/preference/SelfRemovingSwitchPreference$DataStore;
.super Landroidx/preference/PreferenceDataStore;
.source "SelfRemovingSwitchPreference.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/nameless/custom/preference/SelfRemovingSwitchPreference;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x2
    name = "DataStore"
.end annotation


# instance fields
.field final synthetic this$0:Lorg/nameless/custom/preference/SelfRemovingSwitchPreference;


# direct methods
.method private constructor <init>(Lorg/nameless/custom/preference/SelfRemovingSwitchPreference;)V
    .locals 0

    .line 94
    iput-object p1, p0, Lorg/nameless/custom/preference/SelfRemovingSwitchPreference$DataStore;->this$0:Lorg/nameless/custom/preference/SelfRemovingSwitchPreference;

    invoke-direct {p0}, Landroidx/preference/PreferenceDataStore;-><init>()V

    return-void
.end method

.method synthetic constructor <init>(Lorg/nameless/custom/preference/SelfRemovingSwitchPreference;Lorg/nameless/custom/preference/SelfRemovingSwitchPreference$DataStore-IA;)V
    .locals 0

    invoke-direct {p0, p1}, Lorg/nameless/custom/preference/SelfRemovingSwitchPreference$DataStore;-><init>(Lorg/nameless/custom/preference/SelfRemovingSwitchPreference;)V

    return-void
.end method


# virtual methods
.method public getBoolean(Ljava/lang/String;Z)Z
    .locals 0

    .line 102
    iget-object p0, p0, Lorg/nameless/custom/preference/SelfRemovingSwitchPreference$DataStore;->this$0:Lorg/nameless/custom/preference/SelfRemovingSwitchPreference;

    invoke-virtual {p0, p1, p2}, Lorg/nameless/custom/preference/SelfRemovingSwitchPreference;->getBoolean(Ljava/lang/String;Z)Z

    move-result p0

    return p0
.end method

.method public putBoolean(Ljava/lang/String;Z)V
    .locals 0

    .line 97
    iget-object p0, p0, Lorg/nameless/custom/preference/SelfRemovingSwitchPreference$DataStore;->this$0:Lorg/nameless/custom/preference/SelfRemovingSwitchPreference;

    invoke-virtual {p0, p1, p2}, Lorg/nameless/custom/preference/SelfRemovingSwitchPreference;->putBoolean(Ljava/lang/String;Z)V

    return-void
.end method
