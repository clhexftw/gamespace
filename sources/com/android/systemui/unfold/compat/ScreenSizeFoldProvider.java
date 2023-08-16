package com.android.systemui.unfold.compat;

import android.content.Context;
import android.content.res.Configuration;
import com.android.systemui.unfold.updates.FoldProvider$FoldCallback;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: ScreenSizeFoldProvider.kt */
/* loaded from: classes2.dex */
public final class ScreenSizeFoldProvider {
    private List<FoldProvider$FoldCallback> callbacks;
    private boolean isFolded;

    public ScreenSizeFoldProvider(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.callbacks = new ArrayList();
        Configuration configuration = context.getResources().getConfiguration();
        Intrinsics.checkNotNullExpressionValue(configuration, "context.resources.configuration");
        onConfigurationChange(configuration);
    }

    public void registerCallback(FoldProvider$FoldCallback callback, Executor executor) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        Intrinsics.checkNotNullParameter(executor, "executor");
        this.callbacks.add(callback);
        callback.onFoldUpdated(this.isFolded);
    }

    public void unregisterCallback(FoldProvider$FoldCallback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.callbacks.remove(callback);
    }

    public final void onConfigurationChange(Configuration newConfig) {
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        boolean z = newConfig.smallestScreenWidthDp < 600;
        if (z == this.isFolded) {
            return;
        }
        this.isFolded = z;
        for (FoldProvider$FoldCallback foldProvider$FoldCallback : this.callbacks) {
            foldProvider$FoldCallback.onFoldUpdated(this.isFolded);
        }
    }
}
