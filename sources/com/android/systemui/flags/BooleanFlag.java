package com.android.systemui.flags;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.systemui.flags.ParcelableFlag;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: Flag.kt */
/* loaded from: classes2.dex */
public abstract class BooleanFlag implements ParcelableFlag<Boolean> {

    /* renamed from: default  reason: not valid java name */
    private final boolean f0default;
    private final int id;
    private final String name;
    private final String namespace;
    private final boolean overridden;
    private final boolean teamfood;
    public static final Companion Companion = new Companion(null);
    public static final Parcelable.Creator<BooleanFlag> CREATOR = new Parcelable.Creator<BooleanFlag>() { // from class: com.android.systemui.flags.BooleanFlag$Companion$CREATOR$1
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public BooleanFlag createFromParcel2(final Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new BooleanFlag(parcel) { // from class: com.android.systemui.flags.BooleanFlag$Companion$CREATOR$1$createFromParcel$1
            };
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BooleanFlag[] newArray(int i) {
            return new BooleanFlag[i];
        }
    };

    public /* synthetic */ BooleanFlag(Parcel parcel, DefaultConstructorMarker defaultConstructorMarker) {
        this(parcel);
    }

    public BooleanFlag(int i, String name, String namespace, boolean z, boolean z2, boolean z3) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(namespace, "namespace");
        this.id = i;
        this.name = name;
        this.namespace = namespace;
        this.f0default = z;
        this.teamfood = z2;
        this.overridden = z3;
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

    public Boolean getDefault() {
        return Boolean.valueOf(this.f0default);
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
    private BooleanFlag(android.os.Parcel r8) {
        /*
            r7 = this;
            int r1 = r8.readInt()
            java.lang.String r0 = r8.readString()
            java.lang.String r2 = ""
            if (r0 != 0) goto Le
            r3 = r2
            goto Lf
        Le:
            r3 = r0
        Lf:
            java.lang.String r0 = r8.readString()
            if (r0 != 0) goto L17
            r4 = r2
            goto L18
        L17:
            r4 = r0
        L18:
            boolean r5 = r8.readBoolean()
            boolean r6 = r8.readBoolean()
            boolean r8 = r8.readBoolean()
            r0 = r7
            r2 = r3
            r3 = r4
            r4 = r5
            r5 = r6
            r6 = r8
            r0.<init>(r1, r2, r3, r4, r5, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.flags.BooleanFlag.<init>(android.os.Parcel):void");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        parcel.writeInt(getId());
        parcel.writeString(getName());
        parcel.writeString(getNamespace());
        parcel.writeBoolean(getDefault().booleanValue());
        parcel.writeBoolean(getTeamfood());
        parcel.writeBoolean(getOverridden());
    }
}
