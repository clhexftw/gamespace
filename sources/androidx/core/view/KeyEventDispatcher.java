package androidx.core.view;

import android.annotation.SuppressLint;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
/* loaded from: classes.dex */
public class KeyEventDispatcher {

    /* loaded from: classes.dex */
    public interface Component {
        boolean superDispatchKeyEvent(KeyEvent keyEvent);
    }

    public static boolean dispatchBeforeHierarchy(View view, KeyEvent keyEvent) {
        return ViewCompat.dispatchUnhandledKeyEventBeforeHierarchy(view, keyEvent);
    }

    @SuppressLint({"LambdaLast"})
    public static boolean dispatchKeyEvent(Component component, View view, Window.Callback callback, KeyEvent keyEvent) {
        if (component == null) {
            return false;
        }
        return component.superDispatchKeyEvent(keyEvent);
    }
}
