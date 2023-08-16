package com.android.settings.widget;

import android.content.Context;
import android.util.AttributeSet;
import androidx.preference.ListPreference;
import com.android.settings.R;
/* loaded from: classes.dex */
public class AppListPreference extends ListPreference {
    public AppListPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        setLayoutResource(R.layout.preference_app);
    }

    public AppListPreference(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setLayoutResource(R.layout.preference_app);
    }

    public AppListPreference(Context context) {
        super(context);
        setLayoutResource(R.layout.preference_app);
    }

    public AppListPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setLayoutResource(R.layout.preference_app);
    }
}
