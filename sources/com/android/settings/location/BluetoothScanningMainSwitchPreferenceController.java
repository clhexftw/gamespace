package com.android.settings.location;

import android.content.Context;
import android.content.IntentFilter;
import android.os.UserManager;
import android.provider.Settings;
import android.widget.Switch;
import androidx.preference.PreferenceScreen;
import com.android.settings.R;
import com.android.settings.core.TogglePreferenceController;
import com.android.settingslib.widget.MainSwitchPreference;
import com.android.settingslib.widget.OnMainSwitchChangeListener;
/* loaded from: classes.dex */
public class BluetoothScanningMainSwitchPreferenceController extends TogglePreferenceController implements OnMainSwitchChangeListener {
    private static final String KEY_BLUETOOTH_SCANNING_SWITCH = "bluetooth_always_scanning_switch";
    private final UserManager mUserManager;

    @Override // com.android.settings.core.TogglePreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ Class getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.settings.core.TogglePreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.settings.core.TogglePreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.settings.core.TogglePreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public BluetoothScanningMainSwitchPreferenceController(Context context) {
        super(context, KEY_BLUETOOTH_SCANNING_SWITCH);
        this.mUserManager = UserManager.get(context);
    }

    @Override // com.android.settings.core.TogglePreferenceController, com.android.settings.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        MainSwitchPreference mainSwitchPreference = (MainSwitchPreference) preferenceScreen.findPreference(getPreferenceKey());
        mainSwitchPreference.addOnSwitchChangeListener(this);
        mainSwitchPreference.updateStatus(isChecked());
    }

    @Override // com.android.settings.core.BasePreferenceController
    public int getAvailabilityStatus() {
        if (this.mContext.getResources().getBoolean(R.bool.config_show_location_scanning)) {
            return this.mUserManager.hasUserRestriction("no_config_location") ? 5 : 0;
        }
        return 3;
    }

    @Override // com.android.settings.core.TogglePreferenceController
    public boolean isChecked() {
        return Settings.Global.getInt(this.mContext.getContentResolver(), "ble_scan_always_enabled", 0) == 1;
    }

    @Override // com.android.settings.core.TogglePreferenceController
    public boolean setChecked(boolean z) {
        Settings.Global.putInt(this.mContext.getContentResolver(), "ble_scan_always_enabled", z ? 1 : 0);
        return true;
    }

    @Override // com.android.settings.core.TogglePreferenceController, com.android.settings.slices.Sliceable
    public int getSliceHighlightMenuRes() {
        return R.string.menu_key_location;
    }

    @Override // com.android.settingslib.widget.OnMainSwitchChangeListener
    public void onSwitchChanged(Switch r1, boolean z) {
        if (z != isChecked()) {
            setChecked(z);
        }
    }
}
