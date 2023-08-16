package android.hardware.dumpstate;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface IDumpstateDevice extends IInterface {
    public static final String DESCRIPTOR = "android$hardware$dumpstate$IDumpstateDevice".replace('$', '.');

    boolean getVerboseLoggingEnabled() throws RemoteException;

    void setVerboseLoggingEnabled(boolean z) throws RemoteException;

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IDumpstateDevice {
        public static IDumpstateDevice asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IDumpstateDevice.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IDumpstateDevice)) {
                return (IDumpstateDevice) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        /* loaded from: classes.dex */
        private static class Proxy implements IDumpstateDevice {
            private IBinder mRemote;
            private int mCachedVersion = -1;
            private String mCachedHash = "-1";

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // android.hardware.dumpstate.IDumpstateDevice
            public boolean getVerboseLoggingEnabled() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IDumpstateDevice.DESCRIPTOR);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0)) {
                        throw new RemoteException("Method getVerboseLoggingEnabled is unimplemented.");
                    }
                    obtain2.readException();
                    return obtain2.readBoolean();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // android.hardware.dumpstate.IDumpstateDevice
            public void setVerboseLoggingEnabled(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IDumpstateDevice.DESCRIPTOR);
                    obtain.writeBoolean(z);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0)) {
                        throw new RemoteException("Method setVerboseLoggingEnabled is unimplemented.");
                    }
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
