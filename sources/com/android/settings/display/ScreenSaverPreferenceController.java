package com.android.settings.display;

import android.content.Context;
import android.content.IntentFilter;
import com.android.settings.R;
import com.android.settings.Utils;
import com.android.settings.core.BasePreferenceController;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settings.dream.DreamSettings;
/* loaded from: classes.dex */
public class ScreenSaverPreferenceController extends BasePreferenceController implements PreferenceControllerMixin {
    private final boolean mDreamsDisabledByAmbientModeSuppression;

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

    public ScreenSaverPreferenceController(Context context, String str) {
        super(context, str);
        this.mDreamsDisabledByAmbientModeSuppression = context.getResources().getBoolean(17891621);
    }

    @Override // com.android.settings.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return Utils.areDreamsAvailableToCurrentUser(this.mContext) ? 0 : 3;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public CharSequence getSummary() {
        if (this.mDreamsDisabledByAmbientModeSuppression && AmbientDisplayAlwaysOnPreferenceController.isAodSuppressedByBedtime(this.mContext)) {
            return this.mContext.getString(R.string.screensaver_settings_when_to_dream_bedtime);
        }
        return DreamSettings.getSummaryTextWithDreamName(this.mContext);
    }
}
