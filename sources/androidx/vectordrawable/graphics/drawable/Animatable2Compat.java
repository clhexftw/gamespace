package androidx.vectordrawable.graphics.drawable;

import android.graphics.drawable.Animatable;
/* loaded from: classes.dex */
public interface Animatable2Compat extends Animatable {

    /* loaded from: classes.dex */
    public static abstract class AnimationCallback {
    }

    void clearAnimationCallbacks();

    void registerAnimationCallback(AnimationCallback animationCallback);
}
