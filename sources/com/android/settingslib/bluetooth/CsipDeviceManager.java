package com.android.settingslib.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothUuid;
import android.os.ParcelUuid;
import com.android.internal.annotations.VisibleForTesting;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
/* loaded from: classes2.dex */
public class CsipDeviceManager {
    private final LocalBluetoothManager mBtManager;
    private final List<CachedBluetoothDevice> mCachedDevices;

    private static boolean isAtLeastT() {
        return true;
    }

    private boolean isValidGroupId(int i) {
        return i != -1;
    }

    private void log(String str) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CsipDeviceManager(LocalBluetoothManager localBluetoothManager, List<CachedBluetoothDevice> list) {
        this.mBtManager = localBluetoothManager;
        this.mCachedDevices = list;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void initCsipDeviceIfNeeded(CachedBluetoothDevice cachedBluetoothDevice) {
        int baseGroupId = getBaseGroupId(cachedBluetoothDevice.getDevice());
        if (isValidGroupId(baseGroupId)) {
            log("initCsipDeviceIfNeeded: " + cachedBluetoothDevice + " (group: " + baseGroupId + ")");
            cachedBluetoothDevice.setGroupId(baseGroupId);
        }
    }

    private int getBaseGroupId(BluetoothDevice bluetoothDevice) {
        Map<Integer, ParcelUuid> groupUuidMapByDevice;
        CsipSetCoordinatorProfile csipSetCoordinatorProfile = this.mBtManager.getProfileManager().getCsipSetCoordinatorProfile();
        if (csipSetCoordinatorProfile == null || (groupUuidMapByDevice = csipSetCoordinatorProfile.getGroupUuidMapByDevice(bluetoothDevice)) == null) {
            return -1;
        }
        for (Map.Entry<Integer, ParcelUuid> entry : groupUuidMapByDevice.entrySet()) {
            if (entry.getValue().equals(BluetoothUuid.CAP)) {
                return entry.getKey().intValue();
            }
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean setMemberDeviceIfNeeded(CachedBluetoothDevice cachedBluetoothDevice) {
        int groupId = cachedBluetoothDevice.getGroupId();
        if (isValidGroupId(groupId)) {
            CachedBluetoothDevice cachedDevice = getCachedDevice(groupId);
            log("setMemberDeviceIfNeeded, main: " + cachedDevice + ", member: " + cachedBluetoothDevice);
            if (cachedDevice != null) {
                cachedDevice.addMemberDevice(cachedBluetoothDevice);
                cachedBluetoothDevice.setName(cachedDevice.getName());
                return true;
            }
            return false;
        }
        return false;
    }

    public CachedBluetoothDevice getCachedDevice(int i) {
        log("getCachedDevice: groupId: " + i);
        for (int size = this.mCachedDevices.size() + (-1); size >= 0; size--) {
            CachedBluetoothDevice cachedBluetoothDevice = this.mCachedDevices.get(size);
            if (cachedBluetoothDevice.getGroupId() == i) {
                log("getCachedDevice: found cachedDevice with the groupId: " + cachedBluetoothDevice.getDevice().getAnonymizedAddress());
                return cachedBluetoothDevice;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void updateCsipDevices() {
        HashSet<Integer> hashSet = new HashSet();
        for (CachedBluetoothDevice cachedBluetoothDevice : this.mCachedDevices) {
            if (!isValidGroupId(cachedBluetoothDevice.getGroupId())) {
                int baseGroupId = getBaseGroupId(cachedBluetoothDevice.getDevice());
                if (isValidGroupId(baseGroupId)) {
                    cachedBluetoothDevice.setGroupId(baseGroupId);
                    hashSet.add(Integer.valueOf(baseGroupId));
                }
            }
        }
        for (Integer num : hashSet) {
            onGroupIdChanged(num.intValue());
        }
    }

    @VisibleForTesting
    void onGroupIdChanged(final int i) {
        if (!isValidGroupId(i)) {
            log("onGroupIdChanged: groupId is invalid");
            return;
        }
        log("onGroupIdChanged: mCachedDevices list =" + this.mCachedDevices.toString());
        LocalBluetoothProfileManager profileManager = this.mBtManager.getProfileManager();
        CachedBluetoothDeviceManager cachedDeviceManager = this.mBtManager.getCachedDeviceManager();
        LeAudioProfile leAudioProfile = profileManager.getLeAudioProfile();
        BluetoothDevice connectedGroupLeadDevice = (leAudioProfile == null || !isAtLeastT()) ? null : leAudioProfile.getConnectedGroupLeadDevice(i);
        final CachedBluetoothDevice findDevice = connectedGroupLeadDevice != null ? cachedDeviceManager.findDevice(connectedGroupLeadDevice) : null;
        if (findDevice != null) {
            List<CachedBluetoothDevice> list = (List) this.mCachedDevices.stream().filter(new Predicate() { // from class: com.android.settingslib.bluetooth.CsipDeviceManager$$ExternalSyntheticLambda0
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    boolean lambda$onGroupIdChanged$0;
                    lambda$onGroupIdChanged$0 = CsipDeviceManager.lambda$onGroupIdChanged$0(CachedBluetoothDevice.this, i, (CachedBluetoothDevice) obj);
                    return lambda$onGroupIdChanged$0;
                }
            }).collect(Collectors.toList());
            if (list == null || list.isEmpty()) {
                log("onGroupIdChanged: There is no member device in list.");
                return;
            }
            log("onGroupIdChanged: removed from UI device =" + list + ", with groupId=" + i + " mainDevice= " + findDevice);
            for (CachedBluetoothDevice cachedBluetoothDevice : list) {
                Set<CachedBluetoothDevice> memberDevice = cachedBluetoothDevice.getMemberDevice();
                if (!memberDevice.isEmpty()) {
                    log("onGroupIdChanged: Transfer the member list into new main device.");
                    for (CachedBluetoothDevice cachedBluetoothDevice2 : memberDevice) {
                        if (!cachedBluetoothDevice2.equals(findDevice)) {
                            findDevice.addMemberDevice(cachedBluetoothDevice2);
                        }
                    }
                    memberDevice.clear();
                }
                findDevice.addMemberDevice(cachedBluetoothDevice);
                this.mCachedDevices.remove(cachedBluetoothDevice);
                this.mBtManager.getEventManager().dispatchDeviceRemoved(cachedBluetoothDevice);
            }
            if (this.mCachedDevices.contains(findDevice)) {
                return;
            }
            this.mCachedDevices.add(findDevice);
            this.mBtManager.getEventManager().dispatchDeviceAdded(findDevice);
            return;
        }
        log("onGroupIdChanged: There is no main device from the LE profile.");
        int i2 = -1;
        for (int size = this.mCachedDevices.size() - 1; size >= 0; size--) {
            CachedBluetoothDevice cachedBluetoothDevice3 = this.mCachedDevices.get(size);
            if (cachedBluetoothDevice3.getGroupId() == i) {
                if (i2 != -1) {
                    log("onGroupIdChanged: removed from UI device =" + cachedBluetoothDevice3 + ", with groupId=" + i + " firstMatchedIndex=" + i2);
                    findDevice.addMemberDevice(cachedBluetoothDevice3);
                    this.mCachedDevices.remove(size);
                    this.mBtManager.getEventManager().dispatchDeviceRemoved(cachedBluetoothDevice3);
                    return;
                }
                i2 = size;
                findDevice = cachedBluetoothDevice3;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$onGroupIdChanged$0(CachedBluetoothDevice cachedBluetoothDevice, int i, CachedBluetoothDevice cachedBluetoothDevice2) {
        return !cachedBluetoothDevice2.equals(cachedBluetoothDevice) && cachedBluetoothDevice2.getGroupId() == i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean onProfileConnectionStateChangedIfProcessed(CachedBluetoothDevice cachedBluetoothDevice, int i) {
        log("onProfileConnectionStateChangedIfProcessed: " + cachedBluetoothDevice + ", state: " + i);
        if (i != 0) {
            if (i != 2) {
                return false;
            }
            onGroupIdChanged(cachedBluetoothDevice.getGroupId());
            CachedBluetoothDevice findMainDevice = findMainDevice(cachedBluetoothDevice);
            if (findMainDevice != null) {
                if (findMainDevice.isConnected()) {
                    findMainDevice.refresh();
                    return true;
                }
                this.mBtManager.getEventManager().dispatchDeviceRemoved(findMainDevice);
                findMainDevice.switchMemberDeviceContent(cachedBluetoothDevice);
                findMainDevice.refresh();
                this.mBtManager.getEventManager().dispatchDeviceAdded(findMainDevice);
                return true;
            }
            return false;
        }
        CachedBluetoothDevice findMainDevice2 = findMainDevice(cachedBluetoothDevice);
        if (findMainDevice2 != null) {
            findMainDevice2.refresh();
            return true;
        }
        Set<CachedBluetoothDevice> memberDevice = cachedBluetoothDevice.getMemberDevice();
        if (memberDevice.isEmpty()) {
            return false;
        }
        for (CachedBluetoothDevice cachedBluetoothDevice2 : memberDevice) {
            if (cachedBluetoothDevice2.isConnected()) {
                log("set device: " + cachedBluetoothDevice2 + " as the main device");
                this.mBtManager.getEventManager().dispatchDeviceRemoved(cachedBluetoothDevice);
                cachedBluetoothDevice.switchMemberDeviceContent(cachedBluetoothDevice2);
                cachedBluetoothDevice.refresh();
                this.mBtManager.getEventManager().dispatchDeviceAdded(cachedBluetoothDevice);
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CachedBluetoothDevice findMainDevice(CachedBluetoothDevice cachedBluetoothDevice) {
        List<CachedBluetoothDevice> list;
        if (cachedBluetoothDevice != null && (list = this.mCachedDevices) != null) {
            for (CachedBluetoothDevice cachedBluetoothDevice2 : list) {
                if (isValidGroupId(cachedBluetoothDevice2.getGroupId())) {
                    Set<CachedBluetoothDevice> memberDevice = cachedBluetoothDevice2.getMemberDevice();
                    if (memberDevice.isEmpty()) {
                        continue;
                    } else {
                        for (CachedBluetoothDevice cachedBluetoothDevice3 : memberDevice) {
                            if (cachedBluetoothDevice3 != null && cachedBluetoothDevice3.equals(cachedBluetoothDevice)) {
                                return cachedBluetoothDevice2;
                            }
                        }
                        continue;
                    }
                }
            }
        }
        return null;
    }

    public boolean isExistedGroupId(int i) {
        return getCachedDevice(i) != null;
    }
}
