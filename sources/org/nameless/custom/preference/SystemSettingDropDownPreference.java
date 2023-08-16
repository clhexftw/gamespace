package org.nameless.custom.preference;

import android.content.Context;
import android.provider.Settings;
import android.util.AttributeSet;
/* loaded from: classes.dex */
public class SystemSettingDropDownPreference extends SelfRemovingDropDownPreference {
    public SystemSettingDropDownPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // org.nameless.custom.preference.SelfRemovingDropDownPreference
    protected boolean isPersisted() {
        return Settings.System.getString(getContext().getContentResolver(), getKey()) != null;
    }

    @Override // org.nameless.custom.preference.SelfRemovingDropDownPreference
    protected void putString(String str, String str2) {
        Settings.System.putString(getContext().getContentResolver(), str, str2);
    }

    @Override // org.nameless.custom.preference.SelfRemovingDropDownPreference
    protected String getString(String str, String str2) {
        String string = Settings.System.getString(getContext().getContentResolver(), str);
        return string == null ? str2 : string;
    }
}
