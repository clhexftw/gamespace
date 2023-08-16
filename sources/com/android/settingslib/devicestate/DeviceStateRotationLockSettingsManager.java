package com.android.settingslib.devicestate;

import android.content.Context;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseIntArray;
import com.android.internal.annotations.VisibleForTesting;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
/* loaded from: classes2.dex */
public final class DeviceStateRotationLockSettingsManager {
    private static DeviceStateRotationLockSettingsManager sSingleton;
    private String mLastSettingValue;
    private SparseIntArray mPostureDefaultRotationLockSettings;
    private String[] mPostureRotationLockDefaults;
    private SparseIntArray mPostureRotationLockFallbackSettings;
    private SparseIntArray mPostureRotationLockSettings;
    private final PosturesHelper mPosturesHelper;
    private final SecureSettings mSecureSettings;
    private List<SettableDeviceState> mSettableDeviceStates;
    private final Handler mMainHandler = new Handler(Looper.getMainLooper());
    private final Set<DeviceStateRotationLockSettingsListener> mListeners = new HashSet();

    /* loaded from: classes2.dex */
    public interface DeviceStateRotationLockSettingsListener {
        void onSettingsChanged();
    }

    @VisibleForTesting
    DeviceStateRotationLockSettingsManager(Context context, SecureSettings secureSettings) {
        this.mSecureSettings = secureSettings;
        this.mPosturesHelper = new PosturesHelper(context);
        this.mPostureRotationLockDefaults = context.getResources().getStringArray(17236117);
        loadDefaults();
        initializeInMemoryMap();
        listenForSettingsChange();
    }

    public static synchronized DeviceStateRotationLockSettingsManager getInstance(Context context) {
        DeviceStateRotationLockSettingsManager deviceStateRotationLockSettingsManager;
        synchronized (DeviceStateRotationLockSettingsManager.class) {
            if (sSingleton == null) {
                Context applicationContext = context.getApplicationContext();
                sSingleton = new DeviceStateRotationLockSettingsManager(applicationContext, new AndroidSecureSettings(applicationContext.getContentResolver()));
            }
            deviceStateRotationLockSettingsManager = sSingleton;
        }
        return deviceStateRotationLockSettingsManager;
    }

    @VisibleForTesting
    public static synchronized void resetInstance() {
        synchronized (DeviceStateRotationLockSettingsManager.class) {
            sSingleton = null;
        }
    }

    public static boolean isDeviceStateRotationLockEnabled(Context context) {
        return context.getResources().getStringArray(17236117).length > 0;
    }

    private void listenForSettingsChange() {
        this.mSecureSettings.registerContentObserver("device_state_rotation_lock", false, new ContentObserver(this.mMainHandler) { // from class: com.android.settingslib.devicestate.DeviceStateRotationLockSettingsManager.1
            @Override // android.database.ContentObserver
            public void onChange(boolean z) {
                DeviceStateRotationLockSettingsManager.this.onPersistedSettingsChanged();
            }
        }, -2);
    }

    public void registerListener(DeviceStateRotationLockSettingsListener deviceStateRotationLockSettingsListener) {
        this.mListeners.add(deviceStateRotationLockSettingsListener);
    }

    public void unregisterListener(DeviceStateRotationLockSettingsListener deviceStateRotationLockSettingsListener) {
        if (this.mListeners.remove(deviceStateRotationLockSettingsListener)) {
            return;
        }
        Log.w("DSRotLockSettingsMngr", "Attempting to unregister a listener hadn't been registered");
    }

    public void updateSetting(int i, boolean z) {
        int deviceStateToPosture = this.mPosturesHelper.deviceStateToPosture(i);
        if (this.mPostureRotationLockFallbackSettings.indexOfKey(deviceStateToPosture) >= 0) {
            deviceStateToPosture = this.mPostureRotationLockFallbackSettings.get(deviceStateToPosture);
        }
        this.mPostureRotationLockSettings.put(deviceStateToPosture, z ? 1 : 2);
        persistSettings();
    }

    public int getRotationLockSetting(int i) {
        int deviceStateToPosture = this.mPosturesHelper.deviceStateToPosture(i);
        int i2 = this.mPostureRotationLockSettings.get(deviceStateToPosture, 0);
        return i2 == 0 ? getFallbackRotationLockSetting(deviceStateToPosture) : i2;
    }

    private int getFallbackRotationLockSetting(int i) {
        int indexOfKey = this.mPostureRotationLockFallbackSettings.indexOfKey(i);
        if (indexOfKey < 0) {
            Log.w("DSRotLockSettingsMngr", "Setting is ignored, but no fallback was specified.");
            return 0;
        }
        return this.mPostureRotationLockSettings.get(this.mPostureRotationLockFallbackSettings.valueAt(indexOfKey), 0);
    }

    public boolean isRotationLocked(int i) {
        return getRotationLockSetting(i) == 1;
    }

    public boolean isRotationLockedForAllStates() {
        for (int i = 0; i < this.mPostureRotationLockSettings.size(); i++) {
            if (this.mPostureRotationLockSettings.valueAt(i) == 2) {
                return false;
            }
        }
        return true;
    }

    public List<SettableDeviceState> getSettableDeviceStates() {
        return new ArrayList(this.mSettableDeviceStates);
    }

    private void initializeInMemoryMap() {
        String stringForUser = this.mSecureSettings.getStringForUser("device_state_rotation_lock", -2);
        if (TextUtils.isEmpty(stringForUser)) {
            fallbackOnDefaults();
            return;
        }
        String[] split = stringForUser.split(":");
        if (split.length % 2 != 0) {
            Log.wtf("DSRotLockSettingsMngr", "Can't deserialize saved settings, falling back on defaults");
            fallbackOnDefaults();
            return;
        }
        this.mPostureRotationLockSettings = new SparseIntArray(split.length / 2);
        int i = 0;
        while (true) {
            boolean z = true;
            if (i >= split.length - 1) {
                return;
            }
            int i2 = i + 1;
            try {
                int parseInt = Integer.parseInt(split[i]);
                int i3 = i2 + 1;
                int parseInt2 = Integer.parseInt(split[i2]);
                boolean z2 = parseInt2 == 0;
                if (this.mPostureDefaultRotationLockSettings.get(parseInt) != 0) {
                    z = false;
                }
                if (z2 != z) {
                    Log.w("DSRotLockSettingsMngr", "Conflict for ignored device state " + parseInt + ". Falling back on defaults");
                    fallbackOnDefaults();
                    return;
                }
                this.mPostureRotationLockSettings.put(parseInt, parseInt2);
                i = i3;
            } catch (NumberFormatException e) {
                Log.wtf("DSRotLockSettingsMngr", "Error deserializing one of the saved settings", e);
                fallbackOnDefaults();
                return;
            }
        }
    }

    @VisibleForTesting
    public void resetStateForTesting(Resources resources) {
        this.mPostureRotationLockDefaults = resources.getStringArray(17236117);
        fallbackOnDefaults();
    }

    private void fallbackOnDefaults() {
        loadDefaults();
        persistSettings();
    }

    private void persistSettings() {
        if (this.mPostureRotationLockSettings.size() == 0) {
            persistSettingIfChanged("");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.mPostureRotationLockSettings.keyAt(0));
        sb.append(":");
        sb.append(this.mPostureRotationLockSettings.valueAt(0));
        for (int i = 1; i < this.mPostureRotationLockSettings.size(); i++) {
            sb.append(":");
            sb.append(this.mPostureRotationLockSettings.keyAt(i));
            sb.append(":");
            sb.append(this.mPostureRotationLockSettings.valueAt(i));
        }
        persistSettingIfChanged(sb.toString());
    }

    private void persistSettingIfChanged(String str) {
        Log.v("DSRotLockSettingsMngr", "persistSettingIfChanged: last=" + this.mLastSettingValue + ", new=" + str);
        if (TextUtils.equals(this.mLastSettingValue, str)) {
            return;
        }
        this.mLastSettingValue = str;
        this.mSecureSettings.putStringForUser("device_state_rotation_lock", str, -2);
    }

    private void loadDefaults() {
        String[] strArr;
        this.mSettableDeviceStates = new ArrayList(this.mPostureRotationLockDefaults.length);
        this.mPostureDefaultRotationLockSettings = new SparseIntArray(this.mPostureRotationLockDefaults.length);
        this.mPostureRotationLockSettings = new SparseIntArray(this.mPostureRotationLockDefaults.length);
        this.mPostureRotationLockFallbackSettings = new SparseIntArray(1);
        for (String str : this.mPostureRotationLockDefaults) {
            String[] split = str.split(":");
            try {
                int parseInt = Integer.parseInt(split[0]);
                int parseInt2 = Integer.parseInt(split[1]);
                if (parseInt2 == 0) {
                    if (split.length == 3) {
                        this.mPostureRotationLockFallbackSettings.put(parseInt, Integer.parseInt(split[2]));
                    } else {
                        Log.w("DSRotLockSettingsMngr", "Rotation lock setting is IGNORED, but values have unexpected size of " + split.length);
                    }
                }
                boolean z = parseInt2 != 0;
                Integer postureToDeviceState = this.mPosturesHelper.postureToDeviceState(parseInt);
                if (postureToDeviceState != null) {
                    this.mSettableDeviceStates.add(new SettableDeviceState(postureToDeviceState.intValue(), z));
                } else {
                    Log.wtf("DSRotLockSettingsMngr", "No matching device state for posture: " + parseInt);
                }
                this.mPostureRotationLockSettings.put(parseInt, parseInt2);
                this.mPostureDefaultRotationLockSettings.put(parseInt, parseInt2);
            } catch (NumberFormatException e) {
                Log.wtf("DSRotLockSettingsMngr", "Error parsing settings entry. Entry was: " + str, e);
                return;
            }
        }
    }

    @VisibleForTesting
    public void onPersistedSettingsChanged() {
        initializeInMemoryMap();
        notifyListeners();
    }

    private void notifyListeners() {
        for (DeviceStateRotationLockSettingsListener deviceStateRotationLockSettingsListener : this.mListeners) {
            deviceStateRotationLockSettingsListener.onSettingsChanged();
        }
    }

    /* loaded from: classes2.dex */
    public static class SettableDeviceState {
        private final int mDeviceState;
        private final boolean mIsSettable;

        SettableDeviceState(int i, boolean z) {
            this.mDeviceState = i;
            this.mIsSettable = z;
        }

        public int getDeviceState() {
            return this.mDeviceState;
        }

        public boolean isSettable() {
            return this.mIsSettable;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof SettableDeviceState) {
                SettableDeviceState settableDeviceState = (SettableDeviceState) obj;
                return this.mDeviceState == settableDeviceState.mDeviceState && this.mIsSettable == settableDeviceState.mIsSettable;
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(this.mDeviceState), Boolean.valueOf(this.mIsSettable));
        }

        public String toString() {
            return "SettableDeviceState{mDeviceState=" + this.mDeviceState + ", mIsSettable=" + this.mIsSettable + '}';
        }
    }
}
