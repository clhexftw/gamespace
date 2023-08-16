package androidx.viewpager2.widget;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
/* loaded from: classes.dex */
class ViewPager2$SavedState extends View.BaseSavedState {
    public static final Parcelable.Creator<ViewPager2$SavedState> CREATOR = new Parcelable.ClassLoaderCreator<ViewPager2$SavedState>() { // from class: androidx.viewpager2.widget.ViewPager2$SavedState.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.ClassLoaderCreator
        public ViewPager2$SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
            return new ViewPager2$SavedState(parcel, classLoader);
        }

        @Override // android.os.Parcelable.Creator
        public ViewPager2$SavedState createFromParcel(Parcel parcel) {
            return createFromParcel(parcel, (ClassLoader) null);
        }

        @Override // android.os.Parcelable.Creator
        public ViewPager2$SavedState[] newArray(int i) {
            return new ViewPager2$SavedState[i];
        }
    };
    Parcelable mAdapterState;
    int mCurrentItem;
    int mRecyclerViewId;

    ViewPager2$SavedState(Parcel parcel, ClassLoader classLoader) {
        super(parcel, classLoader);
        readValues(parcel, classLoader);
    }

    private void readValues(Parcel parcel, ClassLoader classLoader) {
        this.mRecyclerViewId = parcel.readInt();
        this.mCurrentItem = parcel.readInt();
        this.mAdapterState = parcel.readParcelable(classLoader);
    }

    @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.mRecyclerViewId);
        parcel.writeInt(this.mCurrentItem);
        parcel.writeParcelable(this.mAdapterState, i);
    }
}
