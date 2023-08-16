package org.nameless.gamespace.widget.tiles;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import dagger.hilt.EntryPoints;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.nameless.gamespace.R;
import org.nameless.gamespace.utils.ScreenUtils;
import org.nameless.gamespace.utils.di.ServiceViewEntryPoint;
/* compiled from: StayAwakeTile.kt */
/* loaded from: classes.dex */
public final class StayAwakeTile extends BaseTile {
    private final Lazy screenUtils$delegate;
    private boolean shouldStayAwake;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public StayAwakeTile(Context context) {
        this(context, null, 2, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public /* synthetic */ StayAwakeTile(Context context, AttributeSet attributeSet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? null : attributeSet);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StayAwakeTile(final Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Lazy lazy;
        Intrinsics.checkNotNullParameter(context, "context");
        lazy = LazyKt__LazyJVMKt.lazy(new Function0<ScreenUtils>() { // from class: org.nameless.gamespace.widget.tiles.StayAwakeTile$screenUtils$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final ScreenUtils invoke() {
                Object obj = EntryPoints.get(context.getApplicationContext(), ServiceViewEntryPoint.class);
                Intrinsics.checkNotNullExpressionValue(obj, "get(applicationContext, T::class.java)");
                return ((ServiceViewEntryPoint) obj).screenUtils();
            }
        });
        this.screenUtils$delegate = lazy;
    }

    private final ScreenUtils getScreenUtils() {
        return (ScreenUtils) this.screenUtils$delegate.getValue();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        setShouldStayAwake(getAppSettings().getStayAwake());
        TextView title = getTitle();
        if (title != null) {
            title.setText(getContext().getString(R.string.stay_awake_title));
        }
        ImageView icon = getIcon();
        if (icon != null) {
            icon.setImageResource(R.drawable.ic_awake);
        }
    }

    private final void setShouldStayAwake(boolean z) {
        this.shouldStayAwake = z;
        if (z) {
            TextView summary = getSummary();
            if (summary != null) {
                summary.setText(getContext().getString(R.string.state_enabled));
            }
        } else {
            TextView summary2 = getSummary();
            if (summary2 != null) {
                summary2.setText(getContext().getString(R.string.state_disabled));
            }
        }
        getAppSettings().setStayAwake(z);
        setSelected(z);
        getScreenUtils().setStayAwake(z);
    }

    @Override // org.nameless.gamespace.widget.tiles.BaseTile, android.view.View.OnClickListener
    public void onClick(View view) {
        super.onClick(view);
        setShouldStayAwake(!this.shouldStayAwake);
    }
}
