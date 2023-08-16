package org.nameless.custom.preference;

import android.content.Context;
import android.util.AttributeSet;
/* loaded from: classes.dex */
public class GlobalSettingSeekBarPreference extends CustomSeekBarPreference {
    public GlobalSettingSeekBarPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setPreferenceDataStore(new GlobalSettingsStore(context.getContentResolver()));
    }
}
