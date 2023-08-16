package org.nameless.custom.colorpicker;

import android.content.Context;
import android.util.AttributeSet;
import org.nameless.custom.preference.SecureSettingsStore;
/* loaded from: classes2.dex */
public class ColorPickerSecurePreference extends ColorPickerPreference {
    public ColorPickerSecurePreference(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setPreferenceDataStore(new SecureSettingsStore(context.getContentResolver()));
    }

    public ColorPickerSecurePreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setPreferenceDataStore(new SecureSettingsStore(context.getContentResolver()));
    }
}
