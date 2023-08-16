package com.google.android.material.badge;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Locale;
/* loaded from: classes.dex */
public final class BadgeState$State implements Parcelable {
    public static final Parcelable.Creator<BadgeState$State> CREATOR = new Parcelable.Creator<BadgeState$State>() { // from class: com.google.android.material.badge.BadgeState$State.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BadgeState$State createFromParcel(Parcel parcel) {
            return new BadgeState$State(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BadgeState$State[] newArray(int i) {
            return new BadgeState$State[i];
        }
    };
    private Integer additionalHorizontalOffset;
    private Integer additionalVerticalOffset;
    private int alpha;
    private Integer backgroundColor;
    private Integer badgeGravity;
    private int badgeResId;
    private Integer badgeTextColor;
    private CharSequence contentDescriptionNumberless;
    private int contentDescriptionQuantityStrings;
    private Integer horizontalOffsetWithText;
    private Integer horizontalOffsetWithoutText;
    private Boolean isVisible;
    private int maxCharacterCount;
    private int number;
    private Locale numberLocale;
    private Integer verticalOffsetWithText;
    private Integer verticalOffsetWithoutText;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public BadgeState$State() {
        this.alpha = 255;
        this.number = -2;
        this.maxCharacterCount = -2;
        this.isVisible = Boolean.TRUE;
    }

    BadgeState$State(Parcel parcel) {
        this.alpha = 255;
        this.number = -2;
        this.maxCharacterCount = -2;
        this.isVisible = Boolean.TRUE;
        this.badgeResId = parcel.readInt();
        this.backgroundColor = (Integer) parcel.readSerializable();
        this.badgeTextColor = (Integer) parcel.readSerializable();
        this.alpha = parcel.readInt();
        this.number = parcel.readInt();
        this.maxCharacterCount = parcel.readInt();
        this.contentDescriptionNumberless = parcel.readString();
        this.contentDescriptionQuantityStrings = parcel.readInt();
        this.badgeGravity = (Integer) parcel.readSerializable();
        this.horizontalOffsetWithoutText = (Integer) parcel.readSerializable();
        this.verticalOffsetWithoutText = (Integer) parcel.readSerializable();
        this.horizontalOffsetWithText = (Integer) parcel.readSerializable();
        this.verticalOffsetWithText = (Integer) parcel.readSerializable();
        this.additionalHorizontalOffset = (Integer) parcel.readSerializable();
        this.additionalVerticalOffset = (Integer) parcel.readSerializable();
        this.isVisible = (Boolean) parcel.readSerializable();
        this.numberLocale = (Locale) parcel.readSerializable();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.badgeResId);
        parcel.writeSerializable(this.backgroundColor);
        parcel.writeSerializable(this.badgeTextColor);
        parcel.writeInt(this.alpha);
        parcel.writeInt(this.number);
        parcel.writeInt(this.maxCharacterCount);
        CharSequence charSequence = this.contentDescriptionNumberless;
        parcel.writeString(charSequence == null ? null : charSequence.toString());
        parcel.writeInt(this.contentDescriptionQuantityStrings);
        parcel.writeSerializable(this.badgeGravity);
        parcel.writeSerializable(this.horizontalOffsetWithoutText);
        parcel.writeSerializable(this.verticalOffsetWithoutText);
        parcel.writeSerializable(this.horizontalOffsetWithText);
        parcel.writeSerializable(this.verticalOffsetWithText);
        parcel.writeSerializable(this.additionalHorizontalOffset);
        parcel.writeSerializable(this.additionalVerticalOffset);
        parcel.writeSerializable(this.isVisible);
        parcel.writeSerializable(this.numberLocale);
    }
}
