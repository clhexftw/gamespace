package com.android.settings.preference;

import android.content.Context;
import android.util.AttributeSet;
import com.android.settingslib.widget.MainSwitchPreference;
import org.nameless.custom.preference.GlobalSettingsStore;
/* loaded from: classes.dex */
public class GlobalSettingMainSwitchPreference extends MainSwitchPreference {
    public GlobalSettingMainSwitchPreference(Context context) {
        super(context);
        setPreferenceDataStore(new GlobalSettingsStore(context.getContentResolver()));
    }

    public GlobalSettingMainSwitchPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setPreferenceDataStore(new GlobalSettingsStore(context.getContentResolver()));
    }

    public GlobalSettingMainSwitchPreference(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setPreferenceDataStore(new GlobalSettingsStore(context.getContentResolver()));
    }

    public GlobalSettingMainSwitchPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        setPreferenceDataStore(new GlobalSettingsStore(context.getContentResolver()));
    }
}
