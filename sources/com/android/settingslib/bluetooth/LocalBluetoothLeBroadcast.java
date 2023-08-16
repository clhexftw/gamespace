package com.android.settingslib.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothLeAudioContentMetadata;
import android.bluetooth.BluetoothLeBroadcast;
import android.bluetooth.BluetoothLeBroadcastMetadata;
import android.bluetooth.BluetoothLeBroadcastSubgroup;
import android.bluetooth.BluetoothProfile;
import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import com.android.settingslib.R$string;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
/* loaded from: classes2.dex */
public class LocalBluetoothLeBroadcast implements LocalBluetoothProfile {
    private static final Uri[] SETTINGS_URIS = {Settings.Secure.getUriFor("bluetooth_le_broadcast_program_info"), Settings.Secure.getUriFor("bluetooth_le_broadcast_code"), Settings.Secure.getUriFor("bluetooth_le_broadcast_app_source_name")};
    private BluetoothLeAudioContentMetadata mBluetoothLeAudioContentMetadata;
    private BluetoothLeBroadcastMetadata mBluetoothLeBroadcastMetadata;
    private final BluetoothLeBroadcast.Callback mBroadcastCallback;
    private byte[] mBroadcastCode;
    private BluetoothLeAudioContentMetadata.Builder mBuilder;
    private ContentResolver mContentResolver;
    private Executor mExecutor;
    private boolean mIsProfileReady;
    private String mProgramInfo;
    private BluetoothLeBroadcast mService;
    private final BluetoothProfile.ServiceListener mServiceListener;
    private ContentObserver mSettingsObserver;
    private int mBroadcastId = -1;
    private String mAppSourceName = "";
    private String mNewAppSourceName = "";

    @Override // com.android.settingslib.bluetooth.LocalBluetoothProfile
    public boolean accessProfileEnabled() {
        return false;
    }

    @Override // com.android.settingslib.bluetooth.LocalBluetoothProfile
    public int getConnectionPolicy(BluetoothDevice bluetoothDevice) {
        return 0;
    }

    @Override // com.android.settingslib.bluetooth.LocalBluetoothProfile
    public int getDrawableResource(BluetoothClass bluetoothClass) {
        return 0;
    }

    @Override // com.android.settingslib.bluetooth.LocalBluetoothProfile
    public int getOrdinal() {
        return 1;
    }

    @Override // com.android.settingslib.bluetooth.LocalBluetoothProfile
    public int getProfileId() {
        return 26;
    }

    @Override // com.android.settingslib.bluetooth.LocalBluetoothProfile
    public boolean setEnabled(BluetoothDevice bluetoothDevice, boolean z) {
        return false;
    }

    public String toString() {
        return "LE_AUDIO_BROADCAST";
    }

    /* loaded from: classes2.dex */
    private class BroadcastSettingsObserver extends ContentObserver {
        BroadcastSettingsObserver(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            Log.d("LocalBluetoothLeBroadcast", "BroadcastSettingsObserver: onChange");
            LocalBluetoothLeBroadcast.this.updateBroadcastInfoFromContentProvider();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LocalBluetoothLeBroadcast(Context context) {
        BluetoothProfile.ServiceListener serviceListener = new BluetoothProfile.ServiceListener() { // from class: com.android.settingslib.bluetooth.LocalBluetoothLeBroadcast.1
            @Override // android.bluetooth.BluetoothProfile.ServiceListener
            public void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
                if (LocalBluetoothLeBroadcast.this.mIsProfileReady) {
                    return;
                }
                LocalBluetoothLeBroadcast.this.mService = (BluetoothLeBroadcast) bluetoothProfile;
                LocalBluetoothLeBroadcast.this.mIsProfileReady = true;
                LocalBluetoothLeBroadcast localBluetoothLeBroadcast = LocalBluetoothLeBroadcast.this;
                localBluetoothLeBroadcast.registerServiceCallBack(localBluetoothLeBroadcast.mExecutor, LocalBluetoothLeBroadcast.this.mBroadcastCallback);
                List<BluetoothLeBroadcastMetadata> allBroadcastMetadata = LocalBluetoothLeBroadcast.this.getAllBroadcastMetadata();
                if (!allBroadcastMetadata.isEmpty()) {
                    LocalBluetoothLeBroadcast.this.updateBroadcastInfoFromBroadcastMetadata(allBroadcastMetadata.get(0));
                }
                LocalBluetoothLeBroadcast.this.registerContentObserver();
            }

            @Override // android.bluetooth.BluetoothProfile.ServiceListener
            public void onServiceDisconnected(int i) {
                if (LocalBluetoothLeBroadcast.this.mIsProfileReady) {
                    LocalBluetoothLeBroadcast.this.mIsProfileReady = false;
                    LocalBluetoothLeBroadcast localBluetoothLeBroadcast = LocalBluetoothLeBroadcast.this;
                    localBluetoothLeBroadcast.unregisterServiceCallBack(localBluetoothLeBroadcast.mBroadcastCallback);
                    LocalBluetoothLeBroadcast.this.unregisterContentObserver();
                }
            }
        };
        this.mServiceListener = serviceListener;
        this.mBroadcastCallback = new BluetoothLeBroadcast.Callback() { // from class: com.android.settingslib.bluetooth.LocalBluetoothLeBroadcast.2
            public void onBroadcastStartFailed(int i) {
            }

            public void onBroadcastStopFailed(int i) {
            }

            public void onBroadcastUpdateFailed(int i, int i2) {
            }

            public void onPlaybackStarted(int i, int i2) {
            }

            public void onPlaybackStopped(int i, int i2) {
            }

            public void onBroadcastStarted(int i, int i2) {
                LocalBluetoothLeBroadcast.this.setLatestBroadcastId(i2);
                LocalBluetoothLeBroadcast localBluetoothLeBroadcast = LocalBluetoothLeBroadcast.this;
                localBluetoothLeBroadcast.setAppSourceName(localBluetoothLeBroadcast.mNewAppSourceName, true);
            }

            public void onBroadcastMetadataChanged(int i, BluetoothLeBroadcastMetadata bluetoothLeBroadcastMetadata) {
                LocalBluetoothLeBroadcast.this.setLatestBluetoothLeBroadcastMetadata(bluetoothLeBroadcastMetadata);
            }

            public void onBroadcastStopped(int i, int i2) {
                LocalBluetoothLeBroadcast.this.resetCacheInfo();
            }

            public void onBroadcastUpdated(int i, int i2) {
                LocalBluetoothLeBroadcast.this.setLatestBroadcastId(i2);
                LocalBluetoothLeBroadcast localBluetoothLeBroadcast = LocalBluetoothLeBroadcast.this;
                localBluetoothLeBroadcast.setAppSourceName(localBluetoothLeBroadcast.mNewAppSourceName, true);
            }
        };
        this.mExecutor = Executors.newSingleThreadExecutor();
        this.mBuilder = new BluetoothLeAudioContentMetadata.Builder();
        this.mContentResolver = context.getContentResolver();
        this.mSettingsObserver = new BroadcastSettingsObserver(new Handler(Looper.getMainLooper()));
        updateBroadcastInfoFromContentProvider();
        BluetoothAdapter.getDefaultAdapter().getProfileProxy(context, serviceListener, 26);
    }

    public void startBroadcast(String str, String str2) {
        this.mNewAppSourceName = str;
        if (this.mService == null) {
            Log.d("LocalBluetoothLeBroadcast", "The BluetoothLeBroadcast is null when starting the broadcast.");
            return;
        }
        buildContentMetadata(str2, getProgramInfo());
        this.mService.startBroadcast(this.mBluetoothLeAudioContentMetadata, this.mBroadcastCode);
    }

    public String getProgramInfo() {
        return this.mProgramInfo;
    }

    public void setProgramInfo(String str) {
        setProgramInfo(str, true);
    }

    private void setProgramInfo(String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            Log.d("LocalBluetoothLeBroadcast", "setProgramInfo: programInfo is null or empty");
            return;
        }
        String str2 = this.mProgramInfo;
        if (str2 != null && TextUtils.equals(str2, str)) {
            Log.d("LocalBluetoothLeBroadcast", "setProgramInfo: programInfo is not changed");
            return;
        }
        Log.d("LocalBluetoothLeBroadcast", "setProgramInfo: " + str);
        this.mProgramInfo = str;
        if (z) {
            ContentResolver contentResolver = this.mContentResolver;
            if (contentResolver == null) {
                Log.d("LocalBluetoothLeBroadcast", "mContentResolver is null");
            } else {
                Settings.Secure.putString(contentResolver, "bluetooth_le_broadcast_program_info", str);
            }
        }
    }

    public void setBroadcastCode(byte[] bArr) {
        setBroadcastCode(bArr, true);
    }

    private void setBroadcastCode(byte[] bArr, boolean z) {
        if (bArr == null) {
            Log.d("LocalBluetoothLeBroadcast", "setBroadcastCode: broadcastCode is null");
            return;
        }
        byte[] bArr2 = this.mBroadcastCode;
        if (bArr2 != null && Arrays.equals(bArr, bArr2)) {
            Log.d("LocalBluetoothLeBroadcast", "setBroadcastCode: broadcastCode is not changed");
            return;
        }
        this.mBroadcastCode = bArr;
        if (z) {
            ContentResolver contentResolver = this.mContentResolver;
            if (contentResolver == null) {
                Log.d("LocalBluetoothLeBroadcast", "mContentResolver is null");
            } else {
                Settings.Secure.putString(contentResolver, "bluetooth_le_broadcast_code", new String(bArr, StandardCharsets.UTF_8));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLatestBroadcastId(int i) {
        Log.d("LocalBluetoothLeBroadcast", "setLatestBroadcastId: mBroadcastId is " + i);
        this.mBroadcastId = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAppSourceName(String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        String str2 = this.mAppSourceName;
        if (str2 != null && TextUtils.equals(str2, str)) {
            Log.d("LocalBluetoothLeBroadcast", "setAppSourceName: appSourceName is not changed");
            return;
        }
        this.mAppSourceName = str;
        this.mNewAppSourceName = "";
        if (z) {
            ContentResolver contentResolver = this.mContentResolver;
            if (contentResolver == null) {
                Log.d("LocalBluetoothLeBroadcast", "mContentResolver is null");
            } else {
                Settings.Secure.putString(contentResolver, "bluetooth_le_broadcast_app_source_name", str);
            }
        }
    }

    public String getAppSourceName() {
        return this.mAppSourceName;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLatestBluetoothLeBroadcastMetadata(BluetoothLeBroadcastMetadata bluetoothLeBroadcastMetadata) {
        if (bluetoothLeBroadcastMetadata == null || bluetoothLeBroadcastMetadata.getBroadcastId() != this.mBroadcastId) {
            return;
        }
        this.mBluetoothLeBroadcastMetadata = bluetoothLeBroadcastMetadata;
        updateBroadcastInfoFromBroadcastMetadata(bluetoothLeBroadcastMetadata);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateBroadcastInfoFromContentProvider() {
        byte[] bytes;
        ContentResolver contentResolver = this.mContentResolver;
        if (contentResolver == null) {
            Log.d("LocalBluetoothLeBroadcast", "updateBroadcastInfoFromContentProvider: mContentResolver is null");
            return;
        }
        String string = Settings.Secure.getString(contentResolver, "bluetooth_le_broadcast_program_info");
        if (string == null) {
            string = getDefaultValueOfProgramInfo();
        }
        setProgramInfo(string, false);
        String string2 = Settings.Secure.getString(this.mContentResolver, "bluetooth_le_broadcast_code");
        if (string2 == null) {
            bytes = getDefaultValueOfBroadcastCode();
        } else {
            bytes = string2.getBytes(StandardCharsets.UTF_8);
        }
        setBroadcastCode(bytes, false);
        setAppSourceName(Settings.Secure.getString(this.mContentResolver, "bluetooth_le_broadcast_app_source_name"), false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateBroadcastInfoFromBroadcastMetadata(BluetoothLeBroadcastMetadata bluetoothLeBroadcastMetadata) {
        if (bluetoothLeBroadcastMetadata == null) {
            Log.d("LocalBluetoothLeBroadcast", "The bluetoothLeBroadcastMetadata is null");
            return;
        }
        setBroadcastCode(bluetoothLeBroadcastMetadata.getBroadcastCode());
        setLatestBroadcastId(bluetoothLeBroadcastMetadata.getBroadcastId());
        List subgroups = bluetoothLeBroadcastMetadata.getSubgroups();
        if (subgroups == null || subgroups.size() < 1) {
            Log.d("LocalBluetoothLeBroadcast", "The subgroup is not valid value");
            return;
        }
        setProgramInfo(((BluetoothLeBroadcastSubgroup) subgroups.get(0)).getContentMetadata().getProgramInfo());
        setAppSourceName(getAppSourceName(), true);
    }

    public void registerServiceCallBack(Executor executor, BluetoothLeBroadcast.Callback callback) {
        BluetoothLeBroadcast bluetoothLeBroadcast = this.mService;
        if (bluetoothLeBroadcast == null) {
            Log.d("LocalBluetoothLeBroadcast", "The BluetoothLeBroadcast is null.");
        } else {
            bluetoothLeBroadcast.registerCallback(executor, callback);
        }
    }

    public void unregisterServiceCallBack(BluetoothLeBroadcast.Callback callback) {
        BluetoothLeBroadcast bluetoothLeBroadcast = this.mService;
        if (bluetoothLeBroadcast == null) {
            Log.d("LocalBluetoothLeBroadcast", "The BluetoothLeBroadcast is null.");
        } else {
            bluetoothLeBroadcast.unregisterCallback(callback);
        }
    }

    private void buildContentMetadata(String str, String str2) {
        this.mBluetoothLeAudioContentMetadata = this.mBuilder.setLanguage(str).setProgramInfo(str2).build();
    }

    @Override // com.android.settingslib.bluetooth.LocalBluetoothProfile
    public boolean isProfileReady() {
        return this.mIsProfileReady;
    }

    @Override // com.android.settingslib.bluetooth.LocalBluetoothProfile
    public int getConnectionStatus(BluetoothDevice bluetoothDevice) {
        BluetoothLeBroadcast bluetoothLeBroadcast = this.mService;
        if (bluetoothLeBroadcast == null) {
            return 0;
        }
        return bluetoothLeBroadcast.getConnectionState(bluetoothDevice);
    }

    public List<BluetoothLeBroadcastMetadata> getAllBroadcastMetadata() {
        BluetoothLeBroadcast bluetoothLeBroadcast = this.mService;
        if (bluetoothLeBroadcast == null) {
            Log.d("LocalBluetoothLeBroadcast", "The BluetoothLeBroadcast is null.");
            return Collections.emptyList();
        }
        return bluetoothLeBroadcast.getAllBroadcastMetadata();
    }

    @Override // com.android.settingslib.bluetooth.LocalBluetoothProfile
    public boolean isEnabled(BluetoothDevice bluetoothDevice) {
        BluetoothLeBroadcast bluetoothLeBroadcast = this.mService;
        if (bluetoothLeBroadcast == null) {
            return false;
        }
        return !bluetoothLeBroadcast.getAllBroadcastMetadata().isEmpty();
    }

    @Override // com.android.settingslib.bluetooth.LocalBluetoothProfile
    public int getNameResource(BluetoothDevice bluetoothDevice) {
        return R$string.summary_empty;
    }

    protected void finalize() {
        if (this.mService != null) {
            try {
                BluetoothAdapter.getDefaultAdapter().closeProfileProxy(26, this.mService);
                this.mService = null;
            } catch (Throwable th) {
                Log.w("LocalBluetoothLeBroadcast", "Error cleaning up LeAudio proxy", th);
            }
        }
    }

    private String getDefaultValueOfProgramInfo() {
        int nextInt = ThreadLocalRandom.current().nextInt(1000, 9999);
        return BluetoothAdapter.getDefaultAdapter().getName() + "_" + nextInt;
    }

    private byte[] getDefaultValueOfBroadcastCode() {
        return generateRandomPassword().getBytes(StandardCharsets.UTF_8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetCacheInfo() {
        setAppSourceName("", true);
        this.mBluetoothLeBroadcastMetadata = null;
        this.mBroadcastId = -1;
    }

    private String generateRandomPassword() {
        String uuid = UUID.randomUUID().toString();
        return uuid.substring(0, 8) + uuid.substring(9, 13);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void registerContentObserver() {
        if (this.mContentResolver == null) {
            Log.d("LocalBluetoothLeBroadcast", "mContentResolver is null");
            return;
        }
        for (Uri uri : SETTINGS_URIS) {
            this.mContentResolver.registerContentObserver(uri, false, this.mSettingsObserver);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void unregisterContentObserver() {
        ContentResolver contentResolver = this.mContentResolver;
        if (contentResolver == null) {
            Log.d("LocalBluetoothLeBroadcast", "mContentResolver is null");
        } else {
            contentResolver.unregisterContentObserver(this.mSettingsObserver);
        }
    }
}
