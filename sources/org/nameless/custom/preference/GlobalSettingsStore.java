package org.nameless.custom.preference;

import android.content.ContentResolver;
import android.provider.Settings;
import androidx.preference.PreferenceDataStore;
/* loaded from: classes.dex */
public class GlobalSettingsStore extends PreferenceDataStore implements android.preference.PreferenceDataStore {
    private ContentResolver mContentResolver;

    public GlobalSettingsStore(ContentResolver contentResolver) {
        this.mContentResolver = contentResolver;
    }

    @Override // androidx.preference.PreferenceDataStore, android.preference.PreferenceDataStore
    public boolean getBoolean(String str, boolean z) {
        return getInt(str, z ? 1 : 0) != 0;
    }

    @Override // android.preference.PreferenceDataStore
    public float getFloat(String str, float f) {
        return Settings.Global.getFloat(this.mContentResolver, str, f);
    }

    @Override // androidx.preference.PreferenceDataStore, android.preference.PreferenceDataStore
    public int getInt(String str, int i) {
        return Settings.Global.getInt(this.mContentResolver, str, i);
    }

    @Override // android.preference.PreferenceDataStore
    public long getLong(String str, long j) {
        return Settings.Global.getLong(this.mContentResolver, str, j);
    }

    @Override // androidx.preference.PreferenceDataStore, android.preference.PreferenceDataStore
    public String getString(String str, String str2) {
        String string = Settings.Global.getString(this.mContentResolver, str);
        return string == null ? str2 : string;
    }

    @Override // androidx.preference.PreferenceDataStore, android.preference.PreferenceDataStore
    public void putBoolean(String str, boolean z) {
        putInt(str, z ? 1 : 0);
    }

    @Override // android.preference.PreferenceDataStore
    public void putFloat(String str, float f) {
        Settings.Global.putFloat(this.mContentResolver, str, f);
    }

    @Override // androidx.preference.PreferenceDataStore, android.preference.PreferenceDataStore
    public void putInt(String str, int i) {
        Settings.Global.putInt(this.mContentResolver, str, i);
    }

    @Override // android.preference.PreferenceDataStore
    public void putLong(String str, long j) {
        Settings.Global.putLong(this.mContentResolver, str, j);
    }

    @Override // androidx.preference.PreferenceDataStore, android.preference.PreferenceDataStore
    public void putString(String str, String str2) {
        Settings.Global.putString(this.mContentResolver, str, str2);
    }
}
