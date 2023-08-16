package org.nameless.gamespace.data;

import android.content.ContentResolver;
import android.content.Context;
import android.provider.Settings;
import java.util.List;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.nameless.gamespace.utils.GameModeUtils;
/* compiled from: SystemSettings.kt */
/* loaded from: classes.dex */
public final class SystemSettings {
    private final GameModeUtils gameModeUtils;
    private final ContentResolver resolver;

    private final int toInt(boolean z) {
        return z ? 1 : 0;
    }

    public SystemSettings(Context context, GameModeUtils gameModeUtils) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(gameModeUtils, "gameModeUtils");
        this.gameModeUtils = gameModeUtils;
        this.resolver = context.getContentResolver();
    }

    public final boolean getHeadsUp() {
        return Settings.Global.getInt(this.resolver, "heads_up_notifications_enabled", 1) == 1;
    }

    public final void setHeadsUp(boolean z) {
        Settings.Global.putInt(this.resolver, "heads_up_notifications_enabled", toInt(z));
    }

    public final boolean getAutoBrightness() {
        return Settings.System.getIntForUser(this.resolver, "screen_brightness_mode", 1, -2) == 1;
    }

    public final void setAutoBrightness(boolean z) {
        Settings.System.putIntForUser(this.resolver, "screen_brightness_mode", z ? 1 : 0, -2);
    }

    public final boolean getThreeScreenshot() {
        return Settings.System.getIntForUser(this.resolver, "three_finger_gesture", 0, -2) == 1;
    }

    public final void setThreeScreenshot(boolean z) {
        Settings.System.putIntForUser(this.resolver, "three_finger_gesture", toInt(z), -2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:4:0x000b, code lost:
        r8 = kotlin.text.StringsKt__StringsKt.split$default(r2, new java.lang.String[]{";"}, false, 0, 6, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x001b, code lost:
        r8 = kotlin.collections.CollectionsKt___CollectionsKt.toList(r8);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.List<org.nameless.gamespace.data.UserGame> getUserGames() {
        /*
            r8 = this;
            android.content.ContentResolver r8 = r8.resolver
            java.lang.String r0 = "gamespace_game_list"
            r1 = -2
            java.lang.String r2 = android.provider.Settings.System.getStringForUser(r8, r0, r1)
            if (r2 == 0) goto L6f
            java.lang.String r8 = ";"
            java.lang.String[] r3 = new java.lang.String[]{r8}
            r4 = 0
            r5 = 0
            r6 = 6
            r7 = 0
            java.util.List r8 = kotlin.text.StringsKt.split$default(r2, r3, r4, r5, r6, r7)
            if (r8 == 0) goto L6f
            java.lang.Iterable r8 = (java.lang.Iterable) r8
            java.util.List r8 = kotlin.collections.CollectionsKt.toList(r8)
            if (r8 == 0) goto L6f
            java.lang.Iterable r8 = (java.lang.Iterable) r8
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.Iterator r8 = r8.iterator()
        L2e:
            boolean r1 = r8.hasNext()
            if (r1 == 0) goto L4a
            java.lang.Object r1 = r8.next()
            r2 = r1
            java.lang.String r2 = (java.lang.String) r2
            int r2 = r2.length()
            if (r2 <= 0) goto L43
            r2 = 1
            goto L44
        L43:
            r2 = 0
        L44:
            if (r2 == 0) goto L2e
            r0.add(r1)
            goto L2e
        L4a:
            java.util.ArrayList r8 = new java.util.ArrayList
            r1 = 10
            int r1 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r0, r1)
            r8.<init>(r1)
            java.util.Iterator r0 = r0.iterator()
        L59:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L73
            java.lang.Object r1 = r0.next()
            java.lang.String r1 = (java.lang.String) r1
            org.nameless.gamespace.data.UserGame$Companion r2 = org.nameless.gamespace.data.UserGame.Companion
            org.nameless.gamespace.data.UserGame r1 = r2.fromSettings(r1)
            r8.add(r1)
            goto L59
        L6f:
            java.util.List r8 = kotlin.collections.CollectionsKt.emptyList()
        L73:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: org.nameless.gamespace.data.SystemSettings.getUserGames():java.util.List");
    }

    public final void setUserGames(List<UserGame> games) {
        Intrinsics.checkNotNullParameter(games, "games");
        Settings.System.putStringForUser(this.resolver, "gamespace_game_list", games.isEmpty() ? "" : CollectionsKt___CollectionsKt.joinToString$default(games, ";", null, null, 0, null, new Function1<UserGame, CharSequence>() { // from class: org.nameless.gamespace.data.SystemSettings$userGames$3
            @Override // kotlin.jvm.functions.Function1
            public final CharSequence invoke(UserGame it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it.toString();
            }
        }, 30, null), -2);
        this.gameModeUtils.setupBatteryMode(!games.isEmpty());
    }

    public final boolean getAdbEnabled() {
        return Settings.Global.getInt(this.resolver, "adb_enabled", 0) == 1;
    }

    public final void setAdbEnabled(boolean z) {
        Settings.Global.putInt(this.resolver, "adb_enabled", toInt(z));
    }
}
