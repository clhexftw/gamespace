package androidx.window.layout.adapter;

import android.content.Context;
import androidx.core.util.Consumer;
import androidx.window.layout.WindowLayoutInfo;
import java.util.concurrent.Executor;
/* compiled from: WindowBackend.kt */
/* loaded from: classes.dex */
public interface WindowBackend {
    void registerLayoutChangeCallback(Context context, Executor executor, Consumer<WindowLayoutInfo> consumer);

    void unregisterLayoutChangeCallback(Consumer<WindowLayoutInfo> consumer);
}
