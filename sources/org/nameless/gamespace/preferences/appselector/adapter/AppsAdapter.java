package org.nameless.gamespace.preferences.appselector.adapter;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import java.util.ArrayList;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.RegexOption;
import org.nameless.gamespace.R;
/* compiled from: AppsAdapter.kt */
/* loaded from: classes.dex */
public final class AppsAdapter extends ListAdapter<ApplicationInfo, AppsItemViewHolder> {
    private final List<ApplicationInfo> apps;
    private Function1<? super ApplicationInfo, Unit> onClick;
    private final PackageManager pm;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public AppsAdapter(PackageManager pm, List<? extends ApplicationInfo> apps) {
        super(new DiffCallback(pm));
        Intrinsics.checkNotNullParameter(pm, "pm");
        Intrinsics.checkNotNullParameter(apps, "apps");
        this.pm = pm;
        this.apps = apps;
        submitList(apps);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return getCurrentList().size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public AppsItemViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.app_selector_item, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "from(parent.context)\n   …ctor_item, parent, false)");
        return new AppsItemViewHolder(inflate);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(AppsItemViewHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        ApplicationInfo applicationInfo = getCurrentList().get(i);
        Intrinsics.checkNotNullExpressionValue(applicationInfo, "currentList[position]");
        holder.bind(applicationInfo, new Function1<ApplicationInfo, Unit>() { // from class: org.nameless.gamespace.preferences.appselector.adapter.AppsAdapter$onBindViewHolder$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ApplicationInfo applicationInfo2) {
                invoke2(applicationInfo2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(ApplicationInfo it) {
                Function1 function1;
                Function1 function12;
                Intrinsics.checkNotNullParameter(it, "it");
                function1 = AppsAdapter.this.onClick;
                if (function1 != null) {
                    function12 = AppsAdapter.this.onClick;
                    if (function12 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("onClick");
                        function12 = null;
                    }
                    function12.invoke(it);
                }
            }
        });
    }

    public final void onItemClick(Function1<? super ApplicationInfo, Unit> action) {
        Intrinsics.checkNotNullParameter(action, "action");
        this.onClick = action;
    }

    public final void filterWith(String str) {
        Regex regex = new Regex(".*" + str + ".*", RegexOption.IGNORE_CASE);
        ArrayList arrayList = new ArrayList();
        for (Object obj : this.apps) {
            CharSequence loadLabel = ((ApplicationInfo) obj).loadLabel(this.pm);
            Intrinsics.checkNotNullExpressionValue(loadLabel, "it.loadLabel(pm)");
            if (regex.containsMatchIn(loadLabel)) {
                arrayList.add(obj);
            }
        }
        Unit unit = null;
        if (!(!arrayList.isEmpty())) {
            arrayList = null;
        }
        if (arrayList != null) {
            submitList(arrayList);
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            submitList(this.apps);
        }
    }

    /* compiled from: AppsAdapter.kt */
    /* loaded from: classes.dex */
    private static final class DiffCallback extends DiffUtil.ItemCallback<ApplicationInfo> {
        private final PackageManager pm;

        public DiffCallback(PackageManager pm) {
            Intrinsics.checkNotNullParameter(pm, "pm");
            this.pm = pm;
        }

        @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
        public boolean areItemsTheSame(ApplicationInfo oldItem, ApplicationInfo newItem) {
            Intrinsics.checkNotNullParameter(oldItem, "oldItem");
            Intrinsics.checkNotNullParameter(newItem, "newItem");
            return Intrinsics.areEqual(oldItem.loadLabel(this.pm), newItem.loadLabel(this.pm));
        }

        @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
        public boolean areContentsTheSame(ApplicationInfo oldItem, ApplicationInfo newItem) {
            Intrinsics.checkNotNullParameter(oldItem, "oldItem");
            Intrinsics.checkNotNullParameter(newItem, "newItem");
            return Intrinsics.areEqual(oldItem.packageName, newItem.packageName);
        }
    }
}
