package org.nameless.custom.colorpicker;

import android.content.Context;
import android.util.AttributeSet;
import org.nameless.custom.preference.SystemSettingsStore;
/* loaded from: classes2.dex */
public class ColorPickerSystemPreference extends ColorPickerPreference {
    public ColorPickerSystemPreference(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setPreferenceDataStore(new SystemSettingsStore(context.getContentResolver()));
    }

    public ColorPickerSystemPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setPreferenceDataStore(new SystemSettingsStore(context.getContentResolver()));
    }
}
