package androidx.dynamicanimation.animation;

import android.os.Handler;
import android.os.Looper;
import android.view.Choreographer;
/* loaded from: classes.dex */
public class AnimationHandler {
    private static final ThreadLocal<AnimationHandler> sAnimatorHandler = new ThreadLocal<>();
    public float mDurationScale;
    public DurationScaleChangeListener mDurationScaleChangeListener;

    /* loaded from: classes.dex */
    public interface DurationScaleChangeListener {
    }

    /* loaded from: classes.dex */
    public class DurationScaleChangeListener33 implements DurationScaleChangeListener {
    }

    /* loaded from: classes.dex */
    static final class FrameCallbackScheduler16 {
        private final Choreographer mChoreographer = Choreographer.getInstance();
        private final Looper mLooper = Looper.myLooper();

        FrameCallbackScheduler16() {
        }
    }

    /* loaded from: classes.dex */
    static class FrameCallbackScheduler14 {
        private final Handler mHandler = new Handler(Looper.myLooper());

        FrameCallbackScheduler14() {
        }
    }

    public float getDurationScale() {
        return this.mDurationScale;
    }
}
