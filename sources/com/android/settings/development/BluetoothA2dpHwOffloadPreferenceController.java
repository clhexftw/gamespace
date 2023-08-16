package com.android.settings.development;

import android.content.Context;
import android.os.SystemProperties;
import androidx.preference.Preference;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settingslib.development.DeveloperOptionsPreferenceController;
import org.nameless.custom.preference.SwitchPreference;
/* loaded from: classes.dex */
public class BluetoothA2dpHwOffloadPreferenceController extends DeveloperOptionsPreferenceController implements Preference.OnPreferenceChangeListener, PreferenceControllerMixin {
    boolean mChanged;
    private final DevelopmentSettingsDashboardFragment mFragment;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "bluetooth_disable_a2dp_hw_offload";
    }

    public BluetoothA2dpHwOffloadPreferenceController(Context context, DevelopmentSettingsDashboardFragment developmentSettingsDashboardFragment) {
        super(context);
        this.mChanged = false;
        this.mFragment = developmentSettingsDashboardFragment;
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        BluetoothRebootDialog.show(this.mFragment);
        this.mChanged = true;
        return false;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        super.updateState(preference);
        if (SystemProperties.getBoolean("ro.bluetooth.a2dp_offload.supported", false)) {
            ((SwitchPreference) this.mPreference).setChecked(SystemProperties.getBoolean("persist.bluetooth.a2dp_offload.disabled", false));
            return;
        }
        this.mPreference.setEnabled(false);
        ((SwitchPreference) this.mPreference).setChecked(true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settingslib.development.DeveloperOptionsPreferenceController
    public void onDeveloperOptionsSwitchDisabled() {
        super.onDeveloperOptionsSwitchDisabled();
        if (SystemProperties.getBoolean("ro.bluetooth.a2dp_offload.supported", false)) {
            ((SwitchPreference) this.mPreference).setChecked(false);
            SystemProperties.set("persist.bluetooth.a2dp_offload.disabled", "false");
        }
    }

    public boolean isDefaultValue() {
        return (SystemProperties.getBoolean("ro.bluetooth.a2dp_offload.supported", false) && SystemProperties.getBoolean("persist.bluetooth.a2dp_offload.disabled", false)) ? false : true;
    }

    public void onRebootDialogConfirmed() {
        if (this.mChanged) {
            boolean z = SystemProperties.getBoolean("persist.bluetooth.a2dp_offload.disabled", false);
            SystemProperties.set("persist.bluetooth.a2dp_offload.disabled", Boolean.toString(!z));
            if (z) {
                return;
            }
            SystemProperties.set("persist.bluetooth.leaudio_offload.disabled", Boolean.toString(!z));
        }
    }

    public void onRebootDialogCanceled() {
        this.mChanged = false;
    }
}
