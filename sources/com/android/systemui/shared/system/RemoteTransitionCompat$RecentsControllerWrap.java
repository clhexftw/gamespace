package com.android.systemui.shared.system;

import android.os.IBinder;
import android.util.ArrayMap;
import android.view.SurfaceControl;
import android.window.IRemoteTransitionFinishedCallback;
import android.window.PictureInPictureSurfaceTransaction;
import android.window.TransitionInfo;
import android.window.WindowContainerToken;
import com.android.internal.annotations.VisibleForTesting;
import java.util.ArrayList;
@VisibleForTesting
/* loaded from: classes2.dex */
class RemoteTransitionCompat$RecentsControllerWrap extends RecentsAnimationControllerCompat {
    private RecentsAnimationControllerCompat mWrapped = null;
    private IRemoteTransitionFinishedCallback mFinishCB = null;
    private ArrayList<WindowContainerToken> mPausingTasks = null;
    private WindowContainerToken mPipTask = null;
    private WindowContainerToken mRecentsTask = null;
    private TransitionInfo mInfo = null;
    private ArrayList<SurfaceControl> mOpeningLeashes = null;
    private boolean mOpeningHome = false;
    private ArrayMap<SurfaceControl, SurfaceControl> mLeashMap = null;
    private PictureInPictureSurfaceTransaction mPipTransaction = null;
    private IBinder mTransition = null;
    private boolean mKeyguardLocked = false;
    private boolean mWillFinishToHome = false;

    RemoteTransitionCompat$RecentsControllerWrap() {
    }
}
