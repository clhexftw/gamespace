package com.android.settings.fuelgauge.reversecharging;

import android.content.Context;
import android.content.IntentFilter;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.R;
import com.android.settings.core.BasePreferenceController;
import com.android.settings.widget.CardPreference;
import org.nameless.battery.WirelessChargingSettingsHelper;
/* loaded from: classes.dex */
public class ReverseCharingSuspendPreferenceController extends BasePreferenceController {
    private final Context mContext;
    private CardPreference mPreference;

    @Override // com.android.settings.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return 0;
    }

    @Override // com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ Class getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ int getSliceHighlightMenuRes() {
        return super.getSliceHighlightMenuRes();
    }

    @Override // com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isPublicSlice() {
        return super.isPublicSlice();
    }

    @Override // com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isSliceable() {
        return super.isSliceable();
    }

    @Override // com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public ReverseCharingSuspendPreferenceController(Context context, String str) {
        super(context, str);
        this.mContext = context;
    }

    @Override // com.android.settings.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreference = (CardPreference) preferenceScreen.findPreference(getPreferenceKey());
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public final void updateState(Preference preference) {
        int reverseChargingSuspendedStatus = WirelessChargingSettingsHelper.getReverseChargingSuspendedStatus(this.mContext);
        this.mPreference.setVisible(WirelessChargingSettingsHelper.getReverseChargingEnabled(this.mContext) && reverseChargingSuspendedStatus != 0);
        if (reverseChargingSuspendedStatus == 1) {
            this.mPreference.setTitle(R.string.wireless_reverse_charging_suspended_charging);
        } else if (reverseChargingSuspendedStatus == 2) {
            this.mPreference.setTitle(R.string.wireless_reverse_charging_suspended_low_level);
        } else if (reverseChargingSuspendedStatus != 3) {
        } else {
            this.mPreference.setTitle(R.string.wireless_reverse_charging_suspended_power_save);
        }
    }
}
