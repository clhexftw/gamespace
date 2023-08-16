package com.android.systemui.flags;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.systemui.flags.ParcelableFlag;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: Flag.kt */
/* loaded from: classes2.dex */
public final class LongFlag implements ParcelableFlag<Long> {

    /* renamed from: default  reason: not valid java name */
    private final long f4default;
    private final int id;
    private final String name;
    private final String namespace;
    private final boolean overridden;
    private final boolean teamfood;
    public static final Companion Companion = new Companion(null);
    public static final Parcelable.Creator<LongFlag> CREATOR = new Parcelable.Creator<LongFlag>() { // from class: com.android.systemui.flags.LongFlag$Companion$CREATOR$1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LongFlag createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new LongFlag(parcel, null);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LongFlag[] newArray(int i) {
            return new LongFlag[i];
        }
    };

    public /* synthetic */ LongFlag(Parcel parcel, DefaultConstructorMarker defaultConstructorMarker) {
        this(parcel);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof LongFlag) {
            LongFlag longFlag = (LongFlag) obj;
            return getId() == longFlag.getId() && getDefault().longValue() == longFlag.getDefault().longValue() && getTeamfood() == longFlag.getTeamfood() && Intrinsics.areEqual(getName(), longFlag.getName()) && Intrinsics.areEqual(getNamespace(), longFlag.getNamespace()) && getOverridden() == longFlag.getOverridden();
        }
        return false;
    }

    public int hashCode() {
        int hashCode = ((Integer.hashCode(getId()) * 31) + getDefault().hashCode()) * 31;
        boolean teamfood = getTeamfood();
        int i = teamfood;
        if (teamfood) {
            i = 1;
        }
        int hashCode2 = (((((hashCode + i) * 31) + getName().hashCode()) * 31) + getNamespace().hashCode()) * 31;
        boolean overridden = getOverridden();
        return hashCode2 + (overridden ? 1 : overridden);
    }

    public String toString() {
        int id = getId();
        Long l = getDefault();
        boolean teamfood = getTeamfood();
        String name = getName();
        String namespace = getNamespace();
        boolean overridden = getOverridden();
        return "LongFlag(id=" + id + ", default=" + l + ", teamfood=" + teamfood + ", name=" + name + ", namespace=" + namespace + ", overridden=" + overridden + ")";
    }

    public LongFlag(int i, long j, boolean z, String name, String namespace, boolean z2) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(namespace, "namespace");
        this.id = i;
        this.f4default = j;
        this.teamfood = z;
        this.name = name;
        this.namespace = namespace;
        this.overridden = z2;
    }

    public /* synthetic */ LongFlag(int i, long j, boolean z, String str, String str2, boolean z2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, (i2 & 2) != 0 ? 0L : j, (i2 & 4) != 0 ? false : z, str, str2, (i2 & 32) != 0 ? false : z2);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return ParcelableFlag.DefaultImpls.describeContents(this);
    }

    public int getId() {
        return this.id;
    }

    public Long getDefault() {
        return Long.valueOf(this.f4default);
    }

    public boolean getTeamfood() {
        return this.teamfood;
    }

    public String getName() {
        return this.name;
    }

    public String getNamespace() {
        return this.namespace;
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
    private LongFlag(android.os.Parcel r11) {
        /*
            r10 = this;
            int r1 = r11.readInt()
            java.lang.String r0 = r11.readString()
            java.lang.String r2 = ""
            if (r0 != 0) goto Le
            r5 = r2
            goto Lf
        Le:
            r5 = r0
        Lf:
            java.lang.String r0 = r11.readString()
            if (r0 != 0) goto L17
            r6 = r2
            goto L18
        L17:
            r6 = r0
        L18:
            long r2 = r11.readLong()
            r4 = 0
            r7 = 0
            r8 = 36
            r9 = 0
            r0 = r10
            r0.<init>(r1, r2, r4, r5, r6, r7, r8, r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.flags.LongFlag.<init>(android.os.Parcel):void");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        parcel.writeInt(getId());
        parcel.writeString(getName());
        parcel.writeString(getNamespace());
        parcel.writeLong(getDefault().longValue());
    }
}
