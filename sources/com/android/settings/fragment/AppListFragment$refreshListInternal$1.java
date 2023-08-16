package com.android.settings.fragment;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AppListFragment.kt */
@DebugMetadata(c = "com.android.settings.fragment.AppListFragment", f = "AppListFragment.kt", l = {225}, m = "refreshListInternal")
/* loaded from: classes.dex */
public final class AppListFragment$refreshListInternal$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ AppListFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AppListFragment$refreshListInternal$1(AppListFragment appListFragment, Continuation<? super AppListFragment$refreshListInternal$1> continuation) {
        super(continuation);
        this.this$0 = appListFragment;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object refreshListInternal;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        refreshListInternal = this.this$0.refreshListInternal(this);
        return refreshListInternal;
    }
}
