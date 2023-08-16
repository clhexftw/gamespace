package org.nameless.gamespace.gamebar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.Point;
import android.net.Uri;
import android.os.Binder;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import androidx.core.view.MarginLayoutParamsCompat;
import androidx.core.view.ViewGroupKt;
import com.android.systemui.screenrecord.IRecordingCallback;
import com.android.systemui.screenrecord.IRemoteRecording;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;
import org.nameless.gamespace.R;
import org.nameless.gamespace.data.AppSettings;
import org.nameless.gamespace.settings.SettingsActivity;
import org.nameless.gamespace.utils.ExtensionsKt;
import org.nameless.gamespace.utils.ScreenUtils;
import org.nameless.gamespace.widget.MenuSwitcher;
import org.nameless.gamespace.widget.PanelView;
import org.nameless.gamespace.widget.QuickLaunchAppViewCache;
/* compiled from: GameBarService.kt */
/* loaded from: classes.dex */
public final class GameBarService extends Hilt_GameBarService {
    public static final Companion Companion = new Companion(null);
    public AppSettings appSettings;
    private boolean barExpanded;
    private final WindowManager.LayoutParams barLayoutParam;
    private LinearLayout barView;
    private final GameBarBinder binder;
    public DanmakuService danmakuService;
    private final Runnable firstPaint;
    private MenuSwitcher menuSwitcher;
    private final WindowManager.LayoutParams panelLayoutParam;
    private PanelView panelView;
    private final ClosePanelReceiver receiver;
    private View rootBarView;
    private LinearLayout rootPanelView;
    public ScreenUtils screenUtils;
    private boolean showPanel;
    private final Lazy wm$delegate = LazyKt.lazy(new Function0<WindowManager>() { // from class: org.nameless.gamespace.gamebar.GameBarService$wm$2
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final WindowManager invoke() {
            Object systemService = GameBarService.this.getSystemService("window");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.WindowManager");
            return (WindowManager) systemService;
        }
    });
    private final Lazy handler$delegate = LazyKt.lazy(new Function0<Handler>() { // from class: org.nameless.gamespace.gamebar.GameBarService$handler$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Handler invoke() {
            return new Handler(Looper.getMainLooper());
        }
    });

    public GameBarService() {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(2008, 296, -3);
        layoutParams.width = -2;
        layoutParams.height = -2;
        layoutParams.layoutInDisplayCutoutMode = 3;
        layoutParams.preferMinimalPostProcessing = true;
        layoutParams.gravity = 48;
        this.barLayoutParam = layoutParams;
        WindowManager.LayoutParams layoutParams2 = new WindowManager.LayoutParams(2038, 42, -3);
        layoutParams2.dimAmount = 0.7f;
        layoutParams2.width = -1;
        layoutParams2.height = -1;
        layoutParams2.layoutInDisplayCutoutMode = 3;
        layoutParams2.gravity = 16;
        this.panelLayoutParam = layoutParams2;
        this.binder = new GameBarBinder();
        this.receiver = new ClosePanelReceiver();
        this.firstPaint = new Runnable() { // from class: org.nameless.gamespace.gamebar.GameBarService$firstPaint$1
            @Override // java.lang.Runnable
            public final void run() {
                GameBarService.this.initActions();
            }
        };
    }

    public final AppSettings getAppSettings() {
        AppSettings appSettings = this.appSettings;
        if (appSettings != null) {
            return appSettings;
        }
        Intrinsics.throwUninitializedPropertyAccessException("appSettings");
        return null;
    }

    public final ScreenUtils getScreenUtils() {
        ScreenUtils screenUtils = this.screenUtils;
        if (screenUtils != null) {
            return screenUtils;
        }
        Intrinsics.throwUninitializedPropertyAccessException("screenUtils");
        return null;
    }

    public final DanmakuService getDanmakuService() {
        DanmakuService danmakuService = this.danmakuService;
        if (danmakuService != null) {
            return danmakuService;
        }
        Intrinsics.throwUninitializedPropertyAccessException("danmakuService");
        return null;
    }

    private final WindowManager getWm() {
        return (WindowManager) this.wm$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Handler getHandler() {
        return (Handler) this.handler$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setBarExpanded(boolean z) {
        this.barExpanded = z;
        MenuSwitcher menuSwitcher = this.menuSwitcher;
        LinearLayout linearLayout = null;
        if (menuSwitcher == null) {
            Intrinsics.throwUninitializedPropertyAccessException("menuSwitcher");
            menuSwitcher = null;
        }
        menuSwitcher.updateIconState(z, this.barLayoutParam.x);
        LinearLayout linearLayout2 = this.barView;
        if (linearLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("barView");
        } else {
            linearLayout = linearLayout2;
        }
        for (View view : ViewGroupKt.getChildren(linearLayout)) {
            if (view.getId() != R.id.action_menu_switcher) {
                view.setVisibility(z ? 0 : 8);
            }
        }
        updateBackground();
        updateContainerGaps();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setShowPanel(boolean z) {
        LinearLayout linearLayout;
        this.showPanel = z;
        LinearLayout linearLayout2 = null;
        if (!z || this.rootPanelView != null) {
            LinearLayout linearLayout3 = this.rootPanelView;
            if (linearLayout3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rootPanelView");
                linearLayout3 = null;
            }
            if (linearLayout3.isAttachedToWindow()) {
                if (z || (linearLayout = this.rootPanelView) == null) {
                    return;
                }
                if (linearLayout == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rootPanelView");
                    linearLayout = null;
                }
                if (linearLayout.isAttachedToWindow()) {
                    WindowManager wm = getWm();
                    LinearLayout linearLayout4 = this.rootPanelView;
                    if (linearLayout4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("rootPanelView");
                    } else {
                        linearLayout2 = linearLayout4;
                    }
                    wm.removeView(linearLayout2);
                    return;
                }
                return;
            }
        }
        setupPanelView();
        WindowManager wm2 = getWm();
        LinearLayout linearLayout5 = this.rootPanelView;
        if (linearLayout5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootPanelView");
        } else {
            linearLayout2 = linearLayout5;
        }
        wm2.addView(linearLayout2, this.panelLayoutParam);
    }

    @Override // org.nameless.gamespace.gamebar.Hilt_GameBarService, android.app.Service
    public void onCreate() {
        super.onCreate();
        View inflate = LayoutInflater.from(this).inflate(R.layout.window_util, (ViewGroup) new FrameLayout(this), false);
        Intrinsics.checkNotNullExpressionValue(inflate, "from(this)\n            .…indow_util, frame, false)");
        this.rootBarView = inflate;
        View view = null;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootBarView");
            inflate = null;
        }
        View findViewById = inflate.findViewById(R.id.container_bar);
        Intrinsics.checkNotNullExpressionValue(findViewById, "rootBarView.findViewById(R.id.container_bar)");
        this.barView = (LinearLayout) findViewById;
        View view2 = this.rootBarView;
        if (view2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootBarView");
        } else {
            view = view2;
        }
        View findViewById2 = view.findViewById(R.id.action_menu_switcher);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "rootBarView.findViewById….id.action_menu_switcher)");
        this.menuSwitcher = (MenuSwitcher) findViewById2;
        getDanmakuService().init();
        getHandler().post(new Runnable() { // from class: org.nameless.gamespace.gamebar.GameBarService$onCreate$1
            @Override // java.lang.Runnable
            public final void run() {
                QuickLaunchAppViewCache.INSTANCE.updateAppList(GameBarService.this);
            }
        });
        registerReceiver(this.receiver, new IntentFilter("org.nameless.gamespace.CLONE_PANEL"));
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        super.onStartCommand(intent, i, i2);
        String action = intent != null ? intent.getAction() : null;
        if (Intrinsics.areEqual(action, "GameBar.ACTION_STOP")) {
            onGameLeave();
            return 1;
        } else if (Intrinsics.areEqual(action, "GameBar.ACTION_START")) {
            onGameStart();
            return 1;
        } else {
            return 1;
        }
    }

    @Override // android.app.Service
    public GameBarBinder onBind(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        return this.binder;
    }

    /* compiled from: GameBarService.kt */
    /* loaded from: classes.dex */
    public final class GameBarBinder extends Binder {
        public GameBarBinder() {
        }

        public final GameBarService getService() {
            return GameBarService.this;
        }
    }

    /* compiled from: GameBarService.kt */
    /* loaded from: classes.dex */
    public final class ClosePanelReceiver extends BroadcastReceiver {
        public ClosePanelReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(intent, "intent");
            GameBarService.this.setShowPanel(false);
            GameBarService.this.setBarExpanded(false);
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        unregisterReceiver(this.receiver);
        getDanmakuService().destroy();
        onGameLeave();
        super.onDestroy();
    }

    @Override // android.app.Service, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        super.onConfigurationChanged(newConfig);
        View view = this.rootBarView;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootBarView");
            view = null;
        }
        if (!(view.getVisibility() == 0)) {
            getHandler().removeCallbacks(this.firstPaint);
            getHandler().postDelayed(new Runnable() { // from class: org.nameless.gamespace.gamebar.GameBarService$onConfigurationChanged$1
                @Override // java.lang.Runnable
                public final void run() {
                    Runnable runnable;
                    runnable = GameBarService.this.firstPaint;
                    runnable.run();
                    GameBarService.this.dockCollapsedMenu();
                }
            }, 100L);
        } else {
            dockCollapsedMenu();
        }
        getDanmakuService().updateConfiguration(newConfig);
    }

    public final void onGameStart() {
        View view = this.rootBarView;
        View view2 = null;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootBarView");
            view = null;
        }
        view.setVisibility(8);
        View view3 = this.rootBarView;
        if (view3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootBarView");
        } else {
            view2 = view3;
        }
        view2.setAlpha(0.0f);
        updateRootBarView();
        getHandler().postDelayed(this.firstPaint, 500L);
    }

    public final void onGameLeave() {
        LinearLayout linearLayout = this.rootPanelView;
        View view = null;
        if (linearLayout != null) {
            if (linearLayout == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rootPanelView");
                linearLayout = null;
            }
            if (linearLayout.isAttachedToWindow()) {
                WindowManager wm = getWm();
                LinearLayout linearLayout2 = this.rootPanelView;
                if (linearLayout2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rootPanelView");
                    linearLayout2 = null;
                }
                wm.removeViewImmediate(linearLayout2);
            }
        }
        View view2 = this.rootBarView;
        if (view2 != null) {
            if (view2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rootBarView");
                view2 = null;
            }
            if (view2.isAttachedToWindow()) {
                WindowManager wm2 = getWm();
                View view3 = this.rootBarView;
                if (view3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rootBarView");
                } else {
                    view = view3;
                }
                wm2.removeViewImmediate(view);
            }
        }
    }

    private final void updateRootBarView() {
        View view = this.rootBarView;
        if (view == null) {
            return;
        }
        View view2 = null;
        if (view == null) {
            try {
                Intrinsics.throwUninitializedPropertyAccessException("rootBarView");
                view = null;
            } catch (RuntimeException unused) {
                View view3 = this.rootBarView;
                if (view3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rootBarView");
                    view3 = null;
                }
                if (view3.isAttachedToWindow()) {
                    WindowManager wm = getWm();
                    View view4 = this.rootBarView;
                    if (view4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("rootBarView");
                    } else {
                        view2 = view4;
                    }
                    wm.updateViewLayout(view2, this.barLayoutParam);
                    return;
                }
                return;
            }
        }
        if (view.isAttachedToWindow()) {
            WindowManager wm2 = getWm();
            View view5 = this.rootBarView;
            if (view5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rootBarView");
                view5 = null;
            }
            wm2.removeViewImmediate(view5);
        }
        if (getAppSettings().getShowOverlay()) {
            WindowManager wm3 = getWm();
            View view6 = this.rootBarView;
            if (view6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rootBarView");
                view6 = null;
            }
            wm3.addView(view6, this.barLayoutParam);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateLayout(Function1<? super WindowManager.LayoutParams, Unit> function1) {
        View view = this.rootBarView;
        View view2 = null;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootBarView");
            view = null;
        }
        if (view.isAttachedToWindow()) {
            WindowManager wm = getWm();
            View view3 = this.rootBarView;
            if (view3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rootBarView");
            } else {
                view2 = view3;
            }
            WindowManager.LayoutParams layoutParams = this.barLayoutParam;
            function1.invoke(layoutParams);
            wm.updateViewLayout(view2, layoutParams);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void initActions() {
        View view = this.rootBarView;
        View view2 = null;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootBarView");
            view = null;
        }
        view.setVisibility(0);
        View view3 = this.rootBarView;
        if (view3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootBarView");
        } else {
            view2 = view3;
        }
        ViewPropertyAnimator alpha = view2.animate().alpha(1.0f);
        alpha.setDuration(300L);
        alpha.start();
        setBarExpanded(false);
        this.barLayoutParam.x = getAppSettings().getX();
        this.barLayoutParam.y = getAppSettings().getY();
        dockCollapsedMenu();
        menuSwitcherButton();
        panelButton();
        screenshotButton();
        recorderButton();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:26:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0048  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void updateBackground() {
        /*
            r6 = this;
            boolean r0 = r6.barExpanded
            r1 = 0
            java.lang.String r2 = "barView"
            r3 = 1
            r4 = 0
            if (r0 != 0) goto L21
            android.widget.LinearLayout r0 = r6.barView
            if (r0 != 0) goto L11
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r0 = r1
        L11:
            float r0 = r0.getTranslationX()
            r5 = 0
            int r0 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r0 != 0) goto L1c
            r0 = r3
            goto L1d
        L1c:
            r0 = r4
        L1d:
            if (r0 == 0) goto L21
            r0 = r3
            goto L22
        L21:
            r0 = r4
        L22:
            if (r0 != 0) goto L2c
            android.view.WindowManager$LayoutParams r5 = r6.barLayoutParam
            int r5 = r5.x
            if (r5 >= 0) goto L2c
            r5 = r3
            goto L2d
        L2c:
            r5 = r4
        L2d:
            if (r0 != 0) goto L36
            android.view.WindowManager$LayoutParams r0 = r6.barLayoutParam
            int r0 = r0.x
            if (r0 <= 0) goto L36
            goto L37
        L36:
            r3 = r4
        L37:
            android.widget.LinearLayout r0 = r6.barView
            if (r0 != 0) goto L3f
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            goto L40
        L3f:
            r1 = r0
        L40:
            boolean r6 = r6.barExpanded
            r0 = 2131230847(0x7f08007f, float:1.8077758E38)
            if (r6 == 0) goto L48
            goto L53
        L48:
            if (r5 == 0) goto L4e
            r0 = 2131230846(0x7f08007e, float:1.8077756E38)
            goto L53
        L4e:
            if (r3 == 0) goto L53
            r0 = 2131230845(0x7f08007d, float:1.8077754E38)
        L53:
            r1.setBackgroundResource(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.nameless.gamespace.gamebar.GameBarService.updateBackground():void");
    }

    private final void updateContainerGaps() {
        LinearLayout linearLayout = null;
        if (this.barExpanded) {
            LinearLayout linearLayout2 = this.barView;
            if (linearLayout2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("barView");
                linearLayout2 = null;
            }
            linearLayout2.setPadding(8, 8, 8, 8);
            LinearLayout linearLayout3 = this.barView;
            if (linearLayout3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("barView");
            } else {
                linearLayout = linearLayout3;
            }
            ViewGroup.LayoutParams layoutParams = linearLayout.getLayoutParams();
            Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            marginLayoutParams.setMargins(48, marginLayoutParams.topMargin, 48, marginLayoutParams.bottomMargin);
            return;
        }
        LinearLayout linearLayout4 = this.barView;
        if (linearLayout4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("barView");
            linearLayout4 = null;
        }
        linearLayout4.setPadding(0, 0, 0, 0);
        LinearLayout linearLayout5 = this.barView;
        if (linearLayout5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("barView");
        } else {
            linearLayout = linearLayout5;
        }
        ViewGroup.LayoutParams layoutParams2 = linearLayout.getLayoutParams();
        Intrinsics.checkNotNull(layoutParams2, "null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
        ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) layoutParams2;
        marginLayoutParams2.setMargins(0, marginLayoutParams2.topMargin, 0, marginLayoutParams2.bottomMargin);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void dockCollapsedMenu() {
        int coerceIn;
        int width = getWm().getMaximumWindowMetrics().getBounds().width() / 2;
        MenuSwitcher menuSwitcher = null;
        if (this.barLayoutParam.x < 0) {
            LinearLayout linearLayout = this.barView;
            if (linearLayout == null) {
                Intrinsics.throwUninitializedPropertyAccessException("barView");
                linearLayout = null;
            }
            linearLayout.setTranslationX(-22.0f);
            this.barLayoutParam.x = -width;
        } else {
            LinearLayout linearLayout2 = this.barView;
            if (linearLayout2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("barView");
                linearLayout2 = null;
            }
            linearLayout2.setTranslationX(22.0f);
            this.barLayoutParam.x = width;
        }
        int statusbarHeight = ExtensionsKt.getStatusbarHeight(this) + ExtensionsKt.getDp(4);
        WindowManager.LayoutParams layoutParams = this.barLayoutParam;
        coerceIn = RangesKt___RangesKt.coerceIn(layoutParams.y, statusbarHeight, getWm().getMaximumWindowMetrics().getBounds().height() - statusbarHeight);
        layoutParams.y = coerceIn;
        updateBackground();
        updateContainerGaps();
        MenuSwitcher menuSwitcher2 = this.menuSwitcher;
        if (menuSwitcher2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("menuSwitcher");
            menuSwitcher2 = null;
        }
        menuSwitcher2.setShowFps(this.barExpanded ? false : getAppSettings().getShowFps());
        MenuSwitcher menuSwitcher3 = this.menuSwitcher;
        if (menuSwitcher3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("menuSwitcher");
        } else {
            menuSwitcher = menuSwitcher3;
        }
        menuSwitcher.updateIconState(this.barExpanded, this.barLayoutParam.x);
        updateRootBarView();
    }

    private final void setupPanelView() {
        int last;
        View inflate = LayoutInflater.from(this).inflate(R.layout.window_panel, (ViewGroup) new FrameLayout(this), false);
        Intrinsics.checkNotNull(inflate, "null cannot be cast to non-null type android.widget.LinearLayout");
        LinearLayout linearLayout = (LinearLayout) inflate;
        this.rootPanelView = linearLayout;
        LinearLayout linearLayout2 = null;
        if (linearLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootPanelView");
            linearLayout = null;
        }
        View findViewById = linearLayout.findViewById(R.id.panel_view);
        Intrinsics.checkNotNullExpressionValue(findViewById, "rootPanelView.findViewById(R.id.panel_view)");
        PanelView panelView = (PanelView) findViewById;
        this.panelView = panelView;
        if (panelView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("panelView");
            panelView = null;
        }
        panelView.setAlpha(getAppSettings().getMenuOpacity() / 100.0f);
        LinearLayout linearLayout3 = this.rootPanelView;
        if (linearLayout3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootPanelView");
            linearLayout3 = null;
        }
        linearLayout3.setOnClickListener(new View.OnClickListener() { // from class: org.nameless.gamespace.gamebar.GameBarService$setupPanelView$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GameBarService.this.setShowPanel(false);
            }
        });
        LinearLayout linearLayout4 = this.barView;
        if (linearLayout4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("barView");
            linearLayout4 = null;
        }
        int width = linearLayout4.getWidth();
        LinearLayout linearLayout5 = this.barView;
        if (linearLayout5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("barView");
            linearLayout5 = null;
        }
        ViewGroup.LayoutParams layoutParams = linearLayout5.getLayoutParams();
        int marginStart = width + (layoutParams instanceof ViewGroup.MarginLayoutParams ? MarginLayoutParamsCompat.getMarginStart((ViewGroup.MarginLayoutParams) layoutParams) : 0);
        if (this.barLayoutParam.x < 0) {
            LinearLayout linearLayout6 = this.rootPanelView;
            if (linearLayout6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rootPanelView");
                linearLayout6 = null;
            }
            linearLayout6.setGravity(8388611);
            LinearLayout linearLayout7 = this.rootPanelView;
            if (linearLayout7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rootPanelView");
                linearLayout7 = null;
            }
            linearLayout7.setPaddingRelative(marginStart, 16, 16, 16);
        } else {
            LinearLayout linearLayout8 = this.rootPanelView;
            if (linearLayout8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rootPanelView");
                linearLayout8 = null;
            }
            linearLayout8.setGravity(8388613);
            LinearLayout linearLayout9 = this.rootPanelView;
            if (linearLayout9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rootPanelView");
                linearLayout9 = null;
            }
            linearLayout9.setPaddingRelative(16, 16, marginStart, 16);
        }
        PanelView panelView2 = this.panelView;
        if (panelView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("panelView");
            panelView2 = null;
        }
        LinearLayout linearLayout10 = this.barView;
        if (linearLayout10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("barView");
            linearLayout10 = null;
        }
        int[] locationOnScreen = linearLayout10.getLocationOnScreen();
        Intrinsics.checkNotNullExpressionValue(locationOnScreen, "barView.locationOnScreen");
        last = ArraysKt___ArraysKt.last(locationOnScreen);
        LinearLayout linearLayout11 = this.barView;
        if (linearLayout11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("barView");
        } else {
            linearLayout2 = linearLayout11;
        }
        panelView2.setRelativeY(last - linearLayout2.getHeight());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void takeShot() {
        final Function0<Unit> function0 = new Function0<Unit>() { // from class: org.nameless.gamespace.gamebar.GameBarService$takeShot$afterShot$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2() {
                Handler handler;
                GameBarService.this.setBarExpanded(false);
                handler = GameBarService.this.getHandler();
                final GameBarService gameBarService = GameBarService.this;
                handler.postDelayed(new Runnable() { // from class: org.nameless.gamespace.gamebar.GameBarService$takeShot$afterShot$1.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        GameBarService.this.updateLayout(new Function1<WindowManager.LayoutParams, Unit>() { // from class: org.nameless.gamespace.gamebar.GameBarService.takeShot.afterShot.1.1.1
                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(WindowManager.LayoutParams layoutParams) {
                                invoke2(layoutParams);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke  reason: avoid collision after fix types in other method */
                            public final void invoke2(WindowManager.LayoutParams it) {
                                Intrinsics.checkNotNullParameter(it, "it");
                                it.alpha = 1.0f;
                            }
                        });
                    }
                }, 100L);
            }
        };
        updateLayout(new Function1<WindowManager.LayoutParams, Unit>() { // from class: org.nameless.gamespace.gamebar.GameBarService$takeShot$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(WindowManager.LayoutParams layoutParams) {
                invoke2(layoutParams);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(WindowManager.LayoutParams it) {
                Intrinsics.checkNotNullParameter(it, "it");
                it.alpha = 0.0f;
            }
        });
        getHandler().postDelayed(new Runnable() { // from class: org.nameless.gamespace.gamebar.GameBarService$takeShot$2
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    ScreenUtils screenUtils = GameBarService.this.getScreenUtils();
                    final Function0<Unit> function02 = function0;
                    screenUtils.takeScreenshot(new Function1<Uri, Unit>() { // from class: org.nameless.gamespace.gamebar.GameBarService$takeShot$2.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Uri uri) {
                            invoke2(uri);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke  reason: avoid collision after fix types in other method */
                        public final void invoke2(Uri uri) {
                            function02.invoke();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    function0.invoke();
                }
            }
        }, 250L);
    }

    private final void menuSwitcherButton() {
        MenuSwitcher menuSwitcher = this.menuSwitcher;
        MenuSwitcher menuSwitcher2 = null;
        if (menuSwitcher == null) {
            Intrinsics.throwUninitializedPropertyAccessException("menuSwitcher");
            menuSwitcher = null;
        }
        menuSwitcher.setOnClickListener(new View.OnClickListener() { // from class: org.nameless.gamespace.gamebar.GameBarService$menuSwitcherButton$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                boolean z;
                GameBarService gameBarService = GameBarService.this;
                z = gameBarService.barExpanded;
                gameBarService.setBarExpanded(!z);
            }
        });
        MenuSwitcher menuSwitcher3 = this.menuSwitcher;
        if (menuSwitcher3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("menuSwitcher");
        } else {
            menuSwitcher2 = menuSwitcher3;
        }
        ExtensionsKt.registerDraggableTouchListener(menuSwitcher2, new Function0<Point>() { // from class: org.nameless.gamespace.gamebar.GameBarService$menuSwitcherButton$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Point invoke() {
                WindowManager.LayoutParams layoutParams;
                WindowManager.LayoutParams layoutParams2;
                layoutParams = GameBarService.this.barLayoutParam;
                int i = layoutParams.x;
                layoutParams2 = GameBarService.this.barLayoutParam;
                return new Point(i, layoutParams2.y);
            }
        }, new Function2<Integer, Integer, Unit>() { // from class: org.nameless.gamespace.gamebar.GameBarService$menuSwitcherButton$3
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Integer num, Integer num2) {
                invoke(num.intValue(), num2.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(final int i, final int i2) {
                MenuSwitcher menuSwitcher4;
                MenuSwitcher menuSwitcher5;
                LinearLayout linearLayout;
                menuSwitcher4 = GameBarService.this.menuSwitcher;
                LinearLayout linearLayout2 = null;
                if (menuSwitcher4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("menuSwitcher");
                    menuSwitcher4 = null;
                }
                if (!menuSwitcher4.isDragged()) {
                    menuSwitcher5 = GameBarService.this.menuSwitcher;
                    if (menuSwitcher5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("menuSwitcher");
                        menuSwitcher5 = null;
                    }
                    menuSwitcher5.setDragged(true);
                    linearLayout = GameBarService.this.barView;
                    if (linearLayout == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("barView");
                    } else {
                        linearLayout2 = linearLayout;
                    }
                    linearLayout2.setTranslationX(0.0f);
                }
                GameBarService.this.updateLayout(new Function1<WindowManager.LayoutParams, Unit>() { // from class: org.nameless.gamespace.gamebar.GameBarService$menuSwitcherButton$3.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(WindowManager.LayoutParams layoutParams) {
                        invoke2(layoutParams);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke  reason: avoid collision after fix types in other method */
                    public final void invoke2(WindowManager.LayoutParams it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                        it.x = i;
                        it.y = i2;
                    }
                });
                GameBarService.this.updateBackground();
            }
        }, new Function0<Unit>() { // from class: org.nameless.gamespace.gamebar.GameBarService$menuSwitcherButton$4
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2() {
                MenuSwitcher menuSwitcher4;
                WindowManager.LayoutParams layoutParams;
                WindowManager.LayoutParams layoutParams2;
                menuSwitcher4 = GameBarService.this.menuSwitcher;
                if (menuSwitcher4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("menuSwitcher");
                    menuSwitcher4 = null;
                }
                menuSwitcher4.setDragged(false);
                GameBarService.this.dockCollapsedMenu();
                GameBarService.this.updateBackground();
                AppSettings appSettings = GameBarService.this.getAppSettings();
                layoutParams = GameBarService.this.barLayoutParam;
                appSettings.setX(layoutParams.x);
                AppSettings appSettings2 = GameBarService.this.getAppSettings();
                layoutParams2 = GameBarService.this.barLayoutParam;
                appSettings2.setY(layoutParams2.y);
            }
        });
    }

    private final void panelButton() {
        View view = this.rootBarView;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootBarView");
            view = null;
        }
        ImageButton imageButton = (ImageButton) view.findViewById(R.id.action_panel);
        imageButton.setOnClickListener(new View.OnClickListener() { // from class: org.nameless.gamespace.gamebar.GameBarService$panelButton$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                boolean z;
                GameBarService gameBarService = GameBarService.this;
                z = gameBarService.showPanel;
                gameBarService.setShowPanel(!z);
            }
        });
        imageButton.setOnLongClickListener(new View.OnLongClickListener() { // from class: org.nameless.gamespace.gamebar.GameBarService$panelButton$2
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view2) {
                GameBarService.this.startActivity(new Intent(GameBarService.this, SettingsActivity.class).setFlags(268435456));
                return true;
            }
        });
    }

    private final void screenshotButton() {
        View view = this.rootBarView;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootBarView");
            view = null;
        }
        ((ImageButton) view.findViewById(R.id.action_screenshot)).setOnClickListener(new View.OnClickListener() { // from class: org.nameless.gamespace.gamebar.GameBarService$screenshotButton$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                GameBarService.this.takeShot();
            }
        });
    }

    private final void recorderButton() {
        View view = this.rootBarView;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootBarView");
            view = null;
        }
        final ImageButton actionRecorder = (ImageButton) view.findViewById(R.id.action_record);
        final IRemoteRecording recorder = getScreenUtils().getRecorder();
        if (recorder == null) {
            Intrinsics.checkNotNullExpressionValue(actionRecorder, "actionRecorder");
            actionRecorder.setVisibility(8);
            return;
        }
        recorder.addRecordingCallback(new IRecordingCallback.Stub() { // from class: org.nameless.gamespace.gamebar.GameBarService$recorderButton$1
            @Override // com.android.systemui.screenrecord.IRecordingCallback
            public void onRecordingStart() {
                Handler handler;
                handler = GameBarService.this.getHandler();
                final ImageButton imageButton = actionRecorder;
                handler.post(new Runnable() { // from class: org.nameless.gamespace.gamebar.GameBarService$recorderButton$1$onRecordingStart$1
                    @Override // java.lang.Runnable
                    public final void run() {
                        imageButton.setSelected(true);
                    }
                });
            }

            @Override // com.android.systemui.screenrecord.IRecordingCallback
            public void onRecordingEnd() {
                Handler handler;
                handler = GameBarService.this.getHandler();
                final ImageButton imageButton = actionRecorder;
                handler.post(new Runnable() { // from class: org.nameless.gamespace.gamebar.GameBarService$recorderButton$1$onRecordingEnd$1
                    @Override // java.lang.Runnable
                    public final void run() {
                        imageButton.setSelected(false);
                    }
                });
            }
        });
        actionRecorder.setOnClickListener(new View.OnClickListener() { // from class: org.nameless.gamespace.gamebar.GameBarService$recorderButton$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                if (IRemoteRecording.this.isStarting()) {
                    return;
                }
                if (!IRemoteRecording.this.isRecording()) {
                    IRemoteRecording.this.startRecording();
                } else {
                    IRemoteRecording.this.stopRecording();
                }
                this.setBarExpanded(false);
            }
        });
    }

    /* compiled from: GameBarService.kt */
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
