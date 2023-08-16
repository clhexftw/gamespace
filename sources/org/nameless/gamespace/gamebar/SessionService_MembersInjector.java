package org.nameless.gamespace.gamebar;

import org.nameless.gamespace.data.AppSettings;
import org.nameless.gamespace.data.GameSession;
import org.nameless.gamespace.data.SystemSettings;
import org.nameless.gamespace.utils.GameModeUtils;
import org.nameless.gamespace.utils.ScreenUtils;
/* loaded from: classes.dex */
public final class SessionService_MembersInjector {
    public static void injectAppSettings(SessionService sessionService, AppSettings appSettings) {
        sessionService.appSettings = appSettings;
    }

    public static void injectSettings(SessionService sessionService, SystemSettings systemSettings) {
        sessionService.settings = systemSettings;
    }

    public static void injectSession(SessionService sessionService, GameSession gameSession) {
        sessionService.session = gameSession;
    }

    public static void injectScreenUtils(SessionService sessionService, ScreenUtils screenUtils) {
        sessionService.screenUtils = screenUtils;
    }

    public static void injectGameModeUtils(SessionService sessionService, GameModeUtils gameModeUtils) {
        sessionService.gameModeUtils = gameModeUtils;
    }

    public static void injectCallListener(SessionService sessionService, CallListener callListener) {
        sessionService.callListener = callListener;
    }
}
