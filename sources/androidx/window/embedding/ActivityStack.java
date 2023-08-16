package androidx.window.embedding;

import android.app.Activity;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: ActivityStack.kt */
/* loaded from: classes.dex */
public final class ActivityStack {
    private final List<Activity> activitiesInProcess;
    private final boolean isEmpty;

    /* JADX WARN: Multi-variable type inference failed */
    public ActivityStack(List<? extends Activity> activitiesInProcess, boolean z) {
        Intrinsics.checkNotNullParameter(activitiesInProcess, "activitiesInProcess");
        this.activitiesInProcess = activitiesInProcess;
        this.isEmpty = z;
    }

    public final List<Activity> getActivitiesInProcess$window_release() {
        return this.activitiesInProcess;
    }

    public final boolean isEmpty() {
        return this.isEmpty;
    }

    public final boolean contains(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        return this.activitiesInProcess.contains(activity);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ActivityStack) {
            ActivityStack activityStack = (ActivityStack) obj;
            return Intrinsics.areEqual(this.activitiesInProcess, activityStack.activitiesInProcess) && this.isEmpty == activityStack.isEmpty;
        }
        return false;
    }

    public int hashCode() {
        return (this.activitiesInProcess.hashCode() * 31) + Boolean.hashCode(this.isEmpty);
    }

    public String toString() {
        return "ActivityStack{activitiesInProcess=" + this.activitiesInProcess + ", isEmpty=" + this.isEmpty + '}';
    }
}
