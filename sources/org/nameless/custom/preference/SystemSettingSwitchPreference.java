package org.nameless.custom.preference;

import android.content.Context;
import android.provider.Settings;
import android.util.AttributeSet;
/* loaded from: classes2.dex */
public class SystemSettingSwitchPreference extends SelfRemovingSwitchPreference {
    public SystemSettingSwitchPreference(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public SystemSettingSwitchPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SystemSettingSwitchPreference(Context context) {
        super(context, null);
    }

    @Override // org.nameless.custom.preference.SelfRemovingSwitchPreference
    protected boolean isPersisted() {
        return Settings.System.getString(getContext().getContentResolver(), getKey()) != null;
    }

    @Override // org.nameless.custom.preference.SelfRemovingSwitchPreference
    protected void putBoolean(String str, boolean z) {
        Settings.System.putInt(getContext().getContentResolver(), str, z ? 1 : 0);
    }

    @Override // org.nameless.custom.preference.SelfRemovingSwitchPreference
    protected boolean getBoolean(String str, boolean z) {
        return Settings.System.getInt(getContext().getContentResolver(), str, z ? 1 : 0) != 0;
    }
}
