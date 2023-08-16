package com.android.settings.preference;

import android.content.Context;
import android.util.AttributeSet;
import com.android.settingslib.PrimarySwitchPreference;
import org.nameless.custom.preference.SystemSettingsStore;
/* loaded from: classes.dex */
public class SystemSettingPrimarySwitchPreference extends PrimarySwitchPreference {
    public SystemSettingPrimarySwitchPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        setPreferenceDataStore(new SystemSettingsStore(context.getContentResolver()));
    }

    public SystemSettingPrimarySwitchPreference(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setPreferenceDataStore(new SystemSettingsStore(context.getContentResolver()));
    }

    public SystemSettingPrimarySwitchPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setPreferenceDataStore(new SystemSettingsStore(context.getContentResolver()));
    }
}
