package org.nameless.gamespace.widget;

import android.app.ActivityTaskManager;
import android.app.IActivityTaskManager;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.window.TaskFpsCallback;
import dagger.hilt.EntryPoints;
import java.util.concurrent.Executor;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt__JobKt;
import org.nameless.gamespace.R;
import org.nameless.gamespace.data.AppSettings;
import org.nameless.gamespace.utils.di.ServiceViewEntryPoint;
/* compiled from: MenuSwitcher.kt */
/* loaded from: classes.dex */
public final class MenuSwitcher extends LinearLayout {
    private final Lazy appSettings$delegate;
    private boolean isDragged;
    private final CoroutineScope scope;
    private boolean showFps;
    private final MenuSwitcher$taskFpsCallback$1 taskFpsCallback;
    private final Lazy taskManager$delegate;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public MenuSwitcher(Context context) {
        this(context, null, 2, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public /* synthetic */ MenuSwitcher(Context context, AttributeSet attributeSet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? null : attributeSet);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r3v8, types: [org.nameless.gamespace.widget.MenuSwitcher$taskFpsCallback$1] */
    public MenuSwitcher(final Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Lazy lazy;
        CompletableJob Job$default;
        Lazy lazy2;
        Intrinsics.checkNotNullParameter(context, "context");
        LayoutInflater.from(context).inflate(R.layout.bar_menu_switcher, (ViewGroup) this, true);
        lazy = LazyKt__LazyJVMKt.lazy(new Function0<AppSettings>() { // from class: org.nameless.gamespace.widget.MenuSwitcher$appSettings$2
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
        Job$default = JobKt__JobKt.Job$default(null, 1, null);
        this.scope = CoroutineScopeKt.CoroutineScope(Job$default.plus(Dispatchers.getMain()));
        lazy2 = LazyKt__LazyJVMKt.lazy(new Function0<IActivityTaskManager>() { // from class: org.nameless.gamespace.widget.MenuSwitcher$taskManager$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final IActivityTaskManager invoke() {
                return ActivityTaskManager.getService();
            }
        });
        this.taskManager$delegate = lazy2;
        this.taskFpsCallback = new TaskFpsCallback() { // from class: org.nameless.gamespace.widget.MenuSwitcher$taskFpsCallback$1
            public void onFpsReported(float f) {
                if (MenuSwitcher.this.isAttachedToWindow()) {
                    MenuSwitcher.this.onFrameUpdated(f);
                }
            }
        };
    }

    private final AppSettings getAppSettings() {
        return (AppSettings) this.appSettings$delegate.getValue();
    }

    private final IActivityTaskManager getTaskManager() {
        return (IActivityTaskManager) this.taskManager$delegate.getValue();
    }

    private final WindowManager getWm() {
        Object systemService = getContext().getSystemService("window");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.WindowManager");
        return (WindowManager) systemService;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final TextView getContent() {
        return (TextView) findViewById(R.id.menu_content);
    }

    public final void setShowFps(boolean z) {
        setMenuIcon(null);
        this.showFps = z;
    }

    public final boolean isDragged() {
        return this.isDragged;
    }

    public final void setDragged(boolean z) {
        if (z && !this.showFps) {
            setMenuIcon(Integer.valueOf((int) R.drawable.ic_drag));
        }
        this.isDragged = z;
    }

    public final void updateIconState(boolean z, int i) {
        setShowFps(z ? false : getAppSettings().getShowFps());
        setMenuIcon(Integer.valueOf(z ? R.drawable.ic_close : i > 0 ? R.drawable.ic_arrow_right : R.drawable.ic_arrow_left));
        updateFrameRateBinding();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Job onFrameUpdated(float f) {
        Job launch$default;
        launch$default = BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new MenuSwitcher$onFrameUpdated$1(this, f, null), 3, null);
        return launch$default;
    }

    private final void updateFrameRateBinding() {
        ActivityTaskManager.RootTaskInfo focusedRootTaskInfo;
        if (this.showFps) {
            IActivityTaskManager taskManager = getTaskManager();
            if (taskManager == null || (focusedRootTaskInfo = taskManager.getFocusedRootTaskInfo()) == null) {
                return;
            }
            getWm().registerTaskFpsCallback(focusedRootTaskInfo.taskId, new Executor() { // from class: org.nameless.gamespace.widget.MenuSwitcher$updateFrameRateBinding$1$1
                @Override // java.util.concurrent.Executor
                public final void execute(Runnable p0) {
                    Intrinsics.checkNotNullParameter(p0, "p0");
                    p0.run();
                }
            }, this.taskFpsCallback);
            return;
        }
        getWm().unregisterTaskFpsCallback(this.taskFpsCallback);
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:39:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void setMenuIcon(java.lang.Integer r4) {
        /*
            r3 = this;
            r0 = 1
            if (r4 != 0) goto L4
            goto Lf
        L4:
            int r1 = r4.intValue()
            r2 = 2131230899(0x7f0800b3, float:1.8077864E38)
            if (r1 != r2) goto Lf
        Ld:
            r1 = r0
            goto L1d
        Lf:
            r1 = 2131230905(0x7f0800b9, float:1.8077876E38)
            if (r4 != 0) goto L15
            goto L1c
        L15:
            int r2 = r4.intValue()
            if (r2 != r1) goto L1c
            goto Ld
        L1c:
            r1 = 0
        L1d:
            if (r1 == 0) goto L2c
            android.view.ViewGroup$LayoutParams r1 = r3.getLayoutParams()
            r2 = 36
            int r2 = org.nameless.gamespace.utils.ExtensionsKt.getDp(r2)
            r1.width = r2
            goto L33
        L2c:
            android.view.ViewGroup$LayoutParams r1 = r3.getLayoutParams()
            r2 = -2
            r1.width = r2
        L33:
            r1 = 0
            if (r4 == 0) goto L57
            r4.intValue()
            boolean r2 = r3.showFps
            r0 = r0 ^ r2
            if (r0 == 0) goto L3f
            goto L40
        L3f:
            r4 = r1
        L40:
            if (r4 == 0) goto L57
            int r4 = r4.intValue()
            android.content.res.Resources r0 = r3.getResources()
            android.content.Context r2 = r3.getContext()
            android.content.res.Resources$Theme r2 = r2.getTheme()
            android.graphics.drawable.Drawable r4 = r0.getDrawable(r4, r2)
            goto L58
        L57:
            r4 = r1
        L58:
            android.widget.TextView r0 = r3.getContent()
            if (r0 != 0) goto L5f
            goto L6a
        L5f:
            boolean r2 = r3.showFps
            if (r2 == 0) goto L66
            r2 = 1065353216(0x3f800000, float:1.0)
            goto L67
        L66:
            r2 = 0
        L67:
            r0.setTextScaleX(r2)
        L6a:
            android.widget.TextView r3 = r3.getContent()
            if (r3 == 0) goto L73
            r3.setCompoundDrawablesRelativeWithIntrinsicBounds(r1, r4, r1, r1)
        L73:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.nameless.gamespace.widget.MenuSwitcher.setMenuIcon(java.lang.Integer):void");
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getWm().unregisterTaskFpsCallback(this.taskFpsCallback);
    }
}
