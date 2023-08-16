.class final Lorg/nameless/gamespace/preferences/AppListPreferences$makeAddPref$2;
.super Lkotlin/jvm/internal/Lambda;
.source "AppListPreferences.kt"

# interfaces
.implements Lkotlin/jvm/functions/Function0;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lorg/nameless/gamespace/preferences/AppListPreferences;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x18
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lkotlin/jvm/internal/Lambda;",
        "Lkotlin/jvm/functions/Function0<",
        "Landroidx/preference/Preference;",
        ">;"
    }
.end annotation


# instance fields
.field final synthetic $context:Landroid/content/Context;

.field final synthetic this$0:Lorg/nameless/gamespace/preferences/AppListPreferences;


# direct methods
.method constructor <init>(Landroid/content/Context;Lorg/nameless/gamespace/preferences/AppListPreferences;)V
    .locals 0

    iput-object p1, p0, Lorg/nameless/gamespace/preferences/AppListPreferences$makeAddPref$2;->$context:Landroid/content/Context;

    iput-object p2, p0, Lorg/nameless/gamespace/preferences/AppListPreferences$makeAddPref$2;->this$0:Lorg/nameless/gamespace/preferences/AppListPreferences;

    const/4 p1, 0x0

    invoke-direct {p0, p1}, Lkotlin/jvm/internal/Lambda;-><init>(I)V

    return-void
.end method


# virtual methods
.method public final invoke()Landroidx/preference/Preference;
    .locals 3

    .line 54
    new-instance v0, Landroidx/preference/Preference;

    iget-object v1, p0, Lorg/nameless/gamespace/preferences/AppListPreferences$makeAddPref$2;->$context:Landroid/content/Context;

    invoke-direct {v0, v1}, Landroidx/preference/Preference;-><init>(Landroid/content/Context;)V

    iget-object v1, p0, Lorg/nameless/gamespace/preferences/AppListPreferences$makeAddPref$2;->$context:Landroid/content/Context;

    iget-object p0, p0, Lorg/nameless/gamespace/preferences/AppListPreferences$makeAddPref$2;->this$0:Lorg/nameless/gamespace/preferences/AppListPreferences;

    const v2, 0x7f120176

    .line 55
    invoke-virtual {v1, v2}, Landroid/content/Context;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Landroidx/preference/Preference;->setTitle(Ljava/lang/CharSequence;)V

    const-string v1, "add_game"

    .line 56
    invoke-virtual {v0, v1}, Landroidx/preference/Preference;->setKey(Ljava/lang/String;)V

    const v1, 0x7f0800a2

    .line 57
    invoke-virtual {v0, v1}, Landroidx/preference/Preference;->setIcon(I)V

    const/4 v1, 0x0

    .line 58
    invoke-virtual {v0, v1}, Landroidx/preference/Preference;->setPersistent(Z)V

    .line 59
    invoke-virtual {v0, p0}, Landroidx/preference/Preference;->setOnPreferenceClickListener(Landroidx/preference/Preference$OnPreferenceClickListener;)V

    return-object v0
.end method

.method public bridge synthetic invoke()Ljava/lang/Object;
    .locals 0

    .line 53
    invoke-virtual {p0}, Lorg/nameless/gamespace/preferences/AppListPreferences$makeAddPref$2;->invoke()Landroidx/preference/Preference;

    move-result-object p0

    return-object p0
.end method
