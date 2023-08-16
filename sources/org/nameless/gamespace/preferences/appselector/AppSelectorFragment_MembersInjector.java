package org.nameless.gamespace.preferences.appselector;

import org.nameless.gamespace.data.SystemSettings;
/* loaded from: classes.dex */
public final class AppSelectorFragment_MembersInjector {
    public static void injectSettings(AppSelectorFragment appSelectorFragment, SystemSettings systemSettings) {
        appSelectorFragment.settings = systemSettings;
    }
}
