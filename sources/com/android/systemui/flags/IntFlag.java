package com.android.systemui.flags;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.systemui.flags.ParcelableFlag;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: Flag.kt */
/* loaded from: classes2.dex */
public final class IntFlag implements ParcelableFlag<Integer> {

    /* renamed from: default  reason: not valid java name */
    private final int f3default;
    private final int id;
    private final String name;
    private final String namespace;
    private final boolean overridden;
    private final boolean teamfood;
    public static final Companion Companion = new Companion(null);
    public static final Parcelable.Creator<IntFlag> CREATOR = new Parcelable.Creator<IntFlag>() { // from class: com.android.systemui.flags.IntFlag$Companion$CREATOR$1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IntFlag createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new IntFlag(parcel, null);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IntFlag[] newArray(int i) {
            return new IntFlag[i];
        }
    };

    public /* synthetic */ IntFlag(Parcel parcel, DefaultConstructorMarker defaultConstructorMarker) {
        this(parcel);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof IntFlag) {
            IntFlag intFlag = (IntFlag) obj;
            return getId() == intFlag.getId() && Intrinsics.areEqual(getName(), intFlag.getName()) && Intrinsics.areEqual(getNamespace(), intFlag.getNamespace()) && getDefault().intValue() == intFlag.getDefault().intValue() && getTeamfood() == intFlag.getTeamfood() && getOverridden() == intFlag.getOverridden();
        }
        return false;
    }

    public int hashCode() {
        int hashCode = ((((((Integer.hashCode(getId()) * 31) + getName().hashCode()) * 31) + getNamespace().hashCode()) * 31) + getDefault().hashCode()) * 31;
        boolean teamfood = getTeamfood();
        int i = teamfood;
        if (teamfood) {
            i = 1;
        }
        int i2 = (hashCode + i) * 31;
        boolean overridden = getOverridden();
        return i2 + (overridden ? 1 : overridden);
    }

    public String toString() {
        int id = getId();
        String name = getName();
        String namespace = getNamespace();
        Integer num = getDefault();
        boolean teamfood = getTeamfood();
        boolean overridden = getOverridden();
        return "IntFlag(id=" + id + ", name=" + name + ", namespace=" + namespace + ", default=" + num + ", teamfood=" + teamfood + ", overridden=" + overridden + ")";
    }

    public IntFlag(int i, String name, String namespace, int i2, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(namespace, "namespace");
        this.id = i;
        this.name = name;
        this.namespace = namespace;
        this.f3default = i2;
        this.teamfood = z;
        this.overridden = z2;
    }

    public /* synthetic */ IntFlag(int i, String str, String str2, int i2, boolean z, boolean z2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, str, str2, (i3 & 8) != 0 ? 0 : i2, (i3 & 16) != 0 ? false : z, (i3 & 32) != 0 ? false : z2);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return ParcelableFlag.DefaultImpls.describeContents(this);
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getNamespace() {
        return this.namespace;
    }

    public Integer getDefault() {
        return Integer.valueOf(this.f3default);
    }

    public boolean getTeamfood() {
        return this.teamfood;
    }

    public boolean getOverridden() {
        return this.overridden;
    }

    /* compiled from: Flag.kt */
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private IntFlag(android.os.Parcel r10) {
        /*
            r9 = this;
            int r1 = r10.readInt()
            java.lang.String r0 = r10.readString()
            java.lang.String r2 = ""
            if (r0 != 0) goto Le
            r3 = r2
            goto Lf
        Le:
            r3 = r0
        Lf:
            java.lang.String r0 = r10.readString()
            if (r0 != 0) goto L17
            r4 = r2
            goto L18
        L17:
            r4 = r0
        L18:
            int r10 = r10.readInt()
            r5 = 0
            r6 = 0
            r7 = 48
            r8 = 0
            r0 = r9
            r2 = r3
            r3 = r4
            r4 = r10
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.flags.IntFlag.<init>(android.os.Parcel):void");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        parcel.writeInt(getId());
        parcel.writeString(getName());
        parcel.writeString(getNamespace());
        parcel.writeInt(getDefault().intValue());
    }
}
