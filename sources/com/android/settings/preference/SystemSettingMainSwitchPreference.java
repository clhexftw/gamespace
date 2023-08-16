package com.android.settings.preference;

import android.content.Context;
import android.util.AttributeSet;
import com.android.settingslib.widget.MainSwitchPreference;
import org.nameless.custom.preference.SystemSettingsStore;
/* loaded from: classes.dex */
public class SystemSettingMainSwitchPreference extends MainSwitchPreference {
    public SystemSettingMainSwitchPreference(Context context) {
        super(context);
        setPreferenceDataStore(new SystemSettingsStore(context.getContentResolver()));
    }

    public SystemSettingMainSwitchPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setPreferenceDataStore(new SystemSettingsStore(context.getContentResolver()));
    }

    public SystemSettingMainSwitchPreference(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setPreferenceDataStore(new SystemSettingsStore(context.getContentResolver()));
    }

    public SystemSettingMainSwitchPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        setPreferenceDataStore(new SystemSettingsStore(context.getContentResolver()));
    }
}
