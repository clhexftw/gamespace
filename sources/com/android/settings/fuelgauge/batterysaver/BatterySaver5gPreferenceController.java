package com.android.settings.fuelgauge.batterysaver;

import android.content.Context;
import android.content.IntentFilter;
import android.provider.Settings;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import com.android.settings.R;
import com.android.settings.core.TogglePreferenceController;
import java.util.Arrays;
import java.util.function.IntPredicate;
/* loaded from: classes.dex */
public class BatterySaver5gPreferenceController extends TogglePreferenceController {
    private final SubscriptionManager mSubscriptionManager;
    private final TelephonyManager mTelephonyManager;

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

    public BatterySaver5gPreferenceController(Context context, String str) {
        super(context, str);
        this.mSubscriptionManager = (SubscriptionManager) context.getSystemService(SubscriptionManager.class);
        this.mTelephonyManager = (TelephonyManager) context.getSystemService(TelephonyManager.class);
    }

    @Override // com.android.settings.core.TogglePreferenceController
    public boolean isChecked() {
        return Settings.System.getIntForUser(this.mContext.getContentResolver(), "low_power_disable_5g", 0, -2) == 1;
    }

    @Override // com.android.settings.core.TogglePreferenceController
    public boolean setChecked(boolean z) {
        return Settings.System.putIntForUser(this.mContext.getContentResolver(), "low_power_disable_5g", z ? 1 : 0, -2);
    }

    @Override // com.android.settings.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return Arrays.stream(this.mSubscriptionManager.getActiveSubscriptionIdList()).anyMatch(new IntPredicate() { // from class: com.android.settings.fuelgauge.batterysaver.BatterySaver5gPreferenceController$$ExternalSyntheticLambda0
            @Override // java.util.function.IntPredicate
            public final boolean test(int i) {
                boolean is5gAvailable;
                is5gAvailable = BatterySaver5gPreferenceController.this.is5gAvailable(i);
                return is5gAvailable;
            }
        }) ? 0 : 3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean is5gAvailable(int i) {
        return (this.mTelephonyManager.createForSubscriptionId(i).getSupportedRadioAccessFamily() & 524288) != 0;
    }

    @Override // com.android.settings.core.TogglePreferenceController, com.android.settings.slices.Sliceable
    public int getSliceHighlightMenuRes() {
        return R.string.menu_key_battery;
    }
}
