package com.android.settings.bluetooth;

import android.content.Context;
import android.media.AudioDeviceAttributes;
import android.media.AudioManager;
import android.media.Spatializer;
import android.text.TextUtils;
import android.util.Log;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceScreen;
import com.android.settings.R;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;
import com.android.settingslib.core.lifecycle.Lifecycle;
import org.nameless.custom.preference.SwitchPreference;
/* loaded from: classes.dex */
public class BluetoothDetailsSpatialAudioController extends BluetoothDetailsController implements Preference.OnPreferenceClickListener {
    AudioDeviceAttributes mAudioDevice;
    private boolean mIsAvailable;
    PreferenceCategory mProfilesContainer;
    private final Spatializer mSpatializer;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "spatial_audio_group";
    }

    public BluetoothDetailsSpatialAudioController(Context context, PreferenceFragmentCompat preferenceFragmentCompat, CachedBluetoothDevice cachedBluetoothDevice, Lifecycle lifecycle) {
        super(context, preferenceFragmentCompat, cachedBluetoothDevice, lifecycle);
        this.mSpatializer = ((AudioManager) context.getSystemService(AudioManager.class)).getSpatializer();
        getAvailableDevice();
    }

    @Override // com.android.settings.bluetooth.BluetoothDetailsController, com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return this.mIsAvailable;
    }

    @Override // androidx.preference.Preference.OnPreferenceClickListener
    public boolean onPreferenceClick(Preference preference) {
        SwitchPreference switchPreference = (SwitchPreference) preference;
        String key = switchPreference.getKey();
        if (TextUtils.equals(key, "spatial_audio")) {
            if (switchPreference.isChecked()) {
                this.mSpatializer.addCompatibleAudioDevice(this.mAudioDevice);
            } else {
                this.mSpatializer.removeCompatibleAudioDevice(this.mAudioDevice);
            }
            refresh();
            return true;
        } else if (TextUtils.equals(key, "head_tracking")) {
            this.mSpatializer.setHeadTrackerEnabled(switchPreference.isChecked(), this.mAudioDevice);
            return true;
        } else {
            Log.w("BluetoothSpatialAudioController", "invalid key name.");
            return false;
        }
    }

    @Override // com.android.settings.bluetooth.BluetoothDetailsController
    protected void init(PreferenceScreen preferenceScreen) {
        PreferenceCategory preferenceCategory = (PreferenceCategory) preferenceScreen.findPreference(getPreferenceKey());
        this.mProfilesContainer = preferenceCategory;
        preferenceCategory.setLayoutResource(R.layout.preference_bluetooth_profile_category);
        refresh();
    }

    @Override // com.android.settings.bluetooth.BluetoothDetailsController
    protected void refresh() {
        SwitchPreference switchPreference = (SwitchPreference) this.mProfilesContainer.findPreference("spatial_audio");
        if (switchPreference == null) {
            switchPreference = createSpatialAudioPreference(this.mProfilesContainer.getContext());
            this.mProfilesContainer.addPreference(switchPreference);
        }
        boolean contains = this.mSpatializer.getCompatibleAudioDevices().contains(this.mAudioDevice);
        Log.d("BluetoothSpatialAudioController", "refresh() isSpatialAudioOn : " + contains);
        switchPreference.setChecked(contains);
        SwitchPreference switchPreference2 = (SwitchPreference) this.mProfilesContainer.findPreference("head_tracking");
        if (switchPreference2 == null) {
            switchPreference2 = createHeadTrackingPreference(this.mProfilesContainer.getContext());
            this.mProfilesContainer.addPreference(switchPreference2);
        }
        boolean z = contains && this.mSpatializer.hasHeadTracker(this.mAudioDevice);
        Log.d("BluetoothSpatialAudioController", "refresh() has head tracker : " + this.mSpatializer.hasHeadTracker(this.mAudioDevice));
        switchPreference2.setVisible(z);
        if (z) {
            switchPreference2.setChecked(this.mSpatializer.isHeadTrackerEnabled(this.mAudioDevice));
        }
    }

    SwitchPreference createSpatialAudioPreference(Context context) {
        SwitchPreference switchPreference = new SwitchPreference(context);
        switchPreference.setKey("spatial_audio");
        switchPreference.setTitle(context.getString(R.string.bluetooth_details_spatial_audio_title));
        switchPreference.setSummary(context.getString(R.string.bluetooth_details_spatial_audio_summary));
        switchPreference.setOnPreferenceClickListener(this);
        return switchPreference;
    }

    SwitchPreference createHeadTrackingPreference(Context context) {
        SwitchPreference switchPreference = new SwitchPreference(context);
        switchPreference.setKey("head_tracking");
        switchPreference.setTitle(context.getString(R.string.bluetooth_details_head_tracking_title));
        switchPreference.setSummary(context.getString(R.string.bluetooth_details_head_tracking_summary));
        switchPreference.setOnPreferenceClickListener(this);
        return switchPreference;
    }

    private void getAvailableDevice() {
        AudioDeviceAttributes audioDeviceAttributes = new AudioDeviceAttributes(2, 8, this.mCachedDevice.getAddress());
        AudioDeviceAttributes audioDeviceAttributes2 = new AudioDeviceAttributes(2, 26, this.mCachedDevice.getAddress());
        AudioDeviceAttributes audioDeviceAttributes3 = new AudioDeviceAttributes(2, 27, this.mCachedDevice.getAddress());
        AudioDeviceAttributes audioDeviceAttributes4 = new AudioDeviceAttributes(2, 30, this.mCachedDevice.getAddress());
        AudioDeviceAttributes audioDeviceAttributes5 = new AudioDeviceAttributes(2, 23, this.mCachedDevice.getAddress());
        this.mIsAvailable = true;
        if (this.mSpatializer.isAvailableForDevice(audioDeviceAttributes2)) {
            this.mAudioDevice = audioDeviceAttributes2;
        } else if (this.mSpatializer.isAvailableForDevice(audioDeviceAttributes3)) {
            this.mAudioDevice = audioDeviceAttributes3;
        } else if (this.mSpatializer.isAvailableForDevice(audioDeviceAttributes4)) {
            this.mAudioDevice = audioDeviceAttributes4;
        } else if (this.mSpatializer.isAvailableForDevice(audioDeviceAttributes)) {
            this.mAudioDevice = audioDeviceAttributes;
        } else {
            this.mIsAvailable = this.mSpatializer.isAvailableForDevice(audioDeviceAttributes5);
            this.mAudioDevice = audioDeviceAttributes5;
        }
        Log.d("BluetoothSpatialAudioController", "getAvailableDevice() device : " + this.mCachedDevice.getDevice().getAnonymizedAddress() + ", type : " + this.mAudioDevice.getType() + ", is available : " + this.mIsAvailable);
    }

    void setAvailableDevice(AudioDeviceAttributes audioDeviceAttributes) {
        this.mAudioDevice = audioDeviceAttributes;
        this.mIsAvailable = this.mSpatializer.isAvailableForDevice(audioDeviceAttributes);
    }
}
