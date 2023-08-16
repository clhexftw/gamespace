package org.nameless.gamespace.data;

import java.util.List;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
/* compiled from: UserGame.kt */
/* loaded from: classes.dex */
public final class UserGame {
    public static final Companion Companion = new Companion(null);
    private final int mode;
    private final String packageName;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof UserGame) {
            UserGame userGame = (UserGame) obj;
            return Intrinsics.areEqual(this.packageName, userGame.packageName) && this.mode == userGame.mode;
        }
        return false;
    }

    public int hashCode() {
        return (this.packageName.hashCode() * 31) + Integer.hashCode(this.mode);
    }

    public UserGame(String packageName, int i) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        this.packageName = packageName;
        this.mode = i;
    }

    public /* synthetic */ UserGame(String str, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i2 & 2) != 0 ? 1 : i);
    }

    public final int getMode() {
        return this.mode;
    }

    public final String getPackageName() {
        return this.packageName;
    }

    public String toString() {
        String str = this.packageName;
        int i = this.mode;
        return str + "=" + i;
    }

    /* compiled from: UserGame.kt */
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final UserGame fromSettings(String data) {
            List split$default;
            Object first;
            Object last;
            Intrinsics.checkNotNullParameter(data, "data");
            split$default = StringsKt__StringsKt.split$default(data, new String[]{"="}, false, 0, 6, null);
            if (!(split$default.size() == 2)) {
                split$default = null;
            }
            if (split$default != null) {
                first = CollectionsKt___CollectionsKt.first(split$default);
                last = CollectionsKt___CollectionsKt.last(split$default);
                return new UserGame((String) first, Integer.parseInt((String) last));
            }
            return new UserGame(data, 0, 2, null);
        }
    }
}
