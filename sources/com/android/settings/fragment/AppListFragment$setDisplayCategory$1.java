package com.android.settings.fragment;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.sync.Mutex;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AppListFragment.kt */
@DebugMetadata(c = "com.android.settings.fragment.AppListFragment$setDisplayCategory$1", f = "AppListFragment.kt", l = {353}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class AppListFragment$setDisplayCategory$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ int $category;
    int I$0;
    Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ AppListFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AppListFragment$setDisplayCategory$1(AppListFragment appListFragment, int i, Continuation<? super AppListFragment$setDisplayCategory$1> continuation) {
        super(2, continuation);
        this.this$0 = appListFragment;
        this.$category = i;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AppListFragment$setDisplayCategory$1(this.this$0, this.$category, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AppListFragment$setDisplayCategory$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended;
        Mutex mutex;
        AppListFragment appListFragment;
        Mutex mutex2;
        int i;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            mutex = this.this$0.mutex;
            appListFragment = this.this$0;
            int i3 = this.$category;
            this.L$0 = mutex;
            this.L$1 = appListFragment;
            this.I$0 = i3;
            this.label = 1;
            if (mutex.lock(null, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            mutex2 = mutex;
            i = i3;
        } else if (i2 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            i = this.I$0;
            appListFragment = (AppListFragment) this.L$1;
            mutex2 = (Mutex) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        try {
            appListFragment.displayCategory = i;
            return Unit.INSTANCE;
        } finally {
            mutex2.unlock(null);
        }
    }
}
