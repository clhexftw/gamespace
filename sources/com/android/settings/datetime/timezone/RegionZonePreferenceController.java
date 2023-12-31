package com.android.settings.datetime.timezone;

import android.content.Context;
import android.content.IntentFilter;
import androidx.preference.Preference;
import com.android.settings.R;
/* loaded from: classes.dex */
public class RegionZonePreferenceController extends BaseTimeZonePreferenceController {
    private static final String PREFERENCE_KEY = "region_zone";
    private boolean mIsClickable;
    private TimeZoneInfo mTimeZoneInfo;

    @Override // com.android.settings.datetime.timezone.BaseTimeZonePreferenceController, com.android.settings.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return 0;
    }

    @Override // com.android.settings.datetime.timezone.BaseTimeZonePreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ Class getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.settings.datetime.timezone.BaseTimeZonePreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.settings.datetime.timezone.BaseTimeZonePreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ int getSliceHighlightMenuRes() {
        return super.getSliceHighlightMenuRes();
    }

    @Override // com.android.settings.datetime.timezone.BaseTimeZonePreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.settings.datetime.timezone.BaseTimeZonePreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isPublicSlice() {
        return super.isPublicSlice();
    }

    @Override // com.android.settings.datetime.timezone.BaseTimeZonePreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isSliceable() {
        return super.isSliceable();
    }

    @Override // com.android.settings.datetime.timezone.BaseTimeZonePreferenceController, com.android.settings.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public RegionZonePreferenceController(Context context) {
        super(context, PREFERENCE_KEY);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        super.updateState(preference);
        preference.setEnabled(isClickable());
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public CharSequence getSummary() {
        return this.mTimeZoneInfo == null ? "" : SpannableUtil.getResourcesText(this.mContext.getResources(), R.string.zone_info_exemplar_location_and_offset, this.mTimeZoneInfo.getExemplarLocation(), this.mTimeZoneInfo.getGmtOffset());
    }

    public void setTimeZoneInfo(TimeZoneInfo timeZoneInfo) {
        this.mTimeZoneInfo = timeZoneInfo;
    }

    public TimeZoneInfo getTimeZoneInfo() {
        return this.mTimeZoneInfo;
    }

    public void setClickable(boolean z) {
        this.mIsClickable = z;
    }

    public boolean isClickable() {
        return this.mIsClickable;
    }
}
