package com.android.settingslib.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHidDevice;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.util.Log;
import com.android.settingslib.R$string;
/* loaded from: classes2.dex */
public class HidDeviceProfile implements LocalBluetoothProfile {
    private final CachedBluetoothDeviceManager mDeviceManager;
    private boolean mIsProfileReady;
    private final LocalBluetoothProfileManager mProfileManager;
    private BluetoothHidDevice mService;

    @Override // com.android.settingslib.bluetooth.LocalBluetoothProfile
    public boolean accessProfileEnabled() {
        return true;
    }

    @Override // com.android.settingslib.bluetooth.LocalBluetoothProfile
    public int getConnectionPolicy(BluetoothDevice bluetoothDevice) {
        return -1;
    }

    @Override // com.android.settingslib.bluetooth.LocalBluetoothProfile
    public int getDrawableResource(BluetoothClass bluetoothClass) {
        return 17302340;
    }

    @Override // com.android.settingslib.bluetooth.LocalBluetoothProfile
    public int getOrdinal() {
        return 18;
    }

    @Override // com.android.settingslib.bluetooth.LocalBluetoothProfile
    public int getProfileId() {
        return 19;
    }

    public String toString() {
        return "HID DEVICE";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public HidDeviceProfile(Context context, CachedBluetoothDeviceManager cachedBluetoothDeviceManager, LocalBluetoothProfileManager localBluetoothProfileManager) {
        this.mDeviceManager = cachedBluetoothDeviceManager;
        this.mProfileManager = localBluetoothProfileManager;
        BluetoothAdapter.getDefaultAdapter().getProfileProxy(context, new HidDeviceServiceListener(), 19);
    }

    /* loaded from: classes2.dex */
    private final class HidDeviceServiceListener implements BluetoothProfile.ServiceListener {
        private HidDeviceServiceListener() {
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
            HidDeviceProfile.this.mService = (BluetoothHidDevice) bluetoothProfile;
            for (BluetoothDevice bluetoothDevice : HidDeviceProfile.this.mService.getConnectedDevices()) {
                CachedBluetoothDevice findDevice = HidDeviceProfile.this.mDeviceManager.findDevice(bluetoothDevice);
                if (findDevice == null) {
                    Log.w("HidDeviceProfile", "HidProfile found new device: " + bluetoothDevice);
                    findDevice = HidDeviceProfile.this.mDeviceManager.addDevice(bluetoothDevice);
                }
                Log.d("HidDeviceProfile", "Connection status changed: " + findDevice);
                findDevice.onProfileStateChanged(HidDeviceProfile.this, 2);
                findDevice.refresh();
            }
            HidDeviceProfile.this.mIsProfileReady = true;
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceDisconnected(int i) {
            HidDeviceProfile.this.mIsProfileReady = false;
        }
    }

    @Override // com.android.settingslib.bluetooth.LocalBluetoothProfile
    public boolean isProfileReady() {
        return this.mIsProfileReady;
    }

    @Override // com.android.settingslib.bluetooth.LocalBluetoothProfile
    public int getConnectionStatus(BluetoothDevice bluetoothDevice) {
        BluetoothHidDevice bluetoothHidDevice = this.mService;
        if (bluetoothHidDevice == null) {
            return 0;
        }
        return bluetoothHidDevice.getConnectionState(bluetoothDevice);
    }

    @Override // com.android.settingslib.bluetooth.LocalBluetoothProfile
    public boolean isEnabled(BluetoothDevice bluetoothDevice) {
        return getConnectionStatus(bluetoothDevice) != 0;
    }

    @Override // com.android.settingslib.bluetooth.LocalBluetoothProfile
    public boolean setEnabled(BluetoothDevice bluetoothDevice, boolean z) {
        if (z) {
            return false;
        }
        return this.mService.setConnectionPolicy(bluetoothDevice, 0);
    }

    @Override // com.android.settingslib.bluetooth.LocalBluetoothProfile
    public int getNameResource(BluetoothDevice bluetoothDevice) {
        return R$string.bluetooth_profile_hid;
    }

    protected void finalize() {
        Log.d("HidDeviceProfile", "finalize()");
        if (this.mService != null) {
            try {
                BluetoothAdapter.getDefaultAdapter().closeProfileProxy(19, this.mService);
                this.mService = null;
            } catch (Throwable th) {
                Log.w("HidDeviceProfile", "Error cleaning up HID proxy", th);
            }
        }
    }
}
