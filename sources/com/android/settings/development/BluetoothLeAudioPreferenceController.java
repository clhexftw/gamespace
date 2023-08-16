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
public class BluetoothLeAudioPreferenceController extends DeveloperOptionsPreferenceController implements Preference.OnPreferenceChangeListener, PreferenceControllerMixin {
    static final String LE_AUDIO_DYNAMIC_ENABLED_PROPERTY = "persist.bluetooth.leaudio_switcher.enabled";
    BluetoothAdapter mBluetoothAdapter;
    boolean mChanged;
    private final DevelopmentSettingsDashboardFragment mFragment;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "bluetooth_enable_leaudio";
    }

    public BluetoothLeAudioPreferenceController(Context context, DevelopmentSettingsDashboardFragment developmentSettingsDashboardFragment) {
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
        ((SwitchPreference) this.mPreference).setChecked(z);
        if (!SystemProperties.getBoolean("ro.bluetooth.leaudio_switcher.supported", false)) {
            this.mPreference.setEnabled(false);
        } else {
            SystemProperties.set(LE_AUDIO_DYNAMIC_ENABLED_PROPERTY, Boolean.toString(z));
        }
    }

    public void onRebootDialogConfirmed() {
        BluetoothAdapter bluetoothAdapter;
        if (!this.mChanged || (bluetoothAdapter = this.mBluetoothAdapter) == null) {
            return;
        }
        SystemProperties.set(LE_AUDIO_DYNAMIC_ENABLED_PROPERTY, Boolean.toString(!(bluetoothAdapter.isLeAudioSupported() == 10)));
    }

    public void onRebootDialogCanceled() {
        this.mChanged = false;
    }
}
