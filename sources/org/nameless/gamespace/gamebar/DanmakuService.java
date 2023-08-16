package org.nameless.gamespace.gamebar;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.ComponentName;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.provider.Settings;
import android.service.notification.NotificationListenerService;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.nameless.gamespace.R;
import org.nameless.gamespace.data.AppSettings;
import org.nameless.wm.DisplayResolutionManager;
/* compiled from: DanmakuService.kt */
/* loaded from: classes.dex */
public final class DanmakuService {
    public static final Companion Companion = new Companion(null);
    private final AppSettings appSettings;
    private final Context context;
    private final boolean customResolutionSwitcher;
    private final Handler handler;
    private boolean isPortrait;
    private WindowManager.LayoutParams layoutParams;
    private final Listener notificationListener;
    private final TextView notificationOverlay;
    private final LinkedList<String> notificationStack;
    private ValueAnimator overlayAlphaAnimator;
    private ValueAnimator overlayPositionAnimator;
    private int verticalOffsetLandscape;
    private int verticalOffsetPortrait;
    private final WindowManager windowManager;

    public DanmakuService(Context context, AppSettings appSettings) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(appSettings, "appSettings");
        this.context = context;
        this.appSettings = appSettings;
        TextView textView = new TextView(context);
        textView.setGravity(17);
        textView.setMaxLines(2);
        textView.setTextColor(-1);
        textView.setFocusable(false);
        textView.setClickable(false);
        this.notificationOverlay = textView;
        this.customResolutionSwitcher = DisplayResolutionManager.getCustomResolutionSwitcherType() != 0;
        Object systemService = context.getSystemService(WindowManager.class);
        Intrinsics.checkNotNullExpressionValue(systemService, "context.getSystemService…indowManager::class.java)");
        this.windowManager = (WindowManager) systemService;
        this.handler = new Handler(Looper.getMainLooper());
        this.notificationStack = new LinkedList<>();
        this.notificationListener = new Listener();
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.height = -2;
        layoutParams.flags = layoutParams.flags | 8 | 16 | 16777216;
        layoutParams.type = 2015;
        layoutParams.format = -3;
        layoutParams.gravity = 48;
        this.layoutParams = layoutParams;
        this.isPortrait = context.getResources().getConfiguration().orientation == 1;
    }

    public final void init() {
        updateParams();
        registerListener();
    }

    public final void updateConfiguration(Configuration newConfig) {
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        this.isPortrait = newConfig.orientation == 1;
        ValueAnimator valueAnimator = this.overlayAlphaAnimator;
        if (valueAnimator != null) {
            valueAnimator.end();
        }
        ValueAnimator valueAnimator2 = this.overlayPositionAnimator;
        if (valueAnimator2 != null) {
            valueAnimator2.end();
        }
        updateParams();
        updateViewLayoutSafely(this.layoutParams);
    }

    public final void destroy() {
        unregisterListener();
        ValueAnimator valueAnimator = this.overlayAlphaAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        ValueAnimator valueAnimator2 = this.overlayPositionAnimator;
        if (valueAnimator2 != null) {
            valueAnimator2.cancel();
        }
        removeViewSafely();
    }

    private final void registerListener() {
        try {
            this.notificationListener.registerAsSystemService(this.context, new ComponentName(this.context, DanmakuService.class), -2);
        } catch (RemoteException unused) {
            Log.e("DanmakuService", "RemoteException while registering danmaku service");
        }
    }

    private final void unregisterListener() {
        try {
            this.notificationListener.unregisterAsSystemService();
        } catch (RemoteException unused) {
            Log.e("DanmakuService", "RemoteException while registering danmaku service");
        }
    }

    private final void updateParams() {
        Resources resources = this.context.getResources();
        this.verticalOffsetLandscape = resources.getDimensionPixelSize(R.dimen.notification_vertical_offset_landscape);
        this.verticalOffsetPortrait = resources.getDimensionPixelSize(R.dimen.notification_vertical_offset_portrait);
        this.layoutParams.y = getOffsetForPosition();
        this.layoutParams.width = (this.windowManager.getCurrentWindowMetrics().getBounds().width() * 75) / 100;
        this.notificationOverlay.setTextSize(0, 70 * getScale());
    }

    private final int getOffsetForPosition() {
        return this.isPortrait ? this.verticalOffsetPortrait : this.verticalOffsetLandscape;
    }

    private final float getScale() {
        return (this.customResolutionSwitcher && Settings.System.getInt(this.context.getContentResolver(), "display_resolution_width", 1080) == 1080) ? 0.75f : 1.0f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showNotificationAsOverlay(String str) {
        if (this.notificationOverlay.getParent() == null) {
            this.notificationOverlay.setAlpha(0.0f);
            this.notificationOverlay.setText(str);
            this.windowManager.addView(this.notificationOverlay, this.layoutParams);
            pushNotification();
            return;
        }
        this.notificationStack.add(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void pushNotification() {
        float offsetForPosition = getOffsetForPosition();
        ValueAnimator positionAnimator = getPositionAnimator(500L, 0.5f * offsetForPosition, offsetForPosition);
        positionAnimator.addListener(new Animator.AnimatorListener() { // from class: org.nameless.gamespace.gamebar.DanmakuService$pushNotification$lambda$4$$inlined$addListener$default$1
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                Intrinsics.checkNotNullParameter(animator, "animator");
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
                Intrinsics.checkNotNullParameter(animator, "animator");
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                Intrinsics.checkNotNullParameter(animator, "animator");
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                Handler handler;
                Intrinsics.checkNotNullParameter(animator, "animator");
                handler = DanmakuService.this.handler;
                final DanmakuService danmakuService = DanmakuService.this;
                handler.postDelayed(new Runnable() { // from class: org.nameless.gamespace.gamebar.DanmakuService$pushNotification$1$1$1
                    @Override // java.lang.Runnable
                    public final void run() {
                        DanmakuService.this.popNotification();
                    }
                }, 2000L);
            }
        });
        positionAnimator.start();
        this.overlayPositionAnimator = positionAnimator;
        startAlphaAnimation(500L, 0.0f, 1.0f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void popNotification() {
        float offsetForPosition = getOffsetForPosition();
        ValueAnimator positionAnimator = getPositionAnimator(300L, offsetForPosition, 1.5f * offsetForPosition);
        positionAnimator.addListener(new Animator.AnimatorListener() { // from class: org.nameless.gamespace.gamebar.DanmakuService$popNotification$lambda$6$$inlined$addListener$default$1
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                Intrinsics.checkNotNullParameter(animator, "animator");
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
                Intrinsics.checkNotNullParameter(animator, "animator");
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                Intrinsics.checkNotNullParameter(animator, "animator");
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                LinkedList linkedList;
                TextView textView;
                TextView textView2;
                LinkedList linkedList2;
                Intrinsics.checkNotNullParameter(animator, "animator");
                linkedList = DanmakuService.this.notificationStack;
                if (linkedList.isEmpty()) {
                    DanmakuService.this.removeViewSafely();
                    return;
                }
                textView = DanmakuService.this.notificationOverlay;
                textView.setAlpha(0.0f);
                textView2 = DanmakuService.this.notificationOverlay;
                linkedList2 = DanmakuService.this.notificationStack;
                textView2.setText((CharSequence) linkedList2.pop());
                DanmakuService.this.pushNotification();
            }
        });
        positionAnimator.start();
        this.overlayPositionAnimator = positionAnimator;
        startAlphaAnimation(300L, 1.0f, 0.0f);
    }

    private final ValueAnimator getPositionAnimator(long j, float... fArr) {
        final WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(this.layoutParams);
        ValueAnimator ofFloat = ValueAnimator.ofFloat(Arrays.copyOf(fArr, fArr.length));
        ofFloat.setDuration(j);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: org.nameless.gamespace.gamebar.DanmakuService$getPositionAnimator$1$1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                WindowManager.LayoutParams layoutParams2 = layoutParams;
                Object animatedValue = valueAnimator.getAnimatedValue();
                Intrinsics.checkNotNull(animatedValue, "null cannot be cast to non-null type kotlin.Float");
                layoutParams2.y = (int) ((Float) animatedValue).floatValue();
                this.updateViewLayoutSafely(layoutParams);
            }
        });
        Intrinsics.checkNotNullExpressionValue(ofFloat, "ofFloat(*values).apply {…)\n            }\n        }");
        return ofFloat;
    }

    private final void startAlphaAnimation(long j, float... fArr) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(Arrays.copyOf(fArr, fArr.length));
        ofFloat.setDuration(j);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: org.nameless.gamespace.gamebar.DanmakuService$startAlphaAnimation$1$1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                TextView textView;
                textView = DanmakuService.this.notificationOverlay;
                Object animatedValue = valueAnimator.getAnimatedValue();
                Intrinsics.checkNotNull(animatedValue, "null cannot be cast to non-null type kotlin.Float");
                textView.setAlpha(((Float) animatedValue).floatValue());
            }
        });
        ofFloat.start();
        this.overlayAlphaAnimator = ofFloat;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateViewLayoutSafely(WindowManager.LayoutParams layoutParams) {
        if (this.notificationOverlay.getParent() != null) {
            this.windowManager.updateViewLayout(this.notificationOverlay, layoutParams);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void removeViewSafely() {
        if (this.notificationOverlay.getParent() != null) {
            this.windowManager.removeViewImmediate(this.notificationOverlay);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: DanmakuService.kt */
    /* loaded from: classes.dex */
    public final class Listener extends NotificationListenerService {
        private final Map<String, Long> postedNotifications = new LinkedHashMap();

        public Listener() {
        }

        /* JADX WARN: Removed duplicated region for block: B:17:0x003d  */
        /* JADX WARN: Removed duplicated region for block: B:20:0x0047  */
        /* JADX WARN: Removed duplicated region for block: B:25:0x0053  */
        /* JADX WARN: Removed duplicated region for block: B:28:0x0074  */
        /* JADX WARN: Removed duplicated region for block: B:32:0x007e  */
        /* JADX WARN: Removed duplicated region for block: B:35:0x009a  */
        /* JADX WARN: Removed duplicated region for block: B:45:? A[RETURN, SYNTHETIC] */
        @Override // android.service.notification.NotificationListenerService
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void onNotificationPosted(android.service.notification.StatusBarNotification r7) {
            /*
                r6 = this;
                java.lang.String r0 = "sbn"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
                org.nameless.gamespace.gamebar.DanmakuService r0 = org.nameless.gamespace.gamebar.DanmakuService.this
                org.nameless.gamespace.data.AppSettings r0 = org.nameless.gamespace.gamebar.DanmakuService.access$getAppSettings$p(r0)
                int r0 = r0.getNotificationsMode()
                r1 = 2
                if (r0 == r1) goto L13
                return
            L13:
                boolean r0 = r7.isClearable()
                if (r0 == 0) goto Lbd
                boolean r0 = r7.isOngoing()
                if (r0 == 0) goto L21
                goto Lbd
            L21:
                android.app.Notification r0 = r7.getNotification()
                android.os.Bundle r0 = r0.extras
                java.lang.String r1 = "android.title"
                java.lang.String r1 = r0.getString(r1)
                r2 = 0
                r3 = 1
                if (r1 == 0) goto L3a
                boolean r4 = kotlin.text.StringsKt.isBlank(r1)
                r4 = r4 ^ r3
                if (r4 != r3) goto L3a
                r4 = r3
                goto L3b
            L3a:
                r4 = r2
            L3b:
                if (r4 != 0) goto L43
                java.lang.String r1 = "android.title.big"
                java.lang.String r1 = r0.getString(r1)
            L43:
                java.lang.String r4 = ""
                if (r1 == 0) goto L50
                boolean r5 = kotlin.text.StringsKt.isBlank(r1)
                r5 = r5 ^ r3
                if (r5 != r3) goto L50
                r5 = r3
                goto L51
            L50:
                r5 = r2
            L51:
                if (r5 == 0) goto L6c
                java.lang.StringBuilder r5 = new java.lang.StringBuilder
                r5.<init>()
                r5.append(r4)
                java.lang.String r4 = "["
                r5.append(r4)
                r5.append(r1)
                java.lang.String r1 = "] "
                r5.append(r1)
                java.lang.String r4 = r5.toString()
            L6c:
                java.lang.String r1 = "android.text"
                java.lang.String r0 = r0.getString(r1)
                if (r0 == 0) goto L7c
                boolean r1 = kotlin.text.StringsKt.isBlank(r0)
                r1 = r1 ^ r3
                if (r1 != r3) goto L7c
                r2 = r3
            L7c:
                if (r2 == 0) goto L8d
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                r1.append(r4)
                r1.append(r0)
                java.lang.String r4 = r1.toString()
            L8d:
                android.app.Notification r7 = r7.getNotification()
                long r0 = r7.when
                boolean r7 = kotlin.text.StringsKt.isBlank(r4)
                r7 = r7 ^ r3
                if (r7 == 0) goto Lbd
                java.util.Map<java.lang.String, java.lang.Long> r7 = r6.postedNotifications
                boolean r7 = r7.containsKey(r4)
                if (r7 == 0) goto Lb5
                java.util.Map<java.lang.String, java.lang.Long> r7 = r6.postedNotifications
                java.lang.Object r7 = r7.get(r4)
                java.lang.Long r7 = (java.lang.Long) r7
                if (r7 != 0) goto Lad
                goto Lb5
            Lad:
                long r2 = r7.longValue()
                int r7 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
                if (r7 == 0) goto Lbd
            Lb5:
                org.nameless.gamespace.gamebar.DanmakuService r7 = org.nameless.gamespace.gamebar.DanmakuService.this
                org.nameless.gamespace.gamebar.DanmakuService.access$showNotificationAsOverlay(r7, r4)
                r6.insertPostedNotification(r4, r0)
            Lbd:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.nameless.gamespace.gamebar.DanmakuService.Listener.onNotificationPosted(android.service.notification.StatusBarNotification):void");
        }

        private final void insertPostedNotification(String str, long j) {
            if (this.postedNotifications.size() >= 99) {
                this.postedNotifications.clear();
            }
            this.postedNotifications.put(str, Long.valueOf(j));
        }
    }

    /* compiled from: DanmakuService.kt */
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
