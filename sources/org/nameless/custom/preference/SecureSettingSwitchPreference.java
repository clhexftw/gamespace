package org.nameless.custom.preference;

import android.content.Context;
import android.provider.Settings;
import android.util.AttributeSet;
/* loaded from: classes.dex */
public class SecureSettingSwitchPreference extends SelfRemovingSwitchPreference {
    public SecureSettingSwitchPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // org.nameless.custom.preference.SelfRemovingSwitchPreference
    protected boolean isPersisted() {
        return Settings.Secure.getString(getContext().getContentResolver(), getKey()) != null;
    }

    @Override // org.nameless.custom.preference.SelfRemovingSwitchPreference
    protected void putBoolean(String str, boolean z) {
        Settings.Secure.putInt(getContext().getContentResolver(), str, z ? 1 : 0);
    }

    @Override // org.nameless.custom.preference.SelfRemovingSwitchPreference
    protected boolean getBoolean(String str, boolean z) {
        return Settings.Secure.getInt(getContext().getContentResolver(), str, z ? 1 : 0) != 0;
    }
}
