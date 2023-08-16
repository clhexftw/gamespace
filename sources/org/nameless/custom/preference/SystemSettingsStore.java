package org.nameless.custom.preference;

import android.content.ContentResolver;
import android.provider.Settings;
import androidx.preference.PreferenceDataStore;
/* loaded from: classes.dex */
public class SystemSettingsStore extends PreferenceDataStore implements android.preference.PreferenceDataStore {
    private ContentResolver mContentResolver;

    public SystemSettingsStore(ContentResolver contentResolver) {
        this.mContentResolver = contentResolver;
    }

    @Override // androidx.preference.PreferenceDataStore, android.preference.PreferenceDataStore
    public boolean getBoolean(String str, boolean z) {
        return getInt(str, z ? 1 : 0) != 0;
    }

    @Override // android.preference.PreferenceDataStore
    public float getFloat(String str, float f) {
        return Settings.System.getFloat(this.mContentResolver, str, f);
    }

    @Override // androidx.preference.PreferenceDataStore, android.preference.PreferenceDataStore
    public int getInt(String str, int i) {
        return Settings.System.getInt(this.mContentResolver, str, i);
    }

    @Override // android.preference.PreferenceDataStore
    public long getLong(String str, long j) {
        return Settings.System.getLong(this.mContentResolver, str, j);
    }

    @Override // androidx.preference.PreferenceDataStore, android.preference.PreferenceDataStore
    public String getString(String str, String str2) {
        String string = Settings.System.getString(this.mContentResolver, str);
        return string == null ? str2 : string;
    }

    @Override // androidx.preference.PreferenceDataStore, android.preference.PreferenceDataStore
    public void putBoolean(String str, boolean z) {
        putInt(str, z ? 1 : 0);
    }

    @Override // android.preference.PreferenceDataStore
    public void putFloat(String str, float f) {
        Settings.System.putFloat(this.mContentResolver, str, f);
    }

    @Override // androidx.preference.PreferenceDataStore, android.preference.PreferenceDataStore
    public void putInt(String str, int i) {
        Settings.System.putInt(this.mContentResolver, str, i);
    }

    @Override // android.preference.PreferenceDataStore
    public void putLong(String str, long j) {
        Settings.System.putLong(this.mContentResolver, str, j);
    }

    @Override // androidx.preference.PreferenceDataStore, android.preference.PreferenceDataStore
    public void putString(String str, String str2) {
        Settings.System.putString(this.mContentResolver, str, str2);
    }
}
