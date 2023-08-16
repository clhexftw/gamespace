package com.android.systemui.screenrecord;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface IRecordingCallback extends IInterface {
    void onRecordingEnd() throws RemoteException;

    void onRecordingStart() throws RemoteException;

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IRecordingCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, "com.android.systemui.screenrecord.IRecordingCallback");
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface("com.android.systemui.screenrecord.IRecordingCallback");
            }
            if (i == 1598968902) {
                parcel2.writeString("com.android.systemui.screenrecord.IRecordingCallback");
                return true;
            }
            if (i == 1) {
                onRecordingStart();
                parcel2.writeNoException();
            } else if (i == 2) {
                onRecordingEnd();
                parcel2.writeNoException();
            } else {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            return true;
        }
    }
}
