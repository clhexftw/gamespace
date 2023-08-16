package org.nameless.custom.preference;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import androidx.preference.ListPreference;
/* loaded from: classes2.dex */
public class GlobalSettingListPreference extends ListPreference {
    private boolean mAutoSummary;

    public GlobalSettingListPreference(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mAutoSummary = false;
        setPreferenceDataStore(new GlobalSettingsStore(context.getContentResolver()));
    }

    public GlobalSettingListPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mAutoSummary = false;
        setPreferenceDataStore(new GlobalSettingsStore(context.getContentResolver()));
    }

    @Override // androidx.preference.ListPreference
    public void setValue(String str) {
        super.setValue(str);
        if (this.mAutoSummary || TextUtils.isEmpty(getSummary())) {
            setSummary(getEntry(), true);
        }
    }

    @Override // androidx.preference.ListPreference, androidx.preference.Preference
    public void setSummary(CharSequence charSequence) {
        setSummary(charSequence, false);
    }

    private void setSummary(CharSequence charSequence, boolean z) {
        this.mAutoSummary = z;
        super.setSummary(charSequence);
    }

    @Override // androidx.preference.Preference
    protected void onSetInitialValue(boolean z, Object obj) {
        setValue(z ? getPersistedString((String) obj) : (String) obj);
    }
}
