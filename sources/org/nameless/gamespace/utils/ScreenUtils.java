package org.nameless.gamespace.utils;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.PowerManager;
import android.os.ServiceManager;
import android.os.UserHandle;
import android.view.IWindowManager;
import com.android.internal.util.ScreenshotHelper;
import com.android.systemui.screenrecord.IRemoteRecording;
import java.util.function.Consumer;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: ScreenUtils.kt */
/* loaded from: classes.dex */
public final class ScreenUtils {
    private final Context context;
    private int gestureLockedType;
    private boolean isRecorderBound;
    private int lockGesture;
    private final ScreenUtils$recorderConnection$1 recorderConnection;
    private IRemoteRecording remoteRecording;
    private boolean stayAwake;
    private PowerManager.WakeLock wakelock;
    private final IWindowManager windowManager;

    /* JADX WARN: Type inference failed for: r2v1, types: [org.nameless.gamespace.utils.ScreenUtils$recorderConnection$1] */
    public ScreenUtils(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.recorderConnection = new ServiceConnection() { // from class: org.nameless.gamespace.utils.ScreenUtils$recorderConnection$1
            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                try {
                    ScreenUtils.this.remoteRecording = IRemoteRecording.Stub.asInterface(iBinder);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.exit(1);
                    throw new RuntimeException("System.exit returned normally, while it was supposed to halt JVM.");
                }
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName componentName) {
                ScreenUtils.this.remoteRecording = null;
            }
        };
        this.windowManager = IWindowManager.Stub.asInterface(ServiceManager.getService("window"));
    }

    public final IRemoteRecording getRecorder() {
        return this.remoteRecording;
    }

    public final void bind() {
        Context context = this.context;
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.android.systemui", "com.android.systemui.screenrecord.RecordingService"));
        Unit unit = Unit.INSTANCE;
        boolean bindServiceAsUser = context.bindServiceAsUser(intent, this.recorderConnection, 1, UserHandle.CURRENT);
        this.isRecorderBound = bindServiceAsUser;
        if (!bindServiceAsUser) {
            System.exit(1);
            throw new RuntimeException("System.exit returned normally, while it was supposed to halt JVM.");
        }
        Object systemService = this.context.getSystemService("power");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.os.PowerManager");
        this.wakelock = ((PowerManager) systemService).newWakeLock(26, "GameSpace:ScreenUtils");
    }

    public final void unbind() {
        PowerManager.WakeLock wakeLock = this.wakelock;
        if (wakeLock != null) {
            if (!wakeLock.isHeld()) {
                wakeLock = null;
            }
            if (wakeLock != null) {
                wakeLock.release();
            }
        }
        if (this.isRecorderBound) {
            this.context.unbindService(this.recorderConnection);
        }
        this.remoteRecording = null;
        if (this.gestureLockedType != 0) {
            this.windowManager.setGesturesLocked(0);
            this.gestureLockedType = 0;
        }
    }

    public final void takeScreenshot(final Function1<? super Uri, Unit> function1) {
        final Handler handler = new Handler(Looper.getMainLooper());
        new ScreenshotHelper(this.context).takeScreenshot(1, 0, handler, new Consumer() { // from class: org.nameless.gamespace.utils.ScreenUtils$takeScreenshot$1
            @Override // java.util.function.Consumer
            public final void accept(final Uri uri) {
                Handler handler2 = handler;
                final Function1<Uri, Unit> function12 = function1;
                handler2.post(new Runnable() { // from class: org.nameless.gamespace.utils.ScreenUtils$takeScreenshot$1.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        Function1<Uri, Unit> function13 = function12;
                        if (function13 != null) {
                            function13.invoke(uri);
                        }
                    }
                });
            }
        });
    }

    @SuppressLint({"WakelockTimeout"})
    public final void setStayAwake(boolean z) {
        PowerManager.WakeLock wakeLock;
        this.stayAwake = z;
        if (z) {
            PowerManager.WakeLock wakeLock2 = this.wakelock;
            if (wakeLock2 != null) {
                wakeLock = wakeLock2.isHeld() ^ true ? wakeLock2 : null;
                if (wakeLock != null) {
                    wakeLock.acquire();
                    return;
                }
                return;
            }
            return;
        }
        PowerManager.WakeLock wakeLock3 = this.wakelock;
        if (wakeLock3 != null) {
            wakeLock = wakeLock3.isHeld() ? wakeLock3 : null;
            if (wakeLock != null) {
                wakeLock.release();
            }
        }
    }

    public final void setLockGesture(int i) {
        this.windowManager.setGesturesLocked(i);
        this.lockGesture = i;
        this.gestureLockedType = i;
    }
}
