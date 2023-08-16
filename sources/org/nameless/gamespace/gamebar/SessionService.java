package org.nameless.gamespace.gamebar;

import android.annotation.SuppressLint;
import android.app.ActivityTaskManager;
import android.app.GameManager;
import android.app.IActivityTaskManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.UserHandle;
import android.util.Log;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.JobKt;
import org.nameless.gamespace.data.AppSettings;
import org.nameless.gamespace.data.GameSession;
import org.nameless.gamespace.data.SystemSettings;
import org.nameless.gamespace.data.UserGame;
import org.nameless.gamespace.gamebar.GameBarService;
import org.nameless.gamespace.utils.GameModeUtils;
import org.nameless.gamespace.utils.ScreenUtils;
/* compiled from: SessionService.kt */
/* loaded from: classes.dex */
public final class SessionService extends Hilt_SessionService {
    public static final Companion Companion = new Companion(null);
    private static boolean isRunning;
    public AppSettings appSettings;
    public CallListener callListener;
    private Intent commandIntent;
    private GameBarService gameBar;
    private GameManager gameManager;
    public GameModeUtils gameModeUtils;
    private boolean isBarConnected;
    public ScreenUtils screenUtils;
    public GameSession session;
    public SystemSettings settings;
    private final CoroutineScope scope = CoroutineScopeKt.CoroutineScope(JobKt.Job$default(null, 1, null).plus(Dispatchers.getIO()));
    private final SessionService$gameBarConnection$1 gameBarConnection = new ServiceConnection() { // from class: org.nameless.gamespace.gamebar.SessionService$gameBarConnection$1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            SessionService.this.isBarConnected = true;
            SessionService sessionService = SessionService.this;
            Intrinsics.checkNotNull(iBinder, "null cannot be cast to non-null type org.nameless.gamespace.gamebar.GameBarService.GameBarBinder");
            sessionService.gameBar = ((GameBarService.GameBarBinder) iBinder).getService();
            SessionService.this.onGameBarReady();
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            SessionService.this.isBarConnected = false;
            SessionService.this.stopSelf();
        }
    };

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    public final AppSettings getAppSettings() {
        AppSettings appSettings = this.appSettings;
        if (appSettings != null) {
            return appSettings;
        }
        Intrinsics.throwUninitializedPropertyAccessException("appSettings");
        return null;
    }

    public final SystemSettings getSettings() {
        SystemSettings systemSettings = this.settings;
        if (systemSettings != null) {
            return systemSettings;
        }
        Intrinsics.throwUninitializedPropertyAccessException("settings");
        return null;
    }

    public final GameSession getSession() {
        GameSession gameSession = this.session;
        if (gameSession != null) {
            return gameSession;
        }
        Intrinsics.throwUninitializedPropertyAccessException("session");
        return null;
    }

    public final ScreenUtils getScreenUtils() {
        ScreenUtils screenUtils = this.screenUtils;
        if (screenUtils != null) {
            return screenUtils;
        }
        Intrinsics.throwUninitializedPropertyAccessException("screenUtils");
        return null;
    }

    public final GameModeUtils getGameModeUtils() {
        GameModeUtils gameModeUtils = this.gameModeUtils;
        if (gameModeUtils != null) {
            return gameModeUtils;
        }
        Intrinsics.throwUninitializedPropertyAccessException("gameModeUtils");
        return null;
    }

    public final CallListener getCallListener() {
        CallListener callListener = this.callListener;
        if (callListener != null) {
            return callListener;
        }
        Intrinsics.throwUninitializedPropertyAccessException("callListener");
        return null;
    }

    @Override // org.nameless.gamespace.gamebar.Hilt_SessionService, android.app.Service
    @SuppressLint({"WrongConstant"})
    public void onCreate() {
        super.onCreate();
        try {
            getScreenUtils().bind();
        } catch (RemoteException e) {
            Log.d("SessionService", e.toString());
        }
        Object systemService = getSystemService("game");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.GameManager");
        this.gameManager = (GameManager) systemService;
        GameModeUtils gameModeUtils = getGameModeUtils();
        GameManager gameManager = this.gameManager;
        if (gameManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("gameManager");
            gameManager = null;
        }
        gameModeUtils.bind(gameManager);
        isRunning = true;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        if (intent != null) {
            this.commandIntent = intent;
        }
        super.onStartCommand(intent, i, i2);
        if (intent == null && i == 0 && i2 > 1) {
            return tryStartFromDeath();
        }
        String action = intent != null ? intent.getAction() : null;
        if (Intrinsics.areEqual(action, "game_start")) {
            startGameBar();
        } else if (Intrinsics.areEqual(action, "game_stop")) {
            stopSelf();
        }
        return 1;
    }

    private final void startGameBar() {
        bindServiceAsUser(new Intent(this, GameBarService.class), this.gameBarConnection, 1, UserHandle.CURRENT);
    }

    @Override // android.app.Service
    public void onDestroy() {
        getCallListener().destory();
        if (this.isBarConnected) {
            GameBarService gameBarService = this.gameBar;
            if (gameBarService == null) {
                Intrinsics.throwUninitializedPropertyAccessException("gameBar");
                gameBarService = null;
            }
            gameBarService.onGameLeave();
            unbindService(this.gameBarConnection);
        }
        getSession().unregister();
        getGameModeUtils().unbind();
        getScreenUtils().unbind();
        isRunning = false;
        super.onDestroy();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onGameBarReady() {
        if (!this.isBarConnected) {
            startGameBar();
            return;
        }
        try {
            getSession().unregister();
            if (this.commandIntent == null) {
                stopSelf();
            }
            Intent intent = this.commandIntent;
            GameBarService gameBarService = null;
            if (intent == null) {
                Intrinsics.throwUninitializedPropertyAccessException("commandIntent");
                intent = null;
            }
            String app = intent.getStringExtra("package_name");
            GameSession session = getSession();
            Intrinsics.checkNotNullExpressionValue(app, "app");
            session.register(app);
            applyGameModeConfig(app);
            GameBarService gameBarService2 = this.gameBar;
            if (gameBarService2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("gameBar");
            } else {
                gameBarService = gameBarService2;
            }
            gameBarService.onGameStart();
            getScreenUtils().setStayAwake(getAppSettings().getStayAwake());
            getScreenUtils().setLockGesture((getAppSettings().getLockGesture() ? 1 : 0) | (getAppSettings().getLockStatusBar() ? 2 : 0));
        } catch (Exception e) {
            Log.d("SessionService", e.toString());
        }
        getCallListener().init();
    }

    private final int tryStartFromDeath() {
        ActivityTaskManager.RootTaskInfo focusedRootTaskInfo;
        ComponentName componentName;
        IActivityTaskManager service = ActivityTaskManager.getService();
        String packageName = (service == null || (focusedRootTaskInfo = service.getFocusedRootTaskInfo()) == null || (componentName = focusedRootTaskInfo.topActivity) == null) ? null : componentName.getPackageName();
        if (packageName == null) {
            return 2;
        }
        List<UserGame> userGames = getSettings().getUserGames();
        boolean z = false;
        if (!(userGames instanceof Collection) || !userGames.isEmpty()) {
            Iterator<T> it = userGames.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                } else if (Intrinsics.areEqual(((UserGame) it.next()).getPackageName(), packageName)) {
                    z = true;
                    break;
                }
            }
        }
        if (z) {
            Intent putExtra = new Intent("game_start").putExtra("package_name", packageName);
            Intrinsics.checkNotNullExpressionValue(putExtra, "Intent(START).putExtra(EXTRA_PACKAGE_NAME, game)");
            this.commandIntent = putExtra;
            startGameBar();
            return 1;
        }
        return 2;
    }

    private final void applyGameModeConfig(String str) {
        Object obj;
        Object obj2;
        Iterator<T> it = getSettings().getUserGames().iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (Intrinsics.areEqual(((UserGame) obj).getPackageName(), str)) {
                break;
            }
        }
        UserGame userGame = (UserGame) obj;
        int mode = userGame != null ? userGame.getMode() : 1;
        GameModeUtils gameModeUtils = getGameModeUtils();
        Iterator<T> it2 = getSettings().getUserGames().iterator();
        while (true) {
            if (!it2.hasNext()) {
                obj2 = null;
                break;
            }
            obj2 = it2.next();
            if (Intrinsics.areEqual(((UserGame) obj2).getPackageName(), str)) {
                break;
            }
        }
        gameModeUtils.setActiveGame((UserGame) obj2);
        BuildersKt.launch$default(this.scope, null, null, new SessionService$applyGameModeConfig$2(this, str, mode, null), 3, null);
    }

    /* compiled from: SessionService.kt */
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean isRunning() {
            return SessionService.isRunning;
        }

        public final ComponentName start(Context context, String app) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(app, "app");
            Intent intent = new Intent(context, SessionService.class);
            intent.setAction("game_start");
            intent.putExtra("package_name", app);
            if (!(!SessionService.Companion.isRunning())) {
                intent = null;
            }
            if (intent != null) {
                return context.startServiceAsUser(intent, UserHandle.CURRENT);
            }
            return null;
        }

        public final ComponentName stop(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intent intent = new Intent(context, SessionService.class);
            intent.setAction("game_stop");
            if (!SessionService.Companion.isRunning()) {
                intent = null;
            }
            if (intent != null) {
                return context.startServiceAsUser(intent, UserHandle.CURRENT);
            }
            return null;
        }
    }
}
