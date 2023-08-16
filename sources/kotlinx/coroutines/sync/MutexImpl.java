package kotlinx.coroutines.sync;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.atomicfu.AtomicBoolean;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicRef;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CancellableContinuationImplKt;
import kotlinx.coroutines.CancellableContinuationKt;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.internal.AtomicOp;
import kotlinx.coroutines.internal.LockFreeLinkedListHead;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.OpDescriptor;
import kotlinx.coroutines.internal.Symbol;
/* compiled from: Mutex.kt */
/* loaded from: classes2.dex */
public final class MutexImpl implements Mutex {
    private final AtomicRef<Object> _state;

    public MutexImpl(boolean z) {
        this._state = AtomicFU.atomic(z ? MutexKt.EMPTY_LOCKED : MutexKt.EMPTY_UNLOCKED);
    }

    public boolean tryLock(Object obj) {
        Symbol symbol;
        AtomicRef<Object> atomicRef = this._state;
        while (true) {
            Object value = atomicRef.getValue();
            if (value instanceof Empty) {
                Object obj2 = ((Empty) value).locked;
                symbol = MutexKt.UNLOCKED;
                if (obj2 != symbol) {
                    return false;
                }
                if (this._state.compareAndSet(value, obj == null ? MutexKt.EMPTY_LOCKED : new Empty(obj))) {
                    return true;
                }
            } else if (value instanceof LockedQueue) {
                if (((LockedQueue) value).owner != obj) {
                    return false;
                }
                throw new IllegalStateException(("Already locked by " + obj).toString());
            } else if (!(value instanceof OpDescriptor)) {
                throw new IllegalStateException(("Illegal state " + value).toString());
            } else {
                ((OpDescriptor) value).perform(this);
            }
        }
    }

    @Override // kotlinx.coroutines.sync.Mutex
    public Object lock(Object obj, Continuation<? super Unit> continuation) {
        Object coroutine_suspended;
        if (tryLock(obj)) {
            return Unit.INSTANCE;
        }
        Object lockSuspend = lockSuspend(obj, continuation);
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        return lockSuspend == coroutine_suspended ? lockSuspend : Unit.INSTANCE;
    }

    @Override // kotlinx.coroutines.sync.Mutex
    public void unlock(Object obj) {
        Empty empty;
        Symbol symbol;
        AtomicRef<Object> atomicRef = this._state;
        while (true) {
            Object value = atomicRef.getValue();
            if (value instanceof Empty) {
                if (obj == null) {
                    Object obj2 = ((Empty) value).locked;
                    symbol = MutexKt.UNLOCKED;
                    if (!(obj2 != symbol)) {
                        throw new IllegalStateException("Mutex is not locked".toString());
                    }
                } else {
                    Object obj3 = ((Empty) value).locked;
                    if (!(obj3 == obj)) {
                        throw new IllegalStateException(("Mutex is locked by " + obj3 + " but expected " + obj).toString());
                    }
                }
                AtomicRef<Object> atomicRef2 = this._state;
                empty = MutexKt.EMPTY_UNLOCKED;
                if (atomicRef2.compareAndSet(value, empty)) {
                    return;
                }
            } else if (value instanceof OpDescriptor) {
                ((OpDescriptor) value).perform(this);
            } else if (value instanceof LockedQueue) {
                if (obj != null) {
                    LockedQueue lockedQueue = (LockedQueue) value;
                    if (!(lockedQueue.owner == obj)) {
                        Object obj4 = lockedQueue.owner;
                        throw new IllegalStateException(("Mutex is locked by " + obj4 + " but expected " + obj).toString());
                    }
                }
                LockedQueue lockedQueue2 = (LockedQueue) value;
                LockFreeLinkedListNode removeFirstOrNull = lockedQueue2.removeFirstOrNull();
                if (removeFirstOrNull == null) {
                    UnlockOp unlockOp = new UnlockOp(lockedQueue2);
                    if (this._state.compareAndSet(value, unlockOp) && unlockOp.perform(this) == null) {
                        return;
                    }
                } else {
                    LockWaiter lockWaiter = (LockWaiter) removeFirstOrNull;
                    if (lockWaiter.tryResumeLockWaiter()) {
                        Object obj5 = lockWaiter.owner;
                        if (obj5 == null) {
                            obj5 = MutexKt.LOCKED;
                        }
                        lockedQueue2.owner = obj5;
                        lockWaiter.completeResumeLockWaiter();
                        return;
                    }
                }
            } else {
                throw new IllegalStateException(("Illegal state " + value).toString());
            }
        }
    }

    private final Object lockSuspend(final Object obj, Continuation<? super Unit> continuation) {
        Continuation intercepted;
        Symbol symbol;
        Object coroutine_suspended;
        Object coroutine_suspended2;
        intercepted = IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation);
        CancellableContinuationImpl orCreateCancellableContinuation = CancellableContinuationKt.getOrCreateCancellableContinuation(intercepted);
        LockCont lockCont = new LockCont(this, obj, orCreateCancellableContinuation);
        AtomicRef atomicRef = this._state;
        while (true) {
            Object value = atomicRef.getValue();
            if (value instanceof Empty) {
                Empty empty = (Empty) value;
                Object obj2 = empty.locked;
                symbol = MutexKt.UNLOCKED;
                if (obj2 != symbol) {
                    this._state.compareAndSet(value, new LockedQueue(empty.locked));
                } else {
                    if (this._state.compareAndSet(value, obj == null ? MutexKt.EMPTY_LOCKED : new Empty(obj))) {
                        orCreateCancellableContinuation.resume(Unit.INSTANCE, new Function1<Throwable, Unit>() { // from class: kotlinx.coroutines.sync.MutexImpl$lockSuspend$2$1$1
                            /* JADX INFO: Access modifiers changed from: package-private */
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                                invoke2(th);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke  reason: avoid collision after fix types in other method */
                            public final void invoke2(Throwable it) {
                                Intrinsics.checkNotNullParameter(it, "it");
                                MutexImpl.this.unlock(obj);
                            }
                        });
                        break;
                    }
                }
            } else if (value instanceof LockedQueue) {
                LockedQueue lockedQueue = (LockedQueue) value;
                if (!(lockedQueue.owner != obj)) {
                    throw new IllegalStateException(("Already locked by " + obj).toString());
                }
                lockedQueue.addLast(lockCont);
                if (this._state.getValue() == value || !lockCont.take()) {
                    break;
                }
                lockCont = new LockCont(this, obj, orCreateCancellableContinuation);
            } else if (!(value instanceof OpDescriptor)) {
                throw new IllegalStateException(("Illegal state " + value).toString());
            } else {
                ((OpDescriptor) value).perform(this);
            }
        }
        CancellableContinuationKt.removeOnCancellation(orCreateCancellableContinuation, lockCont);
        Object result = orCreateCancellableContinuation.getResult();
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (result == coroutine_suspended) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        coroutine_suspended2 = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        return result == coroutine_suspended2 ? result : Unit.INSTANCE;
    }

    public String toString() {
        AtomicRef<Object> atomicRef = this._state;
        while (true) {
            Object value = atomicRef.getValue();
            if (value instanceof Empty) {
                Object obj = ((Empty) value).locked;
                return "Mutex[" + obj + "]";
            } else if (!(value instanceof OpDescriptor)) {
                if (!(value instanceof LockedQueue)) {
                    throw new IllegalStateException(("Illegal state " + value).toString());
                }
                Object obj2 = ((LockedQueue) value).owner;
                return "Mutex[" + obj2 + "]";
            } else {
                ((OpDescriptor) value).perform(this);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: Mutex.kt */
    /* loaded from: classes2.dex */
    public static final class LockedQueue extends LockFreeLinkedListHead {
        public volatile Object owner;

        public LockedQueue(Object owner) {
            Intrinsics.checkNotNullParameter(owner, "owner");
            this.owner = owner;
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
        public String toString() {
            Object obj = this.owner;
            return "LockedQueue[" + obj + "]";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: Mutex.kt */
    /* loaded from: classes2.dex */
    public abstract class LockWaiter extends LockFreeLinkedListNode implements DisposableHandle {
        private final AtomicBoolean isTaken = AtomicFU.atomic(false);
        public final Object owner;

        public abstract void completeResumeLockWaiter();

        public abstract boolean tryResumeLockWaiter();

        public LockWaiter(Object obj) {
            this.owner = obj;
        }

        public final boolean take() {
            return this.isTaken.compareAndSet(false, true);
        }

        @Override // kotlinx.coroutines.DisposableHandle
        public final void dispose() {
            mo2185remove();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: Mutex.kt */
    /* loaded from: classes2.dex */
    public final class LockCont extends LockWaiter {
        private final CancellableContinuation<Unit> cont;
        final /* synthetic */ MutexImpl this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public LockCont(MutexImpl mutexImpl, Object obj, CancellableContinuation<? super Unit> cont) {
            super(obj);
            Intrinsics.checkNotNullParameter(cont, "cont");
            this.this$0 = mutexImpl;
            this.cont = cont;
        }

        @Override // kotlinx.coroutines.sync.MutexImpl.LockWaiter
        public boolean tryResumeLockWaiter() {
            if (take()) {
                CancellableContinuation<Unit> cancellableContinuation = this.cont;
                Unit unit = Unit.INSTANCE;
                final MutexImpl mutexImpl = this.this$0;
                return cancellableContinuation.tryResume(unit, null, new Function1<Throwable, Unit>() { // from class: kotlinx.coroutines.sync.MutexImpl$LockCont$tryResumeLockWaiter$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                        invoke2(th);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke  reason: avoid collision after fix types in other method */
                    public final void invoke2(Throwable it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                        MutexImpl.this.unlock(this.owner);
                    }
                }) != null;
            }
            return false;
        }

        @Override // kotlinx.coroutines.sync.MutexImpl.LockWaiter
        public void completeResumeLockWaiter() {
            this.cont.completeResume(CancellableContinuationImplKt.RESUME_TOKEN);
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
        public String toString() {
            Object obj = this.owner;
            CancellableContinuation<Unit> cancellableContinuation = this.cont;
            MutexImpl mutexImpl = this.this$0;
            return "LockCont[" + obj + ", " + cancellableContinuation + "] for " + mutexImpl;
        }
    }

    /* compiled from: Mutex.kt */
    /* loaded from: classes2.dex */
    private static final class UnlockOp extends AtomicOp<MutexImpl> {
        public final LockedQueue queue;

        public UnlockOp(LockedQueue queue) {
            Intrinsics.checkNotNullParameter(queue, "queue");
            this.queue = queue;
        }

        @Override // kotlinx.coroutines.internal.AtomicOp
        public Object prepare(MutexImpl affected) {
            Symbol symbol;
            Intrinsics.checkNotNullParameter(affected, "affected");
            if (this.queue.isEmpty()) {
                return null;
            }
            symbol = MutexKt.UNLOCK_FAIL;
            return symbol;
        }

        @Override // kotlinx.coroutines.internal.AtomicOp
        public void complete(MutexImpl affected, Object obj) {
            Intrinsics.checkNotNullParameter(affected, "affected");
            affected._state.compareAndSet(this, obj == null ? MutexKt.EMPTY_UNLOCKED : this.queue);
        }
    }
}
