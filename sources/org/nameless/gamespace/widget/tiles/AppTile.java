package org.nameless.gamespace.widget.tiles;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.nameless.gamespace.R;
import org.nameless.gamespace.widget.QuickLaunchAppViewCache;
/* compiled from: AppTile.kt */
/* loaded from: classes.dex */
public final class AppTile extends LinearLayout {
    private final String key;

    public /* synthetic */ AppTile(Context context, String str, AttributeSet attributeSet, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, str, (i3 & 4) != 0 ? null : attributeSet, (i3 & 8) != 0 ? 0 : i, (i3 & 16) != 0 ? 0 : i2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AppTile(Context context, String packageName, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        Object value;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        this.key = packageName;
        LayoutInflater.from(context).inflate(R.layout.app_tile, (ViewGroup) this, true);
        View findViewById = findViewById(R.id.app_tile_icon);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(R.id.app_tile_icon)");
        ImageView imageView = (ImageView) findViewById;
        value = MapsKt__MapsKt.getValue(QuickLaunchAppViewCache.INSTANCE.getIconMap(), packageName);
        Drawable drawable = (Drawable) value;
        if (drawable != null) {
            imageView.setImageDrawable(drawable);
        }
    }

    public final String getApp() {
        return this.key;
    }
}
