package androidx.window.area;

import android.app.Activity;
import android.util.Log;
import androidx.window.area.WindowAreaControllerImpl;
import androidx.window.core.BuildConfig;
import androidx.window.core.ExperimentalWindowApi;
import androidx.window.core.VerificationMode;
import androidx.window.extensions.area.WindowAreaComponent;
import androidx.window.extensions.core.util.function.Consumer;
import java.util.concurrent.Executor;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
/* compiled from: WindowAreaControllerImpl.kt */
@ExperimentalWindowApi
/* loaded from: classes.dex */
public final class WindowAreaControllerImpl implements WindowAreaController {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = Reflection.getOrCreateKotlinClass(WindowAreaControllerImpl.class).getSimpleName();
    private WindowAreaStatus currentStatus;
    private final WindowAreaComponent windowAreaComponent;

    public WindowAreaControllerImpl(WindowAreaComponent windowAreaComponent) {
        Intrinsics.checkNotNullParameter(windowAreaComponent, "windowAreaComponent");
        this.windowAreaComponent = windowAreaComponent;
    }

    @Override // androidx.window.area.WindowAreaController
    public Flow<WindowAreaStatus> rearDisplayStatus() {
        return FlowKt.distinctUntilChanged(FlowKt.callbackFlow(new WindowAreaControllerImpl$rearDisplayStatus$1(this, null)));
    }

    @Override // androidx.window.area.WindowAreaController
    public void rearDisplayMode(Activity activity, Executor executor, WindowAreaSessionCallback windowAreaSessionCallback) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(executor, "executor");
        Intrinsics.checkNotNullParameter(windowAreaSessionCallback, "windowAreaSessionCallback");
        WindowAreaStatus windowAreaStatus = this.currentStatus;
        if (windowAreaStatus != null && !Intrinsics.areEqual(windowAreaStatus, WindowAreaStatus.AVAILABLE)) {
            throw new UnsupportedOperationException("Rear Display mode cannot be enabled currently");
        }
        this.windowAreaComponent.startRearDisplaySession(activity, new RearDisplaySessionConsumer(executor, windowAreaSessionCallback, this.windowAreaComponent));
    }

    /* compiled from: WindowAreaControllerImpl.kt */
    @SourceDebugExtension({"SMAP\nWindowAreaControllerImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 WindowAreaControllerImpl.kt\nandroidx/window/area/WindowAreaControllerImpl$RearDisplaySessionConsumer\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,118:1\n1#2:119\n*E\n"})
    /* loaded from: classes.dex */
    public static final class RearDisplaySessionConsumer implements Consumer<Integer> {
        private final WindowAreaSessionCallback appCallback;
        private final Executor executor;
        private final WindowAreaComponent extensionsComponent;
        private WindowAreaSession session;

        public RearDisplaySessionConsumer(Executor executor, WindowAreaSessionCallback appCallback, WindowAreaComponent extensionsComponent) {
            Intrinsics.checkNotNullParameter(executor, "executor");
            Intrinsics.checkNotNullParameter(appCallback, "appCallback");
            Intrinsics.checkNotNullParameter(extensionsComponent, "extensionsComponent");
            this.executor = executor;
            this.appCallback = appCallback;
            this.extensionsComponent = extensionsComponent;
        }

        @Override // androidx.window.extensions.core.util.function.Consumer
        public /* bridge */ /* synthetic */ void accept(Integer num) {
            accept(num.intValue());
        }

        public void accept(int i) {
            if (i == 0) {
                onSessionFinished();
            } else if (i == 1) {
                onSessionStarted();
            } else {
                if (BuildConfig.INSTANCE.getVerificationMode() == VerificationMode.STRICT) {
                    String str = WindowAreaControllerImpl.TAG;
                    Log.d(str, "Received an unknown session status value: " + i);
                }
                onSessionFinished();
            }
        }

        private final void onSessionStarted() {
            final RearDisplaySessionImpl rearDisplaySessionImpl = new RearDisplaySessionImpl(this.extensionsComponent);
            this.session = rearDisplaySessionImpl;
            this.executor.execute(new Runnable() { // from class: androidx.window.area.WindowAreaControllerImpl$RearDisplaySessionConsumer$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    WindowAreaControllerImpl.RearDisplaySessionConsumer.onSessionStarted$lambda$1$lambda$0(WindowAreaControllerImpl.RearDisplaySessionConsumer.this, rearDisplaySessionImpl);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onSessionStarted$lambda$1$lambda$0(RearDisplaySessionConsumer this$0, WindowAreaSession it) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(it, "$it");
            this$0.appCallback.onSessionStarted(it);
        }

        private final void onSessionFinished() {
            this.session = null;
            this.executor.execute(new Runnable() { // from class: androidx.window.area.WindowAreaControllerImpl$RearDisplaySessionConsumer$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    WindowAreaControllerImpl.RearDisplaySessionConsumer.onSessionFinished$lambda$2(WindowAreaControllerImpl.RearDisplaySessionConsumer.this);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onSessionFinished$lambda$2(RearDisplaySessionConsumer this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.appCallback.onSessionEnded();
        }
    }

    /* compiled from: WindowAreaControllerImpl.kt */
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
