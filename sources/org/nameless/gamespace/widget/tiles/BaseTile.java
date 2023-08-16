package org.nameless.gamespace.widget.tiles;

import android.content.Context;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import dagger.hilt.EntryPoints;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.nameless.gamespace.R;
import org.nameless.gamespace.data.AppSettings;
import org.nameless.gamespace.data.SystemSettings;
import org.nameless.gamespace.utils.di.ServiceViewEntryPoint;
import org.nameless.vibrator.CustomVibrationAttributes;
/* compiled from: BaseTile.kt */
/* loaded from: classes.dex */
public abstract class BaseTile extends LinearLayout implements View.OnClickListener {
    private final Lazy appSettings$delegate;
    private final VibrationEffect clickEffect;
    private final Lazy systemSettings$delegate;
    private final Vibrator vibrator;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public BaseTile(Context context) {
        this(context, null, 2, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public /* synthetic */ BaseTile(Context context, AttributeSet attributeSet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? null : attributeSet);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseTile(final Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Lazy lazy;
        Lazy lazy2;
        Intrinsics.checkNotNullParameter(context, "context");
        setClickable(true);
        setFocusable(true);
        prepareLayout();
        lazy = LazyKt__LazyJVMKt.lazy(new Function0<AppSettings>() { // from class: org.nameless.gamespace.widget.tiles.BaseTile$appSettings$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final AppSettings invoke() {
                Object obj = EntryPoints.get(context.getApplicationContext(), ServiceViewEntryPoint.class);
                Intrinsics.checkNotNullExpressionValue(obj, "get(applicationContext, T::class.java)");
                return ((ServiceViewEntryPoint) obj).appSettings();
            }
        });
        this.appSettings$delegate = lazy;
        lazy2 = LazyKt__LazyJVMKt.lazy(new Function0<SystemSettings>() { // from class: org.nameless.gamespace.widget.tiles.BaseTile$systemSettings$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final SystemSettings invoke() {
                Object obj = EntryPoints.get(context.getApplicationContext(), ServiceViewEntryPoint.class);
                Intrinsics.checkNotNullExpressionValue(obj, "get(applicationContext, T::class.java)");
                return ((ServiceViewEntryPoint) obj).systemSettings();
            }
        });
        this.systemSettings$delegate = lazy2;
        Object systemService = context.getSystemService("vibrator");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.os.Vibrator");
        this.vibrator = (Vibrator) systemService;
        this.clickEffect = VibrationEffect.createPredefined(0);
    }

    public final AppSettings getAppSettings() {
        return (AppSettings) this.appSettings$delegate.getValue();
    }

    public final SystemSettings getSystemSettings() {
        return (SystemSettings) this.systemSettings$delegate.getValue();
    }

    public final TextView getTitle() {
        return (TextView) findViewById(R.id.tile_title);
    }

    public final TextView getSummary() {
        return (TextView) findViewById(R.id.tile_summary);
    }

    public final ImageView getIcon() {
        return (ImageView) findViewById(R.id.tile_icon);
    }

    private final void prepareLayout() {
        LayoutInflater.from(getContext()).inflate(R.layout.panel_tile, (ViewGroup) this, true);
        setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        setSelected(!isSelected());
        this.vibrator.vibrate(this.clickEffect, CustomVibrationAttributes.VIBRATION_ATTRIBUTES_QS_TILE);
    }
}
