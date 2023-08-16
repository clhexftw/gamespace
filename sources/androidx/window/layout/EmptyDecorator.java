package androidx.window.layout;

import kotlin.jvm.internal.Intrinsics;
/* compiled from: WindowInfoTracker.kt */
/* loaded from: classes.dex */
final class EmptyDecorator implements WindowInfoTrackerDecorator {
    public static final EmptyDecorator INSTANCE = new EmptyDecorator();

    @Override // androidx.window.layout.WindowInfoTrackerDecorator
    public WindowInfoTracker decorate(WindowInfoTracker tracker) {
        Intrinsics.checkNotNullParameter(tracker, "tracker");
        return tracker;
    }

    private EmptyDecorator() {
    }
}
