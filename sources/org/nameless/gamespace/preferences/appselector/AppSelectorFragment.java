package org.nameless.gamespace.preferences.appselector;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import androidx.core.view.MenuProvider;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.appbar.AppBarLayout;
import kotlin.jvm.internal.Intrinsics;
import org.nameless.gamespace.R;
import org.nameless.gamespace.data.SystemSettings;
import org.nameless.gamespace.preferences.appselector.adapter.AppsAdapter;
/* compiled from: AppSelectorFragment.kt */
/* loaded from: classes.dex */
public final class AppSelectorFragment extends Hilt_AppSelectorFragment implements SearchView.OnQueryTextListener, MenuItem.OnActionExpandListener {
    private AppBarLayout appBarLayout;
    private RecyclerView appListView;
    private AppsAdapter appsAdapter;
    private final AppSelectorFragment$menuProvider$1 menuProvider = new MenuProvider() { // from class: org.nameless.gamespace.preferences.appselector.AppSelectorFragment$menuProvider$1
        @Override // androidx.core.view.MenuProvider
        public boolean onMenuItemSelected(MenuItem menuItem) {
            Intrinsics.checkNotNullParameter(menuItem, "menuItem");
            return false;
        }

        @Override // androidx.core.view.MenuProvider
        public void onCreateMenu(Menu menu, MenuInflater menuInflater) {
            Intrinsics.checkNotNullParameter(menu, "menu");
            Intrinsics.checkNotNullParameter(menuInflater, "menuInflater");
            menuInflater.inflate(R.menu.app_selector_menu, menu);
            MenuItem findItem = menu.findItem(R.id.app_search_menu);
            View actionView = findItem.getActionView();
            SearchView searchView = actionView instanceof SearchView ? (SearchView) actionView : null;
            if (searchView != null) {
                searchView.setOnQueryTextListener(AppSelectorFragment.this);
            }
            if (searchView != null) {
                searchView.setQueryHint(AppSelectorFragment.this.getString(R.string.app_search_title));
            }
            findItem.setOnActionExpandListener(AppSelectorFragment.this);
        }
    };
    public SystemSettings settings;

    @Override // android.widget.SearchView.OnQueryTextListener
    public boolean onQueryTextSubmit(String str) {
        return false;
    }

    public final SystemSettings getSettings() {
        SystemSettings systemSettings = this.settings;
        if (systemSettings != null) {
            return systemSettings;
        }
        Intrinsics.throwUninitializedPropertyAccessException("settings");
        return null;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        super.onCreateView(inflater, viewGroup, bundle);
        FragmentActivity activity = getActivity();
        this.appBarLayout = activity != null ? (AppBarLayout) activity.findViewById(R.id.app_bar) : null;
        return inflater.inflate(R.layout.app_selector, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.addMenuProvider(this.menuProvider, getViewLifecycleOwner(), Lifecycle.State.RESUMED);
        }
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.app_list);
        if (recyclerView != null) {
            setupAppListView(recyclerView);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0082, code lost:
        if (r3 == false) goto L17;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void setupAppListView(final androidx.recyclerview.widget.RecyclerView r10) {
        /*
            r9 = this;
            r9.appListView = r10
            r0 = 128(0x80, double:6.32E-322)
            android.content.pm.PackageManager$ApplicationInfoFlags r0 = android.content.pm.PackageManager.ApplicationInfoFlags.of(r0)
            android.content.Context r1 = r10.getContext()
            android.content.pm.PackageManager r1 = r1.getPackageManager()
            java.util.List r0 = r1.getInstalledApplications(r0)
            java.lang.String r1 = "view.context.packageMana…talledApplications(flags)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.util.Iterator r0 = r0.iterator()
        L24:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L8c
            java.lang.Object r2 = r0.next()
            r3 = r2
            android.content.pm.ApplicationInfo r3 = (android.content.pm.ApplicationInfo) r3
            java.lang.String r4 = r3.packageName
            android.content.Context r5 = r9.getContext()
            if (r5 == 0) goto L3e
            java.lang.String r5 = r5.getPackageName()
            goto L3f
        L3e:
            r5 = 0
        L3f:
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual(r4, r5)
            r5 = 1
            r6 = 0
            if (r4 != 0) goto L85
            int r4 = r3.flags
            r4 = r4 & r5
            if (r4 != 0) goto L85
            org.nameless.gamespace.data.SystemSettings r4 = r9.getSettings()
            java.util.List r4 = r4.getUserGames()
            java.lang.Iterable r4 = (java.lang.Iterable) r4
            boolean r7 = r4 instanceof java.util.Collection
            if (r7 == 0) goto L65
            r7 = r4
            java.util.Collection r7 = (java.util.Collection) r7
            boolean r7 = r7.isEmpty()
            if (r7 == 0) goto L65
        L63:
            r3 = r6
            goto L82
        L65:
            java.util.Iterator r4 = r4.iterator()
        L69:
            boolean r7 = r4.hasNext()
            if (r7 == 0) goto L63
            java.lang.Object r7 = r4.next()
            org.nameless.gamespace.data.UserGame r7 = (org.nameless.gamespace.data.UserGame) r7
            java.lang.String r7 = r7.getPackageName()
            java.lang.String r8 = r3.packageName
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual(r7, r8)
            if (r7 == 0) goto L69
            r3 = r5
        L82:
            if (r3 != 0) goto L85
            goto L86
        L85:
            r5 = r6
        L86:
            if (r5 == 0) goto L24
            r1.add(r2)
            goto L24
        L8c:
            org.nameless.gamespace.preferences.appselector.AppSelectorFragment$setupAppListView$$inlined$sortedBy$1 r0 = new org.nameless.gamespace.preferences.appselector.AppSelectorFragment$setupAppListView$$inlined$sortedBy$1
            r0.<init>()
            java.util.List r0 = kotlin.collections.CollectionsKt.sortedWith(r1, r0)
            org.nameless.gamespace.preferences.appselector.adapter.AppsAdapter r1 = new org.nameless.gamespace.preferences.appselector.adapter.AppsAdapter
            android.content.Context r2 = r10.getContext()
            android.content.pm.PackageManager r2 = r2.getPackageManager()
            java.lang.String r3 = "view.context.packageManager"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
            r1.<init>(r2, r0)
            r9.appsAdapter = r1
            r10.setAdapter(r1)
            androidx.recyclerview.widget.LinearLayoutManager r0 = new androidx.recyclerview.widget.LinearLayoutManager
            android.content.Context r1 = r10.getContext()
            r0.<init>(r1)
            r10.setLayoutManager(r0)
            org.nameless.gamespace.preferences.appselector.adapter.AppsAdapter r10 = r9.appsAdapter
            if (r10 == 0) goto Lc4
            org.nameless.gamespace.preferences.appselector.AppSelectorFragment$setupAppListView$1 r0 = new org.nameless.gamespace.preferences.appselector.AppSelectorFragment$setupAppListView$1
            r0.<init>()
            r10.onItemClick(r0)
        Lc4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.nameless.gamespace.preferences.appselector.AppSelectorFragment.setupAppListView(androidx.recyclerview.widget.RecyclerView):void");
    }

    @Override // android.widget.SearchView.OnQueryTextListener
    public boolean onQueryTextChange(String str) {
        AppsAdapter appsAdapter = this.appsAdapter;
        if (appsAdapter != null) {
            appsAdapter.filterWith(str);
            return false;
        }
        return false;
    }

    @Override // android.view.MenuItem.OnActionExpandListener
    public boolean onMenuItemActionExpand(MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        AppBarLayout appBarLayout = this.appBarLayout;
        if (appBarLayout != null) {
            appBarLayout.setExpanded(false, false);
        }
        RecyclerView recyclerView = this.appListView;
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
        RecyclerView recyclerView = this.appListView;
        if (recyclerView != null) {
            ViewCompat.setNestedScrollingEnabled(recyclerView, true);
        }
        return true;
    }
}
