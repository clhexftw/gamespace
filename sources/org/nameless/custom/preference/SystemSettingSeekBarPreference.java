package org.nameless.custom.preference;

import android.content.Context;
import android.util.AttributeSet;
/* loaded from: classes2.dex */
public class SystemSettingSeekBarPreference extends CustomSeekBarPreference {
    public SystemSettingSeekBarPreference(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setPreferenceDataStore(new SystemSettingsStore(context.getContentResolver()));
    }

    public SystemSettingSeekBarPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setPreferenceDataStore(new SystemSettingsStore(context.getContentResolver()));
    }

    public SystemSettingSeekBarPreference(Context context) {
        super(context, null);
        setPreferenceDataStore(new SystemSettingsStore(context.getContentResolver()));
    }
}
