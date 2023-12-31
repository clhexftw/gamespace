package com.android.settings.privacy;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.Context;
import android.content.IntentFilter;
import android.view.accessibility.AccessibilityManager;
import androidx.preference.Preference;
import com.android.settings.R;
import com.android.settings.core.BasePreferenceController;
import java.util.List;
/* loaded from: classes.dex */
public class AccessibilityUsagePreferenceController extends BasePreferenceController {
    private final AccessibilityManager mAccessibilityManager;
    private List<AccessibilityServiceInfo> mEnabledServiceInfos;

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

    public AccessibilityUsagePreferenceController(Context context, String str) {
        super(context, str);
        AccessibilityManager accessibilityManager = (AccessibilityManager) this.mContext.getSystemService(AccessibilityManager.class);
        this.mAccessibilityManager = accessibilityManager;
        this.mEnabledServiceInfos = accessibilityManager.getEnabledAccessibilityServiceList(-1);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        super.updateState(preference);
        List<AccessibilityServiceInfo> enabledAccessibilityServiceList = this.mAccessibilityManager.getEnabledAccessibilityServiceList(-1);
        this.mEnabledServiceInfos = enabledAccessibilityServiceList;
        if (enabledAccessibilityServiceList.isEmpty()) {
            preference.setVisible(false);
        }
    }

    @Override // com.android.settings.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return this.mEnabledServiceInfos.isEmpty() ? 3 : 0;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public CharSequence getSummary() {
        return this.mContext.getResources().getQuantityString(R.plurals.accessibility_usage_summary, this.mEnabledServiceInfos.size(), Integer.valueOf(this.mEnabledServiceInfos.size()));
    }
}
