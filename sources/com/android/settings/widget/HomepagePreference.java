package com.android.settings.widget;

import android.content.Context;
import android.util.AttributeSet;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import com.android.settings.widget.HomepagePreferenceLayoutHelper;
/* loaded from: classes.dex */
public class HomepagePreference extends Preference implements HomepagePreferenceLayoutHelper.HomepagePreferenceLayout {
    private final HomepagePreferenceLayoutHelper mHelper;

    public HomepagePreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mHelper = new HomepagePreferenceLayoutHelper(this);
    }

    public HomepagePreference(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mHelper = new HomepagePreferenceLayoutHelper(this);
    }

    public HomepagePreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mHelper = new HomepagePreferenceLayoutHelper(this);
    }

    public HomepagePreference(Context context) {
        super(context);
        this.mHelper = new HomepagePreferenceLayoutHelper(this);
    }

    @Override // androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        this.mHelper.onBindViewHolder(preferenceViewHolder);
    }

    @Override // com.android.settings.widget.HomepagePreferenceLayoutHelper.HomepagePreferenceLayout
    public HomepagePreferenceLayoutHelper getHelper() {
        return this.mHelper;
    }
}
