package androidx.constraintlayout.core.motion.utils;
/* loaded from: classes.dex */
public class Easing {
    String mStr = "identity";
    static Easing sDefault = new Easing();
    public static String[] NAMED_EASING = {"standard", "accelerate", "decelerate", "linear"};

    public String toString() {
        return this.mStr;
    }
}
