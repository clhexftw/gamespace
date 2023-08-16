package com.android.settings.datetime;

import android.app.time.TimeManager;
import android.content.Context;
import androidx.preference.Preference;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settingslib.RestrictedPreference;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.datetime.ZoneGetter;
import java.util.Calendar;
/* loaded from: classes.dex */
public class TimeZonePreferenceController extends AbstractPreferenceController implements PreferenceControllerMixin {
    private final TimeManager mTimeManager;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "timezone";
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return true;
    }

    public TimeZonePreferenceController(Context context) {
        super(context);
        this.mTimeManager = (TimeManager) context.getSystemService(TimeManager.class);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        if (preference instanceof RestrictedPreference) {
            preference.setSummary(getTimeZoneOffsetAndName());
            if (((RestrictedPreference) preference).isDisabledByAdmin()) {
                return;
            }
            preference.setEnabled(shouldEnableManualTimeZoneSelection());
        }
    }

    CharSequence getTimeZoneOffsetAndName() {
        Calendar calendar = Calendar.getInstance();
        return ZoneGetter.getTimeZoneOffsetAndName(this.mContext, calendar.getTimeZone(), calendar.getTime());
    }

    private boolean shouldEnableManualTimeZoneSelection() {
        return this.mTimeManager.getTimeZoneCapabilitiesAndConfig().getCapabilities().getSuggestManualTimeZoneCapability() == 40;
    }
}
