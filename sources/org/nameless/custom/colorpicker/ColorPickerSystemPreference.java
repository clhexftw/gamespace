package org.nameless.custom.colorpicker;

import android.content.Context;
import android.util.AttributeSet;
import org.nameless.custom.preference.SystemSettingsStore;
/* loaded from: classes.dex */
public class ColorPickerSystemPreference extends ColorPickerPreference {
    public ColorPickerSystemPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setPreferenceDataStore(new SystemSettingsStore(context.getContentResolver()));
    }
}
