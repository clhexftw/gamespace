package org.nameless.custom.preference;

import android.content.Context;
import android.util.AttributeSet;
/* loaded from: classes2.dex */
public class GlobalSettingSeekBarPreference extends CustomSeekBarPreference {
    public GlobalSettingSeekBarPreference(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setPreferenceDataStore(new GlobalSettingsStore(context.getContentResolver()));
    }

    public GlobalSettingSeekBarPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setPreferenceDataStore(new GlobalSettingsStore(context.getContentResolver()));
    }
}
