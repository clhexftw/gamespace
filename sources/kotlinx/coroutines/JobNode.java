package kotlinx.coroutines;

import kotlin.jvm.internal.Intrinsics;
/* compiled from: JobSupport.kt */
/* loaded from: classes2.dex */
public abstract class JobNode extends CompletionHandlerBase implements DisposableHandle, Incomplete {
    public JobSupport job;

    @Override // kotlinx.coroutines.Incomplete
    public NodeList getList() {
        return null;
    }

    @Override // kotlinx.coroutines.Incomplete
    public boolean isActive() {
        return true;
    }

    public final JobSupport getJob() {
        JobSupport jobSupport = this.job;
        if (jobSupport != null) {
            return jobSupport;
        }
        Intrinsics.throwUninitializedPropertyAccessException("job");
        return null;
    }

    public final void setJob(JobSupport jobSupport) {
        Intrinsics.checkNotNullParameter(jobSupport, "<set-?>");
        this.job = jobSupport;
    }

    @Override // kotlinx.coroutines.DisposableHandle
    public void dispose() {
        getJob().removeNode$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(this);
    }

    @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
    public String toString() {
        String classSimpleName = DebugStringsKt.getClassSimpleName(this);
        String hexAddress = DebugStringsKt.getHexAddress(this);
        String hexAddress2 = DebugStringsKt.getHexAddress(getJob());
        return classSimpleName + "@" + hexAddress + "[job@" + hexAddress2 + "]";
    }
}
