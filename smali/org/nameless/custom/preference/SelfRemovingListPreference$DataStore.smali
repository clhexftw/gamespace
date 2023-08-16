.class Lorg/nameless/custom/preference/SelfRemovingListPreference$DataStore;
.super Landroidx/preference/PreferenceDataStore;
.source "SelfRemovingListPreference.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/nameless/custom/preference/SelfRemovingListPreference;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x2
    name = "DataStore"
.end annotation


# instance fields
.field final synthetic this$0:Lorg/nameless/custom/preference/SelfRemovingListPreference;


# direct methods
.method private constructor <init>(Lorg/nameless/custom/preference/SelfRemovingListPreference;)V
    .locals 0

    .line 95
    iput-object p1, p0, Lorg/nameless/custom/preference/SelfRemovingListPreference$DataStore;->this$0:Lorg/nameless/custom/preference/SelfRemovingListPreference;

    invoke-direct {p0}, Landroidx/preference/PreferenceDataStore;-><init>()V

    return-void
.end method

.method synthetic constructor <init>(Lorg/nameless/custom/preference/SelfRemovingListPreference;Lorg/nameless/custom/preference/SelfRemovingListPreference$DataStore-IA;)V
    .locals 0

    invoke-direct {p0, p1}, Lorg/nameless/custom/preference/SelfRemovingListPreference$DataStore;-><init>(Lorg/nameless/custom/preference/SelfRemovingListPreference;)V

    return-void
.end method


# virtual methods
.method public getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    .locals 0

    .line 103
    iget-object p0, p0, Lorg/nameless/custom/preference/SelfRemovingListPreference$DataStore;->this$0:Lorg/nameless/custom/preference/SelfRemovingListPreference;

    invoke-virtual {p0, p1, p2}, Lorg/nameless/custom/preference/SelfRemovingListPreference;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public putString(Ljava/lang/String;Ljava/lang/String;)V
    .locals 0

    .line 98
    iget-object p0, p0, Lorg/nameless/custom/preference/SelfRemovingListPreference$DataStore;->this$0:Lorg/nameless/custom/preference/SelfRemovingListPreference;

    invoke-virtual {p0, p1, p2}, Lorg/nameless/custom/preference/SelfRemovingListPreference;->putString(Ljava/lang/String;Ljava/lang/String;)V

    return-void
.end method
