package androidx.fragment.app.strictmode;

import androidx.fragment.app.Fragment;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: SetRetainInstanceUsageViolation.kt */
/* loaded from: classes.dex */
public final class SetRetainInstanceUsageViolation extends RetainInstanceUsageViolation {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SetRetainInstanceUsageViolation(Fragment fragment) {
        super(fragment, Intrinsics.stringPlus("Attempting to set retain instance for fragment ", fragment));
        Intrinsics.checkNotNullParameter(fragment, "fragment");
    }
}
