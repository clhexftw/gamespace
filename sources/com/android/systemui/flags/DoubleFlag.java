package com.android.systemui.flags;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.systemui.flags.ParcelableFlag;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: Flag.kt */
/* loaded from: classes2.dex */
public final class DoubleFlag implements ParcelableFlag<Double> {

    /* renamed from: default  reason: not valid java name */
    private final double f1default;
    private final int id;
    private final String name;
    private final String namespace;
    private final boolean overridden;
    private final boolean teamfood;
    public static final Companion Companion = new Companion(null);
    public static final Parcelable.Creator<DoubleFlag> CREATOR = new Parcelable.Creator<DoubleFlag>() { // from class: com.android.systemui.flags.DoubleFlag$Companion$CREATOR$1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DoubleFlag createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new DoubleFlag(parcel, null);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DoubleFlag[] newArray(int i) {
            return new DoubleFlag[i];
        }
    };

    public /* synthetic */ DoubleFlag(Parcel parcel, DefaultConstructorMarker defaultConstructorMarker) {
        this(parcel);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DoubleFlag) {
            DoubleFlag doubleFlag = (DoubleFlag) obj;
            return getId() == doubleFlag.getId() && Intrinsics.areEqual(getName(), doubleFlag.getName()) && Intrinsics.areEqual(getNamespace(), doubleFlag.getNamespace()) && Double.compare(getDefault().doubleValue(), doubleFlag.getDefault().doubleValue()) == 0 && getTeamfood() == doubleFlag.getTeamfood() && getOverridden() == doubleFlag.getOverridden();
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
        Double d = getDefault();
        boolean teamfood = getTeamfood();
        boolean overridden = getOverridden();
        return "DoubleFlag(id=" + id + ", name=" + name + ", namespace=" + namespace + ", default=" + d + ", teamfood=" + teamfood + ", overridden=" + overridden + ")";
    }

    public DoubleFlag(int i, String name, String namespace, double d, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(namespace, "namespace");
        this.id = i;
        this.name = name;
        this.namespace = namespace;
        this.f1default = d;
        this.teamfood = z;
        this.overridden = z2;
    }

    public /* synthetic */ DoubleFlag(int i, String str, String str2, double d, boolean z, boolean z2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, str, str2, (i2 & 8) != 0 ? 0.0d : d, (i2 & 16) != 0 ? false : z, (i2 & 32) != 0 ? false : z2);
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

    public Double getDefault() {
        return Double.valueOf(this.f1default);
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
    private DoubleFlag(android.os.Parcel r11) {
        /*
            r10 = this;
            int r1 = r11.readInt()
            java.lang.String r0 = r11.readString()
            java.lang.String r2 = ""
            if (r0 != 0) goto Le
            r3 = r2
            goto Lf
        Le:
            r3 = r0
        Lf:
            java.lang.String r0 = r11.readString()
            if (r0 != 0) goto L17
            r4 = r2
            goto L18
        L17:
            r4 = r0
        L18:
            double r5 = r11.readDouble()
            r11 = 0
            r7 = 0
            r8 = 48
            r9 = 0
            r0 = r10
            r2 = r3
            r3 = r4
            r4 = r5
            r6 = r11
            r0.<init>(r1, r2, r3, r4, r6, r7, r8, r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.flags.DoubleFlag.<init>(android.os.Parcel):void");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        parcel.writeInt(getId());
        parcel.writeString(getName());
        parcel.writeString(getNamespace());
        parcel.writeDouble(getDefault().doubleValue());
    }
}
