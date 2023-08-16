package androidx.window.area;

import androidx.window.core.ExperimentalWindowApi;
import kotlin.jvm.internal.DefaultConstructorMarker;
/* compiled from: WindowAreaStatus.kt */
@ExperimentalWindowApi
/* loaded from: classes.dex */
public final class WindowAreaStatus {
    private final String mDescription;
    public static final Companion Companion = new Companion(null);
    public static final WindowAreaStatus UNSUPPORTED = new WindowAreaStatus("UNSUPPORTED");
    public static final WindowAreaStatus UNAVAILABLE = new WindowAreaStatus("UNAVAILABLE");
    public static final WindowAreaStatus AVAILABLE = new WindowAreaStatus("AVAILABLE");

    private WindowAreaStatus(String str) {
        this.mDescription = str;
    }

    public String toString() {
        return this.mDescription;
    }

    /* compiled from: WindowAreaStatus.kt */
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
