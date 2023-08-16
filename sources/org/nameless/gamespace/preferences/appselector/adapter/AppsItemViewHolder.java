package org.nameless.gamespace.preferences.appselector.adapter;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.nameless.gamespace.R;
/* compiled from: AppsItemViewHolder.kt */
/* loaded from: classes.dex */
public final class AppsItemViewHolder extends RecyclerView.ViewHolder {
    private final Lazy pm$delegate;
    private final View v;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AppsItemViewHolder(View v) {
        super(v);
        Lazy lazy;
        Intrinsics.checkNotNullParameter(v, "v");
        this.v = v;
        lazy = LazyKt__LazyJVMKt.lazy(new Function0<PackageManager>() { // from class: org.nameless.gamespace.preferences.appselector.adapter.AppsItemViewHolder$pm$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final PackageManager invoke() {
                View view;
                view = AppsItemViewHolder.this.v;
                return view.getContext().getPackageManager();
            }
        });
        this.pm$delegate = lazy;
    }

    private final PackageManager getPm() {
        return (PackageManager) this.pm$delegate.getValue();
    }

    public final void bind(final ApplicationInfo app, final Function1<? super ApplicationInfo, Unit> onClick) {
        Intrinsics.checkNotNullParameter(app, "app");
        Intrinsics.checkNotNullParameter(onClick, "onClick");
        TextView textView = (TextView) this.v.findViewById(R.id.app_name);
        if (textView != null) {
            textView.setText(app.loadLabel(getPm()));
        }
        TextView textView2 = (TextView) this.v.findViewById(R.id.app_summary);
        if (textView2 != null) {
            textView2.setText(app.packageName);
        }
        ImageView imageView = (ImageView) this.v.findViewById(R.id.app_icon);
        if (imageView != null) {
            imageView.setImageDrawable(app.loadIcon(getPm()));
        }
        ViewGroup viewGroup = (ViewGroup) this.v.findViewById(R.id.app_item);
        if (viewGroup != null) {
            viewGroup.setOnClickListener(new View.OnClickListener() { // from class: org.nameless.gamespace.preferences.appselector.adapter.AppsItemViewHolder$bind$1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    onClick.invoke(app);
                }
            });
        }
    }
}
