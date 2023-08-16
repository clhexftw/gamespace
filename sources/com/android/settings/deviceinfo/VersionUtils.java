package com.android.settings.deviceinfo;

import android.os.SystemProperties;
/* loaded from: classes.dex */
public class VersionUtils {
    public static String getNamelessVersion() {
        return SystemProperties.get("ro.nameless.version.display", "");
    }
}
