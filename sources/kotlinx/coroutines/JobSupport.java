package kotlinx.coroutines;

import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CancellationException;
import kotlin.ExceptionsKt__ExceptionsKt;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlinx.atomicfu.AtomicBoolean;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicRef;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.internal.LockFreeLinkedListKt;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.OpDescriptor;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
/* compiled from: JobSupport.kt */
/* loaded from: classes2.dex */
public class JobSupport implements Job, ChildJob, ParentJob {
    private final AtomicRef<ChildHandle> _parentHandle;
    private final AtomicRef<Object> _state;

    /* JADX INFO: Access modifiers changed from: protected */
    public void afterCompletion(Object obj) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String cancellationExceptionMessage() {
        return "Job was cancelled";
    }

    public boolean getHandlesException$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host() {
        return true;
    }

    public boolean getOnCancelComplete$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host() {
        return false;
    }

    protected boolean handleJobException(Throwable exception) {
        Intrinsics.checkNotNullParameter(exception, "exception");
        return false;
    }

    protected boolean isScopedCoroutine() {
        return false;
    }

    protected void onCancelling(Throwable th) {
    }

    protected void onCompletionInternal(Object obj) {
    }

    protected void onStart() {
    }

    public JobSupport(boolean z) {
        this._state = AtomicFU.atomic(z ? JobSupportKt.access$getEMPTY_ACTIVE$p() : JobSupportKt.access$getEMPTY_NEW$p());
        this._parentHandle = AtomicFU.atomic((Object) null);
    }

    @Override // kotlin.coroutines.CoroutineContext
    public <R> R fold(R r, Function2<? super R, ? super CoroutineContext.Element, ? extends R> function2) {
        return (R) Job.DefaultImpls.fold(this, r, function2);
    }

    @Override // kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
    public <E extends CoroutineContext.Element> E get(CoroutineContext.Key<E> key) {
        return (E) Job.DefaultImpls.get(this, key);
    }

    @Override // kotlin.coroutines.CoroutineContext
    public CoroutineContext minusKey(CoroutineContext.Key<?> key) {
        return Job.DefaultImpls.minusKey(this, key);
    }

    @Override // kotlin.coroutines.CoroutineContext
    public CoroutineContext plus(CoroutineContext coroutineContext) {
        return Job.DefaultImpls.plus(this, coroutineContext);
    }

    @Override // kotlin.coroutines.CoroutineContext.Element
    public final CoroutineContext.Key<?> getKey() {
        return Job.Key;
    }

    private final boolean addLastAtomic(final Object obj, NodeList nodeList, final JobNode jobNode) {
        int tryCondAddNext;
        LockFreeLinkedListNode.CondAddOp condAddOp = new LockFreeLinkedListNode.CondAddOp(jobNode) { // from class: kotlinx.coroutines.JobSupport$addLastAtomic$$inlined$addLastIf$1
            @Override // kotlinx.coroutines.internal.AtomicOp
            public Object prepare(LockFreeLinkedListNode affected) {
                Intrinsics.checkNotNullParameter(affected, "affected");
                if (this.getState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host() == obj) {
                    return null;
                }
                return LockFreeLinkedListKt.getCONDITION_FALSE();
            }
        };
        do {
            tryCondAddNext = nodeList.getPrevNode().tryCondAddNext(jobNode, nodeList, condAddOp);
            if (tryCondAddNext == 1) {
                return true;
            }
        } while (tryCondAddNext != 2);
        return false;
    }

    public final ChildHandle getParentHandle$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host() {
        return this._parentHandle.getValue();
    }

    public final void setParentHandle$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(ChildHandle childHandle) {
        this._parentHandle.setValue(childHandle);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void initParentJob(Job job) {
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(getParentHandle$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host() == null)) {
                throw new AssertionError();
            }
        }
        if (job == null) {
            setParentHandle$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(NonDisposableHandle.INSTANCE);
            return;
        }
        job.start();
        ChildHandle attachChild = job.attachChild(this);
        setParentHandle$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(attachChild);
        if (isCompleted()) {
            attachChild.dispose();
            setParentHandle$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(NonDisposableHandle.INSTANCE);
        }
    }

    public final Object getState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host() {
        AtomicRef<Object> atomicRef = this._state;
        while (true) {
            Object value = atomicRef.getValue();
            if (!(value instanceof OpDescriptor)) {
                return value;
            }
            ((OpDescriptor) value).perform(this);
        }
    }

    private final Object cancelMakeCompleting(Object obj) {
        Object tryMakeCompleting;
        do {
            Object state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host = getState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host();
            if (!(state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host instanceof Incomplete) || ((state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host instanceof Finishing) && ((Finishing) state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host).isCompleting())) {
                return JobSupportKt.access$getCOMPLETING_ALREADY$p();
            }
            tryMakeCompleting = tryMakeCompleting(state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host, new CompletedExceptionally(createCauseException(obj), false, 2, null));
        } while (tryMakeCompleting == JobSupportKt.access$getCOMPLETING_RETRY$p());
        return tryMakeCompleting;
    }

    private final Object makeCancelling(Object obj) {
        Throwable th = null;
        while (true) {
            Object state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host = getState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host();
            if (state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host instanceof Finishing) {
                synchronized (state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host) {
                    if (((Finishing) state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host).isSealed()) {
                        return JobSupportKt.access$getTOO_LATE_TO_CANCEL$p();
                    }
                    boolean isCancelling = ((Finishing) state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host).isCancelling();
                    if (obj != null || !isCancelling) {
                        if (th == null) {
                            th = createCauseException(obj);
                        }
                        ((Finishing) state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host).addExceptionLocked(th);
                    }
                    Throwable rootCause = isCancelling ^ true ? ((Finishing) state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host).getRootCause() : null;
                    if (rootCause != null) {
                        notifyCancelling(((Finishing) state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host).getList(), rootCause);
                    }
                    return JobSupportKt.access$getCOMPLETING_ALREADY$p();
                }
            } else if (state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host instanceof Incomplete) {
                if (th == null) {
                    th = createCauseException(obj);
                }
                Incomplete incomplete = (Incomplete) state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host;
                if (incomplete.isActive()) {
                    if (tryMakeCancelling(incomplete, th)) {
                        return JobSupportKt.access$getCOMPLETING_ALREADY$p();
                    }
                } else {
                    Object tryMakeCompleting = tryMakeCompleting(state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host, new CompletedExceptionally(th, false, 2, null));
                    if (tryMakeCompleting == JobSupportKt.access$getCOMPLETING_ALREADY$p()) {
                        throw new IllegalStateException(("Cannot happen in " + state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host).toString());
                    } else if (tryMakeCompleting != JobSupportKt.access$getCOMPLETING_RETRY$p()) {
                        return tryMakeCompleting;
                    }
                }
            } else {
                return JobSupportKt.access$getTOO_LATE_TO_CANCEL$p();
            }
        }
    }

    public final Object makeCompletingOnce$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(Object obj) {
        Object tryMakeCompleting;
        do {
            tryMakeCompleting = tryMakeCompleting(getState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(), obj);
            if (tryMakeCompleting == JobSupportKt.access$getCOMPLETING_ALREADY$p()) {
                throw new IllegalStateException("Job " + this + " is already complete or completing, but is being completed with " + obj, getExceptionOrNull(obj));
            }
        } while (tryMakeCompleting == JobSupportKt.access$getCOMPLETING_RETRY$p());
        return tryMakeCompleting;
    }

    public final void removeNode$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(JobNode node) {
        Object state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host;
        Intrinsics.checkNotNullParameter(node, "node");
        do {
            state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host = getState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host();
            if (!(state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host instanceof JobNode)) {
                if (!(state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host instanceof Incomplete) || ((Incomplete) state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host).getList() == null) {
                    return;
                }
                node.mo2185remove();
                return;
            } else if (state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host != node) {
                return;
            }
        } while (!this._state.compareAndSet(state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host, JobSupportKt.access$getEMPTY_ACTIVE$p()));
    }

    @Override // kotlinx.coroutines.Job
    public final boolean start() {
        int startInternal;
        do {
            startInternal = startInternal(getState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host());
            if (startInternal == 0) {
                return false;
            }
        } while (startInternal != 1);
        return true;
    }

    @Override // kotlinx.coroutines.Job
    public boolean isActive() {
        Object state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host = getState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host();
        return (state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host instanceof Incomplete) && ((Incomplete) state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host).isActive();
    }

    public final boolean isCompleted() {
        return !(getState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host() instanceof Incomplete);
    }

    public final boolean isCancelled() {
        Object state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host = getState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host();
        return (state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host instanceof CompletedExceptionally) || ((state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host instanceof Finishing) && ((Finishing) state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host).isCancelling());
    }

    private final Object finalizeFinishingState(Finishing finishing, Object obj) {
        boolean isCancelling;
        Throwable finalRootCause;
        boolean z = true;
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(getState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host() == finishing)) {
                throw new AssertionError();
            }
        }
        if (!DebugKt.getASSERTIONS_ENABLED() || (!finishing.isSealed())) {
            if (!DebugKt.getASSERTIONS_ENABLED() || finishing.isCompleting()) {
                CompletedExceptionally completedExceptionally = obj instanceof CompletedExceptionally ? (CompletedExceptionally) obj : null;
                Throwable th = completedExceptionally != null ? completedExceptionally.cause : null;
                synchronized (finishing) {
                    isCancelling = finishing.isCancelling();
                    List<Throwable> sealLocked = finishing.sealLocked(th);
                    finalRootCause = getFinalRootCause(finishing, sealLocked);
                    if (finalRootCause != null) {
                        addSuppressedExceptions(finalRootCause, sealLocked);
                    }
                }
                if (finalRootCause != null && finalRootCause != th) {
                    obj = new CompletedExceptionally(finalRootCause, false, 2, null);
                }
                if (finalRootCause != null) {
                    if (!cancelParent(finalRootCause) && !handleJobException(finalRootCause)) {
                        z = false;
                    }
                    if (z) {
                        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlinx.coroutines.CompletedExceptionally");
                        ((CompletedExceptionally) obj).makeHandled();
                    }
                }
                if (!isCancelling) {
                    onCancelling(finalRootCause);
                }
                onCompletionInternal(obj);
                boolean compareAndSet = this._state.compareAndSet(finishing, JobSupportKt.boxIncomplete(obj));
                if (!DebugKt.getASSERTIONS_ENABLED() || compareAndSet) {
                    completeStateFinalization(finishing, obj);
                    return obj;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    private final Throwable getFinalRootCause(Finishing finishing, List<? extends Throwable> list) {
        Object obj;
        boolean z;
        Object obj2 = null;
        if (list.isEmpty()) {
            if (finishing.isCancelling()) {
                return new JobCancellationException(cancellationExceptionMessage(), null, this);
            }
            return null;
        }
        List<? extends Throwable> list2 = list;
        Iterator<T> it = list2.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (!(((Throwable) obj) instanceof CancellationException)) {
                break;
            }
        }
        Throwable th = (Throwable) obj;
        if (th != null) {
            return th;
        }
        Throwable th2 = list.get(0);
        if (th2 instanceof TimeoutCancellationException) {
            Iterator<T> it2 = list2.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                Object next = it2.next();
                Throwable th3 = (Throwable) next;
                if (th3 == th2 || !(th3 instanceof TimeoutCancellationException)) {
                    z = false;
                    continue;
                } else {
                    z = true;
                    continue;
                }
                if (z) {
                    obj2 = next;
                    break;
                }
            }
            Throwable th4 = (Throwable) obj2;
            if (th4 != null) {
                return th4;
            }
        }
        return th2;
    }

    private final void addSuppressedExceptions(Throwable th, List<? extends Throwable> list) {
        if (list.size() <= 1) {
            return;
        }
        Set newSetFromMap = Collections.newSetFromMap(new IdentityHashMap(list.size()));
        Intrinsics.checkNotNullExpressionValue(newSetFromMap, "newSetFromMap(IdentityHashMap(expectedSize))");
        Throwable unwrapImpl = !DebugKt.getRECOVER_STACK_TRACES() ? th : StackTraceRecoveryKt.unwrapImpl(th);
        for (Throwable th2 : list) {
            if (DebugKt.getRECOVER_STACK_TRACES()) {
                th2 = StackTraceRecoveryKt.unwrapImpl(th2);
            }
            if (th2 != th && th2 != unwrapImpl && !(th2 instanceof CancellationException) && newSetFromMap.add(th2)) {
                ExceptionsKt__ExceptionsKt.addSuppressed(th, th2);
            }
        }
    }

    private final boolean tryFinalizeSimpleState(Incomplete incomplete, Object obj) {
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!((incomplete instanceof Empty) || (incomplete instanceof JobNode))) {
                throw new AssertionError();
            }
        }
        if (!DebugKt.getASSERTIONS_ENABLED() || (!(obj instanceof CompletedExceptionally))) {
            if (this._state.compareAndSet(incomplete, JobSupportKt.boxIncomplete(obj))) {
                onCancelling(null);
                onCompletionInternal(obj);
                completeStateFinalization(incomplete, obj);
                return true;
            }
            return false;
        }
        throw new AssertionError();
    }

    private final void completeStateFinalization(Incomplete incomplete, Object obj) {
        ChildHandle parentHandle$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host = getParentHandle$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host();
        if (parentHandle$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host != null) {
            parentHandle$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host.dispose();
            setParentHandle$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(NonDisposableHandle.INSTANCE);
        }
        CompletedExceptionally completedExceptionally = obj instanceof CompletedExceptionally ? (CompletedExceptionally) obj : null;
        Throwable th = completedExceptionally != null ? completedExceptionally.cause : null;
        if (incomplete instanceof JobNode) {
            try {
                ((JobNode) incomplete).invoke(th);
                return;
            } catch (Throwable th2) {
                handleOnCompletionException$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(new CompletionHandlerException("Exception in completion handler " + incomplete + " for " + this, th2));
                return;
            }
        }
        NodeList list = incomplete.getList();
        if (list != null) {
            notifyCompletion(list, th);
        }
    }

    private final void notifyCancelling(NodeList nodeList, Throwable th) {
        onCancelling(th);
        Object next = nodeList.getNext();
        Intrinsics.checkNotNull(next, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }");
        CompletionHandlerException completionHandlerException = null;
        for (LockFreeLinkedListNode lockFreeLinkedListNode = (LockFreeLinkedListNode) next; !Intrinsics.areEqual(lockFreeLinkedListNode, nodeList); lockFreeLinkedListNode = lockFreeLinkedListNode.getNextNode()) {
            if (lockFreeLinkedListNode instanceof JobCancellingNode) {
                JobNode jobNode = (JobNode) lockFreeLinkedListNode;
                try {
                    jobNode.invoke(th);
                } catch (Throwable th2) {
                    if (completionHandlerException != null) {
                        ExceptionsKt__ExceptionsKt.addSuppressed(completionHandlerException, th2);
                    } else {
                        completionHandlerException = new CompletionHandlerException("Exception in completion handler " + jobNode + " for " + this, th2);
                        Unit unit = Unit.INSTANCE;
                    }
                }
            }
        }
        if (completionHandlerException != null) {
            handleOnCompletionException$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(completionHandlerException);
        }
        cancelParent(th);
    }

    private final boolean cancelParent(Throwable th) {
        if (isScopedCoroutine()) {
            return true;
        }
        boolean z = th instanceof CancellationException;
        ChildHandle parentHandle$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host = getParentHandle$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host();
        return (parentHandle$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host == null || parentHandle$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host == NonDisposableHandle.INSTANCE) ? z : parentHandle$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host.childCancelled(th) || z;
    }

    private final int startInternal(Object obj) {
        if (obj instanceof Empty) {
            if (((Empty) obj).isActive()) {
                return 0;
            }
            if (this._state.compareAndSet(obj, JobSupportKt.access$getEMPTY_ACTIVE$p())) {
                onStart();
                return 1;
            }
            return -1;
        } else if (obj instanceof InactiveNodeList) {
            if (this._state.compareAndSet(obj, ((InactiveNodeList) obj).getList())) {
                onStart();
                return 1;
            }
            return -1;
        } else {
            return 0;
        }
    }

    @Override // kotlinx.coroutines.Job
    public final CancellationException getCancellationException() {
        Object state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host = getState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host();
        if (state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host instanceof Finishing) {
            Throwable rootCause = ((Finishing) state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host).getRootCause();
            if (rootCause != null) {
                String classSimpleName = DebugStringsKt.getClassSimpleName(this);
                CancellationException cancellationException = toCancellationException(rootCause, classSimpleName + " is cancelling");
                if (cancellationException != null) {
                    return cancellationException;
                }
            }
            throw new IllegalStateException(("Job is still new or active: " + this).toString());
        } else if (state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host instanceof Incomplete) {
            throw new IllegalStateException(("Job is still new or active: " + this).toString());
        } else if (state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host instanceof CompletedExceptionally) {
            return toCancellationException$default(this, ((CompletedExceptionally) state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host).cause, null, 1, null);
        } else {
            String classSimpleName2 = DebugStringsKt.getClassSimpleName(this);
            return new JobCancellationException(classSimpleName2 + " has completed normally", null, this);
        }
    }

    public static /* synthetic */ CancellationException toCancellationException$default(JobSupport jobSupport, Throwable th, String str, int i, Object obj) {
        if (obj == null) {
            if ((i & 1) != 0) {
                str = null;
            }
            return jobSupport.toCancellationException(th, str);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: toCancellationException");
    }

    protected final CancellationException toCancellationException(Throwable th, String str) {
        Intrinsics.checkNotNullParameter(th, "<this>");
        CancellationException cancellationException = th instanceof CancellationException ? (CancellationException) th : null;
        if (cancellationException == null) {
            if (str == null) {
                str = cancellationExceptionMessage();
            }
            cancellationException = new JobCancellationException(str, th, this);
        }
        return cancellationException;
    }

    public final DisposableHandle invokeOnCompletion(Function1<? super Throwable, Unit> handler) {
        Intrinsics.checkNotNullParameter(handler, "handler");
        return invokeOnCompletion(false, true, handler);
    }

    @Override // kotlinx.coroutines.Job
    public final DisposableHandle invokeOnCompletion(boolean z, boolean z2, Function1<? super Throwable, Unit> handler) {
        Intrinsics.checkNotNullParameter(handler, "handler");
        JobNode makeNode = makeNode(handler, z);
        while (true) {
            Object state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host = getState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host();
            if (state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host instanceof Empty) {
                Empty empty = (Empty) state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host;
                if (empty.isActive()) {
                    if (this._state.compareAndSet(state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host, makeNode)) {
                        return makeNode;
                    }
                } else {
                    promoteEmptyToNodeList(empty);
                }
            } else {
                if (state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host instanceof Incomplete) {
                    NodeList list = ((Incomplete) state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host).getList();
                    if (list == null) {
                        Intrinsics.checkNotNull(state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host, "null cannot be cast to non-null type kotlinx.coroutines.JobNode");
                        promoteSingleToNodeList((JobNode) state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host);
                    } else {
                        DisposableHandle disposableHandle = NonDisposableHandle.INSTANCE;
                        if (z && (state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host instanceof Finishing)) {
                            synchronized (state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host) {
                                r3 = ((Finishing) state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host).getRootCause();
                                if (r3 == null || ((handler instanceof ChildHandleNode) && !((Finishing) state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host).isCompleting())) {
                                    if (addLastAtomic(state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host, list, makeNode)) {
                                        if (r3 == null) {
                                            return makeNode;
                                        }
                                        disposableHandle = makeNode;
                                    }
                                }
                                Unit unit = Unit.INSTANCE;
                            }
                        }
                        if (r3 != null) {
                            if (z2) {
                                handler.invoke(r3);
                            }
                            return disposableHandle;
                        } else if (addLastAtomic(state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host, list, makeNode)) {
                            return makeNode;
                        }
                    }
                } else {
                    if (z2) {
                        CompletedExceptionally completedExceptionally = state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host instanceof CompletedExceptionally ? (CompletedExceptionally) state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host : null;
                        handler.invoke(completedExceptionally != null ? completedExceptionally.cause : null);
                    }
                    return NonDisposableHandle.INSTANCE;
                }
            }
        }
    }

    private final JobNode makeNode(Function1<? super Throwable, Unit> function1, boolean z) {
        JobNode jobNode;
        if (z) {
            jobNode = function1 instanceof JobCancellingNode ? (JobCancellingNode) function1 : null;
            if (jobNode == null) {
                jobNode = new InvokeOnCancelling(function1);
            }
        } else {
            jobNode = function1 instanceof JobNode ? (JobNode) function1 : null;
            if (jobNode != null) {
                if (DebugKt.getASSERTIONS_ENABLED() && !(!(jobNode instanceof JobCancellingNode))) {
                    throw new AssertionError();
                }
            } else {
                jobNode = new InvokeOnCompletion(function1);
            }
        }
        jobNode.setJob(this);
        return jobNode;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [kotlinx.coroutines.InactiveNodeList] */
    private final void promoteEmptyToNodeList(Empty empty) {
        NodeList nodeList = new NodeList();
        if (!empty.isActive()) {
            nodeList = new InactiveNodeList(nodeList);
        }
        this._state.compareAndSet(empty, nodeList);
    }

    private final void promoteSingleToNodeList(JobNode jobNode) {
        jobNode.addOneIfEmpty(new NodeList());
        this._state.compareAndSet(jobNode, jobNode.getNextNode());
    }

    public void cancelInternal(Throwable cause) {
        Intrinsics.checkNotNullParameter(cause, "cause");
        cancelImpl$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(cause);
    }

    @Override // kotlinx.coroutines.ChildJob
    public final void parentCancelled(ParentJob parentJob) {
        Intrinsics.checkNotNullParameter(parentJob, "parentJob");
        cancelImpl$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(parentJob);
    }

    private final void notifyCompletion(NodeList nodeList, Throwable th) {
        Object next = nodeList.getNext();
        Intrinsics.checkNotNull(next, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }");
        CompletionHandlerException completionHandlerException = null;
        for (LockFreeLinkedListNode lockFreeLinkedListNode = (LockFreeLinkedListNode) next; !Intrinsics.areEqual(lockFreeLinkedListNode, nodeList); lockFreeLinkedListNode = lockFreeLinkedListNode.getNextNode()) {
            if (lockFreeLinkedListNode instanceof JobNode) {
                JobNode jobNode = (JobNode) lockFreeLinkedListNode;
                try {
                    jobNode.invoke(th);
                } catch (Throwable th2) {
                    if (completionHandlerException != null) {
                        ExceptionsKt__ExceptionsKt.addSuppressed(completionHandlerException, th2);
                    } else {
                        completionHandlerException = new CompletionHandlerException("Exception in completion handler " + jobNode + " for " + this, th2);
                        Unit unit = Unit.INSTANCE;
                    }
                }
            }
        }
        if (completionHandlerException != null) {
            handleOnCompletionException$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(completionHandlerException);
        }
    }

    public boolean childCancelled(Throwable cause) {
        Intrinsics.checkNotNullParameter(cause, "cause");
        if (cause instanceof CancellationException) {
            return true;
        }
        return cancelImpl$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(cause) && getHandlesException$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host();
    }

    public final boolean cancelCoroutine(Throwable th) {
        return cancelImpl$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(th);
    }

    public final boolean cancelImpl$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(Object obj) {
        Object access$getCOMPLETING_ALREADY$p = JobSupportKt.access$getCOMPLETING_ALREADY$p();
        if (getOnCancelComplete$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host() && (access$getCOMPLETING_ALREADY$p = cancelMakeCompleting(obj)) == JobSupportKt.COMPLETING_WAITING_CHILDREN) {
            return true;
        }
        if (access$getCOMPLETING_ALREADY$p == JobSupportKt.access$getCOMPLETING_ALREADY$p()) {
            access$getCOMPLETING_ALREADY$p = makeCancelling(obj);
        }
        if (access$getCOMPLETING_ALREADY$p == JobSupportKt.access$getCOMPLETING_ALREADY$p() || access$getCOMPLETING_ALREADY$p == JobSupportKt.COMPLETING_WAITING_CHILDREN) {
            return true;
        }
        if (access$getCOMPLETING_ALREADY$p == JobSupportKt.access$getTOO_LATE_TO_CANCEL$p()) {
            return false;
        }
        afterCompletion(access$getCOMPLETING_ALREADY$p);
        return true;
    }

    @Override // kotlinx.coroutines.Job
    public void cancel(CancellationException cancellationException) {
        if (cancellationException == null) {
            cancellationException = new JobCancellationException(cancellationExceptionMessage(), null, this);
        }
        cancelInternal(cancellationException);
    }

    @Override // kotlinx.coroutines.ParentJob
    public CancellationException getChildJobCancellationCause() {
        Throwable th;
        Object state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host = getState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host();
        if (state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host instanceof Finishing) {
            th = ((Finishing) state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host).getRootCause();
        } else if (state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host instanceof CompletedExceptionally) {
            th = ((CompletedExceptionally) state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host).cause;
        } else if (state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host instanceof Incomplete) {
            throw new IllegalStateException(("Cannot be cancelling child in this state: " + state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host).toString());
        } else {
            th = null;
        }
        CancellationException cancellationException = th instanceof CancellationException ? th : null;
        if (cancellationException == null) {
            return new JobCancellationException("Parent job is " + stateString(state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host), th, this);
        }
        return cancellationException;
    }

    private final Throwable createCauseException(Object obj) {
        if (obj == null ? true : obj instanceof Throwable) {
            Throwable th = (Throwable) obj;
            return th == null ? new JobCancellationException(cancellationExceptionMessage(), null, this) : th;
        }
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlinx.coroutines.ParentJob");
        return ((ParentJob) obj).getChildJobCancellationCause();
    }

    private final NodeList getOrPromoteCancellingList(Incomplete incomplete) {
        NodeList list = incomplete.getList();
        if (list == null) {
            if (incomplete instanceof Empty) {
                return new NodeList();
            }
            if (incomplete instanceof JobNode) {
                promoteSingleToNodeList((JobNode) incomplete);
                return null;
            }
            throw new IllegalStateException(("State should have list: " + incomplete).toString());
        }
        return list;
    }

    private final boolean tryMakeCancelling(Incomplete incomplete, Throwable th) {
        if (!DebugKt.getASSERTIONS_ENABLED() || (!(incomplete instanceof Finishing))) {
            if (!DebugKt.getASSERTIONS_ENABLED() || incomplete.isActive()) {
                NodeList orPromoteCancellingList = getOrPromoteCancellingList(incomplete);
                if (orPromoteCancellingList == null) {
                    return false;
                }
                if (this._state.compareAndSet(incomplete, new Finishing(orPromoteCancellingList, false, th))) {
                    notifyCancelling(orPromoteCancellingList, th);
                    return true;
                }
                return false;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    private final Object tryMakeCompleting(Object obj, Object obj2) {
        if (!(obj instanceof Incomplete)) {
            return JobSupportKt.access$getCOMPLETING_ALREADY$p();
        }
        if ((!(obj instanceof Empty) && !(obj instanceof JobNode)) || (obj instanceof ChildHandleNode) || (obj2 instanceof CompletedExceptionally)) {
            return tryMakeCompletingSlowPath((Incomplete) obj, obj2);
        }
        return tryFinalizeSimpleState((Incomplete) obj, obj2) ? obj2 : JobSupportKt.access$getCOMPLETING_RETRY$p();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.lang.Throwable, T] */
    /* JADX WARN: Type inference failed for: r2v2 */
    private final Object tryMakeCompletingSlowPath(Incomplete incomplete, Object obj) {
        NodeList orPromoteCancellingList = getOrPromoteCancellingList(incomplete);
        if (orPromoteCancellingList == null) {
            return JobSupportKt.access$getCOMPLETING_RETRY$p();
        }
        Finishing finishing = incomplete instanceof Finishing ? (Finishing) incomplete : null;
        if (finishing == null) {
            finishing = new Finishing(orPromoteCancellingList, false, null);
        }
        Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
        synchronized (finishing) {
            if (finishing.isCompleting()) {
                return JobSupportKt.access$getCOMPLETING_ALREADY$p();
            }
            finishing.setCompleting(true);
            if (finishing != incomplete && !this._state.compareAndSet(incomplete, finishing)) {
                return JobSupportKt.access$getCOMPLETING_RETRY$p();
            }
            if (DebugKt.getASSERTIONS_ENABLED() && !(!finishing.isSealed())) {
                throw new AssertionError();
            }
            boolean isCancelling = finishing.isCancelling();
            CompletedExceptionally completedExceptionally = obj instanceof CompletedExceptionally ? (CompletedExceptionally) obj : null;
            if (completedExceptionally != null) {
                finishing.addExceptionLocked(completedExceptionally.cause);
            }
            ?? rootCause = Boolean.valueOf(isCancelling ? false : true).booleanValue() ? finishing.getRootCause() : 0;
            ref$ObjectRef.element = rootCause;
            Unit unit = Unit.INSTANCE;
            if (rootCause != 0) {
                notifyCancelling(orPromoteCancellingList, rootCause);
            }
            ChildHandleNode firstChild = firstChild(incomplete);
            if (firstChild != null && tryWaitForChild(finishing, firstChild, obj)) {
                return JobSupportKt.COMPLETING_WAITING_CHILDREN;
            }
            return finalizeFinishingState(finishing, obj);
        }
    }

    private final Throwable getExceptionOrNull(Object obj) {
        CompletedExceptionally completedExceptionally = obj instanceof CompletedExceptionally ? (CompletedExceptionally) obj : null;
        if (completedExceptionally != null) {
            return completedExceptionally.cause;
        }
        return null;
    }

    private final ChildHandleNode firstChild(Incomplete incomplete) {
        ChildHandleNode childHandleNode = incomplete instanceof ChildHandleNode ? (ChildHandleNode) incomplete : null;
        if (childHandleNode == null) {
            NodeList list = incomplete.getList();
            if (list != null) {
                return nextChild(list);
            }
            return null;
        }
        return childHandleNode;
    }

    private final boolean tryWaitForChild(Finishing finishing, ChildHandleNode childHandleNode, Object obj) {
        while (Job.DefaultImpls.invokeOnCompletion$default(childHandleNode.childJob, false, false, new ChildCompletion(this, finishing, childHandleNode, obj), 1, null) == NonDisposableHandle.INSTANCE) {
            childHandleNode = nextChild(childHandleNode);
            if (childHandleNode == null) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void continueCompleting(Finishing finishing, ChildHandleNode childHandleNode, Object obj) {
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(getState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host() == finishing)) {
                throw new AssertionError();
            }
        }
        ChildHandleNode nextChild = nextChild(childHandleNode);
        if (nextChild == null || !tryWaitForChild(finishing, nextChild, obj)) {
            afterCompletion(finalizeFinishingState(finishing, obj));
        }
    }

    private final ChildHandleNode nextChild(LockFreeLinkedListNode lockFreeLinkedListNode) {
        while (lockFreeLinkedListNode.isRemoved()) {
            lockFreeLinkedListNode = lockFreeLinkedListNode.getPrevNode();
        }
        while (true) {
            lockFreeLinkedListNode = lockFreeLinkedListNode.getNextNode();
            if (!lockFreeLinkedListNode.isRemoved()) {
                if (lockFreeLinkedListNode instanceof ChildHandleNode) {
                    return (ChildHandleNode) lockFreeLinkedListNode;
                }
                if (lockFreeLinkedListNode instanceof NodeList) {
                    return null;
                }
            }
        }
    }

    @Override // kotlinx.coroutines.Job
    public final ChildHandle attachChild(ChildJob child) {
        Intrinsics.checkNotNullParameter(child, "child");
        DisposableHandle invokeOnCompletion$default = Job.DefaultImpls.invokeOnCompletion$default(this, true, false, new ChildHandleNode(child), 2, null);
        Intrinsics.checkNotNull(invokeOnCompletion$default, "null cannot be cast to non-null type kotlinx.coroutines.ChildHandle");
        return (ChildHandle) invokeOnCompletion$default;
    }

    public void handleOnCompletionException$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(Throwable exception) {
        Intrinsics.checkNotNullParameter(exception, "exception");
        throw exception;
    }

    public String toString() {
        String debugString = toDebugString();
        String hexAddress = DebugStringsKt.getHexAddress(this);
        return debugString + "@" + hexAddress;
    }

    public final String toDebugString() {
        String nameString$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host = nameString$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host();
        String stateString = stateString(getState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host());
        return nameString$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host + "{" + stateString + "}";
    }

    public String nameString$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host() {
        return DebugStringsKt.getClassSimpleName(this);
    }

    private final String stateString(Object obj) {
        if (!(obj instanceof Finishing)) {
            return obj instanceof Incomplete ? ((Incomplete) obj).isActive() ? "Active" : "New" : obj instanceof CompletedExceptionally ? "Cancelled" : "Completed";
        }
        Finishing finishing = (Finishing) obj;
        return finishing.isCancelling() ? "Cancelling" : finishing.isCompleting() ? "Completing" : "Active";
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: JobSupport.kt */
    /* loaded from: classes2.dex */
    public static final class Finishing implements Incomplete {
        private final AtomicRef<Object> _exceptionsHolder;
        private final AtomicBoolean _isCompleting;
        private final AtomicRef<Throwable> _rootCause;
        private final NodeList list;

        @Override // kotlinx.coroutines.Incomplete
        public NodeList getList() {
            return this.list;
        }

        public Finishing(NodeList list, boolean z, Throwable th) {
            Intrinsics.checkNotNullParameter(list, "list");
            this.list = list;
            this._isCompleting = AtomicFU.atomic(z);
            this._rootCause = AtomicFU.atomic(th);
            this._exceptionsHolder = AtomicFU.atomic((Object) null);
        }

        public final boolean isCompleting() {
            return this._isCompleting.getValue();
        }

        public final void setCompleting(boolean z) {
            this._isCompleting.setValue(z);
        }

        public final Throwable getRootCause() {
            return this._rootCause.getValue();
        }

        public final void setRootCause(Throwable th) {
            this._rootCause.setValue(th);
        }

        private final Object getExceptionsHolder() {
            return this._exceptionsHolder.getValue();
        }

        private final void setExceptionsHolder(Object obj) {
            this._exceptionsHolder.setValue(obj);
        }

        public final boolean isSealed() {
            return getExceptionsHolder() == JobSupportKt.access$getSEALED$p();
        }

        public final boolean isCancelling() {
            return getRootCause() != null;
        }

        @Override // kotlinx.coroutines.Incomplete
        public boolean isActive() {
            return getRootCause() == null;
        }

        public final List<Throwable> sealLocked(Throwable th) {
            ArrayList<Throwable> arrayList;
            Object exceptionsHolder = getExceptionsHolder();
            if (exceptionsHolder == null) {
                arrayList = allocateList();
            } else if (exceptionsHolder instanceof Throwable) {
                ArrayList<Throwable> allocateList = allocateList();
                allocateList.add(exceptionsHolder);
                arrayList = allocateList;
            } else if (!(exceptionsHolder instanceof ArrayList)) {
                throw new IllegalStateException(("State is " + exceptionsHolder).toString());
            } else {
                arrayList = (ArrayList) exceptionsHolder;
            }
            Throwable rootCause = getRootCause();
            if (rootCause != null) {
                arrayList.add(0, rootCause);
            }
            if (th != null && !Intrinsics.areEqual(th, rootCause)) {
                arrayList.add(th);
            }
            setExceptionsHolder(JobSupportKt.access$getSEALED$p());
            return arrayList;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final void addExceptionLocked(Throwable exception) {
            Intrinsics.checkNotNullParameter(exception, "exception");
            Throwable rootCause = getRootCause();
            if (rootCause == null) {
                setRootCause(exception);
            } else if (exception == rootCause) {
            } else {
                Object exceptionsHolder = getExceptionsHolder();
                if (exceptionsHolder == null) {
                    setExceptionsHolder(exception);
                } else if (exceptionsHolder instanceof Throwable) {
                    if (exception == exceptionsHolder) {
                        return;
                    }
                    ArrayList<Throwable> allocateList = allocateList();
                    allocateList.add(exceptionsHolder);
                    allocateList.add(exception);
                    setExceptionsHolder(allocateList);
                } else if (exceptionsHolder instanceof ArrayList) {
                    ((ArrayList) exceptionsHolder).add(exception);
                } else {
                    throw new IllegalStateException(("State is " + exceptionsHolder).toString());
                }
            }
        }

        private final ArrayList<Throwable> allocateList() {
            return new ArrayList<>(4);
        }

        public String toString() {
            boolean isCancelling = isCancelling();
            boolean isCompleting = isCompleting();
            Throwable rootCause = getRootCause();
            Object exceptionsHolder = getExceptionsHolder();
            NodeList list = getList();
            return "Finishing[cancelling=" + isCancelling + ", completing=" + isCompleting + ", rootCause=" + rootCause + ", exceptions=" + exceptionsHolder + ", list=" + list + "]";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: JobSupport.kt */
    /* loaded from: classes2.dex */
    public static final class ChildCompletion extends JobNode {
        private final ChildHandleNode child;
        private final JobSupport parent;
        private final Object proposedUpdate;
        private final Finishing state;

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
            invoke2(th);
            return Unit.INSTANCE;
        }

        public ChildCompletion(JobSupport parent, Finishing state, ChildHandleNode child, Object obj) {
            Intrinsics.checkNotNullParameter(parent, "parent");
            Intrinsics.checkNotNullParameter(state, "state");
            Intrinsics.checkNotNullParameter(child, "child");
            this.parent = parent;
            this.state = state;
            this.child = child;
            this.proposedUpdate = obj;
        }

        @Override // kotlinx.coroutines.CompletionHandlerBase
        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public void invoke2(Throwable th) {
            this.parent.continueCompleting(this.state, this.child, this.proposedUpdate);
        }
    }
}
