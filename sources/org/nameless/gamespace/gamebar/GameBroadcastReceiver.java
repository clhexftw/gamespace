package org.nameless.gamespace.gamebar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.nameless.gamespace.gamebar.SessionService;
/* compiled from: GameBroadcastReceiver.kt */
/* loaded from: classes.dex */
public final class GameBroadcastReceiver extends BroadcastReceiver {
    public static final Companion Companion = new Companion(null);

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        String action = intent.getAction();
        if (Intrinsics.areEqual(action, "org.nameless.gamespace.action.GAME_START")) {
            onGameStart(context, intent);
        } else if (Intrinsics.areEqual(action, "org.nameless.gamespace.action.GAME_STOP")) {
            onGameStop(context);
        }
    }

    private final void onGameStart(Context context, Intent intent) {
        String app = intent.getStringExtra("package_name");
        SessionService.Companion companion = SessionService.Companion;
        Intrinsics.checkNotNullExpressionValue(app, "app");
        companion.start(context, app);
    }

    private final void onGameStop(Context context) {
        SessionService.Companion.stop(context);
    }

    /* compiled from: GameBroadcastReceiver.kt */
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
