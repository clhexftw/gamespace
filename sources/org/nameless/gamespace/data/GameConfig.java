package org.nameless.gamespace.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: GameConfig.kt */
/* loaded from: classes.dex */
public final class GameConfig {
    public static final Companion Companion = new Companion(null);
    private final float downscaleFactor;
    private final int mode;
    private final boolean useAngle;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof GameConfig) {
            GameConfig gameConfig = (GameConfig) obj;
            return this.mode == gameConfig.mode && Float.compare(this.downscaleFactor, gameConfig.downscaleFactor) == 0 && this.useAngle == gameConfig.useAngle;
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int hashCode = ((Integer.hashCode(this.mode) * 31) + Float.hashCode(this.downscaleFactor)) * 31;
        boolean z = this.useAngle;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return hashCode + i;
    }

    public GameConfig(int i, float f, boolean z) {
        this.mode = i;
        this.downscaleFactor = f;
        this.useAngle = z;
    }

    public String toString() {
        String joinToString$default;
        HashMap hashMap = new HashMap();
        hashMap.put("mode", Integer.valueOf(this.mode));
        String format = String.format("%.1f", Arrays.copyOf(new Object[]{Float.valueOf(this.downscaleFactor)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(this, *args)");
        hashMap.put("downscaleFactor", format);
        if (this.useAngle) {
            hashMap.put("useAngle", Boolean.TRUE);
        }
        ArrayList arrayList = new ArrayList(hashMap.size());
        for (Map.Entry entry : hashMap.entrySet()) {
            Object value = entry.getValue();
            arrayList.add(((String) entry.getKey()) + "=" + value);
        }
        joinToString$default = CollectionsKt___CollectionsKt.joinToString$default(arrayList, ",", null, null, 0, null, null, 62, null);
        return joinToString$default;
    }

    /* compiled from: GameConfig.kt */
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String asConfig(Iterable<GameConfig> iterable) {
            String joinToString$default;
            Intrinsics.checkNotNullParameter(iterable, "<this>");
            joinToString$default = CollectionsKt___CollectionsKt.joinToString$default(iterable, ":", null, null, 0, null, new Function1<GameConfig, CharSequence>() { // from class: org.nameless.gamespace.data.GameConfig$Companion$asConfig$1
                @Override // kotlin.jvm.functions.Function1
                public final CharSequence invoke(GameConfig it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return it.toString();
                }
            }, 30, null);
            return joinToString$default;
        }
    }

    /* compiled from: GameConfig.kt */
    /* loaded from: classes.dex */
    public static final class ModeBuilder {
        public static final ModeBuilder INSTANCE = new ModeBuilder();
        private static boolean useAngle;

        private ModeBuilder() {
        }

        public final void setUseAngle(boolean z) {
            useAngle = z;
        }

        public final List<GameConfig> build() {
            List<GameConfig> listOf;
            listOf = CollectionsKt__CollectionsKt.listOf((Object[]) new GameConfig[]{new GameConfig(2, 0.7f, useAngle), new GameConfig(3, 0.8f, useAngle)});
            return listOf;
        }
    }
}
