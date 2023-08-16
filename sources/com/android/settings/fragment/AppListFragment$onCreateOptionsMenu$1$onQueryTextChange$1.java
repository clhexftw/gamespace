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
/* compiled from: AppListFragment.kt */
@DebugMetadata(c = "com.android.settings.fragment.AppListFragment$onCreateOptionsMenu$1$onQueryTextChange$1", f = "AppListFragment.kt", l = {353, 131}, m = "invokeSuspend")
/* loaded from: classes.dex */
final class AppListFragment$onCreateOptionsMenu$1$onQueryTextChange$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $newText;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ AppListFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AppListFragment$onCreateOptionsMenu$1$onQueryTextChange$1(AppListFragment appListFragment, String str, Continuation<? super AppListFragment$onCreateOptionsMenu$1$onQueryTextChange$1> continuation) {
        super(2, continuation);
        this.this$0 = appListFragment;
        this.$newText = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AppListFragment$onCreateOptionsMenu$1$onQueryTextChange$1(this.this$0, this.$newText, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AppListFragment$onCreateOptionsMenu$1$onQueryTextChange$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended;
        Mutex mutex;
        String str;
        AppListFragment appListFragment;
        Object refreshListInternal;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                mutex = this.this$0.mutex;
                AppListFragment appListFragment2 = this.this$0;
                str = this.$newText;
                this.L$0 = mutex;
                this.L$1 = appListFragment2;
                this.L$2 = str;
                this.label = 1;
                if (mutex.lock(null, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                appListFragment = appListFragment2;
            } else if (i != 1) {
                if (i == 2) {
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                str = (String) this.L$2;
                appListFragment = (AppListFragment) this.L$1;
                mutex = (Mutex) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            appListFragment.searchText = str;
            Unit unit = Unit.INSTANCE;
            mutex.unlock(null);
            AppListFragment appListFragment3 = this.this$0;
            this.L$0 = null;
            this.L$1 = null;
            this.L$2 = null;
            this.label = 2;
            refreshListInternal = appListFragment3.refreshListInternal(this);
            if (refreshListInternal == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        } catch (Throwable th) {
            mutex.unlock(null);
            throw th;
        }
    }
}
