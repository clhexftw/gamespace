package com.android.settings.fragment;

import android.content.pm.PackageInfo;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.sync.Mutex;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AppListFragment.kt */
@DebugMetadata(c = "com.android.settings.fragment.AppListFragment$setCustomFilter$1", f = "AppListFragment.kt", l = {353}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class AppListFragment$setCustomFilter$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1<PackageInfo, Boolean> $customFilter;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ AppListFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public AppListFragment$setCustomFilter$1(AppListFragment appListFragment, Function1<? super PackageInfo, Boolean> function1, Continuation<? super AppListFragment$setCustomFilter$1> continuation) {
        super(2, continuation);
        this.this$0 = appListFragment;
        this.$customFilter = function1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AppListFragment$setCustomFilter$1(this.this$0, this.$customFilter, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AppListFragment$setCustomFilter$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended;
        Mutex mutex;
        AppListFragment appListFragment;
        Mutex mutex2;
        Function1<PackageInfo, Boolean> function1;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            mutex = this.this$0.mutex;
            appListFragment = this.this$0;
            Function1<PackageInfo, Boolean> function12 = this.$customFilter;
            this.L$0 = mutex;
            this.L$1 = appListFragment;
            this.L$2 = function12;
            this.label = 1;
            if (mutex.lock(null, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            mutex2 = mutex;
            function1 = function12;
        } else if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            function1 = (Function1) this.L$2;
            appListFragment = (AppListFragment) this.L$1;
            mutex2 = (Mutex) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        try {
            appListFragment.packageFilter = function1;
            return Unit.INSTANCE;
        } finally {
            mutex2.unlock(null);
        }
    }
}
