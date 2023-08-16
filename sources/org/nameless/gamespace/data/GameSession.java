package org.nameless.gamespace.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import com.google.gson.Gson;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: GameSession.kt */
/* loaded from: classes.dex */
public final class GameSession {
    public static final Companion Companion = new Companion(null);
    private final AppSettings appSettings;
    private final Lazy audioManager$delegate;
    private final Context context;
    private final Lazy db$delegate;
    private final Gson gson;
    private final SystemSettings systemSettings;

    public GameSession(Context context, AppSettings appSettings, SystemSettings systemSettings, Gson gson) {
        Lazy lazy;
        Lazy lazy2;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(appSettings, "appSettings");
        Intrinsics.checkNotNullParameter(systemSettings, "systemSettings");
        Intrinsics.checkNotNullParameter(gson, "gson");
        this.context = context;
        this.appSettings = appSettings;
        this.systemSettings = systemSettings;
        this.gson = gson;
        lazy = LazyKt__LazyJVMKt.lazy(new Function0<SharedPreferences>() { // from class: org.nameless.gamespace.data.GameSession$db$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final SharedPreferences invoke() {
                Context context2;
                context2 = GameSession.this.context;
                return context2.getSharedPreferences("persisted_session", 0);
            }
        });
        this.db$delegate = lazy;
        lazy2 = LazyKt__LazyJVMKt.lazy(new Function0<AudioManager>() { // from class: org.nameless.gamespace.data.GameSession$audioManager$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final AudioManager invoke() {
                Context context2;
                context2 = GameSession.this.context;
                Object systemService = context2.getSystemService("audio");
                Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.media.AudioManager");
                return (AudioManager) systemService;
            }
        });
        this.audioManager$delegate = lazy2;
    }

    private final SharedPreferences getDb() {
        return (SharedPreferences) this.db$delegate.getValue();
    }

    private final AudioManager getAudioManager() {
        return (AudioManager) this.audioManager$delegate.getValue();
    }

    private final SessionState getState() {
        String string = getDb().getString("session", "");
        if (!(true ^ (string == null || string.length() == 0))) {
            string = null;
        }
        if (string != null) {
            try {
                return (SessionState) this.gson.fromJson(string, (Class<Object>) SessionState.class);
            } catch (RuntimeException unused) {
                return null;
            }
        }
        return null;
    }

    private final void setState(SessionState sessionState) {
        String str;
        SharedPreferences.Editor edit = getDb().edit();
        if (sessionState != null) {
            try {
                str = this.gson.toJson(sessionState);
            } catch (RuntimeException unused) {
                str = "";
            }
        } else {
            str = null;
        }
        edit.putString("session", str != null ? str : "").apply();
    }

    public final void register(String sessionName) {
        Intrinsics.checkNotNullParameter(sessionName, "sessionName");
        SessionState state = getState();
        if (!Intrinsics.areEqual(state != null ? state.getPackageName() : null, sessionName)) {
            unregister();
        }
        setState(new SessionState(sessionName, Boolean.valueOf(this.systemSettings.getAutoBrightness()), Boolean.valueOf(this.systemSettings.getThreeScreenshot()), Boolean.valueOf(this.systemSettings.getHeadsUp()), getAudioManager().getRingerModeInternal(), Boolean.valueOf(this.systemSettings.getAdbEnabled())));
        if (this.appSettings.getNoAutoBrightness()) {
            this.systemSettings.setAutoBrightness(false);
        }
        if (this.appSettings.getNoThreeScreenshot()) {
            this.systemSettings.setThreeScreenshot(false);
        }
        if (this.appSettings.getNoAdbEnabled()) {
            this.systemSettings.setAdbEnabled(false);
        }
        if (this.appSettings.getNotificationsMode() == 0 || this.appSettings.getNotificationsMode() == 2) {
            this.systemSettings.setHeadsUp(false);
        } else {
            this.systemSettings.setHeadsUp(true);
        }
        if (this.appSettings.getRingerMode() != 3) {
            getAudioManager().setRingerModeInternal(this.appSettings.getRingerMode());
        }
    }

    public final void unregister() {
        SessionState copy$default;
        Boolean adbEnabled;
        Boolean threeScreenshot;
        Boolean autoBrightness;
        SessionState state = getState();
        if (state == null || (copy$default = SessionState.copy$default(state, null, null, null, null, 0, null, 63, null)) == null) {
            return;
        }
        if (this.appSettings.getNoAutoBrightness() && (autoBrightness = copy$default.getAutoBrightness()) != null) {
            this.systemSettings.setAutoBrightness(autoBrightness.booleanValue());
        }
        if (this.appSettings.getNoThreeScreenshot() && (threeScreenshot = copy$default.getThreeScreenshot()) != null) {
            this.systemSettings.setThreeScreenshot(threeScreenshot.booleanValue());
        }
        if (this.appSettings.getNoAdbEnabled() && (adbEnabled = copy$default.getAdbEnabled()) != null) {
            this.systemSettings.setAdbEnabled(adbEnabled.booleanValue());
        }
        Boolean headsUp = copy$default.getHeadsUp();
        if (headsUp != null) {
            this.systemSettings.setHeadsUp(headsUp.booleanValue());
        }
        if (this.appSettings.getRingerMode() != 3) {
            getAudioManager().setRingerModeInternal(copy$default.getRingerMode());
        }
        setState(null);
    }

    public final void finalize() {
        unregister();
    }

    /* compiled from: GameSession.kt */
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
