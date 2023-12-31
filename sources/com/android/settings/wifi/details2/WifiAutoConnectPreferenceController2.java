package com.android.settings.wifi.details2;

import android.content.Context;
import android.content.IntentFilter;
import com.android.settings.R;
import com.android.settings.core.TogglePreferenceController;
import com.android.wifitrackerlib.WifiEntry;
/* loaded from: classes.dex */
public class WifiAutoConnectPreferenceController2 extends TogglePreferenceController {
    private static final String KEY_AUTO_CONNECT = "auto_connect";
    private WifiEntry mWifiEntry;

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

    public WifiAutoConnectPreferenceController2(Context context) {
        super(context, KEY_AUTO_CONNECT);
    }

    public void setWifiEntry(WifiEntry wifiEntry) {
        this.mWifiEntry = wifiEntry;
    }

    @Override // com.android.settings.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return this.mWifiEntry.canSetAutoJoinEnabled() ? 0 : 2;
    }

    @Override // com.android.settings.core.TogglePreferenceController
    public boolean isChecked() {
        return this.mWifiEntry.isAutoJoinEnabled();
    }

    @Override // com.android.settings.core.TogglePreferenceController
    public boolean setChecked(boolean z) {
        this.mWifiEntry.setAutoJoinEnabled(z);
        return true;
    }

    @Override // com.android.settings.core.TogglePreferenceController, com.android.settings.slices.Sliceable
    public int getSliceHighlightMenuRes() {
        return R.string.menu_key_network;
    }
}
