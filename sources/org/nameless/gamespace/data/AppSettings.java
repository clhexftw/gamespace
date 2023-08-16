package org.nameless.gamespace.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.WindowManager;
import androidx.preference.PreferenceManager;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.nameless.gamespace.utils.ExtensionsKt;
/* compiled from: AppSettings.kt */
/* loaded from: classes.dex */
public final class AppSettings {
    public static final Companion Companion = new Companion(null);
    private final Context context;
    private final Lazy db$delegate;
    private final Lazy wm$delegate;

    public AppSettings(Context context) {
        Lazy lazy;
        Lazy lazy2;
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        lazy = LazyKt__LazyJVMKt.lazy(new Function0<SharedPreferences>() { // from class: org.nameless.gamespace.data.AppSettings$db$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final SharedPreferences invoke() {
                Context context2;
                context2 = AppSettings.this.context;
                return PreferenceManager.getDefaultSharedPreferences(context2);
            }
        });
        this.db$delegate = lazy;
        lazy2 = LazyKt__LazyJVMKt.lazy(new Function0<WindowManager>() { // from class: org.nameless.gamespace.data.AppSettings$wm$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final WindowManager invoke() {
                Context context2;
                context2 = AppSettings.this.context;
                Object systemService = context2.getSystemService("window");
                Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.WindowManager");
                return (WindowManager) systemService;
            }
        });
        this.wm$delegate = lazy2;
    }

    private final SharedPreferences getDb() {
        return (SharedPreferences) this.db$delegate.getValue();
    }

    private final WindowManager getWm() {
        return (WindowManager) this.wm$delegate.getValue();
    }

    public final int getX() {
        return getDb().getInt("offset_x", getWm().getMaximumWindowMetrics().getBounds().width() / 2);
    }

    public final void setX(int i) {
        getDb().edit().putInt("offset_x", i).apply();
    }

    public final int getY() {
        return getDb().getInt("offset_y", ExtensionsKt.getStatusbarHeight(this.context) + ExtensionsKt.getDp(8));
    }

    public final void setY(int i) {
        getDb().edit().putInt("offset_y", i).apply();
    }

    public final boolean getShowFps() {
        return getDb().getBoolean("show_fps", false);
    }

    public final void setShowFps(boolean z) {
        getDb().edit().putBoolean("show_fps", z).apply();
    }

    public final boolean getNoAutoBrightness() {
        return getDb().getBoolean("gamespace_auto_brightness_disabled", true);
    }

    public final boolean getNoThreeScreenshot() {
        return getDb().getBoolean("gamespace_tfgesture_disabled", false);
    }

    public final boolean getStayAwake() {
        return getDb().getBoolean("gamespace_stay_awake", false);
    }

    public final void setStayAwake(boolean z) {
        getDb().edit().putBoolean("gamespace_stay_awake", z).apply();
    }

    public final int getCallsMode() {
        String string = getDb().getString("gamespace_calls_mode", "0");
        Intrinsics.checkNotNullExpressionValue(string, "db.getString(KEY_CALLS_MODE, \"0\")");
        return Integer.parseInt(string);
    }

    public final int getNotificationsMode() {
        String string = getDb().getString("gamespace_notifications_mode", "2");
        Intrinsics.checkNotNullExpressionValue(string, "db.getString(KEY_NOTIFICAITONS_MODE, \"2\")");
        return Integer.parseInt(string);
    }

    public final void setNotificationsMode(int i) {
        getDb().edit().putString("gamespace_notifications_mode", String.valueOf(i)).apply();
    }

    public final int getRingerMode() {
        String string = getDb().getString("gamespace_ringer_mode", "0");
        Intrinsics.checkNotNullExpressionValue(string, "db.getString(KEY_RINGER_MODE, \"0\")");
        return Integer.parseInt(string);
    }

    public final int getMenuOpacity() {
        return getDb().getInt("gamespace_menu_opacity", 75);
    }

    public final boolean getNoAdbEnabled() {
        return getDb().getBoolean("gamespace_adb_disabled", false);
    }

    public final boolean getLockGesture() {
        return getDb().getBoolean("gamespace_lock_gesture", false);
    }

    public final boolean getLockStatusBar() {
        return getDb().getBoolean("gamespace_lock_statusbar", false);
    }

    public final boolean getShowOverlay() {
        return getDb().getBoolean("gamespace_show_overlay", true);
    }

    /* compiled from: AppSettings.kt */
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
