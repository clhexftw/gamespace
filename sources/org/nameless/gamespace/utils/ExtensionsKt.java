package org.nameless.gamespace.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.view.View;
import android.view.WindowManager;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.nameless.gamespace.gamebar.DraggableTouchListener;
/* compiled from: Extensions.kt */
/* loaded from: classes.dex */
public final class ExtensionsKt {
    public static final DraggableTouchListener registerDraggableTouchListener(View view, Function0<? extends Point> initPoint, Function2<? super Integer, ? super Integer, Unit> listener, Function0<Unit> onComplete) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        Intrinsics.checkNotNullParameter(initPoint, "initPoint");
        Intrinsics.checkNotNullParameter(listener, "listener");
        Intrinsics.checkNotNullParameter(onComplete, "onComplete");
        Context context = view.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        return new DraggableTouchListener(context, view, initPoint, listener, onComplete);
    }

    public static final int getStatusbarHeight(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Integer valueOf = Integer.valueOf(context.getResources().getIdentifier("status_bar_height", "dimen", "android"));
        if (!(valueOf.intValue() > 0)) {
            valueOf = null;
        }
        if (valueOf != null) {
            return context.getResources().getDimensionPixelSize(valueOf.intValue());
        }
        return getDp(24);
    }

    public static final int getDp(int i) {
        return (int) (i * Resources.getSystem().getDisplayMetrics().density);
    }

    public static final boolean isPortrait(WindowManager windowManager) {
        Intrinsics.checkNotNullParameter(windowManager, "<this>");
        return windowManager.getMaximumWindowMetrics().getBounds().width() < windowManager.getMaximumWindowMetrics().getBounds().height();
    }
}
