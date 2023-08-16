package androidx.swiperefreshlayout.widget;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
/* loaded from: classes.dex */
class SwipeRefreshLayout$SavedState extends View.BaseSavedState {
    public static final Parcelable.Creator<SwipeRefreshLayout$SavedState> CREATOR = new Parcelable.Creator<SwipeRefreshLayout$SavedState>() { // from class: androidx.swiperefreshlayout.widget.SwipeRefreshLayout$SavedState.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SwipeRefreshLayout$SavedState createFromParcel(Parcel parcel) {
            return new SwipeRefreshLayout$SavedState(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SwipeRefreshLayout$SavedState[] newArray(int i) {
            return new SwipeRefreshLayout$SavedState[i];
        }
    };
    final boolean mRefreshing;

    SwipeRefreshLayout$SavedState(Parcel parcel) {
        super(parcel);
        this.mRefreshing = parcel.readByte() != 0;
    }

    @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeByte(this.mRefreshing ? (byte) 1 : (byte) 0);
    }
}
