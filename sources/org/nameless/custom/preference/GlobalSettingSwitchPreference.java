package org.nameless.custom.preference;

import android.content.Context;
import android.provider.Settings;
import android.util.AttributeSet;
/* loaded from: classes2.dex */
public class GlobalSettingSwitchPreference extends SelfRemovingSwitchPreference {
    public GlobalSettingSwitchPreference(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public GlobalSettingSwitchPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public GlobalSettingSwitchPreference(Context context) {
        super(context, null);
    }

    @Override // org.nameless.custom.preference.SelfRemovingSwitchPreference
    protected boolean isPersisted() {
        return Settings.Global.getString(getContext().getContentResolver(), getKey()) != null;
    }

    @Override // org.nameless.custom.preference.SelfRemovingSwitchPreference
    protected void putBoolean(String str, boolean z) {
        Settings.Global.putInt(getContext().getContentResolver(), str, z ? 1 : 0);
    }

    @Override // org.nameless.custom.preference.SelfRemovingSwitchPreference
    protected boolean getBoolean(String str, boolean z) {
        return Settings.Global.getInt(getContext().getContentResolver(), str, z ? 1 : 0) != 0;
    }
}
