package kotlinx.coroutines.sync;

import kotlin.jvm.internal.Intrinsics;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Mutex.kt */
/* loaded from: classes2.dex */
public final class Empty {
    public final Object locked;

    public Empty(Object locked) {
        Intrinsics.checkNotNullParameter(locked, "locked");
        this.locked = locked;
    }

    public String toString() {
        Object obj = this.locked;
        return "Empty[" + obj + "]";
    }
}
