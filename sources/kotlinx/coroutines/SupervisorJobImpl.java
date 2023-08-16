package kotlinx.coroutines;

import kotlin.jvm.internal.Intrinsics;
/* compiled from: Supervisor.kt */
/* loaded from: classes2.dex */
final class SupervisorJobImpl extends JobImpl {
    @Override // kotlinx.coroutines.JobSupport
    public boolean childCancelled(Throwable cause) {
        Intrinsics.checkNotNullParameter(cause, "cause");
        return false;
    }

    public SupervisorJobImpl(Job job) {
        super(job);
    }
}
