.class Lcom/google/android/setupcompat/portal/NotificationComponent$1;
.super Ljava/lang/Object;
.source "NotificationComponent.java"

# interfaces
.implements Landroid/os/Parcelable$Creator;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/google/android/setupcompat/portal/NotificationComponent;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Object;",
        "Landroid/os/Parcelable$Creator<",
        "Lcom/google/android/setupcompat/portal/NotificationComponent;",
        ">;"
    }
.end annotation


# direct methods
.method constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public createFromParcel(Landroid/os/Parcel;)Lcom/google/android/setupcompat/portal/NotificationComponent;
    .locals 0

    .line 68
    new-instance p0, Lcom/google/android/setupcompat/portal/NotificationComponent;

    invoke-direct {p0, p1}, Lcom/google/android/setupcompat/portal/NotificationComponent;-><init>(Landroid/os/Parcel;)V

    return-object p0
.end method

.method public bridge synthetic createFromParcel(Landroid/os/Parcel;)Ljava/lang/Object;
    .locals 0

    .line 65
    invoke-virtual {p0, p1}, Lcom/google/android/setupcompat/portal/NotificationComponent$1;->createFromParcel(Landroid/os/Parcel;)Lcom/google/android/setupcompat/portal/NotificationComponent;

    move-result-object p0

    return-object p0
.end method

.method public newArray(I)[Lcom/google/android/setupcompat/portal/NotificationComponent;
    .locals 0

    .line 73
    new-array p0, p1, [Lcom/google/android/setupcompat/portal/NotificationComponent;

    return-object p0
.end method

.method public bridge synthetic newArray(I)[Ljava/lang/Object;
    .locals 0

    .line 65
    invoke-virtual {p0, p1}, Lcom/google/android/setupcompat/portal/NotificationComponent$1;->newArray(I)[Lcom/google/android/setupcompat/portal/NotificationComponent;

    move-result-object p0

    return-object p0
.end method
