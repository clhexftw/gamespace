package com.android.settings.users;

import android.content.Context;
import android.content.IntentFilter;
import androidx.preference.Preference;
import com.android.settings.core.BasePreferenceController;
/* loaded from: classes.dex */
public class MultiUserTopIntroPreferenceController extends BasePreferenceController {
    final UserCapabilities mUserCaps;

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

    public MultiUserTopIntroPreferenceController(Context context, String str) {
        super(context, str);
        this.mUserCaps = UserCapabilities.create(context);
    }

    @Override // com.android.settings.core.BasePreferenceController
    public int getAvailabilityStatus() {
        UserCapabilities userCapabilities = this.mUserCaps;
        return (!userCapabilities.mEnabled || userCapabilities.mUserSwitcherEnabled) ? 4 : 1;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        this.mUserCaps.updateAddUserCapabilities(this.mContext);
        preference.setVisible(isAvailable());
    }
}
