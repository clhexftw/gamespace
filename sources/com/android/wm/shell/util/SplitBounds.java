package com.android.wm.shell.util;

import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;
/* loaded from: classes2.dex */
public class SplitBounds implements Parcelable {
    public static final Parcelable.Creator<SplitBounds> CREATOR = new Parcelable.Creator<SplitBounds>() { // from class: com.android.wm.shell.util.SplitBounds.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SplitBounds createFromParcel(Parcel parcel) {
            return new SplitBounds(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SplitBounds[] newArray(int i) {
            return new SplitBounds[i];
        }
    };
    public final boolean appsStackedVertically;
    public final float dividerHeightPercent;
    public final float dividerWidthPercent;
    public final float leftTaskPercent;
    public final Rect leftTopBounds;
    public final int leftTopTaskId;
    public final Rect rightBottomBounds;
    public final int rightBottomTaskId;
    public final float topTaskPercent;
    public final Rect visualDividerBounds;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public SplitBounds(Parcel parcel) {
        this.leftTopBounds = (Rect) parcel.readTypedObject(Rect.CREATOR);
        this.rightBottomBounds = (Rect) parcel.readTypedObject(Rect.CREATOR);
        this.visualDividerBounds = (Rect) parcel.readTypedObject(Rect.CREATOR);
        this.topTaskPercent = parcel.readFloat();
        this.leftTaskPercent = parcel.readFloat();
        this.appsStackedVertically = parcel.readBoolean();
        this.leftTopTaskId = parcel.readInt();
        this.rightBottomTaskId = parcel.readInt();
        this.dividerWidthPercent = parcel.readInt();
        this.dividerHeightPercent = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedObject(this.leftTopBounds, i);
        parcel.writeTypedObject(this.rightBottomBounds, i);
        parcel.writeTypedObject(this.visualDividerBounds, i);
        parcel.writeFloat(this.topTaskPercent);
        parcel.writeFloat(this.leftTaskPercent);
        parcel.writeBoolean(this.appsStackedVertically);
        parcel.writeInt(this.leftTopTaskId);
        parcel.writeInt(this.rightBottomTaskId);
        parcel.writeFloat(this.dividerWidthPercent);
        parcel.writeFloat(this.dividerHeightPercent);
    }

    public boolean equals(Object obj) {
        if (obj instanceof SplitBounds) {
            SplitBounds splitBounds = (SplitBounds) obj;
            return Objects.equals(this.leftTopBounds, splitBounds.leftTopBounds) && Objects.equals(this.rightBottomBounds, splitBounds.rightBottomBounds) && this.leftTopTaskId == splitBounds.leftTopTaskId && this.rightBottomTaskId == splitBounds.rightBottomTaskId;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(this.leftTopBounds, this.rightBottomBounds, Integer.valueOf(this.leftTopTaskId), Integer.valueOf(this.rightBottomTaskId));
    }

    public String toString() {
        return "LeftTop: " + this.leftTopBounds + ", taskId: " + this.leftTopTaskId + "\nRightBottom: " + this.rightBottomBounds + ", taskId: " + this.rightBottomTaskId + "\nDivider: " + this.visualDividerBounds + "\nAppsVertical? " + this.appsStackedVertically;
    }
}
