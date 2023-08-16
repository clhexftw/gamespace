package com.android.settingslib.core.lifecycle;

import android.util.Log;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.OnLifecycleEvent;
/* loaded from: classes.dex */
public class Lifecycle extends LifecycleRegistry {
    /* renamed from: -$$Nest$monDestroy  reason: not valid java name */
    static /* bridge */ /* synthetic */ void m32$$Nest$monDestroy(Lifecycle lifecycle) {
        throw null;
    }

    /* renamed from: -$$Nest$monPause  reason: not valid java name */
    static /* bridge */ /* synthetic */ void m33$$Nest$monPause(Lifecycle lifecycle) {
        throw null;
    }

    /* renamed from: -$$Nest$monResume  reason: not valid java name */
    static /* bridge */ /* synthetic */ void m34$$Nest$monResume(Lifecycle lifecycle) {
        throw null;
    }

    /* renamed from: -$$Nest$monStart  reason: not valid java name */
    static /* bridge */ /* synthetic */ void m35$$Nest$monStart(Lifecycle lifecycle) {
        throw null;
    }

    /* renamed from: -$$Nest$monStop  reason: not valid java name */
    static /* bridge */ /* synthetic */ void m36$$Nest$monStop(Lifecycle lifecycle) {
        throw null;
    }

    /* renamed from: com.android.settingslib.core.lifecycle.Lifecycle$1  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$androidx$lifecycle$Lifecycle$Event;

        static {
            int[] iArr = new int[Lifecycle.Event.values().length];
            $SwitchMap$androidx$lifecycle$Lifecycle$Event = iArr;
            try {
                iArr[Lifecycle.Event.ON_CREATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$androidx$lifecycle$Lifecycle$Event[Lifecycle.Event.ON_START.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$androidx$lifecycle$Lifecycle$Event[Lifecycle.Event.ON_RESUME.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$androidx$lifecycle$Lifecycle$Event[Lifecycle.Event.ON_PAUSE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$androidx$lifecycle$Lifecycle$Event[Lifecycle.Event.ON_STOP.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$androidx$lifecycle$Lifecycle$Event[Lifecycle.Event.ON_DESTROY.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$androidx$lifecycle$Lifecycle$Event[Lifecycle.Event.ON_ANY.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    /* loaded from: classes.dex */
    private class LifecycleProxy implements LifecycleObserver {
        @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
        public void onLifecycleEvent(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
            switch (AnonymousClass1.$SwitchMap$androidx$lifecycle$Lifecycle$Event[event.ordinal()]) {
                case 2:
                    Lifecycle.m35$$Nest$monStart(null);
                    return;
                case 3:
                    Lifecycle.m34$$Nest$monResume(null);
                    return;
                case 4:
                    Lifecycle.m33$$Nest$monPause(null);
                    return;
                case 5:
                    Lifecycle.m36$$Nest$monStop(null);
                    return;
                case 6:
                    Lifecycle.m32$$Nest$monDestroy(null);
                    return;
                case 7:
                    Log.wtf("LifecycleObserver", "Should not receive an 'ANY' event!");
                    return;
                default:
                    return;
            }
        }
    }
}
