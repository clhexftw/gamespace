package com.android.settingslib.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothLeAudioCodecConfigMetadata;
import android.bluetooth.BluetoothLeAudioContentMetadata;
import android.bluetooth.BluetoothLeBroadcastChannel;
import android.bluetooth.BluetoothLeBroadcastMetadata;
import android.bluetooth.BluetoothLeBroadcastSubgroup;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/* loaded from: classes2.dex */
public class LocalBluetoothLeBroadcastMetadata {
    private long mAudioLocation;
    private byte[] mBroadcastCode;
    private int mBroadcastId;
    private BluetoothLeBroadcastChannel mChannel;
    private int mChannelIndex;
    private long mCodecId;
    private BluetoothLeAudioCodecConfigMetadata mConfigMetadata;
    private BluetoothLeAudioContentMetadata mContentMetadata;
    private boolean mIsEncrypted;
    private boolean mIsSelected;
    private String mLanguage;
    private int mPaSyncInterval;
    private int mPresentationDelayMicros;
    private String mProgramInfo;
    private int mSourceAddressType;
    private int mSourceAdvertisingSid;
    private BluetoothDevice mSourceDevice;
    private BluetoothLeBroadcastSubgroup mSubgroup;

    public BluetoothLeBroadcastMetadata convertToBroadcastMetadata(String str) {
        Matcher matcher = Pattern.compile("<(.*?)>").matcher(str);
        if (matcher.find()) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(matcher.group(1));
            this.mSourceAddressType = Integer.parseInt((String) arrayList.get(0));
            this.mSourceDevice = BluetoothAdapter.getDefaultAdapter().getRemoteDevice((String) arrayList.get(1));
            this.mSourceAdvertisingSid = Integer.parseInt((String) arrayList.get(2));
            this.mBroadcastId = Integer.parseInt((String) arrayList.get(3));
            this.mPaSyncInterval = Integer.parseInt((String) arrayList.get(4));
            this.mIsEncrypted = Boolean.valueOf((String) arrayList.get(5)).booleanValue();
            this.mBroadcastCode = ((String) arrayList.get(6)).getBytes();
            this.mPresentationDelayMicros = Integer.parseInt((String) arrayList.get(7));
            this.mSubgroup = convertToSubgroup((String) arrayList.get(8));
            return new BluetoothLeBroadcastMetadata.Builder().setSourceDevice(this.mSourceDevice, this.mSourceAddressType).setSourceAdvertisingSid(this.mSourceAdvertisingSid).setBroadcastId(this.mBroadcastId).setPaSyncInterval(this.mPaSyncInterval).setEncrypted(this.mIsEncrypted).setBroadcastCode(this.mBroadcastCode).setPresentationDelayMicros(this.mPresentationDelayMicros).addSubgroup(this.mSubgroup).build();
        }
        return null;
    }

    private BluetoothLeBroadcastSubgroup convertToSubgroup(String str) {
        Matcher matcher = Pattern.compile("<(.*?)>").matcher(str);
        if (matcher.find()) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(matcher.group(1));
            this.mCodecId = Long.getLong((String) arrayList.get(0)).longValue();
            this.mConfigMetadata = convertToConfigMetadata((String) arrayList.get(1));
            this.mContentMetadata = convertToContentMetadata((String) arrayList.get(2));
            this.mChannel = convertToChannel((String) arrayList.get(3), this.mConfigMetadata);
            return new BluetoothLeBroadcastSubgroup.Builder().setCodecId(this.mCodecId).setCodecSpecificConfig(this.mConfigMetadata).setContentMetadata(this.mContentMetadata).addChannel(this.mChannel).build();
        }
        return null;
    }

    private BluetoothLeAudioCodecConfigMetadata convertToConfigMetadata(String str) {
        Matcher matcher = Pattern.compile("<(.*?)>").matcher(str);
        if (matcher.find()) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(matcher.group(1));
            this.mAudioLocation = Long.getLong((String) arrayList.get(0)).longValue();
            return new BluetoothLeAudioCodecConfigMetadata.Builder().setAudioLocation(this.mAudioLocation).build();
        }
        return null;
    }

    private BluetoothLeAudioContentMetadata convertToContentMetadata(String str) {
        Matcher matcher = Pattern.compile("<(.*?)>").matcher(str);
        if (matcher.find()) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(matcher.group(1));
            this.mProgramInfo = (String) arrayList.get(0);
            this.mLanguage = (String) arrayList.get(1);
            return new BluetoothLeAudioContentMetadata.Builder().setProgramInfo(this.mProgramInfo).setLanguage(this.mLanguage).build();
        }
        return null;
    }

    private BluetoothLeBroadcastChannel convertToChannel(String str, BluetoothLeAudioCodecConfigMetadata bluetoothLeAudioCodecConfigMetadata) {
        Matcher matcher = Pattern.compile("<(.*?)>").matcher(str);
        if (matcher.find()) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(matcher.group(1));
            this.mIsSelected = Boolean.valueOf((String) arrayList.get(0)).booleanValue();
            this.mChannelIndex = Integer.parseInt((String) arrayList.get(1));
            return new BluetoothLeBroadcastChannel.Builder().setSelected(this.mIsSelected).setChannelIndex(this.mChannelIndex).setCodecMetadata(bluetoothLeAudioCodecConfigMetadata).build();
        }
        return null;
    }
}
