package com.android.settings.fuelgauge.optimizedcharge;

import android.content.Context;
import android.content.IntentFilter;
import android.text.format.DateFormat;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.core.BasePreferenceController;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import org.nameless.battery.OptimizedChargingSettingsHelper;
/* loaded from: classes.dex */
public class OptimizedChargeEndTimePreferenceController extends BasePreferenceController {
    private final Context mContext;

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

    public OptimizedChargeEndTimePreferenceController(Context context, String str) {
        super(context, str);
        this.mContext = context;
    }

    @Override // com.android.settings.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        updateState(preferenceScreen.findPreference(getPreferenceKey()));
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public final void updateState(Preference preference) {
        preference.setVisible(OptimizedChargingSettingsHelper.getOptimizedChargingEnabled(this.mContext) && OptimizedChargingSettingsHelper.getOptimizedChargingStatus(this.mContext) == 1);
        preference.setSummary(LocalTime.parse(OptimizedChargingSettingsHelper.getOptimizedChargingTime(this.mContext).split(",")[1], DateTimeFormatter.ofPattern("HH:mm")).format(DateTimeFormatter.ofPattern(DateFormat.is24HourFormat(this.mContext) ? "HH:mm" : "hh:mm a")));
    }
}
