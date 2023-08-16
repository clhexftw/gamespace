package org.nameless.gamespace.widget.tiles;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.nameless.gamespace.R;
/* compiled from: NotificationTile.kt */
/* loaded from: classes.dex */
public final class NotificationTile extends BaseTile {
    public static final Companion Companion = new Companion(null);
    private int activeMode;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public NotificationTile(Context context) {
        this(context, null, 2, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public /* synthetic */ NotificationTile(Context context, AttributeSet attributeSet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? null : attributeSet);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NotificationTile(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        this.activeMode = 2;
    }

    private final void setActiveMode(int i) {
        this.activeMode = i;
        getAppSettings().setNotificationsMode(i);
        if (i == 0) {
            getSystemSettings().setHeadsUp(false);
            TextView summary = getSummary();
            if (summary != null) {
                summary.setText(getContext().getString(R.string.notifications_hide));
            }
        } else if (i == 1) {
            getSystemSettings().setHeadsUp(true);
            TextView summary2 = getSummary();
            if (summary2 != null) {
                summary2.setText(getContext().getString(R.string.notifications_headsup));
            }
        } else if (i == 2) {
            getSystemSettings().setHeadsUp(false);
            TextView summary3 = getSummary();
            if (summary3 != null) {
                summary3.setText(getContext().getString(R.string.notifications_danmaku));
            }
        }
        setSelected(i != 0);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        TextView title = getTitle();
        if (title != null) {
            title.setText(getContext().getString(R.string.notifications_title));
        }
        setActiveMode(getAppSettings().getNotificationsMode());
        ImageView icon = getIcon();
        if (icon != null) {
            icon.setImageResource(R.drawable.ic_action_heads_up);
        }
    }

    @Override // org.nameless.gamespace.widget.tiles.BaseTile, android.view.View.OnClickListener
    public void onClick(View view) {
        super.onClick(view);
        int i = this.activeMode;
        setActiveMode(i == 2 ? 0 : i + 1);
    }

    /* compiled from: NotificationTile.kt */
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
