package com.android.settings.display;

import android.content.Context;
import android.content.IntentFilter;
import android.hardware.display.ColorDisplayManager;
import com.android.settings.core.BasePreferenceController;
/* loaded from: classes.dex */
public class ColorModePreferenceController extends BasePreferenceController {
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

    public ColorModePreferenceController(Context context, String str) {
        super(context, str);
    }

    @Override // com.android.settings.core.BasePreferenceController
    public int getAvailabilityStatus() {
        int[] intArray = this.mContext.getResources().getIntArray(17235996);
        return (hasCustomColorMode(intArray) || (intArray.length > 0 && ((ColorDisplayManager) this.mContext.getSystemService(ColorDisplayManager.class)).isDeviceColorManaged() && !ColorDisplayManager.areAccessibilityTransformsEnabled(this.mContext))) ? 0 : 4;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public CharSequence getSummary() {
        return ColorModeUtils.getColorModeMapping(this.mContext.getResources()).get(Integer.valueOf(getColorMode()));
    }

    public int getColorMode() {
        return ((ColorDisplayManager) this.mContext.getSystemService(ColorDisplayManager.class)).getColorMode();
    }

    private static boolean hasCustomColorMode(int[] iArr) {
        if (iArr == null) {
            return false;
        }
        for (int i : iArr) {
            if (i >= 10001) {
                return true;
            }
        }
        return false;
    }
}
