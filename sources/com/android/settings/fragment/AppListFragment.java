package com.android.settings.fragment;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.android.settings.R;
import com.android.settings.fragment.AppListFragment;
import com.google.android.material.appbar.AppBarLayout;
import java.util.Collection;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;
/* compiled from: AppListFragment.kt */
/* loaded from: classes.dex */
public abstract class AppListFragment extends Fragment implements MenuItem.OnActionExpandListener {
    public static final Companion Companion = new Companion(null);
    private AppListAdapter adapter;
    private AppBarLayout appBarLayout;
    private int displayCategory;
    private final Mutex mutex;
    private Function2<? super PackageInfo, ? super PackageInfo, Integer> packageComparator;
    private Function1<? super PackageInfo, Boolean> packageFilter;
    private PackageManager pm;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private String searchText;

    protected abstract List<String> getInitialCheckedList();

    protected abstract int getTitle();

    /* JADX INFO: Access modifiers changed from: protected */
    public void onAppDeselected(String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onAppSelected(String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onListUpdate(List<String> list) {
        Intrinsics.checkNotNullParameter(list, "list");
    }

    public AppListFragment() {
        super(R.layout.app_list_layout);
        this.mutex = MutexKt.Mutex$default(false, 1, null);
        this.searchText = "";
        this.displayCategory = 1;
        this.packageFilter = new Function1<PackageInfo, Boolean>() { // from class: com.android.settings.fragment.AppListFragment$packageFilter$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(PackageInfo it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return Boolean.TRUE;
            }
        };
        this.packageComparator = new Function2<PackageInfo, PackageInfo, Integer>() { // from class: com.android.settings.fragment.AppListFragment$packageComparator$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Integer invoke(PackageInfo first, PackageInfo second) {
                String label;
                String label2;
                Intrinsics.checkNotNullParameter(first, "first");
                Intrinsics.checkNotNullParameter(second, "second");
                label = AppListFragment.this.getLabel(first);
                label2 = AppListFragment.this.getLabel(second);
                return Integer.valueOf(label.compareTo(label2));
            }
        };
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setHasOptionsMenu(true);
        PackageManager packageManager = requireContext().getPackageManager();
        Intrinsics.checkNotNullExpressionValue(packageManager, "requireContext().packageManager");
        this.pm = packageManager;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        requireActivity.setTitle(getTitle());
        this.appBarLayout = (AppBarLayout) requireActivity.findViewById(R.id.app_bar);
        this.progressBar = (ProgressBar) view.findViewById(R.id.loading_progress);
        List<String> initialCheckedList = getInitialCheckedList();
        LayoutInflater layoutInflater = getLayoutInflater();
        Intrinsics.checkNotNullExpressionValue(layoutInflater, "layoutInflater");
        AppListAdapter appListAdapter = new AppListAdapter(initialCheckedList, layoutInflater);
        appListAdapter.setOnAppSelectListener(new Function1<String, Unit>() { // from class: com.android.settings.fragment.AppListFragment$onViewCreated$1$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(String it) {
                Intrinsics.checkNotNullParameter(it, "it");
                AppListFragment.this.onAppSelected(it);
            }
        });
        appListAdapter.setOnAppDeselectListener(new Function1<String, Unit>() { // from class: com.android.settings.fragment.AppListFragment$onViewCreated$1$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(String it) {
                Intrinsics.checkNotNullParameter(it, "it");
                AppListFragment.this.onAppDeselected(it);
            }
        });
        appListAdapter.setOnListUpdateListener(new Function1<List<? extends String>, Unit>() { // from class: com.android.settings.fragment.AppListFragment$onViewCreated$1$3
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(List<? extends String> list) {
                invoke2((List<String>) list);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(List<String> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                AppListFragment.this.onListUpdate(it);
            }
        });
        this.adapter = appListAdapter;
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.apps_list);
        AppListAdapter appListAdapter2 = null;
        if (recyclerView != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            AppListAdapter appListAdapter3 = this.adapter;
            if (appListAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
            } else {
                appListAdapter2 = appListAdapter3;
            }
            recyclerView.setAdapter(appListAdapter2);
        } else {
            recyclerView = null;
        }
        this.recyclerView = recyclerView;
        refreshList();
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        inflater.inflate(R.menu.app_list_menu, menu);
        MenuItem findItem = menu.findItem(R.id.search);
        if (this.appBarLayout != null) {
            findItem.setOnActionExpandListener(this);
        }
        View actionView = findItem.getActionView();
        Intrinsics.checkNotNull(actionView, "null cannot be cast to non-null type android.widget.SearchView");
        SearchView searchView = (SearchView) actionView;
        searchView.setQueryHint(getString(R.string.search_apps));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() { // from class: com.android.settings.fragment.AppListFragment$onCreateOptionsMenu$1
            @Override // android.widget.SearchView.OnQueryTextListener
            public boolean onQueryTextSubmit(String query) {
                Intrinsics.checkNotNullParameter(query, "query");
                return false;
            }

            @Override // android.widget.SearchView.OnQueryTextListener
            public boolean onQueryTextChange(String newText) {
                Intrinsics.checkNotNullParameter(newText, "newText");
                BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(AppListFragment.this), null, null, new AppListFragment$onCreateOptionsMenu$1$onQueryTextChange$1(AppListFragment.this, newText, null), 3, null);
                return true;
            }
        });
    }

    @Override // android.view.MenuItem.OnActionExpandListener
    public boolean onMenuItemActionExpand(MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        AppBarLayout appBarLayout = this.appBarLayout;
        if (appBarLayout != null) {
            appBarLayout.setExpanded(false, false);
        }
        RecyclerView recyclerView = this.recyclerView;
        if (recyclerView != null) {
            ViewCompat.setNestedScrollingEnabled(recyclerView, false);
            return true;
        }
        return true;
    }

    @Override // android.view.MenuItem.OnActionExpandListener
    public boolean onMenuItemActionCollapse(MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        AppBarLayout appBarLayout = this.appBarLayout;
        if (appBarLayout != null) {
            appBarLayout.setExpanded(false, false);
        }
        RecyclerView recyclerView = this.recyclerView;
        if (recyclerView != null) {
            ViewCompat.setNestedScrollingEnabled(recyclerView, true);
        }
        return true;
    }

    public final void setDisplayCategory(int i) {
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new AppListFragment$setDisplayCategory$1(this, i, null), 3, null);
    }

    public final void setCustomFilter(Function1<? super PackageInfo, Boolean> customFilter) {
        Intrinsics.checkNotNullParameter(customFilter, "customFilter");
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new AppListFragment$setCustomFilter$1(this, customFilter, null), 3, null);
    }

    public final void refreshList() {
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new AppListFragment$refreshList$1(this, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0062  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object refreshListInternal(kotlin.coroutines.Continuation<? super kotlin.Unit> r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof com.android.settings.fragment.AppListFragment$refreshListInternal$1
            if (r0 == 0) goto L13
            r0 = r6
            com.android.settings.fragment.AppListFragment$refreshListInternal$1 r0 = (com.android.settings.fragment.AppListFragment$refreshListInternal$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.settings.fragment.AppListFragment$refreshListInternal$1 r0 = new com.android.settings.fragment.AppListFragment$refreshListInternal$1
            r0.<init>(r5, r6)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L36
            if (r2 != r4) goto L2e
            java.lang.Object r5 = r0.L$0
            com.android.settings.fragment.AppListFragment r5 = (com.android.settings.fragment.AppListFragment) r5
            kotlin.ResultKt.throwOnFailure(r6)
            goto L4d
        L2e:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L36:
            kotlin.ResultKt.throwOnFailure(r6)
            kotlinx.coroutines.CoroutineDispatcher r6 = kotlinx.coroutines.Dispatchers.getDefault()
            com.android.settings.fragment.AppListFragment$refreshListInternal$list$1 r2 = new com.android.settings.fragment.AppListFragment$refreshListInternal$list$1
            r2.<init>(r5, r3)
            r0.L$0 = r5
            r0.label = r4
            java.lang.Object r6 = kotlinx.coroutines.BuildersKt.withContext(r6, r2, r0)
            if (r6 != r1) goto L4d
            return r1
        L4d:
            java.util.List r6 = (java.util.List) r6
            com.android.settings.fragment.AppListFragment$AppListAdapter r0 = r5.adapter
            if (r0 != 0) goto L59
            java.lang.String r0 = "adapter"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
            goto L5a
        L59:
            r3 = r0
        L5a:
            r3.submitList(r6)
            android.widget.ProgressBar r5 = r5.progressBar
            if (r5 != 0) goto L62
            goto L67
        L62:
            r6 = 8
            r5.setVisibility(r6)
        L67:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settings.fragment.AppListFragment.refreshListInternal(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getLabel(PackageInfo packageInfo) {
        ApplicationInfo applicationInfo = packageInfo.applicationInfo;
        PackageManager packageManager = this.pm;
        if (packageManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("pm");
            packageManager = null;
        }
        return applicationInfo.loadLabel(packageManager).toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: AppListFragment.kt */
    /* loaded from: classes.dex */
    public static final class AppListAdapter extends ListAdapter<AppInfo, AppListViewHolder> {
        public static final Companion Companion = new Companion(null);
        private static final AppListFragment$AppListAdapter$Companion$itemCallback$1 itemCallback = new DiffUtil.ItemCallback<AppInfo>() { // from class: com.android.settings.fragment.AppListFragment$AppListAdapter$Companion$itemCallback$1
            @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
            public boolean areItemsTheSame(AppListFragment.AppInfo oldInfo, AppListFragment.AppInfo newInfo) {
                Intrinsics.checkNotNullParameter(oldInfo, "oldInfo");
                Intrinsics.checkNotNullParameter(newInfo, "newInfo");
                return Intrinsics.areEqual(oldInfo.getPackageName(), newInfo.getPackageName());
            }

            @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
            public boolean areContentsTheSame(AppListFragment.AppInfo oldInfo, AppListFragment.AppInfo newInfo) {
                Intrinsics.checkNotNullParameter(oldInfo, "oldInfo");
                Intrinsics.checkNotNullParameter(newInfo, "newInfo");
                return Intrinsics.areEqual(oldInfo, newInfo);
            }
        };
        private Function1<? super String, Unit> appDeselectListener;
        private Function1<? super String, Unit> appSelectListener;
        private final List<String> checkedList;
        private final LayoutInflater layoutInflater;
        private Function1<? super List<String>, Unit> listUpdateListener;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AppListAdapter(List<String> initialCheckedList, LayoutInflater layoutInflater) {
            super(itemCallback);
            List<String> mutableList;
            Intrinsics.checkNotNullParameter(initialCheckedList, "initialCheckedList");
            Intrinsics.checkNotNullParameter(layoutInflater, "layoutInflater");
            this.layoutInflater = layoutInflater;
            mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) initialCheckedList);
            this.checkedList = mutableList;
            this.appSelectListener = new Function1<String, Unit>() { // from class: com.android.settings.fragment.AppListFragment$AppListAdapter$appSelectListener$1
                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2(String it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(String str) {
                    invoke2(str);
                    return Unit.INSTANCE;
                }
            };
            this.appDeselectListener = new Function1<String, Unit>() { // from class: com.android.settings.fragment.AppListFragment$AppListAdapter$appDeselectListener$1
                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2(String it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(String str) {
                    invoke2(str);
                    return Unit.INSTANCE;
                }
            };
            this.listUpdateListener = new Function1<List<? extends String>, Unit>() { // from class: com.android.settings.fragment.AppListFragment$AppListAdapter$listUpdateListener$1
                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2(List<String> it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(List<? extends String> list) {
                    invoke2((List<String>) list);
                    return Unit.INSTANCE;
                }
            };
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public AppListViewHolder onCreateViewHolder(ViewGroup parent, int i) {
            Intrinsics.checkNotNullParameter(parent, "parent");
            View inflate = this.layoutInflater.inflate(R.layout.app_list_item, parent, false);
            Intrinsics.checkNotNullExpressionValue(inflate, "layoutInflater.inflate(\n…rent */\n                )");
            return new AppListViewHolder(inflate);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(AppListViewHolder holder, final int i) {
            Intrinsics.checkNotNullParameter(holder, "holder");
            final AppInfo item = getItem(i);
            holder.getLabel().setText(item.getLabel());
            holder.getPackageName().setText(item.getPackageName());
            holder.getIcon().setImageDrawable(item.getIcon());
            holder.getCheckBox().setChecked(this.checkedList.contains(item.getPackageName()));
            holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.android.settings.fragment.AppListFragment$AppListAdapter$onBindViewHolder$1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    List list;
                    List list2;
                    Function1 function1;
                    Function1 function12;
                    List list3;
                    List list4;
                    List list5;
                    Function1 function13;
                    list = AppListFragment.AppListAdapter.this.checkedList;
                    if (list.contains(item.getPackageName())) {
                        list5 = AppListFragment.AppListAdapter.this.checkedList;
                        list5.remove(item.getPackageName());
                        function13 = AppListFragment.AppListAdapter.this.appDeselectListener;
                        function13.invoke(item.getPackageName());
                    } else {
                        list2 = AppListFragment.AppListAdapter.this.checkedList;
                        list2.add(item.getPackageName());
                        function1 = AppListFragment.AppListAdapter.this.appSelectListener;
                        function1.invoke(item.getPackageName());
                    }
                    AppListFragment.AppListAdapter.this.notifyItemChanged(i);
                    function12 = AppListFragment.AppListAdapter.this.listUpdateListener;
                    list3 = AppListFragment.AppListAdapter.this.checkedList;
                    list4 = CollectionsKt___CollectionsKt.toList(list3);
                    function12.invoke(list4);
                }
            });
        }

        public final void setOnAppSelectListener(Function1<? super String, Unit> listener) {
            Intrinsics.checkNotNullParameter(listener, "listener");
            this.appSelectListener = listener;
        }

        public final void setOnAppDeselectListener(Function1<? super String, Unit> listener) {
            Intrinsics.checkNotNullParameter(listener, "listener");
            this.appDeselectListener = listener;
        }

        public final void setOnListUpdateListener(Function1<? super List<String>, Unit> listener) {
            Intrinsics.checkNotNullParameter(listener, "listener");
            this.listUpdateListener = listener;
        }

        /* compiled from: AppListFragment.kt */
        /* loaded from: classes.dex */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: AppListFragment.kt */
    /* loaded from: classes.dex */
    public static final class AppListViewHolder extends RecyclerView.ViewHolder {
        private final CheckBox checkBox;
        private final ImageView icon;
        private final TextView label;
        private final TextView packageName;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AppListViewHolder(View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            View findViewById = itemView.findViewById(R.id.icon);
            Intrinsics.checkNotNullExpressionValue(findViewById, "itemView.findViewById(R.id.icon)");
            this.icon = (ImageView) findViewById;
            View findViewById2 = itemView.findViewById(R.id.label);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "itemView.findViewById(R.id.label)");
            this.label = (TextView) findViewById2;
            View findViewById3 = itemView.findViewById(R.id.package_name);
            Intrinsics.checkNotNullExpressionValue(findViewById3, "itemView.findViewById(R.id.package_name)");
            this.packageName = (TextView) findViewById3;
            View findViewById4 = itemView.findViewById(R.id.check_box);
            Intrinsics.checkNotNullExpressionValue(findViewById4, "itemView.findViewById(R.id.check_box)");
            this.checkBox = (CheckBox) findViewById4;
        }

        public final ImageView getIcon() {
            return this.icon;
        }

        public final TextView getLabel() {
            return this.label;
        }

        public final TextView getPackageName() {
            return this.packageName;
        }

        public final CheckBox getCheckBox() {
            return this.checkBox;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: AppListFragment.kt */
    /* loaded from: classes.dex */
    public static final class AppInfo {
        private final Drawable icon;
        private final String label;
        private final String packageName;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof AppInfo) {
                AppInfo appInfo = (AppInfo) obj;
                return Intrinsics.areEqual(this.packageName, appInfo.packageName) && Intrinsics.areEqual(this.label, appInfo.label) && Intrinsics.areEqual(this.icon, appInfo.icon);
            }
            return false;
        }

        public int hashCode() {
            return (((this.packageName.hashCode() * 31) + this.label.hashCode()) * 31) + this.icon.hashCode();
        }

        public String toString() {
            String str = this.packageName;
            String str2 = this.label;
            Drawable drawable = this.icon;
            return "AppInfo(packageName=" + str + ", label=" + str2 + ", icon=" + drawable + ")";
        }

        public AppInfo(String packageName, String label, Drawable icon) {
            Intrinsics.checkNotNullParameter(packageName, "packageName");
            Intrinsics.checkNotNullParameter(label, "label");
            Intrinsics.checkNotNullParameter(icon, "icon");
            this.packageName = packageName;
            this.label = label;
            this.icon = icon;
        }

        public final String getPackageName() {
            return this.packageName;
        }

        public final String getLabel() {
            return this.label;
        }

        public final Drawable getIcon() {
            return this.icon;
        }
    }

    /* compiled from: AppListFragment.kt */
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
