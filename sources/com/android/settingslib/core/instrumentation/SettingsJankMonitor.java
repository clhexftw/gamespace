package com.android.settingslib.core.instrumentation;

import android.view.View;
import com.android.internal.jank.InteractionJankMonitor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: SettingsJankMonitor.kt */
/* loaded from: classes.dex */
public final class SettingsJankMonitor {
    public static final SettingsJankMonitor INSTANCE = new SettingsJankMonitor();
    private static final InteractionJankMonitor jankMonitor = InteractionJankMonitor.getInstance();
    private static final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

    public static /* synthetic */ void getMONITORED_ANIMATION_DURATION_MS$annotations() {
    }

    private SettingsJankMonitor() {
    }

    public static final void detectToggleJank(String str, View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        InteractionJankMonitor.Configuration.Builder withView = InteractionJankMonitor.Configuration.Builder.withView(57, view);
        if (str != null) {
            withView.setTag(str);
        }
        if (jankMonitor.begin(withView)) {
            scheduledExecutorService.schedule(new Runnable() { // from class: com.android.settingslib.core.instrumentation.SettingsJankMonitor$detectToggleJank$1
                @Override // java.lang.Runnable
                public final void run() {
                    InteractionJankMonitor interactionJankMonitor;
                    interactionJankMonitor = SettingsJankMonitor.jankMonitor;
                    interactionJankMonitor.end(57);
                }
            }, 300L, TimeUnit.MILLISECONDS);
        }
    }
}
