package com.android.systemui.flags;

import android.os.Parcelable;
/* compiled from: Flag.kt */
/* loaded from: classes2.dex */
public interface ParcelableFlag<T> extends Parcelable {

    /* compiled from: Flag.kt */
    /* loaded from: classes2.dex */
    public static final class DefaultImpls {
        public static <T> int describeContents(ParcelableFlag<T> parcelableFlag) {
            return 0;
        }
    }
}
