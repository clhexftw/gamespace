package org.nameless.gamespace.gamebar;

import org.nameless.gamespace.data.AppSettings;
import org.nameless.gamespace.utils.ScreenUtils;
/* loaded from: classes.dex */
public final class GameBarService_MembersInjector {
    public static void injectAppSettings(GameBarService gameBarService, AppSettings appSettings) {
        gameBarService.appSettings = appSettings;
    }

    public static void injectScreenUtils(GameBarService gameBarService, ScreenUtils screenUtils) {
        gameBarService.screenUtils = screenUtils;
    }

    public static void injectDanmakuService(GameBarService gameBarService, DanmakuService danmakuService) {
        gameBarService.danmakuService = danmakuService;
    }
}
