package com.android.settingslib.inputmethod;

import android.content.Context;
import org.nameless.custom.preference.SwitchPreference;
/* loaded from: classes2.dex */
public class SwitchWithNoTextPreference extends SwitchPreference {
    public SwitchWithNoTextPreference(Context context) {
        super(context);
        setSwitchTextOn("");
        setSwitchTextOff("");
    }
}
