package com.android.settings.accessibility;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.provider.Settings;
import com.android.settings.display.ToggleFontSizePreferenceFragment;
import com.android.settingslib.R$array;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
/* loaded from: classes.dex */
final class FontSizeData extends PreviewSizeData<Float> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public FontSizeData(Context context) {
        super(context);
        Resources resources = getContext().getResources();
        ContentResolver contentResolver = getContext().getContentResolver();
        List asList = Arrays.asList(resources.getStringArray(R$array.entryvalues_font_size));
        setDefaultValue(Float.valueOf(1.0f));
        setInitialIndex(ToggleFontSizePreferenceFragment.fontSizeValueToIndex(Settings.System.getFloat(contentResolver, "font_scale", getDefaultValue().floatValue()), (String[]) asList.toArray(new String[0])));
        setValues((List) asList.stream().map(new Function() { // from class: com.android.settings.accessibility.FontSizeData$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Float.valueOf((String) obj);
            }
        }).collect(Collectors.toList()));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void commit(int i) {
        ContentResolver contentResolver = getContext().getContentResolver();
        if (Settings.Secure.getInt(contentResolver, "accessibility_font_scaling_has_been_changed", 0) != 1) {
            Settings.Secure.putInt(contentResolver, "accessibility_font_scaling_has_been_changed", 1);
        }
        Settings.System.putFloat(contentResolver, "font_scale", getValues().get(i).floatValue());
    }
}
