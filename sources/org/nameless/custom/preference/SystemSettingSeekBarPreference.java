package org.nameless.custom.preference;

import android.content.Context;
import android.util.AttributeSet;
/* loaded from: classes.dex */
public class SystemSettingSeekBarPreference extends CustomSeekBarPreference {
    public SystemSettingSeekBarPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setPreferenceDataStore(new SystemSettingsStore(context.getContentResolver()));
    }
}
