package androidx.window.area;

import android.app.Activity;
import android.util.Log;
import androidx.window.core.BuildConfig;
import androidx.window.core.ExperimentalWindowApi;
import androidx.window.core.VerificationMode;
import androidx.window.extensions.WindowExtensionsProvider;
import androidx.window.extensions.area.WindowAreaComponent;
import java.util.concurrent.Executor;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.flow.Flow;
/* compiled from: WindowAreaController.kt */
@ExperimentalWindowApi
/* loaded from: classes.dex */
public interface WindowAreaController {
    public static final Companion Companion = Companion.$$INSTANCE;

    static WindowAreaController getOrCreate() {
        return Companion.getOrCreate();
    }

    static void overrideDecorator(WindowAreaControllerDecorator windowAreaControllerDecorator) {
        Companion.overrideDecorator(windowAreaControllerDecorator);
    }

    static void reset() {
        Companion.reset();
    }

    void rearDisplayMode(Activity activity, Executor executor, WindowAreaSessionCallback windowAreaSessionCallback);

    Flow<WindowAreaStatus> rearDisplayStatus();

    /* compiled from: WindowAreaController.kt */
    /* loaded from: classes.dex */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final String TAG = Reflection.getOrCreateKotlinClass(WindowAreaController.class).getSimpleName();
        private static WindowAreaControllerDecorator decorator = EmptyDecorator.INSTANCE;

        private Companion() {
        }

        public final WindowAreaController getOrCreate() {
            WindowAreaComponent windowAreaComponent;
            WindowAreaController windowAreaControllerImpl;
            try {
                windowAreaComponent = WindowExtensionsProvider.getWindowExtensions().getWindowAreaComponent();
            } catch (Throwable unused) {
                if (BuildConfig.INSTANCE.getVerificationMode() == VerificationMode.STRICT) {
                    Log.d(TAG, "Failed to load WindowExtensions");
                }
                windowAreaComponent = null;
            }
            if (windowAreaComponent == null) {
                windowAreaControllerImpl = new EmptyWindowAreaControllerImpl();
            } else {
                windowAreaControllerImpl = new WindowAreaControllerImpl(windowAreaComponent);
            }
            return decorator.decorate(windowAreaControllerImpl);
        }

        public final void overrideDecorator(WindowAreaControllerDecorator overridingDecorator) {
            Intrinsics.checkNotNullParameter(overridingDecorator, "overridingDecorator");
            decorator = overridingDecorator;
        }

        public final void reset() {
            decorator = EmptyDecorator.INSTANCE;
        }
    }
}
