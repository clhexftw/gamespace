package com.android.settings.development;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.os.SystemProperties;
import androidx.preference.Preference;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settingslib.development.DeveloperOptionsPreferenceController;
import org.nameless.custom.preference.SwitchPreference;
/* loaded from: classes.dex */
public class BluetoothLeAudioHwOffloadPreferenceController extends DeveloperOptionsPreferenceController implements Preference.OnPreferenceChangeListener, PreferenceControllerMixin {
    BluetoothAdapter mBluetoothAdapter;
    boolean mChanged;
    private final DevelopmentSettingsDashboardFragment mFragment;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "bluetooth_disable_le_audio_hw_offload";
    }

    public BluetoothLeAudioHwOffloadPreferenceController(Context context, DevelopmentSettingsDashboardFragment developmentSettingsDashboardFragment) {
        super(context);
        this.mChanged = false;
        this.mFragment = developmentSettingsDashboardFragment;
        this.mBluetoothAdapter = ((BluetoothManager) context.getSystemService(BluetoothManager.class)).getAdapter();
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        BluetoothRebootDialog.show(this.mFragment);
        this.mChanged = true;
        return false;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        BluetoothAdapter bluetoothAdapter = this.mBluetoothAdapter;
        if (bluetoothAdapter == null) {
            return;
        }
        boolean z = bluetoothAdapter.isLeAudioSupported() == 10;
        boolean z2 = SystemProperties.getBoolean("ro.bluetooth.leaudio_offload.supported", false);
        boolean z3 = SystemProperties.getBoolean("persist.bluetooth.a2dp_offload.disabled", false);
        if (z && z2 && !z3) {
            ((SwitchPreference) this.mPreference).setChecked(SystemProperties.getBoolean("persist.bluetooth.leaudio_offload.disabled", true));
            return;
        }
        this.mPreference.setEnabled(false);
        ((SwitchPreference) this.mPreference).setChecked(true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settingslib.development.DeveloperOptionsPreferenceController
    public void onDeveloperOptionsSwitchDisabled() {
        super.onDeveloperOptionsSwitchDisabled();
        BluetoothAdapter bluetoothAdapter = this.mBluetoothAdapter;
        if (bluetoothAdapter == null) {
            return;
        }
        boolean z = bluetoothAdapter.isLeAudioSupported() == 10;
        boolean z2 = SystemProperties.getBoolean("ro.bluetooth.leaudio_offload.supported", false);
        boolean z3 = SystemProperties.getBoolean("persist.bluetooth.a2dp_offload.disabled", false);
        if (z && z2 && !z3) {
            ((SwitchPreference) this.mPreference).setChecked(true);
            SystemProperties.set("persist.bluetooth.leaudio_offload.disabled", "true");
            return;
        }
        this.mPreference.setEnabled(false);
    }

    public boolean isDefaultValue() {
        boolean z = !SystemProperties.getBoolean("persist.bluetooth.a2dp_offload.disabled", false) && SystemProperties.getBoolean("ro.bluetooth.leaudio_offload.supported", false);
        boolean z2 = SystemProperties.getBoolean("persist.bluetooth.leaudio_offload.disabled", false);
        if (z) {
            return z2;
        }
        return true;
    }

    public void onRebootDialogConfirmed() {
        if (this.mChanged) {
            SystemProperties.set("persist.bluetooth.leaudio_offload.disabled", Boolean.toString(!SystemProperties.getBoolean("persist.bluetooth.leaudio_offload.disabled", false)));
        }
    }

    public void onRebootDialogCanceled() {
        this.mChanged = false;
    }
}
