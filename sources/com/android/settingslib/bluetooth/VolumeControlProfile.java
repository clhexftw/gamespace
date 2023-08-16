package com.android.settingslib.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.BluetoothVolumeControl;
import android.content.Context;
import android.util.Log;
import java.util.List;
/* loaded from: classes2.dex */
public class VolumeControlProfile implements LocalBluetoothProfile {
    private static boolean DEBUG = true;
    private Context mContext;
    private final CachedBluetoothDeviceManager mDeviceManager;
    private boolean mIsProfileReady;
    private final LocalBluetoothProfileManager mProfileManager;
    private BluetoothVolumeControl mService;

    @Override // com.android.settingslib.bluetooth.LocalBluetoothProfile
    public boolean accessProfileEnabled() {
        return false;
    }

    @Override // com.android.settingslib.bluetooth.LocalBluetoothProfile
    public int getDrawableResource(BluetoothClass bluetoothClass) {
        return 0;
    }

    @Override // com.android.settingslib.bluetooth.LocalBluetoothProfile
    public int getNameResource(BluetoothDevice bluetoothDevice) {
        return 0;
    }

    @Override // com.android.settingslib.bluetooth.LocalBluetoothProfile
    public int getOrdinal() {
        return 1;
    }

    @Override // com.android.settingslib.bluetooth.LocalBluetoothProfile
    public int getProfileId() {
        return 23;
    }

    public String toString() {
        return "VCP";
    }

    /* loaded from: classes2.dex */
    private final class VolumeControlProfileServiceListener implements BluetoothProfile.ServiceListener {
        private VolumeControlProfileServiceListener() {
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
            if (VolumeControlProfile.DEBUG) {
                Log.d("VolumeControlProfile", "Bluetooth service connected");
            }
            VolumeControlProfile.this.mService = (BluetoothVolumeControl) bluetoothProfile;
            List connectedDevices = VolumeControlProfile.this.mService.getConnectedDevices();
            while (!connectedDevices.isEmpty()) {
                BluetoothDevice bluetoothDevice = (BluetoothDevice) connectedDevices.remove(0);
                CachedBluetoothDevice findDevice = VolumeControlProfile.this.mDeviceManager.findDevice(bluetoothDevice);
                if (findDevice == null) {
                    if (VolumeControlProfile.DEBUG) {
                        Log.d("VolumeControlProfile", "VolumeControlProfile found new device: " + bluetoothDevice);
                    }
                    findDevice = VolumeControlProfile.this.mDeviceManager.addDevice(bluetoothDevice);
                }
                findDevice.onProfileStateChanged(VolumeControlProfile.this, 2);
                findDevice.refresh();
            }
            VolumeControlProfile.this.mProfileManager.callServiceConnectedListeners();
            VolumeControlProfile.this.mIsProfileReady = true;
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceDisconnected(int i) {
            if (VolumeControlProfile.DEBUG) {
                Log.d("VolumeControlProfile", "Bluetooth service disconnected");
            }
            VolumeControlProfile.this.mProfileManager.callServiceDisconnectedListeners();
            VolumeControlProfile.this.mIsProfileReady = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public VolumeControlProfile(Context context, CachedBluetoothDeviceManager cachedBluetoothDeviceManager, LocalBluetoothProfileManager localBluetoothProfileManager) {
        this.mContext = context;
        this.mDeviceManager = cachedBluetoothDeviceManager;
        this.mProfileManager = localBluetoothProfileManager;
        BluetoothAdapter.getDefaultAdapter().getProfileProxy(context, new VolumeControlProfileServiceListener(), 23);
    }

    @Override // com.android.settingslib.bluetooth.LocalBluetoothProfile
    public int getConnectionStatus(BluetoothDevice bluetoothDevice) {
        BluetoothVolumeControl bluetoothVolumeControl = this.mService;
        if (bluetoothVolumeControl == null) {
            return 0;
        }
        return bluetoothVolumeControl.getConnectionState(bluetoothDevice);
    }

    @Override // com.android.settingslib.bluetooth.LocalBluetoothProfile
    public boolean isEnabled(BluetoothDevice bluetoothDevice) {
        BluetoothVolumeControl bluetoothVolumeControl = this.mService;
        return (bluetoothVolumeControl == null || bluetoothDevice == null || bluetoothVolumeControl.getConnectionPolicy(bluetoothDevice) <= 0) ? false : true;
    }

    @Override // com.android.settingslib.bluetooth.LocalBluetoothProfile
    public int getConnectionPolicy(BluetoothDevice bluetoothDevice) {
        BluetoothVolumeControl bluetoothVolumeControl = this.mService;
        if (bluetoothVolumeControl == null || bluetoothDevice == null) {
            return 0;
        }
        return bluetoothVolumeControl.getConnectionPolicy(bluetoothDevice);
    }

    @Override // com.android.settingslib.bluetooth.LocalBluetoothProfile
    public boolean setEnabled(BluetoothDevice bluetoothDevice, boolean z) {
        if (this.mService == null || bluetoothDevice == null) {
            return false;
        }
        if (DEBUG) {
            Log.d("VolumeControlProfile", bluetoothDevice.getAnonymizedAddress() + " setEnabled: " + z);
        }
        if (z) {
            if (this.mService.getConnectionPolicy(bluetoothDevice) < 100) {
                return this.mService.setConnectionPolicy(bluetoothDevice, 100);
            }
            return false;
        }
        return this.mService.setConnectionPolicy(bluetoothDevice, 0);
    }

    @Override // com.android.settingslib.bluetooth.LocalBluetoothProfile
    public boolean isProfileReady() {
        return this.mIsProfileReady;
    }
}
