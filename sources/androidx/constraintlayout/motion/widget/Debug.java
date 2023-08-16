package androidx.constraintlayout.motion.widget;

import android.annotation.SuppressLint;
import android.view.View;
@SuppressLint({"LogConditional"})
/* loaded from: classes.dex */
public class Debug {
    public static String getName(View view) {
        try {
            return view.getContext().getResources().getResourceEntryName(view.getId());
        } catch (Exception unused) {
            return "UNKNOWN";
        }
    }
}
