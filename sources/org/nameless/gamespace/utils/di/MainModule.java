package org.nameless.gamespace.utils.di;

import android.content.Context;
import com.google.gson.Gson;
import kotlin.jvm.internal.Intrinsics;
import org.nameless.gamespace.data.AppSettings;
import org.nameless.gamespace.data.GameSession;
import org.nameless.gamespace.data.SystemSettings;
import org.nameless.gamespace.utils.GameModeUtils;
import org.nameless.gamespace.utils.ScreenUtils;
/* compiled from: MainModule.kt */
/* loaded from: classes.dex */
public final class MainModule {
    public static final MainModule INSTANCE = new MainModule();

    private MainModule() {
    }

    public final Gson provideBaseGson() {
        return new Gson();
    }

    public final ScreenUtils provideScreenUtils(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new ScreenUtils(context);
    }

    public final GameModeUtils provideGameModeUtils(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new GameModeUtils(context);
    }

    public final AppSettings provideAppSettings(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new AppSettings(context);
    }

    public final SystemSettings provideSystemSettings(Context context, GameModeUtils gameModeUtils) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(gameModeUtils, "gameModeUtils");
        return new SystemSettings(context, gameModeUtils);
    }

    public final GameSession provideGameSession(Context context, AppSettings appSettings, SystemSettings systemSettings, Gson gson) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(appSettings, "appSettings");
        Intrinsics.checkNotNullParameter(systemSettings, "systemSettings");
        Intrinsics.checkNotNullParameter(gson, "gson");
        return new GameSession(context, appSettings, systemSettings, gson);
    }
}
