package org.nameless.custom.preference;

import android.content.Context;
import android.util.AttributeSet;
import androidx.preference.DropDownPreference;
import androidx.preference.PreferenceDataStore;
import androidx.preference.PreferenceViewHolder;
/* loaded from: classes.dex */
public abstract class SelfRemovingDropDownPreference extends DropDownPreference {
    private final ConstraintsHelper mConstraints;

    protected abstract String getString(String str, String str2);

    protected abstract boolean isPersisted();

    protected abstract void putString(String str, String str2);

    public SelfRemovingDropDownPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mConstraints = new ConstraintsHelper(context, attributeSet, this);
        setPreferenceDataStore(new DataStore());
    }

    @Override // androidx.preference.Preference
    public void onAttached() {
        super.onAttached();
        this.mConstraints.onAttached();
    }

    @Override // androidx.preference.DropDownPreference, androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        this.mConstraints.onBindViewHolder(preferenceViewHolder);
    }

    @Override // androidx.preference.Preference
    protected void onSetInitialValue(boolean z, Object obj) {
        String str;
        if (z && isPersisted()) {
            str = getString(getKey(), null);
        } else if (obj == null) {
            return;
        } else {
            str = (String) obj;
            if (shouldPersist()) {
                persistString(str);
            }
        }
        setValue(str);
    }

    /* loaded from: classes.dex */
    private class DataStore extends PreferenceDataStore {
        private DataStore() {
        }

        @Override // androidx.preference.PreferenceDataStore, android.preference.PreferenceDataStore
        public void putString(String str, String str2) {
            SelfRemovingDropDownPreference.this.putString(str, str2);
        }

        @Override // androidx.preference.PreferenceDataStore, android.preference.PreferenceDataStore
        public String getString(String str, String str2) {
            return SelfRemovingDropDownPreference.this.getString(str, str2);
        }
    }
}
