package com.android.settings.fragment;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import com.android.settings.fragment.AppListFragment;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.sync.Mutex;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AppListFragment.kt */
@DebugMetadata(c = "com.android.settings.fragment.AppListFragment$refreshListInternal$list$1", f = "AppListFragment.kt", l = {353}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class AppListFragment$refreshListInternal$list$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends AppListFragment.AppInfo>>, Object> {
    Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ AppListFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AppListFragment$refreshListInternal$list$1(AppListFragment appListFragment, Continuation<? super AppListFragment$refreshListInternal$list$1> continuation) {
        super(2, continuation);
        this.this$0 = appListFragment;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AppListFragment$refreshListInternal$list$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends AppListFragment.AppInfo>> continuation) {
        return invoke2(coroutineScope, (Continuation<? super List<AppListFragment.AppInfo>>) continuation);
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super List<AppListFragment.AppInfo>> continuation) {
        return ((AppListFragment$refreshListInternal$list$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended;
        Mutex mutex;
        AppListFragment appListFragment;
        PackageManager packageManager;
        final Function2 function2;
        List sortedWith;
        int collectionSizeOrDefault;
        String label;
        PackageManager packageManager2;
        int i;
        boolean isSystemApp;
        Function1 function1;
        String label2;
        String str;
        boolean contains;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            mutex = this.this$0.mutex;
            AppListFragment appListFragment2 = this.this$0;
            this.L$0 = mutex;
            this.L$1 = appListFragment2;
            this.label = 1;
            if (mutex.lock(null, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            appListFragment = appListFragment2;
        } else if (i2 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            appListFragment = (AppListFragment) this.L$1;
            mutex = (Mutex) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        try {
            packageManager = appListFragment.pm;
            if (packageManager == null) {
                Intrinsics.throwUninitializedPropertyAccessException("pm");
                packageManager = null;
            }
            List installedPackages = packageManager.getInstalledPackages(PackageManager.PackageInfoFlags.of(131072L));
            Intrinsics.checkNotNullExpressionValue(installedPackages, "pm.getInstalledPackages(…Long())\n                )");
            ArrayList arrayList = new ArrayList();
            for (Object obj2 : installedPackages) {
                PackageInfo it = (PackageInfo) obj2;
                i = appListFragment.displayCategory;
                boolean z = false;
                if (i == 0) {
                    isSystemApp = it.applicationInfo.isSystemApp();
                } else {
                    if (i == 1 && it.applicationInfo.isSystemApp()) {
                        isSystemApp = false;
                    }
                    isSystemApp = true;
                }
                if (isSystemApp) {
                    function1 = appListFragment.packageFilter;
                    Intrinsics.checkNotNullExpressionValue(it, "it");
                    if (((Boolean) function1.invoke(it)).booleanValue()) {
                        label2 = appListFragment.getLabel(it);
                        str = appListFragment.searchText;
                        contains = StringsKt__StringsKt.contains(label2, str, true);
                        if (contains) {
                            z = true;
                        }
                    }
                }
                if (z) {
                    arrayList.add(obj2);
                }
            }
            function2 = appListFragment.packageComparator;
            sortedWith = CollectionsKt___CollectionsKt.sortedWith(arrayList, new Comparator(function2) { // from class: com.android.settings.fragment.AppListFragment$sam$java_util_Comparator$0
                private final /* synthetic */ Function2 function;

                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    Intrinsics.checkNotNullParameter(function2, "function");
                    this.function = function2;
                }

                @Override // java.util.Comparator
                public final /* synthetic */ int compare(Object obj3, Object obj4) {
                    return ((Number) this.function.invoke(obj3, obj4)).intValue();
                }
            });
            mutex.unlock(null);
            List<PackageInfo> list = sortedWith;
            AppListFragment appListFragment3 = this.this$0;
            collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10);
            ArrayList arrayList2 = new ArrayList(collectionSizeOrDefault);
            for (PackageInfo it2 : list) {
                String str2 = it2.packageName;
                Intrinsics.checkNotNullExpressionValue(str2, "it.packageName");
                Intrinsics.checkNotNullExpressionValue(it2, "it");
                label = appListFragment3.getLabel(it2);
                ApplicationInfo applicationInfo = it2.applicationInfo;
                packageManager2 = appListFragment3.pm;
                if (packageManager2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("pm");
                    packageManager2 = null;
                }
                Drawable loadIcon = applicationInfo.loadIcon(packageManager2);
                Intrinsics.checkNotNullExpressionValue(loadIcon, "it.applicationInfo.loadIcon(pm)");
                arrayList2.add(new AppListFragment.AppInfo(str2, label, loadIcon));
            }
            return arrayList2;
        } catch (Throwable th) {
            mutex.unlock(null);
            throw th;
        }
    }
}
