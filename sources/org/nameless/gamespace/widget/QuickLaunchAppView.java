package org.nameless.gamespace.widget;

import android.app.ActivityManager;
import android.app.ActivityOptions;
import android.app.ActivityTaskManager;
import android.app.IActivityTaskManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.os.UserHandle;
import android.provider.Settings;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.nameless.gamespace.widget.tiles.AppTile;
/* compiled from: QuickLaunchAppView.kt */
/* loaded from: classes.dex */
public final class QuickLaunchAppView extends LinearLayout implements View.OnClickListener {
    public static final Companion Companion = new Companion(null);
    private final ActivityManager activityManager;
    private final ActivityOptions activityOptions;
    private final IActivityTaskManager activityTaskManager;
    private final PackageManager packageManager;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public QuickLaunchAppView(Context context) {
        this(context, null, 0, 0, 14, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public QuickLaunchAppView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 0, 12, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public QuickLaunchAppView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0, 8, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public /* synthetic */ QuickLaunchAppView(Context context, AttributeSet attributeSet, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? null : attributeSet, (i3 & 4) != 0 ? 0 : i, (i3 & 8) != 0 ? 0 : i2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QuickLaunchAppView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        Intrinsics.checkNotNullParameter(context, "context");
        Object systemService = context.getSystemService("activity");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.ActivityManager");
        this.activityManager = (ActivityManager) systemService;
        this.activityTaskManager = ActivityTaskManager.getService();
        this.activityOptions = ActivityOptions.makeBasic();
        this.packageManager = context.getPackageManager();
        updateAppList();
        ensureFreeformEnabled();
    }

    private final void updateAppList() {
        removeAllViewsInLayout();
        QuickLaunchAppViewCache quickLaunchAppViewCache = QuickLaunchAppViewCache.INSTANCE;
        if (quickLaunchAppViewCache.getAppCount() != 0) {
            setVisibility(0);
            for (String str : quickLaunchAppViewCache.getAppSet()) {
                Context context = getContext();
                Intrinsics.checkNotNullExpressionValue(context, "context");
                AppTile appTile = new AppTile(context, str, null, 0, 0, 28, null);
                appTile.setOnClickListener(this);
                addView(appTile);
            }
            return;
        }
        setVisibility(8);
    }

    private final void ensureFreeformEnabled() {
        Settings.Global.putInt(getContext().getContentResolver(), "force_resizable_activities", 1);
        Settings.Global.putInt(getContext().getContentResolver(), "enable_non_resizable_multi_window", 1);
    }

    private final boolean isPortrait() {
        return getContext().getResources().getConfiguration().orientation == 1;
    }

    private final void startActivity(String str) {
        int i;
        int i2;
        ComponentName componentName;
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        boolean isPortrait = isPortrait();
        if (isPortrait) {
            i2 = displayMetrics.widthPixels - 400;
            i = (i2 * 4) / 3;
        } else {
            i = displayMetrics.heightPixels - 200;
            i2 = (i * 3) / 4;
        }
        int i3 = i2 + 200;
        int i4 = isPortrait ? 400 : 50;
        this.activityOptions.setLaunchWindowingMode(5);
        this.activityOptions.setLaunchBounds(new Rect(200, i4, i3, i + i4));
        try {
            List<ActivityManager.RecentTaskInfo> recentTasks = this.activityManager.getRecentTasks(100, 2);
            Intrinsics.checkNotNullExpressionValue(recentTasks, "activityManager.getRecen…ECENT_IGNORE_UNAVAILABLE)");
            for (ActivityManager.RecentTaskInfo recentTaskInfo : recentTasks) {
                if (recentTaskInfo.isRunning && (componentName = recentTaskInfo.topActivity) != null && str.equals(componentName.getPackageName())) {
                    this.activityTaskManager.startActivityFromRecents(recentTaskInfo.taskId, this.activityOptions.toBundle());
                    return;
                }
            }
        } catch (Exception e) {
            Log.e("QuickLaunchAppView", "Failed to start " + str, e);
        }
        Intent launchIntentForPackage = this.packageManager.getLaunchIntentForPackage(str);
        launchIntentForPackage.addFlags(270532608);
        launchIntentForPackage.setPackage(null);
        getContext().startActivityAsUser(launchIntentForPackage, this.activityOptions.toBundle(), UserHandle.CURRENT);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        if (v instanceof AppTile) {
            startActivity(((AppTile) v).getApp());
            getContext().sendBroadcastAsUser(new Intent("org.nameless.gamespace.CLONE_PANEL"), UserHandle.CURRENT);
        }
    }

    /* compiled from: QuickLaunchAppView.kt */
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
