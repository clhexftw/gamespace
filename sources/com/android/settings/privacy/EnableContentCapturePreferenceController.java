package com.android.settings.privacy;

import android.content.Context;
import android.content.IntentFilter;
import android.os.UserHandle;
import android.os.UserManager;
import com.android.settings.R;
import com.android.settings.core.TogglePreferenceController;
import com.android.settings.utils.ContentCaptureUtils;
/* loaded from: classes.dex */
public final class EnableContentCapturePreferenceController extends TogglePreferenceController {
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

    public EnableContentCapturePreferenceController(Context context, String str) {
        super(context, str);
    }

    @Override // com.android.settings.core.TogglePreferenceController
    public boolean isChecked() {
        return ContentCaptureUtils.isEnabledForUser(this.mContext);
    }

    @Override // com.android.settings.core.TogglePreferenceController
    public boolean setChecked(boolean z) {
        ContentCaptureUtils.setEnabledForUser(this.mContext, z);
        return true;
    }

    @Override // com.android.settings.core.BasePreferenceController
    public int getAvailabilityStatus() {
        if (ContentCaptureUtils.isFeatureAvailable() && ContentCaptureUtils.getServiceSettingsComponentName() == null) {
            return UserManager.get(this.mContext).hasUserRestrictionForUser("no_content_capture", UserHandle.of(UserHandle.myUserId())) ? 4 : 0;
        }
        return 3;
    }

    @Override // com.android.settings.core.TogglePreferenceController, com.android.settings.slices.Sliceable
    public int getSliceHighlightMenuRes() {
        return R.string.menu_key_privacy;
    }
}
