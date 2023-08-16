package org.nameless.custom.preference;

import android.content.Context;
import android.util.AttributeSet;
/* loaded from: classes2.dex */
public class SecureSettingSeekBarPreference extends CustomSeekBarPreference {
    public SecureSettingSeekBarPreference(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setPreferenceDataStore(new SecureSettingsStore(context.getContentResolver()));
    }

    public SecureSettingSeekBarPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setPreferenceDataStore(new SecureSettingsStore(context.getContentResolver()));
    }

    public SecureSettingSeekBarPreference(Context context) {
        super(context, null);
        setPreferenceDataStore(new SecureSettingsStore(context.getContentResolver()));
    }
}
