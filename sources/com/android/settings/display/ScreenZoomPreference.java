package com.android.settings.display;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import androidx.core.content.res.TypedArrayUtils;
import androidx.preference.Preference;
import androidx.preference.R$attr;
import com.android.settingslib.display.DisplayDensityUtils;
/* loaded from: classes.dex */
public class ScreenZoomPreference extends Preference {
    public ScreenZoomPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, TypedArrayUtils.getAttr(context, R$attr.preferenceStyle, 16842894));
        DisplayDensityUtils displayDensityUtils = new DisplayDensityUtils(context);
        if (displayDensityUtils.getCurrentIndexForDefaultDisplay() < 0) {
            setVisible(false);
            setEnabled(false);
        } else if (TextUtils.isEmpty(getSummary())) {
            setSummary(displayDensityUtils.getDefaultDisplayDensityEntries()[displayDensityUtils.getCurrentIndexForDefaultDisplay()]);
        }
    }
}
