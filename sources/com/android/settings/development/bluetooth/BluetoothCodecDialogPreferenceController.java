package com.android.settings.development.bluetooth;

import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothCodecConfig;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.util.Log;
import androidx.preference.PreferenceScreen;
import com.android.settings.development.BluetoothA2dpConfigStore;
import com.android.settings.development.bluetooth.AbstractBluetoothPreferenceController;
import com.android.settingslib.core.lifecycle.Lifecycle;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class BluetoothCodecDialogPreferenceController extends AbstractBluetoothDialogPreferenceController {
    private final AbstractBluetoothPreferenceController.Callback mCallback;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "bluetooth_audio_codec_settings";
    }

    public BluetoothCodecDialogPreferenceController(Context context, Lifecycle lifecycle, BluetoothA2dpConfigStore bluetoothA2dpConfigStore, AbstractBluetoothPreferenceController.Callback callback) {
        super(context, lifecycle, bluetoothA2dpConfigStore);
        this.mCallback = callback;
    }

    @Override // com.android.settingslib.development.DeveloperOptionsPreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        ((BaseBluetoothDialogPreference) this.mPreference).setCallback(this);
    }

    @Override // com.android.settings.development.bluetooth.BaseBluetoothDialogPreference.Callback
    public List<Integer> getSelectableIndex() {
        List<BluetoothCodecConfig> selectableConfigs;
        ArrayList arrayList = new ArrayList();
        BluetoothA2dp bluetoothA2dp = this.mBluetoothA2dp;
        arrayList.add(Integer.valueOf(getDefaultIndex()));
        if (bluetoothA2dp == null) {
            return arrayList;
        }
        BluetoothDevice a2dpActiveDevice = getA2dpActiveDevice();
        if (a2dpActiveDevice == null) {
            Log.d("BtCodecCtr", "Unable to get selectable index. No Active Bluetooth device");
            return arrayList;
        } else if (bluetoothA2dp.isOptionalCodecsEnabled(a2dpActiveDevice) == 1 && (selectableConfigs = getSelectableConfigs(a2dpActiveDevice)) != null) {
            return getIndexFromConfig(selectableConfigs);
        } else {
            arrayList.add(Integer.valueOf(convertCfgToBtnIndex(0)));
            return arrayList;
        }
    }

    @Override // com.android.settings.development.bluetooth.AbstractBluetoothDialogPreferenceController
    protected void writeConfigurationValues(int i) {
        int i2 = 0;
        int i3 = 1000000;
        switch (i) {
            case 0:
                BluetoothDevice a2dpActiveDevice = getA2dpActiveDevice();
                i2 = AbstractBluetoothDialogPreferenceController.getHighestCodec(this.mBluetoothA2dp, a2dpActiveDevice, getSelectableConfigs(a2dpActiveDevice));
                break;
            case 1:
                break;
            case 2:
                i2 = 1;
                break;
            case 3:
                i2 = 2;
                break;
            case 4:
                i2 = 3;
                break;
            case 5:
                i2 = 4;
                break;
            case 6:
                i2 = 5;
                break;
            case 7:
                i2 = 6;
                break;
            default:
                i3 = 0;
                break;
        }
        this.mBluetoothA2dpConfigStore.setCodecType(i2);
        this.mBluetoothA2dpConfigStore.setCodecPriority(i3);
        BluetoothCodecConfig selectableByCodecType = getSelectableByCodecType(i2);
        if (selectableByCodecType == null) {
            Log.d("BtCodecCtr", "Selectable config is null. Unable to reset");
        }
        this.mBluetoothA2dpConfigStore.setSampleRate(AbstractBluetoothDialogPreferenceController.getHighestSampleRate(selectableByCodecType));
        this.mBluetoothA2dpConfigStore.setBitsPerSample(AbstractBluetoothDialogPreferenceController.getHighestBitsPerSample(selectableByCodecType));
        this.mBluetoothA2dpConfigStore.setChannelMode(AbstractBluetoothDialogPreferenceController.getHighestChannelMode(selectableByCodecType));
    }

    @Override // com.android.settings.development.bluetooth.AbstractBluetoothDialogPreferenceController
    protected int getCurrentIndexByConfig(BluetoothCodecConfig bluetoothCodecConfig) {
        if (bluetoothCodecConfig == null) {
            Log.e("BtCodecCtr", "Unable to get current config index. Config is null.");
        }
        return convertCfgToBtnIndex(bluetoothCodecConfig.getCodecType());
    }

    @Override // com.android.settings.development.bluetooth.AbstractBluetoothDialogPreferenceController, com.android.settings.development.bluetooth.BaseBluetoothDialogPreference.Callback
    public void onIndexUpdated(int i) {
        super.onIndexUpdated(i);
        this.mCallback.onBluetoothCodecChanged();
    }

    @Override // com.android.settings.development.bluetooth.AbstractBluetoothDialogPreferenceController
    public void onHDAudioEnabled(boolean z) {
        writeConfigurationValues(0);
    }

    private List<Integer> getIndexFromConfig(List<BluetoothCodecConfig> list) {
        ArrayList arrayList = new ArrayList();
        for (BluetoothCodecConfig bluetoothCodecConfig : list) {
            arrayList.add(Integer.valueOf(convertCfgToBtnIndex(bluetoothCodecConfig.getCodecType())));
        }
        return arrayList;
    }

    int convertCfgToBtnIndex(int i) {
        int defaultIndex = getDefaultIndex();
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            if (i != 6) {
                                Log.e("BtCodecCtr", "Unsupported config:" + i);
                                return defaultIndex;
                            }
                            return 7;
                        }
                        return 5;
                    }
                    return 4;
                }
                return 3;
            }
            return 2;
        }
        return 1;
    }
}
