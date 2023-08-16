package com.android.settings.display.refreshrate;

import android.app.ActivityManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.provider.Settings;
import androidx.appcompat.app.AlertDialog;
import com.android.settings.R;
import com.android.settings.core.TogglePreferenceController;
import com.android.settings.display.iris.FeaturesHolder;
import org.nameless.display.RefreshRateManager;
/* loaded from: classes.dex */
public class ExtremeRefreshRatePreferenceController extends TogglePreferenceController {
    private static final String IRIS_SERVICE_CLASS_NAME = "org.nameless.iris.service.IrisService";
    private static final String KEY = "extreme_refresh_rate";
    private final ActivityManager mActivityManager;
    private final RefreshRateManager mRefreshRateManager;

    @Override // com.android.settings.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return 0;
    }

    @Override // com.android.settings.core.TogglePreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ Class getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.settings.core.TogglePreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.settings.core.TogglePreferenceController, com.android.settings.slices.Sliceable
    public int getSliceHighlightMenuRes() {
        return 0;
    }

    @Override // com.android.settings.core.TogglePreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.settings.core.TogglePreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public ExtremeRefreshRatePreferenceController(Context context, String str) {
        super(context, str);
        this.mActivityManager = (ActivityManager) context.getSystemService(ActivityManager.class);
        this.mRefreshRateManager = (RefreshRateManager) context.getSystemService(RefreshRateManager.class);
    }

    @Override // com.android.settings.core.TogglePreferenceController
    public boolean isChecked() {
        return Settings.System.getIntForUser(this.mContext.getContentResolver(), KEY, 0, 0) == 1;
    }

    @Override // com.android.settings.core.TogglePreferenceController
    public boolean setChecked(boolean z) {
        int i;
        boolean z2 = true;
        if (!z) {
            this.mRefreshRateManager.setExtremeRefreshRateEnabled(false);
            return true;
        }
        if (!isIrisServiceRunning() || !supportIrisFeature()) {
            z2 = false;
        }
        AlertDialog.Builder title = new AlertDialog.Builder(this.mContext).setTitle(this.mContext.getText(R.string.confirm_before_enable_title));
        Context context = this.mContext;
        if (z2) {
            i = R.string.extreme_refresh_rate_warning_iris;
        } else {
            i = R.string.extreme_refresh_rate_warning;
        }
        title.setMessage(context.getText(i)).setPositiveButton(R.string.okay, new DialogInterface.OnClickListener() { // from class: com.android.settings.display.refreshrate.ExtremeRefreshRatePreferenceController.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                ExtremeRefreshRatePreferenceController.this.mRefreshRateManager.setExtremeRefreshRateEnabled(true);
            }
        }).setNegativeButton(R.string.cancel, (DialogInterface.OnClickListener) null).show();
        return isChecked();
    }

    private boolean isIrisServiceRunning() {
        for (ActivityManager.RunningServiceInfo runningServiceInfo : this.mActivityManager.getRunningServices(Integer.MAX_VALUE)) {
            if (IRIS_SERVICE_CLASS_NAME.equals(runningServiceInfo.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    private boolean supportIrisFeature() {
        return FeaturesHolder.MEMC_FHD_SUPPORTED || FeaturesHolder.MEMC_QHD_SUPPORTED || FeaturesHolder.SDR2HDR_SUPPORTED;
    }
}
