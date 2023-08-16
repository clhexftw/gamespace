package org.nameless.gamespace.utils;

import android.app.GameManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.IDeviceIdleController;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.provider.DeviceConfig;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.nameless.gamespace.R;
import org.nameless.gamespace.data.GameConfig;
import org.nameless.gamespace.data.SystemSettings;
import org.nameless.gamespace.data.UserGame;
/* compiled from: GameModeUtils.kt */
/* loaded from: classes.dex */
public final class GameModeUtils {
    public static final Companion Companion = new Companion(null);
    private UserGame activeGame;
    private final Context context;
    private GameManager manager;

    public GameModeUtils(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    public final UserGame getActiveGame() {
        return this.activeGame;
    }

    public final void setActiveGame(UserGame userGame) {
        this.activeGame = userGame;
    }

    public final void bind(GameManager manager) {
        Intrinsics.checkNotNullParameter(manager, "manager");
        this.manager = manager;
    }

    public final void unbind() {
        this.manager = null;
    }

    public final void setIntervention(String packageName, List<GameConfig> list) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        DeviceConfig.setProperty("game_overlay", packageName, list != null ? GameConfig.Companion.asConfig(list) : null, false);
    }

    public final void setActiveGameMode(SystemSettings systemSettings, int i) {
        String packageName;
        Intrinsics.checkNotNullParameter(systemSettings, "systemSettings");
        UserGame userGame = this.activeGame;
        if (userGame == null || (packageName = userGame.getPackageName()) == null) {
            return;
        }
        GameManager gameManager = this.manager;
        if (gameManager != null) {
            gameManager.setGameMode(packageName, i);
        }
        this.activeGame = setGameModeFor(packageName, systemSettings, i);
    }

    public final UserGame setGameModeFor(String packageName, SystemSettings systemSettings, int i) {
        List<UserGame> mutableList;
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(systemSettings, "systemSettings");
        UserGame userGame = new UserGame(packageName, i);
        ArrayList arrayList = new ArrayList();
        for (Object obj : systemSettings.getUserGames()) {
            if (!Intrinsics.areEqual(((UserGame) obj).getPackageName(), packageName)) {
                arrayList.add(obj);
            }
        }
        mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) arrayList);
        mutableList.add(userGame);
        systemSettings.setUserGames(mutableList);
        return userGame;
    }

    public final void setupBatteryMode(boolean z) {
        boolean isPowerSaveWhitelistApp;
        IDeviceIdleController asInterface = IDeviceIdleController.Stub.asInterface(ServiceManager.getService("deviceidle"));
        if (asInterface != null) {
            try {
                isPowerSaveWhitelistApp = asInterface.isPowerSaveWhitelistApp(this.context.getPackageName());
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        } else {
            isPowerSaveWhitelistApp = false;
        }
        if (z && !isPowerSaveWhitelistApp) {
            if (asInterface != null) {
                asInterface.addPowerSaveWhitelistApp(this.context.getPackageName());
            }
        } else if (z || !isPowerSaveWhitelistApp || asInterface == null) {
        } else {
            asInterface.removePowerSaveWhitelistApp(this.context.getPackageName());
        }
    }

    public final ActivityInfo findAnglePackage() {
        Object firstOrNull;
        List info = this.context.getPackageManager().queryIntentActivities(new Intent("android.app.action.ANGLE_FOR_ANDROID"), PackageManager.ResolveInfoFlags.of(1048576L));
        Intrinsics.checkNotNullExpressionValue(info, "info");
        firstOrNull = CollectionsKt___CollectionsKt.firstOrNull(info);
        ResolveInfo resolveInfo = (ResolveInfo) firstOrNull;
        if (resolveInfo != null) {
            return resolveInfo.activityInfo;
        }
        return null;
    }

    public final boolean isAngleUsed(String str) {
        boolean contains$default;
        if (str != null) {
            Boolean bool = null;
            String string = DeviceConfig.getString("game_overlay", str, (String) null);
            if (string != null) {
                contains$default = StringsKt__StringsKt.contains$default(string, "useAngle=true", false, 2, null);
                bool = Boolean.valueOf(contains$default);
            }
            if (bool != null) {
                return bool.booleanValue();
            }
            return false;
        }
        return false;
    }

    /* compiled from: GameModeUtils.kt */
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String describeGameMode(Context context, int i) {
            Intrinsics.checkNotNullParameter(context, "<this>");
            String str = context.getResources().getStringArray(R.array.game_mode_names)[i];
            return str == null ? "Unsupported" : str;
        }
    }
}
