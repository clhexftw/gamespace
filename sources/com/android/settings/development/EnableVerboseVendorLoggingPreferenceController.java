package com.android.settings.development;

import android.content.Context;
import android.hardware.dumpstate.IDumpstateDevice;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Log;
import androidx.preference.Preference;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settingslib.development.DeveloperOptionsPreferenceController;
import java.util.NoSuchElementException;
import org.nameless.custom.preference.SwitchPreference;
/* loaded from: classes.dex */
public class EnableVerboseVendorLoggingPreferenceController extends DeveloperOptionsPreferenceController implements Preference.OnPreferenceChangeListener, PreferenceControllerMixin {
    private static final boolean DBG = Log.isLoggable("EnableVerboseVendorLoggingPreferenceController", 3);
    private static final String DUMP_STATE_AIDL_SERVICE_NAME = IDumpstateDevice.DESCRIPTOR + "/default";
    private int mDumpstateHalVersion;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "enable_verbose_vendor_logging";
    }

    public EnableVerboseVendorLoggingPreferenceController(Context context) {
        super(context);
        this.mDumpstateHalVersion = -1;
    }

    @Override // com.android.settingslib.development.DeveloperOptionsPreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return isIDumpstateDeviceAidlServiceAvailable() || isIDumpstateDeviceV1_1ServiceAvailable();
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        setVerboseLoggingEnabled(((Boolean) obj).booleanValue());
        return true;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        ((SwitchPreference) this.mPreference).setChecked(getVerboseLoggingEnabled());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settingslib.development.DeveloperOptionsPreferenceController
    public void onDeveloperOptionsSwitchDisabled() {
        super.onDeveloperOptionsSwitchDisabled();
        setVerboseLoggingEnabled(false);
        ((SwitchPreference) this.mPreference).setChecked(false);
    }

    boolean isIDumpstateDeviceV1_1ServiceAvailable() {
        android.hardware.dumpstate.V1_0.IDumpstateDevice dumpstateDeviceService = getDumpstateDeviceService();
        if (dumpstateDeviceService == null && DBG) {
            Log.d("EnableVerboseVendorLoggingPreferenceController", "IDumpstateDevice v1.1 service is not available.");
        }
        return dumpstateDeviceService != null && this.mDumpstateHalVersion == 1;
    }

    boolean isIDumpstateDeviceAidlServiceAvailable() {
        return getDumpstateDeviceAidlService() != null;
    }

    void setVerboseLoggingEnabled(boolean z) {
        IDumpstateDevice dumpstateDeviceAidlService = getDumpstateDeviceAidlService();
        if (dumpstateDeviceAidlService != null) {
            try {
                dumpstateDeviceAidlService.setVerboseLoggingEnabled(z);
            } catch (RemoteException e) {
                if (DBG) {
                    Log.e("EnableVerboseVendorLoggingPreferenceController", "aidlService.setVerboseLoggingEnabled fail: " + e);
                }
            }
        }
        android.hardware.dumpstate.V1_0.IDumpstateDevice dumpstateDeviceService = getDumpstateDeviceService();
        if (dumpstateDeviceService == null || this.mDumpstateHalVersion < 1) {
            if (DBG) {
                Log.d("EnableVerboseVendorLoggingPreferenceController", "setVerboseLoggingEnabled not supported.");
                return;
            }
            return;
        }
        try {
            ((android.hardware.dumpstate.V1_1.IDumpstateDevice) dumpstateDeviceService).setVerboseLoggingEnabled(z);
        } catch (RemoteException | RuntimeException e2) {
            if (DBG) {
                Log.e("EnableVerboseVendorLoggingPreferenceController", "HIDL v1.1 setVerboseLoggingEnabled fail: " + e2);
            }
        }
    }

    boolean getVerboseLoggingEnabled() {
        IDumpstateDevice dumpstateDeviceAidlService = getDumpstateDeviceAidlService();
        if (dumpstateDeviceAidlService != null) {
            try {
                return dumpstateDeviceAidlService.getVerboseLoggingEnabled();
            } catch (RemoteException e) {
                if (DBG) {
                    Log.e("EnableVerboseVendorLoggingPreferenceController", "aidlService.getVerboseLoggingEnabled fail: " + e);
                }
            }
        }
        android.hardware.dumpstate.V1_0.IDumpstateDevice dumpstateDeviceService = this.getDumpstateDeviceService();
        if (dumpstateDeviceService == null || this.mDumpstateHalVersion < 1) {
            if (DBG) {
                Log.d("EnableVerboseVendorLoggingPreferenceController", "getVerboseLoggingEnabled not supported.");
            }
            return false;
        }
        try {
            return ((android.hardware.dumpstate.V1_1.IDumpstateDevice) dumpstateDeviceService).getVerboseLoggingEnabled();
        } catch (RemoteException | RuntimeException e2) {
            if (DBG) {
                Log.e("EnableVerboseVendorLoggingPreferenceController", "HIDL v1.1 getVerboseLoggingEnabled fail: " + e2);
            }
            return false;
        }
    }

    android.hardware.dumpstate.V1_0.IDumpstateDevice getDumpstateDeviceService() {
        android.hardware.dumpstate.V1_0.IDumpstateDevice iDumpstateDevice = null;
        try {
            iDumpstateDevice = android.hardware.dumpstate.V1_1.IDumpstateDevice.getService(true);
            this.mDumpstateHalVersion = 1;
        } catch (RemoteException | NoSuchElementException e) {
            if (DBG) {
                Log.e("EnableVerboseVendorLoggingPreferenceController", "Get HIDL v1.1 service fail: " + e);
            }
        }
        if (iDumpstateDevice == null) {
            try {
                iDumpstateDevice = android.hardware.dumpstate.V1_0.IDumpstateDevice.getService(true);
                this.mDumpstateHalVersion = 0;
            } catch (RemoteException | NoSuchElementException e2) {
                if (DBG) {
                    Log.e("EnableVerboseVendorLoggingPreferenceController", "Get HIDL v1.0 service fail: " + e2);
                }
            }
        }
        if (iDumpstateDevice == null) {
            this.mDumpstateHalVersion = -1;
        }
        return iDumpstateDevice;
    }

    IDumpstateDevice getDumpstateDeviceAidlService() {
        IDumpstateDevice iDumpstateDevice;
        try {
            iDumpstateDevice = IDumpstateDevice.Stub.asInterface(ServiceManager.waitForDeclaredService(DUMP_STATE_AIDL_SERVICE_NAME));
        } catch (NoSuchElementException e) {
            if (DBG) {
                Log.e("EnableVerboseVendorLoggingPreferenceController", "Get AIDL service fail: " + e);
            }
            iDumpstateDevice = null;
        }
        if (iDumpstateDevice != null) {
            this.mDumpstateHalVersion = 2;
        }
        return iDumpstateDevice;
    }
}
