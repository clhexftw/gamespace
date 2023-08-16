package com.google.android.setupdesign;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
/* loaded from: classes.dex */
public class SetupWizardLayout$SavedState extends View.BaseSavedState {
    public static final Parcelable.Creator<SetupWizardLayout$SavedState> CREATOR = new Parcelable.Creator<SetupWizardLayout$SavedState>() { // from class: com.google.android.setupdesign.SetupWizardLayout$SavedState.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SetupWizardLayout$SavedState createFromParcel(Parcel parcel) {
            return new SetupWizardLayout$SavedState(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SetupWizardLayout$SavedState[] newArray(int i) {
            return new SetupWizardLayout$SavedState[i];
        }
    };
    boolean isProgressBarShown;

    public SetupWizardLayout$SavedState(Parcel parcel) {
        super(parcel);
        this.isProgressBarShown = false;
        this.isProgressBarShown = parcel.readInt() != 0;
    }

    @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.isProgressBarShown ? 1 : 0);
    }
}
