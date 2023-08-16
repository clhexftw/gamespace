package com.android.systemui.animation;

import android.content.Context;
import android.view.IRemoteAnimationFinishedCallback;
import android.view.IRemoteAnimationRunner;
import android.view.RemoteAnimationTarget;
import com.android.internal.annotations.VisibleForTesting;
/* compiled from: ActivityLaunchAnimator.kt */
@VisibleForTesting
/* loaded from: classes2.dex */
public final class ActivityLaunchAnimator$Runner extends IRemoteAnimationRunner.Stub {
    private final Context context;

    public final ActivityLaunchAnimator$AnimationDelegate getDelegate$frameworks__base__packages__SystemUI__animation__android_common__SystemUIAnimationLib() {
        return null;
    }

    public void onAnimationStart(final int i, final RemoteAnimationTarget[] remoteAnimationTargetArr, final RemoteAnimationTarget[] remoteAnimationTargetArr2, final RemoteAnimationTarget[] remoteAnimationTargetArr3, final IRemoteAnimationFinishedCallback iRemoteAnimationFinishedCallback) {
        this.context.getMainExecutor().execute(new Runnable() { // from class: com.android.systemui.animation.ActivityLaunchAnimator$Runner$onAnimationStart$1
            @Override // java.lang.Runnable
            public final void run() {
                ActivityLaunchAnimator$Runner.this.getDelegate$frameworks__base__packages__SystemUI__animation__android_common__SystemUIAnimationLib();
                throw null;
            }
        });
    }

    public void onAnimationCancelled(final boolean z) {
        this.context.getMainExecutor().execute(new Runnable() { // from class: com.android.systemui.animation.ActivityLaunchAnimator$Runner$onAnimationCancelled$1
            @Override // java.lang.Runnable
            public final void run() {
                ActivityLaunchAnimator$Runner.this.getDelegate$frameworks__base__packages__SystemUI__animation__android_common__SystemUIAnimationLib();
                throw null;
            }
        });
    }
}
