package org.nameless.gamespace.data;

import androidx.annotation.Keep;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: SessionState.kt */
@Keep
/* loaded from: classes.dex */
public final class SessionState {
    private Boolean adbEnabled;
    private Boolean autoBrightness;
    private Boolean headsUp;
    private String packageName;
    private int ringerMode;
    private Boolean threeScreenshot;

    public static /* synthetic */ SessionState copy$default(SessionState sessionState, String str, Boolean bool, Boolean bool2, Boolean bool3, int i, Boolean bool4, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = sessionState.packageName;
        }
        if ((i2 & 2) != 0) {
            bool = sessionState.autoBrightness;
        }
        Boolean bool5 = bool;
        if ((i2 & 4) != 0) {
            bool2 = sessionState.threeScreenshot;
        }
        Boolean bool6 = bool2;
        if ((i2 & 8) != 0) {
            bool3 = sessionState.headsUp;
        }
        Boolean bool7 = bool3;
        if ((i2 & 16) != 0) {
            i = sessionState.ringerMode;
        }
        int i3 = i;
        if ((i2 & 32) != 0) {
            bool4 = sessionState.adbEnabled;
        }
        return sessionState.copy(str, bool5, bool6, bool7, i3, bool4);
    }

    public final String component1() {
        return this.packageName;
    }

    public final Boolean component2() {
        return this.autoBrightness;
    }

    public final Boolean component3() {
        return this.threeScreenshot;
    }

    public final Boolean component4() {
        return this.headsUp;
    }

    public final int component5() {
        return this.ringerMode;
    }

    public final Boolean component6() {
        return this.adbEnabled;
    }

    public final SessionState copy(String packageName, Boolean bool, Boolean bool2, Boolean bool3, int i, Boolean bool4) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        return new SessionState(packageName, bool, bool2, bool3, i, bool4);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SessionState) {
            SessionState sessionState = (SessionState) obj;
            return Intrinsics.areEqual(this.packageName, sessionState.packageName) && Intrinsics.areEqual(this.autoBrightness, sessionState.autoBrightness) && Intrinsics.areEqual(this.threeScreenshot, sessionState.threeScreenshot) && Intrinsics.areEqual(this.headsUp, sessionState.headsUp) && this.ringerMode == sessionState.ringerMode && Intrinsics.areEqual(this.adbEnabled, sessionState.adbEnabled);
        }
        return false;
    }

    public int hashCode() {
        int hashCode = this.packageName.hashCode() * 31;
        Boolean bool = this.autoBrightness;
        int hashCode2 = (hashCode + (bool == null ? 0 : bool.hashCode())) * 31;
        Boolean bool2 = this.threeScreenshot;
        int hashCode3 = (hashCode2 + (bool2 == null ? 0 : bool2.hashCode())) * 31;
        Boolean bool3 = this.headsUp;
        int hashCode4 = (((hashCode3 + (bool3 == null ? 0 : bool3.hashCode())) * 31) + Integer.hashCode(this.ringerMode)) * 31;
        Boolean bool4 = this.adbEnabled;
        return hashCode4 + (bool4 != null ? bool4.hashCode() : 0);
    }

    public String toString() {
        String str = this.packageName;
        Boolean bool = this.autoBrightness;
        Boolean bool2 = this.threeScreenshot;
        Boolean bool3 = this.headsUp;
        int i = this.ringerMode;
        Boolean bool4 = this.adbEnabled;
        return "SessionState(packageName=" + str + ", autoBrightness=" + bool + ", threeScreenshot=" + bool2 + ", headsUp=" + bool3 + ", ringerMode=" + i + ", adbEnabled=" + bool4 + ")";
    }

    public SessionState(String packageName, Boolean bool, Boolean bool2, Boolean bool3, int i, Boolean bool4) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        this.packageName = packageName;
        this.autoBrightness = bool;
        this.threeScreenshot = bool2;
        this.headsUp = bool3;
        this.ringerMode = i;
        this.adbEnabled = bool4;
    }

    public /* synthetic */ SessionState(String str, Boolean bool, Boolean bool2, Boolean bool3, int i, Boolean bool4, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i2 & 2) != 0 ? null : bool, (i2 & 4) != 0 ? null : bool2, (i2 & 8) != 0 ? null : bool3, (i2 & 16) != 0 ? 2 : i, (i2 & 32) == 0 ? bool4 : null);
    }

    public final String getPackageName() {
        return this.packageName;
    }

    public final void setPackageName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.packageName = str;
    }

    public final Boolean getAutoBrightness() {
        return this.autoBrightness;
    }

    public final void setAutoBrightness(Boolean bool) {
        this.autoBrightness = bool;
    }

    public final Boolean getThreeScreenshot() {
        return this.threeScreenshot;
    }

    public final void setThreeScreenshot(Boolean bool) {
        this.threeScreenshot = bool;
    }

    public final Boolean getHeadsUp() {
        return this.headsUp;
    }

    public final void setHeadsUp(Boolean bool) {
        this.headsUp = bool;
    }

    public final int getRingerMode() {
        return this.ringerMode;
    }

    public final void setRingerMode(int i) {
        this.ringerMode = i;
    }

    public final Boolean getAdbEnabled() {
        return this.adbEnabled;
    }

    public final void setAdbEnabled(Boolean bool) {
        this.adbEnabled = bool;
    }
}
