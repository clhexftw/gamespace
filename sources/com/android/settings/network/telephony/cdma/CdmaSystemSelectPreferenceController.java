package com.android.settings.network.telephony.cdma;

import android.content.Context;
import android.content.IntentFilter;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import com.android.settings.network.telephony.MobileNetworkUtils;
/* loaded from: classes.dex */
public class CdmaSystemSelectPreferenceController extends CdmaBasePreferenceController implements Preference.OnPreferenceChangeListener {
    private static final String TAG = "CdmaSystemSelectPreferenceController";

    @Override // com.android.settings.network.telephony.cdma.CdmaBasePreferenceController, com.android.settings.network.telephony.TelephonyBasePreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ Class getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.settings.network.telephony.cdma.CdmaBasePreferenceController, com.android.settings.network.telephony.TelephonyBasePreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.settings.network.telephony.cdma.CdmaBasePreferenceController, com.android.settings.network.telephony.TelephonyBasePreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ int getSliceHighlightMenuRes() {
        return super.getSliceHighlightMenuRes();
    }

    @Override // com.android.settings.network.telephony.cdma.CdmaBasePreferenceController, com.android.settings.network.telephony.TelephonyBasePreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.settings.network.telephony.cdma.CdmaBasePreferenceController, com.android.settings.network.telephony.TelephonyBasePreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isPublicSlice() {
        return super.isPublicSlice();
    }

    @Override // com.android.settings.network.telephony.cdma.CdmaBasePreferenceController, com.android.settings.network.telephony.TelephonyBasePreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isSliceable() {
        return super.isSliceable();
    }

    @Override // com.android.settings.network.telephony.cdma.CdmaBasePreferenceController, com.android.settings.network.telephony.TelephonyBasePreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public CdmaSystemSelectPreferenceController(Context context, String str) {
        super(context, str);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        int cdmaRoamingMode;
        super.updateState(preference);
        ListPreference listPreference = (ListPreference) preference;
        boolean z = true;
        boolean z2 = getAvailabilityStatus() == 0;
        listPreference.setVisible(z2);
        if (z2) {
            TelephonyManager telephonyManager = this.mTelephonyManager;
            boolean z3 = telephonyManager != null;
            if (z3) {
                try {
                    cdmaRoamingMode = telephonyManager.getCdmaRoamingMode();
                } catch (Exception e) {
                    Log.e(TAG, "Fail to access framework API", e);
                    return;
                }
            } else {
                cdmaRoamingMode = -1;
            }
            if (cdmaRoamingMode != -1) {
                if (cdmaRoamingMode != 0 && cdmaRoamingMode != 2) {
                    resetCdmaRoamingModeToDefault();
                }
                listPreference.setValue(Integer.toString(cdmaRoamingMode));
            }
            int networkTypeFromRaf = z3 ? MobileNetworkUtils.getNetworkTypeFromRaf((int) this.mTelephonyManager.getAllowedNetworkTypesForReason(0)) : -1;
            if (networkTypeFromRaf == 9 || networkTypeFromRaf == 26) {
                z = false;
            }
            listPreference.setEnabled(z);
        }
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        int parseInt = Integer.parseInt((String) obj);
        try {
            this.mTelephonyManager.setCdmaRoamingMode(parseInt);
            Settings.Global.putInt(this.mContext.getContentResolver(), "roaming_settings", parseInt);
            return true;
        } catch (IllegalStateException unused) {
            return false;
        }
    }

    private void resetCdmaRoamingModeToDefault() {
        ((ListPreference) this.mPreference).setValue(Integer.toString(2));
        Settings.Global.putInt(this.mContext.getContentResolver(), "roaming_settings", 2);
        this.mTelephonyManager.setCdmaRoamingMode(2);
    }
}
