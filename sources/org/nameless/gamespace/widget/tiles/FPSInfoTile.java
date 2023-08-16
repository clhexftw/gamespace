package org.nameless.gamespace.widget.tiles;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.nameless.gamespace.R;
/* compiled from: FPSInfoTile.kt */
/* loaded from: classes.dex */
public final class FPSInfoTile extends BaseTile {
    private boolean showFpsInfo;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FPSInfoTile(Context context) {
        this(context, null, 2, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public /* synthetic */ FPSInfoTile(Context context, AttributeSet attributeSet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? null : attributeSet);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FPSInfoTile(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        setShowFpsInfo(getAppSettings().getShowFps());
        TextView title = getTitle();
        if (title != null) {
            title.setText(getContext().getString(R.string.fps_Info_title));
        }
        ImageView icon = getIcon();
        if (icon != null) {
            icon.setImageResource(R.drawable.ic_fps);
        }
    }

    private final void setShowFpsInfo(boolean z) {
        this.showFpsInfo = z;
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
        getAppSettings().setShowFps(z);
        setSelected(z);
    }

    @Override // org.nameless.gamespace.widget.tiles.BaseTile, android.view.View.OnClickListener
    public void onClick(View view) {
        super.onClick(view);
        setShowFpsInfo(!this.showFpsInfo);
    }
}
