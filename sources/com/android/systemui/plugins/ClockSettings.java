package com.android.systemui.plugins;

import com.android.internal.annotations.Keep;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;
/* compiled from: ClockProviderPlugin.kt */
@Keep
/* loaded from: classes2.dex */
public final class ClockSettings {
    private final String clockId;
    private JSONObject metadata;
    private final Integer seedColor;
    public static final Companion Companion = new Companion(null);
    private static final String KEY_CLOCK_ID = "clockId";
    private static final String KEY_SEED_COLOR = "seedColor";
    private static final String KEY_METADATA = "metadata";

    public ClockSettings() {
        this(null, null, 3, null);
    }

    public static /* synthetic */ ClockSettings copy$default(ClockSettings clockSettings, String str, Integer num, int i, Object obj) {
        if ((i & 1) != 0) {
            str = clockSettings.clockId;
        }
        if ((i & 2) != 0) {
            num = clockSettings.seedColor;
        }
        return clockSettings.copy(str, num);
    }

    public final String component1() {
        return this.clockId;
    }

    public final Integer component2() {
        return this.seedColor;
    }

    public final ClockSettings copy(String str, Integer num) {
        return new ClockSettings(str, num);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ClockSettings) {
            ClockSettings clockSettings = (ClockSettings) obj;
            return Intrinsics.areEqual(this.clockId, clockSettings.clockId) && Intrinsics.areEqual(this.seedColor, clockSettings.seedColor);
        }
        return false;
    }

    public int hashCode() {
        String str = this.clockId;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        Integer num = this.seedColor;
        return hashCode + (num != null ? num.hashCode() : 0);
    }

    public String toString() {
        String str = this.clockId;
        Integer num = this.seedColor;
        return "ClockSettings(clockId=" + str + ", seedColor=" + num + ")";
    }

    public ClockSettings(String str, Integer num) {
        this.clockId = str;
        this.seedColor = num;
        this.metadata = new JSONObject();
    }

    public /* synthetic */ ClockSettings(String str, Integer num, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : num);
    }

    public final String getClockId() {
        return this.clockId;
    }

    public final Integer getSeedColor() {
        return this.seedColor;
    }

    public final JSONObject getMetadata() {
        return this.metadata;
    }

    public final void setMetadata(JSONObject jSONObject) {
        Intrinsics.checkNotNullParameter(jSONObject, "<set-?>");
        this.metadata = jSONObject;
    }

    /* compiled from: ClockProviderPlugin.kt */
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
