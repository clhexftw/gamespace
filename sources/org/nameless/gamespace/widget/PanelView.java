package org.nameless.gamespace.widget;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Insets;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.nameless.gamespace.R;
import org.nameless.gamespace.utils.ExtensionsKt;
/* compiled from: PanelView.kt */
/* loaded from: classes.dex */
public final class PanelView extends LinearLayout {
    private Float defaultY;
    private final Handler handler;
    private int relativeY;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public PanelView(Context context) {
        this(context, null, 2, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public /* synthetic */ PanelView(Context context, AttributeSet attributeSet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? null : attributeSet);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PanelView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        this.handler = new Handler();
        LayoutInflater.from(context).inflate(R.layout.panel_view, (ViewGroup) this, true);
        setClickable(true);
        setFocusable(true);
    }

    public final int getRelativeY() {
        return this.relativeY;
    }

    public final void setRelativeY(int i) {
        this.relativeY = i;
    }

    /* compiled from: PanelView.kt */
    /* loaded from: classes.dex */
    private final class tempUpdate implements Runnable {
        public tempUpdate() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Intent registerReceiver = PanelView.this.getContext().registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            Intrinsics.checkNotNullExpressionValue(registerReceiver, "context.registerReceiver….ACTION_BATTERY_CHANGED))");
            float intExtra = registerReceiver.getIntExtra("temperature", 0) / 10;
            View findViewById = PanelView.this.findViewById(R.id.temp_info);
            Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(R.id.temp_info)");
            ((TextView) findViewById).setText(PanelView.this.getContext().getString(R.string.temperature_info, Float.valueOf(intExtra)));
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        applyRelativeLocation();
        this.handler.post(new tempUpdate());
    }

    private final void applyRelativeLocation() {
        float floatValue;
        Object systemService = getContext().getSystemService("window");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.WindowManager");
        final WindowManager windowManager = (WindowManager) systemService;
        getLayoutParams().height = -2;
        if (ViewCompat.isLaidOut(this) && !isLayoutRequested()) {
            if (this.defaultY == null) {
                this.defaultY = Float.valueOf(getY());
            }
            if (!ExtensionsKt.isPortrait(windowManager)) {
                Float f = this.defaultY;
                floatValue = f != null ? f.floatValue() : 16.0f;
            } else {
                Insets insets = getRootWindowInsets().getInsets(WindowInsets.Type.systemBars());
                int dp = insets.top + ExtensionsKt.getDp(16);
                int i = insets.top;
                ViewParent parent = getParent();
                Intrinsics.checkNotNull(parent, "null cannot be cast to non-null type android.view.View");
                floatValue = Math.min(Math.max(getRelativeY(), dp), (((i + ((View) parent).getHeight()) - insets.bottom) - getHeight()) - ExtensionsKt.getDp(16));
            }
            setY(floatValue);
            return;
        }
        addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: org.nameless.gamespace.widget.PanelView$applyRelativeLocation$$inlined$doOnLayout$1
            @Override // android.view.View.OnLayoutChangeListener
            public void onLayoutChange(View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
                float floatValue2;
                Intrinsics.checkNotNullParameter(view, "view");
                view.removeOnLayoutChangeListener(this);
                if (PanelView.this.defaultY == null) {
                    PanelView panelView = PanelView.this;
                    panelView.defaultY = Float.valueOf(panelView.getY());
                }
                PanelView panelView2 = PanelView.this;
                if (!ExtensionsKt.isPortrait(windowManager)) {
                    Float f2 = PanelView.this.defaultY;
                    floatValue2 = f2 != null ? f2.floatValue() : 16.0f;
                } else {
                    Insets insets2 = PanelView.this.getRootWindowInsets().getInsets(WindowInsets.Type.systemBars());
                    int dp2 = insets2.top + ExtensionsKt.getDp(16);
                    int i10 = insets2.top;
                    ViewParent parent2 = PanelView.this.getParent();
                    Intrinsics.checkNotNull(parent2, "null cannot be cast to non-null type android.view.View");
                    floatValue2 = Math.min(Math.max(PanelView.this.getRelativeY(), dp2), (((i10 + ((View) parent2).getHeight()) - insets2.bottom) - PanelView.this.getHeight()) - ExtensionsKt.getDp(16));
                }
                panelView2.setY(floatValue2);
            }
        });
    }
}
