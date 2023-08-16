package com.android.settings.bluetooth;

import android.content.Context;
import android.view.View;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceScreen;
import com.android.settings.R;
import com.android.settings.applications.SpacePreference;
import com.android.settings.core.SubSettingLauncher;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.widget.ButtonPreference;
/* loaded from: classes.dex */
public class BluetoothDetailsPairOtherController extends BluetoothDetailsController {
    static final String KEY_SPACE = "hearing_aid_space_layout";
    private ButtonPreference mPreference;
    private SpacePreference mSpacePreference;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "hearing_aid_pair_other_button";
    }

    public BluetoothDetailsPairOtherController(Context context, PreferenceFragmentCompat preferenceFragmentCompat, CachedBluetoothDevice cachedBluetoothDevice, Lifecycle lifecycle) {
        super(context, preferenceFragmentCompat, cachedBluetoothDevice, lifecycle);
        lifecycle.addObserver(this);
    }

    @Override // com.android.settings.bluetooth.BluetoothDetailsController, com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return getButtonPreferenceVisibility(this.mCachedDevice);
    }

    @Override // com.android.settings.bluetooth.BluetoothDetailsController
    protected void init(PreferenceScreen preferenceScreen) {
        int i;
        if (this.mCachedDevice.getDeviceSide() == 0) {
            i = R.string.bluetooth_pair_right_ear_button;
        } else {
            i = R.string.bluetooth_pair_left_ear_button;
        }
        this.mPreference = (ButtonPreference) preferenceScreen.findPreference(getPreferenceKey());
        this.mSpacePreference = (SpacePreference) preferenceScreen.findPreference(KEY_SPACE);
        this.mPreference.setTitle(i);
        setPreferencesVisibility(getButtonPreferenceVisibility(this.mCachedDevice));
        this.mPreference.setOnClickListener(new View.OnClickListener() { // from class: com.android.settings.bluetooth.BluetoothDetailsPairOtherController$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BluetoothDetailsPairOtherController.this.lambda$init$0(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$init$0(View view) {
        launchPairingDetail();
    }

    @Override // com.android.settings.bluetooth.BluetoothDetailsController
    protected void refresh() {
        setPreferencesVisibility(getButtonPreferenceVisibility(this.mCachedDevice));
    }

    private void setPreferencesVisibility(boolean z) {
        this.mPreference.setVisible(z);
        this.mSpacePreference.setVisible(z);
    }

    private boolean getButtonPreferenceVisibility(CachedBluetoothDevice cachedBluetoothDevice) {
        return isBinauralMode(cachedBluetoothDevice) && isOnlyOneSideConnected(cachedBluetoothDevice);
    }

    private void launchPairingDetail() {
        new SubSettingLauncher(((BluetoothDetailsController) this).mContext).setDestination(BluetoothPairingDetail.class.getName()).setSourceMetricsCategory(((BluetoothDeviceDetailsFragment) this.mFragment).getMetricsCategory()).launch();
    }

    private boolean isBinauralMode(CachedBluetoothDevice cachedBluetoothDevice) {
        return cachedBluetoothDevice.getDeviceMode() == 1;
    }

    private boolean isOnlyOneSideConnected(CachedBluetoothDevice cachedBluetoothDevice) {
        if (cachedBluetoothDevice.isConnectedHearingAidDevice()) {
            CachedBluetoothDevice subDevice = cachedBluetoothDevice.getSubDevice();
            return subDevice == null || !subDevice.isConnectedHearingAidDevice();
        }
        return false;
    }
}
