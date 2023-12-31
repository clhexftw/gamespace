package com.android.settings.location;

import android.content.Context;
import android.content.IntentFilter;
import android.os.UserManager;
import android.text.TextUtils;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.R;
import com.android.settings.Utils;
import com.android.settingslib.RestrictedLockUtils;
import com.android.settingslib.RestrictedSwitchPreference;
/* loaded from: classes.dex */
public class LocationForWorkPreferenceController extends LocationBasePreferenceController {
    private RestrictedSwitchPreference mPreference;

    @Override // com.android.settings.location.LocationBasePreferenceController, com.android.settings.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return 0;
    }

    @Override // com.android.settings.location.LocationBasePreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ Class getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.settings.location.LocationBasePreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.settings.location.LocationBasePreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ int getSliceHighlightMenuRes() {
        return super.getSliceHighlightMenuRes();
    }

    @Override // com.android.settings.location.LocationBasePreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.settings.location.LocationBasePreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isPublicSlice() {
        return super.isPublicSlice();
    }

    @Override // com.android.settings.location.LocationBasePreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isSliceable() {
        return super.isSliceable();
    }

    @Override // com.android.settings.location.LocationBasePreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public LocationForWorkPreferenceController(Context context, String str) {
        super(context, str);
    }

    @Override // com.android.settings.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public boolean handlePreferenceTreeClick(Preference preference) {
        if (TextUtils.equals(preference.getKey(), getPreferenceKey())) {
            boolean isChecked = this.mPreference.isChecked();
            UserManager userManager = this.mUserManager;
            userManager.setUserRestriction("no_share_location", !isChecked, Utils.getManagedProfile(userManager));
            this.mPreference.setSummary(isChecked ? R.string.switch_on_text : R.string.switch_off_text);
            return true;
        }
        return false;
    }

    @Override // com.android.settings.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreference = (RestrictedSwitchPreference) preferenceScreen.findPreference(getPreferenceKey());
    }

    @Override // com.android.settings.location.LocationBasePreferenceController, com.android.settings.location.LocationEnabler.LocationModeChangeListener
    public void onLocationModeChanged(int i, boolean z) {
        int i2;
        if (this.mPreference.isVisible() && isAvailable()) {
            RestrictedLockUtils.EnforcedAdmin shareLocationEnforcedAdmin = this.mLocationEnabler.getShareLocationEnforcedAdmin(Utils.getManagedProfile(this.mUserManager).getIdentifier());
            if (shareLocationEnforcedAdmin != null) {
                this.mPreference.setDisabledByAdmin(shareLocationEnforcedAdmin);
                return;
            }
            boolean isEnabled = this.mLocationEnabler.isEnabled(i);
            this.mPreference.setEnabled(isEnabled);
            if (this.mLocationEnabler.isManagedProfileRestrictedByBase() || !isEnabled) {
                this.mPreference.setChecked(false);
                if (isEnabled) {
                    i2 = R.string.switch_off_text;
                } else {
                    i2 = R.string.location_app_permission_summary_location_off;
                }
            } else {
                this.mPreference.setChecked(true);
                i2 = R.string.switch_on_text;
            }
            this.mPreference.setSummary(i2);
        }
    }
}
