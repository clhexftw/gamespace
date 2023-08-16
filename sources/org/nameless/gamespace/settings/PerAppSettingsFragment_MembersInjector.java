package org.nameless.gamespace.settings;

import org.nameless.gamespace.data.SystemSettings;
import org.nameless.gamespace.utils.GameModeUtils;
/* loaded from: classes.dex */
public final class PerAppSettingsFragment_MembersInjector {
    public static void injectSettings(PerAppSettingsFragment perAppSettingsFragment, SystemSettings systemSettings) {
        perAppSettingsFragment.settings = systemSettings;
    }

    public static void injectGameModeUtils(PerAppSettingsFragment perAppSettingsFragment, GameModeUtils gameModeUtils) {
        perAppSettingsFragment.gameModeUtils = gameModeUtils;
    }
}
